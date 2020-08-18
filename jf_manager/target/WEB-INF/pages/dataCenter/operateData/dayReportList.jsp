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
 function formatMoney(s, n)
 {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1];
    t = "";
    for(i = 0; i < l.length; i ++ )
    {
       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
    }
    return t.split("").reverse().join("") + "." + r;
 } 
 
 function isSunday(eachDay){
	 var dt = new Date(eachDay);
	 if(dt.getDay()==0){
		 return true;
	 }else{
		 return false;
	 }
 }
 
$(function(){
	// 禁止分页
	 $("#maingrid").ligerGrid({
       usePager:false
   });
}); 
 
function checkComfirm(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "保证金缴纳确认",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtDeposit/checkComfirm.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
	
 var listConfig={
     url:"/operateData/dayReport/data.shtml",
     listGrid:{ columns: [
						{ display: '时间',width:100, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.eachDay+'</span>';
							}else{
								return rowdata.eachDay;	                		
							}
		                }},
			            { display: '购买人数',width:100,render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.buyUserCount+'</span>';
							}else{
								return '<span>'+rowdata.buyUserCount+'</span>';	                		
							}
		                }},
		                { display: '订单数',width:100, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.orderCount+'</span>';
							}else{
								return '<span>'+rowdata.orderCount+'</span>';	                		
							}
		                }},
			            { display: '商品数',width:100, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.productCount+'</span>';
							}else{
								return '<span>'+rowdata.productCount+'</span>';	                		
							}
		                }},
		                { display: '商品金额',width:100, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.productSalePrice+'</span>';
							}else{
								return '<span>'+rowdata.productSalePrice+'</span>';	                		
							}
		                }},
		                { display: '商家优惠',width:100, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.totalMchtPreferential+'</span>';
							}else{
								return '<span>'+rowdata.totalMchtPreferential+'</span>';	                		
							}
		                }},
		                { display: '平台优惠',width:100, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.totalPlatformPreferential+'</span>';
							}else{
								return '<span>'+rowdata.totalPlatformPreferential+'</span>';	                		
							}
		                }}, 
		                { display: '实收金额',width:100,render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.totalPayAmount+'</span>';
							}else{
								return '<span>'+rowdata.totalPayAmount+'</span>';	                		
							}
		                }},
		                { display: '结算金额',width:100, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+rowdata.totalSettleAmount+'</span>';
							}else{
								return '<span>'+rowdata.totalSettleAmount+'</span>';	                		
							}
		                }},
		                { display: '客单价',width:100,  render: function (rowdata, rowindex) {
		                	if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+formatMoney(rowdata.unitPrice,2)+'</span>';
							}else{
								return formatMoney(rowdata.unitPrice,2);	                		
							}
		                }},
		                { display: '商家优惠率',width:100, render: function (rowdata, rowindex) {
		                	if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+formatMoney(rowdata.mchtDiscountRate,2)+"%"+'</span>';
							}else{
								return formatMoney(rowdata.mchtDiscountRate,2)+"%";	                		
							}
		                }},
		                { display: '平台优惠率',width:100, render: function (rowdata, rowindex) {
		                	if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+formatMoney(rowdata.platDiscountRate,2)+"%"+'</span>';
							}else{
								return formatMoney(rowdata.platDiscountRate,2)+"%";	                		
							}
		                }},
		                { display: '毛利率',width:100, render: function (rowdata, rowindex) {
		                	if(isSunday(rowdata.eachDay)){
								return '<span style="color:red;">'+formatMoney(rowdata.grossProfitRate,2)+"%"+'</span>';
							}else{
								return formatMoney(rowdata.grossProfitRate,2)+"%";	                		
							}
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">付款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${pay_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_begin").ligerDateEditor( {
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
				<input type="text" id="pay_date_end" name="pay_date_end" value="${pay_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<%-- <div class="search-td">
			<div class="search-td-label" style="float:left;">付款状态：</div>
			<div class="l-panel-search-item" >
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>已付款</option>
					<option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>未付款</option>
				</select>
		 	 </div>
			 </div> --%>
			 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">类目：</div>
			<div class="l-panel-search-item" >
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
		</div>
			
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition">
				<input type="text" id="mchtCode" name="mchtCode" >
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
					<div class="search-td-label">栏目：</div>
					<div class="search-td-combobox-condition">
						<select id="activityType" name="activityType">
							<option value="">全部</option>
							<option value="-1">品牌特卖</option>
							<option value="0">会场</option>
							<option value="1">新用户专享</option>
							<option value="4">新用户秒杀</option>
							<option value="2">单品爆款</option>
							<option value="3">醒购秒杀</option>
							<option value="5">积分商城</option>
							<option value="6">断码清仓</option>
							<option value="7">商城销售</option>
							<option value="8">砍价免费拿</option>
							<option value="9">邀请享免单</option>
						</select>
					</div>
				</div>

				<div class="search-td-search-btn">
				<div id="searchbtn" >查看</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>