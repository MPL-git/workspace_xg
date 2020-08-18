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
			<div class="t-title">用户信息</div>
		</div>
		
		<div class="x_content container-fluid sp-gl">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<table class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						<tbody>
							<tr>
								<td>商家序号</td>
								<td>${mchtInfo.mchtCode}</td>
							</tr>
							<tr>
								<td>用户名</td>
								<td>${userInfo.userCode}<a href="javascript:setUserInfo();">【设置帐号】</a></td>
							</tr>
							<tr>
								<td>绑定手机号</td>
								<td>
									${userInfo.mobile}
								</td>
							</tr>
							<tr>
								<td>绑定邮箱</td>
								<td>
									${userInfo.email}
								</td>
							</tr>
							<tr>
								<td>店铺名</td>
								<td>${mchtInfo.shopName}
								<c:if test='${mchtInfo.shopNameAuditStatus==null||mchtInfo.shopNameAuditStatus=="0"||mchtInfo.shopNameAuditStatus=="4"}'>
									<a href="javascript:changeShopInfo();">【修改】</a>
								</c:if>
								</td>
							</tr>
							<tr>
								<td>店铺名审核状态</td>
								<td>${shopNameAuditStatus}</td>
							</tr>
							<tr>
								<td>经营许可证</td>
								<td>
									<c:if test="${empty mchtLicenseChg}">
										<img src="${pageContext.request.contextPath}/file_servelt${mchtInfo.businessLicensePic}" style="width: 60px;height: 60px;">
									</c:if>
									<c:if test="${not empty mchtLicenseChg}">
										<img src="${pageContext.request.contextPath}/file_servelt${mchtLicenseChg.businessLicensePic}" style="width: 60px;height: 60px;">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>经营许可证审核状态</td>
								<td>
									<c:if test="${empty mchtLicenseChg}">
										<c:if test="${mchtInfo.licenseStatus eq 0}">
											未申请
										</c:if>
										<c:if test="${mchtInfo.licenseStatus eq 1}">
											待审
										</c:if>
										<c:if test="${mchtInfo.licenseStatus eq 2}">
											已通过
										</c:if>
										<c:if test="${mchtInfo.licenseStatus eq 3}">
											驳回
										</c:if>
									</c:if>
									<c:if test="${not empty mchtLicenseChg}">
										<c:if test="${mchtLicenseChg.auditStatus eq 0}">
											待审
										</c:if>
										<c:if test="${mchtLicenseChg.auditStatus eq 1}">
											已通过
										</c:if>
										<c:if test="${mchtLicenseChg.auditStatus eq 2}">
											驳回
										</c:if>
									</c:if>
									<a href="javascript:;" onclick="mchtLicenseChgList(${mchtInfo.id});">【申请更新】</a>
								</td>
							</tr>
							<c:if test="${mchtInfo.shopNameAuditStatus==4}">
							<tr>
								<td>驳回原因</td>
								<td>${mchtInfo.shopNameAuditRemarks}</td>
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
			        url: "${ctx}/mchtUser/mchtUserIndex", 
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
	
	
	function mchtLicenseChgList(id){
		var mchtId = id;
		getContent("${ctx}/mchtUser/mchtLicenseChgList?mchtId="+mchtId);
		/* $.ajax({
	        url: "${ctx}/mchtUser/mchtLicenseChgList?mchtId="+mchtId, 
	        type: "GET", 
	        success: function(data){
	        	$("#main-content").html(data);
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		}); */
	}
	
	function toBindMobile(){
		
		$.ajax({
	        url: "${ctx}/mchtUser/toBindMobile", 
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
	function changeShopInfo(){
		
		$.ajax({
	        url: "${ctx}/mchtUser/changeShopInfo", 
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
	
	
	function setUserInfo(){
		
		$.ajax({
	        url: "${ctx}/mchtUser/setUserInfo", 
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
	
	
	function toValidateMobile(){
		
		$.ajax({
	        url: "${ctx}/mchtUser/toValidateMobile", 
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
	
	function toSetProtect(){
		
		$.ajax({
	        url: "${ctx}/mchtUser/toSetProtect", 
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
	
	function changShopInfo(){
		
		$.ajax({
	        url: "${ctx}/mchtUser/toSetProtect", 
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
	
	function toChangePassword(){
		
		$.ajax({
	        url: "${ctx}/mchtUser/toChangePassword", 
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
	
	function toMchtLicenseChg(mchtId){
		$.ajax({
	        url: "${ctx}/mchtUser/toMchtLicenseChg?id="+mchtId, 
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
	
</script>
</body>
</html>
