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
		
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
	 });
	
	//添加
	var oldproductTypeId = true;
	function addProductType(productTypeId, mallCategoryModuleId) {
		if(oldproductTypeId != productTypeId) {
		   oldproductTypeId = productTypeId;
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/mallCategoryItem/addProductType.shtml",
			 data : {productTypeId : productTypeId, mallCategoryModuleId : mallCategoryModuleId},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.code != 200)
					 commUtil.alertError(data.msg);
				 else{
					 $("#searchbtn").click();
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
		
		}
	}
	
 	var listConfig={
	      url:"/mallCategoryItem/getProductTypeList.shtml?mallCategoryModuleId=${mallCategoryModuleId }",
	      btnItems:[],
	      listGrid:{ columns: [
	                        {display:'品类ID', name:'id', align:'center', isSort:false, width:180},
	                        {display:'品类名称', name:'name', align:'center', isSort:false, width:180},
	                        {display:'商品数', name:'product_count', align:'center', isSort:false, width:180},
	                        {display:'操作', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:addProductType(" + rowdata.id + ", ${mallCategoryModuleId });\">【添加】</a>");
							    return html.join("");
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
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >品类ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productTypeId" name="productTypeId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品类名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productTypeName" name="productTypeName" >
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
