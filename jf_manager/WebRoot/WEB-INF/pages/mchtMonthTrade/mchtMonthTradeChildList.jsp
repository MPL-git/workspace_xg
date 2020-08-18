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
 
 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
		$("#searchbtn2").bind('click',function(){
			var defaultYear = '${yearFlag }';
			var year = $("#yearFlag").val();
			var maxDate = new Date(defaultYear);
			var date = new Date(year);
			if(date>maxDate){
				commUtil.alertError("对不起，时间不能超过${yearFlag }。");
				return false;
			}else{
				$("#searchbtn").click();
			}
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
	 
	 function getLastDay(year,month) {     
		  var new_year = year;  //取当前的年份     
		  var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）     
		  if(month>12) {     //如果当前大于12月，则年份转到下一年     
		  	new_month -=12;    //月份减     
		  	new_year++;      //年份增     
		  }     
		  var new_date = new Date(new_year,new_month,1);        //取当年当月中的第一天     
		  return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期     
	 } 
	 
	 //导出
	 function excel(){  
		var defaultYear = '${yearFlag }';
		var year = $("#yearFlag").val();
		var maxDate = new Date(defaultYear);
		var date = new Date(year);
		if(date>maxDate){
			commUtil.alertError("对不起，时间不能超过${yearFlag }。");
			return false;
		}else{
			$("#dataForm").attr("action","${pageContext.request.contextPath}/mchtMonthTrade/exportMchtMonthTradeList.shtml");
			$("#dataForm").submit();
		}
	 }
	 
 	 var listConfig={
	      url:"/mchtMonthTrade/mchtMonthTradeList.shtml",
 		  btnItems:[],
	      listGrid:{ columns: [
							{display:'月份',name:'tradeMonth', align:'center', isSort:false, width:100},
							{display:'商家序号',name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
								return rowdata.mchtCode+"-"+rowdata.mchtId;	                		
	                        }},
							{display:'公司名称',name:'companyName', align:'center', isSort:false, width:180},
							{display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180},
							{display:'初期结欠',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.beginUnpay){
									return formatMoney(rowdata.beginUnpay, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
							{display:'本期应结货款',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.currentMonthSettleAmount){
									return formatMoney(rowdata.currentMonthSettleAmount, 2);	                		
			                	}else {
			                		return "0.00";
			                	}
	                        }},
							{display:'保证金现缴',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.currentDepositAmount){
									return formatMoney(rowdata.currentDepositAmount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'本期付款',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.currentPayAmount){
									return formatMoney(rowdata.currentPayAmount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'应扣违规',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.violateNeedDeduct){
									return formatMoney(rowdata.violateNeedDeduct, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'保证金往来',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.depositDtl){
									return formatMoney(rowdata.depositDtl, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'折扣调差',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.discount){
									return formatMoney(rowdata.discount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'期末结欠',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.endUnpay){
									return "<span style='color: blue;'>"+formatMoney(rowdata.endUnpay, 2)+"</span>";	                		
			                	}else{
			                		return "<span style='color: blue;'>0.00</span>";
			                	}
	                        }},
	                        {display:'本期订单实收金额',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.totalOrderPayAmount){
									return formatMoney(rowdata.totalOrderPayAmount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'已收保证金',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.collectDepositAmount){
									return formatMoney(rowdata.collectDepositAmount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'本期增减',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.currentChangeAmount){
									return formatMoney(rowdata.currentChangeAmount, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }},
	                        {display:'保证金余额',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if(rowdata.depositBalance){
									return formatMoney(rowdata.depositBalance, 2);	                		
			                	}else{
			                		return "0.00";
			                	}
	                        }}
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
<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">年份：</div>
					<div class="l-panel-search-item" >
						<select id="yearFlag" name="yearFlag" style="width: 150px;">
							<option value="2017" <c:if test="${yearFlag eq '2017'}">selected="selected"</c:if>>2017</option>
							<option value="2018" <c:if test="${yearFlag eq '2018'}">selected="selected"</c:if>>2018</option>
							<option value="2019" <c:if test="${yearFlag eq '2019'}">selected="selected"</c:if>>2019</option>
							<option value="2020" <c:if test="${yearFlag eq '2020'}">selected="selected"</c:if>>2020</option>
						</select>
				 	 </div>
				</div>
				<input type="hidden" name="mchtId" value="${mchtId }" >
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<input type="button" class="l-button" ligeruiid="searchbtn" style="width: 60px;" value="搜索" id="searchbtn2">
					<input type="hidden" id="searchbtn">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 23px;cursor: pointer;" value="导出" onclick="excel();">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
