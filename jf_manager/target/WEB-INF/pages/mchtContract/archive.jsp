<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.radioClass{margin-right: 20px;}
.hidden{display:none;}
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
var editor1;
var viewerPictures;
$(function() {
	KindEditor.ready(function(K) {
		 editor1 = K.create('textarea[name="model.remarks"]', {
			width:'85%',
			height : '650px',
			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
			}
			
		});
		prettyPrint();
	});
	
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("input[name='model.archiveStatus']").bind('click',function(){
		var archiveStatus = $(this).val();
		if(archiveStatus == "1"){
			$("#picsTr").show();
			$("tr[name='hideTr']").show();
		}else{
			$("tr[name='hideTr']").hide();
			$("#picsTr").hide();
		}
	});
	
	$(".ligerDate").ligerDateEditor( {
		showTime : false,
		labelWidth : 160,
		width:160,
		labelAlign : 'left'
	});

	$("input[name='model.isPlatformSend']").change(function() {
		var isPlatformSend = $("input[name='model.isPlatformSend']:checked").val();
		if (isPlatformSend == 1){
			$(".expressInfo").removeClass("hidden");
		}else{
			$(".expressInfo").addClass("hidden");
		}
	});


	$.metadata.setType("attr", "validate");
	var v = $("#form1").validate({
		errorPlacement: function (lable, element){
			//console.log(lable[0].innerText);
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
		success: function (lable,element){
			//console.log("x1:" + lable[0]);
			//console.log("x2:" + lable[0].innerText);
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form){
			submitForm();
		}
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
function ajaxFileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "mchtContractPic",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				uploadImageList.addImage("${pageContext.request.contextPath}/file_servelt", result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}

function submitForm(){
	editor1.sync();
	var pictures = [];
	var lis = $(".upload_image_list").find("li");
	lis.each(function(index, item) {
		var path = $("img", item).attr("path");
		var def = ($(".def", item).length == 1 ? "1" : "0");
		var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
		pictures.push(pic);
	});
	if(pictures.length > 0){
		$("#mchtContractPics").val(JSON.stringify(pictures));
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/mchtContract/archiveCommit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(result) {
			if (result.success) {
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(result.message);
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
	<input type="hidden" name="model.id" value="${model.id}">
		<div style="color:#f00;">这是与商家签约的第 ${archivePassCount + 1} 份入驻合同</div>
		<table class="gridtable">
			<tr>
				<td  class="title">最新合同编号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="contractCode" name="model.contractCode" value="${model.contractCode}" validate="{required:true}" />
				</td>
			</tr>
			<tr>
				<td class="title" style="text-align: center;">状态<span class="required">*</span></td>
				<td colspan="3">
					<span class="radioClass"><input type="radio" value="1" name="model.archiveStatus" <c:if test="${model.archiveStatus==0 || model.archiveStatus==1}">checked="checked"</c:if> >通过(归档)</span>
					<span class="radioClass"><input type="radio" value="2" name="model.archiveStatus" <c:if test="${model.archiveStatus==2}">checked="checked"</c:if> >不通过(驳回)</span>
					<span class="radioClass"><input type="radio" value="4" name="model.archiveStatus" <c:if test="${model.archiveStatus==4}">checked="checked"</c:if> >不签约</span>
				</td>
			</tr>
			<tr name="hideTr" <c:if test="${model.archiveStatus!='1'}">style="display:none;"</c:if>>
				<td  class="title">最新合同开始<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" class="ligerDate" name="model.beginDate" value="<fmt:formatDate value='${model.beginDate}' pattern='yyyy-MM-dd'/>" />
				</td>
			</tr>
			<tr name="hideTr" <c:if test="${model.archiveStatus!='1'}">style="display:none;"</c:if>>
				<td  class="title">最新合同到期<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" class="ligerDate" name="model.endDate" value="<fmt:formatDate value='${model.endDate}' pattern='yyyy-MM-dd'/>"  />
				</td>
			</tr>
	        <tr>
	           	<td class="title" style="text-align: center;">备注/驳回原因</td>
	           	<td colspan="3">
	           		<textarea name="model.remarks" id="remarks" style="width:150px;height:300px;visibility:hidden;"><%=htmlspecialchars(htmlData)%>${remarks}</textarea>
				</td>
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">内部备注</td>
	           	<td colspan="3">
	           		<textarea name="model.fwInnerRemarks" id="fwInnerRemarks" style="width:425px;height:100px;">${model.fwInnerRemarks}</textarea>
				</td>
	        </tr>
			<tr name="hideTr" <c:if test="${model.archiveStatus!='1'}">style="display:none;"</c:if>>
				<td  class="title">归档编号</td>
				<td align="left" class="l-table-edit-td">
					<input type="text" name="model.archiveNo" value="${model.archiveNo}" />
				</td>
			</tr>
			<tr id="picsTr" name="hideTr" <c:if test="${model.archiveStatus!='1'}">style="display:none;"</c:if>>
               <td class="title">上传完整合同</td>
               <td colspan="2" align="left" class="l-table-edit-td">
                <t:imageList name="pictures" list="${mchtContractPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
				<div style="float: left;margin-top:100px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="mchtContractPic" name="file" onchange="ajaxFileUpload();" />
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    		</div>
	    		<input id="mchtContractPics" name="mchtContractPics" type="hidden">
               </td>
			</tr>
			<tr name="hideTr" <c:if test="${model.archiveStatus!='1'}">style="display:none;"</c:if>>
				<td  class="title">同时寄回</td>
				<td align="left" class="l-table-edit-td">
					<input type="checkbox" name="model.isPlatformSend" class="liger-checkbox" value="1" <c:if test="${model.isPlatformSend==1}">checked="checked"</c:if> />同时寄回1份给商家
				</td>
			</tr>
			<tr name="hideTr" <c:if test="${model.archiveStatus!='1'}">style="display:none;"</c:if> class="expressInfo <c:if test="${model.isPlatformSend!=1}">hidden</c:if>">
				<td  class="title">寄件快递<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<select name="model.platformExpressId" style="width: 150px;">
						<option value="">请选择</option>
						<c:forEach var="express" items="${expressList}">
							<option value="${express.id}" <c:if test="${model.platformExpressId == express.id}">selected="selected"</c:if>>${express.name}</option>
						</c:forEach>
					</select>
					<input type="text" name="model.platformExpressNo" value="${model.platformExpressNo}">
				</td>
			</tr>
			<c:if test="${empty isView}">
	        <tr>
				<td class="title" style="text-align: center;">操作</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
			</c:if>
		</table>
	</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>  
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
