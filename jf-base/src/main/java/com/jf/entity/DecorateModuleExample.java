package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DecorateModuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public DecorateModuleExample() {
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

        public Criteria andDecorateAreaIdIsNull() {
            addCriterion("decorate_area_id is null");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdIsNotNull() {
            addCriterion("decorate_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdEqualTo(Integer value) {
            addCriterion("decorate_area_id =", value, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdNotEqualTo(Integer value) {
            addCriterion("decorate_area_id <>", value, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdGreaterThan(Integer value) {
            addCriterion("decorate_area_id >", value, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("decorate_area_id >=", value, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdLessThan(Integer value) {
            addCriterion("decorate_area_id <", value, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("decorate_area_id <=", value, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdIn(List<Integer> values) {
            addCriterion("decorate_area_id in", values, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdNotIn(List<Integer> values) {
            addCriterion("decorate_area_id not in", values, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("decorate_area_id between", value1, value2, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andDecorateAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("decorate_area_id not between", value1, value2, "decorateAreaId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIsNull() {
            addCriterion("module_type is null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIsNotNull() {
            addCriterion("module_type is not null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeEqualTo(String value) {
            addCriterion("module_type =", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotEqualTo(String value) {
            addCriterion("module_type <>", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeGreaterThan(String value) {
            addCriterion("module_type >", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("module_type >=", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLessThan(String value) {
            addCriterion("module_type <", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLessThanOrEqualTo(String value) {
            addCriterion("module_type <=", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLike(String value) {
            addCriterion("module_type like", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotLike(String value) {
            addCriterion("module_type not like", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIn(List<String> values) {
            addCriterion("module_type in", values, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotIn(List<String> values) {
            addCriterion("module_type not in", values, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeBetween(String value1, String value2) {
            addCriterion("module_type between", value1, value2, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotBetween(String value1, String value2) {
            addCriterion("module_type not between", value1, value2, "moduleType");
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

        public Criteria andShowNumIsNull() {
            addCriterion("show_num is null");
            return (Criteria) this;
        }

        public Criteria andShowNumIsNotNull() {
            addCriterion("show_num is not null");
            return (Criteria) this;
        }

        public Criteria andShowNumEqualTo(Integer value) {
            addCriterion("show_num =", value, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumNotEqualTo(Integer value) {
            addCriterion("show_num <>", value, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumGreaterThan(Integer value) {
            addCriterion("show_num >", value, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_num >=", value, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumLessThan(Integer value) {
            addCriterion("show_num <", value, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumLessThanOrEqualTo(Integer value) {
            addCriterion("show_num <=", value, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumIn(List<Integer> values) {
            addCriterion("show_num in", values, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumNotIn(List<Integer> values) {
            addCriterion("show_num not in", values, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumBetween(Integer value1, Integer value2) {
            addCriterion("show_num between", value1, value2, "showNum");
            return (Criteria) this;
        }

        public Criteria andShowNumNotBetween(Integer value1, Integer value2) {
            addCriterion("show_num not between", value1, value2, "showNum");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsIsNull() {
            addCriterion("product_type1_ids is null");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsIsNotNull() {
            addCriterion("product_type1_ids is not null");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsEqualTo(String value) {
            addCriterion("product_type1_ids =", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsNotEqualTo(String value) {
            addCriterion("product_type1_ids <>", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsGreaterThan(String value) {
            addCriterion("product_type1_ids >", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsGreaterThanOrEqualTo(String value) {
            addCriterion("product_type1_ids >=", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsLessThan(String value) {
            addCriterion("product_type1_ids <", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsLessThanOrEqualTo(String value) {
            addCriterion("product_type1_ids <=", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsLike(String value) {
            addCriterion("product_type1_ids like", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsNotLike(String value) {
            addCriterion("product_type1_ids not like", value, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsIn(List<String> values) {
            addCriterion("product_type1_ids in", values, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsNotIn(List<String> values) {
            addCriterion("product_type1_ids not in", values, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsBetween(String value1, String value2) {
            addCriterion("product_type1_ids between", value1, value2, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType1IdsNotBetween(String value1, String value2) {
            addCriterion("product_type1_ids not between", value1, value2, "productType1Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsIsNull() {
            addCriterion("product_type2_ids is null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsIsNotNull() {
            addCriterion("product_type2_ids is not null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsEqualTo(String value) {
            addCriterion("product_type2_ids =", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsNotEqualTo(String value) {
            addCriterion("product_type2_ids <>", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsGreaterThan(String value) {
            addCriterion("product_type2_ids >", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsGreaterThanOrEqualTo(String value) {
            addCriterion("product_type2_ids >=", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsLessThan(String value) {
            addCriterion("product_type2_ids <", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsLessThanOrEqualTo(String value) {
            addCriterion("product_type2_ids <=", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsLike(String value) {
            addCriterion("product_type2_ids like", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsNotLike(String value) {
            addCriterion("product_type2_ids not like", value, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsIn(List<String> values) {
            addCriterion("product_type2_ids in", values, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsNotIn(List<String> values) {
            addCriterion("product_type2_ids not in", values, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsBetween(String value1, String value2) {
            addCriterion("product_type2_ids between", value1, value2, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductType2IdsNotBetween(String value1, String value2) {
            addCriterion("product_type2_ids not between", value1, value2, "productType2Ids");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsIsNull() {
            addCriterion("product_brand_ids is null");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsIsNotNull() {
            addCriterion("product_brand_ids is not null");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsEqualTo(String value) {
            addCriterion("product_brand_ids =", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsNotEqualTo(String value) {
            addCriterion("product_brand_ids <>", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsGreaterThan(String value) {
            addCriterion("product_brand_ids >", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsGreaterThanOrEqualTo(String value) {
            addCriterion("product_brand_ids >=", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsLessThan(String value) {
            addCriterion("product_brand_ids <", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsLessThanOrEqualTo(String value) {
            addCriterion("product_brand_ids <=", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsLike(String value) {
            addCriterion("product_brand_ids like", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsNotLike(String value) {
            addCriterion("product_brand_ids not like", value, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsIn(List<String> values) {
            addCriterion("product_brand_ids in", values, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsNotIn(List<String> values) {
            addCriterion("product_brand_ids not in", values, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsBetween(String value1, String value2) {
            addCriterion("product_brand_ids between", value1, value2, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdsNotBetween(String value1, String value2) {
            addCriterion("product_brand_ids not between", value1, value2, "productBrandIds");
            return (Criteria) this;
        }

        public Criteria andBgColorIsNull() {
            addCriterion("bg_color is null");
            return (Criteria) this;
        }

        public Criteria andBgColorIsNotNull() {
            addCriterion("bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andBgColorEqualTo(String value) {
            addCriterion("bg_color =", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorNotEqualTo(String value) {
            addCriterion("bg_color <>", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorGreaterThan(String value) {
            addCriterion("bg_color >", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("bg_color >=", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorLessThan(String value) {
            addCriterion("bg_color <", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorLessThanOrEqualTo(String value) {
            addCriterion("bg_color <=", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorLike(String value) {
            addCriterion("bg_color like", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorNotLike(String value) {
            addCriterion("bg_color not like", value, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorIn(List<String> values) {
            addCriterion("bg_color in", values, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorNotIn(List<String> values) {
            addCriterion("bg_color not in", values, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorBetween(String value1, String value2) {
            addCriterion("bg_color between", value1, value2, "bgColor");
            return (Criteria) this;
        }

        public Criteria andBgColorNotBetween(String value1, String value2) {
            addCriterion("bg_color not between", value1, value2, "bgColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorIsNull() {
            addCriterion("field_font_color is null");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorIsNotNull() {
            addCriterion("field_font_color is not null");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorEqualTo(String value) {
            addCriterion("field_font_color =", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorNotEqualTo(String value) {
            addCriterion("field_font_color <>", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorGreaterThan(String value) {
            addCriterion("field_font_color >", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("field_font_color >=", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorLessThan(String value) {
            addCriterion("field_font_color <", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorLessThanOrEqualTo(String value) {
            addCriterion("field_font_color <=", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorLike(String value) {
            addCriterion("field_font_color like", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorNotLike(String value) {
            addCriterion("field_font_color not like", value, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorIn(List<String> values) {
            addCriterion("field_font_color in", values, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorNotIn(List<String> values) {
            addCriterion("field_font_color not in", values, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorBetween(String value1, String value2) {
            addCriterion("field_font_color between", value1, value2, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldFontColorNotBetween(String value1, String value2) {
            addCriterion("field_font_color not between", value1, value2, "fieldFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorIsNull() {
            addCriterion("field_select_font_color is null");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorIsNotNull() {
            addCriterion("field_select_font_color is not null");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorEqualTo(String value) {
            addCriterion("field_select_font_color =", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorNotEqualTo(String value) {
            addCriterion("field_select_font_color <>", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorGreaterThan(String value) {
            addCriterion("field_select_font_color >", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("field_select_font_color >=", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorLessThan(String value) {
            addCriterion("field_select_font_color <", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorLessThanOrEqualTo(String value) {
            addCriterion("field_select_font_color <=", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorLike(String value) {
            addCriterion("field_select_font_color like", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorNotLike(String value) {
            addCriterion("field_select_font_color not like", value, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorIn(List<String> values) {
            addCriterion("field_select_font_color in", values, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorNotIn(List<String> values) {
            addCriterion("field_select_font_color not in", values, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorBetween(String value1, String value2) {
            addCriterion("field_select_font_color between", value1, value2, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldSelectFontColorNotBetween(String value1, String value2) {
            addCriterion("field_select_font_color not between", value1, value2, "fieldSelectFontColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorIsNull() {
            addCriterion("field_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorIsNotNull() {
            addCriterion("field_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorEqualTo(String value) {
            addCriterion("field_bg_color =", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorNotEqualTo(String value) {
            addCriterion("field_bg_color <>", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorGreaterThan(String value) {
            addCriterion("field_bg_color >", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("field_bg_color >=", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorLessThan(String value) {
            addCriterion("field_bg_color <", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorLessThanOrEqualTo(String value) {
            addCriterion("field_bg_color <=", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorLike(String value) {
            addCriterion("field_bg_color like", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorNotLike(String value) {
            addCriterion("field_bg_color not like", value, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorIn(List<String> values) {
            addCriterion("field_bg_color in", values, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorNotIn(List<String> values) {
            addCriterion("field_bg_color not in", values, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorBetween(String value1, String value2) {
            addCriterion("field_bg_color between", value1, value2, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgColorNotBetween(String value1, String value2) {
            addCriterion("field_bg_color not between", value1, value2, "fieldBgColor");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicIsNull() {
            addCriterion("field_bg_pic is null");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicIsNotNull() {
            addCriterion("field_bg_pic is not null");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicEqualTo(String value) {
            addCriterion("field_bg_pic =", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicNotEqualTo(String value) {
            addCriterion("field_bg_pic <>", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicGreaterThan(String value) {
            addCriterion("field_bg_pic >", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicGreaterThanOrEqualTo(String value) {
            addCriterion("field_bg_pic >=", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicLessThan(String value) {
            addCriterion("field_bg_pic <", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicLessThanOrEqualTo(String value) {
            addCriterion("field_bg_pic <=", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicLike(String value) {
            addCriterion("field_bg_pic like", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicNotLike(String value) {
            addCriterion("field_bg_pic not like", value, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicIn(List<String> values) {
            addCriterion("field_bg_pic in", values, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicNotIn(List<String> values) {
            addCriterion("field_bg_pic not in", values, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicBetween(String value1, String value2) {
            addCriterion("field_bg_pic between", value1, value2, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andFieldBgPicNotBetween(String value1, String value2) {
            addCriterion("field_bg_pic not between", value1, value2, "fieldBgPic");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorIsNull() {
            addCriterion("open_font_color is null");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorIsNotNull() {
            addCriterion("open_font_color is not null");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorEqualTo(String value) {
            addCriterion("open_font_color =", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorNotEqualTo(String value) {
            addCriterion("open_font_color <>", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorGreaterThan(String value) {
            addCriterion("open_font_color >", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("open_font_color >=", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorLessThan(String value) {
            addCriterion("open_font_color <", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorLessThanOrEqualTo(String value) {
            addCriterion("open_font_color <=", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorLike(String value) {
            addCriterion("open_font_color like", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorNotLike(String value) {
            addCriterion("open_font_color not like", value, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorIn(List<String> values) {
            addCriterion("open_font_color in", values, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorNotIn(List<String> values) {
            addCriterion("open_font_color not in", values, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorBetween(String value1, String value2) {
            addCriterion("open_font_color between", value1, value2, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFontColorNotBetween(String value1, String value2) {
            addCriterion("open_font_color not between", value1, value2, "openFontColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorIsNull() {
            addCriterion("open_field_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorIsNotNull() {
            addCriterion("open_field_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorEqualTo(String value) {
            addCriterion("open_field_bg_color =", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorNotEqualTo(String value) {
            addCriterion("open_field_bg_color <>", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorGreaterThan(String value) {
            addCriterion("open_field_bg_color >", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("open_field_bg_color >=", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorLessThan(String value) {
            addCriterion("open_field_bg_color <", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorLessThanOrEqualTo(String value) {
            addCriterion("open_field_bg_color <=", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorLike(String value) {
            addCriterion("open_field_bg_color like", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorNotLike(String value) {
            addCriterion("open_field_bg_color not like", value, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorIn(List<String> values) {
            addCriterion("open_field_bg_color in", values, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorNotIn(List<String> values) {
            addCriterion("open_field_bg_color not in", values, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorBetween(String value1, String value2) {
            addCriterion("open_field_bg_color between", value1, value2, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenFieldBgColorNotBetween(String value1, String value2) {
            addCriterion("open_field_bg_color not between", value1, value2, "openFieldBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorIsNull() {
            addCriterion("open_btn_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorIsNotNull() {
            addCriterion("open_btn_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorEqualTo(String value) {
            addCriterion("open_btn_bg_color =", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorNotEqualTo(String value) {
            addCriterion("open_btn_bg_color <>", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorGreaterThan(String value) {
            addCriterion("open_btn_bg_color >", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("open_btn_bg_color >=", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorLessThan(String value) {
            addCriterion("open_btn_bg_color <", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorLessThanOrEqualTo(String value) {
            addCriterion("open_btn_bg_color <=", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorLike(String value) {
            addCriterion("open_btn_bg_color like", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorNotLike(String value) {
            addCriterion("open_btn_bg_color not like", value, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorIn(List<String> values) {
            addCriterion("open_btn_bg_color in", values, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorNotIn(List<String> values) {
            addCriterion("open_btn_bg_color not in", values, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorBetween(String value1, String value2) {
            addCriterion("open_btn_bg_color between", value1, value2, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnBgColorNotBetween(String value1, String value2) {
            addCriterion("open_btn_bg_color not between", value1, value2, "openBtnBgColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorIsNull() {
            addCriterion("open_btn_arrow_color is null");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorIsNotNull() {
            addCriterion("open_btn_arrow_color is not null");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorEqualTo(String value) {
            addCriterion("open_btn_arrow_color =", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorNotEqualTo(String value) {
            addCriterion("open_btn_arrow_color <>", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorGreaterThan(String value) {
            addCriterion("open_btn_arrow_color >", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorGreaterThanOrEqualTo(String value) {
            addCriterion("open_btn_arrow_color >=", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorLessThan(String value) {
            addCriterion("open_btn_arrow_color <", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorLessThanOrEqualTo(String value) {
            addCriterion("open_btn_arrow_color <=", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorLike(String value) {
            addCriterion("open_btn_arrow_color like", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorNotLike(String value) {
            addCriterion("open_btn_arrow_color not like", value, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorIn(List<String> values) {
            addCriterion("open_btn_arrow_color in", values, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorNotIn(List<String> values) {
            addCriterion("open_btn_arrow_color not in", values, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorBetween(String value1, String value2) {
            addCriterion("open_btn_arrow_color between", value1, value2, "openBtnArrowColor");
            return (Criteria) this;
        }

        public Criteria andOpenBtnArrowColorNotBetween(String value1, String value2) {
            addCriterion("open_btn_arrow_color not between", value1, value2, "openBtnArrowColor");
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

        public Criteria andVideoPathIsNull() {
            addCriterion("video_path is null");
            return (Criteria) this;
        }

        public Criteria andVideoPathIsNotNull() {
            addCriterion("video_path is not null");
            return (Criteria) this;
        }

        public Criteria andVideoPathEqualTo(String value) {
            addCriterion("video_path =", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathNotEqualTo(String value) {
            addCriterion("video_path <>", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathGreaterThan(String value) {
            addCriterion("video_path >", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathGreaterThanOrEqualTo(String value) {
            addCriterion("video_path >=", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathLessThan(String value) {
            addCriterion("video_path <", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathLessThanOrEqualTo(String value) {
            addCriterion("video_path <=", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathLike(String value) {
            addCriterion("video_path like", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathNotLike(String value) {
            addCriterion("video_path not like", value, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathIn(List<String> values) {
            addCriterion("video_path in", values, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathNotIn(List<String> values) {
            addCriterion("video_path not in", values, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathBetween(String value1, String value2) {
            addCriterion("video_path between", value1, value2, "videoPath");
            return (Criteria) this;
        }

        public Criteria andVideoPathNotBetween(String value1, String value2) {
            addCriterion("video_path not between", value1, value2, "videoPath");
            return (Criteria) this;
        }

        public Criteria andShowModelIsNull() {
            addCriterion("show_model is null");
            return (Criteria) this;
        }

        public Criteria andShowModelIsNotNull() {
            addCriterion("show_model is not null");
            return (Criteria) this;
        }

        public Criteria andShowModelEqualTo(Integer value) {
            addCriterion("show_model =", value, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelNotEqualTo(Integer value) {
            addCriterion("show_model <>", value, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelGreaterThan(Integer value) {
            addCriterion("show_model >", value, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_model >=", value, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelLessThan(Integer value) {
            addCriterion("show_model <", value, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelLessThanOrEqualTo(Integer value) {
            addCriterion("show_model <=", value, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelIn(List<Integer> values) {
            addCriterion("show_model in", values, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelNotIn(List<Integer> values) {
            addCriterion("show_model not in", values, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelBetween(Integer value1, Integer value2) {
            addCriterion("show_model between", value1, value2, "showModel");
            return (Criteria) this;
        }

        public Criteria andShowModelNotBetween(Integer value1, Integer value2) {
            addCriterion("show_model not between", value1, value2, "showModel");
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