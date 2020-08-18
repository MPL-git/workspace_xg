package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.IndexTopStyleMapper;
import com.jf.entity.IndexTopStyle;
import com.jf.entity.IndexTopStyleExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IndexTopStyleService extends BaseService<IndexTopStyle, IndexTopStyleExample> {
	@Autowired
	private IndexTopStyleMapper indexTopStyleMapper;
	@Autowired
	public void setIndexTopStyleMapper(IndexTopStyleMapper indexTopStyleMapper) {
		this.setDao(indexTopStyleMapper);
		this.indexTopStyleMapper = indexTopStyleMapper;
	}
	public Map<String, Object> getTopStyles() {
		Date currentDate = new Date();
		String bgPic = "";
		String bgOtherPic = "";
		String fontType = "0"; //字体颜色(0.白色1.黑色)
		IndexTopStyleExample indexTopStyleExample = new IndexTopStyleExample();
		indexTopStyleExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0")
		.andActivityBeginDateLessThanOrEqualTo(currentDate).andActivityEndDateGreaterThanOrEqualTo(currentDate);
		indexTopStyleExample.setOrderByClause("activity_begin_date desc,id desc");
		List<IndexTopStyle> styles = selectByExample(indexTopStyleExample);
		if(CollectionUtils.isNotEmpty(styles)){
			for (IndexTopStyle style : styles) {
				String type = style.getType();
				if("0".equals(type) && StringUtil.isBlank(bgPic)){
					bgPic = StringUtil.getPic(style.getBgPic(), "");
					fontType = style.getFontType();
				}else if("1".equals(type) && StringUtil.isBlank(bgOtherPic)){
					bgOtherPic = StringUtil.getPic(style.getBgPic(), "");
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("bgPic", bgPic);
		map.put("bgOtherPic", bgOtherPic);
		map.put("fontType", fontType);
		return map;
	}
	
	
}
