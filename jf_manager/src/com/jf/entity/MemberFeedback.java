package com.jf.entity;

import java.util.Date;

public class MemberFeedback {
    private Integer id;

    private Integer memberId;

    private String type;

    private String content;

    private String phoneModel;

    private String phoneSystem;
    
    private String systemversion;

    private String appVersion;
    
    private Integer procesby;
    
    private Date processdate;
    
    private String  dealstatus;

    private String dealopinion;
    
    private String relatedorder;
    
    private Date dealdate;
    
    private String mchtCode;
    
    private String mchtcompanyName;
    
    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel == null ? null : phoneModel.trim();
    }

    public String getPhoneSystem() {
        return phoneSystem;
    }

    public void setPhoneSystem(String phoneSystem) {
        this.phoneSystem = phoneSystem == null ? null : phoneSystem.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }
    
    public Integer getProcesby() {
		return procesby;
	}

	public void setProcesby(Integer procesby) {
		this.procesby = procesby;
	}

	public Date getProcessdate() {
		return processdate;
	}

	public void setProcessdate(Date processdate) {
		this.processdate = processdate;
	}

	public String getDealstatus() {
		return dealstatus;
	}

	public void setDealstatus(String dealstatus) {
		this.dealstatus = dealstatus;
	}

	public String getDealopinion() {
		return dealopinion;
	}

	public void setDealopinion(String dealopinion) {
		this.dealopinion = dealopinion;
	}

	public String getRelatedorder() {
		return relatedorder;
	}

	public void setRelatedorder(String relatedorder) {
		this.relatedorder = relatedorder;
	}

	public Date getDealdate() {
		return dealdate;
	}

	public void setDealdate(Date dealdate) {
		this.dealdate = dealdate;
	}

	public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

	public String getSystemversion() {
		return systemversion;
	}

	public void setSystemversion(String systemversion) {
		this.systemversion = systemversion;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}

	public String getMchtcompanyName() {
		return mchtcompanyName;
	}

	public void setMchtcompanyName(String mchtcompanyName) {
		this.mchtcompanyName = mchtcompanyName;
	}
    
}