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
 function otherExceptionOrderList(mchtCode,payDateBegin,payDateEnd) {
	 $.ligerDialog.open({
			height: $(window).height()*0.95,
			width: $(window).width()*0.95,
			title: "其他异常订单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/orderEfficiencyData/otherExceptionOrder/list.shtml?mchtCode="+mchtCode+"&payDateBegin="+payDateBegin+"&payDateEnd="+payDateEnd,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
}
	
 var listConfig={
     url:"/orderEfficiencyData/otherExceptionOrderCount/data.shtml",
     listGrid:{ columns: [
						{ display: '商家序号',width: 200, render: function (rowdata, rowindex) {
							return rowdata.mcht_code;	                		
		                }},
		                { display: '公司名称',width: 190,render: function (rowdata, rowindex) {
			            	return rowdata.company_name;	                		
		                }},
			            { display: '店铺名',width: 190,render: function (rowdata, rowindex) {
			            	return rowdata.shop_name;	                		
		                }},
		                { display: '支付订单总数',width: 190, render: function (rowdata, rowindex) {
							return '<span>'+rowdata.total_order_count+'</span>';	                		
		                }},
			            { display: '其他异常订单总数', width: 190,render: function (rowdata, rowindex) {
							return '<span>'+rowdata.other_exception_order_count+'</span>';	                		
		                }},
		                { display: '其他异常比例', width: 190,render: function (rowdata, rowindex) {
							return rowdata.rate;	                		
		                }},
		                { display: '操作', width: 190,render: function (rowdata, rowindex) {
		                	var payDateBegin = $("#payDateBegin").val();
		                	var payDateEnd = $("#payDateEnd").val();
							return '<a href="javascript:;" onclick="otherExceptionOrderList('+"'"+rowdata.mcht_code+"'"+','+"'"+payDateBegin+"'"+','+"'"+payDateEnd+"'"+');">查看</a>';	                		
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
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
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
		</div>	
		<div class="search-tr"  >	
			<div class="search-td">
				<div class="l-panel-search-item search_right">商家序号：</div>
				<div class="l-panel-search-item">
					<input type="text" id="mchtCode" name="mchtCode"/>
				</div>
			</div>
			
			<div class="search-td">
				<div class="l-panel-search-item search_right">店铺名称：</div>
				<div class="l-panel-search-item">
					<input type="text" id="shopName" name="shopName"/>
				</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" style="color: red">对接人：</div>
			<div class="search-td-condition" >
				<select id="platformContactId" name="platformContactId" >
					<c:if test="${isContact==1}">
						<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
							<option value="">全部商家</option>
						</c:if>
						<option value="${myContactId}">我对接的商家</option>
						<c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
							<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
						</c:forEach>
					</c:if>
					
					<c:if test="${isContact==0}">
					<option value="">全部商家</option>
					<c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
						<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
					</c:forEach>			
					</c:if>
				</select>
			</div>
			</div>
			
			<div class="search-td">
				<div id="searchbtn">查看</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>