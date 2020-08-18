package com.jf.entity;

/**
 * @author luoyb
 * Created on 2019/9/10
 */
public class VideoCommentCustom extends VideoComment{

    private Integer likeOrder; //点赞排行，1表示最多

    public Integer getLikeOrder() {
        return likeOrder;
    }

    public void setLikeOrder(Integer likeOrder) {
        this.likeOrder = likeOrder;
    }
}
