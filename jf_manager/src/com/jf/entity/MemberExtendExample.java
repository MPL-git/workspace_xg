package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberExtendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberExtendExample() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andQqIdIsNull() {
            addCriterion("qq_id is null");
            return (Criteria) this;
        }

        public Criteria andQqIdIsNotNull() {
            addCriterion("qq_id is not null");
            return (Criteria) this;
        }

        public Criteria andQqIdEqualTo(String value) {
            addCriterion("qq_id =", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotEqualTo(String value) {
            addCriterion("qq_id <>", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdGreaterThan(String value) {
            addCriterion("qq_id >", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdGreaterThanOrEqualTo(String value) {
            addCriterion("qq_id >=", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdLessThan(String value) {
            addCriterion("qq_id <", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdLessThanOrEqualTo(String value) {
            addCriterion("qq_id <=", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdLike(String value) {
            addCriterion("qq_id like", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotLike(String value) {
            addCriterion("qq_id not like", value, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdIn(List<String> values) {
            addCriterion("qq_id in", values, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotIn(List<String> values) {
            addCriterion("qq_id not in", values, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdBetween(String value1, String value2) {
            addCriterion("qq_id between", value1, value2, "qqId");
            return (Criteria) this;
        }

        public Criteria andQqIdNotBetween(String value1, String value2) {
            addCriterion("qq_id not between", value1, value2, "qqId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIsNull() {
            addCriterion("weibo_id is null");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIsNotNull() {
            addCriterion("weibo_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboIdEqualTo(String value) {
            addCriterion("weibo_id =", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotEqualTo(String value) {
            addCriterion("weibo_id <>", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdGreaterThan(String value) {
            addCriterion("weibo_id >", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdGreaterThanOrEqualTo(String value) {
            addCriterion("weibo_id >=", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLessThan(String value) {
            addCriterion("weibo_id <", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLessThanOrEqualTo(String value) {
            addCriterion("weibo_id <=", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLike(String value) {
            addCriterion("weibo_id like", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotLike(String value) {
            addCriterion("weibo_id not like", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIn(List<String> values) {
            addCriterion("weibo_id in", values, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotIn(List<String> values) {
            addCriterion("weibo_id not in", values, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdBetween(String value1, String value2) {
            addCriterion("weibo_id between", value1, value2, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotBetween(String value1, String value2) {
            addCriterion("weibo_id not between", value1, value2, "weiboId");
            return (Criteria) this;
        }

        public Criteria andSignInRemindIsNull() {
            addCriterion("sign_in_remind is null");
            return (Criteria) this;
        }

        public Criteria andSignInRemindIsNotNull() {
            addCriterion("sign_in_remind is not null");
            return (Criteria) this;
        }

        public Criteria andSignInRemindEqualTo(String value) {
            addCriterion("sign_in_remind =", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindNotEqualTo(String value) {
            addCriterion("sign_in_remind <>", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindGreaterThan(String value) {
            addCriterion("sign_in_remind >", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindGreaterThanOrEqualTo(String value) {
            addCriterion("sign_in_remind >=", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindLessThan(String value) {
            addCriterion("sign_in_remind <", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindLessThanOrEqualTo(String value) {
            addCriterion("sign_in_remind <=", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindLike(String value) {
            addCriterion("sign_in_remind like", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindNotLike(String value) {
            addCriterion("sign_in_remind not like", value, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindIn(List<String> values) {
            addCriterion("sign_in_remind in", values, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindNotIn(List<String> values) {
            addCriterion("sign_in_remind not in", values, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindBetween(String value1, String value2) {
            addCriterion("sign_in_remind between", value1, value2, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andSignInRemindNotBetween(String value1, String value2) {
            addCriterion("sign_in_remind not between", value1, value2, "signInRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindIsNull() {
            addCriterion("new_user_500_coupon_remind is null");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindIsNotNull() {
            addCriterion("new_user_500_coupon_remind is not null");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindEqualTo(String value) {
            addCriterion("new_user_500_coupon_remind =", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindNotEqualTo(String value) {
            addCriterion("new_user_500_coupon_remind <>", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindGreaterThan(String value) {
            addCriterion("new_user_500_coupon_remind >", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindGreaterThanOrEqualTo(String value) {
            addCriterion("new_user_500_coupon_remind >=", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindLessThan(String value) {
            addCriterion("new_user_500_coupon_remind <", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindLessThanOrEqualTo(String value) {
            addCriterion("new_user_500_coupon_remind <=", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindLike(String value) {
            addCriterion("new_user_500_coupon_remind like", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindNotLike(String value) {
            addCriterion("new_user_500_coupon_remind not like", value, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindIn(List<String> values) {
            addCriterion("new_user_500_coupon_remind in", values, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindNotIn(List<String> values) {
            addCriterion("new_user_500_coupon_remind not in", values, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindBetween(String value1, String value2) {
            addCriterion("new_user_500_coupon_remind between", value1, value2, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andNewUser500CouponRemindNotBetween(String value1, String value2) {
            addCriterion("new_user_500_coupon_remind not between", value1, value2, "newUser500CouponRemind");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdIsNull() {
            addCriterion("zs_platform_contact_id is null");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdIsNotNull() {
            addCriterion("zs_platform_contact_id is not null");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdEqualTo(Integer value) {
            addCriterion("zs_platform_contact_id =", value, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdNotEqualTo(Integer value) {
            addCriterion("zs_platform_contact_id <>", value, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdGreaterThan(Integer value) {
            addCriterion("zs_platform_contact_id >", value, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("zs_platform_contact_id >=", value, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdLessThan(Integer value) {
            addCriterion("zs_platform_contact_id <", value, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdLessThanOrEqualTo(Integer value) {
            addCriterion("zs_platform_contact_id <=", value, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdIn(List<Integer> values) {
            addCriterion("zs_platform_contact_id in", values, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdNotIn(List<Integer> values) {
            addCriterion("zs_platform_contact_id not in", values, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdBetween(Integer value1, Integer value2) {
            addCriterion("zs_platform_contact_id between", value1, value2, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andZsPlatformContactIdNotBetween(Integer value1, Integer value2) {
            addCriterion("zs_platform_contact_id not between", value1, value2, "zsPlatformContactId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdIsNull() {
            addCriterion("mcht_settled_apply_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdIsNotNull() {
            addCriterion("mcht_settled_apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdEqualTo(Integer value) {
            addCriterion("mcht_settled_apply_id =", value, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdNotEqualTo(Integer value) {
            addCriterion("mcht_settled_apply_id <>", value, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdGreaterThan(Integer value) {
            addCriterion("mcht_settled_apply_id >", value, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_settled_apply_id >=", value, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdLessThan(Integer value) {
            addCriterion("mcht_settled_apply_id <", value, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_settled_apply_id <=", value, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdIn(List<Integer> values) {
            addCriterion("mcht_settled_apply_id in", values, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdNotIn(List<Integer> values) {
            addCriterion("mcht_settled_apply_id not in", values, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settled_apply_id between", value1, value2, "mchtSettledApplyId");
            return (Criteria) this;
        }

        public Criteria andMchtSettledApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settled_apply_id not between", value1, value2, "mchtSettledApplyId");
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