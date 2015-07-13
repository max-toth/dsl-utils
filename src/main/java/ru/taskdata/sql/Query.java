package ru.taskdata.sql;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public interface Query {
    Query from(String table);
    Query from(Table table);
    Query where(Condition condition);

    String build() throws Exception;

    Query and(Condition condition);
    Query or(Condition condition);



    Query innerJoin(Table table, Condition condition);

    Query leftJoin(Table table, Condition condition);

    Query limit(String placeholder);

    Query limit(Integer value);

    Query offset(String placeholder);

    Query offset(Integer value);
}
