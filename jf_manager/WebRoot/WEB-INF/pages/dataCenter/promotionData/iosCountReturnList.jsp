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
		labelAlign : 'left'
	 });
	 
	 // 禁止分页
	 $("#maingrid").ligerGrid({
       usePager:false
    });

	$("#searchbtn").bind('click',function(){
		var order_date_begin = $("#order_date_begin").val();
		var order_date_end = $("#order_date_end").val();
		var channel = $("#channel").val();
		if(!order_date_begin){
			commUtil.alertError("请选择下单日期");
			return false;
		}
		if(!order_date_end){
			commUtil.alertError("请选择下单日期");
			return false;
		}
		if(!channel){
			commUtil.alertError("请选择推广渠道");
			return false;
		}
	});	
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
	
 var listConfig={
     url:"/promotionData/iosCountReturn/data.shtml",
     listGrid:{ columns: [
						{ display: '活动名称',width:300,name:'spreadname'},
						{ display: '新客母订单数',width:100,name:'new_guest_combine_order_count'},
						/* { display: '新客消费人数',width:100,name:'new_guest_consume_count'}, */
			            { display: '新客买家实付金额',width:100, name:'new_guest_pay_amount'},
			            { display: '新客买家退货率',width:100, name:'new_quantity_rate'},
			            { display: '老客母订单数',width:100,name:'old_guest_combine_order_count'},
		                { display: '老客消费人数',width:100, name:'old_guest_consume_count'},
		                { display: '老客买家实付金额',width:100,name:'old_guest_pay_amount'},
		                { display: '老客买家退货率',width:100,name:'old_quantity_rate'},
		                { display: '总销售额', width:100,name: 'total_pay_amount'},
		                { display: '新客老客比',width:100,name:'rate'},
		                { display: '总销量/退货商品数',width:120,name:'customer_quantity'},
		                { display: '商品退货率 ',width:100,name:'quantity_rate'}
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
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" style="float:left;">
						<span style="color: #FF0000;font-weight: bold;">*</span>推广渠道：
					</div>
					<div class="search-td-combobox-condition">
						<select id="channel" name="channel" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="6">落地页</option>
							<c:forEach var="list" items="${channelList}">
								<option value="${list.statusValue}">${list.statusDesc}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">活动组：</div>
					<div class="search-td-combobox-condition">
						<select id="activityGroupId" name="activityGroupId" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="list" items="${spreadActivityGroupList}">
								<option value="A-${list.id}">${list.channelName}_${list.activityGroup}</option>
							</c:forEach>
							<c:forEach var="list" items="${spreadActivityGroupSetCustomList}">
								<option value="B-${list.id}">${list.channelName}_${list.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">
						<span style="color: #FF0000;font-weight: bold;">*</span>下单日期：
					</div>
					<div class="l-panel-search-item">
						<input type="text" id="order_date_begin" name="order_date_begin" value="${order_date_begin}" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="order_date_end" name="order_date_end" value="${order_date_end}" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">活动名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="spreadName" name="spreadName">
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
				<div class="search-td-search-btn">
					<div id="searchbtn">查询</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>