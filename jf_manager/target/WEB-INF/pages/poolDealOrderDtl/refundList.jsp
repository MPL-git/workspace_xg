<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
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
var viewerPictures;
function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}

$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#viewAccept").bind('click',function(){
		var date_begin = $("#date_begin").val();
		var date_end = $("#date_end").val();
		if($.trim(date_begin)==""||$.trim(date_end)==""){
			commUtil.alertError("请选择日期");
			return false;
		}
		var mchtCode = $("#mchtCode").val();
		var subOrderCode = $("#subOrderCode").val();
		var brandName = $("#brandName").val();
		var artNo = $("#artNo").val();
		var productName = $("#productName").val();
		$("#accept").val("1");
		$("#refund").val("0");
		var accept = "1";
		location.href="${pageContext.request.contextPath}/poolDealOrderDtl/list.shtml?subOrderCode="+subOrderCode+"&brandName="+encodeURI(encodeURI(brandName))+"&artNo="+artNo+"&productName="+encodeURI(encodeURI(productName))+"&date_begin="+date_begin+"&mchtCode="+mchtCode+"&date_end="+date_end+"&accept="+accept;
	});
	
	$("#viewRefund").bind('click',function(){
		var date_begin = $("#date_begin").val();
		var date_end = $("#date_end").val();
		if($.trim(date_begin)==""||$.trim(date_end)==""){
			commUtil.alertError("请选择日期");
			return false;
		}
		var mchtCode = $("#mchtCode").val();
		var subOrderCode = $("#subOrderCode").val();
		var brandName = $("#brandName").val();
		var artNo = $("#artNo").val();
		var productName = $("#productName").val();
		$("#accept").val("0");
		$("#refund").val("1");
		var refund = "1";
		location.href="${pageContext.request.contextPath}/poolDealOrderDtl/list.shtml?subOrderCode="+subOrderCode+"&brandName="+encodeURI(encodeURI(brandName))+"&artNo="+artNo+"&productName="+encodeURI(encodeURI(productName))+"&date_begin="+date_begin+"&mchtCode="+mchtCode+"&date_end="+date_end+"&refund="+refund;
	});		
	
	$("#export").bind('click',function(){
		var date_begin = $("#date_begin").val();
		var date_end = $("#date_end").val();
		if($.trim(date_begin)==""||$.trim(date_end)==""){
			commUtil.alertError("请选择日期");
			return false;
		}
		var mchtCode = $("#mchtCode").val();
		var subOrderCode = $("#subOrderCode").val();
		var brandName = $("#brandName").val();
		var artNo = $("#artNo").val();
		var productName = $("#productName").val();
		location.href="${pageContext.request.contextPath}/poolDealOrderDtl/export.shtml?subOrderCode="+subOrderCode+"&brandName="+encodeURI(encodeURI(brandName))+"&artNo="+artNo+"&productName="+encodeURI(encodeURI(productName))+"&date_begin="+date_begin+"&mchtCode="+mchtCode+"&date_end="+date_end;
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

function viewMcht(mchtId,companyName,mchtShortName) {
	$.ligerDialog.open({
 		height: $(window).height()*0.4,
		width: $(window).width()*0.3,
		title: companyName+"("+mchtShortName+")",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/poolDealOrderDtl/viewMcht.shtml?mchtId=" + mchtId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
  var listConfig={
		  
	  url:"/poolDealOrderDtl/data.shtml",
      
      listGrid:{ columns: [{ display: 'SKU图', render: function(rowdata, rowindex) {
					    	  if($("#flag").val()=="0"){
								  	var acceptQuantity = 0;
									if(rowdata.acceptQuantity){
									   acceptQuantity = rowdata.acceptQuantity;
									}
									var acceptPayAmount = 0;
									if(rowdata.acceptPayAmount){
										acceptPayAmount = rowdata.acceptPayAmount;
									}
									var acceptSettleAmount = 0;
									if(rowdata.acceptSettleAmount){
										acceptSettleAmount = rowdata.acceptSettleAmount;
									}
									var acceptCommissionAmount = 0;
									if(rowdata.acceptCommissionAmount){
										acceptCommissionAmount = rowdata.acceptCommissionAmount;
									}
									var refundQuantity = 0;
									if(rowdata.refundQuantity){
										refundQuantity = rowdata.refundQuantity;
									}
									var refundPayAmount = 0;
									if(rowdata.refundPayAmount){
										refundPayAmount = rowdata.refundPayAmount;
									}
									var refundSettleAmount = 0;
									if(rowdata.refundSettleAmount){
										refundSettleAmount = rowdata.refundSettleAmount;
									}
									var refundCommissionAmount = 0;
									if(rowdata.refundCommissionAmount){
										refundCommissionAmount = rowdata.refundCommissionAmount;
									}
									var needSettleAmount = 0;
									if(rowdata.needSettleAmount){
										needSettleAmount = rowdata.needSettleAmount;
									}
									var totalCommissionAmount = 0;
									if(rowdata.totalCommissionAmount){
										totalCommissionAmount = rowdata.totalCommissionAmount;
									}
									var html = "【收款情况 】  商品数量："+acceptQuantity+"件，实收金额："+acceptPayAmount+"元，结算金额："+acceptSettleAmount+"元，服务费："+acceptCommissionAmount+"元【退款情况 】  商品数量："+refundQuantity+"件，实退金额："+refundPayAmount+"元，结算金额："+refundSettleAmount+"元，服务费："+refundCommissionAmount+"元【应结算金额 】  应结算金额："+needSettleAmount+"元，服务费总额："+totalCommissionAmount+"元";
									$("#count").html(html);
									$("#flag").val("1");
							  }
    						return '<img src="${pageContext.request.contextPath}/file_servelt${rowdata.skuPic}" onclick="viewerPic(this.src)" style="width:60px;height:60px;">';
    					}},
 		                { display: '子订单编号', render: function (rowdata, rowindex) {
	 		                return '<a href="javascript:;" onclick="viewDetail('+rowdata.subOrderId+')">'+rowdata.subOrderCode+'</a>';
		                }},
 		                { display: '退款时间', render: function (rowdata, rowindex) {
 		                	var refundDate = new Date(rowdata.refundDate);
 		                	return refundDate.format("yyyy-MM-dd hh:mm:ss");
		                }},
		                { display: '最新状态', render: function (rowdata, rowindex) {
 		                	if(!rowdata.productStatus){
 		                		return "未完成";
 		                	}else{
 		                		return rowdata.productStatusDesc;
 		                	}
		                }},
		                { display: '收货人', name: 'receiverName'},
		                { display: '品牌名称', name: 'brandName'},
		                { display: '名称', name:'productName'},
		                { display: '货号', name:'artNo'},
		                { display: '规格', name:'productPropDesc'},
		                { display: '结算单价', name:'settlePrice'},
		                { display: '数量', name:'quantity'},
		                { display: '商家优惠', name:'mchtPreferential'},
		                { display: '客户实付', name:'payAmount'},
		                { display: '供应商', render: function (rowdata, rowindex) {
		                	return '<a href="javascript:;" onclick="viewMcht('+rowdata.mchtId+','+"'"+rowdata.companyName+"'"+','+"'"+rowdata.mchtShortName+"'"+')">'+rowdata.mchtShortName+'</a>';
		                }},
		                { display: '应付货款', name:'settleAmount'},
		                { display: '结算单ID',name:'mchtSettleOrderId'},
		                { display: '付款状态',name:'payStatusDesc'}
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
		<input type="hidden" id="flag" value="${flag}">
		<input id="refund" type="hidden" name="refund" value="${refund}">
		<div class="search-tr" id="count">
			【收款情况 】  商品数量：0件，实收金额：0元，结算金额：0元，服务费：0元
			
			【退款情况 】  商品数量：0件，实退金额：0元，结算金额：0元，服务费：0元
			
			【应结算金额 】  应结算金额：0元，服务费总额：0元
		</div>
		<div class="search-tr"  > 
			<div class="search-td">
				<div class="search-td-label" >商家序号：</div>
				<div class="search-td-condition" >
					<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}">
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" >子订单号：</div>
				<div class="search-td-condition" >
					<input type="text" id="subOrderCode" name="subOrderCode" value="${subOrderCode}">
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" >品牌：</div>
				<div class="search-td-condition" >
					<input type="text" id="brandName" name="brandName" value="${brandName}">
				</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" >货号：</div>
				<div class="search-td-condition" >
					<input type="text" id="artNo" name="artNo" value="${artNo}">
				</div>
			</div>
		</div>
		
		<div class="search-tr"  > 
			<div class="search-td">
				<div class="search-td-label" >商品名称：</div>
				<div class="search-td-condition" >
					<input type="text" id="productName" name="productName" value="${productName}">
				</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="date_begin" name="date_begin" value="${date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#date_begin").ligerDateEditor( {
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
				<input type="text" id="date_end" name="date_end" value="${date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div style="display: inline-flex;">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="查看收款" id="viewAccept">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="查看退款" id="viewRefund">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
				</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>