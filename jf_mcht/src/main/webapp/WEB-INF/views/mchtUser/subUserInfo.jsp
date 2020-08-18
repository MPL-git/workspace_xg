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
			<div class="col-md-12 ">用户信息</div>
		</div>
		
		<div>
			<div class="info-row">
				<span class="info-title">用户名&nbsp;:</span>
				<span  class="info-cotent">${userInfo.userCode}</span>
				<a href="javascript:toChangePassword()" >【修改密码】</a>
			</div>
			<div class="info-row"> <span class="info-title">角色名&nbsp;:</span><span  class="info-cotent">${roleInfo.roleName}</span></div>
			<div class="info-row"> <span class="info-title">店铺名&nbsp;:</span><span  class="info-cotent">${mchtInfo.shopName}</span></div>
			<div class="info-row"> <span class="info-title">公司名称&nbsp;:</span><span  class="info-cotent">${mchtInfo.companyName}</span></div>
			<div class="info-row">
				<span class="info-title">合作状态&nbsp;:</span>
				<span  class="info-cotent">${mchtInfo.statusDesc}</span>
			</div>
			<div class="info-row"> <span class="info-title">合作开始日期&nbsp;:</span><span  class="info-cotent"><fmt:formatDate value="${mchtInfo.cooperateBeginDate}" pattern="yyyy-MM-dd"/></span></div>
			<div class="info-row"> <span class="info-title">最新合同开始&nbsp;:</span><span  class="info-cotent"><fmt:formatDate value="${mchtInfo.agreementBeginDate}" pattern="yyyy-MM-dd"/></span></div>
			<div class="info-row"> <span class="info-title">最新合同到期&nbsp;:</span><span  class="info-cotent"><fmt:formatDate value="${mchtInfo.agreementEndDate}" pattern="yyyy-MM-dd"/></span></div>
    	</div>

	</div>

<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>


<script>

	function toChangePassword(){
		
		$.ajax({
	        url: "${ctx}/subAccount/modifyPassword",
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
