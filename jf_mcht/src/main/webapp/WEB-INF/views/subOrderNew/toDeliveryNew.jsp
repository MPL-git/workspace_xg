<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>立即发货</title>
<style type="text/css">
.color-5{
color: #0351F7;
}
.more {
	position: relative;
	color: blue;
	cursor: pointer;
}
.more:hover:after {
	z-index: 999;
	color: white;
	position: absolute;
	top: calc(100% + 10px);
	left: 50%;
	content: attr(data-more);
	padding: 2px 10px;
	background: rgba(0, 0, 0, .5);
	border-radius: 5px;
	word-break: keep-all;
	transform: translateX(-50%);
}
.more:before {
	z-index: 999;
	display: none;
	position: absolute;
	top: 100%;
	left: 0;
	right: 0;
	content: '';
	width: 0;
	margin: 0 auto;
	border-bottom: 10px solid rgba(0, 0, 0, .5);
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
}
.more:hover:before {
	display: block;
}
.more:hover:after {
    position: absoulte;
    left:200px;
	display: block;
	width:450px;
	z-index:999;
}
.shopnum {
	width: 100px;
	display:inline-block;
}
</style>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
     	<span class="modal-title" >立即发货</span>
      </div>
     <div class="modal-body">
	 <div>
	 	<input type="hidden" id="orderDtlId" value="${orderDtlId}">
		 <div class="col-md-12 datatable-container" id="deliveryDiv">
		 	<table name="table" class="table table-striped table-bordered dataTable no-footer"
								role="grid" aria-describedby="datatable_info">
				<tr>
					<td>快递公司<span class="required">*</span></td>
					<td>
						<select class="form-control" name="expressId">
							<option value="">--请选择--</option>
							<c:forEach var="express" items="${expressList}">
								<c:if test="${express.id eq orderDtlList[0].dtlExpressId}">
								<option value="${express.id}" selected = "selected">${express.name}</option>
								</c:if>
								<c:if test="${express.id ne orderDtlList[0].dtlExpressId}">
								<option value="${express.id}">${express.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>				
				<tr>
					<td>快递单号<span class="required">*</span></td>
					<td>
						<input type="text" class="form-control" name="expressNo" style="width: 200px;">
					</td>
				</tr>	
				<tr>
					<td >
						<span style="white-space: nowrap;">快递商户编号</span>
						<span class="more" data-more="什么是快递商户编号：快递公司的配送运营人员在物流系统给商家录入的唯一身份标识">
							<span  class="glyphicon glyphicon-question-sign color-5" aria-hidden="true"></span>
						</span>
					</td>
					<td>
						<input placeholder="若您选择则的快递公司为京东,请务必填写快递商户编号" type="text" class="form-control" name="merchantCode" style="width:100%">
					</td>
				</tr>				
				<tr>
					<td style="white-space: nowrap;">发货商品信息<span class="required">*</span></td>
					<td>
						<ul class="list-group">
							<c:forEach items="${orderDtlList}" var="orderDtl">
								<li class="list-group-item">
									<div style="display: inline-flex;">
										<input type="checkbox" name="orderDtlId" value="${orderDtl.id}" <c:if test="${orderDtl.markedOutOfStock eq 0}"><c:if test="${orderDtl.deliveryStatus eq 0}">checked="checked"</c:if></c:if>>
										<div style="margin-top: 4px;text-align: left;width: 400px;">
											${orderDtl.brandName}&nbsp;&nbsp;${orderDtl.productName}&nbsp;&nbsp;${orderDtl.artNo}<br>
											${orderDtl.productPropDesc}&nbsp;&nbsp;
											<c:if test="${orderDtl.markedOutOfStock eq 1}"><span style="color: red;">（缺货）</span></c:if>
											<c:if test="${orderDtl.markedOutOfStock eq 0}">
												<c:if test="${orderDtl.deliveryStatus eq 0}"><span style="color: red;">（未发货）</span></c:if>
											</c:if>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</td>
				</tr>				
			</table>
		 </div>					
		<span style="margin-left: 10px;"><a href="javascript:;" id="addNewDelivery">新增发货信息</a></span>
		<div class="modal-footer" id="remarksBtnDiv">
		    <button type="button" class="btn btn-default" id="commit">提交</button>
		</div>
	 </div>
    </div>
    </div>
  </div>
  
  <script type="text/javascript">
  $(function(){
	  $("#addNewDelivery").on('click',function(){
		  var $html = $("table[name='table']").last().clone();
		  var total = $html.find("li").length;
		  $html.find("input[type='checkbox']:checked").closest("li").remove();
		  var length = $html.find("li").length;
		  var checkedLength = $html.find("input[type='checkbox']:checked").length;
		  $html.find("input[name='expressNo']").first().val("");
		  $html.find("input[type='checkbox']").each(function(){
			  if($(this).next().find("span").text() == "（未发货）"){
				  $(this).prop("checked",true);
			  }
		  });
		  if(length == 0 || total == 1){
			  swal("当前已无可发货的商品");
		  }else{
			  if(checkedLength == 0 && length == total){
				  swal("当前已无可发货的商品");
			  }else{
				  $("table[name='table']").last().find("input[type='checkbox']").each(function(){
					  $(this).attr("disabled","disabled");
				  });
				  $("#deliveryDiv").append($html);
			  }
		  }
	  });
	  
	  
	  	var submitting;
	  	$("#commit").on('click',function(){

	  		if(submitting){
	  			return false;	
	  		}
	  		var array = [];
	  		var error = false;
	  		$("table[name='table']").each(function(){
	  			var expressId = $(this).find("select[name='expressId']").first().val();
	  			if(!expressId){
	  				error = true;
	  				swal("请选择快递公司");
	  				return;
	  			}
	  			var expressNo = $(this).find("input[name='expressNo']").first().val();
	  			if(!expressNo){
	  				error = true;
	  				swal("请输入快递单号");
	  				return;
	  			}
	  
	  			var merchantCode = $(this).find("input[name='merchantCode']").first().val();
	  			if(!merchantCode&&expressId=='20'){
	  				error = true;
	  				swal("请填写快递商户编号");
	  				return;
	  			}
	  			if(merchantCode.length>16){
	  				error = true;
	  				swal("您填写快递商户编号太长");
	  				return;
	  			}
	  			var orderDtlIds = "";
	  			$(this).find("input[type='checkbox']:checked").each(function(){
	  				if(!orderDtlIds){
	  					orderDtlIds = $(this).val();
	  				}else{
	  					orderDtlIds += ","+$(this).val();
	  				}
	  			});
	  			if(!orderDtlIds){
	  				error = true;
	  				swal("请选择要发货的商品");
	  				return;
	  			}
	  			array.push({
	  				expressId:expressId,
	  				expressNo:expressNo,
	  				merchantCode:merchantCode,
	  				orderDtlIds:orderDtlIds
	  			});
	  		});
	  		if(error){
  				return;
	  		}
	  		submitting = true;
	  		$.ajax({
	            method: 'POST',
	            url: '${ctx}/subOrderNew/subOrderDeliveryNew',
	            data: {orderDtlId:$("#orderDtlId").val(),jsonStr:JSON.stringify(array)},
	            dataType: 'json'
	        }).done(function (result) {
	            if (result.returnCode =='0000') {
	            	table.ajax.reload( null, false );
	            	$("#viewModal").modal('hide');
	            }else{
	            	if(result.returnMsg){
		            	swal(result.returnMsg);
	            	}else{
		            	swal("操作失败，请稍后重试");
	            	}
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
