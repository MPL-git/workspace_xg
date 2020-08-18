package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ConfigSpecialMchtCustomMapper extends ConfigSpecialMchtMapper{

    Map<String, Object> selectByMchtCode(@Param("mchtCode") String mchtCode);

    List<Map<String, Object>> selectSpecialMchtList(Map<String, Object> parameMap);

    List<Map<String, Object>> selectNotSpecialMchtList(Map<String, Object> parameMap);

    int selectSpecialMchtListCount(Map<String, Object> parameMap);

    int selectNotSpecialMchtListCount(Map<String, Object> parameMap);
}
