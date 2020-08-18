package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgCustom;
import com.jf.entity.MchtBrandChgExample;
import com.jf.entity.MchtProductBrandCustom;

@Repository
public interface MchtBrandChgCustomMapper {
	//获取商家品牌及商家名称通过id
	MchtBrandChgCustom getMchtBrandChgCustomByID(Integer mchtBrandChgId);
	//获取商家品牌及商家名称
	List<MchtBrandChgCustom> getMchtBrandChgCustom(HashMap<String, Object> paramMap);
	
	Integer getMchtBrandChgCustomCount(HashMap<String, Object> paramMap);

	//根据ID对商家品牌更新表进行品牌归档处理
	void mchtBrandChgHandleArchiveUpdate(HashMap<String, Object> paramMap);

	/**
	 * @MethodName selectByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月2日 上午11:11:32
	 */
	List<MchtBrandChgCustom> selectByExampleCustom(MchtBrandChgExample mchtBrandChgExample);
}