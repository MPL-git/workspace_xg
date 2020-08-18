package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberPvMiddleLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberPvMiddleLogExample() {
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

        public Criteria andBatchDateIsNull() {
            addCriterion("batch_date is null");
            return (Criteria) this;
        }

        public Criteria andBatchDateIsNotNull() {
            addCriterion("batch_date is not null");
            return (Criteria) this;
        }

        public Criteria andBatchDateEqualTo(String value) {
            addCriterion("batch_date =", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotEqualTo(String value) {
            addCriterion("batch_date <>", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateGreaterThan(String value) {
            addCriterion("batch_date >", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateGreaterThanOrEqualTo(String value) {
            addCriterion("batch_date >=", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLessThan(String value) {
            addCriterion("batch_date <", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLessThanOrEqualTo(String value) {
            addCriterion("batch_date <=", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLike(String value) {
            addCriterion("batch_date like", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotLike(String value) {
            addCriterion("batch_date not like", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateIn(List<String> values) {
            addCriterion("batch_date in", values, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotIn(List<String> values) {
            addCriterion("batch_date not in", values, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateBetween(String value1, String value2) {
            addCriterion("batch_date between", value1, value2, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotBetween(String value1, String value2) {
            addCriterion("batch_date not between", value1, value2, "batchDate");
            return (Criteria) this;
        }

        public Criteria andParseTypeIsNull() {
            addCriterion("parse_type is null");
            return (Criteria) this;
        }

        public Criteria andParseTypeIsNotNull() {
            addCriterion("parse_type is not null");
            return (Criteria) this;
        }

        public Criteria andParseTypeEqualTo(String value) {
            addCriterion("parse_type =", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeNotEqualTo(String value) {
            addCriterion("parse_type <>", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeGreaterThan(String value) {
            addCriterion("parse_type >", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("parse_type >=", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeLessThan(String value) {
            addCriterion("parse_type <", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeLessThanOrEqualTo(String value) {
            addCriterion("parse_type <=", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeLike(String value) {
            addCriterion("parse_type like", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeNotLike(String value) {
            addCriterion("parse_type not like", value, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeIn(List<String> values) {
            addCriterion("parse_type in", values, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeNotIn(List<String> values) {
            addCriterion("parse_type not in", values, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeBetween(String value1, String value2) {
            addCriterion("parse_type between", value1, value2, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseTypeNotBetween(String value1, String value2) {
            addCriterion("parse_type not between", value1, value2, "parseType");
            return (Criteria) this;
        }

        public Criteria andParseStartDateIsNull() {
            addCriterion("parse_start_date is null");
            return (Criteria) this;
        }

        public Criteria andParseStartDateIsNotNull() {
            addCriterion("parse_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andParseStartDateEqualTo(Date value) {
            addCriterion("parse_start_date =", value, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateNotEqualTo(Date value) {
            addCriterion("parse_start_date <>", value, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateGreaterThan(Date value) {
            addCriterion("parse_start_date >", value, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("parse_start_date >=", value, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateLessThan(Date value) {
            addCriterion("parse_start_date <", value, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateLessThanOrEqualTo(Date value) {
            addCriterion("parse_start_date <=", value, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateIn(List<Date> values) {
            addCriterion("parse_start_date in", values, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateNotIn(List<Date> values) {
            addCriterion("parse_start_date not in", values, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateBetween(Date value1, Date value2) {
            addCriterion("parse_start_date between", value1, value2, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseStartDateNotBetween(Date value1, Date value2) {
            addCriterion("parse_start_date not between", value1, value2, "parseStartDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateIsNull() {
            addCriterion("parse_end_date is null");
            return (Criteria) this;
        }

        public Criteria andParseEndDateIsNotNull() {
            addCriterion("parse_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andParseEndDateEqualTo(Date value) {
            addCriterion("parse_end_date =", value, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateNotEqualTo(Date value) {
            addCriterion("parse_end_date <>", value, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateGreaterThan(Date value) {
            addCriterion("parse_end_date >", value, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("parse_end_date >=", value, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateLessThan(Date value) {
            addCriterion("parse_end_date <", value, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("parse_end_date <=", value, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateIn(List<Date> values) {
            addCriterion("parse_end_date in", values, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateNotIn(List<Date> values) {
            addCriterion("parse_end_date not in", values, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateBetween(Date value1, Date value2) {
            addCriterion("parse_end_date between", value1, value2, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andParseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("parse_end_date not between", value1, value2, "parseEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeIsNull() {
            addCriterion("statistics_type is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeIsNotNull() {
            addCriterion("statistics_type is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeEqualTo(String value) {
            addCriterion("statistics_type =", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotEqualTo(String value) {
            addCriterion("statistics_type <>", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeGreaterThan(String value) {
            addCriterion("statistics_type >", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("statistics_type >=", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeLessThan(String value) {
            addCriterion("statistics_type <", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeLessThanOrEqualTo(String value) {
            addCriterion("statistics_type <=", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeLike(String value) {
            addCriterion("statistics_type like", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotLike(String value) {
            addCriterion("statistics_type not like", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeIn(List<String> values) {
            addCriterion("statistics_type in", values, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotIn(List<String> values) {
            addCriterion("statistics_type not in", values, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeBetween(String value1, String value2) {
            addCriterion("statistics_type between", value1, value2, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotBetween(String value1, String value2) {
            addCriterion("statistics_type not between", value1, value2, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateIsNull() {
            addCriterion("statistics_start_date is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateIsNotNull() {
            addCriterion("statistics_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateEqualTo(Date value) {
            addCriterion("statistics_start_date =", value, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateNotEqualTo(Date value) {
            addCriterion("statistics_start_date <>", value, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateGreaterThan(Date value) {
            addCriterion("statistics_start_date >", value, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("statistics_start_date >=", value, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateLessThan(Date value) {
            addCriterion("statistics_start_date <", value, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateLessThanOrEqualTo(Date value) {
            addCriterion("statistics_start_date <=", value, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateIn(List<Date> values) {
            addCriterion("statistics_start_date in", values, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateNotIn(List<Date> values) {
            addCriterion("statistics_start_date not in", values, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateBetween(Date value1, Date value2) {
            addCriterion("statistics_start_date between", value1, value2, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsStartDateNotBetween(Date value1, Date value2) {
            addCriterion("statistics_start_date not between", value1, value2, "statisticsStartDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateIsNull() {
            addCriterion("statistics_end_date is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateIsNotNull() {
            addCriterion("statistics_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateEqualTo(Date value) {
            addCriterion("statistics_end_date =", value, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateNotEqualTo(Date value) {
            addCriterion("statistics_end_date <>", value, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateGreaterThan(Date value) {
            addCriterion("statistics_end_date >", value, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("statistics_end_date >=", value, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateLessThan(Date value) {
            addCriterion("statistics_end_date <", value, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateLessThanOrEqualTo(Date value) {
            addCriterion("statistics_end_date <=", value, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateIn(List<Date> values) {
            addCriterion("statistics_end_date in", values, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateNotIn(List<Date> values) {
            addCriterion("statistics_end_date not in", values, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateBetween(Date value1, Date value2) {
            addCriterion("statistics_end_date between", value1, value2, "statisticsEndDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsEndDateNotBetween(Date value1, Date value2) {
            addCriterion("statistics_end_date not between", value1, value2, "statisticsEndDate");
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