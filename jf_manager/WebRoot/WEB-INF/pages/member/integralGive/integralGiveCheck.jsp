<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
<script type="text/javascript">
	
	$(function(){
		var type = '${integralGiveCustom.type }';
		if(type == 2) {
			$("#group1").css("display", "");
			$("#group2").css("display", "none");
			$("#group3").css("display", "none");
			$("#templateT").css("display", "none");
		}else if(type == 3) {
			$("#group1").css("display", "none");
			$("#group2").css("display", "");
			$("#group3").css("display", "none");
			$("#templateT").css("display", "");
		}else if(type == 4) {
			$("#group1").css("display", "none");
			$("#group2").css("display", "none");
			$("#group3").css("display", "");
			$("#templateT").css("display", "");
		}else {
			$("#group1").css("display", "none");
			$("#group2").css("display", "none");
			$("#group3").css("display", "none");
			$("#templateT").css("display", "none");
		}
		
		var groupId = '${integralGiveCustom.groupId }';
		if(groupId != '' && groupId != null ) {
  			var str = '';
      		var groupIds = new Array();
      		groupIds = groupId.split(",");
      		for(var i=0;i<groupIds.length;i++) {
      			if(groupIds[i] != '') {
      				if(str == '') {
      					str = "<a href=\"javascript:viewDetail(" + groupIds[i] + ");\">" + groupIds[i] + "</a>";
      				}else {
      					str += "、<a href=\"javascript:viewDetail(" + groupIds[i] + ");\">" + groupIds[i] + "</a>"
      				}
      			}
      		}
      		$("#groupId2").html(str);
  		}
	});
	
	//商家违规详情页
	function viewViolateOrder(id) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "商家违规详情页",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//会员资料
	function viewDetail(id) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title: "会员资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/member/info/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//子订单详情
	function subOrderCode(id) {
		$.ligerDialog.open({
	 		height: $(window).height()-50,
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
	
	function submitForm(auditStatus) {
		var re = /^[1-9]+[0-9]*]*$/;
		if (!re.test($("#integral").val())) {
			$("#tblIntegral").text("请输入正整数"); 
			return;
		}
		$("#auditStatus").val(auditStatus);
		$("#form1").submit();
	}
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" action="${pageContext.request.contextPath}/member/integralGive/saveOrUpdateIntegralGive.shtml" method="post" id="form1" >
		<input type="hidden" id="id" name="id" value="${integralGiveCustom.id }" />
		<input type="hidden" id="groupId" name="groupId" value="${integralGiveCustom.groupId }" />
		<input type="hidden" id="type" name="type" value="${integralGiveCustom.type }" />
		<input type="hidden" id="auditStatus" name="auditStatus" value="" />
		<table class="gridtable">
			<tr>
				<td class="title"  width="30%">赠送范围<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="typeItem" items="${typeList }">
						<span class="radioClass">
							<input name="type" <c:if test="${integralGiveCustom.type == typeItem.statusValue }">checked</c:if> disabled="disabled" type="radio" value="${typeItem.statusValue }" style="width: 30px;" >
							${typeItem.statusDesc}
						</span>
					</c:forEach>
				</td> 
			</tr>
			<tr id="group1">
				<td class="title"  width="30%">会员组<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="memberGroupItem" items="${memberGroupList }">
						<input name="groupId1" <c:if test="${fn:contains(integralGiveCustom.groupId, memberGroupItem.id) }">checked</c:if> disabled="disabled" type="checkbox" value="${memberGroupItem.id }" style="width: 30px;"/>${memberGroupItem.name }
			  		</c:forEach>
				</td> 
			</tr>
			<tr id="group2">
				<td class="title"  width="30%">会员ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span id="groupId2"></span>
					<label id="tblGroupId2" style="margin:0 0 0 10px;color: #FF0000">(多个用户使用英文","隔开)</label>
				</td> 
			</tr>
			<tr id="group3">
				<td class="title"  width="30%">子订单号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<a href="javascript:subOrderCode(${integralGiveCustom.subOrderId });">${integralGiveCustom.subOrderCode }</a>
			  		<label id="tblGroupId3" style="margin:0 0 0 10px;color: #FF0000"></label>
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">赠送积分数量<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="integral" name="integral" style="float:left;width: 150px" value="${integralGiveCustom.integral }" />
					<span>个</span>
				 	<label id="tblIntegral" style="margin:0 0 0 10px;color: #FF0000">(注：100个积分可抵1元)</label> 
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">赠送理由</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows=3 id="remarks" cols="45" class="text" disabled="disabled" >${integralGiveCustom.remarks }</textarea>
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">违规单号</td>
				<td align="left" class="l-table-edit-td">
					<a href="javascript:;" onclick="viewViolateOrder(${integralGiveCustom.violateId });">${integralGiveCustom.violateOrderCode }</a>
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">商家序号</td>
				<td align="left" class="l-table-edit-td">
					${integralGiveCustom.mchtCode }
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">预设承担方<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="costBearItem" items="${costBearList }">
						<span class="radioClass">
							<input <c:if test="${integralGiveCustom.costBear == costBearItem.statusValue }">checked</c:if> disabled="disabled" type="radio" value="${costBearItem.statusValue }" style="width: 30px;" >
							${costBearItem.statusDesc}
						</span>
					</c:forEach>
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">保证金扣款<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="isDepositDeductItem" items="${isDepositDeductList }">
						<input <c:if test="${integralGiveCustom.isDepositDeduct == isDepositDeductItem.statusValue }">checked</c:if> disabled="disabled" type="radio" value="${isDepositDeductItem.statusValue }" style="width: 30px;"/>
						${isDepositDeductItem.statusDesc }
			  		</c:forEach>
			  		<label id="tblIsDepositDeduct" style="margin:0 0 0 10px;color: #FF0000"></label>
				</td> 
			</tr>
			<tr id="templateT">
				<td class="title"  width="30%">短信模板<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					${templateContentStr }
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">操作</td> 
				<td align="left" class="l-table-edit-td" >
					<input type="button" onClick="submitForm(1);" class="l-button l-button-submit" value="通过" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="驳回" onclick="submitForm(2);" />
				</td>
			</tr>
		</table> 
	</form>
	</body>
</html>