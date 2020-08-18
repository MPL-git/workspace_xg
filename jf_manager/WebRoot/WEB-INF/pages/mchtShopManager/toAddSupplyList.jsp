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
 function addSupply(supplierUserId,mchtId){
	 $.ajax({
			url : "${pageContext.request.contextPath}/mchtShopManager/addSupply.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"supplierUserId":supplierUserId,"mchtId":mchtId},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("该供应商添加成功。");
					setTimeout(function(){
						location.reload();
					},1000);
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
    	pageSize:5,	  
      	url:"/mchtShopManager/toAddSupplyData.shtml",
      	listGrid:{ columns: [ 
		              	{ display: '合作供应商', name: 'name', width:180 ,render: function(rowdata, rowindex) {
    		                return rowdata.name;
 		                }},
 		                { display: '操作', name: 'status', width: 120,render: function(rowdata, rowindex) {
 		                	var mchtIds = rowdata.mchtIds;
 		                	var mchtId = $("#mchtId").val();
 		                	if(mchtIds){
 		                		var array = mchtIds.split(",");
 	 		                	var index = $.inArray(mchtId, array);
 	 		                	if(index>=0){
	 		                		return "已添加";
 	 		                	}else{
	 		                		return '<a href="javascript:;" onclick="addSupply('+rowdata.id+','+mchtId+');">添加</a>';
 	 		                	}
 		                	}else{
 		                		return '<a href="javascript:;" onclick="addSupply('+rowdata.id+','+mchtId+');">添加</a>';
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
		<input type="hidden" name="mchtId" id="mchtId" value="${mchtId}">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="width: 30%">
				<div class="search-td-label">供应商名称：</div>
				<div class="search-td-condition" >
					<input type="text" id="companyName" name="companyName" >
				</div>
				</div>
	
			 	<div  class="search-td-search-btn">
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
			</div>
			
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
