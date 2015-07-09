package ru.taskdata.sql;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public class Table {
    private String alias = StringUtils.EMPTY;
    private String table;

    public Table(String table) {
        this.table = table;
    }

    public Table as(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public String toString() {
        String result = table;
        if (!alias.isEmpty()) result += " " + alias;
        return result;
    }

    public String getAlias() {
        return alias;
    }
}
