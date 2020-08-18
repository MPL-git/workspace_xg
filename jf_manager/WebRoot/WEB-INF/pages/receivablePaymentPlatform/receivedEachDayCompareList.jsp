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
		  
	  url:"/receivablePaymentPlatform/receivedEachDayCompareData.shtml",
      listGrid:{ columns: [
                        { display: '日期', render: function(rowdata, rowindex) {
                        	var each_day = new Date(rowdata.each_day);
							return each_day.format("yyyy-MM-dd");
    					}},
 		                { display: '应收金额', render: function(rowdata, rowindex) {
		                	if(rowdata.combine_amount){
	 		                	return formatMoney(rowdata.combine_amount,2);
 		                	}else{
 		                		return "-";
 		                	}
    					}},
		                { display: '当日回款', render: function(rowdata, rowindex) {
		                	if(rowdata.right_day_amount){
	 		                	return formatMoney(rowdata.right_day_amount,2);
 		                	}else{
 		                		return "-";
 		                	}
    					}},
		                { display: '非当日回款', render: function(rowdata, rowindex) {
		                	if(rowdata.not_right_day_amount){
	 		                	return formatMoney(rowdata.not_right_day_amount,2);
 		                	}else{
 		                		return "-";
 		                	}
    					}},
		                { display: '未回款', render: function(rowdata, rowindex) {
		                	if(rowdata.not_amount){
	 		                	return formatMoney(rowdata.not_amount,2);
 		                	}else{
 		                		return "-";
 		                	}
    					}},
		                { display: '日期', render: function (rowdata, rowindex) {
		                	var each_day2 = new Date(rowdata.each_day2);
							return each_day2.format("yyyy-MM-dd");
		                }},
		                { display: '实收金额', render: function(rowdata, rowindex) {
		                	if(rowdata.received_amount){
	 		                	return formatMoney(rowdata.received_amount,2);
 		                	}else{
 		                		return "-";
 		                	}
    					}},
		                { display: '实收当日', render: function (rowdata, rowindex) {
		                	if(rowdata.received_right_day_amount){
	 		                	return formatMoney(rowdata.received_right_day_amount,2);
 		                	}else{
 		                		return "-";
 		                	}
		                }},
		                { display: '实收非当日', render: function (rowdata, rowindex) {
		                	if(rowdata.received_not_right_day_amount){
	 		                	return formatMoney(rowdata.received_not_right_day_amount,2);
 		                	}else{
 		                		return "-";
 		                	}
		                }},
		                { display: '未在应收中', render: function (rowdata, rowindex) {
		                	if(rowdata.not_received){
	 		                	return formatMoney(rowdata.not_received,2);
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
			 <div class="search-td-label">年月：</div>
			 <div class="search-td-condition" >
				<select id="year" name="year">
					<option value="">请选择</option>
						<option value="2016" <c:if test="${defaultYear eq 2016 }">selected="selected"</c:if>>2016</option>
						<option value="2017" <c:if test="${defaultYear eq 2017 }">selected="selected"</c:if>>2017</option>
						<option value="2018" <c:if test="${defaultYear eq 2018 }">selected="selected"</c:if>>2018</option>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label"></div>
			 <div class="search-td-condition" >
				<select id="month" name="month">
					<option value="">请选择</option>
						<option value="01" <c:if test="${defaultMonth eq 01 }">selected="selected"</c:if>>1月</option>
						<option value="02" <c:if test="${defaultMonth eq 02 }">selected="selected"</c:if>>2月</option>
						<option value="03" <c:if test="${defaultMonth eq 03 }">selected="selected"</c:if>>3月</option>
						<option value="04" <c:if test="${defaultMonth eq 04 }">selected="selected"</c:if>>4月</option>
						<option value="05" <c:if test="${defaultMonth eq 05 }">selected="selected"</c:if>>5月</option>
						<option value="06" <c:if test="${defaultMonth eq 06 }">selected="selected"</c:if>>6月</option>
						<option value="07" <c:if test="${defaultMonth eq 07 }">selected="selected"</c:if>>7月</option>
						<option value="08" <c:if test="${defaultMonth eq 08 }">selected="selected"</c:if>>8月</option>
						<option value="09" <c:if test="${defaultMonth eq 09 }">selected="selected"</c:if>>9月</option>
						<option value="10" <c:if test="${defaultMonth eq 10 }">selected="selected"</c:if>>10月</option>
						<option value="11" <c:if test="${defaultMonth eq 11 }">selected="selected"</c:if>>11月</option>
						<option value="12" <c:if test="${defaultMonth eq 12 }">selected="selected"</c:if>>12月</option>
				</select>
		 	 </div>
			 </div>
			 
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
			 </div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>