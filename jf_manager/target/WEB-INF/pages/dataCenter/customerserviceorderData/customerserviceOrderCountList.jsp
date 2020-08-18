<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	
 var listConfig={
     url:"/customerserviceorderData/customerserviceOrderCountdata.shtml",
     listGrid:{ columns: [
					    { display: '类目', width: 200, render: function (rowdata, rowindex) {
							return rowdata.name;	                							    								
		                }},
			            { display: '订单商品数量', width: 190,render: function (rowdata, rowindex) {
			            	return rowdata.totalorder_count;	                		
		                }},
		                { display: '超时发货数量', width: 190, render: function (rowdata, rowindex) {
							return '<span>'+rowdata.overtimeorder_count+'</span>';	                		
		                }},
			            { display: '超时发货占比',  width: 190,render: function (rowdata, rowindex) {
							return '<span>'+rowdata.overtimeordercount_rate+'</span>';	                		
		                }},
			            { display: '虚假发货数量', width: 190,render: function (rowdata, rowindex) {
							return '<span>'+rowdata.falseorder_count+'</span>';	                		
		                }},
		                { display: '虚假发货占比', width: 190,render: function (rowdata, rowindex) {
							return rowdata.falseordercount_rate;	                		
		                }},
		                { display: '缺货数量', width: 190,render: function (rowdata, rowindex) {
							return '<span>'+rowdata.outofstock_order_count+'</span>';	                		
		                }},
		                { display: '缺货占比', width: 190,render: function (rowdata, rowindex) {
							return rowdata.outofstockordercount_rate;	                		
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
		<div class="search-pannel">
		<div class="search-tr"> 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">付款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="payDateBegin" name="payDateBegin" value="${payDateBegin}"/>
				<script type="text/javascript">
					$(function() {
						$("#payDateBegin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="payDateEnd" name="payDateEnd" value="${payDateEnd}"/>
				<script type="text/javascript">
					$(function() {
						$("#payDateEnd").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td">
				<div id="searchbtn">提交</div>
			</div>
		</div>	
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>