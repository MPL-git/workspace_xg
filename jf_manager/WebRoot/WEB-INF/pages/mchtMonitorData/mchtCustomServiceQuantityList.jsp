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
	$("#search").bind('click',function(){
		var payDateBegin = $("#payDateBegin").val();
		var payDateEnd = $("#payDateEnd").val();
		var startDay = new Date(payDateBegin);
		var endDay = new Date(payDateEnd);
		var diffDays = (endDay - startDay) / (1000 * 60 * 60 * 24);
		if(diffDays > 62){
			commUtil.alertError("起始日期间隔不能超过62天");
			return false;
		}else{
			$("#searchbtn").click();
		}
	});
	
	// 禁止分页
	 $("#maingrid").ligerGrid({
       usePager:false
   });
   
   //品牌
	$("#productBrandName").ligerComboBox({
     	 width: 135,
         slide: false,
         selectBoxWidth: 450,
         selectBoxHeight: 300,
         valueField: 'id',
         textField: 'name',
         valueFieldID:'productBrandId',
         grid: getGridOptions(false),
         condition:{ fields: [{ name:'name', label:'品牌名', width:90, type:'text' } ]}
     });
}); 

function getGridOptions(checkbox){
	     var options = {
	         columns: [
				{display:'ID',name:'id', align:'center', isSort:false, width:100},
				{display:'品牌',name:'name', align:'center', isSort:false, width:100}
	         ], 
	         switchPageSizeApplyComboBox: false,
		     url: '${pageContext.request.contextPath}/activityNew/getProductBrandList.shtml',
	         pageSize: 10, 
	         checkbox: checkbox
	     };
	     return options;
	 }

function customServiceOrderCount(mchtCode,payDateBegin,payDateEnd,productBrandId) {
	 $.ligerDialog.open({
			height: $(window).height()*0.95,
			width: $(window).width()*0.95,
			title: "每日售后数量统计",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtMonitorData/customServiceOrderCount.shtml?mchtCode="+mchtCode+"&payDateBegin="+payDateBegin+"&payDateEnd="+payDateEnd+"&productBrandId="+productBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 
}

 var listConfig={
     url:"/mchtMonitorData/mchtCustomServiceQuantityData.shtml",
     listGrid:{ columns: [
						{ display: '商家序号',width:70, render: function (rowdata, rowindex) {
							return rowdata.mcht_code;	                		
		                }},
						{ display: '公司名称',width:150, render: function (rowdata, rowindex) {
							return rowdata.company_name;	                		
		                }},
						{ display: '店铺名称',width:150, render: function (rowdata, rowindex) {
							return rowdata.shop_name;	                		
		                }},
						{ display: '经营品牌',width:150, render: function (rowdata, rowindex) {
							return rowdata.brands;	                		
		                }},
			            { display: '支付商品数量',width:80,render: function (rowdata, rowindex) {
			           		return '<span>'+rowdata.pay_product_quantity+'</span>';
		                }},
		                { display: '未发货商品数量',width:100, render: function (rowdata, rowindex) {
		               		return '<span>'+rowdata.delivery_product_quantity+'</span>';
		                }},
		                { display: '退款商品数量',width:80, render: function (rowdata, rowindex) {
		                	return '<span>'+rowdata.refund_quantity+'</span>';
		                }},
		                { display: '退款率',width:70, render: function (rowdata, rowindex) {
							return rowdata.refund_rate;
		                }},
		                { display: '退货商品数量',width:80, render: function (rowdata, rowindex) {
		                	return '<span>'+rowdata.return_goods_quantity+'</span>';
		                }},
		                { display: '退货率',width:70, render: function (rowdata, rowindex) {
							return rowdata.refund_goods_rate;
		                }},
		                { display: '换货商品数量',width:80, render: function (rowdata, rowindex) {
		                	return '<span>'+rowdata.exchange_goods_quantity+'</span>';
		                }},
		                { display: '换货率',width:70, render: function (rowdata, rowindex) {
							return rowdata.exchange_goods_rate;
		                }},
		                { display: '操作',width:60, render: function (rowdata, rowindex) {
		                	var payDateBegin = $("#payDateBegin").val();
		                	var payDateEnd = $("#payDateEnd").val();
		                	var productBrandId = $("#productBrandId").val();
		                	if(!productBrandId){
		                		productBrandId = 0;
		                	}
							return '<a href="javascript:;" onclick="customServiceOrderCount('+"'"+rowdata.mcht_code+"'"+','+"'"+payDateBegin+"'"+','+"'"+payDateEnd+"'"+','+productBrandId+');">查看</a>';
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
					<div class="l-panel-search-item">
						<input type="text" id="payDateBegin" name="payDateBegin" value="${payDateBegin}" />
						<script type="text/javascript">
							$(function() {
								$("#payDateBegin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="payDateEnd" name="payDateEnd" value="${payDateEnd}" />
						<script type="text/javascript">
							$(function() {
								$("#payDateEnd").ligerDateEditor({
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
					<div class="search-td-combobox-condition">
						<input type="text" id="productBrandName" name="productBrandName" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input type="text" id="mchtCode" name="mchtCode" style="width: 135px;">
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">店铺名称：</div>
					<div class="search-td-condition">
						<input type="text" id="shopName" name="shopName" style="width: 135px;">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red">对接人：</div>
					<div class="search-td-condition">
						<select id="platformContactId" name="platformContactId" style="width: 135px;">
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
					<div class="search-td-label" >类目：</div>
					<div class="search-td-condition">
						<select id="productTypeId" name="productTypeId" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach items="${productTypeList }" var="productType">
								<option value="${productType.id }">${productType.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div style="display: inline-block;">
					<div>
						<input type="button" style="width: 60px;height: 23px;cursor: pointer;" value="查看" id="search">
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