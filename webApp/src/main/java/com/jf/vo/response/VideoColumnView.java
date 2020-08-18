package com.jf.vo.response;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class VideoColumnView {

    private Integer type; //栏目类型：1、我的关注 2、精选 3、品类
    private String name;
    private Integer productType1Id;
    private boolean selected;

    public VideoColumnView() {
    }

    public VideoColumnView(Integer type, String name, boolean selected) {
        this.type = type;
        this.name = name;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductType1Id() {
        return productType1Id;
    }

    public void setProductType1Id(Integer productType1Id) {
        this.productType1Id = productType1Id;
    }
}
