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
 $(function(){
	 $("#export").bind('click',function(){
			var pay_date_begin = $("#pay_date_begin").val();
			var pay_date_end = $("#pay_date_end").val();
			if($.trim(pay_date_begin)==""||$.trim(pay_date_end)==""){
				commUtil.alertError("请选择日期");
				return false;
			}
			var mchtCode = $("#mchtCode").val();
			var activityAreaId = $("#activityAreaId").val();
			var activityId = $("#activityId").val();
			/* var status = $("#status").val();
			window.location.href="${pageContext.request.contextPath}/operateData/orderExport/export.shtml?pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&status="+status+"&activityAreaId="+activityAreaId+"&activityId="+activityId+"&mchtCode="+mchtCode; */
			window.location.href="${pageContext.request.contextPath}/operateData/orderExport/export.shtml?pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&activityAreaId="+activityAreaId+"&activityId="+activityId+"&mchtCode="+mchtCode; 
		});
 });
 
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
 
function checkComfirm(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "保证金缴纳确认",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtDeposit/checkComfirm.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
	
 var listConfig={
     url:"/operateData/orderExport/data.shtml",
     listGrid:{ columns: [
						{ display: '付款日期',width:100, render: function (rowdata, rowindex) {
							var payDate = new Date(rowdata.payDate);
							return payDate.format("yyyy-MM-dd");	                		
		                }},
			            { display: '子订单编号',width:160, name:'subOrderCode'},
			            { display: '付款状态',width:80,name:'combineOrderStatusDesc'},
		                { display: '品牌',width:100, name:'brandName'},
			            { display: '货号',width:120, name:'artNo'},
		                { display: '三级类目',width:100, name:'productTypeName'},
		                { display: '醒购价',width:80, name:'salePrice'},
		                { display: '数量',width:80,  name: 'quantity'}, 
		                { display: '销售商品金额',width:80,render: function (rowdata, rowindex) {
		                	return formatMoney(rowdata.salePrice*rowdata.quantity,2);
		                }},
		                { display: '平台优惠',width:80,  name: 'platformPreferential'},
		                { display: '实付金额',width:80,  name: 'payAmount'},
		                { display: '支付方式',width:80, name:'paymentName'},
		                { display: '商家序号',width:80, name:'mchtCode'},
		                { display: '店铺名称',width:150, name:'mchtShopName'},
		                { display: '设备',width:80, name:'sourceClientDesc'},
		                { display: '收货人地域',width:180, name:'areaName'}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
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
			<div class="search-td-label" style="float:left;">付款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${pay_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
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
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<%-- <div class="search-td">
			<div class="search-td-label" style="float:left;">付款状态：</div>
			<div class="l-panel-search-item" >
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>已付款</option>
					<option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>未付款</option>
				</select>
		 	 </div>
			 </div> --%>
			
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition">
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
		</div>
			
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >会场ID：</div>
			<div class="search-td-condition">
				<input type="text" id="activityAreaId" name="activityAreaId" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >特卖ID：</div>
			<div class="search-td-condition">
				<input type="text" id="activityId" name="activityId" >
			</div>
			</div>
			
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 62px;height: 30px;cursor: pointer;" value="导出" id="export">
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