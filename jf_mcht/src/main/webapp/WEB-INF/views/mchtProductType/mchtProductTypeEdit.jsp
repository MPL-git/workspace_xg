<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>添加类目</title>
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->


</head>

<body>

  <div class="modal-dialog" role="document" style="width:600px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">添加类目</span>
      </div>
			<div class="modal-body">
		<form id="dataFrom">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">一级类目</td>
						<td colspan="2" class="text-left">
					      <select  class="ad-select" name="productTypeId" id="productTypeId" validate="{required:true}">
						   <option value="">--请选择--</option>
						   <c:forEach var="productType" items="${productTypeList}">
						   <option value="${productType.id}">${productType.name}</option>
						   </c:forEach>
                          </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">二级类目</td>
						<td colspan="2" class="text-left">
						 <select  class="ad-select" id="productTypeId2">
						   <option value="">不限</option>
                          </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">三级类目</td>
						<td colspan="2" class="text-left">
						<select  class="ad-select" id="productTypeId3">
						   <option value="">不限</option>
                          </select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		
	<div class="modal-footer">
	
			<button class="btn btn-info" onclick="commitSave();">提交</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
		
			</div>
	</div>
  </div>




	
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
	
<script type="text/javascript">


var dataFromValidate;
$(function(){
	
	$("#productTypeId").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});	
	
	$.metadata.setType("attr", "validate");

	dataFromValidate =$("#dataFrom").validate({
        highlight : function(element) {  
        	$(element).addClass('error');
            $(element).closest('tr').find("td").first().addClass('has-error');  
        },
        success : function(label) {  
            label.closest('tr').find("td").first().removeClass('has-error');  
        }
	});
});





function commitSave(){

	if(dataFromValidate.form()){
		var dataJson = $("#dataFrom").serializeArray();
		$.ajax({
			url : "${ctx}/mchtProductType/mchtProductTypeSave",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					$("#viewModal").modal('hide');
					swal({
						  title: "保存成功!",
						  type: "success",
						  confirmButtonText: "确定"
						  
						});
					isReload=true;
					table.ajax.reload();
				} else {
					swal({
						  title: data.returnMsg,
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
				
			},
			error : function() {
				swal({
					  title: "提交失败！",
					  type: "error",
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
		});
	}
	
	
}

</script>
</body>
</html>
