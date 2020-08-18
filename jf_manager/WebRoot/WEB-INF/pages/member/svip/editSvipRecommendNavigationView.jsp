<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.activity-hint {
	margin-left: 20px;
	color: #6B6B6B;
}
</style>
<script type="text/javascript">
var reg = /^[1-9]\d*$/;
$(function(){
		$("#confirm").bind('click',function(){
			var id = $("#id").val();
			var name = $.trim($("#name").val());
			var remarks = $.trim($("#remarks").val());
			var keyword = $.trim($("#keyword").val());
			var seqNo = $.trim($("#seqNo").val());
			if(!name){
				commUtil.alertError("导航名称不能为空");
				return false;
			}
			if(name.length > 50){
				commUtil.alertError("导航名称最多可输入50个字");
				return false;
			}
			if(remarks && remarks.length > 256){
				commUtil.alertError("备注最多可输入256个字");
				return false;
			}
			if(keyword && keyword.length > 50){
				commUtil.alertError("关键字最多可输入50个字");
				return false;
			}
			if(seqNo && !reg.test(seqNo)){
				commUtil.alertError("排序只能是正整数");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/svipOrder/editSvipRecommendNavigation.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id,name:name,remarks:remarks,keyword:keyword,seqNo:seqNo},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
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
<form method="post" id="form" name="form" action="">
			<input type="hidden" id="id" name="id" value="${svipRecommendNavigation.id}">
			<table class="gridtable">
           	<tr>
               <td class="title">导航名称</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="name" name="name" value="${svipRecommendNavigation.name}" style="width: 500px;" />
               		<br><span class="activity-hint">注意：最多可输入50个字！</span>
               </td>
			</tr>
			<tr>
				<td class="title">关键字</td>
				<td colspan="2" align="left" class="l-table-edit-td">
					<input type="text" id="keyword" name="keyword" style="width: 500px;" <c:if test="${svipRecommendNavigation.id == 1}">disabled</c:if> value="${svipRecommendNavigation.keyword}" onafterpaste="this.value=this.value.replace(/，/g,',')" onkeyup="this.value=this.value.replace(/，/g,',') ">
					<br><span class="activity-hint">注意：最多可输入50个字,不同关键字使用逗号分割！</span>
				</td>
			</tr>
           	<tr>
               <td class="title">备注</td>
               <td colspan="2" align="left" class="l-table-edit-td">
				   <textarea rows=5 id="remarks" name="remarks" <c:if test="${svipRecommendNavigation.id == 1}">disabled</c:if> style="width: 500px" class="text">${svipRecommendNavigation.remarks}</textarea>
				   <br><span class="activity-hint">注意：最多可输入256个字！</span>
               </td>
			</tr>
           	<tr>
               <td class="title">排序</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="seqNo" name="seqNo" value="${svipRecommendNavigation.seqNo}" style="width: 500px;" />
				   <br><span class="activity-hint">注意：必须是正整数！</span>
               </td>
			</tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
