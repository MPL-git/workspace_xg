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
     url:"/eachDayNeedPayCount/data.shtml",
     listGrid:{ columns: [
			            { display: '付款日期', name:'eachDay'},
			            { display: '销售收款金额',render: function (rowdata, rowindex) {
		                	if(rowdata.totalSaleAmount){
		                		return formatMoney(rowdata.totalSaleAmount,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '商品数', render: function (rowdata, rowindex) {
		                	if(rowdata.totalQuantity){
		                		return rowdata.totalQuantity;
		                	}else{
		                		return "0";
		                	}
		                }},
			            { display: '应结款', render: function (rowdata, rowindex) {
		                	if(rowdata.popTotalSettleAmount || rowdata.poolTotalSettleAmount){
		                		var total = Number(rowdata.popTotalSettleAmount)+Number(rowdata.poolTotalSettleAmount);
		                		return formatMoney(total,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: 'POP商品数',render: function (rowdata, rowindex) {
		                	if(rowdata.popTotalQuantity){
		                		return rowdata.popTotalQuantity;
		                	}else{
		                		return "0";
		                	}
		                }},
		                { display: 'POP应结款',render: function (rowdata, rowindex) {
		                	if(rowdata.popTotalSettleAmount){
		                		return formatMoney(rowdata.popTotalSettleAmount,2);
		                	}else{
			                	return "0.00";
		                	}
		                }},
		                { display: 'SPOP商品数',render: function (rowdata, rowindex) {
		                	if(rowdata.poolTotalQuantity){
		                		return rowdata.poolTotalQuantity;
		                	}else{
			                	return "0";
		                	}
		                }},
		                { display: 'SPOP应结款',render: function (rowdata, rowindex) {
		                	if(rowdata.poolTotalSettleAmount){
								return formatMoney(rowdata.poolTotalSettleAmount,2);	                		
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
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${payDateBegin}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_begin").ligerDateEditor( {
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
				<input type="text" id="pay_date_end" name="pay_date_end" value="${payDateEnd}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_end").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			 
			<div class="search-td-search-btn" style="display: inline-flex;">
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