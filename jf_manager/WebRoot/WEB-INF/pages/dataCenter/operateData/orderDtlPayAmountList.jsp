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
 var yd_pay_amount_sum = "";
 var fz_pay_amount_sum = "";
 var xx_pay_amount_sum = "";
 var zb_pay_amount_sum = "";
 var sh_pay_amount_sum = "";
 var sm_pay_amount_sum = "";
 var my_pay_amount_sum = "";
 var mz_pay_amount_sum = "";
 var sp_pay_amount_sum = "";
 var pay_amount_sum = "";
 $(function(){
 	$(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelWidth : 150,
		labelAlign : 'left'
	});
	 
	// 禁止分页
	$("#maingrid").ligerGrid({
	      usePager:false
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
 
 function isSunday(eachDay){
	 var dt = new Date(eachDay);
	 if(dt.getDay() == 0){
		 return true;
	 }else{
		 return false;
	 }
 }
 
 function checkSearchCondition(){
     var beginPayDate = $("#beginPayDate").val();
     var endPayDate = $("#endPayDate").val();
     if(beginPayDate == "" ){
    	 commUtil.alertError("请选择付款开始日期！"); 
    	 return false;
     }
     if(endPayDate == ""){
    	 commUtil.alertError("请选择付款结束日期！"); 
    	 return false;
     }
	 var startTime =  new Date(Date.parse(beginPayDate)).getTime();
	 var endTime =  new Date(Date.parse(endPayDate)).getTime();
	 var dates = Math.abs((startTime - endTime))/(1000*60*60*24);   
     if(dates > 61){
    	 commUtil.alertError("付款时间段不能超过62天！"); 
    	 return false;
     }
     return true;
 }
 
 var listConfig={
     url:"/operateData/oneProductTypeReport/orderDtlPayAmountData.shtml",
     listGrid:{ columns: [
						{ display: '日期', name: 'pay_date', width: 120 },
						{ display: '运动户外', name: 'yd_pay_amount_sum', width: 120, hide:${!ydProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(yd_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(yd_pay_amount_sum) != Number(rowdata.yd_pay_amount_sum) ){
   	   		                   	if(Number(yd_pay_amount_sum) > Number(rowdata.yd_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.yd_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(yd_pay_amount_sum) < Number(rowdata.yd_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.yd_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.yd_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '服装配饰', name: 'fz_pay_amount_sum', width: 120, hide:${!fzProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(fz_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(fz_pay_amount_sum) != Number(rowdata.fz_pay_amount_sum) ){
								if(Number(fz_pay_amount_sum) > Number(rowdata.fz_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.fz_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(fz_pay_amount_sum) < Number(rowdata.fz_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.fz_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.fz_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '鞋靴箱包', name: 'xx_pay_amount_sum', width: 120, hide:${!xxProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(xx_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(xx_pay_amount_sum) != Number(rowdata.xx_pay_amount_sum) ){
   	   		                   	if(Number(xx_pay_amount_sum) > Number(rowdata.xx_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.xx_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(xx_pay_amount_sum) < Number(rowdata.xx_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.xx_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.xx_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '钟表珠宝', name: 'zb_pay_amount_sum', width: 120, hide:${!zbProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(zb_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(zb_pay_amount_sum) != Number(rowdata.zb_pay_amount_sum) ){
   	   		                   	if(Number(zb_pay_amount_sum) > Number(rowdata.zb_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.zb_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(zb_pay_amount_sum) < Number(rowdata.zb_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.zb_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.zb_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '生活家居', name: 'sh_pay_amount_sum', width: 120, hide:${!shProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(sh_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(sh_pay_amount_sum) != Number(rowdata.sh_pay_amount_sum) ){
   	   		                   	if(Number(sh_pay_amount_sum) > Number(rowdata.sh_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.sh_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(sh_pay_amount_sum) < Number(rowdata.sh_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.sh_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.sh_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '数码家电', name: 'sm_pay_amount_sum', width: 120, hide:${!smProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(sm_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(sm_pay_amount_sum) != Number(rowdata.sm_pay_amount_sum) ){
   	   		                   	if(Number(sm_pay_amount_sum) > Number(rowdata.sm_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.sm_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(sm_pay_amount_sum) < Number(rowdata.sm_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.sm_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.sm_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '母婴童装', name: 'my_pay_amount_sum', width: 120, hide:${!myProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(my_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(my_pay_amount_sum) != Number(rowdata.my_pay_amount_sum) ){
   	   		                   	if(Number(my_pay_amount_sum) > Number(rowdata.my_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.my_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(my_pay_amount_sum) < Number(rowdata.my_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.my_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.my_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '美妆个护', name: 'mz_pay_amount_sum', width: 120, hide:${!mzProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(mz_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(mz_pay_amount_sum) != Number(rowdata.mz_pay_amount_sum) ){
   	   		                   	if(Number(mz_pay_amount_sum) > Number(rowdata.mz_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.mz_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(mz_pay_amount_sum) < Number(rowdata.mz_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.mz_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.mz_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '食品超市', name: 'sp_pay_amount_sum', width: 120, hide:${!spProductType }, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(sp_pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(sp_pay_amount_sum) != Number(rowdata.sp_pay_amount_sum) ){
   	   		                   	if(Number(sp_pay_amount_sum) > Number(rowdata.sp_pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.sp_pay_amount_sum+"</span>");
   	   		                   	}else if(Number(sp_pay_amount_sum) < Number(rowdata.sp_pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.sp_pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.sp_pay_amount_sum);
   		                	}
							return html.join("");
 		                }},
						{ display: '每日汇总', name: 'pay_amount_sum', width: 120, render: function(rowdata, rowindex) {
   		                	var html = [];
							if(pay_amount_sum !='' && rowdata.pay_date != '类目汇总' && Number(pay_amount_sum) != Number(rowdata.pay_amount_sum) ){
   	   		                   	if(Number(pay_amount_sum) > Number(rowdata.pay_amount_sum)) {
   	   		                  		html.push("<span style='color:green;'>"+rowdata.pay_amount_sum+"</span>");
   	   		                   	}else if(Number(pay_amount_sum) < Number(rowdata.pay_amount_sum)){
   	   		                  		html.push("<span style='color:red;'>"+rowdata.pay_amount_sum+"</span>");
   	   		                   	}
   		                	}else {
   		                		html.push(rowdata.pay_amount_sum);
   		                	}
							if(rowdata.pay_date == '类目汇总') {
								yd_pay_amount_sum = "";
								fz_pay_amount_sum = "";
								xx_pay_amount_sum = "";
								zb_pay_amount_sum = "";
								sh_pay_amount_sum = "";
								sm_pay_amount_sum = "";
								my_pay_amount_sum = "";
								mz_pay_amount_sum = "";
								sp_pay_amount_sum = "";
								pay_amount_sum = "";
							}else {
								yd_pay_amount_sum = rowdata.yd_pay_amount_sum+"";
								fz_pay_amount_sum = rowdata.fz_pay_amount_sum+"";
								xx_pay_amount_sum = rowdata.xx_pay_amount_sum+"";
								zb_pay_amount_sum = rowdata.zb_pay_amount_sum+"";
								sh_pay_amount_sum = rowdata.sh_pay_amount_sum+"";
								sm_pay_amount_sum = rowdata.sm_pay_amount_sum+"";
								my_pay_amount_sum = rowdata.my_pay_amount_sum+"";
								mz_pay_amount_sum = rowdata.mz_pay_amount_sum+"";
								sp_pay_amount_sum = rowdata.sp_pay_amount_sum+"";
								pay_amount_sum = rowdata.pay_amount_sum+"";
							}
							return html.join("");
 		                }}
		         ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true, //不设置默认为 true
                 beforeSearch: checkSearchCondition
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
	<c:if test="${productTypeStatus }">
		<form id="dataForm" runat="server">
			<input type="hidden" id="ydProductType" name="ydProductType" value="${ydProductType }" />
			<input type="hidden" id="xxProductType" name="xxProductType" value="${xxProductType }" />
			<input type="hidden" id="fzProductType" name="fzProductType" value="${fzProductType }" />
			<input type="hidden" id="shProductType" name="shProductType" value="${shProductType }" />
			<input type="hidden" id="zbProductType" name="zbProductType" value="${zbProductType }" />
			<input type="hidden" id="smProductType" name="smProductType" value="${smProductType }" />
			<input type="hidden" id="mzProductType" name="mzProductType" value="${mzProductType }" />
			<input type="hidden" id="myProductType" name="myProductType" value="${myProductType }" />
			<input type="hidden" id="spProductType" name="spProductType" value="${spProductType }" />
			<div class="search-pannel">
				<div class="search-tr"  > 
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
						<div class="l-panel-search-item">
							<input type="text" id="beginPayDate" name="beginPayDate" value="${beginPayDate }" class="dateEditor" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="endPayDate" name="endPayDate" value="${endPayDate }" class="dateEditor" />
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">栏目：</div>
						<div class="search-td-combobox-condition">
							<select id="activityType" name="activityType" style="width: 135px;" >
								<option value="">全部</option>
								<option value="-1">品牌特卖</option>
								<option value="0">会场</option>
								<option value="1">新用户专享</option>
								<option value="4">新用户秒杀</option>
								<option value="2">单品爆款</option>
								<option value="3">醒购秒杀</option>
								<option value="5">积分商城</option>
								<option value="6">断码清仓</option>
								<option value="7">商城销售</option>
								<option value="8">砍价免费拿</option>
								<option value="9">邀请享免单</option>
							</select>
						</div>
					</div>
					<div class="search-td-search-btn">
						<div id="searchbtn" >查看</div>
					</div>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
	</c:if>
	<c:if test="${!productTypeStatus }">
		<div style="color:red; margin: 10px 0px 0px 20px;">
			<h2>你没有负责的类目</h2>
		</div>
	</c:if>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>