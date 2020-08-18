package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAbnormalLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public OrderAbnormalLogExample() {
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

        public Criteria andAbnormalTypeIsNull() {
            addCriterion("abnormal_type is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeIsNotNull() {
            addCriterion("abnormal_type is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeEqualTo(String value) {
            addCriterion("abnormal_type =", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeNotEqualTo(String value) {
            addCriterion("abnormal_type <>", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeGreaterThan(String value) {
            addCriterion("abnormal_type >", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeGreaterThanOrEqualTo(String value) {
            addCriterion("abnormal_type >=", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeLessThan(String value) {
            addCriterion("abnormal_type <", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeLessThanOrEqualTo(String value) {
            addCriterion("abnormal_type <=", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeLike(String value) {
            addCriterion("abnormal_type like", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeNotLike(String value) {
            addCriterion("abnormal_type not like", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeIn(List<String> values) {
            addCriterion("abnormal_type in", values, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeNotIn(List<String> values) {
            addCriterion("abnormal_type not in", values, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeBetween(String value1, String value2) {
            addCriterion("abnormal_type between", value1, value2, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeNotBetween(String value1, String value2) {
            addCriterion("abnormal_type not between", value1, value2, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescIsNull() {
            addCriterion("abnormal_desc is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescIsNotNull() {
            addCriterion("abnormal_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescEqualTo(String value) {
            addCriterion("abnormal_desc =", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescNotEqualTo(String value) {
            addCriterion("abnormal_desc <>", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescGreaterThan(String value) {
            addCriterion("abnormal_desc >", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescGreaterThanOrEqualTo(String value) {
            addCriterion("abnormal_desc >=", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescLessThan(String value) {
            addCriterion("abnormal_desc <", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescLessThanOrEqualTo(String value) {
            addCriterion("abnormal_desc <=", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescLike(String value) {
            addCriterion("abnormal_desc like", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescNotLike(String value) {
            addCriterion("abnormal_desc not like", value, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescIn(List<String> values) {
            addCriterion("abnormal_desc in", values, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescNotIn(List<String> values) {
            addCriterion("abnormal_desc not in", values, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescBetween(String value1, String value2) {
            addCriterion("abnormal_desc between", value1, value2, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andAbnormalDescNotBetween(String value1, String value2) {
            addCriterion("abnormal_desc not between", value1, value2, "abnormalDesc");
            return (Criteria) this;
        }

        public Criteria andFollowByIsNull() {
            addCriterion("follow_by is null");
            return (Criteria) this;
        }

        public Criteria andFollowByIsNotNull() {
            addCriterion("follow_by is not null");
            return (Criteria) this;
        }

        public Criteria andFollowByEqualTo(Integer value) {
            addCriterion("follow_by =", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByNotEqualTo(Integer value) {
            addCriterion("follow_by <>", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByGreaterThan(Integer value) {
            addCriterion("follow_by >", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByGreaterThanOrEqualTo(Integer value) {
            addCriterion("follow_by >=", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByLessThan(Integer value) {
            addCriterion("follow_by <", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByLessThanOrEqualTo(Integer value) {
            addCriterion("follow_by <=", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByIn(List<Integer> values) {
            addCriterion("follow_by in", values, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByNotIn(List<Integer> values) {
            addCriterion("follow_by not in", values, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByBetween(Integer value1, Integer value2) {
            addCriterion("follow_by between", value1, value2, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByNotBetween(Integer value1, Integer value2) {
            addCriterion("follow_by not between", value1, value2, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowStatusIsNull() {
            addCriterion("follow_status is null");
            return (Criteria) this;
        }

        public Criteria andFollowStatusIsNotNull() {
            addCriterion("follow_status is not null");
            return (Criteria) this;
        }

        public Criteria andFollowStatusEqualTo(String value) {
            addCriterion("follow_status =", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusNotEqualTo(String value) {
            addCriterion("follow_status <>", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusGreaterThan(String value) {
            addCriterion("follow_status >", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusGreaterThanOrEqualTo(String value) {
            addCriterion("follow_status >=", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusLessThan(String value) {
            addCriterion("follow_status <", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusLessThanOrEqualTo(String value) {
            addCriterion("follow_status <=", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusLike(String value) {
            addCriterion("follow_status like", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusNotLike(String value) {
            addCriterion("follow_status not like", value, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusIn(List<String> values) {
            addCriterion("follow_status in", values, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusNotIn(List<String> values) {
            addCriterion("follow_status not in", values, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusBetween(String value1, String value2) {
            addCriterion("follow_status between", value1, value2, "followStatus");
            return (Criteria) this;
        }

        public Criteria andFollowStatusNotBetween(String value1, String value2) {
            addCriterion("follow_status not between", value1, value2, "followStatus");
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