package com.jf.dao;

import com.jf.entity.ToutiaoCampaignInfoExt;
import com.jf.entity.ToutiaoCampaignInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoCampaignInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ToutiaoCampaignInfoExt findById(int id);

    ToutiaoCampaignInfoExt find(ToutiaoCampaignInfoExtExample example);

    List<ToutiaoCampaignInfoExt> list(ToutiaoCampaignInfoExtExample example);

    List<Integer> listId(ToutiaoCampaignInfoExtExample example);

    int count(ToutiaoCampaignInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ToutiaoCampaignInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ToutiaoCampaignInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ToutiaoCampaignInfoExtExample example);

}

