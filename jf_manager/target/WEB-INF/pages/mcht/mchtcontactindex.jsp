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
		var listConfig={
			  
		      url:"/mcht/mcdata.shtml?mchtId=${MCHTID}&contactType=${contactType}",
		      
		      listGrid:{ columns: [  
		                        /* { display: '类型', name: 'contactTypes',width:180 },  */
		                        { display: '类型', name: '',width:180 ,render:function(rowdata,rowindex){
									if(rowdata.contactType=='1'){
										return "电商总负责人";
									}
									if(rowdata.contactType=='2'){
										return "运营对接人";
									}
									if(rowdata.contactType=='3'){
										return "订单对接人";
									}
									if(rowdata.contactType=='4'){
										return "售后对接人";
									}
									if(rowdata.contactType=='5'){
										return "财务对接人";
									}
									if(rowdata.contactType=='6'){
										return "客服对接人";
									}
    			                }},
		                        { display: '是否默认', name: 'isPrimary',width:180 ,render:function(rowdata,rowindex){
									if(rowdata.isPrimary=='1'){
										return "默认";
									}else{
										return "否";
									}
    			                }}, 
				                { display: '姓名', name: 'contactName',width:180},
				                { display: '手机号',  name: 'mobile', width: 180 }, 
				                { display: '微信', name: 'wechat', width:120},
				                { display: 'QQ号', name: 'qq', width:180}, 
				                { display: '邮箱', name: 'email', width: 180 },
				                { display: '通讯地址',   name: 'address' , width: 180},
				                { display: '最后更新日期', render: function (rowdata, rowindex) {
		                	       if (rowdata.updateDate!=null && rowdata.updateDate!=''){
								   var updateDate=new Date(rowdata.updateDate);
								   return updateDate.format("yyyy-MM-dd hh:mm:ss");          		
		                	     }
		                        }}
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
  
  <body>
  	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
