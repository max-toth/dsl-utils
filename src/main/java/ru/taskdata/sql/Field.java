package ru.taskdata.sql;

import org.apache.commons.lang3.StringUtils;

import static ru.taskdata.sql.SQLConstants.*;

/**
 * @author mtolstykh
 * @since 24.06.2015.
 */
public class Field {
    private String placeholder = StringUtils.EMPTY;
    private String field;

    public String getPlaceholder() {
        if (this.placeholder.isEmpty()) return StringUtils.EMPTY;
        return AS + placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Field(String field) {
        this.field = field;
    }

    public Condition eq(Field field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field.getField());
        condition.setOperator(EQ);
        return condition;
    }

    public Condition eq(String field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field);
        condition.setOperator(EQ);
        return condition;
    }

    public Condition eq(Object value) {
        return null;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Condition lt(String field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field);
        condition.setOperator(LT);
        return condition;
    }

    public Condition lt(Field field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field.getField());
        condition.setOperator(EQ);
        return condition;
    }

    public Condition lte(Field field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field.getField());
        condition.setOperator(LTE);
        return condition;
    }

    public Condition lte(String field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field);
        condition.setOperator(LTE);
        return condition;
    }

    public Condition gt(String field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field);
        condition.setOperator(LT);
        return condition;
    }

    public Condition gt(Field field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field.getField());
        condition.setOperator(GT);
        return condition;
    }

    public Condition gte(Field field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field.getField());
        condition.setOperator(GTE);
        return condition;
    }

    public Condition gte(String field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field);
        condition.setOperator(GTE);
        return condition;
    }

    public Condition neq(String field) {
        Condition condition = new Condition();
        condition.setArg1(this.field);
        condition.setArg2(field);
        condition.setOperator(NEQ);
        return condition;
    }

    public Field as(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }
}
