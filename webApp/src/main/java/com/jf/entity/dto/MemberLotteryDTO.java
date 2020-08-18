package com.jf.entity.dto;

import com.jf.entity.MemberLottery;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
public class MemberLotteryDTO extends MemberLottery {

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
