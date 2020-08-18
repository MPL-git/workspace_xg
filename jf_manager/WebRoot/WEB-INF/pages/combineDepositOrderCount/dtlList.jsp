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
	
	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format : "yyyy-MM-dd",
		labelAlign : 'left',
		width : 150
	});
	
	$("#batch").live('click',function(){
		if($(this).attr("checked")){
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",false);
			});
		}
	});
	$("input[name='checkbox']").live('click',function(){
		var totalLength = $("input[name='checkbox']").length;
		if($(this).attr("checked")){
			var selectedLength = $("input[name='checkbox']:checked").length;
			if(selectedLength == totalLength){
				$("#batch").attr("checked",true);
			}else{
				$("#batch").attr("checked",false);
			}
		}else{
			$("#batch").attr("checked",false);
		}
	});
	
	$("#batchSelected").bind('click',function(){
		var selectedLength = $("input[name='checkbox']:checked").length;
		if(selectedLength == 0){
			commUtil.alertError("请先勾选要处理的母订单");
			return false;
		}
		var id=[];
		$("input[name='checkbox']:checked").each(function(){
			id.push($(this).val());
		});
		$.ligerDialog.open({
	 		height: $(window).height()*0.6,
			width: $(window).width()*0.6,
			title: "收款确认操作",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/receivables/combine/toConfirm.shtml?id=" + id.join(","),
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	});
	
	$("#batchAll").bind('click',function(){
		var paymentId = $("#paymentId").val();
		var financialStatus = $("#financialStatus").val();
		var combineOrderCode = $("#combineOrderCode").val();
		var paymentNo = $("#paymentNo").val();
		var financialNo = $("#financialNo").val();
		var create_date_begin = $("#create_date_begin").val();
		var create_date_end = $("#create_date_end").val();
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		var register_date_begin = $("#register_date_begin").val();
		var register_date_end = $("#register_date_end").val();
		$.ligerDialog.open({
	 		height: $(window).height()*0.6,
			width: $(window).width()*0.6,
			title: "收款确认操作",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/receivables/combine/toConfirm.shtml?paymentId="+paymentId+"&financialStatus="+financialStatus+"&combineOrderCode="+combineOrderCode+"&paymentNo="+paymentNo+"&financialNo="+financialNo+"&create_date_begin="+create_date_begin+"&create_date_end="+create_date_end+"&pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&register_date_begin="+register_date_begin+"&register_date_end="+register_date_end,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	});
	
	$("#count").bind('click',function(){
		var paymentId = $("#paymentId").val();
		var financialStatus = $("#financialStatus").val();
		var combineOrderCode = $("#combineOrderCode").val();
		var paymentNo = $("#paymentNo").val();
		var financialNo = $("#financialNo").val();
		var create_date_begin = $("#create_date_begin").val();
		var create_date_end = $("#create_date_end").val();
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		var register_date_begin = $("#register_date_begin").val();
		var register_date_end = $("#register_date_end").val();
		$.ligerDialog.open({
	 		height: $(window).height()*0.20,
			width: $(window).width()*0.15,
			title: "合计",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/receivables/combine/count.shtml?paymentId="+paymentId+"&financialStatus="+financialStatus+"&combineOrderCode="+combineOrderCode+"&paymentNo="+paymentNo+"&financialNo="+financialNo+"&create_date_begin="+create_date_begin+"&create_date_end="+create_date_end+"&pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&register_date_begin="+register_date_begin+"&register_date_end="+register_date_end,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	});
	
});

function viewDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toRegister(id) {
	$.ligerDialog.open({
 		height: $(window).height()*0.6,
		width: $(window).width()*0.6,
		title: "收款登记操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/receivables/combine/toRegister.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toConfirm(id) {
	$.ligerDialog.open({
 		height: $(window).height()*0.6,
		width: $(window).width()*0.6,
		title: "收款确认操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/receivables/combine/toConfirm.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

	//导出
	function excel(){  
		$("#dataForm").attr("action","${pageContext.request.contextPath}/combineDepositOrderCount/dtl/export.shtml");
		$("#dataForm").submit();
	}

	var listConfig={
		  
	  url:"/combineDepositOrderCount/dtl/data.shtml",
      
      listGrid:{ columns: [{ display: "预售订单编号",width: "10%", name:'combineDepositOrderCode'},
                        { display: '商家序号', width: "6%",name: 'mchtCode'},
                        { display: '公司名称', width: "9%",name: 'companyName'}, 
                        { display: '店铺名称', width: "9%",name: 'shopName'}, 
 		                { display: '品牌/货号', width: "14%", render: function (rowdata, rowindex) {
 		                	return "【"+rowdata.brandName+"】"+"&nbsp;"+rowdata.artNo;
		                }},
		                { display: '付款渠道', width: "6%",name: 'paymentName'},
		                { display: '实付金额', width: "5%", render: function (rowdata, rowindex) {
 		                	if(rowdata.payAmount){
	 		                	return formatMoney(rowdata.payAmount,2);
 		                	}else{
 		                		return "0.00";
 		                	}
		                }},
		                { display: '订单状态', width: "5%", render: function (rowdata, rowindex) {
							return "<span class='color-s"+rowdata.status+"'>"+rowdata.statusDesc+"</span>";
		                }},
		                { display: '付款时间', width: "9%", render: function (rowdata, rowindex) {
		                	if (rowdata.payDate){
								var payDate = new Date(rowdata.payDate);
								return payDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '退款时间', width: "9%", render: function (rowdata, rowindex) {
		                	if (rowdata.refundDate){
								var refundDate = new Date(rowdata.refundDate);
								return refundDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '支付交易号',width: "12%", name: 'paymentNo'},
		                { display: '佣金比例',width: "5%", name: 'popCommissionRate'},
		                { display: '技术服务费',width: "5%", name: 'commissionAmount'},
		                { display: '结算金额',width: "6%", name: 'settleAmount'},
		                { display: '尾款子订单',width: "12%", render: function (rowdata, rowindex) {
		                	if(rowdata.subOrderCode){
			                	return '<a href="javascript:;" onclick="viewDetail('+rowdata.subOrderId+');">'+rowdata.subOrderCode+'</a>';
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '交易完成时间', width: "9%", render: function (rowdata, rowindex) {
		                	if (rowdata.completeDate){
								var completeDate=new Date(rowdata.completeDate);
								return completeDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
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
		<input type="hidden" id="combineDepositOrderStatus" name="combineDepositOrderStatus" value="2">
		<div class="search-pannel">
			<div class="search-tr"  > 
				 <div class="search-td">
				 	<div class="search-td-label" >定金的订单状态：</div>
					 <div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;">
							<option value="">请选择</option>
							<option value="2">定金已付</option>
							<option value="3">尾款已下单</option>
							<option value="4">尾款已付</option>
							<option value="5">交易完成</option>
							<option value="6">已退款</option>
							<option value="8">尾款下单后未支付取消</option>
							<option value="9">未付尾款取消</option>
						</select>
				 	 </div>
				 </div>
				 <div class="search-td">
					 <div class="search-td-label" >付款渠道：</div>
					 <div class="search-td-combobox-condition" >
						<select id="paymentId" name="paymentId" style="width: 135px;">
							<option value="">全部</option>
							<option value="1" <c:if test="${paymentId eq 1}">selected="selected"</c:if>>微信APP/H5</option>
							<option value="2" <c:if test="${paymentId eq 2}">selected="selected"</c:if>>支付宝</option>
							<option value="3" <c:if test="${paymentId eq 3}">selected="selected"</c:if>>微信公众号/小程序</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >支付交易号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id = "paymentNo" name="paymentNo" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >预售订单号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="combineDepositOrderCode" name="combineDepositOrderCode" >
					</div>
				</div>
			</div>
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
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">退款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="refund_date_begin" name="refund_date_begin" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="refund_date_end" name="refund_date_end" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="pay_date_begin" name="pay_date_begin" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="pay_date_end" name="pay_date_end" class="dateEditor" />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex; ">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="搜索" id="searchbtn">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();" >
					</div>
				</div>
			</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>