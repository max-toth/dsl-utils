package ru.taskdata.sql;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public class SQL {

    public static Field field(String field) {
        return new Field(field);
    }

    public static Field field(String table, String field) {
        return new Field(table + "." +field);
    }

    public static Field field(Table table, String field) {
        String alias = table.getAlias();
        if (StringUtils.isEmpty(alias)) try {
            throw new Exception("Please set up alias for table [" + table.toString() + "] first.");
        } catch (Exception e) {
            e.printStackTrace();
            return new Field(field);
        }
        return new Field(alias + "." + field);
    }

    public static Table table(String table) {
        return new Table(table);
    }

    public static Condition condition(String field1, String operator, String field2) {
        Condition condition = new Condition();
        condition.setArg1(field1);
        condition.setArg2(field2);
        condition.setOperator(operator);

        return condition;
    }

    public static Condition condition(String prefix, String field1, String operator, String field2) {
        Condition condition = condition(field1, operator, field2);
        condition.setPrefix(prefix);
        return condition;
    }

    public static Condition condition(Field field1, String operator, Field field2) {
        Condition condition = new Condition();
        condition.setArg1(field1.getField());
        condition.setArg2(field2.getField());
        condition.setOperator(operator);

        return condition;
    }

    public static Condition condition(String prefix, Field field1, String operator, Field field2) {
        Condition condition = condition(field1, operator, field2);
        condition.setPrefix(prefix);
        return condition;
    }

    public static Condition condition(String table) {
        return new Condition();
    }

    public static Query select(String ... fields) {
        SelectQuery query = new SelectQuery();
        query.setFields(fields);
        return query;
    }

    public static Query select(Field ... fields) {
        SelectQuery query = new SelectQuery();
        query.setFields(fields);
        return query;
    }

    public static Condition on(Field field1, String operator, Field field2) {
        Condition condition = new Condition();
        condition.setArg1(field1.getField());
        condition.setArg2(field2.getField());
        condition.setOperator(operator);

        return condition;
    }
}
