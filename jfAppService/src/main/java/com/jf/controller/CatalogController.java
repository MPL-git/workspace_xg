package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.Catalog;
import com.jf.entity.CatalogExample;
import com.jf.entity.Information;
import com.jf.entity.InformationExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.service.CatalogService;
import com.jf.service.InformationService;
import com.jf.service.SysParamCfgService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CatalogController extends BaseController {

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private InformationService informationService;
	
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	/**
	 * 
	 * @Title getCatalogList   
	 * @Description TODO(获取规则)   
	 * @author pengl
	 * @date 2018年12月6日 下午12:53:55
	 */
	@ResponseBody
	@RequestMapping(value = "/api/y/getCatalogList")
	public ResponseMsg getCatalogList(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"type"};
			this.requiredStr(obj, request);
			String catalogId = null;
			if(reqDataJson.containsKey("catalogId")) {
				catalogId = reqDataJson.getString("catalogId");
			}
			String type = reqDataJson.getString("type");
			CatalogExample catalogExample = new CatalogExample();
			CatalogExample.Criteria catalogCriteria = catalogExample.createCriteria();
			catalogCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			if(StringUtil.isEmpty(catalogId)) {
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
				if("1".equals(type)) { //醒购规则
					sysParamCfgCriteria.andParamCodeEqualTo(Const.CATALOG_REGULATION_ID);
					List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
					if(sysParamCfgList != null && sysParamCfgList.size() > 0) {
						catalogId = sysParamCfgList.get(0).getParamValue();
					}
				}else if("2".equals(type)) { //关于醒购
					sysParamCfgCriteria.andParamCodeEqualTo(Const.CATALOG_REGARD_ID);
					List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
					if(sysParamCfgList != null && sysParamCfgList.size() > 0) {
						catalogId = sysParamCfgList.get(0).getParamValue();
					}
				}
			}
			catalogCriteria.andParentIdEqualTo(Integer.parseInt(catalogId));
			catalogExample.setOrderByClause(" seq_no asc, id asc");
			List<Catalog> catalogList = catalogService.selectByExample(catalogExample);
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			if(catalogList != null && catalogList.size() > 0) {
				for(Catalog catalog : catalogList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", catalog.getFrontName());
					map.put("id", catalog.getId());
					map.put("type", "1");
					returnList.add(map);
				}
			}else {
				InformationExample informationExample = new InformationExample();
				informationExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andInfoTypeLike("%2%").andCatalogIdEqualTo(Integer.parseInt(catalogId));
				informationExample.setOrderByClause(" seq_no asc, id asc");
				List<Information> informationList = informationService.selectByExample(informationExample);
				for(Information information : informationList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", information.getTitle());
					map.put("id", information.getId());
					map.put("type", "2");
					returnList.add(map);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnList);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * @Title downLoadInformationWordUrl   
	 * @Description TODO(信息规则下载)   
	 * @author pengl
	 * @throws IOException 
	 * @date 2018年12月6日 下午3:09:16
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/downLoadInformationWordUrl")
	public ResponseMsg downLoadInformationWordUrl(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Object[] obj = {"type"};
			this.requiredStr(obj, request);
			String type = reqDataJson.getString("type");
			String id = null;
			if(reqDataJson.containsKey("id")) {
				id = reqDataJson.getString("id");
			}
			// id为空时，以type约定获取规则
			if(StringUtil.isEmpty(id)) {
				if("1".equals(type)){ //醒购隐私政策
					id = "159";
				}else if("2".equals(type)){ //定金不退预售协议
					id = "193";
				}else if("3".equals(type)){ //醒购用户协议
					id = "163";
				}else if("4".equals(type)){ //醒购服务条款
					id = "164";
				}else if("5".equals(type)){ //关于醒购
					id = "165";
				}else if("6".equals(type)){ //评价规则
					id = "187";
				}
			}
			if(!StringUtil.isEmpty(id)) {
				InformationExample informationExample = new InformationExample();
				informationExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
				List<Information> informationList = informationService.selectByExample(informationExample);
				if(CollectionUtils.isNotEmpty(informationList)) {
					Information information = informationList.get(0);
					String fileSite = information.getFileSite();
					Map<String,Object> map = new HashMap<String,Object>();
					if(!StringUtil.isEmpty(fileSite)) {
						map.put("downLoadInformationWordUrl", StringUtil.getPic(fileSite, ""));
						map.put("downLoadButton", true);
					}else {
						map.put("downLoadInformationWordUrl", "");
						map.put("downLoadButton", false);
					}
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
				}
			}
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
    }	
	
	
}
