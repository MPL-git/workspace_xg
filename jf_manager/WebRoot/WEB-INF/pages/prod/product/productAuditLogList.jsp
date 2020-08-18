<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">

 var listConfig={
	  url:"/product/getProductAuditLogList.shtml?productId=${productId}",
      btnItems:[],
      listGrid:{ 
    	  columns: [
    	        {display:'时间',name:'createDate',render:function(rowdata, rowindex){
	                if(rowdata.createDate != null){
	                   var createDate=new Date(rowdata.createDate);
	           		   return createDate.format("yyyy-MM-dd hh:mm:ss");
	                }
                }},
                {display:'审核人',name:'createStaffName'},
                {display:'审核状态',name:'', render:function(rowdata, rowindex){
                    if(rowdata.status == '0'){
                    	return "未提审";
                    }else if(rowdata.status == '1'){
                    	return "待审核";
                    }else if(rowdata.status == '2'){
                    	return "通过";
                    }else if(rowdata.status == '3'){
                    	return "驳回";
                    }else{
	                    return "";
                    }
                }},
                {display:'驳回原因', name:'remarks'}
	         ],   
             showCheckbox : false,  //不设置默认为 true
             showRownumber:true //不设置默认为 true
      } , 							
     container:{
//         toolBarName:"toptoolbar",
//         searchBtnName:"searchbtn",
//         fromName:"dataForm",
        listGridName:"maingrid"
     }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
<!-- 	<form id="dataForm" runat="server"> -->
		<div id="maingrid" style="margin: 0; padding: 0"></div>
<!-- 	</form> -->
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
