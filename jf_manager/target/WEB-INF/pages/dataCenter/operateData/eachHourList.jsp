<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

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
 var listConfig={
      url: "/operateData/eachHour/data.shtml",
      listGrid:{ columns: [
                        {display:'时间',width: 50,name:'hour'},
                        {display:'母订单数',width: 100,name:'order_count'},
                        {display:'商品数',width: 100, name:'product_quantity'},
                        {display:'商品金额',width: 100,name:'product_price'},
                        {display:'商家优惠',width: 100,name:'mcht_preferential'},
                        {display:'平台优惠',width: 100,name:'platform_preferential'},
                        {display:'实收金额',width: 100,name:'pay_amount'},
                        {display:'结算金额',width: 100,name:'settle_amount'},
                        {display:'客单价',width: 100,name:'unit_price'},
                        {display:'商家优惠率',width: 100,name:'mcht_preferential_rate'},
                        {display:'平台优惠率',width: 100,name:'platform_preferential_rate'},
                        {display:'毛利率',width: 100,name:'gross_rate'}
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
</head>
<body style="padding: 0px; overflow: hidden;">
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >

				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;padding-top:6px;">付款日期：</div>
					<div class="l-panel-search-item" style="padding-top:6px;">
						<input type="text" id="payDateBegin" name="payDateBegin" value="${payDateBegin}"/>
					</div>

				<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;padding-top:6px;" >至 </div>

					<div class="l-panel-search-item" style="padding-top:6px;">
						<input type="text" id="payDateEnd" name="payDateEnd" value="${payDateEnd}"/>
					</div>
				</div>
				
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition" >
						<select id="brandId" name="brandId">
							<option value="">请选择</option>
							<c:forEach items="${productBrands}" var="productBrand">
								<option value="${productBrand.id}">${productBrand.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="search-td" style="margin-right:-20px;">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input type="text" id="mchtCode" name="mchtCode"/>
					</div>
				</div>
			</div>

			<div class="search-tr"  >	
				<div class="search-td">
					<div class="l-panel-search-item">店铺名称：</div>
					<div class="l-panel-search-item">
						<input type="text" id="shopName" name="shopName"/>
					</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label" style="float:left;">类目：</div>
				<div class="search-td-condition" >
					<c:if test="${isCwOrgStatus == 1 }">
						<select id="productTypeId" name="productTypeId" disabled="disabled">
							<c:forEach var="productType" items="${productTypes}">
								<option value="${productType.id}">${productType.name}</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${isCwOrgStatus == 0 }">
						<select id="productTypeId" name="productTypeId">
							<option value="">请选择</option>
							<c:forEach var="productType" items="${productTypes}">
								<option value="${productType.id}">${productType.name}</option>
							</c:forEach>
						</select>
					</c:if>
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
				
				<div class="search-td-search-btn">
					<div class="l-button" id="searchbtn">查看</div>
				</div>
			</div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
		
	</form>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
<script type="text/javascript">
	$(function() {
		
		$("#search").bind('click',function(){
			var startCreateDate = $("#startCreateDate").val();
			var endCreateDate = $("#endCreateDate").val();
			var startDay = new Date(startCreateDate);
			var endDay = new Date(endCreateDate);
			var diffDays = (endDay - startDay) / (1000 * 60 * 60 * 24);
			if(diffDays > 31){
				commUtil.alertError("起始日期间隔不能超过31天");
				return false;
			}else{
				$("#searchbtn").click();
			}
		});
		
		// 日期
		$("#payDateBegin").ligerDateEditor( {
			showTime : false,
			labelWidth : 150,
			width:150,
			labelAlign : 'left'
		});
		$("#payDateEnd").ligerDateEditor( {
			showTime : false,
			labelWidth : 150,
			width:150,
			labelAlign : 'left'
		});
		// 禁止分页
		 $("#maingrid").ligerGrid({
            usePager:false
        });
	});
	
</script> 

</html>
