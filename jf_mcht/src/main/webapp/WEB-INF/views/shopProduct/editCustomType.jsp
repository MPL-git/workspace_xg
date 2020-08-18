<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>分类编辑</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<style type="text/css">
.area{
	float: left;
	width: 30%;
}
</style>
</head>

<body>
<!--查看品牌 -->
<div class="modal-dialog wg-xx" role="document" style="width:500px;">
<div class="modal-content">
      <input type="hidden" id="id" value="${id}">
      <input type="hidden" id="ids" value="${ids}">
      <input type="hidden" id="actionType" value="${actionType}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">
        	<c:if test="${actionType == '1'}">
        		追加分类	
        	</c:if>
        	<c:if test="${actionType == '2'}">
        		更新分类	
        	</c:if>
        	<c:if test="${actionType == '3'}">
        		修改分类	
        	</c:if>
        </span>
      </div>
	  <div class="modal-body">
		<div style="margin-left: 20px;overflow: hidden;">
       			<c:if test="${actionType == '1'}">
       				<c:forEach items="${allShopProductCustomTypes}" var="shopProductCustomType">
       					<div class="area"><input type="checkbox" name="shopProductCustomType" value="${shopProductCustomType.id}">${shopProductCustomType.name}</div>
       				</c:forEach>
        		</c:if>
       			<c:if test="${actionType == '2'}">
       				<c:forEach items="${allShopProductCustomTypes}" var="shopProductCustomType">
       					<div class="area"><input type="checkbox" name="shopProductCustomType" value="${shopProductCustomType.id}">${shopProductCustomType.name}</div>
       				</c:forEach>
        		</c:if>
       			<c:if test="${actionType == '3'}">
       				<c:forEach items="${allShopProductCustomTypes}" var="shopProductCustomType">
       					<div class="area"><input type="checkbox" name="shopProductCustomType" value="${shopProductCustomType.id}" <c:forEach items="${shopProductCustomTypes}" var="oldShopProductCustomType"><c:if test="${oldShopProductCustomType.id eq shopProductCustomType.id}">checked="checked"</c:if></c:forEach>>${shopProductCustomType.name}</div>
       				</c:forEach>
        		</c:if>
			    <div class="modal-footer" style="padding: 10px 0 0;float: left;">
				<div style="display: inline-block;text-align: center;" id="btnDiv">
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button> 
					<button type="button" class="btn btn-default" style="margin-right: 17px;" id="confirm">提交</button>
				</div>
				</div>
		</div>			     	
	  </div>
</div>
</div>
<div class="modal fade" id="toComplainModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
  <script type="text/javascript">
  $(function(){
	  var submitting;
	  $("#confirm").on('click',function(){
		  if(submitting){
			  return false;
		  }
		  var actionType = $("#actionType").val();
		  var id = $("#id").val();
		  var ids = $("#ids").val();
		  var shopProductCustomTypeIds="";
		  $("input[name='shopProductCustomType']:checked").each(function(index){
			  var shopProductCustomTypeId = $(this).val();
			  if(index!=$("input[name='shopProductCustomType']:checked").length-1){
				  shopProductCustomTypeIds+=shopProductCustomTypeId+",";
			  }else{
				  shopProductCustomTypeIds+=shopProductCustomTypeId;
			  }
		  });
		  if(!shopProductCustomTypeIds){
			  swal("请选中要添加的分类");
			  return false;
		  }
		  submitting = true;
		  if(actionType == "1"){//1.追加分类
			  $.ajax({
		   			url : "${ctx}/shopProduct/appendCustomType",
		   			type : 'POST',
		   			dataType : 'json',
		   			cache : false,
		   			async : false,
		   			data : {ids:ids,shopProductCustomTypeIds:shopProductCustomTypeIds},
		   			timeout : 30000,
		   			success : function(data) {
		   				if (data.returnCode=="0000") {
		   					$("#viewModal").modal('hide');
		   					table.ajax.reload( null, false );
		   				} else {
		   					swal({
		   						  title: data.returnMsg,
		   						  type: "error",
		   						  confirmButtonText: "确定"
		   						});
		   				}
		   				submitting = false;
		   			},
		   			error : function() {
		   				swal({
		   					  title: "处理失败！",
		   					  type: "error",
		   					  timer: 1500,
		   					  confirmButtonText: "确定"
		   					});
		   				submitting = false;
		   			}
		   		});
		  }else if(actionType == "2" || actionType == "3"){//2.更新分类 ids 3.修改分类 id
			  if(!ids){//3.修改分类
				  ids = $("#id").val();
			  }
			  $.ajax({
		   			url : "${ctx}/shopProduct/updateCustomType",
		   			type : 'POST',
		   			dataType : 'json',
		   			cache : false,
		   			async : false,
		   			data : {ids:ids,shopProductCustomTypeIds:shopProductCustomTypeIds},
		   			timeout : 30000,
		   			success : function(data) {
		   				if (data.returnCode=="0000") {
		   					$("#viewModal").modal('hide');
		   					table.ajax.reload( null, false );
		   				} else {
		   					swal({
		   						  title: data.returnMsg,
		   						  type: "error",
		   						  confirmButtonText: "确定"
		   						});
		   				}
		   				submitting = false;
		   			},
		   			error : function() {
		   				swal({
		   					  title: "处理失败！",
		   					  type: "error",
		   					  timer: 1500,
		   					  confirmButtonText: "确定"
		   					});
		   				submitting = false;
		   			}
		   		});
		  }
		  
	  });
  });
  function toComplain(id){
		$.ajax({
			url: "${ctx}/violateOrder/toComplain?violateOrderId="+id, 
			type: "GET", 
			success: function(data){
				$("#toComplainModal").html(data);
				$("#toComplainModal").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});

	}
  
  </script>
</body>
</html>
