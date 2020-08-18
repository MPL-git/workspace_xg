package com.jf.vo.request.video;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author luoyb
 * Created on 2019/9/9
 */
public class TipOffVideoRequest {

    @NotNull(message = "用户ID不能为空")
    private Integer memberId;
    @NotNull(message = "视频ID不能为空")
    private Integer videoId;
    @NotNull(message = "违规类型不能为空")
    @Min(value = 1,message = "违规类型不存在")
    @Max(value = 5,message = "违规类型不存在")
    private Integer type; //1、低俗色情 2、非法政治 3、枪支毒品 4、影视版权 5、其他
    @NotNull(message = "举报截图不能为空")
    private String imgs; //视频截图，使用,分割
    @Length(max = 100,message = "违规说明不能超过100个字")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}
