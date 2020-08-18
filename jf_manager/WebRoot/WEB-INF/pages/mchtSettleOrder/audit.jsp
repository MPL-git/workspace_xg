<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.4.4.min.js"
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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	

<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	
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
table.gridtable td:nth-child(5) {
	position: relative;
    padding-bottom: 25px;
}
.l-table-edit-td {
	padding-bottom: 50px;
}
</style>
<script type="text/javascript">
$(function(){
	if($('#types').val() != ""){
		$("#type").find("option[value = '"+$('#types').val()+"']").attr("selected","selected");
	}
	
	if(${not empty popSettlePlan.periods}){
		$("#periods").val(${popSettlePlan.periods});
	}else{
		$("#periods").val('0');
	}
	
	$("#confirm").bind('click',function(){
		if($("#periods").val() == ""){
			commUtil.alertError("押几期不可为空");
			return;
		}
		var reg = /^[0-9]\d*$/;
		if(!reg.test($("#periods").val())){
			commUtil.alertError("请输入大于0的整数");
			return;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtSettleOrder/auditSubmit.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : $("#form1").serialize(),
			timeout : 30000,
			success : function(data) {
				if ("200" == data.statusCode) {
					$.ligerDialog.alert('提交成功！', function (yes) { 
					$(window.parent.document).find("#searchbtn").click();
					frameElement.dialog.close();});
				}else{
					$.ligerDialog.error(data.message);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});

</script>
<html>
<body>
	<form method="post" id="form1" name="form1">
	<div class="title-top">
	<input type="hidden" name="id" value="${popSettlePlan.id}">
	<input type="hidden" name="mchtId" value="${mchtId}">
	<input type="hidden" id="types" name="types" value="${popSettlePlan.type}">
		<table class="gridtable">
		    <tr>
          		<td class="title">结算类型<span style="color:red;">*</span></td>
          		<td>
					<select id="type" name="type" style="width:150px;">
					<option value="1">T+1</option>
					<option value="2">暂停</option>
					<option value="3">周期</option>
					<option value="4">重点</option>
					<option value="5">大商家</option>
					</select>
				</td>
          	</tr>
          	<tr>
          		<td class="title">押几期<span style="color:red;">*</span></td>
          		<td>
          			<input type="text" id="periods" name="periods" style="width:100px;">
          		</td>
          	</tr>
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="保存"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();"/>
				</td>
          	</tr>
        </table>
	</div>
	</form>
</body>
</html>
