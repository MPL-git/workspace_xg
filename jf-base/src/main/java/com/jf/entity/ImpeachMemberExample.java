package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImpeachMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ImpeachMemberExample() {
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

        public Criteria andMemberIdsIsNull() {
            addCriterion("member_ids is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdsIsNotNull() {
            addCriterion("member_ids is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdsEqualTo(String value) {
            addCriterion("member_ids =", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotEqualTo(String value) {
            addCriterion("member_ids <>", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsGreaterThan(String value) {
            addCriterion("member_ids >", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsGreaterThanOrEqualTo(String value) {
            addCriterion("member_ids >=", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsLessThan(String value) {
            addCriterion("member_ids <", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsLessThanOrEqualTo(String value) {
            addCriterion("member_ids <=", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsLike(String value) {
            addCriterion("member_ids like", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotLike(String value) {
            addCriterion("member_ids not like", value, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsIn(List<String> values) {
            addCriterion("member_ids in", values, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotIn(List<String> values) {
            addCriterion("member_ids not in", values, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsBetween(String value1, String value2) {
            addCriterion("member_ids between", value1, value2, "memberIds");
            return (Criteria) this;
        }

        public Criteria andMemberIdsNotBetween(String value1, String value2) {
            addCriterion("member_ids not between", value1, value2, "memberIds");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNull() {
            addCriterion("comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(Integer value) {
            addCriterion("comment_id =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(Integer value) {
            addCriterion("comment_id <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(Integer value) {
            addCriterion("comment_id >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_id >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(Integer value) {
            addCriterion("comment_id <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_id <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<Integer> values) {
            addCriterion("comment_id in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<Integer> values) {
            addCriterion("comment_id not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_id between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_id not between", value1, value2, "commentId");
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSceneIsNull() {
            addCriterion("scene is null");
            return (Criteria) this;
        }

        public Criteria andSceneIsNotNull() {
            addCriterion("scene is not null");
            return (Criteria) this;
        }

        public Criteria andSceneEqualTo(String value) {
            addCriterion("scene =", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotEqualTo(String value) {
            addCriterion("scene <>", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThan(String value) {
            addCriterion("scene >", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneGreaterThanOrEqualTo(String value) {
            addCriterion("scene >=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThan(String value) {
            addCriterion("scene <", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLessThanOrEqualTo(String value) {
            addCriterion("scene <=", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneLike(String value) {
            addCriterion("scene like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotLike(String value) {
            addCriterion("scene not like", value, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneIn(List<String> values) {
            addCriterion("scene in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotIn(List<String> values) {
            addCriterion("scene not in", values, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneBetween(String value1, String value2) {
            addCriterion("scene between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andSceneNotBetween(String value1, String value2) {
            addCriterion("scene not between", value1, value2, "scene");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsIsNull() {
            addCriterion("sub_order_ids is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsIsNotNull() {
            addCriterion("sub_order_ids is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsEqualTo(String value) {
            addCriterion("sub_order_ids =", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsNotEqualTo(String value) {
            addCriterion("sub_order_ids <>", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsGreaterThan(String value) {
            addCriterion("sub_order_ids >", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsGreaterThanOrEqualTo(String value) {
            addCriterion("sub_order_ids >=", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsLessThan(String value) {
            addCriterion("sub_order_ids <", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsLessThanOrEqualTo(String value) {
            addCriterion("sub_order_ids <=", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsLike(String value) {
            addCriterion("sub_order_ids like", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsNotLike(String value) {
            addCriterion("sub_order_ids not like", value, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsIn(List<String> values) {
            addCriterion("sub_order_ids in", values, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsNotIn(List<String> values) {
            addCriterion("sub_order_ids not in", values, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsBetween(String value1, String value2) {
            addCriterion("sub_order_ids between", value1, value2, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdsNotBetween(String value1, String value2) {
            addCriterion("sub_order_ids not between", value1, value2, "subOrderIds");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andNeedLimitIsNull() {
            addCriterion("need_limit is null");
            return (Criteria) this;
        }

        public Criteria andNeedLimitIsNotNull() {
            addCriterion("need_limit is not null");
            return (Criteria) this;
        }

        public Criteria andNeedLimitEqualTo(String value) {
            addCriterion("need_limit =", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitNotEqualTo(String value) {
            addCriterion("need_limit <>", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitGreaterThan(String value) {
            addCriterion("need_limit >", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitGreaterThanOrEqualTo(String value) {
            addCriterion("need_limit >=", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitLessThan(String value) {
            addCriterion("need_limit <", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitLessThanOrEqualTo(String value) {
            addCriterion("need_limit <=", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitLike(String value) {
            addCriterion("need_limit like", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitNotLike(String value) {
            addCriterion("need_limit not like", value, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitIn(List<String> values) {
            addCriterion("need_limit in", values, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitNotIn(List<String> values) {
            addCriterion("need_limit not in", values, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitBetween(String value1, String value2) {
            addCriterion("need_limit between", value1, value2, "needLimit");
            return (Criteria) this;
        }

        public Criteria andNeedLimitNotBetween(String value1, String value2) {
            addCriterion("need_limit not between", value1, value2, "needLimit");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionIsNull() {
            addCriterion("limit_member_action is null");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionIsNotNull() {
            addCriterion("limit_member_action is not null");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionEqualTo(String value) {
            addCriterion("limit_member_action =", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionNotEqualTo(String value) {
            addCriterion("limit_member_action <>", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionGreaterThan(String value) {
            addCriterion("limit_member_action >", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionGreaterThanOrEqualTo(String value) {
            addCriterion("limit_member_action >=", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionLessThan(String value) {
            addCriterion("limit_member_action <", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionLessThanOrEqualTo(String value) {
            addCriterion("limit_member_action <=", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionLike(String value) {
            addCriterion("limit_member_action like", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionNotLike(String value) {
            addCriterion("limit_member_action not like", value, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionIn(List<String> values) {
            addCriterion("limit_member_action in", values, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionNotIn(List<String> values) {
            addCriterion("limit_member_action not in", values, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionBetween(String value1, String value2) {
            addCriterion("limit_member_action between", value1, value2, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andLimitMemberActionNotBetween(String value1, String value2) {
            addCriterion("limit_member_action not between", value1, value2, "limitMemberAction");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIsNull() {
            addCriterion("reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIsNotNull() {
            addCriterion("reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRejectReasonEqualTo(String value) {
            addCriterion("reject_reason =", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotEqualTo(String value) {
            addCriterion("reject_reason <>", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonGreaterThan(String value) {
            addCriterion("reject_reason >", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reject_reason >=", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLessThan(String value) {
            addCriterion("reject_reason <", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("reject_reason <=", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLike(String value) {
            addCriterion("reject_reason like", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotLike(String value) {
            addCriterion("reject_reason not like", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIn(List<String> values) {
            addCriterion("reject_reason in", values, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotIn(List<String> values) {
            addCriterion("reject_reason not in", values, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonBetween(String value1, String value2) {
            addCriterion("reject_reason between", value1, value2, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotBetween(String value1, String value2) {
            addCriterion("reject_reason not between", value1, value2, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescIsNull() {
            addCriterion("case_close_desc is null");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescIsNotNull() {
            addCriterion("case_close_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescEqualTo(String value) {
            addCriterion("case_close_desc =", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescNotEqualTo(String value) {
            addCriterion("case_close_desc <>", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescGreaterThan(String value) {
            addCriterion("case_close_desc >", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescGreaterThanOrEqualTo(String value) {
            addCriterion("case_close_desc >=", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescLessThan(String value) {
            addCriterion("case_close_desc <", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescLessThanOrEqualTo(String value) {
            addCriterion("case_close_desc <=", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescLike(String value) {
            addCriterion("case_close_desc like", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescNotLike(String value) {
            addCriterion("case_close_desc not like", value, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescIn(List<String> values) {
            addCriterion("case_close_desc in", values, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescNotIn(List<String> values) {
            addCriterion("case_close_desc not in", values, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescBetween(String value1, String value2) {
            addCriterion("case_close_desc between", value1, value2, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseCloseDescNotBetween(String value1, String value2) {
            addCriterion("case_close_desc not between", value1, value2, "caseCloseDesc");
            return (Criteria) this;
        }

        public Criteria andReceiverDateIsNull() {
            addCriterion("receiver_date is null");
            return (Criteria) this;
        }

        public Criteria andReceiverDateIsNotNull() {
            addCriterion("receiver_date is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverDateEqualTo(Date value) {
            addCriterion("receiver_date =", value, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateNotEqualTo(Date value) {
            addCriterion("receiver_date <>", value, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateGreaterThan(Date value) {
            addCriterion("receiver_date >", value, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateGreaterThanOrEqualTo(Date value) {
            addCriterion("receiver_date >=", value, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateLessThan(Date value) {
            addCriterion("receiver_date <", value, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateLessThanOrEqualTo(Date value) {
            addCriterion("receiver_date <=", value, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateIn(List<Date> values) {
            addCriterion("receiver_date in", values, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateNotIn(List<Date> values) {
            addCriterion("receiver_date not in", values, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateBetween(Date value1, Date value2) {
            addCriterion("receiver_date between", value1, value2, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andReceiverDateNotBetween(Date value1, Date value2) {
            addCriterion("receiver_date not between", value1, value2, "receiverDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksIsNull() {
            addCriterion("commissioner_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksIsNotNull() {
            addCriterion("commissioner_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksEqualTo(String value) {
            addCriterion("commissioner_inner_remarks =", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksNotEqualTo(String value) {
            addCriterion("commissioner_inner_remarks <>", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksGreaterThan(String value) {
            addCriterion("commissioner_inner_remarks >", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("commissioner_inner_remarks >=", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksLessThan(String value) {
            addCriterion("commissioner_inner_remarks <", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("commissioner_inner_remarks <=", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksLike(String value) {
            addCriterion("commissioner_inner_remarks like", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksNotLike(String value) {
            addCriterion("commissioner_inner_remarks not like", value, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksIn(List<String> values) {
            addCriterion("commissioner_inner_remarks in", values, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksNotIn(List<String> values) {
            addCriterion("commissioner_inner_remarks not in", values, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksBetween(String value1, String value2) {
            addCriterion("commissioner_inner_remarks between", value1, value2, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("commissioner_inner_remarks not between", value1, value2, "commissionerInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByIsNull() {
            addCriterion("commissioner_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByIsNotNull() {
            addCriterion("commissioner_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByEqualTo(Integer value) {
            addCriterion("commissioner_audit_by =", value, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByNotEqualTo(Integer value) {
            addCriterion("commissioner_audit_by <>", value, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByGreaterThan(Integer value) {
            addCriterion("commissioner_audit_by >", value, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("commissioner_audit_by >=", value, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByLessThan(Integer value) {
            addCriterion("commissioner_audit_by <", value, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("commissioner_audit_by <=", value, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByIn(List<Integer> values) {
            addCriterion("commissioner_audit_by in", values, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByNotIn(List<Integer> values) {
            addCriterion("commissioner_audit_by not in", values, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByBetween(Integer value1, Integer value2) {
            addCriterion("commissioner_audit_by between", value1, value2, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("commissioner_audit_by not between", value1, value2, "commissionerAuditBy");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateIsNull() {
            addCriterion("commissioner_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateIsNotNull() {
            addCriterion("commissioner_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateEqualTo(Date value) {
            addCriterion("commissioner_audit_date =", value, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateNotEqualTo(Date value) {
            addCriterion("commissioner_audit_date <>", value, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateGreaterThan(Date value) {
            addCriterion("commissioner_audit_date >", value, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commissioner_audit_date >=", value, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateLessThan(Date value) {
            addCriterion("commissioner_audit_date <", value, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("commissioner_audit_date <=", value, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateIn(List<Date> values) {
            addCriterion("commissioner_audit_date in", values, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateNotIn(List<Date> values) {
            addCriterion("commissioner_audit_date not in", values, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateBetween(Date value1, Date value2) {
            addCriterion("commissioner_audit_date between", value1, value2, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andCommissionerAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("commissioner_audit_date not between", value1, value2, "commissionerAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksIsNull() {
            addCriterion("fw_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksIsNotNull() {
            addCriterion("fw_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksEqualTo(String value) {
            addCriterion("fw_inner_remarks =", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksNotEqualTo(String value) {
            addCriterion("fw_inner_remarks <>", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksGreaterThan(String value) {
            addCriterion("fw_inner_remarks >", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("fw_inner_remarks >=", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksLessThan(String value) {
            addCriterion("fw_inner_remarks <", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("fw_inner_remarks <=", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksLike(String value) {
            addCriterion("fw_inner_remarks like", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksNotLike(String value) {
            addCriterion("fw_inner_remarks not like", value, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksIn(List<String> values) {
            addCriterion("fw_inner_remarks in", values, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksNotIn(List<String> values) {
            addCriterion("fw_inner_remarks not in", values, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksBetween(String value1, String value2) {
            addCriterion("fw_inner_remarks between", value1, value2, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("fw_inner_remarks not between", value1, value2, "fwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonIsNull() {
            addCriterion("fw_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonIsNotNull() {
            addCriterion("fw_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonEqualTo(String value) {
            addCriterion("fw_reject_reason =", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonNotEqualTo(String value) {
            addCriterion("fw_reject_reason <>", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonGreaterThan(String value) {
            addCriterion("fw_reject_reason >", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("fw_reject_reason >=", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonLessThan(String value) {
            addCriterion("fw_reject_reason <", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("fw_reject_reason <=", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonLike(String value) {
            addCriterion("fw_reject_reason like", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonNotLike(String value) {
            addCriterion("fw_reject_reason not like", value, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonIn(List<String> values) {
            addCriterion("fw_reject_reason in", values, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonNotIn(List<String> values) {
            addCriterion("fw_reject_reason not in", values, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonBetween(String value1, String value2) {
            addCriterion("fw_reject_reason between", value1, value2, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwRejectReasonNotBetween(String value1, String value2) {
            addCriterion("fw_reject_reason not between", value1, value2, "fwRejectReason");
            return (Criteria) this;
        }

        public Criteria andFwAuditByIsNull() {
            addCriterion("fw_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andFwAuditByIsNotNull() {
            addCriterion("fw_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andFwAuditByEqualTo(Integer value) {
            addCriterion("fw_audit_by =", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByNotEqualTo(Integer value) {
            addCriterion("fw_audit_by <>", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByGreaterThan(Integer value) {
            addCriterion("fw_audit_by >", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("fw_audit_by >=", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByLessThan(Integer value) {
            addCriterion("fw_audit_by <", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("fw_audit_by <=", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByIn(List<Integer> values) {
            addCriterion("fw_audit_by in", values, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByNotIn(List<Integer> values) {
            addCriterion("fw_audit_by not in", values, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByBetween(Integer value1, Integer value2) {
            addCriterion("fw_audit_by between", value1, value2, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("fw_audit_by not between", value1, value2, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateIsNull() {
            addCriterion("fw_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateIsNotNull() {
            addCriterion("fw_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateEqualTo(Date value) {
            addCriterion("fw_audit_date =", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateNotEqualTo(Date value) {
            addCriterion("fw_audit_date <>", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateGreaterThan(Date value) {
            addCriterion("fw_audit_date >", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("fw_audit_date >=", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateLessThan(Date value) {
            addCriterion("fw_audit_date <", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("fw_audit_date <=", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateIn(List<Date> values) {
            addCriterion("fw_audit_date in", values, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateNotIn(List<Date> values) {
            addCriterion("fw_audit_date not in", values, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateBetween(Date value1, Date value2) {
            addCriterion("fw_audit_date between", value1, value2, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("fw_audit_date not between", value1, value2, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksIsNull() {
            addCriterion("end_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksIsNotNull() {
            addCriterion("end_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksEqualTo(String value) {
            addCriterion("end_inner_remarks =", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksNotEqualTo(String value) {
            addCriterion("end_inner_remarks <>", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksGreaterThan(String value) {
            addCriterion("end_inner_remarks >", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("end_inner_remarks >=", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksLessThan(String value) {
            addCriterion("end_inner_remarks <", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("end_inner_remarks <=", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksLike(String value) {
            addCriterion("end_inner_remarks like", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksNotLike(String value) {
            addCriterion("end_inner_remarks not like", value, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksIn(List<String> values) {
            addCriterion("end_inner_remarks in", values, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksNotIn(List<String> values) {
            addCriterion("end_inner_remarks not in", values, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksBetween(String value1, String value2) {
            addCriterion("end_inner_remarks between", value1, value2, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("end_inner_remarks not between", value1, value2, "endInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonIsNull() {
            addCriterion("end_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonIsNotNull() {
            addCriterion("end_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonEqualTo(String value) {
            addCriterion("end_reject_reason =", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonNotEqualTo(String value) {
            addCriterion("end_reject_reason <>", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonGreaterThan(String value) {
            addCriterion("end_reject_reason >", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("end_reject_reason >=", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonLessThan(String value) {
            addCriterion("end_reject_reason <", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("end_reject_reason <=", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonLike(String value) {
            addCriterion("end_reject_reason like", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonNotLike(String value) {
            addCriterion("end_reject_reason not like", value, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonIn(List<String> values) {
            addCriterion("end_reject_reason in", values, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonNotIn(List<String> values) {
            addCriterion("end_reject_reason not in", values, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonBetween(String value1, String value2) {
            addCriterion("end_reject_reason between", value1, value2, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndRejectReasonNotBetween(String value1, String value2) {
            addCriterion("end_reject_reason not between", value1, value2, "endRejectReason");
            return (Criteria) this;
        }

        public Criteria andEndAuditByIsNull() {
            addCriterion("end_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andEndAuditByIsNotNull() {
            addCriterion("end_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andEndAuditByEqualTo(Integer value) {
            addCriterion("end_audit_by =", value, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByNotEqualTo(Integer value) {
            addCriterion("end_audit_by <>", value, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByGreaterThan(Integer value) {
            addCriterion("end_audit_by >", value, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_audit_by >=", value, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByLessThan(Integer value) {
            addCriterion("end_audit_by <", value, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("end_audit_by <=", value, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByIn(List<Integer> values) {
            addCriterion("end_audit_by in", values, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByNotIn(List<Integer> values) {
            addCriterion("end_audit_by not in", values, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByBetween(Integer value1, Integer value2) {
            addCriterion("end_audit_by between", value1, value2, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("end_audit_by not between", value1, value2, "endAuditBy");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateIsNull() {
            addCriterion("end_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateIsNotNull() {
            addCriterion("end_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateEqualTo(Date value) {
            addCriterion("end_audit_date =", value, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateNotEqualTo(Date value) {
            addCriterion("end_audit_date <>", value, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateGreaterThan(Date value) {
            addCriterion("end_audit_date >", value, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_audit_date >=", value, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateLessThan(Date value) {
            addCriterion("end_audit_date <", value, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("end_audit_date <=", value, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateIn(List<Date> values) {
            addCriterion("end_audit_date in", values, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateNotIn(List<Date> values) {
            addCriterion("end_audit_date not in", values, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateBetween(Date value1, Date value2) {
            addCriterion("end_audit_date between", value1, value2, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andEndAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("end_audit_date not between", value1, value2, "endAuditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateIsNull() {
            addCriterion("last_edit_date is null");
            return (Criteria) this;
        }

        public Criteria andLastEditDateIsNotNull() {
            addCriterion("last_edit_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditDateEqualTo(Date value) {
            addCriterion("last_edit_date =", value, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateNotEqualTo(Date value) {
            addCriterion("last_edit_date <>", value, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateGreaterThan(Date value) {
            addCriterion("last_edit_date >", value, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_edit_date >=", value, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateLessThan(Date value) {
            addCriterion("last_edit_date <", value, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateLessThanOrEqualTo(Date value) {
            addCriterion("last_edit_date <=", value, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateIn(List<Date> values) {
            addCriterion("last_edit_date in", values, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateNotIn(List<Date> values) {
            addCriterion("last_edit_date not in", values, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateBetween(Date value1, Date value2) {
            addCriterion("last_edit_date between", value1, value2, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andLastEditDateNotBetween(Date value1, Date value2) {
            addCriterion("last_edit_date not between", value1, value2, "lastEditDate");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
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