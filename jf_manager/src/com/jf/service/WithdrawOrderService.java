package com.jf.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CashTransferMapper;
import com.jf.dao.MemberAccountDtlMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.dao.WithdrawOrderCustomMapper;
import com.jf.dao.WithdrawOrderMapper;
import com.jf.dao.WithdrawOrderPicMapper;
import com.jf.dao.WithdrawOrderStatusLogMapper;
import com.jf.entity.CashTransfer;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.StateCode;
import com.jf.entity.WithdrawOrder;
import com.jf.entity.WithdrawOrderCustom;
import com.jf.entity.WithdrawOrderCustomExample;
import com.jf.entity.WithdrawOrderExample;
import com.jf.entity.WithdrawOrderPic;
import com.jf.entity.WithdrawOrderPicExample;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.entity.WithdrawOrderExample.Criteria;

@Service
@Transactional
public class WithdrawOrderService extends
		BaseService<WithdrawOrder, WithdrawOrderExample> {

	@Autowired
	private WithdrawOrderMapper withdrawOrderMapper;

	@Autowired
	private WithdrawOrderCustomMapper withdrawOrderCustomMapper;

	@Autowired
	private WithdrawOrderStatusLogMapper withdrawOrderStatusLogMapper;

	@Autowired
	private MemberAccountMapper memberAccountMapper;

	@Autowired
	private MemberAccountDtlMapper memberAccountDtlMapper;

	@Autowired
	private CashTransferMapper cashTransferMapper;

	@Autowired
	private WithdrawOrderPicMapper withdrawOrderPicMapper;

	@Autowired
	public void setWithdrawOrderMapper(WithdrawOrderMapper withdrawOrderMapper) {
		super.setDao(withdrawOrderMapper);
		this.withdrawOrderMapper = withdrawOrderMapper;
	}

	public int countByCustomExample(WithdrawOrderCustomExample example) {
		return withdrawOrderCustomMapper.countByCustomExample(example);
	}

	public List<WithdrawOrderCustom> selectByCustomExample(
			WithdrawOrderCustomExample example) {
		return withdrawOrderCustomMapper.selectByCustomExample(example);
	}

	public WithdrawOrderCustom selectByCustomPrimaryKey(Integer id) {
		return withdrawOrderCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(
			@Param("record") WithdrawOrder record,
			@Param("example") WithdrawOrderCustomExample example) {
		return withdrawOrderCustomMapper.updateByCustomExampleSelective(record,
				example);
	}

	public void auditWithdrawOrder(Integer staffID, List<Integer> idsList,
			String status, String remarks) {
		Date date = new Date();
		for (Integer id : idsList) {
			WithdrawOrder withdraw = withdrawOrderMapper.selectByPrimaryKey(id);
			boolean flag = true;
			if ("5".equals(status)) {
				MemberAccount memberAccount = memberAccountMapper
						.selectByPrimaryKey(withdraw.getAccId());
				if (memberAccount.getFreeze().compareTo(withdraw.getAmount()) >= 0) {
					MemberAccountDtl memberAccountDtl = new MemberAccountDtl();
					memberAccountDtl.setAccId(withdraw.getAccId());
					memberAccountDtl.setTallyMode("1");
					memberAccountDtl.setFreezeAmount(withdraw.getAmount());
					memberAccountDtl.setTotalFreeze(memberAccount.getFreeze()
							.subtract(withdraw.getAmount()));
					memberAccountDtl.setBalance(memberAccount.getBalance());
					memberAccountDtl.setBizType("7");
					memberAccountDtl.setBizId(withdraw.getId());
					memberAccountDtl.setCreateBy(staffID);
					memberAccountDtl.setCreateDate(date);
					memberAccountDtl.setDelFlag("0");
					memberAccountDtl.setRemarks("审核驳回,解冻金额");
					memberAccountDtlMapper.insertSelective(memberAccountDtl);
					MemberAccount memberAccountNew = new MemberAccount();
					memberAccountNew.setId(memberAccount.getId());
					memberAccountNew.setFreeze(memberAccount.getFreeze()
							.subtract(withdraw.getAmount()));
					memberAccountNew.setUpdateBy(staffID);
					memberAccountNew.setUpdateDate(date);
					memberAccountMapper
							.updateByPrimaryKeySelective(memberAccountNew);
				} else {
					flag = false;
				}
			}
			if (flag) {
				withdraw.setStatus(status);
				withdraw.setUpdateBy(staffID);
				withdraw.setUpdateDate(date);
				// TODO 通过时，线下转账，插入记录
				if (status.equals("2")
						&& !StringUtils.isEmpty(withdraw.getWithdrawMethod())
						&& withdraw.getWithdrawMethod().equals("1")) {
					CashTransfer cashTransfer = new CashTransfer();
					cashTransfer.setWithdrawOrderId(withdraw.getId());
					cashTransfer.setStatus("0");
					cashTransfer.setDelFlag("0");
					cashTransfer.setCreateDate(new Date());
					cashTransfer.setCreateBy(staffID);
					cashTransferMapper.insertSelective(cashTransfer);
				}
				withdrawOrderMapper.updateByPrimaryKeySelective(withdraw);
				WithdrawOrderStatusLog withdrawOrderStatusLog = new WithdrawOrderStatusLog();
				withdrawOrderStatusLog.setWithdrawOrderId(id);
				withdrawOrderStatusLog.setStatus(status);
				withdrawOrderStatusLog.setCreateBy(staffID);
				withdrawOrderStatusLog.setCreateDate(date);
				withdrawOrderStatusLog.setRemarks(remarks);
				withdrawOrderStatusLog.setDelFlag("0");
				withdrawOrderStatusLogMapper
						.insertSelective(withdrawOrderStatusLog);
			}
		}

	}

	// 新星余额提现审核总数
	public int countByCustomExampleNewStart(
			WithdrawOrderCustomExample withdrawOrderCustomExample) {
		// TODO Auto-generated method stub
		withdrawOrderCustomMapper
				.countByCustomExampleNewStart(withdrawOrderCustomExample);
		return 0;
	}

	// 新星余额提现审核列表
	public List<WithdrawOrderCustom> selectByCustomExampleNewStart(
			WithdrawOrderCustomExample withdrawOrderCustomExample) {
		// TODO Auto-generated method stub
		List<WithdrawOrderCustom> list = withdrawOrderCustomMapper
				.selectByCustomExampleNewStart(withdrawOrderCustomExample);
		return list;
	}

	// 批量查询会员账户提现信息
	public List<WithdrawOrder> selectByPrimaryKeyList(List<String> ids) {
		// TODO Auto-generated method stub
		List<WithdrawOrder> list = withdrawOrderCustomMapper
				.selectByPrimaryKeyList(ids);
		return list;
	}

	// 批量新增会员账户提现状态日志数据
	public void insertSelectiveListLog(
			List<WithdrawOrderStatusLog> withdrawOrderLog) {
		// TODO Auto-generated method stub
		withdrawOrderCustomMapper.insertSelectiveListLog(withdrawOrderLog);
	}

	// 批量新增会员账户提现图片表
	public void insertSelectiveList(List<WithdrawOrderPic> withdrawOrderPics) {
		// TODO Auto-generated method stub
		withdrawOrderCustomMapper.insertSelectiveList(withdrawOrderPics);
	}

	// 审核操作
	@SuppressWarnings("unchecked")
	public Map<String, Object> operate(HttpServletRequest request,
			String staffId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = "提交成功！";
		try {
			// TODO Auto-generated method stub

			String ids = request.getParameter("withdrawOrderIds").substring(1,
					request.getParameter("withdrawOrderIds").length() - 1);
			String status = request.getParameter("status");
			String yyRejectReason = request.getParameter("yyRejectReason");
			String yyInnerRemarks = request.getParameter("yyInnerRemarks");
			String withdrawOrderPic = request.getParameter("withdrawOrderPic");
			String yy = request.getParameter("yy").substring(1,
					request.getParameter("yy").length() - 1);
			List<String> split = Arrays.asList(ids.split(","));
			List<Integer> spIntegers = new ArrayList<Integer>();
			for (String string : split) {
				spIntegers.add(Integer.valueOf(string));
			}
			// 查询出会员账户提现表数据
			WithdrawOrderExample withdrawOrderExample1 = new WithdrawOrderExample();
			Criteria criteria1 = withdrawOrderExample1.createCriteria();
			criteria1.andIdIn(spIntegers).andDelFlagEqualTo("0");
			List<WithdrawOrder> withdrawOrderExamples = selectByExample(withdrawOrderExample1);
			// 对审核的数据进行过滤
			spIntegers.clear();
			Iterator<WithdrawOrder> iterator = withdrawOrderExamples.iterator();
			while (iterator.hasNext()) {
				WithdrawOrder withdrawOrder = iterator.next();
				//运营
				if (StringUtils.equals(yy, "0")) {
					if (StringUtils.equals(withdrawOrder.getStatus(), "1")
							&& StringUtils.isBlank(withdrawOrder
									.getYyAuditStatus())
							|| StringUtils.equals(withdrawOrder.getStatus(),
									"5")
							&& StringUtils.equals(
									withdrawOrder.getYyAuditStatus(), "0")
							&& StringUtils.isBlank(withdrawOrder
									.getCwAuditStatus())) {
						spIntegers.add(withdrawOrder.getId());
					} else {
						iterator.remove();
					}
				}
				
				//财务
				if (StringUtils.equals(yy, "1")) {
					//审核
						if (((StringUtils.equals(withdrawOrder.getStatus(), "1")
								&& StringUtils.equals(
										withdrawOrder.getYyAuditStatus(), "1")
								&& StringUtils.isBlank(withdrawOrder
										.getCwAuditStatus())))||
							((StringUtils.equals(withdrawOrder.getStatus(),
									"5")
									&& StringUtils.equals(
											withdrawOrder.getYyAuditStatus(), "1")
									&& StringUtils.equals(
											withdrawOrder.getCwAuditStatus(), "0")))			
								){
							spIntegers.add(withdrawOrder.getId());					
						}else{
							iterator.remove();	
						}
				}
			}
			if (spIntegers.size() > 0) {
				// 对会员账户提现表进行批量更新
				WithdrawOrderExample withdrawOrderExample = new WithdrawOrderExample();
				Criteria criteria = withdrawOrderExample.createCriteria();
				criteria.andIdIn(spIntegers).andDelFlagEqualTo("0");
				WithdrawOrder wOrder = new WithdrawOrder();
				// 如果是营运
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				if (StringUtils.equals(status, "0")
						&& StringUtils.equals(yy, "0")) {
					wOrder.setStatus("5");
					wOrder.setYyAuditStatus(status);
					wOrder.setYyAuditBy(Integer.valueOf(staffId));
					wOrder.setYyAuditDate(dateFormat.parse(dateFormat
							.format(new Date())));
					wOrder.setYyRejectReason(yyRejectReason);
					wOrder.setYyInnerRemarks(yyInnerRemarks);
				} else if (StringUtils.equals(status, "1")
						&& StringUtils.equals(yy, "0")) {
					wOrder.setStatus("1");
					wOrder.setYyAuditStatus(status);
					wOrder.setYyAuditBy(Integer.valueOf(staffId));
					wOrder.setYyAuditDate(dateFormat.parse(dateFormat
							.format(new Date())));
					wOrder.setYyRejectReason(yyRejectReason);
					wOrder.setYyInnerRemarks(yyInnerRemarks);
				}
				// 如果是财务
				if (StringUtils.equals(status, "0")
						&& StringUtils.equals(yy, "1")) {
					wOrder.setStatus("5");
					wOrder.setCwAuditStatus(status);
					wOrder.setCwAuditBy(Integer.valueOf(staffId));
					wOrder.setCwAuditDate(dateFormat.parse(dateFormat
							.format(new Date())));
					wOrder.setCwRejectReason(yyRejectReason);
					wOrder.setCwInnerRemarks(yyInnerRemarks);
				} else if (StringUtils.equals(status, "1")
						&& StringUtils.equals(yy, "1")) {
					wOrder.setStatus("2");
					wOrder.setCwAuditStatus(status);
					wOrder.setCwAuditBy(Integer.valueOf(staffId));
					wOrder.setCwAuditDate(dateFormat.parse(dateFormat
							.format(new Date())));
					wOrder.setCwRejectReason(yyRejectReason);
					wOrder.setCwInnerRemarks(yyInnerRemarks);
				}
				wOrder.setUpdateBy(Integer.valueOf(staffId));
				wOrder.setUpdateDate(new Date());
				updateByExampleSelective(wOrder, withdrawOrderExample);
				// 获取之前存在的会员账户提现图片信息并删除
				WithdrawOrderPicExample withdrawOrderPicExample = new WithdrawOrderPicExample();
				com.jf.entity.WithdrawOrderPicExample.Criteria criteria2 = withdrawOrderPicExample
						.createCriteria();
				criteria2.andWithdrawOrderIdIn(spIntegers).andTypeEqualTo(yy).andDelFlagEqualTo(
						"0");
				WithdrawOrderPic withdrawOrderPic1 = new WithdrawOrderPic();
				withdrawOrderPic1.setDelFlag("1");
				withdrawOrderPic1.setUpdateBy(Integer.valueOf(staffId));
				withdrawOrderPic1.setUpdateDate(new Date());
				withdrawOrderPicMapper.updateByExampleSelective(
						withdrawOrderPic1, withdrawOrderPicExample);
				// 批量新增会员账户提现图片表
				JSONArray withdrawOrderPicList = JSONArray
						.fromObject(withdrawOrderPic);
				List<String> picList = (List<String>) JSONArray.toCollection(
						withdrawOrderPicList, String.class);
				if (picList.size() != 0) {
					List<WithdrawOrderPic> withdrawOrderPics = new ArrayList<WithdrawOrderPic>();
					for (int withdrawOrderId : spIntegers) {
						for (String pic : picList) {
							WithdrawOrderPic wOrderPic = new WithdrawOrderPic();
							wOrderPic.setWithdrawOrderId(withdrawOrderId);
							wOrderPic.setPic(pic);
							wOrderPic.setType(yy);
							wOrderPic.setCreateBy(Integer.valueOf(staffId));
							wOrderPic.setCreateDate(new Date());
							wOrderPic.setUpdateBy(Integer.valueOf(staffId));
							wOrderPic.setUpdateDate(new Date());
							wOrderPic.setDelFlag("0");
							withdrawOrderPics.add(wOrderPic);
						}
					}
					insertSelectiveList(withdrawOrderPics);
				}

				// 批量新增会员账户提现状态日志数据
				if (StringUtils.equals(yy, "1")) {
					List<WithdrawOrderStatusLog> withdrawOrderLog = new ArrayList<WithdrawOrderStatusLog>();
					for (int item : spIntegers) {
						WithdrawOrderStatusLog wLog = new WithdrawOrderStatusLog();
						wLog.setWithdrawOrderId(Integer.valueOf(item));
						if (StringUtils.equals(status, "0")) {
							wLog.setStatus("5");
						} else {
							wLog.setStatus("2");
						}
						wLog.setCreateBy(Integer.valueOf(staffId));
						wLog.setCreateDate(new Date());
						wLog.setUpdateBy(Integer.valueOf(staffId));
						wLog.setUpdateDate(new Date());
						wLog.setDelFlag("0");
						withdrawOrderLog.add(wLog);
					}
					insertSelectiveListLog(withdrawOrderLog);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put("statusCode", code);
		map.put("message", msg);
		return map;
	}
}
