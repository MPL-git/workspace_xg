<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
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
var viewerPictures;
$(function(){
	$("#confirm").bind('click',function(){
		var id = $("#id").val();
		var status =$("input[name='archiveDealStatus']:checked").val();
		var archiveDealRemarks = $("#archiveDealRemarks").val();
		var archiveDealInnerRemarks = $("#archiveDealInnerRemarks").val();

		//审核状态
		if(status == undefined){	
			commUtil.alertError("审核状态不可为空");
			return;
		}
		//驳回原因
		if(archiveDealRemarks == '' && status == 2){
			commUtil.alertError("驳回原因不可为空");
			return;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/mchtBrandChgHandleArchiveUpdate.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : $("#form1").serialize(),
			contentType: "application/x-www-form-urlencoded",
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.alert('提交成功！', function (yes) { 
					$(window.parent.document).find("#searchbtn").click();
					frameElement.dialog.close();});		
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
<body>
	<form method="post" id="form1" name="form1">
	<div class="title-top">
	<input type="hidden" id="id" name="id" value="${mchtBrandChg.id}">
		<table class="gridtable">
          	<tr>
          		<td class="title">审核状态<span style="color:red;">*</span></td>
          		<td><input type="radio" name="archiveDealStatus" value="1" <c:if test="${mchtBrandChg.archiveDealStatus=='1'}">checked="checked"</c:if>>通过
          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<input type="radio" name="archiveDealStatus" value="2" <c:if test="${mchtBrandChg.archiveDealStatus=='2'}">checked="checked"</c:if>>驳回
          		</td>
          	</tr>
          	<tr>
          		<td class="title">驳回原因</td>
          		<td><textarea style="resize:none;" rows="7" cols="60" id="archiveDealRemarks" name="archiveDealRemarks" maxlength="256">${mchtBrandChg.archiveDealRemarks}</textarea></td>
          	</tr>
          		<tr>
          		<td class="title">内部备注</td>
          		<td><textarea style="resize:none;" rows="7" cols="60" id="archiveDealInnerRemarks" name="archiveDealInnerRemarks" maxlength="256">${mchtBrandChg.archiveDealInnerRemarks}</textarea></td>
          	</tr>      	
          	<tr>
          		<td class="title">操作</td>
          		<td>
					<input id="confirm" type="button" style="float:left;" class="l-button l-button-test" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="关闭" class="l-button l-button-test" onclick="frameElement.dialog.close();"/>
				</td>
          	</tr>
        </table>
	</div>
	</form>
</body>
</html>
