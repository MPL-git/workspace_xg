<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	 
	 //删除
	 function delBlack(id) {
		 $.ligerDialog.confirm("是否删除？", function(status){
            if(status==true){
           	 $.ajax({
       			 type: 'post',
       			 url: '${pageContext.request.contextPath }/member/delSmsBlackMobile.shtml',
       			 data: {id : id},
       			 dataType: 'json',
       			 success: function(data) {
       				 if(data == null || data.statusCode != 200){
       				     commUtil.alertError(data.message);
       				 }else {
       					 $("#searchbtn").click();
       				 }
       			 },
       			 error: function(e) {
       				 commUtil.alertError("系统异常请稍后再试");
       			 }
       		 });
            }
        });
	 }
	 
 	 var listConfig={
	      url:"/member/sms/getSmsBlackMobile.shtml",
	      listGrid:{ columns: [
							{display:'手机号',name:'memberMobile', align:'center', isSort:false, width:200},
							{display:'创建时间',name:'createDate', align:'center', isSort:false, width:200},
							{display:'操作',name:'', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:delBlack(" + rowdata.id + ");\">【删除】</a>");
								return html.join("");
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="ids" name="ids" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >手机号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberMobile" name="memberMobile" >
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
