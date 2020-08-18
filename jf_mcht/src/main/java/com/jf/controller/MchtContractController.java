package com.jf.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ExpressMapper;
import com.jf.dao.MchtBlPicMapper;
import com.jf.dao.SysStaffInfoMapper;
import com.jf.entity.*;
import com.jf.entity.MchtContactExample.Criteria;
import com.jf.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 入驻与合同管理
 */
@Controller
@RequestMapping("mcht/contract")
public class MchtContractController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MchtContractController.class);
	
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private MchtContractService mchtContractService;
	@Resource
	private MchtContractPicService mchtContractPicService;
	@Resource
	private MchtTaxInvoiceInfoService mchtTaxInvoiceInfoService;
	@Resource
	private MchtBankAccountService mchtBankAccountService;
	@Resource
	private MchtContactService mchtContactService;
	@Resource
	private MchtBrandAptitudePicService mchtBrandAptitudePicService;
	@Resource
	private MchtPlatformAuthPicService mchtPlatformAuthPicService;
	@Resource
	private MchtBrandInvoicePicService mchtBrandInvoicePicService;
	@Resource
	private MchtBrandInspectionPicService mchtBrandInspectionPicService;
	@Resource
	private MchtBrandOtherPicService mchtBrandOtherPicService;
	@Resource
	private ExpressService expressService;
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	@Autowired
	private PlatformContactService platformContactService;
	@Autowired
	private MchtProductTypeService mchtProductTypeService;
	@Autowired
	private PlatformCapitalAccountService platformCapitalAccountService;
	@Autowired
	private MchtDepositService mchtDepositService;
	@Autowired
	private DepositOrderService depositOrderService;
	@Autowired
	private SysStaffInfoMapper sysStaffInfoMapper;
	@Autowired
	private ExpressMapper expressMapper;
	@Autowired
	private MchtBlPicMapper mchtBlPicMapper;
	@Autowired
	private MchtBrandAptitudeService mchtBrandAptitudeService;
	@Resource
	private MchtBrandOtherPicServer mchtBrandOtherPicServer;
	@Resource
	private MchtBrandInvoicePicServer mchtBrandInvoicePicServer;
	@Resource
	private MchtBrandInspectionPicServer mchtBrandInspectionPicServer;
	@Resource
	private MchtBrandProductTypeService mchtBrandProductTypeService;
	@Resource
	private MchtBlPicService mchtBlPicService;
	@Resource
	private AreaService areaService;
	@Resource
	private ContractRenewLogService contractRenewLogService;
	@RequestMapping
	public String index() {
		Map<String, Object> data = new HashMap<>();
		MchtUser currentUser = getUserInfo();
		MchtInfoCustom	mchtInfo = mchtInfoService.selectMchtInfoCustomById(currentUser.getMchtId());
		MchtContract contract = mchtContractService.findByMchtId(mchtInfo.getId());
		
		String page;
		if(!Const.MCHT_TOTAL_AUDIT_STATUS_PASS.equals(mchtInfo.getTotalAuditStatus())){
			// 总资质审核状态不通过
			page = "mchtContract/company_perfect";	// 完善店铺资质页面

			//公司资料是否已完善
			boolean isPerfect = "1".equals(mchtInfo.getIsCompanyInfPerfect()) || "2".equals(mchtInfo.getIsCompanyInfPerfect()) || "3".equals(mchtInfo.getIsCompanyInfPerfect()) || "4".equals(mchtInfo.getIsCompanyInfPerfect());

			// 店铺名完善状态
			isPerfect = isPerfect && ("1".equals(mchtInfo.getShopNameAuditStatus()) || "2".equals(mchtInfo.getShopNameAuditStatus()) || "3".equals(mchtInfo.getShopNameAuditStatus()) || "4".equals(mchtInfo.getShopNameAuditStatus()));
			String shopNamePerfectDesc = "";
			if(mchtInfo.getShopNameAuditStatus().equals("0")){
				shopNamePerfectDesc = "未填写";
			}else if(mchtInfo.getShopNameAuditStatus().equals("1")){
				shopNamePerfectDesc = "已填写";
			}else if(mchtInfo.getShopNameAuditStatus().equals("2")){
				shopNamePerfectDesc = "待审核";
			}else if(mchtInfo.getShopNameAuditStatus().equals("3")){
				shopNamePerfectDesc = "通过";
			}else if(mchtInfo.getShopNameAuditStatus().equals("4")){
				shopNamePerfectDesc = "驳回";
			}
			data.put("shopNamePerfectDesc", shopNamePerfectDesc);

			// 结算银行信息
			MchtBankAccount mchtBankAccount = mchtBankAccountService.findByMchtId(mchtInfo.getId());
			isPerfect = isPerfect && mchtBankAccount != null;
			data.put("mchtBankAccount", mchtBankAccount);
			// 结算银行信息完善状态
			//data.put("mchtBankAccountPerfectDesc", Const.getMchtBankStatusStr(mchtBankAccount != null ? mchtBankAccount.getStatus() : null));
			data.put("mchtBankAccountPerfectDesc", (mchtBankAccount == null ? "未完善" :   ( "3".equals(mchtBankAccount.getStatus())?"驳回":"已填写"  )     ));

			// 经营类目
			QueryObject queryObject = new QueryObject();
			List<String> statusIn = new ArrayList<>();
			statusIn.add("0");	//申请中
			statusIn.add("1");	//正常
			queryObject.addQuery("statusIn", statusIn);
			List<ProductType> productTypeList = productTypeService.findListOfTopLevelByMchtId(mchtInfo.getId(), queryObject);
			isPerfect = isPerfect && productTypeList.size() > 0;
			data.put("productTypeList", productTypeList);

			// 经营品牌
			MchtProductBrandCustomExample e = new MchtProductBrandCustomExample();
			MchtProductBrandCustomExample.MchtProductBrandCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMchtIdEqualTo(mchtInfo.getId());
			c.andProductBrandIdIsNotEqualTo();
			List<MchtProductBrandCustom> mchtProductBrandCustomList = mchtProductBrandService.selectMchtProductBrandCustomByExample(e);
			for(MchtProductBrandCustom mchtProductBrandCustom : mchtProductBrandCustomList){
				String zsAuditStatusDesc = "";
				String auditStatusDesc = "";
				if(StringUtil.isEmpty(mchtProductBrandCustom.getZsAuditStatus()) || mchtProductBrandCustom.getZsAuditStatus().equals("0")){
					zsAuditStatusDesc = "未提交审核";
				}else if(mchtProductBrandCustom.getZsAuditStatus().equals("1")){
					if(StringUtil.isEmpty(mchtProductBrandCustom.getIsZsAuditRecommit()) || mchtProductBrandCustom.getIsZsAuditRecommit().equals("0")){
						zsAuditStatusDesc = "首次提审";
					}else{
						zsAuditStatusDesc = "重新提审";
					}
				}else if(mchtProductBrandCustom.getZsAuditStatus().equals("2")){//2.招商通过
					zsAuditStatusDesc = "通过";
					if(StringUtil.isEmpty(mchtProductBrandCustom.getAuditStatus()) || mchtProductBrandCustom.getAuditStatus().equals("0")){
						auditStatusDesc = "未提审";
					}else if(mchtProductBrandCustom.getAuditStatus().equals("1")){//法务状态：0 未提审 1待审
						if(StringUtil.isEmpty(mchtProductBrandCustom.getIsAuditRecommit()) || mchtProductBrandCustom.getIsAuditRecommit().equals("0")){
							auditStatusDesc = "首次提审";
						}else{
							auditStatusDesc = "重新提审";
						}
					}else if(mchtProductBrandCustom.getAuditStatus().equals("3")){//法务状态3.审核通过(原完善)
						auditStatusDesc = "通过";
					}else if(mchtProductBrandCustom.getAuditStatus().equals("4")){
						auditStatusDesc = "驳回";
					}else if(mchtProductBrandCustom.getAuditStatus().equals("5") || mchtProductBrandCustom.getAuditStatus().equals("6")){
						auditStatusDesc = "异常";
					}
				}else if(mchtProductBrandCustom.getZsAuditStatus().equals("4")){
					zsAuditStatusDesc = "驳回";
				}else if(mchtProductBrandCustom.getZsAuditStatus().equals("5") || mchtProductBrandCustom.getZsAuditStatus().equals("6")){//5 不签约,6 黑名单
					zsAuditStatusDesc = "异常";
				}
				mchtProductBrandCustom.put("zsAuditStatusDesc", zsAuditStatusDesc);
				mchtProductBrandCustom.put("auditStatusDesc", auditStatusDesc);
			}
			if(mchtInfo.getSettledType().equals("1")){//1.公司企业
				isPerfect = isPerfect && mchtProductBrandCustomList.size() > 0;
			}else if(mchtInfo.getSettledType().equals("2")){//2.个体商户
				isPerfect = true;
			}
			data.put("productBrandList", mchtProductBrandCustomList);

			if("1".equals(mchtInfo.getIsManageSelf())){//自营不需要验证
				// 电商总负责人
				MchtContact contactAll = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_ALL);
				data.put("contactAll", contactAll);

				// 运营对接人
				MchtContact contactOperation = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_OPERATION);
				data.put("contactOperation", contactOperation);

				// 订单对接人
				MchtContact contactOrder = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_ORDER);
				data.put("contactOrder", contactOrder);

				// 售后对接人
				MchtContact contactCustomerService = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_ORDER_AFTER);
				data.put("contactCustomerService", contactCustomerService);

				// 客服对接人
				MchtContact contactCustomer = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_CUSTOMER);
				data.put("contactCustomer", contactCustomer);
			}else{//非自营状态需要验证是否填写完整
				// 电商总负责人
				MchtContact contactAll = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_ALL);
				isPerfect = isPerfect && contactAll != null;
				data.put("contactAll", contactAll);

				// 运营对接人
				MchtContact contactOperation = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_OPERATION);
				isPerfect = isPerfect && contactOperation != null;
				data.put("contactOperation", contactOperation);

				// 订单对接人
				MchtContact contactOrder = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_ORDER);
				isPerfect = isPerfect && contactOrder != null;
				data.put("contactOrder", contactOrder);

				// 售后对接人
				MchtContact contactCustomerService = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_ORDER_AFTER);
				isPerfect = isPerfect && contactCustomerService != null;
				data.put("contactCustomerService", contactCustomerService);

				// 客服对接人
				MchtContact contactCustomer = mchtContactService.findByMchtId(mchtInfo.getId(), Const.MCHT_CONTACT_TYPE_CUSTOMER);
				isPerfect = isPerfect && contactCustomer != null;
				data.put("contactCustomer", contactCustomer);
			}


			data.put("isPerfect", isPerfect);

			if(StrKit.notBlank(currentUser.getMobile())){
				data.put("linkMobile", getUserInfo().getMobile().substring(0, 3) + "****");
			}

		}else{
			//合同是否已OK
			boolean isContractOk = !Const.MCHT_STATUS_WAIT.equals(mchtInfo.getStatus());	// 合作状态不等于入驻中
			isContractOk = isContractOk && contract != null && Const.MCHT_CONTRACT_ARCHIVE_STATUS_PASS.equals(contract.getArchiveStatus());	// 并且合同已归档
			if(isContractOk){
				page = "mchtContract/contract_index";	// 合同信息页面
			}else{
				data.put("contract", contract);
				// 结算银行信息
				MchtBankAccount mchtBankAccount = mchtBankAccountService.findByMchtId(mchtInfo.getId());
				data.put("mchtBankAccount", mchtBankAccount);
				page = "mchtContract/contract_edit";	// 页面
			}

		}
		if(mchtInfo.getContractDeposit()!=null){
			DecimalFormat df = new DecimalFormat("#.00");
			data.put("contractDeposit", df.format(mchtInfo.getContractDeposit()));
		}
		data.put("mchtInfo", mchtInfo);
		return page(data, page);
	}

	/**
	 * 商家总资质提审
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("mchtInfoCommit")
	public String mchtInfoCommit() throws ParseException {
		mchtInfoService.commitTotalInfo(getMchtInfo().getId());
		return json();
	}

	/**
	 * 合同预览
	 */
	@RequestMapping("contractPreview")
	public String contractPreview(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			MchtContract contract = mchtContractService.selectByPrimaryKey(Integer.parseInt(id));
			data.put("contract", contract);
		}else{
			MchtInfoCustom mchtInfo = getMchtInfo();
			MchtContract contract = mchtContractService.findByMchtId(mchtInfo.getId());
			data.put("mchtInfo", mchtInfo);
			data.put("contract", contract);
		}
		return page(data, "mchtContract/contract_preview");
	}

	/**
	 * 查看合同PDF
	 */
	@RequestMapping("contractPDF")
	public void contractPDF(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		MchtContract contract = new MchtContract();
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty("id")){
			contract = mchtContractService.selectByPrimaryKey(Integer.parseInt(id));
		}else{
			MchtInfoCustom mchtInfo = getMchtInfo();
			contract = mchtContractService.findByMchtId(mchtInfo.getId());
		}
		Assert.notNull(contract, "合同还没生成，不能查看合同扫描件");

		//设置响应内容类型为PDF类型
		response.setContentType("application/pdf");
		ServletOutputStream sos = response.getOutputStream();
		//不在网页中打开，而是直接下载该文件，下载后的文件名为“Example.pdf”
		//response.setHeader("Content-disposition", "attachment;filename=Example.pdf");

		File file = FileUtil.getFile(contract.getFilePath());
		response.setContentLength((int) file.length());
		FileInputStream fis = new FileInputStream(file);

		byte[] buffer = new byte[1024*1024];
		int readBytes = -1;
		while((readBytes = fis.read(buffer, 0, 1024*1024)) != -1){
			sos.write(buffer, 0, 1024*1024);
		}
		if(sos!=null){
			sos.close();
		}
		if(fis!=null){
			fis.close();
		}
	}


	/**
	 * 查看合同扫描件
	 */
	@RequestMapping("contractPic")
	public String contractPic() {
		MchtInfoCustom mchtInfo = getMchtInfo();
		MchtContract contract = mchtContractService.findByMchtId(mchtInfo.getId());
		MchtInfo mchtInfoCustom = mchtInfoService.selectByPrimaryKey(mchtInfo.getId());
		Assert.notNull(contract, "合同还没生成，不能查看合同扫描件");

		Map<String, Object> data = new HashMap<>();
		data.put("mchtInfo", mchtInfoCustom);
		data.put("contract", contract);
		data.put("mchtContractPicList", mchtContractPicService.listByContractId(contract.getId()));
		return page(data, "mchtContract/contract_pic");
	}

	/**
	 * 合同扫描件上传【提交审核】
	 */
	@ResponseBody
	@RequestMapping("contractPicUpload")
	public String contractPicUpload(HttpServletRequest request) {
		MchtInfo mchtInfo = getMchtInfo();
		MchtContract contract = new MchtContract();
		if(StringUtils.equals(request.getParameter("type"), "1")){
			contract = mchtContractService.findByMchtId(mchtInfo.getId());
		}else{
			contract = mchtContractService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		}

		String isNeedUpload = request.getParameter("isNeedUpload");
		if(!StringUtil.isBlank(isNeedUpload)){//修改是否需要上传的状态
			contract.setIsNeedUpload(isNeedUpload);
			mchtContractService.updateByPrimaryKeySelective(contract);
		}

		Assert.notNull(contract, "未找到相应的合同");

		JSONArray imageArray = JSONArray.parseArray(getPara("images"));

		if(!"0".equals(isNeedUpload) && (imageArray == null || imageArray.size() == 0)){
			throw new BizException("请上传合同扫描件");
		}
		if(imageArray.size() > 30){
			throw new BizException("请不要上传太多附件");
		}


		// 上传图片
		mchtContractPicService.deleteByContractId(contract.getId());
		JSONObject jsonObject;
		for (int i = 0; i < imageArray.size(); i ++) {
			jsonObject = imageArray.getJSONObject(i);
			mchtContractPicService.save(mchtInfo.getId(), contract.getId(), jsonObject.getString("pic"));
		}

		//提交审核
		mchtContractService.commit(contract.getId());
		return json();
	}

	@RequestMapping("renewContractPic")
	public String renewContractPic(HttpServletRequest request) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		MchtContract contract = mchtContractService.selectByPrimaryKey(id);
		Assert.notNull(contract, "合同还没生成，不能查看合同扫描件");

		Map<String, Object> data = new HashMap<>();
		data.put("contract", contract);
		data.put("mchtContractPicList", mchtContractPicService.listByContractId(contract.getId()));
		return page(data, "mchtContract/renewContract_pic");
	}

	/**
	 * 查看合同寄件内容（寄件清单）
	 */
	@RequestMapping("contractExpressList")
	public String contractExpressList() {
		MchtInfoCustom mchtInfo = getMchtInfo();
		MchtContract contract = mchtContractService.findByMchtId(mchtInfo.getId());
		Assert.notNull(contract, "未找到相应的合同");

		Map<String, Object> data = new HashMap<>();
		// 合同扫描件
		data.put("mchtContractPicList", mchtContractPicService.listByContractId(contract.getId()));

		// 法人身份证、营业执照副本、组织机构代码证、税务登记证、一般纳税人资格证、银行开户许可证
		data.put("mchtInfo", mchtInfo);
		
		MchtBlPicExample example = new MchtBlPicExample();
		example.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId());
		List<MchtBlPic> mchtBlPics = mchtBlPicMapper.selectByExample(example);
		if(mchtBlPics!=null && mchtBlPics.size()>0){
			data.put("mchtBlPics", mchtBlPics);
		}
		
		// 结算银行确认函
		MchtBankAccount mchtBankAccount = mchtBankAccountService.findByMchtId(mchtInfo.getId());
		if(mchtBankAccount != null){
			data.put("mchtBankAccountConfirmFile", mchtBankAccount.getConfirmFile());
		}

		// 税务信息确认函
		MchtTaxInvoiceInfo mchtTaxInvoiceInfo = mchtTaxInvoiceInfoService.findByMchtId(mchtInfo.getId());
		if(mchtTaxInvoiceInfo != null){
			data.put("mchtTaxInvoiceInfoConfirmFile", mchtTaxInvoiceInfo.getConfirmFile());
		}

		//品牌资质
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId()).andStatusEqualTo("1");//1.正常
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectMchtProductBrandCustomByExample(mpbe);
		for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
			//商标注册证
			MchtBrandAptitudeExample e = new MchtBrandAptitudeExample();
			MchtBrandAptitudeExample.Criteria criteria = e.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(e);
			for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
				mbapec.andDelFlagEqualTo("0");
				mbapec.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
				List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
			}
			mchtProductBrandCustom.setMchtBrandAptitudeCustoms(mchtBrandAptitudeCustoms);
			// 授权图片
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
			mchtProductBrandCustom.setMchtPlatformAuthPics(mchtPlatformAuthPics);
			
			//进货发票
			MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicService.selectByExample(mchtBrandInvoicePicExample);
			mchtProductBrandCustom.setMchtBrandInvoicePics(mchtBrandInvoicePics);
			//质检
			MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicService.selectByExample(mchtBrandInspectionPicExample);
			mchtProductBrandCustom.setMchtBrandInspectionPics(mchtBrandInspectionPics);
			
			// 其他
			MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicService.selectByExample(mchtBrandOtherPicExample);
			mchtProductBrandCustom.setMchtBrandOtherPics(mchtBrandOtherPics);
		}
		data.put("mchtProductBrandCustoms", mchtProductBrandCustoms);

		return page(data, "mchtContract/contract_expressList");
	}

	/**
	 * 查看合同寄件信息（快递信息）
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("contractExpressInfo")
	public String contractExpressInfo() {
		MchtInfoCustom mchtInfo = getMchtInfo();
		MchtInfo mc = mchtInfoService.selectByPrimaryKey(mchtInfo.getId());
		MchtContract contract = mchtContractService.findByMchtId(mchtInfo.getId());
		Assert.notNull(contract, "未找到相应的合同");

		Map<String, Object> data = new HashMap<>();
		data.put("contract", contract);
		data.put("mchtInfo", mc);
		//获取最新店铺总负责人
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		criteria.andMchtIdEqualTo(mchtInfo.getId()).andContactTypeEqualTo("1").andIsPrimaryEqualTo(Const.FLAG_TRUE).andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> contact = mchtContactService.selectByExample(mchtContactExample);
		MchtContact contacts = new MchtContact();
		if(!contact.isEmpty()){
			contacts = contact.get(0);
		data.put("mchtContact", contacts);
		}
		//获取省市区名称
		List<Area> areas = (List<Area>) areaService.getAddress(contacts);
		String address = "";
		for (Area area : areas) {
			address +=area.getAreaName();
		}
		address += contacts.getAddress();
		if(StringUtils.equals(address, "null")){
			address = "";
		}
		
		data.put("address",address);
		//法务对接人
		MchtPlatformContact mchtPlatformContact = mchtPlatformContactService.findByMchtId(mchtInfo.getId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
		if(mchtPlatformContact != null){
			PlatformContact platformContact = platformContactService.findById(mchtPlatformContact.getPlatformContactId());
			data.put("platformContact", platformContact);
			SysStaffInfo sysStaffInfo = sysStaffInfoMapper.selectByPrimaryKey(platformContact.getStaffId());
			data.put("staffName", sysStaffInfo.getStaffName());
		}

		// 快递公司
		data.put("expressList", expressService.findList(new QueryObject()));
		return page(data, "mchtContract/contract_expressInfo");
	}

	/**
	 * 修改寄件的店铺总负责人刷新页面 
	 */
	@ResponseBody
	@RequestMapping("freshenContact")
	public Map<String, Object> freshenContact(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		//如果mailing为1，说明没有点击修改按钮，该负责人状态还是审核通过状态，直接获取
		if(StringUtils.equals(request.getParameter("mailing"), "1")){
			MchtContactExample mchtContactExample2 = new MchtContactExample();
			Criteria criteria2 = mchtContactExample2.createCriteria();
			criteria2.andIdEqualTo(Integer.valueOf(request.getParameter("id"))).andDelFlagEqualTo("0");
			List<MchtContact> mchtContactList2 = mchtContactService.selectByExample(mchtContactExample2);
			if(!mchtContactList2.isEmpty()){
				MchtContact contact = mchtContactList2.get(0);
				//获取省市区名称
				List<Area> areaList = areaService.getAddress(contact);
				data.put("contact", contact);
				data.put("areaList", areaList);
			}		
			return data;
		}
		//如果还有审核通过的总负责人，就取ID最大的总负责人
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		//criteria.andMchtIdEqualTo(getMchtInfo().getId()).andAuditStatusEqualTo("1").andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(getMchtInfo().getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" create_date DESC");
		List<MchtContact> mchtContactList = mchtContactService.selectByExample(mchtContactExample);
		if(!mchtContactList.isEmpty()){
			MchtContact contact = mchtContactList.get(0);
			//获取省市区名称
			List<Area> areaList = areaService.getAddress(contact);
			data.put("contact", contact);
			data.put("areaList", areaList);
			return data;
		}else{
			MchtContactExample mchtContactExample2 = new MchtContactExample();
			Criteria criteria2 = mchtContactExample2.createCriteria();
			criteria2.andIdEqualTo(Integer.valueOf(request.getParameter("id"))).andDelFlagEqualTo("0");
			List<MchtContact> mchtContactList2 = mchtContactService.selectByExample(mchtContactExample2);
			if(!mchtContactList2.isEmpty()){
				MchtContact contact = mchtContactList2.get(0);
				//获取省市区名称
				List<Area> areaList = areaService.getAddress(contact);
				data.put("contact", contact);
				data.put("areaList", areaList);
			}		
			return data;
		}	
	}
	
	/**
	 * 保存商家寄件快递信息
	 */
	@ResponseBody
	@RequestMapping("contractExpressInfoCommit")
	public Map<String, Object> contractExpressInfoCommit(HttpServletRequest request) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		Integer id2 = this.getSessionMchtInfo(request).getId();
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtInfo mc = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(!mchtContacts.isEmpty()){
			 MchtContact mchtContact = mchtContactService.selectByExample(mchtContactExample).get(0);
		 if("1".equals(mchtContact.getAuditStatus()) ){
				MchtInfoCustom mchtInfo = getMchtInfo();
				MchtContract contract = mchtContractService.findByMchtId(mchtInfo.getId());
				Assert.notNull(contract, "未找到相应的合同");

				int expressId = getParaToInt("expressId");// 快递公司
				Assert.moreThanZero(expressId, "快递ID不能为空");
				String expressNo = getPara("expressNo");	// 快递单号
				Assert.notBlank(expressNo, "快递单号不能为空");

				mchtContractService.mchtSend(contract.getId(), expressId, expressNo);

		  }else {
				 resMap.put("returnCode", "404");
					resMap.put("returnMsg", "信息正在审核中,请稍后再试");
			 }
		}else if("1".equals(mc.getIsManageSelf())){//自营
			MchtContract contract = mchtContractService.findByMchtId(mc.getId());
			Assert.notNull(contract, "未找到相应的合同");

			int expressId = getParaToInt("expressId");// 快递公司
			Assert.moreThanZero(expressId, "快递ID不能为空");
			String expressNo = getPara("expressNo");	// 快递单号
			Assert.notBlank(expressNo, "快递单号不能为空");

			mchtContractService.mchtSend(contract.getId(), expressId, expressNo);
		}else{
			resMap.put("returnCode", "404");
			resMap.put("returnMsg", "请添加电商总负责人信息");
		}
		return resMap;
	}

	/**
	 * 删除类目
	 */
	@ResponseBody
	@RequestMapping("/deleteMchtProductType")
	public String deleteMchtProductType(HttpServletRequest request) {
		Integer productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
		Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
		MchtProductTypeExample example = new MchtProductTypeExample();
		MchtProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(mchtId);
		criteria.andProductTypeIdEqualTo(productTypeId);
		MchtProductType mchtProductType = new MchtProductType();
		mchtProductType.setDelFlag("1");
		mchtProductTypeService.updateByExampleSelective(mchtProductType, example);
		return json();
	}
	
	/**
	 * 删除经营品牌
	 */
	@ResponseBody
	@RequestMapping("/deleteProductBrand")
	public String deleteProductBrand(HttpServletRequest request) {
		Integer productBrandId = Integer.parseInt(request.getParameter("productBrandId"));
		MchtProductBrand mchtProductBrand = new MchtProductBrand();
		mchtProductBrand.setId(productBrandId);
		mchtProductBrand.setDelFlag("1");
		mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
		return json();
	}
	
	/**
	 * 添加缴纳记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/addDepositOrder")
	public String addDepositOrder(Model model, HttpServletRequest request) {
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		DecimalFormat df = new DecimalFormat("#0.00");
		model.addAttribute("contractDeposit", df.format(mchtInfo.getContractDeposit()));
		model.addAttribute("mchtCode", mchtInfo.getMchtCode());
		return "mchtContract/addDepositOrder";
	}
	
	/**
	 * 保存缴纳保证金
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ParseException 
	 */

	@RequestMapping("/saveDepositOrder")
	@ResponseBody
	public ResponseMsg saveDepositOrder(HttpServletRequest request) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String amount = request.getParameter("amount");
		String paymentNo = request.getParameter("paymentNo");
		String paymentName = request.getParameter("paymentName");
		String accountNo = request.getParameter("accountNo");
		String accountName = request.getParameter("accountName");
		String payDate = request.getParameter("payDate");
		String remarks = request.getParameter("remarks");
		String proofPic = request.getParameter("proofPic");
		
		DepositOrder depositOrder = new DepositOrder();
		depositOrder.setCreateBy(this.getMchtInfo().getId());
		depositOrder.setCreateDate(new Date());
		depositOrder.setMchtId(this.getMchtInfo().getId());
		depositOrder.setDelFlag("0");
		depositOrder.setAmount(new BigDecimal(amount));
		depositOrder.setPaymentType("3");//银行
		depositOrder.setPaymentNo(paymentNo);
		depositOrder.setStatus("0");
		List<PlatformCapitalAccount> privateBankAccountList  = platformCapitalAccountService.getPlatformCapitalAccountListByPaymentTypeAndAccountType("3","2");
		depositOrder.setAccountId(privateBankAccountList.get(0).getId());
		depositOrder.setAccountName(accountName);
		depositOrder.setAccountNo(accountNo);
		depositOrder.setPaymentName(paymentName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		depositOrder.setPayDate(sdf.parse(payDate+" 00:00:00"));
		depositOrder.setRemarks(remarks);
		depositOrder.setProofPic(proofPic);
		depositOrderService.insertSelective(depositOrder);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 缴纳记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/depositOrderIndex")
	public String depositOrderIndex(Model model, HttpServletRequest request) {
		return "mchtContract/depositOrderIndex";
	}
	
	/**
	 * 商家保证金明细列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/getDepositOrderList")
	@ResponseBody
	public ResponseMsg getDepositOrderList(HttpServletRequest request,Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		DepositOrderExample example = new DepositOrderExample();
		example.setOrderByClause("id desc");
		DepositOrderExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		int totalCount = depositOrderService.countByExample(example);
		example.setLimitStart(page.getLimitStart());
		example.setLimitSize(page.getLimitSize());
		List<DepositOrder> depositOrders = depositOrderService.selectByExample(example);
		returnData.put("Rows", depositOrders);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 合同续签列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/renewIndex")
	public ModelAndView renewIndex(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		MchtInfoCustom mchtInfo = getMchtInfo();
		MchtContract contract = mchtContractService.findByMchtId(mchtInfo.getId());
		//如果合同到期时间大于当前时间+90天，不显示续签
		Date endDate = contract.getEndDate();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 90);
		now = calendar.getTime();
		map.put("timeTag", endDate.before(now));
		map.put("contract", contract);
		if(contract != null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
			map.put("endDate", sdf.format(contract.getEndDate()));
		}
		return new ModelAndView("mchtContract/renewIndex",map);
	}
	
	/**
	 * 合同续签列表数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/getRenewList")
	@ResponseBody
	public ResponseMsg getRenewList(HttpServletRequest request,Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtContractExample example = new MchtContractExample();
		example.setOrderByClause("id desc");
		MchtContractExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		int totalCount = mchtContractService.countByExample(example);
		List<MchtContract> mchtContracts = mchtContractService.selectByExample(example);
		returnData.put("Rows", mchtContracts);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 驳回原因
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/getRemarks")
	public String getRemarks(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.parseInt(id));
		model.addAttribute("fwInnerRemarks", mchtContract.getFwInnerRemarks());
		return "mchtContract/remarks";
	}
	
	
	/**
	 * 立即寄件页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/toSend")
	public String toSend(Model model,HttpServletRequest request) {
		//获取最新店铺总负责人
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		//criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andIdEqualTo(Integer.valueOf(request.getParameter("id"))).andAuditStatusEqualTo("1").andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtContact contact = new MchtContact();
		if(!mchtContacts.isEmpty()){
			contact = mchtContactService.selectByExample(mchtContactExample).get(0);
		}
		model.addAttribute("mchtContact",contact);
		String id = request.getParameter("id");
		model.addAttribute("id",id);
		ExpressExample e = new ExpressExample();
		ExpressExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(e);
		model.addAttribute("expressList", expressList);
		//获取省市区名称
		List<Area> areas = (List<Area>) areaService.getAddress(contact);
		String address = "";
		for (Area area : areas) {
			address +=area.getAreaName();
		}
			address += contact.getAddress();

		if(StringUtils.equals(address, "null")){
			address = "";
		}
		model.addAttribute("address",address);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo",mchtInfo);
		return "mchtContract/toSend";
	}
	
	/**
	 * 立即寄件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/send")
	@ResponseBody
	public Map<String, Object>  send(HttpServletRequest request) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		Integer id2 = this.getSessionMchtInfo(request).getId();
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(!mchtContacts.isEmpty() && !"1".equals(mchtInfo.getIsManageSelf())) {
			MchtContact mchtContact = mchtContactService.selectByExample(mchtContactExample).get(0);
			if ("1".equals(mchtContact.getAuditStatus())) {
				String id = request.getParameter("id");
				String expressId = request.getParameter("expressId");
				String expressNo = request.getParameter("expressNo");
				MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.parseInt(id));
				mchtContract.setIsMchtSend("1");//已寄出
				mchtContract.setMchtSendDate(new Date());
				mchtContract.setMchtExpressId(Integer.parseInt(expressId));
				mchtContract.setMchtExpressNo(expressNo);
				mchtContract.setUpdateBy(this.getSessionMchtInfo(request).getId());
				mchtContract.setUpdateDate(new Date());
				mchtContractService.updateByPrimaryKeySelective(mchtContract);

			} else if (StringUtils.isEmpty(mchtContact.getAuditStatus()) || "2".equals(mchtContact.getAuditStatus())) {//审核状态为空、驳回
				resMap.put("returnCode", "404");
				resMap.put("returnMsg", "请修改收件地址后重新提交");
			} else if ("0".equals(mchtContact.getAuditStatus())) {
				resMap.put("returnCode", "404");
				resMap.put("returnMsg", "信息正在审核中,请稍后再试");
			} else {
				resMap.put("returnCode", "404");
				resMap.put("returnMsg", "请添加电商总负责人信息");
			}
		}
		if("1".equals(mchtInfo.getIsManageSelf())){//自营的寄件操作
			String id = request.getParameter("id");
			String expressId = request.getParameter("expressId");
			String expressNo = request.getParameter("expressNo");
			MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.parseInt(id));
			mchtContract.setIsMchtSend("1");//已寄出
			mchtContract.setMchtSendDate(new Date());
			mchtContract.setMchtExpressId(Integer.parseInt(expressId));
			mchtContract.setMchtExpressNo(expressNo);
			mchtContract.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtContract.setUpdateDate(new Date());
			mchtContractService.updateByPrimaryKeySelective(mchtContract);
		}

			return resMap;
	}
	
	/**
	 * 文件归档情况
	 */
	@RequestMapping("/fileArchive")
	public String fileArchive(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String mchtContractId = request.getParameter("mchtContractId");
		Integer mchtId = this.getSessionMchtInfo(request).getId();
		// 合同扫描件
		data.put("mchtContractPicList", mchtContractPicService.listByContractId(Integer.parseInt(mchtContractId)));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		data.put("mchtInfo", mchtInfo);
		MchtBlPicExample example = new MchtBlPicExample();
		example.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<MchtBlPic> mchtBlPics = mchtBlPicMapper.selectByExample(example);
		data.put("mchtBlPics", mchtBlPics);
		
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andStatusEqualTo("1");//1.正常
		List<MchtProductBrandCustom> mchtProductBrandCustoms = mchtProductBrandService.selectMchtProductBrandCustomByExample(mpbe);
		for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
			//商标注册证
			MchtBrandAptitudeExample e = new MchtBrandAptitudeExample();
			MchtBrandAptitudeExample.Criteria criteria = e.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(e);
			for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
				MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
				MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
				mbapec.andDelFlagEqualTo("0");
				mbapec.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
				List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
				mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
			}
			mchtProductBrandCustom.setMchtBrandAptitudeCustoms(mchtBrandAptitudeCustoms);
			// 授权图片
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
			mchtProductBrandCustom.setMchtPlatformAuthPics(mchtPlatformAuthPics);
			
			//进货发票
			MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicService.selectByExample(mchtBrandInvoicePicExample);
			mchtProductBrandCustom.setMchtBrandInvoicePics(mchtBrandInvoicePics);
			//质检
			MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandCustom.getId());
			List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicService.selectByExample(mchtBrandInspectionPicExample);
			mchtProductBrandCustom.setMchtBrandInspectionPics(mchtBrandInspectionPics);
		}
		data.put("mchtProductBrandCustoms", mchtProductBrandCustoms);
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(Integer.parseInt(mchtContractId));
		data.put("mchtContract", mchtContract);
		return page(data, "mchtContract/fileArchive");
	}
	
	@RequestMapping("/downLoadMchtInfoPic")
	public void downLoadMchtInfoPic(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String mchtId = request.getParameter("mchtId");
		InputStream stream = MchtContractController.class.getResourceAsStream("/config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			return;
		}
		
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
		String zipName = FileUtil.getRandomFileName(mchtInfo.getMchtCode()+"GSXX.zip", 9, Integer.parseInt(mchtId));
        String zipFilePath = defaultPath+zipName;  
        
        //创建需要下载的文件路径的集合
        List<String> filePaths = new ArrayList<String>();  
        filePaths.add(mchtInfo.getCorporationIdcardImg1());  
        filePaths.add(mchtInfo.getCorporationIdcardImg2());
        MchtBlPicExample mbpe = new MchtBlPicExample();
        mbpe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.parseInt(mchtId));
        List<MchtBlPic> mchtBlPics = mchtBlPicMapper.selectByExample(mbpe);
        if(mchtBlPics!=null && mchtBlPics.size()>0){
        	filePaths.add(mchtBlPics.get(0).getPic());
        }
        filePaths.add(mchtInfo.getOccPic());
        filePaths.add(mchtInfo.getTrcPic());
        filePaths.add(mchtInfo.getAtqPic());
        filePaths.add(mchtInfo.getBoaalPic());
        // 税务信息确认函
     	MchtTaxInvoiceInfo mchtTaxInvoiceInfo = mchtTaxInvoiceInfoService.findByMchtId(mchtInfo.getId());
     	if(mchtTaxInvoiceInfo != null){
     		filePaths.add(mchtTaxInvoiceInfo.getConfirmFile());
     	}
        
        //压缩文件
        File zip = new File(zipFilePath);  
        if (!zip.exists()){     
            zip.createNewFile();     
        }
        //创建zip文件输出流  
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        this.zipFile(defaultPath,filePaths,zos);
        zos.close();
        response.setHeader("Content-disposition", "attachment;filename="+mchtInfo.getMchtCode()+"GSXX.zip");//设置下载的压缩文件名称
        
        //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出  
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));  
        byte[] buff = new byte[bis.available()];  
        bis.read(buff);
        bis.close();
        OutputStream out = response.getOutputStream(); 
        out.write(buff);//输出数据文件
        out.flush();//释放缓存
        out.close();//关闭输出流
	}
	
	@RequestMapping("/downLoadBrandPic")
	public void downLoadBrandPic(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String mchtProductBrandId = request.getParameter("mchtProductBrandId");
		InputStream stream = MchtContractController.class.getResourceAsStream("/config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			return;
		}
		
		MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(Integer.parseInt(mchtProductBrandId));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtProductBrand.getMchtId());
		String zipName = FileUtil.getRandomFileName(mchtInfo.getMchtCode()+"PP.zip", 9, mchtInfo.getId());
		String zipFilePath = defaultPath+zipName;


		//创建需要下载的文件路径的集合
		List<String> filePaths = new ArrayList<String>();  
		MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
		mbape.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
		if(mchtBrandAptitudePics!=null && mchtBrandAptitudePics.size()>0){
			for(MchtBrandAptitudePic mchtBrandAptitudePic:mchtBrandAptitudePics){
				filePaths.add(mchtBrandAptitudePic.getPic());
			}
		}
		
		MchtPlatformAuthPicExample mpape = new MchtPlatformAuthPicExample();
		mpape.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicService.selectByExample(mpape);
		if(mchtPlatformAuthPics!=null && mchtPlatformAuthPics.size()>0){
			for(MchtPlatformAuthPic mchtPlatformAuthPic:mchtPlatformAuthPics){
				filePaths.add(mchtPlatformAuthPic.getPic());
			}
		}
		
		MchtBrandInvoicePicExample mbipe = new MchtBrandInvoicePicExample();
		mbipe.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicService.selectByExample(mbipe);
		for(MchtBrandInvoicePic mchtBrandInvoicePic:mchtBrandInvoicePics){
			filePaths.add(mchtBrandInvoicePic.getPic());
		}
		
		MchtBrandInspectionPicExample mbipe2 = new MchtBrandInspectionPicExample();
		mbipe2.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicService.selectByExample(mbipe2);
		for(MchtBrandInspectionPic mchtBrandInspectionPic:mchtBrandInspectionPics){
			filePaths.add(mchtBrandInspectionPic.getPic());
		}
		
		MchtBrandOtherPicExample mbope = new MchtBrandOtherPicExample();
		mbope.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicService.selectByExample(mbope);
		for(MchtBrandOtherPic mchtBrandOtherPic:mchtBrandOtherPics){
			filePaths.add(mchtBrandOtherPic.getPic());
		}
		//压缩文件
		File zip = new File(zipFilePath);  
		if (!zip.exists()){     
			zip.createNewFile();     
		}
		//创建zip文件输出流  
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
		this.zipFile(defaultPath,filePaths,zos);
		zos.close();
		response.setHeader("Content-disposition", "attachment;filename="+mchtInfo.getMchtCode()+"PP.zip");//设置下载的压缩文件名称
		
		//将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出  
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));  
		byte[] buff = new byte[bis.available()];  
		bis.read(buff);
		bis.close();
		OutputStream out = response.getOutputStream(); 
		out.write(buff);//输出数据文件
		out.flush();//释放缓存
		out.close();//关闭输出流
	}
	
	private void zipFile(String defaultPath,List<String> filePaths,ZipOutputStream zos) throws IOException {

        //循环读取文件路径集合，获取每一个文件的路径  
        for(String filePath : filePaths){
        	
            File inputFile = new File(defaultPath+filePath);  //根据文件路径创建文件  
            if(inputFile.exists()) { //判断文件是否存在  
                if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹  
                    //创建输入流读取文件  
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));  

                    //将文件写入zip内，即将文件进行打包  
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));  

                    //写入文件的方法，同上                  
                    int size = 0;  
                    byte[] buffer = new byte[1024];  //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {  
                        zos.write(buffer, 0, size);  
                    }  
                    //关闭输入输出流  
                    zos.closeEntry();  
                    bis.close(); 
                }  
            }
        }
    }
	
	/**
	 * 盖章示范页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toForExample")
	public String toForExample(Model model,HttpServletRequest request) {
		return "mchtContract/toForExample";
	}

	/**
	 * 资质归档情况
	 *
	 * @return
	 */
	@RequestMapping("/archiveSituation")
	public String archiveSituation() {
		return "mchtContract/archiveSituation";
	}

	/**
	 * 资质归档情况列表数据
	 *
	 * @param request
	 * @return
	 */

	@RequestMapping("/archiveSituationData")
	@ResponseBody
	public ResponseMsg archiveSituationData(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Map<String, Object>> mchtContracts = mchtContractService.getArchiveSituationData(this.getSessionMchtInfo(request).getId());
		returnData.put("Rows", mchtContracts);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}

	/**
	 * 合同详情查看
	 *
	 * @return
	 */
	@RequestMapping("/contractDetail")
	public String contractDetail(Model model, HttpServletRequest request, Integer contractId) {
		MchtContractPicExample picExample = new MchtContractPicExample();
		MchtContractPicExample.Criteria picCriteria = picExample.createCriteria();
		picCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		picCriteria.andContractIdEqualTo(contractId);
		picCriteria.andDelFlagEqualTo("0");
		model.addAttribute("contractPicList", mchtContractPicService.selectByExample(picExample));
		return "mchtContract/contractDetail";
	}

	/**
	 * 公司详情查看
	 *
	 * @return
	 */
	@RequestMapping("/mchtDetail")
	public String mchtDetail(Model model, HttpServletRequest request) {
		MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo", mchtInfoCustom);
		// 营业执照(兼容旧数据)
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfoCustom.getId());
		List<MchtBlPic> mchtBlPics = mchtBlPicService.selectByExample(mchtBlPicExample);
		model.addAttribute("mchtBlPics", mchtBlPics);
		return "mchtContract/mchtInfo";
	}

	/**
	 * 商家品牌查看
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtBrandDetail")
	public String mchtBrandView(Model model, HttpServletRequest request) {
		Integer mchtBrandId = Integer.valueOf(request.getParameter("mchtBrandId"));
		MchtProductBrandCustom mchtProductBrandCustom = mchtProductBrandService.selectMchtProductBrandCustomByPrimaryKey(mchtBrandId);
		model.addAttribute("mchtProductBrand", mchtProductBrandCustom);

		// 授权图片是否上传
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtPlatformAuthPic> mchtPlatformAuthPics=mchtPlatformAuthPicService.selectByExample(mchtPlatformAuthPicExample);
		model.addAttribute("mchtPlatformAuthPics", mchtPlatformAuthPics);

		// 进货发票
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInvoicePic> mchtBrandInvoicePics=mchtBrandInvoicePicServer.selectByExample(mchtBrandInvoicePicExample);
		model.addAttribute("mchtBrandInvoicePics", mchtBrandInvoicePics);

		// 质检报告/检疫报告
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandInspectionPic> mchtBrandInspectionPics=mchtBrandInspectionPicServer.selectByExample(mchtBrandInspectionPicExample);
		model.addAttribute("mchtBrandInspectionPics", mchtBrandInspectionPics);

		//商标注册证或受理通知书
		MchtBrandAptitudeExample e = new MchtBrandAptitudeExample();
		MchtBrandAptitudeExample.Criteria criteria = e.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtProductBrandIdEqualTo(mchtBrandId);
		List<MchtBrandAptitudeCustom> mchtBrandAptitudeCustoms = mchtBrandAptitudeService.selectCustomByExample(e);
		for(MchtBrandAptitudeCustom mchtBrandAptitudeCustom:mchtBrandAptitudeCustoms){
			MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
			MchtBrandAptitudePicExample.Criteria mbapec = mbape.createCriteria();
			mbapec.andDelFlagEqualTo("0");
			mbapec.andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeCustom.getId());
			List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicService.selectByExample(mbape);
			mchtBrandAptitudeCustom.setMchtBrandAptitudePics(mchtBrandAptitudePics);
		}
		model.addAttribute("mchtBrandAptitudeCustoms", mchtBrandAptitudeCustoms);
		return "mchtContract/mchtBrandInfo";
	}
	
	/**
	 * 
	 * @MethodName renew
	 * @Description TODO(是否续签操作)
	 * @author chengh
	 * @date 2019年8月6日 下午5:58:29
	 */
	@RequestMapping("/renew")
	@ResponseBody
	public ResponseMsg renew(Model model, HttpServletRequest request) {
		Integer mchtContractId = Integer.valueOf(request.getParameter("mchtContractId"));
		String status = request.getParameter("status");
		String mchtNotRenewRemarks = request.getParameter("mchtNotRenewRemarks");
		MchtContract mchtContract = mchtContractService.selectByPrimaryKey(mchtContractId);
		//不续签原因
		if(StringUtils.equals("1", status)){
			mchtContract.setMchtNotRenewRemarks(mchtNotRenewRemarks);
			mchtContract.setRenewStatus("2");
		}else if(StringUtils.equals("2", status)){
			mchtContract.setRenewStatus("1");
		}
		mchtContract.setRenewProStatus(status);
		mchtContract.setUpdateBy(this.getSessionMchtInfo(request).getId());
		mchtContract.setUpdateDate(new Date());
		mchtContractService.updateByPrimaryKeySelective(mchtContract);
		//插入合同续签日志
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ContractRenewLog contractRenewLog = new ContractRenewLog();
		contractRenewLog.setContractId(mchtContract.getId());
		contractRenewLog.setOperator(this.getSessionMchtInfo(request).getId());
		contractRenewLog.setOperatorType("0");
		contractRenewLog.setStatus(status);
		try {
			contractRenewLog.setStatusDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
			contractRenewLog.setCreateDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
			contractRenewLog.setUpdateDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		contractRenewLog.setUpdateBy(this.getSessionMchtInfo(request).getId());
		contractRenewLog.setUpdateDate(new Date());
		contractRenewLog.setDelFlag("0");
		contractRenewLogService.insertSelective(contractRenewLog);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	@RequestMapping("/sendContent")
	public String sendContent(Model model, HttpServletRequest request) {
		int id = Integer.valueOf(request.getParameter("id"));
		MchtContract contract = mchtContractService.selectByPrimaryKey(id);
		Assert.notNull(contract, "合同还没生成，不能查看合同扫描件");

		Map<String, Object> data = new HashMap<>();
		data.put("contract", contract);
		data.put("mchtContractPicList", mchtContractPicService.listByContractId(contract.getId()));
		return page(data, "mchtContract/sendContent");
	}	
	
	@RequestMapping("/neverRemind")
	@ResponseBody
	public ResponseMsg neverRemind(HttpServletRequest request) {
		//设置合同续签提醒session
		request.getSession().setAttribute("neverRemind","1");
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}	
	
	@RequestMapping("/rejectNeverRemind")
	@ResponseBody
	public ResponseMsg rejectNeverRemind(HttpServletRequest request) {
		//设置合同续签提醒session
		request.getSession().setAttribute("rejectNeverRemind","1");
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}	
}
