<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>
<c:if test="${not empty writeType}">
设置店铺负责人
</c:if>
<c:if test="${not empty perfectType}">
店铺负责人信息
</c:if>
<c:if test="${not empty Mailing}">
修改收件地址
</c:if>
</title>
	<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
            <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
            	<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
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
  <div class="modal-dialog" role="document" style="width:700px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" id="closeX" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabels">
        <c:if test="${not empty writeType}">
		设置店铺负责人
		</c:if>
		<c:if test="${not empty perfectType}">
		店铺负责人信息
		</c:if>
		<c:if test="${not empty Mailing}">
		修改收件地址
		</c:if>
        </span>
      </div>
        <c:if test="${not empty perfectType && mchtContact.auditStatus eq '2' || not empty writeType && mchtContact.auditStatus eq '2'}">
        <div style="margin-left:10px;">
			<font color="red">驳回原因：${mchtContact.rejectReasons}</font>
		</div>
		</c:if>
			<div class="modal-body">
		<form id="dataFrom1">
		<input type="hidden" value="${mchtContact.id}" name="id" id="contactId">
		<input type="hidden" value="${mchtContact.mchtId}" name="mchtId">
		<c:if test="${not empty contactType }">
		<input type="hidden" value="1" name="isPrimary">
		</c:if>
		<div class="table-responsive">
			<table class="table table-bordered " id="shows">
				<tbody>
					<tr>
						<td class="editfield-label-td">姓名<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<div id="edit">
							${mchtContact.contactName }
							<span style="margin-left:5px;">
								<c:if test="${mchtContactStatus ne '1'}">
									<a href="#" onclick="show();">【修改】</a>
								</c:if>
							</span>
						</div>
						<input type="text" id="contactName" name="contactName" value="${mchtContact.contactName }" validate="{required:true}" style="display:none;">
						</td>
					</tr>				
					<tr>
						<td class="editfield-label-td">职责</td>
						<td colspan="2" class="text-left">
						<c:if test="${empty contactType }">
						  <select  name="contactType" id="contactType" validate="{required:true}" >
						   <option value="">--请选择--</option>
						   <c:forEach var="contactTypeDescItem" items="${contactTypeDesc }">
						   <option value="${contactTypeDescItem.statusValue}"   <c:if test="${mchtContact.contactType==contactTypeDescItem.statusValue}">selected</c:if>>${contactTypeDescItem.statusDesc}</option>
						   </c:forEach>
                          </select>
                        </c:if> 
						<c:if test="${not empty contactType }">
						  <select  name="contactType" id="contactType" validate="{required:true}">
						   <c:forEach var="contactTypeDescItem" items="${contactTypeDesc }">
						   <c:if test="${contactType==contactTypeDescItem.statusValue}">
						   <option value="${contactTypeDescItem.statusValue}" selected>${contactTypeDescItem.statusDesc}</option>
						   </c:if>
						   </c:forEach>
                          </select>
                        </c:if> 
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">手机号<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="mobiles" name="mobile" value="${mchtContact.mobile }" validate="{required:true,mobile:true}">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">微信号</td>
						<td colspan="2" class="text-left">
						<input type="text" id="wechat" name="wechat" value="${mchtContact.wechat}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">QQ号<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="qq" name="qq" value="${mchtContact.qq}" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">邮箱<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="email" name="email" value="${mchtContact.email }" validate="{required:true}">
						</td>
					</tr>
					<tr id="IDcardStatus" style="display:none;">
						<td class="editfield-label-td">身份证正反面<c:if test="${contactType == 1}"><span class="required">*</span></c:if></td>
						<td colspan="2" class="text-left">
							<c:if test='${not empty mchtContact.idcardImg1}'>
							<div class="single_pic_picker"><input id="idcardImg1File" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtContact.idcardImg1}"><input type="hidden"  id="idcardImg1" name="idcardImg1" value="${mchtContact.idcardImg1}"></div>
							</c:if>
							<c:if test='${empty mchtContact.idcardImg1}'>
							<div class="single_pic_picker"><input id="idcardImg1File" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="idcardImg1" name="idcardImg1" value="${mchtContact.idcardImg1}"></div>
							</c:if>
							<c:if test='${not empty mchtContact.idcardImg2}'>
							<div class="single_pic_picker"><input id="idcardImg2File" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtContact.idcardImg2}"><input type="hidden"  id="idcardImg2" name="idcardImg2" value="${mchtContact.idcardImg2}"></div>
							</c:if>
							<c:if test='${empty mchtContact.idcardImg2}'>
							<div class="single_pic_picker"><input id="idcardImg2File" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="idcardImg2" name="idcardImg2" value="${mchtContact.idcardImg2}"></div>
							</c:if>
<!-- 							<div style="color: #999999;">身份证复印件 加盖公司公章</div> -->
						</td>
					</tr>
					
					<c:if test="${contactType == 1}">
					<tr>
						<td class="editfield-label-td">店铺负责人手持身份证<c:if test="${contactType == 1}"><span class="required">*</span></c:if></td>
						<td colspan="2" class="text-left">
							<c:if test='${not empty mchtContact.idcardImg}'>
							<div class="single_pic_picker"><input id="idcardImgFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtContact.idcardImg}"><input type="hidden"  id="idcardImg" name="idcardImg" value="${mchtContact.idcardImg}"></div>
							</c:if>	
							<c:if test='${empty mchtContact.idcardImg}'>
							<div class="single_pic_picker"><input id="idcardImgFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="idcardImg" name="idcardImg" value="${mchtContact.idcardImg}"></div>
							</c:if>	
							<div style="color: #999999;">要求身份证上所有信息清晰可见，手持人五官清晰可见。</div>
							<div><a  style="color:#0000FF;" href="#" onclick="showPic(this);">如何手持？点我查看？</a></div>
						</td>
					</tr>
					</c:if>				
					<tr>
						<td class="editfield-label-td">通讯地址<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<select style="width:100px;" class="province-select" onchange="onchangeProvince();" name="province" id="province" validate="{required:true}">
                         </select>
                         <select style="width:100px;" class="city-select" onchange="onchangeCity();" name="city" id="city" validate="{required:true}">
                         </select>
                         <select style="width:100px;" class="county-select"  name="county" id="county" validate="{required:true}">
                         </select>
                         <br>
                         <br>
						<input style="width:80%;" type="text" id="address" name="address" value="${mchtContact.address}" validate="{required:true}">
						 <br>
						 <br>
						 <div style="color: #999999;">该通讯地址将作为您的收件地址（合作合同等文件收件地址），需保证地址真实、有效、准确。否则由此产生法律后果由贵司承担。</div>
						</td>
					</tr>
					<tr id="valideCodeTr" <c:if test="${contactType == 5}">style="display: none;"</c:if>>
						<td class="editfield-label-td">验证码<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text" id="valideCode" name="valideCode" <c:if test="${contactType ne 5}">validate="{required:true}"</c:if>>
							<span id="getValidateCodeBtn" style="width:96px;" onclick="getValideCode();" class="btn btn-default">获取验证码</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
			<button class="btn btn-info" onclick="editCommitSave();" style="margin-right: 17px;">提交</button>
			<button class="btn btn-info" id="currentClose">取消</button>
    </div>
		
	</div>
	</div>
  </div>
<!-- 	查看图片 -->
<div class="modal fade" id="preViewModal"  tabindex="-1" role="dialog" aria-labelledby="preViewModal" data-backdrop="static">
  <div class="modal-dialog" role="document" style="width: 782px;">
    <div class="modal-content">
      <div class="modal-header">
      	<span class="bold">&nbsp;&nbsp;&nbsp;&nbsp;如何手持</span>
        <button type="button" class="close"  aria-label="Close" id="close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body container-fluid">
      	<div id="dynamicPic">
      	</div>   	
    </div>
      <div class="modal-footer">
			<button class="btn btn-info" id="close">关闭</button>
      </div>
     </div>
  </div>
</div>
<!-- 	店铺总负责人提交后信息框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  data-backdrop="static"> 
	<div class="modal-dialog">
		<div class="modal-content"  style="text-align:center;">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<font size="3px;">提醒</font>
				</h4>
			</div>
			<br>
			<br>
			<div class="modal-body">
				提交成功，可在栏目【商家管理】中的【联系人管理】中查看审核情况
			</div>
		</div>
	</div>
</div>


	
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
<script type="text/javascript">
var timer = 0;
var Mailing = 1;
function show(){
	$('#shows tr').eq(1).show();
	$('#shows tr').eq(3).show();
	$('#shows tr').eq(4).show();
	$('#shows tr').eq(5).show();
	$('#shows tr').eq(6).show();
	$('#shows tr').eq(7).show();
	$('#shows tr').find('div').eq(0).hide();
	$('#shows tr').find('input').eq(0).show();
	Mailing = 2;
	$('#exampleModalLabels').html("修改店铺负责人");
}

function countdown() {
	if (timer > 0) {
		timer = timer - 1;
		$("#getValidateCodeBtn").html(timer + "秒");
		setTimeout(function() {
			countdown();
		}, 1000);
	} else {
		$("#getValidateCodeBtn").removeClass("disabled");
		$("#getValidateCodeBtn").html("获取验证码");
	}
}

function getValideCode() {
	if (timer > 0) {
		return;
	}
	var mobile = $.trim($("#mobiles").val());
	if (mobile == "") {
		swal("请输入手机号！");
		return;
	}
	var reg = /^1\d{10}/;
	if (!reg.test(mobile)) {
		swal("请输入正确的手机号！");
		return;
	}

	$("#getValidateCodeBtn").addClass("disabled");
	timer = 120;
	countdown();
	$.ajax({
		url : "${ctx}/common/getMobileValidateCode",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {
			mobile : mobile
		},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode == "0000") {
				swal("验证码已发至您的手机！");
			} else {
				swal("获取失败，请重新获取！");
			}

		},
		error : function() {
			swal("获取失败，请重新获取！");
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

//上传图片
function uploadIdcard(fileElementId,$valueFiled) {
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

function getProvinceList()
{   
	var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentAreaId:0},
  		timeout : 30000,
  		success : function(json) 
  		{  var sel = $(".province-select");
				sel.empty();
				sel.append("<option value=\"" + "\">--省份--</option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			swal('操作超时，请稍后再试！');
		}
  	}); 
}
function getCityList(parentAreaId)
{
	if(typeof(parentAreaId)!="undefined"){
		var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		cache : false,
	  		async : false,
	  		data : { parentAreaId : parentAreaId},
	  		timeout : 30000,
	  		success : function(json) 
	  		{  var sel = $(".city-select");
					sel.empty();
					sel.append("<option value=\"" + "\">--地级市--</option>");
					
					$.each(json, function(index, item) {
						sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
					});
	  	      
	  		},
			error : function() 
			{
				swal('操作超时，请稍后再试！');
			}
	  	});
	}
	
 
}
function getCountyList(parentAreaId)
{
	if(typeof(parentAreaId)!="undefined"){
	var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentAreaId : parentAreaId},
  		timeout : 30000,
  		success : function(json) 
  		{  var sel = $(".county-select");
				sel.empty();
				sel.append("<option value=\"" + "\">--县级--</option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			swal('操作超时，请稍后再试！');
		}
  	}); 
	}
}
function  onchangeProvince()
{
	   $(".city-select").empty();
	   $(".county-select").empty();
	   getCityList($(".province-select").val()); 
}
function  onchangeCity()
{ 
	   $(".county-select").empty();  
	   getCountyList($(".city-select").val()); 
}











var dataFromValidate;
$(function(){
	getProvinceList();
	if(${not empty mchtContact.id}){
		getCityList(${mchtContact.province});
		getCountyList(${mchtContact.city});  
		$(".province-select").val(${mchtContact.province});
		$(".city-select").val(${mchtContact.city});
		$(".county-select").val(${mchtContact.county});
	}else{
		getCityList(${mchtInfo.contactProvince});
		getCountyList(${mchtInfo.contactCity});  
		$(".province-select").val(${mchtInfo.contactProvince});
		$(".city-select").val(${mchtInfo.contactCity});
		$(".county-select").val(${mchtInfo.contactCounty});
		$("#address").val("${mchtInfo.contactAddress}");
	}
	
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom1").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});
	
	//如果是店铺总负责人，不隐藏
	if($('#contactType').val() == 1){	
		$('#IDcardStatus').show();
	}
	$("#contactType").on('change',function(){
		var contactType = $(this).val();
		if(contactType != 1){
			$('#IDcardStatus').hide();
		}else{
			$('#IDcardStatus').show();
		}
		if(contactType != 5){
			$("#valideCodeTr").show();
			$("#valideCode").attr("validate","{required:true}");
		}else{
			$("#valideCodeTr").hide();
		}
	});
	
	//如果是填写负责人信息，就把修改模式隐藏
	if(${not empty writeType} || ${not empty perfectType}){
		$('#contactName').show();
		$('#edit').hide();
	}
	//如果是修改收件地址，则隐藏相关部分
	if(""!= "${Mailing}" && ${mchtContactStatus ne '1'}){
		$('#shows tr').eq(1).hide();
		$('#shows tr').eq(3).hide();
		$('#shows tr').eq(4).hide();
		$('#shows tr').eq(5).hide();
		$('#shows tr').eq(6).hide();
		$('#shows tr').eq(7).hide();
	}
});





function editCommitSave(){
	if(dataFromValidate.form()){
		
		var $idCar1Img=$("#idcardImg1").parent().children("img");
		var $idCar2Img=$("#idcardImg2").parent().children("img");
		var $idCarImg=$("#idcardImg").parent().children("img");
		var contactType = $("#contactType").val();
		if(contactType == 1){
			if($idCar1Img.length<=0||$idCar2Img.length<=0 || $idCarImg.length<=0){
				swal({
					  title: '请上传身份证件',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}
			if("${mchtContact.idcardImg}"==""||$idCarImg.attr("src").indexOf("${mchtContact.idcardImg}")<0){//有修改
				uploadIdcard("idcardImgFile",$("#idcardImg"));
			}
		}
		if("${mchtContact.idcardImg1}"==""||$idCar1Img.attr("src").indexOf("${mchtContact.idcardImg1}")<0){//有修改
			uploadIdcard("idcardImg1File",$("#idcardImg1"));
		}
		if("${mchtContact.idcardImg2}"==""||$idCar2Img.attr("src").indexOf("${mchtContact.idcardImg2}")<0){//有修改
			uploadIdcard("idcardImg2File",$("#idcardImg2"));
		}
		
		var dataJson = $("#dataFrom1").serializeArray();
		$.ajax({
			url : "${ctx}/mchtContact/mchtContactSave?writeType=${writeType}&perfectType=${perfectType}&Mailing="+Mailing,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					isReload=true;
					if(${not empty perfectType}){
						swal({
							  title: "提交成功，可在栏目【商家管理】中的【联系人管理】中查看审核情况",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
						$("#viewModal").modal('hide');
					}else if(${not empty Mailing}){
						swal({
							  title: "提交成功",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
						$("#myViewModal").modal('hide');
						freshenContact($('#contactId').val(),Mailing);	
					}else{
						swal({
							  title: "提交成功",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
						$("#viewModal").modal('hide');
					}
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
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
		});
	}	
};

$('#currentClose').click(function(){
	if(${not empty Mailing}){
		$("#myViewModal").modal('hide');
	}else{
		$("#viewModal").modal('hide');
	}
});

$('#closeX').click(function(){
	if(${not empty Mailing}){
		$("#myViewModal").modal('hide');
	}else{
		$("#viewModal").modal('hide');
	}
});
function showPic(topCover){
	
	$("#dynamicPic").html('<img style="width:700px;height:700px;" src="${ctx}/static/images/handIdCard.png">');
	$("#preViewModal").modal();
}

$('body').on('click','#close',function(){
	$('#preViewModal').modal('hide');
});

//修改寄件的店铺总负责人刷新页面
function freshenContact(id,mailing){
		$.ajax({
			url : "${ctx}/mcht/contract/freshenContact.shtml?id="+id+"&mailing="+mailing,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success : function(data) {
				var contact = data.contact;
				var areaList = data.areaList;
				$("#change").html('您的收件地址<span style="float:right; magin-right:25px;"><a href="javascript:edit('+"'"+'${ctx}/mchtContact/mchtContactEdit?contactType=1&id='+contact.id+'&Mailing=1'+"'"+')">【修改】</a></span><br/>收 &nbsp;件&nbsp;人：'+contact.contactName+'<br/>联系电话：'+contact.mobile+'<br/>收件地址：'+areaList[0].areaName+areaList[1].areaName+areaList[2].areaName+contact.address+'');
		},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});		 
}

</script>
</body>
</html>
