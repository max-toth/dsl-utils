package ru.taskdata.sql;

/**
 * @author mtolstykh
 * @since 13.07.2015.
 */
public class Offset {
    private String placeholder;
    private Integer value;
    private Boolean useValue;

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getUseValue() {
        return useValue;
    }

    public void setUseValue(Boolean useValue) {
        this.useValue = useValue;
    }
}
