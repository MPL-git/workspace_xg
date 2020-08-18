package com.jf.dao;

import com.jf.entity.ThirdPlatformProductInfoExt;
import com.jf.entity.ThirdPlatformProductInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThirdPlatformProductInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ThirdPlatformProductInfoExt findById(int id);

    ThirdPlatformProductInfoExt find(ThirdPlatformProductInfoExtExample example);

    List<ThirdPlatformProductInfoExt> list(ThirdPlatformProductInfoExtExample example);

    List<Integer> listId(ThirdPlatformProductInfoExtExample example);

    int count(ThirdPlatformProductInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ThirdPlatformProductInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ThirdPlatformProductInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ThirdPlatformProductInfoExtExample example);

}

