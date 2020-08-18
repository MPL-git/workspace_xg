<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
      var listConfig={
      url:"/mchtContract/statExpireData.shtml",
      listGrid:{
		  columns: [
			  { display: '月份', name: 'month', width:120 },
			  { display: '到期数量',  name: 'count', width: 150 },
			  { display: "操作", name: "OPER", width: 150, align: "center", render: function(rowdata, rowindex) {
				  if(rowdata.count>0){
					  var html = [];
					  html.push("<a href=\"javascript:listMchtInfo('" + rowdata.month + "');\">【查看商家】</a>&nbsp;&nbsp;");
					  html.push("<a href=\"javascript:sendMessage('" + rowdata.month + "');\">【发短信】</a>");
					  return html.join("");
				  }else{
					  return "";
				  }
			  }},
		  ],
		  showCheckbox : false,  //不设置默认为 true
		  showRownumber:true //不设置默认为 true
      } ,
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
	 }
       
  }
      

</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="archiveStatus" value="1" alt="合同已归档"/>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

	// 查看商家
	function listMchtInfo(month) {
		// 2017-09
		var endYear = month.substring(0, 4);	//2017
		var endMonth = month.substring(5, 7);	//09
		var url = "${pageContext.request.contextPath}/mchtContract/listArchivePass.shtml?endYear=" + endYear + "&endMonth=" + endMonth;
		window.location.href=url;
	}
	// 发短信
	function sendMessage(yearMonth) {
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/sendSms.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {yearMonth:yearMonth},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("短信已发送，共"+data.sendSuccessCount+"条");
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}

</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
