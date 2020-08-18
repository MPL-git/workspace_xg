<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("graphicContent") != null ? request.getParameter("graphicContent") : "";
%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>


<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<style type="text/css">
		html {
			font-size: 100px;
		}
		@media (min-width: 750px) {
			html {
				width: 750px;
				margin: 0 auto;
			}
		}

		body {
			margin: 0;
			font-size: .24rem;
			text-rendering: optimizeLegibility;
			-webkit-font-smoothing: antialiased;
			-webkit-tap-highlight-color: transparent;
		}

		.flex {
			display: -webkit-flex;
			display: flex;
			box-sizing: border-box;
		}
		.jc {
			-webkit-justify-content: center;
			justify-content: center;
		}
		.jb {
			-webkit-justify-content: space-between;
			justify-content: space-between;
		}
		.je {
			-webkit-justify-content: flex-end;
			justify-content: flex-end;
		}
		.ac {
			-webkit-align-items: center;
			align-items: center;
		}

		.box-header {
			height: .6rem;
		}
		.box-btn {
			width: .8rem;
			height: .4rem;
			font-size: inherit;
			border-radius: 0;
			background: #f05050;
			color: #fff;
			border: none;
		}

		.box-file {
			overflow: hidden;
			position: relative;
		}
		.box-file input[type=file] {
			position: absolute;
			top: 0;
			z-index: 1;
			left: 0;
			width: 100%;
			height: 100%;
			opacity: 0;
			cursor: default;
		}

		.box-con {
			position: relative;
			box-shadow: inset 0 0 1px #000;
			width: 7.5rem;
			height: 5rem;
		}
		.box-svg {
			overflow: hidden;
			position: absolute;
			top: 0;
			left: 0;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;

			cursor: crosshair;
		}
		.box-svg img {
			display: block;
			pointer-events: none;
		}
		.box-frame {
			position: absolute;
			top: 0;
			left: 0;
			z-index: 1;
			width: 0;
			height: 0;
			background: rgba(0, 0, 0, .5);
			border: 1px dashed #000;
			cursor: move;
		}
		.box-frame div {
			position: absolute;
			top: 0;
			left: -1px;
			z-index: 1;
			width: calc(100% + 2px);
			height: .2rem;
		}
		.mask-close a,
		.box-frame .box-delete,
		.box-frame .box-popup {
			float: right;
			position: relative;
			width: .2rem;
			height: .2rem;
			border-radius: .1rem;
			background: #fff;
			text-align: center;
			font-size: .14rem;
			font-weight: bold;
			box-sizing: border-box;
			box-shadow: 0 0 .06rem #000;
			cursor: pointer;
			opacity: 0;
			pointer-events: none;

			-webkit-transition: .3s ease-out;
			transition: .3s ease-out;
		}
		.box-frame:hover .box-delete,
		.box-frame:hover .box-popup {
			opacity: 1;
			pointer-events: auto;
		}
		.box-frame .box-delete:active,
		.box-frame .box-popup:active {
			background: #ccc;
		}
		
		.box-frame .box-delete:after,
		.box-frame .box-popup:after {
			content: '!';
		}
		.box-frame .box-delete {
			margin: 0 .1rem;
		}
		.mask-close a:before,
		.mask-close a:after,
		.box-frame .box-delete:before,
		.box-frame .box-delete:after {
			position: absolute;
			top: 50%;
			left: 50%;
			content: '';
			width: .14rem;
			height: .02rem;
			margin: -.01rem 0 0 -.07rem;
			background: #000;
			border-radius: .01rem;
			-webkit-transform: rotate(45deg);
			transform: rotate(45deg);
		}
		.mask-close a:after,
		.box-frame .box-delete:after {
			-webkit-transform: rotate(-45deg);
			transform: rotate(-45deg);
		}

		.box-mask {
			position: fixed;
			top: 0;
			left: 0;
			z-index: 11;
			width: 100vw;
			height: 100vh;
			background: rgba(0, 0, 0, .5);
			opacity: 0;
			pointer-events: none;

			-webkit-transition: .3s ease-out;
			transition: .3s ease-out;
		}
		.box-mask.show {
			opacity: 1;
			pointer-events: auto;
		}
		#test_load p {
			position: absolute;
			top: 50%;
			left: 0;
			width: 100%;
			text-align: center;
			color: #fff;
		}
		.pop-con {
			position: absolute;
			top: 28%;
			left: 50%;
			width: 5rem;
			height: 3.6rem;
			margin: -1rem 0 0 -2.5rem;
			background: #fff;
		}
		.pop-con h3 {
			margin: .2rem 0 0;
			text-align: center;
		}
		.pop-input {
			height: .6rem;
			padding: .2rem .2rem 0;
			clear: both;
		}
		.pop-input label {
			width: .6rem;
			margin-right: .1rem;
		}
		
		.pop-input32 label {
			width: 1.6rem;
			margin-right: .1rem;
		}
		
		.pop-input input {
			width: 3.8rem;
			height: .24rem;
			padding-left: .1rem;
		}

		.box-article p {
			margin: .2rem 0 0;
		}

		.p-index {
			position: relative;
			padding-left: 1.36rem;
			word-break: break-all;
		}
		.p-index:before {
			position: absolute;
			top: 0;
			left: 0;
			width: 1.34rem;
			content: attr(data-t);
		}

		.box-dog {
			position: absolute;
			top: 0;
			left: 0;
			z-index: 2;
			width: .1rem;
			height: .1rem;
		}
		.box-dog:nth-child(2) {
			left: auto;
			right: 0;

			-webkit-transform: rotate(90deg);
			transform: rotate(90deg);
		}
		.box-dog:nth-child(3) {
			left: auto;
			right: 0;
			top: auto;
			bottom: 0;

			-webkit-transform: rotate(180deg);
			transform: rotate(180deg);
		}
		.box-dog:nth-child(4) {
			top: auto;
			bottom: 0;

			-webkit-transform: rotate(-90deg);
			transform: rotate(-90deg);
		}
		.box-dog:after,
		.box-dog:before {
			position: absolute;
			top: 0;
			left: 0;
			content: '';
			width: .04rem;
			height: .1rem;
			background: rgba(255, 255, 255, .6);
		}
		.box-dog:after {
			width: .1rem;
			height: .04rem;
		}

		.box-dog[data-dog="0"] {
			cursor: nw-resize;
		}
		.box-dog[data-dog="1"] {
			cursor: ne-resize;
		}
		.box-dog[data-dog="2"] {
			cursor: se-resize;
		}
		.box-dog[data-dog="3"] {
			cursor: sw-resize;
		}
		
		.mask-close a {
			margin: .1rem .2rem 0 0;
			opacity: 1;
			pointer-events: auto;
		}
</style>
<style type="text/css">
.video_box {
    background: #fff;
    z-index: 2222;
    display: block;
}

.black_box {
    background: #000;
    opacity: 0.6;
    left: 0;
    top: 0;
    z-index: 1111;
    position: fixed;
    height: 100%;
    width: 100%;
}
.video_close {
    position: absolute;
    top: 0px;
    right: 0px;
}

.panel {
    margin-bottom: 14px;
    background-color: #fff;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.panel-default {
    border-color: #ddd;
}
.modal-header {
    height: 34px;
    line-height: 34px;
    padding: 0;
    border-bottom: 1px solid #ddd;
}
.modal-title {
    line-height: 34px;
    font-size: 12px;
    font-weight: bold;
    padding-left: 10px;
}
.l-text-wrapper{
	display:inline-block;
}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){

	   var editor1;
	   KindEditor.ready(function(K) {
		 editor1 = K.create('textarea[name="graphicContent"]', {
			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
			}
			
		});
		prettyPrint();
	});
	  	
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});

	//商品模块--APP显示商品个数
	$("#showNum").bind('change',function(){
		var decorateModuleId = $("#decorateModuleId").val();		
		var showNum = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/updateShowNum.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {decorateModuleId:decorateModuleId,showNum:showNum},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});

	//商品模块--展示模式
	$("#showModel").bind('change',function(){
		var decorateModuleId = $("#decorateModuleId").val();
		var showModel = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/updateShowModel.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {decorateModuleId:decorateModuleId,showModel:showModel},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {

				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});

	//初始化二级分类下拉框选中效果
	<c:if test="${moduleType == 4 || moduleType == 5}">
		var decorateModuleId = $("#decorateModuleId").val();
		if(decorateModuleId){
			var productType2Ids = '${decorateModule.productType2Ids}';
			var productType2IdsArray = productType2Ids.split(",");
			for(var i=0;i<productType2IdsArray.length;i++){
				$("#productType2Ids").find("option[value='"+productType2IdsArray[i]+"']").attr("selected",true);
			}
		}
	</c:if>
	
	$("#confirm").bind('click',function(){
		var decorateInfoId = $("#decorateInfoId").val();
		var decorateAreaId = $("#decorateAreaId").val();
		var decorateModuleId = $("#decorateModuleId").val();
		var moduleType = $("#moduleType").val();
		var seqNo = $("#seqNo").val();
		<c:if test="${moduleType == 10}">
		var fieldFontColor = $("#fieldFontColor").val();
		var fieldSelectFontColor = $("#fieldSelectFontColor").val();
		var fieldBgColor = $("#fieldBgColor").val();
		var fieldBgPic = $("#fieldBgPic").val();
		var openFontColor = $("#openFontColor").val();
		var openFieldBgColor = $("#openFieldBgColor").val();
		var openBtnBgColor = $("#openBtnBgColor").val();
		var openBtnArrowColor = $("input[name='openBtnArrowColor']:checked").val();
		if(!fieldFontColor){
			commUtil.alertError("栏位字体颜色不能为空");
			return false;
		}
		if(!fieldSelectFontColor){
			commUtil.alertError("栏位选中字体颜色不能为空");
			return false;
		}
		if(!fieldBgColor){
			commUtil.alertError("栏位背景颜色不能为空");
			return false;
		}
		if(!openFontColor){
			commUtil.alertError("展开字体颜色不能为空");
			return false;
		}
		if(!openFieldBgColor){
			commUtil.alertError("展开栏位背景颜色不能为空");
			return false;
		}
		if(!openBtnBgColor){
			commUtil.alertError("展开按钮背景颜色不能为空");
			return false;
		}
		if(!openBtnArrowColor){
			commUtil.alertError("请选择展开按钮箭头颜色");
			return false;
		}
		var reg = /^#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})$/;
		if(!reg.test(fieldFontColor)){
			commUtil.alertError("栏位字体颜色输入错误");
			return false;
		}
		if(!reg.test(fieldSelectFontColor)){
			commUtil.alertError("栏位选中字体颜色输入错误");
			return false;
		}
		if(!reg.test(fieldBgColor)){
			commUtil.alertError("栏位背景颜色输入错误");
			return false;
		}
		if(!reg.test(openFontColor)){
			commUtil.alertError("展开字体颜色输入错误");
			return false;
		}
		if(!reg.test(openFieldBgColor)){
			commUtil.alertError("展开栏位背景颜色输入错误");
			return false;
		}
		if(!reg.test(openBtnBgColor)){
			commUtil.alertError("展开按钮背景颜色输入错误");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {
					decorateInfoId:decorateInfoId,
					decorateAreaId:decorateAreaId,
					decorateModuleId:decorateModuleId,
					moduleType:moduleType,
					fieldFontColor:fieldFontColor,
					fieldSelectFontColor:fieldSelectFontColor,
					fieldBgColor:fieldBgColor,
					fieldBgPic:fieldBgPic,
					openFontColor:openFontColor,
					openFieldBgColor:openFieldBgColor,
					openBtnBgColor:openBtnBgColor,
					openBtnArrowColor:openBtnArrowColor
				},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("提交成功");
					parent.location.reload();
					frameElement.dialog.close();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		</c:if>
		<c:if test="${moduleType == 13}">
			var pic = $("#pic").val();
			if(!pic){
				commUtil.alertError("请先上传图片");
				return false;
			}
			var videoPath = $("#videoPath").val();
			if(!videoPath){
				commUtil.alertError("请先上传视频");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {decorateAreaId:decorateAreaId,decorateModuleId:decorateModuleId,moduleType:moduleType,seqNo:seqNo,videoPath:videoPath,pic:pic},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							frameElement.dialog.close();
						},1000);
						parent.location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		</c:if>
		<c:if test="${moduleType == 1 || moduleType == 8 || moduleType == 9 }">
			var pic = $("#pic").val();
			if(!pic){
				commUtil.alertError("请先上传图片");
				return false;
			}
//			this.preventDefault();

			var box_map = [],
				box_frames = box_svg.querySelectorAll('.box-frame');

			if (box_obj.src || pic) {
				for (var i = 0; i < box_frames.length; i++) {
					if(box_frames[i].getAttribute('data-linkType') && box_frames[i].getAttribute('data-linkValue')){
						box_map.push({
							x1: box_frames[i].getAttribute('data-x1'),
							y1: box_frames[i].getAttribute('data-y1'),
							x2: box_frames[i].getAttribute('data-x2'),
							y2: box_frames[i].getAttribute('data-y2'),
							linkType: box_frames[i].getAttribute('data-linkType'),
							linkValue: box_frames[i].getAttribute('data-linkValue')
						});
					}
					if(box_frames[i].getAttribute('data-linkType') && box_frames[i].getAttribute('data-fontSize')){
						box_map.push({
							x1: box_frames[i].getAttribute('data-x1'),
							y1: box_frames[i].getAttribute('data-y1'),
							x2: box_frames[i].getAttribute('data-x2'),
							y2: box_frames[i].getAttribute('data-y2'),
							linkType: box_frames[i].getAttribute('data-linkType'),
							fontSize: box_frames[i].getAttribute('data-fontSize'),
							fontColor: box_frames[i].getAttribute('data-fontColor'),
							countDownBeginDate: box_frames[i].getAttribute('data-countDownBeginDate'),
							countDownEndDate:  box_frames[i].getAttribute('data-countDownEndDate'),
						});
					}
				}

				$.ajax({
					url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {decorateAreaId:decorateAreaId,decorateModuleId:decorateModuleId,moduleType:moduleType,seqNo:seqNo,pic:pic,moduleMapJsonStr:JSON.stringify(box_map)},
					timeout : 30000,
					success : function(data) {
						if ("0000" == data.returnCode) {
							commUtil.alertSuccess("提交成功");
							setTimeout(function(){
								frameElement.dialog.close();
							},1000);
							parent.location.reload();
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
				
			}
			
		</c:if>
		<c:if test="${moduleType == 3}">
			var activityIds = $("#activityIds").val();
			var showNum = $("#showNum3").val();
			if(!activityIds){
				commUtil.alertError("特卖ID不能为空");
				return false;
			}
			if(!showNum){
				commUtil.alertError("显示个数不能为空");
				return false;
			}
			if(!testNumber(showNum)){
				commUtil.alertError("显示个数只能是正整数");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {decorateInfoId:decorateInfoId,decorateAreaId:decorateAreaId,decorateModuleId:decorateModuleId,moduleType:moduleType,seqNo:seqNo,activityIds:activityIds,showNum:showNum},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("提交成功");
						parent.location.reload();
						frameElement.dialog.close();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		</c:if>
		<c:if test="${moduleType == 4 || moduleType == 5}">
			var productType1Ids = $("#productType1Ids").val();
			<c:if test="${moduleType == 4}">
			if(!productType1Ids){
				commUtil.alertError("请选择一级分类");
				return false;
			}
			</c:if>
			var productType2Ids = "";
			$("#productType2Ids").find("option:selected").each(function(index){
				if(index!=$("#productType2Ids").find("option:selected").length-1){
					productType2Ids+=$(this).val()+",";
				}else{
					productType2Ids+=$(this).val();
				}
			});
			<c:if test="${moduleType == 4}">
			if(!productType2Ids){
				commUtil.alertError("请选择二级分类");
				return false;
			}
			</c:if>
			var productBrandIds = "";
			$("a[name='deleteProductBrand']").each(function(index){
				if(index!=$("a[name='deleteProductBrand']").length-1){
					productBrandIds+=$(this).attr("productbrandid")+",";
				}else{
					productBrandIds+=$(this).attr("productbrandid");
				}
			});
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {decorateAreaId:decorateAreaId,decorateModuleId:decorateModuleId,moduleType:moduleType,seqNo:seqNo,productType1Ids:productType1Ids,productType2Ids:productType2Ids,productBrandIds:productBrandIds},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("提交成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
			
		</c:if>	
		<c:if test="${moduleType == 14}">
		editor1.sync();
		var graphicContent = $("#graphicContent").val();
		if(!graphicContent){
			commUtil.alertError("文本框不能为空");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {decorateInfoId:decorateInfoId,decorateAreaId:decorateAreaId,decorateModuleId:decorateModuleId,moduleType:moduleType,seqNo:seqNo,graphicContent:graphicContent},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("提交成功");
					parent.location.reload();
					frameElement.dialog.close();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	</c:if>
	});
	
	$("#productType1Ids").bind('change',function(){
		var productTypeId = $(this).val();
		if(productTypeId){
			$("#selectBrandsDiv").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/getProductTypes.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"productTypeId":productTypeId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypes = data.productTypes;
						var optionArray = [];
						optionArray.push('<option value="">不限</option>');
						for(var i=0;i<productTypes.length;i++){
							var id = productTypes[i].id;
							var name = productTypes[i].name;
							optionArray.push('<option value="'+id+'">'+name+'</option>');
						}
						$("#productType2Ids").html(optionArray.join(""));
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productType2Ids").html('<option value="">不限</option>');
		}
	});
	
	$("a[name='deleteProductBrand']").live('click',function(){
		$(this).parent().remove();
	});
	
	$(".video_close").bind('click',function(){
		$("#addBrandDiv").hide();
		$(".black_box").hide();
	});
	
	$("a[name='add']").live('click',function(){
		var $this = $(this);
		var text = $this.text();
		if(text == "已添加"){
			return false;
		}
		$this.text("已添加");
		var brandId = $this.attr("brandId");
		var brandName = $this.attr("brandName");
		var hasAdd = false;
		$("a[name='deleteProductBrand']").each(function(){
			var productbrandId = $(this).attr("productbrandId");
			if(brandId == productbrandId){
				hasAdd = true;
				return;
			}
		});
		if(!hasAdd){
			$("#selectBrandsDiv").append('<span>'+brandName+'&nbsp;<a href="javascript:;" style="color: blue;" name="deleteProductBrand" productBrandId="'+brandId+'">删</a>&nbsp;&nbsp;&nbsp;</span>');
		}
	});
	
});

function updateSeqNo(moduleItemId){
	var seqNo = $("#moduleItem" + moduleItemId).val()
	if(!seqNo){
		return false;
	}else{
		if(!testNumber(seqNo)){
			commUtil.alertError("排序值只能是正整数");
			return false;
		}else{
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/sortModuleItem.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {moduleItemId:moduleItemId,seqNo:seqNo},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("排序成功");
						location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	}
}

function boxDelete(_this){
	$(_this).parent().parent().remove();
}

function chooseProductList(){
	var decorateInfoId = $("#decorateInfoId").val();
	var decorateAreaId = $("#decorateAreaId").val();
	var decorateModuleId = $("#decorateModuleId").val();
	var showNum = $("#showNum").val();
	var seqNo = $("#seqNo").val();
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "选择商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/chooseProductList.shtml?decorateInfoId=" + decorateInfoId+"&decorateAreaId="+decorateAreaId+"&showNum="+showNum+"&decorateModuleId="+decorateModuleId+"&seqNo="+seqNo,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null,
		closeRefresh:true    
	});
}

function editDecorateProductPool(){
	var decorateInfoId = $("#decorateInfoId").val();
	var isPreSell = $("#isPreSell").val();
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "本装修商品池",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/editDecorateProductPool.shtml?decorateInfoId=" + decorateInfoId +"&pageType="+$("#pageType").val()+"&isPreSell="+ isPreSell,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function managementCategory() {
	var decorateModuleId = $("#decorateModuleId").val();
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.6,
		title: "管理类目",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/managementCategory.shtml?decorateModuleId="+decorateModuleId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null,
		closeRefresh:true
	});
}

function backgroundSetting() {
	var decorateModuleId = $("#decorateModuleId").val();
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.6,
		title: "背景设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/background.shtml?decorateModuleId="+decorateModuleId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null,
		closeRefresh:true
	});
}

function checkBrand(){
	var productBrandIds = $("#productBrandIds").val();
	if(!productBrandIds){
		commUtil.alertError("请输入品牌ID，并用英文逗号隔开");
		return false;
	}
	$.ligerDialog.open({
		height: $(window).height()*0.5,
		width: $(window).width()*0.8,
		title: "检测品牌",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/checkBrand.shtml?productBrandIds=" + productBrandIds,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function addBrand(){
	var productType1Ids = $("#productType1Ids").val();
	<c:if test="${moduleType == 4}">
	if(!productType1Ids){
		commUtil.alertError("请选择一级分类");
		return false;
	}
	</c:if>
	
	$("#add1").empty();
	$("#add2").empty();
	var $op1 = $("#productType1Ids").find("option:selected");
	var op1 = '<option value="'+$op1.val()+'" selected>'+$op1.text()+'</option>';
	var op2 = [];
	$("#productType2Ids").find("option:selected").each(function(){
		op2.push('<option value="'+$(this).val()+'" selected>'+$(this).text()+'</option>');
	});
	$("#add1").append(op1);
	$("#add2").append(op2.join());
	$("#brandList").empty();
	$("#addBrandDiv").show();
	$(".black_box").show();
}

function searchBrand(){
	var productType1Ids = $("#add1").val();
	var productType2Ids = "";
	$("#add2").find("option:selected").each(function(index){
		if(index!=$("#add2").find("option:selected").length-1){
			productType2Ids+=$(this).val()+",";
		}else{
			productType2Ids+=$(this).val();
		}
	});
	var productbrandIdArray = [];
	$("a[name='deleteProductBrand']").each(function(){
		var productbrandId = $(this).attr("productbrandId");
		productbrandIdArray.push(productbrandId);
	});
	var brandIds = productbrandIdArray.join(",");
	$.ajax({
		url : "${pageContext.request.contextPath}/customPage/searchBrand.shtml?productType1Ids="+productType1Ids+"&productType2Ids="+productType2Ids+"&brandIds="+brandIds,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				$("#brandList").empty();
				var brandList = data.brandList;
				var html=[];
				for(var i=0;i<brandList.length;i++){
					var id = brandList[i].id;
					var name = brandList[i].name;
					html.push('<span>'+name+'</span>&nbsp;<a href="javascript:;" name="add" brandId="'+id+'" brandName="'+name+'">添加</a>&nbsp;&nbsp;&nbsp;');
				}
				$("#brandList").html(html.join());
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function deleteModuleItem(moduleItemId){
	if(confirm("确定删除吗？")){
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/deleteModuleItem.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {moduleItemId:moduleItemId},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("删除成功");
					location.reload();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}
function reselect(moduleItemId) {
	var decorateModuleId = $("#decorateModuleId").val();
	$.ligerDialog.open({
		height: 300,
		width: 400,
		title: "选择类目",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/selectCategory.shtml?decorateModuleId="+decorateModuleId+"&moduleItemId="+moduleItemId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
        allowClose: false,
		data: null
	});
}

function selectModuleItem(cateId) {
	var decorateModuleId = $("#decorateModuleId").val();
	if (cateId==0){
		window.parent.document.getElementById("selectCateModuleItem").value = "";
	} else {
		window.parent.document.getElementById("selectCateModuleItem").value = cateId;
		window.parent.document.getElementById("selectCateModuleItem").decorateModuleId = decorateModuleId;
	}
	window.parent.document.getElementById("selectCateModuleItem").click();
	frameElement.dialog.close();
}

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}


/* $("#countDownBeginDate").ligerDateEditor({
	format:'yyyy-MM-ff hh:mm:ss',
	showTime : false,
	labelWidth : 150,
	width : 150,
	labelAlign : 'left'
}); */
</script>

<html>
<body>
<form method="post" id="test_box" name="form" action="${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml">
			<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
			<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateAreaId}">
			<input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${decorateModuleId}">
			<input type="hidden" id="moduleType" name="moduleType" value="${moduleType}">
			<input type="hidden" id="seqNo" name="seqNo" value="${seqNo}">
			<input type="hidden" id="brandIdAndName" name="brandIdAndName" >
			<input type="hidden" id="pageType" name="pageType" value="${pageType}">
			<input type="hidden" id="isPreSell" name="isPreSell" value="${isPreSell}">
			<c:if test="${moduleType == 1 || moduleType == 8}">
			<div>
				<div style="float: left;height: 30px;">
					<c:if test="${pageType eq '21'}">
						<input type="file" id="picFile" name="file" onchange="ajaxFileUploadHDImg('picFile');" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
					</c:if>
					<c:if test="${pageType ne '21'}">
						<input type="file" id="picFile" name="file" onchange="ajaxFileUploadImg('picFile');" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
					</c:if>
				</div>
				<input type="button" style="float:right;width: 80px;height: 35px;cursor: pointer;" value="保存" id="confirm">
				<br>
				<br>
				<div class="box-con">
					<div class="box-svg">
						<img src="${pageContext.request.contextPath}/file_servelt${decorateModule.pic}" style="width: 750px;height: 500px;<c:if test="${empty decorateModule.pic}">display: none;</c:if>" id="modulePic">
					<c:forEach items="${moduleMapJSONArray}" var="moduleMapJsonObject">	
						<div class="box-frame" data-x1="${moduleMapJsonObject.x1}" data-y1="${moduleMapJsonObject.y1}" data-x2="${moduleMapJsonObject.x2}" data-y2="${moduleMapJsonObject.y2}" data-linktype="${moduleMapJsonObject.linkType}" data-linkvalue="${moduleMapJsonObject.linkValue}" 
						data-fontSize="${moduleMapJsonObject.fontSize}" data-fontColor="${moduleMapJsonObject.fontColor}" data-countDownBeginDate="${moduleMapJsonObject.countDownBeginDate}" data-countDownEndDate="${moduleMapJsonObject.countDownEndDate}"  style="left: ${moduleMapJsonObject.x1}%; top: ${moduleMapJsonObject.y1}%; width: ${moduleMapJsonObject.width}%; height: ${moduleMapJsonObject.height}%;">
							<a class="box-dog" data-dog="0"></a>
							<a class="box-dog" data-dog="1"></a>
							<a class="box-dog" data-dog="2"></a>
							<a class="box-dog" data-dog="3"></a>
							<div><a class="box-delete"></a><a class="box-popup"></a></div>
						</div>
					</c:forEach>	
					</div>
				</div>
				<input id="pic" name="pic" type="hidden" value="${decorateModule.pic}">
			</div>
			<br>	
			</c:if>
			<c:if test="${moduleType == 2}">
			<div>
				<a href="javascript:;" id="chooseProductList" onclick="chooseProductList();">选择商品</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:;" id="editDecorateProductPool" onclick="editDecorateProductPool();">编辑商品池</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:;" id="managementCategory" onclick="managementCategory();">管理类目</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:;" id="backgroundSetting" onclick="backgroundSetting();">背景设置</a>
				<span style="float: right;">APP显示商品个数
					<select id="showNum" name="showNum">
						<option value="10" <c:if test="${decorateModule.showNum == 10}">selected="selected"</c:if>>10个</option>
						<option value="20" <c:if test="${decorateModule.showNum == 20}">selected="selected"</c:if>>20个</option>
						<option value="30" <c:if test="${decorateModule.showNum == 30}">selected="selected"</c:if>>30个</option>
						<option value="50" <c:if test="${decorateModule.showNum == 50}">selected="selected"</c:if>>50个</option>
						<option value="80" <c:if test="${decorateModule.showNum == 80}">selected="selected"</c:if>>80个</option>
						<option value="100" <c:if test="${decorateModule.showNum == 100}">selected="selected"</c:if>>100个</option>
					</select>
				</span>
				<span style="float: left;">展示模式
					<select id="showModel" name="showModel">
						<option value="2" <c:if test="${decorateModule.showModel == 2}">selected="selected"</c:if>>2个</option>
						<option value="3" <c:if test="${decorateModule.showModel == 3}">selected="selected"</c:if>>3个</option>
					</select>
				</span>
			</div>
			<br>
			<hr>
			<div style="float: left">
				<a href="javascript:selectModuleItem(0)" <c:if test="${categoryTag == ''|| categoryTag == null}">style="background-color: grey"</c:if>>未设置类目</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<c:forEach var="category" items="${productModuleTypes}">
				<a href="javascript:selectModuleItem(${category.id})" <c:if test="${categoryTag == category.id}">style="background-color: grey"</c:if>>${category.name}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
			</div>
	        <table class="gridtable">
				<c:forEach var="moduleItemCustom" items="${moduleItemCustoms}" varStatus="status">
				<c:if test="${(status.index+1)%2==1}">
					<tr>
				</c:if>
						<td class="l-grid-row-cell ">
							<div class="l-grid-row-cell-inner">
								<span style="display:block;text-align:left;margin-top:8px;">
									<img style="margin:10px;" src="${pageContext.request.contextPath}/file_servelt${moduleItemCustom.pic}" width="100" height="100" onclick="viewerPic(this.src)"><br>
									${moduleItemCustom.productName}<br>
									价格：<c:if test="${moduleItemCustom.salePriceMin != moduleItemCustom.salePriceMax}">
											<span style="color:red;">${moduleItemCustom.salePriceMin}-${moduleItemCustom.salePriceMax}</span>
										</c:if>
										<c:if test="${moduleItemCustom.salePriceMin == moduleItemCustom.salePriceMax}">
											<span style="color:red;">${moduleItemCustom.salePriceMin}</span>
										</c:if>
									元，库存总数：${moduleItemCustom.stock}件<br>
									折扣：<c:if test="${moduleItemCustom.discountMin != moduleItemCustom.discountMax}">
											${moduleItemCustom.discountMin}-${moduleItemCustom.discountMax}
										</c:if>
										<c:if test="${moduleItemCustom.discountMin == moduleItemCustom.discountMax}">
											${moduleItemCustom.discountMax}
										</c:if>
									折，商品ID:${moduleItemCustom.productCode}<br>
									排序值：<input type="text" id="moduleItem${moduleItemCustom.id}" style="width:80px;" moduleItemId="${moduleItemCustom.id}" autocomplete="off" value="${moduleItemCustom.seqNo}" onblur="updateSeqNo(${moduleItemCustom.id})">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="删除商品" onclick="deleteModuleItem(${moduleItemCustom.id});">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="重选类目" onclick="reselect(${moduleItemCustom.id})">
								</span>
							</div>
						</td>
				<c:if test="${(status.index+1)%2==0}">
					</tr>
				</c:if>
				</c:forEach>
			</table>
			</c:if>
			<c:if test="${moduleType == 3}">
			<table class="gridtable">
           	<tr>
               <td class="title">特卖ID</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea id="activityIds" name="activityIds" rows="5" class="textarea" cols="50">${activityIds}</textarea>
               		<br>备注：添加多个特卖时，请用逗号隔开。
               </td>
			</tr>
			<tr>
               <td class="title">APP端显示个数</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="showNum3" name="showNum" value="${decorateModule.showNum}">
               </td>
			</tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
	        </c:if>
	        <c:if test="${moduleType == 4 || moduleType == 5}">
	        <table class="gridtable">
           	<tr>
               <td class="title">一级分类</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType1Ids" name="productType1Ids">
               		<option value="">请选择</option>
               		<c:forEach items="${productTypes}" var="productType">
	               		<option value="${productType.id}" <c:if test="${productType.id == decorateModule.productType1Ids}">selected="selected"</c:if>>${productType.name}</option>
               		</c:forEach>
               	</select>
               </td>
			</tr>
			<tr>
               <td class="title">二级分类</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType2Ids" name="productType2Ids" multiple="multiple" style="width: 200px;height: 200px;">
               		<option value="">请选择</option>
               		<c:forEach items="${secondProductTypes}" var="secondProductType">
               			<option value="${secondProductType.id}">${secondProductType.name}</option>
               		</c:forEach>
               	</select>
               </td>
			</tr>
			<tr>
               <td class="title">品牌</td>
               <td colspan="2" align="left" class="l-table-edit-td" id="selectBrands">
               		<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="添加品牌" onclick="addBrand();"><br>
               		<div id="selectBrandsDiv">
               		<c:forEach items="${productBrands}" var="productBrand">
	               		<span>${productBrand.name}&nbsp;<a href="javascript:;" style="color: blue;" name="deleteProductBrand" productBrandId="${productBrand.id}">删</a>&nbsp;&nbsp;&nbsp;</span>
               		</c:forEach>
               		</div>
               </td>
			</tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
	        </c:if>
	        
	        <c:if test="${moduleType == 9}">
			<div>
				<div style="float: left;height: 30px;">
					
						<input type="file" id="picFile" name="file" onchange="ajaxFileUploadHDImgNine('picFile');" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
				
				</div>
				<input type="button" style="float:right;width: 80px;height: 35px;cursor: pointer;" value="保存" id="confirm">
				<br>
				<br>
				<div class="box-con">
					<div class="box-svg">
						<img src="${pageContext.request.contextPath}/file_servelt${decorateModule.pic}" style="width: 750px;height: 500px;<c:if test="${empty decorateModule.pic}">display: none;</c:if>" id="modulePic">
					<c:forEach items="${moduleMapJSONArray}" var="moduleMapJsonObject">	
						<div class="box-frame" data-x1="${moduleMapJsonObject.x1}" data-y1="${moduleMapJsonObject.y1}" data-x2="${moduleMapJsonObject.x2}" data-y2="${moduleMapJsonObject.y2}" data-linktype="${moduleMapJsonObject.linkType}" data-linkvalue="${moduleMapJsonObject.linkValue}" 
						data-fontSize="${moduleMapJsonObject.fontSize}" data-fontColor="${moduleMapJsonObject.fontColor}" data-countDownBeginDate="${moduleMapJsonObject.countDownBeginDate}" data-countDownEndDate="${moduleMapJsonObject.countDownEndDate}"  style="left: ${moduleMapJsonObject.x1}%; top: ${moduleMapJsonObject.y1}%; width: ${moduleMapJsonObject.width}%; height: ${moduleMapJsonObject.height}%;">
							<a class="box-dog" data-dog="0"></a>
							<a class="box-dog" data-dog="1"></a>
							<a class="box-dog" data-dog="2"></a>
							<a class="box-dog" data-dog="3"></a>
							<div><a class="box-delete"></a><a class="box-popup"></a></div>
						</div>
					</c:forEach>	
					</div>
				</div>
				<input id="pic" name="pic" type="hidden" value="${decorateModule.pic}">
			</div>
			<br>	
			</c:if>
	        
	        
	        
	        
	        
	        <c:if test="${moduleType == 10}">
			<table class="gridtable">
           	<tr>
               <td class="title">栏位字体颜色</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="fieldFontColor" name="fieldFontColor" value="${decorateModule.fieldFontColor}">
               </td>
			</tr>
			<tr>
               <td class="title">栏位选中字体颜色</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="fieldSelectFontColor" name="fieldSelectFontColor" value="${decorateModule.fieldSelectFontColor}">
               </td>
			</tr>
			<tr>
               <td class="title">栏位背景图片</td>
               <td colspan="2" align="left" class="l-table-edit-td">
				   <div>
					   <img id="fieldBgPicImg" style="width: 200px;height: 30px" <c:if test="${not empty decorateModule.fieldBgPic}">src="${pageContext.request.contextPath}/file_servelt${decorateModule.fieldBgPic}"</c:if> alt="" >
				   </div>
				   <span style="float: left;height: 30px;margin-left:210px;margin-top:-25px;">
					   <input style="position:absolute; opacity:0;" type="file" id="fieldBgPicFile" name="file" data_value="industryLicense_viewer" onchange="ajaxFileUploadFieldBgImg('fieldBgPicFile');" />
					   <a href="javascript:void(0);" style="width:30%;">上传图片</a>
				   </span>
				   <input type="hidden" id="fieldBgPic" name="fieldBgPic" value="${decorateModule.fieldBgPic}">
               </td>
			</tr>
			<tr>
               <td class="title">栏位背景颜色</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="fieldBgColor" name="fieldBgColor" value="${decorateModule.fieldBgColor}">
               </td>
			</tr>
			<tr>
               <td class="title">展开字体颜色</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="openFontColor" name="openFontColor" value="${decorateModule.openFontColor}">
               </td>
			</tr>
			<tr>
               <td class="title">展开栏位背景颜色</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="openFieldBgColor" name="openFieldBgColor" value="${decorateModule.openFieldBgColor}">
               </td>
			</tr>
			<tr>
               <td class="title">展开按钮背景颜色</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="openBtnBgColor" name="openBtnBgColor" value="${decorateModule.openBtnBgColor}">
               </td>
			</tr>
			<tr>
               <td class="title">展开按钮箭头颜色</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="radio" name="openBtnArrowColor" value="1" <c:if test="${decorateModule.openBtnArrowColor == 1}">checked="checked"</c:if>>黑色
               		<input type="radio" name="openBtnArrowColor" value="2" <c:if test="${decorateModule.openBtnArrowColor == 2}">checked="checked"</c:if>>白色
               </td>
			</tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
	        </c:if>
	        <c:if test="${moduleType == 13}">
			<div id="videoDiv" style="width:750px;">
				<div style="float: left;height: 30px;">
					<input type="file" id="picFile" name="file" onchange="ajaxFileUploadVideoCover('picFile');" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<div style="float: left;height: 30px;">
					<input type="file" id="introduceVideo" name="file" onchange="ajaxFileUploadattachment(this);" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
					<a href="javascript:void(0);" style="width:30%;">上传视频</a>
				</div>
					<input type="button" style="float:right;width: 80px;height: 35px;cursor: pointer;" value="保存" id="confirm">
				<img src="${pageContext.request.contextPath}/file_servelt${decorateModule.pic}" style="width: 750px;<c:if test="${empty decorateModule.pic}">display: none;</c:if>" id="modulePic">
				<input id="pic" name="pic" type="hidden" value="${decorateModule.pic}">
				&nbsp;
				<c:if test="${decorateModule.videoPath ne null}">
					<video id="video" src="${pageContext.request.contextPath}/file_servelt${decorateModule.videoPath}" style="width: 750px;" controls="controls">
						您的浏览器不支持该视频,推荐使用火狐浏览器
					</video>
				</c:if>
				<c:if test="${decorateModule.videoPath eq null}">
					<video id="video" style="width: 750px;" controls="controls">
						您的浏览器不支持该视频,推荐使用火狐浏览器
					</video>
				</c:if>
	    		<input id="videoPath" name="videoPath" type="hidden" value="${decorateModule.videoPath}">
			</div>
			<br>	
			</c:if>
	        <c:if test="${moduleType == 14}">
	        <table class="gridtable">
           	<tr>
               <td class="title">图文</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<textarea name="graphicContent" id="graphicContent" style="width:150px;height:300px;visibility:hidden;">${decorateModule.graphicContent}</textarea>
               		<%-- <textarea name="graphicContent" id="graphicContent" rows=3 cols="90" class="text" ><%=htmlspecialchars(htmlData)%>${decorateModule.graphicContent}</textarea> --%>
               </td>
			</tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
	        </c:if>
</form>
<div class="video_box" style="position:fixed; width:750px; height:500px; top:5%; left:20%; display: none;border-radius: 2px;overflow-y:auto;" id="addBrandDiv">
	<a href="javascript:;" class="video_close" style=""><img src="${pageContext.request.contextPath}/images/close.png"></a>
	<div class="panel panel-default" style="margin-bottom:0px;">
		<div class="modal-header">
			<h3 class="modal-title">
				添加品牌
			</h3>
		</div>
		<table class="gridtable">
	           	<tr>
	               <td class="title">一级分类</td>
	               <td colspan="2" align="left" class="l-table-edit-td">
	               	<select id="add1" name="add1">
	               		
	               	</select>
	               </td>
				</tr>
				<tr>
	               <td class="title">二级分类</td>
	               <td colspan="2" align="left" class="l-table-edit-td">
	               	<select id="add2" name="add2" multiple="multiple" style="width: 200px;height: 200px;">
	               		
	               	</select>
	               	<br>
	               	<br>
	               	<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索品牌" onclick="searchBrand();">
	               </td>
				</tr>
				<tr>
	               <td class="title">品牌</td>
	               <td colspan="2" align="left" class="l-table-edit-td" id="brandList" style="height:150px;">
	               		
	               </td>
				</tr>
		 </table>
	 </div>
</div>
<!--弹出div End-->
<div class="black_box" style="display: none;"></div>	  
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
<script type="text/javascript">
var linkTypeHtml="";
var linkValueHtml="";
var linkTypeList="";
var linkValueList="";
var linkValueURLList="";
var linkValueHtmls="";



$.ajax({
	url : "${pageContext.request.contextPath}/linkType/moduleMap/getLinkTypeList.shtml",
	type : 'POST',
	dataType : 'json',
	data : {showType:'${showType}'},
	cache : false,
	async : false,
	success : function(data) {
			linkTypeList = data.linkTypeList;
			linkValueList = data.linkValueList;
			linkValueURLList = data.linkValueURLList;
	},
	error : function() {
		$.ligerDialog.error('操作超时，请稍后再试！');
	}
});

	var box_obj = {
			x1: 0, // 距左%
			y1: 0, // 距上%
			x2: 0,
			y2: 0,
			src: '',
			href: '',
			title: ''
		},
		box_min = 40,

		box_img = '',
		box_frame = '',
		box_dbframe = '',
		box_dog = '',

		box_down = false,
		box_move = false,
		box_edit = false,
		box_data = false,
		box_dont = false,
		box_drag = false,

		box_dx = 0,
		box_dy = 0,

		box_body = document.body,
		box_con = test_box.querySelector('.box-con'),
		box_svg = test_box.querySelector('.box-svg'),
		box_file = test_box.querySelector('input[type=file]'),

		box_bw = getStyle(box_con, 'width'),
		box_bh = getStyle(box_con, 'height');

	var winSession = window.sessionStorage,
		box_header = test_box.querySelector('.box-header'),
		box_article = box_body.querySelector('.box-article'),
		box_objs = winSession.getItem('box_objs');
	<c:if test="${not empty decorateModule.pic}">
		box_img = $("#modulePic")[0];
		box_down = true;
	</c:if>
	function getStyle (elem, style) {
		return parseInt(elem.currentStyle ? elem.currentStyle[style] : getComputedStyle(elem)[style]);
	}
	
	function closest(e, s) {
		var matchesSelector = e.matches || e.webkitMatchesSelector || e.mozMatchesSelector || e.msMatchesSelector;
	
		while (e) {
			if (matchesSelector.call(e, s)) {
				break;
			}
			e = e.parentElement;
		}
		return e;
	}

	//图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];  
        if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg|gif|GIF)$/.test(file.type)){  
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        var size = file.size;
        if(size > 200*1024 ) {
        	commUtil.alertError("图片过大，图片大小不能超过200KB,请重新上传！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		var widthStr = '750';
        		var moduleType = "${moduleType}";
        		if(this.width != widthStr && moduleType!="8") {
        			commUtil.alertError("图片宽度不是"+widthStr+"像素！");
            	}else{
            		ajaxFileUpload();
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}
	//750px栏位背景图片格式验证,不限制大小
	function ajaxFileUploadFieldBgImg(statusImg) {
        var file = document.getElementById(statusImg).files[0];
        if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg|gif|GIF)$/.test(file.type)){
            commUtil.alertError("图片格式不正确！");
            $("input[name='file']").attr('type','text');
            $("input[name='file']").attr('type','file');
            return;
        }

        var reader = new FileReader();
        reader.onload = function(e) {
            var image = new Image();
            image.onload = function() {
                if(this.width != '750'){
                    commUtil.alertError("图片宽度需为750px！");
                    $("input[name='file']").attr('type','text');
                    $("input[name='file']").attr('type','file');
                    return;
                }else{
                    ajaxFileUploadFieldBgPic();
                }
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);
	}
	//1242PX,HD图片格式验证
	function ajaxFileUploadHDImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];  
        if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
        	commUtil.alertError("格式错误，请上传.jpg .png的图片！");
            return;
        }
        var size = file.size;
        if(size > 200*1024 ) {
        	commUtil.alertError("图片大小超过200kb,请重新上传！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		var widthStr = '1242';
        		var moduleType = "${moduleType}";
        		if(this.width != widthStr && moduleType!="8") {
        			commUtil.alertError("图片尺寸错误，请上传宽度为"+widthStr+"PX的图片！");
            	}else{
            		ajaxFileUpload();
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}
	
	//1242PX,HD图片格式验证 500kb
	function ajaxFileUploadHDImgNine(statusImg) {
		var file = document.getElementById(statusImg).files[0];  
        if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
        	commUtil.alertError("格式错误，请上传.jpg .png的图片！");
            return;
        }
        var size = file.size;
        if(size > 500*1024 ) {
        	commUtil.alertError("图片大小超过500kb,请重新上传！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		var widthStr = '1242';
        		var moduleType = "${moduleType}";
        		
        		if(this.width < widthStr && moduleType=="9") {
        			commUtil.alertError("图片尺寸错误，请上传宽度大于"+widthStr+"PX的图片！");
            	}else{
            		ajaxFileUpload();
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}

	function ajaxFileUploadFieldBgPic() {
		$.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "fieldBgPicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#fieldBgPicImg").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
					$("#fieldBgPic").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});

	}

	function ajaxFileUpload() {
		$.ajaxFileUpload({
			url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
			secureuri: false,
			fileElementId: "picFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#modulePic").css("display","block");
					$(".box-svg").find("img").attr("src","${pageContext.request.contextPath}/file_servelt"+result.FILE_PATH);
					<c:if test="${not empty moduleMapJSONArray}">
					var moduleMapJSONArray = ${moduleMapJSONArray};
					var moduleMapDivHtml = [];
					for(var i=0;i<moduleMapJSONArray.length;i++){
						var moduleMapJsonObject = moduleMapJSONArray[i];
						var tmpStr = '<div class="box-frame" data-x1="'+moduleMapJsonObject.x1+'" data-y1="'+moduleMapJsonObject.y1+'" data-x2="'+moduleMapJsonObject.x2+'" data-y2="'+moduleMapJsonObject.y2+'" data-linktype="'+moduleMapJsonObject.linkType+'" data-linkvalue="'+moduleMapJsonObject.linkValue+'" style="left: '+moduleMapJsonObject.x1+'%; top: '+moduleMapJsonObject.y1+'%; width: '+moduleMapJsonObject.width+'%; height: '+moduleMapJsonObject.height+'%;">'+
										'<a class="box-dog" data-dog="0"></a>'+
										'<a class="box-dog" data-dog="1"></a>'+
										'<a class="box-dog" data-dog="2"></a>'+
										'<a class="box-dog" data-dog="3"></a>'+
										'<div><a class="box-delete"></a><a class="box-popup"></a></div>'+
									'</div>';
						moduleMapDivHtml.push(tmpStr);			
					}
					$(".box-svg").find(".box-frame").each(function(){
						$(this).remove();
					});
					$(".box-svg").append(moduleMapDivHtml.join(""));
					</c:if>
					$("#modulePic").load(function() {
						box_img = $("#modulePic")[0];
						box_down = true;
					});
					$("#pic").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
		
	}
	
	// 鼠标按下
	box_svg.onmousedown = function (e) {
		if (box_down) {
			if (e.target.className == 'box-dog') {
				box_dx = e.x;
				box_dy = e.y;
	
				box_dog = e.target;
				box_frame = closest(box_dog, '.box-frame');
	
				box_edit = true;
			} else if (e.target.className == 'box-svg') {
				box_obj.x1 = 100 * (e.x - box_svg.getBoundingClientRect().left) / box_img.width,
				box_obj.y1 = 100 * (e.y - box_svg.getBoundingClientRect().top) / box_img.height;
	
				box_frame = document.createElement('div');
				box_frame.className = 'box-frame';
				box_svg.appendChild(box_frame);
	
				box_move = true;
			} else if ((e.target.className == 'box-popup' || e.target.className == 'box-delete') && e.which == 1) {
				box_dont = true;
			} else {
				box_dx = e.x;
				box_dy = e.y;
				box_frame = e.target.className == 'box-frame' ? e.target : closest(e.target, '.box-frame');
	
				box_drag = true;
			}
		}
	}
	
	// 鼠标移动
	box_svg.onmousemove = function (e) {
		if (box_down && box_frame) {
			if (box_move) {
				// 创建
				box_obj.x2 = 100 * (e.x - box_svg.getBoundingClientRect().left) / box_img.width,
				box_obj.y2 = 100 * (e.y - box_svg.getBoundingClientRect().top) / box_img.height;
			} else if (box_drag) {
				// 拖动
				var _x = 100 * (e.x - box_dx) / box_img.width,
					_y = 100 * (e.y - box_dy) / box_img.height,
					_x1 = parseInt(box_frame.getAttribute('data-x1')),
					_y1 = parseInt(box_frame.getAttribute('data-y1')),
					_x2 = parseInt(box_frame.getAttribute('data-x2')),
					_y2 = parseInt(box_frame.getAttribute('data-y2'));
	
				box_obj.x1 = _x1 + _x;
				box_obj.x2 = _x2 + _x;
				box_obj.y1 = _y1 + _y;
				box_obj.y2 = _y2 + _y;
	
				box_obj.x1 < 0 && (box_obj.x1 = 0);
				box_obj.x2 < 0 && (box_obj.x2 = 0);
				box_obj.y1 < 0 && (box_obj.y1 = 0);
				box_obj.y2 < 0 && (box_obj.y2 = 0);
	
				box_obj.x1 > 100 && (box_obj.x1 = 100);
				box_obj.x2 > 100 && (box_obj.x2 = 100);
				box_obj.y1 > 100 && (box_obj.y1 = 100);
				box_obj.y2 > 100 && (box_obj.y2 = 100);
			} else if (box_edit) {
				// 编辑
				var _x = 100 * (e.x - box_dx) / box_img.width,
					_y = 100 * (e.y - box_dy) / box_img.height,
					_x1 = parseInt(box_frame.getAttribute('data-x1')),
					_y1 = parseInt(box_frame.getAttribute('data-y1')),
					_x2 = parseInt(box_frame.getAttribute('data-x2')),
					_y2 = parseInt(box_frame.getAttribute('data-y2')),
					_dog = parseInt(box_dog.getAttribute('data-dog'));
	
				if (_dog == 0) {
					if (_x1 > _x2) {
						box_obj.x1 = _x1;
						box_obj.y1 = _y1;
						box_obj.x2 = _x2 + _x;
						box_obj.y2 = _y2 + _y;
					} else {
						box_obj.x1 = _x1 + _x;
						box_obj.y1 = _y1 + _y;
						box_obj.x2 = _x2;
						box_obj.y2 = _y2;
					}
				} else if (_dog == 1) {
					if (_x1 > _x2) {
						box_obj.x1 = _x1 + _x;
						box_obj.y1 = _y1;
						box_obj.x2 = _x2;
						box_obj.y2 = _y2 + _y;
					} else {
						box_obj.x1 = _x1;
						box_obj.y1 = _y1 + _y;
						box_obj.x2 = _x2 + _x;
						box_obj.y2 = _y2;
					}
				} else if (_dog == 2) {
					if (_x1 > _x2) {
						box_obj.x1 = _x1 + _x;
						box_obj.y1 = _y1 + _y;
						box_obj.x2 = _x2;
						box_obj.y2 = _y2;
					} else {
						box_obj.x1 = _x1;
						box_obj.y1 = _y1;
						box_obj.x2 = _x2 + _x;
						box_obj.y2 = _y2 + _y;
					}
				} else if (_dog == 3) {
					if (_x1 > _x2) {
						box_obj.x1 = _x1;
						box_obj.y1 = _y1 + _y;
						box_obj.x2 = _x2 + _x;
						box_obj.y2 = _y2;
					} else {
						box_obj.x1 = _x1 + _x;
						box_obj.y1 = _y1;
						box_obj.x2 = _x2;
						box_obj.y2 = _y2 + _y;
					}
				}
	
				box_obj.x1 < 0 && (box_obj.x1 = 0);
				box_obj.x2 < 0 && (box_obj.x2 = 0);
				box_obj.y1 < 0 && (box_obj.y1 = 0);
				box_obj.y2 < 0 && (box_obj.y2 = 0);
	
				box_obj.x1 > 100 && (box_obj.x1 = 100);
				box_obj.x2 > 100 && (box_obj.x2 = 100);
				box_obj.y1 > 100 && (box_obj.y1 = 100);
				box_obj.y2 > 100 && (box_obj.y2 = 100);
			}
	
			if (box_move || box_drag || box_edit) {
				box_frame.style.cssText = 'left: ' + Math.min(box_obj.x1, box_obj.x2) + '%; top: ' + Math.min(box_obj.y1, box_obj.y2) + '%; width: ' + Math.abs(box_obj.x2 - box_obj.x1) + '%; height: ' + Math.abs(box_obj.y2 - box_obj.y1) + '%;';
	
				box_data = true;
			}
		}
	}
	
	// 鼠标松开
	document.onmouseup = function (e) {
		if (!box_data && e.target.className == 'box-popup' && box_dont) {
			box_dbframe = e.target.parentNode.parentNode;
			openMapInfo();
		} else if (!box_data && e.target.className == 'box-delete' && box_dont) {
			var _parents = e.target.parentNode.parentNode;
			_parents.parentNode.removeChild(_parents);
		} else if (box_down && (box_move || box_drag || box_edit)) {
			if (Math.min(getStyle(box_frame, 'width'), getStyle(box_frame, 'height')) < box_min) {
				box_frame.parentNode.removeChild(box_frame);
			} else if (box_data) {
				box_frame.setAttribute('data-x1', box_obj.x1);
				box_frame.setAttribute('data-y1', box_obj.y1);
				box_frame.setAttribute('data-x2', box_obj.x2);
				box_frame.setAttribute('data-y2', box_obj.y2);
	
				box_move && (box_frame.innerHTML = '<a class="box-dog" data-dog="0"></a><a class="box-dog" data-dog="1"></a><a class="box-dog" data-dog="2"></a><a class="box-dog" data-dog="3"></a><div><a class="box-delete"></a><a class="box-popup"></a></div>');
			}
		}
	
		box_move = false;
		box_drag = false;
		box_edit = false;
		box_data = false;
		box_dont = false;
		box_dog = '';
		box_frame = '';
	}
	
	// 禁止拖动
	document.ondragstart = function() {
		return false;
	}
	
	// 禁止右键弹出菜单
	box_svg.oncontextmenu = function() {
		return false;
	}
	
	// 打开map信息窗口
	function openMapInfo(_this) {
		var $linkValueDiv = $(_this).parent().next();

		if(_this){
			box_dbframe = _this.parentNode.parentNode;
		}
		var linkType = box_dbframe.getAttribute('data-linkType') || '',
			linkValue = box_dbframe.getAttribute('data-linkValue') || '';
			fontSize = box_dbframe.getAttribute('data-fontSize') || '';
			fontColor = box_dbframe.getAttribute('data-fontColor') || '';
			countDownBeginDate = box_dbframe.getAttribute('data-countDownBeginDate') || '';
			countDownEndDate = box_dbframe.getAttribute('data-countDownEndDate') || '';
			
		var _mask = document.createElement('div');
			
		var html = [];
		html.push('<select name="linkType" id="linkType" onchange="setLinkValueHtml(this)">');
		html.push('<option value="">请选择</option>');
		for(var i=0;i<linkTypeList.length;i++){
			var id = linkTypeList[i].linkType;
			var name = linkTypeList[i].linkTypeName;
			if(linkType && id == linkType){
			    html.push('<option value="'+id+'" selected>'+name+'</option>');
			}else{
			    html.push('<option value="'+id+'">'+name+'</option>');
			}
		}
		html.push('</select>');	
		linkTypeHtml=html.join("");
		if(!linkType){
			linkValueHtml = '<input id="linkValue" name="linkValue" type="text" style="height:35px;" value="">';
		}else{
			if(linkType == "5"){
				var html1 = [];
				html1.push('<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"');
				html1.push('<option value="">请选择</option>');
				for(var i=0;i<linkValueList.length;i++){
					var id = linkValueList[i].linkValue;
					var name = linkValueList[i].linkValueName;
				     if (linkValue==id) {
				    	 html1.push('<option value="'+id+'" selected>'+name+'</option>');
					}else {
						html1.push('<option value="'+id+'">'+name+'</option>');
						
					}
				}
				html1.push('</select>');
	           	linkValueHtml=html1.join("");
			}else if(linkType == "6"){
				linkValueHtml = '<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"><option value="">请选择</option>';
				<c:forEach items="${productTypes}" var="productType">
					var _selected${productType.id}="";
					if(linkValue == ${productType.id}){
						_selected${productType.id} = "selected";
					}
					linkValueHtml+='<option value="${productType.id}" '+_selected${productType.id}+'>${productType.name}</option>';
				</c:forEach>
				linkValueHtml+='</select>';
			}else if(linkType == "15"){
				linkValueHtml = '<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"><option value="">请选择</option>';
				<c:forEach items="${brandteamTypes}" var="brandteamType">
					var _selected="";
					var brandteamTypeId = ${brandteamType.id};
					if(linkValue == brandteamTypeId){
						_selected = "selected";
					}
					linkValueHtml+='<option value="${brandteamType.id}" '+_selected+'>${brandteamType.name}</option>';
				</c:forEach>
				linkValueHtml+='</select>';
			}else if(linkType == "12"){
				linkValueHtml = '<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"><option value="">请选择</option>';
				<c:forEach items="${mallCategorys}" var="mallCategory">
					var _selected="";
					var mallCategoryId = ${mallCategory.id};
					if(linkValue == mallCategoryId){
						_selected = "selected";
					}
					linkValueHtml+='<option value="${mallCategory.id}" '+_selected+'>${mallCategory.categoryName}</option>';
				</c:forEach>
				linkValueHtml+='</select>';

			}else if(linkType == "34"){//功能链接
				var html1 = [];
				html1.push('<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"');
				html1.push('<option value="">请选择</option>');
				for(var i=0;i<linkValueURLList.length;i++){
					var id = linkValueURLList[i].linkValue;
					var name = linkValueURLList[i].linkValueName;
					if (linkValue==id) {
						html1.push('<option value="'+id+'" selected>'+name+'</option>');
					}else {
						html1.push('<option value="'+id+'">'+name+'</option>');

					}
				}
				html1.push('</select>');
				linkValueHtml=html1.join("");

			}else if(linkType == "33"){
				    //linkValueHtml = '<input id="linkValue" name="linkValue" type="text" style="height:35px;" value="'+linkValue+'">';
					$("#fontSize").val(fontSize);
					$("#fontColor").val(fontColor);
				/* 	var a = $("#xiangqin").;
					var b = $("#fontSize").parent('div').prev('div').hide();
					console.log(a);
					console.log(b); */

			}else{
				linkValueHtml = '<input id="linkValue" name="linkValue" type="text" style="height:35px;" value="'+linkValue+'">';
			}
		}
		
		_mask.className = 'box-mask show';
		_mask.setAttribute('onclick', 'closeMapInfo(this)');
		_mask.innerHTML = '<div class="pop-con">'
			+ '<h3>设置链接</h3>'
			+ '<div class="mask-close" style="margin-top:-42px;"><a onclick="closeMapInfo(this, 1)"></a></div>'
			+ '<div class="pop-input flex ac">'
				// 调试链接地址
				+'&nbsp;&nbsp;&nbsp;&nbsp;'
				+ '<label for="pop_href">类型:</label>'
				+ linkTypeHtml
			+ '</div>'
			
			+ '<div class="pop-input flex ac" id = "xiangqin">'
				// 调试链接标题
			+ '<label for="pop_title">详细: </label>'
			+ linkValueHtml
			+ '</div>' 

 			+ '<div class="pop-input32"  id=font style="display:none;">'
			// 调试链接标题
			+ '<label >字体颜色: </label>'
			+ '<input id="fontColor" name="fontColor"   type="text" style="height:35px;" value="'+fontColor+'">'
			+ '<br><label >字体大小: </label>'
			+ '<input id="fontSize" name="fontSize"    type="text" style="height:35px;" value="'+fontSize+'" >'
			+ '<br><label>开始时间: </label>'
			+ '<input id="countDownBeginDate" name="countDownBeginDate" type="text" style="height:35px;border:1px solid gray;" value="'+countDownBeginDate+'" >'
			
			+ '<br><label>结束时间: </label>'
			+ '<input id="countDownEndDate" name="countDownEndDate" type="text" style="height:35px;border:1px solid gray" value="'+countDownEndDate+'" >'
			
		    + '</div>'
		     
			+ '<div class="pop-input flex ac jc">'
				+ '<button class="box-btn flex jc ac" style="cursor:pointer;" onclick="saveMapInfo(this)">提交</button>'
			+ '</div>'
		+ '</div>'; 
	
		box_body.appendChild(_mask);
		 $("#countDownBeginDate").ligerDateEditor({
				showTime : true,
				width: 282,
				format: "yyyy-MM-dd hh:mm:ss"
		});
		 $("#countDownEndDate").ligerDateEditor({
				showTime : true,
				width: 282,
				format: "yyyy-MM-dd hh:mm:ss"
		}); 
		 if(linkType==33){
			 $("#font").show();
			 $("#xiangqin").hide();
		 }
	}
	
	// 关闭map信息窗口
	function closeMapInfo(_this, _a) {
		var evt = window.event || arguments.callee.caller.arguments[0];
		if(_a) {
			box_body.removeChild(closest(_this, '.box-mask'));
		} else {
			evt.target.className == _this.className && box_body.removeChild(_this);
		}
	}
	
	// 保存map信息
	function saveMapInfo(_this) {
		var _pop = closest(_this, '.box-mask');
		// 定位到"类型" (调试中应用id可以忽略此步骤)
		linkType = _pop.querySelector('#linkType');
		
		var fontSize= _pop.querySelector('#fontSize')
		var fontColor= _pop.querySelector('#fontColor')
		var countDownBeginDate= _pop.querySelector('#countDownBeginDate')
		var countDownEndDate= _pop.querySelector('#countDownEndDate')
		if(linkType.value!=33){
			
			// 定位到"详细"
			linkValue = _pop.querySelector('#linkValue');
			if(!linkType.value){
				commUtil.alertError("请选择类型");
				return false;
			}
			if(!linkValue.value){
				commUtil.alertError("详细不能为空");
				return false;
			}
			if(!testNumber(linkValue.value) && linkType.value!=9 && linkType.value!=13){
				commUtil.alertError("只能输入正整数！");
				return false;
			}
		
		}else{
			if(!fontColor.value){
				commUtil.alertError("字体颜色不能为空");
				return false;
			}
			
			if(!fontSize.value){
				commUtil.alertError("字体大小不能为空");
				return false;
			}

			if(!testNumber(fontSize.value)){
				commUtil.alertError("字体大小只能输入正整数！");
				return false;
			}
			if(!countDownBeginDate.value){
				commUtil.alertError("开始时间不能为空");
				return false;
			}
			
			if(!countDownEndDate.value){
				commUtil.alertError("结束时间不能为空");
				return false;
			}
			
			if(countDownBeginDate.value > countDownEndDate.value){
				commUtil.alertError("结束时间不能小于开始时间");
				return false;
			}
			
		
			
		}
		if(linkType.value != 5 && linkType.value != 6 && linkType.value != 9 && linkType.value != 33){//排除5.栏目和6.一级分类和9.url链接
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/checkExists.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {linkType:linkType.value,linkValue:linkValue.value},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						// 保存相关设置 (对保存项[data-]修改时, 注意同时修改保存所有map信息中的相关项)
						box_dbframe.setAttribute('data-linkType', linkType.value);
						box_dbframe.setAttribute('data-linkValue', linkValue.value);
//						box_dbframe.title = pop_title.value;
					
						box_body.removeChild(_pop);
					}else{
						commUtil.alertError(data.returnMsg);
						return false;
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else if(linkType.value == 33){
			// 保存相关设置 (对保存项[data-]修改时, 注意同时修改保存所有map信息中的相关项)
			box_dbframe.setAttribute('data-linkType', linkType.value);
			box_dbframe.setAttribute('data-fontSize', fontSize.value);
			box_dbframe.setAttribute('data-fontColor', fontColor.value);
			box_dbframe.setAttribute('data-countDownBeginDate', countDownBeginDate.value);
			box_dbframe.setAttribute('data-countDownEndDate', countDownEndDate.value);
			
			box_body.removeChild(_pop);
		}else{
			// 保存相关设置 (对保存项[data-]修改时, 注意同时修改保存所有map信息中的相关项)
			box_dbframe.setAttribute('data-linkType', linkType.value);
			box_dbframe.setAttribute('data-linkValue', linkValue.value);
			
//			box_dbframe.title = pop_title.value;
		
			box_body.removeChild(_pop);
			
		}
	}
	
	// 保存所有map信息
	test_box.onsubmit = function (e) {
		e.preventDefault();
	
		var box_map = [],
			box_frames = box_svg.querySelectorAll('.box-frame');
	
		if (box_obj.src) {
			for (var i = 0; i < box_frames.length; i++) {
				box_map.push({
					x1: box_frames[i].getAttribute('data-x1'),
					y1: box_frames[i].getAttribute('data-y1'),
					x2: box_frames[i].getAttribute('data-x2'),
					y2: box_frames[i].getAttribute('data-y2'),
					href: box_frames[i].getAttribute('data-href'),
					title: box_frames[i].getAttribute('data-title')
				});
			}
	
			box_map = {
				src: box_obj.src,
				map: box_map
			};
	
			winSession.setItem('box_objs', JSON.stringify(box_map));
		}
	}
	
	//下拉框选择
	function setLinkValueHtml(_this){
		var linkType = $(_this).val();
		var linkName = $(_this).find("option:selected").text();
		
		var $linkValueDiv = $(_this).parent().next();
		$linkValueDiv.find("input").remove();
		$linkValueDiv.find("select").remove();
		
		var html;
		if(linkType == 5){
			$linkValueDiv.show();
			$linkValueDiv.next().hide();
			html = '<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"><option value="">请选择</option>';
			for(var i=0;i<linkValueList.length;i++){
				var id = linkValueList[i].linkValue;
				var name = linkValueList[i].linkValueName;			
				html+='<option value="'+id+'">'+name+'</option>';
			}
			html+='</select>';
		}else if(linkType == 6){
			$linkValueDiv.show();
			$linkValueDiv.next().hide();
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			<c:forEach items="${productTypes}" var="productType">
				html+='<option value="${productType.id}">${productType.name}</option>';
			</c:forEach>
			html+='</select>';
		}else if(linkType == 15){//新品牌团
			$linkValueDiv.show();
			$linkValueDiv.next().hide();
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			<c:forEach items="${brandteamTypes}" var="brandteamType">
				html+='<option value="${brandteamType.id}">${brandteamType.name}</option>';
			</c:forEach>
			html+='</select>';
		}else if(linkType == 12){//商城一级分类
			$linkValueDiv.show();
			$linkValueDiv.next().hide();
			html = '<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"><option value="">请选择</option>';
			<c:forEach items="${mallCategorys}" var="mallCategory">
				html+='<option value="${mallCategory.id}">${mallCategory.categoryName}</option>';
			</c:forEach>
			html+='</select>';

		}else if(linkType == 34){//功能链接
			$linkValueDiv.show();
			$linkValueDiv.next().hide();
			html = '<select name="linkValue" id="linkValue" style="position:absolute;margin-left: 65px;" onmousedown="if(this.options.length>5){this.size=6}" onblur="this.size=0" onchange="this.size=0"><option value="">请选择</option>';
			for(var i=0;i<linkValueURLList.length;i++){
				var id = linkValueURLList[i].linkValue;
				var name = linkValueURLList[i].linkValueName;
				html+='<option value="'+id+'">'+name+'</option>';
			}
			html+='</select>';

		}else if(linkType == "33"){
			$linkValueDiv.hide();
			$linkValueDiv.next().show(); 

		}else{
			$linkValueDiv.show();
			$linkValueDiv.next().hide();
			var tipName = "请输入"+linkName;
			   html = '<input id="linkValue" name="linkValue"  placeholder="'+tipName+'" type="text" style="height:35px;" value="">';
		}
		$linkValueDiv.append(html);
	}
	
	//限制上传视频附件大小
	function ajaxFileUploadattachment(obj) {
		var file = obj.files[0];
		var patrn = ["mp4","MP4"];
		var FileArr=file.name.split(".");
		var FileExt=FileArr[FileArr.length-1];
	 	if(patrn.indexOf(FileExt) == -1){  
        	commUtil.alertError("格式错误，请上传mp4的视频！");
            return;
        }
	    var reader = new FileReader();  
	    reader.onload = function(e) { 
	    	var filesize = file.size;
	    		if(filesize>1024*1024*100) {
	    			commUtil.alertError("视频大小超过100M，请重新上传！");
	        	}else {
	    			ajaxVideoFileUpload(obj);
	    		}
	    },
	    reader.readAsDataURL(file);
	}

	function ajaxVideoFileUpload(obj) {
	    var file = obj.files[0];
	    var reader = new FileReader();
	    reader.onload = function(e) {
	    	$.ajaxFileUpload({
	    		url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
	    		secureuri: false,
	    		fileElementId: "introduceVideo",
	    		dataType: 'json',
	    		success: function(result, status) {
	    			if(result.RESULT_CODE == 0){
	    			   var filePath = result.FILE_PATH;
		               $("#videoPath").val(filePath);
		               $("#video").attr("src",'${pageContext.request.contextPath}/file_servelt'+filePath);
		               commUtil.alertSuccess("文件上传成功");
	   			  	}else{
	   			  		alert(result.RESULT_MESSAGE);
	   			  	}
	    		},
	    		error: function(e) {
	    			alert("服务异常");
	    		}
	    	});
	    };
	    reader.readAsDataURL(file);  
	}
	
	//视频封面上传
	function ajaxFileUploadVideoCover(statusImg) {
		var file = document.getElementById(statusImg).files[0];  
        if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg)$/.test(file.type)){  
        	commUtil.alertError("格式错误，请上传.jpg .png的图片！");
            return;
        }
        var size = file.size;
        if(size > 200*1024 ) {
        	commUtil.alertError("图片大小超过200kb,请重新上传！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        		var widthStr = '1242';
        		var moduleType = "${moduleType}";
        		if(this.width != widthStr && moduleType!="8") {
        			commUtil.alertError("图片尺寸错误，请上传宽度为"+widthStr+"PX的图片！");
            	}else{
            		ajaxVideoCoverUpload();
            	}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
	}
		
	function ajaxVideoCoverUpload() {
		$.ajaxFileUpload({
			url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
			secureuri: false,
			fileElementId: "picFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#modulePic").attr("src","${pageContext.request.contextPath}/file_servelt"+result.FILE_PATH);
					$("#modulePic").attr("style","width: 750px;");
					$("#pic").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
		
	}

</script>   
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>