package com.jf.dao;

import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomAreaCount;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.MemberInfoExample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Repository
public interface MemberInfoCustomMapper{
    int countByExample(MemberInfoExample example);
    List<MemberInfoCustom> selectByExample(MemberInfoExample example);
    MemberInfoCustom selectByPrimaryKey(Integer id);

    List<MemberInfoCustomAreaCount> selectByExampleGroupByProvince(MemberInfoExample example);
    List<MemberInfoCustomAreaCount> selectByExampleGroupByCity(MemberInfoExample example);
	int countMemberInfoByDay(String day);
	List<String> findMobileBrandList();
	//获取会员分润统计列表
	List<MemberInfoCustom> selectMemberInfoCustomByExampleNova(
			Map<String, Object> paramsMap);
	//获取会员分润统计列表总数量
	int countByExampleNova(Map<String, Object> paramsMap);
	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月15日 下午12:41:33
	 */
	Integer countByCustomExample(MemberInfoCustomExample memberInfoCustomExample);
	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月15日 下午12:41:37
	 */
	List<MemberInfoCustom> selectByCustomExample(MemberInfoCustomExample memberInfoCustomExample);
	
	List<MemberInfoCustom> selectMemberIdByExample(MemberInfoExample example);
	
	public List<MemberInfoCustom> memberEverydayCount(HashMap<String, Object> paramMap);//会员数据(日报表)
	
	public List<MemberInfoCustom> memberMonthlydayCount(HashMap<String, Object> paramMap);//会员数据(月报表)
	
	List<MemberInfoCustom> mmemberWeeklyReportCount(HashMap<String, Object> paramMap);//会员数据(周报表)

	List<Integer> listId(MemberInfoCustomExample example);
	Set<Integer> listIdOfSet(MemberInfoCustomExample example);

	Integer countPartDataList(HashMap<String, Object> paramMap);

	List<MemberInfoCustom> selectPartDataList(HashMap<String, Object> paramMap);

	Integer countPartDataFromOrderList(HashMap<String, Object> paramMap);

	List<MemberInfoCustom> selectPartDataFromOrderList(HashMap<String, Object> paramMap);

	List<Map<String, Object>> getMemeberIdList(Map<String, Integer> paramMap);

    Integer countSmsBlackMobile(HashMap<String, Object> paramMap);

	List<HashMap<String,Object>> selectSmsBlackMobile(HashMap<String, Object> paramMap);

	void delSmsBlackMobil(HashMap<String, Object> paraMap);
}