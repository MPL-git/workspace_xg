<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	

<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	
	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
 .l-text-readonly{
 border-bottom: none !important;
 }
 td img{
 width: 60px;
 height: 40px;
 }
 td ul li{
	display: inline-block;
 }
</style>
<script type="text/javascript">
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


var logoPic_viewer; 
var platformAuthPic_viewer; 
var mchtBrandInvoicePic_viewer; 
var mchtBrandInspectionPic_viewer; 
var mchtBrandOtherPics_viewer; 


$(function ()  {
	
	logoPic_viewer = new Viewer(document.getElementById('logoPic_viewer'), {});
	platformAuthPic_viewer = new Viewer(document.getElementById('platformAuthPic_viewer'), {});
	mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
	mchtBrandInspectionPic_viewer = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer'), {});
	mchtBrandOtherPics_viewer = new Viewer(document.getElementById('mchtBrandOtherPics_viewer'), {});
	
	if('${view }' == "1") {
		$.metadata.setType("attr", "validate");
		
	    //当运营状态=正常时，但审核状态！=通过时。提时：对不起，审核状态未通过，运营状态不能为正常。
	    $.validator.addMethod("statusCheck", function(value, element) {  
	    	if(!$("input[type=radio][name='auditStatus'][value=3]").attr("checked")&&$("#status").val()=="1"){
	 			return false;
	 		}else{
	 			return true;
	 		}
	    }, "审核状态未通过，运营状态不能为正常");
	    
	    //驳回原因必填
	    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
	    	if($("input[type=radio][name='auditStatus'][value=4]").attr("checked")&&$.trim($("#auditRemarks").val())==""){
	 			return false;
	 		}else{
	 			return true;
	 		}
	    }, "请注明驳回原因");
	    
	    //非未完善状态必填
	    $.validator.addMethod("perfectRequired", function(value, element) { 
	    	
	    	if(($("input[type=radio][name='auditStatus'][value=1]").attr("checked")||$("input[type=radio][name='auditStatus'][value=2]").attr("checked")||$("input[type=radio][name='auditStatus'][value=3]").attr("checked")||$("input[type=radio][name='auditStatus'][value=4]").attr("checked"))&&$(element).attr("type")=="radio"){
	  		   var radioName=$(element).attr("name");
			   var hasSelect=false;
			   $($("input[type=radio][name="+radioName+"]")).each(function(){
				    if($(this).attr("checked")){
				    	hasSelect=true;
				    }
				  });
			   return hasSelect;
	    	}
	    	
	    	if(($("input[type=radio][name='auditStatus'][value=1]").attr("checked")||$("input[type=radio][name='auditStatus'][value=2]").attr("checked")||$("input[type=radio][name='auditStatus'][value=3]").attr("checked")||$("input[type=radio][name='auditStatus'][value=4]").attr("checked"))&&value==""){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    	
	    }, "该字段不能为空");
		
	    var v = $("#form1").validate({
	    	
	        errorPlacement: function (lable, element)
	        {  
	        	var elementType=$(element).attr("type");
	
	        	if($(element).hasClass("l-text-field")){
	        		$(element).parent().ligerTip({
						content : lable.html(),width: 100
					});
	        	}
	        	else if('radio'==elementType){
	        		var radioName=$(element).attr("name");
	        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
						content : lable.html(),width: 100
					});
	        	}else if('hidden'==elementType){
	        		$(element).closest('td').find("div").ligerTip({
						content : lable.html(),width: 100
					});  
	        	}
	        	
	        	else{
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
	        	mchtProductBrandSaveSubmit();
	        }
	    });   
	}
	
	            
});    

function getGridOptions(){
    var options = {
        columns: [
        { display: 'ID', name: 'id', minWidth: 100, width: 100 },
        { display: '品牌', name: 'name', minWidth: 100, width: 100 }
        ], 
        switchPageSizeApplyComboBox: false,
        url: '${pageContext.request.contextPath}/productBrand/selectDatalist.shtml',
        pageSize: 10, 
        checkbox: false
    };
    return options;
}

var productBrandSelect;
$(function(){

	var isEidt= ${!empty mchtProductBrand.id};
	 productBrandSelect = $("#productBrandName").ligerComboBox({
	     	 width: 150,
	         slide: false,
	         selectBoxWidth: 450,
	         selectBoxHeight: 300,
	         valueField: 'id',
	         textField: 'name',
	         valueFieldID:'productBrandId',
	         readonly:isEidt,
	         grid: getGridOptions(),
	         condition:{ fields: [{ name: 'name', label: '品牌名',width:90,type:'text'}
	                             ]}
	     });
	 
	 productBrandSelect.setValue('${productBrand.id}');
	 productBrandSelect.setText("${productBrand.name}");
	
		
});

function mchtProductBrandPicsArchive(_this,mchtBrandAptitudeId,picType){
	var mchtProductBrandId = $("#mchtProductBrandId").val();
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtProductBrandPicsArchive.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtProductBrandId":mchtProductBrandId,"mchtBrandAptitudeId":mchtBrandAptitudeId,"picType":picType},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				$(_this).closest("td").find("input[value='1']").attr("checked","checked");
//				parent.location.reload();
//				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function picArchive(picId,archiveStatus,picType){
	var mchtProductBrandId = $("#mchtProductBrandId").val();
	$.ajax({ 
		url : "${pageContext.request.contextPath}/mcht/mchtProductBrandPicArchive.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtProductBrandId":mchtProductBrandId,"picId":picId,"archiveStatus":archiveStatus,"picType":picType},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
//				parent.location.reload();
//				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function mchtProductBrandArchive(mchtProductBrandId,archiveStatus){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtProductBrandArchive.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtProductBrandId":mchtProductBrandId,"archiveStatus":archiveStatus},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
//				parent.location.reload();
//				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<input type="hidden" id="mchtProductBrandId" name="mchtProductBrandId" value="${mchtProductBrandId}">
		<table class="gridtable">
			<tr>
				<td  colspan="1" class="title">项目</td>
				<td  colspan="3" align="left" class="l-table-edit-td title">
					<span>资质文件如是复印件需全部加盖公章。且保证文件内容清晰、章印清楚</span>
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">品牌名称 </td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<span>${productBrandName}</span>
				</td>	
			</tr>
			
			<tr>
					<td  colspan="1" class="title">商标注册证或受理通知书</td>
					<td  colspan="3" align="left" class="l-table-edit-td">
					<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="idx">
						<table class="gridtable" style="margin-top: 10px;" name="mchtBrandAptitudeTable">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										${mchtBrandAptitudeCustom.certificateNo}
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
									<c:if test="${empty hideRadio || hideRadio != 1}">
										<span><a href="javascript:;" onclick="mchtProductBrandPicsArchive(this,${mchtBrandAptitudeCustom.id},1);">全部已归档</a></span>
									</c:if>
										<ul  class="docs-pictures clearfix td-pictures" id="logoPic_viewer" >
										<c:forEach var="mchtBrandAptitudePic" items="${mchtBrandAptitudeCustom.mchtBrandAptitudePicList}" varStatus="status">
											<li>
												<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandAptitudePic.pic}">
													<div>
														<span><input type="radio" name="archiveStatus${idx.index}${status.index}" value="0" onclick="picArchive(${mchtBrandAptitudePic.id},0,1);" <c:if test="${mchtBrandAptitudePic.archiveStatus == '0'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>未齐</span>
														<span><input type="radio" name="archiveStatus${idx.index}${status.index}" value="1" onclick="picArchive(${mchtBrandAptitudePic.id},1,1);" <c:if test="${mchtBrandAptitudePic.archiveStatus == '1'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>已归</span>
													</div>
											</li>
										</c:forEach>
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>	
					</td>
				</tr>				
			
			<tr>
				<td  colspan="1" class="title">销售授权书</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<c:if test="${empty hideRadio || hideRadio != 1}">
					<span><a href="javascript:;" onclick="mchtProductBrandPicsArchive(this,0,2);">全部已归档</a></span>
				</c:if>
					<ul  class="docs-pictures clearfix td-pictures" id="platformAuthPic_viewer" >
						<c:forEach var="mchtPlatformAuthPic" items="${mchtPlatformAuthPics}" varStatus="status">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtPlatformAuthPic.pic}">
									<div>
										<span><input type="radio" name="platformAuthArchiveStatus${status.index}" value="0" onclick="picArchive(${mchtPlatformAuthPic.id},0,2);" <c:if test="${mchtPlatformAuthPic.archiveStatus == '0'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>未齐</span>
										<span><input type="radio" name="platformAuthArchiveStatus${status.index}" value="1" onclick="picArchive(${mchtPlatformAuthPic.id},1,2);" <c:if test="${mchtPlatformAuthPic.archiveStatus == '1'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>已归</span>
									</div>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">进货发票</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<c:if test="${empty hideRadio || hideRadio != 1}">
					<span><a href="javascript:;" onclick="mchtProductBrandPicsArchive(this,0,3);">全部已归档</a></span>
				</c:if>
					<ul  class="docs-pictures clearfix td-pictures" id="mchtBrandInvoicePic_viewer" >
						<c:forEach var="mchtBrandInvoicePic" items="${mchtBrandInvoicePics}" varStatus="status">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandInvoicePic.pic}">
									<div>
										<span><input type="radio" name="brandInvoiceArchiveStatus${status.index}" value="0" onclick="picArchive(${mchtBrandInvoicePic.id},0,3);" <c:if test="${mchtBrandInvoicePic.archiveStatus == '0'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>未齐</span>
										<span><input type="radio" name="brandInvoiceArchiveStatus${status.index}" value="1" onclick="picArchive(${mchtBrandInvoicePic.id},1,3);" <c:if test="${mchtBrandInvoicePic.archiveStatus == '1'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>已归</span>
									</div>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">质检报告/检疫报告</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<c:if test="${empty hideRadio || hideRadio != 1}">
					<span><a href="javascript:;" onclick="mchtProductBrandPicsArchive(this,0,4);">全部已归档</a></span>
				</c:if>
					<ul  class="docs-pictures clearfix td-pictures" id="mchtBrandInspectionPic_viewer" >
						<c:forEach var="mchtBrandInspectionPic" items="${mchtBrandInspectionPics}" varStatus="status">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandInspectionPic.pic}">
									<div>
										<span><input type="radio" name="brandInspectionArchiveStatus${status.index}" value="0" onclick="picArchive(${mchtBrandInspectionPic.id},0,4);" <c:if test="${mchtBrandInspectionPic.archiveStatus == '0'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>未齐</span>
										<span><input type="radio" name="brandInspectionArchiveStatus${status.index}" value="1" onclick="picArchive(${mchtBrandInspectionPic.id},1,4);" <c:if test="${mchtBrandInspectionPic.archiveStatus == '1'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>已归</span>
									</div>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">其他资质<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<c:if test="${empty hideRadio || hideRadio != 1}">
					<span><a href="javascript:;" onclick="mchtProductBrandPicsArchive(this,0,5);">全部已归档</a></span>
				</c:if>
					<ul  class="docs-pictures clearfix td-pictures" id="mchtBrandOtherPics_viewer" >
						<c:forEach var="mchtBrandOtherPic" items="${mchtBrandOtherPics}" varStatus="status">
							<li>
								<img  src="${pageContext.request.contextPath}/file_servelt${mchtBrandOtherPic.pic}">
									<div>
										<span><input type="radio" name="brandOtherArchiveStatus${status.index}" value="0" onclick="picArchive(${mchtBrandOtherPic.id},0,5);" <c:if test="${mchtBrandOtherPic.archiveStatus == '0'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>未齐</span>
										<span><input type="radio" name="brandOtherArchiveStatus${status.index}" value="1" onclick="picArchive(${mchtBrandOtherPic.id},1,5);" <c:if test="${mchtBrandOtherPic.archiveStatus == '1'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>已归</span>
									</div>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">资质归档总状态</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
					<span><input type="radio" name="mchtProductBrandArchiveStatus" value="2" onclick="mchtProductBrandArchive(${mchtProductBrandId},2);" <c:if test="${mchtProductBrandArchiveStatus ne 3}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>未齐全</span>
					<span><input type="radio" name="mchtProductBrandArchiveStatus" value="3" onclick="mchtProductBrandArchive(${mchtProductBrandId},3);" <c:if test="${mchtProductBrandArchiveStatus == '3'}">checked="checked"</c:if> <c:if test="${hideRadio eq '1'}">disabled="true" </c:if>>已齐全</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
