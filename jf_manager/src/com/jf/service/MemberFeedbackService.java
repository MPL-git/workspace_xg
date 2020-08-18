package com.jf.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.Excel_Bean;
import com.jf.common.utils.MyTool;
import com.jf.dao.MemberFeedbackCustomMapper;
import com.jf.dao.MemberFeedbackMapper;
import com.jf.entity.MemberFeedback;
import com.jf.entity.MemberFeedbackCustom;
import com.jf.entity.MemberFeedbackExample;

@Service
@Transactional
public class MemberFeedbackService extends BaseService<MemberFeedback,MemberFeedbackExample> {
	@Autowired
	private MemberFeedbackMapper memberFeedbackMapper;
	@Autowired
	private MemberFeedbackCustomMapper memberFeedbackCustomMapper;
	
	private Excel_Bean excel;
	
	@Autowired
	public void setMemberFeedbackMapper(MemberFeedbackMapper memberFeedbackMapper) {
		super.setDao(memberFeedbackMapper);
		this.memberFeedbackMapper = memberFeedbackMapper;
	}
	
	public int countMemberFeedbackCustomByExample(MemberFeedbackExample example){
		return memberFeedbackCustomMapper.countByExample(example);
	}
    public List<MemberFeedbackCustom> selectMemberFeedbackCustomByExample(MemberFeedbackExample example){
    	return memberFeedbackCustomMapper.selectByExample(example);
    }
    public MemberFeedbackCustom selectMemberFeedbackCustomByPrimaryKey(Integer id){
    	return memberFeedbackCustomMapper.selectByPrimaryKey(id);
    }
    public List<Map<String, Object>> getMemberfeedbackList() {
		return memberFeedbackCustomMapper.getMemberfeedbackList();
	}
	public void createExcel(Excel_Bean excelBean) {
		this.excel = excelBean;

		HSSFSheet sheet = excel.workbook.createSheet(excel.titleStr);
		String str[] = new String[] { "会员ID", "会员昵称", "手机号码", "反馈类型", "反馈内容", "反馈时间","系统","手机型号","APP版本"};

		int columnNum = MyTool.createHeader(excelBean.workbook, sheet, excelBean.titleStr, str);

		HSSFRow[] rows = MyTool.init_table(sheet, 2, columnNum, null, null, excel);

		// 
		String key[] = new String[] { "memberId", "nick", "mobile", "typeDesc", "content", "createDate", "phoneSystemName", "phoneModel", "appVersion"};
		MyTool.setExcle_data(excel.datalist, rows, key, excel);
	}
}
