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
//查看活动商品
function seeSaleActivityProduct(activityId) {
	var type="${type}";
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "品牌特卖专题活动商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allActivityProductList.shtml?activityId=" + activityId+"&type="+type,
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
<%-- 		<tr>
			<td class="title">本次活动销量</td>
			<td align="center" class="l-table-edit-td">${activityCustom.thisQuantity}</td>
			<td class="title">本次活动销售额</td>
			<td align="center" class="l-table-edit-td" width="302px;">${activityCustom.thisSalePrice}</td>
		</tr> --%>
	</table>
	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
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
		<tr>
			<td class="title" align="center">利益点：</td>
			<td><input type="text" disabled="disabled" value="${activityCustom.benefitPoint}" style="width: 300px;" /></td>
		</tr>
		<tr>
			<td class="title" align="center">期望活动时间：</td>
			<td><input type="text" disabled="disabled" value="<fmt:formatDate value="${activityCustom.expectedStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" style="width: 300px;" /></td>
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
		<tr>
			<td class="title" align="center">促销方式：</td>
			<td>
				<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" <c:if test="${activityCustom.preferentialType eq 0}">checked="checked"</c:if> >无</span>
				<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" <c:if test="${activityCustom.preferentialType eq 1}">checked="checked"</c:if> >优惠券</span>
				<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" <c:if test="${activityCustom.preferentialType eq 2}">checked="checked"</c:if> >满减</span>
				<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" <c:if test="${activityCustom.preferentialType eq 3}">checked="checked"</c:if> >满赠</span>
				<span class="radioClass"><input class="radioItem" disabled="disabled" type="radio" <c:if test="${activityCustom.preferentialType eq 4}">checked="checked"</c:if> >多买优惠</span>
			</td>
		</tr>
		<c:if test="${activityCustom.preferentialType eq 1}">
			<tr>
				<td class="title" align="center">优惠券：</td>
				<td>
					<table>
						<c:forEach items="${copuon}" var="list" varStatus="">
							<tr>
								<td style="border:none">
									面额&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${list.money}">元&nbsp; &nbsp;使用条件&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${list.minimum}"/>元&nbsp; &nbsp;发行量&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${list.grantQuantity}"/>张&nbsp;
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:if>
		<c:if test="${activityCustom.preferentialType eq 2}">
			<tr>
				<td class="title" align="center">满减额度：</td>
				<td>
					<table>
						<tr>
							<td style="border:none">
								<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullCutCustom.ladderFlag eq 0}">checked="checked"</c:if> >非阶梯</span>
								<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullCutCustom.ladderFlag eq 1}">checked="checked"</c:if> >阶梯</span>
							</td>
						</tr>
						
						<c:if test="${fullCutCustom.ladderFlag eq 0 }">
						<tr>
							<td style="border:none">
								满&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${fullCutCustom.full}"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${fullCutCustom.cut}"/>元&nbsp;&nbsp;<input type="checkbox" disabled="disabled" <c:if test="${fullCutCustom.sumFlag==1}">checked="checked"</c:if> />累加
							</td>
						</tr>
						</c:if>
						<c:if test="${fullCutCustom.ladderFlag eq 1 }">
							<c:forEach items="${fullCutList}" var="list">
							<tr>
								<td style="border:none">
									满&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${list.full}"/>&nbsp;元&nbsp;&nbsp;减&nbsp;<input style="width: 50px;" type="text" disabled="disabled" value="${list.cut}"/>元
								</td>
							</tr>
							</c:forEach>
						</c:if>
						
					</table>
				</td>
			</tr>
		</c:if>
		<c:if test="${activityCustom.preferentialType eq 3 }">
			<tr>
				<td class="title" align="center">满赠类型</td>
				<td>
					<table>
						<tr>
							<td style="border:none">
								<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" value="1"  validate="{radioRequired:true}" <c:if test="${fullGive.type eq 1}">checked="checked"</c:if> >满额赠</span>
								<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" value="1" validate="{radioRequired:true}" <c:if test="${fullGive.type eq 2}">checked="checked"</c:if> >买即赠</span>
							</td>
						</tr>
						<c:if test="${fullGive.type eq 1}">
						<tr>
							<td style="border:none">
								满额：&nbsp;满&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.minimum}"/>&nbsp;元&nbsp;&nbsp;<input type="checkbox" disabled="disabled" value="" <c:if test="${fullGive.sumFlag eq 1}">checked="checked"</c:if> />累加
							</td>
						</tr>
						</c:if>
						<tr>
							<td style="border:none">
								赠品ID:<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.productId}"/>
							</td>
						</tr>
						<tr>
							<td style="border:none">
								数量:<input type="text" style="width: 50px;" disabled="disabled" value="${fullGive.productNum}"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:if>
		<c:if test="${activityCustom.preferentialType eq 4}">
			<tr>
				<td class="title" align="center">多买优惠：</td>
				<td>
					<table>
						<tr>
							<td style="border:none">
								<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType eq 1}">checked="checked"</c:if> >满M件减N件</span>
								<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType eq 2}">checked="checked"</c:if> >M件N元</span>
								<span class="radioClass"><input border="none" class="radioItem" disabled="disabled" type="radio" <c:if test="${fullDiscountType eq 3}">checked="checked"</c:if> >M件N折</span>
							</td>
						</tr>
						
						<c:if test="${fullDiscountType eq 1 }">
						<tr>
							<td style="border:none">
								&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${fullDiscountMap.fullOfOne}"/>&nbsp;件&nbsp;&nbsp;减&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullDiscountMap.fullGiftsOneName}"/>件
							</td>
						</tr>
						</c:if>
							
						<c:if test="${fullDiscountType eq 2 }">
						<tr>
							<td style="border:none">
								&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${fullDiscountMap.fullOfTwo}"/>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${fullDiscountMap.fullGiftsTwoName}"/>元
							</td>
						</tr>
						
						</c:if>	
						<c:if test="${fullDiscountType eq 3 }">
						<c:forEach items="${fullDiscountList}" var="list">
						<tr>
							<td style="border:none">
								&nbsp;<input type="text" disabled="disabled" style="width: 50px;" value="${list.fullGiscountThree}"/>&nbsp;件&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width: 50px;" disabled="disabled" value="${list.fullGiscountThreeName}"/>折
							</td>
						</tr>
						</c:forEach>
						</c:if>	
					</table>
				</td>
			</tr>
		</c:if>
		<tr>
			<td class="title" align="center">活动排期</td>
			<td>
				<table class="gridtable">
					<tr>
						<td class="title">活动预热时间</td>
						<td class="title">活动开始时间</td>
						<td class="title">活动结束时间</td>
					</tr>
					<tr>
						<td align="center"><input type="text" id="preheatTime" name="preheatTime" value="<fmt:formatDate value='${activityCustom.preheatTime}' pattern='yyyy-MM-dd HH:mm'/>"/></td>
						<td align="center"><input type="text" id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value='${activityCustom.activityBeginTime}' pattern='yyyy-MM-dd HH:mm'/>"/></td>
						<td align="center"><input type="text" id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value='${activityCustom.activityEndTime}' pattern='yyyy-MM-dd HH:mm'/>"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<c:if test="${activityDate eq 1 }">
		<table class="gridtable">
			<tr>
				<td style="border:none">
					是否暂停：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${activityCustom.status=='5'}">
					<input type="checkbox" id="suspend" name="suspend" style="width: 50px;" value="1" onclick="suspend();" checked="true"/>
					</c:if>
					<c:if test="${activityCustom.status!='5'}">
					<input type="checkbox" id="suspend" name="suspend" style="width: 50px;" value="0" onclick="suspend();"/>
					</c:if>
					暂停活动
				</td>
			</tr>
		</table>
		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
		<br/>
	</c:if>
	<table class="gridtable">
		<tr>
			<td style="border:none">
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitButton();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(255, 255, 255, 1);" value="查看活动商品" onclick="seeSaleActivityProduct(${activityCustom.id})"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
	$("#preheatTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#activityBeginTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#activityEndTime").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
});

function suspend(){
	if(document.getElementById("suspend").checked==true){
	    $("#suspend").val("1");
	}else{
		$("#suspend").val("0");
	}
}

//提交按钮
function submitButton(){
	
	var adoptStatus=$('input[name="suspend"]:checked').val();//暂停
	var preheatTime=$("#preheatTime").val();//期望预热时间
	var activityBeginTime=$("#activityBeginTime").val();//开始时间
	var activityEndTime=$("#activityEndTime").val();//结束时间
	if(preheatTime.length==0){
		commUtil.alertError("请选择活动预热时间");
		return;
	}
	if(activityBeginTime.length==0){
		commUtil.alertError("请选择活动开始时间");
		return;
	}
	if(activityEndTime.length==0){
		commUtil.alertError("请选择活动结束时间");
		return;
	}
	
	if(preheatTime>activityBeginTime||preheatTime>activityEndTime){
		commUtil.alertError("预热时间不能大于活动开始时间或者活动结束时间");
		return;
	}
	if(activityBeginTime<preheatTime){
		commUtil.alertError("活动开始时间不能小于预热时间");
		return;
	}
	if(activityBeginTime>activityEndTime){
		commUtil.alertError("活动开始时间不能大于活动结束时间");
		return;
	}
	if(activityEndTime<preheatTime||activityEndTime<activityBeginTime){
		commUtil.alertError("活动结束时间不能小于预热时间或者活动的开始时间");
		return;
	}
	
	$.ajax({ //ajax提交
		type:'POST',
		url:'${pageContext.request.contextPath}' +"/activity/seeReject.shtml",
		data:{
				activityId:activityId,
				adoptStatus:adoptStatus,
				type:type,
				preheatTime:preheatTime,
				activityBeginTime:activityBeginTime,
				activityEndTime:activityEndTime
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
