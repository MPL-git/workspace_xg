package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ProductTypeExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
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

        public Criteria andSeqNoIsNull() {
            addCriterion("seq_no is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(Integer value) {
            addCriterion("seq_no =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(Integer value) {
            addCriterion("seq_no <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(Integer value) {
            addCriterion("seq_no >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq_no >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(Integer value) {
            addCriterion("seq_no <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("seq_no <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<Integer> values) {
            addCriterion("seq_no in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<Integer> values) {
            addCriterion("seq_no not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("seq_no between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("seq_no not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSuitSexIsNull() {
            addCriterion("suit_sex is null");
            return (Criteria) this;
        }

        public Criteria andSuitSexIsNotNull() {
            addCriterion("suit_sex is not null");
            return (Criteria) this;
        }

        public Criteria andSuitSexEqualTo(String value) {
            addCriterion("suit_sex =", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotEqualTo(String value) {
            addCriterion("suit_sex <>", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexGreaterThan(String value) {
            addCriterion("suit_sex >", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexGreaterThanOrEqualTo(String value) {
            addCriterion("suit_sex >=", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLessThan(String value) {
            addCriterion("suit_sex <", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLessThanOrEqualTo(String value) {
            addCriterion("suit_sex <=", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLike(String value) {
            addCriterion("suit_sex like", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotLike(String value) {
            addCriterion("suit_sex not like", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexIn(List<String> values) {
            addCriterion("suit_sex in", values, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotIn(List<String> values) {
            addCriterion("suit_sex not in", values, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexBetween(String value1, String value2) {
            addCriterion("suit_sex between", value1, value2, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotBetween(String value1, String value2) {
            addCriterion("suit_sex not between", value1, value2, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIsNull() {
            addCriterion("suit_group is null");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIsNotNull() {
            addCriterion("suit_group is not null");
            return (Criteria) this;
        }

        public Criteria andSuitGroupEqualTo(String value) {
            addCriterion("suit_group =", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotEqualTo(String value) {
            addCriterion("suit_group <>", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupGreaterThan(String value) {
            addCriterion("suit_group >", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupGreaterThanOrEqualTo(String value) {
            addCriterion("suit_group >=", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLessThan(String value) {
            addCriterion("suit_group <", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLessThanOrEqualTo(String value) {
            addCriterion("suit_group <=", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLike(String value) {
            addCriterion("suit_group like", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotLike(String value) {
            addCriterion("suit_group not like", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIn(List<String> values) {
            addCriterion("suit_group in", values, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotIn(List<String> values) {
            addCriterion("suit_group not in", values, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupBetween(String value1, String value2) {
            addCriterion("suit_group between", value1, value2, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotBetween(String value1, String value2) {
            addCriterion("suit_group not between", value1, value2, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigDecimal value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigDecimal value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigDecimal value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigDecimal value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigDecimal> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigDecimal> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNull() {
            addCriterion("fee_rate is null");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNotNull() {
            addCriterion("fee_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRateEqualTo(BigDecimal value) {
            addCriterion("fee_rate =", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotEqualTo(BigDecimal value) {
            addCriterion("fee_rate <>", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThan(BigDecimal value) {
            addCriterion("fee_rate >", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_rate >=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThan(BigDecimal value) {
            addCriterion("fee_rate <", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_rate <=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateIn(List<BigDecimal> values) {
            addCriterion("fee_rate in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotIn(List<BigDecimal> values) {
            addCriterion("fee_rate not in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_rate between", value1, value2, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_rate not between", value1, value2, "feeRate");
            return (Criteria) this;
        }

        public Criteria andTLevelIsNull() {
            addCriterion("t_level is null");
            return (Criteria) this;
        }

        public Criteria andTLevelIsNotNull() {
            addCriterion("t_level is not null");
            return (Criteria) this;
        }

        public Criteria andTLevelEqualTo(Integer value) {
            addCriterion("t_level =", value, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelNotEqualTo(Integer value) {
            addCriterion("t_level <>", value, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelGreaterThan(Integer value) {
            addCriterion("t_level >", value, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("t_level >=", value, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelLessThan(Integer value) {
            addCriterion("t_level <", value, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelLessThanOrEqualTo(Integer value) {
            addCriterion("t_level <=", value, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelIn(List<Integer> values) {
            addCriterion("t_level in", values, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelNotIn(List<Integer> values) {
            addCriterion("t_level not in", values, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelBetween(Integer value1, Integer value2) {
            addCriterion("t_level between", value1, value2, "tLevel");
            return (Criteria) this;
        }

        public Criteria andTLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("t_level not between", value1, value2, "tLevel");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdIsNull() {
            addCriterion("principal_staff_id is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdIsNotNull() {
            addCriterion("principal_staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdEqualTo(Integer value) {
            addCriterion("principal_staff_id =", value, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdNotEqualTo(Integer value) {
            addCriterion("principal_staff_id <>", value, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdGreaterThan(Integer value) {
            addCriterion("principal_staff_id >", value, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("principal_staff_id >=", value, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdLessThan(Integer value) {
            addCriterion("principal_staff_id <", value, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("principal_staff_id <=", value, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdIn(List<Integer> values) {
            addCriterion("principal_staff_id in", values, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdNotIn(List<Integer> values) {
            addCriterion("principal_staff_id not in", values, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("principal_staff_id between", value1, value2, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andPrincipalStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("principal_staff_id not between", value1, value2, "principalStaffId");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositIsNull() {
            addCriterion("individual_deposit is null");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositIsNotNull() {
            addCriterion("individual_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositEqualTo(BigDecimal value) {
            addCriterion("individual_deposit =", value, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositNotEqualTo(BigDecimal value) {
            addCriterion("individual_deposit <>", value, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositGreaterThan(BigDecimal value) {
            addCriterion("individual_deposit >", value, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("individual_deposit >=", value, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositLessThan(BigDecimal value) {
            addCriterion("individual_deposit <", value, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("individual_deposit <=", value, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositIn(List<BigDecimal> values) {
            addCriterion("individual_deposit in", values, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositNotIn(List<BigDecimal> values) {
            addCriterion("individual_deposit not in", values, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("individual_deposit between", value1, value2, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("individual_deposit not between", value1, value2, "individualDeposit");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateIsNull() {
            addCriterion("individual_fee_rate is null");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateIsNotNull() {
            addCriterion("individual_fee_rate is not null");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateEqualTo(BigDecimal value) {
            addCriterion("individual_fee_rate =", value, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateNotEqualTo(BigDecimal value) {
            addCriterion("individual_fee_rate <>", value, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateGreaterThan(BigDecimal value) {
            addCriterion("individual_fee_rate >", value, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("individual_fee_rate >=", value, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateLessThan(BigDecimal value) {
            addCriterion("individual_fee_rate <", value, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("individual_fee_rate <=", value, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateIn(List<BigDecimal> values) {
            addCriterion("individual_fee_rate in", values, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateNotIn(List<BigDecimal> values) {
            addCriterion("individual_fee_rate not in", values, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("individual_fee_rate between", value1, value2, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andIndividualFeeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("individual_fee_rate not between", value1, value2, "individualFeeRate");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrIsNull() {
            addCriterion("enterprise_activities_DSR is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrIsNotNull() {
            addCriterion("enterprise_activities_DSR is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrEqualTo(BigDecimal value) {
            addCriterion("enterprise_activities_DSR =", value, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrNotEqualTo(BigDecimal value) {
            addCriterion("enterprise_activities_DSR <>", value, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrGreaterThan(BigDecimal value) {
            addCriterion("enterprise_activities_DSR >", value, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("enterprise_activities_DSR >=", value, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrLessThan(BigDecimal value) {
            addCriterion("enterprise_activities_DSR <", value, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrLessThanOrEqualTo(BigDecimal value) {
            addCriterion("enterprise_activities_DSR <=", value, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrIn(List<BigDecimal> values) {
            addCriterion("enterprise_activities_DSR in", values, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrNotIn(List<BigDecimal> values) {
            addCriterion("enterprise_activities_DSR not in", values, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("enterprise_activities_DSR between", value1, value2, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitiesDsrNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("enterprise_activities_DSR not between", value1, value2, "enterpriseActivitiesDsr");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverIsNull() {
            addCriterion("enterprise_turnover is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverIsNotNull() {
            addCriterion("enterprise_turnover is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverEqualTo(Integer value) {
            addCriterion("enterprise_turnover =", value, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverNotEqualTo(Integer value) {
            addCriterion("enterprise_turnover <>", value, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverGreaterThan(Integer value) {
            addCriterion("enterprise_turnover >", value, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverGreaterThanOrEqualTo(Integer value) {
            addCriterion("enterprise_turnover >=", value, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverLessThan(Integer value) {
            addCriterion("enterprise_turnover <", value, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverLessThanOrEqualTo(Integer value) {
            addCriterion("enterprise_turnover <=", value, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverIn(List<Integer> values) {
            addCriterion("enterprise_turnover in", values, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverNotIn(List<Integer> values) {
            addCriterion("enterprise_turnover not in", values, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_turnover between", value1, value2, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTurnoverNotBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_turnover not between", value1, value2, "enterpriseTurnover");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesIsNull() {
            addCriterion("enterprise_activity_sales is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesIsNotNull() {
            addCriterion("enterprise_activity_sales is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesEqualTo(Integer value) {
            addCriterion("enterprise_activity_sales =", value, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesNotEqualTo(Integer value) {
            addCriterion("enterprise_activity_sales <>", value, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesGreaterThan(Integer value) {
            addCriterion("enterprise_activity_sales >", value, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesGreaterThanOrEqualTo(Integer value) {
            addCriterion("enterprise_activity_sales >=", value, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesLessThan(Integer value) {
            addCriterion("enterprise_activity_sales <", value, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesLessThanOrEqualTo(Integer value) {
            addCriterion("enterprise_activity_sales <=", value, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesIn(List<Integer> values) {
            addCriterion("enterprise_activity_sales in", values, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesNotIn(List<Integer> values) {
            addCriterion("enterprise_activity_sales not in", values, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_activity_sales between", value1, value2, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andEnterpriseActivitySalesNotBetween(Integer value1, Integer value2) {
            addCriterion("enterprise_activity_sales not between", value1, value2, "enterpriseActivitySales");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysIsNull() {
            addCriterion("is_return_7days is null");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysIsNotNull() {
            addCriterion("is_return_7days is not null");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysEqualTo(String value) {
            addCriterion("is_return_7days =", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotEqualTo(String value) {
            addCriterion("is_return_7days <>", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysGreaterThan(String value) {
            addCriterion("is_return_7days >", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysGreaterThanOrEqualTo(String value) {
            addCriterion("is_return_7days >=", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysLessThan(String value) {
            addCriterion("is_return_7days <", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysLessThanOrEqualTo(String value) {
            addCriterion("is_return_7days <=", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysLike(String value) {
            addCriterion("is_return_7days like", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotLike(String value) {
            addCriterion("is_return_7days not like", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysIn(List<String> values) {
            addCriterion("is_return_7days in", values, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotIn(List<String> values) {
            addCriterion("is_return_7days not in", values, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysBetween(String value1, String value2) {
            addCriterion("is_return_7days between", value1, value2, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotBetween(String value1, String value2) {
            addCriterion("is_return_7days not between", value1, value2, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareIsNull() {
            addCriterion("is_smallware is null");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareIsNotNull() {
            addCriterion("is_smallware is not null");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareEqualTo(String value) {
            addCriterion("is_smallware =", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareNotEqualTo(String value) {
            addCriterion("is_smallware <>", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareGreaterThan(String value) {
            addCriterion("is_smallware >", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareGreaterThanOrEqualTo(String value) {
            addCriterion("is_smallware >=", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareLessThan(String value) {
            addCriterion("is_smallware <", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareLessThanOrEqualTo(String value) {
            addCriterion("is_smallware <=", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareLike(String value) {
            addCriterion("is_smallware like", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareNotLike(String value) {
            addCriterion("is_smallware not like", value, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareIn(List<String> values) {
            addCriterion("is_smallware in", values, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareNotIn(List<String> values) {
            addCriterion("is_smallware not in", values, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareBetween(String value1, String value2) {
            addCriterion("is_smallware between", value1, value2, "isSmallware");
            return (Criteria) this;
        }

        public Criteria andIsSmallwareNotBetween(String value1, String value2) {
            addCriterion("is_smallware not between", value1, value2, "isSmallware");
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