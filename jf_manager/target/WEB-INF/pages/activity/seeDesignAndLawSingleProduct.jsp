<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript">
</script>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
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

.auditFix{position:fixed; bottom:0; left:0; width:100%; _position:absolute;}
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
.td-pictures li img{
width: 100px;
height: 100px;
}


.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});

function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();
	
}

function submit_fun(){
	var activityProductId="${activityProductId}";
	var type="${type}";
	var remarks=$("#remarks").val();
	var status = $("input[name='status']:checked").val();
	if (status==3){
		if($("#remarks").val().length==0){
			commUtil.alertError("请输入驳回内容");
			return;
		}
	}else if (status!=2){
		commUtil.alertError("请选择状态");
		return;
	}
	
	$.ajax({ //ajax提交
		type:'POST',
		url:"${pageContext.request.contextPath}/activity/activityProductAudit.shtml",
		data:{
			activityProductId:activityProductId,
			status:status,
			remarks:remarks,
			type:type
		},
		dataType:'json',
		cache: false,
		success: function(json){
		   if(json==null || json.statusCode!=200){
		     commUtil.alertError(json.message);
		  }else{
             $.ligerDialog.success("操作成功",function(yes) {
            	 	parent.$("#searchbtn").click();
 					frameElement.dialog.close();
				});
		  }
		},
		error: function(e){
		 commUtil.alertError("系统异常请稍后再试");
		}
	});
}
</script>
<html>
<body>
	<form method="post" id="form1" name="form1">
		<table class="gridtable">
			<tr>
				<td class="title" width="140px;">商品名称</td>
				<td align="left" class="l-table-edit-td">
				${productCustom.name}
				</td>
			</tr>
			<tr>
				<td class="title">商品货号</td>
				<td align="left" class="l-table-edit-td">
				${productCustom.artNo}
				</td>
			</tr>
		</table>
		<br>
		<div><span class="table-title" >SKU信息</span></div>
	
		<table class="gridtable">
		<c:forEach var="propValueStr" items="${productPropValueStr}">
			<tr>				
				<td class="title" width="140px;">${propValueStr.prop_name}</td>
				<td>${propValueStr.prop_value_str}</td>
			</tr>
		</c:forEach>
		</table>
		
		<br>
		<div><span class="table-title" >主图</span></div>
		<div style="width: 100%;">
		<c:forEach var="pic" items="${pics}">
		<img data-original="${pageContext.request.contextPath}/file_servelt${pic.pic}" src="${pageContext.request.contextPath}/file_servelt${pic.pic}" alt="" />
		</c:forEach>
		</div>

		<br>
		<div><span class="table-title" >SKU图</span></div>
		<div style="width: 100%;">
		<c:forEach var="productItem" items="${productItems}">
		<img alt="" src="${pageContext.request.contextPath}/file_servelt${productItem.pic}" onclick='viewerPic(this.src)' style="width:100px;height:100px;" />
		</c:forEach>
		</div>
				
		<br>
		<div><span class="table-title" >商品详情描述信息</span></div>
		<table class="gridtable" style="width:60%;">
		<c:forEach var="desc" items="${productDesc}">
			<tr><td class="l-table-edit-td">${desc}</td></tr>
		</c:forEach>
		</table>
		
		<br>
		<div><span class="table-title" >商品详情图</span></div>
		<div style="width: 100%;">
		<c:forEach var="descPic" items="${productDescPics }">
		<img style="max-width: 100%;" alt="" src="${pageContext.request.contextPath}/file_servelt${descPic.pic}">
		</c:forEach>
		</div>
		<c:if test="${type eq 2 and activityProductCustom.designAuditStatus eq 1 or type eq 3 and activityProductCustom.lawAuditStatus eq 1}">
		<br>
		<table class="gridtable auditFix">
		<tr>
			<td class="title" width="140px;">驳回原因</td>
			<td align="left" class="l-table-edit-td">
				<textarea rows="5" id="remarks" name="remarks" class="text" style="width:100%;height:100%;">${activityProductCustom.remarks}</textarea>
			</td>
		</tr>
		<tr>
			<td class="title">审核状态</td>
			<td align="left" class="l-table-edit-td">
				<span class="radioClass"><input class="radioItem" type="radio" value="2" name="status">通过</span>
				<span class="radioClass"><input class="radioItem" type="radio" value="3" name="status">驳回</span>
				<input name="btnSubmit" type="button" id="Button1" style="float:right" class="l-button l-button-submit" value="提交" onclick="submit_fun();"/>
			</td>
		</tr>
		</table>
		</c:if>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
	</form>
</body>
</html>
