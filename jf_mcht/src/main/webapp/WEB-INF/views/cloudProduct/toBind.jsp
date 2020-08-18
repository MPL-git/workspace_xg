<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>绑定供应商</title>
</head>

<body>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">绑定供应商</span>
      </div>
     <div class="modal-body" style="height: 120px;">
     	 <div class="form-group" style="margin-top: 15px;">
			<label for="productBrand" class="col-md-2 control-label search-lable" style="width: 100px;margin-top:-8px;">供应商用户名：</label>
			<div class="col-md-2 search-condition" >
				 <input class="form-control" type="text"  id="userName" name="userName" style="width: 150px;">
			</div>
			<div class="col-md-3 text-center search-btn">
				<button type="button" class="btn btn-info" id="bind">提交绑定</button>
			</div>
		 </div>
    </div>
    
    </div>
  </div>
  
<script type="text/javascript">
$(function(){
	var submitting;
	$("#bind").on('click',function(){
		if(submitting){
			return false;
		}
		var userName = $.trim($("#userName").val());
		if(!userName){
			swal("供应商用户名不能为空");
			return false;
		}
		submitting = true;
		$.ajax({
		        method: 'POST',
		        url: '${ctx}/cloudProduct/bind',
		        data: {"userName":userName},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	submitting = false;
		           	swal("绑定成功");
		           	$("#cloudProductViewModal").modal('hide');
		           	table.ajax.reload(null, false);
		        }else{
		        	if(result.returnMsg){
		        		swal(result.returnMsg);
		        	}else{
			        	swal("操作失败，请稍后重试");
		        	}
		        	submitting = false;
		        }
		    });
	});
});
</script>
</body>
</html>
