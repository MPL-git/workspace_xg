package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModuleMapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ModuleMapExample() {
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

        public Criteria andDecorateModuleIdIsNull() {
            addCriterion("decorate_module_id is null");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdIsNotNull() {
            addCriterion("decorate_module_id is not null");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdEqualTo(Integer value) {
            addCriterion("decorate_module_id =", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdNotEqualTo(Integer value) {
            addCriterion("decorate_module_id <>", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdGreaterThan(Integer value) {
            addCriterion("decorate_module_id >", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("decorate_module_id >=", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdLessThan(Integer value) {
            addCriterion("decorate_module_id <", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("decorate_module_id <=", value, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdIn(List<Integer> values) {
            addCriterion("decorate_module_id in", values, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdNotIn(List<Integer> values) {
            addCriterion("decorate_module_id not in", values, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdBetween(Integer value1, Integer value2) {
            addCriterion("decorate_module_id between", value1, value2, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andDecorateModuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("decorate_module_id not between", value1, value2, "decorateModuleId");
            return (Criteria) this;
        }

        public Criteria andCoordsIsNull() {
            addCriterion("coords is null");
            return (Criteria) this;
        }

        public Criteria andCoordsIsNotNull() {
            addCriterion("coords is not null");
            return (Criteria) this;
        }

        public Criteria andCoordsEqualTo(String value) {
            addCriterion("coords =", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsNotEqualTo(String value) {
            addCriterion("coords <>", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsGreaterThan(String value) {
            addCriterion("coords >", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsGreaterThanOrEqualTo(String value) {
            addCriterion("coords >=", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsLessThan(String value) {
            addCriterion("coords <", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsLessThanOrEqualTo(String value) {
            addCriterion("coords <=", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsLike(String value) {
            addCriterion("coords like", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsNotLike(String value) {
            addCriterion("coords not like", value, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsIn(List<String> values) {
            addCriterion("coords in", values, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsNotIn(List<String> values) {
            addCriterion("coords not in", values, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsBetween(String value1, String value2) {
            addCriterion("coords between", value1, value2, "coords");
            return (Criteria) this;
        }

        public Criteria andCoordsNotBetween(String value1, String value2) {
            addCriterion("coords not between", value1, value2, "coords");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIsNull() {
            addCriterion("link_type is null");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIsNotNull() {
            addCriterion("link_type is not null");
            return (Criteria) this;
        }

        public Criteria andLinkTypeEqualTo(String value) {
            addCriterion("link_type =", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotEqualTo(String value) {
            addCriterion("link_type <>", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeGreaterThan(String value) {
            addCriterion("link_type >", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("link_type >=", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLessThan(String value) {
            addCriterion("link_type <", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLessThanOrEqualTo(String value) {
            addCriterion("link_type <=", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLike(String value) {
            addCriterion("link_type like", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotLike(String value) {
            addCriterion("link_type not like", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIn(List<String> values) {
            addCriterion("link_type in", values, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotIn(List<String> values) {
            addCriterion("link_type not in", values, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeBetween(String value1, String value2) {
            addCriterion("link_type between", value1, value2, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotBetween(String value1, String value2) {
            addCriterion("link_type not between", value1, value2, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkValueIsNull() {
            addCriterion("link_value is null");
            return (Criteria) this;
        }

        public Criteria andLinkValueIsNotNull() {
            addCriterion("link_value is not null");
            return (Criteria) this;
        }

        public Criteria andLinkValueEqualTo(Integer value) {
            addCriterion("link_value =", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotEqualTo(Integer value) {
            addCriterion("link_value <>", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueGreaterThan(Integer value) {
            addCriterion("link_value >", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_value >=", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLessThan(Integer value) {
            addCriterion("link_value <", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLessThanOrEqualTo(Integer value) {
            addCriterion("link_value <=", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueIn(List<Integer> values) {
            addCriterion("link_value in", values, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotIn(List<Integer> values) {
            addCriterion("link_value not in", values, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueBetween(Integer value1, Integer value2) {
            addCriterion("link_value between", value1, value2, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotBetween(Integer value1, Integer value2) {
            addCriterion("link_value not between", value1, value2, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNull() {
            addCriterion("link_url is null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIsNotNull() {
            addCriterion("link_url is not null");
            return (Criteria) this;
        }

        public Criteria andLinkUrlEqualTo(String value) {
            addCriterion("link_url =", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotEqualTo(String value) {
            addCriterion("link_url <>", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThan(String value) {
            addCriterion("link_url >", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("link_url >=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThan(String value) {
            addCriterion("link_url <", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLessThanOrEqualTo(String value) {
            addCriterion("link_url <=", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlLike(String value) {
            addCriterion("link_url like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotLike(String value) {
            addCriterion("link_url not like", value, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlIn(List<String> values) {
            addCriterion("link_url in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotIn(List<String> values) {
            addCriterion("link_url not in", values, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlBetween(String value1, String value2) {
            addCriterion("link_url between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andLinkUrlNotBetween(String value1, String value2) {
            addCriterion("link_url not between", value1, value2, "linkUrl");
            return (Criteria) this;
        }

        public Criteria andFontColorIsNull() {
            addCriterion("font_color is null");
            return (Criteria) this;
        }

        public Criteria andFontColorIsNotNull() {
            addCriterion("font_color is not null");
            return (Criteria) this;
        }

        public Criteria andFontColorEqualTo(String value) {
            addCriterion("font_color =", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotEqualTo(String value) {
            addCriterion("font_color <>", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorGreaterThan(String value) {
            addCriterion("font_color >", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("font_color >=", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorLessThan(String value) {
            addCriterion("font_color <", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorLessThanOrEqualTo(String value) {
            addCriterion("font_color <=", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorLike(String value) {
            addCriterion("font_color like", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotLike(String value) {
            addCriterion("font_color not like", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorIn(List<String> values) {
            addCriterion("font_color in", values, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotIn(List<String> values) {
            addCriterion("font_color not in", values, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorBetween(String value1, String value2) {
            addCriterion("font_color between", value1, value2, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotBetween(String value1, String value2) {
            addCriterion("font_color not between", value1, value2, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontSizeIsNull() {
            addCriterion("font_size is null");
            return (Criteria) this;
        }

        public Criteria andFontSizeIsNotNull() {
            addCriterion("font_size is not null");
            return (Criteria) this;
        }

        public Criteria andFontSizeEqualTo(Integer value) {
            addCriterion("font_size =", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeNotEqualTo(Integer value) {
            addCriterion("font_size <>", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeGreaterThan(Integer value) {
            addCriterion("font_size >", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("font_size >=", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeLessThan(Integer value) {
            addCriterion("font_size <", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeLessThanOrEqualTo(Integer value) {
            addCriterion("font_size <=", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeIn(List<Integer> values) {
            addCriterion("font_size in", values, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeNotIn(List<Integer> values) {
            addCriterion("font_size not in", values, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeBetween(Integer value1, Integer value2) {
            addCriterion("font_size between", value1, value2, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("font_size not between", value1, value2, "fontSize");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateIsNull() {
            addCriterion("count_down_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateIsNotNull() {
            addCriterion("count_down_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateEqualTo(Date value) {
            addCriterion("count_down_begin_date =", value, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateNotEqualTo(Date value) {
            addCriterion("count_down_begin_date <>", value, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateGreaterThan(Date value) {
            addCriterion("count_down_begin_date >", value, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("count_down_begin_date >=", value, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateLessThan(Date value) {
            addCriterion("count_down_begin_date <", value, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("count_down_begin_date <=", value, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateIn(List<Date> values) {
            addCriterion("count_down_begin_date in", values, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateNotIn(List<Date> values) {
            addCriterion("count_down_begin_date not in", values, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateBetween(Date value1, Date value2) {
            addCriterion("count_down_begin_date between", value1, value2, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("count_down_begin_date not between", value1, value2, "countDownBeginDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateIsNull() {
            addCriterion("count_down_end_date is null");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateIsNotNull() {
            addCriterion("count_down_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateEqualTo(Date value) {
            addCriterion("count_down_end_date =", value, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateNotEqualTo(Date value) {
            addCriterion("count_down_end_date <>", value, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateGreaterThan(Date value) {
            addCriterion("count_down_end_date >", value, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("count_down_end_date >=", value, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateLessThan(Date value) {
            addCriterion("count_down_end_date <", value, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateLessThanOrEqualTo(Date value) {
            addCriterion("count_down_end_date <=", value, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateIn(List<Date> values) {
            addCriterion("count_down_end_date in", values, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateNotIn(List<Date> values) {
            addCriterion("count_down_end_date not in", values, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateBetween(Date value1, Date value2) {
            addCriterion("count_down_end_date between", value1, value2, "countDownEndDate");
            return (Criteria) this;
        }

        public Criteria andCountDownEndDateNotBetween(Date value1, Date value2) {
            addCriterion("count_down_end_date not between", value1, value2, "countDownEndDate");
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