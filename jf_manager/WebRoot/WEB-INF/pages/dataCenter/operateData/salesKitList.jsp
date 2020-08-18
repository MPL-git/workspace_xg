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
 function getData(pay_date_begin,pay_date_end){
		$.ajax({
			url : "${pageContext.request.contextPath}/operateData/salesKit/data.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"pay_date_begin":pay_date_begin,"pay_date_end":pay_date_end},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$("#listContent").html("");
					var brandHtmlA = [];
					var brandHtmlB1 = [];
					var brandHtmlB2 = [];
					var brandHtmlC1 = [];
					var brandHtmlC2 = [];
					var brandHtmlC3 = [];
					var brandHtmlC4 = [];
					var brandHtmlC5 = [];
					var brandHtmlC6 = [];
					var brandHtmlC7 = [];
					var brandHtmlC8 = [];
					var brandHtmlC9 = [];
					var brandHtmlC10 = [];

/*					var brandHtmlD2 = [];
					var brandHtmlD3 = [];
					var brandHtmlD4 = [];
					var brandHtmlD5 = [];
					var brandHtmlD6 = [];
					var brandHtmlD7 = [];
					var brandHtmlD8 = [];
					var brandHtmlD9 = [];*/
					var brandHtmlE = [];
					for(var i=0;i<data.brandList.length;i++){
						var brandObj = data.brandList[i];
						var brandName = brandObj.name;
						var quantity = brandObj.quantity;
						var tmpStr = brandName+quantity+"件";
						if(brandObj.tot_type == "A"){
							brandHtmlA.push(tmpStr);
						}else if(brandObj.tot_type == "B"){
							if(brandObj.type == "1"){
								brandHtmlB1.push(tmpStr);
							}else if(brandObj.type == "2"){
								brandHtmlB2.push(tmpStr);
							}
						}else if(brandObj.tot_type == "C"){
							if(brandObj.type == "1"){
								brandHtmlC1.push(tmpStr);
							}else if(brandObj.type == "2"){
								brandHtmlC2.push(tmpStr);
							}else if(brandObj.type == "3"){
								brandHtmlC3.push(tmpStr);
							}else if(brandObj.type == "4"){
								brandHtmlC4.push(tmpStr);
							}else if(brandObj.type == "5"){
								brandHtmlC5.push(tmpStr);
							}else if(brandObj.type == "6"){
								brandHtmlC6.push(tmpStr);
							}else if(brandObj.type == "7"){
								brandHtmlC7.push(tmpStr);
							}else if(brandObj.type == "8"){
								brandHtmlC8.push(tmpStr);
							}else if(brandObj.type == "9"){
								brandHtmlC9.push(tmpStr);
							}else if(brandObj.type == "10"){
								brandHtmlC10.push(tmpStr);
							}
						}/*else if(brandObj.tot_type == "D"){
							if(brandObj.type == "服装配饰"){
								brandHtmlD1.push(tmpStr);
							}else if(brandObj.type == "生活家居"){
								brandHtmlD2.push(tmpStr);
							}else if(brandObj.type == "鞋靴箱包"){
								brandHtmlD3.push(tmpStr);
							}else if(brandObj.type == "运动户外"){
								brandHtmlD4.push(tmpStr);
							}else if(brandObj.type == "钟表珠宝"){
								brandHtmlD5.push(tmpStr);
							}else if(brandObj.type == "数码家电"){
								brandHtmlD6.push(tmpStr);
							}
							else if(brandObj.type == "美妆个护"){
								brandHtmlD7.push(tmpStr);
							}
							else if(brandObj.type == "母婴童装"){
								brandHtmlD8.push(tmpStr);
							}
							else if(brandObj.type == "食品超市"){
								brandHtmlD9.push(tmpStr);
							}
						}*/else if(brandObj.tot_type == "E"){
							brandHtmlE.push(tmpStr);
						}
					}
					brandHtmlA.reverse();
					brandHtmlB1.reverse();
					brandHtmlB2.reverse();
					brandHtmlC1.reverse();
					brandHtmlC2.reverse();
					brandHtmlC3.reverse();
					brandHtmlC4.reverse();
					brandHtmlC5.reverse();
					brandHtmlC6.reverse();
					brandHtmlC7.reverse();
					brandHtmlC8.reverse();
					brandHtmlC9.reverse();
					brandHtmlC10.reverse();
					/*brandHtmlD1.reverse();
					brandHtmlD2.reverse();
					brandHtmlD3.reverse();
					brandHtmlD4.reverse();
					brandHtmlD5.reverse();
					brandHtmlD6.reverse();
					brandHtmlD7.reverse();
					brandHtmlD8.reverse();
					brandHtmlD9.reverse();*/
					brandHtmlE.reverse();
					var brandHtmlAStr = "";
					var brandHtmlBStr = "";
					var brandHtmlCStr = "";
					var brandHtmlDStr = "";
					var brandHtmlEStr = "";
					if(brandHtmlA && brandHtmlA.length>0){
						brandHtmlAStr = brandHtmlA.join(",");
					}
					var html = [];
					for(var i=0;i<data.orderList.length;i++){
						var orderObj = data.orderList[i];
						var tmpStr = "";
						if(orderObj.tot_type == "A"){
							if(!orderObj.quantity){
								tmpStr = "<div id='allOrder'>【全部】<br><span>订单：0单，数量：0件，实收金额：0元，毛利：0元，毛利率：0%</span><br><span id='allOrderBrand'></span><br><br><br></div>";
							}else{
								tmpStr = "<div id='allOrder'>【全部】<br><span>订单："+orderObj.order_count+"单，数量："+orderObj.quantity+"件，实收金额："+orderObj.pay_amount+"元，毛利："+orderObj.commission_amount+"元，毛利率："+formatMoney(orderObj.rate,2)+"%</span><br><span id='allOrderBrand'>品牌排行："+brandHtmlAStr+"</span><br><br><br></div>";
							}
							html.push(tmpStr);
						}else if(orderObj.tot_type == "B"){
							var title = "";
							var titleRank = "";
							var rankDetail = "";
							if(orderObj.type == "1"){
								title = "会场";
								titleRank = "会场排行";
								var activityAreaSaleQuantity = orderObj.activity_sale_quantity;
								var activityAreaArray = activityAreaSaleQuantity.split("、");
								if(activityAreaArray && activityAreaArray.length>0){
									rankDetail = activityAreaArray.join();
								}
								if(brandHtmlB1 && brandHtmlB1.length>0){
									brandHtmlBStr = brandHtmlB1.join(",");
								}
							}else if(orderObj.type == "2"){
								title = "品牌特卖";
								titleRank = "特卖排行";
								var activitySaleQuantity = orderObj.activity_area_sale_quantity;
								var activityArray = activitySaleQuantity.split("、");
								if(activityArray && activityArray.length>0){
									rankDetail = activityArray.join();
								}
								if(brandHtmlB2 && brandHtmlB2.length>0){
									brandHtmlBStr = brandHtmlB2.join(",");
								}
							}
							tmpStr = "<div id='activityTypeOrder'>【栏目："+title+"】<br><span>订单："+orderObj.order_count+"单，数量："+orderObj.quantity+"件，实收金额："+orderObj.pay_amount+"元，毛利："+orderObj.commission_amount+"元，毛利率："+formatMoney(orderObj.rate,2)+"%</span><br><span id='activityTypeOrderBrand'>品牌排行："+brandHtmlBStr+"</span><br><span>"+titleRank+":"+rankDetail+"</span><br><br><br></div>";
							html.push(tmpStr);
						}else if(orderObj.tot_type == "C"){
							var title = "";
							if(orderObj.type == "1"){
								title = "新用户专享";
								if(brandHtmlC1 && brandHtmlC1.length>0){
									brandHtmlCStr = brandHtmlC1.join(",");
								}
							}else if(orderObj.type == "2"){
								title = "单品爆款";
								if(brandHtmlC2 && brandHtmlC2.length>0){
									brandHtmlCStr = brandHtmlC2.join(",");
								}
							}else if(orderObj.type == "3"){
								title = "限时抢购";
								if(brandHtmlC3 && brandHtmlC3.length>0){
									brandHtmlCStr = brandHtmlC3.join(",");
								}
							}else if(orderObj.type == "4"){
								title = "新用户秒杀";
								if(brandHtmlC4 && brandHtmlC4.length>0){
									brandHtmlCStr = brandHtmlC4.join(",");
								}
							}else if(orderObj.type == "5"){
								title = "积分商城";
								if(brandHtmlC5 && brandHtmlC5.length>0){
									brandHtmlCStr = brandHtmlC5.join(",");
								}
							}else if(orderObj.type == "6"){
								title = "断码清仓";
								if(brandHtmlC6 && brandHtmlC6.length>0){
									brandHtmlCStr = brandHtmlC6.join(",");
								}
							}else if(orderObj.type == "7"){
								title = "砍价免费拿";
								if(brandHtmlC7 && brandHtmlC7.length>0){
									brandHtmlCStr = brandHtmlC7.join(",");
								}
							}else if(orderObj.type == "8"){
								title = "邀请享免单";
								if(brandHtmlC8 && brandHtmlC8.length>0){
									brandHtmlCStr = brandHtmlC8.join(",");
								}
							}else if(orderObj.type == "9"){
								title = "优惠券商品";
								if(brandHtmlC9 && brandHtmlC9.length>0){
									brandHtmlCStr = brandHtmlC9.join(",");
								}
							}else if(orderObj.type == "10"){
								title = "助力大减价";
								if(brandHtmlC10 && brandHtmlC10.length>0){
									brandHtmlCStr = brandHtmlC10.join(",");
								}
							}
							if(orderObj.type != "8"){
								tmpStr = "<div id='singleProductActivityOrder'>【栏目："+title+"】<br><span>订单："+orderObj.order_count+"单，数量："+orderObj.quantity+"件，实收金额："+orderObj.pay_amount+"元，毛利："+orderObj.commission_amount+"元，毛利率："+formatMoney(orderObj.rate,2)+"%</span><br><span id='singleProductActivityOrderBrand'>品牌排行："+brandHtmlCStr+"</span><br><br><br></div>";
							}else{
								tmpStr = "<div id='singleProductActivityOrder'>【栏目："+title+"】<br><span>订单："+orderObj.order_count+"单，数量："+orderObj.quantity+"件，实收金额："+orderObj.pay_amount+"元，毛利："+orderObj.commission_amount+"元，毛利率：</span><br><span id='singleProductActivityOrderBrand'>品牌排行："+brandHtmlCStr+"</span><br><br><br></div>";
							}
							html.push(tmpStr);
						}else if(orderObj.tot_type == "D"){
							var brandHtmlD1 = [];
							for(var z=0;z<data.brandList.length;z++){
								if(orderObj.type == data.brandList[z].type && data.brandList[z].tot_type == "D"){
									var brandObj = data.brandList[z];
									var brandName = brandObj.name;
									var quantity = brandObj.quantity;
									var tmpStrs = brandName+quantity+"件";
									brandHtmlD1.push(tmpStrs);
								}
							}
							brandHtmlD1.reverse();
							brandHtmlDStr = brandHtmlD1.join(",");

							tmpStr = "<div id='productTypeOrder'>【类目："+orderObj.type+"】<br><span>订单："+orderObj.order_count+"单，数量："+orderObj.quantity+"件，实收金额："+orderObj.pay_amount+"元，毛利："+orderObj.commission_amount+"元，毛利率："+formatMoney(orderObj.rate,2)+"%</span><br><span id='productTypeOrderBrand'>品牌排行："+brandHtmlDStr+"</span><br><br><br></div>";
							html.push(tmpStr);
						}else if(orderObj.tot_type == "E"){
							if(brandHtmlE && brandHtmlE.length>0){
								brandHtmlEStr = brandHtmlE.join(",");
							}
							if(!orderObj.quantity){
								tmpStr = "<div id='allOrder'>【全部】<br><span>订单：0单，数量：0件，实收金额：0元，毛利：0元，毛利率：0%</span><br><span id='allOrderBrand'></span><br><br><br></div>";
								tmpStr = "<div id='tgOrder'>【栏目：推广会场】<br><span>订单：0单，数量：0件，实收金额：0元，毛利：0元，毛利率：0%</span><br><span id='tgOrderBrand'>品牌排行："+brandHtmlEStr+"</span><br><br><br></div>";
							}else{
								tmpStr = "<div id='tgOrder'>【栏目：推广会场】<br><span>订单："+orderObj.order_count+"单，数量："+orderObj.quantity+"件，实收金额："+orderObj.pay_amount+"元，毛利："+orderObj.commission_amount+"元，毛利率："+formatMoney(orderObj.rate,2)+"%</span><br><span id='productTypeOrderBrand'>品牌排行："+brandHtmlEStr+"</span><br><br><br></div>";
							}
							html.push(tmpStr);
						}
					}
					$("#listContent").html(html.join(""));
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
$(function(){
	// 禁止分页
	 $("#maingrid").ligerGrid({
       usePager:false
  	 });
	
	var pay_date_begin = $("#pay_date_begin").val();
	var pay_date_end = $("#pay_date_end").val();
	getData(pay_date_begin,pay_date_end);
	
	$("#searchbtn").bind('click',function(){
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		getData(pay_date_begin,pay_date_end);
	});
});
	
 var listConfig={
     url:"",
     listGrid:{ columns: [
						
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  
</script>
</head>
<body style="padding: 10px;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">付款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${payDateBegin}"/>
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
				<input type="text" id="pay_date_end" name="pay_date_end" value="${payDateEnd}"/>
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
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >查看</div>
			</div>
		</div>
		</div>
		<div class="table-title" id="listContent">
			
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>