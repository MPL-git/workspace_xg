<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
	
		<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
	
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}
.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
</style>
<script type="text/javascript">




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
  		{  var sel = $("#contactProvince");
				sel.empty();
				sel.append("<option value=\"" + "\">  请选择 </option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			$.ligerDialog.warn('操作超时，请稍后再试！');
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
	  		{  var sel = $("#contactCity");
					sel.empty();
					sel.append("<option value=\"" + "\">  请选择 </option>");
					
					$.each(json, function(index, item) {
						sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
					});
	  	      
	  		},
			error : function() 
			{
				$.ligerDialog.warn('操作超时，请稍后再试！');
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
  		{  var sel = $("#contactCounty");
				sel.empty();
				sel.append("<option value=\"" + "\">  请选择  </option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			$.ligerDialog.warn('操作超时，请稍后再试！');
		}
  	}); 
	}
}
function  onchangeProvince()
{
	   $("#contactCity").empty();
	   $("#contactCounty").empty();
	   getCityList($("#contactProvince").val()); 
}
function  onchangeCity()
{ 
	   $("#contactCounty").empty();  
	   getCountyList($("#contactCity").val()); 
}


$(function ()  {
	
	getProvinceList();
	getCityList(${mchtInfo.contactProvince});
	getCountyList(${mchtInfo.contactCity});  
// 	if(typeof(${mchtInfo.contactProvince})!="undefined"){
// 	getCityList(${mchtInfo.contactProvince});
// 	}
// 	if(typeof(${mchtInfo.contactCity})!="undefined"){
// 	getCountyList(${mchtInfo.contactCity});  
// 	}
	
	
	$("#contactProvince").val(${mchtInfo.contactProvince});
	$("#contactCity").val(${mchtInfo.contactCity});
	$("#contactCounty").val(${mchtInfo.contactCounty});
	
	Date.prototype.format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} ;
	

	
	var foundedDate=new Date("${mchtInfo.foundedDate }");
	var yearlyInspectionDate=new Date("${mchtInfo.yearlyInspectionDate }");
	$("#foundedDate").ligerDateEditor( {
		showTime : false,
		width:158,
		initValue: foundedDate.format("yyyy-MM-dd")
	});
	$("#yearlyInspectionDate").ligerDateEditor( {
		showTime : false,
		width:158,
		initValue: yearlyInspectionDate.format("yyyy-MM-dd")
	});
	$.metadata.setType("attr", "validate");
    //驳回原因必填
    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
    	if($("input[type=radio][name='isCompanyInfPerfect'][value=4]").attr("checked")&&$.trim($("#companyInfAuditRemarks").val())==""){
 			return false;
 		}else{
 			return true;
 		}
    }, "请注明驳回原因");
	            var v = $("#form1").validate({
	            	
	                errorPlacement: function (lable, element)
	                {   console.log(lable[0].innerText);
	                	if($(element).hasClass("l-text-field")){
	                		$(element).parent().ligerTip({
								content : lable.html(),width: 100
							});
	                	}else{
	                		$(element).ligerTip({
								content : lable.html(),width: 100
							});
	                	}
	                	
	                	
	                },
	                success: function (lable,element)
	                {
	                    lable.ligerHideTip();
	                    lable.remove();
	                },
	                submitHandler: function (form)
	                {   
	                	mchtCompanyInfoChangeSubmit();
	                }
	            });
	            
	  });    

function mchtCompanyInfoChangeSubmit(){
	
// 	获取图片
	var pictures = [];
	var lis = $(".upload_image_list").find("li");
	lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		var def = ($(".def", item).length == 1 ? "1" : "0");
		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
		pictures.push(pic);
	});
	$("#mchtBlPics").val(JSON.stringify(pictures));
	
	
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtCompanyInfoChangeSubmit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
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
}

function ajaxFileUpload() {
	$.ajaxFileUpload({
		url : contextPath + '/service/common/ajax_upload.shtml',
		secureuri : false,
		fileElementId : "mchtBlPic",
		dataType : 'json',
		success : function(result, status) {
			if (result.RESULT_CODE == 0) {
				uploadImageList.addImage(contextPath + "/file_servelt",
						result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error : function(result, status, e) {
			alert("服务异常");
		}
	});

}

//上传身份正
function ajaxFileUploadIdcard(fileElementId,imgId,valueFiledId) {
	$.ajaxFileUpload({
		url : contextPath + '/service/common/ajax_upload.shtml',
		secureuri : false,
		fileElementId : fileElementId,
		dataType : 'json',
		success : function(result, status) {
			if (result.RESULT_CODE == 0) {
				$("#"+imgId).attr("src",
						contextPath + "/file_servelt" + result.FILE_PATH);
				$("#"+valueFiledId).val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error : function(result, status, e) {
			alert("服务异常");
		}
	});

}


</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" value="${mchtInfo.id }" name="id">
		<table class="gridtable">
			<tr>
			<td  colspan="1" class="title">公司名称</td>
				<td  colspan="7" align="left" class="l-table-edit-td">
			<input id="companyName" validate="{required:true}"
					name="companyName" type="text" value="${mchtInfo.companyName }"
					style="float:left;" />
				</td>

				</tr>
				
				<tr>
				<td  colspan="1" class="title">营业执照副本<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
				<t:imageList name="pictures" list="${mchtBlPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
    			<div style="float: left;height: 105px;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="mchtBlPic" name="file" onchange="ajaxFileUpload();" />
    			<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="mchtBlPics" name="mchtBlPics" type="hidden" value="">
				
				</td>
				</tr>
				
				<tr>
				<td  colspan="1" class="title">公司类型 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				<select style="width: 160px;" id="companyType" name="companyType" validate="{selectRequired:true}">
					<c:forEach var="statusItem" items="${companyTypeStatus}">
						<option <c:if test="${mchtInfo.companyType==statusItem.statusValue}">selected</c:if>
						value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
				</select>
				</td>
						<td  colspan="1" class="title">统一信用代码 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="uscc" validate="{required:true}"
					name="uscc" type="text" value="${mchtInfo.uscc }"
					style="float:left;" /></td>
				
			</tr>
				
				
				
				
				<tr>
				<td  colspan="1" class="title">公司住所 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="companyAddress" validate="{required:true}"
					name="companyAddress" type="text" value="${mchtInfo.companyAddress }"
					/></td>
				
				
				<td  colspan="1" class="title">成立日期<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="foundedDate" name="foundedDate"  value="" style="float:left;" validate="{required:true}"/>
				</td>
				
			</tr>
			<tr>
			
			<td  colspan="1" class="title">注册资本<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="regCapital" validate="{required:true,number:true}"
					name="regCapital" type="text" value="${mchtInfo.regCapital }"
					/>万元</td>
			<td  colspan="1" class="title">注册资本币种<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<select style="width: 160px;" id="regFeeType" name="regFeeType" validate="{selectRequired:true}">
					<c:forEach var="statusItem" items="${regFeeTypeStatus}">
						<option <c:if test="${mchtInfo.regFeeType==statusItem.statusValue}">selected</c:if>
						value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
					</select>
				</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">法人姓名<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="corporationName" validate="{required:true}"
					name="corporationName" type="text" value="${mchtInfo.corporationName }"
					/>
				</td>
					
				<td  colspan="1" class="title">法人身份证号<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="corporationIdcardNo" validate="{required:true}"
					name="corporationIdcardNo" type="text" value="${mchtInfo.corporationIdcardNo }"
					/>
				</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">法人手机<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
				<input id="corporationMobile" validate="{required:true}"
					name="corporationMobile" type="text" value="${mchtInfo.corporationMobile }"
					/>
				</td>
					
				
			</tr>
			<tr>
			<td  colspan="1" class="title">身份证正面<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<div><img id="idcardImgUp" style="width: 100px;height: 100px;" alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg1}"></div>
    			<div style="float: left;height: 105px;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="corporationIdcardImgUp" name="file" onchange="ajaxFileUploadIdcard('corporationIdcardImgUp','idcardImgUp','corporationIdcardImg1');" />
    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="corporationIdcardImg1" name="corporationIdcardImg1" type="hidden" value="${mchtInfo.corporationIdcardImg1}" validate="{required:true}">
				</td>
				
			<td  colspan="1" class="title">身份证背面<span class="required">*</span></td>
					<td  colspan="3" align="left" class="l-table-edit-td">
				<div><img id="idcardImgDown" style="width: 100px;height: 100px;" alt="" src="${pageContext.request.contextPath}/file_servelt${mchtInfo.corporationIdcardImg2}"></div>
    			<div style="float: left;height: 105px;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="corporationIdcardImgDown" name="file" onchange="ajaxFileUploadIdcard('corporationIdcardImgDown','idcardImgDown','corporationIdcardImg2');" />
    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="corporationIdcardImg2" name="corporationIdcardImg2" type="hidden" value="${mchtInfo.corporationIdcardImg2}" validate="{required:true}">
				</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">年检有效期限<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="yearlyInspectionDate" name="yearlyInspectionDate" value=""  style="float:left;" validate="{required:true}"/>
				</td>
					
				<td  colspan="1" class="title">公司总机<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input id="companyTel" validate="{required:true}"
					name="companyTel" type="text" value="${mchtInfo.companyTel }"
					/>
				</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">公司通讯地址<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
				<select style="width: 100px;" id="contactProvince" onchange="onchangeProvince();" name="contactProvince" validate="{selectRequired:true}">
				</select>
				<select style="width: 100px;" id="contactCity" onchange="onchangeCity();" name="contactCity" validate="{selectRequired:true}">
				</select>
				<select style="width: 100px;" id=contactCounty   name="contactCounty" validate="{selectRequired:true}">
				</select>
				
				<input id="contactAddress" style="width: 200px;" validate="{required:true}"
					name="contactAddress" type="text" value="${mchtInfo.contactAddress }"
					/>
				</td>
					
			</tr>
					<tr>
			<td  colspan="1" class="title">审核状态<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
				<c:forEach var="pefectStatusItem" items="${isCompanyInfPerfectStatus }">
					<span class="radioClass"><input type="radio" value="${pefectStatusItem.statusValue }" name="isCompanyInfPerfect" <c:if test="${pefectStatusItem.statusValue==mchtInfo.isCompanyInfPerfect}">checked="checked"</c:if> >${pefectStatusItem.statusDesc}</span>
				</c:forEach>	
				</td>
					
			</tr>
					<tr>
			<td  colspan="1" class="title">驳回原因<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
					<textarea rows=5
						id="companyInfAuditRemarks" name="companyInfAuditRemarks" cols="50" class="text" validate="{auditRemarksRequired:true}"  >${mchtInfo.companyInfAuditRemarks}</textarea>
			
				</td>
					
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
							<input name="btnSubmit" type="submit" id="Button1"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
