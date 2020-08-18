package com.jf.vo.request.video;


import com.jf.vo.request.PageRequest;

import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class FindMemberAttentionVideoRequest extends PageRequest {

    private Integer memberId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

}
