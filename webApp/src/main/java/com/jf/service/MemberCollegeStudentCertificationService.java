package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberCollegeStudentCertificationMapper;
import com.jf.dao.OrderDtlCustomMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.MemberCollegeStudentCertification;
import com.jf.entity.MemberCollegeStudentCertificationExample;
import com.jf.entity.SubOrder;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Pengl
 * @create 2020-03-31 上午 9:41
 */
@Service
@Transactional
public class MemberCollegeStudentCertificationService extends BaseService<MemberCollegeStudentCertification, MemberCollegeStudentCertificationExample> {

	@Autowired
	private MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper;

	@Autowired
	private OrderDtlCustomMapper orderDtlCustomMapper;

	@Autowired
	private SubOrderMapper subOrderMapper;

	@Autowired
	public void setMemberCollegeStudentCertificationMapper(MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper) {
		this.setDao(memberCollegeStudentCertificationMapper);
		this.memberCollegeStudentCertificationMapper = memberCollegeStudentCertificationMapper;
	}

	public void addCollegeStudentCertification(JSONObject reqDataJson, Integer memberId) {
		String pic = reqDataJson.getString("pic");
		pic = StringUtil.replace(pic,"xgbuy.cc");
		MemberCollegeStudentCertificationExample cscExample = new MemberCollegeStudentCertificationExample();
		cscExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
		List<MemberCollegeStudentCertification> cscList = memberCollegeStudentCertificationMapper.selectByExample(cscExample);
		if(cscList.size() > 0 ) {
			MemberCollegeStudentCertification memberCollegeStudentCertification = cscList.get(0);
			memberCollegeStudentCertification.setPic(pic);
			memberCollegeStudentCertification.setStatus("1");
			memberCollegeStudentCertification.setCommitDate(DateUtil.getDate());
			memberCollegeStudentCertification.setAuditBy(null);
			memberCollegeStudentCertification.setAuditDate(null);
			memberCollegeStudentCertification.setAuditReasonStatus(null);
			memberCollegeStudentCertification.setUpdateDate(DateUtil.getDate());
			memberCollegeStudentCertificationMapper.updateByPrimaryKey(memberCollegeStudentCertification);
			Integer subOrderId = orderDtlCustomMapper.getCollegeStudentSubOrderId(memberId);
			SubOrder subOrder = new SubOrder();
			subOrder.setId(subOrderId);
			subOrder.setAuditStatus("2");
			subOrderMapper.updateByPrimaryKeySelective(subOrder);
		}else {
			MemberCollegeStudentCertification memberCollegeStudentCertification = new MemberCollegeStudentCertification();
			memberCollegeStudentCertification.setMemberId(memberId);
			memberCollegeStudentCertification.setStatus("1");
			memberCollegeStudentCertification.setPic(pic);
			memberCollegeStudentCertification.setCommitDate(DateUtil.getDate());
			memberCollegeStudentCertification.setCreateBy(memberId);
			memberCollegeStudentCertification.setCreateDate(DateUtil.getDate());
			memberCollegeStudentCertificationMapper.insertSelective(memberCollegeStudentCertification);
		}

	}

}
