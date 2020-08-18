package com.jf.entity.dto;

/**
 * @author luoyb
 * Created on 2019/9/12
 */
public class Top10VideoLast7DayCount {

    private Integer playCount;
    private Integer likeCount;
    private Integer commentCount;
    private Integer collectCount;

    private int denominator = 0;

    public Integer getPlayAvg() {
        return getAvg(playCount);
    }

    public Integer getLikeAvg() {
        return getAvg(likeCount);
    }

    public Integer getCommentAvg() {
        return getAvg(commentCount);
    }

    public Integer getCollectAvg() {
        return getAvg(collectCount);
    }


    private Integer getAvg(Integer count) {
        if (count == null || denominator == 0) {
            return 1;
        }
        int avg = count / denominator;
        return avg == 0 ? 1 : avg;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }
}
