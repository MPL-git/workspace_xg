package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtInfoChgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtInfoChgExample() {
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

        public Criteria andAuditRemarksIsNull() {
            addCriterion("audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksIsNotNull() {
            addCriterion("audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksEqualTo(String value) {
            addCriterion("audit_remarks =", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotEqualTo(String value) {
            addCriterion("audit_remarks <>", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksGreaterThan(String value) {
            addCriterion("audit_remarks >", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("audit_remarks >=", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLessThan(String value) {
            addCriterion("audit_remarks <", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("audit_remarks <=", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLike(String value) {
            addCriterion("audit_remarks like", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotLike(String value) {
            addCriterion("audit_remarks not like", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksIn(List<String> values) {
            addCriterion("audit_remarks in", values, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotIn(List<String> values) {
            addCriterion("audit_remarks not in", values, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksBetween(String value1, String value2) {
            addCriterion("audit_remarks between", value1, value2, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("audit_remarks not between", value1, value2, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andCommitDateIsNull() {
            addCriterion("commit_date is null");
            return (Criteria) this;
        }

        public Criteria andCommitDateIsNotNull() {
            addCriterion("commit_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommitDateEqualTo(Date value) {
            addCriterion("commit_date =", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotEqualTo(Date value) {
            addCriterion("commit_date <>", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateGreaterThan(Date value) {
            addCriterion("commit_date >", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commit_date >=", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateLessThan(Date value) {
            addCriterion("commit_date <", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateLessThanOrEqualTo(Date value) {
            addCriterion("commit_date <=", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateIn(List<Date> values) {
            addCriterion("commit_date in", values, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotIn(List<Date> values) {
            addCriterion("commit_date not in", values, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateBetween(Date value1, Date value2) {
            addCriterion("commit_date between", value1, value2, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotBetween(Date value1, Date value2) {
            addCriterion("commit_date not between", value1, value2, "commitDate");
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

        public Criteria andArchiveDealStatusIsNull() {
            addCriterion("archive_deal_status is null");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusIsNotNull() {
            addCriterion("archive_deal_status is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusEqualTo(String value) {
            addCriterion("archive_deal_status =", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusNotEqualTo(String value) {
            addCriterion("archive_deal_status <>", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusGreaterThan(String value) {
            addCriterion("archive_deal_status >", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusGreaterThanOrEqualTo(String value) {
            addCriterion("archive_deal_status >=", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusLessThan(String value) {
            addCriterion("archive_deal_status <", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusLessThanOrEqualTo(String value) {
            addCriterion("archive_deal_status <=", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusLike(String value) {
            addCriterion("archive_deal_status like", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusNotLike(String value) {
            addCriterion("archive_deal_status not like", value, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusIn(List<String> values) {
            addCriterion("archive_deal_status in", values, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusNotIn(List<String> values) {
            addCriterion("archive_deal_status not in", values, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusBetween(String value1, String value2) {
            addCriterion("archive_deal_status between", value1, value2, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealStatusNotBetween(String value1, String value2) {
            addCriterion("archive_deal_status not between", value1, value2, "archiveDealStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksIsNull() {
            addCriterion("archive_deal_remarks is null");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksIsNotNull() {
            addCriterion("archive_deal_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksEqualTo(String value) {
            addCriterion("archive_deal_remarks =", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksNotEqualTo(String value) {
            addCriterion("archive_deal_remarks <>", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksGreaterThan(String value) {
            addCriterion("archive_deal_remarks >", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("archive_deal_remarks >=", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksLessThan(String value) {
            addCriterion("archive_deal_remarks <", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksLessThanOrEqualTo(String value) {
            addCriterion("archive_deal_remarks <=", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksLike(String value) {
            addCriterion("archive_deal_remarks like", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksNotLike(String value) {
            addCriterion("archive_deal_remarks not like", value, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksIn(List<String> values) {
            addCriterion("archive_deal_remarks in", values, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksNotIn(List<String> values) {
            addCriterion("archive_deal_remarks not in", values, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksBetween(String value1, String value2) {
            addCriterion("archive_deal_remarks between", value1, value2, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealRemarksNotBetween(String value1, String value2) {
            addCriterion("archive_deal_remarks not between", value1, value2, "archiveDealRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksIsNull() {
            addCriterion("archive_deal_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksIsNotNull() {
            addCriterion("archive_deal_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksEqualTo(String value) {
            addCriterion("archive_deal_inner_remarks =", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksNotEqualTo(String value) {
            addCriterion("archive_deal_inner_remarks <>", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksGreaterThan(String value) {
            addCriterion("archive_deal_inner_remarks >", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("archive_deal_inner_remarks >=", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksLessThan(String value) {
            addCriterion("archive_deal_inner_remarks <", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("archive_deal_inner_remarks <=", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksLike(String value) {
            addCriterion("archive_deal_inner_remarks like", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksNotLike(String value) {
            addCriterion("archive_deal_inner_remarks not like", value, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksIn(List<String> values) {
            addCriterion("archive_deal_inner_remarks in", values, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksNotIn(List<String> values) {
            addCriterion("archive_deal_inner_remarks not in", values, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksBetween(String value1, String value2) {
            addCriterion("archive_deal_inner_remarks between", value1, value2, "archiveDealInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveDealInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("archive_deal_inner_remarks not between", value1, value2, "archiveDealInnerRemarks");
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