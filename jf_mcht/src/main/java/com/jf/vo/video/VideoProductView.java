package com.jf.vo.video;

import com.jf.entity.ProductCustom;

/**
 * @author luoyb
 * Created on 2019/9/17
 */
public class VideoProductView extends ProductCustom {

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
