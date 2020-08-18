<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
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
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.title{
	width: 300px;
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

.table-title-link{
color: #1B17EE;
font-size: 15px;
text-decoration: none;
}

.table-title{
font-size: 17px;font-weight: 600;
}
.examine{
	float: left;
}
.examine table{
	width: 318px;
	font-size: 15px;
}
.textarea{margin-left: 100px;}
.ss{
	width:100%;
	background-color: rgba(215, 215, 215, 1);
}
</style>
<script type="text/javascript">
function activityAuditLogList(activityId) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: $(window).width() - 40,
	title: "审核进度",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/activityAuditLog/activityAuditLogList.shtml?activityId=" + activityId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
/* 查看活动商品 */
function seeActivityProductList(activityId) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 10,
		title: "官方会场活动商品审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allActivityProductList.shtml?activityId=" + activityId+"&type=${type}",
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

	<table class="gridtable">
		<tr>
			<td class="title">商家名称</td>
			<td align="center" class="l-table-edit-td">${activityCustom.shortName}</td>
			<td class="title">商家序号</td>
			<td align="center" class="l-table-edit-td">${activityCustom.mchtCode}</td>
		</tr>
<%-- 		<tr>
		
			<td class="title">参加特卖活动总数（商家）</td>
			<td align="center" class="l-table-edit-td">${activityCustom.brandSaleNum}</td>
			<td class="title">30天内参加特卖活动总数（商家）</td>
			<td align="center" class="l-table-edit-td">${activityCustom.thirtyBrandSaleNum}</td>
		</tr>
		<tr>
			<td  class="title">30天销量(商品)</td>
			<td  align="center" class="l-table-edit-td">${activityCustom.thirtyQuantity}</td>
			<td class="title">30天销售额(商品)</td>
			<td align="center" class="l-table-edit-td">${activityCustom.thirtySalePrice}</td>
		</tr> --%>
		<tr>
			<td class="title">投诉次数(商品)</td>
			<td align="center" class="l-table-edit-td">${activityCustom.thisProductAppealNum}</td>
			<td class="title">违规次数(商家)</td>
			<td align="center" class="l-table-edit-td">${activityCustom.thisMchtViolateNum}</td>
		</tr>
		<tr>
		
			<td class="title">商家入驻时间</td>
			<td align="center" class="l-table-edit-td"><fmt:formatDate value="${activityCustom.mchtCooperateBeginDate}" pattern="yyyy-MM-dd"/></td>
			<td class="title">商家运营联系QQ</td>
			<td align="center" class="l-table-edit-td">${activityCustom.qq}</td>
		</tr>
	</table>
	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<table class="gridtable">
		<tr>
			<td class="title">商品数量</td>
			<td align="center" class="l-table-edit-td">${activityCustom.productNum}</td>
			<td class="title">库存数量</td>
			<td align="center" class="l-table-edit-td" width="302px;">${activityCustom.activityStock}</td>
		</tr>
	</table>
	<c:if test="${type eq 5 && activityCustom.feeRate!=null && activityCustom.feeRate!=activityCustom.serviceCharge}">
		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
		<br/>
		<table class="gridtable">
			<tr>
				<td class="title">本次佣金比例</td>
				<td align="center" class="l-table-edit-td"><samp style="color: red;"><fmt:formatNumber value="${activityCustom.feeRate}" pattern="#.####"/></samp></td>
				<td class="title">默认佣金比例</td>
				<td align="center" class="l-table-edit-td" width="302px;"><fmt:formatNumber value="${activityCustom.serviceCharge}" pattern="#.####"/></td>
			</tr>
			<tr>
				<td class="title">申请理由</td>
				<td colspan="3" align="left" class="l-table-edit-td">${activityCustom.remarks}</td>
			</tr>
		</table>
	</c:if>
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	
	<table class="ss">
		<tr>
			<td align="center" width="200px;" style="border:none"><img src='${pageContext.request.contextPath}/file_servelt${activityCustom.activityAreaPic}' width='100px;' height='100px;' style="margin-top: 20px;margin-bottom: 20px;"/></td>
			<td style="border:none">
				<samp style="color: red;font-size: 20px;">${activityCustom.activityAreaTypeDesc}-${activityCustom.activityAreaName }</samp>
				<br>
				<br>
				<samp class="samptime">报名时间：<fmt:formatDate value="${activityCustom.enrollBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;-&nbsp;<fmt:formatDate value="${activityCustom.enrollEndTime}" pattern="yyyy-MM-dd hh:mm:ss"/></samp>
				<br>
				<samp class="samptime">活动时间：<fmt:formatDate value="${activityCustom.activityBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;-&nbsp;<fmt:formatDate value="${activityCustom.activityEndTime}" pattern="yyyy-MM-dd hh:mm:ss"/></samp>
			</td>
		</tr>
	</table>
	
	<br/>
	<div><span class="table-title" >审核进度</span><a class="table-title-link" href="javascript:activityAuditLogList(${activityCustom.id})" >【查看更多】</a></div>
	<br/>
	<table class="gridtable">
		<tr align="center">
			<td class="title">审核部门</td>
			<td class="title">审核意见</td>
			<td class="title">审核时间</td>
			<td class="title">驳回理由</td>
		</tr>
		<c:forEach items="${activityAuditLog}" var="activityAuditLog">
			<tr align="center">
			<td>${activityAuditLog.typeDesc}</td>
			<td>${activityAuditLog.statusDesc}</td>
			<td><fmt:formatDate value="${activityAuditLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${activityAuditLog.remarks}</td>
		</tr>
		</c:forEach>
	</table><br/>
	<c:if test="${type eq 1 or type eq 5}">
	<br/>
	<table class="gridtable">
		<tr>
			<td class="title" align="center">活动名称：</td>
			<td><input type="text" disabled="disabled" value="${activityCustom.name}" style="width: 300px;" /></td>
		</tr>
		<tr>
			<td class="title" align="center">类目：</td>
			<td>
				<input type="text" disabled="disabled" value="${activityCustom.productTypeName}" style="width: 300px;" />
			</td>
		</tr>
		<tr>
			<td class="title" align="center">品牌：</td>
			<td><input type="text" disabled="disabled" value="${activityCustom.productBrandName}" style="width: 300px;" /></td>
		</tr>
		<tr height="200px;">
			<td class="title" align="center">入口图：</td>
			<td align="left">
				<div><img id="logoPic" alt="" src="${pageContext.request.contextPath}/file_servelt${activityCustom.entryPic}"></div>
			</td>
		</tr>
		<tr height="200px;">
			<td class="title" align="center">头部海报：</td>
			<td align="left">
				<div><img id="logoPic" alt="" src="${pageContext.request.contextPath}/file_servelt${activityCustom.posterPic}"></div>
			</td>
		</tr>
		</table>

		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
			<br/>
			<table class="gridtable">
				<tr>
					<td style="border:none">
						<div style="float: left;">
							<input id="feeCheck" type="checkbox" onclick="feeRateSel();"/>修改技术服务费率：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<div id="feeRateDiv" style="float: left;">
							<input type="hidden" id="oldFeeRate" name="oldFeeRate" value="${activityCustom.feeRate}"/>
							服务费比例：<input type="text" style="width: 50px;" id="feeRate" name="feeRate" value="<fmt:formatNumber value="${activityCustom.feeRate}" pattern="#.####"/>"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;默认比例：<fmt:formatNumber value="${activityCustom.serviceCharge}" pattern="#.####"/>&nbsp;&nbsp;&nbsp;申请理由：<input type="text" id="remarks" name="remarks" value="${activityCustom.remarks}" />
						</div>
					</td>
				</tr>
			</table>
		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
		<br/>
		<table class="gridtable">
			<tr>
				<td style="border:none">
					<c:if test="${type eq 1 }">
						是否通过：&nbsp;&nbsp;&nbsp;
						<input type="hidden" id="operateAuditStatus" name="operateAuditStatus" value="${activityCustom.operateAuditStatus}"/>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptOperateStatus" name="adoptOperateStatus" onclick="adoptOperateStatus();" value="" <c:if test="${activityCustom.operateAuditStatus==2}">checked="checked"</c:if> >通过审核</span>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectOperateStatus" name="rejectOperateStatus" onclick="rejectOperateStatus();" value="" <c:if test="${activityCustom.operateAuditStatus==3}">checked="checked"</c:if> >已驳回</span>
					</c:if>
					<c:if test="${type eq 5 }">
						终审：&nbsp;&nbsp;&nbsp;
						<input type="hidden" id="cooAuditStatus" name="cooAuditStatus" value="${activityCustom.cooAuditStatus}"/>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptCooStatus" name="adoptCooStatus" onclick="adoptCooStatus();" value="" <c:if test="${activityCustom.cooAuditStatus==2}">checked="checked"</c:if> >通过终审，上报活动</span>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectCooStatus" name="rejectCooStatus" onclick="rejectCooStatus();" value="" <c:if test="${activityCustom.cooAuditStatus==3}">checked="checked"</c:if> >已驳回</span>
					</c:if>
				</td>
			</tr>
			<tr id="text">
				<td style="border:none">
					<textarea id="statusRemarks" class="textarea" rows="10" cols="100"><c:if test="${type eq 1 }">${activityCustom.operateAuditRemarks}</c:if><c:if test="${type eq 5 }">${activityCustom.cooAuditRemarks}</c:if></textarea>
				</td>
			</tr>
		</table>	
		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
		<br/>
		</c:if>
	<table class="gridtable">
		<tr>
			<td style="border:none">
					<c:if test="${type eq 1 and activityCustom.operateAuditStatus eq 1 }">
						<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitButton();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<c:if test="${type eq 5 and activityCustom.cooAuditStatus eq 1 }">
						<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitButton();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(255, 255, 255, 1);" value="查看活动商品" onclick="seeActivityProductList(${activityCustom.id});" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(255, 255, 255, 1);" value="预览活动商品" />
			</td>
		</tr>
	</table>
</body>
</html>
<script type="text/javascript">
var activityId=${activityCustom.id};
var type=${type};
$(function(){
	$("#text").hide();
	$("#feeRateDiv").hide();
});

function feeRateSel(){
	if(document.getElementById("feeCheck").checked==true){
	    $("#feeRateDiv").show();
	}else{
		$("#feeRateDiv").hide();
	}
}

	//运营专员
	function adoptOperateStatus(){
		$("#operateAuditStatus").val("2");
		$("#text").hide();
		$("#rejectOperateStatus").attr('checked',false);
	}

	function rejectOperateStatus(){
		$("#operateAuditStatus").val("3");
		$("#text").show();
		$("#adoptOperateStatus").attr('checked',false);
	}
	//运营总监
	function adoptCooStatus(){
		$("#cooAuditStatus").val("2");
		$("#text").hide();
		$("#rejectCooStatus").attr('checked',false);
	}

	function rejectCooStatus(){
		$("#cooAuditStatus").val("3");
		$("#text").show();
		$("#adoptCooStatus").attr('checked',false);
	}
	//提交按钮
	function submitButton(){
		var oldFeeRate=$("#oldFeeRate").val();//服务费
		var status="";
		var feeRate=$("#feeRate").val();
		var remarks=$("#remarks").val();
		var statusRemarks=$("#statusRemarks").val();//驳回内容
		if(type==1){
			status=$("#operateAuditStatus").val();
		}else if(type==5){
			status=$("#cooAuditStatus").val();
		}
		
		if(status==3){
			if(statusRemarks==null||statusRemarks==""||statusRemarks==undefined){
				commUtil.alertError("请填写驳回原因");
				return;
			}
		}else if (status==2){
			if (document.getElementById("feeCheck").checked==true){
	 			if (feeRate!='' && (isNaN(feeRate) || feeRate>=1 || feeRate<0)){
					commUtil.alertError("请填写正确的格式，例如:12.5%填写为0.125");
					return;
				}else if (feeRate==''){
					feeRate=${activityCustom.serviceCharge};
				} 
			}else{
	 			if (oldFeeRate==''){
					feeRate=${activityCustom.serviceCharge};
				}else{
					feeRate=oldFeeRate;
				}
			}
		}else{
			commUtil.alertError("提交前请对活动进行审核");
			return;
		}
		
		$.ajax({ //ajax提交
			type:'POST',
			url:'${pageContext.request.contextPath}' +"/activityArea/venueActivityAudit.shtml",
			data:{
					activityId:activityId,
					status:status,
					statusRemarks:statusRemarks,
					type:type,
					feeRate:feeRate,
					remarks:remarks
			},
			dataType:"json",
			cache: false,
			success: function(json){
			   if(json==null || json.statusCode!=200){
			     commUtil.alertError(json.message);
			  }else{
                 $.ligerDialog.success("操作成功",function() {
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