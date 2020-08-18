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

 function addManuallyViolateOrder(subOrderCode,mchtCode) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "添加人工发起违规",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/orderEfficiencyData/otherExceptionOrder/addOtherExceptionViolateOrder.shtml?subOrderCode=" + subOrderCode+"&mchtCode="+mchtCode,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 var listConfig={
     url:"/orderEfficiencyData/otherExceptionOrder/data.shtml",
     listGrid:{ columns: [
						{ display: '支付日期',width: 200, render: function (rowdata, rowindex) {
							if(rowdata.payDate){
								return new Date(rowdata.payDate).format("yyyy-MM-dd hh:mm:ss");	                		
							}
		                }},
						{ display: '子订单号',width: 200, render: function (rowdata, rowindex) {
							return '<a href="javascript:;" onclick="viewDetail('+rowdata.subOrderId+')">'+rowdata.subOrderCode+'</a>';	                		
		                }},
						{ display: '订单状态',width: 200, render: function (rowdata, rowindex) {
							return rowdata.statusDesc;	                		
		                }},
						{ display: '商品明细',width: 200, render: function (rowdata, rowindex) {
							return rowdata.brandCode;	                		
		                }},
						{ display: '商家序号',width: 100, render: function (rowdata, rowindex) {
							return rowdata.mchtCode;	                		
		                }},
		                { display: '公司名称',width: 190,render: function (rowdata, rowindex) {
			            	return rowdata.companyName;	                		
		                }},
			            { display: '店铺名',width: 190,render: function (rowdata, rowindex) {
			            	return rowdata.shopName;	                		
		                }},
			            { display: '售后单',width: 190,render: function (rowdata, rowindex) {
			            	return rowdata.serviceOrderCode;	                		
		                }},
			            { display: '收货人',width: 80,render: function (rowdata, rowindex) {
			            	return rowdata.receiverName;	                		
		                }},
		                { display: '违规编号',width: 190, render: function (rowdata, rowindex) {
		                	if(rowdata.ordercode){
								return rowdata.ordercode;	                		
		                	}else{
								return '<a href="javascript:;" onclick="addManuallyViolateOrder('+"'"+rowdata.subOrderCode+"'"+','+"'"+rowdata.mchtCode+"'"+')">发起</a>';		                		
		                	}	                		
		                }},
			            { display: '异常跟单状态', width: 80,render: function (rowdata, rowindex) {
			            	if(rowdata.followStatusDesc){
								return rowdata.followStatusDesc;	                		
			            	}else{
								return "未处理";	                		
			            	}
		                }},
		                { display: '内部备注', width: 190,render: function (rowdata, rowindex) {
							return rowdata.logRemarks;	                		
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
			
			<div class="search-td">
				<div class="search-td-label">品牌：</div>
				<div class="search-td-combobox-condition" >
					<select id="brandId" name="brandId">
						<option value="">请选择</option>
						<c:forEach items="${productBrands}" var="productBrand">
							<option value="${productBrand.id}">${productBrand.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="search-td">
				<div class="l-panel-search-item search_right">商家序号：</div>
				<div class="l-panel-search-item">
					<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}"/>
				</div>
			</div>
		</div>	
		<div class="search-tr">	
			<div class="search-td">
				<div class="l-panel-search-item search_right">店铺名称：</div>
				<div class="l-panel-search-item">
					<input type="text" id="shopName" name="shopName"/>
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label">跟单人：</div>
				<div class="search-td-combobox-condition" >
					<select id="shamFollowBy" name="shamFollowBy">
						<option value="">请选择</option>
						<c:forEach items="${followByList}" var="followBy">
							<option value="${followBy.follow_by}">${followBy.STAFF_NAME}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label">跟单状态：</div>
				<div class="search-td-combobox-condition" >
					<select id="shamFollowStatus" name="shamFollowStatus">
						<option value="">请选择</option>
						<c:forEach items="${followStatusList}" var="followStatus">
							<option value="${followStatus.statusValue}">${followStatus.statusDesc}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="search-td">
				<div class="l-panel-search-item search_right">子订单号：</div>
				<div class="l-panel-search-item">
					<input type="text" id="subOrderCode" name="subOrderCode"/>
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
				<div id="searchbtn">搜索</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>