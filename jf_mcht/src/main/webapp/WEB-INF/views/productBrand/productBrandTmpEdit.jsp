<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>申请品牌</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
            <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
#uploader-demo{
z-index: 99999 !important;
}
</style>


</head>

<body>

  <div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">申请品牌</span>
      </div>
			<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" value="${productBrandTmp.id}" name="id">
		<input type="hidden" value="${productBrandTmp.mchtId}" name="mchtId">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">名称</td>
						<td colspan="2" class="text-left">
						<input type="text" id="name" name="name" value="${productBrandTmp.name}" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">中文</td>
						<td colspan="2" class="text-left">
						<input type="text" id="nameZh" name="nameZh" value="${productBrandTmp.nameZh}" validate="{nameEnZhRequired:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">英文</td>
						<td colspan="2" class="text-left">
						<input type="text" id="nameEn" name="nameEn" value="${productBrandTmp.nameEn}" validate="{nameEnZhRequired:true}">
						<span>中文、英文，两者至少填一个。</span>
						</td>
						
					</tr>
					<tr>
						<td class="editfield-label-td">LOGO图片</td>
						<td colspan="2" class="text-left">
							<c:if test='${productBrandTmp.logo!=null&&productBrandTmp.logo!=""}'>
							<div class="single_pic_picker"><input id="logoFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${productBrandTmp.logo}"><input type="hidden"  id="logo" name="logo" value="${productBrandTmp.logo}"></div>
							</c:if>
							<c:if test='${productBrandTmp.logo==null||productBrandTmp.logo==""}'>
							<div class="single_pic_picker"><input id="logoFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="logo" name="logo" value="${productBrandTmp.logo}"></div>
							</c:if>
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">适用品类 </td>
						<td colspan="2" class="text-left">
						   <span style="padding:0 10px;">一级品类:</span>
						   <select id="superProductType" onchange="changeProductType();">
						   <option value="">--请选择--</option>
						   <c:forEach var="productType" items="${productTypes }">
						   <option value="${productType.id}">${productType.name}</option>
						   </c:forEach>
                          </select>
                          <br>
                          <br>
						  <span style="padding:0 10px;line-height: 50px;">二级品类:</span>
						  <select style="width:50%;" multiple="multiple" id="productTypeGroup"  name="productTypeGroup" validate="{required:true}">
						  	<c:forEach var="productType" items="${productBrandTmp.productTypes}">
						  	<option value="${productType.id}">${productType.name}</option>
						  	</c:forEach>
                          </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">商标图片</td>
						<td colspan="2" class="text-left">
							
						<div class="pic-container">
							<ul class="docs-pictures clearfix td-pictures pictures-list" id="brandTmkPic-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
								<c:forEach var="brandTmkPicTmp" items="${brandTmkPicTmps}" varStatus="varStatus">
								<li id="brandTmkPicTmp_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${brandTmkPicTmp.pic}">
								<img  src="${ctx}/file_servelt${brandTmkPicTmp.pic}">
								<div class="pic-btn-container" style="height: 0px;">
								<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
								</div>
								</li>
								</c:forEach>
								</ul>
								<div class="filePicker">选择图片</div>
						</div>
						</td>
					</tr>
					
				    <tr>
						<td class="editfield-label-td">商标类别 </td>
						<td colspan="2" class="text-left">
                              <input name="tmkTypeGroup" type="checkbox" value="14" validate="{required:true}"><span style="padding:0 5px;">第14类</span>
                              <input name="tmkTypeGroup" type="checkbox" value="18"><span style="padding:0 5px;">第18类</span>
                              <input name="tmkTypeGroup" type="checkbox" value="24"><span style="padding:0 5px;">第24类</span>
                              <input name="tmkTypeGroup" type="checkbox" value="25"><span style="padding:0 5px;">第25类</span>
						</td>
					</tr>
					<c:if test='${productBrandTmp.id!=null}'>
					<tr>
						<td class="editfield-label-td">状态</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.statusName}</span>
						</td>
					</tr>
					</c:if>
					<c:if test='${productBrandTmp.status=="3"}'>
					<tr>
						<td class="editfield-label-td">驳回理由</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.auditRemarks}</span>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
	
			<button class="btn btn-info" onclick="commitAudit();">直接提交审核</button>
			<button class="btn btn-info" onclick="commitSave();">保存暂不提审</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
		
			</div>
	</div>
  </div>




	
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>	
	
<script type="text/javascript">




var dataFromValidate;
var imageUploader;
$(function(){

	imageUploader=new ImageUploader();
	
	$.metadata.setType("attr", "validate");
	//中英文名称验证
    $.validator.addMethod("nameEnZhRequired", function(value, element) {   
    	if($.trim($("#nameEn").val()) == ""
			&& $.trim($("#nameZh").val()) == ""){
    		return false;
    	}else{
    		return true;
    	}
    }, "中文、英文，两者至少填一个。");

	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        },
        errorPlacement: function(error, element) {  
        	error.appendTo($(element).closest('td'));
        }
	});

    

    
    
    
});


function changeProductType(){
	var productTypeId=$("#superProductType").val();
	var sel = $("#productTypeGroup");
	if(productTypeId==""){
		sel.empty();
		return;
	}
	$.ajax({
		url : "${ctx}/productType/getSubProductTypes",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {parentId:productTypeId},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				sel.empty();
				$.each(data.returnData, function(index, item) {
					sel.append("<option value=\"" + item.id + "\">" + item.name + "</option>");
				});
			} else {
				swal({
					  title: data.returnMsg,
					  type: "error",
					  confirmButtonText: "确定"
					});
			}
			
		},
		error : function() {
			swal({
				  title: "请求失败！",
				  type: "error",
				  confirmButtonText: "确定"
				});
		}
	});
	
}

var brandTmkPics=[];
function commitSave(){
	
	if(dataFromValidate.form()){
		
		imageUploader.upload(function(){
			brandTmkPics=[];
			$("#brandTmkPic-pictures-list").children("li").each(function(index,element){
				var pic={};
		    	pic.pic=$(element).attr("pic_path");
		    	brandTmkPics.push(pic);
			});
			
			if(brandTmkPics.length==0){
				swal({
					  title: '请上传商标图',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}
			
			
			var $logoImg=$("#logo").parent().children("img");
			
			if($logoImg.length<=0){
					swal({
						  title: '请上传身份证件',
						  type: "error",
						  confirmButtonText: "确定"
						});
			return;
			}

			
			
			if("${productBrandTmp.logo}"==""||$logoImg.attr("src").indexOf("${productBrandTmp.logo}")<0){//有修改
				uploadLogo("logoFile",$("#logo"));
			}
			
			
			
			var dataJson = $("#dataFrom").serializeArray();
			dataJson.push({"name":"brandTmkPics","value":JSON.stringify(brandTmkPics)});
			$.ajax({
				url : "${ctx}/productBrand/productBrandTmpCommitSave",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
						isReload=true;
						$("#viewModal").modal('hide');
						swal({
							  title: "提交成功!",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
					} else {
						swal({
							  title: data.returnMsg,
							  type: "error",
							  confirmButtonText: "确定"
							});
					}
					
				},
				error : function() {
					swal({
						  title: "提交失败！",
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
			});
		});
		

	}
	
}

function commitAudit(){
	if(dataFromValidate.form()){
		
		imageUploader.upload(function(){
			
			brandTmkPics=[];
			$("#brandTmkPic-pictures-list").children("li").each(function(index,element){
				var pic={};
		    	pic.pic=$(element).attr("pic_path");
		    	brandTmkPics.push(pic);
			});
			
			if(brandTmkPics.length==0){
				swal({
					  title: '请上传商标图',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}
			
			
	var $logoImg=$("#logo").parent().children("img");
			
			if($logoImg.length<=0){
					swal({
						  title: '请上传身份证件',
						  type: "error",
						  confirmButtonText: "确定"
						});
			return;
			}

			
			console.log("${productBrandTmp.logo}");
			console.log($logoImg.attr("src"));
			console.log("${productBrandTmp.logo}"==""||$logoImg.attr("src").indexOf("${productBrandTmp.logo}")<0);
			
			if("${productBrandTmp.logo}"==""||$logoImg.attr("src").indexOf("${productBrandTmp.logo}")<0){//有修改
				uploadLogo("logoFile",$("#logo"));
			}
			
			
			var dataJson = $("#dataFrom").serializeArray();
			dataJson.push({"name":"brandTmkPics","value":JSON.stringify(brandTmkPics)});
			$.ajax({
				url : "${ctx}/productBrand/productBrandTmpCommitAudit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
						isReload=true;
						$("#viewModal").modal('hide');
						swal({
							  title: "提交成功!",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
					} else {
						swal({
							  title: data.returnMsg,
							  type: "error",
							  confirmButtonText: "确定"
							});
					}
					
				},
				error : function() {
					swal({
						  title: "提交失败！",
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
			});
			
		});
		

	}
}

//上传商标图
// function uploadLogo($img,$valueFiled) {
// 	$.ajax({
// 		url : "${ctx}/common/fileUpload4Base64",
// 		type : 'POST',
// 		dataType : 'json',
// 		cache : false,
// 		async : false,
// 		data : {imageData:$img.attr("src")},
// 		timeout : 30000,
// 		success : function(data) {
// 			if (data.returnCode=="0000") {
// 				$valueFiled.val(data.returnData);
// 			} else {
// 				swal({
// 					  title: "图片上传失败！",
// 					  type: "error",
// 					  confirmButtonText: "确定"
// 					});
// 			}
			
// 		},
// 		error : function() {
// 			swal({
// 				  title: "图片上传失败！",
// 				  type: "error",
// 				  confirmButtonText: "确定"
// 				});
// 		}
// 	});

// }


//上传图片
function uploadLogo(fileElementId,$valueFiled) {
	var oFile = document.getElementById(fileElementId).files[0];
	var formData = new FormData();
	formData.append("file",oFile);
	$.ajax({ 
		url : "${ctx}/common/fileUpload", 
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
		$(obj).parent().children("img").attr("src",oFREvent.target.result);
		$(obj).parent().children("div").remove();
	};
	oFReader.readAsDataURL(oFile);
}

</script>
</body>
</html>
