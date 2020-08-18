package com.jf.controller;


import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ImpeachMemberProofMapper;
import com.jf.entity.ImpeachHandleLog;
import com.jf.entity.ImpeachHandleLogExample;
import com.jf.entity.ImpeachMember;
import com.jf.entity.ImpeachMemberCustom;
import com.jf.entity.ImpeachMemberCustomExample;
import com.jf.entity.ImpeachMemberCustomExample.ImpeachMemberCustomCriteria;
import com.jf.entity.ImpeachMemberProof;
import com.jf.entity.ImpeachMemberProofExample;
import com.jf.entity.ImpeachMemberProofExample.Criteria;
import com.jf.entity.SysStatus;
import com.jf.service.ImpeachHandleLogServer;
import com.jf.service.ImpeachMemberProofServer;
import com.jf.service.ImpeachMemberServer;
import com.jf.service.SubOrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Controller
@RequestMapping("/merchantReport")
public class MerchantReportController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MerchantReportController.class);
	
		@Resource
		private ImpeachMemberServer  impeachMemberServer;
	
		@Resource
		private SubOrderService subOrderService;
		
		@Resource
		private ImpeachMemberProofServer  impeachMemberProofServer;
		
		@Resource
		private ImpeachHandleLogServer impeachHandleLogServer;
		
		@Resource
		private ImpeachMemberProofMapper impeachMemberProofMapper;
		
		@InitBinder
		public void initBinder(ServletRequestDataBinder binder) {
			/**
			 * 自动转换日期类型的字段格式
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		}
	
		/**
		 * 商家举报首页
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping("/merchantReportIndex")
		public String MerchantReportIndex(Model model, HttpServletRequest request){
			model.addAttribute("reportingTypeList", DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "TYPE"));//举报类型	
			String toBeRevised = request.getParameter("toBeRevised");
			if(StringUtil.isEmpty(toBeRevised)){
				toBeRevised="-1";
			}
			model.addAttribute("toBeRevised",toBeRevised);
			return "merchantReport/merchantReportIndex";
		}
		
		

		/**
		 * 数据列表
		 * 
		 * @param request
		 * @param page
		 * @return
		 * @throws ParseException 
		 */
		@RequestMapping("/getMerchantReportList")
		@ResponseBody
		public ResponseMsg getMerchantReportList(HttpServletRequest request, Page page)  throws ParseException {
			Map<String, Object> returnData = new HashMap<String, Object>();
			ImpeachMemberCustomExample impeachMemberCustomExample = new ImpeachMemberCustomExample();
			ImpeachMemberCustomCriteria impeachMemberCustomCriteria = impeachMemberCustomExample.createCriteria();
			String searchReportCode = request.getParameter("search_reportCode");//举报编号`
			String searchSubOrderCode = request.getParameter("search_subOrderCode");//子订单号
			String searchAppealType = request.getParameter("search_appealType");//举报类型`
			String searchStatus = request.getParameter("search_status");//举报状态
			String createTimeBegin = request.getParameter("createTimeBegin");
			String createTimeEnd = request.getParameter("createTimeEnd");
			Integer mchtId = this.getSessionMchtInfo(request).getId();
			
			impeachMemberCustomCriteria.andDelFlagEqualTo("0");
			impeachMemberCustomCriteria.andMchtIdEqualTo(mchtId);
			impeachMemberCustomCriteria.andSourceEqualTo("0");
			
			//====用于只取待修改的数据
			String toBeRevised = request.getParameter("toBeRevised");
			if(!StringUtil.isBlank(toBeRevised)&&"2".equals(toBeRevised)){
				impeachMemberCustomCriteria.andLastEditDateGreaterThanOrEqualTo(new Date());
				impeachMemberCustomCriteria.andStatusEqualTo("3");
			}
			//====	
				
			if(!StringUtil.isEmpty(searchReportCode)){
				impeachMemberCustomCriteria.andCodeEqualTo(searchReportCode.trim());
			}
			if(!StringUtil.isEmpty(searchAppealType)){
				impeachMemberCustomCriteria.andTypeEqualTo(searchAppealType);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(createTimeBegin)){
				impeachMemberCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(createTimeBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(createTimeEnd)){
				impeachMemberCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(createTimeEnd+" 23:59:59"));
			}
			ArrayList<String> list = new ArrayList<String>();
			if(!StringUtil.isEmpty(searchStatus)){
				if("1".equals(searchStatus)){
					impeachMemberCustomCriteria.andStatusEqualTo("0");
				}
				if("2".equals(searchStatus)){
					list.add("1");
					list.add("2");
					list.add("4");
					list.add("5");
					list.add("6");
					impeachMemberCustomCriteria.andStatusIn(list);
				}
				if("3".equals(searchStatus)){
					impeachMemberCustomCriteria.andStatusEqualTo("3");
				}
				if("4".equals(searchStatus)){
					impeachMemberCustomCriteria.andStatusEqualTo("7");
				}
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				impeachMemberCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode.trim());
			}
			impeachMemberCustomExample.setLimitStart(page.getLimitStart());
			impeachMemberCustomExample.setLimitSize(page.getLimitSize());
			int totalCount = impeachMemberServer.countImpeachMemberCustomByExample(impeachMemberCustomExample);
			

			List<ImpeachMemberCustom> ImpeachMemberCustoms = impeachMemberServer.selectImpeachMemberCustomByExample(impeachMemberCustomExample);
			returnData.put("Rows", ImpeachMemberCustoms);
			returnData.put("Total", totalCount);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		}
		
		
		/**
		 * 添加举报模版
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		
		@RequestMapping("/merchantReportAdd")
		public String productAfterTempletAdd(Model model,HttpServletRequest request) {
			model.addAttribute("reportingTypeList", DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "TYPE"));//举报类型	

			//使修改时的filemMap不为空
			Map<String , Long> fileMap = new HashMap<>();
			fileMap.put("0", 0L);
			JSONObject fileMaps = JSONObject.fromObject(fileMap);
			model.addAttribute("fileMap", fileMaps);
			if(!StringUtil.isEmpty(request.getParameter("commentId")) && !StringUtil.isEmpty(request.getParameter("subOrderId"))){
				model.addAttribute("commentId", request.getParameter("commentId"));
				model.addAttribute("subOrderId", request.getParameter("subOrderId"));
			}
			return "/merchantReport/merchantReportEdit";
		}
		
		/**
		 * 添加举报模版的二级联动
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping("/getReportingScenarioList")
		@ResponseBody
		public List<SysStatus>  getAreaList(HttpServletRequest request){
			String searchAppealType = request.getParameter("type");
			if(!StringUtil.isEmpty(searchAppealType)){
				if("1".equals(searchAppealType)){
					List<SysStatus> statusList = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "SCENE"+searchAppealType);//举报类型
					return statusList;		
				}
				if("2".equals(searchAppealType)){
					List<SysStatus> statusList = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "SCENE"+searchAppealType);//举报类型
					return statusList;					
				}
				if("3".equals(searchAppealType)){
					List<SysStatus> statusList = DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "SCENE"+searchAppealType);//举报类型
					System.out.println(statusList.toString());
					statusList.remove(0);
					statusList.remove(4);	
				return statusList;			
				}
				
			}
		return new ArrayList<SysStatus>(); 
		}	
		
		
		
		/**
		 * 查看举报信息
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping("/merchantReportView")
		public String MerchantReportView(Model model, HttpServletRequest request){
			try {
				Integer merchantReportId = Integer.valueOf(request.getParameter("id"));
				ImpeachMemberCustomExample example = new ImpeachMemberCustomExample();
				example.createCriteria().andIdEqualTo(merchantReportId).andDelFlagEqualTo("0");
				List<ImpeachMemberCustom> merchantReports = impeachMemberServer.selectImpeachMemberCustomByExample(example);
				ImpeachMemberCustom impeachMemberCustom = merchantReports.get(0);
				//ImpeachMember merchantReport = impeachMemberServer.selectByPrimaryKey(merchantReportId);
				model.addAttribute("merchantReport", impeachMemberCustom);
				
				ImpeachMemberProofExample impeachMemberProofExample = new ImpeachMemberProofExample();
				impeachMemberProofExample.createCriteria().andDelFlagEqualTo("0").andImpeachMemberIdEqualTo(merchantReportId);
				List<ImpeachMemberProof> impeachMemberProofs = impeachMemberProofServer.selectByExample(impeachMemberProofExample );
				model.addAttribute("impeachMemberProofs", impeachMemberProofs);//会员举报凭证
			
				String type = impeachMemberCustom.getType();
				String scene = impeachMemberCustom.getScene();
				String rejectReason = impeachMemberCustom.getRejectReason();
				String caseCloseDesc = impeachMemberCustom.getCaseCloseDesc();
				model.addAttribute("type", DataDicUtil.getStatusDesc("BU_IMPEACH_MEMBER", "TYPE", type));//举报类型
				model.addAttribute("scene", DataDicUtil.getStatusDesc("BU_IMPEACH_MEMBER", "SCENE"+type, scene));//举报类型
				if(!StringUtil.isBlank(rejectReason)){
					model.addAttribute("rejectReason", DataDicUtil.getStatusDesc("BU_IMPEACH_MEMBER", "REJECT_REASON", rejectReason));//驳回
				}
				if(!StringUtil.isBlank(caseCloseDesc)){
					model.addAttribute("caseCloseDesc", DataDicUtil.getStatusDesc("BU_IMPEACH_MEMBER", "CASE_CLOSE_DESC", caseCloseDesc));//通过
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "merchantReport/merchantReportView";
		}
		
		
		
		
		/**
		 * 保存举报信息
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping("/saveMerchantReport")
		@ResponseBody
		public ResponseMsg saveMerchantReport(HttpServletRequest request , ImpeachMember impeachMember) {	
		try{
			Integer mchtId = this.getSessionMchtInfo(request).getId();
			impeachMember.setMchtId(mchtId);
			String subOrderCodes = request.getParameter("subOrderCodes");
			if(!StringUtil.isEmpty(subOrderCodes)){
				JSONArray subOrderCodesList = JSONArray.fromObject(subOrderCodes);				
				List<String> socList = (List<String>) JSONArray.toCollection(subOrderCodesList, String.class);//相关订单号集合
				String subOrderIds = subOrderService.selectBySubOrderCodes(socList);//子订单的id集合
				impeachMember.setSubOrderIds(subOrderIds);
				String memberIds = subOrderService.selectMemberIdsBySubOrderCodes(socList);//会员id集合
				if(memberIds==null || "".equals(memberIds)){
					throw new ArgException("请输入正确订单号");
				}
				impeachMember.setMemberIds(memberIds);	
			}

			impeachMemberServer.impeachMemberSave(impeachMember);
			
			String filePaths = request.getParameter("filePaths");
			String fileNames = request.getParameter("fileNames");
			if (StringUtil.isEmpty(request.getParameter("filePaths"))) {
				throw new ArgException("请上传举报凭证");
			}
			if (StringUtil.isEmpty(request.getParameter("fileNames"))) {
				throw new ArgException("请上传举报凭证");
			}
			 //修改举报会员凭证表的信息
			ImpeachMemberProofExample impeachMemberProofExample = new ImpeachMemberProofExample();
			impeachMemberProofExample.createCriteria().andImpeachMemberIdEqualTo(impeachMember.getId());
			List<ImpeachMemberProof> ImpeachMemberProofList = impeachMemberProofServer.selectByExample(impeachMemberProofExample);
			
			if(ImpeachMemberProofList.size()<=0||ImpeachMemberProofList==null){//添加文件路径	
				impeachMemberProofServer.saveImpeachMemberProof(impeachMember, request.getParameter("fileNames"), request.getParameter("filePaths"));
	
			 }else{			
				 impeachMemberProofServer.updateImpeachMemberProof(impeachMember, request.getParameter("fileNames"), request.getParameter("filePaths"));
				
			}
		
			//删除原本上传的文件
			String deleteIds = request.getParameter("deleteIds");
			if(!StringUtil.isEmpty(deleteIds)){
				JSONArray deleteIdsList = JSONArray.fromObject(deleteIds);	
				List<String> delList= (List<String>) JSONArray.toCollection(deleteIdsList, String.class);//需要删除的id集合
			for (int i = 0; i < delList.size(); i++) {
				ImpeachMemberProof impeachMemberProofDelete = impeachMemberProofServer.selectByPrimaryKey(Integer.parseInt(delList.get(i)));
				impeachMemberProofDelete.setDelFlag("1");
				impeachMemberProofDelete.setUpdateDate(new Date());
				impeachMemberProofServer.updateByPrimaryKeySelective(impeachMemberProofDelete);
			}
			}
			
			//修改举报处理日志表
			ImpeachHandleLogExample imHandleLogExample = new ImpeachHandleLogExample();
			imHandleLogExample.createCriteria().andImpeachMemberIdEqualTo(impeachMember.getId());
			List<ImpeachHandleLog> imHandleLogList = impeachHandleLogServer.selectByExample(imHandleLogExample);
			if(imHandleLogList==null||imHandleLogList.size()<=0){
				ImpeachHandleLog impeachHandleLog = new ImpeachHandleLog();
				 impeachHandleLog.setImpeachMemberId(impeachMember.getId());
				 impeachHandleLog.setStatus("1");
				 impeachHandleLog.setCreateDate(impeachMember.getCreateDate());
				 impeachHandleLog.setCreateBy(0);
				 impeachHandleLog.setDelFlag("0");
				 impeachHandleLogServer.insertSelective(impeachHandleLog); 
			}else{
				ImpeachHandleLog imHandleLog = imHandleLogList.get(0);
				imHandleLog.setUpdateDate(new Date());
				impeachHandleLogServer.updateByPrimaryKeySelective(imHandleLog);		
				}
			}catch (ArgException arge) {
				return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
			}
			
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		}

	

		/**
		 * 修改举报信息
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		
		@RequestMapping("/merchantReportEdit")
		public String productAfterTempletEdit(Model model,HttpServletRequest request) {
			String impeachMemberId = request.getParameter("id");
			if(!StringUtil.isBlank(impeachMemberId)&&impeachMemberId!=""){
				Integer id=Integer.valueOf(impeachMemberId);
				ImpeachMember impeachMember = impeachMemberServer.selectByPrimaryKey(id);
				model.addAttribute("impeachMember", impeachMember);	
				//举报类型,举报场景,驳回原因
				model.addAttribute("type", impeachMember.getType());
				model.addAttribute("scene", impeachMember.getScene());
				String rejectReason = impeachMember.getRejectReason();
				if(!StringUtil.isEmpty(rejectReason)){
					model.addAttribute("rejectReason", DataDicUtil.getStatusDesc("BU_IMPEACH_MEMBER", "REJECT_REASON", 	rejectReason));	
				}
				model.addAttribute("reportingTypeList", DataDicUtil.getStatusList("BU_IMPEACH_MEMBER", "TYPE"));//举报类型
			  
				//相关订单
				String subOrderIds = impeachMember.getSubOrderIds();
				if(!StringUtil.isBlank(subOrderIds)&&subOrderIds!=""){
					String[] split = subOrderIds.split(",");
					List subOrderIdsList = Arrays.asList(split);
					List<String> subOrderCodesList = subOrderService.selectSubOrderCodesBySubOrderIds(subOrderIdsList);
					model.addAttribute("subOrderCodesList", subOrderCodesList);
				}

				//举报凭证   ,文件地址和文件名字
				ImpeachMemberProofExample impeachMemberProofExample = new ImpeachMemberProofExample();
				impeachMemberProofExample.createCriteria().andDelFlagEqualTo("0").andImpeachMemberIdEqualTo(id);
				List<ImpeachMemberProof> impeachMemberProofs = impeachMemberProofServer.selectByExample(impeachMemberProofExample );
				model.addAttribute("impeachMemberProofs", impeachMemberProofs);//会员举报凭证
			
			
				Map<String , Long> fileMap = new HashMap<>();
				//已上传的文件大小
				if(impeachMemberProofs!=null&&impeachMemberProofs.size()>0){
					//得到文件路径前缀
					InputStream stream = MerchantReportController.class.getResourceAsStream("/config.properties");
					String defaultPath=null;
					try {
						Properties properties = new Properties();
						properties.load(stream);
						defaultPath = properties.getProperty("annex.filePath");
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//读取已保存到数据库文件的大小
					long filesSize=0;
					for(ImpeachMemberProof imp : impeachMemberProofs){
						String filePath = imp.getFilePath();
						File file = new File(defaultPath+filePath);
						long length = file.length();
						filesSize+=length;						
						String imId = imp.getId()+"";
						fileMap.put(imId, length);						
					}
					
					
					model.addAttribute("filesSize", filesSize);//已上传的文件的大小
				}else{
					fileMap.put("0", 0L);
				}
				JSONObject fileMaps = JSONObject.fromObject(fileMap);
				model.addAttribute("fileMap", fileMaps);
			}
			
			return "/merchantReport/merchantReportEdit";
		}

		/**
		 * 修改举报信息时的文件删除
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping("/deleteUpdateFile")
		@ResponseBody
		public ResponseMsg deleteUpdateFile(Model model, HttpServletRequest request) {
			
		try{	
			String fileId = request.getParameter("fileId");
			if(!StringUtil.isEmpty(fileId)){
				//删除文件
				ImpeachMemberProof impeachMemberProof = impeachMemberProofServer.selectByPrimaryKey(Integer.parseInt(fileId));
				impeachMemberProof.setDelFlag("1");
				impeachMemberProofServer.updateByPrimaryKeySelective(impeachMemberProof);
				
				//重新统计已上传文件的大小
				ImpeachMemberProofExample example = new ImpeachMemberProofExample();
				Criteria createCriteria = example.createCriteria();
				createCriteria.andImpeachMemberIdEqualTo(impeachMemberProof.getImpeachMemberId());
				createCriteria.andDelFlagEqualTo("0");
				List<ImpeachMemberProof> impeachMemberProofs = impeachMemberProofServer.selectByExample(example);
				if(impeachMemberProofs!=null&&impeachMemberProofs.size()>0){
					//得到文件路径前缀
					InputStream stream = MerchantReportController.class.getResourceAsStream("/config.properties");
					String defaultPath=null;
					try {
						Properties properties = new Properties();
						properties.load(stream);
						defaultPath = properties.getProperty("annex.filePath");
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//读取已保存到数据库文件的大小
					long filesSize=0;
					for(ImpeachMemberProof imp : impeachMemberProofs){
						String filePath = imp.getFilePath();
						File file = new File(defaultPath+filePath);
						long length = file.length();
						filesSize+=length;
					}
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,filesSize);
				}	
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}	
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}	

		
		
		/**
		 * 文件下载
		 * 
		 * @param model
		 * @param request
		 * @return
		 */

		@RequestMapping("/downLoadImpeachMemberProof")
		public void downLoadImpeachMemberProof(HttpServletRequest request,HttpServletResponse response)  {
			String filePath = request.getParameter("filePath");
			String fileName = request.getParameter("fileName");
			InputStream stream = MerchantReportController.class.getResourceAsStream("/config.properties");
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
			File file = new File(defaultPath+filePath);
			//如果文件不存在
			if(!file.exists()){
			    return;
			}
			//设置响应头，控制浏览器下载该文件
			
			try {
			String downloadFileName = "";
			String agent = request.getHeader("USER-AGENT");
			if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {
				downloadFileName = "=?UTF-8?B?"+ (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
			} else {
				downloadFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			}
			response.setHeader("content-disposition", "attachment;filename="+ downloadFileName);
			// 读取要下载的文件，保存到文件输入流
			FileInputStream in = new FileInputStream(defaultPath + filePath);
			// 创建输出流
			OutputStream out = response.getOutputStream();
			// 缓存区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区中
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			// 关闭
			in.close();
			out.flush();
			out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}

		/**
		 * 超时关闭
		 * 
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping("/overtimeClosure")
		public String overtimeClosure(HttpServletRequest request){
			String id = request.getParameter("id");
			if(!StringUtil.isEmpty(id)){
				ImpeachMember impeachMember = impeachMemberServer.selectByPrimaryKey(Integer.parseInt(id));
				impeachMember.setStatus("8");
				impeachMember.setUpdateDate(new Date());
				impeachMemberServer.updateByPrimaryKeySelective(impeachMember);
			}
			return "merchantReport/merchantReportIndex";
		}	


}
		
