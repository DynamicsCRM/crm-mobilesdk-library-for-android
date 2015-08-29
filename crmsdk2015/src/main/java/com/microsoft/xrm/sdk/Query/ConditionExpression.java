//package com.microsoft.xrm.sdk.Query;
//
//import com.microsoft.xrm.sdk.DataCollection;
//
//import java.util.Arrays;
//import java.util.Collection;
//
///**
// * Created on 3/6/2015.
// */
//public final class ConditionExpression {
//    private String attributeName;
//    private ConditionOperator conditionOperator;
//    private DataCollection<Object> values;
//    private String entityName;
//
//    public ConditionExpression() {
//
//    }
//
//    public ConditionExpression(String attributeName, ConditionOperator conditionOperator, Object... values) {
//        this((String)null, attributeName, conditionOperator, values);
//    }
//
//    public ConditionExpression(String entityName, String attributeName, ConditionOperator conditionOperator, Object... values) {
//        this.entityName = entityName;
//        this.attributeName = attributeName;
//        this.conditionOperator = conditionOperator;
//        if (values == null)
//            return;
//        this.values = new DataCollection<Object>(Arrays.asList(values));
//    }
//
//    public ConditionExpression(String attributeName, ConditionOperator conditionOperator, Object value) {
//        this((String)null, attributeName, conditionOperator, value);
//    }
//
//    public ConditionExpression(String entityName, String attributeName, ConditionOperator conditionOperator, Object value) {
//        this (entityName, attributeName, conditionOperator, new Object[]{value});
//    }
//
//    public ConditionExpression(String attributeName, ConditionOperator conditionOperator) {
//        this((String) null, attributeName, conditionOperator, new Object[0]);
//    }
//
//    public ConditionExpression(String entityName, String attributeName, ConditionOperator conditionOperator) {
//        this(entityName, attributeName, conditionOperator, new Object[0]);
//    }
//
//    public ConditionExpression(String attributeName, ConditionOperator conditionOperator, Collection values) {
//        this.attributeName = attributeName;
//        this.conditionOperator = conditionOperator;
//        if (values == null)
//            return;
//
//        this.values = new DataCollection<Object>();
//        this.values.addAll(values);
//    }
//
//
//    public String getEntityName() {
//        return this.entityName;
//    }
//
//    public void setEntityName(String value) {
//        this.entityName = value;
//    }
//
//    public String getAttributeName() {
//        return this.attributeName;
//    }
//
//    public void setAttributeName(String value) {
//        this.attributeName = value;
//    }
//
//    public ConditionOperator getConditionOperator() {
//        return this.conditionOperator;
//    }
//
//    public void setConditionOperator(ConditionOperator value) {
//        this.conditionOperator = value;
//    }
//
//    public DataCollection<Object> getValues() {
//        if(this.values == null) {
//            this.values = new DataCollection<Object>();
//        }
//
//        return this.values;
//    }
//}
