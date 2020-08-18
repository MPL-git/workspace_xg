package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberPvExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberPvExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPvIdIsNull() {
            addCriterion("pv_id is null");
            return (Criteria) this;
        }

        public Criteria andPvIdIsNotNull() {
            addCriterion("pv_id is not null");
            return (Criteria) this;
        }

        public Criteria andPvIdEqualTo(String value) {
            addCriterion("pv_id =", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdNotEqualTo(String value) {
            addCriterion("pv_id <>", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdGreaterThan(String value) {
            addCriterion("pv_id >", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdGreaterThanOrEqualTo(String value) {
            addCriterion("pv_id >=", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdLessThan(String value) {
            addCriterion("pv_id <", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdLessThanOrEqualTo(String value) {
            addCriterion("pv_id <=", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdLike(String value) {
            addCriterion("pv_id like", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdNotLike(String value) {
            addCriterion("pv_id not like", value, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdIn(List<String> values) {
            addCriterion("pv_id in", values, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdNotIn(List<String> values) {
            addCriterion("pv_id not in", values, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdBetween(String value1, String value2) {
            addCriterion("pv_id between", value1, value2, "pvId");
            return (Criteria) this;
        }

        public Criteria andPvIdNotBetween(String value1, String value2) {
            addCriterion("pv_id not between", value1, value2, "pvId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberIsNull() {
            addCriterion("device_number is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberIsNotNull() {
            addCriterion("device_number is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberEqualTo(String value) {
            addCriterion("device_number =", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotEqualTo(String value) {
            addCriterion("device_number <>", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberGreaterThan(String value) {
            addCriterion("device_number >", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberGreaterThanOrEqualTo(String value) {
            addCriterion("device_number >=", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberLessThan(String value) {
            addCriterion("device_number <", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberLessThanOrEqualTo(String value) {
            addCriterion("device_number <=", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberLike(String value) {
            addCriterion("device_number like", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotLike(String value) {
            addCriterion("device_number not like", value, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberIn(List<String> values) {
            addCriterion("device_number in", values, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotIn(List<String> values) {
            addCriterion("device_number not in", values, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberBetween(String value1, String value2) {
            addCriterion("device_number between", value1, value2, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNumberNotBetween(String value1, String value2) {
            addCriterion("device_number not between", value1, value2, "deviceNumber");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIsNull() {
            addCriterion("column_type is null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIsNotNull() {
            addCriterion("column_type is not null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeEqualTo(String value) {
            addCriterion("column_type =", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotEqualTo(String value) {
            addCriterion("column_type <>", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThan(String value) {
            addCriterion("column_type >", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("column_type >=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThan(String value) {
            addCriterion("column_type <", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThanOrEqualTo(String value) {
            addCriterion("column_type <=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLike(String value) {
            addCriterion("column_type like", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotLike(String value) {
            addCriterion("column_type not like", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIn(List<String> values) {
            addCriterion("column_type in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotIn(List<String> values) {
            addCriterion("column_type not in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeBetween(String value1, String value2) {
            addCriterion("column_type between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotBetween(String value1, String value2) {
            addCriterion("column_type not between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNull() {
            addCriterion("column_id is null");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNotNull() {
            addCriterion("column_id is not null");
            return (Criteria) this;
        }

        public Criteria andColumnIdEqualTo(Integer value) {
            addCriterion("column_id =", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotEqualTo(Integer value) {
            addCriterion("column_id <>", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThan(Integer value) {
            addCriterion("column_id >", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_id >=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThan(Integer value) {
            addCriterion("column_id <", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThanOrEqualTo(Integer value) {
            addCriterion("column_id <=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIn(List<Integer> values) {
            addCriterion("column_id in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotIn(List<Integer> values) {
            addCriterion("column_id not in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdBetween(Integer value1, Integer value2) {
            addCriterion("column_id between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("column_id not between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andPageClassifyIsNull() {
            addCriterion("page_classify is null");
            return (Criteria) this;
        }

        public Criteria andPageClassifyIsNotNull() {
            addCriterion("page_classify is not null");
            return (Criteria) this;
        }

        public Criteria andPageClassifyEqualTo(String value) {
            addCriterion("page_classify =", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyNotEqualTo(String value) {
            addCriterion("page_classify <>", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyGreaterThan(String value) {
            addCriterion("page_classify >", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyGreaterThanOrEqualTo(String value) {
            addCriterion("page_classify >=", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyLessThan(String value) {
            addCriterion("page_classify <", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyLessThanOrEqualTo(String value) {
            addCriterion("page_classify <=", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyLike(String value) {
            addCriterion("page_classify like", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyNotLike(String value) {
            addCriterion("page_classify not like", value, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyIn(List<String> values) {
            addCriterion("page_classify in", values, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyNotIn(List<String> values) {
            addCriterion("page_classify not in", values, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyBetween(String value1, String value2) {
            addCriterion("page_classify between", value1, value2, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageClassifyNotBetween(String value1, String value2) {
            addCriterion("page_classify not between", value1, value2, "pageClassify");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNull() {
            addCriterion("page_type is null");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNotNull() {
            addCriterion("page_type is not null");
            return (Criteria) this;
        }

        public Criteria andPageTypeEqualTo(String value) {
            addCriterion("page_type =", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotEqualTo(String value) {
            addCriterion("page_type <>", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThan(String value) {
            addCriterion("page_type >", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("page_type >=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThan(String value) {
            addCriterion("page_type <", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThanOrEqualTo(String value) {
            addCriterion("page_type <=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLike(String value) {
            addCriterion("page_type like", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotLike(String value) {
            addCriterion("page_type not like", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeIn(List<String> values) {
            addCriterion("page_type in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotIn(List<String> values) {
            addCriterion("page_type not in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeBetween(String value1, String value2) {
            addCriterion("page_type between", value1, value2, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotBetween(String value1, String value2) {
            addCriterion("page_type not between", value1, value2, "pageType");
            return (Criteria) this;
        }

        public Criteria andMchtIdIsNull() {
            addCriterion("mcht_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtIdIsNotNull() {
            addCriterion("mcht_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtIdEqualTo(Integer value) {
            addCriterion("mcht_id =", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotEqualTo(Integer value) {
            addCriterion("mcht_id <>", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThan(Integer value) {
            addCriterion("mcht_id >", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_id >=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThan(Integer value) {
            addCriterion("mcht_id <", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_id <=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdIn(List<Integer> values) {
            addCriterion("mcht_id in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotIn(List<Integer> values) {
            addCriterion("mcht_id not in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id not between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andValueIdIsNull() {
            addCriterion("value_id is null");
            return (Criteria) this;
        }

        public Criteria andValueIdIsNotNull() {
            addCriterion("value_id is not null");
            return (Criteria) this;
        }

        public Criteria andValueIdEqualTo(Integer value) {
            addCriterion("value_id =", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotEqualTo(Integer value) {
            addCriterion("value_id <>", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdGreaterThan(Integer value) {
            addCriterion("value_id >", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("value_id >=", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdLessThan(Integer value) {
            addCriterion("value_id <", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdLessThanOrEqualTo(Integer value) {
            addCriterion("value_id <=", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdIn(List<Integer> values) {
            addCriterion("value_id in", values, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotIn(List<Integer> values) {
            addCriterion("value_id not in", values, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdBetween(Integer value1, Integer value2) {
            addCriterion("value_id between", value1, value2, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotBetween(Integer value1, Integer value2) {
            addCriterion("value_id not between", value1, value2, "valueId");
            return (Criteria) this;
        }

        public Criteria andClientSourceIsNull() {
            addCriterion("client_source is null");
            return (Criteria) this;
        }

        public Criteria andClientSourceIsNotNull() {
            addCriterion("client_source is not null");
            return (Criteria) this;
        }

        public Criteria andClientSourceEqualTo(String value) {
            addCriterion("client_source =", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotEqualTo(String value) {
            addCriterion("client_source <>", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceGreaterThan(String value) {
            addCriterion("client_source >", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceGreaterThanOrEqualTo(String value) {
            addCriterion("client_source >=", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLessThan(String value) {
            addCriterion("client_source <", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLessThanOrEqualTo(String value) {
            addCriterion("client_source <=", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceLike(String value) {
            addCriterion("client_source like", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotLike(String value) {
            addCriterion("client_source not like", value, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceIn(List<String> values) {
            addCriterion("client_source in", values, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotIn(List<String> values) {
            addCriterion("client_source not in", values, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceBetween(String value1, String value2) {
            addCriterion("client_source between", value1, value2, "clientSource");
            return (Criteria) this;
        }

        public Criteria andClientSourceNotBetween(String value1, String value2) {
            addCriterion("client_source not between", value1, value2, "clientSource");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeIsNull() {
            addCriterion("from_page_type is null");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeIsNotNull() {
            addCriterion("from_page_type is not null");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeEqualTo(String value) {
            addCriterion("from_page_type =", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeNotEqualTo(String value) {
            addCriterion("from_page_type <>", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeGreaterThan(String value) {
            addCriterion("from_page_type >", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("from_page_type >=", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeLessThan(String value) {
            addCriterion("from_page_type <", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeLessThanOrEqualTo(String value) {
            addCriterion("from_page_type <=", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeLike(String value) {
            addCriterion("from_page_type like", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeNotLike(String value) {
            addCriterion("from_page_type not like", value, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeIn(List<String> values) {
            addCriterion("from_page_type in", values, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeNotIn(List<String> values) {
            addCriterion("from_page_type not in", values, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeBetween(String value1, String value2) {
            addCriterion("from_page_type between", value1, value2, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPageTypeNotBetween(String value1, String value2) {
            addCriterion("from_page_type not between", value1, value2, "fromPageType");
            return (Criteria) this;
        }

        public Criteria andFromPvIdIsNull() {
            addCriterion("from_pv_id is null");
            return (Criteria) this;
        }

        public Criteria andFromPvIdIsNotNull() {
            addCriterion("from_pv_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromPvIdEqualTo(String value) {
            addCriterion("from_pv_id =", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdNotEqualTo(String value) {
            addCriterion("from_pv_id <>", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdGreaterThan(String value) {
            addCriterion("from_pv_id >", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdGreaterThanOrEqualTo(String value) {
            addCriterion("from_pv_id >=", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdLessThan(String value) {
            addCriterion("from_pv_id <", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdLessThanOrEqualTo(String value) {
            addCriterion("from_pv_id <=", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdLike(String value) {
            addCriterion("from_pv_id like", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdNotLike(String value) {
            addCriterion("from_pv_id not like", value, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdIn(List<String> values) {
            addCriterion("from_pv_id in", values, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdNotIn(List<String> values) {
            addCriterion("from_pv_id not in", values, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdBetween(String value1, String value2) {
            addCriterion("from_pv_id between", value1, value2, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andFromPvIdNotBetween(String value1, String value2) {
            addCriterion("from_pv_id not between", value1, value2, "fromPvId");
            return (Criteria) this;
        }

        public Criteria andStayTimeIsNull() {
            addCriterion("stay_time is null");
            return (Criteria) this;
        }

        public Criteria andStayTimeIsNotNull() {
            addCriterion("stay_time is not null");
            return (Criteria) this;
        }

        public Criteria andStayTimeEqualTo(Integer value) {
            addCriterion("stay_time =", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotEqualTo(Integer value) {
            addCriterion("stay_time <>", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeGreaterThan(Integer value) {
            addCriterion("stay_time >", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("stay_time >=", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeLessThan(Integer value) {
            addCriterion("stay_time <", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeLessThanOrEqualTo(Integer value) {
            addCriterion("stay_time <=", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeIn(List<Integer> values) {
            addCriterion("stay_time in", values, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotIn(List<Integer> values) {
            addCriterion("stay_time not in", values, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeBetween(Integer value1, Integer value2) {
            addCriterion("stay_time between", value1, value2, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("stay_time not between", value1, value2, "stayTime");
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