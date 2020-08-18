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
 
 
 function toEditStatus(id){
	 $.ligerDialog.open({
			height: 250,
			width: 400,
			title: "修改供应商合作状态",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtShopManager/toEditStatus.shtml?mchtSupplierUserId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
      var listConfig={
    	pageSize:5,	  
      	url:"/mchtShopManager/toViewSupplyData.shtml",
      	btnItems:[
				  {text: '添加供应商', icon: 'add', click: function(yes) {
					  $.ligerDialog.open({
							height: 600,
							width: 800,
							title: "添加供应商",
							name: "INSERT_WINDOW",
							url: "${pageContext.request.contextPath}/mchtShopManager/toAddSupplyList.shtml?mchtId=${mchtId}",
							showMax: true,
							showToggle: false,
							showMin: false,
							isResize: true,
							slide: false,
							data: null,
							closeRefresh:true
						});
				  }}
      	          ],
      	listGrid:{ columns: [ 
		              	{ display: '合作供应商', name: 'companyName', width:180 ,render: function(rowdata, rowindex) {
    		                return rowdata.companyName;
 		                }},
 		                { display: '状态', name: 'status', width: 120,render: function(rowdata, rowindex) {
   		                	if(rowdata.status == "0"){
   		                		return "未合作";
   		                	}else if(rowdata.status == "1"){
   		                		return "合作中";
   		                	}else if(rowdata.status == "2"){
   		                		return "终止合作";
   		                	}else if(rowdata.status == "3"){
   		                		return "取消";
   		                	}
 		                }},
 		                { display: '操作', name: 'status', width: 120,render: function(rowdata, rowindex) {
   		                	return '<a href="javascript:;" onclick="toEditStatus('+rowdata.id+');">修改状态</a>';
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
       
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="mchtId" value="${mchtId}">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
