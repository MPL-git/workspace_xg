<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit-td {
	padding: 4px;
}

.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
</style>

<script type="text/javascript">

	$(function(){
		var logGrid = $("#maingridAddress").ligerGrid({
		    columns: [
				{ display: '收货人',  name: 'recipient'},
				{ display: '收货电话',  name: 'phone'},
				{ display: '收货地址',  name: 'fullAddress'},
				{ display: '邮编', name: 'zipCode'},
				{ display: '是否默认',  name: 'isPrimary', render: function(rowdata, rowindex) {
						if (rowdata.isPrimary=="Y"){
							return "是";
						}else{
							return "否";
						}
				}}
		    ],  pageSize:5,pageSizeOptions:[5,10,20,50,100],
		    url:'${pageContext.request.contextPath}/member/address/dataList.shtml?memberId=${memberInfoCustom.id}',
		    width: '100%'
		});
	});

</script>

<html>
<body>
		<div><span class="table-title" >收货地址信息</span></div>
		<br>
		<form id="dataFormAddress" runat="server"> 
			<div id="maingridAddress" style="margin: 0; padding: 0"></div>
		</form>
</body>
</html>
