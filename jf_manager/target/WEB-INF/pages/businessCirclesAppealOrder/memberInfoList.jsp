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
      url:"/businessCirclesAppealOrder/memberinfodata.shtml?memberIdGroup=${memberIdGroup}",
      btnItems:[],
      listGrid:{ columns: [ 
						{display:'会员ID',name:'id', align:'center', isSort:false, width:200},
						{ display: '注册时间', render: function (rowdata, rowindex) {
		                	if (rowdata.createDate!=null){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '最后登录时间', render: function (rowdata, rowindex) {
		                	if (rowdata.lastLoginTime!=null){
								var lastLoginTime=new Date(rowdata.lastLoginTime);
								return lastLoginTime.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '昵称', name: 'nick', render: function(rowdata, rowindex) {
    	                	if(rowdata.nick==null){
    	                		return "醒购会员";
    	                	}else{
    	                
	    	                   return rowdata.nick;
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
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
