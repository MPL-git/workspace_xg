<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>寄件内容</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
    <style type="text/css">
        .td-pictures li{
            display: inline-block;
        }
        .td-pictures li img{
            width: 40px;
            height: 40px;
        }

        .td_title{
            font-weight: normal;
            background-color: #cccccc;
            height:22px;
            border-bottom: 0 !important;
        }

        .text-right{
            width:150px;
        }
    </style>
</head>

<body>

<div class="modal-dialog" role="document" style="width:900px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">寄件内容</span>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
            	<div>
		     	 	寄件地址<br>
					收  件  人：招商<br>
					联系电话：4008088227<br>
					收件地址：厦门市思明区塔埔东路171号1104-A单元<br>
     	 		</div>
     	 		<br>
     	 		<div class="col-md-12">入驻合同<a href="javascript:showInfo('${ctx}/mcht/contract/contractPreview')">[预览合同，打印合同]</a></div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <td class="td_title text-right">项目</td>
                        <td class="td_title text-left">合同一式两份打印下来按照要求盖章（首尾页用黑色签字填写相关信息，后首尾页加盖公章），两份合同及相关资质都要寄回</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="text-right">入驻合同</td>
                        <td class="text-left">
                            <div class="pic-container">
                            <ul class="docs-pictures clearfix td-pictures pictures-list">
                                <c:forEach var="mchtContractPic" items="${mchtContractPicList}">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtContractPic.pic}" onclick="viewerPic(this.src,this)">
                                    </li>
                                </c:forEach>
                            </ul>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
				<div class="col-md-12">公司资质信息<a href="javascript:;" onclick="downLoadMchtInfoPic(${mchtInfo.id})">[下载图片]</a></div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <td class="td_title text-right">项目</td>
                        <td class="td_title text-left">资质文件如是复印件需全部加盖公章。且保证文件内容清晰、章印清楚</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="text-right">法人身份证</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg1}" onclick="viewerPic(this.src,this)">
                                    </li>
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg2}" onclick="viewerPic(this.src,this)">
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <c:if test="${not empty mchtBlPics}">
                    <tr>
                        <td class="text-right">营业执照副本</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                <c:forEach var="mchtBlPic" items="${mchtBlPics}">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtBlPic.pic}" onclick="viewerPic(this.src,this)">
                                    </li>
                                </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtInfo.occPic}">
                    <tr>
                        <td class="text-right">组织机构代码证：</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtInfo.occPic}" onclick="viewerPic(this.src,this)">
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtInfo.trcPic}">
                    <tr>
                        <td class="text-right">税务登记证</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtInfo.trcPic}" onclick="viewerPic(this.src,this)">
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtInfo.atqPic}">
                    <tr>
                        <td class="text-right">一般纳税人资格证</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtInfo.atqPic}" onclick="viewerPic(this.src,this)">
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtInfo.boaalPic}">
                    <tr>
                        <td class="text-right">银行开户许可证</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <li class="pic_li">
                                        <img  src="${ctx}/file_servelt${mchtInfo.boaalPic}" onclick="viewerPic(this.src,this)">
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtTaxInvoiceInfoConfirmFile}">
                        <tr>
                            <td class="text-right">税务信息确认函：</td>
                            <td class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                        <li class="pic_li">
                                            <img  src="${ctx}/file_servelt${mchtTaxInvoiceInfoConfirmFile}" onclick="viewerPic(this.src,this)">
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

				<div class="col-md-12">品牌资质归档</div>
                <c:forEach var="mchtProductBrandCustom" items="${mchtProductBrandCustoms}">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <td class="td_title text-right">项目</td>
                        <td class="td_title text-left">资质文件如是复印件需全部加盖公章。且保证文件内容清晰、章印清楚</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="text-right">品牌名称：</td>
                        <td class="text-left">${mchtProductBrandCustom.productBrandName}<a href="javascript:;" onclick="downLoadBrandPic(${mchtProductBrandCustom.id})">[下载图片]</a></td>
                    </tr>
                    <tr>
                        <td class="text-right">商标注册证：<br/>或受理通知书：</td>
                        <td class="text-left">
                    	<c:forEach var="mchtBrandAptitudeCustom" items="${mchtProductBrandCustom.mchtBrandAptitudeCustoms}">
                            <table class="table table-bordered" name="mchtBrandAptitudeTable" mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
								<tbody>
									<tr>
										<td class="editfield-label-td">商标注册证号<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<input validate="{required:true}" type="text" value="${mchtBrandAptitudeCustom.certificateNo}" name="certificateNo">
											<div style="display: inline-block;color: #999999;">商标注册证号必填</div>
										</td>
									</tr>
									<tr>
										<td class="editfield-label-td">本商标注册证相关文件<span class="required">*</span></td>
										<td colspan="2" class="text-left">
											<div class="pic-container">
												<ul class="docs-pictures clearfix td-pictures pictures-list" name="aptitude-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
													<c:forEach var="mchtBrandAptitudePic" items="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}" varStatus="varStatus">
														<li id="mchtBrandAptitudePic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtBrandAptitudePic.pic}">
															<img  src="${ctx}/file_servelt${mchtBrandAptitudePic.pic}" onclick="viewerPic(this.src,this)">
														</li>
													</c:forEach>
												</ul>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
                    	</c:forEach>
                        </td>
                    </tr>
                    <c:if test="${not empty mchtProductBrandCustom.mchtPlatformAuthPics}">
                    <tr>
                        <td class="text-right">销售授权书：</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="info" items="${mchtProductBrandCustom.mchtPlatformAuthPics}">
                                        <li class="pic_li">
                                            <img  src="${ctx}/file_servelt${info.pic}" onclick="viewerPic(this.src,this)">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtProductBrandCustom.mchtBrandInvoicePics}">
                    <tr>
                        <td class="text-right">进货发票：</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="info" items="${mchtProductBrandCustom.mchtBrandInvoicePics}">
                                        <li class="pic_li">
                                            <img  src="${ctx}/file_servelt${info.pic}" onclick="viewerPic(this.src,this)">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtProductBrandCustom.mchtBrandInspectionPics}">
                    <tr>
                        <td class="text-right">质检报告/检疫报告：</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="info" items="${mchtProductBrandCustom.mchtBrandInspectionPics}">
                                        <li class="pic_li">
                                            <img  src="${ctx}/file_servelt${info.pic}" onclick="viewerPic(this.src,this)">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${not empty mchtProductBrandCustom.mchtBrandOtherPics}">
                    <tr>
                        <td class="text-right">其他资质：</td>
                        <td class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="info" items="${mchtProductBrandCustom.mchtBrandOtherPics}">
                                        <li class="pic_li">
                                            <img  src="${ctx}/file_servelt${info.pic}" onclick="viewerPic(this.src,this)">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    </tbody>
                </table>
                </c:forEach>
            </div>
        </div>

        <div class="modal-footer">
            <button class="btn btn-info" data-dismiss="modal">关闭</button>
        </div>
    </div>
</div>
<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;"></ul>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript">
function viewerPic(imgPath,_this){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	var id = $(_this).data("id");
	$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	$("img[name='pic"+id+"']").each(function(){
		var eachImgPath = $(this).prop("src");
		if(imgPath!=eachImgPath){
			$("#viewer-pictures").append('<li><img data-original="'+eachImgPath+'" src="'+eachImgPath+'" alt=""></li>');
		}
	});
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
	var width=window.parent.document.documentElement.clientWidth;
	var height=window.parent.document.documentElement.clientHeight;
	$(".viewer-container").height(height);
	$(".viewer-container").width(width-20);
}
function downLoadMchtInfoPic(mchtId){
  	location.href="${ctx}/mcht/contract/downLoadMchtInfoPic?mchtId="+mchtId;
}
function downLoadBrandPic(mchtProductBrandId){
	location.href="${ctx}/mcht/contract/downLoadBrandPic?mchtProductBrandId="+mchtProductBrandId;
}
var viewerPictures;
$(document).ready(function () {
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
});
</script>
</body>
</html>
