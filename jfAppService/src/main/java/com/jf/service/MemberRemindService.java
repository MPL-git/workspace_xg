package com.jf.service;

import com.google.common.collect.Lists;
import com.jf.common.base.BaseService;
import com.jf.vo.request.PageRequest;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberRemindCustomMapper;
import com.jf.dao.MemberRemindMapper;
import com.jf.dao.VideoCustomMapper;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindCustom;
import com.jf.entity.MemberRemindExample;
import com.jf.entity.VideoCustom;
import com.jf.vo.response.VideoCollectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf:
  * @date 创建时间：2017年5月8日 下午7:47:54
  * @version 1.0
  * @parameter
  * @return
*/
@Service
@Transactional
public class MemberRemindService extends BaseService<MemberRemind, MemberRemindExample> {

	@Autowired
	private MemberRemindMapper memberRemindMapper;

	@Autowired
	private MemberRemindCustomMapper memberRemindCustomMapper;
    @Autowired
    private VideoCustomMapper videoCustomMapper;

	@Autowired
	public void setMemberRemindMapper(MemberRemindMapper memberRemindMapper) {
		this.setDao(memberRemindMapper);
		this.memberRemindMapper = memberRemindMapper;
	}

	public List<MemberRemindCustom> getProductRemindList(Map<String, Object> params) {

		return memberRemindCustomMapper.getProductRemindList(params);
	}


	public List<MemberRemindCustom> getActivityRemindList(Map<String, Object> params) {

		return memberRemindCustomMapper.getActivityRemindList(params);
	}

	public List<MemberRemindCustom> getMchtShopList(Map<String, Object> params) {

		return  memberRemindCustomMapper.getMchtShopList(params);
	}

	public List<MemberRemindCustom> getProductCollectionList(Map<String, Object> params) {

		return memberRemindCustomMapper.getProductCollectionList(params);
	}

	public Integer findCountByRemindType(Integer remindId, Integer memberId, String remindType) {
		MemberRemindExample memberRemindExample = new MemberRemindExample();
		memberRemindExample.createCriteria().andMemberIdEqualTo(memberId).andRemindIdEqualTo(remindId).andRemindTypeEqualTo(remindType).andDelFlagEqualTo("0");
		return countByExample(memberRemindExample);
	}

	public MemberRemind getMemberRemind(Integer memberId, String remindType, Integer remindId) {
		MemberRemindExample example = new MemberRemindExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andRemindIdEqualTo(remindId).andRemindTypeEqualTo(remindType).andDelFlagEqualTo(StateConst.FALSE);
		List<MemberRemind> list = selectByExample(example);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

    public List<VideoCustom> findVideoCollect(Integer memberId, PageRequest page) {
        return videoCustomMapper.findVideoCollect(memberId, page);
    }

    public List<VideoCollectView> toVideoCollectViewList(List<VideoCustom> videoCustoms) {
        if (CollectionUtils.isEmpty(videoCustoms)) return Collections.emptyList();

        List<VideoCollectView> viewList = Lists.newArrayList();
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(videoCustoms)){
            for (VideoCustom videoCustom : videoCustoms) {
                VideoCollectView view = new VideoCollectView();
                view.setVideoId(videoCustom.getId());
                view.setVideoTitle(videoCustom.getTitle());
                view.setVideoCover(StringUtil.getPic(videoCustom.getVideoCover(), ""));
				view.setVideoUrl(StringUtil.getVideo(videoCustom.getVideoUrl()));
                view.setDescription(videoCustom.getDescription());
                view.setShopName(videoCustom.getShopName());
                view.setShopLogo(StringUtil.getPic(videoCustom.getShopLogo(),""));
				if (StateConst.ONLINE.equals(videoCustom.getStatus()) && "1".equals(videoCustom.getShopStatus())) {
					view.setStatus(StateConst.ONLINE);
				}else {
					view.setStatus(StateConst.OFFLINE);
				}
                viewList.add(view);
            }
        }
        return viewList;
    }
}
