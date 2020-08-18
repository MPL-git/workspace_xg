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
	$(function(){
		$('#type').change(function() {
			var type = $(this).val();
			if(type == 1){
				$("#type_1_tr").attr("style","display: none");
				$("#type_2_tr").removeAttr("style");
			}else if(type == 2){
				$("#type_1_tr").removeAttr("style");
				$("#type_2_tr").attr("style","display: none");
			}else {
				$("#type_1_tr").attr("style","display: none");
				$("#type_2_tr").attr("style","display: none");
			}
		})

		/*$("#minAmount,#maxAmount,#integral").bind("input propertychange",function(event){
			if(!testNumber(event.target.value)){
				commUtil.alertError("输入正整数!");
				return false;
			}
		});*/

	})

	function clearNoNum(obj){
		obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
		obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一位小数
		if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
			obj.value= parseFloat(obj.value);
		}
		$("#span_"+obj.id).text(obj.value);
	}

	function testNumber(num){
		var reg = /^[1-9]\d*$/;
		return reg.test(num);
	}

	function commitSave(){
		var checkType = $('#type').val();
		if(!checkType){
			commUtil.alertError("未选择类型!");
			return false;
		}
		var json;
		if(checkType == 3){
			json = {id : $("#id").val(),type : checkType};
		}else if(checkType == 2){
			var minAmount = $("#minAmount").val();
			var maxAmount = $("#maxAmount").val();
			if(!minAmount || !maxAmount || !testNumber(minAmount) || !testNumber(maxAmount)){
				commUtil.alertError("请输入正整数!");
				return false;
			}
			if(Number(minAmount) >= Number(maxAmount)){
				commUtil.alertError("范围区间有误!");
				return false;
			}
			json = {id : $("#id").val(), type : checkType, minAmount : minAmount, maxAmount : maxAmount, winningProbability : $("#winningProbability_1").val()};
		}else if(checkType == 1){
			var integral = $("#integral").val();
			if(!integral || !testNumber(integral)){
				commUtil.alertError("请输入正整数!");
				return false;
			}
			json = {id : $("#id").val(),type : checkType, integral : integral, winningProbability : $("#winningProbability_2").val()};
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/updateLotterySettings.shtml',
			data: json,
			dataType: 'json',
			success: function (data) {
				if(data.statusCode != null && data.statusCode == "0000") {
					commUtil.alertSuccess("保存成功");
					parent.location.reload();
				}else {
					commUtil.alertError(data.message);
				}
			},
			error: function (e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1">
		<input type="hidden" id="id"  name="id" value="${lotterySettings.id}">
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">类型<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<select id="type" name="type" style="width: 160px" >
						<option value="">请选择</option>
						<option value="1" <c:if test="${lotterySettings.type == 1}">selected</c:if>>积分</option>
						<option value="2" <c:if test="${lotterySettings.type == 2}">selected</c:if>>优惠券</option>
						<option value="3" <c:if test="${lotterySettings.type == 3}">selected</c:if>>商品</option>
					</select>
				</td>
           	</tr>
			<tr id="type_1_tr" <c:if test="${lotterySettings.type == 3 || lotterySettings.type == 1}">style="display: none"</c:if>>
            	<td class="title" width="20%">详情<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<table>
						<tr>
							<td class="title" width="20%">优惠券面额范围</td>
							<td align="left" class="l-table-edit-td" >
								<input id="minAmount" style="width: 50px" value="<fmt:formatNumber type="number" value="${lotterySettings.minAmount}" maxFractionDigits="0"/>"> <= A < <input id="maxAmount" style="width: 50px" value="<fmt:formatNumber type="number" value="${lotterySettings.maxAmount}" maxFractionDigits="0"/>">(A代表优惠券)
							</td>
						</tr>
						<tr>
							<td class="title" width="20%">中奖概率</td>
							<td align="left" class="l-table-edit-td" >
								<input id="winningProbability_1" style="width: 50px" onkeyup="clearNoNum(this)" value="<fmt:formatNumber type="number" value="${lotterySettings.winningProbability * 1000}" pattern="0.00" maxFractionDigits="1"/>">&nbsp;&nbsp;&nbsp;<span id="span_winningProbability_1">0</span>/1000
							</td>
						</tr>
					</table>
				</td>
           	</tr>
			<tr id="type_2_tr" <c:if test="${lotterySettings.type == 3 || lotterySettings.type == 2}">style="display: none"</c:if>>
            	<td class="title" width="20%">详情<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<table>
						<tr>
							<td class="title" width="20%">积分数量</td>
							<td align="left" class="l-table-edit-td" >
								<input id="integral" value="${lotterySettings.integral}">(积分数量为正整数)
							</td>
						</tr>
						<tr>
							<td class="title" width="20%">中奖概率</td>
							<td align="left" class="l-table-edit-td" >
								<input id="winningProbability_2" style="width: 50px" onkeyup="clearNoNum(this)" value="<fmt:formatNumber type="number" value="${lotterySettings.winningProbability * 1000}" pattern="0.00" maxFractionDigits="1"/>">&nbsp;&nbsp;&nbsp;<span id="span_winningProbability_2">0</span>/1000
							</td>
						</tr>
					</table>
				</td>
           	</tr>

			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input name="btnSubmit" id="Button1" class="l-button l-button-submit" onclick="commitSave();" value="提交"/>
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>