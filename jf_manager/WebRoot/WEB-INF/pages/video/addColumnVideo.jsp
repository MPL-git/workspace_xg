<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}

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
.panel {
    margin-bottom: 14px;
    background-color: #fff;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.panel-default {
    border-color: #ddd;
}
.modal-header {
    height: 34px;
    line-height: 34px;
    padding: 0;
    border-bottom: 1px solid #ddd;
}
.modal-title {
    line-height: 34px;
    font-size: 12px;
    font-weight: bold;
    padding-left: 10px;
}
</style>

<script type="text/javascript">

$(function (){
	
	var submitting;
	$("#confirm").bind('click',function(){
		if(submitting){
			return false;
		}
		var id = $("#id").val();
		var name = $("#name").val();
		if(!name){
			commUtil.alertError("分类名称不能为空!");
			return false;
		}
		var productType1 = $("#productType1").val();
		if (productType1=='') {
			productType1='0';
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/video/toAddColumnVideo.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"name":name,"productType1":productType1},
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
				submitting = false;
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
	<form method="post" id="form" name="form" action="">
		<input type="hidden" id="id" value="${videoColumn.id}">
		<table class="gridtable">
			<tr>
               <td class="title">分类名称<span class="required">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="name" name="name" value="${videoColumn.name}" maxlength="10">
               		<span style="color: red;">字数最多10个字</span>
               </td>
			</tr>
           	<tr>
               <td class="title">绑定类目<span class="required">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType1" name="productType1" style="width: 150px;">
               		<option value="">全部类目</option>
               		<c:forEach items="${productTypes}" var="productType">
	               		<option value="${productType.id}" <c:if test="${productType.id == videoColumn.productType1Id}">selected="selected"</c:if>>${productType.name}</option>
               		</c:forEach>
               	</select>
               </td>
			</tr>
		 
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input id="confirm" type="button" style="float:left;" class="l-button l-button-submit"value="取消" onclick="frameElement.dialog.close()" />
					</div>
				</td>
	        </tr>
	        </table>
	</form>
</body>
</html>
