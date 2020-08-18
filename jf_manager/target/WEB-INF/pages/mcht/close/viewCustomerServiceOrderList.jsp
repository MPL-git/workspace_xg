<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript">
var viewerPictures;
$(function(){
	

	
});

function viewSubDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewAfterServiceDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
 		title: "售后详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

  var listConfig={
		  
	  url:"/mcht/close/viewCustomerServiceOrderData.shtml",
      
      listGrid:{ columns: [{ display: '售后单编号', width: 250, render: function(rowdata, rowindex) {
    						return "<a href=\"javascript:viewAfterServiceDetail(" + rowdata.subOrderId + ");\">"+rowdata.orderCode+"</a>";
    					}},
    					{ display: '品牌/货号', width: 180, render: function (rowdata, rowindex) {
 		                	var artBrandGroup=rowdata.artBrandGroup;
 		                	if (artBrandGroup){
 		                		return artBrandGroup.replace(/,/g,"<br>");
 		                	}
		                }},
 		                { display: '实收金额', width: 120, render: function (rowdata, rowindex) {
    						return rowdata.payAmount;
		                }},
		                { display: '售后单状态', width: 120, render: function (rowdata, rowindex) {
 		                	return rowdata.statusDesc+"<br>"+rowdata.proStatusDesc;
		                }},
		                { display: '申请时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.createDate){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '相关子订单编号', width: 250, render: function(rowdata, rowindex) {
    						return "<a href=\"javascript:viewSubDetail(" + rowdata.subOrderId + ");\">"+rowdata.subOrderCode+"</a>";
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
};
  
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server" method="post">
		<input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
		<input type="hidden" id="status" name="status" value="${status}">
		<div class="search-pannel">
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label">售后编号：</div>
			<div class="search-td-condition">
				<input type="text" id="orderCode" name="orderCode">
			</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" >商品名称：</div>
				<div class="search-td-condition" >
					<input type="text" id = "productName" name="productName" >
				</div>
			</div>
			
			 <div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn">搜索</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>