package ru.taskdata.sql;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import static ru.taskdata.sql.SQL.*;
import static ru.taskdata.sql.SQLConstants.*;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public class SelectQuery implements Query {

    private String schema = StringUtils.EMPTY;

    private Limit limit;
    private Offset offset;
    private List<Field> fields = new ArrayList<>();
    private Table table;
    private Map<Table, Condition> innerJoinTables = new HashMap<>();
    private Map<Table, Condition> leftJoinTables = new HashMap<>();
    private List<Condition> conditions = new ArrayList<>();

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public Query from(String table) {
        this.table = new Table(table);
        return this;
    }

    @Override
    public Query from(Table table) {
        this.table = table;
        return this;
    }

    @Override
    public Query where(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    @Override
    public String build() throws Exception {
        StringBuilder builder = new StringBuilder();

        builder.append(SELECT);

        if (fields.isEmpty()) throw new Exception("Fields for SELECT query is EMPTY. Set up array of fields or put '*' instead.");
        builder.append(fieldsToString());

        builder.append(FROM);

        if (table == null) throw new Exception("Table for SELECT query is NULL.");
        builder.append(schema).append(this.table.toString());

        for (Table table : innerJoinTables.keySet()) {
            Condition condition = innerJoinTables.get(table);
            if (condition == null) {
                throw new Exception("Condition for table " + table.toString() + " is NULL.");
            }
            builder.append(INNER_JOIN).append(schema).append(table.toString()).append(ON).append(condition.toString());
        }

        for (Table table : leftJoinTables.keySet()) {
            Condition condition = leftJoinTables.get(table);
            if (condition == null) {
                throw new Exception("Condition for table " + table.toString() + " is NULL.");
            }
            builder.append(LEFT_JOIN).append(schema).append(table.toString()).append(ON).append(condition.toString());
        }

        if (!this.conditions.isEmpty()) {
            builder.append(WHERE);
            builder.append(conditionsToString());
        }

        if (this.limit != null) {
            if (this.limit.getUseValue()) {
                Integer value = this.limit.getValue();
                if (value == null) throw new Exception("Value for limit not set.");
                builder.append(LIMIT).append(value);
            } else {
                String limitPlaceholder = this.limit.getPlaceholder();
                if (limitPlaceholder == null) throw new Exception("Placeholder for limit not set.");
                builder.append(LIMIT).append(":").append(limitPlaceholder);
            }
        }

        if (this.offset != null) {
            if (this.offset.getUseValue()) {
                Integer value = this.offset.getValue();
                if (value == null) throw new Exception("Value for offset not set.");
                builder.append(OFFSET).append(value);
            } else {
                String offsetPlaceholder = this.offset.getPlaceholder();
                if (offsetPlaceholder == null) throw new Exception("Placeholder for offset not set.");
                builder.append(OFFSET).append(":").append(offsetPlaceholder);
            }
        }

        return builder.toString();
    }

    @Override
    public Query and(Condition condition) {
        condition.setPrefix(AND);
        this.conditions.add(condition);
        return this;
    }

    @Override
    public Query or(Condition condition) {
        condition.setPrefix(OR);
        this.conditions.add(condition);
        return this;
    }

    @Override
    public Query innerJoin(Table table, Condition condition) {
        this.innerJoinTables.put(table, condition);
        return this;
    }

    @Override
    public Query leftJoin(Table table, Condition condition) {
        this.leftJoinTables.put(table, condition);
        return this;
    }

    @Override
    public Query limit(String placeholder) {
        this.limit = new Limit();
        this.limit.setPlaceholder(placeholder);
        this.limit.setUseValue(false);
        return this;
    }

    @Override
    public Query limit(Integer value) {
        this.limit = new Limit();
        this.limit.setValue(value);
        this.limit.setUseValue(true);
        return this;
    }

    @Override
    public Query offset(String placeholder) {
        this.offset = new Offset();
        this.offset.setPlaceholder(placeholder);
        this.offset.setUseValue(false);
        return this;
    }

    @Override
    public Query offset(Integer value) {
        this.offset = new Offset();
        this.offset.setValue(value);
        this.offset.setUseValue(true);
        return this;
    }

    @Override
    public Boolean hasConditions() {
        return !this.conditions.isEmpty();
    }

    private String conditionsToString() {
        StringBuilder builder = new StringBuilder();
        for (Condition condition : this.conditions) {
            builder.append(condition.getPrefix()).append(condition.getArg1())
                    .append(condition.getOperator())
                    .append(condition.getArg2());
        }

        return builder.toString();
    }

    private String fieldsToString() {
        StringBuilder builder = new StringBuilder();
        for (Field field : this.fields) {
            builder.append(field.getField()).append(field.getPlaceholder());
            if (fields.indexOf(field) < fields.size() - 1 )
                builder.append(", ");
        }

        return builder.toString();
    }

    public void setFields(String[] fields) {
        for (String field: fields) {
            this.fields.add(new Field(field));
        }
    }

    public void setFields(Field[] fields) {
        Collections.addAll(this.fields, fields);
    }
}
