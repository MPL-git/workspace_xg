<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>添加分类</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
</head>

<body>

  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">添加分类</span>
      </div>
	<div class="modal-body">
		<form id="dataFrom">
			<div class="table-responsive">
				<table class="table table-bordered ">
					<tbody>
						<tr>
							<td class="editfield-label-td">分类名称<span class="required">*</span></td>
							<td colspan="2" class="text-left">
								<input type="text" id="name" name="name" maxlength="10">&nbsp;&nbsp;<span style="color: gray;">分类名称不超过10个字</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		
	<div class="modal-footer">
			<button class="btn btn-info" id="confirm">提交</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
		
			</div>
	</div>
  </div>
<script type="text/javascript">
$(function(){
	$("#name").on('keydown',function(){
		var theEvent = window.event || arguments.callee.caller.arguments[0];
		var code = theEvent.keyCode;
		if (code  == 13) {
	        return false; 
	    }
	});
	
	var submitting;
	$("#confirm").on('click',function(){
		if(submitting){
			return false;
		}
		var name = $.trim($("#name").val());
		if(!name){
			swal("分类名称不能为空");
			return false;
		}
		if(name.length>10){
			swal("分类名称长度不能超过10个字");
			return false;
		}
		submitting = true;
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/shopProductCustomType/saveShopProductCustomType',
	        data: {"name":name},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	table.ajax.reload();
				$("#viewModal").modal('hide');
				swal({
					  title: "提交成功!",
					  type: "success",
					  confirmButtonText: "确定"
					  
				});
	        }else{
	        	swal(result.returnMsg);
	        }
	        submitting = false;
	    });
	});
});
</script>
</body>
</html>
