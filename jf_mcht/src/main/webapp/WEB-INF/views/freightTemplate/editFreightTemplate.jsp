<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>编辑运费模板</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.area{
	float: left;
	width: 30%;
}
.video_box {
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
	top: -14px;
	right: -12px;
}
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
</head>

<body>
  <div class="modal-dialog sh-xq ts-xq" role="document" style="width:1000px;">
    <div class="modal-content">
    <input type="hidden" id="freightTemplateId" name="id" value="${freightTemplate.id}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">
        <c:if test="${empty freightTemplate.id}">
        	添加运费模板
        </c:if>
        <c:if test="${not empty freightTemplate.id}">
        	修改运费模板
        </c:if>
        </span>
      </div>
     <div class="modal-body">
     	<div class="row">
     		<div style="margin-left: 10px;margin-bottom: 5px;">模版名称<span style="color: red;">*</span>&nbsp;&nbsp;<input id="freightTemplate_name" name="name" value="${freightTemplate.name}" maxlength="32"></div>
     		<div style="margin-left: 10px;margin-bottom: 5px;">默认运费：首件运费<input id="firstFreight" name="firstFreight" value="${freightTemplate.firstFreight}" data-type="money" style="width: 50px;">元　每增加一件，增件运费<input id="additionalFreight" name="additionalFreight" value="${freightTemplate.additionalFreight}" data-type="money" style="width: 50px;">元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" checked id="isFullReduction" style="vertical-align: middle;">启用运费满减&nbsp;&nbsp;<span id="isFullReductionShow">满<input id="fullNumber" name="fullNumber" value="${freightTemplate.fullNumber}" data-type="money" style="width: 50px;">件,运费<input id="fullReductionFreight" name="fullReductionFreight" value="${freightTemplate.fullReductionFreight}" data-type="money" style="width: 50px;">元</span></div>
     	</div>
		<div class="row">
			<div style="margin-left: 10px;margin-bottom: 5px;">
				<button type="button" class="btn btn-info" id="setProvinceFreight">设置指定省份运费</button>
				<button type="button" class="btn btn-info" id="productNotice">管理商品公告</button>
			</div>
			<div class="col-md-12 datatable-container">
				<table class="table table-striped table-bordered no-footer"
							role="grid" aria-describedby="datatable_info">
					<tbody id="provinceFreightTbody">		
					<tr>
				       <th>运送范围</th>
				       <th>首件运费</th>
				       <th>增件运费</th>
				       <th>运费满减</th>
				       <th>操作</th>
				    </tr>
					<c:forEach items="${provinceFreightCustoms}" var="provinceFreightCustom">
						<tr name="provinceFreightCustomTr" data-provincefreightid="${provinceFreightCustom.id}" data-areaids="${provinceFreightCustom.areaIds}">
					      <td title="${provinceFreightCustom.areaNames}">${provinceFreightCustom.areaNames}</td>
					      <td>${provinceFreightCustom.firstFreight}</td>
					      <td>${provinceFreightCustom.additionalFreight}</td>
							<c:if test="${empty provinceFreightCustom.fullReductionFreight}">
							<td ></td>
							</c:if>
							<c:if test="${provinceFreightCustom.fullReductionFreight eq 0}">
							<td >满${provinceFreightCustom.fullNumber}包邮</td>
							</c:if>
					      <td><a href="javascript:;" onclick="editProvinceFreight(${provinceFreightCustom.id},this)">编辑</a>&nbsp;&nbsp;<a href="javascript:;" onclick="deleteProvinceFreight(${provinceFreightCustom.id},this)">删除</a></td>
    					<tr>
    				</c:forEach>
    				</tbody>	
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-offset-5 col-md-7">
				<button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close" >取消</button>
				<button type="button" class="btn btn-default" id="saveFreightTemplate">保存</button>
			</div>
		</div>
	</div>
  </div>
</div>
  <!--设置指定省份运费Div-->
<div class="video_box" style="position:fixed; width:450px; height:170px; top:35%; left:0; right: 0;margin: 0 auto; display: none;" id="provinceFreightDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	设置指定省份运费
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="padding: 10px 10px 0;" >
		    	  <input id="provinceFreightId" type="hidden">
		    	  <input id="index" type="hidden">
				  <div class="form-group">
				  		<div id="selectAreaDiv" style="margin-left: 10px;margin-bottom: 5px;">
				  		</div>
				  		<div style="margin-left: 10px;margin-bottom: 5px;">
				  			<span style="color: red;">*</span>运费：首件运费<input id="ff" name="ff" value="" data-type="money" style="width: 50px;">元　每增加一件，增件运费<input id="af" name="af" value="" data-type="money" style="width: 50px;">元
				  		</div>

					    <div style="margin-left: 10px;margin-bottom: 5px;" id="isFullReductionProvinceShow">
							<input type="checkbox" checked id="isFullReductionProvince" style="vertical-align: middle;">启用运费满减&nbsp;&nbsp;满<input id="fullNumberProvince" name="fullNumberProvince" value="" data-type="money" style="width: 50px;">件,运费<input id="fullReductionFreightProvince" name="fullReductionFreightProvince" value="" data-type="money" style="width: 50px;">元
					    </div>
				  </div>
				  <div class="modal-footer">
				    	<div class="">
				      		<button type="button" class="btn btn-info" id="cancle">取消</button>
				      		<button type="button" class="btn btn-info" id="confirm">确定</button>
				    	</div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出商品公告Div-->
<div class="video_box" style="position:fixed; width:600px; height:290px; top:35%; left:34%; display: none;border-radius: 2px;" id="productNoticeDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	商品公告
				</h3>
		    </div>
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-14 control-label" style="padding-left:80px;">图片描述*</label>
				    <span style="color: gray;">（图片要求：宽度：800像素，图片总大小不能超过200K。提示：图片默认在商品详情最顶部）</span>
				    <div class="single_pic_picker x1">
	                    <input type="hidden" name="productNoticePic" value="${freightTemplate.productNoticePic}"/>
	                    <input id="productNoticePicFile" onchange="loadImageFile(this)" type="file">
	                    <c:if test="${not empty freightTemplate.productNoticePic}">
	                    	<img src="${ctx}/file_servelt${freightTemplate.productNoticePic}">
	                    </c:if>
	                    <c:if test="${empty freightTemplate.productNoticePic}">
	                    	<div>+</div>
	                    </c:if>
                    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-3 col-md-7" style="padding-left: 60px;">
				      <button type="button" class="btn btn-default" id="save">保存</button>
				      <button type="button" class="btn btn-default" name="delete">删除公告</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>
<!--弹出div End-->
<div class="black_box" style="display: none;" id="freightBlackBox"></div>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
<script type="text/javascript">
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

function loadImageFile(obj) {
    if (obj.files.length === 0) {
        return;
    }
    var oFile = obj.files[0];
    if(oFile.size>200*1024){
        swal("图片大小不能超过200K");
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
            if(img.width!=800){
                swal("请选择宽度为800像素的图片");
                return;
            }else{
                $(obj).parent().children("img").attr("src",oFREvent.target.result);
                $(obj).parent().children("div").remove();
            }
        }

    };
    oFReader.readAsDataURL(oFile);
}


function editProvinceFreight(provinceFreightId,_this){
	var provinceFreight;
	$.ajax({
		method: 'POST',
		url: '${ctx}/freightTemplate/getProvinceFreightInfo',
		data: {"provinceFreightId":provinceFreightId},
		dataType: 'json',
		cache : false,
		async : false,
		success: function(data){
			provinceFreight = data.provinceFreight;
		},
		error:function(){
			swal('页面不存在');
		}
	})
	var $provinceFreightCustomTr = $(_this).closest("tr[name='provinceFreightCustomTr']");
	$("#ff").val($provinceFreightCustomTr.find("td").eq(1).text());
	$("#af").val($provinceFreightCustomTr.find("td").eq(2).text());
	$("#fullNumberProvince").val(provinceFreight.fullNumber);
	$("#fullReductionFreightProvince").val(provinceFreight.fullReductionFreight);
	var selectedAreaIds = $provinceFreightCustomTr.attr("data-areaids")+"";
	var provincefreightId = $provinceFreightCustomTr.data("provincefreightid");
	$("#provinceFreightId").val(provincefreightId);
	var freightTemplateId = $("#freightTemplateId").val();
	var idx = $("tr[name='provinceFreightCustomTr']").index($provinceFreightCustomTr);
	$("#index").val(idx);
	$.ajax({
        method: 'POST',
        url: '${ctx}/freightTemplate/getAreas',
        data: {"freightTemplateId":freightTemplateId,"provinceFreightId":provinceFreightId},
        dataType: 'json',
        cache : false,
		async : false
    }).done(function (result) {
        if (result.returnCode =='0000') {
        	var areas = result.returnData.areas;
        	var html = [];
        	html.push('<span style="color: red;">*</span>选择区域');
        	html.push('<div style="margin-left: 20px;overflow: hidden;">');
        	var allExistsAreaIds = "";
			$("tr[name='provinceFreightCustomTr']").each(function(index){
				if(index!=$("tr[name='provinceFreightCustomTr']").length-1){
					allExistsAreaIds += $(this).attr("data-areaids")+",";
				}else{
					allExistsAreaIds += $(this).attr("data-areaids");
				}
			});
        	for(var i=0;i<areas.length;i++){
        		if(selectedAreaIds.indexOf(areas[i].areaId)>=0){
	        		html.push('<div class="area"><input type="checkbox" checked name="areaId" data-areaname="'+areas[i].areaName+'" value="'+areas[i].areaId+'" >'+areas[i].areaName+'</div>');
        		}else{
        			if(allExistsAreaIds.indexOf(areas[i].areaId)<0){
	        			html.push('<div class="area"><input type="checkbox" name="areaId" data-areaname="'+areas[i].areaName+'" value="'+areas[i].areaId+'" >'+areas[i].areaName+'</div>');
            		}
        		}
        	}
        	html.push('</div>');
        	$("#selectAreaDiv").empty();
        	$("#selectAreaDiv").append(html.join(""));
        }else{
        	swal("设置失败，请稍后重试");
        }
    });
	$("#provinceFreightDiv").show();
	$("#freightBlackBox").show();
}
function deleteProvinceFreight(provinceFreightId,_this){
	swal({ 
		  title: "确定删除吗？",
		  showCancelButton: true, 
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "确定", 
		  cancelButtonText: "取消",
		  closeOnConfirm: true
		},
		function(isConfirm){ 
		  if(isConfirm) {
			  if(provinceFreightId && provinceFreightId>0){
				  $.ajax({
				        method: 'POST',
				        url: '${ctx}/freightTemplate/deleteProvinceFreight',
				        data: {"provinceFreightId":provinceFreightId},
				        dataType: 'json'
				    }).done(function (result) {
				        if (result.returnCode =='0000') {
				           	$(_this).closest("tr").remove();
				        }else{
				        	swal("删除失败，请稍后重试");
				        }
				    });
			  }else{
				  $(_this).closest("tr").remove();
			  }
		  }
		});
}
	$(function(){

		$('#isFullReduction').on('click',function () {
			$('#isFullReductionShow').toggle();
		})

		$('#isFullReduction').on('click',function () {
			$('#isFullReductionProvinceShow').toggle();
		})

		$("#productNotice").on('click',function(){
			$("#productNoticeDiv").show();
			$("#freightBlackBox").show();
		});

		$("#productNotice").on('click',function(){
			$("#productNoticeDiv").show();
			$("#freightBlackBox").show();
		});

		$("#save").on('click',function(){
			var id = $("#freightTemplateId").val();
			var productNoticePic = $("input[name='productNoticePic']");
            var productNoticePics = productNoticePic.parent().children("img");
            if(productNoticePics.length<=0){
                swal({
                    title: '请上传商品公告图',
                    type: "error",
                    confirmButtonText: "确定"
                });
                return;
            }
            if("${freightTemplate.productNoticePic}"=="" || productNoticePics.attr("src").indexOf("${freightTemplate.productNoticePic}") < 0){//有修改
                uploadImg("productNoticePicFile", productNoticePic);
            }
			var name = $.trim($("#freightTemplate_name").val());
			var firstFreight = $("#firstFreight").val();
			var additionalFreight = $("#additionalFreight").val();
			var fullNumber = $("#fullNumber").val();
			var fullReductionFreight = $("#fullReductionFreight").val();
			var isFullReduction = $('#isFullReduction').is(':checked')?'1':'0';
			if(!name){
				swal("请输入模板名称");
				return false;
			}
			if(!firstFreight){
				swal("请输入首件运费");
				return false;
			}
			if(!additionalFreight){
				swal("请输入增件运费");
				return false;
			}
			if($('#isFullReduction').is(':checked')){
				if(!fullNumber || !fullReductionFreight){
					swal("请输入运费满减");
					return false;
				}
			}
			if(!fullNumber && fullReductionFreight || fullNumber && !fullReductionFreight){
				swal("请输入运费满减");
				return false;
			}
			var provinceFreightArray = [];
			$("tr[name='provinceFreightCustomTr']").each(function(index){
				var provinceFreightId = $(this).data("provincefreightid");
				var areaIds = $(this).attr("data-areaids");
				var ff = $(this).find("td").eq("1").text();
				var af = $(this).find("td").eq("2").text();
				var provinceFreightObj = {"provinceFreightId":provinceFreightId,"areaIds":areaIds,"firstFreight":ff,"additionalFreight":af};
				provinceFreightArray.push(provinceFreightObj);
			});
			var provinceFreightJsonStr = JSON.stringify(provinceFreightArray);
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/freightTemplate/saveProductNoticePic',
		        data: { "id":id,
		        		"productNoticePic":productNoticePic.val(),
		        		"name":name,
		        		"firstFreight":firstFreight,
		        		"additionalFreight":additionalFreight,
		        		"provinceFreightJsonStr":provinceFreightJsonStr,
		        		"fullNumber":fullNumber,
		        		"fullReductionFreight":fullReductionFreight,
						"isFullReduction":isFullReduction
		        	   },
		        cache : false,
				async : false,
		        dataType: 'json'
		    }).done(function (result) {
		    	submiting = false;
		        if (result.returnCode =='0000') {
		        	swal("保存成功");
		        	$("#productNoticeDiv").hide();
		        	$(".black_box").hide();
		        	if(result.returnData.freightTemplateId){
			        	$("#freightTemplateId").val(result.returnData.freightTemplateId);
			        	$("#viewModal").modal('hide');
			        	$(".modal-backdrop").hide();
			        	getContent('${ctx}/freightTemplate/freightTemplateIndex');
		        	}
		        }else{
		        	if(result.returnMsg){
		        		swal(result.returnMsg);
		        	}else{
			        	swal("操作失败，请稍后重试");
		        	}
		        }
		    });
		});
		
		$("#setProvinceFreight").on('click',function(){
			$("#index").val("");
			$("#ff").val("");
			$("#af").val("");
			var selectedAreaIds = "";
			$("tr[name='provinceFreightCustomTr']").each(function(index){
				if(index!=$("tr[name='provinceFreightCustomTr']").length-1){
					selectedAreaIds += $(this).attr("data-areaids")+",";
				}else{
					selectedAreaIds += $(this).attr("data-areaids");
				}
			});
			var freightTemplateId = $("#freightTemplateId").val();
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/freightTemplate/getAreas',
		        data: {"freightTemplateId":freightTemplateId},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	var areas = result.returnData.areas;
		        	var html = [];
		        	html.push('<span style="color: red;">*</span>选择区域');
		        	html.push('<div style="margin-left: 20px;overflow: hidden;">');
		        	for(var i=0;i<areas.length;i++){
		        		if(selectedAreaIds.indexOf(areas[i].areaId)<0){
			        		html.push('<div class="area"><input type="checkbox" name="areaId" data-areaname="'+areas[i].areaName+'" value="'+areas[i].areaId+'" >'+areas[i].areaName+'</div>');
		        		}
		        	}
		        	html.push('</div>');
		        	$("#selectAreaDiv").empty();
		        	$("#selectAreaDiv").append(html.join(""));
		        }else{
		        	swal("设置失败，请稍后重试");
		        }
		    });
			$("#provinceFreightDiv").show();
			$("#freightBlackBox").show();
		});
		
		
		
		
		var submitting;
		$("#saveFreightTemplate").on('click',function(){
			if(submitting){
				return false;
			}
			var id = $("#freightTemplateId").val();
			var name = $.trim($("#freightTemplate_name").val());
			var firstFreight = $("#firstFreight").val();
			var additionalFreight = $("#additionalFreight").val();
			var fullNumber = $("#fullNumber").val();
			var fullReductionFreight = $("#fullReductionFreight").val();
			var isFullReduction = $('#isFullReduction').is(':checked')?'1':'0';
			if(!name){
				swal("请输入模板名称");
				return false;
			}
			if(!firstFreight){
				swal("请输入首件运费");
				return false;
			}
			if(!additionalFreight){
				swal("请输入增件运费");
				return false;
			}
			if($('#isFullReduction').is(':checked')){
				if(!fullNumber || !fullReductionFreight){
					swal("请输入运费满减");
					return false;
				}
			}
			if(!fullNumber && fullReductionFreight || fullNumber && !fullReductionFreight){
				swal("请输入运费满减");
				return false;
			}
			var provinceFreightArray = [];
			$("tr[name='provinceFreightCustomTr']").each(function(index){
				var provinceFreightId = $(this).data("provincefreightid");
				var areaIds = $(this).attr("data-areaids");
				var ff = $(this).find("td").eq("1").text();
				var af = $(this).find("td").eq("2").text();
				var provinceFreightObj = {"provinceFreightId":provinceFreightId,"areaIds":areaIds,"firstFreight":ff,"additionalFreight":af};
				provinceFreightArray.push(provinceFreightObj);
			});
			var provinceFreightJsonStr = JSON.stringify(provinceFreightArray);
			submitting = true;
			$.ajax({
			        method: 'POST',
			        url: '${ctx}/freightTemplate/saveFreightTemplate',
			        data: {"id":id,"name":name,"firstFreight":firstFreight,"additionalFreight":additionalFreight,"provinceFreightJsonStr":provinceFreightJsonStr,"fullNumber":fullNumber, "fullReductionFreight":fullReductionFreight, "isFullReduction":isFullReduction},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	if($("#viewModal1").length>0){//商品编辑页面的运费模板添加
			        		$("#viewModal1").modal('hide');
			        		$("#freightTemplate_Id").append("<option value="+result.returnData.id+">"+result.returnData.name+"</option>");
			        		$("#freightTemplate_Id").val(result.returnData.id);
			        		changeFreightTemplate($("#freightTemplate_Id").get(0));
			        	}else{
				        	$("#viewModal").modal('hide');
				        	$(".modal-backdrop").hide();
//				           	table.ajax.reload(null, false);
							getContent('${ctx}/freightTemplate/freightTemplateIndex');
			        	}

			        }else{
			        	swal("保存失败，请稍后重试");
			        }
			        submitting = false;
			    });
		});
		
		$("#confirm").on('click',function(){
			var length = $("input[name='areaId']:checked").length;
			if(length == 0){
				swal("请选择区域");
				return false;
			}
			var areaIds = "";
			var areaNames = "";
			$("input[name='areaId']:checked").each(function(index){
				if(index!=length-1){
					areaIds+= $(this).val()+",";
					areaNames+= $(this).attr("data-areaname")+"、";
				}else{
					areaIds+= $(this).val();
					areaNames+= $(this).attr("data-areaname");
				}
			});
			var firstFreight = $("#ff").val();
			var additionalFreight = $("#af").val();
			if(!firstFreight){
				swal("请输入首件运费");
				return false;
			}
			if(!additionalFreight){
				swal("请输入增件运费");
				return false;
			}
			var freightTemplateId = $("#freightTemplateId").val();
			var provinceFreightId = 0;
			var id = $("#provinceFreightId").val();
			if(freightTemplateId){
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/freightTemplate/saveProvinceFreight',
			        data: {"freightTemplateId":freightTemplateId,"id":id,"areaIds":areaIds,"firstFreight":firstFreight,"additionalFreight":additionalFreight},
			        cache : false,
					async : false,
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	provinceFreightId = result.returnData.provinceFreightId;
			        }else{
			        	swal("保存失败，请稍后重试");
			        }
			    });
			}
			var index = $("#index").val();
			var provinceFreightHtml='<tr name="provinceFreightCustomTr" data-provincefreightid="'+provinceFreightId+'" data-areaids="'+areaIds+'"><td title="'+areaNames+'">'+areaNames+'</td><td>'+firstFreight+'</td><td>'+additionalFreight+'</td><td><a href="javascript:;" onclick="editProvinceFreight('+provinceFreightId+',this)">编辑</a>&nbsp;&nbsp;<a href="javascript:;" onclick="deleteProvinceFreight('+provinceFreightId+',this)">删除</a></td></tr>';
			if((!id||id=="0") && !index){
				$("#provinceFreightTbody").append(provinceFreightHtml);
			}else{
				$("tr[name='provinceFreightCustomTr']").eq(index).attr("data-areaids",areaIds);
				$("tr[name='provinceFreightCustomTr']").eq(index).find("td").eq(0).prop("title",areaNames);
				$("tr[name='provinceFreightCustomTr']").eq(index).find("td").eq(0).text(areaNames);
				$("tr[name='provinceFreightCustomTr']").eq(index).find("td").eq(1).text(firstFreight);
				$("tr[name='provinceFreightCustomTr']").eq(index).find("td").eq(2).text(additionalFreight);
			}
			$("#provinceFreightDiv").hide();
			$(".black_box").hide();
		});

		$(".video_close").on('click',function(){
			$("#provinceFreightDiv").hide();
			$("#productNoticeDiv").hide();
			$(".black_box").hide();
		});
		
		$("button[name='delete']").on('click',function(){
			swal({ 
				  title: "确定删除吗？",
				  showCancelButton: true, 
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "确定", 
				  cancelButtonText: "取消",
				  closeOnConfirm: true
				},
				function(isConfirm){ 
				  if(isConfirm) {
					  var id = $("#freightTemplateId").val();
					  if(id){
						  $.ajax({
						        method: 'POST',
						        url: '${ctx}/freightTemplate/deleteProductNoticePic',
						        data: {"id":id},
						        dataType: 'json'
						    }).done(function (result) {
						        if (result.returnCode =='0000') {
						        	swal("删除成功");
						        	$("#productNoticeDiv").hide();
									$(".black_box").hide();
						        }else{
						        	swal("删除失败，请稍后重试");
						        }
						    });
					  }
					$(".single_pic_picker").find("img").remove();
					$(".single_pic_picker").append("<div>+</div>");
				  }
				});
		});
		
	});
	
  </script>
</body>
</html>
