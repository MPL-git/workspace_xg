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
	      url:"/productPriceChangeLog/productPriceChangeLogList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
						{display:'商品序号', name:'mchtCode', align:'center', isSort:false, width:100},
						{display:'店铺名称', name:'shopName', align:'center', isSort:false, width:180},  
						{display:'商品ID', name:'productCode', align:'center', isSort:false, width:100},  
						{display:'商品名称', name:'productName', align:'center', isSort:false, width:180},  
						{display:'品牌ID', name:'brandId', align:'center', isSort:false, width:100},  
						{display:'品牌名称', name:'brandName', align:'center', isSort:false, width:180},  
						{display:'货号', name:'artNo', align:'center', isSort:false, width:100}, 
						{display:'商城价（元）', name:'minMallPrice', align:'center', isSort:false, width:100}, 
						{display:'活动价（元）', name:'minSalePrice', align:'center', isSort:false, width:100}, 
						{display:'创建时间', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        	var html = [];
							if(rowdata.createDate != null && rowdata.createDate != "") {
								var createDate = new Date(rowdata.createDate);
								html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
							}
						    return html.join("");
                        }},
                        {display:'操作', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						    return '<a href="javascript:productPriceChangeLogDel('+ rowdata.id +');">删除</a>';
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
 	 
 	 function productPriceChangeLogDel(id){
 		$.ligerDialog.confirm('是否删除？', function (yes) { 
			if(yes) {
				$.ajax({
		 			url : '${pageContext.request.contextPath}/productPriceChangeLog/productPriceChangeLogDel.shtml',
		 			type : 'POST',
		 			dataType : 'json',
		 			data : {id : id},
		 			success : function(data) {
						if (data.statusCode == "200") {
							commUtil.alertSuccess("操作成功!");
							$("#searchbtn").click();
						}else{
							commUtil.alertError(data.message);
						}
					},
		 			error : function() {
		 				commUtil.alertError('操作超时，请稍后再试！');
		 			}
		 		});
			}
		});
 		$(".l-dialog-content").css("margin-right", "10px");
 	 }
 	 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">商城价：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginMinMallPrice" name="beginMinMallPrice" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endMinMallPrice" name="endMinMallPrice" />
					</div>
				</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productBandId" name="productBandId" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">活动价：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginMinSalePrice" name="beginMinSalePrice" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endMinSalePrice" name="endMinSalePrice" />
					</div>
				</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >货号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="artNo" name="artNo" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0;"></div>
	</form>
	
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
