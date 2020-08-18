package com.jf.service;

import com.jf.common.exception.ArgException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtils;
import com.jf.dao.MemberInfoCustomMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.dao.MemberStatusLogMapper;
import com.jf.entity.MemberBlackOperateLog;
import com.jf.entity.MemberBlackOperateLogCustom;
import com.jf.entity.MemberBlackOperateLogExample;
import com.jf.entity.MemberBlackPic;
import com.jf.entity.MemberBlackPicExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomAreaCount;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MemberStatusLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class MemberInfoService extends BaseService<MemberInfo,MemberInfoExample> {
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private MemberInfoCustomMapper memberInfoCustomMapper;
	
	@Autowired
	private MemberBlackPicService memberBlackPicService;
	
	@Autowired
	private MemberBlackOperateLogService memberBlackOperateLogService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	private MemberStatusLogMapper memberStatusLogMapper;
	
	@Autowired
	public void setMemberInfoMapper(MemberInfoMapper memberInfoMapper) {
		super.setDao(memberInfoMapper);
		this.memberInfoMapper = memberInfoMapper;
	}
	
	public int countMemberInfoCustomByExample(MemberInfoExample example){
		return memberInfoCustomMapper.countByExample(example);
	}
    public List<MemberInfoCustom> selectMemberInfoCustomByExample(MemberInfoExample example){
    	return memberInfoCustomMapper.selectByExample(example);
    }
    public MemberInfoCustom selectMemberInfoCustomByPrimaryKey(Integer id){
    	return memberInfoCustomMapper.selectByPrimaryKey(id);
    }
    
	public int count(QueryObject queryObject) {
		return memberInfoCustomMapper.countByExample(builderQuery(queryObject));
	}
	
	public List<MemberInfoCustom> selectMemberIdByExample(MemberInfoExample example){
    	return memberInfoCustomMapper.selectMemberIdByExample(example);
    }
	public List<MemberInfoCustom> memberEverydayCount(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.memberEverydayCount(paramMap);
	}
	
	public List<MemberInfoCustom> memberMonthlydayCount(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.memberMonthlydayCount(paramMap);
	}
	
	public List<MemberInfoCustom> mmemberWeeklyReportCount(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.mmemberWeeklyReportCount(paramMap);
	}
	
	public List<MemberInfo> list(QueryObject queryObject) {
		MemberInfoExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public List<MemberInfoCustomAreaCount> selectByExampleGroupByProvince(QueryObject queryObject) {
		return memberInfoCustomMapper.selectByExampleGroupByProvince(builderQuery(queryObject));
	}

	public List<MemberInfoCustomAreaCount> selectByExampleGroupByCity(QueryObject queryObject) {
		return memberInfoCustomMapper.selectByExampleGroupByCity(builderQuery(queryObject));
	}

	private MemberInfoExample builderQuery(QueryObject queryObject) {
		MemberInfoCustomExample example = new MemberInfoCustomExample();
		MemberInfoCustomExample.MemberInfoCustomCriteria criteria = example.createCriteria();
		//criteria.andDelFlagEqualTo(Const.FLAG_FALSE);取消del_flag条件过滤，对数据影响不大，可提高count性能
		if(queryObject.getQuery("sexType") != null){
			criteria.andSexTypeEqualTo(queryObject.getQueryToStr("sexType"));
		}
		if(queryObject.getQuery("createDateAfter") != null){
			criteria.andCreateDateGreaterThanOrEqualTo(queryObject.getQueryToDate("createDateAfter"));
		}
		if(queryObject.getQuery("createDateBefore") != null){
			criteria.andCreateDateLessThanOrEqualTo(queryObject.getQueryToDate("createDateBefore"));
		}
		if(queryObject.getQuery("loginDateAfter") != null){
			criteria.andLoginDateGreaterThanOrEqualTo(queryObject.getQueryToStr("loginDateAfter"));
		}
		if(queryObject.getQuery("loginDateBefore") != null){
			criteria.andLoginDateLessThanOrEqualTo(queryObject.getQueryToStr("loginDateBefore"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
	
	public int countMemberInfoByDay(String day){
		return memberInfoCustomMapper.countMemberInfoByDay(day);
	}

	public List<String> findMobileBrandList() {
		return memberInfoCustomMapper.findMobileBrandList();
	}
	
	/**
	 * 获取黑名单详情
	 * 
	 * @param memberId
	 * @return
	 */
	public Map<String, Object> getBlackInfo(Integer memberId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
		String limitFunction = memberInfo.getLimitFunction();
		// 回显checkbox勾选项
		if (!StringUtils.isEmpty(limitFunction)) {
			String[] limitFunArr = limitFunction.split(",");
			for (int i = 0; i < limitFunArr.length; i++) {
				if ("1".equals(limitFunArr[i])) {
					resultMap.put("limitEvaluate", 1);
				} else if ("2".equals(limitFunArr[i])) {
					resultMap.put("limitBuy", 1);
				} else if ("3".equals(limitFunArr[i])) {
					resultMap.put("limitLogin", 1);
				}
			}
		}
		resultMap.put("blackReason", memberInfo.getBlackReason());
		resultMap.put("blackInnerRemarks", memberInfo.getBlackInnerRemarks());
		
		MemberBlackPicExample memberBlackPicExample = new MemberBlackPicExample();
		MemberBlackPicExample.Criteria picCriteria = memberBlackPicExample.createCriteria();
		picCriteria.andMemberIdEqualTo(memberId);
		picCriteria.andDelFlagEqualTo("0");
		List<MemberBlackPic> picList = memberBlackPicService.selectByExample(memberBlackPicExample);
		resultMap.put("memberBlackPictures", picList);
		return resultMap;
	}
	
	/**
	 * 加入/解除黑名单 (改造后)
	 * 
	 * @param memberInfo
	 */
	public void saveLimitPermission(MemberInfoCustom memberInfo, Integer staffId) {
		MemberInfo updateMemberInfo = memberInfoMapper.selectByPrimaryKey(memberInfo.getId());
		if (updateMemberInfo == null) {
			throw new ArgException("用户数据错误");
		}
		if (StringUtils.isEmpty(memberInfo.getStatus())) {
			throw new ArgException("请求错误");
		}
		Date date = new Date();
		MemberBlackOperateLog memberBlackOperateLog = new MemberBlackOperateLog();
		memberBlackOperateLog.setMemberId(updateMemberInfo.getId());
		memberBlackOperateLog.setMemberNick(updateMemberInfo.getNick());
		memberBlackOperateLog.setOperateType(memberInfo.getLogOperateType());
		memberBlackOperateLog.setBlackInnerRemarks(memberInfo.getBlackInnerRemarks());
		memberBlackOperateLog.setCreateBy(staffId);
		memberBlackOperateLog.setCreateDate(date);
		memberBlackOperateLog.setDelFlag("0");
		
		// 拉黑操作
		if ("P".equals(memberInfo.getStatus())) {
			// 拉黑需要更新拉黑理由以及限制功能
			if (StringUtils.isEmpty(memberInfo.getBlackReason())) {
				throw new ArgException("拉黑理由不能为空");
			}
			updateMemberInfo.setBlackReason(memberInfo.getBlackReason());
			updateMemberInfo.setLimitFunction(memberInfo.getLimitFunction());
			memberBlackOperateLog.setLimitFunction(memberInfo.getLimitFunction());
			memberBlackOperateLog.setBlackReason(memberInfo.getBlackReason());
		}
		MemberStatusLog memberStatusLog = new MemberStatusLog();
		memberStatusLog.setDelFlag("0");
		memberStatusLog.setCreateDate(new Date());
		memberStatusLog.setMemberId(memberInfo.getId());
		if(updateMemberInfo.getStatus().equals("A")){
			memberStatusLog.setStatus("P");
		}else if(updateMemberInfo.getStatus().equals("P") || updateMemberInfo.getStatus().equals("C")){
			if(memberInfo.getStatus().equals("P")){
				memberStatusLog.setStatus("P");
			}else{
				memberStatusLog.setStatus("A");
			}
		}
		memberStatusLog.setRemarks(memberInfo.getBlackInnerRemarks());
		memberStatusLogMapper.insertSelective(memberStatusLog);
		
		// 更新会员表状态
		updateMemberInfo.setBlackInnerRemarks(memberInfo.getBlackInnerRemarks());
		updateMemberInfo.setStatus(memberInfo.getStatus());
		updateMemberInfo.setUpdateBy(staffId);
		updateMemberInfo.setUpdateDate(date);
		memberInfoMapper.updateByPrimaryKey(updateMemberInfo);
	
		// 更新图片 先删再增
		if (!StringUtils.isEmpty(memberInfo.getBlackPictures())) {
			memberBlackPicService.save(memberInfo.getBlackPictures(), memberInfo.getId(), staffId);
		}
		
		// 插入操作日志
		memberBlackOperateLogService.insert(memberBlackOperateLog);
	}
	
	/**
	 * 获取黑名单操作列表总数
	 * 
	 * @param example
	 * @return
	 */
	public Integer countBlackOperateLogByExample(MemberBlackOperateLogExample example) {
		return memberBlackOperateLogService.countCustomeByExample(example);
	}

	/**
	 * 获取黑名单操作列表数据
	 * 
	 * @param example
	 * @return
	 */
	public List<MemberBlackOperateLogCustom> selectBlackOperateLogByExample(MemberBlackOperateLogExample example) {
		return memberBlackOperateLogService.selectCustomByExample(example);
	}
	
	/**
     * 判断当前用户是否角色id为92的用户
     * 
     * @param staffId
     * @return
     */
    public Integer getCanOperate(Integer staffId) {
    	return sysStaffRoleService.getCanOperate(staffId, 92);
    }

	/**
     * 获取会员分润统计列表
     * 
     * @param staffId
     * @return
     */
	public List<MemberInfoCustom> selectMemberInfoCustomByExampleNova(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		List<MemberInfoCustom> list = memberInfoCustomMapper.selectMemberInfoCustomByExampleNova(paramsMap);
		return list;
	}

	public int countByExampleNova(
			Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		int count = memberInfoCustomMapper.countByExampleNova(paramsMap);
		return count;
	}

	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月15日 下午12:00:43
	 */
	public Integer countByCustomExample(
			MemberInfoCustomExample memberInfoCustomExample) {
		// TODO Auto-generated method stub
		return memberInfoCustomMapper.countByCustomExample(memberInfoCustomExample);
	}

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月15日 下午12:00:52
	 */
	public List<MemberInfoCustom> selectByCustomExample(
			MemberInfoCustomExample memberInfoCustomExample) {
		// TODO Auto-generated method stub
		return memberInfoCustomMapper.selectByCustomExample(memberInfoCustomExample);
	}

	public List<Integer> listId(MemberInfoCustomExample example) {
		return memberInfoCustomMapper.listId(example);
	}
	public Set<Integer> listIdOfSet(MemberInfoCustomExample example) {
		return memberInfoCustomMapper.listIdOfSet(example);
	}
	public Integer countPartDataList(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.countPartDataList(paramMap);
	}

	public List<MemberInfoCustom> selectPartDataList(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.selectPartDataList(paramMap);
	}

	public Integer countPartDataFromOrderList(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.countPartDataFromOrderList(paramMap);
	}

	public List<MemberInfoCustom> selectPartDataFromOrderList(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.selectPartDataFromOrderList(paramMap);
	}

    public Integer countSmsBlackMobile(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.countSmsBlackMobile(paramMap);
    }

	public List<HashMap<String,Object>> selectSmsBlackMobile(HashMap<String, Object> paramMap) {
		return memberInfoCustomMapper.selectSmsBlackMobile(paramMap);
	}

	public void delSmsBlackMobil(HashMap<String, Object> paraMap) {
		memberInfoCustomMapper.delSmsBlackMobil(paraMap);
	}
}
