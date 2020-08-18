<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>盖章示范</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
<style type="text/css">
        .td-pictures li{
            display: inline-block;
        }
        .td-pictures li img{
            width: 100px;
            height: 100px;
        }
    </style>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <input type="hidden" id="mchtContractId" value="${id}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">盖章示范</span>
      </div>
     <div class="modal-body" style="height: 180px;">
     	 <div class="pic-container">
              <ul class="docs-pictures clearfix td-pictures pictures-list">
                  <li class="pic_li">
                      <img  src="${ctx}/static/images/合同骑缝章.jpg" onclick="viewerPic(this.src,this)">
                  </li>
                  <li class="pic_li">
                      <img  src="${ctx}/static/images/合同首页章.jpg" onclick="viewerPic(this.src,this)">
                  </li>
                  <li class="pic_li">
                      <img  src="${ctx}/static/images/合同尾页章.jpg" onclick="viewerPic(this.src,this)">
                  </li>
              </ul>
          </div>
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
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});
</script>
</body>
</html>
