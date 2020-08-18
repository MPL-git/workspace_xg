package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerServiceRecordsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CustomerServiceRecordsExample() {
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

        public Criteria andBusinessCirclesAppealOrderIdIsNull() {
            addCriterion("business_circles_appeal_order_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdIsNotNull() {
            addCriterion("business_circles_appeal_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdEqualTo(Integer value) {
            addCriterion("business_circles_appeal_order_id =", value, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdNotEqualTo(Integer value) {
            addCriterion("business_circles_appeal_order_id <>", value, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdGreaterThan(Integer value) {
            addCriterion("business_circles_appeal_order_id >", value, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_circles_appeal_order_id >=", value, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdLessThan(Integer value) {
            addCriterion("business_circles_appeal_order_id <", value, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_circles_appeal_order_id <=", value, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdIn(List<Integer> values) {
            addCriterion("business_circles_appeal_order_id in", values, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdNotIn(List<Integer> values) {
            addCriterion("business_circles_appeal_order_id not in", values, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("business_circles_appeal_order_id between", value1, value2, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andBusinessCirclesAppealOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_circles_appeal_order_id not between", value1, value2, "businessCirclesAppealOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateIsNull() {
            addCriterion("mcht_deal_date is null");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateIsNotNull() {
            addCriterion("mcht_deal_date is not null");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateEqualTo(Date value) {
            addCriterion("mcht_deal_date =", value, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateNotEqualTo(Date value) {
            addCriterion("mcht_deal_date <>", value, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateGreaterThan(Date value) {
            addCriterion("mcht_deal_date >", value, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateGreaterThanOrEqualTo(Date value) {
            addCriterion("mcht_deal_date >=", value, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateLessThan(Date value) {
            addCriterion("mcht_deal_date <", value, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateLessThanOrEqualTo(Date value) {
            addCriterion("mcht_deal_date <=", value, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateIn(List<Date> values) {
            addCriterion("mcht_deal_date in", values, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateNotIn(List<Date> values) {
            addCriterion("mcht_deal_date not in", values, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateBetween(Date value1, Date value2) {
            addCriterion("mcht_deal_date between", value1, value2, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtDealDateNotBetween(Date value1, Date value2) {
            addCriterion("mcht_deal_date not between", value1, value2, "mchtDealDate");
            return (Criteria) this;
        }

        public Criteria andMchtComplainIsNull() {
            addCriterion("mcht_complain is null");
            return (Criteria) this;
        }

        public Criteria andMchtComplainIsNotNull() {
            addCriterion("mcht_complain is not null");
            return (Criteria) this;
        }

        public Criteria andMchtComplainEqualTo(String value) {
            addCriterion("mcht_complain =", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainNotEqualTo(String value) {
            addCriterion("mcht_complain <>", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainGreaterThan(String value) {
            addCriterion("mcht_complain >", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_complain >=", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainLessThan(String value) {
            addCriterion("mcht_complain <", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainLessThanOrEqualTo(String value) {
            addCriterion("mcht_complain <=", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainLike(String value) {
            addCriterion("mcht_complain like", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainNotLike(String value) {
            addCriterion("mcht_complain not like", value, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainIn(List<String> values) {
            addCriterion("mcht_complain in", values, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainNotIn(List<String> values) {
            addCriterion("mcht_complain not in", values, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainBetween(String value1, String value2) {
            addCriterion("mcht_complain between", value1, value2, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtComplainNotBetween(String value1, String value2) {
            addCriterion("mcht_complain not between", value1, value2, "mchtComplain");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressIsNull() {
            addCriterion("mcht_processing_progress is null");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressIsNotNull() {
            addCriterion("mcht_processing_progress is not null");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressEqualTo(String value) {
            addCriterion("mcht_processing_progress =", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressNotEqualTo(String value) {
            addCriterion("mcht_processing_progress <>", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressGreaterThan(String value) {
            addCriterion("mcht_processing_progress >", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_processing_progress >=", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressLessThan(String value) {
            addCriterion("mcht_processing_progress <", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressLessThanOrEqualTo(String value) {
            addCriterion("mcht_processing_progress <=", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressLike(String value) {
            addCriterion("mcht_processing_progress like", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressNotLike(String value) {
            addCriterion("mcht_processing_progress not like", value, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressIn(List<String> values) {
            addCriterion("mcht_processing_progress in", values, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressNotIn(List<String> values) {
            addCriterion("mcht_processing_progress not in", values, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressBetween(String value1, String value2) {
            addCriterion("mcht_processing_progress between", value1, value2, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andMchtProcessingProgressNotBetween(String value1, String value2) {
            addCriterion("mcht_processing_progress not between", value1, value2, "mchtProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateIsNull() {
            addCriterion("platform_deal_date is null");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateIsNotNull() {
            addCriterion("platform_deal_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateEqualTo(Date value) {
            addCriterion("platform_deal_date =", value, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateNotEqualTo(Date value) {
            addCriterion("platform_deal_date <>", value, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateGreaterThan(Date value) {
            addCriterion("platform_deal_date >", value, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateGreaterThanOrEqualTo(Date value) {
            addCriterion("platform_deal_date >=", value, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateLessThan(Date value) {
            addCriterion("platform_deal_date <", value, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateLessThanOrEqualTo(Date value) {
            addCriterion("platform_deal_date <=", value, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateIn(List<Date> values) {
            addCriterion("platform_deal_date in", values, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateNotIn(List<Date> values) {
            addCriterion("platform_deal_date not in", values, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateBetween(Date value1, Date value2) {
            addCriterion("platform_deal_date between", value1, value2, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformDealDateNotBetween(Date value1, Date value2) {
            addCriterion("platform_deal_date not between", value1, value2, "platformDealDate");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressIsNull() {
            addCriterion("platform_processing_progress is null");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressIsNotNull() {
            addCriterion("platform_processing_progress is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressEqualTo(String value) {
            addCriterion("platform_processing_progress =", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressNotEqualTo(String value) {
            addCriterion("platform_processing_progress <>", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressGreaterThan(String value) {
            addCriterion("platform_processing_progress >", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressGreaterThanOrEqualTo(String value) {
            addCriterion("platform_processing_progress >=", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressLessThan(String value) {
            addCriterion("platform_processing_progress <", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressLessThanOrEqualTo(String value) {
            addCriterion("platform_processing_progress <=", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressLike(String value) {
            addCriterion("platform_processing_progress like", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressNotLike(String value) {
            addCriterion("platform_processing_progress not like", value, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressIn(List<String> values) {
            addCriterion("platform_processing_progress in", values, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressNotIn(List<String> values) {
            addCriterion("platform_processing_progress not in", values, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressBetween(String value1, String value2) {
            addCriterion("platform_processing_progress between", value1, value2, "platformProcessingProgress");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessingProgressNotBetween(String value1, String value2) {
            addCriterion("platform_processing_progress not between", value1, value2, "platformProcessingProgress");
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