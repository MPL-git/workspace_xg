<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>回复评价</title>
</head>

<body>
<!--回复评价 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    	<input type="hidden" id="commentId" value="${commentId}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title">回复评价</span>
      </div>
     <div class="modal-body">
     <div>
     	<textarea class="form-control" style="width: 100%;resize: vertical;margin-bottom: 10px;" rows="5" id="mchtReply" maxlength="200" placeholder="回复评价限制在1~200个字" ></textarea>
     </div>
     <div>
		<div class="modal-footer">
		    <button type="button" class="btn btn-default" id="save">确定</button>
		    <button class="btn btn-info" data-dismiss="modal">取消</button>
		</div>
	 </div>
    </div>
    </div>
  </div>
  
  <script type="text/javascript">
  $(function(){
	  	var submitting;
	  	$("#save").on('click',function(){
	  		if(submitting){
	  			return false;	
	  		}
	  		var commentId = $("#commentId").val();
	  		var mchtReply = $("#mchtReply").val();
	  		if(!mchtReply){
	  			swal("回复评价不能为空");
	  			return;
	  		}
	  		submitting = true;
	  		$.ajax({
	            method: 'POST',
	            url: '${ctx}/comment/replyComment',
	            data: {"commentId":commentId,"mchtReply":mchtReply},
	            dataType: 'json'
	        }).done(function (result) {
	            if (result.returnCode =='0000') {
	            	table.ajax.reload( null, false );
	            	$("#viewModal").modal('hide');
	            }else{
	            	swal("备注失败，请重试");
	            }
	            submitting = false;
	        });
	  	});
	  
	  
  });
	
	
	
	
	
  </script>
  
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
