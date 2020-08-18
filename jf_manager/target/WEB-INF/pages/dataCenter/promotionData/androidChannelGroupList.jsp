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
	// 禁止分页
	$("#maingrid").ligerGrid({
       usePager:false
    });

	$("#searchbtn2").bind('click',function(){
		var orderDateBegin = $("#orderDateBegin").val();
		var orderDateEnd = $("#orderDateEnd").val();
		var androidChannelGroupSetStr = $("#androidChannelGroupSetStr").val();
		if(!orderDateBegin){
			commUtil.alertError("请选择下单日期");
			return false;
		}
		if(!orderDateEnd){
			commUtil.alertError("请选择下单日期");
			return false;
		}
		if(!androidChannelGroupSetStr){
			commUtil.alertError("请选择安卓渠道集");
			return false;
		}
	});	
 });
 
 function formatMoney(s, n){
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
     url:"/promotionData/androidChannelGroup/androidChannelGroupList.shtml",
  	 btnItems:[],
     listGrid:{ columns: [
			{ display: '下单日期', width: 120, render: function (rowdata, rowindex) {
				if (rowdata.total_consumption == null ){
					return "<span style='color:#F00;'>"+rowdata.create_date+"</span>";
				}else{
					return rowdata.create_date;
				}
			}},
			{ display: '渠道集合', width: 200, render: function (rowdata, rowindex) {
				if (rowdata.total_consumption == null ){
					return "<span style='color:#F00;'>"+rowdata.group_name+"</span>";
				}else{
					return rowdata.group_name;
				}
			}},
			{ display: '账面消耗',width:100,name:'total_consumption'},
			{ display: '实际消耗',width:100,name:'actual_consumption'},
			{ display: '激活用户',width:100,name:'conversion_quantity'},
			{ display: '单个激活成本',width:100,name:'single_active_cost'},
			{ display: '新客母订单数',width:100,name:'new_guest_combine_order_count'},
			{ display: '新购成本',width:100,name:'new_guest_cost'},
			{ display: '新客买家实付金额',width:130, name:'new_guest_pay_amount'},
			{ display: '新购客单价',width:100, name:'new_unit_price'},
			{ display: '老客母订单数',width:100,name:'old_guest_combine_order_count'},
			{ display: '老客消费人数',width:100, name:'old_guest_consume_count'},
			{ display: '老客买家实付金额',width:150,name:'old_guest_pay_amount'},
			{ display: '老客客单价',width:100, name:'old_unit_price'},
			{ display: '总销售额', width:100,name: 'total_pay_amount'},
			{ display: '新客老客比',width:100,name:'rate'},
			{ display: 'ROI',width:100,name:'roi'}
		 ],
		 showCheckbox : false,  //不设置默认为 true
		 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
    	toolBarName:"toptoolbar",
        searchBtnName:"searchbtn2",
        fromName:"dataForm",
        listGridName:"maingrid"
	 }
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<%--<div id="toptoolbar"></div>--%>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
				<div class="search-td-label" style="float:left;">
					<span style="color: #FF0000;font-weight: bold;">*</span>下单日期：
				</div>
				<div class="l-panel-search-item" >
					<input type="text" id="orderDateBegin" name="orderDateBegin" />
					<script type="text/javascript">
						$(function() {
							$("#orderDateBegin").ligerDateEditor( {
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
						<input type="text" id="orderDateEnd" name="orderDateEnd" />
						<script type="text/javascript">
							$(function() {
								$("#orderDateEnd").ligerDateEditor( {
									showTime : false,
									labelWidth : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">
						<span style="color: #FF0000;font-weight: bold;">*</span>来源渠道组：
					</div>
					<div class="search-td-condition" >
						<select id="androidChannelGroupSetStr" name="androidChannelGroupSetStr">
							<option value="">请选择</option>
							<c:forEach var="androidChannelGroupSet" items="${androidChannelGroupSetList}">
								<option value="${androidChannelGroupSet.id},${androidChannelGroupSet.name}（组）">${androidChannelGroupSet.name}（组）</option>
							</c:forEach>
						</select>
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
					<div id="searchbtn2">
						查看
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