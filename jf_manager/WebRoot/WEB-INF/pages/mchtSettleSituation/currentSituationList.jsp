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
function exportExcel() {
	var mchtCode = $("#mchtCode").val();
	var name = $("#name").val();
	location.href="${pageContext.request.contextPath}/mchtSettleSituation/exportCurrent.shtml?mchtCode="+mchtCode+"&name="+name;	
}
 
 function getLastDay(year,month)     
 {     
  var new_year = year;  //取当前的年份     
  var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）     
  if(month>12)      //如果当前大于12月，则年份转到下一年     
  {     
  new_month -=12;    //月份减     
  new_year++;      //年份增     
  }     
  var new_date = new Date(new_year,new_month,1);        //取当年当月中的第一天     
  return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期     
 }   
 
 var listConfig={
     url:"/mchtSettleSituation/currentSituationData.shtml",
     listGrid:{ columns: [
			            { display: '商家序号',name:'', render: function (rowdata, rowindex) {
			            	return rowdata.mchtCode+"-"+rowdata.mchtId;	
		                }},
		                { display: '商家公司名称', name:'companyName'},
		                { display: '店铺名称', name:'shopName'},
			            { display: '期初可结算', render: function (rowdata, rowindex) {
			            	if(rowdata.beginSettleAmout){
								return rowdata.beginSettleAmout;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '期初未出账',render: function (rowdata, rowindex) {
			            	if(rowdata.beginNotOutAccount){
								return rowdata.beginNotOutAccount;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '跨期结算',render: function (rowdata, rowindex) {
		                	if(rowdata.acrossMonthSettleAmount){
								return rowdata.acrossMonthSettleAmount;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '跨期退款',render: function (rowdata, rowindex) {
		                	if(rowdata.acrossMonthReturnAmount){
								return rowdata.acrossMonthReturnAmount;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '本期可结算', render: function (rowdata, rowindex) {
		                	if(rowdata.currentMonthSettleAmount){
								return formatMoney(rowdata.currentMonthSettleAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '本期未出账',render: function (rowdata, rowindex) {
		                	if(rowdata.currentNotOutAccount){
		                		return formatMoney(rowdata.currentNotOutAccount,2);	      
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '直赔单',render: function (rowdata, rowindex) {
		                	if(rowdata.refundAmount){
		                		var year = $("#year").val();
		                		var month = $("#month").val();
		                		var dateStr = year+"-"+month;
		                		var success_date_begin = dateStr+"-01";
		                		var lastDay = getLastDay(year,month);
		                		var success_date_end = dateStr+"-"+lastDay;
		                		var serviceType = "D";
								return '<a href="javascript:;" onclick="refundOrder('+"'"+rowdata.mchtCode+"'"+','+"'"+serviceType+"'"+','+"'"+success_date_begin+"'"+','+"'"+success_date_end+"'"+');">'+formatMoney(rowdata.refundAmount,2)+'</a>';	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '实付款',render: function (rowdata, rowindex) {
		                	if(rowdata.payAmount){
								return formatMoney(rowdata.payAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '抵缴保证金',render: function (rowdata, rowindex) {
		                	if(rowdata.deductionDepositTotal){
		                		return formatMoney(rowdata.deductionDepositTotal,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '折让',render: function (rowdata, rowindex) {
		                	if(rowdata.discount){
								return rowdata.discount;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '期末可结算',render: function (rowdata, rowindex) {
		                	if(rowdata.endSettleAmount){
								return rowdata.endSettleAmount;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '期末未出账',render: function (rowdata, rowindex) {
		                	if(rowdata.endNotOutAccount){
								return rowdata.endNotOutAccount;	                		
		                	}else{
		                		return "0.00";
		                	}
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      },  							
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
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="name" name="name" >
			</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >主营类目：</div>
				<div class="search-td-condition" >
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled">
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
			</div>
		 	<div class="search-td">
			<div class="search-td-label">品牌：</div>
			<div  class="search-td-condition">
				<input name="productBrandName">
			</div>
		    </div>
			 <div  style="display: inline-flex;">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索" id="searchbtn">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="导出汇总表" id="export" onclick="exportExcel();">
				</div>
			 </div>
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>