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
//视频显示和隐藏状态
function changeCommentStatus(id,status) {
		var title;
		if (status=="0"){
			title="是否隐藏？";
		}
		if (status=="1"){
			title="是否显示？";
		}
		$.ligerDialog.confirm(title, function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/video/changeCommentStatus.shtml?id=" +id+"&status="+status,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
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
	
	
//视频评论删除
function delCommentStatus(id,delstatus) {
		var title;
		if (status=="1"){
			title="是否删除？";
		}
		$.ligerDialog.confirm(title, function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/video/delCommentStatus.shtml?id=" +id+"&delstatus="+delstatus,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
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
		  
	      url:"/video/videoCommentdata.shtml?videoId=${videoId}",
	      listGrid:{ columns: [ 
	                        { display: '用户ID', name: 'memberId',width:150 }, 
			                { display: '评论内容', name: 'content',width:150},
			                {display:'显示状态',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
						    	 var html = [];
					             if (rowdata.status=='1') {
									html.push("显示");
								 }else if (rowdata.status=='0') {
									 html.push("隐藏");
								}									
							    return html.join("");
						    }},
			                { display: "操作", name: "OPER", width: 150, align: "center", render: function(rowdata, rowindex) {
								var html = [];
								if (rowdata.status=='1') {
							    	html.push("<a href=\"javascript:changeCommentStatus(" + rowdata.id + ",0);\">隐藏</a><br>");
								    html.push("<a href=\"javascript:delCommentStatus(" + rowdata.id + ",1);\">删除</a>"); 
								}else if (rowdata.status=='0') {
									html.push("<a href=\"javascript:changeCommentStatus(" + rowdata.id + ",1);\">显示</a><br>");
								    html.push("<a href=\"javascript:delCommentStatus(" + rowdata.id + ",1);\">删除</a>"); 
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
