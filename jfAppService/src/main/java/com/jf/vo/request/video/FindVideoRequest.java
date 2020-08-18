package com.jf.vo.request.video;


import com.jf.vo.request.PageRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class FindVideoRequest extends PageRequest {

    @NotNull(message = "栏目类型不能为空")
    @Min(value = 1, message = "栏目类型不正确")
    @Max(value = 3, message = "栏目类型不正确")
    private Integer type; //1、我的关注 2、精选 3、品类栏目

    private Integer productType1Id; //仅当type为3时有值

    private Integer memberId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProductType1Id() {
        return productType1Id;
    }

    public void setProductType1Id(Integer productType1Id) {
        this.productType1Id = productType1Id;
    }
}
