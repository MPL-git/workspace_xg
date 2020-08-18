package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MchtProductBrandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtProductBrandExample() {
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

        public Criteria andAptitudePicIsNull() {
            addCriterion("aptitude_pic is null");
            return (Criteria) this;
        }

        public Criteria andAptitudePicIsNotNull() {
            addCriterion("aptitude_pic is not null");
            return (Criteria) this;
        }

        public Criteria andAptitudePicEqualTo(String value) {
            addCriterion("aptitude_pic =", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicNotEqualTo(String value) {
            addCriterion("aptitude_pic <>", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicGreaterThan(String value) {
            addCriterion("aptitude_pic >", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicGreaterThanOrEqualTo(String value) {
            addCriterion("aptitude_pic >=", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicLessThan(String value) {
            addCriterion("aptitude_pic <", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicLessThanOrEqualTo(String value) {
            addCriterion("aptitude_pic <=", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicLike(String value) {
            addCriterion("aptitude_pic like", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicNotLike(String value) {
            addCriterion("aptitude_pic not like", value, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicIn(List<String> values) {
            addCriterion("aptitude_pic in", values, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicNotIn(List<String> values) {
            addCriterion("aptitude_pic not in", values, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicBetween(String value1, String value2) {
            addCriterion("aptitude_pic between", value1, value2, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andAptitudePicNotBetween(String value1, String value2) {
            addCriterion("aptitude_pic not between", value1, value2, "aptitudePic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicIsNull() {
            addCriterion("platform_auth_pic is null");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicIsNotNull() {
            addCriterion("platform_auth_pic is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicEqualTo(String value) {
            addCriterion("platform_auth_pic =", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicNotEqualTo(String value) {
            addCriterion("platform_auth_pic <>", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicGreaterThan(String value) {
            addCriterion("platform_auth_pic >", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicGreaterThanOrEqualTo(String value) {
            addCriterion("platform_auth_pic >=", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicLessThan(String value) {
            addCriterion("platform_auth_pic <", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicLessThanOrEqualTo(String value) {
            addCriterion("platform_auth_pic <=", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicLike(String value) {
            addCriterion("platform_auth_pic like", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicNotLike(String value) {
            addCriterion("platform_auth_pic not like", value, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicIn(List<String> values) {
            addCriterion("platform_auth_pic in", values, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicNotIn(List<String> values) {
            addCriterion("platform_auth_pic not in", values, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicBetween(String value1, String value2) {
            addCriterion("platform_auth_pic between", value1, value2, "platformAuthPic");
            return (Criteria) this;
        }

        public Criteria andPlatformAuthPicNotBetween(String value1, String value2) {
            addCriterion("platform_auth_pic not between", value1, value2, "platformAuthPic");
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

        public Criteria andIsActivityIsNull() {
            addCriterion("is_activity is null");
            return (Criteria) this;
        }

        public Criteria andIsActivityIsNotNull() {
            addCriterion("is_activity is not null");
            return (Criteria) this;
        }

        public Criteria andIsActivityEqualTo(String value) {
            addCriterion("is_activity =", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotEqualTo(String value) {
            addCriterion("is_activity <>", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityGreaterThan(String value) {
            addCriterion("is_activity >", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityGreaterThanOrEqualTo(String value) {
            addCriterion("is_activity >=", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityLessThan(String value) {
            addCriterion("is_activity <", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityLessThanOrEqualTo(String value) {
            addCriterion("is_activity <=", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityLike(String value) {
            addCriterion("is_activity like", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotLike(String value) {
            addCriterion("is_activity not like", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityIn(List<String> values) {
            addCriterion("is_activity in", values, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotIn(List<String> values) {
            addCriterion("is_activity not in", values, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityBetween(String value1, String value2) {
            addCriterion("is_activity between", value1, value2, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotBetween(String value1, String value2) {
            addCriterion("is_activity not between", value1, value2, "isActivity");
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

        public Criteria andIsSpeciallyApprovedIsNull() {
            addCriterion("is_specially_approved is null");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedIsNotNull() {
            addCriterion("is_specially_approved is not null");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedEqualTo(String value) {
            addCriterion("is_specially_approved =", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedNotEqualTo(String value) {
            addCriterion("is_specially_approved <>", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedGreaterThan(String value) {
            addCriterion("is_specially_approved >", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedGreaterThanOrEqualTo(String value) {
            addCriterion("is_specially_approved >=", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedLessThan(String value) {
            addCriterion("is_specially_approved <", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedLessThanOrEqualTo(String value) {
            addCriterion("is_specially_approved <=", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedLike(String value) {
            addCriterion("is_specially_approved like", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedNotLike(String value) {
            addCriterion("is_specially_approved not like", value, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedIn(List<String> values) {
            addCriterion("is_specially_approved in", values, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedNotIn(List<String> values) {
            addCriterion("is_specially_approved not in", values, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedBetween(String value1, String value2) {
            addCriterion("is_specially_approved between", value1, value2, "isSpeciallyApproved");
            return (Criteria) this;
        }

        public Criteria andIsSpeciallyApprovedNotBetween(String value1, String value2) {
            addCriterion("is_specially_approved not between", value1, value2, "isSpeciallyApproved");
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

        public Criteria andSpeciallyApprovedSourceIsNull() {
            addCriterion("specially_approved_source is null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceIsNotNull() {
            addCriterion("specially_approved_source is not null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceEqualTo(String value) {
            addCriterion("specially_approved_source =", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceNotEqualTo(String value) {
            addCriterion("specially_approved_source <>", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceGreaterThan(String value) {
            addCriterion("specially_approved_source >", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceGreaterThanOrEqualTo(String value) {
            addCriterion("specially_approved_source >=", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceLessThan(String value) {
            addCriterion("specially_approved_source <", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceLessThanOrEqualTo(String value) {
            addCriterion("specially_approved_source <=", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceLike(String value) {
            addCriterion("specially_approved_source like", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceNotLike(String value) {
            addCriterion("specially_approved_source not like", value, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceIn(List<String> values) {
            addCriterion("specially_approved_source in", values, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceNotIn(List<String> values) {
            addCriterion("specially_approved_source not in", values, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceBetween(String value1, String value2) {
            addCriterion("specially_approved_source between", value1, value2, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedSourceNotBetween(String value1, String value2) {
            addCriterion("specially_approved_source not between", value1, value2, "speciallyApprovedSource");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateIsNull() {
            addCriterion("specially_approved_date is null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateIsNotNull() {
            addCriterion("specially_approved_date is not null");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateEqualTo(Date value) {
            addCriterion("specially_approved_date =", value, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateNotEqualTo(Date value) {
            addCriterion("specially_approved_date <>", value, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateGreaterThan(Date value) {
            addCriterion("specially_approved_date >", value, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("specially_approved_date >=", value, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateLessThan(Date value) {
            addCriterion("specially_approved_date <", value, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateLessThanOrEqualTo(Date value) {
            addCriterion("specially_approved_date <=", value, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateIn(List<Date> values) {
            addCriterion("specially_approved_date in", values, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateNotIn(List<Date> values) {
            addCriterion("specially_approved_date not in", values, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateBetween(Date value1, Date value2) {
            addCriterion("specially_approved_date between", value1, value2, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andSpeciallyApprovedDateNotBetween(Date value1, Date value2) {
            addCriterion("specially_approved_date not between", value1, value2, "speciallyApprovedDate");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksIsNull() {
            addCriterion("status_remarks is null");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksIsNotNull() {
            addCriterion("status_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksEqualTo(String value) {
            addCriterion("status_remarks =", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksNotEqualTo(String value) {
            addCriterion("status_remarks <>", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksGreaterThan(String value) {
            addCriterion("status_remarks >", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("status_remarks >=", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksLessThan(String value) {
            addCriterion("status_remarks <", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksLessThanOrEqualTo(String value) {
            addCriterion("status_remarks <=", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksLike(String value) {
            addCriterion("status_remarks like", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksNotLike(String value) {
            addCriterion("status_remarks not like", value, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksIn(List<String> values) {
            addCriterion("status_remarks in", values, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksNotIn(List<String> values) {
            addCriterion("status_remarks not in", values, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksBetween(String value1, String value2) {
            addCriterion("status_remarks between", value1, value2, "statusRemarks");
            return (Criteria) this;
        }

        public Criteria andStatusRemarksNotBetween(String value1, String value2) {
            addCriterion("status_remarks not between", value1, value2, "statusRemarks");
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

        public Criteria andStatusByIsNull() {
            addCriterion("status_by is null");
            return (Criteria) this;
        }

        public Criteria andStatusByIsNotNull() {
            addCriterion("status_by is not null");
            return (Criteria) this;
        }

        public Criteria andStatusByEqualTo(Integer value) {
            addCriterion("status_by =", value, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByNotEqualTo(Integer value) {
            addCriterion("status_by <>", value, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByGreaterThan(Integer value) {
            addCriterion("status_by >", value, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_by >=", value, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByLessThan(Integer value) {
            addCriterion("status_by <", value, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByLessThanOrEqualTo(Integer value) {
            addCriterion("status_by <=", value, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByIn(List<Integer> values) {
            addCriterion("status_by in", values, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByNotIn(List<Integer> values) {
            addCriterion("status_by not in", values, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByBetween(Integer value1, Integer value2) {
            addCriterion("status_by between", value1, value2, "statusBy");
            return (Criteria) this;
        }

        public Criteria andStatusByNotBetween(Integer value1, Integer value2) {
            addCriterion("status_by not between", value1, value2, "statusBy");
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

        public Criteria andZsAuditRemarksIsNull() {
            addCriterion("zs_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksIsNotNull() {
            addCriterion("zs_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksEqualTo(String value) {
            addCriterion("zs_audit_remarks =", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotEqualTo(String value) {
            addCriterion("zs_audit_remarks <>", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksGreaterThan(String value) {
            addCriterion("zs_audit_remarks >", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("zs_audit_remarks >=", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksLessThan(String value) {
            addCriterion("zs_audit_remarks <", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("zs_audit_remarks <=", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksLike(String value) {
            addCriterion("zs_audit_remarks like", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotLike(String value) {
            addCriterion("zs_audit_remarks not like", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksIn(List<String> values) {
            addCriterion("zs_audit_remarks in", values, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotIn(List<String> values) {
            addCriterion("zs_audit_remarks not in", values, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksBetween(String value1, String value2) {
            addCriterion("zs_audit_remarks between", value1, value2, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("zs_audit_remarks not between", value1, value2, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksIsNull() {
            addCriterion("zs_audit_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksIsNotNull() {
            addCriterion("zs_audit_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksEqualTo(String value) {
            addCriterion("zs_audit_inner_remarks =", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksNotEqualTo(String value) {
            addCriterion("zs_audit_inner_remarks <>", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksGreaterThan(String value) {
            addCriterion("zs_audit_inner_remarks >", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("zs_audit_inner_remarks >=", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksLessThan(String value) {
            addCriterion("zs_audit_inner_remarks <", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("zs_audit_inner_remarks <=", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksLike(String value) {
            addCriterion("zs_audit_inner_remarks like", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksNotLike(String value) {
            addCriterion("zs_audit_inner_remarks not like", value, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksIn(List<String> values) {
            addCriterion("zs_audit_inner_remarks in", values, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksNotIn(List<String> values) {
            addCriterion("zs_audit_inner_remarks not in", values, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksBetween(String value1, String value2) {
            addCriterion("zs_audit_inner_remarks between", value1, value2, "zsAuditInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("zs_audit_inner_remarks not between", value1, value2, "zsAuditInnerRemarks");
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

        public Criteria andZsAuditDateIsNull() {
            addCriterion("zs_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateIsNotNull() {
            addCriterion("zs_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateEqualTo(Date value) {
            addCriterion("zs_audit_date =", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateNotEqualTo(Date value) {
            addCriterion("zs_audit_date <>", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateGreaterThan(Date value) {
            addCriterion("zs_audit_date >", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("zs_audit_date >=", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateLessThan(Date value) {
            addCriterion("zs_audit_date <", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("zs_audit_date <=", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateIn(List<Date> values) {
            addCriterion("zs_audit_date in", values, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateNotIn(List<Date> values) {
            addCriterion("zs_audit_date not in", values, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateBetween(Date value1, Date value2) {
            addCriterion("zs_audit_date between", value1, value2, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("zs_audit_date not between", value1, value2, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitIsNull() {
            addCriterion("is_zs_audit_recommit is null");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitIsNotNull() {
            addCriterion("is_zs_audit_recommit is not null");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitEqualTo(String value) {
            addCriterion("is_zs_audit_recommit =", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitNotEqualTo(String value) {
            addCriterion("is_zs_audit_recommit <>", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitGreaterThan(String value) {
            addCriterion("is_zs_audit_recommit >", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitGreaterThanOrEqualTo(String value) {
            addCriterion("is_zs_audit_recommit >=", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitLessThan(String value) {
            addCriterion("is_zs_audit_recommit <", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitLessThanOrEqualTo(String value) {
            addCriterion("is_zs_audit_recommit <=", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitLike(String value) {
            addCriterion("is_zs_audit_recommit like", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitNotLike(String value) {
            addCriterion("is_zs_audit_recommit not like", value, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitIn(List<String> values) {
            addCriterion("is_zs_audit_recommit in", values, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitNotIn(List<String> values) {
            addCriterion("is_zs_audit_recommit not in", values, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitBetween(String value1, String value2) {
            addCriterion("is_zs_audit_recommit between", value1, value2, "isZsAuditRecommit");
            return (Criteria) this;
        }

        public Criteria andIsZsAuditRecommitNotBetween(String value1, String value2) {
            addCriterion("is_zs_audit_recommit not between", value1, value2, "isZsAuditRecommit");
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

        public Criteria andAuditByIsNull() {
            addCriterion("audit_by is null");
            return (Criteria) this;
        }

        public Criteria andAuditByIsNotNull() {
            addCriterion("audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andAuditByEqualTo(Integer value) {
            addCriterion("audit_by =", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByNotEqualTo(Integer value) {
            addCriterion("audit_by <>", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByGreaterThan(Integer value) {
            addCriterion("audit_by >", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_by >=", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByLessThan(Integer value) {
            addCriterion("audit_by <", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("audit_by <=", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByIn(List<Integer> values) {
            addCriterion("audit_by in", values, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByNotIn(List<Integer> values) {
            addCriterion("audit_by not in", values, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByBetween(Integer value1, Integer value2) {
            addCriterion("audit_by between", value1, value2, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_by not between", value1, value2, "auditBy");
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

        public Criteria andAuditDateIsNull() {
            addCriterion("audit_date is null");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNotNull() {
            addCriterion("audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDateEqualTo(Date value) {
            addCriterion("audit_date =", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotEqualTo(Date value) {
            addCriterion("audit_date <>", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThan(Date value) {
            addCriterion("audit_date >", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_date >=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThan(Date value) {
            addCriterion("audit_date <", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("audit_date <=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateIn(List<Date> values) {
            addCriterion("audit_date in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotIn(List<Date> values) {
            addCriterion("audit_date not in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateBetween(Date value1, Date value2) {
            addCriterion("audit_date between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("audit_date not between", value1, value2, "auditDate");
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

        public Criteria andBrandSourceIsNull() {
            addCriterion("brand_source is null");
            return (Criteria) this;
        }

        public Criteria andBrandSourceIsNotNull() {
            addCriterion("brand_source is not null");
            return (Criteria) this;
        }

        public Criteria andBrandSourceEqualTo(String value) {
            addCriterion("brand_source =", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceNotEqualTo(String value) {
            addCriterion("brand_source <>", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceGreaterThan(String value) {
            addCriterion("brand_source >", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceGreaterThanOrEqualTo(String value) {
            addCriterion("brand_source >=", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceLessThan(String value) {
            addCriterion("brand_source <", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceLessThanOrEqualTo(String value) {
            addCriterion("brand_source <=", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceLike(String value) {
            addCriterion("brand_source like", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceNotLike(String value) {
            addCriterion("brand_source not like", value, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceIn(List<String> values) {
            addCriterion("brand_source in", values, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceNotIn(List<String> values) {
            addCriterion("brand_source not in", values, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceBetween(String value1, String value2) {
            addCriterion("brand_source between", value1, value2, "brandSource");
            return (Criteria) this;
        }

        public Criteria andBrandSourceNotBetween(String value1, String value2) {
            addCriterion("brand_source not between", value1, value2, "brandSource");
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