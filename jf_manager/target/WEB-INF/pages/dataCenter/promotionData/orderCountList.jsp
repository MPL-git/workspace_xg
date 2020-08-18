<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript">
 $(function(){
	
 	$(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelWidth : 150,
		labelAlign : 'left'
	});
	
	productTypelist();
	 
	// 禁止分页
	$("#maingrid").ligerGrid({
       usePager:false
   	});
	 
	$("#searchType").bind('change',function(){
		var searchType = $(this).val();
		location.href="${pageContext.request.contextPath}/promotionData/orderCount/list.shtml?searchType="+searchType;
	});
	 
	var data = [{id : "province", pid : null, data : "${pageContext.request.contextPath}/webcommon/prodata.shtml"},
	           {id : "city", pid : "province", data : "${pageContext.request.contextPath}/webcommon/areadata.shtml"}];
	LinkageComboBox.init(data);
	$("#province").ligerComboBox({selectBoxWidth: 200, selectBoxHeight: 200});
	$("#city").ligerComboBox({selectBoxWidth: 200, selectBoxHeight: 200});
	
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
     url:"/promotionData/orderCount/data.shtml",
     listGrid:{ columns: [
						{ display: '日期', hide:${searchType!=1},name:'eachDay'},
						{ display: '时间段', hide:${searchType!=2},name:'eachHour'},
						{ display: '手机品牌',hide:${searchType!=3},name:'mobileBrand'},
			            { display: '母订单数',width:160, name:'orderCount'},
			            { display: '子订单数',name:'subOrderCount'},
/* 		                { display: '活跃人数', name:'activeCount'}, */
		                { display: '消费人数', name:'consumeCount'},
/* 			            { display: '转化率', render: function (rowdata, rowindex) {
							return formatMoney(rowdata.conversionRate,2)+"%";	                		
		                }}, */
		                { display: '销售商品金额',name:'productSalePrice'},
		                { display: '平台优惠',  name: 'totalPlatformPreferential'},
		                { display: '积分金额',  name: 'totalIntegralPreferential'},
		                { display: '商家优惠',  name: 'totalMchtPreferential'},
		                { display: '实付金额', name:'totalPayAmount'}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true, //不设置默认为 true
                 beforeSearch:checkSearchCondition
      } ,  							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
 };
 
 function checkSearchCondition(){
     var beginDate=$("#order_date_begin").val();
     var endDate=$("#order_date_end").val();
     if(beginDate==""){
    	 commUtil.alertError("请选择订单开始日期"); 
    	 return false;
     }
     if(endDate==""){
    	 commUtil.alertError("请选择订单结束日期"); 
    	 return false;
     }
	 var startTime =  new Date(Date.parse(beginDate)).getTime();
	 var endTime =  new Date(Date.parse(endDate)).getTime();
	 var dates = Math.abs((startTime - endTime))/(1000*60*60*24);   
     if(dates>61){
    	 commUtil.alertError("订单时间段不能超过62天"); 
    	 return false;
     }
     return true;
 }
 
 //获取二级类目
 function productTypelist() {
	 var productTypeId = $("#firstProductTypeId").val();
	 if(productTypeId == '') {
	 	var option = [];
		option.push('<option value="">请选择...</option>');
		$("#secondProductTypeId").html(option.join(''));
		$("#secondProductTypeId").attr("disabled", "disabled");
	 }else {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/productTypelist.shtml',
			dataType : 'json',
			data: {productTypeId:productTypeId},
			success: function(data) {
				if(data.code == 200) {
					var option = [];
					option.push('<option value="">请选择...</option>');
					for(var i=0;i<data.productTypeList.length;i++) {
						option.push('<option value="'+data.productTypeList[i].id+'">'+data.productTypeList[i].name+'</option>');
					}
					$("#secondProductTypeId").html(option.join(''));
					$("#secondProductTypeId").attr("disabled", "");
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError('操作超时，请稍后再试！');
			}
		});
	}	
 }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
					<div class="l-panel-search-item">
						<input type="text" id="order_date_begin" name="order_date_begin" value="${order_date_begin}" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="order_date_end" name="order_date_end" value="${order_date_end}" class="dateEditor" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">来源渠道：</div>
					<div class="search-td-combobox-condition" >
						<select id="sprChnl" name="sprChnl" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sprChnl" items="${sprChnls}">
								<option value="${sprChnl.statusValue}">${sprChnl.statusDesc}</option>
							</c:forEach>
							<c:forEach var="androidChannelGroup" items="${androidChannelGroups}">
								<option value="${androidChannelGroup.prefix}">${androidChannelGroup.groupName}</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
			</div>	
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" style="float:left;">省：</div>
					<div class="l-panel-search-item">
						<input id="province" name="province" type="text"/>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">市：</div>
					<div class="l-panel-search-item" >
						<input id="city" name="city" type="text"/>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">付款状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1" selected="selected">已付款</option>
							<option value="0" >未付款</option>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">支付方式：</div>
					<div class="search-td-combobox-condition" >
						<select id="sysPayment" name="sysPayment" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="payment" items="${payments}">
								<option value="${payment.id}">${payment.remarks}</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >品牌名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="brandName" name="brandName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">一级类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="firstProductTypeId" name="firstProductTypeId" onchange="productTypelist();" style="width: 135px;" disabled="disabled" >
								<c:forEach var="firstProductType" items="${firstProductTypes}">
									<option value="${firstProductType.id}">${firstProductType.name}</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="firstProductTypeId" name="firstProductTypeId" onchange="productTypelist();" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="firstProductType" items="${firstProductTypes}">
									<option value="${firstProductType.id}">${firstProductType.name}</option>
								</c:forEach>
							</select>
						</c:if>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">二级类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="secondProductTypeId" name="secondProductTypeId" style="width: 135px;" >
							<option value="">请选择...</option>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >活动ID：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="activityId" name="activityId" >
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >会场ID：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="activityAreaId" name="activityAreaId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">下单系统：</div>
					<div class="search-td-combobox-condition" >
						<select id="sourceClient" name="sourceClient" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sourceClient" items="${sourceClients}">
								<option value="${sourceClient.statusValue}">${sourceClient.statusDesc}</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">消费人群：</div>
					<div class="search-td-combobox-condition" >
						<select id="consumePeople" name="consumePeople" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">新客户消费(第一次消费)</option>
							<option value="2">老客户消费(非第一次消费)</option>
							<option value="3">新用户消费(当天注册用户)</option>
							<option value="4">老用户消费(非当天注册用户)</option>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">查看方式：</div>
					<div class="search-td-combobox-condition" >
						<select id="searchType" name="searchType" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1" <c:if test="${searchType eq '1'}">selected</c:if> >按每天</option>
							<option value="2" <c:if test="${searchType eq '2'}">selected</c:if> >按小时</option>
							<option value="3" <c:if test="${searchType eq '3'}">selected</c:if> >按手机品牌</option>
						</select>
				 	</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >推广渠道：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="channel" name="channel" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >活动名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="spreadname" name="spreadname" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">访客类型：</div>
					<div class="search-td-combobox-condition">
						<select id="visitType" name="visitType" style="width: 135px;">
							<option value="firstVisit">首次访问</option>
							<option value="newVisit">最新访问</option>
							<option value="orderVisit">下单访问</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">IOS活动组：</div>
					<div class="search-td-combobox-condition" >
						<select id="spreadActivityGroupId" name="spreadActivityGroupId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="spreadActivityGroup" items="${spreadActivityGroupCustoms }">
								<option value="${spreadActivityGroup.id }">${spreadActivityGroup.activityGroup }</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >推广设备类型：</div>
					<div class="search-td-combobox-condition" >
						<select  id="regClient" name="regClient" style="width: 135px;" >
							<option value="1">IOS</option>
							<option value="2" selected >Android</option>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn">查看</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>