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
			var defaultYear = '${year }';
			var defaultMonth = '${month }';
			var year = $("#year").val();
			var month = $("#month").val();
			var maxDate = new Date(defaultYear,defaultMonth);
			var date = new Date(year,month);
			if(date>maxDate){
				commUtil.alertError("对不起，时间不能超过上个月。");
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
		var defaultYear = '${year }';
		var defaultMonth = '${month }';
		var year = $("#year").val();
		var month = $("#month").val();
		var maxDate = new Date(defaultYear,defaultMonth);
		var date = new Date(year,month);
		if(date>maxDate){
			commUtil.alertError("对不起，时间不能超过上个月。");
			return false;
		}else{
			$("#dataForm").attr("action","${pageContext.request.contextPath}/mchtMonthTrade/exportMchtMonthTradeList.shtml");
			$("#dataForm").submit();
		}
	 }
	 
	 //查看商品
	 function mchtMonthTradeChildList(mchtId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title: "商家月往来详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtMonthTrade/mchtMonthTradeManager.shtml?statusPage=2&mchtId="+mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //折扣调差
	 function updateDiscount(id, endUnpay, discount) {
		 $.ligerDialog.open({
				height: 400,
				width: 500,
				title: "折扣调差",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtMonthTrade/updateDiscountManager.shtml?mchtMonthTradeId="+id+"&endUnpay="+endUnpay+"&discount="+discount,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	 
 	 var listConfig={
	      url:"/mchtMonthTrade/mchtMonthTradeList.shtml",
 		  btnItems:[],
	      listGrid:{ columns: [
							{display:'月份',name:'tradeMonth', align:'center', isSort:false, width:100},
							{display:'商家序号',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								return "<a href=\"javascript:mchtMonthTradeChildList(" + rowdata.mchtId + ");\">"+rowdata.mchtCode+"-"+rowdata.mchtId+"</a>";
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
	                        	var html = [];
	                        	if(${managerFlag }) {
	                        		if(rowdata.discount) {
		                        		html.push("<a href=\"javascript:updateDiscount(" + rowdata.id + ", " + rowdata.endUnpay + ", " + rowdata.discount + ");\">"+formatMoney(rowdata.discount, 2)+"</a>");
	                        		}else {
	                        			html.push("<a href=\"javascript:updateDiscount(" + rowdata.id + ", " + rowdata.endUnpay + ", " + rowdata.discount + ");\">0.00</a>");	                        			
	                        		}
	                        	}else {
	                        		if(rowdata.discount) {
		                        		html.push(formatMoney(rowdata.discount, 2));
	                        		}else {
	                        			html.push("0.00");	                        			
	                        		}
	                        	}
	                        	return html.join("");
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
					<div class="search-td-label" style="float:left;">年份：</div>
					<div class="l-panel-search-item" >
						<select id="year" name="year" style="width: 150px;">
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
			 <div class="search-tr"  > 
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
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<input type="button" class="l-button" ligeruiid="searchbtn" style="width: 60px;" value="搜索" id="searchbtn2">
					<input type="hidden" id="searchbtn">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 23px;cursor: pointer;" value="导出" onclick="excel();">
					</div>
				</div>				
			</div>
		  </div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
