package com.jf.vo.response.couponrain;

/**
 * @author luoyb
 * Created on 2019/9/26
 */
public class ParticipateResponse {

    private Integer recordId;

    private String rainType; //P.普通红包 S.圣诞红包

    public ParticipateResponse() {
    }

    public ParticipateResponse(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRainType() {
        return rainType;
    }

    public void setRainType(String rainType) {
        this.rainType = rainType;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}
