<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
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
			<td class="title">30天内参加特卖活动总数(商家)</td>
			<td align="center" class="l-table-edit-td">${activityCustom.thirtyBrandSaleNum}</td>
		</tr>
		<tr>
			<td  class="title">30天销量（商品）</td>
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
				<td class="title">本次技术服务费</td>
				<td align="center" class="l-table-edit-td"><span style="color:#FF0000"><fmt:formatNumber value="${activityCustom.feeRate}" pattern="#.####"/></span></td>
				<td class="title">默认技术服务费</td>
				<td align="center" class="l-table-edit-td" width="302px;"><fmt:formatNumber value="${activityCustom.serviceCharge}" pattern="#.####"/></td>
			</tr>
			<tr>
				<td class="title">申请理由</td>
				<td align="left" colspan="3" class="l-table-edit-td">${activityCustom.remarks}</td>
			</tr>
		</table>
	</c:if>
	
	<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
	<br/>
	<div><span class="table-title" >审核进度</span><a class="table-title-link" href="javascript:activityAuditLogList(${activityCustom.id})" >【查看更多】</a></div>
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
	</table>
	
	<br/>
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
						<td align="center"><input type="text" <c:if test="${type ne 1 }">disabled="disabled"</c:if> id="preheatTime" name="preheatTime" value="<fmt:formatDate value='${activityCustom.preheatTime}' pattern='yyyy-MM-dd HH:mm'/>"/></td>
						<td align="center"><input type="text" <c:if test="${type ne 1 }">disabled="disabled"</c:if> id="activityBeginTime" name="activityBeginTime" value="<fmt:formatDate value='${activityCustom.activityBeginTime}' pattern='yyyy-MM-dd HH:mm'/>"/></td>
						<td align="center"><input type="text" <c:if test="${type ne 1 }">disabled="disabled"</c:if> id="activityEndTime" name="activityEndTime" value="<fmt:formatDate value='${activityCustom.activityEndTime}' pattern='yyyy-MM-dd HH:mm'/>"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<c:if test="${type eq 5 }">
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
					是否通过：&nbsp;&nbsp;&nbsp;
<!-- 					<c:if test="${type eq 1 }"> -->
<!-- 						<input type="hidden" id="operateAuditStatus" name="operateAuditStatus" value="${activityCustom.operateAuditStatus}"/> -->
<!-- 						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptOperateStatus" name="adoptOperateStatus" onclick="adoptOperateStatus();" value="" <c:if test="${activityCustom.operateAuditStatus==2}">checked="checked"</c:if> >通过审核</span> -->
<!-- 						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectOperateStatus" name="rejectOperateStatus" onclick="rejectOperateStatus();" value="" <c:if test="${activityCustom.operateAuditStatus==3}">checked="checked"</c:if> >已驳回</span> -->
<!-- 					</c:if> -->
<!-- 					<c:if test="${type eq 5 }"> -->
						<input type="hidden" id="cooAuditStatus" name="cooAuditStatus" value="${activityCustom.cooAuditStatus}"/>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="adoptCooStatus" name="adoptCooStatus" onclick="adoptCooStatus();" value="" <c:if test="${activityCustom.cooAuditStatus==2}">checked="checked"</c:if> >通过审核</span>
						<span class="radioClass"><input border="none" class="radioItem" type="radio" value="" id="rejectCooStatus" name="rejectCooStatus" onclick="rejectCooStatus();" value="" <c:if test="${activityCustom.cooAuditStatus==3}">checked="checked"</c:if> >已驳回</span>
<!-- 					</c:if> -->
				</td>
			</tr>
			<tr id="text">
				<td style="border:none">
					<textarea id="statusRemarks" class="textarea" rows="10" cols="100">${activityCustom.cooAuditRemarks}</textarea>
				</td>
			</tr>
		</table>	
		<br/><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV>
		<br/>
		<table class="gridtable">
			<tr>
				<td style="border:none">
					<c:if test="${activityCustom.cooAuditStatus eq 1 }">
						<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(22, 155, 213, 1);border: none;" value="提交" onclick="submitButton();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(255, 255, 255, 1);" value="查看活动商品" onclick="seeSaleActivityProduct(${activityCustom.id})"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" style="cursor:pointer;width: 150px;height: 50px;background-color: rgba(255, 255, 255, 1);" value="预览活动商品" />
				</td>
			</tr>
		</table>
	</c:if>
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
	var feeRate=$("#feeRate").val();//服务费
	var remarks=$("#remarks").val();//申请内容
	var preheatTime=$("#preheatTime").val();//期望时间
	var activityBeginTime=$("#activityBeginTime").val();//开始时间
	var activityEndTime=$("#activityEndTime").val();//结束时间
	var statusRemarks=$("#statusRemarks").val();//驳回内容
	var status=null;
	if(type==5){
		status=$("#cooAuditStatus").val();
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
	}
	
	if(status==3){
		if(statusRemarks==null||statusRemarks==""||statusRemarks==undefined){
			commUtil.alertError("请填写驳回原因");
			return;
		}
	}else if(status!=2){
		commUtil.alertError("提交前请对活动进行审核");
		return;
	}
	
	$.ajax({ //ajax提交
		type:'POST',
		url:'${pageContext.request.contextPath}' +"/activity/seeReject.shtml",
		data:{
				activityId:activityId,
				statusRemarks:statusRemarks,
				status:status,
				remarks:remarks,
				type:type,
				preheatTime:preheatTime,
				activityBeginTime:activityBeginTime,
				activityEndTime:activityEndTime,
				feeRate:feeRate
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