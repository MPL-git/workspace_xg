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
		labelAlign : 'left',
		labelwidth: 120,
		width:120
	});
	
});

//导出
function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/svipOrder/exportFinanceSvipOrderList.shtml");
	$("#dataForm").submit();
}

//删除方法
function updateStatus(id,memberId) {
	$.ligerDialog.confirm("确定将会员"+memberId+"退款吗", function(yes) {
		if(yes) {

			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/svipOrder/SvipOrderUpdateStatus.shtml",
				 data : {id : id,memberId : memberId,status : '2'},
				 dataType : 'json',
				 success : function(data){
					 if(data.returnCode == "0000" )
						 $("#searchbtn").click();
						 
					 else{
						 commUtil.alertError(data.returnMsg); 
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}
	});
}
	
var listConfig = {
		url:"/svipOrder/financeSvipOrderList.shtml",
    	btnItems:[],
    	listGrid:{ columns: [
				{ display: '订单编号', name: 'orderCode', width: 160 },
				{ display: '会员ID', name: 'memberId', width: 100, render: function(rowdata, rowindex) {
			      	var html = [];
			      	html.push(rowdata.memberId);
			      	if(rowdata.memberStatus == 'P') {
			      		html.push("</br><span style='color: red;'>黑名单</span>");
			      	}
			      	return html.join("");
			    } },
			    { display: '昵称', name: 'memberNick', width: 150 },
			    { display: '购买年限', name: 'yearsOfPurchase', width: 100 },
			    { display: '付款渠道', name: 'paymentName', width: 100 },
			    { display: '实付金额', name: 'payAmount', width: 100 },
			    { display: '订单状态', name: 'svipOrderDesc', width: 100 },
			    { display: '付款时间', width: 160, render: function (rowdata, rowindex) {
                	if (rowdata.payDate != null){
						var payDate = new Date(rowdata.payDate);
						return payDate.format("yyyy-MM-dd hh:mm:ss");
                	}
                }},
			    { display: '支付交易号', name: 'paymentNo', width: 160 },
			    { display: '交易完成时间', width: 160, render: function (rowdata, rowindex) {
                	if (rowdata.dealCompleteDate != null){
						var dealCompleteDate = new Date(rowdata.dealCompleteDate);
						return dealCompleteDate.format("yyyy-MM-dd hh:mm:ss");
                	}
                }},
                { display: '操作', width: 160, render: function (rowdata, rowindex) {
                	if (rowdata.status == "1"){
						return "<a href=\"javascript:updateStatus(" + rowdata.id +","+ rowdata.memberId + ");\">退款</a>";
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
					<div class="search-td-label">订单编号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="orderCode" name="orderCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">支付交易号：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="paymentNo" name="paymentNo">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >付款渠道：</div>
					<div class="search-td-combobox-condition">
						<select id="paymentId" name="paymentId" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1" <c:if test="${paymentId eq 1}">selected="selected"</c:if>>微信APP/H5</option>
							<option value="2" <c:if test="${paymentId eq 2}">selected="selected"</c:if>>支付宝</option>
							<option value="3" <c:if test="${paymentId eq 3}">selected="selected"</c:if>>微信公众号/小程序</option>
						</select>
				 	</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="payDateBegin" name="payDateBegin" class="dateEditor" value="${nowDate}" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="payDateEnd" name="payDateEnd" class="dateEditor" value="${nowDate}" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();">
					</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js">
</script>