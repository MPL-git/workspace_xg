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
 var listConfig={
     url:"/mchtDeposit/mchtDepositDtlCountEachDayData.shtml",
     listGrid:{ columns: [
			            { display: '日期',name:'eachDay'},
		                { display: '期初余额(元)', render: function (rowdata, rowindex) {
		                	if(rowdata.first){
								return formatMoney(rowdata.first,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
			            { display: '用现金缴纳(元)', render: function (rowdata, rowindex) {
		                	if(rowdata.cashPayment){
								return formatMoney(rowdata.cashPayment,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '用货款抵缴(元)',render: function (rowdata, rowindex) {
		                	if(rowdata.paymentOfGoods){
								return formatMoney(rowdata.paymentOfGoods,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '退保证金(元)', render: function (rowdata, rowindex) {
		                	if(rowdata.refundDeposit){
								return formatMoney(rowdata.refundDeposit,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '违规扣款(元)',render: function (rowdata, rowindex) {
		                	if(rowdata.violateMoney){
								return formatMoney(rowdata.violateMoney,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '申诉成功(元)',render: function (rowdata, rowindex) {
		                	if(rowdata.appealMoney){
								return formatMoney(rowdata.appealMoney,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '售后扣款(元)',render: function (rowdata, rowindex) {
		                	if(rowdata.afterSaleMoney){
								return formatMoney(rowdata.afterSaleMoney,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '期末余额(元)',render: function (rowdata, rowindex) {
		                	if(rowdata.last){
								return formatMoney(rowdata.last,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: false //不设置默认为 true
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
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="create_date_begin" value="${createDateBegin}"/>
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="create_date_end" name="create_date_end" value="${createDateEnd}"/>
				<script type="text/javascript">
					$(function() {
						$("#create_date_end").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div style="width: 15%;display: inline-block;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
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