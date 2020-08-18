<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<html>
<body>
<form method="post" id="form" name="form" action="">
			<div> 【合计】期初总欠：${beginUnpay}元，本月代收：${orderAmount}元，本月代收应结：${settleAmount}元，本月代退：${returnAmount}元，本月代退应扣：${returnOrderAmount}元，直赔单应扣：${refundAmount}元，本月总应付：${needPayAmount}元，本月付款：${payAmount}元，期末总欠：${endUnpay}元。</div>
</form>	        
</body>
</html>
