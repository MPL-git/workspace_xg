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
		 listModel.initdataPage();
	 }
	 
	 //导出
	 function excel(){  
		$("#dataForm").attr("action","${pageContext.request.contextPath}/signInCnf/exportSignInCnfStatistics.shtml");
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
	      url:"/signInCnf/signInCnfStatisticsList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'时间',name:'date', align:'center', isSort:false, width:120},
							{display:'签到人数',name:'qd_count', align:'center', isSort:false, width:120},
							{display:'其中新用户人数',name:'new_member_count', align:'center', isSort:false, width:120},
							{display:'累计签到金额',name:'lj_amount_sum', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.lj_amount_sum, 2);
			                }},
							{display:'申请提现金额',name:'tx_amount_sum', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.tx_amount_sum, 2);
			                }},
			                {display:'未审核金额',name:'tx_ws_amount_sum', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.tx_ws_amount_sum, 2);
			                }},
			                {display:'审核失败金额',name:'tx_sb_amount_sum', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.tx_sb_amount_sum, 2);
			                }},
			                {display:'审核通过金额',name:'tx_cg_amount_sum', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.tx_cg_amount_sum, 2);
			                }},
			                {display:'微信红包（通过）',name:'tx_cgxj_amount_sum', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.tx_cgxj_amount_sum, 2);
			                }},
			                {display:'抵用券（通过）',name:'tx_cgyh_amount_sum', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return formatMoney(rowdata.tx_cgyh_amount_sum, 2);
			                }},
			                {display:'购买人数',name:'gm_count', align:'center', isSort:false, width:120},
							{display:'其中新用户购买人数',name:'xy_gm_count', align:'center', isSort:false, width:150},
							{display:'订单数',name:'dd_count', align:'center', isSort:false, width:120},
							{display:'其中新用户产生订单数',name:'xy_dd_count', align:'center', isSort:false, width:150},
							{display:'预估收益',name:'yg_commission_amount_sum', align:'center', isSort:false, width:120},
							{display:'其中新用户产生预估收益',name:'xy_yg_commission_amount_sum', align:'center', isSort:false, width:150},
							{display:'消费转化率%',name:'con_percent', align:'center', isSort:false, width:120}
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
