<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style type="text/css">
.color-s0,.color-fs1{color: #0000FF;}
.color-s1,.color-fs2{color: #008000;}
.color-s4{color: #333333;}
.color-fs0{color: #000000;}
</style>
<script type="text/javascript">
var totalCount = "";
var combineAmount = "";
var wxAmount = "";
var zfbAmount = "";
var gzhAmount = "";
var wxCount = "";
var zfbCount = "";
var gzhCount = "";

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
$(function(){
	$("#export").bind('click',function(){
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		location.href="${pageContext.request.contextPath}/combineDepositOrderCount/export.shtml?pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end;
	});

	$(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left',
		labelwidth: 120,
		width:120
	});
	
});

function combineList(payDate,paymentId) {
	/* $.ligerDialog.open({
 		height: $(window).height()-100,
		width: $(window).width()-100,
		title: "收款母订单列表",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/receivables/combine/list.shtml?paymentId=" + paymentId+"&payDate="+payDate,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	}); */
}

function toLockDate(lockDate) {
	$.ligerDialog.open({
 		height: $(window).height()*0.45,
		width: $(window).width()*0.30,
		title: "锁住收款日期操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/receivables/count/toLockDate.shtml?lockDate="+lockDate,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function checkSearchCondition(){
	totalCount = "";
	combineAmount = "";
	wxAmount = "";
	zfbAmount = "";
	gzhAmount = "";
	wxCount = "";
	zfbCount = "";
	gzhCount = "";
    return true;
 }

  var listConfig={
		  
	  url:"/combineDepositOrderCount/data.shtml",
      
      listGrid:{ columns: [{ display: "付款日期", name:'each_day'},
 		                { display: '笔数', render: function (rowdata, rowindex) {
 		                	var html = [];
 		                	var count = Number(rowdata.wx_count) + Number(rowdata.zfb_count) + Number(rowdata.gzh_count);
			            	if(count != rowdata.total_count){
			            		html.push(rowdata.total_count+"<span style='color:red;'>[异常]</span>");	                		
		                	}else if(totalCount != ''){
		                		if(Number(totalCount) > Number(rowdata.total_count) ) {
			            			html.push("<span style='color:green;'>"+ rowdata.total_count +"</span>");
			            		}else if(Number(totalCount) < Number(rowdata.total_count) ) {
			            			html.push("<span style='color:red;'>"+ rowdata.total_count +"</span>");
			            		}else {
			            			html.push(rowdata.total_count);
			            		}
		                	}else {
		                		html.push(rowdata.total_count);
		                	}
			            	return html.join("");
		                }},
		                { display: "定金总金额", name:'combine_amount'},
		                { display: '微信APP/H5金额', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(wxAmount != ''){
		                		if(Number(wxAmount) > Number(formatMoney(rowdata.wx_amount, 2)) ) {
		                			html.push("<a href='javascript:;' style='color:green;' onclick='combineList(\""+ rowdata.each_day +"\",1);'>"+ formatMoney(rowdata.wx_amount, 2) +"</a>");
			            		}else if(Number(wxAmount) < Number(formatMoney(rowdata.wx_amount, 2)) ) {
			            			html.push("<a href='javascript:;' style='color:red;' onclick='combineList(\""+ rowdata.each_day +"\",1);'>"+ formatMoney(rowdata.wx_amount, 2) +"</a>");
			            		}else {
			            			html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.each_day +"\",1);'>"+ formatMoney(rowdata.wx_amount, 2) +"</a>");
			            		}
		                	}else {
		                		html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.each_day +"\",1);'>"+ formatMoney(rowdata.wx_amount, 2) +"</a>");
		                	}
			            	return html.join("");
		                }},
		                { display: '支付宝金额',render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(zfbAmount != ''){
		                		if(Number(zfbAmount) > Number(formatMoney(rowdata.zfb_amount, 2)) ) {
		                			html.push("<a href='javascript:;' style='color:green;' onclick='combineList(\""+ rowdata.each_day +"\", 2);'>"+ formatMoney(rowdata.zfb_amount, 2) +"</a>");
			            		}else if(Number(zfbAmount) < Number(formatMoney(rowdata.zfb_amount, 2)) ) {
			            			html.push("<a href='javascript:;' style='color:red;' onclick='combineList(\""+ rowdata.each_day +"\", 2);'>"+ formatMoney(rowdata.zfb_amount, 2) +"</a>");
			            		}else {
			            			html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.each_day +"\", 2);'>"+ formatMoney(rowdata.zfb_amount, 2) +"</a>");
			            		}
		                	}else {
		                		html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.each_day +"\", 2);'>"+ formatMoney(rowdata.zfb_amount, 2) +"</a>");
		                	}
			            	return html.join("");
		                }},
		                { display: '微信公众号金额', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(gzhAmount != ''){
		                		if(Number(gzhAmount) > Number(formatMoney(rowdata.gzh_amount, 2)) ) {
		                			html.push("<a href='javascript:;' style='color:green;' onclick='combineList(\""+ rowdata.each_day +"\",3);'>"+ formatMoney(rowdata.gzh_amount, 2) +"</a>");
			            		}else if(Number(gzhAmount) < Number(formatMoney(rowdata.gzh_amount, 2)) ) {
			            			html.push("<a href='javascript:;' style='color:red;' onclick='combineList(\""+ rowdata.each_day +"\",3);'>"+ formatMoney(rowdata.gzh_amount, 2) +"</a>");
			            		}else {
			            			html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.each_day +"\",3);'>"+ formatMoney(rowdata.gzh_amount, 2) +"</a>");
			            		}
		                	}else {
		                		html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.each_day +"\",3);'>"+ formatMoney(rowdata.gzh_amount, 2) +"</a>");
		                	}
			            	return html.join("");
		                }},
		                { display: '微信APP/H5笔数', name:'', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(wxCount != ''){
		                		if(Number(wxCount) > Number(rowdata.wx_count) ) {
		                			html.push("<span style='color:green;'>"+ rowdata.wx_count +"</span>");
			            		}else if(Number(wxCount) < Number(rowdata.wx_count) ) {
			            			html.push("<span style='color:red;'>"+ rowdata.wx_count +"</span>");
			            		}else {
			            			html.push(rowdata.wx_count);
			            		}
		                	}else {
		                		html.push(rowdata.wx_count);
		                	}
			            	return html.join("");
		                }},
		                { display: '支付宝笔数',name:'', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(zfbCount != ''){
		                		if(Number(zfbCount) > Number(rowdata.zfb_count) ) {
		                			html.push("<span style='color:green;'>"+ rowdata.zfb_count +"</span>");
			            		}else if(Number(zfbCount) < Number(rowdata.zfb_count) ) {
			            			html.push("<span style='color:red;'>"+ rowdata.zfb_count +"</span>");
			            		}else {
			            			html.push(rowdata.zfb_count);
			            		}
		                	}else {
		                		html.push(rowdata.zfb_count);
		                	}
			            	return html.join("");
		                }},
		                { display: '微信公众号笔数',name:'gzhCount', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(gzhCount != ''){
		                		if(Number(gzhCount) > Number(rowdata.gzh_count) ) {
		                			html.push("<span style='color:green;'>"+ rowdata.gzh_count +"</span>");
			            		}else if(Number(gzhCount) < Number(rowdata.gzh_count) ) {
			            			html.push("<span style='color:red;'>"+ rowdata.gzh_count +"</span>");
			            		}else {
			            			html.push(rowdata.gzh_count);
			            		}
		                	}else {
		                		html.push(rowdata.gzh_count);
		                	}
		                	totalCount = rowdata.total_count+"";
		                	combineAmount = rowdata.combine_amount+"";
		                	wxAmount = rowdata.wx_amount+"";
		                	zfbAmount = rowdata.zfb_amount+"";
		                	gzhAmount = rowdata.gzh_amount+"";
		                	wxCount = rowdata.wx_count+"";
		                	zfbCount = rowdata.zfb_count+"";
		                	gzhCount = rowdata.gzh_count+"";
			            	return html.join("");
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true, //不设置默认为 true
                 beforeSearch: checkSearchCondition
      } , 							
     container:{
        // toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
}
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
	<%--	<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${pay_date_begin}"/>
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
				<input type="text" id="pay_date_end" name="pay_date_end" value="${pay_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
				</div>
			</div>
		</div>--%>

		<div class="search-tr">
			<div class="search-td" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="pay_date_begin" name="pay_date_begin" class="dateEditor" value="${pay_date_begin}" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="pay_date_end" name="pay_date_end" class="dateEditor" value="${pay_date_end}" style="width: 135px;" />
				</div>
			</div>
			<div class="search-td-search-btn" style="display: inline-flex;" >
				<div id="searchbtn" >统计</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" >
				</div>
			</div>
		</div>

		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
	<script type="text/javascript">
	$(function() {
		// 禁止分页
		 $("#maingrid").ligerGrid({
            usePager:false
        });
	});
</script>
</body>