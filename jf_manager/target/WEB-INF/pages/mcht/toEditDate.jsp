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
var submitting;
$(function(){
	
	$("#confirm").bind('click',function(){
		if(submitting){
			return;
		}
		var id = $("#id").val();
		var type = $("#type").val();
		var date = $("#date").val();
		if(!date){
			commUtil.alertError("有效期不能为空");
			return;
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/editDate.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"type":type,"date":date},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					submitting = false;
					commUtil.alertSuccess("提交成功");
					parent.location.reload();
				}else{
					submitting = false;
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
	<input type="hidden" id="id" value="${id}">
	<input type="hidden" id="type" value="${type}">
		<table class="gridtable">
          	<tr>
          		<td class="title">有效期</td>
          		<td>
          			<input type="text" id="date" name="date" value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>" />
	               	<script type="text/javascript">
						$(function(){
							$("#date").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>
          		</td>
          	</tr>
	        <tr>
	          	<td class="title">操作</td>
	          	<td>
					<input id="confirm" type="button" style="float:left;width: 70px;" class="l-button l-button-test" value="提交"/>
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 10px;" onclick="frameElement.dialog.close();" />
				</td>
	        </tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
