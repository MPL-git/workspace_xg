package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MchtInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtInfoExample() {
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
            addCriterion("cooperate_begin_date =", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateNotEqualTo(Date value) {
            addCriterion("cooperate_begin_date <>", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateGreaterThan(Date value) {
            addCriterion("cooperate_begin_date >", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("cooperate_begin_date >=", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateLessThan(Date value) {
            addCriterion("cooperate_begin_date <", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("cooperate_begin_date <=", value, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateIn(List<Date> values) {
            addCriterion("cooperate_begin_date in", values, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateNotIn(List<Date> values) {
            addCriterion("cooperate_begin_date not in", values, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateBetween(Date value1, Date value2) {
            addCriterion("cooperate_begin_date between", value1, value2, "cooperateBeginDate");
            return (Criteria) this;
        }

        public Criteria andCooperateBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("cooperate_begin_date not between", value1, value2, "cooperateBeginDate");
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

        public Criteria andStatusDateIsNull() {
            addCriterion("status_date is null");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNotNull() {
            addCriterion("status_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDateEqualTo(Date value) {
            addCriterion("status_date =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterion("status_date <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterion("status_date >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("status_date >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterion("status_date <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("status_date <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterion("status_date in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterion("status_date not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterion("status_date between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("status_date not between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusIsNull() {
            addCriterion("total_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusIsNotNull() {
            addCriterion("total_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusEqualTo(String value) {
            addCriterion("total_audit_status =", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusNotEqualTo(String value) {
            addCriterion("total_audit_status <>", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusGreaterThan(String value) {
            addCriterion("total_audit_status >", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("total_audit_status >=", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusLessThan(String value) {
            addCriterion("total_audit_status <", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("total_audit_status <=", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusLike(String value) {
            addCriterion("total_audit_status like", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusNotLike(String value) {
            addCriterion("total_audit_status not like", value, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusIn(List<String> values) {
            addCriterion("total_audit_status in", values, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusNotIn(List<String> values) {
            addCriterion("total_audit_status not in", values, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusBetween(String value1, String value2) {
            addCriterion("total_audit_status between", value1, value2, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditStatusNotBetween(String value1, String value2) {
            addCriterion("total_audit_status not between", value1, value2, "totalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksIsNull() {
            addCriterion("total_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksIsNotNull() {
            addCriterion("total_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksEqualTo(String value) {
            addCriterion("total_audit_remarks =", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksNotEqualTo(String value) {
            addCriterion("total_audit_remarks <>", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksGreaterThan(String value) {
            addCriterion("total_audit_remarks >", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("total_audit_remarks >=", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksLessThan(String value) {
            addCriterion("total_audit_remarks <", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("total_audit_remarks <=", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksLike(String value) {
            addCriterion("total_audit_remarks like", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksNotLike(String value) {
            addCriterion("total_audit_remarks not like", value, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksIn(List<String> values) {
            addCriterion("total_audit_remarks in", values, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksNotIn(List<String> values) {
            addCriterion("total_audit_remarks not in", values, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksBetween(String value1, String value2) {
            addCriterion("total_audit_remarks between", value1, value2, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("total_audit_remarks not between", value1, value2, "totalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateIsNull() {
            addCriterion("total_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateIsNotNull() {
            addCriterion("total_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateEqualTo(Date value) {
            addCriterion("total_audit_date =", value, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateNotEqualTo(Date value) {
            addCriterion("total_audit_date <>", value, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateGreaterThan(Date value) {
            addCriterion("total_audit_date >", value, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("total_audit_date >=", value, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateLessThan(Date value) {
            addCriterion("total_audit_date <", value, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("total_audit_date <=", value, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateIn(List<Date> values) {
            addCriterion("total_audit_date in", values, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateNotIn(List<Date> values) {
            addCriterion("total_audit_date not in", values, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateBetween(Date value1, Date value2) {
            addCriterion("total_audit_date between", value1, value2, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("total_audit_date not between", value1, value2, "totalAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateIsNull() {
            addCriterion("commit_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateIsNotNull() {
            addCriterion("commit_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateEqualTo(Date value) {
            addCriterion("commit_audit_date =", value, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateNotEqualTo(Date value) {
            addCriterion("commit_audit_date <>", value, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateGreaterThan(Date value) {
            addCriterion("commit_audit_date >", value, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commit_audit_date >=", value, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateLessThan(Date value) {
            addCriterion("commit_audit_date <", value, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("commit_audit_date <=", value, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateIn(List<Date> values) {
            addCriterion("commit_audit_date in", values, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateNotIn(List<Date> values) {
            addCriterion("commit_audit_date not in", values, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateBetween(Date value1, Date value2) {
            addCriterion("commit_audit_date between", value1, value2, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommitAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("commit_audit_date not between", value1, value2, "commitAuditDate");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitIsNull() {
            addCriterion("is_total_audit_re_commit is null");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitIsNotNull() {
            addCriterion("is_total_audit_re_commit is not null");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitEqualTo(String value) {
            addCriterion("is_total_audit_re_commit =", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitNotEqualTo(String value) {
            addCriterion("is_total_audit_re_commit <>", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitGreaterThan(String value) {
            addCriterion("is_total_audit_re_commit >", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitGreaterThanOrEqualTo(String value) {
            addCriterion("is_total_audit_re_commit >=", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitLessThan(String value) {
            addCriterion("is_total_audit_re_commit <", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitLessThanOrEqualTo(String value) {
            addCriterion("is_total_audit_re_commit <=", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitLike(String value) {
            addCriterion("is_total_audit_re_commit like", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitNotLike(String value) {
            addCriterion("is_total_audit_re_commit not like", value, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitIn(List<String> values) {
            addCriterion("is_total_audit_re_commit in", values, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitNotIn(List<String> values) {
            addCriterion("is_total_audit_re_commit not in", values, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitBetween(String value1, String value2) {
            addCriterion("is_total_audit_re_commit between", value1, value2, "isTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsTotalAuditReCommitNotBetween(String value1, String value2) {
            addCriterion("is_total_audit_re_commit not between", value1, value2, "isTotalAuditReCommit");
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

        public Criteria andCorporationIdcardDateIsNull() {
            addCriterion("corporation_idcard_date is null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateIsNotNull() {
            addCriterion("corporation_idcard_date is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateEqualTo(Date value) {
            addCriterion("corporation_idcard_date =", value, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateNotEqualTo(Date value) {
            addCriterion("corporation_idcard_date <>", value, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateGreaterThan(Date value) {
            addCriterion("corporation_idcard_date >", value, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateGreaterThanOrEqualTo(Date value) {
            addCriterion("corporation_idcard_date >=", value, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateLessThan(Date value) {
            addCriterion("corporation_idcard_date <", value, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateLessThanOrEqualTo(Date value) {
            addCriterion("corporation_idcard_date <=", value, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateIn(List<Date> values) {
            addCriterion("corporation_idcard_date in", values, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateNotIn(List<Date> values) {
            addCriterion("corporation_idcard_date not in", values, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateBetween(Date value1, Date value2) {
            addCriterion("corporation_idcard_date between", value1, value2, "corporationIdcardDate");
            return (Criteria) this;
        }

        public Criteria andCorporationIdcardDateNotBetween(Date value1, Date value2) {
            addCriterion("corporation_idcard_date not between", value1, value2, "corporationIdcardDate");
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

        public Criteria andContractDepositIsNull() {
            addCriterion("contract_deposit is null");
            return (Criteria) this;
        }

        public Criteria andContractDepositIsNotNull() {
            addCriterion("contract_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andContractDepositEqualTo(BigDecimal value) {
            addCriterion("contract_deposit =", value, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositNotEqualTo(BigDecimal value) {
            addCriterion("contract_deposit <>", value, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositGreaterThan(BigDecimal value) {
            addCriterion("contract_deposit >", value, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_deposit >=", value, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositLessThan(BigDecimal value) {
            addCriterion("contract_deposit <", value, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_deposit <=", value, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositIn(List<BigDecimal> values) {
            addCriterion("contract_deposit in", values, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositNotIn(List<BigDecimal> values) {
            addCriterion("contract_deposit not in", values, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_deposit between", value1, value2, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andContractDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_deposit not between", value1, value2, "contractDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositIsNull() {
            addCriterion("brand_deposit is null");
            return (Criteria) this;
        }

        public Criteria andBrandDepositIsNotNull() {
            addCriterion("brand_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andBrandDepositEqualTo(BigDecimal value) {
            addCriterion("brand_deposit =", value, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositNotEqualTo(BigDecimal value) {
            addCriterion("brand_deposit <>", value, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositGreaterThan(BigDecimal value) {
            addCriterion("brand_deposit >", value, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brand_deposit >=", value, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositLessThan(BigDecimal value) {
            addCriterion("brand_deposit <", value, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brand_deposit <=", value, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositIn(List<BigDecimal> values) {
            addCriterion("brand_deposit in", values, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositNotIn(List<BigDecimal> values) {
            addCriterion("brand_deposit not in", values, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brand_deposit between", value1, value2, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andBrandDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brand_deposit not between", value1, value2, "brandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositIsNull() {
            addCriterion("select_contract_deposit is null");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositIsNotNull() {
            addCriterion("select_contract_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositEqualTo(String value) {
            addCriterion("select_contract_deposit =", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositNotEqualTo(String value) {
            addCriterion("select_contract_deposit <>", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositGreaterThan(String value) {
            addCriterion("select_contract_deposit >", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositGreaterThanOrEqualTo(String value) {
            addCriterion("select_contract_deposit >=", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositLessThan(String value) {
            addCriterion("select_contract_deposit <", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositLessThanOrEqualTo(String value) {
            addCriterion("select_contract_deposit <=", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositLike(String value) {
            addCriterion("select_contract_deposit like", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositNotLike(String value) {
            addCriterion("select_contract_deposit not like", value, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositIn(List<String> values) {
            addCriterion("select_contract_deposit in", values, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositNotIn(List<String> values) {
            addCriterion("select_contract_deposit not in", values, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositBetween(String value1, String value2) {
            addCriterion("select_contract_deposit between", value1, value2, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectContractDepositNotBetween(String value1, String value2) {
            addCriterion("select_contract_deposit not between", value1, value2, "selectContractDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositIsNull() {
            addCriterion("select_brand_deposit is null");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositIsNotNull() {
            addCriterion("select_brand_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositEqualTo(String value) {
            addCriterion("select_brand_deposit =", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositNotEqualTo(String value) {
            addCriterion("select_brand_deposit <>", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositGreaterThan(String value) {
            addCriterion("select_brand_deposit >", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositGreaterThanOrEqualTo(String value) {
            addCriterion("select_brand_deposit >=", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositLessThan(String value) {
            addCriterion("select_brand_deposit <", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositLessThanOrEqualTo(String value) {
            addCriterion("select_brand_deposit <=", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositLike(String value) {
            addCriterion("select_brand_deposit like", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositNotLike(String value) {
            addCriterion("select_brand_deposit not like", value, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositIn(List<String> values) {
            addCriterion("select_brand_deposit in", values, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositNotIn(List<String> values) {
            addCriterion("select_brand_deposit not in", values, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositBetween(String value1, String value2) {
            addCriterion("select_brand_deposit between", value1, value2, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andSelectBrandDepositNotBetween(String value1, String value2) {
            addCriterion("select_brand_deposit not between", value1, value2, "selectBrandDeposit");
            return (Criteria) this;
        }

        public Criteria andDepositTypeIsNull() {
            addCriterion("deposit_type is null");
            return (Criteria) this;
        }

        public Criteria andDepositTypeIsNotNull() {
            addCriterion("deposit_type is not null");
            return (Criteria) this;
        }

        public Criteria andDepositTypeEqualTo(String value) {
            addCriterion("deposit_type =", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotEqualTo(String value) {
            addCriterion("deposit_type <>", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeGreaterThan(String value) {
            addCriterion("deposit_type >", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_type >=", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeLessThan(String value) {
            addCriterion("deposit_type <", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeLessThanOrEqualTo(String value) {
            addCriterion("deposit_type <=", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeLike(String value) {
            addCriterion("deposit_type like", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotLike(String value) {
            addCriterion("deposit_type not like", value, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeIn(List<String> values) {
            addCriterion("deposit_type in", values, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotIn(List<String> values) {
            addCriterion("deposit_type not in", values, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeBetween(String value1, String value2) {
            addCriterion("deposit_type between", value1, value2, "depositType");
            return (Criteria) this;
        }

        public Criteria andDepositTypeNotBetween(String value1, String value2) {
            addCriterion("deposit_type not between", value1, value2, "depositType");
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

        public Criteria andCustomServiceTypeIsNull() {
            addCriterion("custom_service_type is null");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeIsNotNull() {
            addCriterion("custom_service_type is not null");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeEqualTo(String value) {
            addCriterion("custom_service_type =", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeNotEqualTo(String value) {
            addCriterion("custom_service_type <>", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeGreaterThan(String value) {
            addCriterion("custom_service_type >", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("custom_service_type >=", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeLessThan(String value) {
            addCriterion("custom_service_type <", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeLessThanOrEqualTo(String value) {
            addCriterion("custom_service_type <=", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeLike(String value) {
            addCriterion("custom_service_type like", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeNotLike(String value) {
            addCriterion("custom_service_type not like", value, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeIn(List<String> values) {
            addCriterion("custom_service_type in", values, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeNotIn(List<String> values) {
            addCriterion("custom_service_type not in", values, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeBetween(String value1, String value2) {
            addCriterion("custom_service_type between", value1, value2, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andCustomServiceTypeNotBetween(String value1, String value2) {
            addCriterion("custom_service_type not between", value1, value2, "customServiceType");
            return (Criteria) this;
        }

        public Criteria andSobotIdIsNull() {
            addCriterion("sobot_id is null");
            return (Criteria) this;
        }

        public Criteria andSobotIdIsNotNull() {
            addCriterion("sobot_id is not null");
            return (Criteria) this;
        }

        public Criteria andSobotIdEqualTo(Integer value) {
            addCriterion("sobot_id =", value, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdNotEqualTo(Integer value) {
            addCriterion("sobot_id <>", value, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdGreaterThan(Integer value) {
            addCriterion("sobot_id >", value, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sobot_id >=", value, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdLessThan(Integer value) {
            addCriterion("sobot_id <", value, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdLessThanOrEqualTo(Integer value) {
            addCriterion("sobot_id <=", value, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdIn(List<Integer> values) {
            addCriterion("sobot_id in", values, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdNotIn(List<Integer> values) {
            addCriterion("sobot_id not in", values, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdBetween(Integer value1, Integer value2) {
            addCriterion("sobot_id between", value1, value2, "sobotId");
            return (Criteria) this;
        }

        public Criteria andSobotIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sobot_id not between", value1, value2, "sobotId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdIsNull() {
            addCriterion("xiaoneng_id is null");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdIsNotNull() {
            addCriterion("xiaoneng_id is not null");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdEqualTo(Integer value) {
            addCriterion("xiaoneng_id =", value, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdNotEqualTo(Integer value) {
            addCriterion("xiaoneng_id <>", value, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdGreaterThan(Integer value) {
            addCriterion("xiaoneng_id >", value, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("xiaoneng_id >=", value, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdLessThan(Integer value) {
            addCriterion("xiaoneng_id <", value, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdLessThanOrEqualTo(Integer value) {
            addCriterion("xiaoneng_id <=", value, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdIn(List<Integer> values) {
            addCriterion("xiaoneng_id in", values, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdNotIn(List<Integer> values) {
            addCriterion("xiaoneng_id not in", values, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdBetween(Integer value1, Integer value2) {
            addCriterion("xiaoneng_id between", value1, value2, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andXiaonengIdNotBetween(Integer value1, Integer value2) {
            addCriterion("xiaoneng_id not between", value1, value2, "xiaonengId");
            return (Criteria) this;
        }

        public Criteria andInBlacklistIsNull() {
            addCriterion("in_blacklist is null");
            return (Criteria) this;
        }

        public Criteria andInBlacklistIsNotNull() {
            addCriterion("in_blacklist is not null");
            return (Criteria) this;
        }

        public Criteria andInBlacklistEqualTo(String value) {
            addCriterion("in_blacklist =", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistNotEqualTo(String value) {
            addCriterion("in_blacklist <>", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistGreaterThan(String value) {
            addCriterion("in_blacklist >", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistGreaterThanOrEqualTo(String value) {
            addCriterion("in_blacklist >=", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistLessThan(String value) {
            addCriterion("in_blacklist <", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistLessThanOrEqualTo(String value) {
            addCriterion("in_blacklist <=", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistLike(String value) {
            addCriterion("in_blacklist like", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistNotLike(String value) {
            addCriterion("in_blacklist not like", value, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistIn(List<String> values) {
            addCriterion("in_blacklist in", values, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistNotIn(List<String> values) {
            addCriterion("in_blacklist not in", values, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistBetween(String value1, String value2) {
            addCriterion("in_blacklist between", value1, value2, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andInBlacklistNotBetween(String value1, String value2) {
            addCriterion("in_blacklist not between", value1, value2, "inBlacklist");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusIsNull() {
            addCriterion("zs_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusIsNotNull() {
            addCriterion("zs_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusEqualTo(String value) {
            addCriterion("zs_audit_status =", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotEqualTo(String value) {
            addCriterion("zs_audit_status <>", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusGreaterThan(String value) {
            addCriterion("zs_audit_status >", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("zs_audit_status >=", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusLessThan(String value) {
            addCriterion("zs_audit_status <", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("zs_audit_status <=", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusLike(String value) {
            addCriterion("zs_audit_status like", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotLike(String value) {
            addCriterion("zs_audit_status not like", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusIn(List<String> values) {
            addCriterion("zs_audit_status in", values, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotIn(List<String> values) {
            addCriterion("zs_audit_status not in", values, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusBetween(String value1, String value2) {
            addCriterion("zs_audit_status between", value1, value2, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotBetween(String value1, String value2) {
            addCriterion("zs_audit_status not between", value1, value2, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditByIsNull() {
            addCriterion("zs_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditByIsNotNull() {
            addCriterion("zs_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditByEqualTo(Integer value) {
            addCriterion("zs_audit_by =", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByNotEqualTo(Integer value) {
            addCriterion("zs_audit_by <>", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByGreaterThan(Integer value) {
            addCriterion("zs_audit_by >", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("zs_audit_by >=", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByLessThan(Integer value) {
            addCriterion("zs_audit_by <", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("zs_audit_by <=", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByIn(List<Integer> values) {
            addCriterion("zs_audit_by in", values, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByNotIn(List<Integer> values) {
            addCriterion("zs_audit_by not in", values, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByBetween(Integer value1, Integer value2) {
            addCriterion("zs_audit_by between", value1, value2, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("zs_audit_by not between", value1, value2, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusIsNull() {
            addCriterion("finance_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusIsNotNull() {
            addCriterion("finance_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusEqualTo(String value) {
            addCriterion("finance_confirm_status =", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusNotEqualTo(String value) {
            addCriterion("finance_confirm_status <>", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusGreaterThan(String value) {
            addCriterion("finance_confirm_status >", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("finance_confirm_status >=", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusLessThan(String value) {
            addCriterion("finance_confirm_status <", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("finance_confirm_status <=", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusLike(String value) {
            addCriterion("finance_confirm_status like", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusNotLike(String value) {
            addCriterion("finance_confirm_status not like", value, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusIn(List<String> values) {
            addCriterion("finance_confirm_status in", values, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusNotIn(List<String> values) {
            addCriterion("finance_confirm_status not in", values, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusBetween(String value1, String value2) {
            addCriterion("finance_confirm_status between", value1, value2, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("finance_confirm_status not between", value1, value2, "financeConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByIsNull() {
            addCriterion("finance_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByIsNotNull() {
            addCriterion("finance_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByEqualTo(Integer value) {
            addCriterion("finance_confirm_by =", value, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByNotEqualTo(Integer value) {
            addCriterion("finance_confirm_by <>", value, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByGreaterThan(Integer value) {
            addCriterion("finance_confirm_by >", value, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("finance_confirm_by >=", value, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByLessThan(Integer value) {
            addCriterion("finance_confirm_by <", value, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("finance_confirm_by <=", value, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByIn(List<Integer> values) {
            addCriterion("finance_confirm_by in", values, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByNotIn(List<Integer> values) {
            addCriterion("finance_confirm_by not in", values, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("finance_confirm_by between", value1, value2, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andFinanceConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("finance_confirm_by not between", value1, value2, "financeConfirmBy");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNull() {
            addCriterion("shop_status is null");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNotNull() {
            addCriterion("shop_status is not null");
            return (Criteria) this;
        }

        public Criteria andShopStatusEqualTo(String value) {
            addCriterion("shop_status =", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotEqualTo(String value) {
            addCriterion("shop_status <>", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThan(String value) {
            addCriterion("shop_status >", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThanOrEqualTo(String value) {
            addCriterion("shop_status >=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThan(String value) {
            addCriterion("shop_status <", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThanOrEqualTo(String value) {
            addCriterion("shop_status <=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLike(String value) {
            addCriterion("shop_status like", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotLike(String value) {
            addCriterion("shop_status not like", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusIn(List<String> values) {
            addCriterion("shop_status in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotIn(List<String> values) {
            addCriterion("shop_status not in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusBetween(String value1, String value2) {
            addCriterion("shop_status between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotBetween(String value1, String value2) {
            addCriterion("shop_status not between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateIsNull() {
            addCriterion("shop_status_date is null");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateIsNotNull() {
            addCriterion("shop_status_date is not null");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateEqualTo(Date value) {
            addCriterion("shop_status_date =", value, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateNotEqualTo(Date value) {
            addCriterion("shop_status_date <>", value, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateGreaterThan(Date value) {
            addCriterion("shop_status_date >", value, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("shop_status_date >=", value, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateLessThan(Date value) {
            addCriterion("shop_status_date <", value, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("shop_status_date <=", value, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateIn(List<Date> values) {
            addCriterion("shop_status_date in", values, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateNotIn(List<Date> values) {
            addCriterion("shop_status_date not in", values, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateBetween(Date value1, Date value2) {
            addCriterion("shop_status_date between", value1, value2, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("shop_status_date not between", value1, value2, "shopStatusDate");
            return (Criteria) this;
        }

        public Criteria andShopLogoIsNull() {
            addCriterion("shop_logo is null");
            return (Criteria) this;
        }

        public Criteria andShopLogoIsNotNull() {
            addCriterion("shop_logo is not null");
            return (Criteria) this;
        }

        public Criteria andShopLogoEqualTo(String value) {
            addCriterion("shop_logo =", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoNotEqualTo(String value) {
            addCriterion("shop_logo <>", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoGreaterThan(String value) {
            addCriterion("shop_logo >", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoGreaterThanOrEqualTo(String value) {
            addCriterion("shop_logo >=", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoLessThan(String value) {
            addCriterion("shop_logo <", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoLessThanOrEqualTo(String value) {
            addCriterion("shop_logo <=", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoLike(String value) {
            addCriterion("shop_logo like", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoNotLike(String value) {
            addCriterion("shop_logo not like", value, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoIn(List<String> values) {
            addCriterion("shop_logo in", values, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoNotIn(List<String> values) {
            addCriterion("shop_logo not in", values, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoBetween(String value1, String value2) {
            addCriterion("shop_logo between", value1, value2, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andShopLogoNotBetween(String value1, String value2) {
            addCriterion("shop_logo not between", value1, value2, "shopLogo");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusIsNull() {
            addCriterion("activity_auth_status is null");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusIsNotNull() {
            addCriterion("activity_auth_status is not null");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusEqualTo(String value) {
            addCriterion("activity_auth_status =", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusNotEqualTo(String value) {
            addCriterion("activity_auth_status <>", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusGreaterThan(String value) {
            addCriterion("activity_auth_status >", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusGreaterThanOrEqualTo(String value) {
            addCriterion("activity_auth_status >=", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusLessThan(String value) {
            addCriterion("activity_auth_status <", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusLessThanOrEqualTo(String value) {
            addCriterion("activity_auth_status <=", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusLike(String value) {
            addCriterion("activity_auth_status like", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusNotLike(String value) {
            addCriterion("activity_auth_status not like", value, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusIn(List<String> values) {
            addCriterion("activity_auth_status in", values, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusNotIn(List<String> values) {
            addCriterion("activity_auth_status not in", values, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusBetween(String value1, String value2) {
            addCriterion("activity_auth_status between", value1, value2, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusNotBetween(String value1, String value2) {
            addCriterion("activity_auth_status not between", value1, value2, "activityAuthStatus");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksIsNull() {
            addCriterion("shop_name_audit_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksIsNotNull() {
            addCriterion("shop_name_audit_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksEqualTo(String value) {
            addCriterion("shop_name_audit_inner_remarks =", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksNotEqualTo(String value) {
            addCriterion("shop_name_audit_inner_remarks <>", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksGreaterThan(String value) {
            addCriterion("shop_name_audit_inner_remarks >", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name_audit_inner_remarks >=", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksLessThan(String value) {
            addCriterion("shop_name_audit_inner_remarks <", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("shop_name_audit_inner_remarks <=", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksLike(String value) {
            addCriterion("shop_name_audit_inner_remarks like", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksNotLike(String value) {
            addCriterion("shop_name_audit_inner_remarks not like", value, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksIn(List<String> values) {
            addCriterion("shop_name_audit_inner_remarks in", values, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksNotIn(List<String> values) {
            addCriterion("shop_name_audit_inner_remarks not in", values, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksBetween(String value1, String value2) {
            addCriterion("shop_name_audit_inner_remarks between", value1, value2, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopNameAuditInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("shop_name_audit_inner_remarks not between", value1, value2, "shopNameAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusIsNull() {
            addCriterion("zs_total_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusIsNotNull() {
            addCriterion("zs_total_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusEqualTo(String value) {
            addCriterion("zs_total_audit_status =", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusNotEqualTo(String value) {
            addCriterion("zs_total_audit_status <>", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusGreaterThan(String value) {
            addCriterion("zs_total_audit_status >", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("zs_total_audit_status >=", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusLessThan(String value) {
            addCriterion("zs_total_audit_status <", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("zs_total_audit_status <=", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusLike(String value) {
            addCriterion("zs_total_audit_status like", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusNotLike(String value) {
            addCriterion("zs_total_audit_status not like", value, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusIn(List<String> values) {
            addCriterion("zs_total_audit_status in", values, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusNotIn(List<String> values) {
            addCriterion("zs_total_audit_status not in", values, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusBetween(String value1, String value2) {
            addCriterion("zs_total_audit_status between", value1, value2, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditStatusNotBetween(String value1, String value2) {
            addCriterion("zs_total_audit_status not between", value1, value2, "zsTotalAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByIsNull() {
            addCriterion("zs_total_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByIsNotNull() {
            addCriterion("zs_total_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByEqualTo(Integer value) {
            addCriterion("zs_total_audit_by =", value, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByNotEqualTo(Integer value) {
            addCriterion("zs_total_audit_by <>", value, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByGreaterThan(Integer value) {
            addCriterion("zs_total_audit_by >", value, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("zs_total_audit_by >=", value, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByLessThan(Integer value) {
            addCriterion("zs_total_audit_by <", value, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("zs_total_audit_by <=", value, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByIn(List<Integer> values) {
            addCriterion("zs_total_audit_by in", values, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByNotIn(List<Integer> values) {
            addCriterion("zs_total_audit_by not in", values, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByBetween(Integer value1, Integer value2) {
            addCriterion("zs_total_audit_by between", value1, value2, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("zs_total_audit_by not between", value1, value2, "zsTotalAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksIsNull() {
            addCriterion("zs_total_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksIsNotNull() {
            addCriterion("zs_total_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksEqualTo(String value) {
            addCriterion("zs_total_audit_remarks =", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksNotEqualTo(String value) {
            addCriterion("zs_total_audit_remarks <>", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksGreaterThan(String value) {
            addCriterion("zs_total_audit_remarks >", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("zs_total_audit_remarks >=", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksLessThan(String value) {
            addCriterion("zs_total_audit_remarks <", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("zs_total_audit_remarks <=", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksLike(String value) {
            addCriterion("zs_total_audit_remarks like", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksNotLike(String value) {
            addCriterion("zs_total_audit_remarks not like", value, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksIn(List<String> values) {
            addCriterion("zs_total_audit_remarks in", values, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksNotIn(List<String> values) {
            addCriterion("zs_total_audit_remarks not in", values, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksBetween(String value1, String value2) {
            addCriterion("zs_total_audit_remarks between", value1, value2, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("zs_total_audit_remarks not between", value1, value2, "zsTotalAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateIsNull() {
            addCriterion("zs_total_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateIsNotNull() {
            addCriterion("zs_total_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateEqualTo(Date value) {
            addCriterion("zs_total_audit_date =", value, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateNotEqualTo(Date value) {
            addCriterion("zs_total_audit_date <>", value, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateGreaterThan(Date value) {
            addCriterion("zs_total_audit_date >", value, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("zs_total_audit_date >=", value, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateLessThan(Date value) {
            addCriterion("zs_total_audit_date <", value, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("zs_total_audit_date <=", value, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateIn(List<Date> values) {
            addCriterion("zs_total_audit_date in", values, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateNotIn(List<Date> values) {
            addCriterion("zs_total_audit_date not in", values, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateBetween(Date value1, Date value2) {
            addCriterion("zs_total_audit_date between", value1, value2, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsTotalAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("zs_total_audit_date not between", value1, value2, "zsTotalAuditDate");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitIsNull() {
            addCriterion("is_zs_total_audit_re_commit is null");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitIsNotNull() {
            addCriterion("is_zs_total_audit_re_commit is not null");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitEqualTo(String value) {
            addCriterion("is_zs_total_audit_re_commit =", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitNotEqualTo(String value) {
            addCriterion("is_zs_total_audit_re_commit <>", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitGreaterThan(String value) {
            addCriterion("is_zs_total_audit_re_commit >", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitGreaterThanOrEqualTo(String value) {
            addCriterion("is_zs_total_audit_re_commit >=", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitLessThan(String value) {
            addCriterion("is_zs_total_audit_re_commit <", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitLessThanOrEqualTo(String value) {
            addCriterion("is_zs_total_audit_re_commit <=", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitLike(String value) {
            addCriterion("is_zs_total_audit_re_commit like", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitNotLike(String value) {
            addCriterion("is_zs_total_audit_re_commit not like", value, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitIn(List<String> values) {
            addCriterion("is_zs_total_audit_re_commit in", values, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitNotIn(List<String> values) {
            addCriterion("is_zs_total_audit_re_commit not in", values, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitBetween(String value1, String value2) {
            addCriterion("is_zs_total_audit_re_commit between", value1, value2, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andIsZsTotalAuditReCommitNotBetween(String value1, String value2) {
            addCriterion("is_zs_total_audit_re_commit not between", value1, value2, "isZsTotalAuditReCommit");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateIsNull() {
            addCriterion("zs_commit_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateIsNotNull() {
            addCriterion("zs_commit_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateEqualTo(Date value) {
            addCriterion("zs_commit_audit_date =", value, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateNotEqualTo(Date value) {
            addCriterion("zs_commit_audit_date <>", value, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateGreaterThan(Date value) {
            addCriterion("zs_commit_audit_date >", value, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("zs_commit_audit_date >=", value, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateLessThan(Date value) {
            addCriterion("zs_commit_audit_date <", value, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("zs_commit_audit_date <=", value, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateIn(List<Date> values) {
            addCriterion("zs_commit_audit_date in", values, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateNotIn(List<Date> values) {
            addCriterion("zs_commit_audit_date not in", values, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateBetween(Date value1, Date value2) {
            addCriterion("zs_commit_audit_date between", value1, value2, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsCommitAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("zs_commit_audit_date not between", value1, value2, "zsCommitAuditDate");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByIsNull() {
            addCriterion("total_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByIsNotNull() {
            addCriterion("total_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByEqualTo(Integer value) {
            addCriterion("total_audit_by =", value, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByNotEqualTo(Integer value) {
            addCriterion("total_audit_by <>", value, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByGreaterThan(Integer value) {
            addCriterion("total_audit_by >", value, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_audit_by >=", value, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByLessThan(Integer value) {
            addCriterion("total_audit_by <", value, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("total_audit_by <=", value, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByIn(List<Integer> values) {
            addCriterion("total_audit_by in", values, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByNotIn(List<Integer> values) {
            addCriterion("total_audit_by not in", values, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByBetween(Integer value1, Integer value2) {
            addCriterion("total_audit_by between", value1, value2, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andTotalAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("total_audit_by not between", value1, value2, "totalAuditBy");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusIsNull() {
            addCriterion("idcard_img_1_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusIsNotNull() {
            addCriterion("idcard_img_1_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusEqualTo(String value) {
            addCriterion("idcard_img_1_archive_status =", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusNotEqualTo(String value) {
            addCriterion("idcard_img_1_archive_status <>", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusGreaterThan(String value) {
            addCriterion("idcard_img_1_archive_status >", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_img_1_archive_status >=", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusLessThan(String value) {
            addCriterion("idcard_img_1_archive_status <", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("idcard_img_1_archive_status <=", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusLike(String value) {
            addCriterion("idcard_img_1_archive_status like", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusNotLike(String value) {
            addCriterion("idcard_img_1_archive_status not like", value, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusIn(List<String> values) {
            addCriterion("idcard_img_1_archive_status in", values, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusNotIn(List<String> values) {
            addCriterion("idcard_img_1_archive_status not in", values, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusBetween(String value1, String value2) {
            addCriterion("idcard_img_1_archive_status between", value1, value2, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg1ArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("idcard_img_1_archive_status not between", value1, value2, "idcardImg1ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusIsNull() {
            addCriterion("idcard_img_2_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusIsNotNull() {
            addCriterion("idcard_img_2_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusEqualTo(String value) {
            addCriterion("idcard_img_2_archive_status =", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusNotEqualTo(String value) {
            addCriterion("idcard_img_2_archive_status <>", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusGreaterThan(String value) {
            addCriterion("idcard_img_2_archive_status >", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_img_2_archive_status >=", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusLessThan(String value) {
            addCriterion("idcard_img_2_archive_status <", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("idcard_img_2_archive_status <=", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusLike(String value) {
            addCriterion("idcard_img_2_archive_status like", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusNotLike(String value) {
            addCriterion("idcard_img_2_archive_status not like", value, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusIn(List<String> values) {
            addCriterion("idcard_img_2_archive_status in", values, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusNotIn(List<String> values) {
            addCriterion("idcard_img_2_archive_status not in", values, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusBetween(String value1, String value2) {
            addCriterion("idcard_img_2_archive_status between", value1, value2, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardImg2ArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("idcard_img_2_archive_status not between", value1, value2, "idcardImg2ArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusIsNull() {
            addCriterion("occ_pic_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusIsNotNull() {
            addCriterion("occ_pic_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusEqualTo(String value) {
            addCriterion("occ_pic_archive_status =", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusNotEqualTo(String value) {
            addCriterion("occ_pic_archive_status <>", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusGreaterThan(String value) {
            addCriterion("occ_pic_archive_status >", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("occ_pic_archive_status >=", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusLessThan(String value) {
            addCriterion("occ_pic_archive_status <", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("occ_pic_archive_status <=", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusLike(String value) {
            addCriterion("occ_pic_archive_status like", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusNotLike(String value) {
            addCriterion("occ_pic_archive_status not like", value, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusIn(List<String> values) {
            addCriterion("occ_pic_archive_status in", values, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusNotIn(List<String> values) {
            addCriterion("occ_pic_archive_status not in", values, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusBetween(String value1, String value2) {
            addCriterion("occ_pic_archive_status between", value1, value2, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOccPicArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("occ_pic_archive_status not between", value1, value2, "occPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusIsNull() {
            addCriterion("trc_pic_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusIsNotNull() {
            addCriterion("trc_pic_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusEqualTo(String value) {
            addCriterion("trc_pic_archive_status =", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusNotEqualTo(String value) {
            addCriterion("trc_pic_archive_status <>", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusGreaterThan(String value) {
            addCriterion("trc_pic_archive_status >", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("trc_pic_archive_status >=", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusLessThan(String value) {
            addCriterion("trc_pic_archive_status <", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("trc_pic_archive_status <=", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusLike(String value) {
            addCriterion("trc_pic_archive_status like", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusNotLike(String value) {
            addCriterion("trc_pic_archive_status not like", value, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusIn(List<String> values) {
            addCriterion("trc_pic_archive_status in", values, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusNotIn(List<String> values) {
            addCriterion("trc_pic_archive_status not in", values, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusBetween(String value1, String value2) {
            addCriterion("trc_pic_archive_status between", value1, value2, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andTrcPicArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("trc_pic_archive_status not between", value1, value2, "trcPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusIsNull() {
            addCriterion("atq_pic_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusIsNotNull() {
            addCriterion("atq_pic_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusEqualTo(String value) {
            addCriterion("atq_pic_archive_status =", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusNotEqualTo(String value) {
            addCriterion("atq_pic_archive_status <>", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusGreaterThan(String value) {
            addCriterion("atq_pic_archive_status >", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("atq_pic_archive_status >=", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusLessThan(String value) {
            addCriterion("atq_pic_archive_status <", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("atq_pic_archive_status <=", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusLike(String value) {
            addCriterion("atq_pic_archive_status like", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusNotLike(String value) {
            addCriterion("atq_pic_archive_status not like", value, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusIn(List<String> values) {
            addCriterion("atq_pic_archive_status in", values, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusNotIn(List<String> values) {
            addCriterion("atq_pic_archive_status not in", values, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusBetween(String value1, String value2) {
            addCriterion("atq_pic_archive_status between", value1, value2, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andAtqPicArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("atq_pic_archive_status not between", value1, value2, "atqPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusIsNull() {
            addCriterion("boaal_pic_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusIsNotNull() {
            addCriterion("boaal_pic_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusEqualTo(String value) {
            addCriterion("boaal_pic_archive_status =", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusNotEqualTo(String value) {
            addCriterion("boaal_pic_archive_status <>", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusGreaterThan(String value) {
            addCriterion("boaal_pic_archive_status >", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("boaal_pic_archive_status >=", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusLessThan(String value) {
            addCriterion("boaal_pic_archive_status <", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("boaal_pic_archive_status <=", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusLike(String value) {
            addCriterion("boaal_pic_archive_status like", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusNotLike(String value) {
            addCriterion("boaal_pic_archive_status not like", value, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusIn(List<String> values) {
            addCriterion("boaal_pic_archive_status in", values, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusNotIn(List<String> values) {
            addCriterion("boaal_pic_archive_status not in", values, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusBetween(String value1, String value2) {
            addCriterion("boaal_pic_archive_status between", value1, value2, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andBoaalPicArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("boaal_pic_archive_status not between", value1, value2, "boaalPicArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksIsNull() {
            addCriterion("company_inf_audit_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksIsNotNull() {
            addCriterion("company_inf_audit_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksEqualTo(String value) {
            addCriterion("company_inf_audit_inner_remarks =", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksNotEqualTo(String value) {
            addCriterion("company_inf_audit_inner_remarks <>", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksGreaterThan(String value) {
            addCriterion("company_inf_audit_inner_remarks >", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("company_inf_audit_inner_remarks >=", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksLessThan(String value) {
            addCriterion("company_inf_audit_inner_remarks <", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("company_inf_audit_inner_remarks <=", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksLike(String value) {
            addCriterion("company_inf_audit_inner_remarks like", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksNotLike(String value) {
            addCriterion("company_inf_audit_inner_remarks not like", value, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksIn(List<String> values) {
            addCriterion("company_inf_audit_inner_remarks in", values, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksNotIn(List<String> values) {
            addCriterion("company_inf_audit_inner_remarks not in", values, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksBetween(String value1, String value2) {
            addCriterion("company_inf_audit_inner_remarks between", value1, value2, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCompanyInfAuditInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("company_inf_audit_inner_remarks not between", value1, value2, "companyInfAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateIsNull() {
            addCriterion("activity_auth_status_date is null");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateIsNotNull() {
            addCriterion("activity_auth_status_date is not null");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateEqualTo(Date value) {
            addCriterion("activity_auth_status_date =", value, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateNotEqualTo(Date value) {
            addCriterion("activity_auth_status_date <>", value, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateGreaterThan(Date value) {
            addCriterion("activity_auth_status_date >", value, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_auth_status_date >=", value, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateLessThan(Date value) {
            addCriterion("activity_auth_status_date <", value, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("activity_auth_status_date <=", value, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateIn(List<Date> values) {
            addCriterion("activity_auth_status_date in", values, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateNotIn(List<Date> values) {
            addCriterion("activity_auth_status_date not in", values, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateBetween(Date value1, Date value2) {
            addCriterion("activity_auth_status_date between", value1, value2, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andActivityAuthStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("activity_auth_status_date not between", value1, value2, "activityAuthStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIsNull() {
            addCriterion("operate_status is null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIsNotNull() {
            addCriterion("operate_status is not null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusEqualTo(String value) {
            addCriterion("operate_status =", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotEqualTo(String value) {
            addCriterion("operate_status <>", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusGreaterThan(String value) {
            addCriterion("operate_status >", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusGreaterThanOrEqualTo(String value) {
            addCriterion("operate_status >=", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusLessThan(String value) {
            addCriterion("operate_status <", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusLessThanOrEqualTo(String value) {
            addCriterion("operate_status <=", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusLike(String value) {
            addCriterion("operate_status like", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotLike(String value) {
            addCriterion("operate_status not like", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIn(List<String> values) {
            addCriterion("operate_status in", values, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotIn(List<String> values) {
            addCriterion("operate_status not in", values, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusBetween(String value1, String value2) {
            addCriterion("operate_status between", value1, value2, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotBetween(String value1, String value2) {
            addCriterion("operate_status not between", value1, value2, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateIsNull() {
            addCriterion("operate_status_date is null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateIsNotNull() {
            addCriterion("operate_status_date is not null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateEqualTo(Date value) {
            addCriterion("operate_status_date =", value, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateNotEqualTo(Date value) {
            addCriterion("operate_status_date <>", value, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateGreaterThan(Date value) {
            addCriterion("operate_status_date >", value, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("operate_status_date >=", value, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateLessThan(Date value) {
            addCriterion("operate_status_date <", value, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("operate_status_date <=", value, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateIn(List<Date> values) {
            addCriterion("operate_status_date in", values, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateNotIn(List<Date> values) {
            addCriterion("operate_status_date not in", values, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateBetween(Date value1, Date value2) {
            addCriterion("operate_status_date between", value1, value2, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("operate_status_date not between", value1, value2, "operateStatusDate");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksIsNull() {
            addCriterion("operate_remarks is null");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksIsNotNull() {
            addCriterion("operate_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksEqualTo(String value) {
            addCriterion("operate_remarks =", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotEqualTo(String value) {
            addCriterion("operate_remarks <>", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksGreaterThan(String value) {
            addCriterion("operate_remarks >", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("operate_remarks >=", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksLessThan(String value) {
            addCriterion("operate_remarks <", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksLessThanOrEqualTo(String value) {
            addCriterion("operate_remarks <=", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksLike(String value) {
            addCriterion("operate_remarks like", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotLike(String value) {
            addCriterion("operate_remarks not like", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksIn(List<String> values) {
            addCriterion("operate_remarks in", values, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotIn(List<String> values) {
            addCriterion("operate_remarks not in", values, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksBetween(String value1, String value2) {
            addCriterion("operate_remarks between", value1, value2, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotBetween(String value1, String value2) {
            addCriterion("operate_remarks not between", value1, value2, "operateRemarks");
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

        public Criteria andArchiveStatusIsNull() {
            addCriterion("archive_status is null");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusIsNotNull() {
            addCriterion("archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusEqualTo(String value) {
            addCriterion("archive_status =", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotEqualTo(String value) {
            addCriterion("archive_status <>", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusGreaterThan(String value) {
            addCriterion("archive_status >", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("archive_status >=", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusLessThan(String value) {
            addCriterion("archive_status <", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("archive_status <=", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusLike(String value) {
            addCriterion("archive_status like", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotLike(String value) {
            addCriterion("archive_status not like", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusIn(List<String> values) {
            addCriterion("archive_status in", values, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotIn(List<String> values) {
            addCriterion("archive_status not in", values, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusBetween(String value1, String value2) {
            addCriterion("archive_status between", value1, value2, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("archive_status not between", value1, value2, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseIsNull() {
            addCriterion("allow_mcht_apply_close is null");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseIsNotNull() {
            addCriterion("allow_mcht_apply_close is not null");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseEqualTo(String value) {
            addCriterion("allow_mcht_apply_close =", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseNotEqualTo(String value) {
            addCriterion("allow_mcht_apply_close <>", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseGreaterThan(String value) {
            addCriterion("allow_mcht_apply_close >", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseGreaterThanOrEqualTo(String value) {
            addCriterion("allow_mcht_apply_close >=", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseLessThan(String value) {
            addCriterion("allow_mcht_apply_close <", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseLessThanOrEqualTo(String value) {
            addCriterion("allow_mcht_apply_close <=", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseLike(String value) {
            addCriterion("allow_mcht_apply_close like", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseNotLike(String value) {
            addCriterion("allow_mcht_apply_close not like", value, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseIn(List<String> values) {
            addCriterion("allow_mcht_apply_close in", values, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseNotIn(List<String> values) {
            addCriterion("allow_mcht_apply_close not in", values, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseBetween(String value1, String value2) {
            addCriterion("allow_mcht_apply_close between", value1, value2, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andAllowMchtApplyCloseNotBetween(String value1, String value2) {
            addCriterion("allow_mcht_apply_close not between", value1, value2, "allowMchtApplyClose");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusIsNull() {
            addCriterion("company_info_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusIsNotNull() {
            addCriterion("company_info_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusEqualTo(String value) {
            addCriterion("company_info_archive_status =", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusNotEqualTo(String value) {
            addCriterion("company_info_archive_status <>", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusGreaterThan(String value) {
            addCriterion("company_info_archive_status >", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("company_info_archive_status >=", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusLessThan(String value) {
            addCriterion("company_info_archive_status <", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("company_info_archive_status <=", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusLike(String value) {
            addCriterion("company_info_archive_status like", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusNotLike(String value) {
            addCriterion("company_info_archive_status not like", value, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusIn(List<String> values) {
            addCriterion("company_info_archive_status in", values, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusNotIn(List<String> values) {
            addCriterion("company_info_archive_status not in", values, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusBetween(String value1, String value2) {
            addCriterion("company_info_archive_status between", value1, value2, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andCompanyInfoArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("company_info_archive_status not between", value1, value2, "companyInfoArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusIsNull() {
            addCriterion("mcht_brand_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusIsNotNull() {
            addCriterion("mcht_brand_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusEqualTo(String value) {
            addCriterion("mcht_brand_archive_status =", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusNotEqualTo(String value) {
            addCriterion("mcht_brand_archive_status <>", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusGreaterThan(String value) {
            addCriterion("mcht_brand_archive_status >", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_brand_archive_status >=", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusLessThan(String value) {
            addCriterion("mcht_brand_archive_status <", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("mcht_brand_archive_status <=", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusLike(String value) {
            addCriterion("mcht_brand_archive_status like", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusNotLike(String value) {
            addCriterion("mcht_brand_archive_status not like", value, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusIn(List<String> values) {
            addCriterion("mcht_brand_archive_status in", values, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusNotIn(List<String> values) {
            addCriterion("mcht_brand_archive_status not in", values, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusBetween(String value1, String value2) {
            addCriterion("mcht_brand_archive_status between", value1, value2, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andMchtBrandArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("mcht_brand_archive_status not between", value1, value2, "mchtBrandArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessIsNull() {
            addCriterion("scope_of_business is null");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessIsNotNull() {
            addCriterion("scope_of_business is not null");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessEqualTo(String value) {
            addCriterion("scope_of_business =", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessNotEqualTo(String value) {
            addCriterion("scope_of_business <>", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessGreaterThan(String value) {
            addCriterion("scope_of_business >", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessGreaterThanOrEqualTo(String value) {
            addCriterion("scope_of_business >=", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessLessThan(String value) {
            addCriterion("scope_of_business <", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessLessThanOrEqualTo(String value) {
            addCriterion("scope_of_business <=", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessLike(String value) {
            addCriterion("scope_of_business like", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessNotLike(String value) {
            addCriterion("scope_of_business not like", value, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessIn(List<String> values) {
            addCriterion("scope_of_business in", values, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessNotIn(List<String> values) {
            addCriterion("scope_of_business not in", values, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessBetween(String value1, String value2) {
            addCriterion("scope_of_business between", value1, value2, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andScopeOfBusinessNotBetween(String value1, String value2) {
            addCriterion("scope_of_business not between", value1, value2, "scopeOfBusiness");
            return (Criteria) this;
        }

        public Criteria andDelayStatusIsNull() {
            addCriterion("delay_status is null");
            return (Criteria) this;
        }

        public Criteria andDelayStatusIsNotNull() {
            addCriterion("delay_status is not null");
            return (Criteria) this;
        }

        public Criteria andDelayStatusEqualTo(String value) {
            addCriterion("delay_status =", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusNotEqualTo(String value) {
            addCriterion("delay_status <>", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusGreaterThan(String value) {
            addCriterion("delay_status >", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("delay_status >=", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusLessThan(String value) {
            addCriterion("delay_status <", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusLessThanOrEqualTo(String value) {
            addCriterion("delay_status <=", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusLike(String value) {
            addCriterion("delay_status like", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusNotLike(String value) {
            addCriterion("delay_status not like", value, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusIn(List<String> values) {
            addCriterion("delay_status in", values, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusNotIn(List<String> values) {
            addCriterion("delay_status not in", values, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusBetween(String value1, String value2) {
            addCriterion("delay_status between", value1, value2, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayStatusNotBetween(String value1, String value2) {
            addCriterion("delay_status not between", value1, value2, "delayStatus");
            return (Criteria) this;
        }

        public Criteria andDelayDaysIsNull() {
            addCriterion("delay_days is null");
            return (Criteria) this;
        }

        public Criteria andDelayDaysIsNotNull() {
            addCriterion("delay_days is not null");
            return (Criteria) this;
        }

        public Criteria andDelayDaysEqualTo(Integer value) {
            addCriterion("delay_days =", value, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysNotEqualTo(Integer value) {
            addCriterion("delay_days <>", value, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysGreaterThan(Integer value) {
            addCriterion("delay_days >", value, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("delay_days >=", value, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysLessThan(Integer value) {
            addCriterion("delay_days <", value, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysLessThanOrEqualTo(Integer value) {
            addCriterion("delay_days <=", value, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysIn(List<Integer> values) {
            addCriterion("delay_days in", values, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysNotIn(List<Integer> values) {
            addCriterion("delay_days not in", values, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysBetween(Integer value1, Integer value2) {
            addCriterion("delay_days between", value1, value2, "delayDays");
            return (Criteria) this;
        }

        public Criteria andDelayDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("delay_days not between", value1, value2, "delayDays");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeIsNull() {
            addCriterion("change_apply_type is null");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeIsNotNull() {
            addCriterion("change_apply_type is not null");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeEqualTo(String value) {
            addCriterion("change_apply_type =", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeNotEqualTo(String value) {
            addCriterion("change_apply_type <>", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeGreaterThan(String value) {
            addCriterion("change_apply_type >", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("change_apply_type >=", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeLessThan(String value) {
            addCriterion("change_apply_type <", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeLessThanOrEqualTo(String value) {
            addCriterion("change_apply_type <=", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeLike(String value) {
            addCriterion("change_apply_type like", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeNotLike(String value) {
            addCriterion("change_apply_type not like", value, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeIn(List<String> values) {
            addCriterion("change_apply_type in", values, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeNotIn(List<String> values) {
            addCriterion("change_apply_type not in", values, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeBetween(String value1, String value2) {
            addCriterion("change_apply_type between", value1, value2, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeApplyTypeNotBetween(String value1, String value2) {
            addCriterion("change_apply_type not between", value1, value2, "changeApplyType");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateIsNull() {
            addCriterion("change_end_date is null");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateIsNotNull() {
            addCriterion("change_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateEqualTo(Date value) {
            addCriterion("change_end_date =", value, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateNotEqualTo(Date value) {
            addCriterion("change_end_date <>", value, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateGreaterThan(Date value) {
            addCriterion("change_end_date >", value, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("change_end_date >=", value, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateLessThan(Date value) {
            addCriterion("change_end_date <", value, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateLessThanOrEqualTo(Date value) {
            addCriterion("change_end_date <=", value, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateIn(List<Date> values) {
            addCriterion("change_end_date in", values, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateNotIn(List<Date> values) {
            addCriterion("change_end_date not in", values, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateBetween(Date value1, Date value2) {
            addCriterion("change_end_date between", value1, value2, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andChangeEndDateNotBetween(Date value1, Date value2) {
            addCriterion("change_end_date not between", value1, value2, "changeEndDate");
            return (Criteria) this;
        }

        public Criteria andSettledTypeIsNull() {
            addCriterion("settled_type is null");
            return (Criteria) this;
        }

        public Criteria andSettledTypeIsNotNull() {
            addCriterion("settled_type is not null");
            return (Criteria) this;
        }

        public Criteria andSettledTypeEqualTo(String value) {
            addCriterion("settled_type =", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeNotEqualTo(String value) {
            addCriterion("settled_type <>", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeGreaterThan(String value) {
            addCriterion("settled_type >", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeGreaterThanOrEqualTo(String value) {
            addCriterion("settled_type >=", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeLessThan(String value) {
            addCriterion("settled_type <", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeLessThanOrEqualTo(String value) {
            addCriterion("settled_type <=", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeLike(String value) {
            addCriterion("settled_type like", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeNotLike(String value) {
            addCriterion("settled_type not like", value, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeIn(List<String> values) {
            addCriterion("settled_type in", values, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeNotIn(List<String> values) {
            addCriterion("settled_type not in", values, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeBetween(String value1, String value2) {
            addCriterion("settled_type between", value1, value2, "settledType");
            return (Criteria) this;
        }

        public Criteria andSettledTypeNotBetween(String value1, String value2) {
            addCriterion("settled_type not between", value1, value2, "settledType");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusIsNull() {
            addCriterion("specially_approved_status is null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusIsNotNull() {
            addCriterion("specially_approved_status is not null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusEqualTo(String value) {
            addCriterion("specially_approved_status =", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusNotEqualTo(String value) {
            addCriterion("specially_approved_status <>", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusGreaterThan(String value) {
            addCriterion("specially_approved_status >", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusGreaterThanOrEqualTo(String value) {
            addCriterion("specially_approved_status >=", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusLessThan(String value) {
            addCriterion("specially_approved_status <", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusLessThanOrEqualTo(String value) {
            addCriterion("specially_approved_status <=", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusLike(String value) {
            addCriterion("specially_approved_status like", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusNotLike(String value) {
            addCriterion("specially_approved_status not like", value, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusIn(List<String> values) {
            addCriterion("specially_approved_status in", values, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusNotIn(List<String> values) {
            addCriterion("specially_approved_status not in", values, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusBetween(String value1, String value2) {
            addCriterion("specially_approved_status between", value1, value2, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedStatusNotBetween(String value1, String value2) {
            addCriterion("specially_approved_status not between", value1, value2, "speciallyApprovedStatus");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksIsNull() {
            addCriterion("specially_approved_remarks is null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksIsNotNull() {
            addCriterion("specially_approved_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksEqualTo(String value) {
            addCriterion("specially_approved_remarks =", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksNotEqualTo(String value) {
            addCriterion("specially_approved_remarks <>", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksGreaterThan(String value) {
            addCriterion("specially_approved_remarks >", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("specially_approved_remarks >=", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksLessThan(String value) {
            addCriterion("specially_approved_remarks <", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksLessThanOrEqualTo(String value) {
            addCriterion("specially_approved_remarks <=", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksLike(String value) {
            addCriterion("specially_approved_remarks like", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksNotLike(String value) {
            addCriterion("specially_approved_remarks not like", value, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksIn(List<String> values) {
            addCriterion("specially_approved_remarks in", values, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksNotIn(List<String> values) {
            addCriterion("specially_approved_remarks not in", values, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksBetween(String value1, String value2) {
            addCriterion("specially_approved_remarks between", value1, value2, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedRemarksNotBetween(String value1, String value2) {
            addCriterion("specially_approved_remarks not between", value1, value2, "speciallyApprovedRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateIsNull() {
            addCriterion("last_close_date is null");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateIsNotNull() {
            addCriterion("last_close_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateEqualTo(Date value) {
            addCriterion("last_close_date =", value, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateNotEqualTo(Date value) {
            addCriterion("last_close_date <>", value, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateGreaterThan(Date value) {
            addCriterion("last_close_date >", value, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_close_date >=", value, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateLessThan(Date value) {
            addCriterion("last_close_date <", value, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateLessThanOrEqualTo(Date value) {
            addCriterion("last_close_date <=", value, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateIn(List<Date> values) {
            addCriterion("last_close_date in", values, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateNotIn(List<Date> values) {
            addCriterion("last_close_date not in", values, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateBetween(Date value1, Date value2) {
            addCriterion("last_close_date between", value1, value2, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseDateNotBetween(Date value1, Date value2) {
            addCriterion("last_close_date not between", value1, value2, "lastCloseDate");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksIsNull() {
            addCriterion("last_close_remarks is null");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksIsNotNull() {
            addCriterion("last_close_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksEqualTo(String value) {
            addCriterion("last_close_remarks =", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksNotEqualTo(String value) {
            addCriterion("last_close_remarks <>", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksGreaterThan(String value) {
            addCriterion("last_close_remarks >", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("last_close_remarks >=", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksLessThan(String value) {
            addCriterion("last_close_remarks <", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksLessThanOrEqualTo(String value) {
            addCriterion("last_close_remarks <=", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksLike(String value) {
            addCriterion("last_close_remarks like", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksNotLike(String value) {
            addCriterion("last_close_remarks not like", value, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksIn(List<String> values) {
            addCriterion("last_close_remarks in", values, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksNotIn(List<String> values) {
            addCriterion("last_close_remarks not in", values, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksBetween(String value1, String value2) {
            addCriterion("last_close_remarks between", value1, value2, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andLastCloseRemarksNotBetween(String value1, String value2) {
            addCriterion("last_close_remarks not between", value1, value2, "lastCloseRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusIsNull() {
            addCriterion("supply_chain_status is null");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusIsNotNull() {
            addCriterion("supply_chain_status is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusEqualTo(String value) {
            addCriterion("supply_chain_status =", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusNotEqualTo(String value) {
            addCriterion("supply_chain_status <>", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusGreaterThan(String value) {
            addCriterion("supply_chain_status >", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusGreaterThanOrEqualTo(String value) {
            addCriterion("supply_chain_status >=", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusLessThan(String value) {
            addCriterion("supply_chain_status <", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusLessThanOrEqualTo(String value) {
            addCriterion("supply_chain_status <=", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusLike(String value) {
            addCriterion("supply_chain_status like", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusNotLike(String value) {
            addCriterion("supply_chain_status not like", value, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusIn(List<String> values) {
            addCriterion("supply_chain_status in", values, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusNotIn(List<String> values) {
            addCriterion("supply_chain_status not in", values, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusBetween(String value1, String value2) {
            addCriterion("supply_chain_status between", value1, value2, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andSupplyChainStatusNotBetween(String value1, String value2) {
            addCriterion("supply_chain_status not between", value1, value2, "supplyChainStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustIsNull() {
            addCriterion("license_is_must is null");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustIsNotNull() {
            addCriterion("license_is_must is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustEqualTo(String value) {
            addCriterion("license_is_must =", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustNotEqualTo(String value) {
            addCriterion("license_is_must <>", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustGreaterThan(String value) {
            addCriterion("license_is_must >", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustGreaterThanOrEqualTo(String value) {
            addCriterion("license_is_must >=", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustLessThan(String value) {
            addCriterion("license_is_must <", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustLessThanOrEqualTo(String value) {
            addCriterion("license_is_must <=", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustLike(String value) {
            addCriterion("license_is_must like", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustNotLike(String value) {
            addCriterion("license_is_must not like", value, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustIn(List<String> values) {
            addCriterion("license_is_must in", values, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustNotIn(List<String> values) {
            addCriterion("license_is_must not in", values, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustBetween(String value1, String value2) {
            addCriterion("license_is_must between", value1, value2, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andLicenseIsMustNotBetween(String value1, String value2) {
            addCriterion("license_is_must not between", value1, value2, "licenseIsMust");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicIsNull() {
            addCriterion("business_license_pic is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicIsNotNull() {
            addCriterion("business_license_pic is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicEqualTo(String value) {
            addCriterion("business_license_pic =", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotEqualTo(String value) {
            addCriterion("business_license_pic <>", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicGreaterThan(String value) {
            addCriterion("business_license_pic >", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicGreaterThanOrEqualTo(String value) {
            addCriterion("business_license_pic >=", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicLessThan(String value) {
            addCriterion("business_license_pic <", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicLessThanOrEqualTo(String value) {
            addCriterion("business_license_pic <=", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicLike(String value) {
            addCriterion("business_license_pic like", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotLike(String value) {
            addCriterion("business_license_pic not like", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicIn(List<String> values) {
            addCriterion("business_license_pic in", values, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotIn(List<String> values) {
            addCriterion("business_license_pic not in", values, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicBetween(String value1, String value2) {
            addCriterion("business_license_pic between", value1, value2, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotBetween(String value1, String value2) {
            addCriterion("business_license_pic not between", value1, value2, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusIsNull() {
            addCriterion("license_status is null");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusIsNotNull() {
            addCriterion("license_status is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusEqualTo(String value) {
            addCriterion("license_status =", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusNotEqualTo(String value) {
            addCriterion("license_status <>", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusGreaterThan(String value) {
            addCriterion("license_status >", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusGreaterThanOrEqualTo(String value) {
            addCriterion("license_status >=", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusLessThan(String value) {
            addCriterion("license_status <", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusLessThanOrEqualTo(String value) {
            addCriterion("license_status <=", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusLike(String value) {
            addCriterion("license_status like", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusNotLike(String value) {
            addCriterion("license_status not like", value, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusIn(List<String> values) {
            addCriterion("license_status in", values, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusNotIn(List<String> values) {
            addCriterion("license_status not in", values, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusBetween(String value1, String value2) {
            addCriterion("license_status between", value1, value2, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseStatusNotBetween(String value1, String value2) {
            addCriterion("license_status not between", value1, value2, "licenseStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonIsNull() {
            addCriterion("license_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonIsNotNull() {
            addCriterion("license_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonEqualTo(String value) {
            addCriterion("license_reject_reason =", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotEqualTo(String value) {
            addCriterion("license_reject_reason <>", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonGreaterThan(String value) {
            addCriterion("license_reject_reason >", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("license_reject_reason >=", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonLessThan(String value) {
            addCriterion("license_reject_reason <", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("license_reject_reason <=", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonLike(String value) {
            addCriterion("license_reject_reason like", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotLike(String value) {
            addCriterion("license_reject_reason not like", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonIn(List<String> values) {
            addCriterion("license_reject_reason in", values, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotIn(List<String> values) {
            addCriterion("license_reject_reason not in", values, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonBetween(String value1, String value2) {
            addCriterion("license_reject_reason between", value1, value2, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotBetween(String value1, String value2) {
            addCriterion("license_reject_reason not between", value1, value2, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusIsNull() {
            addCriterion("license_archive_status is null");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusIsNotNull() {
            addCriterion("license_archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusEqualTo(String value) {
            addCriterion("license_archive_status =", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusNotEqualTo(String value) {
            addCriterion("license_archive_status <>", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusGreaterThan(String value) {
            addCriterion("license_archive_status >", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("license_archive_status >=", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusLessThan(String value) {
            addCriterion("license_archive_status <", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("license_archive_status <=", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusLike(String value) {
            addCriterion("license_archive_status like", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusNotLike(String value) {
            addCriterion("license_archive_status not like", value, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusIn(List<String> values) {
            addCriterion("license_archive_status in", values, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusNotIn(List<String> values) {
            addCriterion("license_archive_status not in", values, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusBetween(String value1, String value2) {
            addCriterion("license_archive_status between", value1, value2, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andLicenseArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("license_archive_status not between", value1, value2, "licenseArchiveStatus");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
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

        public Criteria andBrandTypeIsNull() {
            addCriterion("brand_type is null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIsNotNull() {
            addCriterion("brand_type is not null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeEqualTo(String value) {
            addCriterion("brand_type =", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNotEqualTo(String value) {
            addCriterion("brand_type <>", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeGreaterThan(String value) {
            addCriterion("brand_type >", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeGreaterThanOrEqualTo(String value) {
            addCriterion("brand_type >=", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeLessThan(String value) {
            addCriterion("brand_type <", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeLessThanOrEqualTo(String value) {
            addCriterion("brand_type <=", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeLike(String value) {
            addCriterion("brand_type like", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNotLike(String value) {
            addCriterion("brand_type not like", value, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeIn(List<String> values) {
            addCriterion("brand_type in", values, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNotIn(List<String> values) {
            addCriterion("brand_type not in", values, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeBetween(String value1, String value2) {
            addCriterion("brand_type between", value1, value2, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeNotBetween(String value1, String value2) {
            addCriterion("brand_type not between", value1, value2, "brandType");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescIsNull() {
            addCriterion("brand_type_desc is null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescIsNotNull() {
            addCriterion("brand_type_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescEqualTo(String value) {
            addCriterion("brand_type_desc =", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescNotEqualTo(String value) {
            addCriterion("brand_type_desc <>", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescGreaterThan(String value) {
            addCriterion("brand_type_desc >", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("brand_type_desc >=", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescLessThan(String value) {
            addCriterion("brand_type_desc <", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescLessThanOrEqualTo(String value) {
            addCriterion("brand_type_desc <=", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescLike(String value) {
            addCriterion("brand_type_desc like", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescNotLike(String value) {
            addCriterion("brand_type_desc not like", value, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescIn(List<String> values) {
            addCriterion("brand_type_desc in", values, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescNotIn(List<String> values) {
            addCriterion("brand_type_desc not in", values, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescBetween(String value1, String value2) {
            addCriterion("brand_type_desc between", value1, value2, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andBrandTypeDescNotBetween(String value1, String value2) {
            addCriterion("brand_type_desc not between", value1, value2, "brandTypeDesc");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIsNull() {
            addCriterion("product_type2_id is null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIsNotNull() {
            addCriterion("product_type2_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdEqualTo(Integer value) {
            addCriterion("product_type2_id =", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotEqualTo(Integer value) {
            addCriterion("product_type2_id <>", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdGreaterThan(Integer value) {
            addCriterion("product_type2_id >", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type2_id >=", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdLessThan(Integer value) {
            addCriterion("product_type2_id <", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type2_id <=", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIn(List<Integer> values) {
            addCriterion("product_type2_id in", values, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotIn(List<Integer> values) {
            addCriterion("product_type2_id not in", values, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdBetween(Integer value1, Integer value2) {
            addCriterion("product_type2_id between", value1, value2, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type2_id not between", value1, value2, "productType2Id");
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