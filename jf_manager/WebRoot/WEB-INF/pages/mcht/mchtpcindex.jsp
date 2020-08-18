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
	//取消
	function ajaxchanggestatus(id){
		$.ligerDialog.confirm("是否取消？", function (yes) 
		{ 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/mcht/changemchtplatconstatus.shtml?platformContactId="+id+"&mchtId=${MCHTID}",
					secureuri : false,
					fileElementId : "status",
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							//location.reload();
							listModel.gridManager.reload();
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
						
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
	}
	
	 var listConfig={
		  
	      url:"/mcht/mcpcontdata.shtml?mchtId=${MCHTID}",
	   
	      btnItems:[{ text: '添加', icon: 'add', dtype:'win',  click: itemclick, url:"/mcht/addmchtplatformcontact.shtml?mchtId=${MCHTID}", seqId:"", width : 800, heigh:400}],
	      
	      listGrid:{ columns: [  { display: '类型', name: 'contactTypeDesc',width:150 }, 
			                { display: '姓名', name: 'contactName',width:150},
			                { display: '手机号',  name: 'mobile', width: 150 }, 
// 			                { display: '对接人状态', name: 'contactStatusDesc' ,width: 150 },
			                { display: '座机号', name: 'tel', width:150},
			                { display: 'QQ号', name: 'qq', width:150}, 
			                { display: '邮箱', name: 'email', width: 150 },
			                { display: '分配日期', width: 150, render: function (rowdata, rowindex) {
			                	if (rowdata.createDate!=null){
									var createDate=new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd");                		
			                	}
			                }},
			                { display: '分配状态',   name: 'statusDesc' , width: 150},
			                { display: "操作", name: "OPER", width: 150, align: "center", render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status == "1"){
									//正常状态
									html.push("<a href=\"javascript:ajaxchanggestatus(" + rowdata.platformContactId + ");\">【取消】</a>&nbsp;&nbsp;");
								}
							    return html.join("");
						 }
		             }
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
