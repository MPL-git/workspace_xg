<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	

<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	

<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}
table.gridtable td:nth-child(5) {
    padding-bottom: 25px;
}
.l-table-edit-td {
	padding-bottom: 50px;
}
</style>

<script type="text/javascript">
var viewerPictures;
var viewerPictures1;
$(function(){
	idcarPicViewer = new Viewer(document.getElementById('viewer-pictures'), {});
	idcarPicViewer1 = new Viewer(document.getElementById('viewer-pictures1'), {});
});

</script>
</head>
<body>
	<form method="post" id="form1" name="form1">
	<div class="title-top">
		<table class="gridtable">
          	<tr>
          		<td class="title">会员ID：</td>
          		<td>${wthdrawOrder.memberId}</td>
          	</tr>
          	<tr>
          		<td class="title">会员昵称：</td>
          		<td>${wthdrawOrder.nick}</td>
          	</tr>
          	<tr>
          		<td class="title">提现前余额：</td>
          		<td><c:if test="${wthdrawOrder.novaBalance == null}">0.00</c:if>
          			<c:if test="${wthdrawOrder.novaBalance != null}">${wthdrawOrder.novaBalance}</c:if>
          		</td>
          	</tr>
          	
          	<c:if test="${pageType=='1'}">
          		<tr>
				<td class="title">提现余额：</td>
				<td><c:if test="${wthdrawOrder.amount == null}">0.00</c:if>
          			<c:if test="${wthdrawOrder.amount != null}">${wthdrawOrder.amount}</c:if>
          		</td>
			</tr>
          	<tr>
				<td class="title">账户名：</td>
				<td>${wthdrawOrder.realName}</td>
			</tr>
			<tr>
				<td class="title">银行卡号：</td>
				<td>${wthdrawOrder.alipayAccount}</td>
			</tr>
			<tr>
				<td class="title">开户行：</td>
				<td>${wthdrawOrder.branchName}</td>
			</tr>
			<tr>
				<td class="title">省份/城市：</td>
				<td>${wthdrawOrder.provinceAreaName}/${wthdrawOrder.cityAreaName}</td>
			</tr>
          	</c:if>
			
          	<tr>
				<td class="title">申请时间：</td>
				<td>${createDate}</td>
			</tr>
        	<tr>
				<td class="title">审核信息：</td>
				<td>
					<table class="gridtable">
          				<tr align="center">
							<td class="title">审核結果</td>
							<td class="title">驳回理由</td>
							<td class="title">内部备注</td>
							<td class="title">图片</td>
							<td class="title">审核人</td>
							<td class="title">审核时间</td>
						</tr>
				
          				<tr>
          					<td align="center" width="100px;"><c:if test="${wthdrawOrder.yyAuditStatus == '0'}">审核不通过</c:if>
          						<c:if test="${wthdrawOrder.yyAuditStatus == '1'}">审核通过</c:if>
          					</td>
          					<td align="center" width="350px;">${wthdrawOrder.yyRejectReason}</td>
          					<td align="center" width="350px;">${wthdrawOrder.yyInnerRemarks}</td>
          					<td align="center" width="350px;" class="l-table-edit-td" id="viewer-pictures">
          					
				 			<c:forEach var="withdrawOrderPic" items="${withdrawOrderPics}">
				 			<c:if test="${withdrawOrderPic.type eq 0}">
					 			<img src="${pageContext.request.contextPath}/file_servelt${withdrawOrderPic.pic}" style="max-width: 80px;max-height: 100px;">
							</c:if> 
				 			</c:forEach>

							</td>
							<td align="center" width="100px;">${wthdrawOrder.yyName}</td>
							<td align="center">${yyAuditDate}</td>
          				</tr>
        			</table>
				</td>
			</tr>
			<tr>
				<td class="title">财审信息：</td>
				<td>
					<table class="gridtable">
          				<tr align="center">
							<td class="title">审核結果</td>
							<td class="title">驳回理由</td>
							<td class="title">内部备注</td>
							<td class="title">图片</td>
							<td class="title">审核人</td>
							<td class="title">审核时间</td>
						</tr>
				
          				<tr>
          					<td align="center"><c:if test="${wthdrawOrder.cwAuditStatus == '0'}">审核不通过</c:if>
          						<c:if test="${wthdrawOrder.cwAuditStatus == '1'}">审核通过</c:if>
          					</td>
          					<td align="center">${wthdrawOrder.cwRejectReason}</td>
          					<td align="center">${wthdrawOrder.cwInnerRemarks}</td>
          					<td align="center" class="l-table-edit-td" id="viewer-pictures1">
				 			<c:forEach var="withdrawOrderPic" items="${withdrawOrderPics}">
				 			<c:if test="${withdrawOrderPic.type eq 1}">
					 			<img src="${pageContext.request.contextPath}/file_servelt${withdrawOrderPic.pic}" style="max-width: 80px;max-height: 100px;">
							</c:if> 
				 			</c:forEach>
							</td>
							<td align="center">${wthdrawOrder.cwName}</td>
							<td align="center">${cwAuditDate}</td>
          				</tr>
        			</table>
				</td>
			</tr>
        </table>
	</div>
	</form>
</body>
</html>
