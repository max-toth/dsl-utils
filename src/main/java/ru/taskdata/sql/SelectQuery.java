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
    public String build() {
        StringBuilder builder = new StringBuilder();

        builder.append(SELECT);
        builder.append(fieldsToString());
        builder.append(FROM);
        builder.append(schema).append(this.table.toString());
        for (Table table : innerJoinTables.keySet()) {
            builder.append(INNER_JOIN).append(schema).append(table.toString()).append(ON).append(innerJoinTables.get(table).toString());
        }

        for (Table table : leftJoinTables.keySet()) {
            builder.append(LEFT_JOIN).append(schema).append(table.toString()).append(ON).append(leftJoinTables.get(table).toString());
        }

        if (!this.conditions.isEmpty()) {
            builder.append(WHERE);
            builder.append(conditionsToString());
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
