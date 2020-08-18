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
	
	$("input[name='auditStatus']").bind('click',function(){
		var auditStatus = $(this).val();
		if(auditStatus == "1"){
			$("#rejectTr").hide();
		}else{
			$("#rejectTr").show();
		}
	});
	
	$("#checkManuallyViolateOrder").bind('click',function(){
		var auditStatus = $("input[name='auditStatus']:checked").val();
		if(!auditStatus){
			commUtil.alertError("请选择审核结果");
			return false;
		}
		if(auditStatus == "1"){//通过
			$("#rejectTr").hide();
		}
		var violateOrderId = $("#violateOrderId").val();
		var rejectReason = $("#rejectReason").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/checkManuallyViolateOrder.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"violateOrderId":violateOrderId,"auditStatus":auditStatus,"rejectReason":rejectReason},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("审核成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
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
	<input type="hidden" id="violateOrderId" name="violateOrderId" value="${violateOrder.id}">
	<div class="table-title">
		<span class="marR10">
			商家信息:
		</span>
	</div>
	<table class="gridtable">
		<tr>
			<td class="title">商家</td>
			<td >${mchtInfo.companyName}</td>
			<td class="title">商家序号</td>
			<td >${mchtInfo.mchtCode}</td>
			<td class="title">对接运营</td>
			<td >${contactName}</td>
		</tr>
		<tr>
			<td class="title">店铺名称</td>
			<td >${mchtInfo.shopName}</td>
			<td class="title">30天内违规次数</td>
			<td >${thirtyDaysViolateCount}</td>
			<td class="title">总违规次数</td>
			<td >${totalViolateCount}</td>
		 </tr>
	</table>	
	<div class="table-title">
		<span class="marR10">
			违规信息：
		</span>
	</div>
	<table class="gridtable">
		<tr>
			<td class="title" style="width: 161px;">违规编号</td>
			<td >${violateOrder.orderCode}</td>
			<td class="title">子订单号</td>
			<td >${subOrderCode}</td>
			<td class="title">申诉状态</td>
			<td ><span style="color: red;">${statusDesc}</span></td>
		</tr>
		<tr>
			<td class="title">创建时间</td>
			<td ><fmt:formatDate value="${violateOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td class="title">创建人员</td>
			<td >${staffName}</td>
			<td class="title">来源/状态</td>
			<td >${orderSourceDesc}【<span style="color: red;">${auditStatusDesc}</span>】</td>
		</tr>
		<tr>
			<td class="title">违规类型</td>
			<td >${violateTypeDesc}</td>
			<td class="title">违规行为</td>
			<td >${violateActionDesc}</td>
			<td class="title">处罚金额</td>
			<td >
				<c:if test="${not empty violateOrder.money}">
				${violateOrder.money}元
				</c:if>
				<c:if test="${empty violateOrder.money}">
				0元
				</c:if>
				<span style="color: red;">
				<c:if test="${violateOrder.jifenStatus eq '0'}">，需发放积分${violateOrder.jifenIntegral}个</c:if>
				<c:if test="${violateOrder.jifenStatus eq '1'}">，已发放积分${violateOrder.jifenIntegral}个</c:if>
				<c:if test="${violateOrder.jifenStatus eq '2'}">，不用发放积分</c:if>
				</span>
			</td>
		</tr>
	</table>
	<table class="gridtable">
		<tr>
			<td class="title" style="width: 161px;">违规详情</td>
			<td >${violateOrder.content}</td>
		</tr>
		<tr>
			<td class="title">处罚方式</td>
			<td >${violateOrder.punishMeans}</td>
		</tr>
		<tr>
			<td class="title">内部备注</td>
			<td >${violateOrder.platformRemarks}</td>
		</tr>
		<tr>
			<td class="title">内部凭证图片</td>
			<td >
				<c:forEach items="${violatePlatformRemarksPics}" var="violatePlatformRemarksPic">
					<img style="width: 80px;height: 80px" src="${pageContext.request.contextPath}/file_servelt${violatePlatformRemarksPic.pic}" onclick="viewerPic(this.src)">
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td class="title">内部附件</td>
			<td ><a href="${pageContext.request.contextPath}/file_servelt${violateOrder.enclosure}">${enclosureName}</a></td>
		</tr>
		<tr>
			<td class="title">审核结果</td>
			<td >
				<span style="padding-left: 20px;"><input type="radio" name="auditStatus" value="1">通过</span>
				<span style="padding-left: 20px;"><input type="radio" name="auditStatus" value="2" checked="checked">驳回</span>
			</td>
		</tr>
		<tr id="rejectTr">
			<td class="title">驳回原因</td>
			<td >
				<textarea id="rejectReason" name="rejectReason" rows="5" class="textarea" cols="100"></textarea>
			</td>
		</tr>
	</table>
	<div class="table-title" id="" style="margin-top: 15px;">
		<div style="padding-top: 10px;"><input type="button" style="cursor: pointer;width: 120px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" id="checkManuallyViolateOrder"/></div>
	</div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
