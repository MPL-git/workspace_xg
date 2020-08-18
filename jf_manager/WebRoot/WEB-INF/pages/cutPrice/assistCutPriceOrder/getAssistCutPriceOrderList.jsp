<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 150
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
	 
	//商品信息
	function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1200,
			title: "商品信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
	 //助力详情
	 function showCutPriceOrderDtl(cutPriceOrderId) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 300,
				title: "助力详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/assistCutPriceOrder/assistCutPriceOrderDtlManager.shtml?cutPriceOrderId="+cutPriceOrderId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 // 母订单详情
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
	 
 	 var listConfig={
	      url:"/assistCutPriceOrder/getAssistCutPriceOrderList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'助力编号',name:'orderCode', align:'center', isSort:false, width:180},
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:80},
							{display:'会员名称',name:'memberNick', align:'center', isSort:false, width:120},
							{display:'主图',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
								return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"'></div>";
							}},
							{display:'品牌 / 货号/ 商品ID',name:'brandName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return (rowdata.brandName==null?'':rowdata.brandName)
	                        		   + "<br/>" + 
	                        		   (rowdata.artNo==null?'':rowdata.artNo)
	                        		   + "<br/>" + 
	                        		   (rowdata.productCode==null?'':rowdata.productCode);
	                        }},
	                        {display:'店铺名 / 商品名',name:'shopName', align:'center', isSort:false, width:260, render:function(rowdata, rowindex) {
	                        	return (rowdata.shopName==null?'':rowdata.shopName) 
	                        			+ "<br/><a href=\"javascript:viewProduct(" + rowdata.productId + ");\">"
	                        			+ (rowdata.productName==null?'':rowdata.productName) + "</a>";
	                        }},
	                        {display:'活动价',name:'salePrice', align:'center', isSort:false, width:100},
	                        {display:'发起时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'状态',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if (rowdata.status == '1') {
									return "减价中";
								} else if(rowdata.status == '2') {
									return "减价完成";
								} else if(rowdata.status == '3') {
									return "任务超时";
								} else if(rowdata.status == '4') {
									return "已下单";
								} else {
									return "";
								}
							}},
							{display:'成功邀请人数',name:'cutPriceOrderDtlSuccessCount', align:'center', isSort:false, width:100},
							{display:'已减价金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if(rowdata.cutPriceOrderDtlSuccessCount > rowdata.maxInviteTimes) {
									return (rowdata.maxInviteTimes*rowdata.fixedAmount).toFixed(2);
								}else {
									return (rowdata.cutPriceOrderDtlSuccessCount*rowdata.fixedAmount).toFixed(2);
								}
							}},
							{display:'结算价',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if(rowdata.cutPriceOrderDtlSuccessCount > rowdata.maxInviteTimes) {
									return (rowdata.salePrice - rowdata.maxInviteTimes*rowdata.fixedAmount).toFixed(2);
								}else {
									return (rowdata.salePrice - rowdata.cutPriceOrderDtlSuccessCount*rowdata.fixedAmount).toFixed(2);
								}
							}},
							{display:'是否付款',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.combineOrderStatus == '1') {
									html.push("是");
								}else {
									html.push("否");
								}
								return html.join("");
							}},
							{display:'付款时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if (rowdata.payDate != null && rowdata.payDate != '') {
									var payDate = new Date(rowdata.payDate);
									return payDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'订单编号',name:'', align:'center', isSort:false, width: 160, render: function(rowdata, rowindex) {
	    						var html = [];
	    						if(rowdata.combineOrderCode != null) {
		    						html.push("<a href=\"javascript:viewDetail(" + rowdata.combineOrderId + ");\">"+rowdata.combineOrderCode+"</a>"); 
	    						}
	    						return html.join("");
	    					}},
							{display:'操作',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='showCutPriceOrderDtl("+rowdata.id+");'>【查看】</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 	
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >店铺名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="shopName" name="shopName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">减价中</option>
							<option value="2">减价完成</option>
							<option value="3">任务超时</option>
							<option value="4">已下单</option>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >是否付款：</div>
					<div class="search-td-combobox-condition" >
						<select id="payStatus" name="payStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
