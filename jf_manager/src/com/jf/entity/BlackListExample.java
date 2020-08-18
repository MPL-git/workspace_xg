package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlackListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public BlackListExample() {
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

        public Criteria andBlackTypeIsNull() {
            addCriterion("black_type is null");
            return (Criteria) this;
        }

        public Criteria andBlackTypeIsNotNull() {
            addCriterion("black_type is not null");
            return (Criteria) this;
        }

        public Criteria andBlackTypeEqualTo(String value) {
            addCriterion("black_type =", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeNotEqualTo(String value) {
            addCriterion("black_type <>", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeGreaterThan(String value) {
            addCriterion("black_type >", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeGreaterThanOrEqualTo(String value) {
            addCriterion("black_type >=", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeLessThan(String value) {
            addCriterion("black_type <", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeLessThanOrEqualTo(String value) {
            addCriterion("black_type <=", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeLike(String value) {
            addCriterion("black_type like", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeNotLike(String value) {
            addCriterion("black_type not like", value, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeIn(List<String> values) {
            addCriterion("black_type in", values, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeNotIn(List<String> values) {
            addCriterion("black_type not in", values, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeBetween(String value1, String value2) {
            addCriterion("black_type between", value1, value2, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackTypeNotBetween(String value1, String value2) {
            addCriterion("black_type not between", value1, value2, "blackType");
            return (Criteria) this;
        }

        public Criteria andBlackReasonIsNull() {
            addCriterion("black_reason is null");
            return (Criteria) this;
        }

        public Criteria andBlackReasonIsNotNull() {
            addCriterion("black_reason is not null");
            return (Criteria) this;
        }

        public Criteria andBlackReasonEqualTo(String value) {
            addCriterion("black_reason =", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotEqualTo(String value) {
            addCriterion("black_reason <>", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonGreaterThan(String value) {
            addCriterion("black_reason >", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonGreaterThanOrEqualTo(String value) {
            addCriterion("black_reason >=", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonLessThan(String value) {
            addCriterion("black_reason <", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonLessThanOrEqualTo(String value) {
            addCriterion("black_reason <=", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonLike(String value) {
            addCriterion("black_reason like", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotLike(String value) {
            addCriterion("black_reason not like", value, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonIn(List<String> values) {
            addCriterion("black_reason in", values, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotIn(List<String> values) {
            addCriterion("black_reason not in", values, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonBetween(String value1, String value2) {
            addCriterion("black_reason between", value1, value2, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackReasonNotBetween(String value1, String value2) {
            addCriterion("black_reason not between", value1, value2, "blackReason");
            return (Criteria) this;
        }

        public Criteria andBlackDateIsNull() {
            addCriterion("black_date is null");
            return (Criteria) this;
        }

        public Criteria andBlackDateIsNotNull() {
            addCriterion("black_date is not null");
            return (Criteria) this;
        }

        public Criteria andBlackDateEqualTo(Date value) {
            addCriterion("black_date =", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateNotEqualTo(Date value) {
            addCriterion("black_date <>", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateGreaterThan(Date value) {
            addCriterion("black_date >", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateGreaterThanOrEqualTo(Date value) {
            addCriterion("black_date >=", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateLessThan(Date value) {
            addCriterion("black_date <", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateLessThanOrEqualTo(Date value) {
            addCriterion("black_date <=", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateIn(List<Date> values) {
            addCriterion("black_date in", values, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateNotIn(List<Date> values) {
            addCriterion("black_date not in", values, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateBetween(Date value1, Date value2) {
            addCriterion("black_date between", value1, value2, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateNotBetween(Date value1, Date value2) {
            addCriterion("black_date not between", value1, value2, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateIsNull() {
            addCriterion("black_to_date is null");
            return (Criteria) this;
        }

        public Criteria andBlackToDateIsNotNull() {
            addCriterion("black_to_date is not null");
            return (Criteria) this;
        }

        public Criteria andBlackToDateEqualTo(Date value) {
            addCriterion("black_to_date =", value, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateNotEqualTo(Date value) {
            addCriterion("black_to_date <>", value, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateGreaterThan(Date value) {
            addCriterion("black_to_date >", value, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateGreaterThanOrEqualTo(Date value) {
            addCriterion("black_to_date >=", value, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateLessThan(Date value) {
            addCriterion("black_to_date <", value, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateLessThanOrEqualTo(Date value) {
            addCriterion("black_to_date <=", value, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateIn(List<Date> values) {
            addCriterion("black_to_date in", values, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateNotIn(List<Date> values) {
            addCriterion("black_to_date not in", values, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateBetween(Date value1, Date value2) {
            addCriterion("black_to_date between", value1, value2, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andBlackToDateNotBetween(Date value1, Date value2) {
            addCriterion("black_to_date not between", value1, value2, "blackToDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateIsNull() {
            addCriterion("unblack_date is null");
            return (Criteria) this;
        }

        public Criteria andUnblackDateIsNotNull() {
            addCriterion("unblack_date is not null");
            return (Criteria) this;
        }

        public Criteria andUnblackDateEqualTo(Date value) {
            addCriterion("unblack_date =", value, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateNotEqualTo(Date value) {
            addCriterion("unblack_date <>", value, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateGreaterThan(Date value) {
            addCriterion("unblack_date >", value, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateGreaterThanOrEqualTo(Date value) {
            addCriterion("unblack_date >=", value, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateLessThan(Date value) {
            addCriterion("unblack_date <", value, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateLessThanOrEqualTo(Date value) {
            addCriterion("unblack_date <=", value, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateIn(List<Date> values) {
            addCriterion("unblack_date in", values, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateNotIn(List<Date> values) {
            addCriterion("unblack_date not in", values, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateBetween(Date value1, Date value2) {
            addCriterion("unblack_date between", value1, value2, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackDateNotBetween(Date value1, Date value2) {
            addCriterion("unblack_date not between", value1, value2, "unblackDate");
            return (Criteria) this;
        }

        public Criteria andUnblackByIsNull() {
            addCriterion("unblack_by is null");
            return (Criteria) this;
        }

        public Criteria andUnblackByIsNotNull() {
            addCriterion("unblack_by is not null");
            return (Criteria) this;
        }

        public Criteria andUnblackByEqualTo(Integer value) {
            addCriterion("unblack_by =", value, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByNotEqualTo(Integer value) {
            addCriterion("unblack_by <>", value, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByGreaterThan(Integer value) {
            addCriterion("unblack_by >", value, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByGreaterThanOrEqualTo(Integer value) {
            addCriterion("unblack_by >=", value, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByLessThan(Integer value) {
            addCriterion("unblack_by <", value, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByLessThanOrEqualTo(Integer value) {
            addCriterion("unblack_by <=", value, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByIn(List<Integer> values) {
            addCriterion("unblack_by in", values, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByNotIn(List<Integer> values) {
            addCriterion("unblack_by not in", values, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByBetween(Integer value1, Integer value2) {
            addCriterion("unblack_by between", value1, value2, "unblackBy");
            return (Criteria) this;
        }

        public Criteria andUnblackByNotBetween(Integer value1, Integer value2) {
            addCriterion("unblack_by not between", value1, value2, "unblackBy");
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