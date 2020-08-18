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
	$("#confirm").bind('click',function(){
		var id = $("#mchtId").val();
		var remarks = $("#remarks").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/zsConfirm.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtId":id,"remarks":remarks},
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
});
</script>
<html>
<body>
	<div class="title-top">
	<input type="hidden" id="mchtId" value="${id}">
		<table class="gridtable">
          	<tr>
          		<td class="title">备注内容</td>
          		<td>
          			<c:if test="${empty remarks}">
          			<textarea rows="20" cols="80" id="remarks" name="remarks">主营类目：
佣金比例：
合作品牌：
每个品牌保证金：
总应收保证金：
各品牌的主要商品有哪些？或提供其他平台的店铺网址。</textarea>
					</c:if>
          			<c:if test="${not empty remarks}"><textarea rows="20" cols="80" id="remarks" name="remarks">${remarks}</textarea></c:if>
          		</td>
          	</tr>
          	<c:if test="${mchtInfo.totalAuditStatus!='2' && sessionScope.USER_SESSION.staffID==zsMchtPlatformContact.staffId}">
	          	<tr>
	          		<td class="title">操作</td>
	          		<td>
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="确定"/>
					</td>
	          	</tr>
          	</c:if>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
