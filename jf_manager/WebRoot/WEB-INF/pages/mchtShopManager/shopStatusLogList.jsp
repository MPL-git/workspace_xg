<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 
      var listConfig={
    	pageSize:10,	  
      	url:"/mchtShopManager/shopStatusLogData.shtml",
      	listGrid:{ columns: [ 
		              	{ display: '开通日期', name: 'status_date', width:180 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.createDate){
    	   		                return new Date(rowdata.createDate).format("yyyy-MM-dd hh:mm:ss");
    		                }else{
    		                	return "";
    		                }
 		                }},
 		                { display: '类型', name: 'type', width: 120,render: function(rowdata, rowindex) {
   		                	if(rowdata.type == "0"){
   		                		return "店铺";
   		                	}else if(rowdata.type == "1"){
   		                		return "活动";
   		                	}else if(rowdata.type == "2"){
   		                		return "供应链";
   		                	}
 		                }},
 		                { display: '状态', name: 'status', width: 120,render: function(rowdata, rowindex) {
   		                	if(rowdata.status == "0"){
   		                		return "关闭";
   		                	}else if(rowdata.status == "1"){
   		                		return "开通";
   		                	}
 		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
       
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="mchtId" value="${mchtId}">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
