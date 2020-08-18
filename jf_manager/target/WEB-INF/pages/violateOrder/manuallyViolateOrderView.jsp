<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
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
	viewerPictures.show();
}

function viewSubDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
</script>
<html>
<body>
	<input type="hidden" id="violateOrderId" value="${violateOrder.id}">
	<table class="gridtable">
		<tr>
			<td class="title">商家序号：</td>
			<td align="left" class="l-table-edit-td"><input type="text" id="" name="" readonly="readonly" value="${mchtInfo.mchtCode}" style="width: 300px;height: 18px;" /><span style="color: red;">${mchtInfo.companyName}</span></td>
		</tr>
		<tr>
			<td class="title">违规行为：</td>
			<td align="left" class="l-table-edit-td">
				<select style="width: 120px;" id="" name="">
					<option value="" selected="selected">${violateTypeDesc}</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="title">违规详情：</td>
			<td align="left" class="l-table-edit-td">
				<textarea id="" rows="5" readonly="readonly" class="textarea" cols="100">${violateOrder.content}</textarea>
			</td>
		</tr>
	</table>	
	<div class="table-title">
		<span class="marR10">
			处罚方式
		</span>
	</div>
	<table class="gridtable">
		<tr>
			<td class="title">支付违约金：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="" name="" readonly="readonly" value="${violateOrder.money}" style="width: 300px;height: 18px;" />元
			</td>
		</tr>
		<tr>
			<td class="title">其他处罚方式：</td>
			<td align="left" class="l-table-edit-td">
				<textarea id="" rows="5" readonly="readonly" class="textarea" cols="100">${violateOrder.punishMeans}</textarea>
			</td>
		</tr>
	</table>
	<table class="gridtable">
		<tr>
			<td class="title">备注：</td>
			<td align="left" class="l-table-edit-td">
				<textarea id="" rows="5" readonly="readonly" class="textarea" cols="100">${violateOrder.platformRemarks}</textarea>
				<c:forEach items="${violatePlatformRemarksPics}" var="violatePlatformRemarksPic">
					<img style="width: 80px;height: 80px" src="${pageContext.request.contextPath}/file_servelt${violatePlatformRemarksPic.pic}" onclick="viewerPic(this.src);">
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="title">提交人：</td>
			<td align="left" class="l-table-edit-td">
				<input type="text" id="" name="" readonly="readonly" value="${staffName}" style="width: 300px;height: 18px;" />
			</td>
		</tr>
	</table>
		<div class="table-title" id="" style="margin-top: 15px;">
			<div style="padding-top: 10px;"><span>状态：<span style="padding-left: 20px;<c:if test="${violateOrder.auditStatus eq '0'}">color:#ec971f;</c:if><c:if test="${violateOrder.auditStatus eq '2'}">color:#d43f3a;</c:if>">${auditStatusDesc}</span></span></div>
		</div>	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
