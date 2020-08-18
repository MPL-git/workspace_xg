package com.jf.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.ResultBean;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
/**
 * web转发控制器的公共基础类，集成了一些基本的方法
 */
public class BaseController  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> map = new HashMap<String, Object>();
	//protected Pagination page = new Pagination();
	public final static String userSessionKey = "USER_SESSION";
	protected final  String ERROR_PAGE = "/common/success";     //成功页面
	protected final  String SUCCESS_PAGE = "/common/error";  	//系统错误页面
	protected final  String RES_MSG_PAGE = "/common/common_msg";  	//系统操作公共提示页面
	protected final  String OPER_RESULT = "/common/result";		//操作结果页面
	protected final  String SUCCESS_MESSAGE = StateCode.SUCCESS.getStateMessage();       //成功提示语
	
	//------------ 分页条 参数设置 -----
	private String  PAGE_NUM_SHOW="20"; //数字展现导航页个数  
	private String  PAGE_PER_NUM="20";   //每页展示多少条记录
	
	//------ ajax 操作类返回JSON 数据格式{"statusCode"："","message"：""}-----
	protected final  String  JSON_RESULT_CODE ="statusCode";  //返回结果编码
	protected final  String  JSON_RESULT_MESSAGE="message";     //结果说明
	
	/*
	 * 参数不能为空验证 应用实例如下：<br>
	 *  String[] paramsArray = {"STAFF_CODE:工号","PASSWD:密码"};<br>
	 *	Map<String,Object> paraMap = JSONFactory.JSONToMap(params);<br>
	 *	Map<String,Object> map = CommUtil.requiredCheck(paramsArray, paraMap);<br>
	 *	if(map!=null){<br>
	 *		return map;<br>
	 *	}<br>
	 * @param checks
	 * @param params
	 * @return
	 */
	protected ResultBean requiredCheck(String[] checks, Map<String, Object> params) {
		try {
			for (String item : checks) {
				String parameterName = this.getParameterName(item);

				// 参数是否存在，不存在则返回false
				if (!params.containsKey(parameterName)) {
					return ResultBean.getResultBean(StateCode.ERR_OTHER.getStateCode(), this.getResultMessage(item));
				}

				Object value = params.get(parameterName);
				if (value == null) {
					return ResultBean.getResultBean(StateCode.ERR_OTHER.getStateCode(), this.getResultMessage(item));
				}

				if (value instanceof String) {
					String s = (String) value;
					if ("".equals(s.trim())) {
						return ResultBean.getResultBean(StateCode.ERR_OTHER.getStateCode(), this.getResultMessage(item));
					}
				}
			}
		} catch (Exception e) {
			return ResultBean.getResultBean(StateCode.ERR_OTHER.getStateCode(), "入参不为空验证不通过.");
		}
		return ResultBean.getResultBean(StateCode.SUCCESS.getStateCode(),StateCode.SUCCESS.getStateMessage());
	}
	private String getResultMessage(String item) {
		String parameterName = this.getParameterName(item);
		String parameterDesc = this.getParameterDesc(item);

		StringBuffer buffer = new StringBuffer();
		buffer.append("参数不合法:").append(parameterName);
		buffer.append("(").append(parameterDesc).append(")");
		buffer.append("不能为空");
		return buffer.toString();
	}

	/**
	 * 取验证参数中文名
	 * @param item
	 * @return
	 * @author huangcp Mar 3, 2014
	 */
	private String getParameterName(String item) {
		return item.substring(0, item.indexOf(":"));
	}

	/**
	 * 取验证参数编码
	 * @param item
	 * @return
	 * @author huangcp Mar 3, 2014
	 */
	private String getParameterDesc(String item) {
		return item.substring(item.indexOf(":") + 1);
	}
 protected  HashMap<String,Object> setPageParameters(HttpServletRequest request,HashMap<String,Object> paramMap ){
	   if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		String numPerPage = StringUtil.valueOf(paramMap.get("pagesize"));
		numPerPage = !"".equals(numPerPage) ? numPerPage : this.PAGE_PER_NUM;
		String pageNum = StringUtil.valueOf(paramMap.get("page"));
		pageNum = !"".equals(pageNum) ? pageNum : "1";
		

		request.setAttribute("numPerPage", numPerPage); // 每页多少条
		request.setAttribute("currentPage", pageNum); // 当前页码
		request.setAttribute("pageNumShown", this.PAGE_NUM_SHOW); // 数字展现导航页个数
		String totalCount=StringUtil.valueOf(request.getParameter("totalCount"));
		String isFirst=StringUtil.valueOf(request.getParameter("isFirst"));
		request.setAttribute("totalCount",totalCount); // 总共多少条
		request.setAttribute("isFirst", isFirst); // 非第一次翻页面记录 

		int curPageId = Integer.valueOf(pageNum);
		int pageRecord = Integer.valueOf(numPerPage);
		paramMap.put("MAX_NUM", pageRecord); // 每页查询多少条
		paramMap.put("MIN_NUM", (curPageId - 1) * pageRecord); // 当前页最小记录行号
	   /*
		 * String startDate=StringUtil.valueOf(map.get("START_DATE")); String
		 * endDate=StringUtil.valueOf(map.get("END_DATE"));
		 * if(!"".equals(startDate)){ map.put("START_DATE",
		 * startDate.replaceAll("-", "")); } if(!"".equals(endDate)){
		 * map.put("END_DATE", endDate.replaceAll("-", "")); }
		 */
       
       return paramMap; 
   }
 
 protected  HashMap<String,Object> setPageParametersLiger(HttpServletRequest request,HashMap<String,Object> paramMap ){
	   if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		String numPerPage = StringUtil.valueOf(paramMap.get("pagesize"));
		if(paramMap.get("pageSizeSearch")!=null&&!StringUtil.isEmpty(StringUtil.valueOf(paramMap.get("pageSizeSearch")))){
			numPerPage=StringUtil.valueOf(paramMap.get("pageSizeSearch"));
		}
		numPerPage = !"".equals(numPerPage) ? numPerPage : this.PAGE_PER_NUM;
		String pageNum = StringUtil.valueOf(paramMap.get("page"));
		pageNum = !"".equals(pageNum) ? pageNum : "1";
		
	/*	String totalCount=StringUtil.valueOf(request.getParameter("totalCount"));
		String isFirst=StringUtil.valueOf(request.getParameter("isFirst"));
		request.setAttribute("totalCount",totalCount); // 总共多少条
		request.setAttribute("isFirst", isFirst); // 非第一次翻页面记录 
*/
		int curPageId = Integer.valueOf(pageNum);
		int pageRecord = Integer.valueOf(numPerPage);
		paramMap.put("MAX_NUM", pageRecord); // 每页查询多少条
		paramMap.put("MIN_NUM", (curPageId - 1) * pageRecord); // 当前页最小记录行号
	   /*
		 * String startDate=StringUtil.valueOf(map.get("START_DATE")); String
		 * endDate=StringUtil.valueOf(map.get("END_DATE"));
		 * if(!"".equals(startDate)){ map.put("START_DATE",
		 * startDate.replaceAll("-", "")); } if(!"".equals(endDate)){
		 * map.put("END_DATE", endDate.replaceAll("-", "")); }
		 */
     
     return paramMap; 
 }
 
	
	
    /**  
     * 初始化分页基本参数  
     */  
 protected void initPage(HttpServletRequest request,String totalCount ){    
       
        /**页面状态,这个状态是分页自带的,与业务无关*/  
		request.setAttribute("totalCount", totalCount); // 总共多少条
		request.setAttribute("isFirst", "0"); // 非第一次翻页面记录  
    }    
     
    /**
     * 查询条件赋值
     */
   /* public void setPageParameters(Map<String, Object> map, String maxNumKey, String minNumKey,Pagination page) {
    	if(!StringUtil.isEmpty(StringUtil.objToStr(map.get("pageRecord")))){
    		page.setPageRecord(Integer.parseInt(StringUtil.objToStr(map.get("pageRecord"))));
    	}
		Float pageRecord = Float.valueOf(page.getPageRecord());
		Float TotalRecord = Float.valueOf(page.getTotalRecord());
		double i = Math.ceil(TotalRecord / pageRecord);
		i = i == 0 ? 1 : i;
		page.setTotalPage((int) i);
		int targetPage=0;
		if(null==map.get("targetPage")||StringUtil.isEmpty(StringUtil.objToStr(map.get("targetPage")))){
			targetPage = page.getTargetPage();
			page.setMinNum((page.getTargetPage() - 1)
					* page.getPageRecord());
			page.setMaxNum(page.getTargetPage()
					* page.getPageRecord());
		}else{
			targetPage = Integer.parseInt(StringUtil.objToStr(map.get("targetPage")));
			page.setMinNum((targetPage - 1)* page.getPageRecord());
			page.setMaxNum(targetPage
					* page.getPageRecord());
		}
		if("1".equals(StringUtil.objToStr(map.get("isExport")))){
			page.setMinNum(0);
			page.setMaxNum(99999);
		}
		// 把当前页改为目标页
		page.setCurrentPage(targetPage);
		maxNumKey = StringUtil.isEmpty(maxNumKey) ? "MAX_NUM" : maxNumKey;
		minNumKey = StringUtil.isEmpty(minNumKey) ? "MIN_NUM" : minNumKey;
		map.put(maxNumKey, page.getMaxNum());
		map.put(minNumKey, page.getMinNum());
		// 如果是全量导出
		if (page.getIsExport().equals("all")) {
			map.put(maxNumKey, "60000");
			map.put(minNumKey, "0");
			return;
		}
	}  
    
    protected void setNewPageParameters(HttpServletRequest request,Map<String, Object> map, String maxNumKey, String minNumKey,Pagination page){
    	setPageParameters(map,maxNumKey,minNumKey,page);
    	map.put("".equals(StringUtil.valueOf(maxNumKey))?"MAX_NUM":maxNumKey, page.getPageRecord());
    	String startDate=StringUtil.valueOf(map.get("START_DATE"));
    	String endDate=StringUtil.valueOf(map.get("END_DATE"));
    	if(!"".equals(startDate)){ map.put("START_DATE", startDate.replaceAll("-", "")); }
    	if(!"".equals(endDate)){ map.put("END_DATE", endDate.replaceAll("-", "")); }
    	
    }
    
  
    //获取分页参数的基本条件
    protected Pagination getPage(HttpServletRequest request){    
        
        *//**页面状态,这个状态是分页自带的,与业务无关*//*   
        //初始化分页工具条
    	Pagination	page = new Pagination();
    	
    	if(null!=request.getAttribute("currentPage")){
    		page.setCurrentPage(Integer.parseInt(StringUtil.objToStr(request.getAttribute("currentPage"))));
    	}
		if(null!=request.getAttribute("totalPage")){
			page.setTotalPage(Integer.parseInt(StringUtil.objToStr(request.getAttribute("totalPage"))));		
		}
		if(null!=request.getAttribute("totalRecord")){
			page.setTotalRecord(Integer.parseInt(StringUtil.objToStr(request.getAttribute("totalRecord"))));
			
		}
		if(null!=request.getAttribute("pageRecord")){
			page.setPageRecord(Integer.parseInt(StringUtil.objToStr(request.getAttribute("pageRecord"))));
		}
		
		if(null!=request.getAttribute("targetPage")){
			page.setTargetPage(Integer.parseInt(StringUtil.objToStr(request.getAttribute("targetPage"))));
		}
		
       
        return page;    
    } 
    
	*//**
	 * 导出
	 * 
	 * @return
	 *//*
	@SuppressWarnings({ "rawtypes" })
	public boolean exportExcel(List<?> dataList,HttpServletResponse response,HashMap<String, Object> map) {
		try {
			
			response.setHeader("Content-Type", "application/vnd.ms-excel; charset=utf-8");
			String exportName = StringUtil.objToStr(map.get("ExportFileName"));
			String fileName = new String((exportName).getBytes(), "ISO-8859-1");
			fileName += ".xls";
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			OutputStream out = response.getOutputStream();

			// 循环数据开始导出
			jxl.write.WritableWorkbook wwb = jxl.Workbook.createWorkbook(out);
			jxl.write.WritableSheet ws1 = wwb.createSheet(exportName, 0);

			// 循环Excel列头
			ExcelCommon excelCommon = new ExcelCommon();
			String[] headerField = StringUtil.objToStr(map.get("ExportField")).split(",");
			String[] listField = new String[headerField.length];
			String headerTxt = "";
			int col = 1; // 当前的列数
			int row = 1; // 当前的行数
			int colspan = 1; // 默认合并的列数
			int rowspan = 1; // 默认合并的行数
			int maxRow = 0; // 标题占用的行数
			
			// 循环列头
			for (int i = 0, j = 0; i < headerField.length; i++) {
				try{
				String[] currHead = headerField[i].trim().split("@");
				// 取列头的文字
				String[] currHeadVal = currHead[0].trim().split(":");
				headerTxt = currHeadVal[0].trim();
				// 判断是否需获取清单
				if (!currHeadVal[1].trim().equals("NULL")) {
					listField[j] = headerField[i];
					j++;
				}
				// 获取列头的属性
				if (currHead.length == 2) {
					String[] currHeadAttr = currHead[1].trim().split(":");
					// 获取列、行、合并列数、合并行数
					col = Integer.parseInt(currHeadAttr[0]);
					row = Integer.parseInt(currHeadAttr[1]);
					if (currHeadAttr.length >= 3) {
						colspan = Integer.parseInt(currHeadAttr[2]);
					} else {
						colspan = 1; // 默认合并列为1
					}
					if (currHeadAttr.length >= 4) {
						rowspan = Integer.parseInt(currHeadAttr[3]);
					} else {
						rowspan = 1; // 默认合并行为1
					}
				} else {
					col = i + 1;
					row = 1;
					colspan = 1;
					rowspan = 1;
				}
				// 将数值转换为适用EXCEL的格式，EXCEL都是从0开始
				col--;
				row--;
				colspan--;
				rowspan--;
				
				int hcol = col + colspan;
				int hrow = row + rowspan;
				
				ws1.addCell(new jxl.write.Label(col, row, headerTxt,
						excelCommon.getBasicCellType()));
				ws1.mergeCells(col, row, hcol, hrow);
				ws1.setColumnView(col, headerTxt.length() * 2 + 5);
				// 设置表头高度
				ws1.setRowView(row, excelCommon.getCellHeight());
				System.out.println(headerTxt + ":" + col + "," + row + "," + hcol + "," + hrow);
				if(row>maxRow){
					maxRow = row;
				}
				}catch(Exception e){
					
				}
			}
			
			// 循环清单数据
			Map dataMap = null;
			String dataTxt = "";
			for (int i = 0; i < dataList.size(); i++) {
				dataMap = (Map) dataList.get(i);
				for (int j = 0; j < listField.length; j++) {
					if (listField[j] == null)
						break;
					dataTxt = (listField[j].split("@")[0]).split(":")[1]
							.trim();
					ws1.addCell(new jxl.write.Label(j, 1 + i + maxRow,
							StringUtil.objToStr(dataMap.get(dataTxt))));
				}
				ws1.setRowView(1 + i + maxRow, excelCommon.getCellHeight());
			}
			
			// 写入Exel工作表
			wwb.write();
			// 关闭Excel工作薄对象
			wwb.close();
			out.close();
			return true;
		
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
    
	*//**
	 * 保存用户信息
	 * @param request
	 * @param userInfo
	 */
    public void saveUserInfoToSession(HttpServletRequest request,StaffBean userInfo){
    	request.getSession().setAttribute(userSessionKey,userInfo);
    }
    
    /**
     * 
     * @Title saveParamSession   
     * @Description TODO(这里用一句话描述这个方法的作用)   
     * @author pengl
     * @date 2018年5月4日 下午4:24:21
     */
    public void saveParamSession(HttpServletRequest request, String key, String value){
    	request.getSession().setAttribute(key, value);
    }
    public String getParamSession(HttpServletRequest request, String key){
    	if(request.getSession().getAttribute(key) != null) {
    		return request.getSession().getAttribute(key).toString();
    	}
    	return null;
    }
    
    /**
     * 获取用户信息
     * @param request
     * @return
     */
    public StaffBean getSessionStaffBean(HttpServletRequest request){
    	if(request.getSession().getAttribute(userSessionKey)!=null){
    		if(request.getSession().getAttribute(userSessionKey) instanceof StaffBean){
    			return (StaffBean)request.getSession().getAttribute(userSessionKey);
    		}else{
    			return null;
    		}
    	}else{
    		return null;
    	}
    }
    
    /**
     * 清除用户信息
     * @param request
     */
    public void removeSessionStaffBean(HttpServletRequest request){
    	request.getSession().removeAttribute(userSessionKey);
    }
    
    /**
	 * 将List设置为输出JSON字符串，并返回包含Json的ModelAndView对象，
	 * 该方法主要用在异步访问的时候，输出list为json格式
	 * @param object 数据
	 * @param resultName 存放list的结构名称，在返回到页面的时候，需要用它来获取值
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView getMavForJson(Object object,String resultName) {
		//1、设置结果
		Map resMap = new HashMap<String,Object>();
		resMap.put(resultName,object);
				
		//2.将结果设置为JSON格式
		MappingJacksonJsonView mapJson = new MappingJacksonJsonView();
		mapJson.setAttributesMap(resMap);
		
		//3.返回控制器目标对象
		ModelAndView r = new ModelAndView();
		r.setView(mapJson);
		
		return r;
	}
	
	protected String getStr(HashMap<String, Object> map,String colName) {
		return StringUtil.objToStr(map.get(colName));
	}
	
	protected String getWebRootPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/"); 
	}


	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	protected void response_json(Map map,HttpServletResponse response){
		try {
			
			JSONArray jsonArray = JSONArray.fromObject(map);
			
	        String jsonstr = jsonArray.toString();
	        
	        String json = jsonstr.substring(1, jsonstr.length() - 1);
	       
	        response.setContentType("text/json; charset=gbk");
	        response.getWriter().write(json);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).warn("MAP转JSON异常");
		}
		
	}
	protected void response_json(List<Map<String,String>> list,HttpServletResponse response){
		try {
			JSONArray jsonArray = JSONArray.fromObject(list);
	        String jsonstr = jsonArray.toString();
	        response.setContentType("text/json; charset=gbk");
	        response.getWriter().write(jsonstr);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).warn("List转JSON异常");
		}
		
	}
	
	protected List<String> getBetweenDays(String stime,String etime){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=null;
        Date eDate=null;
        try {
             sdate=df.parse(stime);
             eDate=df.parse(etime);
        } catch (Exception e) {
              e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
              list.add(df.format(sdate));
              c.setTime(sdate);
              c.add(Calendar.DATE, 1); // 日期加1天
              sdate = c.getTime();
              }
        return list;
  }
}
