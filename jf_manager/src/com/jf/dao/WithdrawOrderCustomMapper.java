package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderCustom;
import com.jf.entity.WithdrawOrderCustomExample;
import com.jf.entity.WithdrawOrderPic;
import com.jf.entity.WithdrawOrderStatusLog;

@Repository
public interface WithdrawOrderCustomMapper {
    
	public int countByCustomExample(WithdrawOrderCustomExample example);

	public List<WithdrawOrderCustom> selectByCustomExample(WithdrawOrderCustomExample example);

	public WithdrawOrderCustom selectByCustomPrimaryKey(Integer id);

	public int updateByCustomExampleSelective(@Param("record") WithdrawOrder record, @Param("example") WithdrawOrderCustomExample example);

	//新星余额提现审核总数
	public void countByCustomExampleNewStart(
			WithdrawOrderCustomExample withdrawOrderCustomExample);
	//新星余额提现审核列表
	public List<WithdrawOrderCustom> selectByCustomExampleNewStart(
			WithdrawOrderCustomExample withdrawOrderCustomExample);
	
	//批量查询会员账户提现信息
	public List<WithdrawOrder> selectByPrimaryKeyList(List<String> ids);

	//批量新增会员账户提现状态日志数据
	public void insertSelectiveListLog(
			List<WithdrawOrderStatusLog> withdrawOrderLog);

	//批量新增会员账户提现图片表
	public void insertSelectiveList(List<WithdrawOrderPic> withdrawOrderPics);

}