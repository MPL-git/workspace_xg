package com.jf.common.ext.core;

import com.jf.common.ext.util.JsonKit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 17-4-8.
 * @author hdl
 */
public abstract class Model implements Serializable {

    private static final long serialVersionUID = -1;

    /**
     * Attributes of this model
     */
    private Map<String, Object> attrs = new HashMap<String, Object>();

    /**
     * Put key value pair to the model without check attribute name.
     */
    public void put(String key, Object value) {
        attrs.put(key, value);
    }

    /**
     * Put map to the model without check attribute name.
     */
    public void put(Map<String, Object> map) {
        attrs.putAll(map);
    }

    /**
     * Get attribute of any mysql type
     */
    public <T> T get(String attr) {
        return (T)(attrs.get(attr));
    }

    /**
     * Get attribute of any mysql type. Returns defaultValue if null.
     */
    public <T> T get(String attr, Object defaultValue) {
        Object result = attrs.get(attr);
        return (T)(result != null ? result : defaultValue);
    }

    /**
     * Get attribute of mysql type: varchar, char, enum, set, text, tinytext, mediumtext, longtext
     */
    public String getStr(String attr) {
        return (String)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: int, integer, tinyint(n) n > 1, smallint, mediumint
     */
    public Integer getInt(String attr) {
        return (Integer)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: bigint, unsign int
     */
    public Long getLong(String attr) {
        return (Long)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: unsigned bigint
     */
    public java.math.BigInteger getBigInteger(String attr) {
        return (java.math.BigInteger)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: date, year
     */
    public java.util.Date getDate(String attr) {
        return (java.util.Date)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: time
     */
    public java.sql.Time getTime(String attr) {
        return (java.sql.Time)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: timestamp, datetime
     */
    public java.sql.Timestamp getTimestamp(String attr) {
        return (java.sql.Timestamp)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: real, double
     */
    public Double getDouble(String attr) {
        return (Double)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: float
     */
    public Float getFloat(String attr) {
        return (Float)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: bit, tinyint(1)
     */
    public Boolean getBoolean(String attr) {
        return (Boolean)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: decimal, numeric
     */
    public java.math.BigDecimal getBigDecimal(String attr) {
        return (java.math.BigDecimal)attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: binary, varbinary, tinyblob, blob, mediumblob, longblob
     */
    public byte[] getBytes(String attr) {
        return (byte[])attrs.get(attr);
    }

    /**
     * Get attribute of any type that extends from Number
     */
    public Number getNumber(String attr) {
        return (Number)attrs.get(attr);
    }

    /**
     * Return attribute Map.
     * <p>
     * Danger! The update method will ignore the attribute if you change it directly.
     * You must use set method to change attribute that update method can handle it.
     */
    protected Map<String, Object> getAttrs() {
        return attrs;
    }

    /**
     * Return attribute Set.
     */
//    public Set<Map.Entry<String, Object>> getAttrsEntrySet() {
//        return attrs.entrySet();
//    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(" {");
        boolean first = true;
        for (Map.Entry<String, Object> e : attrs.entrySet()) {
            if (first)
                first = false;
            else
                sb.append(", ");

            Object value = e.getValue();
            if (value != null)
                value = value.toString();
            sb.append(e.getKey()).append(":").append(value);
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Return json string of this model.
     */
    public String toJson() {
        return JsonKit.toJson(attrs, 4);
    }


}
