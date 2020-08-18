<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.color-1{color: #9D999D;}
.color-2{color: #FC0000;}
.color-3{color: #EFD104;}
.color-4{color: #00FC28;}
.color-5{color: #0351F7;}
.color-6{color: #DF00FB;}
.l-box-select .l-box-select-table td {
	font-size: 12px;
	line-height: 18px;
}
</style>
<script type="text/javascript">
$(function(){
	
});


function viewDetail(id) {
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

  var listConfig={
	  url:"/mcht/close/viewSubOrderData.shtml",
      listGrid:{ columns: [{ display: '子订单编号', width: 200, render: function(rowdata, rowindex) {
    						var html = [];
    						html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">"+rowdata.subOrderCode+"</a>"); 
    						if(rowdata.memberInfoStatus == 'P') {
    							html.push("</br><span style='color:red;'>异常</span>");
    						}
    						return html.join("");
    					}},
    					
 		                { display: '品牌/货号', width: 180, render: function (rowdata, rowindex) {
 		                	var artBrandGroup=rowdata.artBrandGroup;
 		                	if (artBrandGroup!=null){
 		                		return artBrandGroup.replace(/,/g,"<br>");
 		                	}
		                }},
		                { display: '实付金额', width: 100, name: 'payAmount'},
		                { display: '订单状态', width: 100, name: 'statusDesc'},
		                { display: '付款时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.orderPayDate!=null){
								var orderPayDate=new Date(rowdata.orderPayDate);
								return orderPayDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '发货时间', width: 150, render: function (rowdata, rowindex) {
		                	if (rowdata.deliveryDate!=null){
								var deliveryDate=new Date(rowdata.deliveryDate);
								return deliveryDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
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

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" method="post" action="" >
		<input type="hidden" id="mchtId" name="mchtId" value="${mchtId}">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
				<div class="search-td-label" >子订单编号：</div>
				<div class="search-td-condition" >
					<input type="text" id = "subOrderCode" name="subOrderCode" >
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label" >商品名称：</div>
				<div class="search-td-condition" >
					<input type="text" id = "productName" name="productName" >
				</div>
				</div>
				
				<div class="search-td" <c:if test="${subOrderStatus == 1}">style="display: none;"</c:if>>
				<div class="search-td-label" >订单状态：</div>
				<div class="search-td-condition" >
					<select id="status" name="status">
						<option value="">请选择...</option>
						<option value="1" <c:if test="${subOrderStatus == 0}">selected="selected"</c:if>>待发货</option>
						<option value="2" <c:if test="${subOrderStatus == 1}">selected="selected"</c:if>>已发货</option>
					</select>
				</div>
				</div>
				
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>