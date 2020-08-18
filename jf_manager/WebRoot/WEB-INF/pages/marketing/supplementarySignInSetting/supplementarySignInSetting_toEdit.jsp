<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
		$("#save").bind('click',function(){
			var invitationCount = $.trim($("#invitationCount").val());
			var supplementaryCardCount = $.trim($("#supplementaryCardCount").val());
			if(!invitationCount){
				commUtil.alertError("邀请人数不能为空");
				return false;
			}
			if(!supplementaryCardCount){
				commUtil.alertError("获得补签卡数量不能为空");
				return false;
			}
			if(!testNumber(invitationCount)){
				commUtil.alertError("邀请人数请输入正整数");
				return false;
			}
			if(!testNumber(supplementaryCardCount)){
				commUtil.alertError("获得补签卡数量请输入正整数");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/marketing/supplementarySignInSetting/save.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"invitationCount":invitationCount,supplementaryCardCount:supplementaryCardCount},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("保存成功");
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
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/marketing/supplementarySignInSetting/save.shtml">
			<table class="gridtable">
           	<tr>
               <td class="title">邀请人数<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<input type="text" id="invitationCount" name="invitationCount" >
               </td>
			</tr>
	        <tr>
               <td class="title">获得补签卡数量<span style="color: red;">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<input type="text" id="supplementaryCardCount" name="supplementaryCardCount" >
               </td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="保存"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
