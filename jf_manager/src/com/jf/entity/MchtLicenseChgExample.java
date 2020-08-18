package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtLicenseChgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtLicenseChgExample() {
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

        public Criteria andBusinessLicensePicIsNull() {
            addCriterion("business_license_pic is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicIsNotNull() {
            addCriterion("business_license_pic is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicEqualTo(String value) {
            addCriterion("business_license_pic =", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotEqualTo(String value) {
            addCriterion("business_license_pic <>", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicGreaterThan(String value) {
            addCriterion("business_license_pic >", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicGreaterThanOrEqualTo(String value) {
            addCriterion("business_license_pic >=", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicLessThan(String value) {
            addCriterion("business_license_pic <", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicLessThanOrEqualTo(String value) {
            addCriterion("business_license_pic <=", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicLike(String value) {
            addCriterion("business_license_pic like", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotLike(String value) {
            addCriterion("business_license_pic not like", value, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicIn(List<String> values) {
            addCriterion("business_license_pic in", values, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotIn(List<String> values) {
            addCriterion("business_license_pic not in", values, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicBetween(String value1, String value2) {
            addCriterion("business_license_pic between", value1, value2, "businessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBusinessLicensePicNotBetween(String value1, String value2) {
            addCriterion("business_license_pic not between", value1, value2, "businessLicensePic");
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

        public Criteria andLicenseRejectReasonIsNull() {
            addCriterion("license_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonIsNotNull() {
            addCriterion("license_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonEqualTo(String value) {
            addCriterion("license_reject_reason =", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotEqualTo(String value) {
            addCriterion("license_reject_reason <>", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonGreaterThan(String value) {
            addCriterion("license_reject_reason >", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("license_reject_reason >=", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonLessThan(String value) {
            addCriterion("license_reject_reason <", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("license_reject_reason <=", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonLike(String value) {
            addCriterion("license_reject_reason like", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotLike(String value) {
            addCriterion("license_reject_reason not like", value, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonIn(List<String> values) {
            addCriterion("license_reject_reason in", values, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotIn(List<String> values) {
            addCriterion("license_reject_reason not in", values, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonBetween(String value1, String value2) {
            addCriterion("license_reject_reason between", value1, value2, "licenseRejectReason");
            return (Criteria) this;
        }

        public Criteria andLicenseRejectReasonNotBetween(String value1, String value2) {
            addCriterion("license_reject_reason not between", value1, value2, "licenseRejectReason");
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