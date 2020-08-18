<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/dateUtil.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	

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
	function ajaxFileUpload(_this) {
		var id = $(_this).attr("id");
		var $ul = $(_this).parent().prev();
		var listId = $ul.attr("id");
		var data_value = $(_this).attr("data_value");
		$.ajaxFileUpload({
			url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
			secureuri: false,
			fileElementId: id,
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					uploadImageList.addImage("${pageContext.request.contextPath}/file_servelt", result.FILE_PATH,listId);
					
					<c:forEach var="mchtBrandAptitudeChgCustom" items="${mchtBrandAptitudeChgCustoms}" varStatus="status">
						if(data_value == 'aptitudePic_viewer${status.index}'){
							aptitudePic_viewer${status.index}.destroy();
							aptitudePic_viewer${status.index} = new Viewer(document.getElementById('aptitudePic_viewer${status.index}'), {});
						}	
		 	 		</c:forEach>
		 	 	
					 if(data_value == 'platformAuthPic_viewer'){
						platformAuthPic_viewer.destroy();
						platformAuthPic_viewer = new Viewer(document.getElementById('platformAuthPic_viewer'), {});
					}else if(data_value == 'mchtBrandInvoicePic_viewer'){
						mchtBrandInvoicePic_viewer.destroy();
						mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
					}else if(data_value == 'mchtBrandInspectionPic_viewer'){
						mchtBrandInspectionPic_viewer.destroy();
						mchtBrandInspectionPic_viewer = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer'), {});
					}else if(data_value == 'mchtBrandOtherPics_viewer'){
						mchtBrandOtherPics_viewer.destroy();
						mchtBrandOtherPics_viewer = new Viewer(document.getElementById('mchtBrandOtherPics_viewer'), {});
					}
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
	}
	
	function addBrand(id) {
		$.ligerDialog
				.open({
					height : $(window).height() - 40,
					width : $(window).width() - 40,
					title : "品牌修改",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/service/prod/product_brand/add2BrandFromMchtBrand.shtml?id="
							+ id,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}
	
	var viewerPictures;	
	var productBrandSelect;
	var logo_viewer;
	var logo_viewer1;
	var platformAuthPic_viewer;
	var platformAuthPic_viewer1;
	var mchtBrandInvoicePic_viewer;
	var mchtBrandInvoicePic_viewer1;
	var mchtBrandInspectionPic_viewer;
	var mchtBrandInspectionPic_viewer1;
	var mchtBrandOtherPics_viewer;
	var mchtBrandOtherPics_viewer1;
	
	 <c:forEach var="mchtBrandAptitudeChgCustom" items="${mchtBrandAptitudeChgCustoms}" varStatus="status">
	 	var aptitudePic_viewer${status.index};
	 </c:forEach>
	 
	<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="index">	
		var aptitudePic_viewer1${index.index};
	</c:forEach>
    $(function ()  {
    	if($("#logo_viewer").length>0){
	    	logo_viewer = new Viewer(document.getElementById('logo_viewer'), {});
    	}
    	if($("#logo_viewer1").length>0){
	    	logo_viewer1 = new Viewer(document.getElementById('logo_viewer1'), {});
    	}
    	
    	<c:forEach var="mchtBrandAptitudeChgCustom" items="${mchtBrandAptitudeChgCustoms}" varStatus="status">
 	 		aptitudePic_viewer${status.index} = new Viewer(document.getElementById('aptitudePic_viewer${status.index}'), {});
 	 	</c:forEach>
 	 
 		<c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="index">	
 			aptitudePic_viewer1${index.index} = new Viewer(document.getElementById('aptitudePic_viewer1${index.index}'), {});
 		</c:forEach>
    	
    	if($("#platformAuthPic_viewer").length>0){
	    	platformAuthPic_viewer = new Viewer(document.getElementById('platformAuthPic_viewer'), {});
    	}
    	if($("#platformAuthPic_viewer1").length>0){
	    	platformAuthPic_viewer1 = new Viewer(document.getElementById('platformAuthPic_viewer1'), {});
    	}
    	if($("#mchtBrandInvoicePic_viewer").length>0){
	    	mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});
    	}
    	if($("#mchtBrandInvoicePic_viewer1").length>0){
	    	mchtBrandInvoicePic_viewer1 = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer1'), {});
    	}
    	if($("#mchtBrandInspectionPic_viewer").length>0){
	    	mchtBrandInspectionPic_viewer = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer'), {});
    	}
    	if($("#mchtBrandInspectionPic_viewer1").length>0){
	    	mchtBrandInspectionPic_viewer1 = new Viewer(document.getElementById('mchtBrandInspectionPic_viewer1'), {});
    	}
    	if($("#mchtBrandOtherPics_viewer").length>0){
	    	mchtBrandOtherPics_viewer = new Viewer(document.getElementById('mchtBrandOtherPics_viewer'), {});
    	}
    	if($("#mchtBrandOtherPics_viewer1").length>0){
	    	mchtBrandOtherPics_viewer1 = new Viewer(document.getElementById('mchtBrandOtherPics_viewer1'), {});
    	}
    	
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
   	 productBrandSelect = $("#brandName").ligerComboBox({
   	     	 width: 150,
   	         slide: false,
   	         selectBoxWidth: 450,
   	         selectBoxHeight: 300,
   	         valueField: 'id',
   	         textField: 'name',
   	         valueFieldID:'productBrandId',
   	         grid: getGridOptions(),
   	         condition:{ fields: [{ name: 'name', label: '品牌名',width:90,type:'text'}
   	                             ]}
   	     });
   	 
   	 productBrandSelect.setValue("${mchtBrandChg.productBrandId}");
   	 productBrandSelect.setText("${mchtBrandChg.brandName}");
    	
    	$.metadata.setType("attr", "validate");
        //驳回原因必填
        $.validator.addMethod("auditRemarksRequired", function(value, element) {
        	var auditStatus = $("input[name='auditStatus']:checked").val();
        	if(auditStatus == 4){
        		var auditRemarks = $.trim($("#auditRemarks").val());
        		if(!auditRemarks){
        			return false;
        		}else{
        			return true;
        		}
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
    	                	auditSubmit();
    	                }
    	            });
    	            
    	            
 	        	var aptitudeExpDate=new Date("${mchtBrandChg.aptitudeExpDate }");
 	        	var platformAuthExpDate=new Date("${mchtBrandChg.platformAuthExpDate }");
 	        	$("#aptitudeExpDate").ligerDateEditor( {
 	        		showTime : false,
 	        		width:158,
 	        		initValue: aptitudeExpDate.format("yyyy-MM-dd")
 	        	});
 	        	$("#platformAuthExpDate").ligerDateEditor( {
 	        		showTime : false,
 	        		width:158,
 	        		initValue: platformAuthExpDate.format("yyyy-MM-dd")
 	        	});  
 	        	var inspectionExpDate=new Date("${mchtBrandChg.inspectionExpDate }");
 	        	var otherExpDate=new Date("${mchtBrandChg.otherExpDate }");
 	        	$("#inspectionExpDate").ligerDateEditor( {
 	        		showTime : false,
 	        		width:158,
 	        		initValue: inspectionExpDate.format("yyyy-MM-dd")
 	        	});
 	        	$("#otherExpDate").ligerDateEditor( {
 	        		showTime : false,
 	        		width:158,
 	        		initValue: otherExpDate.format("yyyy-MM-dd")
 	        	});  
 	        	
    	            
    	  });
	
    function auditSubmit(){
    	var auditStatus = $("input[name='auditStatus']:checked").val();
    	if(auditStatus == null ||auditStatus == ""){
    		commUtil.alertError("法务审核必选");
    		return;
    	}
    	if(auditStatus == 4){
    		var auditRemarks = $("#auditRemarks").val();
    		if(!auditRemarks){
    			commUtil.alertError("驳回原因必填");
	    		return;
    		}
    	}
    	var mchtPlatformAuthPictures = [];
    	var lis = $("#mchtPlatformAuthPicChgs").find("li");
    	lis.each(function(index, item) {
    		var path = $("img", item).attr("path");
    		var def = ($(".def", item).length == 1 ? "1" : "0");
    		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    		mchtPlatformAuthPictures.push(pic);
    	});
    	
    	var mchtBrandInvoicePictures = [];
    	var lis = $("#mchtBrandInvoicePicChgs").find("li");
    	lis.each(function(index, item) {
    		var path = $("img", item).attr("path");
    		var def = ($(".def", item).length == 1 ? "1" : "0");
    		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    		mchtBrandInvoicePictures.push(pic);
    	});
    	
    	var mchtBrandInspectionPictures = [];
    	var lis = $("#mchtBrandInspectionPicChgs").find("li");
    	lis.each(function(index, item) {
    		var path = $("img", item).attr("path");
    		var def = ($(".def", item).length == 1 ? "1" : "0");
    		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    		mchtBrandInspectionPictures.push(pic);
    	});
    	
    	var mchtBrandOtherPictures = [];
    	var lis = $("#mchtBrandOtherPicChgs").find("li");
    	lis.each(function(index, item) {
    		var path = $("img", item).attr("path");
    		var def = ($(".def", item).length == 1 ? "1" : "0");
    		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    		mchtBrandOtherPictures.push(pic);
    	});
    	
    	//TODO 商标注册证或受理通知书
    	var mchtBrandAptitudeChgArray = [];
    	$("table[name='mchtBrandAptitudeTable']").each(function(idx){
    		var mchtBrandAptitudeChgId = $(this).attr("mchtBrandAptitudeChgId");
    		var certificateNo = $(this).attr("certificateNo");
    		var mchtBrandAptitudePictures = [];
    		$(this).find("ul").find("li").each(function(index, item) {
    			var path = $("img", item).attr("path");
    			var def = ($(".def", item).length == 1 ? "1" : "0");
    			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    			mchtBrandAptitudePictures.push(pic);
    		});
    		var aptitudeExpDate = $(this).find("td[name='aptitudeExpDate']").text();
    		mchtBrandAptitudeChgArray.push({
    			"mchtBrandAptitudeChgId":mchtBrandAptitudeChgId,
    			"certificateNo":certificateNo,
    			"aptitudeExpDate":aptitudeExpDate,
    			"mchtBrandAptitudePicChgs":JSON.stringify(mchtBrandAptitudePictures)
    		});
    	});
    	
    	var dataJson = $("#form1").serializeArray();
    	dataJson.push({"name":"mchtPlatformAuthPicChgs","value":JSON.stringify(mchtPlatformAuthPictures)});
    	dataJson.push({"name":"mchtBrandInvoicePicChgs","value":JSON.stringify(mchtBrandInvoicePictures)});
    	dataJson.push({"name":"mchtBrandInspectionPicChgs","value":JSON.stringify(mchtBrandInspectionPictures)});
    	dataJson.push({"name":"mchtBrandOtherPicChgs","value":JSON.stringify(mchtBrandOtherPictures)});
    	dataJson.push({"name":"mchtBrandAptitudeChgJsonStr","value":JSON.stringify(mchtBrandAptitudeChgArray)});
    	
    	$.ajax({
    		url : "${pageContext.request.contextPath}/mcht/editbrandsave.shtml",
    		type : 'POST',
    		dataType : 'json',
    		cache : false,
    		async : false,
    		data : dataJson,
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
    }

  	</script>
	
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

.td-pictures li{
display: inline-block;
}
td img{
width: 60px;
height: 40px;
}
.infoChange{
	color: red;
}
</style>
  </head>
  
  <body>
  	<form class="form1" method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mcht/editbrandsave.shtml"  >
		<input type="hidden" value="${mchtBrandChg.id}" name="id" >
 		<table class="gridtable">
			<tr>
				<td class="title" >内容</td>
				<td class="title">新内容</td>
				<td class="title">旧内容</td>
			</tr>
            <tr>
            
            	<td  colspan="1" class="title">申请品牌名称</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span <c:if test="${mchtBrandChg.productBrandName!=mchtProductBrand.productBrandName}"> class="infoChange"</c:if>>
						<input id="productBrandName" name="productBrandName" value="${mchtBrandChg.productBrandName}">
						<a class="table-title-link" href="javascript:addBrand(${mchtProductBrand.id})" >添加到品牌库</a>
					</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtProductBrand.productBrandName}</span>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">资质类型</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtBrandChg.aptitudeTypeDesc!=mchtProductBrand.aptitudeTypeDesc}">
				 class="infoChange"
				</c:if>
				 >
				<c:forEach var="aptitudeTypeItem" varStatus="varStatus" items="${aptitudeTypeStatus }">
					<span class="radioClass"><input type="radio" 
					<c:if test="${aptitudeTypeItem.statusValue==mchtBrandChg.aptitudeType }">
					checked="checked" 
					</c:if>
					validate="{required:true}" value="${aptitudeTypeItem.statusValue }" name="aptitudeType" >${aptitudeTypeItem.statusDesc}</span>
				</c:forEach>	
				</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>
				${mchtProductBrand.aptitudeTypeDesc}
				</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">品牌库品牌 </td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<input id="productBrandId"  type="hidden" name="productBrandId" value="${mchtBrandChg.productBrandId}"/>
					<input id="brandName"  type="text" name="brandName"  value="${mchtBrandChg.brandName}" validate="{brandRequired:true}"/>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					${mchtProductBrand.brandName}
				</td>	
			</tr>
			
			<tr>
				<td  colspan="1" class="title">LOGO图片</td>
				
				<td  colspan="1" id="logo_viewer">
				<img src="${pageContext.request.contextPath}/file_servelt${mchtBrandChg.logo}" alt=""  ></li>
				</td>
				<td  colspan="1" id="logo_viewer1">
					<img src="${pageContext.request.contextPath}/file_servelt${mchtProductBrand.logo}" alt="" ></li>
				 </td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">商标注册证或受理通知书</td>
				
				<td  colspan="1">
				 <c:forEach var="mchtBrandAptitudeChgCustom" items="${mchtBrandAptitudeChgCustoms}" varStatus="status">
						<table class="gridtable" style="margin-top: 10px;" mchtBrandAptitudeChgId="${mchtBrandAptitudeChgCustom.id}" certificateNo="${mchtBrandAptitudeChgCustom.certificateNo}" name="mchtBrandAptitudeTable">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										${mchtBrandAptitudeChgCustom.certificateNo}
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件</td>
									<td  colspan="3" align="left" class="l-table-edit-td" id="aptitudePic_viewer${status.index}">
										<t:imageList name="mchtBrandAptitudePicChgList${status.index}" list="${mchtBrandAptitudeChgCustom.mchtBrandAptitudePicChgList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
										<div style="float: left;height: 105px;margin: 10px;">
							    			<input style="position:absolute; opacity:0;" type="file" id="mchtBrandAptitudePic${status.index}"  data_value="aptitudePic_viewer${status.index}" name="file" onchange="ajaxFileUpload(this);" />
											<a href="javascript:void(0);" style="width:30%;">上传图片</a>
							    		</div>
	    								<input id="mchtBrandAptitudePicChgs" name="mchtBrandAptitudePicChgs" type="hidden" value="${mchtBrandAptitudeChgCustom.mchtBrandAptitudePicChgs}">
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td" name="aptitudeExpDate"><fmt:formatDate value="${mchtBrandAptitudeChgCustom.aptitudeExpDate}" pattern="yyyy-MM-dd"/></td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
          		</td>
				
				<td  colspan="1">
				<c:if test="${not empty mchtBrandAptitudeCustoms}">
				 <c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}" varStatus="idx">
						<table class="gridtable" style="margin-top: 10px;">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										${mchtBrandAptitudeCustom.certificateNo}
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件</td>
									<td  colspan="3" align="left" class="l-table-edit-td" id="aptitudePic_viewer1${idx.index}" >
										<t:imageList name="mchtBrandAptitudePics${idx.index}" list="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td"><fmt:formatDate value="${mchtBrandAptitudeCustom.aptitudeExpDate}" pattern="yyyy-MM-dd"/></td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</c:if>
				<c:if test="${empty mchtBrandAptitudeCustoms}">
					<table class="gridtable" style="margin-top: 10px;">
							<tbody>
								<tr>
									<td  colspan="1" class="title" style="width:150px;">商标注册证号</td>
									<td  colspan="3" align="left" class="l-table-edit-td">
										
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">本商标注册证相关文件</td>
									<td  colspan="3" align="left" class="l-table-edit-td" id="aptitudePic_viewer1">
										<t:imageList name="mchtBrandAptitudePics" list="${mchtBrandAptitudePics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
									</td>
								</tr>
								<tr>
									<td  colspan="1" class="title">商家注册证有效期</td>
									<td  colspan="3" align="left" class="l-table-edit-td"></td>
								</tr>
							</tbody>
					</table>
				</c:if>	
          		</td>
			</tr>
			<tr>
				<td colspan="1" class="title">销售授权书</td>
				<td colspan="1" id="platformAuthPic_viewer">
					 <t:imageList name="mchtPlatformAuthPicChgs" list="${mchtPlatformAuthPicChgs}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					 <div style="float: left;height: 105px;margin: 10px;">
				    	<input style="position:absolute; opacity:0;" type="file" id="mchtPlatformAuthPic"  data_value="platformAuthPic_viewer" name="file" onchange="ajaxFileUpload(this);" />
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
				     </div>
		    		 <input id="mchtPlatformAuthPics" name="mchtPlatformAuthPics" type="hidden" value="${mchtPlatformAuthPics}">
				</td>
				<td colspan="1" id="platformAuthPic_viewer1" id="platformAuthPic_viewer1">
				 <c:forEach var="mchtPlatformAuthPic" items="${mchtPlatformAuthPics}"> 
				 	<img src="${pageContext.request.contextPath}/file_servelt${mchtPlatformAuthPic.pic}" alt="" >
				 </c:forEach>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">授权有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtBrandChg.platformAuthExpDate!=mchtProductBrand.platformAuthExpDate}">
				 class="infoChange"
				</c:if>
				 ><input type="text" id="platformAuthExpDate" name="platformAuthExpDate"  value="" style="float:left;" validate="{required:true}"/></span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span><fmt:formatDate value="${mchtProductBrand.platformAuthExpDate}" pattern="yyyy-MM-dd"/></span>	
				</td>
			
			</tr>
			
			
			<tr>
				<td colspan="1" class="title">进货发票</td>
				<td colspan="1" id="mchtBrandInvoicePic_viewer">
				 <t:imageList name="mchtBrandInvoicePicChgs" list="${mchtBrandInvoicePicChgs}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
			    		<input style="position:absolute; opacity:0;" type="file" id="mchtBrandInvoicePic" data_value="mchtBrandInvoicePic_viewer" name="file" onchange="ajaxFileUpload(this);" />
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
			    	</div>
	    			<input id="mchtBrandInvoicePics" name="mchtBrandInvoicePics" type="hidden" value="${mchtBrandInvoicePicChgs}">
				 
				</td>
				<td colspan="1" id="mchtBrandInvoicePic_viewer1">
				 <c:forEach var="mchtBrandInvoicePic" items="${mchtBrandInvoicePics}"> 
				 <img src="${pageContext.request.contextPath}/file_servelt${mchtBrandInvoicePic.pic}" alt="" >
				 </c:forEach>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">质检报告/检疫报告</td>
				<td colspan="1" id="mchtBrandInspectionPic_viewer">
				 <t:imageList name="mchtBrandInspectionPicChgs" list="${mchtBrandInspectionPicChgs}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
			    			<input style="position:absolute; opacity:0;" type="file" id="mchtBrandInspectionPic" data_value="mchtBrandInspectionPic_viewer" name="file" onchange="ajaxFileUpload(this);" />
							<a href="javascript:void(0);" style="width:30%;">上传图片</a>
			    	</div>
	    			<input id="mchtBrandInspectionPics" name="mchtBrandInspectionPics" type="hidden" value="${mchtBrandInspectionPicChgs}">
				</td>
				<td colspan="1" id="mchtBrandInspectionPic_viewer1">
				 <c:forEach var="mchtBrandInspectionPic" items="${mchtBrandInspectionPics}"> 
				 <img src="${pageContext.request.contextPath}/file_servelt${mchtBrandInspectionPic.pic}" alt="" >
				 </c:forEach>
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">报告有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtBrandChg.inspectionExpDate!=mchtProductBrand.inspectionExpDate}">
				 class="infoChange"
				</c:if>
				 ><input type="text" id="inspectionExpDate" name="inspectionExpDate"  value="" style="float:left;" validate="{required:true}"/></span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span><fmt:formatDate value="${mchtProductBrand.inspectionExpDate}" pattern="yyyy-MM-dd"/></span>	
				</td>
			
			</tr>
			<tr>
				<td colspan="1" class="title">其他资质</td>
				<td colspan="1" id="mchtBrandOtherPics_viewer">
				 <t:imageList name="mchtBrandOtherPicChgs" list="${mchtBrandOtherPicChgs}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
					<div style="float: left;height: 105px;margin: 10px;">
			    		<input style="position:absolute; opacity:0;" type="file" id="mchtBrandOtherPic" data_value="mchtBrandOtherPics_viewer" name="file" onchange="ajaxFileUpload(this);" />
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
			    	</div>
	    			<input id="mchtBrandOtherPics" name="mchtBrandOtherPics" type="hidden" value="${mchtBrandOtherPicChgs}">
				</td>
				<td colspan="1" id="mchtBrandOtherPics_viewer1">
				 <c:forEach var="mchtBrandOtherPic" items="${mchtBrandOtherPics}"> 
				 <img src="${pageContext.request.contextPath}/file_servelt${mchtBrandOtherPic.pic}" alt="" >
				 </c:forEach>
				</td>
			</tr>
			
			
			<tr>
				<td  colspan="1" class="title">报告有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span
				<c:if test="${mchtBrandChg.otherExpDate!=mchtProductBrand.otherExpDate}">
				 class="infoChange"
				</c:if>
				 ><input type="text" id="otherExpDate" name="otherExpDate"  value="" style="float:left;" validate="{required:true}"/></span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span><fmt:formatDate value="${mchtProductBrand.otherExpDate}" pattern="yyyy-MM-dd"/></span>	
				</td>
			
			</tr>
			
			<tr>
				<td  colspan="1" class="title">品牌经营的类目</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<table class="gridtable">
						<tbody>
							<tr>
								<td class="title">一级类目</td>
								<td class="title">二级类目</td>
								<td class="title">三级类目</td>
							</tr>
							<c:forEach var="mchtBrandChgProductTypeCustom" items="${mchtBrandChgProductTypeCustoms}">
								<tr>
									<td>${mchtBrandChgProductTypeCustom.firstProductTypeName}</td>
									<td>${mchtBrandChgProductTypeCustom.secondProductTypeName}</td>
									<td>${mchtBrandChgProductTypeCustom.thirdProductTypeNames}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<table class="gridtable">
						<tbody>
							<tr>
								<td class="title">一级类目</td>
								<td class="title">二级类目</td>
								<td class="title">三级类目</td>
							</tr>
							<c:forEach var="mchtBrandProductTypeCustom" items="${mchtBrandProductTypeCustoms}">
								<tr>
									<td>${mchtBrandProductTypeCustom.firstProductTypeName}</td>
									<td>${mchtBrandProductTypeCustom.secondProductTypeName}</td>
									<td>${mchtBrandProductTypeCustom.thirdProductTypeNames}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr>
				<c:if test='${mchtInfo.mchtType=="2"}'>
					<td  colspan="1" class="title">技术服务费<span class="required">*</span></td>
				</c:if>
				<c:if test='${mchtInfo.mchtType=="1"}'>
					<td  colspan="1" class="title">定价方式<span class="required">*</span></td>
				</c:if>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<c:if test='${mchtInfo.mchtType=="2"}'>
						<input id="popCommissionRate" validate="{required:true,number:true}"
							name="popCommissionRate" type="text" value="${mchtBrandChg.popCommissionRate }"
							style="float:left;" <c:if test="${sessionScope.USER_SESSION.staffID!=1}">readonly="readonly"</c:if> />
					</c:if>
					<c:if test='${mchtInfo.mchtType=="1"}'>
						<select style="width: 160px;" id="priceModel" name="priceModel" validate="{required:true}">
							<option value="">请选择</option>
							<c:forEach var="statusItem" items="${priceModelStatusDesc}">
								<option  <c:if test="${mchtBrandChg.priceModel==statusItem.statusValue}">selected</c:if>
								value="${statusItem.statusValue}">${statusItem.statusDesc}
								</option>
							</c:forEach>
						</select>
						<br>
						<br>
						<textarea rows="3" style="width:90%;" name="priceModelDesc" validate="{required:true}">${mchtBrandChg.priceModelDesc}</textarea>
					</c:if>
				</td>	
				<td  colspan="1" align="left" class="l-table-edit-td">
					<c:if test='${mchtInfo.mchtType=="2"}'>
						<input id="popCommissionRate" validate="{required:true,number:true}"
							name="popCommissionRate" type="text" value="${mchtProductBrand.popCommissionRate }"
							style="float:left;" <c:if test="${sessionScope.USER_SESSION.staffID!=1}">readonly="readonly"</c:if> />
					</c:if>
					<c:if test='${mchtInfo.mchtType=="1"}'>
						<select style="width: 160px;" id="priceModel" name="priceModel" validate="{required:true}">
							<option value="">请选择</option>
							<c:forEach var="statusItem" items="${priceModelStatusDesc}">
								<option  <c:if test="${mchtProductBrand.priceModel==statusItem.statusValue}">selected</c:if>
								value="${statusItem.statusValue}">${statusItem.statusDesc}
								</option>
							</c:forEach>
						</select>
						<br>
						<br>
						<textarea rows="3" style="width:90%;" name="priceModelDesc" validate="{required:true}">${mchtProductBrand.priceModelDesc}</textarea>
					</c:if>
				</td>	
			 </tr>
			
			<tr>
				<td  colspan="1" class="title">运营状态</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtProductBrand.statusDesc}</span>	
				</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtProductBrand.statusDesc}</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">法务审核<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
						<input type="radio" name="auditStatus" value="3" <c:if test="${mchtBrandChg.auditStatus == 3}">checked="checked"</c:if>>通过
						<input type="radio" name="auditStatus" value="4" <c:if test="${mchtBrandChg.auditStatus == 4}">checked="checked"</c:if>>驳回
						<input type="radio" name="auditStatus" value="5" <c:if test="${mchtBrandChg.auditStatus == 5}">checked="checked"</c:if>>不签约
						<input type="radio" name="auditStatus" value="6" <c:if test="${mchtBrandChg.auditStatus == 6}">checked="checked"</c:if>>黑名单
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">驳回原因<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
					<textarea rows=5 id="auditRemarks" name="auditRemarks" cols="50" class="text" validate="{auditRemarksRequired:true}">${mchtBrandChg.auditRemarks}</textarea>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">内部备注<span class="required">*</span></td>
				<td  colspan="7" align="left" class="l-table-edit-td">
					<textarea rows=5 id="auditInnerRemarks" name="auditInnerRemarks" cols="50" class="text">${mchtBrandChg.auditInnerRemarks}</textarea>
				</td>
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" onclick="auditSubmit();" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
  </body>
</html>
