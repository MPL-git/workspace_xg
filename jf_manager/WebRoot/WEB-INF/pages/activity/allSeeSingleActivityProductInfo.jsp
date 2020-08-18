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
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	

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
.l-grid-header2{height:35px!important;}
 </style>
</style>
<script type="text/javascript">
function activityProductAuditLogList(activityProductId) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: $(window).width() - 40,
	title: "单品商品审核流水表进度",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/activityProductAuditLog/activityProductAuditLogList.shtml?activityProductId=" + activityProductId,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
$(function(){
	var status=$("#status").val();
	if (status!=3){
		$(".text").hide();		
	}
});

//运营专员
function adoptStatus(){
	if($("#adoptStatus").prop("checked")){
		$("#status").val("2");
		$(".text").hide();
		$("#rejectStatus").attr("checked", false);
	}
}
function rejectStatus(){
	if($("#rejectStatus").prop("checked")){
		$("#status").val("3");
		$(".text").show();
		$("#adoptStatus").attr("checked", false);
	}
}
//运营总监
function adoptCooStatus(){
	if($("#adoptCooStatus").prop("checked")){
		$("#status").val("2");
		$(".text").hide();
		$("#rejectCooStatus").attr("checked", false);
	}
}
function rejectCooStatus(){
	if($("#rejectCooStatus").prop("checked")){
		$("#status").val("3");
		$(".text").show();
		$("#adoptCooStatus").attr("checked", false);
	}
}

function submitSingleButton(){
	var activityProductId="${activityProductId}";
	var type="${type}";
	var status=$("#status").val();
	var remarks=$("#remarks").val();
	
	if (type==1 || type==4){
		if (status==3){
			if($("#remarks").val().length==0){
				commUtil.alertError("请输入驳回内容");
				return;
			}
		}else if (status!=2){
			commUtil.alertError("请选择状态");
			return;
		}
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
<style type="text/css">
.ss{
	width:100%;
	background-color: rgba(215, 215, 215, 1);
}
.samptime{font-size: 15px;}

</style>
<html>
<body>


	<table class="ss">
		<tr>
			<td align="center" width="200px;" style="border:none"><img src='${pageContext.request.contextPath}/file_servelt${activityProductCustom.activityAreaPic}' width='100px;' height='100px;' style="margin-top: 20px;margin-bottom: 20px;"/></td>
			<td style="border:none">
				<samp style="color: red;font-size: 20px;">${activityProductCustom.activityAreaTypeDesc}-${activityProductCustom.activityAreaName }</samp>
				<br>
				<br>
				<samp class="samptime">报名时间：<fmt:formatDate value="${activityProductCustom.enrollBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;-&nbsp;<fmt:formatDate value="${activityProductCustom.enrollEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></samp>
				<br>
				<samp class="samptime">活动时间：<fmt:formatDate value="${activityProductCustom.activityBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;-&nbsp;<fmt:formatDate value="${activityProductCustom.activityEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></samp>
			</td>
		</tr>
	</table>
	<br/>
	<table class="gridtable">
		<tr>
			<td class="title">商家名称</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.shortName}</td>
			<td class="title">商家序号</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.mchtCode}</td>
		</tr>
		<tr>
		
			<td class="title">参加单品活动总数（商家）</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.joinSaleNum}</td>
			<td class="title">30天内参加单品活动总数（商家）</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.joinDfgSaleNum}</td>
		</tr>
		<tr>
			<td  class="title">30天销量(商品)</td>
			<td  align="center" class="l-table-edit-td">${activityProductCustom.dfgNum}</td>
			<td class="title">30天销售额（商品）</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.dfgPrice}</td>
		</tr>
		<tr>
		
			<td class="title">投诉次数（商品）</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.thisProductAppealNum}</td>
			<td class="title">违规次数（商家）</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.thisMchtViolateNum}</td>
		</tr>
		<tr>
		
			<td class="title">商家入驻时间</td>
			<td align="center" class="l-table-edit-td"><fmt:formatDate value="${activityProductCustom.mchtOoperateBeginDate}" pattern="yyyy-MM-dd"/></td>
			<td class="title">商家运营联系QQ</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.qq}</td>
		</tr>
		<tr>
			<td class="title">对接运营</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.contactName}</td>
			<td class="title">商家品牌技术服务费</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.productBrandName}：${activityProductCustom.popCommissionRate}</td>
		</tr>
	</table>
	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<table class="gridtable">
		<tr>
			<td class="title">本次活动报名商品数量</td>
			<td align="center" class="l-table-edit-td">${activityProductCustom.signUpProductNum}</td>
			<td class="title">库存数量</td>
			<td align="center" class="l-table-edit-td" width="302px;">${activityProductCustom.productStock}</td>
		</tr>
	</table>
	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<table class="gridtable">
		<tr align="center">
			<td class="title">商品图</td>
			<td class="title">商品信息</td>
			<td class="title">活动价/历史最低价/原价</td>
			<td class="title">类目/品牌</td>
			<td class="title">本次销量/历史总销量</td>
			<td class="title">库存</td>
			<td class="title">参加单品活动次数</td>
		</tr>
		<tr align="center">
			<td>
				<img src='${pageContext.request.contextPath}/file_servelt${activityProductCustom.productPic}' width='100px;' height='100px;' style="margin-top: 20px;margin-bottom: 20px;"/>
			</td>
			<td>
				<samp>${activityProductCustom.productName}</samp>
				<br>
				<br>
				<samp>商品ID：${activityProductCustom.productCode}</samp>&nbsp;&nbsp;&nbsp;&nbsp;<samp>货号：${activityProductCustom.productArtNo}</samp>
			</td>
			<td>
				<samp>${activityProductCustom.activitySalePrice}</samp><br>
				<samp>${activityProductCustom.minimumPrice}</samp><br>
				<samp>${activityProductCustom.tagPrice}</samp>
				
			</td>
			<td>
				<samp>${activityProductCustom.productTypeName}</samp>
				<br>
				<br>
				<samp>${activityProductCustom.productBrandName}</samp>
				</td>
			<td>
				<samp>${activityProductCustom.thisSalesNum}</samp><br>
				<samp>${activityProductCustom.totalSalesNum}</samp>
			</td>
			<td>${activityProductCustom.productStock}</td>
			<td>${activityProductCustom.signleTypeTwoNum}</td>
		</tr>
	
	</table>
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<div><span class="table-title" >审核进度</span><a class="table-title-link" href="javascript:activityProductAuditLogList(${activityProductCustom.id})" >【查看更多】</a></div>
	<table class="gridtable">
		<tr align="center">
			<td class="title">审核部门</td>
			<td class="title">审核意见</td>
			<td class="title">审核时间</td>
			<td class="title">驳回理由</td>
		</tr>
		<c:forEach items="${ActivityProductAuditLogCustom}" var="activityProductAuditLog">
			<tr align="center">
			<td>${activityProductAuditLog.typeDesc}</td>
			<td>${activityProductAuditLog.statusDesc}</td>
			<td><fmt:formatDate value="${activityProductAuditLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${activityProductAuditLog.remarks}</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:if test="${type eq 1 and activityProductCustom.operateAuditStatus eq 1 or type eq 4 and activityProductCustom.cooAuditStatus eq 1}">
		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
		<br/>
		<table class="gridtable">
			<tr>
				<td style="border:none">
					是否通过：&nbsp;&nbsp;&nbsp;
					<c:if test="${staffType eq 1 and type eq 1}">
					<input type="hidden" id="status" name="status" value="${activityProductCustom.operateAuditStatus}">
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptStatus" name="adoptStatus" onclick="adoptStatus();" value="" <c:if test="${activityProductCustom.operateAuditStatus==2}">checked="checked"</c:if> >通过审核</span>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectStatus" name="rejectStatus" onclick="rejectStatus();" value="" <c:if test="${activityProductCustom.operateAuditStatus==3}">checked="checked"</c:if> >已驳回</span>
					</c:if>
					<c:if test="${type eq 4}">
					<input type="hidden" id="status" name="status" value="${activityProductCustom.cooAuditStatus}">
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptCooStatus" name="adoptCooStatus" onclick="adoptCooStatus();" value="" <c:if test="${activityProductCustom.cooAuditStatus==2}">checked="checked"</c:if> >通过审核</span>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectCooStatus" name="rejectCooStatus" onclick="rejectCooStatus();" value="" <c:if test="${activityProductCustom.cooAuditStatus==3}">checked="checked"</c:if> >已驳回</span>
					</c:if>
				</td>
			</tr>
			<tr class="text">
				<td style="border:none">
					<textarea id="remarks" class="textarea" rows="3" cols="60">${activityCustom.remarks}</textarea>
				</td>
			</tr>
		</table>	
		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
		<br/>
		<table class="gridtable">
			<tr>
				<td style="border:none">
					<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitSingleButton();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>
