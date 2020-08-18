<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>签署合同步骤</title>
	<style type="text/css">
		body{
			font-size: 14px;
		}
		.hidden{
			display:none;
		}
		.row-body{
			padding:10px 20px;
		}
		.color-red{
			color:#f00;
		}
		.row_sub{
			padding:20px 45px;
		}
	</style>
</head>

<body>
<div class="x_panel container-fluid">
	<div class="row content-title">
		<div class="col-md-12">入驻及合同管理</div>
	</div>

	<div class="x_content container-fluid">
		<div class="row" style="padding:20px;">
			<div class="col-md-12">签署合同步骤</div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">第1步：<a href="javascript:showInfo('${ctx}/mcht/contract/contractPreview')">预览合同，打印合同</a></div>
		</div>
		<div class="row row-body">
			<div class="col-md-12">第2步：将合同盖公章<a href="javascript:showInfo('${ctx}/mcht/contract/contractPic')">【扫描上传】</a></div>
		</div>
		
		<div class="row row-body">
			<div class="col-md-12">
				<div class="row_sub color-red">
					合同审核状态：
						<c:if test="${contract.auditStatus == 1}">未上传</c:if>
						<c:if test="${contract.auditStatus == 2}">待审核</c:if>
						<c:if test="${contract.auditStatus == 3}">审核通过</c:if>
						<c:if test="${contract.auditStatus == 4}">
							驳回
							<br/>
							合同驳回原因：${contract.remarks}
						</c:if>
					<br>	
					<span style="color: gray;">
						备注：合同盖章要求：请将合同打印下来，用黑色签字笔在首页和尾页按要求填写相关信息后加盖公章，并盖骑缝章（保证章印清楚）；
						合同上传要求：扫描后按照页码顺序上传，保证图片清晰，内容不模糊<a href="javascript:;" onclick="toForExample();">【盖章示范】</a>
					</span>	
					<c:if test="${contract.auditStatus != 1}">
					<c:if test="${mchtInfo.depositType == 2}">
						<br>
						<span style="color: black;">缴纳信息</span><br>
						<span style="color: black;">请及时缴纳合同保证金：</span>${contractDeposit}<span style="color: black;">元，请汇入醒购指定的银行账号，缴纳完成请添加缴纳记录</span>
						<br>
						<img alt="" src="${ctx}/static/images/SKZH.jpg" style="width: 460px;height: 360px;"><br>
						汇款时请备注：${mchtInfo.mchtCode}保证金，并截图通知醒购招商专员<br>
						<a href="javascript:;" onclick="addDepositOrder()">【添加缴纳记录】</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="depositOrderIndex()">【缴纳记录】</a>
					</c:if>
					</c:if>
				</div>
			</div>
		</div>
		
		<div class="row row-body">
			<div class="col-md-12">第3步：财务确认银行及保证金信息</div>
			<div class="col-md-12">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				银行账号信息审核状态：
				<c:if test="${mchtBankAccount.status == 0}">待审</c:if>
				<c:if test="${mchtBankAccount.status == 1}">审核中</c:if>
				<c:if test="${mchtBankAccount.status == 2}">通过</c:if>
				<c:if test="${mchtBankAccount.status == 3}">
					驳回<a href="javascript:showInfo('${ctx}/mchtBankAccount/mchtBankAccountEdit?id=${mchtBankAccount.id}&isReload=1')">【修改】</a>
				</c:if>
			</div>
		</div>
		
		<c:if test="${contract.auditStatus == 3}">
		<div class="row row-body">
			<div class="col-md-12">
				第4步：商家寄合同及纸质的资质文件寄往醒购商城
				<div class="row_sub">
					<a href="javascript:showInfo('${ctx}/mcht/contract/contractExpressList')">【查看寄件内容】</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:showInfo('${ctx}/mcht/contract/contractExpressInfo')">【立即寄件】</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:showInfo('${ctx}/mcht/contract/fileArchive?mchtContractId=${contract.id}')">【文件归档情况】</a>
				</div>
				<div class="row_sub" style="color: gray;">备注：您可以在【查看寄件内容】中查看需寄往平台的合同及相关的纸质文件</div>
			</div>
		</div>
		</c:if>

	</div>
</div>


<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>

	$(document).ready(function () {

	});


	function showInfo(url){
		if(url == "")	return;
		$.ajax({
			url: url, 
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

	function viewContract(url){
		window.open(url);
	}

	function addDepositOrder(){
		$.ajax({
	        url: "${ctx}/mcht/contract/addDepositOrder", 
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
	
	function depositOrderIndex(){
		$.ajax({
	        url: "${ctx}/mcht/contract/depositOrderIndex", 
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

	function toForExample(){
		$.ajax({
	        url: "${ctx}/mcht/contract/toForExample", 
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
