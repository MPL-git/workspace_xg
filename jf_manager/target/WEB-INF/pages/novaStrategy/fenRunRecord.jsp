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
 function formatMoney(s, n)
 {
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

	 $(function() {
	  $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
	  });
	  
		 $('#tallyModes').change(function(){
			 if($(this).val() == '2'){
				$('.search-td').eq(1).hide(); 
			 }else{
				 $('.search-td').eq(1).show();
			 }
		 });
		 
		 
		 $("#export").bind('click',function(){
			  var tallyModes = $("#tallyModes").val();
			  var bizTypes = $("#bizTypes").val();
			  var memberId = $("#memberId").val();
			  var nick=$("#nick").val();
			  var createDateOrderBegin = $("#createDateOrderBegin").val();
			  var createDateOrderEnd = $("#createDateOrderEnd").val();
			  var id=${id};
			  location.href="${pageContext.request.contextPath}/theoryStatistics/export.shtml?tallyModes="+tallyModes+"&nick="+nick+"&bizTypes="+bizTypes+"&nick="+nick+"&memberId="+memberId+"&createDateOrderBegin="+createDateOrderBegin+"&createDateOrderBegin="+createDateOrderEnd+"&id="+id;
		  });
	 });
	 function orderDetail(id) {
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
	      url:"/theoryStatistics/fenRunRecordList.shtml?id=${id}",
	      listGrid:{ columns: [
	                        {display:'收支类型', name:'tallyModes', align:'center', isSort:false,render: function (rowdata, rowindex) {
	 		                	if(rowdata.tallyModes == '收入'){
	 		                		return '<span style="color:red;">收入</span>';
	 		                	}else if(rowdata.tallyModes == '支出'){
	 		                		return '<span style="color:green;">支出</span>';
	 		                	}
	                        }},
	                        {display:'来源/去向', name:'bizTypes', align:'center', isSort:false},
	                        {display:'会员ID', name:'memberId', align:'center', isSort:false,render: function (rowdata, rowindex) {
	                        	if(rowdata.bizTypes == '邀新奖励'){
	                        		if(rowdata.newMemberId == null){
		                        		return '--';
		                        	}else{
		                        		return rowdata.newMemberId;
		                        	}                	
	                        	}else if(rowdata.bizTypes == '订单分润'){
	                        		if(rowdata.memberId == null){
		                        		return '--';
		                        	}else{
		                        		return rowdata.memberId;
		                        	}        
	                        	}
	                        	
	                        }},
	                        {display:'会员昵称', name:'nick', align:'center', isSort:false,render: function (rowdata, rowindex) {
	                        	if(rowdata.bizTypes == '邀新奖励'){
	                        		if(rowdata.newNick == null){
		                        		return '--';
		                        	}else{
		                        		return rowdata.newNick;
		                        	}                 	
	                        	}else if(rowdata.bizTypes == '订单分润'){
	                        		if(rowdata.nick == null){
		                        		return '--';
		                        	}else{
		                        		return rowdata.nick;
		                        	}
	                        	}           	
	                        }},
	                        {display:'商品ID', name:'productId', align:'center', isSort:false,render: function (rowdata, rowindex) {
	                        	if(rowdata.productId == null){
	                        		return '--';
	                        	}else{
	                        		return rowdata.productId;
	                        	}    	                	
	                        }},
	                        {display:'商品名称', name:'productName', align:'center', isSort:false,render: function (rowdata, rowindex) {
	                        	if(rowdata.productName != null){
	                        		return '<span style="color:blue;">'+rowdata.productName+'</span>';
	                        	}
	                        }},
	                        {display:'子订单号', name:'subOrderCode', align:'center', isSort:false,render: function (rowdata, rowindex) {
	                        	if(rowdata.subOrderCode ==null){
	                        		return '--';
	                        	}else{
	 		                		return '<a href="javascript:orderDetail('+rowdata.subOrderId+');"><span style="color:blue;">'+rowdata.subOrderCode+'</span></a>';
	                        	}
	                        }},
	                        {display:'实付金额', name:'payAmount', align:'center', isSort:false, render: function (rowdata, rowindex) {
	                        	if(rowdata.payAmount == 'null'){
	                        		return '--';
	                        	}else if(rowdata.payAmount){
	                        		return formatMoney(rowdata.payAmount,2);
	                        	}else{
	                        		return "0.00";
	                        	}     
	                        }},
	                        {display:'变化金额', name:'amount', align:'center', isSort:false, render: function (rowdata, rowindex) {
	                        	if(rowdata.tallyModes == "收入"){
	 		                		return '<span style="color:red;">'+'+'+formatMoney(rowdata.amount,2)+'</span>';             	
	 		                	}else if(rowdata.tallyModes == "支出"){
	 		                		return '<span style="color:green;">'+'-'+formatMoney(rowdata.amount,2)+'</span>';    		
	 		                	}
	                        }},
	                        {display:'当前现金余额(含提现中金额)', name:'balance', align:'center', isSort:false, render: function (rowdata, rowindex) {
								if(rowdata.balance){
	                        		return formatMoney(rowdata.balance,2);
	                        	}else{
	                        		return "0.00";
	                        	}     
	                        }},
	                        {display:'变动时间', name:'createDate', align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDates = new Date(rowdata.createDate);
									html.push(createDates.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}}		
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">收支类型</div>
					<div class="search-td-combobox-condition" >
						<select id="tallyModes" name="tallyModes" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1">收入</option>
							<option value="2">支出</option>
							<%-- <c:forEach var="list" items="${sprChnls}">
									<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:forEach> --%>
						</select>
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">来源</div>
					<div class="search-td-combobox-condition" >
						<select id="bizTypes" name="bizTypes" style="width: 135px;">
							<option value="">请选择</option>
							<option value="9">订单分润</option>
							<option value="8">邀新奖励</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label"  >会员ID</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label"  >会员昵称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="nick" name="nick">
					</div>
				</div>
				<div class="search-td" style="width: 25%;margin-left:50px;margin-bottom:-6px;">
						<div class="search-td-label" style="float: left;width: 15%;margin-top:2px;">变动日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateOrderBegin" name="createDateOrderBegin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 5px;margin-right: 5px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateOrderEnd" name="createDateOrderEnd" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;width:130px;">
					<div id="searchbtn" class="l-button">搜索</div>
					<div style="float: left; padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出Excel" id="export">
					</div>
				</div>
			</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
