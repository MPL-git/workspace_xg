package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtProductBrandCustom;
import com.jf.entity.MchtProductBrandCustomExample;
import com.jf.entity.MchtProductBrandExample;
import com.jf.vo.Page;
@Repository
public interface MchtProductBrandCustomMapper{
	List<MchtProductBrandCustom> getMchtProductBrandCustomsByMchtId(@Param("mchtId")Integer mchtId,@Param("page")Page page);
	//获取商家品牌及商家名称
	List<MchtProductBrandCustom> getMchtProductbrandInfoCustom(HashMap<String, Object> paramMap);
	int getMchtBrandCustomCount(HashMap<String, Object> paramMap);
	//获取商家品牌及商家名称通过id
	MchtProductBrandCustom getMchtProductbrandInfoCustomByID(Integer id);
	int countExpireBrand(HashMap<String, Object> paramMap);
	List<MchtProductBrandCustom> getMchtProductBrandCustoms(HashMap<String, Object> paramMap);
	List<HashMap<String, Object>> getMchtContacts(HashMap<String, Object> paramMap);
	
	List<MchtProductBrandCustom> selectByExample(MchtProductBrandExample example);
	List<MchtProductBrandCustom> selectByExample3(MchtProductBrandCustomExample example);
	int countByExample(MchtProductBrandExample example);
	int countByExample3(MchtProductBrandCustomExample example);
	
	List<MchtProductBrandCustom> selectByExample2(MchtProductBrandExample example);
	List<MchtProductBrandCustom> getMchtProductBrandCustomsByMchtIds(String mchtIds);
	
	public List<Map<String, Object>> selectDockingByListExample(MchtProductBrandExample example);
}