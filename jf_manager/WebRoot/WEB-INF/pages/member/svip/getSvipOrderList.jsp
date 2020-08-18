<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<script type="text/javascript">
$(function(){

	$(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left'
	});
	
});

function viewDetail(id) {
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()-50,
		title: "母订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/combine/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

var listConfig = {
		url:"/svipOrder/getSvipOrderList.shtml",
    	btnItems:[],
    	listGrid:{ columns: [
				{ display: '会员ID', name: 'memberId', width: 100, render: function(rowdata, rowindex) {
			      	var html = [];
			      	html.push(rowdata.memberId);
			      	if(rowdata.memberStatus == 'P') {
			      		html.push("</br><span style='color: red;'>黑名单</span>");
			      	}
			      	return html.join("");
			    } },
			    { display: '昵称', name: 'memberNick', width: 200 },
			    { display: '会员状态', name: 'memberStatusDesc', width: 150 },
			    { display: '购买类型', name: 'buyTypeDesc', width: 150 },
				{ display: '支付状态', name: 'svipOrderDesc', width: 150 },
 	            { display: '支付时间', width: 200, render: function (rowdata, rowindex) {
                	if (rowdata.payDate != null){
						var payDate = new Date(rowdata.payDate);
						return payDate.format("yyyy-MM-dd hh:mm:ss");
                	}
                }},
                { display: '购买年限', name: 'yearsOfPurchase', width: 100 },
                { display: '支付金额', name: 'payAmount', width: 100 },
				{ display: "母订单编号", width: 160, render: function(rowdata, rowindex) {
					if(rowdata.combineOrderId != null){
						var html = [];
						html.push("<a href=\"javascript:viewDetail(" + rowdata.combineOrderId + ");\">"+rowdata.combineOrderCode+"</a>");
						return html.join("");
					}
				}},
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
 
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">会员ID：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="memberId" name="memberId">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">购买类型：</div>
					<div class="search-td-combobox-condition">
						<select id="buyType" name="buyType" style="width: 135px;">
							<option value="">请选择</option>
							<c:forEach items="${buyTypeList}" var="buyType">
								<option value="${buyType.statusValue }">${buyType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">支付状态：</div>
					<div class="search-td-combobox-condition">
						<select id="statusType" name="statusType" style="width: 135px;">
							<option value="">请选择</option>
							<c:forEach items="${statusTypeList}" var="statusType">
								<option <c:if test="${statusType.statusValue =='1'}">selected = "selected"</c:if> value="${statusType.statusValue }">${statusType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">支付时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="payDateBegin" name="payDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="payDateEnd" name="payDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" style="margin-right: 20%;">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js">
</script>