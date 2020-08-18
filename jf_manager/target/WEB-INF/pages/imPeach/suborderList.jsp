<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
 function viewDetail(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
 var listConfig={
      url:"/businessCirclesAppealOrder/suborderdata.shtml?suborderList=${suborderList}",
      btnItems:[],
      listGrid:{ columns: [ 
						{display:'子订单编号',name:'', align:'center', width:200,render:function(rowdata, rowindex){
							var html = [];
    						html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">"+rowdata.subOrderCode+"</a>"); 
    						return html.join("");
						}},
						{display:'品牌/商品名称',name:'', align:'center', isSort:false, width:300,render:function(rowdata, rowindex){
							var brandProductName=rowdata.brandProductName
							if (brandProductName!=null) {
								return brandProductName.replace(/,/g,"<br>");
							}
						}},
						{ display: '实付金额', width: 100, name: 'payAmount'},
						{display:'订单状态',name:'statusDesc', align:'center', isSort:false, width:100},					
						{display:'付款时间',name:'', align:'center', isSort:false, width:300,render:function(rowdata, rowindex){
							var html = [];
							if(rowdata.orderPayDate != null && rowdata.orderPayDate!='') {
							       var orderPayDate = new Date(rowdata.orderPayDate);
								   html.push(orderPayDate.format("yyyy-MM-dd hh:mm"));
							  }
							  return html.join("");
						}},
						{display:'发货时间',name:'', align:'center', isSort:false, width:200,render:function(rowdata, rowindex){
							var html = [];
							if(rowdata.deliveryDate != null && rowdata.deliveryDate!='') {
							       var deliveryDate = new Date(rowdata.deliveryDate);
								   html.push(deliveryDate.format("yyyy-MM-dd hh:mm"));
							  }
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
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">	
			<div class="search-tr"  >
		 	  <div class="search-td">
					<div class="search-td-label" >子订单号：</div>
					<div class="search-td-condition" >
						<input type="text" id="subOrderCode" name="subOrderCode" >
					</div>
			  </div>
			  <div class="search-td">
					<div class="search-td-label" >商品名称：</div>
					<div class="search-td-condition" >
						<input type="text" id="productName" name="productName" >
					</div>
			  </div>
			  <div class="search-td">
			 	<div class="search-td-label" >订单状态：</div>
			 	<div class="search-td-condition" >
				<select id="Status" name="Status" class="querysel">
					<option value="">请选择...</option>
					<option value="0">待付款</option>
					<option value="1">待发货</option>
					<option value="2">已发货</option>
					<option value="3">完成</option>
					<option value="4">取消(未付款客户取消,平台取消)</option>	
					<option value="5">关闭(退款后关闭)</option>
					<option value="6">已收货</option>							
				</select>
				</div>
		 	  </div>	  			
			 <div class="search-td-search-btn" style="display: inline-flex;">
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
