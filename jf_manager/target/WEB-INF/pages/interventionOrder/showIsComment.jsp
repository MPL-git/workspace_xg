<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.title-width{width: 20%}
.marL20{margin-left:20px;}
</style>
<script type="text/javascript">
	
function submitFun(interventionOrderId) {
	var commentType = $("[name='commentType']:checked").val();
	var content = $("#content").val();
	$.ajax({
		type: 'post',
		url: '${pageContext.request.contextPath}/interventionOrder/saveClientServiceComment.shtml',
		data: {interventionOrderId:interventionOrderId, commentType:commentType, content:content},
		dataType: 'json',
		success: function(data) {
			if(data == null || data.code != 200) {
				commUtil.alertError(data.msg);
			}else {
				$.ligerDialog.success(data.msg, function() {
					parent.updateIsCommentFun(interventionOrderId);
            	 	frameElement.dialog.close();
				});	 
			}
		},
		error: function(e) {
			commUtil.alertError('操作超时，请稍后再试！');
		}
	});
}
	
</script>
<html>
<body>
	<div class="title-top" >
		<div class="marT10">
			<span class="marL20"><input type="radio" value="1" name="commentType" >差评</span>
			<span class="marL20"><input type="radio" value="2" name="commentType" checked="checked" >一般</span>
			<span class="marL20"><input type="radio" value="3" name="commentType" >满意</span>
			<span class="marL20"><input type="radio" value="4" name="commentType" >非常满意</span>
		</div>
	</div>
	<div class="title-top">
		<div class="table-title">
			<span>评价服务</span>
		</div>
		<table class="gridtable marT10 status-table">
           	<tr>
               <td>
               		<textarea rows="5" cols="100" style="height: 150px; width: 100%;" id="content" name="content" ></textarea>
               </td>
			</tr>
        </table>
	</div>
	<div style="margin: 20 auto; text-align: center">
		<button style="cursor: pointer;width: 150px;height: 35px;background-color: rgba(22, 155, 213, 1);border: none;color: white;" onclick="submitFun(${interventionOrderId });" >
			提交
		</button>
	</div>

</body>
</html>
