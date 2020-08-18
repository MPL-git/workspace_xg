<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>客服中心</title>
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">客服中心</div>
		</div>
		
		<div class="x_content container-fluid sp-gl">
			<div class="row">
				<br>
				<div class="panel panel-default">
					<c:if test="${not empty enterpriseNo}">
					<div class="panel-heading" style="background-color: #eff9ff;">
						<div style="font-size: 14px;font-weight: bold;">账户相关</div>
						<div>企业号：${enterpriseNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账户：admin&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：初始密码请关注<a href="javascript:;" onclick="getContent('/jf_mcht/platformMsg')">站内信</a>，登录后请及时修改密码</div> 
					</div>
					</c:if>	
					<div class="panel-body">
						<div>
							<img src="${ctx}/static/images/xn.png" style="margin-left: -7px;">
						</div>
						<div style="margin-top: 10px;margin-left: 30%">
							<a href="http://download.ntalker.com/install/t2ddown/Xiaoneng_6.93.1709071620.exe" class="btn btn-default" style="width: 156px;">下载醒购小能</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://p1.xgbuy.cc/download/xgxnkf.zip" class="btn btn-default">醒购小能使用帮助文档</a>
						</div>
					</div>
				</div>
			</div>
	    </div>
	</div>
	
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>


<script>
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
	
</script>
</body>
</html>
