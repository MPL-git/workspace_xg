package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KdnWuliuInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public KdnWuliuInfoExample() {
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

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(Integer value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(Integer value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(Integer value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(Integer value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<Integer> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<Integer> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeIsNull() {
            addCriterion("logistic_code is null");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeIsNotNull() {
            addCriterion("logistic_code is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeEqualTo(String value) {
            addCriterion("logistic_code =", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeNotEqualTo(String value) {
            addCriterion("logistic_code <>", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeGreaterThan(String value) {
            addCriterion("logistic_code >", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_code >=", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeLessThan(String value) {
            addCriterion("logistic_code <", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeLessThanOrEqualTo(String value) {
            addCriterion("logistic_code <=", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeLike(String value) {
            addCriterion("logistic_code like", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeNotLike(String value) {
            addCriterion("logistic_code not like", value, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeIn(List<String> values) {
            addCriterion("logistic_code in", values, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeNotIn(List<String> values) {
            addCriterion("logistic_code not in", values, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeBetween(String value1, String value2) {
            addCriterion("logistic_code between", value1, value2, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andLogisticCodeNotBetween(String value1, String value2) {
            addCriterion("logistic_code not between", value1, value2, "logisticCode");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusIsNull() {
            addCriterion("subscribe_status is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusIsNotNull() {
            addCriterion("subscribe_status is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusEqualTo(String value) {
            addCriterion("subscribe_status =", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusNotEqualTo(String value) {
            addCriterion("subscribe_status <>", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusGreaterThan(String value) {
            addCriterion("subscribe_status >", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("subscribe_status >=", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusLessThan(String value) {
            addCriterion("subscribe_status <", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusLessThanOrEqualTo(String value) {
            addCriterion("subscribe_status <=", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusLike(String value) {
            addCriterion("subscribe_status like", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusNotLike(String value) {
            addCriterion("subscribe_status not like", value, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusIn(List<String> values) {
            addCriterion("subscribe_status in", values, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusNotIn(List<String> values) {
            addCriterion("subscribe_status not in", values, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusBetween(String value1, String value2) {
            addCriterion("subscribe_status between", value1, value2, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeStatusNotBetween(String value1, String value2) {
            addCriterion("subscribe_status not between", value1, value2, "subscribeStatus");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonIsNull() {
            addCriterion("subscribe_failed_reason is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonIsNotNull() {
            addCriterion("subscribe_failed_reason is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonEqualTo(String value) {
            addCriterion("subscribe_failed_reason =", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonNotEqualTo(String value) {
            addCriterion("subscribe_failed_reason <>", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonGreaterThan(String value) {
            addCriterion("subscribe_failed_reason >", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonGreaterThanOrEqualTo(String value) {
            addCriterion("subscribe_failed_reason >=", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonLessThan(String value) {
            addCriterion("subscribe_failed_reason <", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonLessThanOrEqualTo(String value) {
            addCriterion("subscribe_failed_reason <=", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonLike(String value) {
            addCriterion("subscribe_failed_reason like", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonNotLike(String value) {
            addCriterion("subscribe_failed_reason not like", value, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonIn(List<String> values) {
            addCriterion("subscribe_failed_reason in", values, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonNotIn(List<String> values) {
            addCriterion("subscribe_failed_reason not in", values, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonBetween(String value1, String value2) {
            addCriterion("subscribe_failed_reason between", value1, value2, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeFailedReasonNotBetween(String value1, String value2) {
            addCriterion("subscribe_failed_reason not between", value1, value2, "subscribeFailedReason");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeIsNull() {
            addCriterion("subscribe_time is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeIsNotNull() {
            addCriterion("subscribe_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeEqualTo(Date value) {
            addCriterion("subscribe_time =", value, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeNotEqualTo(Date value) {
            addCriterion("subscribe_time <>", value, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeGreaterThan(Date value) {
            addCriterion("subscribe_time >", value, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("subscribe_time >=", value, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeLessThan(Date value) {
            addCriterion("subscribe_time <", value, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeLessThanOrEqualTo(Date value) {
            addCriterion("subscribe_time <=", value, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeIn(List<Date> values) {
            addCriterion("subscribe_time in", values, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeNotIn(List<Date> values) {
            addCriterion("subscribe_time not in", values, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeBetween(Date value1, Date value2) {
            addCriterion("subscribe_time between", value1, value2, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andSubscribeTimeNotBetween(Date value1, Date value2) {
            addCriterion("subscribe_time not between", value1, value2, "subscribeTime");
            return (Criteria) this;
        }

        public Criteria andTryTimesIsNull() {
            addCriterion("try_times is null");
            return (Criteria) this;
        }

        public Criteria andTryTimesIsNotNull() {
            addCriterion("try_times is not null");
            return (Criteria) this;
        }

        public Criteria andTryTimesEqualTo(Integer value) {
            addCriterion("try_times =", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotEqualTo(Integer value) {
            addCriterion("try_times <>", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesGreaterThan(Integer value) {
            addCriterion("try_times >", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("try_times >=", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesLessThan(Integer value) {
            addCriterion("try_times <", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesLessThanOrEqualTo(Integer value) {
            addCriterion("try_times <=", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesIn(List<Integer> values) {
            addCriterion("try_times in", values, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotIn(List<Integer> values) {
            addCriterion("try_times not in", values, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesBetween(Integer value1, Integer value2) {
            addCriterion("try_times between", value1, value2, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("try_times not between", value1, value2, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTractInfoIsNull() {
            addCriterion("tract_info is null");
            return (Criteria) this;
        }

        public Criteria andTractInfoIsNotNull() {
            addCriterion("tract_info is not null");
            return (Criteria) this;
        }

        public Criteria andTractInfoEqualTo(String value) {
            addCriterion("tract_info =", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoNotEqualTo(String value) {
            addCriterion("tract_info <>", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoGreaterThan(String value) {
            addCriterion("tract_info >", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoGreaterThanOrEqualTo(String value) {
            addCriterion("tract_info >=", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoLessThan(String value) {
            addCriterion("tract_info <", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoLessThanOrEqualTo(String value) {
            addCriterion("tract_info <=", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoLike(String value) {
            addCriterion("tract_info like", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoNotLike(String value) {
            addCriterion("tract_info not like", value, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoIn(List<String> values) {
            addCriterion("tract_info in", values, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoNotIn(List<String> values) {
            addCriterion("tract_info not in", values, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoBetween(String value1, String value2) {
            addCriterion("tract_info between", value1, value2, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoNotBetween(String value1, String value2) {
            addCriterion("tract_info not between", value1, value2, "tractInfo");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceIsNull() {
            addCriterion("tract_info_source is null");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceIsNotNull() {
            addCriterion("tract_info_source is not null");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceEqualTo(String value) {
            addCriterion("tract_info_source =", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceNotEqualTo(String value) {
            addCriterion("tract_info_source <>", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceGreaterThan(String value) {
            addCriterion("tract_info_source >", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceGreaterThanOrEqualTo(String value) {
            addCriterion("tract_info_source >=", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceLessThan(String value) {
            addCriterion("tract_info_source <", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceLessThanOrEqualTo(String value) {
            addCriterion("tract_info_source <=", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceLike(String value) {
            addCriterion("tract_info_source like", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceNotLike(String value) {
            addCriterion("tract_info_source not like", value, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceIn(List<String> values) {
            addCriterion("tract_info_source in", values, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceNotIn(List<String> values) {
            addCriterion("tract_info_source not in", values, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceBetween(String value1, String value2) {
            addCriterion("tract_info_source between", value1, value2, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andTractInfoSourceNotBetween(String value1, String value2) {
            addCriterion("tract_info_source not between", value1, value2, "tractInfoSource");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameIsNull() {
            addCriterion("zzy_task_name is null");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameIsNotNull() {
            addCriterion("zzy_task_name is not null");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameEqualTo(String value) {
            addCriterion("zzy_task_name =", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameNotEqualTo(String value) {
            addCriterion("zzy_task_name <>", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameGreaterThan(String value) {
            addCriterion("zzy_task_name >", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("zzy_task_name >=", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameLessThan(String value) {
            addCriterion("zzy_task_name <", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameLessThanOrEqualTo(String value) {
            addCriterion("zzy_task_name <=", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameLike(String value) {
            addCriterion("zzy_task_name like", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameNotLike(String value) {
            addCriterion("zzy_task_name not like", value, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameIn(List<String> values) {
            addCriterion("zzy_task_name in", values, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameNotIn(List<String> values) {
            addCriterion("zzy_task_name not in", values, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameBetween(String value1, String value2) {
            addCriterion("zzy_task_name between", value1, value2, "zzyTaskName");
            return (Criteria) this;
        }

        public Criteria andZzyTaskNameNotBetween(String value1, String value2) {
            addCriterion("zzy_task_name not between", value1, value2, "zzyTaskName");
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

        public Criteria andSubOrderIdIsNull() {
            addCriterion("sub_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNotNull() {
            addCriterion("sub_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdEqualTo(Integer value) {
            addCriterion("sub_order_id =", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotEqualTo(Integer value) {
            addCriterion("sub_order_id <>", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThan(Integer value) {
            addCriterion("sub_order_id >", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id >=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThan(Integer value) {
            addCriterion("sub_order_id <", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id <=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIn(List<Integer> values) {
            addCriterion("sub_order_id in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotIn(List<Integer> values) {
            addCriterion("sub_order_id not in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id not between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNull() {
            addCriterion("merchant_code is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("merchant_code is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("merchant_code =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("merchant_code <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("merchant_code >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_code >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("merchant_code <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("merchant_code <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("merchant_code like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("merchant_code not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("merchant_code in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("merchant_code not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("merchant_code between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("merchant_code not between", value1, value2, "merchantCode");
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