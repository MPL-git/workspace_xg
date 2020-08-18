<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>公司信息</title>
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">税票信息管理</div>
		</div>
		<div class="x_content container-fluid sp-gl">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<table class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						<tbody>
							<tr>
								<td>公司名称</td>
								<td>${mchtTaxInvoiceInfo.companyName}</td>
							</tr>
							<tr>
								<td>纳税类型</td>
								<td>${mchtTaxInvoiceInfo.taxTypeDesc}</td>
							</tr>
							<tr>
								<td>纳税人识别号</td>
								<td>${mchtTaxInvoiceInfo.taxNumber}</td>
							</tr>
							<tr>
								<td>地址</td>
								<td>${mchtTaxInvoiceInfo.address}</td>
							</tr>
							<tr>
								<td>电话</td>
								<td>${mchtTaxInvoiceInfo.tel}</td>
							</tr>
							<tr>
								<td>开户行</td>
								<td>${mchtTaxInvoiceInfo.depositBank}</td>
							</tr>
							<tr>
								<td>账号</td>
								<td>${mchtTaxInvoiceInfo.accountNumber}</td>
							</tr>
							<tr>
								<td>状态</td>
								<td>
									${mchtTaxInvoiceInfo.auditStatusDesc}
									<c:if test="${mchtTaxInvoiceInfo.auditStatus=='3'}">
									<a href="javascript:mchtInfoChgApply()">【申请更新】</a>
									</c:if>
									<c:if test="${mchtTaxInvoiceInfo.id==null||mchtTaxInvoiceInfo.auditStatus=='0'||mchtTaxInvoiceInfo.auditStatus=='1'||mchtTaxInvoiceInfo.auditStatus=='4'}">
									<a href="javascript:mchtTaxInvoiceEdit()">【修改】</a>
									</c:if>
								</td>
							</tr>
							<c:if test="${mchtTaxInvoiceInfo.auditStatus=='4'}">
							<tr>
								<td>驳回原因</td>
								<td>${mchtTaxInvoiceInfo.auditRemarks}</td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
	    </div>
	</div>
	
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>


<script>

var isReload=false;
$(function(){
	$('#viewModal').on('hidden.bs.modal', function (e) {
		if(isReload){
			$.ajax({
		        url: "${ctx}/mchtTaxInvoice/mchtTaxInvoiceIndex", 
		        type: "GET", 
		        success: function(data){
		            $("#main-content").html(data);
		            isReload=false;
		        },
			    error:function(){
			    	swal('页面不存在');
			    }
			});
		}
		});
});


	function mchtTaxInvoiceEdit(){
		
		$.ajax({
	        url: "${ctx}/mchtTaxInvoice/mchtTaxInvoiceEdit", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}
	
	
	function mchtInfoChgApply(){
		getContent("${ctx}/mchtTaxInvoice/mchtTaxInvoiceChgIndex");
		
// 		$.ajax({
// 	        url: "${ctx}/mchtTaxInvoice/mchtTaxInvoiceChgApply", 
// 	        type: "GET", 
// 	        success: function(data){
// 	        	 $("#viewModal").html(data);
// 		         $("#viewModal").modal();
// 	        },
// 		    error:function(){
// 		    	swal('页面不存在');
// 		    }
// 		});
	}
	
	
	
</script>
</body>
</html>
