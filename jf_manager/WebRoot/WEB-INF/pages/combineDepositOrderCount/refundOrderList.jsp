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
			commUtil.alertError("请先勾选要处理的退款单");
			return false;
		}
		var ids=[];
		$("input[name='checkbox']:checked").each(function(){
			ids.push($(this).val());
		});
		$.ligerDialog.open({
	 		height: $(window).height()*0.5,
			width: $(window).width()*0.5,
			title: "退款确认操作",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/refundOrder/toBatch.shtml?ids=" + ids.join(","),
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	});
	
	$("#batchAll").bind('click',function(){
		var refundCode = $.trim($("#refundCode").val());
		if(refundCode==""){
			commUtil.alertError("对不起，退款编号不可为空！");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/refundOrder/check.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"refundCode":refundCode},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.open({
				 		height: $(window).height()*0.5,
						width: $(window).width()*0.5,
						title: "退款确认操作",
						name: "INSERT_WINDOW",
						url: "${pageContext.request.contextPath}/refundOrder/toBatch.shtml?refundCode=" + refundCode,
						showMax: true,
						showToggle: false,
						showMin: false,
						isResize: true,
						slide: false,
						data: null
					});
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
	
	$("#export").bind('click',function(){
		var paymentId = $("#paymentId").val();
		var refundPaymentId = $("#refundPaymentId").val();
		var status = $("#status").val();
		var serviceType = $("#serviceType").val();
		var mchtCode = $("#mchtCode").val();
		var refund_agree_date_begin = $("#refund_agree_date_begin").val();
		var refund_agree_date_end = $("#refund_agree_date_end").val();
		var combineDepositOrderCode = $("#combineDepositOrderCode").val();
		var serviceOrderCode = $("#serviceOrderCode").val();
		var success_date_begin = $("#success_date_begin").val();
		var success_date_end = $("#success_date_end").val();
		var refundCode = $("#refundCode").val();
		var refund_register_date_begin = $("#refund_register_date_begin").val();
		var refund_register_date_end = $("#refund_register_date_end").val();
		location.href="${pageContext.request.contextPath}/refundOrder/export.shtml?paymentId="+paymentId+"&status="+status+"&combineDepositOrderCode="+combineDepositOrderCode+"&refundPaymentId="+refundPaymentId+"&serviceType="+serviceType+"&mchtCode="+mchtCode+"&refund_agree_date_begin="+refund_agree_date_begin+"&refund_agree_date_end="+refund_agree_date_end+"&serviceOrderCode="+serviceOrderCode+"&success_date_begin="+success_date_begin+"&success_date_end="+success_date_end+"&refundCode="+refundCode+"&refund_register_date_begin="+refund_register_date_begin+"&refund_register_date_end="+refund_register_date_end;
	});
	
	$("#count").bind('click',function(){
		var paymentId = $("#paymentId").val();
		var refundPaymentId = $("#refundPaymentId").val();
		var status = $("#status").val();
		var serviceType = $("#serviceType").val();
		var mchtCode = $("#mchtCode").val();
		var refund_agree_date_begin = $("#refund_agree_date_begin").val();
		var refund_agree_date_end = $("#refund_agree_date_end").val();
		var combineOrderCode = $("#combineOrderCode").val();
		var serviceOrderCode = $("#serviceOrderCode").val();
		var success_date_begin = $("#success_date_begin").val();
		var success_date_end = $("#success_date_end").val();
		var refundCode = $("#refundCode").val();
		var refund_register_date_begin = $("#refund_register_date_begin").val();
		var refund_register_date_end = $("#refund_register_date_end").val();
		$.ligerDialog.open({
	 		height: $(window).height()*0.20,
			width: $(window).width()*0.15,
			title: "合计",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/refundOrder/count.shtml?paymentId="+paymentId+"&refundPaymentId="+refundPaymentId+"&status="+status+"&serviceType="+serviceType+"&mchtCode="+mchtCode+"&refund_agree_date_begin="+refund_agree_date_begin+"&refund_agree_date_end="+refund_agree_date_end+"&combineOrderCode="+combineOrderCode+"&serviceOrderCode="+serviceOrderCode+"&success_date_begin="+success_date_begin+"&success_date_end="+success_date_end+"&refundCode="+refundCode+"&refund_register_date_begin="+refund_register_date_begin+"&refund_register_date_end="+refund_register_date_end,
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
		title: "母订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/combine/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function viewServiceOrderDetail(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "售后单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toView(id) {
	$.ligerDialog.open({
 		height: $(window).height()*0.50,
		width: $(window).width()*0.40,
		title: "退款查看",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/refundOrder/toView.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toRefund(id) {
	$.ligerDialog.open({
 		height: $(window).height()*0.6,
		width: $(window).width()*0.5,
		title: "退款操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/refundOrder/toRefund.shtml?id=" + id,
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
 		height: $(window).height()*0.5,
		width: $(window).width()*0.5,
		title: "退款登记操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/refundOrder/toRegister.shtml?id=" + id,
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
 		height: $(window).height()*0.5,
		width: $(window).width()*0.5,
		title: "退款确认操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/refundOrder/toConfirm.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

  var listConfig={
		  
	  url:"/combineDepositOrderCount/refundOrder/data.shtml",
      
      listGrid:{ columns: [{ display: "母订单号", width: "10%",render: function(rowdata, rowindex) {
    						var html = [];
    						console.log(rowdata.orderType);
    						if(rowdata.orderType == 1){
	    						html.push("<a href=\"javascript:viewDetail(" + rowdata.combineOrderId + ");\">"+rowdata.combineOrderCode+"</a>"); 
    						}else{
	    						html.push(rowdata.combineDepositOrderCode); 
    						}
    						return html.join("");
    					}},
 		                { display: '实付金额',width: "5%", render: function (rowdata, rowindex) {
 		                	if(rowdata.orderType == 1){
	 		                	if(rowdata.totalPayAmount){
		 		                	return formatMoney(rowdata.totalPayAmount,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
 		                	}else{
 		                		if(rowdata.totalDeposit){
		 		                	return formatMoney(rowdata.totalDeposit,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
 		                	}
		                }},
		                { display: '母订单状态', width: "4%",render: function (rowdata, rowindex) {
		                	if(rowdata.orderType == 1){
								return "<span class='color-s"+rowdata.status+"'>"+rowdata.combineOrderStatusDesc+"</span>";
		                	}else{
								return "<span class='color-s"+rowdata.status+"'>"+rowdata.combineDepositOrderStatusDesc+"</span>";
		                	}
		                }},
		                { display: '原付款渠道', width: "6%",render: function (rowdata, rowindex) {
		                	if(rowdata.orderType == 1){
								return rowdata.paymentName;
		                	}else{
								return rowdata.depositPaymentName;
		                	}
		                }},
		                { display: '支付交易号', width: "12%",render: function (rowdata, rowindex) {
		                	if(rowdata.orderType == 1){
								return rowdata.paymentNo;
		                	}else{
								return rowdata.depositPaymentNo;
		                	}
		                }},
		                { display: '退款同意时间', width: "9%", render: function (rowdata, rowindex) {
		                	if (rowdata.refundAgreeDate!=null){
								var refundAgreeDate = new Date(rowdata.refundAgreeDate);
								return refundAgreeDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '售后类型', render: function(rowdata, rowindex) {
    						var html = [];
    						html.push("<a href=\"javascript:viewServiceOrderDetail(" + rowdata.serviceOrderId + ");\">"+rowdata.serviceTypeDesc+"</a>"); 
    						return html.join("");
    					}},
		                { display: '应退金额', render: function (rowdata, rowindex) {
 		                	if(rowdata.refundAmount){
	 		                	return formatMoney(rowdata.refundAmount,2);
 		                	}else{
 		                		return "0.00";
 		                	}
		                }},
		                { display: '退款状态', width: "4%", name:'statusDesc'},
		                { display: '明细ID', width: "6%", name:'orderDtlId'},
		                { display: '退款时间', width: "9%", render: function (rowdata, rowindex) {
		                	if (rowdata.successDate!=null){
								var successDate = new Date(rowdata.successDate);
								return successDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '退款编号',width: "6%", width: 100, name: 'refundCode'},
		                { display: '退款登记日期', width: "5%", render: function (rowdata, rowindex) {
		                	if (rowdata.refundRegisterDate!=null){
								var refundRegisterDate = new Date(rowdata.refundRegisterDate);
								return refundRegisterDate.format("yyyy-MM-dd");
		                	}
		                }},
		                { display: '退款出纳', name: 'refundStaffName'},
		                { display: '退款渠道', width: "4%",render: function (rowdata, rowindex) {
		                	if (rowdata.refundMethod=="1"){//原路返还
		                		if(rowdata.orderType == 1){
									return rowdata.paymentName;
		                		}else{
									return rowdata.depositPaymentName;
		                		}
		                	}else if(rowdata.refundMethod=="2"){//现金支付，转账
		                		return "其他";
		                	}
		                }},
		                { display: '退款方式', width: "4%", name: 'refundMethodDesc'},
		                { display: '操作',render: function (rowdata, rowindex) {
		                	if(rowdata.status == "1"){//已退
		                		return '<a href="javascript:;" onclick="toRegister('+rowdata.id+');">登记</a>';
		                	}else if(rowdata.status == "2"){//退款失败
		                		return '<a href="javascript:;" onclick="toRefund('+rowdata.id+');">退款</a>';
		                	}else if(rowdata.status == "3"){//已登记
		                		return '<a href="javascript:;" onclick="toConfirm('+rowdata.id+');">确认</a>';
		                	}else if(rowdata.status == "4"){//已确认
		                		return '<a href="javascript:;" onclick="toView('+rowdata.id+');">查看</a>';
		                	}
		                }},
		                { display: '<input type="checkbox" id="batch">批量',render: function (rowdata, rowindex) {
		                	if(rowdata.status == "1" || rowdata.status == "3"){
								return '<input type="checkbox" name="checkbox" value="'+rowdata.id+'">';
		                	}
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
}
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			 <div class="search-td">
			 <div class="search-td-label" >原付款渠道：</div>
			 <div class="search-td-condition" >
				<select id="paymentId" name="paymentId">
					<option value="">请选择</option>
					<option value="1" <c:if test="${paymentId eq 1}">selected="selected"</c:if>>微信APP/H5</option>
					<option value="2" <c:if test="${paymentId eq 2}">selected="selected"</c:if>>支付宝</option>
					<option value="3" <c:if test="${paymentId eq 3}">selected="selected"</c:if>>微信公众号/小程序</option>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >退款渠道：</div>
			 <div class="search-td-condition" >
				<select id="refundPaymentId" name="refundPaymentId">
					<option value="">请选择</option>
					<c:forEach var="list" items="${sysPayments}">
						<option value="${list.id}" <c:if test="${paymentId eq list.id}">selected="selected"</c:if>>${list.name}</option>
					</c:forEach>
					<option value="-1" <c:if test="${refundPaymentId eq '-1'}">selected="selected"</c:if>>其他</option>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >退款状态：</div>
			 <div class="search-td-condition" >
				<select id="status" name="status">
					<option value="">请选择</option>
					<c:forEach var="status" items="${statusList}">
						<option value="${status.statusValue}">${status.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >售后类型：</div>
			 <div class="search-td-condition" >
				<select id="serviceType" name="serviceType">
					<option value="">请选择</option>
					<c:forEach var="st" items="${serviceTypeList}">
						<option value="${st.statusValue}" <c:if test="${serviceType eq st.statusValue}">selected="selected"</c:if>>${st.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
		</div> 
		<div class="search-tr"  >	 
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}">
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">同意退款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="refund_agree_date_begin" name="refund_agree_date_begin"/>
				<script type="text/javascript">
					$(function() {
						$("#refund_agree_date_begin").ligerDateEditor( {
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
				<input type="text" id="refund_agree_date_end" name="refund_agree_date_end"/>
				<script type="text/javascript">
					$(function() {
						$("#refund_agree_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
		
			<div class="search-td">
			<div class="search-td-label" >母订单号：</div>
			<div class="search-td-condition" >
				<input type="text" id="combineDepositOrderCode" name="combineDepositOrderCode" >
			</div>
			</div>
		</div>	 
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >售后单号：</div>
			<div class="search-td-condition" >
				<input type="text" id="serviceOrderCode" name="serviceOrderCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">退款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="success_date_begin" name="success_date_begin" value="${success_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#success_date_begin").ligerDateEditor( {
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
				<input type="text" id="success_date_end" name="success_date_end" value="${success_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#success_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >退款编号：</div>
			<div class="search-td-condition" >
				<input type="text" id="refundCode" name="refundCode" >
			</div>
			</div>
		</div>	
		<div class="search-tr"  >	
			<div class="search-td">
			<div class="search-td-label" style="float:left;">退款登记日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="refund_register_date_begin" name="refund_register_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#refund_register_date_begin").ligerDateEditor( {
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
				<input type="text" id="refund_register_date_end" name="refund_register_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#refund_register_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >明细ID：</div>
			<div class="search-td-condition" >
				<input type="text" id="orderDtlId" name="orderDtlId">
			</div>
			</div>
			
			<div style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量（全部）" id="batchAll">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量（选中）" id="batchSelected">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="统计" id="count">
				</div>
			</div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>