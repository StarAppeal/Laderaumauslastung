package de.starappeal.laderaumauslastung.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class FilterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5975613752337556887L;
    private String key;
    private Operator operator;
    private Object value;
    private FieldType fieldType;
    private List<Object> values;

    public FilterRequest() {
    }

    public FilterRequest(String key, Operator operator, Object value, FieldType fieldType,
                         List<Object> values) {
        this.key = key;
        this.operator = operator;
        this.value = value;
        this.fieldType = fieldType;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FilterRequest) obj;
        return Objects.equals(this.key, that.key) &&
                Objects.equals(this.operator, that.operator) &&
                Objects.equals(this.value, that.value) &&
                Objects.equals(this.fieldType, that.fieldType) &&
                Objects.equals(this.values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, operator, value, fieldType, values);
    }

    @Override
    public String toString() {
        return "FilterRequest[" +
                "key=" + key + ", " +
                "operator=" + operator + ", " +
                "value=" + value + ", " +
                "fieldType=" + fieldType + ", " +
                "values=" + values + ']';
    }


}
