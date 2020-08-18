package com.jf.vo.response;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2020/8/4
 */
public class OrderProductView {

    private Integer productId;
    private String productName;
    private String skuPic;
    private String productPropDesc;
    private Integer quantity;
    private BigDecimal salePrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic;
    }

    public String getProductPropDesc() {
        return productPropDesc;
    }

    public void setProductPropDesc(String productPropDesc) {
        this.productPropDesc = productPropDesc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
