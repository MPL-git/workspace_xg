package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.HelpTagMapper;
import com.jf.entity.HelpItem;
import com.jf.entity.HelpItemExample;
import com.jf.entity.HelpTag;
import com.jf.entity.HelpTagExample;

@Service
@Transactional
public class HelpTagService extends BaseService<HelpTag, HelpTagExample> {
	@Autowired
	private HelpTagMapper helpTagMapper;
	@Autowired
	private HelpItemService helpItemService;
	@Autowired
	public void setHelpTagMapper(HelpTagMapper helpTagMapper) {
		this.setDao(helpTagMapper);
		this.helpTagMapper = helpTagMapper;
	}
	public List<HelpTag> getHelpTagList() {
		HelpTagExample helpTagExample = new HelpTagExample();
		helpTagExample.createCriteria().andDelFlagEqualTo("0");
		helpTagExample.setOrderByClause("seq_no desc,id desc");
		List<HelpTag> helpTags = selectByExample(helpTagExample);
		return helpTags;
	}
	public List<HelpItem> getHelpItemList(Integer helpTagId) {
		HelpItemExample helpItemExample = new HelpItemExample();
		helpItemExample.createCriteria().andHelpTagIdEqualTo(helpTagId).andDelFlagEqualTo("0");
		helpItemExample.setOrderByClause("seq_no desc,id desc");
		List<HelpItem> helpItems = helpItemService.selectByExample(helpItemExample);
		if(CollectionUtils.isNotEmpty(helpItems)){
			for (HelpItem helpItem : helpItems) {
				String content = "";
				String c = helpItem.getContent() == null ? "" : helpItem.getContent();
				String[] contents = c.split("\n");
				for (String str : contents) {
					if(!StringUtil.isBlank(str)){
						content+=str+"<br>";
					}
				}
				helpItem.setContent(content);
			}
		}
		return helpItems;
	}
	public List<HelpItem> searchHelpItemList(String helpName) {
		HelpItemExample helpItemExample = new HelpItemExample();
		helpItemExample.createCriteria().andTitleLike("%"+helpName+"%").andDelFlagEqualTo("0");
		helpItemExample.setOrderByClause("seq_no desc,id desc");
		List<HelpItem> helpItems = helpItemService.selectByExample(helpItemExample);
		if(CollectionUtils.isNotEmpty(helpItems)){
			for (HelpItem helpItem : helpItems) {
				String content = "";
				String c = helpItem.getContent() == null ? "" : helpItem.getContent();
				String[] contents = c.split("\n");
				for (String str : contents) {
					if(!StringUtil.isBlank(str)){
						content+=str+"<br>";
					}
				}
				helpItem.setContent(content);
			}
		}
		return helpItems;
	}
	
	
}
