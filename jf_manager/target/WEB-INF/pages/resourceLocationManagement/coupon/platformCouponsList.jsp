<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript">
 function gridRefresh() {
	    if (listModel.gridManager) {
	        var gridparms = [];
	        gridparms.push({ name: "staff", value: "self" });
	        listModel.gridManager.loadServerData(gridparms);
	    }
	}
 
 function viewCoupon(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-600,
		title: "查看优惠劵",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/coupon/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}

 function editCoupon(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-600,
		title: "管理优惠劵",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/coupon/edit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
 function couponDtl(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-400,
		title: "优惠劵明细",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/coupon/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}

  var listConfig={
	  
      url:"/resourceLocationManagement/platformCouponsListData.shtml",
    
      listGrid:{ columns: [  { display: '优惠劵ID', name: 'id', width: 100}, 
                        { display: '优惠劵名称', name: 'name', width: 200},
 		                { display: '面额/使用条件',width: 200, render: function (rowdata, rowindex) {
 		                	if (rowdata.preferentialType=='1') {
							   return rowdata.money+"元 / 满"+rowdata.minimum+"元 ";	
							}else {
								var discount=rowdata.discount*10;
								    discounts  = discount.toFixed(1);
								if (rowdata.conditionType=='1' && rowdata.maxDiscountMoney!=null) {
									 return discounts+"折 /无条件/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='1' && rowdata.maxDiscountMoney==null) {
									return discounts+"折 /无条件/折扣不设上限";
								}else if (rowdata.conditionType=='2' && rowdata.maxDiscountMoney!=null) {
									 return discounts+"折 /满"+rowdata.minimum+"元可用/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='2' && rowdata.maxDiscountMoney==null) {
									return discounts+"折 /满"+rowdata.minimum+"元可用/折扣不设上限";
								}else if (rowdata.conditionType=='3' && rowdata.maxDiscountMoney!=null){
									 return discounts+"折 /满"+rowdata.minicount+"件可用/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='3' && rowdata.maxDiscountMoney==null) {
									return discounts+"折 /满"+rowdata.minicount+"件可用/折扣不设上限";
								}
							}
		                }},
		                { display: '优惠券类型', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.preferentialType==1){
		                    	html.push("满减");
		                    }
		                    if (rowdata.preferentialType==2){
		                    	html.push("折扣");
		                    }
							return html.join("");
		                }},
		                { display: '品类范围', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.couponType==1){
		                    	html.push("平台");
		                    }
		                    if (rowdata.couponType==2){
		                    	html.push("品类");
		                    }
		                    if (rowdata.couponType==3){
		                    	html.push("品牌");
		                    }
		                    if (rowdata.couponType==4){
		                    	html.push("商品");
		                    }
							return html.join("");
		                }},
		         /*        { display: '使用量/领取量/发行量', width: 150, render: function (rowdata, rowindex) {
							return rowdata.useQuantity+" / "+rowdata.recQuantity+" / "+rowdata.grantQuantity;
		                }}, */
		                { display: '领取时间', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.recBeginDate!=null){
		                    	var beginDate=new Date(rowdata.recBeginDate);
		                    	html.push("起 "+ beginDate.format("yyyy-MM-dd hh:mm"));
		                    }
		                    if (rowdata.recEndDate!=null){
		                    	var endDate=new Date(rowdata.recEndDate);
		                    	html.push("止 "+endDate.format("yyyy-MM-dd hh:mm"));
		                    }
							return html.join("<br>");
		                }},
		                { display: '活动时间', width: 150 , render: function (rowdata, rowindex) {
		                	if (rowdata.expiryType==2){
		                		return rowdata.expiryDays+"天";
		                	}else{
		                		if (rowdata.expiryBeginDate!=null){
		                		var beginDate=new Date(rowdata.expiryBeginDate);
								var endDate=new Date(rowdata.expiryEndDate);
								return "起 "+ beginDate.format("yyyy-MM-dd hh:mm")+"<br>止 "+endDate.format("yyyy-MM-dd hh:mm");
		                		}
		                	}
		                }},
		                { display: '发行量',  name: 'grantQuantity'},
		                { display: '领取量',  name: 'recQuantity'},
		                { display: '使用量',  name: 'useQuantity'},
		                { display: '状态', render: function(rowdata, rowindex) {
		                	if (rowdata.status==0){
		                		return "<span style='color:#CC0000'>"+rowdata.statusDesc+"</span>";
		                	}else{
		                		return rowdata.statusDesc;
		                	}
		                }},
		                { display: '是否失效', render: function(rowdata, rowindex) {
		                	if (rowdata.secondNum>0){
		                		return "已失效";
		                	}else{
		                		return "未失效";
		                	}
		                }},
		                { display: '操作', name: "OPER", align: "center",  render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:viewCoupon(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    return html.join("");
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
	<c:if test="${roleType!=1}">
	<div id="toptoolbar"></div>
	</c:if>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">领取开始时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="recBeginDate" name="recBeginDate" value="${nowDate}" />
				<script type="text/javascript">
					$(function() {
						$("#recBeginDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>
			
			<div class="search-td">
			 <div class="search-td-label" >时间点：</div>
			 <div class="search-td-condition" >
				<select id="couponCenterTime" name="couponCenterTime">
					<option value="">请选择</option>
					<c:forEach items="${couponCenterTimes}" var="couponCenterTime">
						<option value="${couponCenterTime.startHours}:${couponCenterTime.startMin}">${couponCenterTime.startHours}:${couponCenterTime.startMin}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 
			 <div class="search-td">
			 <div class="search-td-label" >类型：</div>
			 <div class="search-td-condition" >
				<select id="preferentialType" name="preferentialType">
					<option value="">请选择...</option>
					<option value="1">满减</option>
					<option value="2">折扣</option>
				</select>
		 	 </div>
			 </div>
			 
			 
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>