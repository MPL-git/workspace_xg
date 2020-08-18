<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <style type="text/css">
 	#day, #month, #year{
 		width: 60px;
 		height: 25px;
 		line-height: 25px;
 		cursor: pointer;
 	}
 	#day, #month{
 		margin-right: 20px;
 	}
 		
 </style>
 <script type="text/javascript">
	 $(function() {
		 $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		 });
		
	 });
	 
	 function formatMoney(s, n) {
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
	 
	 // 日   月    年
	 function subButton(dateFlag) {
		 $("#day").attr("class", "");
		 $("#month").attr("class", "");
		 $("#year").attr("class", "");
		 if('day' == dateFlag) {
			 $("#day").attr("class", "l-button");
		 }else if('month' == dateFlag) {
			 $("#month").attr("class", "l-button");
		 }else if('year' == dateFlag) {
			 $("#year").attr("class", "l-button");
		 }
		 $("#dateFlag").val(dateFlag);
		 $("#beginDate").val("");
		 $("#endDate").val("");
		 $("#searchbtn").click();
	 }
	 
	 //导出
	 function excel(){  
		$("#dataForm").attr("action","${pageContext.request.contextPath}/superCutPriceOrder/exportSuperCutPriceOrderStatistics.shtml");
		$("#dataForm").submit();
	 }
	 
	 //修改 日 月 年
	 function updateSubButton() {
		 var beginDate = $("#beginDate").val();
		 var endDate = $("#endDate").val();
		 if(beginDate != '' || endDate != '') {
			 $("#day").attr("class", "");
			 $("#month").attr("class", "");
			 $("#year").attr("class", "");
			 $("#day").attr("class", "l-button");
		 }
	 }
	 
 	 var listConfig={
	      url:"/superCutPriceOrder/superCutPriceOrderStatisticsList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'时间',name:'date', align:'center', isSort:false, width:100},
							{display:'发起人数',name:'count_member', align:'center', isSort:false, width:100},
							{display:'被邀请人数',name:'sum_cut_price_order_dtl', align:'center', isSort:false, width:100},
							{display:'已下载APP人数',name:'sum_cut_price_order_dtl_status', align:'center', isSort:false, width:120},
							{display:'被邀请人下单人数',name:'sum_combine_order_member', align:'center', isSort:false, width:120},
							{display:'下载率',name:'download_rate', align:'center', isSort:false, width:100},
							{display:'购买率',name:'buy_rate', align:'center', isSort:false, width:100},
							{display:'发起享免单数',name:'count_cut_price_order', align:'center', isSort:false, width:120},
							{display:'审核通过的享免单数',name:'count_cut_price_order_audit_status', align:'center', isSort:false, width:120},
							{display:'平台支出',name:'sum_total_platform_preferential', align:'center', isSort:false, width:100},
							{display:'成功率',name:'success_rate', align:'center', isSort:false, width:100},
							{display:'被邀请人订单数',name:'sum_combine_order', align:'center', isSort:false, width:120},
							{display:'被邀请人消费金额',name:'sum_total_pay_amount', align:'center', isSort:false, width:120},
							{display:'平台收益',name:'sum_commission_amount', align:'center', isSort:false, width:100},
							{display:'消费率',name:'expense_rate', align:'center', isSort:false, width:100}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server" method="post" >
		<input type="hidden" id="dateFlag" name="dateFlag" value="${dateFlag }"  />
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginDate" name="beginDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endDate" name="endDate" class="dateEditor" />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn" onclick="updateSubButton();" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();">
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%; margin-left: 6%;">
					<input type="button" id="day" <c:if test="${dateFlag == 'day' }">class="l-button"</c:if> value="日" onclick="subButton('day');" /> 
					<input type="button" id="month" <c:if test="${dateFlag == 'month' }">class="l-button"</c:if> value="月" onclick="subButton('month');" />
					<input type="button" id="year" <c:if test="${dateFlag == 'year' }">class="l-button"</c:if> value="年" onclick="subButton('year');" />
				</div>
			</div>
		</div>
	</form>
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
