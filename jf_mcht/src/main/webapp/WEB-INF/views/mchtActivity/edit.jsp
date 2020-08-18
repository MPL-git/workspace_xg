<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="readonly" value="true" />
<c:if test="${empty activity || activity.status==1 || activity.status==4}">
    <c:set var="readonly" value="false" />
</c:if>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>编辑特卖活动</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
        .hidden{
            display:none;
        }
        .dv1{
            border:1px solid #cccccc;
            border-width:2px 1px 1px 1px;
            padding:10px 10px;
            margin-bottom: 10px;
            line-height: 30px;
        }

        .vm-famate .vm {
            margin: 0 3px 0 0;
        }
    </style>
</head>

<body>
<div class="x_panel container-fluid py-tm">
    <div class="row content-title">
        <input type="hidden" id="basePath" value="${ctx}">
        <div class="t-title">
            <c:if test="${empty activity}">
                添加品牌特卖
                <a href="javascript:void(0);" onclick="getContent('${ctx}/mcht/activity')">返回</a>
            </c:if>
            <c:if test="${not empty activity}">
                查看品牌特卖
                <a href="javascript:void(0);" onclick="getContent('${ctx}/mcht/activity/view?id=${id}')">返回</a>
            </c:if>

        </div>
    </div>

    <div class="search-container container-fluid vm-famate">
        <c:if test="${not empty activity && activity.status==6}">
            <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <tr>
                    <td rowspan="2" class="text-center" style="vertical-align:middle;">活动排期</td>
                    <td>活动预热时间</td>
                    <td>活动开始时间</td>
                    <td>活动结束时间</td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${not empty activityArea.preheatTime}">
                            <fmt:formatDate value='${activityArea.preheatTime}' pattern='yyyy-MM-dd hh:mm:ss'/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${not empty activityArea.activityBeginTime}">
                            <fmt:formatDate value='${activityArea.activityBeginTime}' pattern='yyyy-MM-dd hh:mm:ss'/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${not empty activityArea.activityEndTime}">
                            <fmt:formatDate value='${activityArea.activityEndTime}' pattern='yyyy-MM-dd hh:mm:ss'/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </c:if>
        <c:if test="${not empty activity && activity.status==4}">
            <div class="dv1">
                驳回原因: <span style="color: #F00000">${activity.operateAuditRemarks}</span>
            </div>
        </c:if>

        <form id="dataFrom">
            <input type="hidden" value="${activityArea.id}" name="activityArea.id">
            <input type="hidden" name="activity.preSellAuditStatus" id="preSellAuditStatus">
            <div class="table-responsive">
                <table class="table table-bordered ">
                    <tbody>
                    <tr>
                        <td class="editfield-label-td">活动名称</td>
                        <td colspan="2" class="text-left">
                            <input type="text" id="name" name="activityArea.name" value="${activityArea.name}"
                                   validate="{required:true}" maxlength="100">
                            <span style="color: #999">限制100个字</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">类目</td>
                        <td colspan="2" class="text-left">
                            <select name="activity.productTypeId" id="productTypeId" validate="{required:true}" <c:if test="${not empty activity}">disabled="disabled"</c:if>>
                                <option value="">--请选择--</option>
                                <c:forEach var="productType" items="${productTypeList}">
                                    <option value="${productType.id}"
                                            <c:if test="${activity.productTypeId==productType.id}">selected</c:if>>${productType.name}</option>
                                </c:forEach>
                            </select>
                            <select name="activity.productTypeSecondId" id="productTypeSecondId" validate="{required:true}" <c:if test="${not empty activity}">disabled="disabled"</c:if>>
                                <c:if test="${empty activity}">
                                    <option value="">--请选择--</option>
                                </c:if>
                                <c:if test="${not empty activity}">
                                    <option value="${secondProductType.id}">${secondProductType.name}</option>
                                </c:if>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">活动主品牌</td>
                        <td colspan="2" class="text-left">
                            <select name="activity.productBrandId" id="productBrandId" validate="{required:true}" <c:if test="${not empty activity}">disabled="disabled"</c:if>>
                                <option value="">--请选择--</option>
                                <c:forEach var="productBrand" items="${productBrandList}">
                                    <option value="${productBrand.id}"
                                            <c:if test="${activity.productBrandId==productBrand.id}">selected</c:if>>${productBrand.name}</option>
                                </c:forEach>
                            </select>
                            品牌限制：<input type="radio" name="activity.brandLimitType" value="1" <c:if test="${not empty activity}">disabled="disabled"</c:if> <c:if test="${empty activity || activity.brandLimitType=='1'}">checked="checked"</c:if>>品牌专场
                            <input type="radio" name="activity.brandLimitType" value="2" <c:if test="${not empty activity}">disabled="disabled"</c:if> <c:if test="${activity.brandLimitType=='2'}">checked="checked"</c:if>>多品牌联合
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">利益点</td>
                        <td colspan="2" class="text-left">
                            <input type="text" id="benefitPoint" name="activityArea.benefitPoint"
                                   value="${activityArea.benefitPoint}" validate="{required:true}">
                            <span style="color: #999">简述促销方案，与入口图、海报图同时展示给用户</span>
                        </td>
                    </tr>
        <%--
                    <c:if test="${mchtInfo.mchtType !='1'}">
                        <tr>
                            <td class="editfield-label-td">申请预售定金</td>
                            <td colspan="2" class="text-left">
                                <input type="checkbox" id="agree" <c:if test="${not empty activity.preSellAuditStatus && activity.preSellAuditStatus ne '0'}">checked="checked"</c:if>>选择即同意<a href="javascript:;" onclick="toAgreement();">《醒购预售商家协议》</a>
                            </td>
                        </tr>
                    </c:if>
        --%>

                    <tr>
                        <td class="editfield-label-td">期望活动时间</td>
                        <td colspan="2" class="text-left">
                            <input type="text" validate="{required:true}"
                                   value="<fmt:formatDate value='${activity.expectedStartTime}' pattern='yyyy-MM-dd'/>"
                                   id="expectedStartTime" name="activity.expectedStartTime" data-date-format="yyyy-mm-dd">
                        </td>
                    </tr>

                    <style>
                        .single_pic_picker.x1 {
                            width: 400px;
                            height: 200px;
                            margin: 0;
                        }
                        .single_pic_picker.x1 div,
                        .single_pic_picker.x1 img,
                        .single_pic_picker.x1 input {
                            width: 100% !important;
                            height: 100% !important;
                        }
                        .single_pic_picker.x1 div {
                            line-height: 200px;
                        }
                    </style>
                    <tr>
                        <td class="editfield-label-td">品牌团入口图</td>
                        <td colspan="2" class="text-left padding-10">
                            <div class="single_pic_picker x1">
                                <input type="hidden" name="activity.brandTeamPic" value="${activity.brandTeamPic}"/>
                                <input id="activity.brandTeamPicFile" onchange="loadImageFile(this, 'brandTeamPicFile')" type="file">
                                <c:if test='${activity.brandTeamPic != null && activity.brandTeamPic != ""}'>
                                    <img src="${ctx}/file_servelt${activity.brandTeamPic}" id="brand_header">
                                </c:if>
                                <c:if test='${activity.brandTeamPic==null||activity.brandTeamPic==""}'>
                                    <div>+</div>
                                </c:if>
                            </div>
                            <div style="color: #999;">图片尺寸宽1080*335px，图片大小为100KB以内</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">入口图</td>
                        <td colspan="2" class="text-left padding-10">
                            <div class="single_pic_picker x1">
                                <input type="hidden" name="activity.entryPic" value="${activity.entryPic}"/>
                                <input id="activity.entryPicFile" onchange="loadImageFile(this, 'entryPicFile')" type="file">
                                <c:if test='${activity.entryPic != null && activity.entryPic != ""}'>
                                    <img src="${ctx}/file_servelt${activity.entryPic}" id="entry_header">
                                </c:if>
                                <c:if test='${activity.entryPic==null||activity.entryPic==""}'>
                                    <div>+</div>
                                </c:if>
                            </div>
                            <div style="color: #999;">图片尺寸宽800*400px，图片大小为100KB以内</div>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">头部海报</td>
                        <td colspan="2" class="text-left padding-10">
                            <div class="single_pic_picker x1">
                                <input type="hidden" name="activity.posterPic" value="${activity.posterPic}"/>
                                <input id="activity.posterPicFile" onchange="loadImageFile(this, 'posterPicFile')" type="file">
                                <c:if test='${activity.posterPic != null && activity.posterPic != ""}'>
                                    <img src="${ctx}/file_servelt${activity.posterPic}" id="preview_header">
                                </c:if>
                                <c:if test='${activity.posterPic==null||activity.posterPic==""}'>
                                    <div>+</div>
                                </c:if>
                            </div>
                            <div style="color: #999;">图片尺寸宽800*400px，图片大小为100KB以内</div>
                        </td>
                    </tr>
                    <%-- pop--%>
                    <c:if test="${mchtInfo.mchtType !='1' or mchtInfo.isManageSelf =='1'}">
                        <tr id="tr_preferentialType">
                            <td class="editfield-label-td">促销方式</td>
                            <td colspan="2" class="text-left">
                                <c:forEach var="preferentialType" items="${preferentialTypeList}">
                                    <input class="vm" type="radio" name="activityArea.preferentialType" value="${preferentialType.value}"
                                           <c:if test="${empty activityArea && preferentialType.value==0}">checked="checked"</c:if>
                                           <c:if test="${activityArea.preferentialType==preferentialType.value}">checked="checked"</c:if>
                                           <c:if test="${preferentialType.value=='5'}">hidden="hidden"  </c:if>
                                           validate="{required:true}" onchange="changePreferentialType();" >
                                    <c:if test="${preferentialType.value!='5'}">
                                    ${preferentialType.name} </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:if>

                    <%--根据mchtType判断如果是spop是不展示满赠和多买优惠的--%>
                    <c:if test="${mchtInfo.mchtType==1 and  mchtInfo.isManageSelf !='1'}">
                        <tr id="tr_preferentialType">
                            <td class="editfield-label-td">促销方式</td>
                            <td colspan="2" class="text-left">
                                <c:forEach var="preferentialType" items="${preferentialTypeList}">
                                    <input class="vm" type="radio" name="activityArea.preferentialType" value="${preferentialType.value}"
                                    <c:if test="${empty activityArea && preferentialType.value==0}"> checked="checked"   </c:if>
                                    <c:if test="${activityArea.preferentialType==preferentialType.value}">  checked="checked"   </c:if>
                                           <c:if test="${preferentialType.value==3 ||preferentialType.value==4 ||preferentialType.value==5}">hidden="hidden"</c:if>
                                           validate="{required:true}" onchange="changePreferentialType();" >
                                    <c:if test="${preferentialType.value==1 ||preferentialType.value==2 ||preferentialType.value==0 }">
                                        ${preferentialType.name}
                                    </c:if>
                                </c:forEach>

                            </td>
                        </tr>
                    </c:if>


                    <tr id="couponDiv" class="hidden">
                        <td class="editfield-label-td">优惠券</td>
                        <td colspan="2" class="text-left">
                            <div class="container-fluid">
                                <c:if test="${empty couponList || couponList.size()==0}">
                                    <div class="row couponRule">
                                        面额<input type="text" size="6" name="couponList[][money]" value="" validate="{number:true,min:0, max:9999}">元
                                        &nbsp;&nbsp;&nbsp;使用条件订单满<input type="text" size="6" name="couponList[][minimum]" value="" validate="{number:true,min:0, max:9999}">元
                                        &nbsp;&nbsp;&nbsp;发行量<input type="text" size="6" name="couponList[][grantQuantity]" value="" validate="{digits:true,min:1, max:999999}">张
                                        <button type="button" class="btn btn-primary btn-xs" id="addCoupon">&nbsp;+&nbsp;</button>
                                    </div>
                                </c:if>
                                <c:if test="${couponList.size()>0}">
                                    <c:forEach var="coupon" items="${couponList}" varStatus="vs" >
                                        <div class="row couponRule">
                                            面额<input type="text" size="6" name="couponList[][money]" value="${coupon.money}" validate="{number:true,min:0, max:9999}">元
                                            &nbsp;&nbsp;&nbsp;使用条件<input type="text" size="6" name="couponList[][minimum]" value="${coupon.minimum}" validate="{number:true,min:0, max:9999}">元
                                            &nbsp;&nbsp;&nbsp;发行量<input type="text" size="6" name="couponList[][grantQuantity]" value="${coupon.grantQuantity}" validate="{digits:true,min:1, max:999999}">张
                                            <c:if test="${vs.index==0}">
                                                <button type="button" class="btn btn-primary btn-xs" id="addCoupon">&nbsp;+&nbsp;</button>
                                            </c:if>
                                            <c:if test="${vs.index>0}">
                                                <button type="button" class="btn btn-danger btn-xs delCoupon" onclick="delCoupon(this);">&nbsp;-&nbsp;</button>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <div id="template_coupon" class="hidden">
                                    <div class="row couponRule">
                                        面额<input type="text" size="6" name="couponList[][money]" value="" validate="{number:true,min:0, max:9999}">元
                                        &nbsp;&nbsp;&nbsp;使用条件<input type="text" size="6" name="couponList[][minimum]" value="" validate="{number:true,min:0, max:9999}">元
                                        &nbsp;&nbsp;&nbsp;发行量<input type="text" size="6" name="couponList[][grantQuantity]" value="" validate="{digits:true,min:1, max:999999}">张
                                        <button type="button" class="btn btn-danger btn-xs delCoupon" onclick="delCoupon(this);">&nbsp;-&nbsp;</button>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>

                    <tr id="fullCutDiv" class="hidden">
                        <td class="editfield-label-td">满减</td>
                        <td colspan="2" class="text-left">
                            <div class="container-fluid">
                                <div class="row">
                                    <input type="radio" class="vm" name="fullCut[sumFlag]"  value="1" <c:if test="${fullCut.sumFlag==1}">checked="checked"</c:if>>累加（上不封顶）
                                    <c:if test="${fullCut.sumFlag==1}">
                                        <input type="hidden" class="vm" name="fullCut[ladderFlag]"  value="0">
                                    </c:if>
                                    <c:if test="${empty fullCut.sumFlag || fullCut.sumFlag==0}">
                                        <input type="hidden" class="vm" name="fullCut[ladderFlag]"  value="1">
                                    </c:if>
                                </div>
                                <div class="row" style="margin-top: 5px;">
                                    订单满<input type="text" size="6" name="fullCut[minimum]" value="${fullCut.get("minimum")}" validate="{number:true,min:0}">元
                                    &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCut[money]" value="${fullCut.get("money")}" validate="{number:true,min:0}">元
                                </div>
                                <div class="row" style="margin-top: 5px;">
                                    <input type="radio" class="vm" name="fullCut[sumFlag]"  value="0" <c:if test="${empty fullCut.sumFlag || fullCut.sumFlag==0}">checked="checked"</c:if>>不累加
                                </div>
                                <c:if test="${empty fullCut || fullCut.ladderFlag==0}">
                                    <div class="row fullCutRule" style="margin-top: 5px;">
                                        订单满<input type="text" size="6" name="fullCutList[][minimum]" value="" validate="{number:true,min:0}">元
                                        &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCutList[][money]" value="" validate="{number:true,min:0}">元
                                        <button type="button" class="btn btn-primary btn-xs" id="addFullCutRule">&nbsp;+&nbsp;</button>
                                    </div>
                                </c:if>
                                <c:if test="${not empty fullCut && fullCut.ladderFlag==1 &&  fullCut.get('ruleList').size()>0}">
                                    <c:forEach var="rule" items="${fullCut.get('ruleList')}" varStatus="vs" >
                                        <div class="row fullCutRule" style="margin-top: 5px;">
                                            订单满<input type="text" size="6" name="fullCutList[][minimum]" value="${rule.minimum}" validate="{number:true,min:0}">元
                                            &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCutList[][money]" value="${rule.money}" validate="{number:true,min:0}">元
                                            <c:if test="${vs.index==0}">
                                                <button type="button" class="btn btn-primary btn-xs" id="addFullCutRule">&nbsp;+&nbsp;</button>
                                            </c:if>
                                            <c:if test="${vs.index>0}">
                                                <button type="button" class="btn btn-danger btn-xs delFullCutRule" onclick="delFullCutRule(this);">&nbsp;-&nbsp;</button>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </c:if>

                                <div id="template_fullCutRule" class="hidden">
                                    <div class="row fullCutRule" style="margin-top: 5px;">
                                        订单满<input type="text" size="6" name="fullCutList[][minimum]" value="" validate="{number:true,min:0}">元
                                        &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCutList[][money]" value="" validate="{number:true,min:0}">元
                                        <button type="button" class="btn btn-danger btn-xs delFullCutRule" onclick="delFullCutRule(this);">&nbsp;-&nbsp;</button>
                                    </div>
                                </div>

                            </div>
                        </td>
                    </tr>

                    <tr id="fullGiveDiv" class="hidden">
                        <td class="editfield-label-td">满赠</td>
                        <td colspan="2" class="text-left">
                            <div class="container-fluid">
                                <div class="row" style="margin-bottom: 10px;">
                                    <input style="margin-right: 3px;" class="vm" type="radio" <c:if test="${empty fullGive}">checked="checked"</c:if><c:if test="${fullGive.type==1}">checked="checked"</c:if> name="fullGive[type]" value="1" onchange="changeFullGiveType();">满额赠
                                    <input style="margin-left: 10px;margin-right: 3px;" class="vm" type="radio" <c:if test="${fullGive.type==2}">checked="checked"</c:if> name="fullGive[type]" value="2" onchange="changeFullGiveType();">买即赠（只送1份）
                                </div>
                                <div class="row" id="fullGive_type1" style="margin-bottom: 10px;">
                                    订单满 <input type="text" size="6" name="fullGive[minimum]" value="${fullGive.minimum}" validate="{number:true,min:0}"> 元，
                                    送<input type="text" size="6" name="fullGive[productNum]" value="${fullGive.productNum}" validate="{digits:true,min:0}">件&nbsp;&nbsp;&nbsp;
                                    <input style="margin-left: 10px;margin-right: 3px;" class="vm" type="checkbox" name="fullGive[sumFlag]" value="1" <c:if test="${fullGive.sumFlag==1}">checked="checked"</c:if>>累加成倍送（上不封顶）
                                </div>
                                <div class="row" style="margin-bottom: 10px;">
                                    <button style="vertical-align: baseline;margin-left: 7px;" type="button" class="btn btn-info" onclick="selectProduct();">选择赠品</button>
                                    <br>
                                    <input type="hidden" name="fullGive[productId]" value="${fullGive.productId}">
                                    赠品：
                                    <div class="no-check" <c:if test="${empty fullGive}">style="display: none;"</c:if> id="giftProductDiv">
                                        <div class="width-60">
                                            <img src="${ctx}/file_servelt${fullGive.get('productPic')}">
                                        </div>
                                        <div class="width-226">
                                            <p class="ellipsis" title="${fullGive.get('productName')}" id="productName">${fullGive.get('productName')}</p>
                                            <p id="productCode">ID:${fullGive.get('productCode')}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>
                    </tr>

                    <tr class="fullDiscountDiv hidden">
                        <td class="editfield-label-td">多买优惠</td>
                        <td colspan="2" class="text-left">
                            <input type="radio" class="vm" <c:if test="${empty fullDiscount}">checked="checked"</c:if><c:if test="${fullDiscount.type==1}">checked="checked"</c:if> name="fullDiscount[type]" value="1" onchange="changeFullDiscountType();">满M件减N件
                            <input type="radio" class="vm" <c:if test="${fullDiscount.type==2}">checked="checked"</c:if> name="fullDiscount[type]" value="2" onchange="changeFullDiscountType();">M件N元
                            <input type="radio" class="vm" <c:if test="${fullDiscount.type==3}">checked="checked"</c:if> name="fullDiscount[type]" value="3" onchange="changeFullDiscountType();">满M件N折
                        </td>
                    </tr>
                    <tr class="fullDiscountDiv hidden">
                        <td class="editfield-label-td">多买规则</td>
                        <td colspan="2" class="text-left">
                            <div class="container-fluid" style="padding-left: 10px;" id="fullDiscountDivRule">
                                <c:if test="${empty fullDiscount}">
                                    <div class="row fullDiscountRule">
                                        <span class="fullDiscount_text1">订单满</span><input style="margin-right: 3px;" type="text" size="6" name="fullDiscountList[][minimum]" value="" validate="{digits:true,min:0}"><span class="fullDiscount_text2">件</span>
                                        <span class="fullDiscount_text3">减</span><input style="margin: 0 3px 0 2px;" type="text" size="6" name="fullDiscountList[][money]" value="" validate="{number:true,min:0}"><span class="fullDiscount_text4">件</span>
                                        <button type="button" class="btn btn-primary btn-xs" style="margin-bottom: 1px;" id="addFullDiscountRule">&nbsp;+&nbsp;</button>
                                    </div>
                                </c:if>
                                <c:if test="${not empty fullDiscount && fullDiscount.get('ruleList').size()>0}">
                                    <c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
                                        <div class="row fullDiscountRule">
                                            <span class="fullDiscount_text1">订单满</span><input style="margin: 5px 3px 0 0;" type="text" size="6" name="fullDiscountList[][minimum]" value="${rule.minimum}" validate="{digits:true,min:0}"><span class="fullDiscount_text2">件</span>
                                            <span class="fullDiscount_text3">减</span><input style="margin: 0 3px 0 2px;" type="text" size="6" name="fullDiscountList[][money]" value="${rule.money}" validate="{number:true,min:0}"><span class="fullDiscount_text4">件</span>
                                            <c:if test="${vs.index==0}">
                                                <button type="button" class="btn btn-primary btn-xs" style="margin-bottom: 1px;" id="addFullDiscountRule">&nbsp;+&nbsp;</button>
                                            </c:if>
                                            <c:if test="${vs.index>0}">
                                                <button type="button" class="btn btn-danger btn-xs delFullDiscountRule" style="margin-bottom: 1px;" onclick="delFullDiscountRule(this);">&nbsp;-&nbsp;</button>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </c:if>

                                <div id="template_fullDiscountRule" class="hidden">
                                    <div class="row template_fullDiscountRule_add  fullDiscountRule">
                                        <span class="fullDiscount_text1">订单满</span><input style="margin: 5px 3px 0 0;" type="text" size="6" name="fullDiscountList[][minimum]" value="" validate="{digits:true,min:0}"><span class="fullDiscount_text2">件</span>
                                        <span class="fullDiscount_text3">减</span><input style="margin: 0 3px 0 2px;" type="text" size="6" name="fullDiscountList[][money]" value="" validate="{number:true,min:0}"><span class="fullDiscount_text4">件</span>
                                        <button type="button" class="btn btn-danger btn-xs delFullDiscountRule" style="margin-bottom: 1px;" onclick="delFullDiscountRule(this);">&nbsp;-&nbsp;</button>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </form>

        <div class="modal-footer">
            <button class="btn btn-info" onclick="commitSave();">提交</button>
        </div>
    </div>
</div>

<!-- <div class="modal-dialog" role="document" style="width:900px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">
                <c:if test="${empty activity}">
                    添加品牌特卖
                </c:if>
                <c:if test="${not empty activity}">
                    查看品牌特卖
                </c:if>
            </span>
        </div>
        <div class="modal-body">
            <c:if test="${not empty activity && activity.status==6}">
                <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <tr>
                        <td rowspan="2" class="text-center" style="vertical-align:middle;">活动排期</td>
                        <td>活动预热时间</td>
                        <td>活动开始时间</td>
                        <td>活动结束时间</td>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${not empty activityArea.preheatTime}">
                                <fmt:formatDate value='${activityArea.preheatTime}' pattern='yyyy-MM-dd hh:mm:ss'/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not empty activityArea.activityBeginTime}">
                                <fmt:formatDate value='${activityArea.activityBeginTime}' pattern='yyyy-MM-dd hh:mm:ss'/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not empty activityArea.activityEndTime}">
                                <fmt:formatDate value='${activityArea.activityEndTime}' pattern='yyyy-MM-dd hh:mm:ss'/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${not empty activity && activity.status==4}">
                <div class="dv1">
                    驳回原因: <span style="color: #F00000">${activity.operateAuditRemarks}</span>
                </div>
            </c:if>

            <form id="dataFrom">
                <input type="hidden" value="${activityArea.id}" name="activityArea.id">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">活动名称</td>
                            <td colspan="2" class="text-left">
                                <input type="text" id="name" name="activityArea.name" value="${activityArea.name}"
                                       validate="{required:true}" >
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">类目</td>
                            <td colspan="2" class="text-left">
                                <select name="activity.productTypeId" id="productTypeId" validate="{required:true}">
                                    <option value="">--请选择--</option>
                                    <c:forEach var="productType" items="${productTypeList}">
                                        <option value="${productType.id}"
                                                <c:if test="${activity.productTypeId==productType.id}">selected</c:if>>${productType.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">品牌</td>
                            <td colspan="2" class="text-left">
                                <select name="activity.productBrandId" id="productBrandId" validate="{required:true}">
                                    <option value="">--请选择--</option>
                                    <c:forEach var="productBrand" items="${productBrandList}">
                                        <option value="${productBrand.id}"
                                                <c:if test="${activity.productBrandId==productBrand.id}">selected</c:if>>${productBrand.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">利益点</td>
                            <td colspan="2" class="text-left">
                                <input type="text" id="benefitPoint" name="activityArea.benefitPoint"
                                       value="${activityArea.benefitPoint}" validate="{required:true}">
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">期望活动时间</td>
                            <td colspan="2" class="text-left">
                                <input type="text" validate="{required:true}"
                                       value="<fmt:formatDate value='${activity.expectedStartTime}' pattern='yyyy-MM-dd'/>"
                                       id="expectedStartTime" name="activity.expectedStartTime" data-date-format="yyyy-mm-dd">
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">入口图</td>
                            <td colspan="2" class="text-left padding-10">
                                <div class="single_pic_picker">
                                    <input type="hidden" name="activity.entryPic" value="${activity.entryPic}"/>
                                    <input id="activity.entryPicFile" onchange="loadImageFile(this)" type="file">
                                    <c:if test='${activity.entryPic != null && activity.entryPic != ""}'>
                                    <img src="${ctx}/file_servelt${activity.entryPic}" width="160px" height="80px">
                                    </c:if>
                                    <c:if test='${activity.entryPic==null||activity.entryPic==""}'>
                                    <div>+</div>
                                    </c:if>
                                </div>
                                <div>图片尺寸宽800*400px，图片大小为100KB以内</div>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">头部海报</td>
                            <td colspan="2" class="text-left padding-10">
                                <div class="single_pic_picker">
                                    <input type="hidden" name="activity.posterPic" value="${activity.posterPic}"/>
                                    <input id="activity.posterPicFile" onchange="loadImageFile(this)" type="file">
                                    <c:if test='${activity.posterPic != null && activity.posterPic != ""}'>
                                        <img src="${ctx}/file_servelt${activity.posterPic}" width="160" height="80" id="preview_header">
                                    </c:if>
                                    <c:if test='${activity.posterPic==null||activity.posterPic==""}'>
                                        <div>+</div>
                                    </c:if>
                                </div>
                                <div>图片尺寸宽800*400px，图片大小为100KB以内</div>
                            </td>
                        </tr>

                        <tr id="tr_preferentialType">
                            <td class="editfield-label-td">促销方式</td>
                            <td colspan="2" class="text-left">
                            <c:forEach var="preferentialType" items="${preferentialTypeList}">
                                <input class="vm" type="radio" name="activityArea.preferentialType" value="${preferentialType.value}"
                                       <c:if test="${empty activityArea && preferentialType.value==0}">checked="checked"</c:if>
                                       <c:if test="${activityArea.preferentialType==preferentialType.value}">checked="checked"</c:if>
                                       validate="{required:true}" onchange="changePreferentialType();">${preferentialType.name}
                            </c:forEach>
                            </td>
                        </tr>

                        <tr id="couponDiv" class="hidden">
                            <td class="editfield-label-td">优惠券</td>
                            <td colspan="2" class="text-left">
                                <div class="container-fluid">
                                    <c:if test="${empty couponList || couponList.size()==0}">
                                    <div class="row couponRule">
                                        面额<input type="text" size="6" name="couponList[][money]" value="" validate="{number:true,min:0, max:9999}">元
                                        &nbsp;&nbsp;&nbsp;使用条件<input type="text" size="6" name="couponList[][minimum]" value="" validate="{number:true,min:0, max:9999}">元
                                        &nbsp;&nbsp;&nbsp;发行量<input type="text" size="6" name="couponList[][grantQuantity]" value="" validate="{digits:true,min:1, max:999999}">张
                                        <button type="button" class="btn btn-primary btn-xs" id="addCoupon">&nbsp;+&nbsp;</button>
                                    </div>
                                    </c:if>
                                    <c:if test="${couponList.size()>0}">
                                    <c:forEach var="coupon" items="${couponList}" varStatus="vs" >
                                        <div class="row couponRule">
                                            面额<input type="text" size="6" name="couponList[][money]" value="${coupon.money}" validate="{number:true,min:0, max:9999}">元
                                            &nbsp;&nbsp;&nbsp;使用条件<input type="text" size="6" name="couponList[][minimum]" value="${coupon.minimum}" validate="{number:true,min:0, max:9999}">元
                                            &nbsp;&nbsp;&nbsp;发行量<input type="text" size="6" name="couponList[][grantQuantity]" value="${coupon.grantQuantity}" validate="{digits:true,min:1, max:999999}">张
                                            <c:if test="${vs.index==0}">
                                                <button type="button" class="btn btn-primary btn-xs" id="addCoupon">&nbsp;+&nbsp;</button>
                                            </c:if>
                                            <c:if test="${vs.index>0}">
                                                <button type="button" class="btn btn-danger btn-xs delCoupon" onclick="delCoupon(this);">&nbsp;-&nbsp;</button>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                    </c:if>
                                    <div id="template_coupon" class="hidden">
                                        <div class="row couponRule">
                                            面额<input type="text" size="6" name="couponList[][money]" value="" validate="{number:true,min:0, max:9999}">元
                                            &nbsp;&nbsp;&nbsp;使用条件<input type="text" size="6" name="couponList[][minimum]" value="" validate="{number:true,min:0, max:9999}">元
                                            &nbsp;&nbsp;&nbsp;发行量<input type="text" size="6" name="couponList[][grantQuantity]" value="" validate="{digits:true,min:1, max:999999}">张
                                            <button type="button" class="btn btn-danger btn-xs delCoupon" onclick="delCoupon(this);">&nbsp;-&nbsp;</button>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <tr id="fullCutDiv" class="hidden">
                            <td class="editfield-label-td">满减额度</td>
                            <td colspan="2" class="text-left">
                                <div class="container-fluid">
                                    <div class="row">
                                        非阶梯<input type="radio" class="vm" name="fullCut[ladderFlag]"  value="0" <c:if test="${fullCut.ladderFlag==0}">checked="checked"</c:if>>
                                    </div>
                                    <div class="row">
                                        满<input type="text" size="6" name="fullCut[minimum]" value="${fullCut.get("minimum")}" validate="{number:true,min:0}">元
                                        &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCut[money]" value="${fullCut.get("money")}" validate="{number:true,min:0}">元
                                        <input type="checkbox" name="fullCut[sumFlag]" value="1" <c:if test="${fullCut.sumFlag==1}">checked="checked"</c:if>>累加
                                    </div>
                                    <div class="row">
                                        阶梯<input type="radio" class="vm" name="fullCut[ladderFlag]"  value="1" <c:if test="${fullCut.ladderFlag==1}">checked="checked"</c:if>>
                                    </div>
                                    <c:if test="${empty fullCut || fullCut.ladderFlag==0}">
                                        <div class="row fullCutRule">
                                            满<input type="text" size="6" name="fullCutList[][minimum]" value="" validate="{number:true,min:0}">元
                                            &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCutList[][money]" value="" validate="{number:true,min:0}">元
                                            <button type="button" class="btn btn-primary btn-xs" id="addFullCutRule">&nbsp;+&nbsp;</button>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty fullCut && fullCut.ladderFlag==1 &&  fullCut.get('ruleList').size()>0}">
                                        <c:forEach var="rule" items="${fullCut.get('ruleList')}" varStatus="vs" >
                                            <div class="row fullCutRule">
                                                满<input type="text" size="6" name="fullCutList[][minimum]" value="${rule.minimum}" validate="{number:true,min:0}">元
                                                &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCutList[][money]" value="${rule.money}" validate="{number:true,min:0}">元
                                                <c:if test="${vs.index==0}">
                                                    <button type="button" class="btn btn-primary btn-xs" id="addFullCutRule">&nbsp;+&nbsp;</button>
                                                </c:if>
                                                <c:if test="${vs.index>0}">
                                                    <button type="button" class="btn btn-danger btn-xs delFullCutRule" onclick="delFullCutRule(this);">&nbsp;-&nbsp;</button>
                                                </c:if>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                    <div id="template_fullCutRule" class="hidden">
                                        <div class="row fullCutRule">
                                            满<input type="text" size="6" name="fullCutList[][minimum]" value="" validate="{number:true,min:0}">元
                                            &nbsp;&nbsp;&nbsp;减<input type="text" size="6" name="fullCutList[][money]" value="" validate="{number:true,min:0}">元
                                            <button type="button" class="btn btn-danger btn-xs delFullCutRule" onclick="delFullCutRule(this);">&nbsp;-&nbsp;</button>
                                        </div>
                                    </div>

                                </div>
                            </td>
                        </tr>

                        <tr id="fullGiveDiv" class="hidden">
                            <td class="editfield-label-td">满赠类型</td>
                            <td colspan="2" class="text-left">
                                <div class="container-fluid">
                                    <div class="row" style="margin-bottom: 10px;">
                                        <input style="margin-right: 3px;" class="vm" type="radio" <c:if test="${empty fullGive}">checked="checked"</c:if><c:if test="${fullGive.type==1}">checked="checked"</c:if> name="fullGive[type]" value="1" onchange="changeFullGiveType();">满额赠
                                        <input style="margin-left: 10px;margin-right: 3px;" class="vm" type="radio" <c:if test="${fullGive.type==2}">checked="checked"</c:if> name="fullGive[type]" value="2" onchange="changeFullGiveType();">买即赠
                                    </div>
                                    <div class="row" id="fullGive_type1" style="margin-bottom: 10px;">
                                        满 <input type="text" size="6" name="fullGive[minimum]" value="${fullGive.minimum}" validate="{number:true,min:0}"> 元
                                        <input style="margin-left: 10px;margin-right: 3px;" class="vm" type="checkbox" name="fullGive[sumFlag]" value="1" <c:if test="${fullGive.sumFlag==1}">checked="checked"</c:if>>累加
                                    </div>
                                    <div class="row" style="margin-bottom: 10px;">
                                        <input type="hidden" name="fullGive[productId]" value="${fullGive.productId}">
                                        赠品选择：<input type="text" size="32" name="fullGive[productName]" value="${fullGive.get('productName')}" readonly="readonly">
                                        <button style="vertical-align: baseline;margin-left: 7px;" type="button" class="btn btn-info" onclick="selectProduct();">选择商品</button>
                                    </div>
                                    <div class="row">
                                        数量：<input type="text" size="6" name="fullGive[productNum]" value="${fullGive.productNum}" validate="{digits:true,min:0}">
                                    </div>
                                </div>

                            </td>
                        </tr>

                        <tr class="fullDiscountDiv hidden">
                            <td class="editfield-label-td">多买类型</td>
                            <td colspan="2" class="text-left">
                                <input type="radio" class="vm" <c:if test="${empty fullDiscount}">checked="checked"</c:if><c:if test="${fullDiscount.type==1}">checked="checked"</c:if> name="fullDiscount[type]" value="1" onchange="changeFullDiscountType();">满M件减N件
                                <input type="radio" class="vm" <c:if test="${fullDiscount.type==2}">checked="checked"</c:if> name="fullDiscount[type]" value="2" onchange="changeFullDiscountType();">M件N元
                                <input type="radio" class="vm" <c:if test="${fullDiscount.type==3}">checked="checked"</c:if> name="fullDiscount[type]" value="3" onchange="changeFullDiscountType();">M件N折
                            </td>
                        </tr>
                        <tr class="fullDiscountDiv hidden">
                            <td class="editfield-label-td">多买规则</td>
                            <td colspan="2" class="text-left">
                                <div class="container-fluid" style="padding-left: 10px;" id="fullDiscountDivRule">
                                    <c:if test="${empty fullDiscount}">
                                        <div class="row fullDiscountRule">
                                            <span class="fullDiscount_text1">满</span><input style="margin-right: 3px;" type="text" size="6" name="fullDiscountList[][minimum]" value="" validate="{digits:true,min:0}"><span class="fullDiscount_text2">件</span>
                                            <span class="fullDiscount_text3">减</span><input style="margin: 0 3px 0 2px;" type="text" size="6" name="fullDiscountList[][money]" value="" validate="{number:true,min:0}"><span class="fullDiscount_text4">件</span>
                                            <button type="button" class="btn btn-primary btn-xs" style="margin-bottom: 1px;" id="addFullDiscountRule">&nbsp;+&nbsp;</button>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty fullDiscount && fullDiscount.get('ruleList').size()>0}">
                                        <c:forEach var="rule" items="${fullDiscount.get('ruleList')}" varStatus="vs" >
                                            <div class="row fullDiscountRule">
                                                <span class="fullDiscount_text1">满</span><input style="margin: 5px 3px 0 0;" type="text" size="6" name="fullDiscountList[][minimum]" value="${rule.minimum}" validate="{digits:true,min:0}"><span class="fullDiscount_text2">件</span>
                                                <span class="fullDiscount_text3">减</span><input style="margin: 0 3px 0 2px;" type="text" size="6" name="fullDiscountList[][money]" value="${rule.money}" validate="{number:true,min:0}"><span class="fullDiscount_text4">件</span>
                                                <c:if test="${vs.index==0}">
                                                    <button type="button" class="btn btn-primary btn-xs" style="margin-bottom: 1px;" id="addFullDiscountRule">&nbsp;+&nbsp;</button>
                                                </c:if>
                                                <c:if test="${vs.index>0}">
                                                    <button type="button" class="btn btn-danger btn-xs delFullDiscountRule" style="margin-bottom: 1px;" onclick="delFullDiscountRule(this);">&nbsp;-&nbsp;</button>
                                                </c:if>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                    <div id="template_fullDiscountRule" class="hidden">
                                    <div class="row template_fullDiscountRule_add  fullDiscountRule">
                                        <span class="fullDiscount_text1">满</span><input style="margin: 5px 3px 0 0;" type="text" size="6" name="fullDiscountList[][minimum]" value="" validate="{digits:true,min:0}"><span class="fullDiscount_text2">件</span>
                                        <span class="fullDiscount_text3">减</span><input style="margin: 0 3px 0 2px;" type="text" size="6" name="fullDiscountList[][money]" value="" validate="{number:true,min:0}"><span class="fullDiscount_text4">件</span>
                                        <button type="button" class="btn btn-danger btn-xs delFullDiscountRule" style="margin-bottom: 1px;" onclick="delFullDiscountRule(this);">&nbsp;-&nbsp;</button>
                                    </div>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
                <%--<c:if test="${empty activity || activity.status==1 || activity.status==2 || activity.status==4}">--%>
                <c:if test="${empty activity || activity.status==1 || activity.status==4}">
                    <button class="btn btn-info" onclick="commitSave();">下一步（添加活动商品）</button>
                    <button class="btn btn-info" data-dismiss="modal">取消</button>
                </c:if>
                <%--<c:if test="${not empty activity && (activity.status==3 || activity.status==6)}">--%>
                <c:if test="${not empty activity && (activity.status==2 || activity.status==3 || activity.status==6)}">
                    <c:if test="${not empty auth}">
                        <button class="btn btn-info" onclick="commitSave();">下一步（添加活动商品）</button>
                    </c:if>
                    <c:if test="${empty auth}">
                        <button class="btn btn-info" onclick="modalListProduct(${activity.id}, 2);">查看活动商品</button>
                    </c:if>
                    <button class="btn btn-info" data-dismiss="modal">返回活动列表</button>
                </c:if>

                <button class="btn btn-info" id="btn_preview">预览活动列表</button>
                <%--<c:if test="${not empty activity}">--%>
                    <%--<button class="btn btn-info" onclick="modalListProduct(${activity.id}, 2);">查看活动商品</button>--%>
                <%--</c:if>--%>
            </div>
        </div>
    </div>
</div> -->

<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal2" tabindex="1" role="dialog" aria-labelledby="productModalLabel"
     data-backdrop="static">
</div>

<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.serializejson.js" type="text/javascript"></script>

<script type="text/javascript">
    var dataFromValidate;
    $(function () {
    	$("input[name='fullCut[sumFlag]']").on('click',function(){
    		var sumFlag = $(this).val();
    		if(sumFlag == "0"){
    			$("input[name='fullCut[ladderFlag]']").val(1);
    		}else if(sumFlag == "1"){
    			$("input[name='fullCut[ladderFlag]']").val(0);
    		}
    	});

        <c:if test="${readonly}">
        disableForm("dataFrom");
        </c:if>

        <c:if test="${!readonly ||(not empty auth && auth.activityPreferentialFlag==1)}">
        $("#addCoupon").click(function(){
            if($("#couponDiv .couponRule").length < 4){
                $(this).parent().parent().append($("#template_coupon").html());
            }

        });
        $("#addFullCutRule").click(function(){
            if($("#fullCutDiv .fullCutRule").length < 4){
                $(this).parent().parent().append($("#template_fullCutRule").html());
            }

        });
        $("#addFullDiscountRule").click(function(){
            if($("#fullDiscountDivRule .fullDiscountRule").length < 4){
                $(this).parent().parent().append($("#template_fullDiscountRule").html());
            }

        });
        </c:if>

        $.metadata.setType("attr", "validate");
        dataFromValidate = $("#dataFrom").validate({
            highlight: function (element) {
                $(element).addClass('error');
                $(element).closest('tr').find("td").first().addClass('has-error');
            },
            success: function (label) {
                label.closest('tr').find("td").first().removeClass('has-error');
            },
            errorPlacement: function (error, element) {
                error.appendTo($(element).closest('td'));
            }
        });

        $('#expectedStartTime').datetimepicker({
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
            language: 'zh-CN', //汉化
            autoclose:true //选择日期后自动关闭
        });

        $("#productTypeId").on('change',function(){
            var productTypeId = $(this).val();
            if(!productTypeId){
                return false;
            }else{
                $.ajax({
                    url: '${ctx}/mcht/activity/getSecondProductType',
                    type: "POST",
                    data : {productTypeId:productTypeId},
                    async: false,
                    success: function (data) {
                        var productTypes = data.list;
                        var html = [];
                        for(var i=0;i<productTypes.length;i++){
                            var productTypeSecondId = productTypes[i].productTypeSecondId;
                            var name = productTypes[i].name;
                            var optionStr = '<option value="'+productTypeSecondId+'">'+name+'</option>';
                            html.push(optionStr);
                        }
                        $("#productTypeSecondId").empty();
                        $("#productTypeSecondId").append("<option>--请选择--</option>");
                        $("#productTypeSecondId").append(html.join(""));
                    },
                    error: function (data) {
                        swal("获取二级类目失败，请稍后重试");
                    }
                });
            }
        });

        changePreferentialType();
        changeFullGiveType();
        changeFullDiscountType();


    });


    function commitSave() {
        enableForm("dataFrom");
        if (dataFromValidate.form()) {
            if($("#productTypeSecondId").val() == "--请选择--"){
                swal("请选择二级类目");
                return;
            }
            var brandTeamPic = $("input[name='activity.brandTeamPic']");
            var brandTeamPics = brandTeamPic.parent().children("img");
            if(brandTeamPics.length<=0){
                swal({
                    title: '请上传品牌团入口图',
                    type: "error",
                    confirmButtonText: "确定"
                });
                return;
            }
           /* if("${activity.brandTeamPic}"=="" || brandTeamPics.attr("src").indexOf("${activity.brandTeamPic}") < 0){//有修改
                uploadImg("activity.brandTeamPicFile", brandTeamPic);
            }*/

            var entryPic = $("input[name='activity.entryPic']");
            var entryPics = entryPic.parent().children("img");
            if(entryPics.length<=0){
                swal({
                    title: '请上传入口图',
                    type: "error",
                    confirmButtonText: "确定"
                });
                return;
            }
           /* if("${activity.entryPic}"=="" || entryPics.attr("src").indexOf("${activity.entryPic}") < 0){//有修改
                uploadImg("activity.entryPicFile", entryPic);
            }*/

            var posterPic = $("input[name='activity.posterPic']");
            var posterPics = posterPic.parent().children("img");
            if(posterPics.length<=0){
                swal({
                    title: '请上传头部海报',
                    type: "error",
                    confirmButtonText: "确定"
                });
                return;
            }
          /*  if("${activity.posterPic}"=="" || posterPics.attr("src").indexOf("${activity.posterPic}") < 0){//有修改
                uploadImg("activity.posterPicFile", posterPic);
            }*/
            var checked = $("#agree").prop("checked");
            if(checked){
                $("#preSellAuditStatus").val("1");
            }else{
                $("#preSellAuditStatus").val("0");
            }
            var dataJson = $("#dataFrom").serializeArray()
            dataJson.push({"name":"json", "value":JSON.stringify($("#dataFrom").find(':input').filter(function () {
                    return $.trim(this.value).length > 0
                }).serializeJSON())});

            $.ajax({
                url: "${ctx}/mcht/activity/save",
                type: 'POST',
                //ontentType : 'application/json;charset=utf-8', //设置请求头信息
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
//                        $("#viewModal").modal('hide');
//                        table.ajax.reload();

//                        swal({
//                            title: "提交成功!",
//                            type: "success",
//                            confirmButtonText: "确定"
//
//                        });

//                        modalListProduct(result.data.activity.id, 2);
                        getContent("${ctx}/mcht/activity/view?id="+result.data.activityArea.id);
                    } else {
                        swal({
                            title: result.returnMsg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }

                },
                error: function () {
                    swal({
                        title: "提交失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            });
        }

    }

    function changePreferentialType(){
        var preferentialType = $("input[name='activityArea.preferentialType']:checked").val();
        if(preferentialType == 0) {
            $("#couponDiv").addClass("hidden");
            $("#fullCutDiv").addClass("hidden");
            $("#fullGiveDiv").addClass("hidden");
            $(".fullDiscountDiv").addClass("hidden");
        }else if(preferentialType == 1){
            $("#couponDiv").removeClass("hidden");

            $("#fullCutDiv").addClass("hidden");
            $("#fullGiveDiv").addClass("hidden");
            $(".fullDiscountDiv").addClass("hidden");
        }else if(preferentialType == 2){
            $("#fullCutDiv").removeClass("hidden");

            $("#couponDiv").addClass("hidden");
            $("#fullGiveDiv").addClass("hidden");
            $(".fullDiscountDiv").addClass("hidden");
        }else if(preferentialType == 3){
            $("#fullGiveDiv").removeClass("hidden");

            $("#couponDiv").addClass("hidden");
            $("#fullCutDiv").addClass("hidden");
            $(".fullDiscountDiv").addClass("hidden");
        }else if(preferentialType == 4){
            $(".fullDiscountDiv").removeClass("hidden");

            $("#couponDiv").addClass("hidden");
            $("#fullCutDiv").addClass("hidden");
            $("#fullGiveDiv").addClass("hidden");
        }
    }

    function changeFullGiveType(){
        var type = $("input[name='fullGive[type]']:checked").val();
        if(type == 1) {
            $("#fullGive_type1").removeClass("hidden");
        }else if(type == 2){
            $("#fullGive_type1").addClass("hidden");
        }
    }

    function changeFullDiscountType(){
        var discountType = $("input[name='fullDiscount[type]']:checked").val();
        if(discountType == 1) {
            $(".fullDiscount_text1").text("");
            $(".fullDiscount_text2").text("件");
            $(".fullDiscount_text3").text("减");
            $(".fullDiscount_text4").text("件");

            $("#addFullDiscountRule").addClass("hidden");
            $("#fullDiscountDivRule > .template_fullDiscountRule_add").remove();
        }else if(discountType == 2){
            $(".fullDiscount_text1").text("");
            $(".fullDiscount_text2").text("件");
            $(".fullDiscount_text3").text("");
            $(".fullDiscount_text4").text("元");

            $("#addFullDiscountRule").addClass("hidden");
            $("#fullDiscountDivRule > .template_fullDiscountRule_add").remove();
        }else if(discountType == 3){
            $(".fullDiscount_text1").text("");
            $(".fullDiscount_text2").text("件");
            $(".fullDiscount_text3").text("");
            $(".fullDiscount_text4").text("折");

            $("#addFullDiscountRule").removeClass("hidden");

            $("input[name='fullDiscountList[][money]'").attr("validate", "{number:true,min:0.1,max:9.9}");
        }
    }

    <c:if test="${!readonly ||(not empty auth && auth.activityPreferentialFlag==1)}">
    function delCoupon(obj){
        $(obj).parent().remove();
    }
    function delFullCutRule(obj){
        $(obj).parent().remove();
    }
    function delFullDiscountRule(obj){
        $(obj).parent().remove();
    }

    function selectProduct() {
        var url = "${ctx}/product/select";
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                $("#viewModal2").html(data);
                $("#viewModal2").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    function chooseProductAndClose(id, name,code,pic) {
        $('#viewModal2').modal('hide');
        $("input[name='fullGive[productId]']").val(id);
        var basePath = $("#basePath").val();
        $("#giftProductDiv").find("img").prop("src",basePath+"/file_servelt"+pic);
        $("#productName").text(name);
        $("#productCode").text("ID:"+code);
        $("#giftProductDiv").show();
//      $("input[name='fullGive[productName]']").val(name);
    }

    function closeViewModal2() {
        $('#viewModal2').modal('hide');
    }
    </c:if>



    //禁用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]
    function disableForm(formId) {
        $("#"+formId+ " :text").attr("disabled","disabled");
        $("#"+formId+ " textarea").attr("disabled","disabled");
        $("#"+formId+ " select").attr("disabled","disabled");
        $("#"+formId+ " :radio").attr("disabled","disabled");
        $("#"+formId+ " :checkbox").attr("disabled","disabled");

        <c:if test="${not empty auth}">
        <c:if test="${auth.activityNameFlag==1}">
        $("input[name='activityArea.name']").removeAttr("disabled");
        </c:if>
        <c:if test="${auth.activityTypeFlag==1}">
        $("select[name='activity.productTypeId']").removeAttr("disabled");
        </c:if>
        <c:if test="${auth.activityBrandFlag==1}">
        $("select[name='activity.productBrandId']").removeAttr("disabled");
        </c:if>
        <c:if test="${auth.activityBenefitFlag==1}">
        $("input[name='activityArea.benefitPoint']").removeAttr("disabled");
        </c:if>
        <c:if test="${auth.activityEntryFlag==1}">
        $("input[name='activity.entryPic']").removeAttr("disabled");
        </c:if>
        <c:if test="${auth.activityPosterFlag==1}">
        $("input[name='activity.posterPic']").removeAttr("disabled");
        </c:if>
        <c:if test="${auth.activityPreferentialFlag==1}">
        $("input[name='activityArea.preferentialType']").removeAttr("disabled");
        enableForm("couponDiv");
        enableForm("fullCutDiv");
        enableForm("fullGiveDiv");
        enableFormByClass("fullDiscountDiv");
        </c:if>
        </c:if>
    }

    function enableForm(id) {
        $("#"+id+ " :text").removeAttr("disabled");
        $("#"+id+ " textarea").removeAttr("disabled");
        $("#"+id+ " select").removeAttr("disabled");
        $("#"+id+ " :radio").removeAttr("disabled");
        $("#"+id+ " :checkbox").removeAttr("disabled");
    }
    function enableFormByClass(id) {
        $("."+id+ " :text").removeAttr("disabled");
        $("."+id+ " textarea").removeAttr("disabled");
        $("."+id+ " select").removeAttr("disabled");
        $("."+id+ " :radio").removeAttr("disabled");
        $("."+id+ " :checkbox").removeAttr("disabled");
    }








    //上传图片
    function uploadImg($img,$valueFiled) {
        var oFile = document.getElementById($img).files[0];
        var formData = new FormData();
        formData.append("file",oFile);
        $.ajax({
            url : "${ctx}/common/fileUpload?fileType=3",
            type : 'POST',
            data : formData,
            async: false,
            // 告诉jQuery不要去处理发送的数据
            processData : false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType : false,
            beforeSend:function(){
                console.log("图片片上传中，请稍候");
            },
            success : function(data) {
                if (data.returnCode=="0000") {
                    $valueFiled.val(data.returnData);
                } else {
                    swal({
                        title: "图片上传失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            },
            error : function(responseStr) {
                swal("图片上传失败");
            }
        });


    }


    function loadImageFile(obj, flagFile) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        if(oFile.size>100*1024){
            swal("图片大小不能超过100K");
            return;
        }
        var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
        if (!rFilter.test(oFile.type)) {
            swal("请选择图片文件");
            return;
        }
        if($(obj).parent().children("img").length<=0){
            $('<img>').appendTo( $(obj).parent() );;
        }

        var oFReader = new FileReader();
        oFReader.onload = function(oFREvent) {
            var img=new Image();
            img.src=oFREvent.target.result;
            img.onload=function(){
                if(flagFile == 'brandTeamPicFile' && (img.width != 1080 || img.height != 335) ) {
                    document.getElementById("activity."+flagFile).setAttribute("type", "text");
                    document.getElementById("activity."+flagFile).setAttribute("type", "file");
                    swal("请选择尺寸为1080*335的图片");
                    return;
                }else if((flagFile == 'entryPicFile' || flagFile == 'posterPicFile') && (img.width != 800 || img.height != 400) ) {
                    document.getElementById("activity."+flagFile).setAttribute("type", "text");
                    document.getElementById("activity."+flagFile).setAttribute("type", "file");
                    swal("请选择尺寸为800*400的图片");
                    return;
                }else{
                    $(obj).parent().children("img").attr("src",oFREvent.target.result);
                    $(obj).parent().children("div").remove();

                    if(flagFile == 'brandTeamPicFile'){
                        var brandTeamPic = $("input[name='activity.brandTeamPic']");
                        uploadImg("activity.brandTeamPicFile", brandTeamPic);
                    }
                    if(flagFile == 'entryPicFile'){
                        var entryPic = $("input[name='activity.entryPic']");
                        uploadImg("activity.entryPicFile", entryPic);
                    }
                    if(flagFile == 'posterPicFile'){
                        var posterPic = $("input[name='activity.posterPic']");
                        uploadImg("activity.posterPicFile", posterPic);
                    }

                }
            }

        };
        oFReader.readAsDataURL(oFile);
    }

    function modalListProduct(activityId, status) {
        // $('#viewModal').on('hidden.bs.modal', function (e) {
        //     var url = "${ctx}/mcht/activity/listProductPage?activityId=" + activityId + "&status=" + status;
        //     getContent(url);
        // })

        // $("#viewModal").modal('hide');
        var url = "${ctx}/mcht/activity/listProductPage?activityId=" + activityId + "&status=" + status;
        getContent(url);
    }

    function toAgreement(){
        var url = "${ctx}/subDepositOrder/toAgreement";
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                $("#viewModal2").html(data);
                $("#viewModal2").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }
</script>
</body>
</html>
