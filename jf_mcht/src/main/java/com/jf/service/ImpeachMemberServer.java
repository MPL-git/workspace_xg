package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ImpeachHandleLogMapper;
import com.jf.dao.ImpeachMemberCustomMapper;
import com.jf.dao.ImpeachMemberMapper;
import com.jf.dao.ImpeachMemberProofMapper;
import com.jf.entity.ImpeachMember;
import com.jf.entity.ImpeachMemberCustom;
import com.jf.entity.ImpeachMemberCustomExample;
import com.jf.entity.ImpeachMemberExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


//举报会员
@Service
@Transactional
public class ImpeachMemberServer  extends BaseService<ImpeachMember, ImpeachMemberExample> {

	@Autowired
	private ImpeachMemberMapper dao;
	
	@Autowired
	private ImpeachMemberCustomMapper impeachMemberCustomMapper;
	
	@Autowired
	private ImpeachMemberProofMapper impeachMemberProofMapper;
	
	@Autowired
	private ImpeachHandleLogMapper impeachHandleLogMapper;
	
	@Autowired
	public void setImpeachMemberMapper(ImpeachMemberMapper impeachMemberMapper) {
		super.setDao(impeachMemberMapper);
		this.dao = impeachMemberMapper;
	}
	
	public List<ImpeachMemberCustom> selectImpeachMemberCustomByExample(ImpeachMemberCustomExample example) {
		return impeachMemberCustomMapper.selectByExample(example);
		
	}
	
	public int countImpeachMemberCustomByExample(ImpeachMemberCustomExample example) {
		return impeachMemberCustomMapper.countByExample(example);
	}
	
	//保存举报会员表（bu_impeach_member）
	public void impeachMemberSave(ImpeachMember impeachMember) throws ArgException {
		if(StringUtil.isEmpty(impeachMember.getName())){
    		throw new ArgException("姓名不能为空");
    	}
    	if(StringUtil.isEmpty(impeachMember.getMobile())){
    		throw new ArgException("手机号不能为空");
    	}
    	if(impeachMember.getId()==null){
			//添加保存举报会员表
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		String code = sdf.format(new Date())+CommonUtil.getRandomNum(6);	
    		impeachMember.setCreateBy(0);//创建人
    		impeachMember.setCode(code);//举报编号
    		impeachMember.setStatus("0");//设置举报状态	
    		impeachMember.setSource("0");//设置举报源
    		impeachMember.setCreateDate(new Date());//创建时间
    		impeachMember.setDelFlag("0");//删除标志
    		dao.insertSelective(impeachMember);   			
        	}else{ 
        		//修改 举报会员表
        		impeachMember.setUpdateDate(new Date());
        		impeachMember.setStatus("1");//设置举报状态	
        		dao.updateByPrimaryKeySelective(impeachMember);

        	}
		}
	
	
	
	
}
