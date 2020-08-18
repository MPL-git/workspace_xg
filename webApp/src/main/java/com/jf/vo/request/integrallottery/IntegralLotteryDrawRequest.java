package com.jf.vo.request.integrallottery;

import com.jf.vo.response.integrallottery.LotterySettingsView;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
public class IntegralLotteryDrawRequest {

    @NotEmpty(message = "页面转盘信息列表不能为空")
    private List<LotterySettingsView> pageSettingsList;

    private Integer free = 0;

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public List<LotterySettingsView> getPageSettingsList() {
        return pageSettingsList;
    }

    public void setPageSettingsList(List<LotterySettingsView> pageSettingsList) {
        this.pageSettingsList = pageSettingsList;
    }
}
