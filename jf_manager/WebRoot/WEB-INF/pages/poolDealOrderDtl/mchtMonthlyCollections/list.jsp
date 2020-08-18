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
function exportByMchtId(mchtId,mchtMonthlyCollectionsId,date_begin,date_end) {
	location.href="${pageContext.request.contextPath}/poolDealOrderDtl/mchtMonthlyCollections/exportByMchtId.shtml?mchtId="+mchtId+"&mchtMonthlyCollectionsId="+mchtMonthlyCollectionsId+"&date_begin="+date_begin+"&date_end="+date_end;	
}

$(function(){
	$("#export").bind('click',function(){
		var mchtCode = $("#mchtCode").val();
		var name = $("#name").val();
		var year = $("#year").val();
		var month = $("#month").val();
		location.href="${pageContext.request.contextPath}/poolDealOrderDtl/mchtMonthlyCollections/exportTotal.shtml?mchtCode="+mchtCode+"&name="+name+"&year="+year+"&month="+month;
	});
});

function serviceOrderList(mchtId,dateBegin,dateEnd){
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "直赔单列表",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/afterService/list.shtml?mchtId=" + mchtId+"&date_begin="+dateBegin+"&date_end="+dateEnd+"&serviceType=D",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function poolDealOrderDtlList(mchtCode,dateBegin,dateEnd,accept,refund){
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "SPOP交易商品明细",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/poolDealOrderDtl/list.shtml?mchtCode=" + mchtCode+"&date_begin="+dateBegin+"&date_end="+dateEnd+"&accept="+accept+"&refund="+refund,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
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
     url:"/poolDealOrderDtl/mchtMonthlyCollections/data.shtml",
     listGrid:{ columns: [
			            { display: '商家序号',name:'mchtCode'},
		                { display: '商家简称', name:'mchtShortName'},
		                { display: '店铺名称', name:'mchtShopName'},
		                { display: '商家公司名称', name:'mchtCompanyName'},
			            { display: '期初结欠', render: function (rowdata, rowindex) {
			            	if(rowdata.beginUnpay){
								return formatMoney(rowdata.beginUnpay,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '本月收款应结',render: function (rowdata, rowindex) {
			            	if(rowdata.settleAmount){
			            		var year = $("#year").val();
		                		var month = $("#month").val();
		                		var monthLastDay = getLastDay(year,month);
		                		var date_begin = year+"-"+month+"-01";
		                		var date_end = year+"-"+month+"-"+monthLastDay;
		                		var accept = "1";
		                		var refund = "0";
								return '<a href="javascript:;" onclick="poolDealOrderDtlList('+"'"+rowdata.mchtCode+"'"+','+"'"+date_begin+"'"+','+"'"+date_end+"'"+','+accept+','+refund+');">'+formatMoney(rowdata.settleAmount,2)+'</a>';
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '本月退款应扣',render: function (rowdata, rowindex) {
		                	if(rowdata.returnOrderAmount){
		                		var year = $("#year").val();
		                		var month = $("#month").val();
		                		var monthLastDay = getLastDay(year,month);
		                		var date_begin = year+"-"+month+"-01";
		                		var date_end = year+"-"+month+"-"+monthLastDay;
		                		var accept = "0";
		                		var refund = "1";
								return '<a href="javascript:;" onclick="poolDealOrderDtlList('+"'"+rowdata.mchtCode+"'"+','+"'"+date_begin+"'"+','+"'"+date_end+"'"+','+accept+','+refund+');">'+formatMoney(rowdata.returnOrderAmount,2)+'</a>';
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '直赔单应扣',render: function (rowdata, rowindex) {
		                	if(rowdata.refundAmount){
		                		var year = $("#year").val();
		                		var month = $("#month").val();
		                		var monthLastDay = getLastDay(year,month);
		                		var date_begin = year+"-"+month+"-01";
		                		var date_end = year+"-"+month+"-"+monthLastDay;
								return '<a href="javascript:;" onclick="serviceOrderList('+rowdata.mchtId+','+"'"+date_begin+"'"+','+"'"+date_end+"'"+');">'+formatMoney(rowdata.refundAmount,2)+'</a>';
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '本月总应付',render: function (rowdata, rowindex) {
		                	if(rowdata.needPayAmount){
		                		return formatMoney(rowdata.needPayAmount,2);
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '本月付款',render: function (rowdata, rowindex) {
		                	if(rowdata.payAmount){
								return '<a href="javascript:;" onclick="payToMchtLogList('+"'"+rowdata.mchtCode+"'"+','+"'"+rowdata.settleDate+"'"+');">'+formatMoney(rowdata.payAmount,2)+'</a>';	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '期末实欠',render: function (rowdata, rowindex) {
		                	if(rowdata.endUnpay){
								return formatMoney(rowdata.endUnpay,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '导出',render: function (rowdata, rowindex) {
		                	var year = $("#year").val();
	                		var month = $("#month").val();
	                		var monthLastDay = getLastDay(year,month);
	                		var date_begin = year+"-"+month+"-01";
	                		var date_end = year+"-"+month+"-"+monthLastDay;
		                	return '<a href="javascript:;" onclick="exportByMchtId('+rowdata.mchtId+','+rowdata.id+','+"'"+date_begin+"'"+','+"'"+date_end+"'"+');">导出</a>';
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      },  							
     container:{
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
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="name" name="name" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">年份：</div>
			<div class="l-panel-search-item" >
				<select id="year" name="year" style="width: 150px;">
					<option value="">请选择</option>
					<option value="2017" <c:if test="${year eq '2017'}">selected="selected"</c:if>>2017</option>
					<option value="2018" <c:if test="${year eq '2018'}">selected="selected"</c:if>>2018</option>
					<option value="2019" <c:if test="${year eq '2019'}">selected="selected"</c:if>>2019</option>
					<option value="2020" <c:if test="${year eq '2020'}">selected="selected"</c:if>>2020</option>
				</select>
		 	 </div>
			 </div>
			 
		 	<div class="search-td">
			<div class="search-td-label" style="float:left;">月份：</div>
			<div class="l-panel-search-item" >
				<select id="month" name="month" style="width: 150px;">
					<option value="01" <c:if test="${month eq '01'}">selected="selected"</c:if>>01</option>
					<option value="02" <c:if test="${month eq '02'}">selected="selected"</c:if>>02</option>
					<option value="03" <c:if test="${month eq '03'}">selected="selected"</c:if>>03</option>
					<option value="04" <c:if test="${month eq '04'}">selected="selected"</c:if>>04</option>
					<option value="05" <c:if test="${month eq '05'}">selected="selected"</c:if>>05</option>
					<option value="06" <c:if test="${month eq '06'}">selected="selected"</c:if>>06</option>
					<option value="07" <c:if test="${month eq '07'}">selected="selected"</c:if>>07</option>
					<option value="08" <c:if test="${month eq '08'}">selected="selected"</c:if>>08</option>
					<option value="09" <c:if test="${month eq '09'}">selected="selected"</c:if>>09</option>
					<option value="10" <c:if test="${month eq '10'}">selected="selected"</c:if>>10</option>
					<option value="11" <c:if test="${month eq '11'}">selected="selected"</c:if>>11</option>
					<option value="12" <c:if test="${month eq '12'}">selected="selected"</c:if>>12</option>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 65px;height: 30px;cursor: pointer;" value="导出汇总表" id="export">
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