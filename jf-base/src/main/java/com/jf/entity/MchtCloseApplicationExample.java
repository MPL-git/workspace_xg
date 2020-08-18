package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtCloseApplicationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtCloseApplicationExample() {
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

        public Criteria andApplySourceIsNull() {
            addCriterion("apply_source is null");
            return (Criteria) this;
        }

        public Criteria andApplySourceIsNotNull() {
            addCriterion("apply_source is not null");
            return (Criteria) this;
        }

        public Criteria andApplySourceEqualTo(String value) {
            addCriterion("apply_source =", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceNotEqualTo(String value) {
            addCriterion("apply_source <>", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceGreaterThan(String value) {
            addCriterion("apply_source >", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceGreaterThanOrEqualTo(String value) {
            addCriterion("apply_source >=", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceLessThan(String value) {
            addCriterion("apply_source <", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceLessThanOrEqualTo(String value) {
            addCriterion("apply_source <=", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceLike(String value) {
            addCriterion("apply_source like", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceNotLike(String value) {
            addCriterion("apply_source not like", value, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceIn(List<String> values) {
            addCriterion("apply_source in", values, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceNotIn(List<String> values) {
            addCriterion("apply_source not in", values, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceBetween(String value1, String value2) {
            addCriterion("apply_source between", value1, value2, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplySourceNotBetween(String value1, String value2) {
            addCriterion("apply_source not between", value1, value2, "applySource");
            return (Criteria) this;
        }

        public Criteria andApplyNameIsNull() {
            addCriterion("apply_name is null");
            return (Criteria) this;
        }

        public Criteria andApplyNameIsNotNull() {
            addCriterion("apply_name is not null");
            return (Criteria) this;
        }

        public Criteria andApplyNameEqualTo(String value) {
            addCriterion("apply_name =", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotEqualTo(String value) {
            addCriterion("apply_name <>", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameGreaterThan(String value) {
            addCriterion("apply_name >", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameGreaterThanOrEqualTo(String value) {
            addCriterion("apply_name >=", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameLessThan(String value) {
            addCriterion("apply_name <", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameLessThanOrEqualTo(String value) {
            addCriterion("apply_name <=", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameLike(String value) {
            addCriterion("apply_name like", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotLike(String value) {
            addCriterion("apply_name not like", value, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameIn(List<String> values) {
            addCriterion("apply_name in", values, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotIn(List<String> values) {
            addCriterion("apply_name not in", values, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameBetween(String value1, String value2) {
            addCriterion("apply_name between", value1, value2, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyNameNotBetween(String value1, String value2) {
            addCriterion("apply_name not between", value1, value2, "applyName");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIsNull() {
            addCriterion("apply_reason is null");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIsNotNull() {
            addCriterion("apply_reason is not null");
            return (Criteria) this;
        }

        public Criteria andApplyReasonEqualTo(String value) {
            addCriterion("apply_reason =", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotEqualTo(String value) {
            addCriterion("apply_reason <>", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonGreaterThan(String value) {
            addCriterion("apply_reason >", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonGreaterThanOrEqualTo(String value) {
            addCriterion("apply_reason >=", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLessThan(String value) {
            addCriterion("apply_reason <", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLessThanOrEqualTo(String value) {
            addCriterion("apply_reason <=", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLike(String value) {
            addCriterion("apply_reason like", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotLike(String value) {
            addCriterion("apply_reason not like", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIn(List<String> values) {
            addCriterion("apply_reason in", values, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotIn(List<String> values) {
            addCriterion("apply_reason not in", values, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonBetween(String value1, String value2) {
            addCriterion("apply_reason between", value1, value2, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotBetween(String value1, String value2) {
            addCriterion("apply_reason not between", value1, value2, "applyReason");
            return (Criteria) this;
        }

        public Criteria andProgressStatusIsNull() {
            addCriterion("progress_status is null");
            return (Criteria) this;
        }

        public Criteria andProgressStatusIsNotNull() {
            addCriterion("progress_status is not null");
            return (Criteria) this;
        }

        public Criteria andProgressStatusEqualTo(String value) {
            addCriterion("progress_status =", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusNotEqualTo(String value) {
            addCriterion("progress_status <>", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusGreaterThan(String value) {
            addCriterion("progress_status >", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusGreaterThanOrEqualTo(String value) {
            addCriterion("progress_status >=", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusLessThan(String value) {
            addCriterion("progress_status <", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusLessThanOrEqualTo(String value) {
            addCriterion("progress_status <=", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusLike(String value) {
            addCriterion("progress_status like", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusNotLike(String value) {
            addCriterion("progress_status not like", value, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusIn(List<String> values) {
            addCriterion("progress_status in", values, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusNotIn(List<String> values) {
            addCriterion("progress_status not in", values, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusBetween(String value1, String value2) {
            addCriterion("progress_status between", value1, value2, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andProgressStatusNotBetween(String value1, String value2) {
            addCriterion("progress_status not between", value1, value2, "progressStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusIsNull() {
            addCriterion("zs_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusIsNotNull() {
            addCriterion("zs_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusEqualTo(String value) {
            addCriterion("zs_confirm_status =", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusNotEqualTo(String value) {
            addCriterion("zs_confirm_status <>", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusGreaterThan(String value) {
            addCriterion("zs_confirm_status >", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("zs_confirm_status >=", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusLessThan(String value) {
            addCriterion("zs_confirm_status <", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("zs_confirm_status <=", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusLike(String value) {
            addCriterion("zs_confirm_status like", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusNotLike(String value) {
            addCriterion("zs_confirm_status not like", value, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusIn(List<String> values) {
            addCriterion("zs_confirm_status in", values, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusNotIn(List<String> values) {
            addCriterion("zs_confirm_status not in", values, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusBetween(String value1, String value2) {
            addCriterion("zs_confirm_status between", value1, value2, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("zs_confirm_status not between", value1, value2, "zsConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateIsNull() {
            addCriterion("zs_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateIsNotNull() {
            addCriterion("zs_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateEqualTo(Date value) {
            addCriterion("zs_confirm_date =", value, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateNotEqualTo(Date value) {
            addCriterion("zs_confirm_date <>", value, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateGreaterThan(Date value) {
            addCriterion("zs_confirm_date >", value, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("zs_confirm_date >=", value, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateLessThan(Date value) {
            addCriterion("zs_confirm_date <", value, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("zs_confirm_date <=", value, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateIn(List<Date> values) {
            addCriterion("zs_confirm_date in", values, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateNotIn(List<Date> values) {
            addCriterion("zs_confirm_date not in", values, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateBetween(Date value1, Date value2) {
            addCriterion("zs_confirm_date between", value1, value2, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("zs_confirm_date not between", value1, value2, "zsConfirmDate");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByIsNull() {
            addCriterion("zs_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByIsNotNull() {
            addCriterion("zs_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByEqualTo(Integer value) {
            addCriterion("zs_confirm_by =", value, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByNotEqualTo(Integer value) {
            addCriterion("zs_confirm_by <>", value, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByGreaterThan(Integer value) {
            addCriterion("zs_confirm_by >", value, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("zs_confirm_by >=", value, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByLessThan(Integer value) {
            addCriterion("zs_confirm_by <", value, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("zs_confirm_by <=", value, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByIn(List<Integer> values) {
            addCriterion("zs_confirm_by in", values, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByNotIn(List<Integer> values) {
            addCriterion("zs_confirm_by not in", values, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("zs_confirm_by between", value1, value2, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("zs_confirm_by not between", value1, value2, "zsConfirmBy");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonIsNull() {
            addCriterion("zs_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonIsNotNull() {
            addCriterion("zs_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonEqualTo(String value) {
            addCriterion("zs_reject_reason =", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonNotEqualTo(String value) {
            addCriterion("zs_reject_reason <>", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonGreaterThan(String value) {
            addCriterion("zs_reject_reason >", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("zs_reject_reason >=", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonLessThan(String value) {
            addCriterion("zs_reject_reason <", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("zs_reject_reason <=", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonLike(String value) {
            addCriterion("zs_reject_reason like", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonNotLike(String value) {
            addCriterion("zs_reject_reason not like", value, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonIn(List<String> values) {
            addCriterion("zs_reject_reason in", values, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonNotIn(List<String> values) {
            addCriterion("zs_reject_reason not in", values, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonBetween(String value1, String value2) {
            addCriterion("zs_reject_reason between", value1, value2, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRejectReasonNotBetween(String value1, String value2) {
            addCriterion("zs_reject_reason not between", value1, value2, "zsRejectReason");
            return (Criteria) this;
        }

        public Criteria andZsRemarksIsNull() {
            addCriterion("zs_remarks is null");
            return (Criteria) this;
        }

        public Criteria andZsRemarksIsNotNull() {
            addCriterion("zs_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andZsRemarksEqualTo(String value) {
            addCriterion("zs_remarks =", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksNotEqualTo(String value) {
            addCriterion("zs_remarks <>", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksGreaterThan(String value) {
            addCriterion("zs_remarks >", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("zs_remarks >=", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksLessThan(String value) {
            addCriterion("zs_remarks <", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksLessThanOrEqualTo(String value) {
            addCriterion("zs_remarks <=", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksLike(String value) {
            addCriterion("zs_remarks like", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksNotLike(String value) {
            addCriterion("zs_remarks not like", value, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksIn(List<String> values) {
            addCriterion("zs_remarks in", values, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksNotIn(List<String> values) {
            addCriterion("zs_remarks not in", values, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksBetween(String value1, String value2) {
            addCriterion("zs_remarks between", value1, value2, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksNotBetween(String value1, String value2) {
            addCriterion("zs_remarks not between", value1, value2, "zsRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateIsNull() {
            addCriterion("zs_remarks_date is null");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateIsNotNull() {
            addCriterion("zs_remarks_date is not null");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateEqualTo(Date value) {
            addCriterion("zs_remarks_date =", value, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateNotEqualTo(Date value) {
            addCriterion("zs_remarks_date <>", value, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateGreaterThan(Date value) {
            addCriterion("zs_remarks_date >", value, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateGreaterThanOrEqualTo(Date value) {
            addCriterion("zs_remarks_date >=", value, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateLessThan(Date value) {
            addCriterion("zs_remarks_date <", value, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateLessThanOrEqualTo(Date value) {
            addCriterion("zs_remarks_date <=", value, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateIn(List<Date> values) {
            addCriterion("zs_remarks_date in", values, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateNotIn(List<Date> values) {
            addCriterion("zs_remarks_date not in", values, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateBetween(Date value1, Date value2) {
            addCriterion("zs_remarks_date between", value1, value2, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andZsRemarksDateNotBetween(Date value1, Date value2) {
            addCriterion("zs_remarks_date not between", value1, value2, "zsRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusIsNull() {
            addCriterion("mcht_info_status is null");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusIsNotNull() {
            addCriterion("mcht_info_status is not null");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusEqualTo(String value) {
            addCriterion("mcht_info_status =", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusNotEqualTo(String value) {
            addCriterion("mcht_info_status <>", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusGreaterThan(String value) {
            addCriterion("mcht_info_status >", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_info_status >=", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusLessThan(String value) {
            addCriterion("mcht_info_status <", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusLessThanOrEqualTo(String value) {
            addCriterion("mcht_info_status <=", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusLike(String value) {
            addCriterion("mcht_info_status like", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusNotLike(String value) {
            addCriterion("mcht_info_status not like", value, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusIn(List<String> values) {
            addCriterion("mcht_info_status in", values, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusNotIn(List<String> values) {
            addCriterion("mcht_info_status not in", values, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusBetween(String value1, String value2) {
            addCriterion("mcht_info_status between", value1, value2, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInfoStatusNotBetween(String value1, String value2) {
            addCriterion("mcht_info_status not between", value1, value2, "mchtInfoStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNull() {
            addCriterion("activity_status is null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNotNull() {
            addCriterion("activity_status is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusEqualTo(String value) {
            addCriterion("activity_status =", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotEqualTo(String value) {
            addCriterion("activity_status <>", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThan(String value) {
            addCriterion("activity_status >", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThanOrEqualTo(String value) {
            addCriterion("activity_status >=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThan(String value) {
            addCriterion("activity_status <", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThanOrEqualTo(String value) {
            addCriterion("activity_status <=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLike(String value) {
            addCriterion("activity_status like", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotLike(String value) {
            addCriterion("activity_status not like", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIn(List<String> values) {
            addCriterion("activity_status in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotIn(List<String> values) {
            addCriterion("activity_status not in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusBetween(String value1, String value2) {
            addCriterion("activity_status between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotBetween(String value1, String value2) {
            addCriterion("activity_status not between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusIsNull() {
            addCriterion("commodity_status is null");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusIsNotNull() {
            addCriterion("commodity_status is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusEqualTo(String value) {
            addCriterion("commodity_status =", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusNotEqualTo(String value) {
            addCriterion("commodity_status <>", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusGreaterThan(String value) {
            addCriterion("commodity_status >", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_status >=", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusLessThan(String value) {
            addCriterion("commodity_status <", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusLessThanOrEqualTo(String value) {
            addCriterion("commodity_status <=", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusLike(String value) {
            addCriterion("commodity_status like", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusNotLike(String value) {
            addCriterion("commodity_status not like", value, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusIn(List<String> values) {
            addCriterion("commodity_status in", values, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusNotIn(List<String> values) {
            addCriterion("commodity_status not in", values, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusBetween(String value1, String value2) {
            addCriterion("commodity_status between", value1, value2, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityStatusNotBetween(String value1, String value2) {
            addCriterion("commodity_status not between", value1, value2, "commodityStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusIsNull() {
            addCriterion("marketing_status is null");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusIsNotNull() {
            addCriterion("marketing_status is not null");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusEqualTo(String value) {
            addCriterion("marketing_status =", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusNotEqualTo(String value) {
            addCriterion("marketing_status <>", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusGreaterThan(String value) {
            addCriterion("marketing_status >", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusGreaterThanOrEqualTo(String value) {
            addCriterion("marketing_status >=", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusLessThan(String value) {
            addCriterion("marketing_status <", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusLessThanOrEqualTo(String value) {
            addCriterion("marketing_status <=", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusLike(String value) {
            addCriterion("marketing_status like", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusNotLike(String value) {
            addCriterion("marketing_status not like", value, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusIn(List<String> values) {
            addCriterion("marketing_status in", values, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusNotIn(List<String> values) {
            addCriterion("marketing_status not in", values, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusBetween(String value1, String value2) {
            addCriterion("marketing_status between", value1, value2, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andMarketingStatusNotBetween(String value1, String value2) {
            addCriterion("marketing_status not between", value1, value2, "marketingStatus");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateIsNull() {
            addCriterion("commodity_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateIsNotNull() {
            addCriterion("commodity_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateEqualTo(Date value) {
            addCriterion("commodity_confirm_date =", value, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateNotEqualTo(Date value) {
            addCriterion("commodity_confirm_date <>", value, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateGreaterThan(Date value) {
            addCriterion("commodity_confirm_date >", value, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commodity_confirm_date >=", value, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateLessThan(Date value) {
            addCriterion("commodity_confirm_date <", value, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("commodity_confirm_date <=", value, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateIn(List<Date> values) {
            addCriterion("commodity_confirm_date in", values, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateNotIn(List<Date> values) {
            addCriterion("commodity_confirm_date not in", values, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateBetween(Date value1, Date value2) {
            addCriterion("commodity_confirm_date between", value1, value2, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("commodity_confirm_date not between", value1, value2, "commodityConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByIsNull() {
            addCriterion("commodity_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByIsNotNull() {
            addCriterion("commodity_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByEqualTo(Integer value) {
            addCriterion("commodity_confirm_by =", value, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByNotEqualTo(Integer value) {
            addCriterion("commodity_confirm_by <>", value, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByGreaterThan(Integer value) {
            addCriterion("commodity_confirm_by >", value, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("commodity_confirm_by >=", value, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByLessThan(Integer value) {
            addCriterion("commodity_confirm_by <", value, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("commodity_confirm_by <=", value, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByIn(List<Integer> values) {
            addCriterion("commodity_confirm_by in", values, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByNotIn(List<Integer> values) {
            addCriterion("commodity_confirm_by not in", values, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("commodity_confirm_by between", value1, value2, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("commodity_confirm_by not between", value1, value2, "commodityConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksIsNull() {
            addCriterion("commodity_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksIsNotNull() {
            addCriterion("commodity_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksEqualTo(String value) {
            addCriterion("commodity_remarks =", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksNotEqualTo(String value) {
            addCriterion("commodity_remarks <>", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksGreaterThan(String value) {
            addCriterion("commodity_remarks >", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_remarks >=", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksLessThan(String value) {
            addCriterion("commodity_remarks <", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksLessThanOrEqualTo(String value) {
            addCriterion("commodity_remarks <=", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksLike(String value) {
            addCriterion("commodity_remarks like", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksNotLike(String value) {
            addCriterion("commodity_remarks not like", value, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksIn(List<String> values) {
            addCriterion("commodity_remarks in", values, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksNotIn(List<String> values) {
            addCriterion("commodity_remarks not in", values, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksBetween(String value1, String value2) {
            addCriterion("commodity_remarks between", value1, value2, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksNotBetween(String value1, String value2) {
            addCriterion("commodity_remarks not between", value1, value2, "commodityRemarks");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateIsNull() {
            addCriterion("commodity_remarks_date is null");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateIsNotNull() {
            addCriterion("commodity_remarks_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateEqualTo(Date value) {
            addCriterion("commodity_remarks_date =", value, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateNotEqualTo(Date value) {
            addCriterion("commodity_remarks_date <>", value, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateGreaterThan(Date value) {
            addCriterion("commodity_remarks_date >", value, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commodity_remarks_date >=", value, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateLessThan(Date value) {
            addCriterion("commodity_remarks_date <", value, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateLessThanOrEqualTo(Date value) {
            addCriterion("commodity_remarks_date <=", value, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateIn(List<Date> values) {
            addCriterion("commodity_remarks_date in", values, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateNotIn(List<Date> values) {
            addCriterion("commodity_remarks_date not in", values, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateBetween(Date value1, Date value2) {
            addCriterion("commodity_remarks_date between", value1, value2, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCommodityRemarksDateNotBetween(Date value1, Date value2) {
            addCriterion("commodity_remarks_date not between", value1, value2, "commodityRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusIsNull() {
            addCriterion("fw_close_hang_up_status is null");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusIsNotNull() {
            addCriterion("fw_close_hang_up_status is not null");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusEqualTo(String value) {
            addCriterion("fw_close_hang_up_status =", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusNotEqualTo(String value) {
            addCriterion("fw_close_hang_up_status <>", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusGreaterThan(String value) {
            addCriterion("fw_close_hang_up_status >", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusGreaterThanOrEqualTo(String value) {
            addCriterion("fw_close_hang_up_status >=", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusLessThan(String value) {
            addCriterion("fw_close_hang_up_status <", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusLessThanOrEqualTo(String value) {
            addCriterion("fw_close_hang_up_status <=", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusLike(String value) {
            addCriterion("fw_close_hang_up_status like", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusNotLike(String value) {
            addCriterion("fw_close_hang_up_status not like", value, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusIn(List<String> values) {
            addCriterion("fw_close_hang_up_status in", values, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusNotIn(List<String> values) {
            addCriterion("fw_close_hang_up_status not in", values, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusBetween(String value1, String value2) {
            addCriterion("fw_close_hang_up_status between", value1, value2, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andFwCloseHangUpStatusNotBetween(String value1, String value2) {
            addCriterion("fw_close_hang_up_status not between", value1, value2, "fwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusIsNull() {
            addCriterion("mcht_contract_status is null");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusIsNotNull() {
            addCriterion("mcht_contract_status is not null");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusEqualTo(String value) {
            addCriterion("mcht_contract_status =", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusNotEqualTo(String value) {
            addCriterion("mcht_contract_status <>", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusGreaterThan(String value) {
            addCriterion("mcht_contract_status >", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_contract_status >=", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusLessThan(String value) {
            addCriterion("mcht_contract_status <", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusLessThanOrEqualTo(String value) {
            addCriterion("mcht_contract_status <=", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusLike(String value) {
            addCriterion("mcht_contract_status like", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusNotLike(String value) {
            addCriterion("mcht_contract_status not like", value, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusIn(List<String> values) {
            addCriterion("mcht_contract_status in", values, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusNotIn(List<String> values) {
            addCriterion("mcht_contract_status not in", values, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusBetween(String value1, String value2) {
            addCriterion("mcht_contract_status between", value1, value2, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtContractStatusNotBetween(String value1, String value2) {
            addCriterion("mcht_contract_status not between", value1, value2, "mchtContractStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusIsNull() {
            addCriterion("mcht_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusIsNotNull() {
            addCriterion("mcht_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusEqualTo(String value) {
            addCriterion("mcht_archive_status =", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusNotEqualTo(String value) {
            addCriterion("mcht_archive_status <>", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusGreaterThan(String value) {
            addCriterion("mcht_archive_status >", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_archive_status >=", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusLessThan(String value) {
            addCriterion("mcht_archive_status <", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("mcht_archive_status <=", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusLike(String value) {
            addCriterion("mcht_archive_status like", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusNotLike(String value) {
            addCriterion("mcht_archive_status not like", value, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusIn(List<String> values) {
            addCriterion("mcht_archive_status in", values, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusNotIn(List<String> values) {
            addCriterion("mcht_archive_status not in", values, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusBetween(String value1, String value2) {
            addCriterion("mcht_archive_status between", value1, value2, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("mcht_archive_status not between", value1, value2, "mchtArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusIsNull() {
            addCriterion("business_information_status is null");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusIsNotNull() {
            addCriterion("business_information_status is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusEqualTo(String value) {
            addCriterion("business_information_status =", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusNotEqualTo(String value) {
            addCriterion("business_information_status <>", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusGreaterThan(String value) {
            addCriterion("business_information_status >", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusGreaterThanOrEqualTo(String value) {
            addCriterion("business_information_status >=", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusLessThan(String value) {
            addCriterion("business_information_status <", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusLessThanOrEqualTo(String value) {
            addCriterion("business_information_status <=", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusLike(String value) {
            addCriterion("business_information_status like", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusNotLike(String value) {
            addCriterion("business_information_status not like", value, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusIn(List<String> values) {
            addCriterion("business_information_status in", values, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusNotIn(List<String> values) {
            addCriterion("business_information_status not in", values, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusBetween(String value1, String value2) {
            addCriterion("business_information_status between", value1, value2, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationStatusNotBetween(String value1, String value2) {
            addCriterion("business_information_status not between", value1, value2, "businessInformationStatus");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksIsNull() {
            addCriterion("business_information_remarks is null");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksIsNotNull() {
            addCriterion("business_information_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksEqualTo(String value) {
            addCriterion("business_information_remarks =", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksNotEqualTo(String value) {
            addCriterion("business_information_remarks <>", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksGreaterThan(String value) {
            addCriterion("business_information_remarks >", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("business_information_remarks >=", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksLessThan(String value) {
            addCriterion("business_information_remarks <", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksLessThanOrEqualTo(String value) {
            addCriterion("business_information_remarks <=", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksLike(String value) {
            addCriterion("business_information_remarks like", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksNotLike(String value) {
            addCriterion("business_information_remarks not like", value, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksIn(List<String> values) {
            addCriterion("business_information_remarks in", values, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksNotIn(List<String> values) {
            addCriterion("business_information_remarks not in", values, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksBetween(String value1, String value2) {
            addCriterion("business_information_remarks between", value1, value2, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andBusinessInformationRemarksNotBetween(String value1, String value2) {
            addCriterion("business_information_remarks not between", value1, value2, "businessInformationRemarks");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonIsNull() {
            addCriterion("fw_hang_up_reason is null");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonIsNotNull() {
            addCriterion("fw_hang_up_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonEqualTo(String value) {
            addCriterion("fw_hang_up_reason =", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonNotEqualTo(String value) {
            addCriterion("fw_hang_up_reason <>", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonGreaterThan(String value) {
            addCriterion("fw_hang_up_reason >", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonGreaterThanOrEqualTo(String value) {
            addCriterion("fw_hang_up_reason >=", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonLessThan(String value) {
            addCriterion("fw_hang_up_reason <", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonLessThanOrEqualTo(String value) {
            addCriterion("fw_hang_up_reason <=", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonLike(String value) {
            addCriterion("fw_hang_up_reason like", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonNotLike(String value) {
            addCriterion("fw_hang_up_reason not like", value, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonIn(List<String> values) {
            addCriterion("fw_hang_up_reason in", values, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonNotIn(List<String> values) {
            addCriterion("fw_hang_up_reason not in", values, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonBetween(String value1, String value2) {
            addCriterion("fw_hang_up_reason between", value1, value2, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpReasonNotBetween(String value1, String value2) {
            addCriterion("fw_hang_up_reason not between", value1, value2, "fwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateIsNull() {
            addCriterion("fw_hang_up_date is null");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateIsNotNull() {
            addCriterion("fw_hang_up_date is not null");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateEqualTo(Date value) {
            addCriterion("fw_hang_up_date =", value, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateNotEqualTo(Date value) {
            addCriterion("fw_hang_up_date <>", value, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateGreaterThan(Date value) {
            addCriterion("fw_hang_up_date >", value, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("fw_hang_up_date >=", value, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateLessThan(Date value) {
            addCriterion("fw_hang_up_date <", value, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateLessThanOrEqualTo(Date value) {
            addCriterion("fw_hang_up_date <=", value, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateIn(List<Date> values) {
            addCriterion("fw_hang_up_date in", values, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateNotIn(List<Date> values) {
            addCriterion("fw_hang_up_date not in", values, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateBetween(Date value1, Date value2) {
            addCriterion("fw_hang_up_date between", value1, value2, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andFwHangUpDateNotBetween(Date value1, Date value2) {
            addCriterion("fw_hang_up_date not between", value1, value2, "fwHangUpDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateIsNull() {
            addCriterion("mcht_archive_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateIsNotNull() {
            addCriterion("mcht_archive_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateEqualTo(Date value) {
            addCriterion("mcht_archive_confirm_date =", value, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateNotEqualTo(Date value) {
            addCriterion("mcht_archive_confirm_date <>", value, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateGreaterThan(Date value) {
            addCriterion("mcht_archive_confirm_date >", value, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("mcht_archive_confirm_date >=", value, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateLessThan(Date value) {
            addCriterion("mcht_archive_confirm_date <", value, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("mcht_archive_confirm_date <=", value, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateIn(List<Date> values) {
            addCriterion("mcht_archive_confirm_date in", values, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateNotIn(List<Date> values) {
            addCriterion("mcht_archive_confirm_date not in", values, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateBetween(Date value1, Date value2) {
            addCriterion("mcht_archive_confirm_date between", value1, value2, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("mcht_archive_confirm_date not between", value1, value2, "mchtArchiveConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByIsNull() {
            addCriterion("mcht_archive_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByIsNotNull() {
            addCriterion("mcht_archive_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByEqualTo(Integer value) {
            addCriterion("mcht_archive_confirm_by =", value, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByNotEqualTo(Integer value) {
            addCriterion("mcht_archive_confirm_by <>", value, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByGreaterThan(Integer value) {
            addCriterion("mcht_archive_confirm_by >", value, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_archive_confirm_by >=", value, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByLessThan(Integer value) {
            addCriterion("mcht_archive_confirm_by <", value, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_archive_confirm_by <=", value, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByIn(List<Integer> values) {
            addCriterion("mcht_archive_confirm_by in", values, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByNotIn(List<Integer> values) {
            addCriterion("mcht_archive_confirm_by not in", values, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("mcht_archive_confirm_by between", value1, value2, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_archive_confirm_by not between", value1, value2, "mchtArchiveConfirmBy");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksIsNull() {
            addCriterion("mcht_archive_remarks is null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksIsNotNull() {
            addCriterion("mcht_archive_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksEqualTo(String value) {
            addCriterion("mcht_archive_remarks =", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksNotEqualTo(String value) {
            addCriterion("mcht_archive_remarks <>", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksGreaterThan(String value) {
            addCriterion("mcht_archive_remarks >", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_archive_remarks >=", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksLessThan(String value) {
            addCriterion("mcht_archive_remarks <", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksLessThanOrEqualTo(String value) {
            addCriterion("mcht_archive_remarks <=", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksLike(String value) {
            addCriterion("mcht_archive_remarks like", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksNotLike(String value) {
            addCriterion("mcht_archive_remarks not like", value, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksIn(List<String> values) {
            addCriterion("mcht_archive_remarks in", values, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksNotIn(List<String> values) {
            addCriterion("mcht_archive_remarks not in", values, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksBetween(String value1, String value2) {
            addCriterion("mcht_archive_remarks between", value1, value2, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksNotBetween(String value1, String value2) {
            addCriterion("mcht_archive_remarks not between", value1, value2, "mchtArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateIsNull() {
            addCriterion("mcht_archive_remarks_date is null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateIsNotNull() {
            addCriterion("mcht_archive_remarks_date is not null");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateEqualTo(Date value) {
            addCriterion("mcht_archive_remarks_date =", value, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateNotEqualTo(Date value) {
            addCriterion("mcht_archive_remarks_date <>", value, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateGreaterThan(Date value) {
            addCriterion("mcht_archive_remarks_date >", value, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateGreaterThanOrEqualTo(Date value) {
            addCriterion("mcht_archive_remarks_date >=", value, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateLessThan(Date value) {
            addCriterion("mcht_archive_remarks_date <", value, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateLessThanOrEqualTo(Date value) {
            addCriterion("mcht_archive_remarks_date <=", value, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateIn(List<Date> values) {
            addCriterion("mcht_archive_remarks_date in", values, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateNotIn(List<Date> values) {
            addCriterion("mcht_archive_remarks_date not in", values, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateBetween(Date value1, Date value2) {
            addCriterion("mcht_archive_remarks_date between", value1, value2, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andMchtArchiveRemarksDateNotBetween(Date value1, Date value2) {
            addCriterion("mcht_archive_remarks_date not between", value1, value2, "mchtArchiveRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusIsNull() {
            addCriterion("kf_close_hang_up_status is null");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusIsNotNull() {
            addCriterion("kf_close_hang_up_status is not null");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusEqualTo(String value) {
            addCriterion("kf_close_hang_up_status =", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusNotEqualTo(String value) {
            addCriterion("kf_close_hang_up_status <>", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusGreaterThan(String value) {
            addCriterion("kf_close_hang_up_status >", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusGreaterThanOrEqualTo(String value) {
            addCriterion("kf_close_hang_up_status >=", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusLessThan(String value) {
            addCriterion("kf_close_hang_up_status <", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusLessThanOrEqualTo(String value) {
            addCriterion("kf_close_hang_up_status <=", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusLike(String value) {
            addCriterion("kf_close_hang_up_status like", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusNotLike(String value) {
            addCriterion("kf_close_hang_up_status not like", value, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusIn(List<String> values) {
            addCriterion("kf_close_hang_up_status in", values, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusNotIn(List<String> values) {
            addCriterion("kf_close_hang_up_status not in", values, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusBetween(String value1, String value2) {
            addCriterion("kf_close_hang_up_status between", value1, value2, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andKfCloseHangUpStatusNotBetween(String value1, String value2) {
            addCriterion("kf_close_hang_up_status not between", value1, value2, "kfCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusIsNull() {
            addCriterion("order_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusIsNotNull() {
            addCriterion("order_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusEqualTo(String value) {
            addCriterion("order_confirm_status =", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusNotEqualTo(String value) {
            addCriterion("order_confirm_status <>", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusGreaterThan(String value) {
            addCriterion("order_confirm_status >", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("order_confirm_status >=", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusLessThan(String value) {
            addCriterion("order_confirm_status <", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("order_confirm_status <=", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusLike(String value) {
            addCriterion("order_confirm_status like", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusNotLike(String value) {
            addCriterion("order_confirm_status not like", value, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusIn(List<String> values) {
            addCriterion("order_confirm_status in", values, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusNotIn(List<String> values) {
            addCriterion("order_confirm_status not in", values, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusBetween(String value1, String value2) {
            addCriterion("order_confirm_status between", value1, value2, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andOrderConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("order_confirm_status not between", value1, value2, "orderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusIsNull() {
            addCriterion("service_order_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusIsNotNull() {
            addCriterion("service_order_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusEqualTo(String value) {
            addCriterion("service_order_confirm_status =", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusNotEqualTo(String value) {
            addCriterion("service_order_confirm_status <>", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusGreaterThan(String value) {
            addCriterion("service_order_confirm_status >", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("service_order_confirm_status >=", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusLessThan(String value) {
            addCriterion("service_order_confirm_status <", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("service_order_confirm_status <=", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusLike(String value) {
            addCriterion("service_order_confirm_status like", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusNotLike(String value) {
            addCriterion("service_order_confirm_status not like", value, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusIn(List<String> values) {
            addCriterion("service_order_confirm_status in", values, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusNotIn(List<String> values) {
            addCriterion("service_order_confirm_status not in", values, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusBetween(String value1, String value2) {
            addCriterion("service_order_confirm_status between", value1, value2, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andServiceOrderConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("service_order_confirm_status not between", value1, value2, "serviceOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusIsNull() {
            addCriterion("appeal_order_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusIsNotNull() {
            addCriterion("appeal_order_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusEqualTo(String value) {
            addCriterion("appeal_order_confirm_status =", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusNotEqualTo(String value) {
            addCriterion("appeal_order_confirm_status <>", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusGreaterThan(String value) {
            addCriterion("appeal_order_confirm_status >", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_order_confirm_status >=", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusLessThan(String value) {
            addCriterion("appeal_order_confirm_status <", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("appeal_order_confirm_status <=", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusLike(String value) {
            addCriterion("appeal_order_confirm_status like", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusNotLike(String value) {
            addCriterion("appeal_order_confirm_status not like", value, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusIn(List<String> values) {
            addCriterion("appeal_order_confirm_status in", values, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusNotIn(List<String> values) {
            addCriterion("appeal_order_confirm_status not in", values, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusBetween(String value1, String value2) {
            addCriterion("appeal_order_confirm_status between", value1, value2, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andAppealOrderConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("appeal_order_confirm_status not between", value1, value2, "appealOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusIsNull() {
            addCriterion("intervention_order_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusIsNotNull() {
            addCriterion("intervention_order_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusEqualTo(String value) {
            addCriterion("intervention_order_confirm_status =", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusNotEqualTo(String value) {
            addCriterion("intervention_order_confirm_status <>", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusGreaterThan(String value) {
            addCriterion("intervention_order_confirm_status >", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("intervention_order_confirm_status >=", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusLessThan(String value) {
            addCriterion("intervention_order_confirm_status <", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("intervention_order_confirm_status <=", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusLike(String value) {
            addCriterion("intervention_order_confirm_status like", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusNotLike(String value) {
            addCriterion("intervention_order_confirm_status not like", value, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusIn(List<String> values) {
            addCriterion("intervention_order_confirm_status in", values, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusNotIn(List<String> values) {
            addCriterion("intervention_order_confirm_status not in", values, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusBetween(String value1, String value2) {
            addCriterion("intervention_order_confirm_status between", value1, value2, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andInterventionOrderConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("intervention_order_confirm_status not between", value1, value2, "interventionOrderConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodIsNull() {
            addCriterion("three_package_period is null");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodIsNotNull() {
            addCriterion("three_package_period is not null");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodEqualTo(String value) {
            addCriterion("three_package_period =", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodNotEqualTo(String value) {
            addCriterion("three_package_period <>", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodGreaterThan(String value) {
            addCriterion("three_package_period >", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodGreaterThanOrEqualTo(String value) {
            addCriterion("three_package_period >=", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodLessThan(String value) {
            addCriterion("three_package_period <", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodLessThanOrEqualTo(String value) {
            addCriterion("three_package_period <=", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodLike(String value) {
            addCriterion("three_package_period like", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodNotLike(String value) {
            addCriterion("three_package_period not like", value, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodIn(List<String> values) {
            addCriterion("three_package_period in", values, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodNotIn(List<String> values) {
            addCriterion("three_package_period not in", values, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodBetween(String value1, String value2) {
            addCriterion("three_package_period between", value1, value2, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andThreePackagePeriodNotBetween(String value1, String value2) {
            addCriterion("three_package_period not between", value1, value2, "threePackagePeriod");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonIsNull() {
            addCriterion("kf_hang_up_reason is null");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonIsNotNull() {
            addCriterion("kf_hang_up_reason is not null");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonEqualTo(String value) {
            addCriterion("kf_hang_up_reason =", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonNotEqualTo(String value) {
            addCriterion("kf_hang_up_reason <>", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonGreaterThan(String value) {
            addCriterion("kf_hang_up_reason >", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonGreaterThanOrEqualTo(String value) {
            addCriterion("kf_hang_up_reason >=", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonLessThan(String value) {
            addCriterion("kf_hang_up_reason <", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonLessThanOrEqualTo(String value) {
            addCriterion("kf_hang_up_reason <=", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonLike(String value) {
            addCriterion("kf_hang_up_reason like", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonNotLike(String value) {
            addCriterion("kf_hang_up_reason not like", value, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonIn(List<String> values) {
            addCriterion("kf_hang_up_reason in", values, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonNotIn(List<String> values) {
            addCriterion("kf_hang_up_reason not in", values, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonBetween(String value1, String value2) {
            addCriterion("kf_hang_up_reason between", value1, value2, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpReasonNotBetween(String value1, String value2) {
            addCriterion("kf_hang_up_reason not between", value1, value2, "kfHangUpReason");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateIsNull() {
            addCriterion("kf_hang_up_date is null");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateIsNotNull() {
            addCriterion("kf_hang_up_date is not null");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateEqualTo(Date value) {
            addCriterion("kf_hang_up_date =", value, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateNotEqualTo(Date value) {
            addCriterion("kf_hang_up_date <>", value, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateGreaterThan(Date value) {
            addCriterion("kf_hang_up_date >", value, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("kf_hang_up_date >=", value, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateLessThan(Date value) {
            addCriterion("kf_hang_up_date <", value, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateLessThanOrEqualTo(Date value) {
            addCriterion("kf_hang_up_date <=", value, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateIn(List<Date> values) {
            addCriterion("kf_hang_up_date in", values, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateNotIn(List<Date> values) {
            addCriterion("kf_hang_up_date not in", values, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateBetween(Date value1, Date value2) {
            addCriterion("kf_hang_up_date between", value1, value2, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfHangUpDateNotBetween(Date value1, Date value2) {
            addCriterion("kf_hang_up_date not between", value1, value2, "kfHangUpDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateIsNull() {
            addCriterion("kf_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateIsNotNull() {
            addCriterion("kf_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateEqualTo(Date value) {
            addCriterion("kf_confirm_date =", value, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateNotEqualTo(Date value) {
            addCriterion("kf_confirm_date <>", value, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateGreaterThan(Date value) {
            addCriterion("kf_confirm_date >", value, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("kf_confirm_date >=", value, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateLessThan(Date value) {
            addCriterion("kf_confirm_date <", value, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("kf_confirm_date <=", value, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateIn(List<Date> values) {
            addCriterion("kf_confirm_date in", values, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateNotIn(List<Date> values) {
            addCriterion("kf_confirm_date not in", values, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateBetween(Date value1, Date value2) {
            addCriterion("kf_confirm_date between", value1, value2, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("kf_confirm_date not between", value1, value2, "kfConfirmDate");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByIsNull() {
            addCriterion("kf_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByIsNotNull() {
            addCriterion("kf_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByEqualTo(Integer value) {
            addCriterion("kf_confirm_by =", value, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByNotEqualTo(Integer value) {
            addCriterion("kf_confirm_by <>", value, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByGreaterThan(Integer value) {
            addCriterion("kf_confirm_by >", value, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("kf_confirm_by >=", value, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByLessThan(Integer value) {
            addCriterion("kf_confirm_by <", value, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("kf_confirm_by <=", value, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByIn(List<Integer> values) {
            addCriterion("kf_confirm_by in", values, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByNotIn(List<Integer> values) {
            addCriterion("kf_confirm_by not in", values, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("kf_confirm_by between", value1, value2, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("kf_confirm_by not between", value1, value2, "kfConfirmBy");
            return (Criteria) this;
        }

        public Criteria andKfRemarksIsNull() {
            addCriterion("kf_remarks is null");
            return (Criteria) this;
        }

        public Criteria andKfRemarksIsNotNull() {
            addCriterion("kf_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andKfRemarksEqualTo(String value) {
            addCriterion("kf_remarks =", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksNotEqualTo(String value) {
            addCriterion("kf_remarks <>", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksGreaterThan(String value) {
            addCriterion("kf_remarks >", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("kf_remarks >=", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksLessThan(String value) {
            addCriterion("kf_remarks <", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksLessThanOrEqualTo(String value) {
            addCriterion("kf_remarks <=", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksLike(String value) {
            addCriterion("kf_remarks like", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksNotLike(String value) {
            addCriterion("kf_remarks not like", value, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksIn(List<String> values) {
            addCriterion("kf_remarks in", values, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksNotIn(List<String> values) {
            addCriterion("kf_remarks not in", values, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksBetween(String value1, String value2) {
            addCriterion("kf_remarks between", value1, value2, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksNotBetween(String value1, String value2) {
            addCriterion("kf_remarks not between", value1, value2, "kfRemarks");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateIsNull() {
            addCriterion("kf_remarks_date is null");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateIsNotNull() {
            addCriterion("kf_remarks_date is not null");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateEqualTo(Date value) {
            addCriterion("kf_remarks_date =", value, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateNotEqualTo(Date value) {
            addCriterion("kf_remarks_date <>", value, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateGreaterThan(Date value) {
            addCriterion("kf_remarks_date >", value, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateGreaterThanOrEqualTo(Date value) {
            addCriterion("kf_remarks_date >=", value, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateLessThan(Date value) {
            addCriterion("kf_remarks_date <", value, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateLessThanOrEqualTo(Date value) {
            addCriterion("kf_remarks_date <=", value, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateIn(List<Date> values) {
            addCriterion("kf_remarks_date in", values, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateNotIn(List<Date> values) {
            addCriterion("kf_remarks_date not in", values, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateBetween(Date value1, Date value2) {
            addCriterion("kf_remarks_date between", value1, value2, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andKfRemarksDateNotBetween(Date value1, Date value2) {
            addCriterion("kf_remarks_date not between", value1, value2, "kfRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusIsNull() {
            addCriterion("cw_close_hang_up_status is null");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusIsNotNull() {
            addCriterion("cw_close_hang_up_status is not null");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusEqualTo(String value) {
            addCriterion("cw_close_hang_up_status =", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusNotEqualTo(String value) {
            addCriterion("cw_close_hang_up_status <>", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusGreaterThan(String value) {
            addCriterion("cw_close_hang_up_status >", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusGreaterThanOrEqualTo(String value) {
            addCriterion("cw_close_hang_up_status >=", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusLessThan(String value) {
            addCriterion("cw_close_hang_up_status <", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusLessThanOrEqualTo(String value) {
            addCriterion("cw_close_hang_up_status <=", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusLike(String value) {
            addCriterion("cw_close_hang_up_status like", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusNotLike(String value) {
            addCriterion("cw_close_hang_up_status not like", value, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusIn(List<String> values) {
            addCriterion("cw_close_hang_up_status in", values, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusNotIn(List<String> values) {
            addCriterion("cw_close_hang_up_status not in", values, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusBetween(String value1, String value2) {
            addCriterion("cw_close_hang_up_status between", value1, value2, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andCwCloseHangUpStatusNotBetween(String value1, String value2) {
            addCriterion("cw_close_hang_up_status not between", value1, value2, "cwCloseHangUpStatus");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmIsNull() {
            addCriterion("payment_of_goods_confirm is null");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmIsNotNull() {
            addCriterion("payment_of_goods_confirm is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmEqualTo(String value) {
            addCriterion("payment_of_goods_confirm =", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmNotEqualTo(String value) {
            addCriterion("payment_of_goods_confirm <>", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmGreaterThan(String value) {
            addCriterion("payment_of_goods_confirm >", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmGreaterThanOrEqualTo(String value) {
            addCriterion("payment_of_goods_confirm >=", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmLessThan(String value) {
            addCriterion("payment_of_goods_confirm <", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmLessThanOrEqualTo(String value) {
            addCriterion("payment_of_goods_confirm <=", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmLike(String value) {
            addCriterion("payment_of_goods_confirm like", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmNotLike(String value) {
            addCriterion("payment_of_goods_confirm not like", value, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmIn(List<String> values) {
            addCriterion("payment_of_goods_confirm in", values, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmNotIn(List<String> values) {
            addCriterion("payment_of_goods_confirm not in", values, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmBetween(String value1, String value2) {
            addCriterion("payment_of_goods_confirm between", value1, value2, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andPaymentOfGoodsConfirmNotBetween(String value1, String value2) {
            addCriterion("payment_of_goods_confirm not between", value1, value2, "paymentOfGoodsConfirm");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonIsNull() {
            addCriterion("cw_hang_up_reason is null");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonIsNotNull() {
            addCriterion("cw_hang_up_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonEqualTo(String value) {
            addCriterion("cw_hang_up_reason =", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonNotEqualTo(String value) {
            addCriterion("cw_hang_up_reason <>", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonGreaterThan(String value) {
            addCriterion("cw_hang_up_reason >", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonGreaterThanOrEqualTo(String value) {
            addCriterion("cw_hang_up_reason >=", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonLessThan(String value) {
            addCriterion("cw_hang_up_reason <", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonLessThanOrEqualTo(String value) {
            addCriterion("cw_hang_up_reason <=", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonLike(String value) {
            addCriterion("cw_hang_up_reason like", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonNotLike(String value) {
            addCriterion("cw_hang_up_reason not like", value, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonIn(List<String> values) {
            addCriterion("cw_hang_up_reason in", values, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonNotIn(List<String> values) {
            addCriterion("cw_hang_up_reason not in", values, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonBetween(String value1, String value2) {
            addCriterion("cw_hang_up_reason between", value1, value2, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwHangUpReasonNotBetween(String value1, String value2) {
            addCriterion("cw_hang_up_reason not between", value1, value2, "cwHangUpReason");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateIsNull() {
            addCriterion("cw_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateIsNotNull() {
            addCriterion("cw_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateEqualTo(Date value) {
            addCriterion("cw_confirm_date =", value, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateNotEqualTo(Date value) {
            addCriterion("cw_confirm_date <>", value, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateGreaterThan(Date value) {
            addCriterion("cw_confirm_date >", value, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("cw_confirm_date >=", value, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateLessThan(Date value) {
            addCriterion("cw_confirm_date <", value, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("cw_confirm_date <=", value, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateIn(List<Date> values) {
            addCriterion("cw_confirm_date in", values, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateNotIn(List<Date> values) {
            addCriterion("cw_confirm_date not in", values, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateBetween(Date value1, Date value2) {
            addCriterion("cw_confirm_date between", value1, value2, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("cw_confirm_date not between", value1, value2, "cwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByIsNull() {
            addCriterion("cw_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByIsNotNull() {
            addCriterion("cw_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByEqualTo(Integer value) {
            addCriterion("cw_confirm_by =", value, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByNotEqualTo(Integer value) {
            addCriterion("cw_confirm_by <>", value, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByGreaterThan(Integer value) {
            addCriterion("cw_confirm_by >", value, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("cw_confirm_by >=", value, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByLessThan(Integer value) {
            addCriterion("cw_confirm_by <", value, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("cw_confirm_by <=", value, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByIn(List<Integer> values) {
            addCriterion("cw_confirm_by in", values, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByNotIn(List<Integer> values) {
            addCriterion("cw_confirm_by not in", values, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("cw_confirm_by between", value1, value2, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("cw_confirm_by not between", value1, value2, "cwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCwRemarksIsNull() {
            addCriterion("cw_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCwRemarksIsNotNull() {
            addCriterion("cw_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCwRemarksEqualTo(String value) {
            addCriterion("cw_remarks =", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksNotEqualTo(String value) {
            addCriterion("cw_remarks <>", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksGreaterThan(String value) {
            addCriterion("cw_remarks >", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("cw_remarks >=", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksLessThan(String value) {
            addCriterion("cw_remarks <", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksLessThanOrEqualTo(String value) {
            addCriterion("cw_remarks <=", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksLike(String value) {
            addCriterion("cw_remarks like", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksNotLike(String value) {
            addCriterion("cw_remarks not like", value, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksIn(List<String> values) {
            addCriterion("cw_remarks in", values, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksNotIn(List<String> values) {
            addCriterion("cw_remarks not in", values, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksBetween(String value1, String value2) {
            addCriterion("cw_remarks between", value1, value2, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksNotBetween(String value1, String value2) {
            addCriterion("cw_remarks not between", value1, value2, "cwRemarks");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateIsNull() {
            addCriterion("cw_remarks_date is null");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateIsNotNull() {
            addCriterion("cw_remarks_date is not null");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateEqualTo(Date value) {
            addCriterion("cw_remarks_date =", value, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateNotEqualTo(Date value) {
            addCriterion("cw_remarks_date <>", value, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateGreaterThan(Date value) {
            addCriterion("cw_remarks_date >", value, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateGreaterThanOrEqualTo(Date value) {
            addCriterion("cw_remarks_date >=", value, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateLessThan(Date value) {
            addCriterion("cw_remarks_date <", value, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateLessThanOrEqualTo(Date value) {
            addCriterion("cw_remarks_date <=", value, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateIn(List<Date> values) {
            addCriterion("cw_remarks_date in", values, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateNotIn(List<Date> values) {
            addCriterion("cw_remarks_date not in", values, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateBetween(Date value1, Date value2) {
            addCriterion("cw_remarks_date between", value1, value2, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andCwRemarksDateNotBetween(Date value1, Date value2) {
            addCriterion("cw_remarks_date not between", value1, value2, "cwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
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

        public Criteria andExpressNoIsNull() {
            addCriterion("express_no is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("express_no is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("express_no =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("express_no <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("express_no >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("express_no >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("express_no <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("express_no <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("express_no like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("express_no not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("express_no in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("express_no not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("express_no between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("express_no not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementIsNull() {
            addCriterion("end_cooperation_agreement is null");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementIsNotNull() {
            addCriterion("end_cooperation_agreement is not null");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementEqualTo(String value) {
            addCriterion("end_cooperation_agreement =", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementNotEqualTo(String value) {
            addCriterion("end_cooperation_agreement <>", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementGreaterThan(String value) {
            addCriterion("end_cooperation_agreement >", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("end_cooperation_agreement >=", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementLessThan(String value) {
            addCriterion("end_cooperation_agreement <", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementLessThanOrEqualTo(String value) {
            addCriterion("end_cooperation_agreement <=", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementLike(String value) {
            addCriterion("end_cooperation_agreement like", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementNotLike(String value) {
            addCriterion("end_cooperation_agreement not like", value, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementIn(List<String> values) {
            addCriterion("end_cooperation_agreement in", values, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementNotIn(List<String> values) {
            addCriterion("end_cooperation_agreement not in", values, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementBetween(String value1, String value2) {
            addCriterion("end_cooperation_agreement between", value1, value2, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andEndCooperationAgreementNotBetween(String value1, String value2) {
            addCriterion("end_cooperation_agreement not between", value1, value2, "endCooperationAgreement");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateIsNull() {
            addCriterion("agreement_issue_date is null");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateIsNotNull() {
            addCriterion("agreement_issue_date is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateEqualTo(Date value) {
            addCriterion("agreement_issue_date =", value, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateNotEqualTo(Date value) {
            addCriterion("agreement_issue_date <>", value, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateGreaterThan(Date value) {
            addCriterion("agreement_issue_date >", value, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateGreaterThanOrEqualTo(Date value) {
            addCriterion("agreement_issue_date >=", value, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateLessThan(Date value) {
            addCriterion("agreement_issue_date <", value, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateLessThanOrEqualTo(Date value) {
            addCriterion("agreement_issue_date <=", value, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateIn(List<Date> values) {
            addCriterion("agreement_issue_date in", values, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateNotIn(List<Date> values) {
            addCriterion("agreement_issue_date not in", values, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateBetween(Date value1, Date value2) {
            addCriterion("agreement_issue_date between", value1, value2, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andAgreementIssueDateNotBetween(Date value1, Date value2) {
            addCriterion("agreement_issue_date not between", value1, value2, "agreementIssueDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateIsNull() {
            addCriterion("deposit_date is null");
            return (Criteria) this;
        }

        public Criteria andDepositDateIsNotNull() {
            addCriterion("deposit_date is not null");
            return (Criteria) this;
        }

        public Criteria andDepositDateEqualTo(Date value) {
            addCriterion("deposit_date =", value, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateNotEqualTo(Date value) {
            addCriterion("deposit_date <>", value, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateGreaterThan(Date value) {
            addCriterion("deposit_date >", value, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateGreaterThanOrEqualTo(Date value) {
            addCriterion("deposit_date >=", value, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateLessThan(Date value) {
            addCriterion("deposit_date <", value, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateLessThanOrEqualTo(Date value) {
            addCriterion("deposit_date <=", value, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateIn(List<Date> values) {
            addCriterion("deposit_date in", values, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateNotIn(List<Date> values) {
            addCriterion("deposit_date not in", values, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateBetween(Date value1, Date value2) {
            addCriterion("deposit_date between", value1, value2, "depositDate");
            return (Criteria) this;
        }

        public Criteria andDepositDateNotBetween(Date value1, Date value2) {
            addCriterion("deposit_date not between", value1, value2, "depositDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateIsNull() {
            addCriterion("fw_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateIsNotNull() {
            addCriterion("fw_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateEqualTo(Date value) {
            addCriterion("fw_confirm_date =", value, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateNotEqualTo(Date value) {
            addCriterion("fw_confirm_date <>", value, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateGreaterThan(Date value) {
            addCriterion("fw_confirm_date >", value, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("fw_confirm_date >=", value, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateLessThan(Date value) {
            addCriterion("fw_confirm_date <", value, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("fw_confirm_date <=", value, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateIn(List<Date> values) {
            addCriterion("fw_confirm_date in", values, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateNotIn(List<Date> values) {
            addCriterion("fw_confirm_date not in", values, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateBetween(Date value1, Date value2) {
            addCriterion("fw_confirm_date between", value1, value2, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("fw_confirm_date not between", value1, value2, "fwConfirmDate");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByIsNull() {
            addCriterion("fw_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByIsNotNull() {
            addCriterion("fw_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByEqualTo(Integer value) {
            addCriterion("fw_confirm_by =", value, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByNotEqualTo(Integer value) {
            addCriterion("fw_confirm_by <>", value, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByGreaterThan(Integer value) {
            addCriterion("fw_confirm_by >", value, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("fw_confirm_by >=", value, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByLessThan(Integer value) {
            addCriterion("fw_confirm_by <", value, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("fw_confirm_by <=", value, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByIn(List<Integer> values) {
            addCriterion("fw_confirm_by in", values, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByNotIn(List<Integer> values) {
            addCriterion("fw_confirm_by not in", values, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("fw_confirm_by between", value1, value2, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("fw_confirm_by not between", value1, value2, "fwConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFwRemarksIsNull() {
            addCriterion("fw_remarks is null");
            return (Criteria) this;
        }

        public Criteria andFwRemarksIsNotNull() {
            addCriterion("fw_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andFwRemarksEqualTo(String value) {
            addCriterion("fw_remarks =", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksNotEqualTo(String value) {
            addCriterion("fw_remarks <>", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksGreaterThan(String value) {
            addCriterion("fw_remarks >", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("fw_remarks >=", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksLessThan(String value) {
            addCriterion("fw_remarks <", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksLessThanOrEqualTo(String value) {
            addCriterion("fw_remarks <=", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksLike(String value) {
            addCriterion("fw_remarks like", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksNotLike(String value) {
            addCriterion("fw_remarks not like", value, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksIn(List<String> values) {
            addCriterion("fw_remarks in", values, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksNotIn(List<String> values) {
            addCriterion("fw_remarks not in", values, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksBetween(String value1, String value2) {
            addCriterion("fw_remarks between", value1, value2, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksNotBetween(String value1, String value2) {
            addCriterion("fw_remarks not between", value1, value2, "fwRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateIsNull() {
            addCriterion("fw_remarks_date is null");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateIsNotNull() {
            addCriterion("fw_remarks_date is not null");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateEqualTo(Date value) {
            addCriterion("fw_remarks_date =", value, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateNotEqualTo(Date value) {
            addCriterion("fw_remarks_date <>", value, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateGreaterThan(Date value) {
            addCriterion("fw_remarks_date >", value, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateGreaterThanOrEqualTo(Date value) {
            addCriterion("fw_remarks_date >=", value, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateLessThan(Date value) {
            addCriterion("fw_remarks_date <", value, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateLessThanOrEqualTo(Date value) {
            addCriterion("fw_remarks_date <=", value, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateIn(List<Date> values) {
            addCriterion("fw_remarks_date in", values, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateNotIn(List<Date> values) {
            addCriterion("fw_remarks_date not in", values, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateBetween(Date value1, Date value2) {
            addCriterion("fw_remarks_date between", value1, value2, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andFwRemarksDateNotBetween(Date value1, Date value2) {
            addCriterion("fw_remarks_date not between", value1, value2, "fwRemarksDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusIsNull() {
            addCriterion("director_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusIsNotNull() {
            addCriterion("director_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusEqualTo(String value) {
            addCriterion("director_confirm_status =", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusNotEqualTo(String value) {
            addCriterion("director_confirm_status <>", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusGreaterThan(String value) {
            addCriterion("director_confirm_status >", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("director_confirm_status >=", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusLessThan(String value) {
            addCriterion("director_confirm_status <", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("director_confirm_status <=", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusLike(String value) {
            addCriterion("director_confirm_status like", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusNotLike(String value) {
            addCriterion("director_confirm_status not like", value, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusIn(List<String> values) {
            addCriterion("director_confirm_status in", values, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusNotIn(List<String> values) {
            addCriterion("director_confirm_status not in", values, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusBetween(String value1, String value2) {
            addCriterion("director_confirm_status between", value1, value2, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("director_confirm_status not between", value1, value2, "directorConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateIsNull() {
            addCriterion("director_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateIsNotNull() {
            addCriterion("director_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateEqualTo(Date value) {
            addCriterion("director_confirm_date =", value, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateNotEqualTo(Date value) {
            addCriterion("director_confirm_date <>", value, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateGreaterThan(Date value) {
            addCriterion("director_confirm_date >", value, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("director_confirm_date >=", value, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateLessThan(Date value) {
            addCriterion("director_confirm_date <", value, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("director_confirm_date <=", value, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateIn(List<Date> values) {
            addCriterion("director_confirm_date in", values, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateNotIn(List<Date> values) {
            addCriterion("director_confirm_date not in", values, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateBetween(Date value1, Date value2) {
            addCriterion("director_confirm_date between", value1, value2, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("director_confirm_date not between", value1, value2, "directorConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByIsNull() {
            addCriterion("director_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByIsNotNull() {
            addCriterion("director_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByEqualTo(Integer value) {
            addCriterion("director_confirm_by =", value, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByNotEqualTo(Integer value) {
            addCriterion("director_confirm_by <>", value, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByGreaterThan(Integer value) {
            addCriterion("director_confirm_by >", value, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("director_confirm_by >=", value, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByLessThan(Integer value) {
            addCriterion("director_confirm_by <", value, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("director_confirm_by <=", value, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByIn(List<Integer> values) {
            addCriterion("director_confirm_by in", values, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByNotIn(List<Integer> values) {
            addCriterion("director_confirm_by not in", values, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("director_confirm_by between", value1, value2, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDirectorConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("director_confirm_by not between", value1, value2, "directorConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusIsNull() {
            addCriterion("product_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusIsNotNull() {
            addCriterion("product_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusEqualTo(String value) {
            addCriterion("product_confirm_status =", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusNotEqualTo(String value) {
            addCriterion("product_confirm_status <>", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusGreaterThan(String value) {
            addCriterion("product_confirm_status >", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("product_confirm_status >=", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusLessThan(String value) {
            addCriterion("product_confirm_status <", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("product_confirm_status <=", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusLike(String value) {
            addCriterion("product_confirm_status like", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusNotLike(String value) {
            addCriterion("product_confirm_status not like", value, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusIn(List<String> values) {
            addCriterion("product_confirm_status in", values, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusNotIn(List<String> values) {
            addCriterion("product_confirm_status not in", values, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusBetween(String value1, String value2) {
            addCriterion("product_confirm_status between", value1, value2, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("product_confirm_status not between", value1, value2, "productConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateIsNull() {
            addCriterion("product_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateIsNotNull() {
            addCriterion("product_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateEqualTo(Date value) {
            addCriterion("product_confirm_date =", value, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateNotEqualTo(Date value) {
            addCriterion("product_confirm_date <>", value, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateGreaterThan(Date value) {
            addCriterion("product_confirm_date >", value, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("product_confirm_date >=", value, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateLessThan(Date value) {
            addCriterion("product_confirm_date <", value, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("product_confirm_date <=", value, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateIn(List<Date> values) {
            addCriterion("product_confirm_date in", values, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateNotIn(List<Date> values) {
            addCriterion("product_confirm_date not in", values, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateBetween(Date value1, Date value2) {
            addCriterion("product_confirm_date between", value1, value2, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("product_confirm_date not between", value1, value2, "productConfirmDate");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByIsNull() {
            addCriterion("product_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByIsNotNull() {
            addCriterion("product_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByEqualTo(Integer value) {
            addCriterion("product_confirm_by =", value, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByNotEqualTo(Integer value) {
            addCriterion("product_confirm_by <>", value, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByGreaterThan(Integer value) {
            addCriterion("product_confirm_by >", value, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_confirm_by >=", value, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByLessThan(Integer value) {
            addCriterion("product_confirm_by <", value, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("product_confirm_by <=", value, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByIn(List<Integer> values) {
            addCriterion("product_confirm_by in", values, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByNotIn(List<Integer> values) {
            addCriterion("product_confirm_by not in", values, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("product_confirm_by between", value1, value2, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andProductConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("product_confirm_by not between", value1, value2, "productConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusIsNull() {
            addCriterion("settlement_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusIsNotNull() {
            addCriterion("settlement_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusEqualTo(String value) {
            addCriterion("settlement_audit_status =", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusNotEqualTo(String value) {
            addCriterion("settlement_audit_status <>", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusGreaterThan(String value) {
            addCriterion("settlement_audit_status >", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("settlement_audit_status >=", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusLessThan(String value) {
            addCriterion("settlement_audit_status <", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("settlement_audit_status <=", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusLike(String value) {
            addCriterion("settlement_audit_status like", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusNotLike(String value) {
            addCriterion("settlement_audit_status not like", value, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusIn(List<String> values) {
            addCriterion("settlement_audit_status in", values, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusNotIn(List<String> values) {
            addCriterion("settlement_audit_status not in", values, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusBetween(String value1, String value2) {
            addCriterion("settlement_audit_status between", value1, value2, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementAuditStatusNotBetween(String value1, String value2) {
            addCriterion("settlement_audit_status not between", value1, value2, "settlementAuditStatus");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateIsNull() {
            addCriterion("settlement_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateIsNotNull() {
            addCriterion("settlement_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateEqualTo(Date value) {
            addCriterion("settlement_confirm_date =", value, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateNotEqualTo(Date value) {
            addCriterion("settlement_confirm_date <>", value, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateGreaterThan(Date value) {
            addCriterion("settlement_confirm_date >", value, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("settlement_confirm_date >=", value, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateLessThan(Date value) {
            addCriterion("settlement_confirm_date <", value, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("settlement_confirm_date <=", value, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateIn(List<Date> values) {
            addCriterion("settlement_confirm_date in", values, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateNotIn(List<Date> values) {
            addCriterion("settlement_confirm_date not in", values, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateBetween(Date value1, Date value2) {
            addCriterion("settlement_confirm_date between", value1, value2, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("settlement_confirm_date not between", value1, value2, "settlementConfirmDate");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByIsNull() {
            addCriterion("settlement_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByIsNotNull() {
            addCriterion("settlement_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByEqualTo(Integer value) {
            addCriterion("settlement_confirm_by =", value, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByNotEqualTo(Integer value) {
            addCriterion("settlement_confirm_by <>", value, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByGreaterThan(Integer value) {
            addCriterion("settlement_confirm_by >", value, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("settlement_confirm_by >=", value, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByLessThan(Integer value) {
            addCriterion("settlement_confirm_by <", value, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("settlement_confirm_by <=", value, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByIn(List<Integer> values) {
            addCriterion("settlement_confirm_by in", values, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByNotIn(List<Integer> values) {
            addCriterion("settlement_confirm_by not in", values, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("settlement_confirm_by between", value1, value2, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andSettlementConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("settlement_confirm_by not between", value1, value2, "settlementConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusIsNull() {
            addCriterion("deposit_return_status is null");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusIsNotNull() {
            addCriterion("deposit_return_status is not null");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusEqualTo(String value) {
            addCriterion("deposit_return_status =", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusNotEqualTo(String value) {
            addCriterion("deposit_return_status <>", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusGreaterThan(String value) {
            addCriterion("deposit_return_status >", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_return_status >=", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusLessThan(String value) {
            addCriterion("deposit_return_status <", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusLessThanOrEqualTo(String value) {
            addCriterion("deposit_return_status <=", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusLike(String value) {
            addCriterion("deposit_return_status like", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusNotLike(String value) {
            addCriterion("deposit_return_status not like", value, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusIn(List<String> values) {
            addCriterion("deposit_return_status in", values, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusNotIn(List<String> values) {
            addCriterion("deposit_return_status not in", values, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusBetween(String value1, String value2) {
            addCriterion("deposit_return_status between", value1, value2, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnStatusNotBetween(String value1, String value2) {
            addCriterion("deposit_return_status not between", value1, value2, "depositReturnStatus");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateIsNull() {
            addCriterion("deposit_return_date is null");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateIsNotNull() {
            addCriterion("deposit_return_date is not null");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateEqualTo(Date value) {
            addCriterion("deposit_return_date =", value, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateNotEqualTo(Date value) {
            addCriterion("deposit_return_date <>", value, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateGreaterThan(Date value) {
            addCriterion("deposit_return_date >", value, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateGreaterThanOrEqualTo(Date value) {
            addCriterion("deposit_return_date >=", value, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateLessThan(Date value) {
            addCriterion("deposit_return_date <", value, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateLessThanOrEqualTo(Date value) {
            addCriterion("deposit_return_date <=", value, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateIn(List<Date> values) {
            addCriterion("deposit_return_date in", values, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateNotIn(List<Date> values) {
            addCriterion("deposit_return_date not in", values, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateBetween(Date value1, Date value2) {
            addCriterion("deposit_return_date between", value1, value2, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andDepositReturnDateNotBetween(Date value1, Date value2) {
            addCriterion("deposit_return_date not between", value1, value2, "depositReturnDate");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusIsNull() {
            addCriterion("need_pay_status is null");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusIsNotNull() {
            addCriterion("need_pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusEqualTo(String value) {
            addCriterion("need_pay_status =", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusNotEqualTo(String value) {
            addCriterion("need_pay_status <>", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusGreaterThan(String value) {
            addCriterion("need_pay_status >", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("need_pay_status >=", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusLessThan(String value) {
            addCriterion("need_pay_status <", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusLessThanOrEqualTo(String value) {
            addCriterion("need_pay_status <=", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusLike(String value) {
            addCriterion("need_pay_status like", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusNotLike(String value) {
            addCriterion("need_pay_status not like", value, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusIn(List<String> values) {
            addCriterion("need_pay_status in", values, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusNotIn(List<String> values) {
            addCriterion("need_pay_status not in", values, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusBetween(String value1, String value2) {
            addCriterion("need_pay_status between", value1, value2, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andNeedPayStatusNotBetween(String value1, String value2) {
            addCriterion("need_pay_status not between", value1, value2, "needPayStatus");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateIsNull() {
            addCriterion("op_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateIsNotNull() {
            addCriterion("op_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateEqualTo(Date value) {
            addCriterion("op_confirm_date =", value, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateNotEqualTo(Date value) {
            addCriterion("op_confirm_date <>", value, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateGreaterThan(Date value) {
            addCriterion("op_confirm_date >", value, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("op_confirm_date >=", value, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateLessThan(Date value) {
            addCriterion("op_confirm_date <", value, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("op_confirm_date <=", value, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateIn(List<Date> values) {
            addCriterion("op_confirm_date in", values, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateNotIn(List<Date> values) {
            addCriterion("op_confirm_date not in", values, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateBetween(Date value1, Date value2) {
            addCriterion("op_confirm_date between", value1, value2, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("op_confirm_date not between", value1, value2, "opConfirmDate");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByIsNull() {
            addCriterion("op_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByIsNotNull() {
            addCriterion("op_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByEqualTo(Integer value) {
            addCriterion("op_confirm_by =", value, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByNotEqualTo(Integer value) {
            addCriterion("op_confirm_by <>", value, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByGreaterThan(Integer value) {
            addCriterion("op_confirm_by >", value, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_confirm_by >=", value, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByLessThan(Integer value) {
            addCriterion("op_confirm_by <", value, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("op_confirm_by <=", value, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByIn(List<Integer> values) {
            addCriterion("op_confirm_by in", values, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByNotIn(List<Integer> values) {
            addCriterion("op_confirm_by not in", values, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("op_confirm_by between", value1, value2, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andOpConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("op_confirm_by not between", value1, value2, "opConfirmBy");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNull() {
            addCriterion("close_reason is null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNotNull() {
            addCriterion("close_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonEqualTo(String value) {
            addCriterion("close_reason =", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotEqualTo(String value) {
            addCriterion("close_reason <>", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThan(String value) {
            addCriterion("close_reason >", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("close_reason >=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThan(String value) {
            addCriterion("close_reason <", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThanOrEqualTo(String value) {
            addCriterion("close_reason <=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLike(String value) {
            addCriterion("close_reason like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotLike(String value) {
            addCriterion("close_reason not like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIn(List<String> values) {
            addCriterion("close_reason in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotIn(List<String> values) {
            addCriterion("close_reason not in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonBetween(String value1, String value2) {
            addCriterion("close_reason between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotBetween(String value1, String value2) {
            addCriterion("close_reason not between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendIsNull() {
            addCriterion("is_platform_send is null");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendIsNotNull() {
            addCriterion("is_platform_send is not null");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendEqualTo(String value) {
            addCriterion("is_platform_send =", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotEqualTo(String value) {
            addCriterion("is_platform_send <>", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendGreaterThan(String value) {
            addCriterion("is_platform_send >", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendGreaterThanOrEqualTo(String value) {
            addCriterion("is_platform_send >=", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendLessThan(String value) {
            addCriterion("is_platform_send <", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendLessThanOrEqualTo(String value) {
            addCriterion("is_platform_send <=", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendLike(String value) {
            addCriterion("is_platform_send like", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotLike(String value) {
            addCriterion("is_platform_send not like", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendIn(List<String> values) {
            addCriterion("is_platform_send in", values, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotIn(List<String> values) {
            addCriterion("is_platform_send not in", values, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendBetween(String value1, String value2) {
            addCriterion("is_platform_send between", value1, value2, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotBetween(String value1, String value2) {
            addCriterion("is_platform_send not between", value1, value2, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateIsNull() {
            addCriterion("platform_send_date is null");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateIsNotNull() {
            addCriterion("platform_send_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateEqualTo(Date value) {
            addCriterion("platform_send_date =", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateNotEqualTo(Date value) {
            addCriterion("platform_send_date <>", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateGreaterThan(Date value) {
            addCriterion("platform_send_date >", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("platform_send_date >=", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateLessThan(Date value) {
            addCriterion("platform_send_date <", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateLessThanOrEqualTo(Date value) {
            addCriterion("platform_send_date <=", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateIn(List<Date> values) {
            addCriterion("platform_send_date in", values, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateNotIn(List<Date> values) {
            addCriterion("platform_send_date not in", values, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateBetween(Date value1, Date value2) {
            addCriterion("platform_send_date between", value1, value2, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateNotBetween(Date value1, Date value2) {
            addCriterion("platform_send_date not between", value1, value2, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdIsNull() {
            addCriterion("platform_express_id is null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdIsNotNull() {
            addCriterion("platform_express_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdEqualTo(Integer value) {
            addCriterion("platform_express_id =", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdNotEqualTo(Integer value) {
            addCriterion("platform_express_id <>", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdGreaterThan(Integer value) {
            addCriterion("platform_express_id >", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_express_id >=", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdLessThan(Integer value) {
            addCriterion("platform_express_id <", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("platform_express_id <=", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdIn(List<Integer> values) {
            addCriterion("platform_express_id in", values, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdNotIn(List<Integer> values) {
            addCriterion("platform_express_id not in", values, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("platform_express_id between", value1, value2, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_express_id not between", value1, value2, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoIsNull() {
            addCriterion("platform_express_no is null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoIsNotNull() {
            addCriterion("platform_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoEqualTo(String value) {
            addCriterion("platform_express_no =", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotEqualTo(String value) {
            addCriterion("platform_express_no <>", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoGreaterThan(String value) {
            addCriterion("platform_express_no >", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("platform_express_no >=", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoLessThan(String value) {
            addCriterion("platform_express_no <", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoLessThanOrEqualTo(String value) {
            addCriterion("platform_express_no <=", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoLike(String value) {
            addCriterion("platform_express_no like", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotLike(String value) {
            addCriterion("platform_express_no not like", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoIn(List<String> values) {
            addCriterion("platform_express_no in", values, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotIn(List<String> values) {
            addCriterion("platform_express_no not in", values, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoBetween(String value1, String value2) {
            addCriterion("platform_express_no between", value1, value2, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotBetween(String value1, String value2) {
            addCriterion("platform_express_no not between", value1, value2, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusIsNull() {
            addCriterion("contract_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusIsNotNull() {
            addCriterion("contract_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusEqualTo(String value) {
            addCriterion("contract_audit_status =", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusNotEqualTo(String value) {
            addCriterion("contract_audit_status <>", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusGreaterThan(String value) {
            addCriterion("contract_audit_status >", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("contract_audit_status >=", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusLessThan(String value) {
            addCriterion("contract_audit_status <", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("contract_audit_status <=", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusLike(String value) {
            addCriterion("contract_audit_status like", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusNotLike(String value) {
            addCriterion("contract_audit_status not like", value, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusIn(List<String> values) {
            addCriterion("contract_audit_status in", values, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusNotIn(List<String> values) {
            addCriterion("contract_audit_status not in", values, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusBetween(String value1, String value2) {
            addCriterion("contract_audit_status between", value1, value2, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditStatusNotBetween(String value1, String value2) {
            addCriterion("contract_audit_status not between", value1, value2, "contractAuditStatus");
            return (Criteria) this;
        }

        public Criteria andContractAuditByIsNull() {
            addCriterion("contract_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andContractAuditByIsNotNull() {
            addCriterion("contract_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andContractAuditByEqualTo(Integer value) {
            addCriterion("contract_audit_by =", value, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByNotEqualTo(Integer value) {
            addCriterion("contract_audit_by <>", value, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByGreaterThan(Integer value) {
            addCriterion("contract_audit_by >", value, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("contract_audit_by >=", value, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByLessThan(Integer value) {
            addCriterion("contract_audit_by <", value, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("contract_audit_by <=", value, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByIn(List<Integer> values) {
            addCriterion("contract_audit_by in", values, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByNotIn(List<Integer> values) {
            addCriterion("contract_audit_by not in", values, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByBetween(Integer value1, Integer value2) {
            addCriterion("contract_audit_by between", value1, value2, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("contract_audit_by not between", value1, value2, "contractAuditBy");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateIsNull() {
            addCriterion("contract_upload_date is null");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateIsNotNull() {
            addCriterion("contract_upload_date is not null");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateEqualTo(Date value) {
            addCriterion("contract_upload_date =", value, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateNotEqualTo(Date value) {
            addCriterion("contract_upload_date <>", value, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateGreaterThan(Date value) {
            addCriterion("contract_upload_date >", value, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateGreaterThanOrEqualTo(Date value) {
            addCriterion("contract_upload_date >=", value, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateLessThan(Date value) {
            addCriterion("contract_upload_date <", value, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateLessThanOrEqualTo(Date value) {
            addCriterion("contract_upload_date <=", value, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateIn(List<Date> values) {
            addCriterion("contract_upload_date in", values, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateNotIn(List<Date> values) {
            addCriterion("contract_upload_date not in", values, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateBetween(Date value1, Date value2) {
            addCriterion("contract_upload_date between", value1, value2, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andContractUploadDateNotBetween(Date value1, Date value2) {
            addCriterion("contract_upload_date not between", value1, value2, "contractUploadDate");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksIsNull() {
            addCriterion("inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksIsNotNull() {
            addCriterion("inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksEqualTo(String value) {
            addCriterion("inner_remarks =", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotEqualTo(String value) {
            addCriterion("inner_remarks <>", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksGreaterThan(String value) {
            addCriterion("inner_remarks >", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("inner_remarks >=", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksLessThan(String value) {
            addCriterion("inner_remarks <", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("inner_remarks <=", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksLike(String value) {
            addCriterion("inner_remarks like", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotLike(String value) {
            addCriterion("inner_remarks not like", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksIn(List<String> values) {
            addCriterion("inner_remarks in", values, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotIn(List<String> values) {
            addCriterion("inner_remarks not in", values, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksBetween(String value1, String value2) {
            addCriterion("inner_remarks between", value1, value2, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("inner_remarks not between", value1, value2, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksIsNull() {
            addCriterion("contract_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksIsNotNull() {
            addCriterion("contract_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksEqualTo(String value) {
            addCriterion("contract_audit_remarks =", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksNotEqualTo(String value) {
            addCriterion("contract_audit_remarks <>", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksGreaterThan(String value) {
            addCriterion("contract_audit_remarks >", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("contract_audit_remarks >=", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksLessThan(String value) {
            addCriterion("contract_audit_remarks <", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("contract_audit_remarks <=", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksLike(String value) {
            addCriterion("contract_audit_remarks like", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksNotLike(String value) {
            addCriterion("contract_audit_remarks not like", value, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksIn(List<String> values) {
            addCriterion("contract_audit_remarks in", values, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksNotIn(List<String> values) {
            addCriterion("contract_audit_remarks not in", values, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksBetween(String value1, String value2) {
            addCriterion("contract_audit_remarks between", value1, value2, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("contract_audit_remarks not between", value1, value2, "contractAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusIsNull() {
            addCriterion("contract_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusIsNotNull() {
            addCriterion("contract_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusEqualTo(String value) {
            addCriterion("contract_archive_status =", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusNotEqualTo(String value) {
            addCriterion("contract_archive_status <>", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusGreaterThan(String value) {
            addCriterion("contract_archive_status >", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("contract_archive_status >=", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusLessThan(String value) {
            addCriterion("contract_archive_status <", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("contract_archive_status <=", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusLike(String value) {
            addCriterion("contract_archive_status like", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusNotLike(String value) {
            addCriterion("contract_archive_status not like", value, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusIn(List<String> values) {
            addCriterion("contract_archive_status in", values, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusNotIn(List<String> values) {
            addCriterion("contract_archive_status not in", values, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusBetween(String value1, String value2) {
            addCriterion("contract_archive_status between", value1, value2, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("contract_archive_status not between", value1, value2, "contractArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByIsNull() {
            addCriterion("contract_archive_by is null");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByIsNotNull() {
            addCriterion("contract_archive_by is not null");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByEqualTo(Integer value) {
            addCriterion("contract_archive_by =", value, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByNotEqualTo(Integer value) {
            addCriterion("contract_archive_by <>", value, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByGreaterThan(Integer value) {
            addCriterion("contract_archive_by >", value, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByGreaterThanOrEqualTo(Integer value) {
            addCriterion("contract_archive_by >=", value, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByLessThan(Integer value) {
            addCriterion("contract_archive_by <", value, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByLessThanOrEqualTo(Integer value) {
            addCriterion("contract_archive_by <=", value, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByIn(List<Integer> values) {
            addCriterion("contract_archive_by in", values, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByNotIn(List<Integer> values) {
            addCriterion("contract_archive_by not in", values, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByBetween(Integer value1, Integer value2) {
            addCriterion("contract_archive_by between", value1, value2, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveByNotBetween(Integer value1, Integer value2) {
            addCriterion("contract_archive_by not between", value1, value2, "contractArchiveBy");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksIsNull() {
            addCriterion("contract_archive_remarks is null");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksIsNotNull() {
            addCriterion("contract_archive_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksEqualTo(String value) {
            addCriterion("contract_archive_remarks =", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksNotEqualTo(String value) {
            addCriterion("contract_archive_remarks <>", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksGreaterThan(String value) {
            addCriterion("contract_archive_remarks >", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("contract_archive_remarks >=", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksLessThan(String value) {
            addCriterion("contract_archive_remarks <", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksLessThanOrEqualTo(String value) {
            addCriterion("contract_archive_remarks <=", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksLike(String value) {
            addCriterion("contract_archive_remarks like", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksNotLike(String value) {
            addCriterion("contract_archive_remarks not like", value, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksIn(List<String> values) {
            addCriterion("contract_archive_remarks in", values, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksNotIn(List<String> values) {
            addCriterion("contract_archive_remarks not in", values, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksBetween(String value1, String value2) {
            addCriterion("contract_archive_remarks between", value1, value2, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andContractArchiveRemarksNotBetween(String value1, String value2) {
            addCriterion("contract_archive_remarks not between", value1, value2, "contractArchiveRemarks");
            return (Criteria) this;
        }

        public Criteria andBillsPathIsNull() {
            addCriterion("bills_path is null");
            return (Criteria) this;
        }

        public Criteria andBillsPathIsNotNull() {
            addCriterion("bills_path is not null");
            return (Criteria) this;
        }

        public Criteria andBillsPathEqualTo(String value) {
            addCriterion("bills_path =", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathNotEqualTo(String value) {
            addCriterion("bills_path <>", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathGreaterThan(String value) {
            addCriterion("bills_path >", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathGreaterThanOrEqualTo(String value) {
            addCriterion("bills_path >=", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathLessThan(String value) {
            addCriterion("bills_path <", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathLessThanOrEqualTo(String value) {
            addCriterion("bills_path <=", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathLike(String value) {
            addCriterion("bills_path like", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathNotLike(String value) {
            addCriterion("bills_path not like", value, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathIn(List<String> values) {
            addCriterion("bills_path in", values, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathNotIn(List<String> values) {
            addCriterion("bills_path not in", values, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathBetween(String value1, String value2) {
            addCriterion("bills_path between", value1, value2, "billsPath");
            return (Criteria) this;
        }

        public Criteria andBillsPathNotBetween(String value1, String value2) {
            addCriterion("bills_path not between", value1, value2, "billsPath");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusIsNull() {
            addCriterion("comfirm_bills_status is null");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusIsNotNull() {
            addCriterion("comfirm_bills_status is not null");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusEqualTo(String value) {
            addCriterion("comfirm_bills_status =", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusNotEqualTo(String value) {
            addCriterion("comfirm_bills_status <>", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusGreaterThan(String value) {
            addCriterion("comfirm_bills_status >", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("comfirm_bills_status >=", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusLessThan(String value) {
            addCriterion("comfirm_bills_status <", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusLessThanOrEqualTo(String value) {
            addCriterion("comfirm_bills_status <=", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusLike(String value) {
            addCriterion("comfirm_bills_status like", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusNotLike(String value) {
            addCriterion("comfirm_bills_status not like", value, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusIn(List<String> values) {
            addCriterion("comfirm_bills_status in", values, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusNotIn(List<String> values) {
            addCriterion("comfirm_bills_status not in", values, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusBetween(String value1, String value2) {
            addCriterion("comfirm_bills_status between", value1, value2, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andComfirmBillsStatusNotBetween(String value1, String value2) {
            addCriterion("comfirm_bills_status not between", value1, value2, "comfirmBillsStatus");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountIsNull() {
            addCriterion("returned_deposit_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountIsNotNull() {
            addCriterion("returned_deposit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountEqualTo(BigDecimal value) {
            addCriterion("returned_deposit_amount =", value, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountNotEqualTo(BigDecimal value) {
            addCriterion("returned_deposit_amount <>", value, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountGreaterThan(BigDecimal value) {
            addCriterion("returned_deposit_amount >", value, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("returned_deposit_amount >=", value, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountLessThan(BigDecimal value) {
            addCriterion("returned_deposit_amount <", value, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("returned_deposit_amount <=", value, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountIn(List<BigDecimal> values) {
            addCriterion("returned_deposit_amount in", values, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountNotIn(List<BigDecimal> values) {
            addCriterion("returned_deposit_amount not in", values, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("returned_deposit_amount between", value1, value2, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andReturnedDepositAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("returned_deposit_amount not between", value1, value2, "returnedDepositAmount");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadIsNull() {
            addCriterion("is_need_upload is null");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadIsNotNull() {
            addCriterion("is_need_upload is not null");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadEqualTo(String value) {
            addCriterion("is_need_upload =", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotEqualTo(String value) {
            addCriterion("is_need_upload <>", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadGreaterThan(String value) {
            addCriterion("is_need_upload >", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadGreaterThanOrEqualTo(String value) {
            addCriterion("is_need_upload >=", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadLessThan(String value) {
            addCriterion("is_need_upload <", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadLessThanOrEqualTo(String value) {
            addCriterion("is_need_upload <=", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadLike(String value) {
            addCriterion("is_need_upload like", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotLike(String value) {
            addCriterion("is_need_upload not like", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadIn(List<String> values) {
            addCriterion("is_need_upload in", values, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotIn(List<String> values) {
            addCriterion("is_need_upload not in", values, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadBetween(String value1, String value2) {
            addCriterion("is_need_upload between", value1, value2, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotBetween(String value1, String value2) {
            addCriterion("is_need_upload not between", value1, value2, "isNeedUpload");
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