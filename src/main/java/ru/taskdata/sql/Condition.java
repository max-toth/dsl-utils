package ru.taskdata.sql;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public class Condition {
    private String prefix = StringUtils.EMPTY;
    private String arg1;
    private String arg2;
    private String operator;

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg1() {
        return arg1;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg2() {
        return arg2;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return prefix + arg1 + operator + arg2;
    }
}
