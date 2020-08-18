package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtContractExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtContractExample() {
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

        public Criteria andContractCodeIsNull() {
            addCriterion("contract_code is null");
            return (Criteria) this;
        }

        public Criteria andContractCodeIsNotNull() {
            addCriterion("contract_code is not null");
            return (Criteria) this;
        }

        public Criteria andContractCodeEqualTo(String value) {
            addCriterion("contract_code =", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeNotEqualTo(String value) {
            addCriterion("contract_code <>", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeGreaterThan(String value) {
            addCriterion("contract_code >", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeGreaterThanOrEqualTo(String value) {
            addCriterion("contract_code >=", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeLessThan(String value) {
            addCriterion("contract_code <", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeLessThanOrEqualTo(String value) {
            addCriterion("contract_code <=", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeLike(String value) {
            addCriterion("contract_code like", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeNotLike(String value) {
            addCriterion("contract_code not like", value, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeIn(List<String> values) {
            addCriterion("contract_code in", values, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeNotIn(List<String> values) {
            addCriterion("contract_code not in", values, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeBetween(String value1, String value2) {
            addCriterion("contract_code between", value1, value2, "contractCode");
            return (Criteria) this;
        }

        public Criteria andContractCodeNotBetween(String value1, String value2) {
            addCriterion("contract_code not between", value1, value2, "contractCode");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadIsNull() {
            addCriterion("is_need_upload is null");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadIsNotNull() {
            addCriterion("is_need_upload is not null");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadEqualTo(String value) {
            addCriterion("is_need_upload =", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotEqualTo(String value) {
            addCriterion("is_need_upload <>", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadGreaterThan(String value) {
            addCriterion("is_need_upload >", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadGreaterThanOrEqualTo(String value) {
            addCriterion("is_need_upload >=", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadLessThan(String value) {
            addCriterion("is_need_upload <", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadLessThanOrEqualTo(String value) {
            addCriterion("is_need_upload <=", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadLike(String value) {
            addCriterion("is_need_upload like", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotLike(String value) {
            addCriterion("is_need_upload not like", value, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadIn(List<String> values) {
            addCriterion("is_need_upload in", values, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotIn(List<String> values) {
            addCriterion("is_need_upload not in", values, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadBetween(String value1, String value2) {
            addCriterion("is_need_upload between", value1, value2, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andIsNeedUploadNotBetween(String value1, String value2) {
            addCriterion("is_need_upload not between", value1, value2, "isNeedUpload");
            return (Criteria) this;
        }

        public Criteria andUploadDateIsNull() {
            addCriterion("upload_date is null");
            return (Criteria) this;
        }

        public Criteria andUploadDateIsNotNull() {
            addCriterion("upload_date is not null");
            return (Criteria) this;
        }

        public Criteria andUploadDateEqualTo(Date value) {
            addCriterion("upload_date =", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotEqualTo(Date value) {
            addCriterion("upload_date <>", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateGreaterThan(Date value) {
            addCriterion("upload_date >", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_date >=", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateLessThan(Date value) {
            addCriterion("upload_date <", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateLessThanOrEqualTo(Date value) {
            addCriterion("upload_date <=", value, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateIn(List<Date> values) {
            addCriterion("upload_date in", values, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotIn(List<Date> values) {
            addCriterion("upload_date not in", values, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateBetween(Date value1, Date value2) {
            addCriterion("upload_date between", value1, value2, "uploadDate");
            return (Criteria) this;
        }

        public Criteria andUploadDateNotBetween(Date value1, Date value2) {
            addCriterion("upload_date not between", value1, value2, "uploadDate");
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

        public Criteria andIsMchtSendIsNull() {
            addCriterion("is_mcht_send is null");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendIsNotNull() {
            addCriterion("is_mcht_send is not null");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendEqualTo(String value) {
            addCriterion("is_mcht_send =", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendNotEqualTo(String value) {
            addCriterion("is_mcht_send <>", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendGreaterThan(String value) {
            addCriterion("is_mcht_send >", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendGreaterThanOrEqualTo(String value) {
            addCriterion("is_mcht_send >=", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendLessThan(String value) {
            addCriterion("is_mcht_send <", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendLessThanOrEqualTo(String value) {
            addCriterion("is_mcht_send <=", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendLike(String value) {
            addCriterion("is_mcht_send like", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendNotLike(String value) {
            addCriterion("is_mcht_send not like", value, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendIn(List<String> values) {
            addCriterion("is_mcht_send in", values, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendNotIn(List<String> values) {
            addCriterion("is_mcht_send not in", values, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendBetween(String value1, String value2) {
            addCriterion("is_mcht_send between", value1, value2, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andIsMchtSendNotBetween(String value1, String value2) {
            addCriterion("is_mcht_send not between", value1, value2, "isMchtSend");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateIsNull() {
            addCriterion("mcht_send_date is null");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateIsNotNull() {
            addCriterion("mcht_send_date is not null");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateEqualTo(Date value) {
            addCriterion("mcht_send_date =", value, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateNotEqualTo(Date value) {
            addCriterion("mcht_send_date <>", value, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateGreaterThan(Date value) {
            addCriterion("mcht_send_date >", value, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("mcht_send_date >=", value, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateLessThan(Date value) {
            addCriterion("mcht_send_date <", value, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateLessThanOrEqualTo(Date value) {
            addCriterion("mcht_send_date <=", value, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateIn(List<Date> values) {
            addCriterion("mcht_send_date in", values, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateNotIn(List<Date> values) {
            addCriterion("mcht_send_date not in", values, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateBetween(Date value1, Date value2) {
            addCriterion("mcht_send_date between", value1, value2, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtSendDateNotBetween(Date value1, Date value2) {
            addCriterion("mcht_send_date not between", value1, value2, "mchtSendDate");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdIsNull() {
            addCriterion("mcht_express_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdIsNotNull() {
            addCriterion("mcht_express_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdEqualTo(Integer value) {
            addCriterion("mcht_express_id =", value, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdNotEqualTo(Integer value) {
            addCriterion("mcht_express_id <>", value, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdGreaterThan(Integer value) {
            addCriterion("mcht_express_id >", value, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_express_id >=", value, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdLessThan(Integer value) {
            addCriterion("mcht_express_id <", value, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_express_id <=", value, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdIn(List<Integer> values) {
            addCriterion("mcht_express_id in", values, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdNotIn(List<Integer> values) {
            addCriterion("mcht_express_id not in", values, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_express_id between", value1, value2, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_express_id not between", value1, value2, "mchtExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoIsNull() {
            addCriterion("mcht_express_no is null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoIsNotNull() {
            addCriterion("mcht_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoEqualTo(String value) {
            addCriterion("mcht_express_no =", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotEqualTo(String value) {
            addCriterion("mcht_express_no <>", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoGreaterThan(String value) {
            addCriterion("mcht_express_no >", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_express_no >=", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoLessThan(String value) {
            addCriterion("mcht_express_no <", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoLessThanOrEqualTo(String value) {
            addCriterion("mcht_express_no <=", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoLike(String value) {
            addCriterion("mcht_express_no like", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotLike(String value) {
            addCriterion("mcht_express_no not like", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoIn(List<String> values) {
            addCriterion("mcht_express_no in", values, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotIn(List<String> values) {
            addCriterion("mcht_express_no not in", values, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoBetween(String value1, String value2) {
            addCriterion("mcht_express_no between", value1, value2, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotBetween(String value1, String value2) {
            addCriterion("mcht_express_no not between", value1, value2, "mchtExpressNo");
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

        public Criteria andArchiveDateIsNull() {
            addCriterion("archive_date is null");
            return (Criteria) this;
        }

        public Criteria andArchiveDateIsNotNull() {
            addCriterion("archive_date is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveDateEqualTo(Date value) {
            addCriterion("archive_date =", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateNotEqualTo(Date value) {
            addCriterion("archive_date <>", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateGreaterThan(Date value) {
            addCriterion("archive_date >", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("archive_date >=", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateLessThan(Date value) {
            addCriterion("archive_date <", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateLessThanOrEqualTo(Date value) {
            addCriterion("archive_date <=", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateIn(List<Date> values) {
            addCriterion("archive_date in", values, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateNotIn(List<Date> values) {
            addCriterion("archive_date not in", values, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateBetween(Date value1, Date value2) {
            addCriterion("archive_date between", value1, value2, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateNotBetween(Date value1, Date value2) {
            addCriterion("archive_date not between", value1, value2, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveNoIsNull() {
            addCriterion("archive_no is null");
            return (Criteria) this;
        }

        public Criteria andArchiveNoIsNotNull() {
            addCriterion("archive_no is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveNoEqualTo(String value) {
            addCriterion("archive_no =", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotEqualTo(String value) {
            addCriterion("archive_no <>", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoGreaterThan(String value) {
            addCriterion("archive_no >", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoGreaterThanOrEqualTo(String value) {
            addCriterion("archive_no >=", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoLessThan(String value) {
            addCriterion("archive_no <", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoLessThanOrEqualTo(String value) {
            addCriterion("archive_no <=", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoLike(String value) {
            addCriterion("archive_no like", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotLike(String value) {
            addCriterion("archive_no not like", value, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoIn(List<String> values) {
            addCriterion("archive_no in", values, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotIn(List<String> values) {
            addCriterion("archive_no not in", values, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoBetween(String value1, String value2) {
            addCriterion("archive_no between", value1, value2, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveNoNotBetween(String value1, String value2) {
            addCriterion("archive_no not between", value1, value2, "archiveNo");
            return (Criteria) this;
        }

        public Criteria andArchiveByIsNull() {
            addCriterion("archive_by is null");
            return (Criteria) this;
        }

        public Criteria andArchiveByIsNotNull() {
            addCriterion("archive_by is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveByEqualTo(Integer value) {
            addCriterion("archive_by =", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByNotEqualTo(Integer value) {
            addCriterion("archive_by <>", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByGreaterThan(Integer value) {
            addCriterion("archive_by >", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByGreaterThanOrEqualTo(Integer value) {
            addCriterion("archive_by >=", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByLessThan(Integer value) {
            addCriterion("archive_by <", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByLessThanOrEqualTo(Integer value) {
            addCriterion("archive_by <=", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByIn(List<Integer> values) {
            addCriterion("archive_by in", values, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByNotIn(List<Integer> values) {
            addCriterion("archive_by not in", values, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByBetween(Integer value1, Integer value2) {
            addCriterion("archive_by between", value1, value2, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByNotBetween(Integer value1, Integer value2) {
            addCriterion("archive_by not between", value1, value2, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendIsNull() {
            addCriterion("is_platform_send is null");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendIsNotNull() {
            addCriterion("is_platform_send is not null");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendEqualTo(String value) {
            addCriterion("is_platform_send =", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotEqualTo(String value) {
            addCriterion("is_platform_send <>", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendGreaterThan(String value) {
            addCriterion("is_platform_send >", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendGreaterThanOrEqualTo(String value) {
            addCriterion("is_platform_send >=", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendLessThan(String value) {
            addCriterion("is_platform_send <", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendLessThanOrEqualTo(String value) {
            addCriterion("is_platform_send <=", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendLike(String value) {
            addCriterion("is_platform_send like", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotLike(String value) {
            addCriterion("is_platform_send not like", value, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendIn(List<String> values) {
            addCriterion("is_platform_send in", values, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotIn(List<String> values) {
            addCriterion("is_platform_send not in", values, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendBetween(String value1, String value2) {
            addCriterion("is_platform_send between", value1, value2, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andIsPlatformSendNotBetween(String value1, String value2) {
            addCriterion("is_platform_send not between", value1, value2, "isPlatformSend");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateIsNull() {
            addCriterion("platform_send_date is null");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateIsNotNull() {
            addCriterion("platform_send_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateEqualTo(Date value) {
            addCriterion("platform_send_date =", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateNotEqualTo(Date value) {
            addCriterion("platform_send_date <>", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateGreaterThan(Date value) {
            addCriterion("platform_send_date >", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("platform_send_date >=", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateLessThan(Date value) {
            addCriterion("platform_send_date <", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateLessThanOrEqualTo(Date value) {
            addCriterion("platform_send_date <=", value, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateIn(List<Date> values) {
            addCriterion("platform_send_date in", values, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateNotIn(List<Date> values) {
            addCriterion("platform_send_date not in", values, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateBetween(Date value1, Date value2) {
            addCriterion("platform_send_date between", value1, value2, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformSendDateNotBetween(Date value1, Date value2) {
            addCriterion("platform_send_date not between", value1, value2, "platformSendDate");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdIsNull() {
            addCriterion("platform_express_id is null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdIsNotNull() {
            addCriterion("platform_express_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdEqualTo(Integer value) {
            addCriterion("platform_express_id =", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdNotEqualTo(Integer value) {
            addCriterion("platform_express_id <>", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdGreaterThan(Integer value) {
            addCriterion("platform_express_id >", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_express_id >=", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdLessThan(Integer value) {
            addCriterion("platform_express_id <", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("platform_express_id <=", value, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdIn(List<Integer> values) {
            addCriterion("platform_express_id in", values, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdNotIn(List<Integer> values) {
            addCriterion("platform_express_id not in", values, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("platform_express_id between", value1, value2, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_express_id not between", value1, value2, "platformExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoIsNull() {
            addCriterion("platform_express_no is null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoIsNotNull() {
            addCriterion("platform_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoEqualTo(String value) {
            addCriterion("platform_express_no =", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotEqualTo(String value) {
            addCriterion("platform_express_no <>", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoGreaterThan(String value) {
            addCriterion("platform_express_no >", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("platform_express_no >=", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoLessThan(String value) {
            addCriterion("platform_express_no <", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoLessThanOrEqualTo(String value) {
            addCriterion("platform_express_no <=", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoLike(String value) {
            addCriterion("platform_express_no like", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotLike(String value) {
            addCriterion("platform_express_no not like", value, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoIn(List<String> values) {
            addCriterion("platform_express_no in", values, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotIn(List<String> values) {
            addCriterion("platform_express_no not in", values, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoBetween(String value1, String value2) {
            addCriterion("platform_express_no between", value1, value2, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformExpressNoNotBetween(String value1, String value2) {
            addCriterion("platform_express_no not between", value1, value2, "platformExpressNo");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Date value) {
            addCriterion("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Date value) {
            addCriterion("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Date value) {
            addCriterion("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Date value) {
            addCriterion("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Date> values) {
            addCriterion("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Date> values) {
            addCriterion("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Date value1, Date value2) {
            addCriterion("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andLastContractIdIsNull() {
            addCriterion("last_contract_id is null");
            return (Criteria) this;
        }

        public Criteria andLastContractIdIsNotNull() {
            addCriterion("last_contract_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastContractIdEqualTo(Integer value) {
            addCriterion("last_contract_id =", value, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdNotEqualTo(Integer value) {
            addCriterion("last_contract_id <>", value, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdGreaterThan(Integer value) {
            addCriterion("last_contract_id >", value, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_contract_id >=", value, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdLessThan(Integer value) {
            addCriterion("last_contract_id <", value, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_contract_id <=", value, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdIn(List<Integer> values) {
            addCriterion("last_contract_id in", values, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdNotIn(List<Integer> values) {
            addCriterion("last_contract_id not in", values, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdBetween(Integer value1, Integer value2) {
            addCriterion("last_contract_id between", value1, value2, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andLastContractIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_contract_id not between", value1, value2, "lastContractId");
            return (Criteria) this;
        }

        public Criteria andContractTypeIsNull() {
            addCriterion("contract_type is null");
            return (Criteria) this;
        }

        public Criteria andContractTypeIsNotNull() {
            addCriterion("contract_type is not null");
            return (Criteria) this;
        }

        public Criteria andContractTypeEqualTo(String value) {
            addCriterion("contract_type =", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotEqualTo(String value) {
            addCriterion("contract_type <>", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeGreaterThan(String value) {
            addCriterion("contract_type >", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeGreaterThanOrEqualTo(String value) {
            addCriterion("contract_type >=", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLessThan(String value) {
            addCriterion("contract_type <", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLessThanOrEqualTo(String value) {
            addCriterion("contract_type <=", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLike(String value) {
            addCriterion("contract_type like", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotLike(String value) {
            addCriterion("contract_type not like", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeIn(List<String> values) {
            addCriterion("contract_type in", values, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotIn(List<String> values) {
            addCriterion("contract_type not in", values, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeBetween(String value1, String value2) {
            addCriterion("contract_type between", value1, value2, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotBetween(String value1, String value2) {
            addCriterion("contract_type not between", value1, value2, "contractType");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusIsNull() {
            addCriterion("renew_pro_status is null");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusIsNotNull() {
            addCriterion("renew_pro_status is not null");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusEqualTo(String value) {
            addCriterion("renew_pro_status =", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusNotEqualTo(String value) {
            addCriterion("renew_pro_status <>", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusGreaterThan(String value) {
            addCriterion("renew_pro_status >", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusGreaterThanOrEqualTo(String value) {
            addCriterion("renew_pro_status >=", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusLessThan(String value) {
            addCriterion("renew_pro_status <", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusLessThanOrEqualTo(String value) {
            addCriterion("renew_pro_status <=", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusLike(String value) {
            addCriterion("renew_pro_status like", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusNotLike(String value) {
            addCriterion("renew_pro_status not like", value, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusIn(List<String> values) {
            addCriterion("renew_pro_status in", values, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusNotIn(List<String> values) {
            addCriterion("renew_pro_status not in", values, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusBetween(String value1, String value2) {
            addCriterion("renew_pro_status between", value1, value2, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andRenewProStatusNotBetween(String value1, String value2) {
            addCriterion("renew_pro_status not between", value1, value2, "renewProStatus");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksIsNull() {
            addCriterion("mcht_not_renew_remarks is null");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksIsNotNull() {
            addCriterion("mcht_not_renew_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksEqualTo(String value) {
            addCriterion("mcht_not_renew_remarks =", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksNotEqualTo(String value) {
            addCriterion("mcht_not_renew_remarks <>", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksGreaterThan(String value) {
            addCriterion("mcht_not_renew_remarks >", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_not_renew_remarks >=", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksLessThan(String value) {
            addCriterion("mcht_not_renew_remarks <", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksLessThanOrEqualTo(String value) {
            addCriterion("mcht_not_renew_remarks <=", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksLike(String value) {
            addCriterion("mcht_not_renew_remarks like", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksNotLike(String value) {
            addCriterion("mcht_not_renew_remarks not like", value, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksIn(List<String> values) {
            addCriterion("mcht_not_renew_remarks in", values, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksNotIn(List<String> values) {
            addCriterion("mcht_not_renew_remarks not in", values, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksBetween(String value1, String value2) {
            addCriterion("mcht_not_renew_remarks between", value1, value2, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtNotRenewRemarksNotBetween(String value1, String value2) {
            addCriterion("mcht_not_renew_remarks not between", value1, value2, "mchtNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksIsNull() {
            addCriterion("zs_not_renew_remarks is null");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksIsNotNull() {
            addCriterion("zs_not_renew_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksEqualTo(String value) {
            addCriterion("zs_not_renew_remarks =", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksNotEqualTo(String value) {
            addCriterion("zs_not_renew_remarks <>", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksGreaterThan(String value) {
            addCriterion("zs_not_renew_remarks >", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("zs_not_renew_remarks >=", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksLessThan(String value) {
            addCriterion("zs_not_renew_remarks <", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksLessThanOrEqualTo(String value) {
            addCriterion("zs_not_renew_remarks <=", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksLike(String value) {
            addCriterion("zs_not_renew_remarks like", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksNotLike(String value) {
            addCriterion("zs_not_renew_remarks not like", value, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksIn(List<String> values) {
            addCriterion("zs_not_renew_remarks in", values, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksNotIn(List<String> values) {
            addCriterion("zs_not_renew_remarks not in", values, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksBetween(String value1, String value2) {
            addCriterion("zs_not_renew_remarks between", value1, value2, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsNotRenewRemarksNotBetween(String value1, String value2) {
            addCriterion("zs_not_renew_remarks not between", value1, value2, "zsNotRenewRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksIsNull() {
            addCriterion("zs_renew_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksIsNotNull() {
            addCriterion("zs_renew_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksEqualTo(String value) {
            addCriterion("zs_renew_inner_remarks =", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksNotEqualTo(String value) {
            addCriterion("zs_renew_inner_remarks <>", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksGreaterThan(String value) {
            addCriterion("zs_renew_inner_remarks >", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("zs_renew_inner_remarks >=", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksLessThan(String value) {
            addCriterion("zs_renew_inner_remarks <", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("zs_renew_inner_remarks <=", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksLike(String value) {
            addCriterion("zs_renew_inner_remarks like", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksNotLike(String value) {
            addCriterion("zs_renew_inner_remarks not like", value, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksIn(List<String> values) {
            addCriterion("zs_renew_inner_remarks in", values, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksNotIn(List<String> values) {
            addCriterion("zs_renew_inner_remarks not in", values, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksBetween(String value1, String value2) {
            addCriterion("zs_renew_inner_remarks between", value1, value2, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andZsRenewInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("zs_renew_inner_remarks not between", value1, value2, "zsRenewInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonIsNull() {
            addCriterion("shop_close_reason is null");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonIsNotNull() {
            addCriterion("shop_close_reason is not null");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonEqualTo(String value) {
            addCriterion("shop_close_reason =", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonNotEqualTo(String value) {
            addCriterion("shop_close_reason <>", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonGreaterThan(String value) {
            addCriterion("shop_close_reason >", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("shop_close_reason >=", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonLessThan(String value) {
            addCriterion("shop_close_reason <", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonLessThanOrEqualTo(String value) {
            addCriterion("shop_close_reason <=", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonLike(String value) {
            addCriterion("shop_close_reason like", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonNotLike(String value) {
            addCriterion("shop_close_reason not like", value, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonIn(List<String> values) {
            addCriterion("shop_close_reason in", values, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonNotIn(List<String> values) {
            addCriterion("shop_close_reason not in", values, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonBetween(String value1, String value2) {
            addCriterion("shop_close_reason between", value1, value2, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andShopCloseReasonNotBetween(String value1, String value2) {
            addCriterion("shop_close_reason not between", value1, value2, "shopCloseReason");
            return (Criteria) this;
        }

        public Criteria andRenewStatusIsNull() {
            addCriterion("renew_status is null");
            return (Criteria) this;
        }

        public Criteria andRenewStatusIsNotNull() {
            addCriterion("renew_status is not null");
            return (Criteria) this;
        }

        public Criteria andRenewStatusEqualTo(String value) {
            addCriterion("renew_status =", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusNotEqualTo(String value) {
            addCriterion("renew_status <>", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusGreaterThan(String value) {
            addCriterion("renew_status >", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusGreaterThanOrEqualTo(String value) {
            addCriterion("renew_status >=", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusLessThan(String value) {
            addCriterion("renew_status <", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusLessThanOrEqualTo(String value) {
            addCriterion("renew_status <=", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusLike(String value) {
            addCriterion("renew_status like", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusNotLike(String value) {
            addCriterion("renew_status not like", value, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusIn(List<String> values) {
            addCriterion("renew_status in", values, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusNotIn(List<String> values) {
            addCriterion("renew_status not in", values, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusBetween(String value1, String value2) {
            addCriterion("renew_status between", value1, value2, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewStatusNotBetween(String value1, String value2) {
            addCriterion("renew_status not between", value1, value2, "renewStatus");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksIsNull() {
            addCriterion("renew_remarks is null");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksIsNotNull() {
            addCriterion("renew_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksEqualTo(String value) {
            addCriterion("renew_remarks =", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksNotEqualTo(String value) {
            addCriterion("renew_remarks <>", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksGreaterThan(String value) {
            addCriterion("renew_remarks >", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("renew_remarks >=", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksLessThan(String value) {
            addCriterion("renew_remarks <", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksLessThanOrEqualTo(String value) {
            addCriterion("renew_remarks <=", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksLike(String value) {
            addCriterion("renew_remarks like", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksNotLike(String value) {
            addCriterion("renew_remarks not like", value, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksIn(List<String> values) {
            addCriterion("renew_remarks in", values, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksNotIn(List<String> values) {
            addCriterion("renew_remarks not in", values, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksBetween(String value1, String value2) {
            addCriterion("renew_remarks between", value1, value2, "renewRemarks");
            return (Criteria) this;
        }

        public Criteria andRenewRemarksNotBetween(String value1, String value2) {
            addCriterion("renew_remarks not between", value1, value2, "renewRemarks");
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

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
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