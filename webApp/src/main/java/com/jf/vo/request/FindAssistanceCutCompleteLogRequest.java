package com.jf.vo.request;

/**
 * @author luoyb
 * Created on 2020/1/3
 */
public class FindAssistanceCutCompleteLogRequest extends PageRequest{

    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
