<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	
<script type="text/javascript">
	function viewBrandList(yearMonth){
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看品牌",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/viewBrandList.shtml?yearMonth=" + yearMonth,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	function sendMessage(yearMonth){
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/sendSms.shtml",
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
	
	 var listConfig={
	      url:"/mcht/brandExpireCountData.shtml",
	      listGrid:{ columns: [  { display: '月份', name: 'yearMonth'}, 
			                { display: '到期品牌统计', name: 'expireCount'},
			                { display: '操作',  render: function (rowdata, rowindex) {
			                	if(rowdata.expireCount>0){
			                		return '<a href="javascript:;" onclick="viewBrandList('+"'"+rowdata.yearMonth+"'"+');">【查看品牌】</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="sendMessage('+"'"+rowdata.yearMonth+"'"+');">【发短信】</a>';
			                	}else{
			                		return "";
			                	}
			                }}
			                ],   
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
	     container:{
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	       
	  };
</script>
	
  </head>
  
  <body>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
