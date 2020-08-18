<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/clipboard.min.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	$(function(){
		$('#submit').bind('click',function () {
			if($('#productIds').val() == ''){
				commUtil.alertError("商品ID不可为空");
				return;
			}
			var productIdList = $('#productIds').val().split("\n");
			var reg = /^[1-9]\d*$/;
			var tag = true;
			for(var i = 0;i<productIdList.length;i++){
				if(!reg.test(productIdList[i])){
					$.ligerDialog.error('输入商品ID不规范，请检查修改后再试');
					tag = false;
					break;
				}
			}
			if(!tag){
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/svipBindProduct/toAddProductSubmit.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form1").serializeArray(),
				timeout : 30000,
				success: function (data) {
					$.ligerDialog.success("成功导入"+data.idSuccess+ "个商品,商品ID错误"+data.idError+"个,未设置SVIP" + data.unSvip + "个");
					setTimeout(function(){window.parent.location.reload();},3000);
				},
				error: function () {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		})
	});
</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" id="form1">
		<table class="gridtable">
			<tr>
				<td class="title"><span style="color:red;">*</span>应用信息</td>
				<td><textarea rows="10" cols="35" id="productIds" name="productIds" style="resize: none;" placeholder="一行一个商品ID"></textarea></td>
			</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" class="l-button l-button-submit" id="submit" value="提交保存"/>
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>