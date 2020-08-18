package com.jf.dao;


import com.jf.entity.TypeColumnPvStatistical;
import com.jf.entity.TypeColumnPvStatisticalCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TypeColumnPvStatisticalCustomMapper {

    public List<TypeColumnPvStatisticalCustom> getProductColumnPvStatistical(Map<String, Object> paramMap);

    public List<TypeColumnPvStatisticalCustom> getMchtColumnPvStatistical(Map<String, Object> paramMap);

    void batchInsertTypeColumnPvStatistical(@Param("dataList")List<TypeColumnPvStatisticalCustom> dataList,@Param("type")Character type);
}
