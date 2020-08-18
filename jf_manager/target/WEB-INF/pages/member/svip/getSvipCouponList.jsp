<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript">

$(function(){
 
	 $(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left'
	 });
 
});

//管理优惠劵
function svipCouponEdit(id) {
		$.ligerDialog.open({
		height: 550,
		width: 700,
		title: "管理优惠劵",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/coupon/svipCouponEditManager.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//优惠劵明细
function svipCouponDetail(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-400,
		title: "优惠劵明细",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/coupon/svipCouponDetailManager.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

var listConfig = {
      url:"/coupon/getSvipCouponList.shtml",
      btnItems:[{ text: '创建优惠劵', icon: 'add', dtype:'win',  click: itemclick, url: "/coupon/svipCouponAddManager.shtml", seqId:"", width: 720, height: 700}],
      listGrid:{ columns: [  
                        { display: '优惠劵ID', name: 'id', width: 100}, 
                        { display: '优惠劵名称', name: 'name', width: 200},
		                { display: '面额/使用条件',width: 230,render: function (rowdata, rowindex) {
							var html="<div id='usingDetail"+rowdata.id+"'>";
							if (rowdata.preferentialType=='1') {
								html +=  rowdata.money+"元 / 满"+rowdata.minimum+"元 ";
							}else {
								var discount=rowdata.discount*10;
								discounts  = discount.toFixed(1);
								if (rowdata.conditionType=='1' && rowdata.maxDiscountMoney!=null) {
									html +=  discounts+"折 /无条件/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='1' && rowdata.maxDiscountMoney==null) {
									html +=  discounts+"折 /无条件/折扣不设上限";
								}else if (rowdata.conditionType=='2' && rowdata.maxDiscountMoney!=null) {
									html +=  discounts+"折 /满"+rowdata.minimum+"元可用/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='2' && rowdata.maxDiscountMoney==null) {
									html +=  discounts+"折 /满"+rowdata.minimum+"元可用/折扣不设上限";
								}else if (rowdata.conditionType=='3' && rowdata.maxDiscountMoney!=null){
									html +=  discounts+"折 /满"+rowdata.minicount+"件可用/折扣上限"+rowdata.maxDiscountMoney;
								}else if (rowdata.conditionType=='3' && rowdata.maxDiscountMoney==null) {
									html +=  discounts+"折 /满"+rowdata.minicount+"件可用/折扣不设上限";
								}
							}
							html += "</div>";
							return html;
		                }},
		                { display: '类目', name: 'productTypeName', width: 100, render: function (rowdata, rowindex) {
							if(rowdata.productTypeName) {
								return rowdata.productTypeName;
							}else {
								return "不限";
							}
		                }}, 
		                { display: '使用量/领取量/发行量', width: 150, render: function (rowdata, rowindex) {
							return rowdata.useQuantity+" / "+rowdata.recQuantity+" / "+rowdata.grantQuantity;
		                }},
		                { display: '领取时间', width: 200, render: function (rowdata, rowindex) {
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
		                { display: '使用时间类型',  name: 'expiryTypeDesc', width: 100}, 
		                { display: '有效期',  name: 'expiryDays', width: 150,render:function(rowdata, rowindex){
		                	if (rowdata.expiryType=='2') {
		                		return rowdata.expiryDays+"天";
							}else {
								if (rowdata.expiryBeginDate!=null){
			                		var beginDate=new Date(rowdata.expiryBeginDate);
									var endDate=new Date(rowdata.expiryEndDate);
									return "起 "+ beginDate.format("yyyy-MM-dd hh:mm")+"<br>止 "+endDate.format("yyyy-MM-dd hh:mm");
			                	}
							}
		                }}, 
		                { display: '限领', width: 180, render: function(rowdata, rowindex) {
		                	if (rowdata.recLimitType=='1'){
		                		return "每人每天限领1张";
		                	}else if (rowdata.recLimitType=='2') {
		                		return "每人限领指定数量";
							}else if (rowdata.recLimitType=='3') {
								return "不限";
							}else if (rowdata.recLimitType=='4') {
								return "每人每月限领1张";
							}
		                }},
		                { display: '状态', width: 100, render: function(rowdata, rowindex) {
		                	if (rowdata.status==0){
		                		return "<span style='color:#CC0000'>"+rowdata.statusDesc+"</span>";
		                	}else{
		                		return rowdata.statusDesc;
		                	}
		                }},
		                { display: '操作', name: '', align: 'center',width: 120, render: function(rowdata, rowindex) {
							var html = [];
							if(${managerFlag}) {
							    html.push("<a href=\"javascript:svipCouponEdit(" + rowdata.id + ");\">管理</a>"); 
							    html.push("&nbsp;&nbsp;|&nbsp;&nbsp;"); 
							}
						    html.push("<a href=\"javascript:svipCouponDetail(" + rowdata.id + ");\">明细</a>");
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >优惠劵ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id" >
					</div>
				</div>
			 	<div class="search-td">
			 		<div class="search-td-label" >状态:</div>
			 		<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;">
							<option value="">请选择</option>
							<c:forEach items="${statusList}" var="statusList">
								<option value="${statusList.statusValue}">${statusList.statusDesc}
								</option>
							</c:forEach>
						</select>
		 	 		</div>
			 	</div>
			 	<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">领取有效期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="recBeginDate" name="recBeginDate" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="recEndDate" name="recEndDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label" >优惠劵名称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "name" name="name" >
			</div>
			</div>
			
			<div class="search-td">
			 		<div class="search-td-label" >限领:</div>
			 		<div class="search-td-combobox-condition" >
						<select id="recLimitType" name="recLimitType" style="width: 135px;">
							<option value="">请选择</option>
							<c:forEach items="${recLimitTypeList}" var="recLimitTypeList">
								<option value="${recLimitTypeList.statusValue}">${recLimitTypeList.statusDesc}
								</option>
							</c:forEach>
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