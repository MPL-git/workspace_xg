<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
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
$(function(){
	$("#confirm").bind('click',function(){
		var mchtId = $("#mchtId").val();
		var depositType = $("input[name='depositType']:checked").val();
		if(!depositType){
			commUtil.alertError("请选择合作模式");
			return;
		}
		var mchtType = $("input[name='mchtType']:checked").val();
		if(!mchtType){
			commUtil.alertError("请选择商家类型");
			return;
		}
		var contractDeposit = $("#contractDeposit").val();
		if(!contractDeposit){
			commUtil.alertError("店铺保证金不能为空");
			return;
		}
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		if(!reg.test(contractDeposit)){
			commUtil.alertError("请输入正确格式的店铺保证金");
			return false;
		}
		if(contractDeposit.length>9){
			commUtil.alertError("店铺保证金不能超过9位数字");
			return false;
		}
		var isManageSelf = $("input[name='isManageSelf']:checked").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/saveContractDeposit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtId":mchtId,"mchtType":mchtType,"depositType":depositType,"contractDeposit":contractDeposit,"isManageSelf":isManageSelf},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					commUtil.alertSuccess("提交成功");
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

	showManageSelf();
});

function showManageSelf(){

	var mchtType=$("input[name='mchtType']:checked").val();
	if(mchtType == '1'){
		$("#showIsManageSelf").show();
	}
	if (mchtType=='2') {
		$("#showIsManageSelf").hide();
	}
}
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="mchtId" value="${mchtId}">
		<table class="gridtable">
			<tr>
				<td class="title">合作模式</td>
				<td>
					<input name="mchtType" type="radio" value="2" <c:if test="${mchtInfo.mchtType == 2}">checked="checked"</c:if>  onclick="showManageSelf()">POP
					<input name="mchtType" type="radio" value="1" <c:if test="${mchtInfo.mchtType == 1}">checked="checked"</c:if>  onclick="showManageSelf()">SPOP
				</td>
			</tr>

			<tr id="showIsManageSelf"  style="display:none;">
				<td class="title">是否自营</td>
				<td>
					<label><input type="radio" name="isManageSelf" value="0" <c:if test="${mchtInfo.isManageSelf eq '0'}">checked="checked"</c:if>>非自营</label>
					<label><input type="radio" name="isManageSelf" value="1" <c:if test="${mchtInfo.isManageSelf eq '1'}">checked="checked"</c:if>>自营</label>
				</td>
			</tr>

          	<tr>
          		<td class="title">保证金缴费类型</td>
          		<td>
	          		<input name="depositType" type="radio" value="1" <c:if test="${depositType == 1}">checked="checked"</c:if>>可货款抵缴
	          		<input name="depositType" type="radio" value="2" <c:if test="${depositType == 2}">checked="checked"</c:if>>不可货款抵缴
          		</td>
          	</tr>
          	<tr>
          		<td class="title">保证金金额</td>
          		<td><input id="contractDeposit" name="contractDeposit" value="${contractDeposit}"></td>
          	</tr>
	        <tr>
	          	<td class="title">操作</td>
	          	<td>
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="提交"/>
				</td>
	        </tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
