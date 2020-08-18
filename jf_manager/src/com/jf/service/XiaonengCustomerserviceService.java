package com.jf.service;

import com.jf.dao.MchtInfoMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.dao.XiaonengcustomerserviceMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.PlatformMsg;
import com.jf.entity.Xiaonengcustomerservice;
import com.jf.entity.XiaonengcustomerserviceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class XiaonengCustomerserviceService extends BaseService<Xiaonengcustomerservice,XiaonengcustomerserviceExample> {
	@Autowired
	private XiaonengcustomerserviceMapper xiaonengcustomerserviceMapper;
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	

	@Autowired
	public void setMchtSettleOrderMapper(XiaonengcustomerserviceMapper xiaonengcustomerserviceMapper) {
		super.setDao(xiaonengcustomerserviceMapper);
		this.xiaonengcustomerserviceMapper = xiaonengcustomerserviceMapper;
	}
	
	public int countByExample(XiaonengcustomerserviceExample example){
		return xiaonengcustomerserviceMapper.countByExample(example);
	}
	
	public List<Xiaonengcustomerservice> selectByExample(XiaonengcustomerserviceExample example){
		return xiaonengcustomerserviceMapper.selectByExample(example);
	}
	public void insertsale(Xiaonengcustomerservice xiaonengcustomerservice){
		   xiaonengcustomerserviceMapper.insertSelective(xiaonengcustomerservice);
			
    }
		
	public void updatesale(Xiaonengcustomerservice xiaonengcustomerservice) {
	      xiaonengcustomerserviceMapper.updateByPrimaryKeySelective(xiaonengcustomerservice);
	} 
	
	/**
	 * 
	 * @Title addOrUpdateXiaonengCustomerService   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月23日 上午10:14:29
	 */
	public void addOrUpdateXiaonengCustomerService(HttpServletRequest request, Xiaonengcustomerservice xiaonengCustomerService, Integer staffId) {
		Date date = new Date();
		String mchtId = request.getParameter("mchtId");
		String xiaonengPwd = request.getParameter("xiaonengPwd"); //初始密码
		String platformMsgStatus = request.getParameter("platformMsgStatus"); // 0 不发送站内信  1 发送站内信
		if(xiaonengCustomerService.getId() == null) {
			xiaonengCustomerService.setCreateBy(staffId);
			xiaonengCustomerService.setCreateDate(date);
			xiaonengCustomerService.setDelFlag("0");
			xiaonengcustomerserviceMapper.insertSelective(xiaonengCustomerService);
			MchtInfo mchtInfo = new MchtInfo();
			mchtInfo.setId(Integer.parseInt(mchtId));
			mchtInfo.setXiaonengId(xiaonengCustomerService.getId());
			mchtInfo.setCustomServiceType("2");
			mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		}else {
			xiaonengCustomerService.setUpdateBy(staffId);
			xiaonengCustomerService.setUpdateDate(date);
			xiaonengCustomerService.setDelFlag("0");
			xiaonengcustomerserviceMapper.updateByPrimaryKeySelective(xiaonengCustomerService);
			MchtInfo mchtInfo = new MchtInfo();
			mchtInfo.setId(Integer.parseInt(mchtId));
			mchtInfo.setXiaonengId(xiaonengCustomerService.getId());
			mchtInfo.setCustomServiceType("2");
			mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		}
		if(platformMsgStatus != null && "1".equals(platformMsgStatus)) {
			PlatformMsg platformMsg = new PlatformMsg();
			platformMsg.setMchtId(Integer.parseInt(mchtId));
			platformMsg.setMsgType("TZ");
			platformMsg.setTitle("关于小能客服开通通知");
			platformMsg.setContent("您好，贵公司的客服ID：JE"+xiaonengCustomerService.getBusId()+"，账号：admin，初始密码："+xiaonengPwd+"。请您尽快完善最新版在线客服软件服务内容，具体信息请查看商家管理后台“帮助中心”操作文档内容。使用过程如有问题请联系平台运营经理，感谢您一直以来对醒购商城的支持与信赖。特别提醒您：请及时修改初始密码，并妥当保管密码。");
			platformMsg.setBizId(xiaonengCustomerService.getId());
			platformMsg.setStatus("0"); //0：未读  1：已读
			platformMsg.setCreateBy(staffId);
			platformMsg.setCreateDate(date);
			platformMsg.setDelFlag("0");
			platformMsgMapper.insertSelective(platformMsg);
		}
	}
	
	/**
	 * 
	 * @Title updateXiaonengCustomerService   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月23日 下午12:21:06
	 */
	public void updateXiaonengCustomerService(HttpServletRequest request, Xiaonengcustomerservice xiaonengCustomerService, Integer staffId) {
		Date date = new Date();
		String mchtId = request.getParameter("mchtId");
		String xiaonengPwd = request.getParameter("xiaonengPwd"); //初始密码
		String platformMsgStatus = request.getParameter("platformMsgStatus"); // 0 不发送站内信  1 发送站内信
		if(xiaonengCustomerService.getId() != null) {
			xiaonengCustomerService.setCreateBy(staffId);
			xiaonengCustomerService.setCreateDate(date);
			xiaonengCustomerService.setDelFlag("0");
			xiaonengcustomerserviceMapper.updateByPrimaryKeySelective(xiaonengCustomerService);
		}
		if(platformMsgStatus != null && "1".equals(platformMsgStatus)) {
			PlatformMsg platformMsg = new PlatformMsg();
			platformMsg.setMchtId(Integer.parseInt(mchtId));
			platformMsg.setMsgType("TZ");
			platformMsg.setTitle("关于小能客服修改通知");
			platformMsg.setContent("您好，贵公司的客服ID：JE"+xiaonengCustomerService.getBusId()+"，账号：admin，初始密码："+xiaonengPwd+"。请您尽快完善最新版在线客服软件服务内容，具体信息请查看商家管理后台“帮助中心”操作文档内容。使用过程如有问题请联系平台运营经理，感谢您一直以来对醒购商城的支持与信赖。特别提醒您：请及时修改初始密码，并妥当保管密码。");
			platformMsg.setBizId(xiaonengCustomerService.getId());
			platformMsg.setStatus("0"); //0：未读  1：已读
			platformMsg.setCreateBy(staffId);
			platformMsg.setCreateDate(date);
			platformMsg.setDelFlag("0");
			platformMsgMapper.insertSelective(platformMsg);
		}
	}
	
	/**
	 * 
	 * @Title delXiaoneng   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月23日 下午2:25:11
	 */
	public void delXiaoneng(Integer staffId, Integer xiaonengId, Integer mchtId) {
		Date date = new Date();
		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(mchtId);
		mchtInfo.setXiaonengId(null);
		mchtInfo.setUpdateBy(staffId);
		mchtInfo.setUpdateDate(date);
		mchtInfoMapper.updateByPrimaryKey(mchtInfo);
		Xiaonengcustomerservice xiaonengCustomerService = new Xiaonengcustomerservice();
		xiaonengCustomerService.setId(xiaonengId);
		xiaonengCustomerService.setUpdateBy(staffId);
		xiaonengCustomerService.setUpdateDate(date);
		xiaonengCustomerService.setDelFlag("0");
		xiaonengcustomerserviceMapper.updateByPrimaryKeySelective(xiaonengCustomerService);
	}

	/**
	 *
	 * @Title compoundXiaoneng
	 * @Description 合成小能信息
	 * @author dlj
	 * @date 2020年3月14日
	 */
	public void compoundXiaoneng(MchtInfo mchtInfo,Integer staffId) {

		String mchtCode = mchtInfo.getMchtCode();
		Xiaonengcustomerservice xiaonengCustomerService = new Xiaonengcustomerservice();
		String busId = mchtCode.substring(1);//企业ID取商家序号去除首字母P
		xiaonengCustomerService.setBusId(Integer.parseInt(busId));
		String companyName = mchtInfo.getCompanyName();//商家名称
		xiaonengCustomerService.setMchtName(companyName);
		String account = "admin";//商家账号默认为admin
		xiaonengCustomerService.setAccount(account);
		int i = (int) ((Math.random() * 9 + 1) * 100000);
		String password = "" + i;//密码:6位数字,随机数
		xiaonengCustomerService.setPassword(password);
		String productType = mchtInfo.getProductType();//主营类目,暂时没有调用到,未加到数据库,但是可以直接根据商家查到

		String code = "je_"+ busId + "_9999";//客服代码
		xiaonengCustomerService.setCode(code);
		String status = "2";//启用状态:未启用
		xiaonengCustomerService.setStatus(status);
		xiaonengCustomerService.setCreateBy(staffId);
		xiaonengCustomerService.setCreateDate(new Date());
		xiaonengcustomerserviceMapper.insertSelective(xiaonengCustomerService);
		MchtInfo m = new MchtInfo();
		m.setId(mchtInfo.getId());
		m.setXiaonengId(xiaonengCustomerService.getId());
		m.setCustomServiceType("2");
		mchtInfoMapper.updateByPrimaryKeySelective(m);
	}

	//性能浪费在校验是否密码唯一,好像不太必要,暂时不用
	public String checkPassword(String password){
		XiaonengcustomerserviceExample where = new XiaonengcustomerserviceExample();
		where.createCriteria().andPasswordEqualTo(password);
		List<Xiaonengcustomerservice> xiaonengCustomerServices = xiaonengcustomerserviceMapper.selectByExample(where);
		if (xiaonengCustomerServices.size()>0){
			int i = (int) ((Math.random() * 9 + 1) * 100000);
			password = "" + i;//密码:6位数字,随机数
			checkPassword(password);
		}
		return password;
	}
}
