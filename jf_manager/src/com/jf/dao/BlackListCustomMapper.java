package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.BlackList;
import com.jf.entity.BlackListCustom;
import com.jf.entity.BlackListCustomExample;
import com.jf.entity.CutPriceCnfDtl;

@Repository
public interface BlackListCustomMapper {
    
	public int countByCustomExample(BlackListCustomExample example);

	public List<BlackListCustom> selectByCustomExample(BlackListCustomExample example);

	public BlackListCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") BlackList record, @Param("example") BlackListCustomExample example);

	/**
	 * 
	 * @Title updateBlackListIdListSelective   
	 * @Description TODO(批量修改)   
	 * @author pengl
	 * @date 2018年6月11日 下午5:31:48
	 */
	public void updateBlackListIdListSelective(List<BlackList> record);
	
	
	
}