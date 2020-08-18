package com.jf.entity.dto;

/**
 * @author luoyb
 * Created on 2019/9/12
 */
public class VideoWeightDTO {

    private int id;
    private Integer seasonWeight;
    private Integer manualWeicht;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSeasonWeight() {
        return seasonWeight;
    }

    public void setSeasonWeight(Integer seasonWeight) {
        this.seasonWeight = seasonWeight;
    }

    public Integer getManualWeicht() {
        return manualWeicht;
    }

    public void setManualWeicht(Integer manualWeicht) {
        this.manualWeicht = manualWeicht;
    }
}
