package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductExtendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ProductExtendExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize=limitSize;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsIsNull() {
            addCriterion("storage_conditions is null");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsIsNotNull() {
            addCriterion("storage_conditions is not null");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsEqualTo(String value) {
            addCriterion("storage_conditions =", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotEqualTo(String value) {
            addCriterion("storage_conditions <>", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsGreaterThan(String value) {
            addCriterion("storage_conditions >", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("storage_conditions >=", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsLessThan(String value) {
            addCriterion("storage_conditions <", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsLessThanOrEqualTo(String value) {
            addCriterion("storage_conditions <=", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsLike(String value) {
            addCriterion("storage_conditions like", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotLike(String value) {
            addCriterion("storage_conditions not like", value, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsIn(List<String> values) {
            addCriterion("storage_conditions in", values, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotIn(List<String> values) {
            addCriterion("storage_conditions not in", values, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsBetween(String value1, String value2) {
            addCriterion("storage_conditions between", value1, value2, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andStorageConditionsNotBetween(String value1, String value2) {
            addCriterion("storage_conditions not between", value1, value2, "storageConditions");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginIsNull() {
            addCriterion("place_of_origin is null");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginIsNotNull() {
            addCriterion("place_of_origin is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginEqualTo(String value) {
            addCriterion("place_of_origin =", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotEqualTo(String value) {
            addCriterion("place_of_origin <>", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginGreaterThan(String value) {
            addCriterion("place_of_origin >", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginGreaterThanOrEqualTo(String value) {
            addCriterion("place_of_origin >=", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginLessThan(String value) {
            addCriterion("place_of_origin <", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginLessThanOrEqualTo(String value) {
            addCriterion("place_of_origin <=", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginLike(String value) {
            addCriterion("place_of_origin like", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotLike(String value) {
            addCriterion("place_of_origin not like", value, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginIn(List<String> values) {
            addCriterion("place_of_origin in", values, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotIn(List<String> values) {
            addCriterion("place_of_origin not in", values, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginBetween(String value1, String value2) {
            addCriterion("place_of_origin between", value1, value2, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andPlaceOfOriginNotBetween(String value1, String value2) {
            addCriterion("place_of_origin not between", value1, value2, "placeOfOrigin");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicIsNull() {
            addCriterion("food_label_pic is null");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicIsNotNull() {
            addCriterion("food_label_pic is not null");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicEqualTo(String value) {
            addCriterion("food_label_pic =", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicNotEqualTo(String value) {
            addCriterion("food_label_pic <>", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicGreaterThan(String value) {
            addCriterion("food_label_pic >", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicGreaterThanOrEqualTo(String value) {
            addCriterion("food_label_pic >=", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicLessThan(String value) {
            addCriterion("food_label_pic <", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicLessThanOrEqualTo(String value) {
            addCriterion("food_label_pic <=", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicLike(String value) {
            addCriterion("food_label_pic like", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicNotLike(String value) {
            addCriterion("food_label_pic not like", value, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicIn(List<String> values) {
            addCriterion("food_label_pic in", values, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicNotIn(List<String> values) {
            addCriterion("food_label_pic not in", values, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicBetween(String value1, String value2) {
            addCriterion("food_label_pic between", value1, value2, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andFoodLabelPicNotBetween(String value1, String value2) {
            addCriterion("food_label_pic not between", value1, value2, "foodLabelPic");
            return (Criteria) this;
        }

        public Criteria andProducerInformationIsNull() {
            addCriterion("producer_information is null");
            return (Criteria) this;
        }

        public Criteria andProducerInformationIsNotNull() {
            addCriterion("producer_information is not null");
            return (Criteria) this;
        }

        public Criteria andProducerInformationEqualTo(String value) {
            addCriterion("producer_information =", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationNotEqualTo(String value) {
            addCriterion("producer_information <>", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationGreaterThan(String value) {
            addCriterion("producer_information >", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationGreaterThanOrEqualTo(String value) {
            addCriterion("producer_information >=", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationLessThan(String value) {
            addCriterion("producer_information <", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationLessThanOrEqualTo(String value) {
            addCriterion("producer_information <=", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationLike(String value) {
            addCriterion("producer_information like", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationNotLike(String value) {
            addCriterion("producer_information not like", value, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationIn(List<String> values) {
            addCriterion("producer_information in", values, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationNotIn(List<String> values) {
            addCriterion("producer_information not in", values, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationBetween(String value1, String value2) {
            addCriterion("producer_information between", value1, value2, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andProducerInformationNotBetween(String value1, String value2) {
            addCriterion("producer_information not between", value1, value2, "producerInformation");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberIsNull() {
            addCriterion("approval_number is null");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberIsNotNull() {
            addCriterion("approval_number is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberEqualTo(String value) {
            addCriterion("approval_number =", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotEqualTo(String value) {
            addCriterion("approval_number <>", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberGreaterThan(String value) {
            addCriterion("approval_number >", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberGreaterThanOrEqualTo(String value) {
            addCriterion("approval_number >=", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberLessThan(String value) {
            addCriterion("approval_number <", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberLessThanOrEqualTo(String value) {
            addCriterion("approval_number <=", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberLike(String value) {
            addCriterion("approval_number like", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotLike(String value) {
            addCriterion("approval_number not like", value, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberIn(List<String> values) {
            addCriterion("approval_number in", values, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotIn(List<String> values) {
            addCriterion("approval_number not in", values, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberBetween(String value1, String value2) {
            addCriterion("approval_number between", value1, value2, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andApprovalNumberNotBetween(String value1, String value2) {
            addCriterion("approval_number not between", value1, value2, "approvalNumber");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}