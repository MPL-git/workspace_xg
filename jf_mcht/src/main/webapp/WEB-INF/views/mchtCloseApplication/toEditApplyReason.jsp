<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>修改申请理由</title>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <input type="hidden" id="id" value="${id}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">修改申请理由</span>
      </div>
     <div class="modal-body">
     	<div>
			申请理由：
			<textarea class="form-control"
				style="width: 100%;resize: vertical;margin-bottom: 10px;" rows="10"
				id="applyReason" name="applyReason" maxlength="256"></textarea>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" id="confirm">提交</button>
		</div>
     </div>
    
    </div>
  </div>
  
<script type="text/javascript">
$(function(){
	var submitting;
	$("#confirm").on('click',function(){
		if(submitting){
			return false;
		}
		var id = $("#id").val();
		var applyReason = $("#applyReason").val();
		if(!applyReason){
			swal("申请理由不能为空");
			return false;
		}
		submitting = true;
		$.ajax({
		        method: 'POST',
		        url: '${ctx}/mchtCloseApplication/editApplyReason',
		        data: {"id":id,"applyReason":applyReason},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	submitting = false;
		           	swal("操作成功");
		           	$("#viewModal").modal('hide');
		           	$(".modal-backdrop").hide();
		           	var url = "${ctx}/mchtCloseApplication/index";
		            getContent(url);
		        }else{
		        	swal("操作失败，请稍后重试");
		        	submitting = false;
		        }
		    });
	});
});
</script>
</body>
</html>
