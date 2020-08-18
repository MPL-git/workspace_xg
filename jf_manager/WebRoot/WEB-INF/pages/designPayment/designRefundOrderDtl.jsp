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
	$(function(){

		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left',
			labelwidth: 120,
			width:120
		});
		getTotalPayAmount();

		/*$("#export").bind('click',function(){
			var orderCode = $("#orderCode").val();
			var mchtCode = $("#mchtCode").val();
			var mchtName = $("#mchtName").val();
			var taskName = $("#taskName").val();
			var paymentNo = $("#paymentNo").val();
			var taskType = $("#taskType").val();
			var payDateBegin = $("#payDateBegin").val();
			var payDateEnd = $("#payDateEnd").val();
			var tag = $("#tag").val();
			location.href="${pageContext.request.contextPath}/designRefundOrderDtl/count/export.shtml?orderCode="+orderCode+"&mchtCode="+mchtCode+"&mchtName="+mchtName+"&taskName="+taskName+"&paymentNo="+paymentNo+"&taskType="+taskType+"&payDateBegin="+payDateBegin+"&payDateEnd="+payDateEnd+"&tag="+tag;
		});*/
	});

	//导出
	function excel(){
		$("#dataForm").attr("action","${pageContext.request.contextPath}/designRefundOrderDtl/count/export.shtml");
		$("#dataForm").submit();
	}



	var listConfig = {
		url:"/desiPayment/designReceiptList.shtml?tag=2",
		btnItems:[],
		listGrid:{ columns: [
				{ display: '订单编号', name: 'orderCode', width: 180 },
				{ display: '类型', name: 'taskType', width: 150, render: function (rowdata,rowindex) {
						var html = [];
						if(rowdata.taskType == 1){
							html.push("品牌特卖");
						}else if (rowdata.taskType == 2){
							html.push("店铺装修");
						}
						return html.join("");
					}
				},
				{ display: '任务名称', name: 'taskName', width: 160 },
				{ display: '商家序号', name: 'mchtCode', width: 160 },
				{ display: '公司名称', name: 'companyName', width: 160 },
				{ display: '店铺名称', name: 'shopName', width: 160 },
				{ display: '付款渠道', name: 'paymentName', width: 100 },
				{ display: '实付金额(元)', name: 'payAmount', width: 100 },
				{ display: '付款状态', name: 'payStatus', width: 100,render: function (rowdata,rowindex) {
						var html = [];
						if(rowdata.payStatus == 1 && rowdata.payStatusDesc == null){
							html.push("已付款");
						}else if (rowdata.payStatus == 1 && rowdata.payStatusDesc == 0 || rowdata.payStatusDesc == 1){
							html.push("退款中");
						}else if (rowdata.payStatus == 1 && rowdata.payStatusDesc == 2){
							html.push("退款成功");
						}else if (rowdata.payStatus == 1 && rowdata.payStatusDesc == 3){
							html.push("退款失败");
						}
						return html.join("");
					}
				},
				{ display: '付款时间', width: 160, render: function (rowdata, rowindex) {
						if (rowdata.payDate != null){
							var payDate = new Date(rowdata.payDate);
							return payDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}},
				{ display: '支付交易号', name: 'paymentNo', width: 240 }

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

	};
	function getTotalPayAmount() {
		$("#loadTotalPayment").html(" ");
		$.ajax({
			url: "${pageContext.request.contextPath}/desiPayment/getDesignReceiptTotalPayment.shtml?tag=2",
			type: 'POST',
			dataType: 'json',
			data: $('#dataForm').serialize(),
			success: function(data){
				// var totalPayment = data.totalPayment;
				// $("#loadTotalPayment").attr(data);
				// $("#loadTotalPayment").html(data);
				// var dataText =(data);
				$("#loadTotalPayment").append("总收款金额:   "+data.totalPayment + "元");
			},
			error:function(){
				alert('数据不存在');
			}
		});

	}



</script>



<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" id="tag" name="tag" value="2" />
		<div id ="loadTotalPayment" style="margin-left: 110px; margin-top: 10px; min-height: 1px;position: relative; " ></div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">订单编号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="orderCode" name="orderCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtName" name="mchtName">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">任务名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="taskName" name="taskName">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="margin-top: 5%;">支付交易号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="paymentNo" name="paymentNo">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >类型：</div>
					<div class="search-td-combobox-condition">
						<select id="taskType" name="taskType" style="width: 135px;">
							<option value="" selected="selected" >请选择</option>
							<option value="1" >品牌特卖</option>
							<option value="2" >店铺装修</option>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="payDateBegin" name="payDateBegin" class="dateEditor" value="${nowDate}" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="payDateEnd" name="payDateEnd" class="dateEditor" value="${nowDate}" style="width: 135px;" />
					</div>
				</div>

				<div class="search-td-search-btn" style="display: inline-flex; width: 60%;" >
					<div id="searchbtn" >
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="搜索" onclick="getTotalPayAmount();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();">
					</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>