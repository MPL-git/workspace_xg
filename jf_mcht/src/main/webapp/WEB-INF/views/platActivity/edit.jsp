<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="readonly" value="true" />
<c:if test="${empty activity || activity.status==1 || activity.status==4}">
    <c:set var="readonly" value="false" />
</c:if>
<c:set var="moreInfo" value="false" />
<c:if test="${activityArea.type==1}">
    <c:set var="moreInfo" value="true" />
</c:if>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>活动报名</title>
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
    </style>
    
    <!-- 预览活动列表 -->
<style>
	.modal-preview p,
	.modal-preview ul {
		margin: 0;
	}
	.modal-preview ul {
		overflow: hidden;
		padding: 0;
	}
	.modal-preview li {
		list-style: none;
	}
	.modal-preview {
		display: none;
		position: absolute;
		top: 50%;
		left: 50%;
		z-index: 1051;
		width: 401px;
		height: 804px;
		margin: -402px 0 0 190px;
		background: url(${pageContext.request.contextPath}/static/images/active-preview/preview-bg.png) no-repeat;
		cursor: move;
	}
	.scroll-preview {
		overflow: hidden;
		position: relative;
		width: 334px;
		height: 538px;
		margin: 158px auto 0;
		cursor: default;
	}
	.close-preview {
		position: absolute;
		top: 0;
		right: 8px;
		z-index: 3;
		width: 37px;
		height: 37px;
		background: #333 url(${pageContext.request.contextPath}/static/images/active-preview/preview-close.png) no-repeat center;
		cursor: pointer;
		border-radius: 100%;

		transition: .6s ease-out;
	}
	.close-preview:hover {
		transform: rotate(360deg);
	}

	.header-preview {
		position: relative;
		height: 167px;
	}
	.header-preview img {
		display: block;
		border: none;
		width: 100%;
	}
	.header-preview p {
		position: absolute;
		bottom: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 24px;
		line-height: 24px;
		color: #fff;
		font-size: 14px;
		padding-left: 6px;
		box-sizing: border-box;
		background: #737779;
	}

	.con-preview {
		overflow: hidden;
		padding: 8px 6px 0;
		background: #fafafa;
	}
	.con-preview ul {
		width: 328px;
	}
	.con-preview li {
		float: left;
		width: 154px;
		height: 226px;
		margin: 0 6px 6px 0;
		background: #fff;
		border: 1px solid #ddd;
		box-sizing: border-box;
	}

	.con-preview-top {
		position: relative;
		height: 152px;
		background: #f1f1f1;
	}
	.con-preview-top div {
		position: absolute;
		top: 7px;
		left: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 22px;
		padding-right: 6px;
		box-sizing: border-box;
	}
	.con-preview-top span {
		display: none;
		float: right;
		width: 22px;
		height: 100%;
		margin-left: 8px;
		border-radius: 100%;
		background: #333 url(${pageContext.request.contextPath}/static/images/active-preview/preview-edit.png) no-repeat;
		cursor: pointer;

		transition: transform .3s ease-out;
	}
	.con-preview-top span:active {
		transform: scale(1.2);
	}
	.con-preview-top span.by-bot {
		background-position: 0 -66px;
	}
	.con-preview-top span.to-bot {
		background-position: 0 -44px;
	}
	.con-preview-top span.to-top {
		background-position: 0 -22px;
	}
	.con-preview-top span.by-top {
		background-position: 0 0;
	}
	.con-preview-top span.ban {
		background-color: #999;
	}
	.con-preview-top img {
		display: block;
		width: 100%;
		border: none;
	}

	.con-preview-bot {
		padding: 8px 5px 0;
	}
	.con-preview-bot p {
		overflow: hidden;
		height: 30px;
		font: 12px/14px '微软雅黑';
		color: #333;
	}
	.con-preview-bot div {
		padding-top: 6px;
		line-height: 22px;
	}
	.con-preview-bot strong,
	.con-preview-bot s {
		float: left;
		max-width: 50px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.con-preview-bot strong {
		font-size: 14px;
		font-weight: bold;
		margin-right: 4px;
		color: #f55;
	}
	.con-preview-bot s {
		font-size: 12px;
		color: #999;
	}
	.con-preview-bot em {
		float: right;
		font-style: normal;
		height: 18px;
		line-height: 18px;
		padding: 0 2px;
		font-size: 12px;
		color: #f55;
		border: 1px solid #f55;
	}

	.footer-preview {
		padding-top: 20px;
		text-align: center;
	}
	.footer-preview span {
		display: inline-block;
		vertical-align: top;
		width: 100px;
		height: 34px;
		line-height: 34px;
		margin: 0 17px;
		text-align: center;
		background: #ff5050;
		font-size: 14px;
		color: #fff;
		cursor: pointer;
	}
	.footer-preview span.hide {
		display: none;
	}

	.iScrollLoneScrollbar {
		background: rgba(0, 0, 0, .3);
	}
	.iScrollIndicator {
		border: none !important;
	}
</style>
</head>

<body>
<div class="x_panel container-fluid py-tm">
	<div class="row content-title">
            <div class="t-title">
                <c:if test="${empty activity}">
                    	添加会场活动报名
	                <c:if test="${!hasActivity}">
		                <a href="javascript:void(0);" onclick="getContent('${ctx}/plat/activity')">返回</a>
	                </c:if>
	                <c:if test="${hasActivity}">
		                <a href="javascript:void(0);" onclick="getContent('${ctx}/plat/activity/manage?id=${id}')">返回</a>
	                </c:if>
                </c:if>
                <c:if test="${not empty activity}">
                   		 修改会场活动报名
                	<a href="javascript:void(0);" onclick="getContent('${ctx}/plat/activity/viewActivity?id=${id}&activityId=${activityId}')">返回</a>
                </c:if>

            </div>
     </div>
     <div class="search-container container-fluid vm-famate">
     	<form id="dataFrom">
                <input type="hidden" value="${activityArea.id}" name="activityArea.id">
                <input type="hidden" value="${activity.id}" name="activity.id">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">专区名称</td>
                            <td colspan="2" class="text-left">
                                <input type="text" id="name" name="activity.name" value="${activity.name}"
                                       validate="{required:true}" >
                            </td>
                        </tr>
                        <c:if test="${moreInfo}">
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
                            <td class="editfield-label-td">品牌</td>
                            <td colspan="2" class="text-left">
                                <select name="activity.productBrandId" id="productBrandId" validate="{required:true}" <c:if test="${not empty activity}">disabled="disabled"</c:if>>
                                    <option value="">--请选择--</option>
                                   	<c:if test="${empty activity}">
	                                    <c:forEach var="productBrand" items="${productBrandList}">
	                                        <option value="${productBrand.id}"
	                                                <c:if test="${activity.productBrandId==productBrand.id}">selected</c:if>>${productBrand.name}</option>
	                                    </c:forEach>
                                   	</c:if>
                                   	<c:if test="${not empty activity}">
                                   		<option value="${activity.productBrandId}" selected >${productBrandName}</option>
                                   	</c:if>
                                </select>
                                	品牌限制：<input type="radio" name="activity.brandLimitType" value="1" <c:if test="${not empty activity}">disabled="disabled"</c:if> <c:if test="${empty activity || activity.brandLimitType=='1'}">checked="checked"</c:if>>品牌专场
                               	 	   	   <input type="radio" name="activity.brandLimitType" value="2" <c:if test="${not empty activity}">disabled="disabled"</c:if> <c:if test="${activity.brandLimitType=='2'}">checked="checked"</c:if>>多品牌联合
                            </td>
                        </tr>
                        <c:if test="${not empty activityArea.preferentialType && activityArea.preferentialType eq '5'}">
                        <tr>
                            <td class="editfield-label-td">
                                <span>津贴模式</span>
                                <a href="javascript:void(0);" onclick="allowanceInfo();" >
                                <span class="glyphicon glyphicon-question-sign"></span>
                                </a>
                                <br>
                                <span >(可跨店)</span>
                            </td>
                            <td colspan="2" class="text-left">
                                <input type="radio" id="allowance" name="allowance" value="5" checked="checked">
                                 <c:if test="${not empty allowanceInfo}">
                                     每满${allowanceInfo.get("minimum")}元&nbsp;&nbsp;减${allowanceInfo.get("money")}元
                                 </c:if>
                            </td>
                        </tr>
                        </c:if>

                        <tr>
                            <td class="editfield-label-td">入口图</td>
                            <td colspan="2" class="text-left">
                                <div>
                                    <div class="single_pic_picker">
                                        <input type="hidden" name="activity.entryPic" value="${activity.entryPic}"/>
                                        <input id="activity.entryPicFile" onchange="loadImageFile(this)" type="file">
                                        <c:if test='${activity.entryPic != null && activity.entryPic != ""}'>
                                            <img src="${ctx}/file_servelt${activity.entryPic}" width="160" height="80">
                                        </c:if>
                                        <c:if test='${activity.entryPic==null||activity.entryPic==""}'>
                                            <div>+</div>
                                        </c:if>
                                    </div>
                                    <div>图片尺寸宽800*400px，图片大小为100KB以内</div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">头部海报</td>
                            <td colspan="2" class="text-left">
                                <div>
                                    <div class="single_pic_picker">
                                        <input type="hidden" name="activity.posterPic" value="${activity.posterPic}"/>
                                        <input id="activity.posterPicFile"  onchange="loadImageFile(this)" type="file">
                                        <c:if test='${activity.posterPic != null && activity.posterPic != ""}'>
                                            <img src="${ctx}/file_servelt${activity.posterPic}" width="160" height="80">
                                        </c:if>
                                        <c:if test='${activity.posterPic==null||activity.posterPic==""}'>
                                            <div>+</div>
                                        </c:if>
                                    </div>
                                    <div>图片尺寸宽800*400px，图片大小为100KB以内</div>
                                </div>
                            </td>
                        </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </form>
            <c:if test="${moreInfo && activityArea.isPreSell eq '1'}">
                <div class="modal-footer">
                    <input type="checkbox" id="advanceSaleTag">报名即表示同意<a href="javascript:;" onclick="toAgreement();">《醒购预售商家协议》</a>
                </div>
            </c:if>
            <div class="modal-footer">
                    <button class="btn btn-info" onclick="commitSave();">提交</button>
            </div>
     </div>
</div>
<div class="modal fade" id="viewModal2" tabindex="1" role="dialog" aria-labelledby="productModalLabel" data-backdrop="static"></div>
<!-- 预览活动列表 -->
<div class="modal-preview" id="modal_preview">
	<div class="scroll-preview" id="scroll_preview">
		<div class="main-preview" id="main_preview">
			<div class="header-preview">
				<img src="" alt="" id="header_preview_img">
				<p id="header_preview_txt"></p>
			</div>

			<div class="con-preview" id="con_preview"></div>
		</div>
	</div>

	<div class="footer-preview">
		<span class="btn-preview" id="order_edit">编辑排序</span>
		<span class="btn-preview hide" id="order_post">提交排序</span>
		<span class="btn-preview hide" id="order_cancer">取消排序</span>
	</div>

	<div class="close-preview" id="close_preview"></div>


	<style>
		/*弹出提示*/
		.alert-preview {
			display: none;
			position: absolute;
			top: 0;
			left: 0;
			z-index: 2;
			width: 100%;
			height: 100%;
			color: #333;
			font-size: 14px;
		}

		/*温馨提示*/
		.alert-preview-con {
			display: none;
			overflow: hidden;
			position: absolute;
			top: 50%;
			left: 50%;
			width: 277px;
			height: 133px;
			margin: -66.5px 0 0 -138.5px;
			border-radius: 8px;
			background: #fff;
			box-shadow: 0 0 6px #ddd;
			cursor: default;
		}
		.alert-preview-body {
			height: 96px;
			text-align: center;
		}
		.alert-preview-body h4 {
			font-size: 20px;
			line-height: 36px;
			margin: 0;
			padding-top: 12px;
			font-weight: bold;
		}
		.alert-preview-body p {
			line-height: 30px;
		}
		.alert-preview-footer {
			height: 36px;
			line-height: 36px;
			text-align: center;
			border-top: 1px solid #ddd;
		}
		.alert-preview-footer span {
			float: left;
			width: 50%;
			height: 100%;
			box-sizing: border-box;
			cursor: pointer;
		}
		.alert-preview-footer span:first-child {
			border-right: 1px solid #ddd;
		}

		#alert_preview_delay {
			height: 60px;
		}
	</style>
	<div class="alert-preview" id="alert_preview">
		<!-- 编辑-取消与确认 -->
		<div class="alert-preview-con" id="alert_preview_confirm">
			<div class="alert-preview-body">
				<h4>温馨提示</h4>
				<p>是否取消排序</p>
			</div>
			<div class="alert-preview-footer">
				<span class="yes" id="alert_preview_yes">确认</span>
				<span class="no" id="alert_preview_no">取消</span>
			</div>
		</div>

		<!-- 编辑-成功或失败 -->
		<div class="alert-preview-con" id="alert_preview_delay">
			<div class="alert-preview-body">
				<h4>提交成功</h4>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="viewModal3" tabindex="1" role="dialog" aria-labelledby="productModalLabel" data-backdrop="static">  <%--津贴规则弹出--%>

</div>

<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.serializejson.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/iscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/tmpl.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/draggabilly.pkgd.min.js"></script>

<!-- 预览活动列表 -->
<script id="tpl_data" type="text/html">
	<ul>
	{% for (var i = 0; i < o.list.length; i++) { %}
		<li data-id="{%= o.list[i].id %}" data-activityproductid="{%= o.list[i].activityProductId %}">
			<div class="con-preview-top">
				<div>
					<span class="by-bot" title="移到最低" data-id=""></span>
					<span class="to-bot" title="向下移" data-id=""></span>
					<span class="to-top" title="向上移" data-id=""></span>
					<span class="by-top" title="移到最顶" data-id=""></span>
				</div>
				<img src="${ctx}/file_servelt{%= o.list[i].img %}" alt="">
			</div>
			<div class="con-preview-bot">
				<p title="{%= o.list[i].name %}">{%= o.list[i].name %}</p>
				<div>
					<strong title="¥{%= o.list[i].price %}">¥{%= o.list[i].price %}</strong>
					<s title="¥{%= o.list[i].cost %}">¥{%= o.list[i].cost %}</s>
					<em>{%= o.list[i].discount %}折</em>
				</div>
			</div>
		</li>
	{% } %}
	</ul>
</script>



<script type="text/javascript">

//拖动预览活动列表
new Draggabilly(modal_preview);

// 预览活动列表
function viewAndSort(activityId){
	$.ajax({
		url: '${ctx}/plat/activity/getProducts', 
		type: "GET",
		data : {activityId:activityId},
		async: false, 
		success: function (data) {
			viewAndSortSucsess(data);
		},
		error: function (data) {
			swal(data);
		}
	});
	
	// 关闭
	$('#close_preview').click(function () {
		$('#modal_preview').fadeOut();
	});
}

function viewAndSortSucsess(data) {
	// 赋值
	$('#con_preview').html(tmpl('tpl_data', data));

	$('#header_preview_img').attr('src', "${ctx}/file_servelt"+data.posterPic);
	$('#header_preview_txt').text($('#benefitPoint').val());

	$('#con_preview li:first-child .by-top').addClass('ban');
	$('#con_preview li:first-child .to-top').addClass('ban');
	$('#con_preview li:last-child .by-bot').addClass('ban');
	$('#con_preview li:last-child .to-bot').addClass('ban');

	// 重置
	$('#alert_preview').hide();
	$('#order_edit').removeClass('hide').siblings().addClass('hide');
	$('.alert-preview-con').hide();

	// 显示
	$('#modal_preview').stop().fadeIn();

	// 生成滚动条
	new IScroll('#scroll_preview', {
		mouseWheel: true,
		scrollbars: true
	});

	// 编辑排序
	$('#order_edit').click(function () {
		$(this).addClass('hide').siblings().removeClass('hide');
		$('.con-preview-top span').show();
	});

	 // 提交排序
	 var order_post_loading = false;
	$('#order_post').click(function () {
		if (order_post_loading) {
			return;
		}
		order_post_loading = true;
		// 请求返回结果
		var activityProductIds="";
        $("#con_preview").find("ul").find("li").each(function(){
        	activityProductIds+=$(this).data("activityproductid")+",";
        });
        if($.trim(activityProductIds)!=""){
        	activityProductIds=activityProductIds.substring(0, activityProductIds.length-1);
        }
        $.ajax({
        	url: '${ctx}/mcht/activity/saveSort', 
            type: "GET",
            data : {activityProductIds:activityProductIds},
            async: false, 
            success: function (data) {
				$('#alert_preview_delay h4').html('提交成功');
				$('#order_edit').removeClass('hide').siblings().addClass('hide');
				$('.con-preview-top span').hide();
				$('#alert_preview_delay').show().siblings().hide();
				$('#alert_preview').stop(true).fadeIn(100).delay(1000).fadeOut(100);
				order_post_loading = false;
            },
            error : function() {
				$('#alert_preview_delay h4').html('提交失败');
				$('#alert_preview_delay').show().siblings().hide();
				$('#alert_preview').stop(true).fadeIn(100).delay(1000).fadeOut(100);
				order_post_loading = false;
			}
        });
	});

	 // 取消排序
	$('#order_cancer').click(function () {
		$('#alert_preview_confirm').show().siblings().hide();;
		$('#alert_preview').stop().fadeIn();
	});

	// 取消-取消提示
	$('#alert_preview_no').click(function () {
		$('#alert_preview').hide();
	});

	// 确认-取消提示
	$('#alert_preview_yes').click(function () {
		viewAndSortSucsess(data);
	});

	// 移至最低
	$('.by-bot').click(function () {
		var that = $(this);

		if (that.hasClass('ban')) {
			return;
		}

		that.addClass('ban').siblings().removeClass('ban');
		that.siblings('.to-bot').addClass('ban');

		$('#con_preview li:last-child span').removeClass('ban');

		if (that.closest('li').index() == 0) {
			$('#con_preview li:nth-child(2) .by-top').addClass('ban');
			$('#con_preview li:nth-child(2) .to-top').addClass('ban');
		}

		$('#con_preview ul').append(that.closest('li'));
	});

	// 下移
	$('.to-bot').click(function () {
		var that = $(this);
		
		if (that.hasClass('ban')) {
			return;
		}

		if (that.closest('li').index() == 0) {
			that.removeClass('ban').siblings().removeClass('ban');

			$('#con_preview li:nth-child(2) span').removeClass('ban');
			$('#con_preview li:nth-child(2) .by-top').addClass('ban');
			$('#con_preview li:nth-child(2) .to-top').addClass('ban');

			if (that.closest('ul').find('li').length == 2) {
				$('#con_preview li:first-child .by-bot').addClass('ban');
				$('#con_preview li:first-child .to-bot').addClass('ban');
			}
		} else if (that.closest('li').index() == that.closest('ul').find('li').length - 2) {
			that.addClass('ban').siblings('.by-bot').addClass('ban');
			$('#con_preview li:last-child span').removeClass('ban');
		}

		$('#con_preview li').eq(that.closest('li').index() + 1).after(that.closest('li'));
	});

	// 上移
	$('.to-top').click(function () {
		var that = $(this);
		
		if (that.hasClass('ban')) {
			return;
		}

		if (that.closest('li').index() == 1) {
			$('#con_preview li:first-child span').removeClass('ban');

			if (that.closest('ul').find('li').length == 2) {
				$('#con_preview li:first-child .by-bot').addClass('ban');
				$('#con_preview li:first-child .to-bot').addClass('ban');
			}

			that.addClass('ban').siblings().removeClass('ban').siblings('.by-top').addClass('ban');
		} else if (that.closest('li').index() == that.closest('ul').find('li').length - 1) {
			$('#con_preview li:last-child span').removeClass('ban');

			$('#con_preview li').eq(that.closest('li').index() - 1).find('.by-bot').addClass('ban');
			$('#con_preview li').eq(that.closest('li').index() - 1).find('.to-bot').addClass('ban');
		}

		$('#con_preview li').eq(that.closest('li').index() - 1).before(that.closest('li'));
	});

	// 移至最顶
	$('.by-top').click(function () {
		var that = $(this);
		
		if (that.hasClass('ban')) {
			return;
		}

		that.addClass('ban').siblings().removeClass('ban');
		that.siblings('.to-top').addClass('ban');

		$('#con_preview li:first-child span').removeClass('ban');

		if (that.closest('li').index() == that.closest('ul').find('li').length - 1) {
			$('#con_preview li:nth-last-child(2) .by-bot').addClass('ban');
			$('#con_preview li:nth-last-child(2) .to-bot').addClass('ban');
		}

		$('#con_preview ul').prepend(that.closest('li'));
	});
}

    var dataFromValidate;
    $(function () {

        <c:if test="${readonly}">
        disableForm("dataFrom", true);
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
        
    });


    function commitSave() {
        enableForm("dataFrom");
        if (dataFromValidate.form()) {
			//预售提交判断
            if(!$("#advanceSaleTag").is(':checked') && ${activityArea.isPreSell eq '1' && activityArea.type eq '1'}){
                swal('请阅读并同意 《醒购预付商家协议》');
                return;
            }
        	if($("#productTypeSecondId").val() == "--请选择--"){
				swal("请选择二级类目");
				return;
			}
            <c:if test="${moreInfo}">

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
                if("${activity.entryPic}"=="" || entryPics.attr("src").indexOf("${activity.entryPic}") < 0){//有修改
                	uploadImg("activity.entryPicFile", entryPic);
                }

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
                if("${activity.posterPic}"=="" || posterPics.attr("src").indexOf("${activity.posterPic}") < 0){//有修改
                	uploadImg("activity.posterPicFile", posterPic);
                }

            </c:if>


            var dataJson = $("#dataFrom").serializeArray();
            dataJson.push({"name":"json", "value":JSON.stringify($("#dataFrom").find(':input').filter(function () {
                return $.trim(this.value).length > 0
            }).serializeJSON())});

            $.ajax({
                url: "${ctx}/plat/activity/save",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        //$("#viewModal").modal('hide');
                        //table.ajax.reload();
//                        swal({
//                            title: "提交成功!",
//                            type: "success",
//                            confirmButtonText: "确定"
//
//                        });
//                        listProduct(result.data.activity.id, 1);
                    	if(result.data.type == 1){
                	    	var url = "${ctx}/plat/activity/viewActivity?id=" + result.data.id+"&activityId="+result.data.activity.id;
                	        getContent(url);
                		}else if(result.data.type == 2 || result.data.type == 3){
                			var url = "${ctx}/plat/activity/listProductPage?activityId=" + result.data.activity.id+"&status=2";
                	        getContent(url);
                		}
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

    //禁用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]
    function disableForm(formId) {
        $("#"+formId+ " :text").attr("disabled","disabled");
        $("#"+formId+ " textarea").attr("disabled","disabled");
        $("#"+formId+ " select").attr("disabled","disabled");
        $("#"+formId+ " :radio").attr("disabled","disabled");
        $("#"+formId+ " :checkbox").attr("disabled","disabled");

        <c:if test="${not empty auth}">
            <c:if test="${auth.activityNameFlag==1}">
            $("input[name='activity.name']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityTypeFlag==1}">
            $("select[name='activity.productTypeId']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityBrandFlag==1}">
            $("select[name='activity.productBrandId']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityEntryFlag==1}">
            $("input[name='activity.entryPic']").removeAttr("disabled");
            </c:if>
            <c:if test="${auth.activityPosterFlag==1}">
            $("input[name='activity.posterPic']").removeAttr("disabled");
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

    function listProduct(activityId, status) {
        $('#viewModal').on('hidden.bs.modal', function (e) {
            var url = "${ctx}/plat/activity/listProductPage?activityId=" + activityId + "&status=" + status;
            getContent(url);
        })

        $("#viewModal").modal('hide');
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
    			alert("图片上传失败");
    		} 
    		});


    }


    function loadImageFile(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        if(oFile.size>100*1024){
            alert("图片大小不能超过100K");
            return;
        }
        var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
        if (!rFilter.test(oFile.type)) {
            alert("请选择图片文件");
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
                if(img.width!=800||img.height!=400){
                    alert("请选择尺寸为800*400的图片");
                    return;
                }else{
                    $(obj).parent().children("img").attr("src",oFREvent.target.result);
                    $(obj).parent().children("div").remove();
                }
            }
        };
        oFReader.readAsDataURL(oFile);
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

function allowanceInfo() {
    $.ajax({
        url: "${ctx}/plat/activity/allowanceInfo",
        type: "GET",
        success: function(data){
            $("#viewModal3").html(data);
            $("#viewModal3").modal();
        },
        error: function() {
            swal('页面不存在');
        }
    });
}
</script>
</body>
</html>
