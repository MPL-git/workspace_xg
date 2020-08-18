package com.jf.service;

import com.jf.common.utils.StringUtils;
import com.jf.dao.ActivityAreaMapper;
import com.jf.dao.LandingPageCustomMapper;
import com.jf.dao.LandingPageMapper;
import com.jf.dao.LandingPagePicMapper;
import com.jf.entity.LandingPage;
import com.jf.entity.LandingPageCustom;
import com.jf.entity.LandingPageExample;
import com.jf.entity.LandingPagePic;
import com.jf.entity.LandingPagePicExample;
import com.jf.entity.StateCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LandingPageService extends BaseService<LandingPage, LandingPageExample>{

	@Autowired
	private LandingPageCustomMapper landingPageCustomMapper;
    @Autowired
	private LandingPageMapper landingPageMapper;
    @Autowired
	private LandingPagePicMapper landingPagePicMapper;

	@Autowired
	public void setlandingPageMapper(LandingPageMapper landingPageMapper) {
		super.setDao(landingPageMapper);
		this.landingPageMapper = landingPageMapper;
	}

	public Integer countLandingPagesCustomByExample(LandingPageExample landingPageExample) {
		return landingPageCustomMapper.countLandingPagesCustomByExample(landingPageExample);
	}

	public List<LandingPageCustom> selectLandingPagesCustomByExample(LandingPageExample landingPageExample) {
		return landingPageCustomMapper.selectLandingPagesCustomByExample(landingPageExample);
	}

    public Map<String, Object> submit(HttpServletRequest request, String staffId) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		String msg = "提交成功！";
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String editorRecommend = request.getParameter("editorRecommend");
		String applicationInformation = request.getParameter("applicationInformation");
		String remarks = request.getParameter("remarks");
		String landingPagePics = request.getParameter("landingPagePics");
		LandingPage landingPage = new LandingPage();
		try {
			if(StringUtils.isEmpty(id)) {
				landingPage.setCreateBy(Integer.parseInt(staffId));
				landingPage.setCreateDate(new Date());
			}else {
				landingPage = landingPageMapper.selectByPrimaryKey(Integer.parseInt(id));
				landingPage.setUpdateBy(Integer.parseInt(staffId));
				landingPage.setUpdateDate(new Date());
			}
				landingPage.setName(name);
				landingPage.setEditorRecommend(editorRecommend);
				landingPage.setApplicationInformation(applicationInformation);
				landingPage.setRemarks(remarks);

			if (StringUtils.isEmpty(id)) {
				landingPageMapper.insertSelective(landingPage);
			} else {
				landingPageMapper.updateByPrimaryKeySelective(landingPage);
				//删除原有图片
				LandingPagePicExample landingPagePicExample = new LandingPagePicExample();
				landingPagePicExample.createCriteria().andLandingPageIdEqualTo(landingPage.getId()).andDelFlagEqualTo("0");
				LandingPagePic landingPagePic = new LandingPagePic();
				landingPagePic.setDelFlag("1");
				landingPagePicMapper.updateByExampleSelective(landingPagePic,landingPagePicExample);
			}
			//图片存储
				JSONArray landingPagePicList = JSONArray.fromObject(landingPagePics);
				List<String> picList = (List<String>) JSONArray.toCollection(landingPagePicList,String.class);
				for (String pic : picList){
					LandingPagePic landingPagePic = new LandingPagePic();
					landingPagePic.setPic(pic);
					landingPagePic.setLandingPageId(landingPage.getId());
					landingPagePic.setCreateBy(Integer.parseInt(staffId));
					landingPage.setCreateDate(new Date());
					landingPagePicMapper.insertSelective(landingPagePic);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_DB_INVOKE.getStateMessage();
		}
		map.put("statusCode", code);
		map.put("message", msg);
		return map;
    }
}
