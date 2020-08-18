package com.jf.common.base;

/**
 * 接口返回值
 * 
 * @author gonghongqing01
 * @since 2016-10-29
 */
public class Message<T> {

    private Integer status; // 状态码

    private T content; // 要返回给客户端的具体内容

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}
