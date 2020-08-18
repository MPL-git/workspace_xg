package com.jf.entity.dto;

/**
 * @Description:
 * @Author: XDD
 * @Date: 2019/12/19 17:43
 */
public class ZzySubscribeDTO {
    /**
     * 快递鸟ID
     */
    private Integer id;
    /**
     * 快递公司猪猪云编号
     */
    private String expressCompanyCode;
    /**
     * 快递单号
     */
    private String expressCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressCompanyCode() {
        return expressCompanyCode;
    }

    public void setExpressCompanyCode(String expressCompanyCode) {
        this.expressCompanyCode = expressCompanyCode;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    @Override
    public String toString() {
        return "ZzySubscribeDTO{" +
                "id=" + id +
                ", expressCompanyCode='" + expressCompanyCode + '\'' +
                ", expressCode='" + expressCode + '\'' +
                '}';
    }
}
