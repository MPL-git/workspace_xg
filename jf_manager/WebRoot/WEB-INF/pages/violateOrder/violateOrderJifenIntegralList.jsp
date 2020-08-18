<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	 $(function() {
		 $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		 
	 });
		 
 	 var listConfig={
	      url:"/violateOrder/violateOrderJifenIntegralList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'创建时间', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if(rowdata.createDate){
									var createDate=new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");		                		
								}
							}},
							{display:'来源/违规单编号', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								return rowdata.orderSourceDesc+'<br><a href="javascript:;" onclick="viewViolateOrder('+rowdata.id+');">'+rowdata.orderCode+'</a>';	                	
			                }},
			                {display:'商家', align:'center', isSort:false, width:150, render:function(rowdata, rowindex) {
								return rowdata.mchtCode+"<br>"+rowdata.shopName+"<br>"+rowdata.companyName;		                	
			                }},
			                {display:'子订单号', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								return '<a href="javascript:viewDetail('+rowdata.subOrderId+');">'+rowdata.subOrderCode+'</a>';	                	
			                }},
			                {display:'违规行为', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								return "【"+rowdata.violateTypeDesc+"】"+rowdata.violateActionDesc;		                	
			                }},
			                {display:'处罚金额（元）', name:'money', align:'center', isSort:false, width:100},
			                {display:'商家保证金余额', name:'payAmt', align:'center', isSort:false, width:100},
			                {display:'赠送积分', name:'jifenIntegral', align:'center', isSort:false, width:100},
			                {display:'积分赠送状态', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								if(rowdata.jifenStatus == '0') {
									return '<span style="color:red;">未赠送</span>';
								}else if(rowdata.jifenStatus == '3') {
									return '<span style="color:red;">商家保证金不足未补偿</span>';
								}else {
									return '已增送';
								}
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	 };
 
 	 //商家违规详情页
 	 function viewViolateOrder(id) {
 		$.ligerDialog.open({
 			height: $(window).height() - 100,
 			width: $(window).width() - 400,
 			title: "商家违规详情页",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id,
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	 }
 	 
 	 //子订单详情
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
 	
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >违规行为：</div>
					<div class="search-td-combobox-condition" >
						<select id="violateAction" name="violateAction" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${violateActionList }" var="violateAction">
								<option value="${violateAction.statusValue }">${violateAction.statusDesc }</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >积分赠送状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="jifenStatus" name="jifenStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">未赠送</option>
							<option value="1">已增送</option>
							<option value="2">商家保证金不足未补偿</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
