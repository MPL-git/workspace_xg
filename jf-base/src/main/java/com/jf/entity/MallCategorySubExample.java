package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MallCategorySubExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MallCategorySubExample() {
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

        public Criteria andProductType1IsNull() {
            addCriterion("product_type1 is null");
            return (Criteria) this;
        }

        public Criteria andProductType1IsNotNull() {
            addCriterion("product_type1 is not null");
            return (Criteria) this;
        }

        public Criteria andProductType1EqualTo(Integer value) {
            addCriterion("product_type1 =", value, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1NotEqualTo(Integer value) {
            addCriterion("product_type1 <>", value, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1GreaterThan(Integer value) {
            addCriterion("product_type1 >", value, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1GreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type1 >=", value, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1LessThan(Integer value) {
            addCriterion("product_type1 <", value, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1LessThanOrEqualTo(Integer value) {
            addCriterion("product_type1 <=", value, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1In(List<Integer> values) {
            addCriterion("product_type1 in", values, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1NotIn(List<Integer> values) {
            addCriterion("product_type1 not in", values, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1Between(Integer value1, Integer value2) {
            addCriterion("product_type1 between", value1, value2, "productType1");
            return (Criteria) this;
        }

        public Criteria andProductType1NotBetween(Integer value1, Integer value2) {
            addCriterion("product_type1 not between", value1, value2, "productType1");
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

        public Criteria andProductType3IdsIsNull() {
            addCriterion("product_type3_ids is null");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsIsNotNull() {
            addCriterion("product_type3_ids is not null");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsEqualTo(String value) {
            addCriterion("product_type3_ids =", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsNotEqualTo(String value) {
            addCriterion("product_type3_ids <>", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsGreaterThan(String value) {
            addCriterion("product_type3_ids >", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsGreaterThanOrEqualTo(String value) {
            addCriterion("product_type3_ids >=", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsLessThan(String value) {
            addCriterion("product_type3_ids <", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsLessThanOrEqualTo(String value) {
            addCriterion("product_type3_ids <=", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsLike(String value) {
            addCriterion("product_type3_ids like", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsNotLike(String value) {
            addCriterion("product_type3_ids not like", value, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsIn(List<String> values) {
            addCriterion("product_type3_ids in", values, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsNotIn(List<String> values) {
            addCriterion("product_type3_ids not in", values, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsBetween(String value1, String value2) {
            addCriterion("product_type3_ids between", value1, value2, "productType3Ids");
            return (Criteria) this;
        }

        public Criteria andProductType3IdsNotBetween(String value1, String value2) {
            addCriterion("product_type3_ids not between", value1, value2, "productType3Ids");
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

        public Criteria andDecorateInfoIdIsNull() {
            addCriterion("decorate_info_id is null");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdIsNotNull() {
            addCriterion("decorate_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdEqualTo(Integer value) {
            addCriterion("decorate_info_id =", value, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdNotEqualTo(Integer value) {
            addCriterion("decorate_info_id <>", value, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdGreaterThan(Integer value) {
            addCriterion("decorate_info_id >", value, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("decorate_info_id >=", value, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdLessThan(Integer value) {
            addCriterion("decorate_info_id <", value, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("decorate_info_id <=", value, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdIn(List<Integer> values) {
            addCriterion("decorate_info_id in", values, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdNotIn(List<Integer> values) {
            addCriterion("decorate_info_id not in", values, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("decorate_info_id between", value1, value2, "decorateInfoId");
            return (Criteria) this;
        }

        public Criteria andDecorateInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("decorate_info_id not between", value1, value2, "decorateInfoId");
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