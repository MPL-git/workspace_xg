<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
 	
</style>
<script type="text/javascript">
	var submitFlag = true;
	$(function(){
		$("input[class^='validate-']").bind('change', function(){
			if($(".custom-validate").html() != null) {
				if(customValidate()) {
					submitCustomValidate();
				}
			}
		});
		
	});
	
	function customValidate() {
		var flag = true;
		$(".custom-validate").remove();
		var tipName = $(".validate-tipName").val();
		if(tipName == '') {
			$(".validate-tipName").parent().append('<span class="custom-validate" style="color: red;">该字段不能为空</span>');
			flag = false;
		}else if(tipName.length > 20) {
			$(".validate-tipName").parent().append('<span class="custom-validate" style="color: red;">请输入一个长度最多是 20 的字符串</span>');
			flag = false;
		}
		$(".validate-tipRate").each(function(){
			var tipRate = $(this).val();
			if($(this).parent().find(".custom-validate").html() == null) {
				if(tipRate == '') {
					$(this).parent().append('<span class="custom-validate" style="color: red;">该字段不能为空</span>');
					flag = false;
				}else if(!(/(^[1-9]\d*(\.[0-9]{0,2})?$)|(^0\.[0-9]?([1-9]{1})$)|(^0\.[1-9]{1}([0-9]?)$)/).test(tipRate)) {
					$(this).parent().append('<span class="custom-validate" style="color: red;">请输入一个大于0最多保留两位小数</span>');
					flag = false;
				}else if(Number(tipRate) > Number(9999)) {
					$(this).parent().append('<span class="custom-validate" style="color: red;">请输入一个最大为 9999 的值</span>');
					flag = false;
				}
			}
		});
		$(".validate-tipInviteLimit").each(function(){
			var tipAmount = $(this).val();
			if(tipAmount == '') {
				$(this).parent().append('<span class="custom-validate" style="color: red;">该字段不能为空</span>');
				flag = false;
			}else if(!(/^[1-9]\d*$/).test(tipAmount)) {
				$(this).parent().append('<span class="custom-validate" style="color: red;">请输入一个正整数</span>');
				flag = false;
			}
		});
		return flag;
	}
	
	function submitCustomValidate() {
		var flag = true;
		var oneEndAmount = $("#oneEndAmount").val();
		var twoEndAmount = $("#twoEndAmount").val();
		var threeEndAmount = $("#threeEndAmount").val();
		var fourEndAmount = $("#fourEndAmount").val();
		if(Number(oneEndAmount) >= Number(twoEndAmount)) {
			$("#twoEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+oneEndAmount+'的正整数</span>');
			flag = false;
		}
		if(Number(twoEndAmount) >= Number(threeEndAmount)) {
			$("#threeEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+twoEndAmount+'的正整数</span>');
			flag = false;
		}
		if(Number(threeEndAmount) >= Number(fourEndAmount)) {
			$("#fourEndAmount").parent().append('<span class="custom-validate" style="color: red;">请输入一个大于'+threeEndAmount+'的正整数</span>');
			flag = false;
		}
		$(".validate-tipRate").each(function(){
			if($(this).parent().find(".custom-validate").html() == null) {
				var beginRate = $(this).parent().find(".validate-tipRate:first").val();
				var endinRate = $(this).parent().find(".validate-tipRate:last").val();
				if(Number(beginRate) > Number(endinRate)) {
					$(this).parent().append('<span class="custom-validate" style="color: red;">结束比例不得小于起始比例</span>');
					flag = false;
				}
			}
		});
		return flag;
	}
	
	function submitFun(){
		var validateFlag = customValidate();
		if(validateFlag && submitFlag) {
			if(submitCustomValidate()) {
				submitFlag = false;
				$("#formData").submit();
			}
		}
	}
	
	//查看计算结果
	function showResult() {
		var validateFlag = customValidate();
		if(validateFlag && submitFlag) {
			if(submitCustomValidate()) {
				var baseAmount = $("#baseAmount").val();
				var sqBeginRate = $("#sqBeginRate").val();
				var sqEndRate = $("#sqEndRate").val();
				var ljBeginRate = $("#ljBeginRate").val();
				var ljEndRate = $("#ljEndRate").val();
				var oneBeginAmount = $("#oneBeginAmount").val();
				var oneEndAmount = $("#oneEndAmount").val();
				var twoEndAmount = $("#twoEndAmount").val();
				var threeEndAmount = $("#threeEndAmount").val();
				var fourEndAmount = $("#fourEndAmount").val();
				var oneBeginRate = $("#oneBeginRate").val();
				var oneEndRate = $("#oneEndRate").val();
				var twoBeginRate = $("#twoBeginRate").val();
				var twoEndRate = $("#twoEndRate").val();
				var threeBeginRate = $("#threeBeginRate").val();
				var threeEndRate = $("#threeEndRate").val();
				var fourBeginRate = $("#fourBeginRate").val();
				var fourEndRate = $("#fourEndRate").val();
				var qtBeginRate = $("#qtBeginRate").val();
				var qtEndRate = $("#qtEndRate").val();
				var inviteLimit = $("#inviteLimit").val();
				var syqBeginRate = $("#syqBeginRate").val();
				var syqEndRate = $("#syqEndRate").val();
				var xyqBeginRate = $("#xyqBeginRate").val();
				var xyqEndRate = $("#xyqEndRate").val();
				var lyqBeginRate = $("#lyqBeginRate").val();
				var lyqEndRate = $("#lyqEndRate").val();
				$.ligerDialog.open({
					height: $(window).height() - 300,
					width: $(window).width() - 700,
					title: "计算结果",
					name: "INSERT_WINDOW",
					url: "${pageContext.request.contextPath}/signInCnf/showResult.shtml?baseAmount="+baseAmount+"&sqBeginRate="+sqBeginRate+"&sqEndRate="+sqEndRate
							+"&ljBeginRate="+ljBeginRate+"&ljEndRate="+ljEndRate+"&oneBeginAmount="+oneBeginAmount+"&oneEndAmount="+oneEndAmount+"&twoEndAmount="+twoEndAmount+"&threeEndAmount="+threeEndAmount
							+"&fourEndAmount="+fourEndAmount+"&oneBeginRate="+oneBeginRate+"&oneEndRate="+oneEndRate+"&twoBeginRate="+twoBeginRate+"&twoEndRate="+twoEndRate
							+"&threeBeginRate="+threeBeginRate+"&threeEndRate="+threeEndRate+"&fourBeginRate="+fourBeginRate+"&fourEndRate="+fourEndRate
							+"&qtBeginRate="+qtBeginRate+"&qtEndRate="+qtEndRate+"&inviteLimit="+inviteLimit+"&syqBeginRate="+syqBeginRate+"&syqEndRate="+syqEndRate
							+"&xyqBeginRate="+xyqBeginRate+"&xyqEndRate="+xyqEndRate+"&lyqBeginRate="+lyqBeginRate+"&lyqEndRate="+lyqEndRate,
					showMax: true,
					showToggle: false,
					showMin: false,
					isResize: true,
					slide: false,
					data: null
				});
			}
		}
	}
	
</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="formData" class="formData" method="post" id="formData" action="${pageContext.request.contextPath}/signInCnf/addOrUpdateSignInCnf.shtml" >
		<input type="hidden" name="signInCnfId" value="${signInCnf.id }" />
		<input type="hidden" name="oneId" value="${oneSignInCnfDtl.id }" />
		<input type="hidden" name="twoId" value="${twoSignInCnfDtl.id }" />
		<input type="hidden" name="threeId" value="${threeSignInCnfDtl.id }" />
		<input type="hidden" name="fourId" value="${fourSignInCnfDtl.id }" />
		<input type="hidden" name="qtId" value="${qtSignInCnfDtl.id }" />
		<input type="hidden" name="sqId" value="${sqSignInCnfDtl.id }" />
		<input type="hidden" name="ljId" value="${ljSignInCnfDtl.id }" />
		<input type="hidden" name="syqId" value="${syqSignInCnfDtl.id }" />
		<input type="hidden" name="xyqId" value="${xyqSignInCnfDtl.id }" />
		<input type="hidden" name="lyqId" value="${lyqSignInCnfDtl.id }" />
		
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">方案名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="name" class="validate-tipName" name="name" value="${signInCnf.name }" type="text" style="width:220px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">签到比例基础值<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<select id="baseAmount" name="baseAmount" style="width: 135px;"  >
						<c:if test="${not empty baseAmount }">
							<option value="${baseAmount }" selected="selected" >${baseAmount }</option>
						</c:if>
						<c:forEach var="str" items="${strList }">
							<c:if test="${str != baseAmount }">
								<option value="${str }" >${str }</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">首次签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="sqBeginRate" class="validate-tipRate" name="sqBeginRate" value="${sqSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="sqEndRate" class="validate-tipRate" name="sqEndRate" value="${sqSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">连续签到7天获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="ljBeginRate" class="validate-tipRate" name="ljBeginRate" value="${ljSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="ljEndRate" class="validate-tipRate" name="ljEndRate" value="${ljSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">用户累计签到金额小于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" style="width: 26%;" >
					<input id="oneBeginAmount" name="oneBeginAmount" type="hidden" value="${oneSignInCnfDtl.beginAmount }" />
					<input id="oneEndAmount" class="validate-tipRate" name="oneEndAmount" value="${oneSignInCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="oneBeginRate" class="validate-tipRate" name="oneBeginRate" value="${oneSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="oneEndRate" class="validate-tipRate" name="oneEndRate" value="${oneSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">用户累计签到金额小于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" >
					<input id="twoEndAmount" class="validate-tipRate" name="twoEndAmount" value="${twoSignInCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="twoBeginRate" class="validate-tipRate" name="twoBeginRate" value="${twoSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="twoEndRate" class="validate-tipRate" name="twoEndRate" value="${twoSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">用户累计签到金额小于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" >
					<input id="threeEndAmount" class="validate-tipRate" name="threeEndAmount" value="${threeSignInCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="threeBeginRate" class="validate-tipRate" name="threeBeginRate" value="${threeSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="threeEndRate" class="validate-tipRate" name="threeEndRate" value="${threeSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">用户累计签到金额小于<span class="required">*</span></td>
            	<td align="left" class="l-table-edit-td" >
					<input id="fourEndAmount" class="validate-tipRate" name="fourEndAmount" value="${fourSignInCnfDtl.endAmount }" type="text" style="width:80px;" />
				</td>
            	<td class="title" width="20%">签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input id="fourBeginRate" class="validate-tipRate" name="fourBeginRate" value="${fourSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="fourEndRate" class="validate-tipRate" name="fourEndRate" value="${fourSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">其他情况签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="qtBeginRate" class="validate-tipRate" name="qtBeginRate" value="${qtSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="qtEndRate" class="validate-tipRate" name="qtEndRate" value="${qtSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">每日邀请好友上限人数<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="inviteLimit" class="validate-tipInviteLimit" name="inviteLimit" value="${signInCnf.inviteLimit }" type="text" style="width:80px;" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">首次好友签到获得金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="syqBeginRate" class="validate-tipRate" name="syqBeginRate" value="${syqSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="syqEndRate" class="validate-tipRate" name="syqEndRate" value="${syqSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">邀请新用户签到获金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="xyqBeginRate" class="validate-tipRate" name="xyqBeginRate" value="${xyqSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="xyqEndRate" class="validate-tipRate" name="xyqEndRate" value="${xyqSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">邀请老用户签到获金额比例（%）<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input id="lyqBeginRate" class="validate-tipRate" name="lyqBeginRate" value="${lyqSignInCnfDtl.beginRate }" type="text" style="width:80px;" />
					~
					<input id="lyqEndRate" class="validate-tipRate" name="lyqEndRate" value="${lyqSignInCnfDtl.endRate }" type="text" style="width:80px;" />
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" colspan="3" >
					<input class="l-button" type="button" value="开始计算" onclick="showResult();" />
					<input style="margin-left: 20px;" type="button" class="l-button" value="提交" onclick="submitFun();" /> 
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>