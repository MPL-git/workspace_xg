package com.jf.vo.response;

/**
 * @author luoyb
 * Created on 2020/3/9
 */
public class ProductTypeView {

    private Integer id;
    private Integer parentId;
    private String name;
    private Integer tLevel;

    public Integer gettLevel() {
        return tLevel;
    }

    public void settLevel(Integer tLevel) {
        this.tLevel = tLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
