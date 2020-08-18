package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordExample;
import com.jf.entity.SysParamCfg;
import com.jf.service.HotSearchWordService;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private AppContext appContext;

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
			hotSearchWordService.addHotSearchWord(reqDataJson);
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
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			HotSearchWordExample hotSearchWordExample = new HotSearchWordExample();
			HotSearchWordExample.Criteria c = hotSearchWordExample.createCriteria();
			c.andStatusEqualTo("1").andDelFlagEqualTo("0");
			String hotSearchPage = "";
			if(reqDataJson.containsKey("hotSearchPage")){
				hotSearchPage = reqDataJson.getString("hotSearchPage");
			}
			if(StringUtil.isEmpty(hotSearchPage)){
				c.andHotSearchPageEqualTo("1");
			}else{
				c.andHotSearchPageEqualTo(hotSearchPage);
			}
			hotSearchWordExample.setLimitStart(0);
			hotSearchWordExample.setLimitSize(20);
			hotSearchWordExample.setOrderByClause("ifnull(seq_no,99999),id desc");
			List<HotSearchWord> hotSearchWords = hotSearchWordService.selectByExample(hotSearchWordExample);
			if(CollectionUtil.isNotEmpty(hotSearchWords)){
				for (HotSearchWord hotSearchWord : hotSearchWords) {
					Map<String, String> dataMap = new HashMap<String, String>();
					dataMap.put("searchName", hotSearchWord.getWord());
					String tag = "";
					if(!StringUtil.isEmpty(hotSearchWord.getTag())){
						tag = hotSearchWord.getTag();
					}
					dataMap.put("tag", tag);
					list.add(dataMap);
				}
			}
			map.put("dataList", list);
			String pic = "";
			List<SysParamCfg> sysParamCfgList = DataDicUtil.getSysParamCfg("APP_WETAO_GUIDE_PIC_2");
			if(CollectionUtils.isNotEmpty(sysParamCfgList)){
				pic = StringUtil.getPic(sysParamCfgList.get(0).getParamValue(), "");
			}
			map.put("pic", pic);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 * 随机获取 5个 搜索热词
	 */
	@RequestMapping(value = "/api/n/randomHotSearchWord")
	@ResponseBody
	public ResponseMsg randomOneHotSearchWord() {
        JSONObject reqDataJson = appContext.reqData();
        String hotSearchPage = reqDataJson.optString("hotSearchPage"); //1.首页/商城热搜 2.淘宝优选热搜
        List<Map<String,Object>> hotSearchWordList = hotSearchWordService.randomHotSearchWord(hotSearchPage, 5);
        return ResponseMsg.success(hotSearchWordList);
	}
}
