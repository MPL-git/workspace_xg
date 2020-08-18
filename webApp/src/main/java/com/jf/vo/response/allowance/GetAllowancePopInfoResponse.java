package com.jf.vo.response.allowance;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
public class GetAllowancePopInfoResponse {

    private boolean allowance;

    private String pic;
    private String popType; //弹窗类型 1.每天一次 2.终生一次 3.自定义
    private Integer day; //当弹窗类型为自定义时有值

    public String getPopType() {
        return popType;
    }

    public void setPopType(String popType) {
        this.popType = popType;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public boolean isAllowance() {
        return allowance;
    }

    public void setAllowance(boolean allowance) {
        this.allowance = allowance;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
