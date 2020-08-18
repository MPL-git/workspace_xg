<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>订单备注</title>
<style type="text/css">
.color-1{
color: #9D999D;
}
.color-2{
color: #FC0000;
}
.color-3{
color: #EFD104;
}
.color-4{
color: #00FC28;
}
.color-5{
color: #0351F7;
}
.color-6{
color: #DF00FB;
}

.p-c {
	position: absolute;
	top: 50%;
	left: 50%;
	height: 216px;
	margin: -108px 0 0 -300px;
}
</style>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">订单备注</span>
        <div style="display: inline-block;float: right;" id="remarksColors">
				    		<a href="javascript:;" name="remarksColor1" data-remarkscolor="1"><span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a>
				    		<a href="javascript:;" name="remarksColor1" data-remarkscolor="2"><span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor1" data-remarkscolor="3"><span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor1" data-remarkscolor="4"><span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor1" data-remarkscolor="5"><span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor1" data-remarkscolor="6"><span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>
					    	
				    </div>
      </div>
     <div class="modal-body">
     <div>
     	<textarea class="form-control" style="width: 100%;resize: vertical;margin-bottom: 10px;" rows="3" id="remarks1">${remarks}</textarea>
     </div>
	 <div>
		    <span id="selectedRemark1">
		    	旗帜设置为:
		    	<c:if test="${remarksColor eq '1'}">
		    		<span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '2'}">
		    		<span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '3'}">
		    		<span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '4'}">
		    		<span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '5'}">
		    		<span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '6'}">
		    		<span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>
		    	</c:if>
		    </span>
			<div class="modal-footer" id="remarksBtnDiv">
			   	<input type="hidden" id="subOrderIds" value="${ids}">
			   	<input type="hidden" id="remarksColor1" value="${remarksColor}">
			    <button type="button" class="btn btn-default" id="batchSaveRemarks">提交备注</button>
			</div>
	 </div>
    </div>
    </div>
  </div>
  
  <script type="text/javascript">
  $(function(){
	  $("a[name='remarksColor1']").on('click',function(){
			var remarksColor = $(this).data("remarkscolor");
			var remarksColorHtml = $(this).html();
			$("#selectedRemark1").html("旗帜设置为:"+remarksColorHtml);
			$("#remarksColor1").val(remarksColor);
		});
	  
	  	var submitting;
	  	$("#batchSaveRemarks").on('click',function(){
	  		if(submitting){
	  			return false;	
	  		}
	  		var subOrderIds = $("#subOrderIds").val();
	  		var remarks = $("#remarks1").val();
	  		var remarksColor = $("#remarksColor1").val();
	  		if(remarks && remarks.length > 256){
                swal("备注长度超过限制,请重新编辑");
                return false;
			}
	  		submitting = true;
	  		$.ajax({
	            method: 'POST',
	            url: '${ctx}/subOrder/remarksBatchSave',
	            data: {"subOrderIds":subOrderIds,"remarks":remarks,"remarksColor":remarksColor},
	            dataType: 'json'
	        }).done(function (result) {
	            if (result.returnCode =='0000') {
	            	table.ajax.reload( null, false );
	            	$("#remarksModal").modal('hide');
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
