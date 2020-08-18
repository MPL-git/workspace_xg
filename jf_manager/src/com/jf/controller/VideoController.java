package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.PageConst;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class VideoController extends BaseController {
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private VideoService videoService;
	
	@Resource
	private VideoAttentionService videoAttentionService;
	
	@Resource
	private VideoColumnService videoColumnService;
	
	@Resource
	private VideoCommentService videoCommentService;
	
	@Resource
	private VideoCommentLikeService videoCommentLikeService;
	
	@Resource
	private VideoCommentReplyService videoCommentReplyService;
	
	@Resource
	private VideoExtendService videoExtendService;
	
	@Resource
	private VideoLikeService videoLikeService;
	
	@Resource
	private VideoPlayRecordService videoPlayRecordService;
	
	@Resource
	private VideoProductService videoProductService;
	
	@Resource
	private VideoPvStatisticalService videoPvStatisticalService;
	
	@Resource
	private VideoTipOffService videoTipOffService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private PlatformMsgService platformMsgService;
	
	@Resource
	private SysAppMessageService sysAppMessageService;
	
	@Resource
	private SysAppMessageMemberService sysAppMessageMemberService;
	
	@Resource
	private WeixinService weixinService;

	@Resource
	private VideoAuthorizedAccessoriesService videoAuthorizedAccessoriesService;
	
	@Resource
	private PlatformContactService platformContactService;

	//视频类目管理
	@RequestMapping("/video/columnVideo.shtml")
	public ModelAndView categoryVideo(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/video/columnVideo");
		return m;
	}
	
	    //视频类目管理列表
		@ResponseBody
		@RequestMapping("/video/columnVideoList.shtml")
		public Map<String, Object> columnVideoList(HttpServletRequest request, Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<VideoColumnCustom> dataList = null;
			Integer totalCount = 0;
			try {
				VideoColumnCustomExample videoColumnCustomExample=new VideoColumnCustomExample();
				VideoColumnCustomExample.VideoColumnCustomCriteria videoColumnCustomCriteria=videoColumnCustomExample.createCriteria();
				videoColumnCustomCriteria.andDelFlagEqualTo("0");
				videoColumnCustomExample.setOrderByClause("IFNULL(vco.seq_no, 99999999999) asc, vco.create_date desc");
				if (!StringUtil.isEmpty(request.getParameter("status"))) {
					if (request.getParameter("status").equals("0")) {
						videoColumnCustomCriteria.andStatusEqualTo("0");
					}else {
						videoColumnCustomCriteria.andStatusEqualTo("1");
					}
				}
				
				if (!StringUtil.isEmpty(request.getParameter("create_begin")) && !StringUtil.isEmpty(request.getParameter("create_end"))) {
					videoColumnCustomCriteria.andCreateDateBetween(sdf.parse(request.getParameter("create_begin")+" 00:00:00"), sdf.parse(request.getParameter("create_end")+" 23:59:59"));				
				}
				videoColumnCustomExample.setLimitStart(page.getLimitStart());
				videoColumnCustomExample.setLimitSize(page.getLimitSize());
				totalCount = videoColumnService.countByVideoColumnCustomExample(videoColumnCustomExample);
				dataList = videoColumnService.selectByVideoColumnCustomExample(videoColumnCustomExample);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		//添加视频类目界面
		@RequestMapping(value = "/video/addColumnVideo.shtml")
		public ModelAndView addColumnVideo(HttpServletRequest request) {
			String rtPage = "/video/addColumnVideo";
			Map<String, Object> resMap = new HashMap<String, Object>();
			ProductTypeExample pte = new ProductTypeExample();
			ProductTypeExample.Criteria ptec = pte.createCriteria();
			ptec.andDelFlagEqualTo("0");
			ptec.andTLevelEqualTo(1);
			ptec.andStatusEqualTo("1");
			List<ProductType> productTypes = productTypeService.selectByExample(pte);
			resMap.put("productTypes", productTypes);

			String id = request.getParameter("id");
			if (!StringUtils.isEmpty(id)) {
				VideoColumn videoColumn=videoColumnService.selectByPrimaryKey(Integer.valueOf(id));
				resMap.put("videoColumn", videoColumn);
				
			}
			
			return new ModelAndView(rtPage,resMap);
		}

		//保存视频类目
		@RequestMapping(value = "/video/toAddColumnVideo.shtml")
		@ResponseBody
		public Map<String, Object> toAddColumnVideo(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			try {
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String productType1 = request.getParameter("productType1");
				
				VideoColumn videoColumn = new VideoColumn();
				if(!StringUtils.isEmpty(id)){
					videoColumn = videoColumnService.selectByPrimaryKey(Integer.parseInt(id));
					if (!videoColumn.getName().equals(name)) {
						VideoColumnExample videoColumnExample=new VideoColumnExample();
						videoColumnExample.createCriteria().andDelFlagEqualTo("0").andNameEqualTo(name);
						List<VideoColumn> videoColumns=videoColumnService.selectByExample(videoColumnExample);
						if (videoColumns!=null && videoColumns.size()>0) {
							resMap.put("returnCode", "9999");
							resMap.put("returnMsg", "分类名称不能相同!");					
							return resMap;
						}else {
							videoColumn.setUpdateBy(staffID);
							videoColumn.setUpdateDate(new Date());
							videoColumn.setName(name);
							if (productType1.equals("0")) {
								videoColumn.setProductType1Id(null);
							}else {
								videoColumn.setProductType1Id(Integer.valueOf(productType1));
							}
							videoColumnService.updateByPrimaryKey(videoColumn);
							
						}
						
					
					}else {
						videoColumn.setUpdateBy(staffID);
						videoColumn.setUpdateDate(new Date());
						videoColumn.setName(name);
						if (productType1.equals("0")) {
							videoColumn.setProductType1Id(null);
						}else {
							videoColumn.setProductType1Id(Integer.valueOf(productType1));
						}
						videoColumnService.updateByPrimaryKey(videoColumn);
					}
				}else {
					VideoColumnExample videoColumnExample=new VideoColumnExample();
					videoColumnExample.createCriteria().andDelFlagEqualTo("0").andNameEqualTo(name);
					List<VideoColumn> videoColumns=videoColumnService.selectByExample(videoColumnExample);
					if (videoColumns!=null && videoColumns.size()>0) {
						resMap.put("returnCode", "9999");
						resMap.put("returnMsg", "分类名称不能相同!");					
						return resMap;
					}else {
						videoColumn.setCreateBy(staffID);
						videoColumn.setCreateDate(new Date());
						videoColumn.setDelFlag("0");
						videoColumn.setName(name);
						videoColumn.setProductType1Id(Integer.valueOf(productType1));
						videoColumn.setStatus("0");
						videoColumnService.insert(videoColumn);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "保存失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}
		

		//变更视频类目上下架
		@RequestMapping(value = "/video/changeColumnVieoStatus.shtml")
		@ResponseBody
		public Map<String, Object> changeColumnVieoStatus(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			try {
				String id = request.getParameter("id");
				VideoColumn videoColumn=videoColumnService.selectByPrimaryKey(Integer.valueOf(id));
				if(videoColumn.getStatus().equals("0")){
					videoColumn.setStatus("1");
				}else{
					videoColumn.setStatus("0");
				}
				videoColumn.setUpdateDate(new Date());
				videoColumn.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				videoColumnService.updateByPrimaryKeySelective(videoColumn);
			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "保存失败，请稍后重试");
				return resMap;
			}
			return resMap;
		}

		
		//删除视频类目
		@ResponseBody
		@RequestMapping("/video/delColumnVieo.shtml")
		public Map<String, Object> delColumnVieo(HttpServletRequest request, String id) {
			Map<String, Object> map = new HashMap<String, Object>(); 
			String code = "200";
			String msg = "操作成功！";
			try {
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				if(!StringUtil.isEmpty(id)) {
					VideoColumn videoColumn=videoColumnService.selectByPrimaryKey(Integer.valueOf(id));
					videoColumn.setUpdateBy(staffID);
					videoColumn.setUpdateDate(new Date());
					videoColumn.setDelFlag("1");
					videoColumnService.updateByPrimaryKeySelective(videoColumn);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				code = "9999";
				msg = "操作失败！";
			}
			map.put("code", code);
			map.put("msg", msg);
			return map;
		}

		
		//修改视频类目排序值
		@ResponseBody
		@RequestMapping(value = "/video/updateColumnArtNo.shtml")
		public Map<String, Object> updateColumnArtNo(HttpServletRequest request, Integer id, Integer seqNo) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			try {
				VideoColumn videoColumn=videoColumnService.selectByPrimaryKey(Integer.valueOf(id));
				videoColumn.setSeqNo(seqNo);
				videoColumn.setUpdateBy(staffID);
				videoColumn.setUpdateDate(new Date());
				videoColumnService.updateByPrimaryKey(videoColumn);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return resMap;
		}
		
		
		//视频审核
		@RequestMapping("/video/examineVideo.shtml")
		public ModelAndView examineVideo(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/video/examineVideo");
			
			String staffID = this.getSessionStaffBean(request).getStaffID();
			m.addObject("staffID", staffID);
			List<Integer> lisIntegers=new ArrayList<Integer>();
	    	SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
	    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
	    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
	    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysStaffProductTypeService.selectByCustomExample(sysstaffProductTypeex);
	    	for (SysStaffProductTypeCustom sysStaffProductTypeCustom : sysStaffProductTypeList) {
	    		lisIntegers.add(sysStaffProductTypeCustom.getProductTypeId());
			}
	    	ProductTypeExample productTypeExample=new ProductTypeExample();
	    	productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(lisIntegers);
	    	List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
	    	m.addObject("productTypes", JSONArray.fromObject(productTypes));

			m.addObject("type", request.getParameter("type"));
			
			PlatformContactExample platformContactExample=new PlatformContactExample();
			PlatformContactExample.Criteria plCriteria=platformContactExample.createCriteria();
			plCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("7").andStaffIdEqualTo(Integer.valueOf(staffID));
			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExample);
			if (platformContacts.size()>0) {
			    m.addObject("fwStaffId", true);
			 }

			return m;
		}
		
	
		       //视频审核列表
				@ResponseBody
				@RequestMapping("/video/examineVideoList.shtml")
				public Map<String, Object> examineVideoList(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<VideoCustom> dataList = null;
					Integer totalCount = 0;
					try {
						VideoCustomExample videCustomExample=new VideoCustomExample();
						VideoCustomExample.VideoCustomCriteria videoCustomCriteria=videCustomExample.createCriteria();
						videoCustomCriteria.andDelFlagEqualTo("0");
						videCustomExample.setOrderByClause("v.audit_time desc");
						
						String staffID = this.getSessionStaffBean(request).getStaffID();
						SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
				    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
				    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
				    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysStaffProductTypeService.selectByCustomExample(sysstaffProductTypeex);
				    	String sysStaffProductType="";
				    	for (SysStaffProductTypeCustom sysStaffProductTypeCustom : sysStaffProductTypeList) {
							  if ("".equals(sysStaffProductType)) {
								sysStaffProductType+=sysStaffProductTypeCustom.getProductTypeId().toString();
							}else {
								sysStaffProductType+=","+sysStaffProductTypeCustom.getProductTypeId().toString();
							}
						}
				    	if (!StringUtil.isEmpty(sysStaffProductType)) {
				    		videoCustomCriteria.andProductTypeIdIn(sysStaffProductType);
						}
						
						if (!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
							String auditStatus=request.getParameter("auditStatus");
							videoCustomCriteria.andAuditStatusEqualTo(auditStatus);
						}else if(!StringUtil.isEmpty(request.getParameter("type"))){
				    		if("2".equals(request.getParameter("type"))){
								videoCustomCriteria.andAuditStatusIn(new ArrayList<String>(Arrays.asList("3","5","6")));
							}else if("1".equals(request.getParameter("type"))){
								videoCustomCriteria.andAuditStatusIn(new ArrayList<String>(Arrays.asList("2","3","4")));
							}
						}
						if (!StringUtil.isEmpty(request.getParameter("title"))) {
							String title=request.getParameter("title");
							videoCustomCriteria.andTitleLike("%"+title+"%");
						}
						if (!StringUtil.isEmpty(request.getParameter("vedioId"))) {
							videoCustomCriteria.andIdEqualTo(Integer.parseInt(request.getParameter("vedioId")));
						}
						if (!StringUtil.isEmpty(request.getParameter("create_begin")) && !StringUtil.isEmpty(request.getParameter("create_end"))) {
							videoCustomCriteria.andCreateDateBetween(sdf.parse(request.getParameter("create_begin")+" 00:00:00"), sdf.parse(request.getParameter("create_end")+" 23:59:59"));				
						}
						if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
							videoCustomCriteria.andmchtProductTypeIdEqualTo(request.getParameter("productTypeId"));
						}
						if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
							videoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
							String companyName=request.getParameter("companyName");
							videoCustomCriteria.andcompanyNameLike("%"+companyName+"%");
						}
						if (!StringUtil.isEmpty(request.getParameter("productCode"))) {
							videoCustomCriteria.andProductCodeCodeEqualTo(request.getParameter("productCode"));
						}
						videCustomExample.setLimitStart(page.getLimitStart());
						videCustomExample.setLimitSize(page.getLimitSize());
						totalCount = videoService.countByVideoCustomExample(videCustomExample);
						dataList = videoService.selectByVideoCustomExample(videCustomExample);
						List<Integer> videoidList = new ArrayList<Integer>();
						for (VideoCustom videoCustom : dataList) {
							videoidList.add(videoCustom.getId());
						}
						if (videoidList!=null && videoidList.size()>0) {
							//查询视频附件
							VideoAuthorizedAccessoriesExample videoAuthorizedAccessoriesExample = new VideoAuthorizedAccessoriesExample();
							videoAuthorizedAccessoriesExample.createCriteria().andDelFlagEqualTo("0").andVideoIdIn(videoidList);
							List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList = videoAuthorizedAccessoriesService.selectByExample(videoAuthorizedAccessoriesExample);

							for (VideoCustom videoCustom : dataList) {
								//根据videoId判断是否有该视频的附件
								List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList1 = new ArrayList<VideoAuthorizedAccessories>();
								for (VideoAuthorizedAccessories videoAuthorizedAccessories : videoAuthorizedAccessoriesList) {
									if(videoCustom.getId().equals(videoAuthorizedAccessories.getVideoId())){
										videoAuthorizedAccessoriesList1.add(videoAuthorizedAccessories);
									}
								}
								videoCustom.setVideoAuthorizedAccessoriesList(videoAuthorizedAccessoriesList1);
							}

							VideoProductCustomExample videoProductCustomExample=new VideoProductCustomExample();
							videoProductCustomExample.createCriteria().andDelFlagEqualTo("0").andVideoIdIn(videoidList);
							List<VideoProductCustom> videoProductCustoms=videoProductService.selectByVideoProductCustomExample(videoProductCustomExample);


							for (VideoCustom videoCustom : dataList) {
								  Integer videoid=videoCustom.getId();
								  List<VideoProductCustom> videoProductCustomsLsit = new ArrayList<VideoProductCustom>();
								  for (VideoProductCustom videoProductCustom : videoProductCustoms) {
									   Integer VideoId=videoProductCustom.getVideoId();
									   if (videoid.equals(VideoId)) {
										   videoProductCustomsLsit.add(videoProductCustom);
									}
								}
								  videoCustom.setVideoProductCustoms(videoProductCustomsLsit);
							}
							
							
							
		
						}
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
	
				
						
				//视频审核通过
				@RequestMapping(value = "/video/changeExamineVieoStatus.shtml")
				@ResponseBody
				public Map<String, Object> changeExamineVieoStatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					try {
						String id = request.getParameter("id");
						String auditstatus=request.getParameter("auditstatus");
						Video video=videoService.selectByPrimaryKey(Integer.valueOf(id));
						if ("3".equals(auditstatus)){
                            video.setAuditStatus("3");
							video.setAuditTime(new Date());
                        }else if("5".equals(auditstatus)) {
							video.setAuditStatus("5");
							video.setAuditTime(new Date());
							video.setStatus("1");
							String scene = StringUtils.buildMsg(PageConst.VIDEO_DETAIL_PAGE_PARAMS_TEMPLATE, video.getId());
							video.setVideoXcxCode(weixinService.createAppletUnlimitQrcode(PageConst.VIDEO_DETAIL_PAGE, scene));
						}
						video.setUpdateDate(new Date());
						video.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						videoService.updateByPrimaryKeySelective(video);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}

				
				
				//获取视频播放
				@RequestMapping("/video/videoUrl.shtml")
				public ModelAndView videoUrl(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/video/videoUrl");
					String videoid=request.getParameter("videoid");
					Video video=videoService.selectByPrimaryKey(Integer.valueOf(videoid));
					m.addObject("video", video);
					return m;
				}
				
				
				//视频审核驳回
				@RequestMapping("/video/rejectExamineVieoStatus.shtml")
				public ModelAndView rejectExamineVieoStatus(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/video/rejectExamineVieo");
					String videoid=request.getParameter("videoid");
					m.addObject("videoid", videoid);
					String auditStatus=request.getParameter("auditStatus");
					m.addObject("auditStatus", auditStatus);
					return m;
				}
				
				//保存视频审核驳回
				@ResponseBody
				@RequestMapping(value = "/video/addAuditRemark.shtml")
				public ModelAndView addAuditRemark(HttpServletRequest request, String id, String auditRemark, String auditStatus) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					try {
						Video video=videoService.selectByPrimaryKey(Integer.valueOf(id));
						video.setUpdateBy(staffID);
						video.setUpdateDate(new Date());
						video.setAuditStatus(auditStatus);
						video.setAuditRemark(auditRemark);
						//video.setAuditTime(new Date());
						videoService.updateByPrimaryKeySelective(video);
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap);
				}
				
				//查看视频评论
				@RequestMapping(value = "/video/videoComment.shtml")
				public ModelAndView videoComment(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap) {
					String rtPage = "/video/viewComment";
					Map<String, Object> resMap = new HashMap<String, Object>();
					String videoId = paramMap.get("videoId").toString();
					resMap.put("videoId", videoId);
					return new ModelAndView(rtPage, resMap);
				}
				
				
				//查看视频评论数据
				@RequestMapping(value = "/video/videoCommentdata.shtml")
				@ResponseBody
				public Map<String, Object> videoCommentdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					int videoId = Integer.valueOf(request.getParameter("videoId"));
					int totalCount = 0;
					List<VideoCommentCustom> daCommentCustoms=null;
					try {
						VideoCommentCustomExample videoCommentCustomExample=new VideoCommentCustomExample();
						videoCommentCustomExample.createCriteria().andDelFlagEqualTo("0").andVideoIdEqualTo(videoId);
						
						videoCommentCustomExample.setLimitStart(page.getLimitStart());
						videoCommentCustomExample.setLimitSize(page.getLimitSize());
						totalCount=videoCommentService.countByVideoCommentCustomExample(videoCommentCustomExample);
						daCommentCustoms=videoCommentService.selectByVideoCommentCustomExample(videoCommentCustomExample);
						resMap.put("Rows", daCommentCustoms);
						resMap.put("Total", totalCount);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return resMap;
				}
				
				
				
				//获取视频缩略图
				@ResponseBody
				@RequestMapping("/video/videothumbnailsList.shtml")
				public List<Video> videotHumbnailsList(HttpServletRequest request){
					List<Video> videoThumbnailsList = new ArrayList<Video>();
					if(!StringUtil.isEmpty(request.getParameter("videoId"))){
						VideoExample videoExample=new VideoExample();
						videoExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(request.getParameter("videoId")));
						videoExample.setLimitStart(0);
						videoExample.setLimitSize(6);
						videoThumbnailsList = videoService.selectByExample(videoExample);
					}
					return videoThumbnailsList;
				}
				

				//获取视频封面图
				@RequestMapping(value="/video/videoCoverList.shtml")
				@ResponseBody
				public List<Video> videoCoverList(HttpServletRequest request){
					List<Video> videos=new ArrayList<Video>();
					if(StringUtil.isEmpty(request.getParameter("videoID"))){
						return videos;
					}
					VideoExample videoExample=new VideoExample();
					videoExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(request.getParameter("videoID")));
					videoExample.setOrderByClause("seq_no asc");
					videos=videoService.selectByExample(videoExample);
					return videos;
				}
				
	
				//视频管理
				@RequestMapping("/video/administrationVideo.shtml")
				public ModelAndView examineVideoList(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/video/administrationVideo");

					String staffID = this.getSessionStaffBean(request).getStaffID();
					List<Integer> lisIntegers=new ArrayList<Integer>();
			    	SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
			    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
			    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
			    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysStaffProductTypeService.selectByCustomExample(sysstaffProductTypeex);
			    	for (SysStaffProductTypeCustom sysStaffProductTypeCustom : sysStaffProductTypeList) {
			    		lisIntegers.add(sysStaffProductTypeCustom.getProductTypeId());
					}
			    	ProductTypeExample productTypeExample=new ProductTypeExample();
			    	productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(lisIntegers);
			    	List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
			    	m.addObject("productTypes", JSONArray.fromObject(productTypes));

					return m;
				}
				

				//视频管理列表
				@ResponseBody
				@RequestMapping("/video/administrationVideoList.shtml")
				public Map<String, Object> administrationVideoList(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<VideoCustom> dataList = null;
					Integer totalCount = 0;
					try {
						VideoCustomExample videCustomExample=new VideoCustomExample();
						VideoCustomExample.VideoCustomCriteria videoCustomCriteria=videCustomExample.createCriteria();
						videoCustomCriteria.andDelFlagEqualTo("0").andAuditStatusEqualTo("5");
						videCustomExample.setOrderByClause("IFNULL(v.seq_no, 99999999999) asc,v.audit_time desc");
						
						String staffID = this.getSessionStaffBean(request).getStaffID();
						SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();//当前账号负责类目
				    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
				    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
				    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysStaffProductTypeService.selectByCustomExample(sysstaffProductTypeex);
				    	String sysStaffProductType="";
				    	for (SysStaffProductTypeCustom sysStaffProductTypeCustom : sysStaffProductTypeList) {
							  if ("".equals(sysStaffProductType)) {
								sysStaffProductType+=sysStaffProductTypeCustom.getProductTypeId().toString();
							}else {
								sysStaffProductType+=","+sysStaffProductTypeCustom.getProductTypeId().toString();
							}
						}
				    	if (!StringUtil.isEmpty(sysStaffProductType)) {
				    		videoCustomCriteria.andProductTypeIdIn(sysStaffProductType);
						}
				    	
						if (!StringUtil.isEmpty(request.getParameter("status"))) {
							String status=request.getParameter("status");
							if (status.equals("0")) {
								videoCustomCriteria.andStatusEqualTo("0");
							}else if (status.equals("1")) {
								videoCustomCriteria.andStatusEqualTo("1");
							}
						}
						if (!StringUtil.isEmpty(request.getParameter("title"))) {
							String title=request.getParameter("title");
							videoCustomCriteria.andTitleLike("%"+title+"%");
						}
						
						if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
							videoCustomCriteria.andmchtProductTypeIdEqualTo(request.getParameter("productTypeId"));
						}
						if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
							videoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("companyName"))) {
							String companyName=request.getParameter("companyName");
							videoCustomCriteria.andcompanyNameLike("%"+companyName+"%");
						}
						if (!StringUtil.isEmpty(request.getParameter("productCode"))) {
							videoCustomCriteria.andProductCodeCodeEqualTo(request.getParameter("productCode"));
						}
						//是否推荐
						if (!StringUtil.isEmpty(request.getParameter("isRecommend"))) {
							if(org.apache.commons.lang.StringUtils.equals(request.getParameter("isRecommend"),"0")){
								videoCustomCriteria.andIsrecommendIsNull();
							}else{
								videoCustomCriteria.andIsrecommendEqualTo(request.getParameter("isRecommend"));
							}
						}
						//视频ID
						if (!StringUtil.isEmpty(request.getParameter("videoId"))) {
							videoCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("videoId")));
						}
						//视频来源
						if (!StringUtil.isEmpty(request.getParameter("videoSource"))) {
							videoCustomCriteria.andVideoSourceEqualTo(request.getParameter("videoSource"));
						}
						videCustomExample.setLimitStart(page.getLimitStart());
						videCustomExample.setLimitSize(page.getLimitSize());
						totalCount = videoService.countByVideoCustomExample(videCustomExample);
						dataList = videoService.selectByVideoCustomExample(videCustomExample);

						List<Integer> videoidList = new ArrayList<Integer>();
						for (VideoCustom videoCustom : dataList) {
							videoidList.add(videoCustom.getId());
						}
						if (videoidList!=null && videoidList.size()>0) {
							//查询视频附件
							VideoAuthorizedAccessoriesExample videoAuthorizedAccessoriesExample = new VideoAuthorizedAccessoriesExample();
							videoAuthorizedAccessoriesExample.createCriteria().andDelFlagEqualTo("0").andVideoIdIn(videoidList);
							List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList = videoAuthorizedAccessoriesService.selectByExample(videoAuthorizedAccessoriesExample);

							for (VideoCustom videoCustom : dataList) {
								//根据videoId判断是否有该视频的附件
								List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList1 = new ArrayList<VideoAuthorizedAccessories>();
								for (VideoAuthorizedAccessories videoAuthorizedAccessories : videoAuthorizedAccessoriesList) {
									if(videoCustom.getId().equals(videoAuthorizedAccessories.getVideoId())){
										videoAuthorizedAccessoriesList1.add(videoAuthorizedAccessories);
									}
								}
								videoCustom.setVideoAuthorizedAccessoriesList(videoAuthorizedAccessoriesList1);
							}

							VideoProductCustomExample videoProductCustomExample=new VideoProductCustomExample();
							videoProductCustomExample.createCriteria().andDelFlagEqualTo("0").andVideoIdIn(videoidList);
							List<VideoProductCustom> videoProductCustoms=videoProductService.selectByVideoProductCustomExample(videoProductCustomExample);

							for (VideoCustom videoCustom : dataList) {
								  Integer videoid=videoCustom.getId();
								  List<VideoProductCustom> videoProductCustomsLsit = new ArrayList<VideoProductCustom>();
								  for (VideoProductCustom videoProductCustom : videoProductCustoms) {
									   Integer VideoId=videoProductCustom.getVideoId();
									   if (videoid.equals(VideoId)) {
										   videoProductCustomsLsit.add(videoProductCustom);
									}
								}
								  videoCustom.setVideoProductCustoms(videoProductCustomsLsit);
							}
							
						}
					 
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
	
				//修改视频管理排序值
				@ResponseBody
				@RequestMapping(value = "/video/updatevideoSeqNo.shtml")
				public Map<String, Object> updatevideoSeqNo(HttpServletRequest request, Integer id, Integer seqNo) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					try {
						Video video=videoService.selectByPrimaryKey(id);
						if (video.getIsrecommend()==null) {
							resMap.put(this.JSON_RESULT_CODE, "9999");
							resMap.put(this.JSON_RESULT_MESSAGE, "已推荐到精选的才能排序!");
							return resMap;
						}else {
							video.setUpdateBy(staffID);
							video.setUpdateDate(new Date());
							video.setSeqNo(seqNo);
							videoService.updateByPrimaryKeySelective(video);
						}
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return resMap;
				}

				//清空视频管理排序值
				@ResponseBody
				@RequestMapping(value = "/video/delVideoSeqNo.shtml")
				public Map<String, Object> delVideoSeqNo(HttpServletRequest request, Integer id) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						Video video=videoService.selectByPrimaryKey(id);
						video.setUpdateBy(staffID);
						video.setUpdateDate(new Date());
						video.setSeqNo(null);
						videoService.updateByPrimaryKey(video);
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return resMap;
				}
				
	
				//视频管理（是否推荐到精选）
				@ResponseBody
				@RequestMapping("/video/videoisRecommend.shtml")
				public Map<String, Object> videoisRecommend(HttpServletRequest request, String ids) {
					Map<String, Object> map = new HashMap<String, Object>(); 
					String code = "200";
					String msg = "操作成功！";
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						if(!StringUtil.isEmpty(ids)) {
							//批量操作
							List<Integer> idList = new ArrayList<>();
							for (String id:ids.split(",")) {
								idList.add(Integer.valueOf(id));
							}
							//批量查询
							VideoCustomExample videoExample = new VideoCustomExample();
							VideoCustomExample.VideoCustomCriteria criteria = videoExample.createCriteria();
							criteria.andIdIn(idList).andDelFlagEqualTo("0").andStatusEqualTo("1");
							criteria.andIsrecommendCustomEqualTo();
							List<Video> videoList =videoService.selectByExample(videoExample);
							for (Video video:videoList) {
								video.setUpdateBy(staffID);
								video.setUpdateDate(new Date());
								video.setIsrecommend("1");
								videoService.updateByPrimaryKeySelective(video);
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						code = "9999";
						msg = "操作失败！";
					}
					map.put("code", code);
					map.put("msg", msg);
					return map;
				}

	//视频管理（是否取消推荐到精选）
	@ResponseBody
	@RequestMapping("/video/videoIsNotRecommend.shtml")
	public Map<String, Object> videoIsNotRecommend(HttpServletRequest request, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(!StringUtil.isEmpty(ids)) {
				//批量操作
				List<Integer> idList = new ArrayList<>();
				for (String id:ids.split(",")) {
					idList.add(Integer.valueOf(id));
				}
				//批量查询
				VideoExample videoExample = new VideoExample();
				videoExample.createCriteria().andIdIn(idList).andDelFlagEqualTo("0").andStatusEqualTo("1").andIsrecommendEqualTo("1");
				List<Video> videoList =videoService.selectByExample(videoExample);
				for (Video video:videoList) {
					video.setUpdateBy(staffID);
					video.setUpdateDate(new Date());
					video.setIsrecommend("0");
					videoService.updateByPrimaryKeySelective(video);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

				//视频管理修改上下架
				@RequestMapping(value = "video/videoStatus.shtml")
				@ResponseBody
				public Map<String, Object> videoStatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					try {
						String id = request.getParameter("id");
						String status=request.getParameter("status");
						Video video=videoService.selectByPrimaryKey(Integer.valueOf(id));
						video.setUpdateBy(staffID);
						video.setUpdateDate(new Date());
						video.setStatus(status);
						videoService.updateByPrimaryKeySelective(video);
						
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}
				

				//获取视频管理权重值
				@RequestMapping("/video/videoWeicht.shtml")
				public ModelAndView videoWeicht(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/video/weichtVideo");
					String videoid=request.getParameter("videoid");
					VideoCustomExample videoCustomExample=new VideoCustomExample();
					videoCustomExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(videoid));
					List<VideoCustom> videoCustoms=videoService.selectByVideoCustomExample(videoCustomExample);
					m.addObject("videoCustoms", videoCustoms);
			   
					return m;
				}
				
		
				//修改视频管理权重值
				@ResponseBody
				@RequestMapping(value = "/video/updatemanualWeichtVideo.shtml")
				public Map<String, Object> updateManualWeicht(HttpServletRequest request, Integer id, Integer manualWeicht) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					try {
						    Video video=videoService.selectByPrimaryKey(id);
							video.setUpdateBy(staffID);
							video.setUpdateDate(new Date());
							video.setManualWeicht(manualWeicht);
							videoService.updateByPrimaryKeySelective(video);
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return resMap;
				}
				
	
				//视频管理批量下架
				@ResponseBody
				@RequestMapping("/video/videoStsus.shtml")
				public Map<String, Object> videoStsus(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>(); 
					String code = "200";
					String msg = "操作成功！";
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						String id = request.getParameter("id");
						String[] strs = id.split(",");
						List<Integer> listVideoID=new ArrayList<Integer>();
						for (String videoID : strs) {
							listVideoID.add(Integer.valueOf(videoID));
						}
						VideoCustomExample videoCustomExample=new VideoCustomExample();
						videoCustomExample.createCriteria().andDelFlagEqualTo("0").andIdIn(listVideoID);
								
						Video video=new Video();
						video.setUpdateBy(staffID);
						video.setUpdateDate(new Date());
						video.setStatus("0");
						videoService.updateByExampleSelective(video, videoCustomExample);
			
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						code = "9999";
						msg = "操作失败！";
					}
					map.put("code", code);
					map.put("msg", msg);
					return map;
				}

				//视频举报管理
				@RequestMapping("/video/reportVideo.shtml")
				public ModelAndView reportVideo(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/video/reportVideo");
					
					String staffID = this.getSessionStaffBean(request).getStaffID();
					List<Integer> lisIntegers=new ArrayList<Integer>();
			    	SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
			    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
			    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
			    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysStaffProductTypeService.selectByCustomExample(sysstaffProductTypeex);
			    	for (SysStaffProductTypeCustom sysStaffProductTypeCustom : sysStaffProductTypeList) {
			    		lisIntegers.add(sysStaffProductTypeCustom.getProductTypeId());
					}
			    	ProductTypeExample productTypeExample=new ProductTypeExample();
			    	productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(lisIntegers);
			    	List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
			    	m.addObject("productTypes", JSONArray.fromObject(productTypes));
			   
					return m;
				}
				
				
				//视频举报管理列表
				@ResponseBody
				@RequestMapping("/video/reportVideoList.shtml")
				public Map<String, Object> reportVideoList(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<VideoTipOffCustom> dataList = null;
					Integer totalCount = 0;
					try {
						VideoTipOffCustomExample videoTipOffCustomExample=new VideoTipOffCustomExample();
						VideoTipOffCustomExample.VideoTipOffCustomCriteria videoTipOffCustomCriteria=videoTipOffCustomExample.createCriteria();
						videoTipOffCustomCriteria.andDelFlagEqualTo("0");
						videoTipOffCustomExample.setOrderByClause("vt.create_date desc");
						
						String staffID = this.getSessionStaffBean(request).getStaffID();
						SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
				    	SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
				    	sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
				    	List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysStaffProductTypeService.selectByCustomExample(sysstaffProductTypeex);
				    	String sysStaffProductType="";
				    	for (SysStaffProductTypeCustom sysStaffProductTypeCustom : sysStaffProductTypeList) {
							  if ("".equals(sysStaffProductType)) {
								sysStaffProductType+=sysStaffProductTypeCustom.getProductTypeId().toString();
							}else {
								sysStaffProductType+=","+sysStaffProductTypeCustom.getProductTypeId().toString();
							}
						}
				    	if (!StringUtil.isEmpty(sysStaffProductType)) {
				    		videoTipOffCustomCriteria.andProductTypeIdIn(sysStaffProductType);
						}
				    	
						if (!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
							videoTipOffCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
						}
						if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
							videoTipOffCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
							videoTipOffCustomCriteria.andmchtProductTypeIdEqualTo(request.getParameter("productTypeId"));
						}
						if (!StringUtil.isEmpty(request.getParameter("productId"))) {
							videoTipOffCustomCriteria.andProductCodeCodeEqualTo(request.getParameter("productId"));
						}
						if (!StringUtil.isEmpty(request.getParameter("title"))) {
							videoTipOffCustomCriteria.andVideoTitleEqualTo(request.getParameter("title"));
						}
						if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
							videoTipOffCustomCriteria.andMchtNameEqualTo(request.getParameter("mchtName"));
						}
						if (!StringUtil.isEmpty(request.getParameter("videoStatus"))) {
							videoTipOffCustomCriteria.andVideoStatusEqualTo(request.getParameter("videoStatus"));
						}
						videoTipOffCustomExample.setLimitStart(page.getLimitStart());
						videoTipOffCustomExample.setLimitSize(page.getLimitSize());
						totalCount =videoTipOffService.countByVideoTipOffCustomExample(videoTipOffCustomExample);
						dataList = videoTipOffService.selectByVideoTipOffCustomExample(videoTipOffCustomExample);

						List<Integer> videoIdList = new ArrayList<>();
						for (VideoTipOffCustom videoTipOffCustom:dataList) {
							videoIdList.add(videoTipOffCustom.getVideoId());
						}
						//查询视频附件
						VideoAuthorizedAccessoriesExample videoAuthorizedAccessoriesExample = new VideoAuthorizedAccessoriesExample();
						videoAuthorizedAccessoriesExample.createCriteria().andDelFlagEqualTo("0").andVideoIdIn(videoIdList);
						List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList = videoAuthorizedAccessoriesService.selectByExample(videoAuthorizedAccessoriesExample);

						for (VideoTipOffCustom videoTipOffCustom : dataList) {
							//根据videoId判断是否有该视频的附件
							List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList1 = new ArrayList<VideoAuthorizedAccessories>();
							for (VideoAuthorizedAccessories videoAuthorizedAccessories : videoAuthorizedAccessoriesList) {
								if(videoTipOffCustom.getVideoId() == videoAuthorizedAccessories.getVideoId()){
									videoAuthorizedAccessoriesList1.add(videoAuthorizedAccessories);
								}
							}
							videoTipOffCustom.setVideoAuthorizedAccessoriesList(videoAuthorizedAccessoriesList1);
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
				
				//获取视频凭证
				@ResponseBody
				@RequestMapping("/video/reportVideoPicsList.shtml")
				public List<VideoTipOff> reportVideoPicsList(HttpServletRequest request){
					List<VideoTipOff> videoTipOffpicsList = new ArrayList<VideoTipOff>();
					if(!StringUtil.isEmpty(request.getParameter("id"))){
						VideoTipOffExample videoTipOffExample=new VideoTipOffExample();
						videoTipOffExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(request.getParameter("id")));
						videoTipOffExample.setLimitStart(0);
						videoTipOffExample.setLimitSize(10);
						videoTipOffpicsList=videoTipOffService.selectByExample(videoTipOffExample);
					}
					return videoTipOffpicsList;
				}
		

				//视频举报通过或驳回
				@RequestMapping(value = "/video/reportVieoAuditStatus.shtml")
				@ResponseBody
				public Map<String, Object> reportVieoAuditStatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					try {
						String staffID = this.getSessionStaffBean(request).getStaffID();
						String id = request.getParameter("id");
						String auditstatus=request.getParameter("auditstatus");
						VideoTipOff videoTipOff=videoTipOffService.selectByPrimaryKey(Integer.valueOf(id));
						videoTipOff.setUpdateBy(Integer.valueOf(staffID));
						videoTipOff.setUpdateDate(new Date());
						if (auditstatus.equals("2")) {
							videoTipOff.setAuditStatus("2");
						}else if (auditstatus.equals("3")) {
							videoTipOff.setAuditStatus("3");
						}
						videoTipOffService.updateByPrimaryKeySelective(videoTipOff);
						
						
						if (videoTipOff.getAuditStatus().equals("2")) {
							VideoTipOffCustom videoTipOffCustom=videoTipOffService.selectByVideoTipOffCustomPrimaryKey(Integer.valueOf(id));
							
							Video video=videoService.selectByPrimaryKey(videoTipOffCustom.getVideoId());//举报审核通过时视频下架
							video.setStatus("0");
							video.setUpdateBy(Integer.valueOf(staffID));
							video.setUpdateDate(new Date());
							videoService.updateByPrimaryKey(video);
							
							// 发送站内信
							String title = "视频内容违规";
							String content = "您的{"+videoTipOffCustom.getVideoTitle()+"}内容违规，已被平台下架。";
							platformMsgService.save(videoTipOffCustom.getMchtId(), title, content, "TZ");	
							
							//推送系统消息
							String content1 = "经平台核实您举报视频情况属实已将该视频下架，谢谢您的支持。";
							
							SysAppMessage sysAppMessage=new SysAppMessage();
							sysAppMessage.setCreateDate(new Date());
							sysAppMessage.setCreateBy(Integer.valueOf(staffID));
							sysAppMessage.setType(SysAppMessageCustom.TYPE_SYS);
							sysAppMessage.setTitle(SysAppMessageCustom.TITLE_VIDEO);
							sysAppMessage.setContent(content1);
							sysAppMessage.setPushFlag("0");
							sysAppMessage.setLinkId(videoTipOffCustom.getId());
							sysAppMessage.setLinkType("12");//推送视频消息业务
							sysAppMessage.setDelFlag("0");
							sysAppMessageService.insertSelective(sysAppMessage);
							
							SysAppMessageMember sysAppMessageMember=new SysAppMessageMember();
							sysAppMessageMember.setCreateDate(new Date());
							sysAppMessageMember.setCreateBy(Integer.valueOf(staffID));
							sysAppMessageMember.setAppMessageId(sysAppMessage.getId());
							sysAppMessageMember.setMemberId(videoTipOffCustom.getMemberId());
							sysAppMessageMember.setDelFlag("0");
							sysAppMessageMemberService.insert(sysAppMessageMember);
							
						}
						
						
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}
				
				//视频举报批量审核
				@RequestMapping(value = "/video/videoTipOffAuditstatus.shtml")
				public ModelAndView videoTipOffAuditstatus(Model model, HttpServletRequest request) {
					String rtPage = "/video/videoTipoffAuditStatus";
					Map<String, Object> resMap = new HashMap<String, Object>();				
					String ids=request.getParameter("videoid");
					resMap.put("ids", ids);		
					return new ModelAndView(rtPage,resMap);
				}
				
		
				//视频举报批量审核
				@RequestMapping(value = "/video/auditstatuSubmit.shtml")
				public ModelAndView auditstatuSubmit(HttpServletRequest request) {
					String rtPage = "/success/success";
					Map<String, Object> resMap = new HashMap<String, Object>(); 
					String code = null;
					String msg =null;
					try {
						Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
						
						List <String> idsStr=Arrays.asList(request.getParameter("ids").split(","));
						String auditstatus=request.getParameter("auditStatus");
						List<Integer> ids =new ArrayList<Integer>(idsStr.size());
						
						for(int i=0,j=idsStr.size();i<j;i++){
							ids.add(Integer.parseInt(idsStr.get(i)));   
				        } 
						
						VideoTipOffExample videoTipOffExample=new VideoTipOffExample();
						videoTipOffExample.createCriteria().andDelFlagEqualTo("0").andIdIn(ids);
						
						VideoTipOff videoTipOf=new VideoTipOff();
						videoTipOf.setUpdateBy(staffId);
						videoTipOf.setUpdateDate(new Date());
						if (auditstatus.equals("2")) {
							videoTipOf.setAuditStatus("2");
						}else if (auditstatus.equals("3")) {
							videoTipOf.setAuditStatus("3");
						}
					    videoTipOffService.updateByExampleSelective(videoTipOf, videoTipOffExample);
					    
					    
					    VideoTipOffCustomExample videoTipOffCustomExample=new VideoTipOffCustomExample();
					    videoTipOffCustomExample.createCriteria().andDelFlagEqualTo("0").andIdIn(ids);
					    List<VideoTipOffCustom> videoTipOffCustoms=videoTipOffService.selectByVideoTipOffCustomExample(videoTipOffCustomExample);
					    if (videoTipOffCustoms.size()>0) {
							for (VideoTipOffCustom videoTipOffCustom : videoTipOffCustoms) {
								  if (videoTipOffCustom.getAuditStatus().equals("2")) {
									  
									    Video video=videoService.selectByPrimaryKey(videoTipOffCustom.getVideoId());//举报审核通过时视频下架
										video.setStatus("0");
										video.setUpdateBy(staffId);
										video.setUpdateDate(new Date());
										videoService.updateByPrimaryKey(video);
									  
									  // 发送站内信
									  String title = "视频内容违规";
									  String content = "您的{"+videoTipOffCustom.getVideoTitle()+"}内容违规，已被平台下架。";
									  platformMsgService.save(videoTipOffCustom.getMchtId(), title, content, "TZ");	
									    
									   //推送系统消息
									    String content1 = "经平台核实您举报视频情况属实已将该视频下架，谢谢您的支持。"; 
									  
									    SysAppMessage sysAppMessage=new SysAppMessage();
										sysAppMessage.setCreateDate(new Date());
										sysAppMessage.setCreateBy(staffId);
										sysAppMessage.setType(SysAppMessageCustom.TYPE_SYS);
										sysAppMessage.setTitle(SysAppMessageCustom.TITLE_VIDEO);
										sysAppMessage.setContent(content1);
										sysAppMessage.setPushFlag("0");
										sysAppMessage.setLinkId(videoTipOffCustom.getId());
										sysAppMessage.setLinkType("12");//推送视频消息业务
										sysAppMessage.setDelFlag("0");
										sysAppMessageService.insertSelective(sysAppMessage);
										
										SysAppMessageMember sysAppMessageMember=new SysAppMessageMember();
										sysAppMessageMember.setCreateDate(new Date());
										sysAppMessageMember.setCreateBy(staffId);
										sysAppMessageMember.setAppMessageId(sysAppMessage.getId());
										sysAppMessageMember.setMemberId(videoTipOffCustom.getMemberId());
										sysAppMessageMember.setDelFlag("0");
										sysAppMessageMemberService.insert(sysAppMessageMember);
										
								}
							}
						}
					    
						
						code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
						msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
					} catch (Exception e) {
						  e.printStackTrace();
						 code = StateCode.JSON_AJAX_ERROR.getStateCode();
						 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
					}
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);

					return new ModelAndView(rtPage, resMap);
				}
				
				
				//视频评论管理
				@RequestMapping("/video/commentVideo.shtml")
				public ModelAndView commentVideo(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/video/commentVideo");
					return m;
				}
				
				
				//视频评论管理列表
				@ResponseBody
				@RequestMapping("/video/commentVideoList.shtml")
				public Map<String, Object> commentVideoList(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<VideoCommentCustom> dataList = null;
					Integer totalCount = 0;
					try {
						VideoCommentCustomExample videoCommentCustomExample=new VideoCommentCustomExample();
						VideoCommentCustomExample.VideoCommentCustomCriteria videoCommentCustomCriteria=videoCommentCustomExample.createCriteria();
						videoCommentCustomCriteria.andDelFlagEqualTo("0");
						videoCommentCustomExample.setOrderByClause("vcm.create_date desc");
						if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
							videoCommentCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("title"))) {
							videoCommentCustomCriteria.andVideoTitleEqualTo(request.getParameter("title"));
						}
						if (!StringUtil.isEmpty(request.getParameter("memberId"))) {
							videoCommentCustomCriteria.andMemberIdEqualTo(Integer.valueOf(request.getParameter("memberId")));
						}
						if (!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
							String auditStatus=request.getParameter("auditStatus");
							if (auditStatus.equals("1")) {
								videoCommentCustomCriteria.andAuditStatusEqualTo("1");
							}else if (auditStatus.equals("2")) {
								videoCommentCustomCriteria.andAuditStatusEqualTo("2");
							}else if (auditStatus.equals("3")) {
								videoCommentCustomCriteria.andAuditStatusEqualTo("3");
							}
						}
						
						videoCommentCustomExample.setLimitStart(page.getLimitStart());
						videoCommentCustomExample.setLimitSize(page.getLimitSize());
						totalCount=videoCommentService.countByVideoCommentCustomExample(videoCommentCustomExample);
						dataList=videoCommentService.selectByVideoCommentCustomExample(videoCommentCustomExample);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}
				
	
				//视频评论批量通过
				@ResponseBody
				@RequestMapping("/video/auditStatus.shtml")
				public Map<String, Object> auditStatus(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>(); 
					String code = "200";
					String msg = "操作成功！";
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						String id = request.getParameter("id");
						String[] strs = id.split(",");
						List<Integer> videoCommentid=new ArrayList<Integer>();
						for (String videocommentid : strs) {
							videoCommentid.add(Integer.valueOf(videocommentid));
						}
						
						VideoCommentExample videoCommentExample=new VideoCommentExample();
						videoCommentExample.createCriteria().andDelFlagEqualTo("0").andIdIn(videoCommentid);
										
						VideoComment videoComment=new VideoComment();
						videoComment.setUpdateBy(staffID);
						videoComment.setUpdateDate(new Date());
						videoComment.setAuditStatus("2");
						videoCommentService.updateByExampleSelective(videoComment, videoCommentExample);
					
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						code = "9999";
						msg = "操作失败！";
					}
					map.put("code", code);
					map.put("msg", msg);
					return map;
				}
				
				//视频评论通过或驳回
				@RequestMapping(value = "/video/changeCommentAuditstatus.shtml")
				@ResponseBody
				public Map<String, Object> changeCommentAuditstatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						String id = request.getParameter("id");
						String auditstatus=request.getParameter("auditstatus");
						VideoComment videoComment=videoCommentService.selectByPrimaryKey(Integer.valueOf(id));
						if (auditstatus.equals("2")) {
							videoComment.setAuditStatus("2");
						}else if (auditstatus.equals("3")) {
							videoComment.setAuditStatus("3");
						}
						videoComment.setUpdateBy(staffID);
						videoComment.setUpdateDate(new Date());
						videoCommentService.updateByPrimaryKeySelective(videoComment);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}
				
				
				//视频评论显示或隐藏
				@RequestMapping(value = "/video/changeCommentStatus.shtml")
				@ResponseBody
				public Map<String, Object> changeCommentStatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						String id = request.getParameter("id");
						String status=request.getParameter("status");
						VideoComment videoComment=videoCommentService.selectByPrimaryKey(Integer.valueOf(id));
						if (status.equals("0")) {//隐藏后评论数减
							videoComment.setStatus("0");
							VideoExtendExample videoExtendExample=new VideoExtendExample();
							videoExtendExample.createCriteria().andDelFlagEqualTo("0").andVideoIdEqualTo(Integer.valueOf(videoComment.getVideoId()));
							List<VideoExtend> videoExtends=videoExtendService.selectByExample(videoExtendExample);
							if (videoExtends.size()>0) {
								BigDecimal commentCount = new BigDecimal(videoExtends.get(0).getCommentCount());
								VideoExtend videoExtend=videoExtendService.selectByPrimaryKey(videoExtends.get(0).getId());
								videoExtend.setUpdateBy(staffID);
								videoExtend.setUpdateDate(new Date());
								videoExtend.setCommentCount(Integer.valueOf(commentCount.subtract(new BigDecimal(1)).toString()));
								videoExtendService.updateByPrimaryKey(videoExtend);
							}
							
							
						}else if (status.equals("1")) {//显示后评论数加
							videoComment.setStatus("1");
							VideoExtendExample videoExtendExample=new VideoExtendExample();
							videoExtendExample.createCriteria().andDelFlagEqualTo("0").andVideoIdEqualTo(Integer.valueOf(videoComment.getVideoId()));
							List<VideoExtend> videoExtends=videoExtendService.selectByExample(videoExtendExample);
							if (videoExtends.size()>0) {
								BigDecimal commentCount = new BigDecimal(videoExtends.get(0).getCommentCount());
								VideoExtend videoExtend=videoExtendService.selectByPrimaryKey(videoExtends.get(0).getId());
								videoExtend.setUpdateBy(staffID);
								videoExtend.setUpdateDate(new Date());
								videoExtend.setCommentCount(Integer.valueOf(commentCount.add(new BigDecimal(1)).toString()));
								videoExtendService.updateByPrimaryKey(videoExtend);
							}
						}
						videoComment.setUpdateBy(staffID);
						videoComment.setUpdateDate(new Date());
						videoCommentService.updateByPrimaryKeySelective(videoComment);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}
				
				//视频评论删除
				@RequestMapping(value = "/video/delCommentStatus.shtml")
				@ResponseBody
				public Map<String, Object> delCommentStatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						String id = request.getParameter("id");
						String delstatus=request.getParameter("delstatus");
						VideoComment videoComment=videoCommentService.selectByPrimaryKey(Integer.valueOf(id));
						if (delstatus.equals("1")) {
							videoComment.setDelFlag("1");
							
							VideoExtendExample videoExtendExample=new VideoExtendExample();
							videoExtendExample.createCriteria().andDelFlagEqualTo("0").andVideoIdEqualTo(Integer.valueOf(videoComment.getVideoId()));
							List<VideoExtend> videoExtends=videoExtendService.selectByExample(videoExtendExample);
							if (videoExtends.size()>0) {
								BigDecimal commentCount = new BigDecimal(videoExtends.get(0).getCommentCount());
								VideoExtend videoExtend=videoExtendService.selectByPrimaryKey(videoExtends.get(0).getId());
								videoExtend.setUpdateBy(staffID);
								videoExtend.setUpdateDate(new Date());
								videoExtend.setCommentCount(Integer.valueOf(commentCount.subtract(new BigDecimal(1)).toString()));
								videoExtendService.updateByPrimaryKey(videoExtend);
							}
						}
						videoComment.setUpdateBy(staffID);
						videoComment.setUpdateDate(new Date());
						videoCommentService.updateByPrimaryKeySelective(videoComment);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}

			/**
			 * 下载附件
			 * @param request
			 * @param response
			 * @throws Exception
			 */
			@RequestMapping(value ="/video/downLoadVideoAuthorizedAccessories.shtml")
			public void downLoadUserCodeExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
                String fileName = request.getParameter("fileName");
                String filePath = request.getParameter("filePath");
                if (StringUtils.isEmpty(filePath) || StringUtils.isEmpty(fileName)){
                    return;
                }
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


	/**
	 * 视频流量统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/video/videoPvStatisticalManager.shtml")
	public ModelAndView videoPvStatisticalManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/video/videoPvStatisticalList");

		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example );
		m.addObject("productTypes", productTypes);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		m.addObject("payDate", sdf.format(DateUtil.getDateAfter(date, -1)));
		m.addObject("nowPayDate", sdf.format(date));

		return m;
	}

	@ResponseBody
	@RequestMapping("/video/videoPvStatisticalList.shtml")
	public Map<String, Object> videoPvStatisticalList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<VideoCustom> dataList = null;
		Integer totalCount = 0;
		try {
			VideoCustomExample videoCustomExample = new VideoCustomExample();
			VideoCustomExample.VideoCustomCriteria videoCustomCriteria = videoCustomExample.createCriteria();
			videoCustomCriteria.andDelFlagEqualTo("0").andAuditStatusEqualTo("5");
			videoCustomExample.setOrderByClause("IFNULL(v.seq_no, 99999999999) asc,v.audit_time desc");

			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				videoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				videoCustomCriteria.andcompanyNameLike(request.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				videoCustomCriteria.andmchtProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if (!StringUtil.isEmpty(request.getParameter("videoTitle"))) {
				String title = request.getParameter("videoTitle");
				videoCustomCriteria.andTitleLike("%"+title+"%");
			}
			videoCustomExample.setLimitStart(page.getLimitStart());
			videoCustomExample.setLimitSize(page.getLimitSize());
			totalCount = videoService.countByVideoCustomExample(videoCustomExample);
			dataList = videoService.selectByVideoCustomExample(videoCustomExample);

			List<Integer> videoIdList = new ArrayList<Integer>();
			for (VideoCustom videoCustom : dataList) {
				videoIdList.add(videoCustom.getId());
			}
			if (videoIdList != null && videoIdList.size() > 0 ) {
				//查询视频附件
				VideoAuthorizedAccessoriesExample videoAuthorizedAccessoriesExample = new VideoAuthorizedAccessoriesExample();
				videoAuthorizedAccessoriesExample.createCriteria().andDelFlagEqualTo("0").andVideoIdIn(videoIdList);
				List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList = videoAuthorizedAccessoriesService.selectByExample(videoAuthorizedAccessoriesExample);

				VideoProductCustomExample videoProductCustomExample = new VideoProductCustomExample();
				videoProductCustomExample.createCriteria().andDelFlagEqualTo("0").andVideoIdIn(videoIdList);
				List<VideoProductCustom> videoProductCustomList = videoProductService.selectByVideoProductCustomExample(videoProductCustomExample);

				Map<String, VideoCustom> videoCustomMap = new HashMap<>();
				for (VideoCustom videoCustom : dataList) {
					//根据videoId判断是否有该视频的附件
					Integer videoId = videoCustom.getId();
					List<VideoAuthorizedAccessories> videoAuthorizedAccessoriesList1 = new ArrayList<VideoAuthorizedAccessories>();
					for (VideoAuthorizedAccessories videoAuthorizedAccessories : videoAuthorizedAccessoriesList) {
						if(videoId.equals(videoAuthorizedAccessories.getVideoId())){
							videoAuthorizedAccessoriesList1.add(videoAuthorizedAccessories);
						}
					}
					videoCustom.setVideoAuthorizedAccessoriesList(videoAuthorizedAccessoriesList1);

					List<VideoProductCustom> videoProductCustomsLsit = new ArrayList<VideoProductCustom>();
					for (VideoProductCustom videoProductCustom : videoProductCustomList) {
						if (videoId.equals(videoProductCustom.getVideoId())) {
							videoProductCustomsLsit.add(videoProductCustom);
						}
					}
					videoCustom.setVideoProductCustoms(videoProductCustomsLsit);

					videoCustom.setTotalVisitorCount(0);
					videoCustom.setTotalVisitorCountTourist(0);
					videoCustom.setTotalPvCount(0);
					videoCustom.setTotalPvCountTourist(0);
					videoCustom.setShoppingCartCount(0);
					videoCustom.setAddProductRate("0.00%");
					videoCustom.setSubProductCount(0);
					videoCustom.setSubmitOrderRate("0.00%");
					videoCustom.setPayProductCount(0);
					videoCustom.setPaymentRate("0.00%");

					videoCustomMap.put(videoCustom.getId().toString(), videoCustom);
				}

				if(!StringUtils.isEmpty(request.getParameter("statisticsType")) ) {
					String nowPayDateBegin = request.getParameter("nowPayDateBegin");
					String nowPayDateEnd = request.getParameter("nowPayDateEnd");
					String payDateBegin = request.getParameter("payDateBegin");
					String payDateEnd = request.getParameter("payDateEnd");
					if("1".equals(request.getParameter("statisticsType")) && !StringUtils.isEmpty(nowPayDateBegin) && !StringUtils.isEmpty(nowPayDateEnd) ) { //实时数据
						Map<String, Object> paramMap = new HashMap();
						paramMap.put("videoIdList", videoIdList);
						paramMap.put("nowPayDateBegin", nowPayDateBegin+" 00:00:00");
						paramMap.put("nowPayDateEnd", nowPayDateEnd+" 23:59:59");
						List<Map<String, Object>> currentTimeStatisticalList = videoProductService.selectCurrentTimeStatistical(paramMap);
						for(Map<String, Object> map : currentTimeStatisticalList ) {
							VideoCustom videoCustom = videoCustomMap.get(map.get("video_id").toString());
							if(videoCustom != null ) {
								String totalCountStr = map.get("total_count").toString();
								String totalVisitorCount = totalCountStr.split(",")[0];
								String totalPvCount = totalCountStr.split(",")[1];
								String shoppingCartCount = map.get("shopping_cart_count").toString();
								String subProductCount = map.get("sub_product_count").toString();
								String payProductCount = map.get("pay_product_count").toString();
								String addProductRate = new BigDecimal(totalVisitorCount).compareTo(new BigDecimal("0"))<=0?"0.00%":new BigDecimal(shoppingCartCount).multiply(new BigDecimal("100")).divide(new BigDecimal(totalVisitorCount),2,BigDecimal.ROUND_DOWN).toString();
								String submitOrderRate = new BigDecimal(shoppingCartCount).compareTo(new BigDecimal("0"))<=0?"0.00%":new BigDecimal(subProductCount).multiply(new BigDecimal("100")).divide(new BigDecimal(shoppingCartCount),2,BigDecimal.ROUND_DOWN).toString();
								String paymentRate = new BigDecimal(subProductCount).compareTo(new BigDecimal("0"))<=0?"0.00%":new BigDecimal(payProductCount).multiply(new BigDecimal("100")).divide(new BigDecimal(subProductCount),2,BigDecimal.ROUND_DOWN).toString();
								videoCustom.setTotalVisitorCount(Integer.parseInt(totalVisitorCount));
								videoCustom.setTotalVisitorCountTourist(Integer.parseInt(map.get("total_visitor_count_tourist").toString()));
								videoCustom.setTotalPvCount(Integer.parseInt(totalPvCount));
								videoCustom.setTotalPvCountTourist(Integer.parseInt(map.get("total_pv_count_tourist").toString()));
								videoCustom.setShoppingCartCount(Integer.parseInt(shoppingCartCount));
								videoCustom.setAddProductRate(addProductRate);
								videoCustom.setSubProductCount(Integer.parseInt(subProductCount));
								videoCustom.setSubmitOrderRate(submitOrderRate);
								videoCustom.setPayProductCount(Integer.parseInt(payProductCount));
								videoCustom.setPaymentRate(paymentRate);
							}
						}
					}else if("2".equals(request.getParameter("statisticsType")) && !StringUtils.isEmpty(payDateBegin) && !StringUtils.isEmpty(payDateEnd) ) { //历史记录
						Map<String, Object> paramMap = new HashMap();
						paramMap.put("videoIdList", videoIdList);
						paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
						paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
						List<Map<String, Object>> historyStatisticalList = videoProductService.selectHistoryStatistical(paramMap);
						for(Map<String, Object> map : historyStatisticalList ) {
							VideoCustom videoCustom = videoCustomMap.get(map.get("video_id").toString());
							if(videoCustom != null ) {
								String totalVisitorCount = map.get("total_visitor_count").toString();
								String totalVisitorCountTourist = map.get("total_visitor_count_tourist").toString();
								String totalPvCount = map.get("total_pv_count").toString();
								String payProductCount = map.get("pay_product_count").toString();
								String shoppingCartCount = map.get("shopping_cart_count").toString();
								String subProductCount = map.get("sub_product_count").toString();
								String addProductRate = new BigDecimal(totalVisitorCount).add(new BigDecimal(totalVisitorCountTourist)).compareTo(new BigDecimal("0"))<=0?"0.00%":new BigDecimal(shoppingCartCount).multiply(new BigDecimal("100")).divide(new BigDecimal(totalVisitorCount).add(new BigDecimal(totalVisitorCountTourist)),2,BigDecimal.ROUND_DOWN).toString();
								String submitOrderRate = new BigDecimal(shoppingCartCount).compareTo(new BigDecimal("0"))<=0?"0.00%":new BigDecimal(subProductCount).multiply(new BigDecimal("100")).divide(new BigDecimal(shoppingCartCount),2,BigDecimal.ROUND_DOWN).toString();
								String paymentRate = new BigDecimal(subProductCount).compareTo(new BigDecimal("0"))<=0?"0.00%":new BigDecimal(payProductCount).multiply(new BigDecimal("100")).divide(new BigDecimal(subProductCount),2,BigDecimal.ROUND_DOWN).toString();
								videoCustom.setTotalVisitorCount(Integer.parseInt(totalVisitorCount));
								videoCustom.setTotalVisitorCountTourist(Integer.parseInt(totalVisitorCountTourist));
								videoCustom.setTotalPvCount(Integer.parseInt(totalPvCount));
								videoCustom.setTotalPvCountTourist(Integer.parseInt(map.get("total_pv_count_tourist").toString()));
								videoCustom.setShoppingCartCount(Integer.parseInt(shoppingCartCount));
								videoCustom.setAddProductRate(addProductRate);
								videoCustom.setSubProductCount(Integer.parseInt(subProductCount));
								videoCustom.setSubmitOrderRate(submitOrderRate);
								videoCustom.setPayProductCount(Integer.parseInt(payProductCount));
								videoCustom.setPaymentRate(paymentRate);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}



}
