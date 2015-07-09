package ru.taskdata.sql;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public interface Query {
    Query from(String table);
    Query from(Table table);
    Query where(Condition condition);

    String build();

    Query and(Condition condition);
    Query or(Condition condition);

    String SELECT = "SELECT ";
    String FROM = " FROM ";
    String WHERE = " WHERE ";
    String AND = " AND ";
    String OR = " OR ";

    Query innerJoin(Table table, Condition condition);

    Query leftJoin(Table table, Condition condition);
}
