package com.jf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.CollectionUtil;
import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordExample;
import com.jf.service.HotSearchWordService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 商城热搜词
  * @date 创建时间：2018年7月25日 上午9:23:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class HotSearchWordController extends BaseController{
	@Resource
	private HotSearchWordService hotSearchWordService;
	
	/**
	 * 新增收索热词
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/addHotSearchWord")
	@ResponseBody
	public ResponseMsg addHotSearchWord(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Integer memberId = getMemberId(request);
			hotSearchWordService.addHotSearchWord(reqDataJson,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}

	}
	
	/**
	 * 获取20个搜索热词
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getHotSearchWord")
	@ResponseBody
	public ResponseMsg getHotSearchWord(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
			hotSearchWordExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
			hotSearchWordExample.setLimitStart(0);
			hotSearchWordExample.setLimitSize(20);
			hotSearchWordExample.setOrderByClause("ifnull(seq_no,99999),id desc");
			List<HotSearchWord> hotSearchWords = hotSearchWordService.selectByExample(hotSearchWordExample);
			if(CollectionUtil.isNotEmpty(hotSearchWords)){
				for (HotSearchWord hotSearchWord : hotSearchWords) {
					Map<String, String> dataMap = new HashMap<String, String>();
					dataMap.put("searchName", hotSearchWord.getWord());
					list.add(dataMap);
				}
			}
			map.put("dataList", list);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
