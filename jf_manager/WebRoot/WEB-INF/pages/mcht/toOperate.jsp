<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
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
	var submitting;
	$("#confirm").bind('click',function(){
		if(submitting){
			return;
		}
		var mchtId = $("#mchtId").val();
		var operateStatus = $("input[name='operateStatus']:checked").val();
		if(!operateStatus){
			commUtil.alertError("请选择运营开通");
			return;
		}
		var activityAuthStatus = $("input[name='activityAuthStatus']:checked").val();
		if(!activityAuthStatus){
			commUtil.alertError("请选择开通活动报名");
			return;
		}
		var shopStatus = $("input[name='shopStatus']:checked").val();
		if(!shopStatus){
			commUtil.alertError("请选择开通商城店铺");
			return;
		}
		var operateRemarks = $("#operateRemarks").val().trim();
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/operate.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtId":mchtId,"operateStatus":operateStatus,"activityAuthStatus":activityAuthStatus,"shopStatus":shopStatus,"operateRemarks":operateRemarks},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					submitting = false;
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					submitting = false;
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				submitting = false;
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="mchtId" value="${mchtId}">
		<table class="gridtable">
          	<tr>
          		<td class="title">运营开通<span style="color: red;">*</span></td>
          		<td>
          			<input type="radio" name="operateStatus" value="1" checked="checked">开通<input type="radio" name="operateStatus" value="0">驳回
          		</td>
          	</tr>
          	<tr>
          		<td class="title">开通活动报名<span style="color: red;">*</span></td>
          		<td>
          			<input type="radio" name="activityAuthStatus" value="1" checked="checked">开通<input type="radio" name="activityAuthStatus" value="0">不开通
          		</td>
          	</tr>
          	<tr>
          		<td class="title">开通商城店铺<span style="color: red;">*</span></td>
          		<td>
          			<input type="radio" name="shopStatus" value="1" checked="checked">开通<input type="radio" name="shopStatus" value="0">不开通
          		</td>
          	</tr>
          	<tr>
          		<td class="title">备注</td>
          		<td>
          			<textarea rows="15" cols="50" id="operateRemarks" name="operateRemarks"></textarea>
          		</td>
          	</tr>
	        <tr>
	          	<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
