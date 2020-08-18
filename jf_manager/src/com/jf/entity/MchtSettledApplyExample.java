package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtSettledApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtSettledApplyExample() {
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

        public Criteria andSourceTypeIsNull() {
            addCriterion("source_type is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("source_type is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(String value) {
            addCriterion("source_type =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(String value) {
            addCriterion("source_type <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(String value) {
            addCriterion("source_type >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("source_type >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(String value) {
            addCriterion("source_type <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(String value) {
            addCriterion("source_type <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLike(String value) {
            addCriterion("source_type like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotLike(String value) {
            addCriterion("source_type not like", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<String> values) {
            addCriterion("source_type in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<String> values) {
            addCriterion("source_type not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(String value1, String value2) {
            addCriterion("source_type between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(String value1, String value2) {
            addCriterion("source_type not between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkIsNull() {
            addCriterion("source_remark is null");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkIsNotNull() {
            addCriterion("source_remark is not null");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkEqualTo(String value) {
            addCriterion("source_remark =", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkNotEqualTo(String value) {
            addCriterion("source_remark <>", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkGreaterThan(String value) {
            addCriterion("source_remark >", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("source_remark >=", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkLessThan(String value) {
            addCriterion("source_remark <", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkLessThanOrEqualTo(String value) {
            addCriterion("source_remark <=", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkLike(String value) {
            addCriterion("source_remark like", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkNotLike(String value) {
            addCriterion("source_remark not like", value, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkIn(List<String> values) {
            addCriterion("source_remark in", values, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkNotIn(List<String> values) {
            addCriterion("source_remark not in", values, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkBetween(String value1, String value2) {
            addCriterion("source_remark between", value1, value2, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkNotBetween(String value1, String value2) {
            addCriterion("source_remark not between", value1, value2, "sourceRemark");
            return (Criteria) this;
        }

        public Criteria andClientTypeIsNull() {
            addCriterion("client_type is null");
            return (Criteria) this;
        }

        public Criteria andClientTypeIsNotNull() {
            addCriterion("client_type is not null");
            return (Criteria) this;
        }

        public Criteria andClientTypeEqualTo(String value) {
            addCriterion("client_type =", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeNotEqualTo(String value) {
            addCriterion("client_type <>", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeGreaterThan(String value) {
            addCriterion("client_type >", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeGreaterThanOrEqualTo(String value) {
            addCriterion("client_type >=", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeLessThan(String value) {
            addCriterion("client_type <", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeLessThanOrEqualTo(String value) {
            addCriterion("client_type <=", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeLike(String value) {
            addCriterion("client_type like", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeNotLike(String value) {
            addCriterion("client_type not like", value, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeIn(List<String> values) {
            addCriterion("client_type in", values, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeNotIn(List<String> values) {
            addCriterion("client_type not in", values, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeBetween(String value1, String value2) {
            addCriterion("client_type between", value1, value2, "clientType");
            return (Criteria) this;
        }

        public Criteria andClientTypeNotBetween(String value1, String value2) {
            addCriterion("client_type not between", value1, value2, "clientType");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceIsNull() {
            addCriterion("customer_source is null");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceIsNotNull() {
            addCriterion("customer_source is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceEqualTo(String value) {
            addCriterion("customer_source =", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceNotEqualTo(String value) {
            addCriterion("customer_source <>", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceGreaterThan(String value) {
            addCriterion("customer_source >", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceGreaterThanOrEqualTo(String value) {
            addCriterion("customer_source >=", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceLessThan(String value) {
            addCriterion("customer_source <", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceLessThanOrEqualTo(String value) {
            addCriterion("customer_source <=", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceLike(String value) {
            addCriterion("customer_source like", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceNotLike(String value) {
            addCriterion("customer_source not like", value, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceIn(List<String> values) {
            addCriterion("customer_source in", values, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceNotIn(List<String> values) {
            addCriterion("customer_source not in", values, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceBetween(String value1, String value2) {
            addCriterion("customer_source between", value1, value2, "customerSource");
            return (Criteria) this;
        }

        public Criteria andCustomerSourceNotBetween(String value1, String value2) {
            addCriterion("customer_source not between", value1, value2, "customerSource");
            return (Criteria) this;
        }

        public Criteria andIsimportIsNull() {
            addCriterion("isimport is null");
            return (Criteria) this;
        }

        public Criteria andIsimportIsNotNull() {
            addCriterion("isimport is not null");
            return (Criteria) this;
        }

        public Criteria andIsimportEqualTo(String value) {
            addCriterion("isimport =", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportNotEqualTo(String value) {
            addCriterion("isimport <>", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportGreaterThan(String value) {
            addCriterion("isimport >", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportGreaterThanOrEqualTo(String value) {
            addCriterion("isimport >=", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportLessThan(String value) {
            addCriterion("isimport <", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportLessThanOrEqualTo(String value) {
            addCriterion("isimport <=", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportLike(String value) {
            addCriterion("isimport like", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportNotLike(String value) {
            addCriterion("isimport not like", value, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportIn(List<String> values) {
            addCriterion("isimport in", values, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportNotIn(List<String> values) {
            addCriterion("isimport not in", values, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportBetween(String value1, String value2) {
            addCriterion("isimport between", value1, value2, "isimport");
            return (Criteria) this;
        }

        public Criteria andIsimportNotBetween(String value1, String value2) {
            addCriterion("isimport not between", value1, value2, "isimport");
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(Integer value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(Integer value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(Integer value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(Integer value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(Integer value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(Integer value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<Integer> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<Integer> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(Integer value1, Integer value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(Integer value1, Integer value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(Integer value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(Integer value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(Integer value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(Integer value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(Integer value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(Integer value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<Integer> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<Integer> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(Integer value1, Integer value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(Integer value1, Integer value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(Integer value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(Integer value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(Integer value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(Integer value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(Integer value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(Integer value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<Integer> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<Integer> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(Integer value1, Integer value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(Integer value1, Integer value2) {
            addCriterion("county not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactJobIsNull() {
            addCriterion("contact_job is null");
            return (Criteria) this;
        }

        public Criteria andContactJobIsNotNull() {
            addCriterion("contact_job is not null");
            return (Criteria) this;
        }

        public Criteria andContactJobEqualTo(String value) {
            addCriterion("contact_job =", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobNotEqualTo(String value) {
            addCriterion("contact_job <>", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobGreaterThan(String value) {
            addCriterion("contact_job >", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobGreaterThanOrEqualTo(String value) {
            addCriterion("contact_job >=", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobLessThan(String value) {
            addCriterion("contact_job <", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobLessThanOrEqualTo(String value) {
            addCriterion("contact_job <=", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobLike(String value) {
            addCriterion("contact_job like", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobNotLike(String value) {
            addCriterion("contact_job not like", value, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobIn(List<String> values) {
            addCriterion("contact_job in", values, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobNotIn(List<String> values) {
            addCriterion("contact_job not in", values, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobBetween(String value1, String value2) {
            addCriterion("contact_job between", value1, value2, "contactJob");
            return (Criteria) this;
        }

        public Criteria andContactJobNotBetween(String value1, String value2) {
            addCriterion("contact_job not between", value1, value2, "contactJob");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
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

        public Criteria andTmallShopIsNull() {
            addCriterion("tmall_shop is null");
            return (Criteria) this;
        }

        public Criteria andTmallShopIsNotNull() {
            addCriterion("tmall_shop is not null");
            return (Criteria) this;
        }

        public Criteria andTmallShopEqualTo(String value) {
            addCriterion("tmall_shop =", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopNotEqualTo(String value) {
            addCriterion("tmall_shop <>", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopGreaterThan(String value) {
            addCriterion("tmall_shop >", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopGreaterThanOrEqualTo(String value) {
            addCriterion("tmall_shop >=", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopLessThan(String value) {
            addCriterion("tmall_shop <", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopLessThanOrEqualTo(String value) {
            addCriterion("tmall_shop <=", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopLike(String value) {
            addCriterion("tmall_shop like", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopNotLike(String value) {
            addCriterion("tmall_shop not like", value, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopIn(List<String> values) {
            addCriterion("tmall_shop in", values, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopNotIn(List<String> values) {
            addCriterion("tmall_shop not in", values, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopBetween(String value1, String value2) {
            addCriterion("tmall_shop between", value1, value2, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTmallShopNotBetween(String value1, String value2) {
            addCriterion("tmall_shop not between", value1, value2, "tmallShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopIsNull() {
            addCriterion("taobao_shop is null");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopIsNotNull() {
            addCriterion("taobao_shop is not null");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopEqualTo(String value) {
            addCriterion("taobao_shop =", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopNotEqualTo(String value) {
            addCriterion("taobao_shop <>", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopGreaterThan(String value) {
            addCriterion("taobao_shop >", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopGreaterThanOrEqualTo(String value) {
            addCriterion("taobao_shop >=", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopLessThan(String value) {
            addCriterion("taobao_shop <", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopLessThanOrEqualTo(String value) {
            addCriterion("taobao_shop <=", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopLike(String value) {
            addCriterion("taobao_shop like", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopNotLike(String value) {
            addCriterion("taobao_shop not like", value, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopIn(List<String> values) {
            addCriterion("taobao_shop in", values, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopNotIn(List<String> values) {
            addCriterion("taobao_shop not in", values, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopBetween(String value1, String value2) {
            addCriterion("taobao_shop between", value1, value2, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andTaobaoShopNotBetween(String value1, String value2) {
            addCriterion("taobao_shop not between", value1, value2, "taobaoShop");
            return (Criteria) this;
        }

        public Criteria andJdShopIsNull() {
            addCriterion("jd_shop is null");
            return (Criteria) this;
        }

        public Criteria andJdShopIsNotNull() {
            addCriterion("jd_shop is not null");
            return (Criteria) this;
        }

        public Criteria andJdShopEqualTo(String value) {
            addCriterion("jd_shop =", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopNotEqualTo(String value) {
            addCriterion("jd_shop <>", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopGreaterThan(String value) {
            addCriterion("jd_shop >", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopGreaterThanOrEqualTo(String value) {
            addCriterion("jd_shop >=", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopLessThan(String value) {
            addCriterion("jd_shop <", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopLessThanOrEqualTo(String value) {
            addCriterion("jd_shop <=", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopLike(String value) {
            addCriterion("jd_shop like", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopNotLike(String value) {
            addCriterion("jd_shop not like", value, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopIn(List<String> values) {
            addCriterion("jd_shop in", values, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopNotIn(List<String> values) {
            addCriterion("jd_shop not in", values, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopBetween(String value1, String value2) {
            addCriterion("jd_shop between", value1, value2, "jdShop");
            return (Criteria) this;
        }

        public Criteria andJdShopNotBetween(String value1, String value2) {
            addCriterion("jd_shop not between", value1, value2, "jdShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopIsNull() {
            addCriterion("vips_shop is null");
            return (Criteria) this;
        }

        public Criteria andVipsShopIsNotNull() {
            addCriterion("vips_shop is not null");
            return (Criteria) this;
        }

        public Criteria andVipsShopEqualTo(String value) {
            addCriterion("vips_shop =", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopNotEqualTo(String value) {
            addCriterion("vips_shop <>", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopGreaterThan(String value) {
            addCriterion("vips_shop >", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopGreaterThanOrEqualTo(String value) {
            addCriterion("vips_shop >=", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopLessThan(String value) {
            addCriterion("vips_shop <", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopLessThanOrEqualTo(String value) {
            addCriterion("vips_shop <=", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopLike(String value) {
            addCriterion("vips_shop like", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopNotLike(String value) {
            addCriterion("vips_shop not like", value, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopIn(List<String> values) {
            addCriterion("vips_shop in", values, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopNotIn(List<String> values) {
            addCriterion("vips_shop not in", values, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopBetween(String value1, String value2) {
            addCriterion("vips_shop between", value1, value2, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andVipsShopNotBetween(String value1, String value2) {
            addCriterion("vips_shop not between", value1, value2, "vipsShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopIsNull() {
            addCriterion("other_shop is null");
            return (Criteria) this;
        }

        public Criteria andOtherShopIsNotNull() {
            addCriterion("other_shop is not null");
            return (Criteria) this;
        }

        public Criteria andOtherShopEqualTo(String value) {
            addCriterion("other_shop =", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopNotEqualTo(String value) {
            addCriterion("other_shop <>", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopGreaterThan(String value) {
            addCriterion("other_shop >", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopGreaterThanOrEqualTo(String value) {
            addCriterion("other_shop >=", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopLessThan(String value) {
            addCriterion("other_shop <", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopLessThanOrEqualTo(String value) {
            addCriterion("other_shop <=", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopLike(String value) {
            addCriterion("other_shop like", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopNotLike(String value) {
            addCriterion("other_shop not like", value, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopIn(List<String> values) {
            addCriterion("other_shop in", values, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopNotIn(List<String> values) {
            addCriterion("other_shop not in", values, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopBetween(String value1, String value2) {
            addCriterion("other_shop between", value1, value2, "otherShop");
            return (Criteria) this;
        }

        public Criteria andOtherShopNotBetween(String value1, String value2) {
            addCriterion("other_shop not between", value1, value2, "otherShop");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNull() {
            addCriterion("product_type_id is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNotNull() {
            addCriterion("product_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdEqualTo(Integer value) {
            addCriterion("product_type_id =", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotEqualTo(Integer value) {
            addCriterion("product_type_id <>", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThan(Integer value) {
            addCriterion("product_type_id >", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type_id >=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThan(Integer value) {
            addCriterion("product_type_id <", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type_id <=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIn(List<Integer> values) {
            addCriterion("product_type_id in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotIn(List<Integer> values) {
            addCriterion("product_type_id not in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("product_type_id between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type_id not between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainIsNull() {
            addCriterion("product_type_main is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainIsNotNull() {
            addCriterion("product_type_main is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainEqualTo(String value) {
            addCriterion("product_type_main =", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainNotEqualTo(String value) {
            addCriterion("product_type_main <>", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainGreaterThan(String value) {
            addCriterion("product_type_main >", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainGreaterThanOrEqualTo(String value) {
            addCriterion("product_type_main >=", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainLessThan(String value) {
            addCriterion("product_type_main <", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainLessThanOrEqualTo(String value) {
            addCriterion("product_type_main <=", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainLike(String value) {
            addCriterion("product_type_main like", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainNotLike(String value) {
            addCriterion("product_type_main not like", value, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainIn(List<String> values) {
            addCriterion("product_type_main in", values, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainNotIn(List<String> values) {
            addCriterion("product_type_main not in", values, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainBetween(String value1, String value2) {
            addCriterion("product_type_main between", value1, value2, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductTypeMainNotBetween(String value1, String value2) {
            addCriterion("product_type_main not between", value1, value2, "productTypeMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainIsNull() {
            addCriterion("product_brand_main is null");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainIsNotNull() {
            addCriterion("product_brand_main is not null");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainEqualTo(String value) {
            addCriterion("product_brand_main =", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainNotEqualTo(String value) {
            addCriterion("product_brand_main <>", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainGreaterThan(String value) {
            addCriterion("product_brand_main >", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainGreaterThanOrEqualTo(String value) {
            addCriterion("product_brand_main >=", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainLessThan(String value) {
            addCriterion("product_brand_main <", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainLessThanOrEqualTo(String value) {
            addCriterion("product_brand_main <=", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainLike(String value) {
            addCriterion("product_brand_main like", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainNotLike(String value) {
            addCriterion("product_brand_main not like", value, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainIn(List<String> values) {
            addCriterion("product_brand_main in", values, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainNotIn(List<String> values) {
            addCriterion("product_brand_main not in", values, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainBetween(String value1, String value2) {
            addCriterion("product_brand_main between", value1, value2, "productBrandMain");
            return (Criteria) this;
        }

        public Criteria andProductBrandMainNotBetween(String value1, String value2) {
            addCriterion("product_brand_main not between", value1, value2, "productBrandMain");
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

        public Criteria andZsProductTypeIdsIsNull() {
            addCriterion("zs_product_type_ids is null");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsIsNotNull() {
            addCriterion("zs_product_type_ids is not null");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsEqualTo(String value) {
            addCriterion("zs_product_type_ids =", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsNotEqualTo(String value) {
            addCriterion("zs_product_type_ids <>", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsGreaterThan(String value) {
            addCriterion("zs_product_type_ids >", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsGreaterThanOrEqualTo(String value) {
            addCriterion("zs_product_type_ids >=", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsLessThan(String value) {
            addCriterion("zs_product_type_ids <", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsLessThanOrEqualTo(String value) {
            addCriterion("zs_product_type_ids <=", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsLike(String value) {
            addCriterion("zs_product_type_ids like", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsNotLike(String value) {
            addCriterion("zs_product_type_ids not like", value, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsIn(List<String> values) {
            addCriterion("zs_product_type_ids in", values, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsNotIn(List<String> values) {
            addCriterion("zs_product_type_ids not in", values, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsBetween(String value1, String value2) {
            addCriterion("zs_product_type_ids between", value1, value2, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andZsProductTypeIdsNotBetween(String value1, String value2) {
            addCriterion("zs_product_type_ids not between", value1, value2, "zsProductTypeIds");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdIsNull() {
            addCriterion("platform_contact_id is null");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdIsNotNull() {
            addCriterion("platform_contact_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdEqualTo(Integer value) {
            addCriterion("platform_contact_id =", value, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdNotEqualTo(Integer value) {
            addCriterion("platform_contact_id <>", value, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdGreaterThan(Integer value) {
            addCriterion("platform_contact_id >", value, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_contact_id >=", value, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdLessThan(Integer value) {
            addCriterion("platform_contact_id <", value, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdLessThanOrEqualTo(Integer value) {
            addCriterion("platform_contact_id <=", value, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdIn(List<Integer> values) {
            addCriterion("platform_contact_id in", values, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdNotIn(List<Integer> values) {
            addCriterion("platform_contact_id not in", values, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdBetween(Integer value1, Integer value2) {
            addCriterion("platform_contact_id between", value1, value2, "platformContactId");
            return (Criteria) this;
        }

        public Criteria andPlatformContactIdNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_contact_id not between", value1, value2, "platformContactId");
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

        public Criteria andProductSituationIsNull() {
            addCriterion("product_situation is null");
            return (Criteria) this;
        }

        public Criteria andProductSituationIsNotNull() {
            addCriterion("product_situation is not null");
            return (Criteria) this;
        }

        public Criteria andProductSituationEqualTo(String value) {
            addCriterion("product_situation =", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationNotEqualTo(String value) {
            addCriterion("product_situation <>", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationGreaterThan(String value) {
            addCriterion("product_situation >", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationGreaterThanOrEqualTo(String value) {
            addCriterion("product_situation >=", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationLessThan(String value) {
            addCriterion("product_situation <", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationLessThanOrEqualTo(String value) {
            addCriterion("product_situation <=", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationLike(String value) {
            addCriterion("product_situation like", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationNotLike(String value) {
            addCriterion("product_situation not like", value, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationIn(List<String> values) {
            addCriterion("product_situation in", values, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationNotIn(List<String> values) {
            addCriterion("product_situation not in", values, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationBetween(String value1, String value2) {
            addCriterion("product_situation between", value1, value2, "productSituation");
            return (Criteria) this;
        }

        public Criteria andProductSituationNotBetween(String value1, String value2) {
            addCriterion("product_situation not between", value1, value2, "productSituation");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkIsNull() {
            addCriterion("other_channel_link is null");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkIsNotNull() {
            addCriterion("other_channel_link is not null");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkEqualTo(String value) {
            addCriterion("other_channel_link =", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkNotEqualTo(String value) {
            addCriterion("other_channel_link <>", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkGreaterThan(String value) {
            addCriterion("other_channel_link >", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkGreaterThanOrEqualTo(String value) {
            addCriterion("other_channel_link >=", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkLessThan(String value) {
            addCriterion("other_channel_link <", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkLessThanOrEqualTo(String value) {
            addCriterion("other_channel_link <=", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkLike(String value) {
            addCriterion("other_channel_link like", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkNotLike(String value) {
            addCriterion("other_channel_link not like", value, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkIn(List<String> values) {
            addCriterion("other_channel_link in", values, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkNotIn(List<String> values) {
            addCriterion("other_channel_link not in", values, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkBetween(String value1, String value2) {
            addCriterion("other_channel_link between", value1, value2, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andOtherChannelLinkNotBetween(String value1, String value2) {
            addCriterion("other_channel_link not between", value1, value2, "otherChannelLink");
            return (Criteria) this;
        }

        public Criteria andLogisticsIsNull() {
            addCriterion("logistics is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsIsNotNull() {
            addCriterion("logistics is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsEqualTo(String value) {
            addCriterion("logistics =", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotEqualTo(String value) {
            addCriterion("logistics <>", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsGreaterThan(String value) {
            addCriterion("logistics >", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsGreaterThanOrEqualTo(String value) {
            addCriterion("logistics >=", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsLessThan(String value) {
            addCriterion("logistics <", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsLessThanOrEqualTo(String value) {
            addCriterion("logistics <=", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsLike(String value) {
            addCriterion("logistics like", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotLike(String value) {
            addCriterion("logistics not like", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsIn(List<String> values) {
            addCriterion("logistics in", values, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotIn(List<String> values) {
            addCriterion("logistics not in", values, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsBetween(String value1, String value2) {
            addCriterion("logistics between", value1, value2, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotBetween(String value1, String value2) {
            addCriterion("logistics not between", value1, value2, "logistics");
            return (Criteria) this;
        }

        public Criteria andTeamSituationIsNull() {
            addCriterion("team_situation is null");
            return (Criteria) this;
        }

        public Criteria andTeamSituationIsNotNull() {
            addCriterion("team_situation is not null");
            return (Criteria) this;
        }

        public Criteria andTeamSituationEqualTo(String value) {
            addCriterion("team_situation =", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationNotEqualTo(String value) {
            addCriterion("team_situation <>", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationGreaterThan(String value) {
            addCriterion("team_situation >", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationGreaterThanOrEqualTo(String value) {
            addCriterion("team_situation >=", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationLessThan(String value) {
            addCriterion("team_situation <", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationLessThanOrEqualTo(String value) {
            addCriterion("team_situation <=", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationLike(String value) {
            addCriterion("team_situation like", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationNotLike(String value) {
            addCriterion("team_situation not like", value, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationIn(List<String> values) {
            addCriterion("team_situation in", values, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationNotIn(List<String> values) {
            addCriterion("team_situation not in", values, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationBetween(String value1, String value2) {
            addCriterion("team_situation between", value1, value2, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andTeamSituationNotBetween(String value1, String value2) {
            addCriterion("team_situation not between", value1, value2, "teamSituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationIsNull() {
            addCriterion("company_situation is null");
            return (Criteria) this;
        }

        public Criteria andCompanySituationIsNotNull() {
            addCriterion("company_situation is not null");
            return (Criteria) this;
        }

        public Criteria andCompanySituationEqualTo(String value) {
            addCriterion("company_situation =", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationNotEqualTo(String value) {
            addCriterion("company_situation <>", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationGreaterThan(String value) {
            addCriterion("company_situation >", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationGreaterThanOrEqualTo(String value) {
            addCriterion("company_situation >=", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationLessThan(String value) {
            addCriterion("company_situation <", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationLessThanOrEqualTo(String value) {
            addCriterion("company_situation <=", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationLike(String value) {
            addCriterion("company_situation like", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationNotLike(String value) {
            addCriterion("company_situation not like", value, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationIn(List<String> values) {
            addCriterion("company_situation in", values, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationNotIn(List<String> values) {
            addCriterion("company_situation not in", values, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationBetween(String value1, String value2) {
            addCriterion("company_situation between", value1, value2, "companySituation");
            return (Criteria) this;
        }

        public Criteria andCompanySituationNotBetween(String value1, String value2) {
            addCriterion("company_situation not between", value1, value2, "companySituation");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateIsNull() {
            addCriterion("info_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateIsNotNull() {
            addCriterion("info_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateEqualTo(Date value) {
            addCriterion("info_confirm_date =", value, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateNotEqualTo(Date value) {
            addCriterion("info_confirm_date <>", value, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateGreaterThan(Date value) {
            addCriterion("info_confirm_date >", value, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("info_confirm_date >=", value, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateLessThan(Date value) {
            addCriterion("info_confirm_date <", value, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("info_confirm_date <=", value, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateIn(List<Date> values) {
            addCriterion("info_confirm_date in", values, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateNotIn(List<Date> values) {
            addCriterion("info_confirm_date not in", values, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateBetween(Date value1, Date value2) {
            addCriterion("info_confirm_date between", value1, value2, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("info_confirm_date not between", value1, value2, "infoConfirmDate");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByIsNull() {
            addCriterion("info_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByIsNotNull() {
            addCriterion("info_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByEqualTo(Integer value) {
            addCriterion("info_confirm_by =", value, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByNotEqualTo(Integer value) {
            addCriterion("info_confirm_by <>", value, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByGreaterThan(Integer value) {
            addCriterion("info_confirm_by >", value, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("info_confirm_by >=", value, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByLessThan(Integer value) {
            addCriterion("info_confirm_by <", value, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("info_confirm_by <=", value, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByIn(List<Integer> values) {
            addCriterion("info_confirm_by in", values, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByNotIn(List<Integer> values) {
            addCriterion("info_confirm_by not in", values, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("info_confirm_by between", value1, value2, "infoConfirmBy");
            return (Criteria) this;
        }

        public Criteria andInfoConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("info_confirm_by not between", value1, value2, "infoConfirmBy");
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

        public Criteria andDepositConfirmStatusIsNull() {
            addCriterion("deposit_confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusIsNotNull() {
            addCriterion("deposit_confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusEqualTo(String value) {
            addCriterion("deposit_confirm_status =", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusNotEqualTo(String value) {
            addCriterion("deposit_confirm_status <>", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusGreaterThan(String value) {
            addCriterion("deposit_confirm_status >", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_confirm_status >=", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusLessThan(String value) {
            addCriterion("deposit_confirm_status <", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("deposit_confirm_status <=", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusLike(String value) {
            addCriterion("deposit_confirm_status like", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusNotLike(String value) {
            addCriterion("deposit_confirm_status not like", value, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusIn(List<String> values) {
            addCriterion("deposit_confirm_status in", values, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusNotIn(List<String> values) {
            addCriterion("deposit_confirm_status not in", values, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusBetween(String value1, String value2) {
            addCriterion("deposit_confirm_status between", value1, value2, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("deposit_confirm_status not between", value1, value2, "depositConfirmStatus");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateIsNull() {
            addCriterion("deposit_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateIsNotNull() {
            addCriterion("deposit_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateEqualTo(Date value) {
            addCriterion("deposit_confirm_date =", value, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateNotEqualTo(Date value) {
            addCriterion("deposit_confirm_date <>", value, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateGreaterThan(Date value) {
            addCriterion("deposit_confirm_date >", value, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("deposit_confirm_date >=", value, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateLessThan(Date value) {
            addCriterion("deposit_confirm_date <", value, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("deposit_confirm_date <=", value, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateIn(List<Date> values) {
            addCriterion("deposit_confirm_date in", values, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateNotIn(List<Date> values) {
            addCriterion("deposit_confirm_date not in", values, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateBetween(Date value1, Date value2) {
            addCriterion("deposit_confirm_date between", value1, value2, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("deposit_confirm_date not between", value1, value2, "depositConfirmDate");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByIsNull() {
            addCriterion("deposit_confirm_by is null");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByIsNotNull() {
            addCriterion("deposit_confirm_by is not null");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByEqualTo(Integer value) {
            addCriterion("deposit_confirm_by =", value, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByNotEqualTo(Integer value) {
            addCriterion("deposit_confirm_by <>", value, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByGreaterThan(Integer value) {
            addCriterion("deposit_confirm_by >", value, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByGreaterThanOrEqualTo(Integer value) {
            addCriterion("deposit_confirm_by >=", value, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByLessThan(Integer value) {
            addCriterion("deposit_confirm_by <", value, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByLessThanOrEqualTo(Integer value) {
            addCriterion("deposit_confirm_by <=", value, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByIn(List<Integer> values) {
            addCriterion("deposit_confirm_by in", values, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByNotIn(List<Integer> values) {
            addCriterion("deposit_confirm_by not in", values, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByBetween(Integer value1, Integer value2) {
            addCriterion("deposit_confirm_by between", value1, value2, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andDepositConfirmByNotBetween(Integer value1, Integer value2) {
            addCriterion("deposit_confirm_by not between", value1, value2, "depositConfirmBy");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andWechatIsNull() {
            addCriterion("wechat is null");
            return (Criteria) this;
        }

        public Criteria andWechatIsNotNull() {
            addCriterion("wechat is not null");
            return (Criteria) this;
        }

        public Criteria andWechatEqualTo(String value) {
            addCriterion("wechat =", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotEqualTo(String value) {
            addCriterion("wechat <>", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThan(String value) {
            addCriterion("wechat >", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThanOrEqualTo(String value) {
            addCriterion("wechat >=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThan(String value) {
            addCriterion("wechat <", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThanOrEqualTo(String value) {
            addCriterion("wechat <=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLike(String value) {
            addCriterion("wechat like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotLike(String value) {
            addCriterion("wechat not like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatIn(List<String> values) {
            addCriterion("wechat in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotIn(List<String> values) {
            addCriterion("wechat not in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatBetween(String value1, String value2) {
            addCriterion("wechat between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotBetween(String value1, String value2) {
            addCriterion("wechat not between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andUsedExpressIsNull() {
            addCriterion("used_express is null");
            return (Criteria) this;
        }

        public Criteria andUsedExpressIsNotNull() {
            addCriterion("used_express is not null");
            return (Criteria) this;
        }

        public Criteria andUsedExpressEqualTo(String value) {
            addCriterion("used_express =", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressNotEqualTo(String value) {
            addCriterion("used_express <>", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressGreaterThan(String value) {
            addCriterion("used_express >", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressGreaterThanOrEqualTo(String value) {
            addCriterion("used_express >=", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressLessThan(String value) {
            addCriterion("used_express <", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressLessThanOrEqualTo(String value) {
            addCriterion("used_express <=", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressLike(String value) {
            addCriterion("used_express like", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressNotLike(String value) {
            addCriterion("used_express not like", value, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressIn(List<String> values) {
            addCriterion("used_express in", values, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressNotIn(List<String> values) {
            addCriterion("used_express not in", values, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressBetween(String value1, String value2) {
            addCriterion("used_express between", value1, value2, "usedExpress");
            return (Criteria) this;
        }

        public Criteria andUsedExpressNotBetween(String value1, String value2) {
            addCriterion("used_express not between", value1, value2, "usedExpress");
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

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
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