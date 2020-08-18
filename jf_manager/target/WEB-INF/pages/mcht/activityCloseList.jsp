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
	 $(function() {
		 $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		 
	 });
		 
 	 var listConfig={
	      url:"/mcht/activityCloseList.shtml?mchtId=${mchtId }&productBrandId=${productBrandId }",
	      btnItems:[{ text:'全部驳回', icon:'modify', dtype:'win', click:activityClose }],
	      listGrid:{ columns: [
							{display:'活动ID', name:'id', align:'center', isSort:false, width:80},
							{display:'活动类型', name:'sourceDesc', align:'center', isSort:false, width:100},
							{display:'活动状态', name:'activityStatusDesc', align:'center', isSort:false, width:100},   
							{display:'活动名称', name:'name', align:'center', isSort:false, width:180},  
							{display:'活动开始时间', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if(rowdata.activityBeginTime){
									var activityBeginTime = new Date(rowdata.activityBeginTime);
									return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");		                		
								}
							}},
							{display:'活动结束时间', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if(rowdata.activityEndTime){
									var activityEndTime = new Date(rowdata.activityEndTime);
									return activityEndTime.format("yyyy-MM-dd hh:mm:ss");		                		
								}
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	 };
 	 
 	 function activityClose(){
 	 	debugger;
 		var mchtId = ${mchtId };
 		var productBrandId = ${productBrandId } == null?"":${productBrandId };
 		$.ligerDialog.confirm('您确定将品牌活动全部驳回吗？', function (yes) { 
			if(yes) {
				$.ajax({
		 			url : '${pageContext.request.contextPath}/mcht/activityClose.shtml',
		 			type : 'POST',
		 			dataType : 'json',
		 			data : {mchtId : mchtId, productBrandId : productBrandId},
		 			success : function(data) {
		 				if ('0000' == data.returnCode) {
		 					parent.location.reload();
		 					frameElement.dialog.close();
		 				}else{
		 					commUtil.alertError(data.returnMsg);
		 				}
		 			},
		 			error : function() {
		 				$.ligerDialog.error('操作超时，请稍后再试！');
		 			}
		 		});
			}
		});
 		$(".l-dialog-content").css("margin-right", "10px");
 	 }
 	 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td-search-btn" >
					<div id="searchbtn" style="display: none;" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
