package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberLabelRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberLabelRuleExample() {
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

        public Criteria andLabelTypeIdIsNull() {
            addCriterion("label_type_id is null");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdIsNotNull() {
            addCriterion("label_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdEqualTo(Integer value) {
            addCriterion("label_type_id =", value, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdNotEqualTo(Integer value) {
            addCriterion("label_type_id <>", value, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdGreaterThan(Integer value) {
            addCriterion("label_type_id >", value, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("label_type_id >=", value, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdLessThan(Integer value) {
            addCriterion("label_type_id <", value, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("label_type_id <=", value, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdIn(List<Integer> values) {
            addCriterion("label_type_id in", values, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdNotIn(List<Integer> values) {
            addCriterion("label_type_id not in", values, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("label_type_id between", value1, value2, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("label_type_id not between", value1, value2, "labelTypeId");
            return (Criteria) this;
        }

        public Criteria andLabelIdIsNull() {
            addCriterion("label_id is null");
            return (Criteria) this;
        }

        public Criteria andLabelIdIsNotNull() {
            addCriterion("label_id is not null");
            return (Criteria) this;
        }

        public Criteria andLabelIdEqualTo(Integer value) {
            addCriterion("label_id =", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotEqualTo(Integer value) {
            addCriterion("label_id <>", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThan(Integer value) {
            addCriterion("label_id >", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("label_id >=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThan(Integer value) {
            addCriterion("label_id <", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThanOrEqualTo(Integer value) {
            addCriterion("label_id <=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdIn(List<Integer> values) {
            addCriterion("label_id in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotIn(List<Integer> values) {
            addCriterion("label_id not in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdBetween(Integer value1, Integer value2) {
            addCriterion("label_id between", value1, value2, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("label_id not between", value1, value2, "labelId");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginIsNull() {
            addCriterion("sign_date_begin is null");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginIsNotNull() {
            addCriterion("sign_date_begin is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginEqualTo(Date value) {
            addCriterion("sign_date_begin =", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginNotEqualTo(Date value) {
            addCriterion("sign_date_begin <>", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginGreaterThan(Date value) {
            addCriterion("sign_date_begin >", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginGreaterThanOrEqualTo(Date value) {
            addCriterion("sign_date_begin >=", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginLessThan(Date value) {
            addCriterion("sign_date_begin <", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginLessThanOrEqualTo(Date value) {
            addCriterion("sign_date_begin <=", value, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginIn(List<Date> values) {
            addCriterion("sign_date_begin in", values, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginNotIn(List<Date> values) {
            addCriterion("sign_date_begin not in", values, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginBetween(Date value1, Date value2) {
            addCriterion("sign_date_begin between", value1, value2, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateBeginNotBetween(Date value1, Date value2) {
            addCriterion("sign_date_begin not between", value1, value2, "signDateBegin");
            return (Criteria) this;
        }

        public Criteria andSignDateEndIsNull() {
            addCriterion("sign_date_end is null");
            return (Criteria) this;
        }

        public Criteria andSignDateEndIsNotNull() {
            addCriterion("sign_date_end is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateEndEqualTo(Date value) {
            addCriterion("sign_date_end =", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndNotEqualTo(Date value) {
            addCriterion("sign_date_end <>", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndGreaterThan(Date value) {
            addCriterion("sign_date_end >", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndGreaterThanOrEqualTo(Date value) {
            addCriterion("sign_date_end >=", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndLessThan(Date value) {
            addCriterion("sign_date_end <", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndLessThanOrEqualTo(Date value) {
            addCriterion("sign_date_end <=", value, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndIn(List<Date> values) {
            addCriterion("sign_date_end in", values, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndNotIn(List<Date> values) {
            addCriterion("sign_date_end not in", values, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndBetween(Date value1, Date value2) {
            addCriterion("sign_date_end between", value1, value2, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andSignDateEndNotBetween(Date value1, Date value2) {
            addCriterion("sign_date_end not between", value1, value2, "signDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginIsNull() {
            addCriterion("last_login_date_begin is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginIsNotNull() {
            addCriterion("last_login_date_begin is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginEqualTo(Date value) {
            addCriterion("last_login_date_begin =", value, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginNotEqualTo(Date value) {
            addCriterion("last_login_date_begin <>", value, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginGreaterThan(Date value) {
            addCriterion("last_login_date_begin >", value, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_date_begin >=", value, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginLessThan(Date value) {
            addCriterion("last_login_date_begin <", value, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginLessThanOrEqualTo(Date value) {
            addCriterion("last_login_date_begin <=", value, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginIn(List<Date> values) {
            addCriterion("last_login_date_begin in", values, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginNotIn(List<Date> values) {
            addCriterion("last_login_date_begin not in", values, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginBetween(Date value1, Date value2) {
            addCriterion("last_login_date_begin between", value1, value2, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBeginNotBetween(Date value1, Date value2) {
            addCriterion("last_login_date_begin not between", value1, value2, "lastLoginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndIsNull() {
            addCriterion("last_login_date_end is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndIsNotNull() {
            addCriterion("last_login_date_end is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndEqualTo(Date value) {
            addCriterion("last_login_date_end =", value, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndNotEqualTo(Date value) {
            addCriterion("last_login_date_end <>", value, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndGreaterThan(Date value) {
            addCriterion("last_login_date_end >", value, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_date_end >=", value, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndLessThan(Date value) {
            addCriterion("last_login_date_end <", value, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndLessThanOrEqualTo(Date value) {
            addCriterion("last_login_date_end <=", value, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndIn(List<Date> values) {
            addCriterion("last_login_date_end in", values, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndNotIn(List<Date> values) {
            addCriterion("last_login_date_end not in", values, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndBetween(Date value1, Date value2) {
            addCriterion("last_login_date_end between", value1, value2, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEndNotBetween(Date value1, Date value2) {
            addCriterion("last_login_date_end not between", value1, value2, "lastLoginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginIsNull() {
            addCriterion("last_expense_date_begin is null");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginIsNotNull() {
            addCriterion("last_expense_date_begin is not null");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginEqualTo(Date value) {
            addCriterion("last_expense_date_begin =", value, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginNotEqualTo(Date value) {
            addCriterion("last_expense_date_begin <>", value, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginGreaterThan(Date value) {
            addCriterion("last_expense_date_begin >", value, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginGreaterThanOrEqualTo(Date value) {
            addCriterion("last_expense_date_begin >=", value, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginLessThan(Date value) {
            addCriterion("last_expense_date_begin <", value, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginLessThanOrEqualTo(Date value) {
            addCriterion("last_expense_date_begin <=", value, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginIn(List<Date> values) {
            addCriterion("last_expense_date_begin in", values, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginNotIn(List<Date> values) {
            addCriterion("last_expense_date_begin not in", values, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginBetween(Date value1, Date value2) {
            addCriterion("last_expense_date_begin between", value1, value2, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateBeginNotBetween(Date value1, Date value2) {
            addCriterion("last_expense_date_begin not between", value1, value2, "lastExpenseDateBegin");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndIsNull() {
            addCriterion("last_expense_date_end is null");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndIsNotNull() {
            addCriterion("last_expense_date_end is not null");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndEqualTo(Date value) {
            addCriterion("last_expense_date_end =", value, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndNotEqualTo(Date value) {
            addCriterion("last_expense_date_end <>", value, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndGreaterThan(Date value) {
            addCriterion("last_expense_date_end >", value, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndGreaterThanOrEqualTo(Date value) {
            addCriterion("last_expense_date_end >=", value, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndLessThan(Date value) {
            addCriterion("last_expense_date_end <", value, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndLessThanOrEqualTo(Date value) {
            addCriterion("last_expense_date_end <=", value, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndIn(List<Date> values) {
            addCriterion("last_expense_date_end in", values, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndNotIn(List<Date> values) {
            addCriterion("last_expense_date_end not in", values, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndBetween(Date value1, Date value2) {
            addCriterion("last_expense_date_end between", value1, value2, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLastExpenseDateEndNotBetween(Date value1, Date value2) {
            addCriterion("last_expense_date_end not between", value1, value2, "lastExpenseDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginIsNull() {
            addCriterion("login_date_begin is null");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginIsNotNull() {
            addCriterion("login_date_begin is not null");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginEqualTo(Date value) {
            addCriterion("login_date_begin =", value, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginNotEqualTo(Date value) {
            addCriterion("login_date_begin <>", value, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginGreaterThan(Date value) {
            addCriterion("login_date_begin >", value, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginGreaterThanOrEqualTo(Date value) {
            addCriterion("login_date_begin >=", value, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginLessThan(Date value) {
            addCriterion("login_date_begin <", value, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginLessThanOrEqualTo(Date value) {
            addCriterion("login_date_begin <=", value, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginIn(List<Date> values) {
            addCriterion("login_date_begin in", values, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginNotIn(List<Date> values) {
            addCriterion("login_date_begin not in", values, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginBetween(Date value1, Date value2) {
            addCriterion("login_date_begin between", value1, value2, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateBeginNotBetween(Date value1, Date value2) {
            addCriterion("login_date_begin not between", value1, value2, "loginDateBegin");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndIsNull() {
            addCriterion("login_date_end is null");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndIsNotNull() {
            addCriterion("login_date_end is not null");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndEqualTo(Date value) {
            addCriterion("login_date_end =", value, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndNotEqualTo(Date value) {
            addCriterion("login_date_end <>", value, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndGreaterThan(Date value) {
            addCriterion("login_date_end >", value, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndGreaterThanOrEqualTo(Date value) {
            addCriterion("login_date_end >=", value, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndLessThan(Date value) {
            addCriterion("login_date_end <", value, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndLessThanOrEqualTo(Date value) {
            addCriterion("login_date_end <=", value, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndIn(List<Date> values) {
            addCriterion("login_date_end in", values, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndNotIn(List<Date> values) {
            addCriterion("login_date_end not in", values, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndBetween(Date value1, Date value2) {
            addCriterion("login_date_end between", value1, value2, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andLoginDateEndNotBetween(Date value1, Date value2) {
            addCriterion("login_date_end not between", value1, value2, "loginDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginIsNull() {
            addCriterion("pay_date_begin is null");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginIsNotNull() {
            addCriterion("pay_date_begin is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginEqualTo(Date value) {
            addCriterion("pay_date_begin =", value, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginNotEqualTo(Date value) {
            addCriterion("pay_date_begin <>", value, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginGreaterThan(Date value) {
            addCriterion("pay_date_begin >", value, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date_begin >=", value, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginLessThan(Date value) {
            addCriterion("pay_date_begin <", value, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginLessThanOrEqualTo(Date value) {
            addCriterion("pay_date_begin <=", value, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginIn(List<Date> values) {
            addCriterion("pay_date_begin in", values, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginNotIn(List<Date> values) {
            addCriterion("pay_date_begin not in", values, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginBetween(Date value1, Date value2) {
            addCriterion("pay_date_begin between", value1, value2, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateBeginNotBetween(Date value1, Date value2) {
            addCriterion("pay_date_begin not between", value1, value2, "payDateBegin");
            return (Criteria) this;
        }

        public Criteria andPayDateEndIsNull() {
            addCriterion("pay_date_end is null");
            return (Criteria) this;
        }

        public Criteria andPayDateEndIsNotNull() {
            addCriterion("pay_date_end is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEndEqualTo(Date value) {
            addCriterion("pay_date_end =", value, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndNotEqualTo(Date value) {
            addCriterion("pay_date_end <>", value, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndGreaterThan(Date value) {
            addCriterion("pay_date_end >", value, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date_end >=", value, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndLessThan(Date value) {
            addCriterion("pay_date_end <", value, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndLessThanOrEqualTo(Date value) {
            addCriterion("pay_date_end <=", value, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndIn(List<Date> values) {
            addCriterion("pay_date_end in", values, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndNotIn(List<Date> values) {
            addCriterion("pay_date_end not in", values, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndBetween(Date value1, Date value2) {
            addCriterion("pay_date_end between", value1, value2, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayDateEndNotBetween(Date value1, Date value2) {
            addCriterion("pay_date_end not between", value1, value2, "payDateEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginIsNull() {
            addCriterion("pay_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginIsNotNull() {
            addCriterion("pay_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginEqualTo(Integer value) {
            addCriterion("pay_count_begin =", value, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginNotEqualTo(Integer value) {
            addCriterion("pay_count_begin <>", value, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginGreaterThan(Integer value) {
            addCriterion("pay_count_begin >", value, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_count_begin >=", value, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginLessThan(Integer value) {
            addCriterion("pay_count_begin <", value, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("pay_count_begin <=", value, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginIn(List<Integer> values) {
            addCriterion("pay_count_begin in", values, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginNotIn(List<Integer> values) {
            addCriterion("pay_count_begin not in", values, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("pay_count_begin between", value1, value2, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_count_begin not between", value1, value2, "payCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayCountEndIsNull() {
            addCriterion("pay_count_end is null");
            return (Criteria) this;
        }

        public Criteria andPayCountEndIsNotNull() {
            addCriterion("pay_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andPayCountEndEqualTo(Integer value) {
            addCriterion("pay_count_end =", value, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndNotEqualTo(Integer value) {
            addCriterion("pay_count_end <>", value, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndGreaterThan(Integer value) {
            addCriterion("pay_count_end >", value, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_count_end >=", value, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndLessThan(Integer value) {
            addCriterion("pay_count_end <", value, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("pay_count_end <=", value, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndIn(List<Integer> values) {
            addCriterion("pay_count_end in", values, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndNotIn(List<Integer> values) {
            addCriterion("pay_count_end not in", values, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndBetween(Integer value1, Integer value2) {
            addCriterion("pay_count_end between", value1, value2, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_count_end not between", value1, value2, "payCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginIsNull() {
            addCriterion("pay_amount_begin is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginIsNotNull() {
            addCriterion("pay_amount_begin is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginEqualTo(BigDecimal value) {
            addCriterion("pay_amount_begin =", value, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount_begin <>", value, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginGreaterThan(BigDecimal value) {
            addCriterion("pay_amount_begin >", value, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_begin >=", value, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginLessThan(BigDecimal value) {
            addCriterion("pay_amount_begin <", value, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_begin <=", value, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginIn(List<BigDecimal> values) {
            addCriterion("pay_amount_begin in", values, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount_begin not in", values, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_begin between", value1, value2, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountBeginNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_begin not between", value1, value2, "payAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndIsNull() {
            addCriterion("pay_amount_end is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndIsNotNull() {
            addCriterion("pay_amount_end is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndEqualTo(BigDecimal value) {
            addCriterion("pay_amount_end =", value, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount_end <>", value, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndGreaterThan(BigDecimal value) {
            addCriterion("pay_amount_end >", value, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_end >=", value, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndLessThan(BigDecimal value) {
            addCriterion("pay_amount_end <", value, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_end <=", value, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndIn(List<BigDecimal> values) {
            addCriterion("pay_amount_end in", values, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount_end not in", values, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_end between", value1, value2, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayAmountEndNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_end not between", value1, value2, "payAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginIsNull() {
            addCriterion("pay_log_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginIsNotNull() {
            addCriterion("pay_log_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginEqualTo(Integer value) {
            addCriterion("pay_log_count_begin =", value, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginNotEqualTo(Integer value) {
            addCriterion("pay_log_count_begin <>", value, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginGreaterThan(Integer value) {
            addCriterion("pay_log_count_begin >", value, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_log_count_begin >=", value, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginLessThan(Integer value) {
            addCriterion("pay_log_count_begin <", value, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("pay_log_count_begin <=", value, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginIn(List<Integer> values) {
            addCriterion("pay_log_count_begin in", values, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginNotIn(List<Integer> values) {
            addCriterion("pay_log_count_begin not in", values, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("pay_log_count_begin between", value1, value2, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_log_count_begin not between", value1, value2, "payLogCountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndIsNull() {
            addCriterion("pay_log_count_end is null");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndIsNotNull() {
            addCriterion("pay_log_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndEqualTo(Integer value) {
            addCriterion("pay_log_count_end =", value, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndNotEqualTo(Integer value) {
            addCriterion("pay_log_count_end <>", value, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndGreaterThan(Integer value) {
            addCriterion("pay_log_count_end >", value, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_log_count_end >=", value, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndLessThan(Integer value) {
            addCriterion("pay_log_count_end <", value, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("pay_log_count_end <=", value, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndIn(List<Integer> values) {
            addCriterion("pay_log_count_end in", values, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndNotIn(List<Integer> values) {
            addCriterion("pay_log_count_end not in", values, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndBetween(Integer value1, Integer value2) {
            addCriterion("pay_log_count_end between", value1, value2, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_log_count_end not between", value1, value2, "payLogCountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginIsNull() {
            addCriterion("pay_log_amount_begin is null");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginIsNotNull() {
            addCriterion("pay_log_amount_begin is not null");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_begin =", value, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginNotEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_begin <>", value, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginGreaterThan(BigDecimal value) {
            addCriterion("pay_log_amount_begin >", value, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_begin >=", value, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginLessThan(BigDecimal value) {
            addCriterion("pay_log_amount_begin <", value, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_begin <=", value, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginIn(List<BigDecimal> values) {
            addCriterion("pay_log_amount_begin in", values, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginNotIn(List<BigDecimal> values) {
            addCriterion("pay_log_amount_begin not in", values, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_log_amount_begin between", value1, value2, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountBeginNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_log_amount_begin not between", value1, value2, "payLogAmountBegin");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndIsNull() {
            addCriterion("pay_log_amount_end is null");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndIsNotNull() {
            addCriterion("pay_log_amount_end is not null");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_end =", value, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndNotEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_end <>", value, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndGreaterThan(BigDecimal value) {
            addCriterion("pay_log_amount_end >", value, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_end >=", value, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndLessThan(BigDecimal value) {
            addCriterion("pay_log_amount_end <", value, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_log_amount_end <=", value, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndIn(List<BigDecimal> values) {
            addCriterion("pay_log_amount_end in", values, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndNotIn(List<BigDecimal> values) {
            addCriterion("pay_log_amount_end not in", values, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_log_amount_end between", value1, value2, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andPayLogAmountEndNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_log_amount_end not between", value1, value2, "payLogAmountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginIsNull() {
            addCriterion("sport_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginIsNotNull() {
            addCriterion("sport_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginEqualTo(Integer value) {
            addCriterion("sport_type_count_begin =", value, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("sport_type_count_begin <>", value, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginGreaterThan(Integer value) {
            addCriterion("sport_type_count_begin >", value, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("sport_type_count_begin >=", value, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginLessThan(Integer value) {
            addCriterion("sport_type_count_begin <", value, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("sport_type_count_begin <=", value, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginIn(List<Integer> values) {
            addCriterion("sport_type_count_begin in", values, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("sport_type_count_begin not in", values, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("sport_type_count_begin between", value1, value2, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("sport_type_count_begin not between", value1, value2, "sportTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndIsNull() {
            addCriterion("sport_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndIsNotNull() {
            addCriterion("sport_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndEqualTo(Integer value) {
            addCriterion("sport_type_count_end =", value, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndNotEqualTo(Integer value) {
            addCriterion("sport_type_count_end <>", value, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndGreaterThan(Integer value) {
            addCriterion("sport_type_count_end >", value, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("sport_type_count_end >=", value, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndLessThan(Integer value) {
            addCriterion("sport_type_count_end <", value, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("sport_type_count_end <=", value, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndIn(List<Integer> values) {
            addCriterion("sport_type_count_end in", values, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndNotIn(List<Integer> values) {
            addCriterion("sport_type_count_end not in", values, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("sport_type_count_end between", value1, value2, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSportTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("sport_type_count_end not between", value1, value2, "sportTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginIsNull() {
            addCriterion("costume_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginIsNotNull() {
            addCriterion("costume_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginEqualTo(Integer value) {
            addCriterion("costume_type_count_begin =", value, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("costume_type_count_begin <>", value, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginGreaterThan(Integer value) {
            addCriterion("costume_type_count_begin >", value, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("costume_type_count_begin >=", value, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginLessThan(Integer value) {
            addCriterion("costume_type_count_begin <", value, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("costume_type_count_begin <=", value, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginIn(List<Integer> values) {
            addCriterion("costume_type_count_begin in", values, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("costume_type_count_begin not in", values, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("costume_type_count_begin between", value1, value2, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("costume_type_count_begin not between", value1, value2, "costumeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndIsNull() {
            addCriterion("costume_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndIsNotNull() {
            addCriterion("costume_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndEqualTo(Integer value) {
            addCriterion("costume_type_count_end =", value, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndNotEqualTo(Integer value) {
            addCriterion("costume_type_count_end <>", value, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndGreaterThan(Integer value) {
            addCriterion("costume_type_count_end >", value, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("costume_type_count_end >=", value, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndLessThan(Integer value) {
            addCriterion("costume_type_count_end <", value, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("costume_type_count_end <=", value, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndIn(List<Integer> values) {
            addCriterion("costume_type_count_end in", values, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndNotIn(List<Integer> values) {
            addCriterion("costume_type_count_end not in", values, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("costume_type_count_end between", value1, value2, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andCostumeTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("costume_type_count_end not between", value1, value2, "costumeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginIsNull() {
            addCriterion("shoe_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginIsNotNull() {
            addCriterion("shoe_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginEqualTo(Integer value) {
            addCriterion("shoe_type_count_begin =", value, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("shoe_type_count_begin <>", value, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginGreaterThan(Integer value) {
            addCriterion("shoe_type_count_begin >", value, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("shoe_type_count_begin >=", value, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginLessThan(Integer value) {
            addCriterion("shoe_type_count_begin <", value, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("shoe_type_count_begin <=", value, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginIn(List<Integer> values) {
            addCriterion("shoe_type_count_begin in", values, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("shoe_type_count_begin not in", values, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("shoe_type_count_begin between", value1, value2, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("shoe_type_count_begin not between", value1, value2, "shoeTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndIsNull() {
            addCriterion("shoe_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndIsNotNull() {
            addCriterion("shoe_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndEqualTo(Integer value) {
            addCriterion("shoe_type_count_end =", value, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndNotEqualTo(Integer value) {
            addCriterion("shoe_type_count_end <>", value, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndGreaterThan(Integer value) {
            addCriterion("shoe_type_count_end >", value, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("shoe_type_count_end >=", value, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndLessThan(Integer value) {
            addCriterion("shoe_type_count_end <", value, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("shoe_type_count_end <=", value, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndIn(List<Integer> values) {
            addCriterion("shoe_type_count_end in", values, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndNotIn(List<Integer> values) {
            addCriterion("shoe_type_count_end not in", values, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("shoe_type_count_end between", value1, value2, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andShoeTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("shoe_type_count_end not between", value1, value2, "shoeTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginIsNull() {
            addCriterion("jewel_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginIsNotNull() {
            addCriterion("jewel_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginEqualTo(Integer value) {
            addCriterion("jewel_type_count_begin =", value, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("jewel_type_count_begin <>", value, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginGreaterThan(Integer value) {
            addCriterion("jewel_type_count_begin >", value, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("jewel_type_count_begin >=", value, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginLessThan(Integer value) {
            addCriterion("jewel_type_count_begin <", value, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("jewel_type_count_begin <=", value, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginIn(List<Integer> values) {
            addCriterion("jewel_type_count_begin in", values, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("jewel_type_count_begin not in", values, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("jewel_type_count_begin between", value1, value2, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("jewel_type_count_begin not between", value1, value2, "jewelTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndIsNull() {
            addCriterion("jewel_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndIsNotNull() {
            addCriterion("jewel_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndEqualTo(Integer value) {
            addCriterion("jewel_type_count_end =", value, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndNotEqualTo(Integer value) {
            addCriterion("jewel_type_count_end <>", value, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndGreaterThan(Integer value) {
            addCriterion("jewel_type_count_end >", value, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("jewel_type_count_end >=", value, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndLessThan(Integer value) {
            addCriterion("jewel_type_count_end <", value, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("jewel_type_count_end <=", value, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndIn(List<Integer> values) {
            addCriterion("jewel_type_count_end in", values, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndNotIn(List<Integer> values) {
            addCriterion("jewel_type_count_end not in", values, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("jewel_type_count_end between", value1, value2, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andJewelTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("jewel_type_count_end not between", value1, value2, "jewelTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginIsNull() {
            addCriterion("live_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginIsNotNull() {
            addCriterion("live_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginEqualTo(Integer value) {
            addCriterion("live_type_count_begin =", value, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("live_type_count_begin <>", value, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginGreaterThan(Integer value) {
            addCriterion("live_type_count_begin >", value, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_type_count_begin >=", value, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginLessThan(Integer value) {
            addCriterion("live_type_count_begin <", value, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("live_type_count_begin <=", value, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginIn(List<Integer> values) {
            addCriterion("live_type_count_begin in", values, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("live_type_count_begin not in", values, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("live_type_count_begin between", value1, value2, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("live_type_count_begin not between", value1, value2, "liveTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndIsNull() {
            addCriterion("live_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndIsNotNull() {
            addCriterion("live_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndEqualTo(Integer value) {
            addCriterion("live_type_count_end =", value, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndNotEqualTo(Integer value) {
            addCriterion("live_type_count_end <>", value, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndGreaterThan(Integer value) {
            addCriterion("live_type_count_end >", value, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_type_count_end >=", value, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndLessThan(Integer value) {
            addCriterion("live_type_count_end <", value, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("live_type_count_end <=", value, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndIn(List<Integer> values) {
            addCriterion("live_type_count_end in", values, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndNotIn(List<Integer> values) {
            addCriterion("live_type_count_end not in", values, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("live_type_count_end between", value1, value2, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andLiveTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("live_type_count_end not between", value1, value2, "liveTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginIsNull() {
            addCriterion("digital_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginIsNotNull() {
            addCriterion("digital_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginEqualTo(Integer value) {
            addCriterion("digital_type_count_begin =", value, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("digital_type_count_begin <>", value, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginGreaterThan(Integer value) {
            addCriterion("digital_type_count_begin >", value, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("digital_type_count_begin >=", value, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginLessThan(Integer value) {
            addCriterion("digital_type_count_begin <", value, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("digital_type_count_begin <=", value, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginIn(List<Integer> values) {
            addCriterion("digital_type_count_begin in", values, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("digital_type_count_begin not in", values, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("digital_type_count_begin between", value1, value2, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("digital_type_count_begin not between", value1, value2, "digitalTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndIsNull() {
            addCriterion("digital_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndIsNotNull() {
            addCriterion("digital_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndEqualTo(Integer value) {
            addCriterion("digital_type_count_end =", value, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndNotEqualTo(Integer value) {
            addCriterion("digital_type_count_end <>", value, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndGreaterThan(Integer value) {
            addCriterion("digital_type_count_end >", value, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("digital_type_count_end >=", value, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndLessThan(Integer value) {
            addCriterion("digital_type_count_end <", value, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("digital_type_count_end <=", value, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndIn(List<Integer> values) {
            addCriterion("digital_type_count_end in", values, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndNotIn(List<Integer> values) {
            addCriterion("digital_type_count_end not in", values, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("digital_type_count_end between", value1, value2, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andDigitalTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("digital_type_count_end not between", value1, value2, "digitalTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginIsNull() {
            addCriterion("makeup_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginIsNotNull() {
            addCriterion("makeup_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginEqualTo(Integer value) {
            addCriterion("makeup_type_count_begin =", value, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("makeup_type_count_begin <>", value, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginGreaterThan(Integer value) {
            addCriterion("makeup_type_count_begin >", value, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("makeup_type_count_begin >=", value, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginLessThan(Integer value) {
            addCriterion("makeup_type_count_begin <", value, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("makeup_type_count_begin <=", value, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginIn(List<Integer> values) {
            addCriterion("makeup_type_count_begin in", values, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("makeup_type_count_begin not in", values, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("makeup_type_count_begin between", value1, value2, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("makeup_type_count_begin not between", value1, value2, "makeupTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndIsNull() {
            addCriterion("makeup_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndIsNotNull() {
            addCriterion("makeup_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndEqualTo(Integer value) {
            addCriterion("makeup_type_count_end =", value, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndNotEqualTo(Integer value) {
            addCriterion("makeup_type_count_end <>", value, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndGreaterThan(Integer value) {
            addCriterion("makeup_type_count_end >", value, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("makeup_type_count_end >=", value, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndLessThan(Integer value) {
            addCriterion("makeup_type_count_end <", value, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("makeup_type_count_end <=", value, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndIn(List<Integer> values) {
            addCriterion("makeup_type_count_end in", values, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndNotIn(List<Integer> values) {
            addCriterion("makeup_type_count_end not in", values, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("makeup_type_count_end between", value1, value2, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andMakeupTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("makeup_type_count_end not between", value1, value2, "makeupTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginIsNull() {
            addCriterion("child_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginIsNotNull() {
            addCriterion("child_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginEqualTo(Integer value) {
            addCriterion("child_type_count_begin =", value, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("child_type_count_begin <>", value, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginGreaterThan(Integer value) {
            addCriterion("child_type_count_begin >", value, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("child_type_count_begin >=", value, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginLessThan(Integer value) {
            addCriterion("child_type_count_begin <", value, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("child_type_count_begin <=", value, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginIn(List<Integer> values) {
            addCriterion("child_type_count_begin in", values, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("child_type_count_begin not in", values, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("child_type_count_begin between", value1, value2, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("child_type_count_begin not between", value1, value2, "childTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndIsNull() {
            addCriterion("child_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndIsNotNull() {
            addCriterion("child_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndEqualTo(Integer value) {
            addCriterion("child_type_count_end =", value, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndNotEqualTo(Integer value) {
            addCriterion("child_type_count_end <>", value, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndGreaterThan(Integer value) {
            addCriterion("child_type_count_end >", value, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("child_type_count_end >=", value, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndLessThan(Integer value) {
            addCriterion("child_type_count_end <", value, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("child_type_count_end <=", value, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndIn(List<Integer> values) {
            addCriterion("child_type_count_end in", values, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndNotIn(List<Integer> values) {
            addCriterion("child_type_count_end not in", values, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("child_type_count_end between", value1, value2, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andChildTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("child_type_count_end not between", value1, value2, "childTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginIsNull() {
            addCriterion("food_type_count_begin is null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginIsNotNull() {
            addCriterion("food_type_count_begin is not null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginEqualTo(Integer value) {
            addCriterion("food_type_count_begin =", value, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginNotEqualTo(Integer value) {
            addCriterion("food_type_count_begin <>", value, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginGreaterThan(Integer value) {
            addCriterion("food_type_count_begin >", value, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_type_count_begin >=", value, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginLessThan(Integer value) {
            addCriterion("food_type_count_begin <", value, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginLessThanOrEqualTo(Integer value) {
            addCriterion("food_type_count_begin <=", value, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginIn(List<Integer> values) {
            addCriterion("food_type_count_begin in", values, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginNotIn(List<Integer> values) {
            addCriterion("food_type_count_begin not in", values, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginBetween(Integer value1, Integer value2) {
            addCriterion("food_type_count_begin between", value1, value2, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountBeginNotBetween(Integer value1, Integer value2) {
            addCriterion("food_type_count_begin not between", value1, value2, "foodTypeCountBegin");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndIsNull() {
            addCriterion("food_type_count_end is null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndIsNotNull() {
            addCriterion("food_type_count_end is not null");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndEqualTo(Integer value) {
            addCriterion("food_type_count_end =", value, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndNotEqualTo(Integer value) {
            addCriterion("food_type_count_end <>", value, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndGreaterThan(Integer value) {
            addCriterion("food_type_count_end >", value, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndGreaterThanOrEqualTo(Integer value) {
            addCriterion("food_type_count_end >=", value, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndLessThan(Integer value) {
            addCriterion("food_type_count_end <", value, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndLessThanOrEqualTo(Integer value) {
            addCriterion("food_type_count_end <=", value, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndIn(List<Integer> values) {
            addCriterion("food_type_count_end in", values, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndNotIn(List<Integer> values) {
            addCriterion("food_type_count_end not in", values, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndBetween(Integer value1, Integer value2) {
            addCriterion("food_type_count_end between", value1, value2, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andFoodTypeCountEndNotBetween(Integer value1, Integer value2) {
            addCriterion("food_type_count_end not between", value1, value2, "foodTypeCountEnd");
            return (Criteria) this;
        }

        public Criteria andSvipYearIsNull() {
            addCriterion("svip_year is null");
            return (Criteria) this;
        }

        public Criteria andSvipYearIsNotNull() {
            addCriterion("svip_year is not null");
            return (Criteria) this;
        }

        public Criteria andSvipYearEqualTo(Integer value) {
            addCriterion("svip_year =", value, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearNotEqualTo(Integer value) {
            addCriterion("svip_year <>", value, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearGreaterThan(Integer value) {
            addCriterion("svip_year >", value, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("svip_year >=", value, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearLessThan(Integer value) {
            addCriterion("svip_year <", value, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearLessThanOrEqualTo(Integer value) {
            addCriterion("svip_year <=", value, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearIn(List<Integer> values) {
            addCriterion("svip_year in", values, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearNotIn(List<Integer> values) {
            addCriterion("svip_year not in", values, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearBetween(Integer value1, Integer value2) {
            addCriterion("svip_year between", value1, value2, "svipYear");
            return (Criteria) this;
        }

        public Criteria andSvipYearNotBetween(Integer value1, Integer value2) {
            addCriterion("svip_year not between", value1, value2, "svipYear");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinIsNull() {
            addCriterion("return_goods_rate_min is null");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinIsNotNull() {
            addCriterion("return_goods_rate_min is not null");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_min =", value, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinNotEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_min <>", value, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinGreaterThan(BigDecimal value) {
            addCriterion("return_goods_rate_min >", value, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_min >=", value, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinLessThan(BigDecimal value) {
            addCriterion("return_goods_rate_min <", value, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_min <=", value, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinIn(List<BigDecimal> values) {
            addCriterion("return_goods_rate_min in", values, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinNotIn(List<BigDecimal> values) {
            addCriterion("return_goods_rate_min not in", values, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_goods_rate_min between", value1, value2, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_goods_rate_min not between", value1, value2, "returnGoodsRateMin");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxIsNull() {
            addCriterion("return_goods_rate_max is null");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxIsNotNull() {
            addCriterion("return_goods_rate_max is not null");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_max =", value, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxNotEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_max <>", value, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxGreaterThan(BigDecimal value) {
            addCriterion("return_goods_rate_max >", value, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_max >=", value, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxLessThan(BigDecimal value) {
            addCriterion("return_goods_rate_max <", value, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_goods_rate_max <=", value, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxIn(List<BigDecimal> values) {
            addCriterion("return_goods_rate_max in", values, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxNotIn(List<BigDecimal> values) {
            addCriterion("return_goods_rate_max not in", values, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_goods_rate_max between", value1, value2, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andReturnGoodsRateMaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_goods_rate_max not between", value1, value2, "returnGoodsRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinIsNull() {
            addCriterion("refund_rate_min is null");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinIsNotNull() {
            addCriterion("refund_rate_min is not null");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinEqualTo(BigDecimal value) {
            addCriterion("refund_rate_min =", value, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinNotEqualTo(BigDecimal value) {
            addCriterion("refund_rate_min <>", value, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinGreaterThan(BigDecimal value) {
            addCriterion("refund_rate_min >", value, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_rate_min >=", value, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinLessThan(BigDecimal value) {
            addCriterion("refund_rate_min <", value, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_rate_min <=", value, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinIn(List<BigDecimal> values) {
            addCriterion("refund_rate_min in", values, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinNotIn(List<BigDecimal> values) {
            addCriterion("refund_rate_min not in", values, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_rate_min between", value1, value2, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_rate_min not between", value1, value2, "refundRateMin");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxIsNull() {
            addCriterion("refund_rate_max is null");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxIsNotNull() {
            addCriterion("refund_rate_max is not null");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxEqualTo(BigDecimal value) {
            addCriterion("refund_rate_max =", value, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxNotEqualTo(BigDecimal value) {
            addCriterion("refund_rate_max <>", value, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxGreaterThan(BigDecimal value) {
            addCriterion("refund_rate_max >", value, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_rate_max >=", value, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxLessThan(BigDecimal value) {
            addCriterion("refund_rate_max <", value, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_rate_max <=", value, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxIn(List<BigDecimal> values) {
            addCriterion("refund_rate_max in", values, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxNotIn(List<BigDecimal> values) {
            addCriterion("refund_rate_max not in", values, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_rate_max between", value1, value2, "refundRateMax");
            return (Criteria) this;
        }

        public Criteria andRefundRateMaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_rate_max not between", value1, value2, "refundRateMax");
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