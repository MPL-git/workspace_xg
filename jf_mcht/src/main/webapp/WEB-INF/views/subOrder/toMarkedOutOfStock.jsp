<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>标记缺货商品</title>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
     	<span class="modal-title" >标记缺货商品</span>
      </div>
     <div class="modal-body">
	 <div>
	 	<input type="hidden" id="subOrderId" value="${subOrderId}">
		 <div class="col-md-12 datatable-container" >
		 	<table name="table" class="table table-striped table-bordered dataTable no-footer"
								role="grid" aria-describedby="datatable_info">
				<tr>
					<td>选择标识缺货商品</td>
					<td>
						<ul class="list-group">
							<c:forEach items="${orderDtlList}" var="orderDtl">
								<li class="list-group-item">
									<div style="display: inline-flex;">
										<input type="checkbox" name="orderDtlId" value="${orderDtl.id}">
										<div style="margin-top: 4px;text-align: left;width: 400px;">${orderDtl.brandName}&nbsp;&nbsp;${orderDtl.productName}&nbsp;&nbsp;${orderDtl.artNo}<br>${orderDtl.productPropDesc}&nbsp;&nbsp;<c:if test="${orderDtl.deliveryStatus eq 0}"><span style="color: red;">（未发货）</span></c:if><c:if test="${orderDtl.markedOutOfStock eq 1}"><span style="color: red;">（缺货）</span></c:if></div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</td>
				</tr>				
			</table>
		 </div>					
		<div class="modal-footer" >
			<button class="btn btn-info" data-dismiss="modal">取消</button>
		    <button type="button" class="btn btn-default" id="commit">提交</button>
		</div>
	 </div>
    </div>
    </div>
  </div>
  
  <script type="text/javascript">
  $(function(){
	  	var submitting;
	  	$("#commit").on('click',function(){
	  		if(submitting){
	  			return false;	
	  		}
	  		var orderDtlIds = "";
  			$("input[type='checkbox']:checked").each(function(){
  				if(!orderDtlIds){
  					orderDtlIds = $(this).val();
  				}else{
  					orderDtlIds += ","+$(this).val();
  				}
  			});
	  		submitting = true;
	  		$.ajax({
	            method: 'POST',
	            url: '${ctx}/subOrder/markedOutOfStock',
	            data: {subOrderId:$("#subOrderId").val(),orderDtlIds:orderDtlIds},
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
