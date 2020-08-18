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
	
	$("#atypism").bind('click',function(){
		if($(this).attr("checked")){
			$(this).val("1");
		}else{
			$(this).val("0");
		}
	});
	
	$("#export").bind('click',function(){
		var type = $("#type").val();
		var paymentType = $("#paymentType").val();
		var atypism = $("#atypism").val();
		var customer_pay_date_begin = $("#customer_pay_date_begin").val();
		var customer_pay_date_end = $("#customer_pay_date_end").val();
		location.href="${pageContext.request.contextPath}/receivablePaymentPlatform/exportXGPaymentPlatform.shtml?type="+type+"&paymentType="+paymentType+"&customer_pay_date_begin="+customer_pay_date_begin+"&customer_pay_date_end="+customer_pay_date_end+"&atypism="+atypism;
	});
	
});
	
//批量删除
function batchDelete(ids){
	   $.ajax({ //ajax提交
				type:'POST',
				url:'${pageContext.request.contextPath}' +"/receivablePaymentPlatform/batchDelete.shtml?ids="+ids,
				data:"",
				dataType:"json",
				cache: false,
				success: function(result){
				  if(result.returnCode == "0000"){
	                 $.ligerDialog.success("批量删除成功",function() {
	                            javascript:location.reload();
						});
				  }else{
				     commUtil.alertError(result.returnMsg);
				  }
				},
				error: function(e){
				 commUtil.alertError("系统异常请稍后再试");
				}
	    });
}
	
  var listConfig={
		  
	  url:"/receivablePaymentPlatform/xgPaymentPlatformData.shtml",
      listGrid:{ columns: [
                        { display: "客户付款日期", render: function(rowdata, rowindex) {
                        	var payDate = new Date(rowdata.payDate);
							return payDate.format("yyyy-MM-dd");
    					}},
 		                { display: '母订单号', name: 'combineOrderCode'},
		                { display: '交易号', name:'paymentNo'},
		                { display: '金额', render: function(rowdata, rowindex) {
		                	if(rowdata.totalPayAmount){
	 		                	return formatMoney(rowdata.totalPayAmount,2);
 		                	}else{
 		                		return "0.00";
 		                	}
    					}},
		                { display: '类型', render: function(rowdata, rowindex) {
		                	var payDate = new Date(rowdata.payDate);
							var receivedDate = new Date(rowdata.receivedDate);
		                	if(!rowdata.receivedDate || !rowdata.receivedAmount){
		                		return "未回款";
		                	}else if(payDate.format("yyyy-MM-dd") == receivedDate.format("yyyy-MM-dd")){
		                		return "当日回款";
		                	}else if(payDate.format("yyyy-MM-dd") != receivedDate.format("yyyy-MM-dd")){
		                		return "非当日回款";
		                	}
    					}},
		                { display: '支付渠道', render: function (rowdata, rowindex) {
		                	if(rowdata.paymentId==1 || rowdata.paymentId==6){
		                		return "支付宝（APP、H5）";
		                	}else if(rowdata.paymentId==2 || rowdata.paymentId==5){
		                		return "微信（APP、H5）";
		                	}else if(rowdata.paymentId==4){
		                		return "微信（公众号）";
		                	}
		                }},
		                { display: '回款日期', render: function(rowdata, rowindex) {
		                	if(rowdata.receivedDate){
	                        	var receivedDate = new Date(rowdata.receivedDate);
								return receivedDate.format("yyyy-MM-dd");
		                	}else{
		                		return "-";
		                	}
    					}},
		                { display: '回款金额', render: function (rowdata, rowindex) {
		                	if(rowdata.receivedAmount){
	 		                	return formatMoney(rowdata.receivedAmount,2);
 		                	}else{
 		                		return "-";
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
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">客户付款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="customer_pay_date_begin" name="customer_pay_date_begin" value="${customer_pay_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#customer_pay_date_begin").ligerDateEditor({
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
				<input type="text" id="customer_pay_date_end" name="customer_pay_date_end" value="${customer_pay_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#customer_pay_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			 
			 <div class="search-td">
			 <div class="search-td-label">类型：</div>
			 <div class="search-td-condition" >
				<select id="type" name="type">
					<option value="">请选择</option>
						<option value="1">当日回款</option>
						<option value="2">非当日回款</option>
						<option value="3">未回款</option>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >回款金额：<input type="checkbox" id="atypism" name="atypism"></div>
			 <div class="search-td-condition" >
				不一致
		 	 </div>
			 </div>
			 
		</div>
		
		<div class="search-tr"  > 
			<div class="search-td">
			 <div class="search-td-label" >支付渠道：</div>
			 <div class="search-td-condition" >
				<select id="paymentType" name="paymentType">
					<option value="">请选择</option>
					<option value="1">微信（APP、H5）</option>
					<option value="2">微信(公众号)</option>
					<option value="3">支付宝(APP、H5)</option>
				</select>
		 	 </div>
			 </div>
			
			<div style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					查看
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
				</div>
			</div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>