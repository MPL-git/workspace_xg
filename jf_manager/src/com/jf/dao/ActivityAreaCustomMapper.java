package com.jf.dao;

import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaCustom;
import com.jf.entity.ActivityAreaCustomExample;
import com.jf.entity.ActivityAreaExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface ActivityAreaCustomMapper{

   public List<ActivityAreaCustom> selectByCustomExample(ActivityAreaCustomExample example);
   
   public Integer countByCustomExample(ActivityAreaCustomExample example);
   
   ActivityAreaCustom selectByCustomPrimaryKey(Integer id);
   
   public List<Map<String, Object>> getTypeEqualToSixOrSeven(Integer type);

   int updateActivityAreaPreheatSeqNo(HashMap<String,Object> map);
   
   int updateByExampleSelective(@Param("record") ActivityArea record, @Param("example") ActivityAreaExample example);
   
   /**
    * 
    * @Title getCreateByList   
    * @Description TODO(获取30天内已创建人)   
    * @author pengl
    * @date 2018年1月22日 上午10:43:38
    */
   public List<Map<String, Object>> getCreateByList();

   public List<String> getEntryPics(HashMap<String, Object> paramMap);

   public List<Integer> getProductIdsByActivityAreaIds(String activityAreaIds);

   public int countActivityByMchtId(Integer mchtId);

    void updateShareIntegralIsNull(Integer id);
}