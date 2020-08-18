package com.jf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ObligeeNoticeMapper;
import com.jf.entity.IntellectualPropertyRight;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.ObligeeNotice;
import com.jf.entity.ObligeeNoticeExample;


@Service
@Transactional
public class ObligeeNoticeService extends BaseService<ObligeeNotice, ObligeeNoticeExample> {
	
	@Autowired
	private ObligeeNoticeMapper mapper;
	
	@Autowired
	public void setObligeeNoticeMapper(ObligeeNoticeMapper obligeeNoticeMapper) {
		super.setDao(obligeeNoticeMapper);
		this.mapper = obligeeNoticeMapper;
	}
	
	public int countByExample(ObligeeNoticeExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<ObligeeNotice> selectByExample(ObligeeNoticeExample example){
    	return this.mapper.selectByExample(example);
    }
    
    public ObligeeNotice selectByPrimaryKey(Integer id){
    	return this.mapper.selectByPrimaryKey(id);
    }
    
    /**
     * 创建产权审核通知
     * 
     * @param right 
     * @param type 审核状态 （通过：1/驳回：2）
     * @param createBy 审核人
     */
    public void insertRightAuditNotice(IntellectualPropertyRight right, String type, Integer createBy){
    	ObligeeNotice obligeeNotice = new ObligeeNotice();
    	obligeeNotice.setObligeeId(right.getObligeeId());
    	String title = "产权审核通过通知";
    	String content = "您提交的【" + right.getPropertyRightName() + "】已通过审核。";
    	if ("2".equals(type)) {
    		title = "产权审核驳回通知";
    		content = "您提交的【" + right.getPropertyRightName() + "】被驳回。";
    	}
    	obligeeNotice.setTitle(title);
    	//通知类型为产权
    	obligeeNotice.setType("1");
    	obligeeNotice.setLinkId(right.getId());
    	//状态为产权审核
    	obligeeNotice.setProStatus("2");
    	obligeeNotice.setContent(content);
    	obligeeNotice.setIsRead("0");
    	obligeeNotice.setCreateBy(createBy);
    	obligeeNotice.setCreateDate(new Date());
    	obligeeNotice.setDelFlag("0");
    	
    	this.mapper.insert(obligeeNotice);
    }
    
    /**
     * 创建平台受理通知
     * 
     * @param rightAppeal 
     * @param type 审核状态 （通过：1/驳回：2）
     * @param createBy 审核人
     */
    public void insertRightAppealNotice(IntellectualPropertyRightAppeal rightAppeal, String type, Integer createBy){
    	ObligeeNotice obligeeNotice = new ObligeeNotice();
    	obligeeNotice.setObligeeId(rightAppeal.getObligeeId());
    	String title = "平台受理通知";
    	String content = "您的投诉平台已受理，并对商家相应的处罚，等待商家的声明。";
    	if ("2".equals(type)) {
    		title = "平台不受理通知";
    		content = "您提交的投诉不符合受理条件，具体原因请登入平台查看。";
    	}
    	obligeeNotice.setTitle(title);
    	//通知类型为投诉
    	obligeeNotice.setType("2");
    	obligeeNotice.setLinkId(rightAppeal.getId());
    	//状态为平台受理
    	obligeeNotice.setProStatus("1");
    	obligeeNotice.setContent(content);
    	obligeeNotice.setIsRead("0");
    	obligeeNotice.setCreateBy(createBy);
    	obligeeNotice.setCreateDate(new Date());
    	obligeeNotice.setDelFlag("0");
    	
    	this.mapper.insert(obligeeNotice);
    }
    
    /**
     * 创建声明转发通知
     * 
     * @param rightAppeal 
     * @param type 审核状态 （通过：1/多次未按要求整改：2）
     * @param createBy 审核人
     */
    public void insertRightComplainNotice(IntellectualPropertyRightAppeal rightAppeal, String type, Integer createBy){
    	ObligeeNotice obligeeNotice = new ObligeeNotice();
    	obligeeNotice.setObligeeId(rightAppeal.getObligeeId());
    	String title = "关于被投诉人声明";
    	String content = " 被投诉人根据您的投诉已发起申诉，对此您如有异议可以向有关主管部门投诉或者向人民法院起诉，并登入平台填写相关信息。若15天内未在平台登记起诉信息，视为投诉不成立 ，平台将撤销对被投诉人的处理";
    	if ("2".equals(type)) {
    		content = "商家未在规定时间提交声明，平台将维持对商家的处理。";
    	}
    	obligeeNotice.setTitle(title);
    	//通知类型为投诉
    	obligeeNotice.setType("2");
    	obligeeNotice.setLinkId(rightAppeal.getId());
    	//状态为平台受理
    	obligeeNotice.setProStatus("3");
    	obligeeNotice.setContent(content);
    	obligeeNotice.setIsRead("0");
    	obligeeNotice.setCreateBy(createBy);
    	obligeeNotice.setCreateDate(new Date());
    	obligeeNotice.setDelFlag("0");
    	
    	this.mapper.insert(obligeeNotice);
    }
	
    /**
     * 起诉跟进通知
     * 
     * @param rightAppeal 
     * @param type 起诉跟进状态 （成立：1/失败：2）
     * @param createBy 审核人
     */
    public void insertRightProsecutionNotice(IntellectualPropertyRightAppeal rightAppeal, String type, Integer createBy){
    	ObligeeNotice obligeeNotice = new ObligeeNotice();
    	obligeeNotice.setObligeeId(rightAppeal.getObligeeId());
    	String title = "关于起诉信息确认";
    	String content = " 您提交起诉/投诉信息平台已收悉，平台会维持对商家的处罚。感谢您对平台的支持。";
    	if ("2".equals(type)) {
    		content = "您提交起诉信息有误，平台视为您放弃投诉，平台已撤销处罚";
    	}
    	obligeeNotice.setTitle(title);
    	//通知类型为投诉
    	obligeeNotice.setType("2");
    	obligeeNotice.setLinkId(rightAppeal.getId());
    	//状态为起诉信息确认
    	obligeeNotice.setProStatus("5");
    	obligeeNotice.setContent(content);
    	obligeeNotice.setIsRead("0");
    	obligeeNotice.setCreateBy(createBy);
    	obligeeNotice.setCreateDate(new Date());
    	obligeeNotice.setDelFlag("0");
    	
    	this.mapper.insert(obligeeNotice);
    }
    
    /**
     * 起诉结果确认通知
     * 
     * @param rightAppeal 
     * @param type 胜诉方 （权利人：1/商家：2）
     * @param createBy 审核人
     */
    public void insertRightProsecutionResult(IntellectualPropertyRightAppeal rightAppeal, String type, Integer createBy){
    	ObligeeNotice obligeeNotice = new ObligeeNotice();
    	obligeeNotice.setObligeeId(rightAppeal.getObligeeId());
    	String title = "关于起诉信息确认";
    	String content = "经核实，产权投诉有效，平台维持对商家处罚";
//    	if ("2".equals(type)) {
//    		content = "您提交起诉信息有误，平台视为您放弃投诉，平台已撤销处罚";
//    	}
    	obligeeNotice.setTitle(title);
    	//通知类型为投诉
    	obligeeNotice.setType("2");
    	obligeeNotice.setLinkId(rightAppeal.getId());
    	//状态为起诉信息确认
    	obligeeNotice.setProStatus("6");
    	obligeeNotice.setContent(content);
    	obligeeNotice.setIsRead("0");
    	obligeeNotice.setCreateBy(createBy);
    	obligeeNotice.setCreateDate(new Date());
    	obligeeNotice.setDelFlag("0");
    	
    	this.mapper.insert(obligeeNotice);
    }
}
