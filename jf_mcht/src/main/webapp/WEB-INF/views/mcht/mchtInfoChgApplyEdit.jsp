<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>申请修改公司信息</title>
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
        <span class="modal-title" id="exampleModalLabel">申请修改公司信息</span>
      </div>
		<div class="modal-body">
		<c:if test='${mchtInfoChg.status=="4"}'>
		<span style="color:red;">对不起，您的申请被驳回，驳回原因：${mchtInfoChg.auditRemarks}</span>
		<br>
		<br>
		</c:if>

		<form id="dataFrom">
		<input type="hidden" value="${mchtInfoChg.id}" name="id">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">公司名称<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input style="width:80%;" type="text" id="companyName" name="companyName" value="${mchtInfoChg.companyName}" validate="{required:true}">
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">公司类型<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						  <%-- <select  name="companyType" id="companyType" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="companyTypeStatus" items="${companyTypeStatusList }">
						   <option value="${companyTypeStatus.statusValue}"   <c:if test="${mchtInfoChg.companyType==companyTypeStatus.statusValue}">selected</c:if>>${companyTypeStatus.statusDesc}</option>
						   </c:forEach>
                          </select> --%>
                          <input type="text" id="companyType" name="companyType" value="${mchtInfoChg.companyType}" validate="{required:true}">
						</td>
					</tr>
					
					
				    <tr>
						<td class="editfield-label-td">营业执照注册号</td>
						<td colspan="2" class="text-left">
							<input style="width:80%;" type="text" id="uscc" name="uscc" value="${mchtInfoChg.uscc}" validate="{required:true}">
						</td>
					</tr>
					
					
					
					<tr>
						<td class="editfield-label-td">注册资本<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input style="width:15%;" type="text" value="${mchtInfoChg.regCapital}" name="regCapital" validate="{required:true,number:true}">
						<span>（万）</span>
						<select  name="regFeeType" id="regFeeType" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="regfeeTypeStatus" items="${regfeeTypeStatusList }">
						   <option value="${regfeeTypeStatus.statusValue}"   <c:if test="${mchtInfoChg.regFeeType==regfeeTypeStatus.statusValue}">selected</c:if>>${regfeeTypeStatus.statusDesc}</option>
						   </c:forEach>
                         </select>
						
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">公司住所<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input style="width:80%;" type="text" id="companyAddress" name="companyAddress" value="${mchtInfoChg.companyAddress}" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">成立日期<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text"  validate="{required:true,dateISO:true}" value="<fmt:formatDate value='${mchtInfoChg.foundedDate}' pattern='yyyy-MM-dd'/>" id="foundedDate" name="foundedDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">营业执照有效期<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<input type="text"  validate="{required:true,dateISO:true}" value="<fmt:formatDate value='${mchtInfoChg.yearlyInspectionDate}' pattern='yyyy-MM-dd'/>" id="yearlyInspectionDate" name="yearlyInspectionDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">营业执照经营范围<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="scopeOfBusiness" name="scopeOfBusiness" validate="{required:true}">${mchtInfoChg.scopeOfBusiness}</textarea>
						</td>
					</tr>
										
					<tr>
						<td class="editfield-label-td">法人姓名<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="corporationName" name="corporationName" value="${mchtInfoChg.corporationName}" validate="{required:true}">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">法人身份证号<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input style="width:80%;" type="text" id="corporationIdcardNo" name="corporationIdcardNo" value="${mchtInfoChg.corporationIdcardNo}" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">法人身份证正背面<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfoChg.corporationIdcardImg1!=null&&mchtInfoChg.corporationIdcardImg1!=""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg1File" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfoChg.corporationIdcardImg1}"><input type="hidden"  id="corporationIdcardImg1" name="corporationIdcardImg1" value="${mchtInfoChg.corporationIdcardImg1}"></div>
							</c:if>
							<c:if test='${mchtInfoChg.corporationIdcardImg1==null||mchtInfoChg.corporationIdcardImg1==""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg1File" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="corporationIdcardImg1" name="corporationIdcardImg1" ></div>
							</c:if>
							<c:if test='${mchtInfoChg.corporationIdcardImg2!=null&&mchtInfoChg.corporationIdcardImg2!=""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg2File" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfoChg.corporationIdcardImg2}"><input type="hidden"  id="corporationIdcardImg2" name="corporationIdcardImg2" value="${mchtInfoChg.corporationIdcardImg2}"></div>
							</c:if>
							<c:if test='${mchtInfoChg.corporationIdcardImg2==null||mchtInfoChg.corporationIdcardImg2==""}'>
							<div class="single_pic_picker"><input id="corporationIdcardImg2File" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="corporationIdcardImg2" name="corporationIdcardImg2" ></div>
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">法人身份证有效期</td>
						<td colspan="2" class="text-left">
							<input type="text" value="<fmt:formatDate value='${mchtInfoChg.corporationIdcardDate}' pattern='yyyy-MM-dd'/>" id="corporationIdcardDate" name="corporationIdcardDate" data-date-format="yyyy-mm-dd">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">法人手机</td>
						<td colspan="2" class="text-left">
						<input type="text" id="corporationMobile" name="corporationMobile" value="${mchtInfoChg.corporationMobile}" validate="{required:true,mobile:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">公司总机</td>
						<td colspan="2" class="text-left">
						<input type="text" id="companyTel" name="companyTel" value="${mchtInfoChg.companyTel}" validate="{required:true}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">公司通讯地址</td>
						<td colspan="2" class="text-left">
						<select style="width:100px;" class="province-select" onchange="onchangeProvince();" name="contactProvince" id="contactProvince" >
                         </select>
                         <select style="width:100px;" class="city-select" onchange="onchangeCity();" name="contactCity" id="contactCity" >
                         </select>
                         <select style="width:100px;" class="county-select"  name="contactCounty" id="contactCounty" validate="{required:true}">
                         </select>
                         <br>
                         <br>
						<input style="width:80%;"  type="text" id="contactAddress" name="contactAddress" value="${mchtInfoChg.contactAddress}" validate="{required:true}">
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">营业执照副本<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						
							<c:if test='${mchtInfoChg.blPic!=null&&mchtInfoChg.blPic!=""}'>
							<div class="single_pic_picker"><input id="blPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfoChg.blPic}"><input type="hidden"  id="blPic" name="blPic" value="${mchtInfoChg.blPic}"></div>
							</c:if>
							<c:if test='${mchtInfoChg.blPic==null||mchtInfoChg.blPic==""}'>
							<div class="single_pic_picker"><input id="blPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="blPic" name="blPic" value=""></div>
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">组织机构代码证：<br><span style="color:#999999;">已三证合一可以不填</span></td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfoChg.occPic!=null&&mchtInfoChg.occPic!=""}'>
								<div class="single_pic_picker"><input id="occPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfoChg.occPic}"><input type="hidden"  id="occPic" name="occPic" value="${mchtInfoChg.occPic}"></div>
							</c:if>
							<c:if test='${mchtInfoChg.occPic==null||mchtInfoChg.occPic==""}'>
								<div class="single_pic_picker"><input id="occPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="occPic" name="occPic" value=""></div>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">税务登记证<br><span style="color:#999999;">已三证合一可以不填</span></td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfoChg.trcPic!=null&&mchtInfoChg.trcPic!=""}'>
								<div class="single_pic_picker"><input id="trcPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfoChg.trcPic}"><input type="hidden"  id="trcPic" name="trcPic" value="${mchtInfoChg.trcPic}"></div>
							</c:if>
							<c:if test='${mchtInfoChg.trcPic==null||mchtInfoChg.trcPic==""}'>
								<div class="single_pic_picker"><input id="trcPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="trcPic" name="trcPic" value=""></div>
							</c:if>
						</td>
					</tr>
					
				    <tr>
						<td class="editfield-label-td">一般纳税人资格证</td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfoChg.atqPic!=null&&mchtInfoChg.atqPic!=""}'>
								<div class="single_pic_picker"><input id="atqPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfoChg.atqPic}"><input type="hidden"  id="atqPic" name="atqPic" value="${mchtInfoChg.atqPic}"></div>
							</c:if>
							<c:if test='${mchtInfoChg.atqPic==null||mchtInfoChg.atqPic==""}'>
								<div class="single_pic_picker"><input id="atqPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="atqPic" name="atqPic" value=""></div>
							</c:if>
						</td>
					</tr>

				    <tr>
						<td class="editfield-label-td">银行开户许可证</td>
						<td colspan="2" class="text-left">
							<c:if test='${mchtInfoChg.boaalPic!=null&&mchtInfoChg.boaalPic!=""}'>
							<div class="single_pic_picker"><input id="boaalPicFile" onchange="loadImageFile(this)" type="file"><img  src="${ctx}/file_servelt${mchtInfoChg.boaalPic}"><input type="hidden"  id="boaalPic" name="boaalPic" value="${mchtInfoChg.boaalPic}"></div>
							</c:if>
							<c:if test='${mchtInfoChg.boaalPic==null||mchtInfoChg.boaalPic==""}'>
							<div class="single_pic_picker"><input id="boaalPicFile" onchange="loadImageFile(this)" type="file"><div>+</div><input type="hidden"  id="boaalPic" name="boaalPic" value=""></div>
							</c:if>
						</td>
					</tr>
					<tr>
						 <td class="editfield-label-td">主要经营品牌类型<span class="required">*</span></td>
						 <td colspan="2" class="text-left">
                         <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}">
                         <option value="">请选择</option>
                         <option value="0" <c:if test="${mchtInfoChg.brandType eq 0}">  selected = "selected" </c:if>>品牌官方</option>
                         <option value="1" <c:if test="${mchtInfoChg.brandType eq 1}">  selected = "selected" </c:if>>品牌总代</option>
                         <option value="2" <c:if test="${mchtInfoChg.brandType eq 2}">  selected = "selected" </c:if>>品牌代理商</option>
                         </select>
                         <br><br>
                         <textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfoChg.brandTypeDesc}</textarea>
						 </td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
	
			<button class="btn btn-info" onclick="commitAudit();">提交审核</button>
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
<!--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/fileUpload.js"></script> -->
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>	
	
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

$(function(){
	
	getProvinceList();
	getCityList(${mchtInfoChg.contactProvince});
	getCountyList(${mchtInfoChg.contactCity});  
	
	$(".province-select").val(${mchtInfoChg.contactProvince});
	$(".city-select").val(${mchtInfoChg.contactCity});
	$(".county-select").val(${mchtInfoChg.contactCounty});
	$("#brandType").val(${mchtInfoChg.brandType});
	
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




function commitSave(){
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

			
			if("${mchtInfoChg.corporationIdcardImg1}"==""||$idCar1Img.attr("src").indexOf("${mchtInfoChg.corporationIdcardImg1}")<0){//有修改
				uploadIdcard("corporationIdcardImg1File",$("#corporationIdcardImg1"));
			}
			if("${mchtInfoChg.corporationIdcardImg2}"==""||$idCar2Img.attr("src").indexOf("${mchtInfoChg.corporationIdcardImg2}")<0){//有修改
				uploadIdcard("corporationIdcardImg2File",$("#corporationIdcardImg2"));
			}
			
			
			if("${mchtInfoChg.blPic}"==""||$blPic.attr("src").indexOf("data:image")==0){//有修改
				uploadIdcard("blPicFile",$("#blPic"));
			}
			
			if("${mchtInfoChg.occPic}"==""||$occPic.attr("src").indexOf("${mchtInfoChg.occPic}")<0){//有修改
				uploadIdcard("occPicFile",$("#occPic"));
			}
			
			if("${mchtInfoChg.trcPic}"==""||$trcPic.attr("src").indexOf("${mchtInfoChg.trcPic}")<0){//有修改
				uploadIdcard("trcPicFile",$("#trcPic"));
			}
			
			if("${mchtInfoChg.atqPic}"==""||$atqPic.attr("src").indexOf("${mchtInfoChg.atqPic}")<0){//有修改
				uploadIdcard("atqPicFile",$("#atqPic"));
			}
			
			if($boaalPic.length>0&&$boaalPic.attr("src")!=""&&$boaalPic.attr("src").indexOf("data:image")==0){//有修改
				uploadIdcard("boaalPicFile",$("#boaalPic"));
			}
			
			var dataJson = $("#dataFrom").serializeArray();
			$.ajax({
				url : "${ctx}/mcht/mchtInfoChgCommitSave",
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

			
			if("${mchtInfoChg.corporationIdcardImg1}"==""||$idCar1Img.attr("src").indexOf("${mchtInfoChg.corporationIdcardImg1}")<0){//有修改
				uploadIdcard("corporationIdcardImg1File",$("#corporationIdcardImg1"));
			}
			if("${mchtInfoChg.corporationIdcardImg2}"==""||$idCar2Img.attr("src").indexOf("${mchtInfoChg.corporationIdcardImg2}")<0){//有修改
				uploadIdcard("corporationIdcardImg2File",$("#corporationIdcardImg2"));
			}
			
			
			if("${mchtInfo.blPic}"==""||$blPic.attr("src").indexOf("data:image")==0){//有修改
				uploadIdcard("blPicFile",$("#blPic"));
			}
			
			if("${mchtInfoChg.occPic}"==""||$occPic.attr("src").indexOf("${mchtInfoChg.occPic}")<0){//有修改
				uploadIdcard("occPicFile",$("#occPic"));
			}
			
			if("${mchtInfoChg.trcPic}"==""||$trcPic.attr("src").indexOf("${mchtInfoChg.trcPic}")<0){//有修改
				uploadIdcard("trcPicFile",$("#trcPic"));
			}
			
			if("${mchtInfoChg.atqPic}"==""||$atqPic.attr("src").indexOf("${mchtInfoChg.atqPic}")<0){//有修改
				uploadIdcard("atqPicFile",$("#atqPic"));
			}
			
			if($boaalPic.length>0&&$boaalPic.attr("src")!=""&&$boaalPic.attr("src").indexOf("data:image")==0){//有修改
				uploadIdcard("boaalPicFile",$("#boaalPic"));
			}
			
			var dataJson = $("#dataFrom").serializeArray();
			$.ajax({
				url : "${ctx}/mcht/mchtInfoChgCommitAudit",
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
						$("#infoChgApply").remove();
						 table.ajax.reload();
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



//上传身份正
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
