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
   
   //品牌
	var productBrandNameComboBox = $("#productBrandName").ligerComboBox({
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
	productBrandNameComboBox.setValue(${productBrandId});
	productBrandNameComboBox.setText("${productBrandName}");
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
	
 var listConfig={
     url:"/mchtMonitorData/customServiceOrderAmountCountList.shtml",
     listGrid:{ columns: [
						{ display: '时间',width:150, render: function (rowdata, rowindex) {
							if(isSunday(rowdata.payDate)){
								return '<span style="color:red;">'+rowdata.payDate+'</span>';
							}else{
								return rowdata.payDate;	                		
							}
		                }},
			            { display: '支付商品金额',width:150,render: function (rowdata, rowindex) {
			           		return '<span>'+rowdata.payOrderAmount+'</span>';
		                }},
		                { display: '未发货商品金额',width:150, render: function (rowdata, rowindex) {
		               		return '<span>'+rowdata.deliveryAmount+'</span>';
		                }},
		                { display: '退款商品金额',width:150, render: function (rowdata, rowindex) {
		                	return '<span>'+rowdata.refundOrderAmount+'</span>';
		                }},
		                { display: '退款率',width:150, render: function (rowdata, rowindex) {
							return formatMoney(rowdata.refundAmountRate,2)+"%";
		                }},
		                { display: '退货商品金额',width:150, render: function (rowdata, rowindex) {
		                	return '<span>'+rowdata.returnBillAmount+'</span>';
		                }},
		                { display: '退货率',width:150, render: function (rowdata, rowindex) {
							return formatMoney(rowdata.refundbillRate,2)+"%";
		                }},
		                { display: '换货商品金额',width:150, render: function (rowdata, rowindex) {
		                	return '<span>'+rowdata.swapOrderAmount+'</span>';
		                }},
		                 { display: '换货率',width:150, render: function (rowdata, rowindex) {
							return formatMoney(rowdata.turnoverRate,2)+"%";
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
						<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}" style="width: 135px;" >
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">店铺名称：</div>
					<div class="search-td-condition">
						<input type="text" id="shopName" name="shopName" style="width: 135px;" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red">对接人：</div>
					<div class="search-td-condition">
						<select id="platformContactId" name="platformContactId" style="width: 135px;" >
							<c:if test="${isContact==1}">
								<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
									<option value="">全部商家</option>
								</c:if>
								<option value="${myContactId}">我对接的商家</option>
								<c:forEach items="${platformAssistantContacts}"
									var="platformAssistantContactList">
									<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
								</c:forEach>
							</c:if>

							<c:if test="${isContact==0}">
								<option value="">全部商家</option>
								<c:forEach items="${platformMchtContacts}"
									var="platformMchtContactList">
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
				<div class="search-td-search-btn">
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