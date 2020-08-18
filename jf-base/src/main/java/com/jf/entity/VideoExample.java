package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public VideoExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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

        public Criteria andVideoUrlIsNull() {
            addCriterion("video_url is null");
            return (Criteria) this;
        }

        public Criteria andVideoUrlIsNotNull() {
            addCriterion("video_url is not null");
            return (Criteria) this;
        }

        public Criteria andVideoUrlEqualTo(String value) {
            addCriterion("video_url =", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotEqualTo(String value) {
            addCriterion("video_url <>", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlGreaterThan(String value) {
            addCriterion("video_url >", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("video_url >=", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLessThan(String value) {
            addCriterion("video_url <", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLessThanOrEqualTo(String value) {
            addCriterion("video_url <=", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLike(String value) {
            addCriterion("video_url like", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotLike(String value) {
            addCriterion("video_url not like", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlIn(List<String> values) {
            addCriterion("video_url in", values, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotIn(List<String> values) {
            addCriterion("video_url not in", values, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlBetween(String value1, String value2) {
            addCriterion("video_url between", value1, value2, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotBetween(String value1, String value2) {
            addCriterion("video_url not between", value1, value2, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeIsNull() {
            addCriterion("video_xcx_code is null");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeIsNotNull() {
            addCriterion("video_xcx_code is not null");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeEqualTo(String value) {
            addCriterion("video_xcx_code =", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeNotEqualTo(String value) {
            addCriterion("video_xcx_code <>", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeGreaterThan(String value) {
            addCriterion("video_xcx_code >", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeGreaterThanOrEqualTo(String value) {
            addCriterion("video_xcx_code >=", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeLessThan(String value) {
            addCriterion("video_xcx_code <", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeLessThanOrEqualTo(String value) {
            addCriterion("video_xcx_code <=", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeLike(String value) {
            addCriterion("video_xcx_code like", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeNotLike(String value) {
            addCriterion("video_xcx_code not like", value, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeIn(List<String> values) {
            addCriterion("video_xcx_code in", values, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeNotIn(List<String> values) {
            addCriterion("video_xcx_code not in", values, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeBetween(String value1, String value2) {
            addCriterion("video_xcx_code between", value1, value2, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoXcxCodeNotBetween(String value1, String value2) {
            addCriterion("video_xcx_code not between", value1, value2, "videoXcxCode");
            return (Criteria) this;
        }

        public Criteria andVideoCoverIsNull() {
            addCriterion("video_cover is null");
            return (Criteria) this;
        }

        public Criteria andVideoCoverIsNotNull() {
            addCriterion("video_cover is not null");
            return (Criteria) this;
        }

        public Criteria andVideoCoverEqualTo(String value) {
            addCriterion("video_cover =", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverNotEqualTo(String value) {
            addCriterion("video_cover <>", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverGreaterThan(String value) {
            addCriterion("video_cover >", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverGreaterThanOrEqualTo(String value) {
            addCriterion("video_cover >=", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverLessThan(String value) {
            addCriterion("video_cover <", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverLessThanOrEqualTo(String value) {
            addCriterion("video_cover <=", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverLike(String value) {
            addCriterion("video_cover like", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverNotLike(String value) {
            addCriterion("video_cover not like", value, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverIn(List<String> values) {
            addCriterion("video_cover in", values, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverNotIn(List<String> values) {
            addCriterion("video_cover not in", values, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverBetween(String value1, String value2) {
            addCriterion("video_cover between", value1, value2, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoCoverNotBetween(String value1, String value2) {
            addCriterion("video_cover not between", value1, value2, "videoCover");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsIsNull() {
            addCriterion("video_thumbnails is null");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsIsNotNull() {
            addCriterion("video_thumbnails is not null");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsEqualTo(String value) {
            addCriterion("video_thumbnails =", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsNotEqualTo(String value) {
            addCriterion("video_thumbnails <>", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsGreaterThan(String value) {
            addCriterion("video_thumbnails >", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsGreaterThanOrEqualTo(String value) {
            addCriterion("video_thumbnails >=", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsLessThan(String value) {
            addCriterion("video_thumbnails <", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsLessThanOrEqualTo(String value) {
            addCriterion("video_thumbnails <=", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsLike(String value) {
            addCriterion("video_thumbnails like", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsNotLike(String value) {
            addCriterion("video_thumbnails not like", value, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsIn(List<String> values) {
            addCriterion("video_thumbnails in", values, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsNotIn(List<String> values) {
            addCriterion("video_thumbnails not in", values, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsBetween(String value1, String value2) {
            addCriterion("video_thumbnails between", value1, value2, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoThumbnailsNotBetween(String value1, String value2) {
            addCriterion("video_thumbnails not between", value1, value2, "videoThumbnails");
            return (Criteria) this;
        }

        public Criteria andVideoTimeIsNull() {
            addCriterion("video_time is null");
            return (Criteria) this;
        }

        public Criteria andVideoTimeIsNotNull() {
            addCriterion("video_time is not null");
            return (Criteria) this;
        }

        public Criteria andVideoTimeEqualTo(Integer value) {
            addCriterion("video_time =", value, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeNotEqualTo(Integer value) {
            addCriterion("video_time <>", value, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeGreaterThan(Integer value) {
            addCriterion("video_time >", value, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_time >=", value, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeLessThan(Integer value) {
            addCriterion("video_time <", value, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeLessThanOrEqualTo(Integer value) {
            addCriterion("video_time <=", value, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeIn(List<Integer> values) {
            addCriterion("video_time in", values, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeNotIn(List<Integer> values) {
            addCriterion("video_time not in", values, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeBetween(Integer value1, Integer value2) {
            addCriterion("video_time between", value1, value2, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("video_time not between", value1, value2, "videoTime");
            return (Criteria) this;
        }

        public Criteria andVideoSizeIsNull() {
            addCriterion("video_size is null");
            return (Criteria) this;
        }

        public Criteria andVideoSizeIsNotNull() {
            addCriterion("video_size is not null");
            return (Criteria) this;
        }

        public Criteria andVideoSizeEqualTo(Integer value) {
            addCriterion("video_size =", value, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeNotEqualTo(Integer value) {
            addCriterion("video_size <>", value, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeGreaterThan(Integer value) {
            addCriterion("video_size >", value, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_size >=", value, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeLessThan(Integer value) {
            addCriterion("video_size <", value, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeLessThanOrEqualTo(Integer value) {
            addCriterion("video_size <=", value, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeIn(List<Integer> values) {
            addCriterion("video_size in", values, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeNotIn(List<Integer> values) {
            addCriterion("video_size not in", values, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeBetween(Integer value1, Integer value2) {
            addCriterion("video_size between", value1, value2, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("video_size not between", value1, value2, "videoSize");
            return (Criteria) this;
        }

        public Criteria andVideoWidthIsNull() {
            addCriterion("video_width is null");
            return (Criteria) this;
        }

        public Criteria andVideoWidthIsNotNull() {
            addCriterion("video_width is not null");
            return (Criteria) this;
        }

        public Criteria andVideoWidthEqualTo(Integer value) {
            addCriterion("video_width =", value, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthNotEqualTo(Integer value) {
            addCriterion("video_width <>", value, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthGreaterThan(Integer value) {
            addCriterion("video_width >", value, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_width >=", value, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthLessThan(Integer value) {
            addCriterion("video_width <", value, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthLessThanOrEqualTo(Integer value) {
            addCriterion("video_width <=", value, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthIn(List<Integer> values) {
            addCriterion("video_width in", values, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthNotIn(List<Integer> values) {
            addCriterion("video_width not in", values, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthBetween(Integer value1, Integer value2) {
            addCriterion("video_width between", value1, value2, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("video_width not between", value1, value2, "videoWidth");
            return (Criteria) this;
        }

        public Criteria andVideoHeightIsNull() {
            addCriterion("video_height is null");
            return (Criteria) this;
        }

        public Criteria andVideoHeightIsNotNull() {
            addCriterion("video_height is not null");
            return (Criteria) this;
        }

        public Criteria andVideoHeightEqualTo(Integer value) {
            addCriterion("video_height =", value, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightNotEqualTo(Integer value) {
            addCriterion("video_height <>", value, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightGreaterThan(Integer value) {
            addCriterion("video_height >", value, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_height >=", value, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightLessThan(Integer value) {
            addCriterion("video_height <", value, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightLessThanOrEqualTo(Integer value) {
            addCriterion("video_height <=", value, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightIn(List<Integer> values) {
            addCriterion("video_height in", values, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightNotIn(List<Integer> values) {
            addCriterion("video_height not in", values, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightBetween(Integer value1, Integer value2) {
            addCriterion("video_height between", value1, value2, "videoHeight");
            return (Criteria) this;
        }

        public Criteria andVideoHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("video_height not between", value1, value2, "videoHeight");
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

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkIsNull() {
            addCriterion("audit_remark is null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkIsNotNull() {
            addCriterion("audit_remark is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkEqualTo(String value) {
            addCriterion("audit_remark =", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkNotEqualTo(String value) {
            addCriterion("audit_remark <>", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkGreaterThan(String value) {
            addCriterion("audit_remark >", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("audit_remark >=", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkLessThan(String value) {
            addCriterion("audit_remark <", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkLessThanOrEqualTo(String value) {
            addCriterion("audit_remark <=", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkLike(String value) {
            addCriterion("audit_remark like", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkNotLike(String value) {
            addCriterion("audit_remark not like", value, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkIn(List<String> values) {
            addCriterion("audit_remark in", values, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkNotIn(List<String> values) {
            addCriterion("audit_remark not in", values, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkBetween(String value1, String value2) {
            addCriterion("audit_remark between", value1, value2, "auditRemark");
            return (Criteria) this;
        }

        public Criteria andAuditRemarkNotBetween(String value1, String value2) {
            addCriterion("audit_remark not between", value1, value2, "auditRemark");
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

        public Criteria andSeasonWeightIsNull() {
            addCriterion("season_weight is null");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightIsNotNull() {
            addCriterion("season_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightEqualTo(Integer value) {
            addCriterion("season_weight =", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightNotEqualTo(Integer value) {
            addCriterion("season_weight <>", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightGreaterThan(Integer value) {
            addCriterion("season_weight >", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("season_weight >=", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightLessThan(Integer value) {
            addCriterion("season_weight <", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightLessThanOrEqualTo(Integer value) {
            addCriterion("season_weight <=", value, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightIn(List<Integer> values) {
            addCriterion("season_weight in", values, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightNotIn(List<Integer> values) {
            addCriterion("season_weight not in", values, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightBetween(Integer value1, Integer value2) {
            addCriterion("season_weight between", value1, value2, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andSeasonWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("season_weight not between", value1, value2, "seasonWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightIsNull() {
            addCriterion("play_weight is null");
            return (Criteria) this;
        }

        public Criteria andPlayWeightIsNotNull() {
            addCriterion("play_weight is not null");
            return (Criteria) this;
        }

        public Criteria andPlayWeightEqualTo(Integer value) {
            addCriterion("play_weight =", value, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightNotEqualTo(Integer value) {
            addCriterion("play_weight <>", value, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightGreaterThan(Integer value) {
            addCriterion("play_weight >", value, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("play_weight >=", value, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightLessThan(Integer value) {
            addCriterion("play_weight <", value, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightLessThanOrEqualTo(Integer value) {
            addCriterion("play_weight <=", value, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightIn(List<Integer> values) {
            addCriterion("play_weight in", values, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightNotIn(List<Integer> values) {
            addCriterion("play_weight not in", values, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightBetween(Integer value1, Integer value2) {
            addCriterion("play_weight between", value1, value2, "playWeight");
            return (Criteria) this;
        }

        public Criteria andPlayWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("play_weight not between", value1, value2, "playWeight");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtIsNull() {
            addCriterion("like_weicht is null");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtIsNotNull() {
            addCriterion("like_weicht is not null");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtEqualTo(Integer value) {
            addCriterion("like_weicht =", value, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtNotEqualTo(Integer value) {
            addCriterion("like_weicht <>", value, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtGreaterThan(Integer value) {
            addCriterion("like_weicht >", value, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_weicht >=", value, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtLessThan(Integer value) {
            addCriterion("like_weicht <", value, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtLessThanOrEqualTo(Integer value) {
            addCriterion("like_weicht <=", value, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtIn(List<Integer> values) {
            addCriterion("like_weicht in", values, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtNotIn(List<Integer> values) {
            addCriterion("like_weicht not in", values, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtBetween(Integer value1, Integer value2) {
            addCriterion("like_weicht between", value1, value2, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andLikeWeichtNotBetween(Integer value1, Integer value2) {
            addCriterion("like_weicht not between", value1, value2, "likeWeicht");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIsNull() {
            addCriterion("comment_weight is null");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIsNotNull() {
            addCriterion("comment_weight is not null");
            return (Criteria) this;
        }

        public Criteria andCommentWeightEqualTo(Integer value) {
            addCriterion("comment_weight =", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotEqualTo(Integer value) {
            addCriterion("comment_weight <>", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightGreaterThan(Integer value) {
            addCriterion("comment_weight >", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_weight >=", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightLessThan(Integer value) {
            addCriterion("comment_weight <", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightLessThanOrEqualTo(Integer value) {
            addCriterion("comment_weight <=", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIn(List<Integer> values) {
            addCriterion("comment_weight in", values, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotIn(List<Integer> values) {
            addCriterion("comment_weight not in", values, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightBetween(Integer value1, Integer value2) {
            addCriterion("comment_weight between", value1, value2, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_weight not between", value1, value2, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightIsNull() {
            addCriterion("collect_weight is null");
            return (Criteria) this;
        }

        public Criteria andCollectWeightIsNotNull() {
            addCriterion("collect_weight is not null");
            return (Criteria) this;
        }

        public Criteria andCollectWeightEqualTo(Integer value) {
            addCriterion("collect_weight =", value, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightNotEqualTo(Integer value) {
            addCriterion("collect_weight <>", value, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightGreaterThan(Integer value) {
            addCriterion("collect_weight >", value, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_weight >=", value, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightLessThan(Integer value) {
            addCriterion("collect_weight <", value, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightLessThanOrEqualTo(Integer value) {
            addCriterion("collect_weight <=", value, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightIn(List<Integer> values) {
            addCriterion("collect_weight in", values, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightNotIn(List<Integer> values) {
            addCriterion("collect_weight not in", values, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightBetween(Integer value1, Integer value2) {
            addCriterion("collect_weight between", value1, value2, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andCollectWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_weight not between", value1, value2, "collectWeight");
            return (Criteria) this;
        }

        public Criteria andManualWeichtIsNull() {
            addCriterion("manual_weicht is null");
            return (Criteria) this;
        }

        public Criteria andManualWeichtIsNotNull() {
            addCriterion("manual_weicht is not null");
            return (Criteria) this;
        }

        public Criteria andManualWeichtEqualTo(Integer value) {
            addCriterion("manual_weicht =", value, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtNotEqualTo(Integer value) {
            addCriterion("manual_weicht <>", value, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtGreaterThan(Integer value) {
            addCriterion("manual_weicht >", value, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtGreaterThanOrEqualTo(Integer value) {
            addCriterion("manual_weicht >=", value, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtLessThan(Integer value) {
            addCriterion("manual_weicht <", value, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtLessThanOrEqualTo(Integer value) {
            addCriterion("manual_weicht <=", value, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtIn(List<Integer> values) {
            addCriterion("manual_weicht in", values, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtNotIn(List<Integer> values) {
            addCriterion("manual_weicht not in", values, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtBetween(Integer value1, Integer value2) {
            addCriterion("manual_weicht between", value1, value2, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andManualWeichtNotBetween(Integer value1, Integer value2) {
            addCriterion("manual_weicht not between", value1, value2, "manualWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtIsNull() {
            addCriterion("total_weicht is null");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtIsNotNull() {
            addCriterion("total_weicht is not null");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtEqualTo(Integer value) {
            addCriterion("total_weicht =", value, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtNotEqualTo(Integer value) {
            addCriterion("total_weicht <>", value, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtGreaterThan(Integer value) {
            addCriterion("total_weicht >", value, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_weicht >=", value, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtLessThan(Integer value) {
            addCriterion("total_weicht <", value, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtLessThanOrEqualTo(Integer value) {
            addCriterion("total_weicht <=", value, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtIn(List<Integer> values) {
            addCriterion("total_weicht in", values, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtNotIn(List<Integer> values) {
            addCriterion("total_weicht not in", values, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtBetween(Integer value1, Integer value2) {
            addCriterion("total_weicht between", value1, value2, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andTotalWeichtNotBetween(Integer value1, Integer value2) {
            addCriterion("total_weicht not between", value1, value2, "totalWeicht");
            return (Criteria) this;
        }

        public Criteria andWeightTimeIsNull() {
            addCriterion("weight_time is null");
            return (Criteria) this;
        }

        public Criteria andWeightTimeIsNotNull() {
            addCriterion("weight_time is not null");
            return (Criteria) this;
        }

        public Criteria andWeightTimeEqualTo(Date value) {
            addCriterion("weight_time =", value, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeNotEqualTo(Date value) {
            addCriterion("weight_time <>", value, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeGreaterThan(Date value) {
            addCriterion("weight_time >", value, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("weight_time >=", value, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeLessThan(Date value) {
            addCriterion("weight_time <", value, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeLessThanOrEqualTo(Date value) {
            addCriterion("weight_time <=", value, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeIn(List<Date> values) {
            addCriterion("weight_time in", values, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeNotIn(List<Date> values) {
            addCriterion("weight_time not in", values, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeBetween(Date value1, Date value2) {
            addCriterion("weight_time between", value1, value2, "weightTime");
            return (Criteria) this;
        }

        public Criteria andWeightTimeNotBetween(Date value1, Date value2) {
            addCriterion("weight_time not between", value1, value2, "weightTime");
            return (Criteria) this;
        }

        public Criteria andIsrecommendIsNull() {
            addCriterion("isRecommend is null");
            return (Criteria) this;
        }

        public Criteria andIsrecommendIsNotNull() {
            addCriterion("isRecommend is not null");
            return (Criteria) this;
        }

        public Criteria andIsrecommendEqualTo(String value) {
            addCriterion("isRecommend =", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendNotEqualTo(String value) {
            addCriterion("isRecommend <>", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendGreaterThan(String value) {
            addCriterion("isRecommend >", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendGreaterThanOrEqualTo(String value) {
            addCriterion("isRecommend >=", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendLessThan(String value) {
            addCriterion("isRecommend <", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendLessThanOrEqualTo(String value) {
            addCriterion("isRecommend <=", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendLike(String value) {
            addCriterion("isRecommend like", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendNotLike(String value) {
            addCriterion("isRecommend not like", value, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendIn(List<String> values) {
            addCriterion("isRecommend in", values, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendNotIn(List<String> values) {
            addCriterion("isRecommend not in", values, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendBetween(String value1, String value2) {
            addCriterion("isRecommend between", value1, value2, "isrecommend");
            return (Criteria) this;
        }

        public Criteria andIsrecommendNotBetween(String value1, String value2) {
            addCriterion("isRecommend not between", value1, value2, "isrecommend");
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

        public Criteria andVideoSourceIsNull() {
            addCriterion("video_source is null");
            return (Criteria) this;
        }

        public Criteria andVideoSourceIsNotNull() {
            addCriterion("video_source is not null");
            return (Criteria) this;
        }

        public Criteria andVideoSourceEqualTo(String value) {
            addCriterion("video_source =", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceNotEqualTo(String value) {
            addCriterion("video_source <>", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceGreaterThan(String value) {
            addCriterion("video_source >", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceGreaterThanOrEqualTo(String value) {
            addCriterion("video_source >=", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceLessThan(String value) {
            addCriterion("video_source <", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceLessThanOrEqualTo(String value) {
            addCriterion("video_source <=", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceLike(String value) {
            addCriterion("video_source like", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceNotLike(String value) {
            addCriterion("video_source not like", value, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceIn(List<String> values) {
            addCriterion("video_source in", values, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceNotIn(List<String> values) {
            addCriterion("video_source not in", values, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceBetween(String value1, String value2) {
            addCriterion("video_source between", value1, value2, "videoSource");
            return (Criteria) this;
        }

        public Criteria andVideoSourceNotBetween(String value1, String value2) {
            addCriterion("video_source not between", value1, value2, "videoSource");
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