package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtLicenseChgCustom;
import com.jf.entity.MchtLicenseChgCustomExample;
@Repository
public interface MchtLicenseChgCustomMapper{
    int countByExample(MchtLicenseChgCustomExample example);
    List<MchtLicenseChgCustom> selectByExample(MchtLicenseChgCustomExample example);
    void mchtLicenseChgHandleArchiveUpdate(HashMap<String, Object> paramMap);
}