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

.video_box {
    background: #fff;
    z-index: 2222;
    display: block;
}

.black_box {
    background: #000;
    opacity: 0.6;
    left: 0;
    top: 0;
    z-index: 1111;
    position: fixed;
    height: 100%;
    width: 100%;
}
.video_close {
    position: absolute;
    top: -14px;
    right: -12px;
}
</style>
<script type="text/javascript">
$(function(){
	$("#confirm").bind('click',function(){
		var id = $("#id").val();
		var archiveStatus = $("input[name='archiveStatus']:checked").val();
		if(!archiveStatus){
			commUtil.alertError("请选择归档确认结果");
			return false;
		}
		var archiveRemarks = $("#archiveRemarks").val().trim();
		if(!archiveRemarks && archiveStatus == 2){
			commUtil.alertError("驳回理由不能为空");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/cooperationChangeApply/archive.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"archiveStatus":archiveStatus,"archiveRemarks":archiveRemarks},
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
	<input type="hidden" id="id" value="${cooperationChangeApply.id}">
	<table class="gridtable">
		<tr>
         		<td colspan="1">归档确认</td>
         		<td>
         			<input type="radio" name="archiveStatus" value="1" <c:if test="${cooperationChangeApply.archiveStatus eq 1}">checked="checked"</c:if>>通过（已归档）
         			<input type="radio" name="archiveStatus" value="2" <c:if test="${cooperationChangeApply.archiveStatus eq 2}">checked="checked"</c:if>>归档驳回
         		</td>
         	</tr>
         	<tr>
         		<td colspan="1">备注/驳回理由<span style="color: red;">*</span></td>
         		<td>
         			<textarea rows="10" cols="80" id="archiveRemarks" name="archiveRemarks" maxlength="256"></textarea>
         		</td>
         	</tr>
         	<tr>
          	<td colspan="1">操作</td>
			<td align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
				</div>
			</td>
     	</tr>
	</table>
	</div>
	
<!--弹出div End-->
<div class="black_box" style="display: none;"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
