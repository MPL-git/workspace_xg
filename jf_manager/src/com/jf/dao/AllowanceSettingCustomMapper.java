package com.jf.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AllowanceSettingCustomMapper {
    int countAllowanceSettingList(Map<String, Object> paramMap);

    List<Map<String, Object>> selectAllowanceSettingList(Map<String, Object> paramMap);

}
