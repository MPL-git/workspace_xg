package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MchtBrandChgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtBrandChgExample() {
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

        public Criteria andMchtProductBrandIdIsNull() {
            addCriterion("mcht_product_brand_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdIsNotNull() {
            addCriterion("mcht_product_brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdEqualTo(Integer value) {
            addCriterion("mcht_product_brand_id =", value, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdNotEqualTo(Integer value) {
            addCriterion("mcht_product_brand_id <>", value, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdGreaterThan(Integer value) {
            addCriterion("mcht_product_brand_id >", value, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_product_brand_id >=", value, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdLessThan(Integer value) {
            addCriterion("mcht_product_brand_id <", value, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_product_brand_id <=", value, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdIn(List<Integer> values) {
            addCriterion("mcht_product_brand_id in", values, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdNotIn(List<Integer> values) {
            addCriterion("mcht_product_brand_id not in", values, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_product_brand_id between", value1, value2, "mchtProductBrandId");
            return (Criteria) this;
        }

        public Criteria andMchtProductBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_product_brand_id not between", value1, value2, "mchtProductBrandId");
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

        public Criteria andProductBrandIdIsNull() {
            addCriterion("product_brand_id is null");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdIsNotNull() {
            addCriterion("product_brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdEqualTo(Integer value) {
            addCriterion("product_brand_id =", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdNotEqualTo(Integer value) {
            addCriterion("product_brand_id <>", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdGreaterThan(Integer value) {
            addCriterion("product_brand_id >", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_brand_id >=", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdLessThan(Integer value) {
            addCriterion("product_brand_id <", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_brand_id <=", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdIn(List<Integer> values) {
            addCriterion("product_brand_id in", values, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdNotIn(List<Integer> values) {
            addCriterion("product_brand_id not in", values, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("product_brand_id between", value1, value2, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_brand_id not between", value1, value2, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameIsNull() {
            addCriterion("product_brand_name is null");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameIsNotNull() {
            addCriterion("product_brand_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameEqualTo(String value) {
            addCriterion("product_brand_name =", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameNotEqualTo(String value) {
            addCriterion("product_brand_name <>", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameGreaterThan(String value) {
            addCriterion("product_brand_name >", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_brand_name >=", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameLessThan(String value) {
            addCriterion("product_brand_name <", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameLessThanOrEqualTo(String value) {
            addCriterion("product_brand_name <=", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameLike(String value) {
            addCriterion("product_brand_name like", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameNotLike(String value) {
            addCriterion("product_brand_name not like", value, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameIn(List<String> values) {
            addCriterion("product_brand_name in", values, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameNotIn(List<String> values) {
            addCriterion("product_brand_name not in", values, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameBetween(String value1, String value2) {
            addCriterion("product_brand_name between", value1, value2, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andProductBrandNameNotBetween(String value1, String value2) {
            addCriterion("product_brand_name not between", value1, value2, "productBrandName");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateIsNull() {
            addCriterion("inspection_exp_date is null");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateIsNotNull() {
            addCriterion("inspection_exp_date is not null");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateEqualTo(Date value) {
            addCriterionForJDBCDate("inspection_exp_date =", value, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("inspection_exp_date <>", value, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("inspection_exp_date >", value, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("inspection_exp_date >=", value, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateLessThan(Date value) {
            addCriterionForJDBCDate("inspection_exp_date <", value, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("inspection_exp_date <=", value, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateIn(List<Date> values) {
            addCriterionForJDBCDate("inspection_exp_date in", values, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("inspection_exp_date not in", values, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("inspection_exp_date between", value1, value2, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andInspectionExpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("inspection_exp_date not between", value1, value2, "inspectionExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateIsNull() {
            addCriterion("other_exp_date is null");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateIsNotNull() {
            addCriterion("other_exp_date is not null");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateEqualTo(Date value) {
            addCriterionForJDBCDate("other_exp_date =", value, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("other_exp_date <>", value, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("other_exp_date >", value, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("other_exp_date >=", value, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateLessThan(Date value) {
            addCriterionForJDBCDate("other_exp_date <", value, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("other_exp_date <=", value, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateIn(List<Date> values) {
            addCriterionForJDBCDate("other_exp_date in", values, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("other_exp_date not in", values, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("other_exp_date between", value1, value2, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andOtherExpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("other_exp_date not between", value1, value2, "otherExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeIsNull() {
            addCriterion("aptitude_type is null");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeIsNotNull() {
            addCriterion("aptitude_type is not null");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeEqualTo(String value) {
            addCriterion("aptitude_type =", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeNotEqualTo(String value) {
            addCriterion("aptitude_type <>", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeGreaterThan(String value) {
            addCriterion("aptitude_type >", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("aptitude_type >=", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeLessThan(String value) {
            addCriterion("aptitude_type <", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeLessThanOrEqualTo(String value) {
            addCriterion("aptitude_type <=", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeLike(String value) {
            addCriterion("aptitude_type like", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeNotLike(String value) {
            addCriterion("aptitude_type not like", value, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeIn(List<String> values) {
            addCriterion("aptitude_type in", values, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeNotIn(List<String> values) {
            addCriterion("aptitude_type not in", values, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeBetween(String value1, String value2) {
            addCriterion("aptitude_type between", value1, value2, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeTypeNotBetween(String value1, String value2) {
            addCriterion("aptitude_type not between", value1, value2, "aptitudeType");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateIsNull() {
            addCriterion("aptitude_exp_date is null");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateIsNotNull() {
            addCriterion("aptitude_exp_date is not null");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateEqualTo(Date value) {
            addCriterionForJDBCDate("aptitude_exp_date =", value, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("aptitude_exp_date <>", value, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("aptitude_exp_date >", value, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("aptitude_exp_date >=", value, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateLessThan(Date value) {
            addCriterionForJDBCDate("aptitude_exp_date <", value, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("aptitude_exp_date <=", value, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateIn(List<Date> values) {
            addCriterionForJDBCDate("aptitude_exp_date in", values, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("aptitude_exp_date not in", values, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("aptitude_exp_date between", value1, value2, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andAptitudeExpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("aptitude_exp_date not between", value1, value2, "aptitudeExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateIsNull() {
            addCriterion("platform_auth_exp_date is null");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateIsNotNull() {
            addCriterion("platform_auth_exp_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateEqualTo(Date value) {
            addCriterionForJDBCDate("platform_auth_exp_date =", value, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("platform_auth_exp_date <>", value, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("platform_auth_exp_date >", value, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("platform_auth_exp_date >=", value, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateLessThan(Date value) {
            addCriterionForJDBCDate("platform_auth_exp_date <", value, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("platform_auth_exp_date <=", value, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateIn(List<Date> values) {
            addCriterionForJDBCDate("platform_auth_exp_date in", values, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("platform_auth_exp_date not in", values, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("platform_auth_exp_date between", value1, value2, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthExpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("platform_auth_exp_date not between", value1, value2, "platformAuthExpDate");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("audit_status like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("audit_status not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
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

        public Criteria andAuditInnerRemarksIsNull() {
            addCriterion("audit_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksIsNotNull() {
            addCriterion("audit_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksEqualTo(String value) {
            addCriterion("audit_inner_remarks =", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksNotEqualTo(String value) {
            addCriterion("audit_inner_remarks <>", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksGreaterThan(String value) {
            addCriterion("audit_inner_remarks >", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("audit_inner_remarks >=", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksLessThan(String value) {
            addCriterion("audit_inner_remarks <", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("audit_inner_remarks <=", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksLike(String value) {
            addCriterion("audit_inner_remarks like", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksNotLike(String value) {
            addCriterion("audit_inner_remarks not like", value, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksIn(List<String> values) {
            addCriterion("audit_inner_remarks in", values, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksNotIn(List<String> values) {
            addCriterion("audit_inner_remarks not in", values, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksBetween(String value1, String value2) {
            addCriterion("audit_inner_remarks between", value1, value2, "auditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("audit_inner_remarks not between", value1, value2, "auditInnerRemarks");
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

        public Criteria andPopCommissionRateIsNull() {
            addCriterion("pop_commission_rate is null");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateIsNotNull() {
            addCriterion("pop_commission_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate =", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateNotEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate <>", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateGreaterThan(BigDecimal value) {
            addCriterion("pop_commission_rate >", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate >=", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateLessThan(BigDecimal value) {
            addCriterion("pop_commission_rate <", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate <=", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateIn(List<BigDecimal> values) {
            addCriterion("pop_commission_rate in", values, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateNotIn(List<BigDecimal> values) {
            addCriterion("pop_commission_rate not in", values, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pop_commission_rate between", value1, value2, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pop_commission_rate not between", value1, value2, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPriceModelIsNull() {
            addCriterion("price_model is null");
            return (Criteria) this;
        }

        public Criteria andPriceModelIsNotNull() {
            addCriterion("price_model is not null");
            return (Criteria) this;
        }

        public Criteria andPriceModelEqualTo(String value) {
            addCriterion("price_model =", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelNotEqualTo(String value) {
            addCriterion("price_model <>", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelGreaterThan(String value) {
            addCriterion("price_model >", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelGreaterThanOrEqualTo(String value) {
            addCriterion("price_model >=", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelLessThan(String value) {
            addCriterion("price_model <", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelLessThanOrEqualTo(String value) {
            addCriterion("price_model <=", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelLike(String value) {
            addCriterion("price_model like", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelNotLike(String value) {
            addCriterion("price_model not like", value, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelIn(List<String> values) {
            addCriterion("price_model in", values, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelNotIn(List<String> values) {
            addCriterion("price_model not in", values, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelBetween(String value1, String value2) {
            addCriterion("price_model between", value1, value2, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelNotBetween(String value1, String value2) {
            addCriterion("price_model not between", value1, value2, "priceModel");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescIsNull() {
            addCriterion("price_model_desc is null");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescIsNotNull() {
            addCriterion("price_model_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescEqualTo(String value) {
            addCriterion("price_model_desc =", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescNotEqualTo(String value) {
            addCriterion("price_model_desc <>", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescGreaterThan(String value) {
            addCriterion("price_model_desc >", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescGreaterThanOrEqualTo(String value) {
            addCriterion("price_model_desc >=", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescLessThan(String value) {
            addCriterion("price_model_desc <", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescLessThanOrEqualTo(String value) {
            addCriterion("price_model_desc <=", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescLike(String value) {
            addCriterion("price_model_desc like", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescNotLike(String value) {
            addCriterion("price_model_desc not like", value, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescIn(List<String> values) {
            addCriterion("price_model_desc in", values, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescNotIn(List<String> values) {
            addCriterion("price_model_desc not in", values, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescBetween(String value1, String value2) {
            addCriterion("price_model_desc between", value1, value2, "priceModelDesc");
            return (Criteria) this;
        }

        public Criteria andPriceModelDescNotBetween(String value1, String value2) {
            addCriterion("price_model_desc not between", value1, value2, "priceModelDesc");
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

        public Criteria andIsAuditRecommitIsNull() {
            addCriterion("is_audit_recommit is null");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitIsNotNull() {
            addCriterion("is_audit_recommit is not null");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitEqualTo(String value) {
            addCriterion("is_audit_recommit =", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitNotEqualTo(String value) {
            addCriterion("is_audit_recommit <>", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitGreaterThan(String value) {
            addCriterion("is_audit_recommit >", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitGreaterThanOrEqualTo(String value) {
            addCriterion("is_audit_recommit >=", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitLessThan(String value) {
            addCriterion("is_audit_recommit <", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitLessThanOrEqualTo(String value) {
            addCriterion("is_audit_recommit <=", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitLike(String value) {
            addCriterion("is_audit_recommit like", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitNotLike(String value) {
            addCriterion("is_audit_recommit not like", value, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitIn(List<String> values) {
            addCriterion("is_audit_recommit in", values, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitNotIn(List<String> values) {
            addCriterion("is_audit_recommit not in", values, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitBetween(String value1, String value2) {
            addCriterion("is_audit_recommit between", value1, value2, "isAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsAuditRecommitNotBetween(String value1, String value2) {
            addCriterion("is_audit_recommit not between", value1, value2, "isAuditRecommit");
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