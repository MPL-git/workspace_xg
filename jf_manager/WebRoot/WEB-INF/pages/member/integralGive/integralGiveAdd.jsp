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
		$("#group1").css("display", "none");
		$("#group2").css("display", "none");
		$("#group3").css("display", "none");
		$("#templateT").css("display", "none");
		
	});
	
	function typeChecked() {
		var type = $("input:radio[name='type']:checked").val();
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
	}
	
	function submitForm() {
		$("#tblTpye").text("");
		$("#tblGroupId1").text("");
	 	$("#tblGroupId2").text("");
	 	$("#tblGroupId3").text("");
	 	$("#tblIntegral").text("");
	 	$("#tblCostBear").text("");
	 	$("#tblIsDepositDeduct").text("");
	 	
	 	var type = $('input:radio[name="type"]:checked').val();
	 	if(type == null){
	 	   	 $("#tblTpye").text("请选择赠送范围");
	 	     return;
 	    }
	 	var groupId1 = $('input:checkbox[name="groupId1"]:checked').val();
	 	if(type == 2 && groupId1 == null) {
	 		$("#tblGroupId1").text("请勾选会员组"); 
	 		return;
	 	}
	 	if(type == 3 && ($("#groupId2").val()) == '' ) {
			$("#tblGroupId2").text("会员ID不能为空"); 
			return;
	 	}
	 	if(type == 4 ) {
	 		$("#smsTemplateId").val($("#templateS").val());
	 		if(($("#groupId3").val()) == '' ) {
				$("#tblGroupId3").text("子订单号不能为空"); 
				return;
	 		} else {
	 			var subOrderCode = $("#groupId3").val();
	 			var flag = "";
	 			$.ajax({
	 				url : "${pageContext.request.contextPath}/member/integralGive/getSubOrderCode.shtml",
	 				data : {subOrderCode : subOrderCode},
	 				dataType : 'json',
	 				async : false,
	 				success : function(data) {
	 					if(data == null || data.statusCode == 999 ) {
	 						$.ligerDialog.error('操作超时，请稍后再试！');
	 						flag = "flag";
	 					}else if(data.statusCode == 200){
	 						$("#mchtCode").val(data.subOrderCustom.mchtCode);
	 						$("#groupId").val(data.subOrderCustom.memberId);
	 					}else if(data.statusCode == 404) {
	 						$("#tblGroupId3").text("此子订单不存在"); 
	 						$("#mchtCode").val("");
	 						flag = "flag";
	 					}
	 				},
	 				error : function() {
	 					$.ligerDialog.error('操作超时，请稍后再试！');
	 					flag = "flag";
	 				}
	 			});
	 			if(flag == 'flag') {
	 				return;
	 			}
	 		}
	 	}
	 	var re = /^[1-9]+[0-9]*]*$/;
		if (!re.test($("#integral").val())) {
			$("#tblIntegral").text("请输入正整数"); 
			return;
		}
		var costBear = $('input:radio[name="costBear"]:checked').val();
		if(costBear == null){
	 	   	 $("#tblCostBear").text("请选择预设承担方");
	 	     return;
	    }
		var isDepositDeduct = $('input:radio[name="isDepositDeduct"]:checked').val();
		if(isDepositDeduct == null){
	 	   	 $("#tblIsDepositDeduct").text("请选择保证金扣款");
	 	     return;
	    }
		var groupId = "";
		if (type == 2) {
		    $("[name='groupId1']").each(function () {
		    	if (this.checked){
		      		groupId += this.value +",";
		        }
		    });
		}else if (type == 3) {
			groupId = $("#groupId2").val();
			$("#smsTemplateId").val($("#templateS").val());
		}
		if(groupId != '') {
			$("#groupId").val(groupId.replace(/(\,*$)/g, ""));
		}
		$("#form1").submit();
	}

	function getSubOrderCode() {
		var subOrderCode = $("#groupId3").val();
		if(subOrderCode != '') {
			$.ajax({
				url : "${pageContext.request.contextPath}/member/integralGive/getSubOrderCode.shtml",
				data : {subOrderCode : subOrderCode},
				dataType : 'json',
				success : function(data) {
					if(data == null || data.statusCode == 999 ) {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}else if(data.statusCode == 200){
						$("#mchtCode").val(data.subOrderCustom.mchtCode);
						$("#tblGroupId3").text("");
					}else if(data.statusCode == 404) {
						$("#tblGroupId3").text("此子订单不存在"); 
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else {
			$("#tblGroupId3").text("子订单号不能为空"); 
		}
	}
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" action="${pageContext.request.contextPath}/member/integralGive/saveOrUpdateIntegralGive.shtml" method="post" id="form1" >
		<input type="hidden" id="groupId" name="groupId" />
		<input type="hidden" id="smsTemplateId" name="smsTemplateId" />
		<table class="gridtable">
			<tr>
				<td class="title"  width="30%">赠送范围<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="typeItem" items="${typeList }">
						<span class="radioClass">
							<input name="type" onChange="typeChecked();" <c:if test="${typeItem.statusValue == 1 }">checked</c:if> class="radioItem" type="radio" value="${typeItem.statusValue }" style="width: 30px;" >
							${typeItem.statusDesc}
						</span>
					</c:forEach>
					<label id="tblTpye" style="margin:0 0 0 10px;color: #FF0000"></label> 
				</td> 
			</tr>
			<tr id="group1">
				<td class="title"  width="30%">会员组<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="memberGroupItem" items="${memberGroupList }">
						<input name="groupId1" type="checkbox" value="${memberGroupItem.id }" style="width: 30px;"/>${memberGroupItem.name }
			  		</c:forEach>
			  		<label id="tblGroupId1" style="margin:0 0 0 10px;color: #FF0000"></label>
				</td> 
			</tr>
			<tr id="group2">
				<td class="title"  width="30%">会员ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea rows=3 id="groupId2" name="groupId2" cols="45" class="text" ></textarea>
					<label id="tblGroupId2" style="margin:0 0 0 10px;color: #FF0000">(多个用户使用英文","隔开)</label>
				</td> 
			</tr>
			<tr id="group3">
				<td class="title"  width="30%">子订单号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input id="groupId3" style="width: 200px;" name="subOrderCode" type="text" value="" />
					<input id="groupIdButton" onClick="getSubOrderCode();" style="width: 50px;" type="button" value="检测" >
			  		<label id="tblGroupId3" style="margin:0 0 0 10px;color: #FF0000"></label>
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">赠送积分数量<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="text" id="integral" name="integral" style="float:left;width: 150px" value="" />
					<span>个</span>
				 	<label id="tblIntegral" style="margin:0 0 0 10px;color: #FF0000">(注：100个积分可抵1元)</label> 
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">赠送理由</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows=3 id="remarks" name="remarks" cols="45" class="text" ></textarea>
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">违规单号</td>
				<td align="left" class="l-table-edit-td">
					<input name="violateOrderCode" type="text" value="" />
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">商家序号</td>
				<td align="left" class="l-table-edit-td">
					<input id="mchtCode" name="mchtCode" type="text" value="" />
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">预设承担方<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="costBearItem" items="${costBearList }">
						<span class="radioClass">
							<input name="costBear" class="radioItem" type="radio" value="${costBearItem.statusValue }" style="width: 30px;" >
							${costBearItem.statusDesc}
						</span>
					</c:forEach>
					<label id="tblCostBear" style="margin:0 0 0 10px;color: #FF0000"></label> 
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">保证金扣款<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:forEach var="isDepositDeductItem" items="${isDepositDeductList }">
						<input name="isDepositDeduct" type="radio" value="${isDepositDeductItem.statusValue }" style="width: 30px;"/>
						${isDepositDeductItem.statusDesc }
			  		</c:forEach>
			  		<label id="tblIsDepositDeduct" style="margin:0 0 0 10px;color: #FF0000"></label>
				</td> 
			</tr>
			<tr id="templateT">
				<td class="title"  width="30%">短信模板<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<select id="templateS" name="templateS">
						<c:forEach var="sysSmsTemplate" items="${sysSmsTemplateList }">
							<option value="${sysSmsTemplate.id }">
								${fn:replace(fn:replace(sysSmsTemplate.content, '{1}', '××××'), '{2}', '××.××') }
							</option>
				  		</c:forEach>
					</select>
				</td> 
			</tr>
			<tr>
				<td class="title"  width="30%">操作</td> 
				<td align="left" class="l-table-edit-td" >
					<input type="button" onClick="submitForm();" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close()" />
				</td>
			</tr>
		</table> 
	</form>
	</body>
</html>