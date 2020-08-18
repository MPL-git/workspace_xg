package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.dao.CommentDrawMapper;
import com.jf.dao.CommentMapper;
import com.jf.entity.CommentDraw;
import com.jf.entity.CommentDrawExample;
import com.jf.entity.CommentExample;
import com.jf.entity.IntegralDtl;
import com.jf.entity.MemberAccount;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月29日 下午1:49:46 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CommentDrawService extends BaseService<CommentDraw, CommentDrawExample> {
	@Autowired
	private CommentDrawMapper commentDrawMapper;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private IntegralDtlService integralDtlService;
	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	public void setCommentDrawMapper(CommentDrawMapper commentDrawMapper) {
		this.setDao(commentDrawMapper);
		this.commentDrawMapper = commentDrawMapper;
	}

	public void addMemberCommentDraw(JSONObject reqDataJson) {
		Integer subOrderId = reqDataJson.getInt("subOrderId");
		Integer memberId = reqDataJson.getInt("memberId");
		Date date = new Date();
		CommentDrawExample commentDrawExample = new CommentDrawExample();
		commentDrawExample.createCriteria().andStatusEqualTo("0").andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0");
		commentDrawExample.setOrderByClause("id desc");
		List<CommentDraw> commentDraws = selectByExample(commentDrawExample);
		if(CollectionUtils.isNotEmpty(commentDraws)){
			CommentDraw commentDraw = commentDraws.get(0);
			CommentDraw commentDrawUpdate = new CommentDraw();
			commentDrawUpdate.setStatus("1");
			int count = updateByExampleSelective(commentDrawUpdate, commentDrawExample);
			if(count <= 0){
				throw new ArgException("已评价获得积分奖励");
			}
			Integer integral = commentDraw.getIntegral();
			MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
			memberAccount.setIntegral(memberAccount.getIntegral()+integral);
			memberAccount.setUpdateBy(memberId);
			memberAccount.setUpdateDate(date);
			memberAccountService.updateByPrimaryKeySelective(memberAccount);
			IntegralDtl integralDtl = new IntegralDtl();
			integralDtl.setAccId(memberAccount.getId());
			integralDtl.setTallyMode(Const.INTEGRAL_TALLY_MODE_INCOME);
			integralDtl.setBalance(memberAccount.getIntegral());
			integralDtl.setIntegral(integral);
			integralDtl.setType(Integer.valueOf(Const.INTEGRAL_TYPE_EVERY_DAY_LEPING));
			integralDtl.setBizType("2");//订单类型(1母订单 2子订单 3订单明细 4违规单 5竞猜)
			integralDtl.setOrderId(subOrderId);
			integralDtl.setCreateBy(memberId);
			integralDtl.setCreateDate(date);
			integralDtl.setDelFlag("0");
			integralDtlService.insertSelective(integralDtl);
			return;
		}
		if (!hadComment(memberId, subOrderId)) {
			throw new ArgException("请先参与评价");
		}
	}

	private boolean hadComment(Integer memberId, Integer subOrderId) {
		CommentExample example = new CommentExample();
		example.createCriteria().andSubOrderIdEqualTo(subOrderId).andMemberIdEqualTo(memberId).andDelFlagEqualTo(StateConst.FALSE);
		int count = commentMapper.countByExample(example);
		return count > 0;
	}

}
