package com.jf.vo.video;

import com.google.common.collect.Lists;
import com.jf.entity.VideoExt;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/16
 */
public class VideoView  extends VideoExt {

    private final List<String> imgList = Lists.newArrayList();
    private final List<VideoProductSimpleView> productList = Lists.newArrayList();
    private boolean tipOff; //是否已被举报

    public boolean isTipOff() {
        return tipOff;
    }

    public void setTipOff(boolean tipOff) {
        this.tipOff = tipOff;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public List<VideoProductSimpleView> getProductList() {
        return productList;
    }
}
