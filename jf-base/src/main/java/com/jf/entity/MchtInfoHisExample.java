package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MchtInfoHisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtInfoHisExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andMchtCodeIsNull() {
            addCriterion("mcht_code is null");
            return (Criteria) this;
        }

        public Criteria andMchtCodeIsNotNull() {
            addCriterion("mcht_code is not null");
            return (Criteria) this;
        }

        public Criteria andMchtCodeEqualTo(String value) {
            addCriterion("mcht_code =", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotEqualTo(String value) {
            addCriterion("mcht_code <>", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeGreaterThan(String value) {
            addCriterion("mcht_code >", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_code >=", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeLessThan(String value) {
            addCriterion("mcht_code <", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeLessThanOrEqualTo(String value) {
            addCriterion("mcht_code <=", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeLike(String value) {
            addCriterion("mcht_code like", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotLike(String value) {
            addCriterion("mcht_code not like", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeIn(List<String> values) {
            addCriterion("mcht_code in", values, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotIn(List<String> values) {
            addCriterion("mcht_code not in", values, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeBetween(String value1, String value2) {
            addCriterion("mcht_code between", value1, value2, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotBetween(String value1, String value2) {
            addCriterion("mcht_code not between", value1, value2, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNull() {
            addCriterion("short_name is null");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNotNull() {
            addCriterion("short_name is not null");
            return (Criteria) this;
        }

        public Criteria andShortNameEqualTo(String value) {
            addCriterion("short_name =", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotEqualTo(String value) {
            addCriterion("short_name <>", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThan(String value) {
            addCriterion("short_name >", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("short_name >=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThan(String value) {
            addCriterion("short_name <", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThanOrEqualTo(String value) {
            addCriterion("short_name <=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLike(String value) {
            addCriterion("short_name like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotLike(String value) {
            addCriterion("short_name not like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameIn(List<String> values) {
            addCriterion("short_name in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotIn(List<String> values) {
            addCriterion("short_name not in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameBetween(String value1, String value2) {
            addCriterion("short_name between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotBetween(String value1, String value2) {
            addCriterion("short_name not between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIsNull() {
            addCriterion("mcht_type is null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIsNotNull() {
            addCriterion("mcht_type is not null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeEqualTo(String value) {
            addCriterion("mcht_type =", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotEqualTo(String value) {
            addCriterion("mcht_type <>", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThan(String value) {
            addCriterion("mcht_type >", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_type >=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThan(String value) {
            addCriterion("mcht_type <", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThanOrEqualTo(String value) {
            addCriterion("mcht_type <=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLike(String value) {
            addCriterion("mcht_type like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotLike(String value) {
            addCriterion("mcht_type not like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIn(List<String> values) {
            addCriterion("mcht_type in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotIn(List<String> values) {
            addCriterion("mcht_type not in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeBetween(String value1, String value2) {
            addCriterion("mcht_type between", value1, value2, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotBetween(String value1, String value2) {
            addCriterion("mcht_type not between", value1, value2, "mchtType");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateIsNull() {
            addCriterion("cooperate_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateIsNotNull() {
            addCriterion("cooperate_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateEqualTo(Date value) {
            addCriterionForJDBCDate("cooperate_begin_date =", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("cooperate_begin_date <>", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateGreaterThan(Date value) {
            addCriterionForJDBCDate("cooperate_begin_date >", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("cooperate_begin_date >=", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateLessThan(Date value) {
            addCriterionForJDBCDate("cooperate_begin_date <", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("cooperate_begin_date <=", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateIn(List<Date> values) {
            addCriterionForJDBCDate("cooperate_begin_date in", values, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("cooperate_begin_date not in", values, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("cooperate_begin_date between", value1, value2, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("cooperate_begin_date not between", value1, value2, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfIsNull() {
            addCriterion("is_manage_self is null");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfIsNotNull() {
            addCriterion("is_manage_self is not null");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfEqualTo(String value) {
            addCriterion("is_manage_self =", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotEqualTo(String value) {
            addCriterion("is_manage_self <>", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfGreaterThan(String value) {
            addCriterion("is_manage_self >", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfGreaterThanOrEqualTo(String value) {
            addCriterion("is_manage_self >=", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfLessThan(String value) {
            addCriterion("is_manage_self <", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfLessThanOrEqualTo(String value) {
            addCriterion("is_manage_self <=", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfLike(String value) {
            addCriterion("is_manage_self like", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotLike(String value) {
            addCriterion("is_manage_self not like", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfIn(List<String> values) {
            addCriterion("is_manage_self in", values, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotIn(List<String> values) {
            addCriterion("is_manage_self not in", values, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfBetween(String value1, String value2) {
            addCriterion("is_manage_self between", value1, value2, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotBetween(String value1, String value2) {
            addCriterion("is_manage_self not between", value1, value2, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateIsNull() {
            addCriterion("agreement_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateIsNotNull() {
            addCriterion("agreement_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_begin_date =", value, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_begin_date <>", value, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateGreaterThan(Date value) {
            addCriterionForJDBCDate("agreement_begin_date >", value, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_begin_date >=", value, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateLessThan(Date value) {
            addCriterionForJDBCDate("agreement_begin_date <", value, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_begin_date <=", value, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_begin_date in", values, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_begin_date not in", values, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_begin_date between", value1, value2, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementBeginDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_begin_date not between", value1, value2, "agreementBeginDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateIsNull() {
            addCriterion("agreement_end_date is null");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateIsNotNull() {
            addCriterion("agreement_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end_date =", value, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end_date <>", value, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("agreement_end_date >", value, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end_date >=", value, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateLessThan(Date value) {
            addCriterionForJDBCDate("agreement_end_date <", value, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("agreement_end_date <=", value, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_end_date in", values, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("agreement_end_date not in", values, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_end_date between", value1, value2, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andAgreementEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("agreement_end_date not between", value1, value2, "agreementEndDate");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopCatalogIsNull() {
            addCriterion("shop_catalog is null");
            return (Criteria) this;
        }

        public Criteria andShopCatalogIsNotNull() {
            addCriterion("shop_catalog is not null");
            return (Criteria) this;
        }

        public Criteria andShopCatalogEqualTo(String value) {
            addCriterion("shop_catalog =", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogNotEqualTo(String value) {
            addCriterion("shop_catalog <>", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogGreaterThan(String value) {
            addCriterion("shop_catalog >", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("shop_catalog >=", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogLessThan(String value) {
            addCriterion("shop_catalog <", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogLessThanOrEqualTo(String value) {
            addCriterion("shop_catalog <=", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogLike(String value) {
            addCriterion("shop_catalog like", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogNotLike(String value) {
            addCriterion("shop_catalog not like", value, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogIn(List<String> values) {
            addCriterion("shop_catalog in", values, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogNotIn(List<String> values) {
            addCriterion("shop_catalog not in", values, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogBetween(String value1, String value2) {
            addCriterion("shop_catalog between", value1, value2, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andShopCatalogNotBetween(String value1, String value2) {
            addCriterion("shop_catalog not between", value1, value2, "shopCatalog");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsIsNull() {
            addCriterion("business_firms is null");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsIsNotNull() {
            addCriterion("business_firms is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsEqualTo(String value) {
            addCriterion("business_firms =", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotEqualTo(String value) {
            addCriterion("business_firms <>", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsGreaterThan(String value) {
            addCriterion("business_firms >", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsGreaterThanOrEqualTo(String value) {
            addCriterion("business_firms >=", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsLessThan(String value) {
            addCriterion("business_firms <", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsLessThanOrEqualTo(String value) {
            addCriterion("business_firms <=", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsLike(String value) {
            addCriterion("business_firms like", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotLike(String value) {
            addCriterion("business_firms not like", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsIn(List<String> values) {
            addCriterion("business_firms in", values, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotIn(List<String> values) {
            addCriterion("business_firms not in", values, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsBetween(String value1, String value2) {
            addCriterion("business_firms between", value1, value2, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotBetween(String value1, String value2) {
            addCriterion("business_firms not between", value1, value2, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("product_type like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("product_type not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("product_type not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andShopTypeIsNull() {
            addCriterion("shop_type is null");
            return (Criteria) this;
        }

        public Criteria andShopTypeIsNotNull() {
            addCriterion("shop_type is not null");
            return (Criteria) this;
        }

        public Criteria andShopTypeEqualTo(String value) {
            addCriterion("shop_type =", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotEqualTo(String value) {
            addCriterion("shop_type <>", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeGreaterThan(String value) {
            addCriterion("shop_type >", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_type >=", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLessThan(String value) {
            addCriterion("shop_type <", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLessThanOrEqualTo(String value) {
            addCriterion("shop_type <=", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLike(String value) {
            addCriterion("shop_type like", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotLike(String value) {
            addCriterion("shop_type not like", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeIn(List<String> values) {
            addCriterion("shop_type in", values, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotIn(List<String> values) {
            addCriterion("shop_type not in", values, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeBetween(String value1, String value2) {
            addCriterion("shop_type between", value1, value2, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotBetween(String value1, String value2) {
            addCriterion("shop_type not between", value1, value2, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusIsNull() {
            addCriterion("shop_name_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusIsNotNull() {
            addCriterion("shop_name_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusEqualTo(String value) {
            addCriterion("shop_name_audit_status =", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusNotEqualTo(String value) {
            addCriterion("shop_name_audit_status <>", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusGreaterThan(String value) {
            addCriterion("shop_name_audit_status >", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name_audit_status >=", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusLessThan(String value) {
            addCriterion("shop_name_audit_status <", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("shop_name_audit_status <=", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusLike(String value) {
            addCriterion("shop_name_audit_status like", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusNotLike(String value) {
            addCriterion("shop_name_audit_status not like", value, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusIn(List<String> values) {
            addCriterion("shop_name_audit_status in", values, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusNotIn(List<String> values) {
            addCriterion("shop_name_audit_status not in", values, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusBetween(String value1, String value2) {
            addCriterion("shop_name_audit_status between", value1, value2, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditStatusNotBetween(String value1, String value2) {
            addCriterion("shop_name_audit_status not between", value1, value2, "shopNameAuditStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksIsNull() {
            addCriterion("shop_name_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksIsNotNull() {
            addCriterion("shop_name_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksEqualTo(String value) {
            addCriterion("shop_name_audit_remarks =", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksNotEqualTo(String value) {
            addCriterion("shop_name_audit_remarks <>", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksGreaterThan(String value) {
            addCriterion("shop_name_audit_remarks >", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name_audit_remarks >=", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksLessThan(String value) {
            addCriterion("shop_name_audit_remarks <", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("shop_name_audit_remarks <=", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksLike(String value) {
            addCriterion("shop_name_audit_remarks like", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksNotLike(String value) {
            addCriterion("shop_name_audit_remarks not like", value, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksIn(List<String> values) {
            addCriterion("shop_name_audit_remarks in", values, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksNotIn(List<String> values) {
            addCriterion("shop_name_audit_remarks not in", values, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksBetween(String value1, String value2) {
            addCriterion("shop_name_audit_remarks between", value1, value2, "shopNameAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("shop_name_audit_remarks not between", value1, value2, "shopNameAuditRemarks");
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

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNull() {
            addCriterion("company_type is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNotNull() {
            addCriterion("company_type is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeEqualTo(String value) {
            addCriterion("company_type =", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotEqualTo(String value) {
            addCriterion("company_type <>", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThan(String value) {
            addCriterion("company_type >", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("company_type >=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThan(String value) {
            addCriterion("company_type <", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThanOrEqualTo(String value) {
            addCriterion("company_type <=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLike(String value) {
            addCriterion("company_type like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotLike(String value) {
            addCriterion("company_type not like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIn(List<String> values) {
            addCriterion("company_type in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotIn(List<String> values) {
            addCriterion("company_type not in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeBetween(String value1, String value2) {
            addCriterion("company_type between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotBetween(String value1, String value2) {
            addCriterion("company_type not between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameIsNull() {
            addCriterion("company_reg_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameIsNotNull() {
            addCriterion("company_reg_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameEqualTo(String value) {
            addCriterion("company_reg_name =", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameNotEqualTo(String value) {
            addCriterion("company_reg_name <>", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameGreaterThan(String value) {
            addCriterion("company_reg_name >", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_reg_name >=", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameLessThan(String value) {
            addCriterion("company_reg_name <", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameLessThanOrEqualTo(String value) {
            addCriterion("company_reg_name <=", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameLike(String value) {
            addCriterion("company_reg_name like", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameNotLike(String value) {
            addCriterion("company_reg_name not like", value, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameIn(List<String> values) {
            addCriterion("company_reg_name in", values, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameNotIn(List<String> values) {
            addCriterion("company_reg_name not in", values, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameBetween(String value1, String value2) {
            addCriterion("company_reg_name between", value1, value2, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andCompanyRegNameNotBetween(String value1, String value2) {
            addCriterion("company_reg_name not between", value1, value2, "companyRegName");
            return (Criteria) this;
        }

        public Criteria andUsccIsNull() {
            addCriterion("uscc is null");
            return (Criteria) this;
        }

        public Criteria andUsccIsNotNull() {
            addCriterion("uscc is not null");
            return (Criteria) this;
        }

        public Criteria andUsccEqualTo(String value) {
            addCriterion("uscc =", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccNotEqualTo(String value) {
            addCriterion("uscc <>", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccGreaterThan(String value) {
            addCriterion("uscc >", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccGreaterThanOrEqualTo(String value) {
            addCriterion("uscc >=", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccLessThan(String value) {
            addCriterion("uscc <", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccLessThanOrEqualTo(String value) {
            addCriterion("uscc <=", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccLike(String value) {
            addCriterion("uscc like", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccNotLike(String value) {
            addCriterion("uscc not like", value, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccIn(List<String> values) {
            addCriterion("uscc in", values, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccNotIn(List<String> values) {
            addCriterion("uscc not in", values, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccBetween(String value1, String value2) {
            addCriterion("uscc between", value1, value2, "uscc");
            return (Criteria) this;
        }

        public Criteria andUsccNotBetween(String value1, String value2) {
            addCriterion("uscc not between", value1, value2, "uscc");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andFoundedDateIsNull() {
            addCriterion("founded_date is null");
            return (Criteria) this;
        }

        public Criteria andFoundedDateIsNotNull() {
            addCriterion("founded_date is not null");
            return (Criteria) this;
        }

        public Criteria andFoundedDateEqualTo(Date value) {
            addCriterion("founded_date =", value, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateNotEqualTo(Date value) {
            addCriterion("founded_date <>", value, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateGreaterThan(Date value) {
            addCriterion("founded_date >", value, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("founded_date >=", value, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateLessThan(Date value) {
            addCriterion("founded_date <", value, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateLessThanOrEqualTo(Date value) {
            addCriterion("founded_date <=", value, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateIn(List<Date> values) {
            addCriterion("founded_date in", values, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateNotIn(List<Date> values) {
            addCriterion("founded_date not in", values, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateBetween(Date value1, Date value2) {
            addCriterion("founded_date between", value1, value2, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andFoundedDateNotBetween(Date value1, Date value2) {
            addCriterion("founded_date not between", value1, value2, "foundedDate");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIsNull() {
            addCriterion("reg_capital is null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIsNotNull() {
            addCriterion("reg_capital is not null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalEqualTo(BigDecimal value) {
            addCriterion("reg_capital =", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotEqualTo(BigDecimal value) {
            addCriterion("reg_capital <>", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalGreaterThan(BigDecimal value) {
            addCriterion("reg_capital >", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reg_capital >=", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLessThan(BigDecimal value) {
            addCriterion("reg_capital <", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reg_capital <=", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIn(List<BigDecimal> values) {
            addCriterion("reg_capital in", values, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotIn(List<BigDecimal> values) {
            addCriterion("reg_capital not in", values, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reg_capital between", value1, value2, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reg_capital not between", value1, value2, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeIsNull() {
            addCriterion("reg_fee_type is null");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeIsNotNull() {
            addCriterion("reg_fee_type is not null");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeEqualTo(String value) {
            addCriterion("reg_fee_type =", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeNotEqualTo(String value) {
            addCriterion("reg_fee_type <>", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeGreaterThan(String value) {
            addCriterion("reg_fee_type >", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("reg_fee_type >=", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeLessThan(String value) {
            addCriterion("reg_fee_type <", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeLessThanOrEqualTo(String value) {
            addCriterion("reg_fee_type <=", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeLike(String value) {
            addCriterion("reg_fee_type like", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeNotLike(String value) {
            addCriterion("reg_fee_type not like", value, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeIn(List<String> values) {
            addCriterion("reg_fee_type in", values, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeNotIn(List<String> values) {
            addCriterion("reg_fee_type not in", values, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeBetween(String value1, String value2) {
            addCriterion("reg_fee_type between", value1, value2, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andRegFeeTypeNotBetween(String value1, String value2) {
            addCriterion("reg_fee_type not between", value1, value2, "regFeeType");
            return (Criteria) this;
        }

        public Criteria andCorporationNameIsNull() {
            addCriterion("corporation_name is null");
            return (Criteria) this;
        }

        public Criteria andCorporationNameIsNotNull() {
            addCriterion("corporation_name is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationNameEqualTo(String value) {
            addCriterion("corporation_name =", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotEqualTo(String value) {
            addCriterion("corporation_name <>", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameGreaterThan(String value) {
            addCriterion("corporation_name >", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameGreaterThanOrEqualTo(String value) {
            addCriterion("corporation_name >=", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameLessThan(String value) {
            addCriterion("corporation_name <", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameLessThanOrEqualTo(String value) {
            addCriterion("corporation_name <=", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameLike(String value) {
            addCriterion("corporation_name like", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotLike(String value) {
            addCriterion("corporation_name not like", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameIn(List<String> values) {
            addCriterion("corporation_name in", values, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotIn(List<String> values) {
            addCriterion("corporation_name not in", values, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameBetween(String value1, String value2) {
            addCriterion("corporation_name between", value1, value2, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotBetween(String value1, String value2) {
            addCriterion("corporation_name not between", value1, value2, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoIsNull() {
            addCriterion("corporation_idcard_no is null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoIsNotNull() {
            addCriterion("corporation_idcard_no is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoEqualTo(String value) {
            addCriterion("corporation_idcard_no =", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoNotEqualTo(String value) {
            addCriterion("corporation_idcard_no <>", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoGreaterThan(String value) {
            addCriterion("corporation_idcard_no >", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoGreaterThanOrEqualTo(String value) {
            addCriterion("corporation_idcard_no >=", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoLessThan(String value) {
            addCriterion("corporation_idcard_no <", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoLessThanOrEqualTo(String value) {
            addCriterion("corporation_idcard_no <=", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoLike(String value) {
            addCriterion("corporation_idcard_no like", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoNotLike(String value) {
            addCriterion("corporation_idcard_no not like", value, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoIn(List<String> values) {
            addCriterion("corporation_idcard_no in", values, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoNotIn(List<String> values) {
            addCriterion("corporation_idcard_no not in", values, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoBetween(String value1, String value2) {
            addCriterion("corporation_idcard_no between", value1, value2, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardNoNotBetween(String value1, String value2) {
            addCriterion("corporation_idcard_no not between", value1, value2, "corporationIdcardNo");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileIsNull() {
            addCriterion("corporation_mobile is null");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileIsNotNull() {
            addCriterion("corporation_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileEqualTo(String value) {
            addCriterion("corporation_mobile =", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileNotEqualTo(String value) {
            addCriterion("corporation_mobile <>", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileGreaterThan(String value) {
            addCriterion("corporation_mobile >", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileGreaterThanOrEqualTo(String value) {
            addCriterion("corporation_mobile >=", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileLessThan(String value) {
            addCriterion("corporation_mobile <", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileLessThanOrEqualTo(String value) {
            addCriterion("corporation_mobile <=", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileLike(String value) {
            addCriterion("corporation_mobile like", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileNotLike(String value) {
            addCriterion("corporation_mobile not like", value, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileIn(List<String> values) {
            addCriterion("corporation_mobile in", values, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileNotIn(List<String> values) {
            addCriterion("corporation_mobile not in", values, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileBetween(String value1, String value2) {
            addCriterion("corporation_mobile between", value1, value2, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationMobileNotBetween(String value1, String value2) {
            addCriterion("corporation_mobile not between", value1, value2, "corporationMobile");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1IsNull() {
            addCriterion("corporation_idcard_img_1 is null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1IsNotNull() {
            addCriterion("corporation_idcard_img_1 is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1EqualTo(String value) {
            addCriterion("corporation_idcard_img_1 =", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1NotEqualTo(String value) {
            addCriterion("corporation_idcard_img_1 <>", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1GreaterThan(String value) {
            addCriterion("corporation_idcard_img_1 >", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1GreaterThanOrEqualTo(String value) {
            addCriterion("corporation_idcard_img_1 >=", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1LessThan(String value) {
            addCriterion("corporation_idcard_img_1 <", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1LessThanOrEqualTo(String value) {
            addCriterion("corporation_idcard_img_1 <=", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1Like(String value) {
            addCriterion("corporation_idcard_img_1 like", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1NotLike(String value) {
            addCriterion("corporation_idcard_img_1 not like", value, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1In(List<String> values) {
            addCriterion("corporation_idcard_img_1 in", values, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1NotIn(List<String> values) {
            addCriterion("corporation_idcard_img_1 not in", values, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1Between(String value1, String value2) {
            addCriterion("corporation_idcard_img_1 between", value1, value2, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg1NotBetween(String value1, String value2) {
            addCriterion("corporation_idcard_img_1 not between", value1, value2, "corporationIdcardImg1");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2IsNull() {
            addCriterion("corporation_idcard_img_2 is null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2IsNotNull() {
            addCriterion("corporation_idcard_img_2 is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2EqualTo(String value) {
            addCriterion("corporation_idcard_img_2 =", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2NotEqualTo(String value) {
            addCriterion("corporation_idcard_img_2 <>", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2GreaterThan(String value) {
            addCriterion("corporation_idcard_img_2 >", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2GreaterThanOrEqualTo(String value) {
            addCriterion("corporation_idcard_img_2 >=", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2LessThan(String value) {
            addCriterion("corporation_idcard_img_2 <", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2LessThanOrEqualTo(String value) {
            addCriterion("corporation_idcard_img_2 <=", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2Like(String value) {
            addCriterion("corporation_idcard_img_2 like", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2NotLike(String value) {
            addCriterion("corporation_idcard_img_2 not like", value, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2In(List<String> values) {
            addCriterion("corporation_idcard_img_2 in", values, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2NotIn(List<String> values) {
            addCriterion("corporation_idcard_img_2 not in", values, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2Between(String value1, String value2) {
            addCriterion("corporation_idcard_img_2 between", value1, value2, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardImg2NotBetween(String value1, String value2) {
            addCriterion("corporation_idcard_img_2 not between", value1, value2, "corporationIdcardImg2");
            return (Criteria) this;
        }

        public Criteria andBlPicIsNull() {
            addCriterion("bl_pic is null");
            return (Criteria) this;
        }

        public Criteria andBlPicIsNotNull() {
            addCriterion("bl_pic is not null");
            return (Criteria) this;
        }

        public Criteria andBlPicEqualTo(String value) {
            addCriterion("bl_pic =", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicNotEqualTo(String value) {
            addCriterion("bl_pic <>", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicGreaterThan(String value) {
            addCriterion("bl_pic >", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicGreaterThanOrEqualTo(String value) {
            addCriterion("bl_pic >=", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicLessThan(String value) {
            addCriterion("bl_pic <", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicLessThanOrEqualTo(String value) {
            addCriterion("bl_pic <=", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicLike(String value) {
            addCriterion("bl_pic like", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicNotLike(String value) {
            addCriterion("bl_pic not like", value, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicIn(List<String> values) {
            addCriterion("bl_pic in", values, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicNotIn(List<String> values) {
            addCriterion("bl_pic not in", values, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicBetween(String value1, String value2) {
            addCriterion("bl_pic between", value1, value2, "blPic");
            return (Criteria) this;
        }

        public Criteria andBlPicNotBetween(String value1, String value2) {
            addCriterion("bl_pic not between", value1, value2, "blPic");
            return (Criteria) this;
        }

        public Criteria andOccPicIsNull() {
            addCriterion("occ_pic is null");
            return (Criteria) this;
        }

        public Criteria andOccPicIsNotNull() {
            addCriterion("occ_pic is not null");
            return (Criteria) this;
        }

        public Criteria andOccPicEqualTo(String value) {
            addCriterion("occ_pic =", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicNotEqualTo(String value) {
            addCriterion("occ_pic <>", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicGreaterThan(String value) {
            addCriterion("occ_pic >", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicGreaterThanOrEqualTo(String value) {
            addCriterion("occ_pic >=", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicLessThan(String value) {
            addCriterion("occ_pic <", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicLessThanOrEqualTo(String value) {
            addCriterion("occ_pic <=", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicLike(String value) {
            addCriterion("occ_pic like", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicNotLike(String value) {
            addCriterion("occ_pic not like", value, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicIn(List<String> values) {
            addCriterion("occ_pic in", values, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicNotIn(List<String> values) {
            addCriterion("occ_pic not in", values, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicBetween(String value1, String value2) {
            addCriterion("occ_pic between", value1, value2, "occPic");
            return (Criteria) this;
        }

        public Criteria andOccPicNotBetween(String value1, String value2) {
            addCriterion("occ_pic not between", value1, value2, "occPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicIsNull() {
            addCriterion("trc_pic is null");
            return (Criteria) this;
        }

        public Criteria andTrcPicIsNotNull() {
            addCriterion("trc_pic is not null");
            return (Criteria) this;
        }

        public Criteria andTrcPicEqualTo(String value) {
            addCriterion("trc_pic =", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicNotEqualTo(String value) {
            addCriterion("trc_pic <>", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicGreaterThan(String value) {
            addCriterion("trc_pic >", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicGreaterThanOrEqualTo(String value) {
            addCriterion("trc_pic >=", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicLessThan(String value) {
            addCriterion("trc_pic <", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicLessThanOrEqualTo(String value) {
            addCriterion("trc_pic <=", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicLike(String value) {
            addCriterion("trc_pic like", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicNotLike(String value) {
            addCriterion("trc_pic not like", value, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicIn(List<String> values) {
            addCriterion("trc_pic in", values, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicNotIn(List<String> values) {
            addCriterion("trc_pic not in", values, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicBetween(String value1, String value2) {
            addCriterion("trc_pic between", value1, value2, "trcPic");
            return (Criteria) this;
        }

        public Criteria andTrcPicNotBetween(String value1, String value2) {
            addCriterion("trc_pic not between", value1, value2, "trcPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicIsNull() {
            addCriterion("atq_pic is null");
            return (Criteria) this;
        }

        public Criteria andAtqPicIsNotNull() {
            addCriterion("atq_pic is not null");
            return (Criteria) this;
        }

        public Criteria andAtqPicEqualTo(String value) {
            addCriterion("atq_pic =", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicNotEqualTo(String value) {
            addCriterion("atq_pic <>", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicGreaterThan(String value) {
            addCriterion("atq_pic >", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicGreaterThanOrEqualTo(String value) {
            addCriterion("atq_pic >=", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicLessThan(String value) {
            addCriterion("atq_pic <", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicLessThanOrEqualTo(String value) {
            addCriterion("atq_pic <=", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicLike(String value) {
            addCriterion("atq_pic like", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicNotLike(String value) {
            addCriterion("atq_pic not like", value, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicIn(List<String> values) {
            addCriterion("atq_pic in", values, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicNotIn(List<String> values) {
            addCriterion("atq_pic not in", values, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicBetween(String value1, String value2) {
            addCriterion("atq_pic between", value1, value2, "atqPic");
            return (Criteria) this;
        }

        public Criteria andAtqPicNotBetween(String value1, String value2) {
            addCriterion("atq_pic not between", value1, value2, "atqPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicIsNull() {
            addCriterion("boaal_pic is null");
            return (Criteria) this;
        }

        public Criteria andBoaalPicIsNotNull() {
            addCriterion("boaal_pic is not null");
            return (Criteria) this;
        }

        public Criteria andBoaalPicEqualTo(String value) {
            addCriterion("boaal_pic =", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicNotEqualTo(String value) {
            addCriterion("boaal_pic <>", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicGreaterThan(String value) {
            addCriterion("boaal_pic >", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicGreaterThanOrEqualTo(String value) {
            addCriterion("boaal_pic >=", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicLessThan(String value) {
            addCriterion("boaal_pic <", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicLessThanOrEqualTo(String value) {
            addCriterion("boaal_pic <=", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicLike(String value) {
            addCriterion("boaal_pic like", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicNotLike(String value) {
            addCriterion("boaal_pic not like", value, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicIn(List<String> values) {
            addCriterion("boaal_pic in", values, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicNotIn(List<String> values) {
            addCriterion("boaal_pic not in", values, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicBetween(String value1, String value2) {
            addCriterion("boaal_pic between", value1, value2, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andBoaalPicNotBetween(String value1, String value2) {
            addCriterion("boaal_pic not between", value1, value2, "boaalPic");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateIsNull() {
            addCriterion("yearly_inspection_date is null");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateIsNotNull() {
            addCriterion("yearly_inspection_date is not null");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateEqualTo(Date value) {
            addCriterion("yearly_inspection_date =", value, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateNotEqualTo(Date value) {
            addCriterion("yearly_inspection_date <>", value, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateGreaterThan(Date value) {
            addCriterion("yearly_inspection_date >", value, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateGreaterThanOrEqualTo(Date value) {
            addCriterion("yearly_inspection_date >=", value, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateLessThan(Date value) {
            addCriterion("yearly_inspection_date <", value, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateLessThanOrEqualTo(Date value) {
            addCriterion("yearly_inspection_date <=", value, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateIn(List<Date> values) {
            addCriterion("yearly_inspection_date in", values, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateNotIn(List<Date> values) {
            addCriterion("yearly_inspection_date not in", values, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateBetween(Date value1, Date value2) {
            addCriterion("yearly_inspection_date between", value1, value2, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andYearlyInspectionDateNotBetween(Date value1, Date value2) {
            addCriterion("yearly_inspection_date not between", value1, value2, "yearlyInspectionDate");
            return (Criteria) this;
        }

        public Criteria andCompanyTelIsNull() {
            addCriterion("company_tel is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTelIsNotNull() {
            addCriterion("company_tel is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTelEqualTo(String value) {
            addCriterion("company_tel =", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotEqualTo(String value) {
            addCriterion("company_tel <>", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelGreaterThan(String value) {
            addCriterion("company_tel >", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelGreaterThanOrEqualTo(String value) {
            addCriterion("company_tel >=", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelLessThan(String value) {
            addCriterion("company_tel <", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelLessThanOrEqualTo(String value) {
            addCriterion("company_tel <=", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelLike(String value) {
            addCriterion("company_tel like", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotLike(String value) {
            addCriterion("company_tel not like", value, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelIn(List<String> values) {
            addCriterion("company_tel in", values, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotIn(List<String> values) {
            addCriterion("company_tel not in", values, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelBetween(String value1, String value2) {
            addCriterion("company_tel between", value1, value2, "companyTel");
            return (Criteria) this;
        }

        public Criteria andCompanyTelNotBetween(String value1, String value2) {
            addCriterion("company_tel not between", value1, value2, "companyTel");
            return (Criteria) this;
        }

        public Criteria andContactProvinceIsNull() {
            addCriterion("contact_province is null");
            return (Criteria) this;
        }

        public Criteria andContactProvinceIsNotNull() {
            addCriterion("contact_province is not null");
            return (Criteria) this;
        }

        public Criteria andContactProvinceEqualTo(Integer value) {
            addCriterion("contact_province =", value, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceNotEqualTo(Integer value) {
            addCriterion("contact_province <>", value, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceGreaterThan(Integer value) {
            addCriterion("contact_province >", value, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_province >=", value, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceLessThan(Integer value) {
            addCriterion("contact_province <", value, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceLessThanOrEqualTo(Integer value) {
            addCriterion("contact_province <=", value, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceIn(List<Integer> values) {
            addCriterion("contact_province in", values, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceNotIn(List<Integer> values) {
            addCriterion("contact_province not in", values, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceBetween(Integer value1, Integer value2) {
            addCriterion("contact_province between", value1, value2, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactProvinceNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_province not between", value1, value2, "contactProvince");
            return (Criteria) this;
        }

        public Criteria andContactCityIsNull() {
            addCriterion("contact_city is null");
            return (Criteria) this;
        }

        public Criteria andContactCityIsNotNull() {
            addCriterion("contact_city is not null");
            return (Criteria) this;
        }

        public Criteria andContactCityEqualTo(Integer value) {
            addCriterion("contact_city =", value, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityNotEqualTo(Integer value) {
            addCriterion("contact_city <>", value, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityGreaterThan(Integer value) {
            addCriterion("contact_city >", value, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_city >=", value, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityLessThan(Integer value) {
            addCriterion("contact_city <", value, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityLessThanOrEqualTo(Integer value) {
            addCriterion("contact_city <=", value, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityIn(List<Integer> values) {
            addCriterion("contact_city in", values, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityNotIn(List<Integer> values) {
            addCriterion("contact_city not in", values, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityBetween(Integer value1, Integer value2) {
            addCriterion("contact_city between", value1, value2, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCityNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_city not between", value1, value2, "contactCity");
            return (Criteria) this;
        }

        public Criteria andContactCountyIsNull() {
            addCriterion("contact_county is null");
            return (Criteria) this;
        }

        public Criteria andContactCountyIsNotNull() {
            addCriterion("contact_county is not null");
            return (Criteria) this;
        }

        public Criteria andContactCountyEqualTo(Integer value) {
            addCriterion("contact_county =", value, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyNotEqualTo(Integer value) {
            addCriterion("contact_county <>", value, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyGreaterThan(Integer value) {
            addCriterion("contact_county >", value, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_county >=", value, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyLessThan(Integer value) {
            addCriterion("contact_county <", value, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyLessThanOrEqualTo(Integer value) {
            addCriterion("contact_county <=", value, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyIn(List<Integer> values) {
            addCriterion("contact_county in", values, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyNotIn(List<Integer> values) {
            addCriterion("contact_county not in", values, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyBetween(Integer value1, Integer value2) {
            addCriterion("contact_county between", value1, value2, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactCountyNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_county not between", value1, value2, "contactCounty");
            return (Criteria) this;
        }

        public Criteria andContactAddressIsNull() {
            addCriterion("contact_address is null");
            return (Criteria) this;
        }

        public Criteria andContactAddressIsNotNull() {
            addCriterion("contact_address is not null");
            return (Criteria) this;
        }

        public Criteria andContactAddressEqualTo(String value) {
            addCriterion("contact_address =", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotEqualTo(String value) {
            addCriterion("contact_address <>", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressGreaterThan(String value) {
            addCriterion("contact_address >", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressGreaterThanOrEqualTo(String value) {
            addCriterion("contact_address >=", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressLessThan(String value) {
            addCriterion("contact_address <", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressLessThanOrEqualTo(String value) {
            addCriterion("contact_address <=", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressLike(String value) {
            addCriterion("contact_address like", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotLike(String value) {
            addCriterion("contact_address not like", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressIn(List<String> values) {
            addCriterion("contact_address in", values, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotIn(List<String> values) {
            addCriterion("contact_address not in", values, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressBetween(String value1, String value2) {
            addCriterion("contact_address between", value1, value2, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotBetween(String value1, String value2) {
            addCriterion("contact_address not between", value1, value2, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectIsNull() {
            addCriterion("is_company_inf_perfect is null");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectIsNotNull() {
            addCriterion("is_company_inf_perfect is not null");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectEqualTo(String value) {
            addCriterion("is_company_inf_perfect =", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectNotEqualTo(String value) {
            addCriterion("is_company_inf_perfect <>", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectGreaterThan(String value) {
            addCriterion("is_company_inf_perfect >", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectGreaterThanOrEqualTo(String value) {
            addCriterion("is_company_inf_perfect >=", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectLessThan(String value) {
            addCriterion("is_company_inf_perfect <", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectLessThanOrEqualTo(String value) {
            addCriterion("is_company_inf_perfect <=", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectLike(String value) {
            addCriterion("is_company_inf_perfect like", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectNotLike(String value) {
            addCriterion("is_company_inf_perfect not like", value, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectIn(List<String> values) {
            addCriterion("is_company_inf_perfect in", values, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectNotIn(List<String> values) {
            addCriterion("is_company_inf_perfect not in", values, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectBetween(String value1, String value2) {
            addCriterion("is_company_inf_perfect between", value1, value2, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andIsCompanyInfPerfectNotBetween(String value1, String value2) {
            addCriterion("is_company_inf_perfect not between", value1, value2, "isCompanyInfPerfect");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksIsNull() {
            addCriterion("company_inf_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksIsNotNull() {
            addCriterion("company_inf_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksEqualTo(String value) {
            addCriterion("company_inf_audit_remarks =", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksNotEqualTo(String value) {
            addCriterion("company_inf_audit_remarks <>", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksGreaterThan(String value) {
            addCriterion("company_inf_audit_remarks >", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("company_inf_audit_remarks >=", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksLessThan(String value) {
            addCriterion("company_inf_audit_remarks <", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("company_inf_audit_remarks <=", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksLike(String value) {
            addCriterion("company_inf_audit_remarks like", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksNotLike(String value) {
            addCriterion("company_inf_audit_remarks not like", value, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksIn(List<String> values) {
            addCriterion("company_inf_audit_remarks in", values, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksNotIn(List<String> values) {
            addCriterion("company_inf_audit_remarks not in", values, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksBetween(String value1, String value2) {
            addCriterion("company_inf_audit_remarks between", value1, value2, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("company_inf_audit_remarks not between", value1, value2, "companyInfAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositContentIsNull() {
            addCriterion("deposit_content is null");
            return (Criteria) this;
        }

        public Criteria andDepositContentIsNotNull() {
            addCriterion("deposit_content is not null");
            return (Criteria) this;
        }

        public Criteria andDepositContentEqualTo(String value) {
            addCriterion("deposit_content =", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotEqualTo(String value) {
            addCriterion("deposit_content <>", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentGreaterThan(String value) {
            addCriterion("deposit_content >", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_content >=", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentLessThan(String value) {
            addCriterion("deposit_content <", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentLessThanOrEqualTo(String value) {
            addCriterion("deposit_content <=", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentLike(String value) {
            addCriterion("deposit_content like", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotLike(String value) {
            addCriterion("deposit_content not like", value, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentIn(List<String> values) {
            addCriterion("deposit_content in", values, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotIn(List<String> values) {
            addCriterion("deposit_content not in", values, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentBetween(String value1, String value2) {
            addCriterion("deposit_content between", value1, value2, "depositContent");
            return (Criteria) this;
        }

        public Criteria andDepositContentNotBetween(String value1, String value2) {
            addCriterion("deposit_content not between", value1, value2, "depositContent");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNull() {
            addCriterion("biz_id is null");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNotNull() {
            addCriterion("biz_id is not null");
            return (Criteria) this;
        }

        public Criteria andBizIdEqualTo(Integer value) {
            addCriterion("biz_id =", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotEqualTo(Integer value) {
            addCriterion("biz_id <>", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThan(Integer value) {
            addCriterion("biz_id >", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("biz_id >=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThan(Integer value) {
            addCriterion("biz_id <", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThanOrEqualTo(Integer value) {
            addCriterion("biz_id <=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdIn(List<Integer> values) {
            addCriterion("biz_id in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotIn(List<Integer> values) {
            addCriterion("biz_id not in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdBetween(Integer value1, Integer value2) {
            addCriterion("biz_id between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotBetween(Integer value1, Integer value2) {
            addCriterion("biz_id not between", value1, value2, "bizId");
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