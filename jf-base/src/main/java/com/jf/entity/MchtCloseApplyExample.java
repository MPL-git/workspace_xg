package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtCloseApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtCloseApplyExample() {
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

        public Criteria andStopBeginDateIsNull() {
            addCriterion("stop_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateIsNotNull() {
            addCriterion("stop_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateEqualTo(Date value) {
            addCriterion("stop_begin_date =", value, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateNotEqualTo(Date value) {
            addCriterion("stop_begin_date <>", value, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateGreaterThan(Date value) {
            addCriterion("stop_begin_date >", value, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("stop_begin_date >=", value, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateLessThan(Date value) {
            addCriterion("stop_begin_date <", value, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("stop_begin_date <=", value, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateIn(List<Date> values) {
            addCriterion("stop_begin_date in", values, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateNotIn(List<Date> values) {
            addCriterion("stop_begin_date not in", values, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateBetween(Date value1, Date value2) {
            addCriterion("stop_begin_date between", value1, value2, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("stop_begin_date not between", value1, value2, "stopBeginDate");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusIsNull() {
            addCriterion("stop_begin_status is null");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusIsNotNull() {
            addCriterion("stop_begin_status is not null");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusEqualTo(String value) {
            addCriterion("stop_begin_status =", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusNotEqualTo(String value) {
            addCriterion("stop_begin_status <>", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusGreaterThan(String value) {
            addCriterion("stop_begin_status >", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusGreaterThanOrEqualTo(String value) {
            addCriterion("stop_begin_status >=", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusLessThan(String value) {
            addCriterion("stop_begin_status <", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusLessThanOrEqualTo(String value) {
            addCriterion("stop_begin_status <=", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusLike(String value) {
            addCriterion("stop_begin_status like", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusNotLike(String value) {
            addCriterion("stop_begin_status not like", value, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusIn(List<String> values) {
            addCriterion("stop_begin_status in", values, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusNotIn(List<String> values) {
            addCriterion("stop_begin_status not in", values, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusBetween(String value1, String value2) {
            addCriterion("stop_begin_status between", value1, value2, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopBeginStatusNotBetween(String value1, String value2) {
            addCriterion("stop_begin_status not between", value1, value2, "stopBeginStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndDateIsNull() {
            addCriterion("stop_end_date is null");
            return (Criteria) this;
        }

        public Criteria andStopEndDateIsNotNull() {
            addCriterion("stop_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andStopEndDateEqualTo(Date value) {
            addCriterion("stop_end_date =", value, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateNotEqualTo(Date value) {
            addCriterion("stop_end_date <>", value, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateGreaterThan(Date value) {
            addCriterion("stop_end_date >", value, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("stop_end_date >=", value, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateLessThan(Date value) {
            addCriterion("stop_end_date <", value, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateLessThanOrEqualTo(Date value) {
            addCriterion("stop_end_date <=", value, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateIn(List<Date> values) {
            addCriterion("stop_end_date in", values, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateNotIn(List<Date> values) {
            addCriterion("stop_end_date not in", values, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateBetween(Date value1, Date value2) {
            addCriterion("stop_end_date between", value1, value2, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndDateNotBetween(Date value1, Date value2) {
            addCriterion("stop_end_date not between", value1, value2, "stopEndDate");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusIsNull() {
            addCriterion("stop_end_status is null");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusIsNotNull() {
            addCriterion("stop_end_status is not null");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusEqualTo(String value) {
            addCriterion("stop_end_status =", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusNotEqualTo(String value) {
            addCriterion("stop_end_status <>", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusGreaterThan(String value) {
            addCriterion("stop_end_status >", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusGreaterThanOrEqualTo(String value) {
            addCriterion("stop_end_status >=", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusLessThan(String value) {
            addCriterion("stop_end_status <", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusLessThanOrEqualTo(String value) {
            addCriterion("stop_end_status <=", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusLike(String value) {
            addCriterion("stop_end_status like", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusNotLike(String value) {
            addCriterion("stop_end_status not like", value, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusIn(List<String> values) {
            addCriterion("stop_end_status in", values, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusNotIn(List<String> values) {
            addCriterion("stop_end_status not in", values, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusBetween(String value1, String value2) {
            addCriterion("stop_end_status between", value1, value2, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andStopEndStatusNotBetween(String value1, String value2) {
            addCriterion("stop_end_status not between", value1, value2, "stopEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseFlagIsNull() {
            addCriterion("close_flag is null");
            return (Criteria) this;
        }

        public Criteria andCloseFlagIsNotNull() {
            addCriterion("close_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCloseFlagEqualTo(String value) {
            addCriterion("close_flag =", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagNotEqualTo(String value) {
            addCriterion("close_flag <>", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagGreaterThan(String value) {
            addCriterion("close_flag >", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagGreaterThanOrEqualTo(String value) {
            addCriterion("close_flag >=", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagLessThan(String value) {
            addCriterion("close_flag <", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagLessThanOrEqualTo(String value) {
            addCriterion("close_flag <=", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagLike(String value) {
            addCriterion("close_flag like", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagNotLike(String value) {
            addCriterion("close_flag not like", value, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagIn(List<String> values) {
            addCriterion("close_flag in", values, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagNotIn(List<String> values) {
            addCriterion("close_flag not in", values, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagBetween(String value1, String value2) {
            addCriterion("close_flag between", value1, value2, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseFlagNotBetween(String value1, String value2) {
            addCriterion("close_flag not between", value1, value2, "closeFlag");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateIsNull() {
            addCriterion("close_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateIsNotNull() {
            addCriterion("close_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateEqualTo(Date value) {
            addCriterion("close_begin_date =", value, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateNotEqualTo(Date value) {
            addCriterion("close_begin_date <>", value, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateGreaterThan(Date value) {
            addCriterion("close_begin_date >", value, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("close_begin_date >=", value, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateLessThan(Date value) {
            addCriterion("close_begin_date <", value, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("close_begin_date <=", value, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateIn(List<Date> values) {
            addCriterion("close_begin_date in", values, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateNotIn(List<Date> values) {
            addCriterion("close_begin_date not in", values, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateBetween(Date value1, Date value2) {
            addCriterion("close_begin_date between", value1, value2, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("close_begin_date not between", value1, value2, "closeBeginDate");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusIsNull() {
            addCriterion("close_begin_status is null");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusIsNotNull() {
            addCriterion("close_begin_status is not null");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusEqualTo(String value) {
            addCriterion("close_begin_status =", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusNotEqualTo(String value) {
            addCriterion("close_begin_status <>", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusGreaterThan(String value) {
            addCriterion("close_begin_status >", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusGreaterThanOrEqualTo(String value) {
            addCriterion("close_begin_status >=", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusLessThan(String value) {
            addCriterion("close_begin_status <", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusLessThanOrEqualTo(String value) {
            addCriterion("close_begin_status <=", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusLike(String value) {
            addCriterion("close_begin_status like", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusNotLike(String value) {
            addCriterion("close_begin_status not like", value, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusIn(List<String> values) {
            addCriterion("close_begin_status in", values, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusNotIn(List<String> values) {
            addCriterion("close_begin_status not in", values, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusBetween(String value1, String value2) {
            addCriterion("close_begin_status between", value1, value2, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseBeginStatusNotBetween(String value1, String value2) {
            addCriterion("close_begin_status not between", value1, value2, "closeBeginStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateIsNull() {
            addCriterion("close_end_date is null");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateIsNotNull() {
            addCriterion("close_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateEqualTo(Date value) {
            addCriterion("close_end_date =", value, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateNotEqualTo(Date value) {
            addCriterion("close_end_date <>", value, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateGreaterThan(Date value) {
            addCriterion("close_end_date >", value, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("close_end_date >=", value, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateLessThan(Date value) {
            addCriterion("close_end_date <", value, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("close_end_date <=", value, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateIn(List<Date> values) {
            addCriterion("close_end_date in", values, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateNotIn(List<Date> values) {
            addCriterion("close_end_date not in", values, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateBetween(Date value1, Date value2) {
            addCriterion("close_end_date between", value1, value2, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("close_end_date not between", value1, value2, "closeEndDate");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusIsNull() {
            addCriterion("close_end_status is null");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusIsNotNull() {
            addCriterion("close_end_status is not null");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusEqualTo(String value) {
            addCriterion("close_end_status =", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusNotEqualTo(String value) {
            addCriterion("close_end_status <>", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusGreaterThan(String value) {
            addCriterion("close_end_status >", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusGreaterThanOrEqualTo(String value) {
            addCriterion("close_end_status >=", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusLessThan(String value) {
            addCriterion("close_end_status <", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusLessThanOrEqualTo(String value) {
            addCriterion("close_end_status <=", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusLike(String value) {
            addCriterion("close_end_status like", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusNotLike(String value) {
            addCriterion("close_end_status not like", value, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusIn(List<String> values) {
            addCriterion("close_end_status in", values, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusNotIn(List<String> values) {
            addCriterion("close_end_status not in", values, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusBetween(String value1, String value2) {
            addCriterion("close_end_status between", value1, value2, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andCloseEndStatusNotBetween(String value1, String value2) {
            addCriterion("close_end_status not between", value1, value2, "closeEndStatus");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRequestByIsNull() {
            addCriterion("request_by is null");
            return (Criteria) this;
        }

        public Criteria andRequestByIsNotNull() {
            addCriterion("request_by is not null");
            return (Criteria) this;
        }

        public Criteria andRequestByEqualTo(Integer value) {
            addCriterion("request_by =", value, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByNotEqualTo(Integer value) {
            addCriterion("request_by <>", value, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByGreaterThan(Integer value) {
            addCriterion("request_by >", value, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByGreaterThanOrEqualTo(Integer value) {
            addCriterion("request_by >=", value, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByLessThan(Integer value) {
            addCriterion("request_by <", value, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByLessThanOrEqualTo(Integer value) {
            addCriterion("request_by <=", value, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByIn(List<Integer> values) {
            addCriterion("request_by in", values, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByNotIn(List<Integer> values) {
            addCriterion("request_by not in", values, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByBetween(Integer value1, Integer value2) {
            addCriterion("request_by between", value1, value2, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestByNotBetween(Integer value1, Integer value2) {
            addCriterion("request_by not between", value1, value2, "requestBy");
            return (Criteria) this;
        }

        public Criteria andRequestTypeIsNull() {
            addCriterion("request_type is null");
            return (Criteria) this;
        }

        public Criteria andRequestTypeIsNotNull() {
            addCriterion("request_type is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTypeEqualTo(String value) {
            addCriterion("request_type =", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeNotEqualTo(String value) {
            addCriterion("request_type <>", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeGreaterThan(String value) {
            addCriterion("request_type >", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("request_type >=", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeLessThan(String value) {
            addCriterion("request_type <", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeLessThanOrEqualTo(String value) {
            addCriterion("request_type <=", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeLike(String value) {
            addCriterion("request_type like", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeNotLike(String value) {
            addCriterion("request_type not like", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeIn(List<String> values) {
            addCriterion("request_type in", values, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeNotIn(List<String> values) {
            addCriterion("request_type not in", values, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeBetween(String value1, String value2) {
            addCriterion("request_type between", value1, value2, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeNotBetween(String value1, String value2) {
            addCriterion("request_type not between", value1, value2, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestDateIsNull() {
            addCriterion("request_date is null");
            return (Criteria) this;
        }

        public Criteria andRequestDateIsNotNull() {
            addCriterion("request_date is not null");
            return (Criteria) this;
        }

        public Criteria andRequestDateEqualTo(Date value) {
            addCriterion("request_date =", value, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateNotEqualTo(Date value) {
            addCriterion("request_date <>", value, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateGreaterThan(Date value) {
            addCriterion("request_date >", value, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateGreaterThanOrEqualTo(Date value) {
            addCriterion("request_date >=", value, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateLessThan(Date value) {
            addCriterion("request_date <", value, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateLessThanOrEqualTo(Date value) {
            addCriterion("request_date <=", value, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateIn(List<Date> values) {
            addCriterion("request_date in", values, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateNotIn(List<Date> values) {
            addCriterion("request_date not in", values, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateBetween(Date value1, Date value2) {
            addCriterion("request_date between", value1, value2, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestDateNotBetween(Date value1, Date value2) {
            addCriterion("request_date not between", value1, value2, "requestDate");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksIsNull() {
            addCriterion("request_remarks is null");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksIsNotNull() {
            addCriterion("request_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksEqualTo(String value) {
            addCriterion("request_remarks =", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksNotEqualTo(String value) {
            addCriterion("request_remarks <>", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksGreaterThan(String value) {
            addCriterion("request_remarks >", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("request_remarks >=", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksLessThan(String value) {
            addCriterion("request_remarks <", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksLessThanOrEqualTo(String value) {
            addCriterion("request_remarks <=", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksLike(String value) {
            addCriterion("request_remarks like", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksNotLike(String value) {
            addCriterion("request_remarks not like", value, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksIn(List<String> values) {
            addCriterion("request_remarks in", values, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksNotIn(List<String> values) {
            addCriterion("request_remarks not in", values, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksBetween(String value1, String value2) {
            addCriterion("request_remarks between", value1, value2, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andRequestRemarksNotBetween(String value1, String value2) {
            addCriterion("request_remarks not between", value1, value2, "requestRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByIsNull() {
            addCriterion("operate_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByIsNotNull() {
            addCriterion("operate_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByEqualTo(Integer value) {
            addCriterion("operate_audit_by =", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByNotEqualTo(Integer value) {
            addCriterion("operate_audit_by <>", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByGreaterThan(Integer value) {
            addCriterion("operate_audit_by >", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_audit_by >=", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByLessThan(Integer value) {
            addCriterion("operate_audit_by <", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("operate_audit_by <=", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByIn(List<Integer> values) {
            addCriterion("operate_audit_by in", values, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByNotIn(List<Integer> values) {
            addCriterion("operate_audit_by not in", values, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByBetween(Integer value1, Integer value2) {
            addCriterion("operate_audit_by between", value1, value2, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_audit_by not between", value1, value2, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusIsNull() {
            addCriterion("operate_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusIsNotNull() {
            addCriterion("operate_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusEqualTo(String value) {
            addCriterion("operate_audit_status =", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotEqualTo(String value) {
            addCriterion("operate_audit_status <>", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusGreaterThan(String value) {
            addCriterion("operate_audit_status >", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("operate_audit_status >=", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusLessThan(String value) {
            addCriterion("operate_audit_status <", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("operate_audit_status <=", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusLike(String value) {
            addCriterion("operate_audit_status like", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotLike(String value) {
            addCriterion("operate_audit_status not like", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusIn(List<String> values) {
            addCriterion("operate_audit_status in", values, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotIn(List<String> values) {
            addCriterion("operate_audit_status not in", values, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusBetween(String value1, String value2) {
            addCriterion("operate_audit_status between", value1, value2, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotBetween(String value1, String value2) {
            addCriterion("operate_audit_status not between", value1, value2, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateIsNull() {
            addCriterion("operate_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateIsNotNull() {
            addCriterion("operate_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateEqualTo(Date value) {
            addCriterion("operate_audit_date =", value, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateNotEqualTo(Date value) {
            addCriterion("operate_audit_date <>", value, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateGreaterThan(Date value) {
            addCriterion("operate_audit_date >", value, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("operate_audit_date >=", value, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateLessThan(Date value) {
            addCriterion("operate_audit_date <", value, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("operate_audit_date <=", value, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateIn(List<Date> values) {
            addCriterion("operate_audit_date in", values, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateNotIn(List<Date> values) {
            addCriterion("operate_audit_date not in", values, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateBetween(Date value1, Date value2) {
            addCriterion("operate_audit_date between", value1, value2, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("operate_audit_date not between", value1, value2, "operateAuditDate");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksIsNull() {
            addCriterion("operate_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksIsNotNull() {
            addCriterion("operate_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksEqualTo(String value) {
            addCriterion("operate_audit_remarks =", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotEqualTo(String value) {
            addCriterion("operate_audit_remarks <>", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksGreaterThan(String value) {
            addCriterion("operate_audit_remarks >", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("operate_audit_remarks >=", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksLessThan(String value) {
            addCriterion("operate_audit_remarks <", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("operate_audit_remarks <=", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksLike(String value) {
            addCriterion("operate_audit_remarks like", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotLike(String value) {
            addCriterion("operate_audit_remarks not like", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksIn(List<String> values) {
            addCriterion("operate_audit_remarks in", values, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotIn(List<String> values) {
            addCriterion("operate_audit_remarks not in", values, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksBetween(String value1, String value2) {
            addCriterion("operate_audit_remarks between", value1, value2, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("operate_audit_remarks not between", value1, value2, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByIsNull() {
            addCriterion("merchants_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByIsNotNull() {
            addCriterion("merchants_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByEqualTo(Integer value) {
            addCriterion("merchants_audit_by =", value, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByNotEqualTo(Integer value) {
            addCriterion("merchants_audit_by <>", value, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByGreaterThan(Integer value) {
            addCriterion("merchants_audit_by >", value, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchants_audit_by >=", value, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByLessThan(Integer value) {
            addCriterion("merchants_audit_by <", value, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("merchants_audit_by <=", value, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByIn(List<Integer> values) {
            addCriterion("merchants_audit_by in", values, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByNotIn(List<Integer> values) {
            addCriterion("merchants_audit_by not in", values, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByBetween(Integer value1, Integer value2) {
            addCriterion("merchants_audit_by between", value1, value2, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("merchants_audit_by not between", value1, value2, "merchantsAuditBy");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusIsNull() {
            addCriterion("merchants_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusIsNotNull() {
            addCriterion("merchants_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusEqualTo(String value) {
            addCriterion("merchants_audit_status =", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusNotEqualTo(String value) {
            addCriterion("merchants_audit_status <>", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusGreaterThan(String value) {
            addCriterion("merchants_audit_status >", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("merchants_audit_status >=", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusLessThan(String value) {
            addCriterion("merchants_audit_status <", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("merchants_audit_status <=", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusLike(String value) {
            addCriterion("merchants_audit_status like", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusNotLike(String value) {
            addCriterion("merchants_audit_status not like", value, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusIn(List<String> values) {
            addCriterion("merchants_audit_status in", values, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusNotIn(List<String> values) {
            addCriterion("merchants_audit_status not in", values, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusBetween(String value1, String value2) {
            addCriterion("merchants_audit_status between", value1, value2, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditStatusNotBetween(String value1, String value2) {
            addCriterion("merchants_audit_status not between", value1, value2, "merchantsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateIsNull() {
            addCriterion("merchants_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateIsNotNull() {
            addCriterion("merchants_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateEqualTo(Date value) {
            addCriterion("merchants_audit_date =", value, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateNotEqualTo(Date value) {
            addCriterion("merchants_audit_date <>", value, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateGreaterThan(Date value) {
            addCriterion("merchants_audit_date >", value, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("merchants_audit_date >=", value, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateLessThan(Date value) {
            addCriterion("merchants_audit_date <", value, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("merchants_audit_date <=", value, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateIn(List<Date> values) {
            addCriterion("merchants_audit_date in", values, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateNotIn(List<Date> values) {
            addCriterion("merchants_audit_date not in", values, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateBetween(Date value1, Date value2) {
            addCriterion("merchants_audit_date between", value1, value2, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("merchants_audit_date not between", value1, value2, "merchantsAuditDate");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksIsNull() {
            addCriterion("merchants_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksIsNotNull() {
            addCriterion("merchants_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksEqualTo(String value) {
            addCriterion("merchants_audit_remarks =", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksNotEqualTo(String value) {
            addCriterion("merchants_audit_remarks <>", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksGreaterThan(String value) {
            addCriterion("merchants_audit_remarks >", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("merchants_audit_remarks >=", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksLessThan(String value) {
            addCriterion("merchants_audit_remarks <", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("merchants_audit_remarks <=", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksLike(String value) {
            addCriterion("merchants_audit_remarks like", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksNotLike(String value) {
            addCriterion("merchants_audit_remarks not like", value, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksIn(List<String> values) {
            addCriterion("merchants_audit_remarks in", values, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksNotIn(List<String> values) {
            addCriterion("merchants_audit_remarks not in", values, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksBetween(String value1, String value2) {
            addCriterion("merchants_audit_remarks between", value1, value2, "merchantsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andMerchantsAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("merchants_audit_remarks not between", value1, value2, "merchantsAuditRemarks");
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