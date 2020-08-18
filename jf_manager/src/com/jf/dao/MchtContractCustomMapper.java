package com.jf.dao;

import com.jf.entity.MchtContractCustom;
import com.jf.entity.MchtContractCustomExample;
import com.jf.entity.PlatformContractReturnLis;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface MchtContractCustomMapper{
	List<HashMap<String, Object>> getMchtContacts(HashMap<String, Object> paramMap);

	List<HashMap<String, Object>> countMchtContract(HashMap<String, Object> paramMap);

	List<HashMap<String, Object>> countArchiveStatusMchtContract(HashMap<String, Object> paramMap);

	List<MchtContractCustom> selectByExample(MchtContractCustomExample example);

	int countByExample(MchtContractCustomExample example);

	List<PlatformContractReturnLis> contractReturnList(Map<String, Object> map);

	int countByContractReturnList(Map<String, Object> map);

	/**
	 * @MethodName selectByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月31日 下午9:01:32
	 */
	List<MchtContractCustom> selectByExampleCustom(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName countByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月31日 下午9:01:37
	 */
	int countByExampleCustom(MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName countByExampleCustomFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月2日 上午9:50:44
	 */
	int countByExampleCustomFW(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName selectByExampleCustomFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月2日 上午9:50:48
	 */
	List<MchtContractCustom> selectByExampleCustomFW(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName countByExampleCustomOnlineFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月3日 下午4:15:44
	 */
	int countByExampleCustomOnlineFW(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName selectByExampleCustomOnlineFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月3日 下午4:15:46
	 */
	List<MchtContractCustom> selectByExampleCustomOnlineFW(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName countByExampleCustomNotRenew
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月5日 下午7:36:02
	 */
	int countByExampleCustomNotRenew(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName selectByExampleCustomNotRenew
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月5日 下午7:36:09
	 */
	List<MchtContractCustom> selectByExampleCustomNotRenew(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName selectByExampleCustomSuspended
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月6日 下午2:58:24
	 */
	List<MchtContractCustom> selectByExampleCustomSuspended(
			MchtContractCustomExample mchtContractCustomExample);

	/**
	 * @MethodName countByExampleCustomSuspended
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月6日 下午2:58:28
	 */
	int countByExampleCustomSuspended(
			MchtContractCustomExample mchtContractCustomExample);

	List<Map<String, Object>> selectExpireListByExample(Map<String, Object> map);

	int countExpireListByExample(Map<String, Object> map);
    
}