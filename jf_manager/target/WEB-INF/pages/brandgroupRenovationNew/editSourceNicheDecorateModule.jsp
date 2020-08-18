<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
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
			top: 50%;
			left: 50%;
			width: 5rem;
			height: 2.4rem;
			margin: -1rem 0 0 -2.5rem;
			background: #fff;
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
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#confirm").bind('click',function(){
		var decorateAreaId = $("#decorateAreaId").val();
		var decorateModuleId = $("#decorateModuleId").val();
		var moduleType = $("#moduleType").val();
		var seqNo = $("#seqNo").val();
		<c:if test="${moduleType == 1 || moduleType == 9}">
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
	});
	
});

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}
</script>

<html>
<body>
<form method="post" id="test_box" name="form" action="${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml">
			<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
			<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateAreaId}">
			<input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${decorateModuleId}">
			<input type="hidden" id="moduleType" name="moduleType" value="${moduleType}">
			<input type="hidden" id="seqNo" name="seqNo" value="${seqNo}">
			<c:if test="${moduleType == 1 || moduleType == 9}">
			<div>
				<div style="float: left;height: 30px;">
					<input type="file" id="picFile" name="file" onchange="ajaxFileUploadImg('picFile');" style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
				</div>
				<input type="button" style="float:right;width: 80px;height: 35px;cursor: pointer;" value="保存" id="confirm">
				<br>
				<br>
				<div class="box-con">
					<div class="box-svg">
						<img src="${pageContext.request.contextPath}/file_servelt${decorateModule.pic}" style="<c:if test="${empty decorateModule.pic}">display: none;</c:if>" id="modulePic">
					<c:forEach items="${moduleMapJSONArray}" var="moduleMapJsonObject">	
						<div class="box-frame" data-x1="${moduleMapJsonObject.x1}" data-y1="${moduleMapJsonObject.y1}" data-x2="${moduleMapJsonObject.x2}" data-y2="${moduleMapJsonObject.y2}" data-linktype="${moduleMapJsonObject.linkType}" data-linkvalue="${moduleMapJsonObject.linkValue}" style="left: ${moduleMapJsonObject.x1}%; top: ${moduleMapJsonObject.y1}%; width: ${moduleMapJsonObject.width}%; height: ${moduleMapJsonObject.height}%;">
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
</form>	  
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
<script type="text/javascript">
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
        		var widthStr = '1242';
        		var moduleType = ${moduleType};
        		if(moduleType == 1){
        			if(this.width != widthStr){
        				commUtil.alertError("图片宽度不是"+widthStr+"像素！");
        			}else{
        				ajaxFileUpload();
        			}
        		}else if(moduleType == 9){
        			if(this.width <= 1242){
        				commUtil.alertError("图片宽度需>"+widthStr+"像素！");
        			}else if(this.height != 576){
        				commUtil.alertError("图片高度只能是576像素！");
        			}else{
        				ajaxFileUpload();
        			}
        		}
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);  
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
		if(_this){
			box_dbframe = _this.parentNode.parentNode;
		}
		var linkType = box_dbframe.getAttribute('data-linkType') || '',
			linkValue = box_dbframe.getAttribute('data-linkValue') || '';
		var _mask = document.createElement('div');
		var linkTypeHtml;
		var linkValueHtml;
		if(!linkType){
			linkTypeHtml = '<select name="linkType" id="linkType" onchange="setLinkValueHtml(this)"><option value="">请选择</option><option value="1">会场ID</option><option value="2">特卖ID</option><option value="3">商品ID</option><option value="4">自建页面ID</option><option value="9">url链接</option></select>';
			linkValueHtml = '<input id="linkValue" name="linkValue" type="text" style="height:35px;" value="">';
		}else{
			var _selected1="",_selected2="",_selected3="",_selected4="",_selected9="";
			if(linkType == "1"){
				_selected1 = "selected";
			}else if(linkType == "2"){
				_selected2 = "selected";
			}else if(linkType == "3"){
				_selected3 = "selected";
			}else if(linkType == "4"){
				_selected4 = "selected";
			}else if(linkType == "9"){
				_selected9 = "selected";
			}
			linkTypeHtml = '<select name="linkType" id="linkType" onchange="setLinkValueHtml(this)"><option value="">请选择</option><option value="1" '+_selected1+'>会场ID</option><option value="2" '+_selected2+'>特卖ID</option><option value="3" '+_selected3+'>商品ID</option><option value="4" '+_selected4+'>自建页面ID</option><option value="9" '+_selected9+'>url链接</option></select>';
			linkValueHtml = '<input id="linkValue" name="linkValue" type="text" style="height:35px;" value="'+linkValue+'">';
		}
		_mask.className = 'box-mask show';
		_mask.setAttribute('onclick', 'closeMapInfo(this)');
		_mask.innerHTML = '<div class="pop-con">'
			+ '<div class="mask-close"><a onclick="closeMapInfo(this, 1)"></a></div>'
			+ '<div class="pop-input flex ac">'
				// 调试链接地址
				+ '<label for="pop_href">类型: </label>'
				+ linkTypeHtml
			+ '</div>'
			+ '<div class="pop-input flex ac">'
				// 调试链接标题
				+ '<label for="pop_title">详细: </label>'
				+ linkValueHtml
			+ '</div>'
	
			+ '<div class="pop-input flex ac jc">'
				+ '<button class="box-btn flex jc ac" style="cursor:pointer;" onclick="saveMapInfo(this)">保存</button>'
			+ '</div>'
		+ '</div>';
	
		box_body.appendChild(_mask);
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
		if(!testNumber(linkValue.value) && linkType.value!=9){
			commUtil.alertError("只能输入正整数！");
			return false;
		}
		if(linkType.value != 5 && linkType.value != 6 && linkType.value != 9){//排除5.栏目和6.一级分类和9.url链接
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
						commUtil.alertError("ID有误，请输入正确的ID");
						return false;
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
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
		var $linkValueDiv = $(_this).parent().next();
		$linkValueDiv.find("input").remove();
		$linkValueDiv.find("select").remove();
		var html;
		if(linkType == 5){
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option><option value="1">新用户专享</option><option value="2">单品爆款</option><option value="3">限时抢购</option><option value="4">新用户秒杀</option><option value="5">积分商城</option><option value="6">断码清仓</option></* option value="7">签到</option><option value="8">砍价</option><option value="9">邀请免单</option> */<option value="10">有好货</option><option value="11">每日好店</option>/* <option value="12">新品牌团</option> */</select>';
		}else if(linkType == 6){
			html = '<select name="linkValue" id="linkValue"><option value="">请选择</option>';
			<c:forEach items="${productTypes}" var="productType">
				html+='<option value="${productType.id}">${productType.name}</option>';
			</c:forEach>
			html+='</select>';
		}else{
			html = '<input id="linkValue" name="linkValue" type="text" style="height:35px;" value="">';
		}
		$linkValueDiv.append(html);
	}
</script>   
</body>
</html>
