<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>公司信息完善</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
      <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
.glyphicon-remove{
color:red;
margin-left: 3px;
cursor:pointer;
}
.docs-pictures{
padding: 0px;
}
.docs-pictures li{
position: relative;
margin: 3px;
}


.pic-btn-container{
	width:100%;
	position: absolute;
    top: 0px;
    background:rgba(0, 0, 0, 0.5);
    height:0px;
    z-index: 300;
    overflow: hidden;
    text-align: right;
    padding-right: 3px;
}

</style>


</head>

<body>

  <div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">公司信息完善</span>
      </div>
			<div class="modal-body">
		<span style="color:red;">填写前请准备好内容及资质文件，所有资质文件都要求真实、合法有效；所有资质文件以A4纸复印并加盖公司公章扫描后上传，上传后需保证文件图片清晰、内容清楚、章印清晰。</span>
		<br>
		<br>
		<form id="dataFrom">
		<input type="hidden" value="${mchtInfo.id}" name="id">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">公司名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input style="width:80%;"  type="text" id="companyName" name="companyName" value="${mchtInfo.companyName}" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">公司类型</c:if><c:if test="${mchtInfo.settledType eq 2}">组成形式</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
						  <%-- <select  name="companyType" id="companyType" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="companyTypeStatus" items="${companyTypeStatusList }">
						   <option value="${companyTypeStatus.statusValue}"   <c:if test="${mchtInfo.companyType==companyTypeStatus.statusValue}">selected</c:if>>${companyTypeStatus.statusDesc}</option>
						   </c:forEach>
                          </select> --%>
                          <input type="text" id="companyType" name="companyType" value="${mchtInfo.companyType}" validate="{required:true}">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">营业执照注册号<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input style="width:80%;"  type="text" id="uscc" name="uscc" value="${mchtInfo.uscc}" validate="{required:true}">
						</td>
					</tr>
					<c:if test="${mchtInfo.settledType eq 1}">
					<tr>
						<td class="editfield-label-td">注册资本<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input style="width:15%;"  type="text" value="${mchtInfo.regCapital}" name="regCapital" validate="{required:true,number:true}">
						<span>（万）</span>
						<select  name="regFeeType" id="regFeeType" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="regfeeTypeStatus" items="${regfeeTypeStatusList }">
						   <option value="${regfeeTypeStatus.statusValue}"   <c:if test="${mchtInfo.regFeeType==regfeeTypeStatus.statusValue}">selected</c:if>>${regfeeTypeStatus.statusDesc}</option>
						   </c:forEach>
                         </select>
						
						</td>
					</tr>
					</c:if>

					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">公司住所</c:if><c:if test="${mchtInfo.settledType eq 2}">经营场所</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input style="width:80%;"  type="text" id="companyAddress" name="companyAddress" value="${mchtInfo.companyAddress}" validate="{required:true}">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">成立日期</c:if><c:if test="${mchtInfo.settledType eq 2}">发照时间</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text"  validate="{required:true,dateISO:true}" value="<fmt:formatDate value='${mchtInfo.foundedDate}' pattern='yyyy-MM-dd'/>" id="foundedDate" name="foundedDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">营业执照有效期<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text"  validate="{required:true,dateISO:true}" value="<fmt:formatDate value='${mchtInfo.yearlyInspectionDate}' pattern='yyyy-MM-dd'/>" id="yearlyInspectionDate" name="yearlyInspectionDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">营业执照经营范围</c:if><c:if test="${mchtInfo.settledType eq 2}">经营范围</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="scopeOfBusiness" name="scopeOfBusiness" validate="{required:true}">${mchtInfo.scopeOfBusiness}</textarea>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">法人姓名</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者姓名</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="corporationName" name="corporationName" value="${mchtInfo.corporationName}" validate="{required:true}"><span style="color: #999999;">需与公司执照、法人身份证一致</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">法人身份证号</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者身份证号</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input style="width:70%;"  type="text" id="corporationIdcardNo" name="corporationIdcardNo" value="${mchtInfo.corporationIdcardNo}" validate="{required:true}"><span style="color: #999999;">需与公司执照、法人身份证一致</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">法人身份证正背面</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者身份证正背面</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfo.corporationIdcardImg1!=null&&mchtInfo.corporationIdcardImg1!=""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg1File" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg1}"><input type="hidden"  id="corporationIdcardImg1" name="corporationIdcardImg1" value="${mchtInfo.corporationIdcardImg1}"></div>
							</c:if>
							<c:if test='${mchtInfo.corporationIdcardImg1==null||mchtInfo.corporationIdcardImg1==""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg1File" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="corporationIdcardImg1" name="corporationIdcardImg1" value="${mchtInfo.corporationIdcardImg1}"></div>
							</c:if>
							<c:if test='${mchtInfo.corporationIdcardImg2!=null&&mchtInfo.corporationIdcardImg2!=""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg2File" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg2}"><input type="hidden"  id="corporationIdcardImg2" name="corporationIdcardImg2" value="${mchtInfo.corporationIdcardImg2}"></div>
							</c:if>
							<c:if test='${mchtInfo.corporationIdcardImg2==null||mchtInfo.corporationIdcardImg2==""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg2File" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="corporationIdcardImg2" name="corporationIdcardImg2" value="${mchtInfo.corporationIdcardImg2}"></div>
							</c:if>
							<div style="color: #999999;">法人身份证复印至同一张A4纸内，由法人签字并加盖公司公章</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">法人身份证有效期</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者身份证有效期</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="corporationIdcardDate" name="corporationIdcardDate" value="<fmt:formatDate value='${mchtInfo.corporationIdcardDate}' pattern='yyyy-MM-dd'/>" validate="{required:true,dateISO:true}" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">法人手机</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者手机</c:if></td>
						<td colspan="2" class="text-left">
						<input type="text" id="corporationMobile" name="corporationMobile" value="${mchtInfo.corporationMobile}" validate="{required:true}">
						</td>
					</tr>
					<c:if test="${mchtInfo.settledType eq 1}">
					<tr>
						<td class="editfield-label-td">公司总机</td>
						<td colspan="2" class="text-left">
						<input type="text" id="companyTel" name="companyTel" value="${mchtInfo.companyTel}" validate="{required:true}">
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">公司通讯地址</c:if><c:if test="${mchtInfo.settledType eq 2}">通讯地址</c:if><span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<select style="width:100px;" class="province-select" onchange="onchangeProvince();" name="contactProvince" id="contactProvince" >
                         </select>
                         <select style="width:100px;" class="city-select" onchange="onchangeCity();" name="contactCity" id="contactCity">
                         </select>
                         <select style="width:100px;" class="county-select"  name="contactCounty" id="contactCounty" validate="{required:true}">
                         </select>
                         <div style="color: #999999;">该通讯地址将作为合作协议上的通讯地址，需保证地址真实、有效、准确</div>
                         <br>
						<input style="width:80%;" type="text" id="contactAddress" name="contactAddress" value="${mchtInfo.contactAddress}" validate="{required:true}">
						</td>
					</tr>
				<tr>
						<td class="editfield-label-td">营业执照副本<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						
							<c:if test='${mchtInfo.blPic!=null&&mchtInfo.blPic!=""}'>
							<div class="single_pic_picker"><input id="blPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.blPic}"><input type="hidden"  id="blPic" name="blPic" value="${mchtInfo.blPic}"></div>
							</c:if>
							<c:if test='${mchtInfo.blPic==null||mchtInfo.blPic==""}'>
							<div class="single_pic_picker"><input id="blPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="blPic" name="blPic" value=""></div>
							</c:if>
						
						
<!-- 					    <div class="pic-container"> -->
<!-- 								<ul class="docs-pictures clearfix td-pictures pictures-list" id="pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)"> -->
<!-- 								<c:forEach var="mchtBlPic" items="${mchtBlPics}" varStatus="varStatus"> -->
<!-- 								<li id="mchtBlPic_li_${varStatus.index }" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtBlPic.pic}"> -->
<!-- 								<img  src="${ctx}/file_servelt${mchtBlPic.pic}"> -->
<!-- 								<div class="pic-btn-container" style="height: 0px;"> -->
<!-- 								<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span> -->
<!-- 								</div> -->
<!-- 								</li> -->
<!-- 								</c:forEach> -->
<!-- 								</ul> -->
<!-- 								<div class="filePicker">选择图片</div> -->
<!-- 						</div> -->

						</td>
					</tr>
					<c:if test="${mchtInfo.settledType eq 1}">
				    <tr>
						<td class="editfield-label-td">组织机构代码证：<br><span style="color:#999999;">已三证合一可以不填</span></td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfo.occPic!=null&&mchtInfo.occPic!=""}'>
								<div class="single_pic_picker"><input id="occPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.occPic}"><input type="hidden"  id="occPic" name="occPic" value="${mchtInfo.occPic}"></div>
							</c:if>
							<c:if test='${mchtInfo.occPic==null||mchtInfo.occPic==""}'>
								<div class="single_pic_picker"><input id="occPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="occPic" name="occPic" value=""></div>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">税务登记证<br><span style="color:#999999;">已三证合一可以不填</span></td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfo.trcPic!=null&&mchtInfo.trcPic!=""}'>
								<div class="single_pic_picker"><input id="trcPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.trcPic}"><input type="hidden"  id="trcPic" name="trcPic" value="${mchtInfo.trcPic}"></div>
							</c:if>
							<c:if test='${mchtInfo.trcPic==null||mchtInfo.trcPic==""}'>
								<div class="single_pic_picker"><input id="trcPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="trcPic" name="trcPic" value=""></div>
							</c:if>
						</td>
					</tr>
					
				    <tr>
						<td class="editfield-label-td">一般纳税人资格证</td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfo.atqPic!=null&&mchtInfo.atqPic!=""}'>
								<div class="single_pic_picker"><input id="atqPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.atqPic}"><input type="hidden"  id="atqPic" name="atqPic" value="${mchtInfo.atqPic}"></div>
							</c:if>
							<c:if test='${mchtInfo.atqPic==null||mchtInfo.atqPic==""}'>
								<div class="single_pic_picker"><input id="atqPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="atqPic" name="atqPic" value=""></div>
							</c:if>
						</td>
					</tr>
					</c:if>
				    <tr>
						<td class="editfield-label-td"><c:if test="${mchtInfo.settledType eq 1}">银行开户许可证</c:if><c:if test="${mchtInfo.settledType eq 2}">经营者银行卡信息</c:if></td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfo.boaalPic!=null&&mchtInfo.boaalPic!=""}'>
							<div class="single_pic_picker"><input id="boaalPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfo.boaalPic}"><input type="hidden"  id="boaalPic" name="boaalPic" value="${mchtInfo.boaalPic}"></div>
							</c:if>
							<c:if test='${mchtInfo.boaalPic==null||mchtInfo.boaalPic==""}'>
							<div class="single_pic_picker"><input id="boaalPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="boaalPic" name="boaalPic" value=""></div>
							</c:if>
							<div style="color: #999999;">经营者的银行卡（银行卡复印件上需经营者写明开户行、账号、账户名，并由经营者签字按手印）</div>
						</td>
					</tr>
					<tr>
						 <td class="editfield-label-td">主要经营品牌类型<span class="required">*</span></td>
						 <td colspan="2" class="text-left">
                         <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}">
                         <option value="">请选择</option>
                         <option value="0">品牌官方</option>
                         <option value="1">品牌总代</option>
                         <option value="2">品牌代理商</option>
                         </select>
                         <br><br>
                         <textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfo.brandTypeDesc}</textarea>
						 </td>
					</tr>
						
					<c:if test="${mchtInfo.isCompanyInfPerfect == 4}">
					<tr>
						<td class="editfield-label-td" style="color: red;">驳回原因</td>
						<td colspan="2" class="text-left" style="color: red;word-break:break-all;">
							${mchtInfo.companyInfAuditRemarks}
						</td>
					</tr>
					</c:if>
					
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
			<button class="btn btn-info" onclick="commitAudit();" <c:if test="${mchtInfo.isCompanyInfPerfect == 3 || mchtInfo.isCompanyInfPerfect == 1}">disabled="disabled"</c:if>>提交</button>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileUpload.js"></script> -->
<!--     <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>	 -->
	
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
  		{  var sel = $(".province-select");
				sel.empty();
				sel.append("<option value=\"" + "\">--请选择--</option>");
				
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
					sel.append("<option value=\"" + "\">--请选择--</option>");
					
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
				sel.append("<option value=\"" + "\">--请选择--</option>");
				
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

function callBack(){
	
}
$(function(){

	getProvinceList();
	getCityList(${mchtInfo.contactProvince});
	getCountyList(${mchtInfo.contactCity});  
	
	$(".province-select").val(${mchtInfo.contactProvince});
	$(".city-select").val(${mchtInfo.contactCity});
	$(".county-select").val(${mchtInfo.contactCounty});
	$("#brandType").val(${mchtInfo.brandType});
	
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});

    $('#foundedDate').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
    $('#yearlyInspectionDate').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
    $('#corporationIdcardDate').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
});


var blPics=[];
function commitAudit(){
	if(dataFromValidate.form()){
			var $idCar1Img=$("#corporationIdcardImg1").parent().children("img");
			var $idCar2Img=$("#corporationIdcardImg2").parent().children("img");
			
			if($idCar1Img.length<=0||$idCar2Img.length<=0){
					swal({
						  title: '请上传身份证件',
						  type: "error",
						  confirmButtonText: "确定"
						});
				return;
			}
			
			var $blPic=$("#blPic").parent().children("img");
			if($blPic.length<=0){
				swal({
					  title: '请上传营业执照副本',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}
			
			var $occPic=$("#occPic").parent().children("img");
			var $trcPic=$("#trcPic").parent().children("img");
			var $atqPic=$("#atqPic").parent().children("img");
			
			var $boaalPic=$("#boaalPic").parent().children("img");
/*			if($boaalPic.length<=0){
				swal({
					  title: '请上传银行开户许可证',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}*/

			
			if("${mchtInfo.corporationIdcardImg1}"==""||$idCar1Img.attr("src").indexOf("${mchtInfo.corporationIdcardImg1}")<0){//有修改
				uploadIdcard("corporationIdcardImg1File",$("#corporationIdcardImg1"));
			}
			if("${mchtInfo.corporationIdcardImg2}"==""||$idCar2Img.attr("src").indexOf("${mchtInfo.corporationIdcardImg2}")<0){//有修改
				uploadIdcard("corporationIdcardImg2File",$("#corporationIdcardImg2"));
			}
			
			if("${mchtInfo.blPic}"==""||$blPic.attr("src").indexOf("data:image")==0){//有修改
				uploadIdcard("blPicFile",$("#blPic"));
			}
			
			if(${mchtInfo.settledType eq 1}){
				if("${mchtInfo.occPic}"==""||$occPic.attr("src").indexOf("${mchtInfo.occPic}")<0){//有修改
					uploadIdcard("occPicFile",$("#occPic"));
				}
				if("${mchtInfo.trcPic}"==""||$trcPic.attr("src").indexOf("${mchtInfo.trcPic}")<0){//有修改
					uploadIdcard("trcPicFile",$("#trcPic"));
				}
				
				if("${mchtInfo.atqPic}"==""||$atqPic.attr("src").indexOf("${mchtInfo.atqPic}")<0){//有修改
					uploadIdcard("atqPicFile",$("#atqPic"));
				}
			}
			if($boaalPic.length>0&&$boaalPic.attr("src")!=""&&$boaalPic.attr("src").indexOf("data:image")==0){//有修改
				uploadIdcard("boaalPicFile",$("#boaalPic"));
			}
			var dataJson = $("#dataFrom").serializeArray();
			$.ajax({
				url : "${ctx}/mcht/mchtInfoPerfectCommitAudit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
						$("#viewModal").modal('hide');
						swal({
							  title: "提交成功!",
							  type: "success",
							  confirmButtonText: "确定"
							  
							});
						<c:if test="${mchtInfo.status == 0}">
							setTimeout(function(){
								location.reload();
							},1000);
						</c:if>
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
	}
	
}
// 上传身份正
// function uploadIdcard($img,$valueFiled) {
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
