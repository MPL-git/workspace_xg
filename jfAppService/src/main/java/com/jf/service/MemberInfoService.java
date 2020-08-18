package com.jf.service;

import com.jf.biz.MemberInfoBiz;
import com.jf.common.AppContext;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.vo.CurrentMember;
import com.jf.dao.AppAccessTokenMapper;
import com.jf.dao.MemberExtendMapper;
import com.jf.dao.MemberInfoCustomMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.entity.*;
import com.jf.vo.SVipInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class MemberInfoService extends BaseService<MemberInfo, MemberInfoExample> {
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private MemberInfoCustomMapper memberInfoCustomMapper;

    @Autowired
    private AppAccessTokenMapper appAccessTokenMapper;

    @Resource
    private AppAccessTokenService appAccessTokenService;

    @Resource
    private AppLoginLogService appLoginLogService;
    @Resource
    private MemberAccountService memberAccountService;
    @Resource
    private MemberGroupService memberGroupService;
    @Resource
    private CutPriceOrderDtlService cutPriceOrderDtlService;
    @Resource
    private OrderDtlService orderDtlService;
    @Resource
    private AppealOrderService appealOrderService;
    @Resource
    private SubDepositOrderService subDepositOrderService;
    @Resource
    private AssistanceDtlService assistanceDtlService;
    @Resource
    private MemberStatusLogService memberStatusLogService;
    @Resource
    private DouyinMemberBindService douyinMemberBindService;
    @Resource
    private MemberExtendService memberExtendService;
    @Resource
    private IntegralDtlService integralDtlService;
    @Resource
    private MemberAttentionService memberAttentionService;
    @Autowired
    private MemberNovaRecordService memberNovaRecordService;
    @Autowired
    private MemberInfoBiz memberInfoBiz;
    @Autowired
    private AreaService areaService;
    @Autowired
    private IntegralTypeService integralTypeService;
    @Autowired
    private GrowthDtlService growthDtlService;
    @Autowired
    private MemberExtendMapper memberExtendMapper;
    @Autowired
    private AppContext appContext;


    @Autowired
    public void setMemberInfoMapper(MemberInfoMapper memberInfoMapper) {
        super.setDao(memberInfoMapper);
        this.memberInfoMapper = memberInfoMapper;
    }

    public AppAccessToken queryAccessToken(Integer memberId, String token) {
        AppAccessTokenExample appAccessTokenExample = new AppAccessTokenExample();
        appAccessTokenExample.createCriteria().andAccessTokenEqualTo(token).andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
        List<AppAccessToken> appAccessTokens = appAccessTokenMapper.selectByExample(appAccessTokenExample);
        if (appAccessTokens != null && appAccessTokens.size() > 0) {
            return appAccessTokens.get(0);
        } else {
            return null;
        }
    }

    public MemberInfo findMemberById(Integer memberId) {

        return memberInfoMapper.selectByPrimaryKey(memberId);
    }

    public void update(MemberInfo memberInfo) {
        memberInfo.setUpdateDate(new Date());
        memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
    }

    public MemberInfo findModelByImei(String imei, String type) {
        if (!StringUtil.isBlank(imei)) {
            MemberInfoExample memberInfoExample = new MemberInfoExample();
            memberInfoExample.createCriteria().andReqImeiEqualTo(imei).andDelFlagEqualTo("0").andTypeEqualTo(type)
                    .andReqImeiNotEqualTo("").andReqImeiNotEqualTo("unknown").andReqImeiNotLike("00000000%");
            memberInfoExample.setOrderByClause("id desc");
            List<MemberInfo> memberInfos = memberInfoMapper.selectByExample(memberInfoExample);
            if (CollectionUtils.isNotEmpty(memberInfos)) {
                return memberInfos.get(0);
            }
        }
        return null;
    }

    public MemberInfo findModelByMobile(String mobile) {
        MemberInfoExample memberInfoExample = new MemberInfoExample();
        memberInfoExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
        List<MemberInfo> memberInfos = memberInfoMapper.selectByExample(memberInfoExample);
        if (CollectionUtils.isNotEmpty(memberInfos)) {
            return memberInfos.get(0);
        }
        return null;
    }

    public JSONObject addMemberInfoBythirdPartyLogin(JSONObject reqDataJson, HttpServletRequest request, String systemVersion) throws ArgException {
        JSONObject returnData = newThirdPartyLogin(reqDataJson, request, systemVersion);

        return returnData;
    }

    private JSONObject newThirdPartyLogin(JSONObject reqDataJson, HttpServletRequest request, String systemVersion) throws ArgException {
        JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
        //第三方id
        String thirdPartyId = reqDataJson.getString("thirdPartyId");
        //第三方标识QQ WX WB
        String thirdPartyMark = reqDataJson.getString("thirdPartyMark");
        //昵称
        String nick = "";
        if (reqDataJson.containsKey("nick")) {
            nick = reqDataJson.getString("nick");
            if (!StringUtil.isBlank(nick)) {
                nick = StringUtil.filterEmoji(nick);
            }
        }
        //客户端类型 1IOS 2Android
        String regClient = reqDataJson.getString("regClient");
        //注册IP
        String regIp = StringUtil.getIpAddr(request);
        //渠道类型
        String sprChnl = reqDataJson.getString("sprChnl");
        sprChnl = DataDicUtil.getSprChnl(sprChnl);
        //注册地区 暂时固定值为 厦门
        String regArea = reqDataJson.getString("regArea");
        regArea = "";
        //头像
        String pic = reqDataJson.getString("pic");
        //性别
        String sexType = reqDataJson.getString("sexType");
        //生日
        String birthday = reqDataJson.getString("birthday");

        //设备id
        String deviceId = reqDataJson.getString("deviceId");
        //手机品牌
        String mobileBrand = reqDataJson.getString("mobileBrand");
        //手机品牌
        String mobileModel = reqDataJson.getString("mobileModel");
        //系统类型
        String system = reqDataJson.getString("system");
        //版本
        String version = reqDataJson.getString("version");
        //版本号
        String versionNum = reqDataJson.getString("versionNum");
        //序列号
        String imei = "";
        String unionid = reqDataJson.getString("unionid");

        if (!JsonUtils.isEmpty(reqDataJson, "imei")) {
            imei = reqDataJson.getString("imei");
        }
        //QQ WX WB
        if (!thirdPartyMark.equalsIgnoreCase("QQ") && !thirdPartyMark.equalsIgnoreCase("WX") && !thirdPartyMark.equalsIgnoreCase("WB") && !thirdPartyMark.equalsIgnoreCase("DY")) {
            throw new ArgException(thirdPartyMark + "不属于第三方联合登入");
        }
        String type = "2";
        Date createDate = new Date();
        JSONObject returnData = new JSONObject();
        MemberInfo memberInfo = new MemberInfo();
        if (thirdPartyMark.equalsIgnoreCase("WX")) {
            if (StringUtil.isBlank(unionid)) {
                throw new ArgException("登入失败");
            }
            MemberInfoExample memberInfoExampleWX = new MemberInfoExample();
            memberInfoExampleWX.createCriteria().andDelFlagEqualTo("0").andWeixinUnionidEqualTo(unionid);
            List<MemberInfo> memberInfoWXList = selectByExample(memberInfoExampleWX);
            if (CollectionUtils.isNotEmpty(memberInfoWXList)) {
                //根据unionid联合id查询到该微信用户
                //直接登入
                memberInfo = memberInfoWXList.get(0);
                Map<String, String> map = checkMemberStatus(memberInfo, reqPRM.getString("system"), reqPRM.getInt("version"), "3");
                if ("false".equals(map.get("success"))) {
                    returnData.put("success", map.get("success"));
                    returnData.put("errorMsg", map.get("errorMsg"));
                    returnData.put("mobile", map.get("mobile"));
                    return returnData;
                }
                type = memberInfo.getType() == null ? "" : memberInfo.getType();
            } else {
                //根据unionid联合id找不到用户，就根据thirdPartyId寻找用户
                MemberInfoExample memberInfoExample = new MemberInfoExample();
                memberInfoExample.createCriteria().andDelFlagEqualTo("0").andWeixinIdEqualTo(thirdPartyId);
                List<MemberInfo> memberInfoList = selectByExample(memberInfoExample);
                if (CollectionUtils.isNotEmpty(memberInfoList)) {
                    //存在微信用户,则更新用户信息
                    memberInfo = memberInfoList.get(0);
                    type = memberInfo.getType() == null ? "" : memberInfo.getType();
                    memberInfo.setWeixinUnionid(unionid);
                    memberInfo.setWeixinHead(pic);
                    if (!type.equals(Const.MEMBER_INFO_TYPE_MEMBER)) {
                        memberInfo.setNick(nick);
                        memberInfo.setPic(pic);
                        memberInfo.setUpdateDate(createDate);
                    }
                    updateByPrimaryKeySelective(memberInfo);

                    updateMemberExtend(memberInfo.getId(), sprChnl);

                } else {
                    //不存在，创建用户信息
                    memberInfo = addMemberInfo(type, nick, thirdPartyMark, thirdPartyId, unionid, sprChnl,
                            regArea, regClient, regIp, createDate, imei, mobileBrand, mobileModel, sexType, birthday, pic, null, null);
                }
            }
            //微信登入，查询用户是否有分享享免单
            cutPriceOrderDtlService.updateCutOrderDtlInfo(memberInfo);
            //微信登入，查询用户是否有帮助好友助力（邀请享补签卡）
            assistanceDtlService.updateAssistanceDtlInfo(memberInfo);
        } else if (thirdPartyMark.equalsIgnoreCase("QQ") || thirdPartyMark.equalsIgnoreCase("WB")) {
            memberInfo = memberExtendService.thirdExtendLogin(thirdPartyMark, thirdPartyId);
            if (memberInfo != null) {
                Map<String, String> map = checkMemberStatus(memberInfo, reqPRM.getString("system"), reqPRM.getInt("version"), "3");
                if ("false".equals(map.get("success"))) {
                    returnData.put("success", map.get("success"));
                    returnData.put("errorMsg", map.get("errorMsg"));
                    returnData.put("mobile", map.get("mobile"));
                    return returnData;
                }
                type = memberInfo.getType() == null ? "" : memberInfo.getType();
                if (!type.equals(Const.MEMBER_INFO_TYPE_MEMBER)) {
                    memberInfo.setNick(nick);
                    memberInfo.setPic(pic);
                    memberInfo.setUpdateDate(createDate);
                    updateByPrimaryKeySelective(memberInfo);
                }
                updateMemberExtend(memberInfo.getId(), sprChnl);
            } else {
                memberInfo = addMemberInfo(type, nick, thirdPartyMark, thirdPartyId, unionid, sprChnl, regArea, regClient, regIp, createDate, imei, mobileBrand, mobileModel, sexType, birthday, pic, null, null);
            }
        } else if (thirdPartyMark.equalsIgnoreCase("DY")) {
            List<DouyinMemberBind> binds = douyinMemberBindService.findModels(thirdPartyId);
            if (CollectionUtils.isNotEmpty(binds)) {
                memberInfo = selectByPrimaryKey(binds.get(0).getMemberId());
                Map<String, String> map = checkMemberStatus(memberInfo, reqPRM.getString("system"), reqPRM.getInt("version"), "3");
                if ("false".equals(map.get("success"))) {
                    returnData.put("success", map.get("success"));
                    returnData.put("errorMsg", map.get("errorMsg"));
                    returnData.put("mobile", map.get("mobile"));
                    return returnData;
                }
                type = memberInfo.getType() == null ? "" : memberInfo.getType();
                if (!type.equals(Const.MEMBER_INFO_TYPE_MEMBER)) {
                    memberInfo.setNick(nick);
                    memberInfo.setPic(pic);
                    memberInfo.setUpdateDate(createDate);
                    updateByPrimaryKeySelective(memberInfo);
                }
                updateMemberExtend(memberInfo.getId(), sprChnl);
            } else {
                memberInfo = addMemberInfo(type, nick, thirdPartyMark, thirdPartyId, unionid, sprChnl, regArea, regClient, regIp, createDate, imei, mobileBrand, mobileModel, sexType, birthday, pic, null, null);
                douyinMemberBindService.addDouYinAccount(memberInfo.getId(), thirdPartyId, unionid);
            }
        }
        returnData = thirdLogin(memberInfo, deviceId, regIp, mobileBrand, mobileModel, system, version, versionNum, imei, type, systemVersion);
        return returnData;
    }

    public void updateMemberExtend(Integer memberId, String sprChnl) {
        if(!StringUtil.isEmpty(sprChnl)) {
            MemberExtendExample memberExtendExample = new MemberExtendExample();
            memberExtendExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
            MemberExtend memberExtend = new MemberExtend();
            memberExtend.setSprChnl(sprChnl);
            memberExtend.setUpdateDate(DateUtil.getDate());
            memberExtendMapper.updateByExampleSelective(memberExtend, memberExtendExample);
        }
    }

    public MemberInfo addMemberInfo(String type, String nick, String thirdPartyMark, String thirdPartyId, String unionid,
                                    String sprChnl, String regArea, String regClient, String regIp, Date createDate, String imei,
                                    String mobileBrand, String mobileModel, String sexType, String birthday, String pic, String mobile, String mVerfiyFlag) {
        MemberInfo memberInfo = new MemberInfo();
        if ("1".equals(regClient)) {
            if (!StringUtil.isBlank(mobileModel) && mobileModel.contains(",")) {
                String newIosMobileModel = DataDicUtil.getStatusDesc("BU_MEMBER_INFO", "REQ_MOBILE_MODEL", mobileModel);
                if (!StringUtil.isBlank(newIosMobileModel)) {
                    mobileModel = newIosMobileModel;
                }
            }
        }
        if (!StringUtil.isBlank(mobile)) {
            memberInfo.setMobile(mobile);
        }
        if (!StringUtil.isBlank(mVerfiyFlag)) {
            memberInfo.setmVerfiyFlag(mVerfiyFlag);
        }
        if (!StringUtil.isBlank(type)) {
            memberInfo.setType(type);
        }
        if (!StringUtil.isBlank(nick)) {
            memberInfo.setNick(nick);
        }
        if (!StringUtil.isBlank(thirdPartyMark)) {
            if (thirdPartyMark.equals("WX")) {
                memberInfo.setWeixinId(thirdPartyId);
                memberInfo.setRemarks(thirdPartyMark);
                if (!StringUtil.isBlank(unionid)) {
                    memberInfo.setWeixinUnionid(unionid);
                }
            }
        }
        if (!StringUtil.isBlank(sprChnl)) {
            memberInfo.setSprChnl(sprChnl);
        }
        if (!StringUtil.isBlank(regArea)) {
            memberInfo.setRegArea(regArea);
        }
        if (!StringUtil.isBlank(regClient)) {
            memberInfo.setRegClient(regClient);
        }
        if (!StringUtil.isBlank(regIp)) {
            memberInfo.setRegIp(regIp);
        }
        if (!StringUtil.isBlank(imei)) {
            memberInfo.setReqImei(imei);
        }
        if (!StringUtil.isBlank(mobileBrand)) {
            memberInfo.setReqMobileBrand(mobileBrand);
        }
        if (!StringUtil.isBlank(mobileModel)) {
            memberInfo.setReqMobileModel(mobileModel);
        }
        if (!StringUtil.isBlank(sexType)) {
            memberInfo.setSexType(sexType);
        }
        if (!StringUtil.isBlank(birthday)) {
            Date date = DateUtil.getFormatString(birthday, "yyyy-MM-dd");
            memberInfo.setBirthday(date);
        }
        if (!StringUtil.isBlank(pic)) {
            memberInfo.setPic(pic);
            memberInfo.setWeixinHead(pic);
        }
        memberInfo.setCreateDate(createDate);
        memberInfo.setDelFlag("0");
        memberInfo.setGroupId(1);//注册会员标识
        insertSelective(memberInfo);
        //创建用户账户信息
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setMemberId(memberInfo.getId());
        memberAccount.setCreateBy(memberInfo.getId());
        memberAccount.setCreateDate(createDate);
        memberAccount.setDelFlag("0");
        memberAccountService.insertSelective(memberAccount);
        //添加会员注册状态
        memberStatusLogService.addMemberRegisterStatus(memberInfo.getId(), Const.MEMBER_INFO_STATUS_ACTIVATION_A);
        //会员扩展
        memberExtendService.addModel(memberInfo.getId(), thirdPartyMark, thirdPartyId, sprChnl);
        return memberInfo;
    }

    public JSONObject thirdLogin(MemberInfo memberInfo, String deviceId, String regIp, String mobileBrand, String mobileModel, String system, String version, String versionNum, String imei, String type, String systemVersion) {
        //登入
        String accessToken = UUID.randomUUID().toString();
        String mVerfiyFlag = memberInfo.getmVerfiyFlag();
        String isBingMobile = "1";//是否已绑定手机 0 否 1是
        String mustBeBoundMobile = "0"; //是否要求必须绑定手机号 0 否 1是
        String memberThirdRegType = getMemberThridLoginType(memberInfo, memberInfo.getId());
        AppAccessTokenExample appAccessTokenExample = new AppAccessTokenExample();
        appAccessTokenExample.createCriteria().andMemberIdEqualTo(memberInfo.getId()).andDeviceIdEqualTo(deviceId).andDelFlagEqualTo("0");
        List<AppAccessToken> appAccessTokens = appAccessTokenService.selectByExample(appAccessTokenExample);
        AppAccessToken appAccessToken;
        if (appAccessTokens == null || appAccessTokens.size() == 0) {
            appAccessToken = new AppAccessToken();
            appAccessToken.setMemberId(Integer.valueOf(memberInfo.getId()));
            appAccessToken.setAccessToken(accessToken);
            appAccessToken.setDelFlag("0");
            appAccessToken.setCreateDate(new Date());
            appAccessToken.setUpdateDate(new Date());
            appAccessToken.setDeviceId(deviceId);

            appAccessTokenService.insertSelective(appAccessToken);
        } else {
            appAccessToken = appAccessTokens.get(0);

            AppAccessToken accessToken4Update = new AppAccessToken();

            accessToken4Update.setAccessToken(accessToken);
            accessToken4Update.setUpdateDate(new Date());
            accessToken4Update.setId(appAccessToken.getId());
            appAccessTokenService.updateByPrimaryKeySelective(accessToken4Update);

//			appAccessToken.setAccessToken(accessToken);
//			appAccessToken.setUpdateDate(new Date());
//			appAccessTokenService.updateByPrimaryKeySelective(appAccessToken);
        }

        // 插入一条登录日志
        AppLoginLog appLoginLog = new AppLoginLog();
        appLoginLog.setCreateDate(new Date());
        appLoginLog.setDelFlag("0");
        appLoginLog.setIp(regIp);
        appLoginLog.setMemberId(memberInfo.getId());
        appLoginLog.setMobileBrand(mobileBrand);
        appLoginLog.setMobileModel(mobileModel);
        appLoginLog.setSystem(system);
        appLoginLog.setVersion(version);
        appLoginLog.setVersionNum(versionNum);
        appLoginLog.setImei(imei);
        appLoginLog.setSystemVersion(systemVersion);
        appLoginLog.setType("1");
        appLoginLogService.insertSelective(appLoginLog);

        MemberGroup memberGroup = memberGroupService.findModel(memberInfo.getGroupId());
        String levelPic = memberGroup.getPic();
        if (!StringUtil.isBlank(levelPic)) {
            levelPic = FileUtil.getImageServiceUrl() + levelPic;
        }
        if (appContext.olderThan(68, 70)) { //低版本才需要判断qq、微博注册用户是否绑定过手机号
            if (("2".equals(memberThirdRegType) || "3".equals(memberThirdRegType)) && !"1".equals(mVerfiyFlag)) {
                isBingMobile = "0";
            }
        } else { //改自需求1962 一键登陆
            if (!"1".equals(mVerfiyFlag)) {
                isBingMobile = "0";
            }
            if ("2".equals(memberThirdRegType) || "3".equals(memberThirdRegType)) {
                mustBeBoundMobile = "1";
            }
        }
        JSONObject returnData = new JSONObject();
        returnData.put("isBingMobile", isBingMobile);
        returnData.put("mustBeBoundMobile", mustBeBoundMobile);
        returnData.put("levelName", memberGroup.getName());
        returnData.put("levelPic", levelPic);
        returnData.put("token", accessToken);
        returnData.put("memberId", memberInfo.getId());
        returnData.put("nick", memberInfo.getNick());
        returnData.put("type", type);
        returnData.put("userLevel", "0");//客服等级 0普通用户 1VIP
        return returnData;
    }

    /**
     * 获取第三方注册类型
     * @return 1 微信 2 qq 3 微博 4 抖音
     */
    public String getMemberThridLoginType(MemberInfo memberInfo, Integer memberId) {
        if (memberInfo == null) {
            memberInfo = selectByPrimaryKey(memberId);
        }
        String type = memberInfo.getType();
        if ("2".equals(type)) {
            if (!StringUtil.isBlank(memberInfo.getWeixinUnionid()) || !StringUtil.isBlank(memberInfo.getWeixinId())) {
                return "1";//微信
            }
            List<MemberExtend> memberExtends = memberExtendService.findModelByMemberId(memberInfo.getId());
            if (CollectionUtils.isNotEmpty(memberExtends)) {
                MemberExtend memberExtend = memberExtends.get(0);
                if (!StringUtil.isBlank(memberExtend.getQqId())) {
                    return "2";//qq
                } else if (!StringUtil.isBlank(memberExtend.getWeiboId())) {
                    return "3";//微博
                }
            }
            DouyinMemberBind douyinMemberBind = douyinMemberBindService.getByMemberId(memberInfo.getId());
            if (douyinMemberBind != null) {
                return "4";
            }
        }
        return "";
    }


    public MemberInfo updateweixinUnionId(String weixinUnionId, Integer memberId) {
        //1.查看weixinUnionid微信用户是否存在
        Date date = new Date();
        MemberInfo memberInfo;
        List<MemberInfo> memberInfos = findModelByUnionId(weixinUnionId);
        if (CollectionUtils.isEmpty(memberInfos)) {
            //2.存在,更换微信绑定
            memberInfo = findMemberById(memberId);
            if (memberInfo != null && memberInfo.getId() != null) {
                memberInfo.setWeixinUnionid(weixinUnionId);
                memberInfo.setUpdateDate(date);
                updateByPrimaryKeySelective(memberInfo);
            } else {
                throw new ArgException("未登入");
            }
        } else {
            //2.不存在
            throw new ArgException("绑定失败,您的微信已注册过醒购或已绑定过其他账号。");
        }

        return memberInfo;
    }

    public List<MemberInfo> findModelByUnionId(String weixinUnionId) {
        MemberInfoExample memberInfoExample = new MemberInfoExample();
        memberInfoExample.createCriteria().andWeixinUnionidEqualTo(weixinUnionId).andDelFlagEqualTo(Const.FLAG_FALSE);
        memberInfoExample.setOrderByClause("id desc");
        List<MemberInfo> memberInfos = selectByExample(memberInfoExample);
        return memberInfos;
    }

    public Map<String, String> checkMemberStatus(MemberInfo memberInfo, String system, Integer version, String type) {
        Map<String, String> map = new HashMap<String, String>();
        String limitFunction = memberInfo.getLimitFunction();//限制功能(1.限制评价2.限制购买3.限制登录 如：1,2表示限制评价、购买功能)
        String status = memberInfo.getStatus();
        String errorMsg = "";
        String success = "true";
        if (status.equalsIgnoreCase("C")) {
            success = "false";
            if ((version <= 45 && system.equals(Const.ANDROID)) || (version <= 48 && system.equals(Const.IOS))) {
                throw new ArgException("您已经注销账户，若要恢复账户，请联系平台客服处理，平台客服电话：01053185321（工作日9:00-22:00）");
            } else {
                StringBuffer sbf = new StringBuffer();
                sbf.append("<HTML>");
                sbf.append("<BODY>");
                sbf.append("<style>");
                sbf.append("* {text-align:center ; font-size:13px}");
                sbf.append("</style>");
                sbf.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' >");
                sbf.append("您已经注销账户，若要恢复账户，</br>");
                sbf.append("联系平台客服处理，平台客服</br>");
                sbf.append("电话：01053185321（工作日9:00-22:00）");
                sbf.append("</BODY>");
                sbf.append("</HTML>");
                errorMsg = sbf.toString();
            }
        } else {
            if (!StringUtil.isBlank(limitFunction) && status.equalsIgnoreCase("P")) {
                List<String> limitFunctions = Arrays.asList(limitFunction.split(","));
                if (limitFunctions.contains(type)) {
                    success = "false";
                    String msg = "您的账号使用异常，请联系平台客服：";
                    if ("3".equals(type)) {
                        msg = "您的账号登陆异常，请联系平台客服：";
                    }
                    if ((system.equals(Const.ANDROID) && version > 49) || (system.equals(Const.IOS) && version > 52)) {
                        StringBuffer sbf = new StringBuffer();
                        sbf.append("<HTML>");
                        sbf.append("<BODY>");
                        sbf.append("<style>");
                        sbf.append("* {text-align:center ; font-size:13px}");
                        sbf.append("</style>");
                        sbf.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' >");
                        sbf.append(msg + "</br>");
                        sbf.append("01053185321（工作日9:00-22:00）");
                        sbf.append("</BODY>");
                        sbf.append("</HTML>");
                        errorMsg = sbf.toString();
                    }
                }
            }
        }
        map.put("success", success);
        map.put("errorMsg", errorMsg);
        map.put("mobile", "01053185321");
        return map;
    }

    public Map<String, Object> memberJudgeCanCancal(Integer memberId, String cancelReason) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = true;
        String msg = "";
        String subOrderCode = "";
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        //1：凡是存在待付款，待发货，待收货，已发货的订单，不满足注销条件
        paramsMap.put("memberId", memberId);
        paramsMap.put("type", "1");
        List<OrderDtlCustom> orderDtlCustoms = orderDtlService.getUnComplateOrderNum(paramsMap);
        if (CollectionUtils.isNotEmpty(orderDtlCustoms)) {
            //不满足注销条件
            subOrderCode = orderDtlCustoms.get(0).getSubOrderCode();
            success = false;
        }
        //2:存在待商家回复或待客户回复的投诉单，不满足注销条件
        if (success) {
            AppealOrderExample appealOrderExample = new AppealOrderExample();
            appealOrderExample.createCriteria().andUserIdEqualTo(memberId).andStatusIn(Arrays.asList("1", "2")).andDelFlagEqualTo("0");
            Integer appealOrderNum = appealOrderService.countByExample(appealOrderExample);
            if (appealOrderNum > 0) {
                //不满足注销条件
                success = false;
            }
        }
        //3：判断订单的完成时间是否大于15天，若不大于则不满足条件
        if (success) {
            paramsMap.put("type", "2");
            orderDtlCustoms = orderDtlService.getUnComplateOrderNum(paramsMap);
            if (CollectionUtils.isNotEmpty(orderDtlCustoms)) {
                //不满足注销条件
                subOrderCode = orderDtlCustoms.get(0).getSubOrderCode();
                success = false;
            }
        }
        //4：定金存在未付，已付，尾款已下单，尾款已支付，尾款已下单取消，即不满足条件
        if (success) {
            SubDepositOrderExample depositOrderExample = new SubDepositOrderExample();
            depositOrderExample.createCriteria().andMemberIdEqualTo(memberId).andStatusIn(Arrays.asList("1", "2", "3", "4", "8")).andDelFlagEqualTo("0");
            depositOrderExample.setLimitStart(0);
            depositOrderExample.setLimitSize(1);
            List<SubDepositOrder> subDepositOrders = subDepositOrderService.selectByExample(depositOrderExample);
            if (CollectionUtils.isNotEmpty(subDepositOrders)) {
                subOrderCode = subDepositOrders.get(0).getSubDepositOrderCode();
                success = false;
            }
        }
        if (success) {
            msg = "您的账户已经成功注销！如有需要可联系平台客服哦，客服电话：01053185321（工作日：9:00-22:00）";
            //更新会员信息表信息
            MemberInfo memberInfo = selectByPrimaryKey(memberId);
            memberInfo.setStatus("C");//C 注销状态
            memberInfo.setCancelReason(cancelReason);
            memberInfo.setUpdateBy(memberId);
            memberInfo.setUpdateDate(new Date());
            updateByPrimaryKeySelective(memberInfo);
        } else {
            msg = "您的账户在15天内有过交易记录，为保证交易信息的安全和完整性，请交易结束（交易成功/交易关闭）15天后再申请注销";
            if (!StringUtil.isBlank(subOrderCode)) {
                msg = msg + ",订单号：" + subOrderCode;
            }
        }
        if (success) {
            //添加会员注册状态
            memberStatusLogService.addMemberRegisterStatus(memberId, Const.MEMBER_INFO_STATUS_LOGOUT_C);
        }
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }

    @Autowired
    private SVipPracticeRecordService sVipPracticeRecordService;

    public String getIsSvip(MemberInfo memberInfo, Integer memberId) {
        if(memberId==null) return StateConst.FALSE;

        SVipInfo sVipInfo = isSVip(memberInfo, memberId);
        return sVipInfo.isSVip() ? StateConst.TRUE : StateConst.FALSE;
    }

    public SVipInfo isSVip(MemberInfo memberInfo, Integer memberId) {
        if(memberId==null) return SVipInfo.no();

        //真实svip判断
        if (isRealSVip(memberInfo, memberId)) {
            return SVipInfo.real();
        }
        //试用svip判断
        SvipPracticeRecord svipPracticeRecord = sVipPracticeRecordService.getMemberLastRecRecord(memberId);
        boolean isUsableSVipPractice = isSVipPractice(svipPracticeRecord);
        if (isUsableSVipPractice) {
            return SVipInfo.trail(svipPracticeRecord);
        }
        return SVipInfo.no();
    }

    private boolean isSVipPractice(SvipPracticeRecord recRecord) {
        if (recRecord == null || StateConst.TRUE.equals(recRecord.getDelFlag()) || recRecord.getPracticeStartTime() == null || recRecord.getPracticeEndTime() == null) {
            return false;
        }
        Date now = new Date();
        return now.after(recRecord.getPracticeStartTime()) && now.before(recRecord.getPracticeEndTime());
    }

    public boolean isRealSVip(MemberInfo memberInfo, Integer memberId) {
        if (memberInfo == null) {
            memberInfo = selectByPrimaryKey(memberId);
        }
        return memberInfo != null
                && StateConst.TRUE.equals(memberInfo.getIsSvip())
                && memberInfo.getSvipExpireDate() != null
                && new Date().before(memberInfo.getSvipExpireDate());
    }

    public void updateMemberSvipInfo(SvipOrder order) {
        Date curentDate = new Date();
        Integer memberId = order.getMemberId();
        Integer yearsOfPurchase = order.getYearsOfPurchase();
        MemberInfo memberInfo = findMemberById(memberId);
        Date svipExpireDate = memberInfo.getSvipExpireDate();
        if (svipExpireDate == null) {
            svipExpireDate = DateUtil.addYear(curentDate, yearsOfPurchase);
        } else {
            boolean isSvip = isRealSVip(memberInfo, memberId);
            if (isSvip) {
                svipExpireDate = DateUtil.addYear(svipExpireDate, yearsOfPurchase);
            } else {
                svipExpireDate = DateUtil.addYear(curentDate, yearsOfPurchase);
            }
        }
        memberInfo.setIsSvip("1");
        memberInfo.setSvipExpireDate(svipExpireDate);
        memberInfo.setUpdateDate(curentDate);
        memberInfo.setUpdateBy(memberId);
        updateByPrimaryKeySelective(memberInfo);
    }

    public boolean getMemberIsBindSuperior(MemberInfo memberInfo, Integer memberId) {
        boolean isBind = true;
        if (memberInfo == null) {
            memberInfo = selectByPrimaryKey(memberId);
        }
        if (memberInfo.getInvitationMemberId() == null) {
            isBind = false;
        }
        return isBind;
    }

    /**
     * 找出会员的下级会员的数量
     *
     * @param openType
     */
    public int getMemberSubCount(MemberInfo memberInfo, Integer memberId, String openType) {
        Integer count = 0;
        Date currentDate = new Date();
        if (memberInfo == null) {
            memberInfo = selectByPrimaryKey(memberId);
        }
        MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberInfo.getId(), openType);
        if (memberNovaRecord != null) {
            Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
            Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
            MemberInfoExample memberInfoExample = new MemberInfoExample();
            memberInfoExample.createCriteria().andInvitationMemberIdEqualTo(memberId).andInvitationCodeBindTimeLessThanOrEqualTo(currentDate)
                    .andInvitationCodeBindTimeGreaterThanOrEqualTo(novaProjectBeginDate).andDelFlagEqualTo("0");
            count = countByExample(memberInfoExample);
        }
        return count;
    }

    /**
     * 找出会员的下级会员
     */
    public List<Integer> getMemberSubUser(Integer memberId) {
        List<Integer> memberIds = new ArrayList<>();
        MemberInfoExample memberInfoExample = new MemberInfoExample();
        memberInfoExample.createCriteria().andInvitationMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
        List<MemberInfo> infos = selectByExample(memberInfoExample);
        if (CollectionUtils.isNotEmpty(infos)) {
            for (MemberInfo info : infos) {
                memberIds.add(info.getId());
            }
        }
        return memberIds;
    }

    /**
     * 找出邀请码相对的会员
     */
    public MemberInfo getMemberByInvitationCode(String invitationCode) {
        MemberInfo memberInfo = null;
        MemberInfoExample memberInfoExample = new MemberInfoExample();
        memberInfoExample.createCriteria().andInvitationCodeEqualTo(invitationCode).andDelFlagEqualTo("0");
        List<MemberInfo> infos = selectByExample(memberInfoExample);
        if (CollectionUtils.isNotEmpty(infos)) {
            memberInfo = infos.get(0);
        }
        return memberInfo;
    }

    public Map<String, Object> updateMemberInvitationCode(JSONObject reqPRM, JSONObject reqDataJson) {
        Integer memberId = reqDataJson.getInt("memberId");
        String invitationCode = reqDataJson.getString("invitationCode");
        MemberInfo memberInfo = selectByPrimaryKey(memberId);
        Map<String, Object> map = new HashMap<>();
        boolean isSuccess = true;
        String msg = "成功绑定上级";
        if (memberInfo.getInvitationMemberId() != null) {
            isSuccess = false;
            msg = "您已绑定上级，请勿重复绑定";
            map.put("isSuccess", isSuccess);
            map.put("msg", msg);
            return map;
        }
        MemberInfo parentUser = getMemberByInvitationCode(invitationCode);
        if (parentUser == null) {
            isSuccess = false;
            msg = "邀请码不存在";
            map.put("isSuccess", isSuccess);
            map.put("msg", msg);
            return map;
        }
        if (parentUser.getCreateDate().after(memberInfo.getCreateDate())) {
            isSuccess = false;
            msg = "绑定失败，不能绑定新用户为上级";
            map.put("isSuccess", isSuccess);
            map.put("msg", msg);
            return map;
        }
        if (isSuccess) {
            Date currentDate = new Date();
            MemberInfoExample memberInfoExample = new MemberInfoExample();
            memberInfoExample.createCriteria().andIdEqualTo(memberId).andInvitationMemberIdIsNull().andDelFlagEqualTo("0");
            memberInfo.setInvitationMemberId(parentUser.getId());
            memberInfo.setInvitationCodeBindTime(currentDate);
            memberInfo.setUpdateDate(currentDate);
            int count = updateByExampleSelective(memberInfo, memberInfoExample);
            if (count <= 0) {
                throw new ArgException("您已绑定上级，请勿重复绑定");
            }
            //互相关注
            reqDataJson.put("memberId", memberId);
            reqDataJson.put("friendMemberId", parentUser.getId());
            memberAttentionService.updateMemberAttention(reqPRM, reqDataJson);
            reqDataJson.put("memberId", parentUser.getId());
            reqDataJson.put("friendMemberId", memberId);
            memberAttentionService.updateMemberAttention(reqPRM, reqDataJson);
            //用户增加150积分
            Integer integral = 150;
            memberAccountService.addGiftIntegral(integral, memberInfo, memberId, Const.INTEGRAL_TYPE_INVITATION_CODE);
        }
        map.put("isSuccess", isSuccess);
        map.put("msg", msg);
        return map;
    }

    /**
     * 获取会员关注的用户
     */
    public List<MemberInfoCustom> getMemberFollowUser(Map<String, Object> paramsMap) {

        return memberInfoCustomMapper.getMemberFollowUser(paramsMap);
    }

    /**
     * 获取我的粉丝
     */
    public List<MemberInfoCustom> getMemberFansUser(Map<String, Object> paramsMap) {

        return memberInfoCustomMapper.getMemberFansUser(paramsMap);
    }

    /**
     * 获取我的推荐好友
     */
    public List<MemberInfoCustom> getMemberRecommendUser(Map<String, Object> paramsMap) {

        return memberInfoCustomMapper.getMemberRecommendUser(paramsMap);
    }

    public CurrentMember createCurrentMember(int id, String token) {
        return memberInfoBiz.createCurrentMember(id, token);
    }

	public void saveMemberAreaInfo(String memberId, String province, String city) {
		Area provinceArea = areaService.selectByPrimaryKey(Integer.parseInt(province));
		Area cityArea = areaService.selectByPrimaryKey(Integer.parseInt(city));
		MemberInfo mi = new MemberInfo();
		mi.setUpdateDate(new Date());
		mi.setRegArea(provinceArea.getAreaName()+cityArea.getAreaName());
		mi.setProvince(Integer.parseInt(province));
		mi.setCity(Integer.parseInt(city));
		MemberInfoExample mie = new MemberInfoExample();
		mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(memberId));
		this.updateByExampleSelective(mi, mie);
	}

	@Transactional
    public ResponseMsg doBindMobile(String mobile, Integer memberId, String password, String deviceId) {
        MemberInfoExample memberInfoCount = new MemberInfoExample();
        memberInfoCount.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
        int count = countByExample(memberInfoCount);
        if (count > 0) {
            throw new ArgException("该手机号已注册过或已被绑定过!");
        }

        MemberInfoExample memberInfoExample = new MemberInfoExample();
        memberInfoExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(memberId);
        List<MemberInfo> memberInfos = selectByExample(memberInfoExample);
        MemberInfo memberInfo = new MemberInfo();
        if (CollectionUtils.isNotEmpty(memberInfos)) {
            memberInfo = memberInfos.get(0);
            memberInfo.setMobile(mobile);
            memberInfo.setmVerfiyFlag("1");
            memberInfo.setNick(mobile);
            //memberInfo.setLoginCode(mobile);
            if (!StringUtil.isBlank(password)) {
                memberInfo.setLoginPass(password);
            }
            memberInfo.setType("1");
            memberInfo.setUpdateBy(memberId);
            memberInfo.setUpdateDate(new Date());
            updateByPrimaryKeySelective(memberInfo);
            //金额和积分的兑换比例
            IntegralType integralType = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION));
            Integer integral = 0;
            Integer gValue = 0;
            if (integralType != null) {
                if (integralType.getIntType().equals("1")) {
                    integral = integralType.getIntegral() == null ? 0 : integralType.getIntegral();
                }
                if (integralType.getGrowType().equals("1")) {
                    gValue = integralType.getgValue() == null ? 0 : integralType.getgValue();
                }
            }
            MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
            if (memberAccount != null) {
                memberAccount.setIntegral(memberAccount.getIntegral() + integral);
                memberAccount.setgValue(memberAccount.getgValue() + gValue);
                memberAccountService.updateModel(memberAccount);
                memberGroupService.updateMemberGroup(memberAccount.getgValue(), memberId);
                if (gValue != 0) {
                    growthDtlService.addGrowthDtl(gValue, gValue, memberAccount.getId(), memberId, null, Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION);
                }
                if (integral != 0) {
                    integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME,
                            Const.INTEGRAL_TYPE_MOBILE_AUTHENTICATION, integral, integral, null, memberInfo.getId(), "1");
                }
            }
        } else {
            throw new ArgException("找不到该会员");
        }

        String accessToken = UUID.randomUUID().toString();

        AppAccessTokenExample appAccessTokenExample = new AppAccessTokenExample();
        appAccessTokenExample.createCriteria().andMemberIdEqualTo(memberInfo.getId()).andDeviceIdEqualTo(deviceId).andDelFlagEqualTo("0");
        List<AppAccessToken> appAccessTokens = appAccessTokenService.selectByExample(appAccessTokenExample);
        AppAccessToken appAccessToken;
        if (appAccessTokens == null || appAccessTokens.size() == 0) {
            appAccessToken = new AppAccessToken();
            appAccessToken.setMemberId(memberInfo.getId());
            appAccessToken.setAccessToken(accessToken);
            appAccessToken.setDelFlag("0");
            appAccessToken.setCreateDate(new Date());
            appAccessToken.setUpdateDate(new Date());
            appAccessToken.setDeviceId(deviceId);

            appAccessTokenService.insertSelective(appAccessToken);
        } else {
            appAccessToken = appAccessTokens.get(0);
            appAccessToken.setAccessToken(accessToken);
            appAccessToken.setUpdateDate(new Date());
            appAccessTokenService.updateByPrimaryKeySelective(appAccessToken);
        }

        MemberGroup memberGroup = memberGroupService.findModel(memberInfo.getGroupId());
        String levelPic = "";
        String levelName = "";
        if (memberGroup != null) {
            levelPic = memberGroup.getPic();
            levelName = memberGroup.getName();
            if (!StringUtil.isBlank(levelPic)) {
                levelPic = FileUtil.getImageServiceUrl() + levelPic;
            }
        }
        JSONObject returnData = new JSONObject();
        returnData.put("levelName", levelName);
        returnData.put("levelPic", levelPic);
        returnData.put("token", accessToken);
        returnData.put("memberId", memberInfo.getId());
        returnData.put("nick", memberInfo.getNick());
        returnData.put("type", memberInfo.getType());
        returnData.put("userLevel", "0");//客服等级 0普通用户 1VIP
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
    }
}
