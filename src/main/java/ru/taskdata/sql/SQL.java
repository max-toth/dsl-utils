package ru.taskdata.sql;

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
        return new Field(table.getAlias() + "." + field);
    }

    public static Table table(String table) {
        return new Table(table);
    }

    public static Condition condition(String table) {
        return new Condition();
    }

    public static Query select(String ... fields) {
        return null;
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
