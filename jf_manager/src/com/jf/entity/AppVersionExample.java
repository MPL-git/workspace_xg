package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppVersionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public AppVersionExample() {
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

        public Criteria andAppVersionIsNull() {
            addCriterion("app_version is null");
            return (Criteria) this;
        }

        public Criteria andAppVersionIsNotNull() {
            addCriterion("app_version is not null");
            return (Criteria) this;
        }

        public Criteria andAppVersionEqualTo(Integer value) {
            addCriterion("app_version =", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotEqualTo(Integer value) {
            addCriterion("app_version <>", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionGreaterThan(Integer value) {
            addCriterion("app_version >", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_version >=", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLessThan(Integer value) {
            addCriterion("app_version <", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLessThanOrEqualTo(Integer value) {
            addCriterion("app_version <=", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionIn(List<Integer> values) {
            addCriterion("app_version in", values, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotIn(List<Integer> values) {
            addCriterion("app_version not in", values, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionBetween(Integer value1, Integer value2) {
            addCriterion("app_version between", value1, value2, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("app_version not between", value1, value2, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoIsNull() {
            addCriterion("app_version_no is null");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoIsNotNull() {
            addCriterion("app_version_no is not null");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoEqualTo(String value) {
            addCriterion("app_version_no =", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoNotEqualTo(String value) {
            addCriterion("app_version_no <>", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoGreaterThan(String value) {
            addCriterion("app_version_no >", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoGreaterThanOrEqualTo(String value) {
            addCriterion("app_version_no >=", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoLessThan(String value) {
            addCriterion("app_version_no <", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoLessThanOrEqualTo(String value) {
            addCriterion("app_version_no <=", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoLike(String value) {
            addCriterion("app_version_no like", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoNotLike(String value) {
            addCriterion("app_version_no not like", value, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoIn(List<String> values) {
            addCriterion("app_version_no in", values, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoNotIn(List<String> values) {
            addCriterion("app_version_no not in", values, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoBetween(String value1, String value2) {
            addCriterion("app_version_no between", value1, value2, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andAppVersionNoNotBetween(String value1, String value2) {
            addCriterion("app_version_no not between", value1, value2, "appVersionNo");
            return (Criteria) this;
        }

        public Criteria andTargetVersionIsNull() {
            addCriterion("target_version is null");
            return (Criteria) this;
        }

        public Criteria andTargetVersionIsNotNull() {
            addCriterion("target_version is not null");
            return (Criteria) this;
        }

        public Criteria andTargetVersionEqualTo(Integer value) {
            addCriterion("target_version =", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionNotEqualTo(Integer value) {
            addCriterion("target_version <>", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionGreaterThan(Integer value) {
            addCriterion("target_version >", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_version >=", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionLessThan(Integer value) {
            addCriterion("target_version <", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionLessThanOrEqualTo(Integer value) {
            addCriterion("target_version <=", value, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionIn(List<Integer> values) {
            addCriterion("target_version in", values, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionNotIn(List<Integer> values) {
            addCriterion("target_version not in", values, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionBetween(Integer value1, Integer value2) {
            addCriterion("target_version between", value1, value2, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andTargetVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("target_version not between", value1, value2, "targetVersion");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNull() {
            addCriterion("app_type is null");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNotNull() {
            addCriterion("app_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppTypeEqualTo(String value) {
            addCriterion("app_type =", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotEqualTo(String value) {
            addCriterion("app_type <>", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThan(String value) {
            addCriterion("app_type >", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThanOrEqualTo(String value) {
            addCriterion("app_type >=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThan(String value) {
            addCriterion("app_type <", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThanOrEqualTo(String value) {
            addCriterion("app_type <=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLike(String value) {
            addCriterion("app_type like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotLike(String value) {
            addCriterion("app_type not like", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeIn(List<String> values) {
            addCriterion("app_type in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotIn(List<String> values) {
            addCriterion("app_type not in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeBetween(String value1, String value2) {
            addCriterion("app_type between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotBetween(String value1, String value2) {
            addCriterion("app_type not between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andSprChnlIsNull() {
            addCriterion("spr_chnl is null");
            return (Criteria) this;
        }

        public Criteria andSprChnlIsNotNull() {
            addCriterion("spr_chnl is not null");
            return (Criteria) this;
        }

        public Criteria andSprChnlEqualTo(String value) {
            addCriterion("spr_chnl =", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotEqualTo(String value) {
            addCriterion("spr_chnl <>", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlGreaterThan(String value) {
            addCriterion("spr_chnl >", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlGreaterThanOrEqualTo(String value) {
            addCriterion("spr_chnl >=", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlLessThan(String value) {
            addCriterion("spr_chnl <", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlLessThanOrEqualTo(String value) {
            addCriterion("spr_chnl <=", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlLike(String value) {
            addCriterion("spr_chnl like", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotLike(String value) {
            addCriterion("spr_chnl not like", value, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlIn(List<String> values) {
            addCriterion("spr_chnl in", values, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotIn(List<String> values) {
            addCriterion("spr_chnl not in", values, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlBetween(String value1, String value2) {
            addCriterion("spr_chnl between", value1, value2, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andSprChnlNotBetween(String value1, String value2) {
            addCriterion("spr_chnl not between", value1, value2, "sprChnl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlIsNull() {
            addCriterion("package_url is null");
            return (Criteria) this;
        }

        public Criteria andPackageUrlIsNotNull() {
            addCriterion("package_url is not null");
            return (Criteria) this;
        }

        public Criteria andPackageUrlEqualTo(String value) {
            addCriterion("package_url =", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlNotEqualTo(String value) {
            addCriterion("package_url <>", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlGreaterThan(String value) {
            addCriterion("package_url >", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("package_url >=", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlLessThan(String value) {
            addCriterion("package_url <", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlLessThanOrEqualTo(String value) {
            addCriterion("package_url <=", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlLike(String value) {
            addCriterion("package_url like", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlNotLike(String value) {
            addCriterion("package_url not like", value, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlIn(List<String> values) {
            addCriterion("package_url in", values, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlNotIn(List<String> values) {
            addCriterion("package_url not in", values, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlBetween(String value1, String value2) {
            addCriterion("package_url between", value1, value2, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageUrlNotBetween(String value1, String value2) {
            addCriterion("package_url not between", value1, value2, "packageUrl");
            return (Criteria) this;
        }

        public Criteria andPackageSizeIsNull() {
            addCriterion("package_size is null");
            return (Criteria) this;
        }

        public Criteria andPackageSizeIsNotNull() {
            addCriterion("package_size is not null");
            return (Criteria) this;
        }

        public Criteria andPackageSizeEqualTo(Integer value) {
            addCriterion("package_size =", value, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeNotEqualTo(Integer value) {
            addCriterion("package_size <>", value, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeGreaterThan(Integer value) {
            addCriterion("package_size >", value, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("package_size >=", value, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeLessThan(Integer value) {
            addCriterion("package_size <", value, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeLessThanOrEqualTo(Integer value) {
            addCriterion("package_size <=", value, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeIn(List<Integer> values) {
            addCriterion("package_size in", values, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeNotIn(List<Integer> values) {
            addCriterion("package_size not in", values, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeBetween(Integer value1, Integer value2) {
            addCriterion("package_size between", value1, value2, "packageSize");
            return (Criteria) this;
        }

        public Criteria andPackageSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("package_size not between", value1, value2, "packageSize");
            return (Criteria) this;
        }

        public Criteria andIsEffectIsNull() {
            addCriterion("is_effect is null");
            return (Criteria) this;
        }

        public Criteria andIsEffectIsNotNull() {
            addCriterion("is_effect is not null");
            return (Criteria) this;
        }

        public Criteria andIsEffectEqualTo(String value) {
            addCriterion("is_effect =", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotEqualTo(String value) {
            addCriterion("is_effect <>", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThan(String value) {
            addCriterion("is_effect >", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThanOrEqualTo(String value) {
            addCriterion("is_effect >=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThan(String value) {
            addCriterion("is_effect <", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThanOrEqualTo(String value) {
            addCriterion("is_effect <=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLike(String value) {
            addCriterion("is_effect like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotLike(String value) {
            addCriterion("is_effect not like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectIn(List<String> values) {
            addCriterion("is_effect in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotIn(List<String> values) {
            addCriterion("is_effect not in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectBetween(String value1, String value2) {
            addCriterion("is_effect between", value1, value2, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotBetween(String value1, String value2) {
            addCriterion("is_effect not between", value1, value2, "isEffect");
            return (Criteria) this;
        }

        public Criteria andLaunchContentIsNull() {
            addCriterion("launch_content is null");
            return (Criteria) this;
        }

        public Criteria andLaunchContentIsNotNull() {
            addCriterion("launch_content is not null");
            return (Criteria) this;
        }

        public Criteria andLaunchContentEqualTo(String value) {
            addCriterion("launch_content =", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentNotEqualTo(String value) {
            addCriterion("launch_content <>", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentGreaterThan(String value) {
            addCriterion("launch_content >", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentGreaterThanOrEqualTo(String value) {
            addCriterion("launch_content >=", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentLessThan(String value) {
            addCriterion("launch_content <", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentLessThanOrEqualTo(String value) {
            addCriterion("launch_content <=", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentLike(String value) {
            addCriterion("launch_content like", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentNotLike(String value) {
            addCriterion("launch_content not like", value, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentIn(List<String> values) {
            addCriterion("launch_content in", values, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentNotIn(List<String> values) {
            addCriterion("launch_content not in", values, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentBetween(String value1, String value2) {
            addCriterion("launch_content between", value1, value2, "launchContent");
            return (Criteria) this;
        }

        public Criteria andLaunchContentNotBetween(String value1, String value2) {
            addCriterion("launch_content not between", value1, value2, "launchContent");
            return (Criteria) this;
        }

        public Criteria andIsMustIsNull() {
            addCriterion("is_must is null");
            return (Criteria) this;
        }

        public Criteria andIsMustIsNotNull() {
            addCriterion("is_must is not null");
            return (Criteria) this;
        }

        public Criteria andIsMustEqualTo(String value) {
            addCriterion("is_must =", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustNotEqualTo(String value) {
            addCriterion("is_must <>", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustGreaterThan(String value) {
            addCriterion("is_must >", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustGreaterThanOrEqualTo(String value) {
            addCriterion("is_must >=", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustLessThan(String value) {
            addCriterion("is_must <", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustLessThanOrEqualTo(String value) {
            addCriterion("is_must <=", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustLike(String value) {
            addCriterion("is_must like", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustNotLike(String value) {
            addCriterion("is_must not like", value, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustIn(List<String> values) {
            addCriterion("is_must in", values, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustNotIn(List<String> values) {
            addCriterion("is_must not in", values, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustBetween(String value1, String value2) {
            addCriterion("is_must between", value1, value2, "isMust");
            return (Criteria) this;
        }

        public Criteria andIsMustNotBetween(String value1, String value2) {
            addCriterion("is_must not between", value1, value2, "isMust");
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