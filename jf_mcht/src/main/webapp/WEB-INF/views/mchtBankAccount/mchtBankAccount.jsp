<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>公司信息</title>
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">结算银行帐号管理</div>
		</div>
		<div class="x_content container-fluid sp-gl">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<table class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						<tbody>
							<tr>
								<td>账户类型</td>
								<td>${mchtBankAccount.accTypeDesc}</td>
							</tr>
							<tr>
								<td>账户名称</td>
								<td>${mchtBankAccount.accName}</td>
							</tr>
							<tr>
								<td>开户银行</td>
								<td>${mchtBankAccount.bankName}</td>
							</tr>
							<tr>
								<td>账户账号</td>
								<td>${mchtBankAccount.accNumber}</td>
							</tr>
							<tr>
								<td>开户支行名称</td>
								<td>${mchtBankAccount.depositBank}</td>
							</tr>
							<tr>
								<td>状态</td>
								<td>${mchtBankAccount.statusDesc}</td>
							</tr>
							<tr>
								<td>操作</td>
								<td>
							
									<c:if test="${empty mchtBankAccount.id||mchtBankAccount.status=='0'||mchtBankAccount.status=='2'||mchtBankAccount.status=='3'}">
									<a href="javascript:mchtBankAccountEdit()">【修改】</a>
									</c:if>
									<a href="javascript:viewChgLog()">【查看更新记录】</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
	    </div>
	</div>
	
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
	<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">
	
	</ul>

<script>


var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});

function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
	
}

var isReload=false;
$(function(){
	$('#viewModal').on('hidden.bs.modal', function (e) {
		if(isReload){
			$.ajax({
		        url: "${ctx}/mchtBankAccount/mchtBankAccountIndex", 
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


	function mchtBankAccountEdit(){
		
		$.ajax({
	        url: "${ctx}/mchtBankAccount/mchtBankAccountEdit?id=${mchtBankAccount.id}", 
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
	
	
	function viewChgLog(){
		getContent("${ctx}/mchtBankAccount/mchtBankAccountHisIndex");
		
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
