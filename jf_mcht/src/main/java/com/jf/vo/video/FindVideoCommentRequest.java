package com.jf.vo.video;

import com.jf.vo.PageRequest;

/**
 * @author luoyb
 * Created on 2019/9/19
 */
public class FindVideoCommentRequest extends PageRequest {

    private Integer videoId;

    private String dateBegin;

    private String dateEnd;

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
