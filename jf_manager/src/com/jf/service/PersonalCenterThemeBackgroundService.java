package com.jf.service;

import com.jf.dao.PersonalCenterThemeBackgroundMapper;
import com.jf.entity.PersonalCenterThemeBackground;
import com.jf.entity.PersonalCenterThemeBackgroundExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName 个人中心主题背景
 * @Author YRD
 * @Date 2020/6/5 14:44
 **/
@Service
public class PersonalCenterThemeBackgroundService extends BaseService<PersonalCenterThemeBackground, PersonalCenterThemeBackgroundExample> {

    @Autowired
    private PersonalCenterThemeBackgroundMapper personalCenterThemeBackgroundMapper;

    @Autowired
    public void setPersonalCenterThemeBackgroundMapper(PersonalCenterThemeBackgroundMapper personalCenterThemeBackgroundMapper) {
        super.setDao(personalCenterThemeBackgroundMapper);
        this.personalCenterThemeBackgroundMapper = personalCenterThemeBackgroundMapper;
    }
}
