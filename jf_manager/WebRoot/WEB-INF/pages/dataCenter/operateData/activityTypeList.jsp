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
 var brand_sale_amount_quantity = "";
 var brand_sale_amount = "";
 var area_sale_quantity = "";
 var area_sale_amount = "";
 var new_user_sale_quantity = "";
 var new_user_sale_amount = "";
 var new_user_kill_quantity = "";
 var new_user_kill_amount = "";
 var single_product_quantity = "";
 var single_product_amount = "";
 var xg_kill_quantity = "";
 var xg_kill_amount = "";
 var integral_quantity = "";
 var integral_amount = "";
 var broken_code_quantity = "";
 var broken_code_amount = "";
 var mall_sale_quantity = "";
 var mall_sale_amount = "";
 
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
	$("#search").bind('click',function(){
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		var startDay = new Date(pay_date_begin);
		var endDay = new Date(pay_date_end);
		var diffDays = (endDay - startDay) / (1000 * 60 * 60 * 24);
		if(diffDays > 31){
			commUtil.alertError("起始日期间隔不能超过31天");
			return false;
		}else if (pay_date_begin > pay_date_end) {
			commUtil.alertError("开始日期不能大于结束日期");
			return false;
		}else if (pay_date_begin=='' || pay_date_end=='') {
			commUtil.alertError("请选择日期");
			return false;
		}else{
			$("#searchbtn").click();
		}
	});
	// 禁止分页
	 $("#maingrid").ligerGrid({
       usePager:false
   });
}); 
 


 
function subOrderReportList(date,activityType) {
	var productTypeId = $("select[name='productTypeId']").val(); 
	var platformContactId=$("select[name='platformContactId']").val();
	var payDateBegin='';
	var payDateEnd='';
	if (date=='合计') {
		 payDateBegin = $("#pay_date_begin").val();
		 payDateEnd = $("#pay_date_end").val();
	}else {
		 payDateBegin =date;
		 payDateEnd = date;
	}
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: "订单排行",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/operateData/subOrderReport/list.shtml?rowType=brand&startCreateDate="+payDateBegin+" 00:00:00"+"&endCreateDate="+payDateEnd+" 23:59:59"+"&pageSize=500&orderType=num&activityType="+activityType+"&productTypeId="+productTypeId+"&platformContactId="+platformContactId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
}

 function checkSearchCondition(){
	brand_sale_amount_quantity = "";
 	brand_sale_amount = "";
 	area_sale_quantity = "";
 	area_sale_amount = "";
 	new_user_sale_quantity = "";
 	new_user_sale_amount = "";
 	new_user_kill_quantity = "";
 	new_user_kill_amount = "";
 	single_product_quantity = "";
 	single_product_amount = "";
 	xg_kill_quantity = "";
 	xg_kill_amount = "";
 	integral_quantity = "";
 	integral_amount = "";
 	broken_code_quantity = "";
 	broken_code_amount = "";
 	mall_sale_quantity = "";
 	mall_sale_amount = "";
    return true;
 }

 var listConfig={
     url:"/operateData/activityType/data.shtml",
     listGrid:{ columns: [
						{ display: '日期',width: 100, render: function (rowdata, rowindex) {
							return rowdata.each_day;	                		
		                }},
			            { display: '品牌特卖',width: 190,render: function (rowdata, rowindex) {
			            	var html = [];
			            	if(brand_sale_amount_quantity != '' && brand_sale_amount != '') {
			            		if(Number(brand_sale_amount_quantity) > Number(rowdata.brand_sale_amount_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.brand_sale_amount_quantity+"</span>件");
			            		}else if(Number(brand_sale_amount_quantity) < Number(rowdata.brand_sale_amount_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.brand_sale_amount_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.brand_sale_amount_quantity+"件");
			            		}
			            		if(Number(brand_sale_amount) > Number(rowdata.brand_sale_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.brand_sale_amount+"</span>元");
			            		}else if(Number(brand_sale_amount) < Number(rowdata.brand_sale_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.brand_sale_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.brand_sale_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.brand_sale_amount_quantity+"件");
			            		html.push(rowdata.brand_sale_amount+"元");
			            	}
							return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',-1);">'+html.join("")+'</a>';	                		
		                }},
		                { display: '会场',width: 190, render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(area_sale_quantity != '' && area_sale_amount != '') {
			            		if(Number(area_sale_quantity) > Number(rowdata.area_sale_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.area_sale_quantity+"</span>件");
			            		}else if(Number(area_sale_quantity) < Number(rowdata.area_sale_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.area_sale_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.area_sale_quantity+"件");
			            		}
			            		if(Number(area_sale_amount) > Number(rowdata.area_sale_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.area_sale_amount+"</span>元");
			            		}else if(Number(area_sale_amount) < Number(rowdata.area_sale_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.area_sale_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.area_sale_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.area_sale_quantity+"件");
			            		html.push(rowdata.area_sale_amount+"元");
			            	}
		                	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',0);">'+html.join("")+'</a>';	                		
		                }},
			            { display: '新用户专享', width: 190,render: function (rowdata, rowindex) {
			            	var html = [];
			            	if(new_user_sale_quantity != '' && new_user_sale_amount != '') {
			            		if(Number(new_user_sale_quantity) > Number(rowdata.new_user_sale_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.new_user_sale_quantity+"</span>件");
			            		}else if(Number(new_user_sale_quantity) < Number(rowdata.new_user_sale_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.new_user_sale_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.new_user_sale_quantity+"件");
			            		}
			            		if(Number(new_user_sale_amount) > Number(rowdata.new_user_sale_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.new_user_sale_amount+"</span>元");
			            		}else if(Number(new_user_sale_amount) < Number(rowdata.new_user_sale_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.new_user_sale_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.new_user_sale_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.new_user_sale_quantity+"件");
			            		html.push(rowdata.new_user_sale_amount+"元");
			            	}
			            	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',1);">'+html.join("")+'</a>';	                		
		                }},
		                { display: '新用户秒杀', width: 190,render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(new_user_kill_quantity != '' && new_user_kill_amount != '') {
			            		if(Number(new_user_kill_quantity) > Number(rowdata.new_user_kill_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.new_user_kill_quantity+"</span>件");
			            		}else if(Number(new_user_kill_quantity) < Number(rowdata.new_user_kill_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.new_user_kill_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.new_user_kill_quantity+"件");
			            		}
			            		if(Number(new_user_kill_amount) > Number(rowdata.new_user_kill_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.new_user_kill_amount+"</span>元");
			            		}else if(Number(new_user_kill_amount) < Number(rowdata.new_user_kill_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.new_user_kill_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.new_user_kill_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.new_user_kill_quantity+"件");
			            		html.push(rowdata.new_user_kill_amount+"元");
			            	}
		                	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',4);">'+html.join("")+'</a>';	                		
		                }},
		                { display: '单品爆款', width: 190,render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(single_product_quantity != '' && single_product_amount != '') {
			            		if(Number(single_product_quantity) > Number(rowdata.single_product_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.single_product_quantity+"</span>件");
			            		}else if(Number(single_product_quantity) < Number(rowdata.single_product_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.single_product_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.single_product_quantity+"件");
			            		}
			            		if(Number(single_product_amount) > Number(rowdata.single_product_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.single_product_amount+"</span>元");
			            		}else if(Number(single_product_amount) < Number(rowdata.single_product_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.single_product_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.single_product_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.single_product_quantity+"件");
			            		html.push(rowdata.single_product_amount+"元");
			            	}
		                	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',2);">'+html.join("")+'</a>';	                		
		                }},
		                { display: '限时秒杀',width: 190, render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(xg_kill_quantity != '' && xg_kill_amount != '') {
			            		if(Number(xg_kill_quantity) > Number(rowdata.xg_kill_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.xg_kill_quantity+"</span>件");
			            		}else if(Number(xg_kill_quantity) < Number(rowdata.xg_kill_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.xg_kill_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.xg_kill_quantity+"件");
			            		}
			            		if(Number(xg_kill_amount) > Number(rowdata.xg_kill_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.xg_kill_amount+"</span>元");
			            		}else if(Number(xg_kill_amount) < Number(rowdata.xg_kill_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.xg_kill_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.xg_kill_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.xg_kill_quantity+"件");
			            		html.push(rowdata.xg_kill_amount+"元");
			            	}
		                	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',3);">'+html.join("")+'</a>';	                		
		                }}, 
		                { display: '积分商城',width: 190,render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(integral_quantity != '' && integral_amount != '') {
			            		if(Number(integral_quantity) > Number(rowdata.integral_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.integral_quantity+"</span>件");
			            		}else if(Number(integral_quantity) < Number(rowdata.integral_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.integral_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.integral_quantity+"件");
			            		}
			            		if(Number(integral_amount) > Number(rowdata.integral_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.integral_amount+"</span>元");
			            		}else if(Number(integral_amount) < Number(rowdata.integral_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.integral_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.integral_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.integral_quantity+"件");
			            		html.push(rowdata.integral_amount+"元");
			            	}
		                	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',5);">'+html.join("")+'</a>';	                		
		                }},
		                { display: '断码清仓', width: 190,render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(broken_code_quantity != '' && integral_amount != '') {
			            		if(Number(broken_code_quantity) > Number(rowdata.integral_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.broken_code_quantity+"</span>件");
			            		}else if(Number(broken_code_quantity) < Number(rowdata.broken_code_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.broken_code_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.broken_code_quantity+"件");
			            		}
			            		if(Number(broken_code_amount) > Number(rowdata.broken_code_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.broken_code_amount+"</span>元");
			            		}else if(Number(broken_code_amount) < Number(rowdata.broken_code_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.broken_code_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.broken_code_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.broken_code_quantity+"件");
			            		html.push(rowdata.broken_code_amount+"元");
			            	}
		                	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',6);">'+html.join("")+'</a>';	                		
		                }},
		                { display: '商城销售', width: 190,render: function (rowdata, rowindex) {
		                	var html = [];
			            	if(mall_sale_quantity != '' && integral_amount != '') {
			            		if(Number(mall_sale_quantity) > Number(rowdata.mall_sale_quantity) ) {
			            			html.push("<span style='color:green;'>"+rowdata.mall_sale_quantity+"</span>件");
			            		}else if(Number(mall_sale_quantity) < Number(rowdata.mall_sale_quantity) ) {
			            			html.push("<span style='color:red;'>"+rowdata.mall_sale_quantity+"</span>件");
			            		}else {
			            			html.push(rowdata.mall_sale_quantity+"件");
			            		}
			            		if(Number(mall_sale_amount) > Number(rowdata.mall_sale_amount) ) {
			            			html.push("<span style='color:green;'>"+rowdata.mall_sale_amount+"</span>元");
			            		}else if(Number(mall_sale_amount) < Number(rowdata.mall_sale_amount) ) {
			            			html.push("<span style='color:red;'>"+rowdata.mall_sale_amount+"</span>元");
			            		}else {
			            			html.push(rowdata.mall_sale_amount+"</span>元");
			            		}
			            	}else {
			            		html.push(rowdata.mall_sale_quantity+"件");
			            		html.push(rowdata.mall_sale_amount+"元");
			            	}
			            	brand_sale_amount_quantity = rowdata.brand_sale_amount_quantity;
			            	brand_sale_amount = rowdata.brand_sale_amount;
			            	area_sale_quantity = rowdata.area_sale_quantity;
			            	area_sale_amount = rowdata.area_sale_amount;
			            	new_user_sale_quantity = rowdata.new_user_sale_quantity;
			            	new_user_sale_amount = rowdata.new_user_sale_amount;
			            	new_user_kill_quantity = rowdata.new_user_kill_quantity;
			            	new_user_kill_amount = rowdata.new_user_kill_amount;
			            	single_product_quantity = rowdata.single_product_quantity;
			            	single_product_amount = rowdata.single_product_amount;
			            	xg_kill_quantity = rowdata.xg_kill_quantity;
			            	xg_kill_amount = rowdata.xg_kill_amount;
			            	integral_quantity = rowdata.integral_quantity;
			            	integral_amount = rowdata.integral_amount;
			            	broken_code_quantity = rowdata.broken_code_quantity;
			            	broken_code_amount = rowdata.broken_code_amount;
			            	mall_sale_quantity = rowdata.mall_sale_quantity;
			            	mall_sale_amount = rowdata.mall_sale_amount;
		                	return '<a href="javascript:;" onclick="subOrderReportList('+"'"+rowdata.each_day+"'"+',7);">'+html.join("")+'</a>';	                		
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true, //不设置默认为 true
                 beforeSearch: checkSearchCondition
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
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="查看" id="search">
				</div>
				<div id="searchbtn" style="display: none;">查看</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>