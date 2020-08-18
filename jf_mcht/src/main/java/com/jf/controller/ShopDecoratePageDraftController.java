package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.SysConfig;
import com.jf.entity.MchtHomeInfo;
import com.jf.entity.MchtUser;
import com.jf.entity.ShopDecoratePageDraft;
import com.jf.entity.ShopModuleDraft;
import com.jf.service.CommentService;
import com.jf.service.MchtInfoService;
import com.jf.service.ShopDecoratePageDraftService;
import com.jf.service.ShopScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 店铺装修页面控制层
 * @Author: zhen.li
 * @Date: 2018/11/7 15:05
 */
@Controller
@RequestMapping("/shopDecoratePageDraft")
public class ShopDecoratePageDraftController extends BaseController {

	@Autowired
	private ShopDecoratePageDraftService shopDecoratePageDraftService;

	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ShopScoreService shopScoreService;
	
	/**
	 * 店铺装修主页
	 *
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/decorateIndex")
	public String decorateIndex(Model model, HttpServletRequest request) {
	//店铺DSR
	MchtUser currentUser = (MchtUser)request.getSession().getAttribute(BaseDefine.MCHT_USER);
	MchtHomeInfo mchtHomeInfo=mchtInfoService.selectMchtHomeByPrimaryKey(currentUser.getMchtId());
	double productScore = commentService.countProductScoreByMhctId(mchtHomeInfo.getId());
	model.addAttribute("productScore", productScore);
	List<HashMap<String,Object>> list = shopScoreService.countShopScoreByMhctId(mchtHomeInfo.getId());
	model.addAttribute("mchtScore", list.get(0).get("mchtScore"));
	model.addAttribute("wuliuScore", list.get(0).get("wuliuScore"));
	
	
	Integer mchtId = this.getSessionMchtInfo(request).getId();
	Integer userId = this.getSessionUserInfo(request).getId();
	List<ShopDecoratePageDraft> shopDecoratePageDrafts = shopDecoratePageDraftService.getShopDecoratePageDrafts(mchtId);
	if (shopDecoratePageDrafts == null || shopDecoratePageDrafts.isEmpty()) {
		shopDecoratePageDraftService.initDecoratePageDraft(mchtId, userId);
	}
	Map<String, Object> data = shopDecoratePageDraftService.findData(mchtId, userId);
	model.addAttribute("data", data);
	model.addAttribute("mchtId", this.getSessionMchtInfo(request).getId());
	model.addAttribute("previewServerUrl", SysConfig.previewServerUrl);
	return "shopDecorate/shopDecorateIndex";
	}

	/**
	 * 头部背景
	 *
	 * @return
	 */
	@RequestMapping("/bannerEdit")
	public String bannerEdit(Model model, Integer decoratePageId) {
		ShopDecoratePageDraft shopDecoratePageDraft = shopDecoratePageDraftService.selectByPrimaryKey(decoratePageId);
		if (shopDecoratePageDraft != null) {
			model.addAttribute("decoratePage", shopDecoratePageDraft);
		}
		return "shopDecorate/bannerEdit";
	}

	/**
	 * 店铺LOGO
	 *
	 * @return
	 */
	@RequestMapping("/shopLogoEdit")
	public String shopLogoEdit(Model model, Integer decoratePageId) {
		ShopDecoratePageDraft shopDecoratePageDraft = shopDecoratePageDraftService.selectByPrimaryKey(decoratePageId);
		if (shopDecoratePageDraft != null) {
			model.addAttribute("decoratePage", shopDecoratePageDraft);
		}
		return "shopDecorate/shopLogoEdit";
	}

    /**
     * 编辑模块
     *
     * @return
     */
    @RequestMapping("/moduleEdit")
    public String moduleEdit(Model model, Integer shopModuleId, Integer shopDecorateAreaId) {
        if (shopModuleId != null) {
            model.addAttribute("moduleData", shopDecoratePageDraftService.getShopModuleAndPicMap(shopModuleId));
        }
        model.addAttribute("shopDecorateAreaId", shopDecorateAreaId);
        return "shopDecorate/moduleEdit";
    }

	/**
	 * 保存头部背景
	 *
	 * @param request
	 * @param shopDecoratePageDraft
	 * @return
	 */
	@RequestMapping("/saveBanner")
	@ResponseBody
	public ResponseMsg saveBanner(HttpServletRequest request, ShopDecoratePageDraft shopDecoratePageDraft) {
		try {
			shopDecoratePageDraft.setMchtId(this.getSessionMchtInfo(request).getId());
			shopDecoratePageDraft.setUpdateBy(this.getSessionUserInfo(request).getId());
			shopDecoratePageDraftService.saveBanner(shopDecoratePageDraft);
		} catch (ArgException arge) {
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 保存头像图片
	 *
	 * @param request
	 * @param shopDecoratePageDraft
	 * @return
	 */
	@RequestMapping("/saveShopLogo")
	@ResponseBody
	public ResponseMsg saveShopLogo(HttpServletRequest request, ShopDecoratePageDraft shopDecoratePageDraft) {
		try {
			shopDecoratePageDraft.setMchtId(this.getSessionMchtInfo(request).getId());
			shopDecoratePageDraft.setUpdateBy(this.getSessionUserInfo(request).getId());
			shopDecoratePageDraftService.saveShopLogo(shopDecoratePageDraft);
		} catch (ArgException arge) {
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 保存模块
	 *
	 * @param request
	 * @param shopModuleDraft
	 * @return
	 */
	@RequestMapping("/saveModuleDraft")
	@ResponseBody
	public ResponseMsg saveModuleDraft(HttpServletRequest request, ShopModuleDraft shopModuleDraft, String moduleMapJsonStr) {
		try {
			shopModuleDraft.setMchtId(this.getSessionMchtInfo(request).getId());
			Integer userId = this.getSessionUserInfo(request).getId();
			shopDecoratePageDraftService.saveModuleDraft(shopModuleDraft, userId, moduleMapJsonStr);
		} catch (ArgException arge) {
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 上下移模块
	 *
	 * @param request
	 * @param firstModuleId
	 * @param secondModuleId
	 * @return
	 */
	@RequestMapping("/changeSeqNo")
	@ResponseBody
	public ResponseMsg changeSeqNo(HttpServletRequest request, Integer firstModuleId, Integer secondModuleId) {
		try {
			Integer userId = this.getSessionUserInfo(request).getId();
			shopDecoratePageDraftService.changeSeqNo(firstModuleId, secondModuleId, userId);
		} catch (ArgException arge) {
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 删除模块
	 *
	 * @param request
	 * @param moduleId
	 * @return
	 */
	@RequestMapping("/deleteModule")
	@ResponseBody
	public ResponseMsg deleteModule(HttpServletRequest request, Integer moduleId) {
		try {
			Integer userId = this.getSessionUserInfo(request).getId();
			Integer mchtId = this.getSessionMchtInfo(request).getId();
			shopDecoratePageDraftService.deleteModule(mchtId, moduleId, userId);
		} catch (ArgException arge) {
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 发布装修
	 *
	 * @param request
	 * @param decoratePageId
	 * @return
	 */
	@RequestMapping("/announceDecorate")
	@ResponseBody
	public ResponseMsg announceDecorate(HttpServletRequest request, Integer decoratePageId) {
		try {
			Integer mchtId = this.getSessionMchtInfo(request).getId();
			Integer userId = this.getSessionUserInfo(request).getId();
			shopDecoratePageDraftService.announceDecorate(decoratePageId, mchtId, userId);
		} catch (ArgException arge) {
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 还原
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/restoreDecorate")
	@ResponseBody
	public ResponseMsg restoreDecorate(HttpServletRequest request) {
		try {
			Integer mchtId = this.getSessionMchtInfo(request).getId();
			Integer userId = this.getSessionUserInfo(request).getId();
			shopDecoratePageDraftService.restoreDecorate(mchtId, userId);
		} catch (ArgException arge) {
			arge.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

    /**
     * 校验链接填写的商家code或者商品code是否存在对应的数据
     *
     * @param linkType
     * @param linkValue
     * @return
     */
    @RequestMapping("/checkLink")
    @ResponseBody
    public ResponseMsg checkLink(Integer linkType, String linkValue) {
        try {
            shopDecoratePageDraftService.checkLink(linkType, linkValue);
        } catch (ArgException arge) {
            arge.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
        }
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }
}
