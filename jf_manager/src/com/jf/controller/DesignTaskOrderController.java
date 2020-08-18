package com.jf.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.codec.binary.Base64;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskOrderCustom;
import com.jf.entity.DesignTaskOrderCustomExample;
import com.jf.entity.DesignTaskOrderPic;
import com.jf.entity.DesignTaskOrderPicCustom;
import com.jf.entity.DesignTaskOrderPicCustomExample;
import com.jf.entity.DesignTaskOrderPicDetailCustom;
import com.jf.entity.DesignTaskOrderPicDetailCustomExample;
import com.jf.entity.DesignTaskOrderPicExample;
import com.jf.entity.DesignTaskRefundOrder;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.entity.SysStatus;
import com.jf.service.DesignTaskOrderPicDetailService;
import com.jf.service.DesignTaskOrderPicService;
import com.jf.service.DesignTaskOrderService;
import com.jf.service.DesignTaskRefundOrderService;
import com.jf.service.SysStaffRoleService;
import com.jf.vo.Page;

@Controller
public class DesignTaskOrderController extends BaseController {
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private DesignTaskOrderService designTaskOrderService;
	
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	
	@Resource
	private DesignTaskOrderPicDetailService designTaskOrderPicDetailService;
	
	@Resource
	private DesignTaskRefundOrderService designTaskRefundOrderService;
	
	@Resource
	private DesignTaskOrderPicService designTaskOrderPicService;
	
	
	//设计任务订单列表
	@RequestMapping("/activityNew/designTaskOrder.shtml")
	public ModelAndView designTaskOrder(HttpServletRequest request) {
		String rtPage = "/activityNew/designTaskOrderList";
		Map<String,Object> resMap = new HashMap<String,Object>();
		String staffID = this.getSessionStaffBean(request).getStaffID();
		//获取控建角色
		SysStaffRoleExample sysStaffRoleExample=new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(26);
	    List<SysStaffRole> sysStaffRolelist=sysStaffRoleService.selectByExample(sysStaffRoleExample);
	    if (sysStaffRolelist!=null && sysStaffRolelist.size()>0) {
	    	resMap.put("roleId",sysStaffRolelist.get(0).getRoleId());
		}
	    resMap.put("getDesignerList", designTaskOrderService.getDesignerList());//获取设计领取人
	    resMap.put("type", request.getParameter("type"));
	    return new ModelAndView(rtPage, resMap); 

	}
	
	//设计任务订单数据
	@ResponseBody
	@RequestMapping("/activityNew/designTaskOrderdata.shtml")
	public Map<String, Object> designTaskOrderdata(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			DesignTaskOrderCustomExample designTaskOrderCustomExample=new DesignTaskOrderCustomExample();
			DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria designTaskOrderCustomCriteria=designTaskOrderCustomExample.createCriteria();
			designTaskOrderCustomCriteria.andDelFlagEqualTo("0");
			designTaskOrderCustomExample.setOrderByClause("dto.create_date desc");			
			if (!StringUtil.isEmpty(request.getParameter("orderCode"))) {
				designTaskOrderCustomCriteria.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("taskType"))) {
				designTaskOrderCustomCriteria.andTaskTypeEqualTo(request.getParameter("taskType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("designStatus"))) {
				designTaskOrderCustomCriteria.andDesignStatusEqualTo(request.getParameter("designStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("taskName"))) {
				designTaskOrderCustomCriteria.andTaskNameLike("%"+request.getParameter("taskName")+"%");
			}
			if (!StringUtil.isEmpty(request.getParameter("payStatus"))) {
                if (Integer.parseInt(request.getParameter("payStatus").toString()) < 1) {
                    designTaskOrderCustomCriteria.andPayStatusEqualTo(request.getParameter("payStatus"));
                } else {
                    designTaskOrderCustomCriteria.andPayStatusCustomEqualTo(request.getParameter("payStatus"));
                }
            }
			if (!StringUtil.isEmpty(request.getParameter("commentType"))) {
				designTaskOrderCustomCriteria.andCommentTypeEqualTo(request.getParameter("commentType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				designTaskOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}else {
				designTaskOrderCustomCriteria.andStatusEqualTo("0");
			}
			if (!StringUtil.isEmpty(request.getParameter("designer"))) {
				if (request.getParameter("designer").equals("0")) {
					designTaskOrderCustomCriteria.andDesignerIsNull();
				}else {
					designTaskOrderCustomCriteria.andDesignerEqualTo(Integer.valueOf(request.getParameter("designer")));
				}
			}			
			totalCount = designTaskOrderService.designTaskOrderCountByExample(designTaskOrderCustomExample);
			designTaskOrderCustomExample.setLimitStart(page.getLimitStart());
			designTaskOrderCustomExample.setLimitSize(page.getLimitSize());
			List<DesignTaskOrderCustom> designTaskOrderCustoms = designTaskOrderService.designTaskOrderSelectByExample(designTaskOrderCustomExample);			
			resMap.put("Rows", designTaskOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}
	
    //更新设计领取人
	@ResponseBody
	@RequestMapping("/activityNew/getDesigner.shtml")
	public Map<String, Object> getDesigner(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "200";
		String message = "";
		try {
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());				
				DesignTaskOrder designTaskOrder=designTaskOrderService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
				designTaskOrder.setDesigner(staffId);
				designTaskOrder.setReceiveDate(new Date());
				designTaskOrderService.updateByPrimaryKeySelective(designTaskOrder);				
			}
		} catch (Exception e) {
			statusCode = "999";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}
	
	
	//退款订单任务
    @RequestMapping(value = "/activityNew/reFund.shtml")
    @ResponseBody
    public Map<String, Object> reFund(HttpServletRequest request, HttpServletResponse response, String id) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());
            Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String batchno = format.format(date);
			int x=(int)(Math.random()*900000)+100000;  
			String SJ="SJ";
			String serial = SJ+batchno + x; //时间后面加六位随机数;           
            DesignTaskOrder designTaskOrder=designTaskOrderService.selectByPrimaryKey(Integer.valueOf(id));           
            DesignTaskRefundOrder designTaskRefundOrder=new DesignTaskRefundOrder();
            designTaskRefundOrder.setDesignTaskOrderId(Integer.valueOf(id));
            designTaskRefundOrder.setStatus("0");
            designTaskRefundOrder.setRefundAmount(designTaskOrder.getPayAmount());
            designTaskRefundOrder.setRefundAgreeDate(date);
            designTaskRefundOrder.setRefundCode(serial);
            designTaskRefundOrder.setRefundMethod("1");
            designTaskRefundOrder.setTryTimes(0);
            designTaskRefundOrder.setDelFlag("0");
            designTaskRefundOrder.setCreateBy(staffId);
            designTaskRefundOrder.setCreateDate(date);
            designTaskRefundOrderService.insert(designTaskRefundOrder);           
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", e.getMessage());
        }

        return resMap;
    }
	
	
	/**
	 * 查看详情
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/activityNew/showTask.shtml")
	public ModelAndView showTask(HttpServletRequest request) {
		String rtPage = "/activityNew/showTask";
		Map<String, Object> resMap = new HashMap<String, Object>();
		int id=Integer.valueOf(request.getParameter("id"));	    
	    DesignTaskOrder designTaskOrder=designTaskOrderService.selectByPrimaryKey(id);
	    resMap.put("designTaskOrder", designTaskOrder);		
	    resMap.put("statusPage", request.getParameter("statusPage"));	    
	    DesignTaskOrderPicDetailCustomExample designTaskOrderPicDetailCustomExample=new DesignTaskOrderPicDetailCustomExample();
	    designTaskOrderPicDetailCustomExample.createCriteria().andDelFlagEqualTo("0").andTaskTypeEqualTo(designTaskOrder.getTaskType());
	    List<DesignTaskOrderPicDetailCustom> designTaskOrderPicDetailCustoms=designTaskOrderPicDetailService.designTaskOrderPicDetailSelectByExample(designTaskOrderPicDetailCustomExample);
	    /*resMap.put("designTaskOrderPicDetailList", JSONArray.fromObject(designTaskOrderPicDetailCustoms));*/	    
	    resMap.put("getWidth", designTaskOrderPicDetailCustoms.get(0).getWidth());
	    resMap.put("getHeight", designTaskOrderPicDetailCustoms.get(0).getHeight());
	    resMap.put("getPicDesc", designTaskOrderPicDetailCustoms.get(0).getPicDesc());	    
	    resMap.put("getWidth1", designTaskOrderPicDetailCustoms.get(1).getWidth());
	    resMap.put("getHeight1", designTaskOrderPicDetailCustoms.get(1).getHeight());
	    resMap.put("getPicDesc1", designTaskOrderPicDetailCustoms.get(1).getPicDesc());	    
	    resMap.put("getWidth2", designTaskOrderPicDetailCustoms.get(2).getWidth());
	    resMap.put("getHeight2", designTaskOrderPicDetailCustoms.get(2).getHeight());
	    resMap.put("getPicDesc2", designTaskOrderPicDetailCustoms.get(2).getPicDesc());	    	    
	    DesignTaskOrderPicExample designTaskOrderPicExample=new DesignTaskOrderPicExample();
	    designTaskOrderPicExample.createCriteria().andDelFlagEqualTo("0").andDesignTaskOrderIdEqualTo(designTaskOrder.getId());
	    List<DesignTaskOrderPic> designTaskOrderPics=designTaskOrderPicService.selectByExample(designTaskOrderPicExample);
	    if (designTaskOrder.getTaskType().equals("1")) {
	    	for (DesignTaskOrderPic designTaskOrderPic : designTaskOrderPics) {
	    		if (designTaskOrderPic.getPicDetail().equals(designTaskOrderPicDetailCustoms.get(0).getWidth()+"*"+designTaskOrderPicDetailCustoms.get(0).getHeight()+","+designTaskOrderPicDetailCustoms.get(0).getPicDesc())) {
	    			resMap.put("designTaskOrderPic", designTaskOrderPic.getPic());
	    		}else if (designTaskOrderPic.getPicDetail().equals(designTaskOrderPicDetailCustoms.get(1).getWidth()+"*"+designTaskOrderPicDetailCustoms.get(1).getHeight()+","+designTaskOrderPicDetailCustoms.get(1).getPicDesc())) {
	    			resMap.put("designTaskOrderPic1", designTaskOrderPic.getPic());
	    		}else if (designTaskOrderPic.getPicDetail().equals(designTaskOrderPicDetailCustoms.get(2).getWidth()+"*"+designTaskOrderPicDetailCustoms.get(2).getHeight()+","+designTaskOrderPicDetailCustoms.get(2).getPicDesc())) {
	    			resMap.put("designTaskOrderPic2", designTaskOrderPic.getPic());
	    			
	    		}
	    	}		
		}else {
			DesignTaskOrderPicCustomExample designTaskOrderPicCustomExample=new DesignTaskOrderPicCustomExample();
			DesignTaskOrderPicCustomExample.DesignTaskOrderPicCustomCriteria designTaskOrderPicExample1=designTaskOrderPicCustomExample.createCriteria();
			designTaskOrderPicExample1.andDelFlagEqualTo("0").andDesignTaskOrderIdEqualTo(designTaskOrder.getId());   
		    List<DesignTaskOrderPicCustom> designTaskOrderPicCustoms=designTaskOrderPicService.designTaskOrderPicSelectByExample(designTaskOrderPicCustomExample);	    
			for (DesignTaskOrderPic designTaskOrderPic : designTaskOrderPics) {
	    		if (designTaskOrderPic.getPicDetail().equals(designTaskOrderPicDetailCustoms.get(0).getWidth()+"*"+designTaskOrderPicDetailCustoms.get(0).getHeight()+","+designTaskOrderPicDetailCustoms.get(0).getPicDesc())) {
	    			resMap.put("designTaskOrderPic", designTaskOrderPic.getPic());
	    		}else if (designTaskOrderPic.getPic().equals(designTaskOrderPicCustoms.get(1).getPic())) {
	    			resMap.put("designTaskOrderPic1", designTaskOrderPic.getPic());
	    		}else if (designTaskOrderPic.getPic().equals(designTaskOrderPicCustoms.get(2).getPic())) {
	    			resMap.put("designTaskOrderPic2", designTaskOrderPic.getPic());
	    			
	    		}
	    	}
			
		}
		  
		return new ModelAndView(rtPage,resMap);
	}
	
	
	//添加或修改图片
    @RequestMapping(value = "/activityNew/saveDesignTaskOrderPic.shtml")
    @ResponseBody
    public Map<String, Object> saveDesignTaskOrderPic(HttpServletRequest request,Integer designTaskOrderId) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        String code = null;
        String msg = null;
        try {
            StaffBean staffBean = this.getSessionStaffBean(request);
            int staffId = Integer.valueOf(staffBean.getStaffID());            
            String pic=request.getParameter("pic");
            String pic2=request.getParameter("pic2");
            String pic3=request.getParameter("pic3");            
            String getWidth=request.getParameter("getWidth");
            String getHeight=request.getParameter("getHeight");
            String getPicDesc=request.getParameter("getPicDesc");            
            String getWidth1=request.getParameter("getWidth1");
            String getHeight1=request.getParameter("getHeight1");
            String getPicDesc1=request.getParameter("getPicDesc1");            
            String getWidth2=request.getParameter("getWidth2");
            String getHeight2=request.getParameter("getHeight2");
            String getPicDesc2=request.getParameter("getPicDesc2");
            DesignTaskOrder designTaskOrder=designTaskOrderService.selectByPrimaryKey(designTaskOrderId);      
            List<String> listPic=new ArrayList<>();
            listPic.add(pic);
            listPic.add(pic2);
            listPic.add(pic3);     
            DesignTaskOrderPicExample designTaskOrderPicExample1=new DesignTaskOrderPicExample();
            designTaskOrderPicExample1.createCriteria().andDelFlagEqualTo("0").andDesignTaskOrderIdEqualTo(designTaskOrderId);
            List<DesignTaskOrderPic> designTaskOrderPics1=designTaskOrderPicService.selectByExample(designTaskOrderPicExample1); 
            List<Integer> designTaskOrderPicsId=new ArrayList<>();
            if (designTaskOrderPics1!=null && designTaskOrderPics1.size()>0) {
            	 for (DesignTaskOrderPic designTaskOrderPic : designTaskOrderPics1) {
            		 designTaskOrderPicsId.add(designTaskOrderPic.getId());
				}	
			}                                     
             if (designTaskOrderPicsId!=null && designTaskOrderPicsId.size()>0) {
            	 DesignTaskOrderPicExample designTaskOrderPicExample=new DesignTaskOrderPicExample();
         		 designTaskOrderPicExample.createCriteria().andDelFlagEqualTo("0").andIdIn(designTaskOrderPicsId);
         		 List<DesignTaskOrderPic> designTaskOrderPics=designTaskOrderPicService.selectByExample(designTaskOrderPicExample); 
            	 if (designTaskOrder.getTaskType().equals("1")) {             
            		 for (DesignTaskOrderPic designTaskOrderPic1 : designTaskOrderPics) {
            			 DesignTaskOrderPic designTaskOrderPic=designTaskOrderPicService.selectByPrimaryKey(designTaskOrderPic1.getId());
            			 if (designTaskOrderPic.getPicDetail().equals(getWidth+"*"+getHeight+","+getPicDesc)) {
            				 designTaskOrderPic.setPic(pic);
            			 }else if (designTaskOrderPic.getPicDetail().equals(getWidth1+"*"+getHeight1+","+getPicDesc1)) {
            				 designTaskOrderPic.setPic(pic2);
            			 }else if (designTaskOrderPic.getPicDetail().equals(getWidth2+"*"+getHeight2+","+getPicDesc2)) {
            				 designTaskOrderPic.setPic(pic3);
            			 }
            			 designTaskOrderPic.setUpdateBy(staffId);
            			 designTaskOrderPic.setUpdateDate(new Date());
            			 designTaskOrderPicService.updateByPrimaryKeySelective(designTaskOrderPic);
            		 }
					
				}else {
					DesignTaskOrderPic designTaskOrderPic=new DesignTaskOrderPic();
       			    designTaskOrderPic.setUpdateBy(staffId);
       			    designTaskOrderPic.setUpdateDate(new Date());
       			    designTaskOrderPic.setDelFlag("1");
       			    designTaskOrderPicService.updateByExampleSelective(designTaskOrderPic, designTaskOrderPicExample);	      			    
       			 for (String pic1 : listPic) {
              		DesignTaskOrderPic designTaskOrderPic1=new DesignTaskOrderPic();
              		designTaskOrderPic1.setDelFlag("0");
              		designTaskOrderPic1.setDesignTaskOrderId(designTaskOrderId);
              		designTaskOrderPic1.setPic(pic1);
              		if (pic1.equals(pic)) {         
              			designTaskOrderPic1.setPicDetail(getWidth+"*"+getHeight+","+getPicDesc);
              		}else if (pic1.equals(pic2)) {            	
              			designTaskOrderPic1.setPicDetail(getWidth1+"*"+getHeight1+","+getPicDesc1);
              		}else if (pic1.equals(pic3)) {
              			designTaskOrderPic1.setPicDetail(getWidth2+"*"+getHeight2+","+getPicDesc2);
              		}
              		designTaskOrderPic1.setCreateBy(staffId);
              		designTaskOrderPic1.setCreateDate(new Date());
              		designTaskOrderPicService.insert(designTaskOrderPic1);
              	  }
				}				
             	designTaskOrder.setUploadCount(2);             	
           	    designTaskOrder.setUpdateBy(staffId);
           	    designTaskOrder.setUpdateDate(new Date());
           	    designTaskOrderService.updateByPrimaryKeySelective(designTaskOrder);                
            }else {
            	 for (String pic1 : listPic) {
             		DesignTaskOrderPic designTaskOrderPic=new DesignTaskOrderPic();
             		designTaskOrderPic.setDelFlag("0");
             		designTaskOrderPic.setDesignTaskOrderId(designTaskOrderId);
             		designTaskOrderPic.setPic(pic1);
             		if (pic1.equals(pic)) {
             			designTaskOrderPic.setPicDetail(getWidth+"*"+getHeight+","+getPicDesc);
             		}else if (pic1.equals(pic2)) {
             			designTaskOrderPic.setPicDetail(getWidth1+"*"+getHeight1+","+getPicDesc1);
             		}else if (pic1.equals(pic3)) {
             			designTaskOrderPic.setPicDetail(getWidth2+"*"+getHeight2+","+getPicDesc2);
             		}
             		designTaskOrderPic.setCreateBy(staffId);
             		designTaskOrderPic.setCreateDate(new Date());
             		designTaskOrderPicService.insert(designTaskOrderPic);
             	}
             	
             	designTaskOrder.setUploadCount(1);
             	designTaskOrder.setDesignStatus("1");
             	designTaskOrder.setUpdateBy(staffId);
             	designTaskOrder.setUpdateDate(new Date());
             	designTaskOrderService.updateByPrimaryKeySelective(designTaskOrder);
			}
             
          code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
          msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "保存失败，请稍后重试");
            return resMap;
        }
        resMap.put(this.JSON_RESULT_CODE, code);
        resMap.put(this.JSON_RESULT_MESSAGE, msg);

        return resMap;
    }
	
	
	//获取附件
	@RequestMapping(value = "/designTaskOrder/checkFileExists.shtml")
	@ResponseBody
	public Map<String, Object> checkFileExists(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "200");
		map.put("msg", "操作成功！");
		String attachmentPath = request.getParameter("attachmentPath");
		if(!StringUtils.isEmpty(attachmentPath)){
			InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
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
				map.put("code", "4004");
				map.put("msg", "文件目录不存在");
				return map;
			}
			File file = new File(defaultPath+attachmentPath);
			//如果文件不存在
			if(!file.exists()){
				map.put("code", "4004");
				map.put("msg", "附件不存在或已被删除");
				return map;
			}
		}else{
			map.put("code", "4004");
			map.put("msg", "附件不存在或已被删除");
			return map;
		}
		return map;
	}
	
	
	//下载附件
	@RequestMapping(value = "/designTaskOrder/downLoadAttachment.shtml")
	public void downLoadAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("fileName");
		InputStream stream = OrderController.class.getResourceAsStream("/base_config.properties");
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
		String downloadFileName = "";
		String agent = request.getHeader("USER-AGENT");  
        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
        {
            downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";    
        }
        else
        {
            downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
        }
		response.setHeader("content-disposition", "attachment;filename=" + downloadFileName);
		//读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(defaultPath+filePath);
		//创建输出流
		OutputStream out = response.getOutputStream();
		//缓存区
		byte buffer[] = new byte[1024];
		int len = 0;
		//循环将输入流中的内容读取到缓冲区中
		while((len = in.read(buffer)) > 0){
		    out.write(buffer, 0, len);
		}
		//关闭
		in.close();
		out.flush();
		out.close(); 
	}
}
