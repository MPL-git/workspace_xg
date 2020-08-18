package com.jf.dao;

import com.jf.entity.ActivityCustom;
import com.jf.entity.LandingPageCustom;
import com.jf.entity.LandingPageExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandingPageCustomMapper{

    Integer countLandingPagesCustomByExample(LandingPageExample landingPageExample);

    List<LandingPageCustom> selectLandingPagesCustomByExample(LandingPageExample landingPageExample);
}