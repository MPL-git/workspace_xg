<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
 $(function(){
	 $("#checkAll").live('click',function(){
		 if($(this).attr("checked")){
			 $("input[name='mchtSettleOrderId']").each(function(){
				 $(this).attr("checked",true);
			 });
		 }else{
			 $("input[name='mchtSettleOrderId']").each(function(){
				 $(this).attr("checked",false);
			 });
		 }
	 });
	 $("input[name='mchtSettleOrderId']").live('click',function(){
		 if($(this).attr("checked")){
			 var selectedLength = $("input[name='mchtSettleOrderId']:checked").length;
			 var length = $("input[name='mchtSettleOrderId']").length;
			 if(selectedLength == length){
				 $("#checkAll").attr("checked",true);
			 }else{
				 $("#checkAll").attr("checked",false);
			 }
		 }else{
			 $("#checkAll").attr("checked",false);
		 }
	 });
 });
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
 function view(id) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "SPOP结算详情页",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtSettleOrder/poolView.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 function toSetPayReadyDate(id){
	 $.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "结算单排款确认",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtSettleOrder/toSetPayReadyDate.shtml?id=" +id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
 function viewByMchtCode(mchtCode){
	 $.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "SPOP结算列表",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtSettleOrder/poolList.shtml?mchtCode=" +mchtCode ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
 $(function(){
		$("#batchConfirm").bind('click',function(){
			var ids="";
			$("input[name='mchtSettleOrderId']:checked").each(function(){
				var confirmStatus = $(this).attr("confirmStatus");
				if(confirmStatus=="2"){//待平台确认
					var mchtSettleOrderId = $(this).attr("mchtSettleOrderId");
					ids+=mchtSettleOrderId+",";
				}
			});
			if(ids.length>0){
				ids = ids.substring(0, ids.length-1);
			}
			$.ligerDialog.open({
				height: $(window).height()*0.25,
				width: $(window).width()*0.3,
				title: "提示",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtSettleOrder/toBatchConfirm.shtml?ids="+ids,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		});
		
		$("#batchPk").bind('click',function(){
			var ids="";
			$("input[name='mchtSettleOrderId']:checked").each(function(){
				var payStatus = $(this).attr("payStatus");
				if(payStatus=="1"||payStatus=="2"){//未排或已排
					var mchtSettleOrderId = $(this).attr("mchtSettleOrderId");
					ids+=mchtSettleOrderId+",";
				}
			});
			if(ids.length>0){
				ids = ids.substring(0, ids.length-1);
			}
			$.ligerDialog.open({
				height: $(window).height()*0.4,
				width: $(window).width()*0.3,
				title: "批量排款",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtSettleOrder/toBatchPk.shtml?ids="+ids,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		});
	});
 
 var listConfig={
     url:"/mchtSettleOrder/poolData.shtml",
     listGrid:{ columns: [
						{ display: '<input type="checkbox" id="checkAll"/>', render: function (rowdata, rowindex) {
							return '<input type="checkbox" name="mchtSettleOrderId" mchtSettleOrderId="'+rowdata.id+'" confirmStatus="'+rowdata.confirmStatus+'" payStatus="'+rowdata.payStatus+'"/>';
						}},
			            { display: '结算单日期', render: function (rowdata, rowindex) {
		                	var beginDate = new Date(rowdata.beginDate);
		                	var endDate = new Date(rowdata.endDate);
		                	return beginDate.format("yyyy-MM-dd")+"到"+endDate.format("yyyy-MM-dd");
		                }},
			            { display: '商家序号',name:'mchtCode'},
		                { display: '商家简称',render: function (rowdata, rowindex) {
		                	return '<a href="javascript:;" onclick="viewByMchtCode('+"'"+rowdata.mchtCode+"'"+')">'+rowdata.shortName+'</a>';
		                }},
		                { display: '结算单金额（元）', render: function (rowdata, rowindex) {
		                	if(rowdata.settleAmount){
								return formatMoney(rowdata.settleAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '抵缴保证金（元）',render: function (rowdata, rowindex) {
		                	if(rowdata.depositAmount){
								return formatMoney(rowdata.depositAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '还需支付（元）',render: function (rowdata, rowindex) {
		                	if(rowdata.needPayAmount){
								return formatMoney(rowdata.needPayAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '实付金额(元)',render: function (rowdata, rowindex) {
		                	if(rowdata.payAmount){
								return formatMoney(rowdata.payAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '确认状态',name:'confirmStatusDesc'},
		                { display: '商家确认日期',render: function (rowdata, rowindex) {
		                	if(rowdata.mchtConfirmDate){
		                		var mchtConfirmDate = new Date(rowdata.mchtConfirmDate);
		                		return mchtConfirmDate.format("yyyy-MM-dd");
		                	}else{
		                		return "";
		                	}
		                }},
		                { display: '收票状态',name:'platformCollectStatusDesc'},
		                { display: '排款日期',render: function (rowdata, rowindex) {
		                	var payReadyDate = new Date(rowdata.payReadyDate);
		                	return payReadyDate.format("yyyy-MM-dd");
		                }},
		                { display: '付款状态',render: function (rowdata, rowindex) {
		                	if(rowdata.payStatus == "1" || rowdata.payStatus == "2"){
		                		return '<a href="javascript:;" onclick="toSetPayReadyDate('+rowdata.id+')">'+rowdata.payStatusDesc+'</a>';
		                	}else{
			                	return rowdata.payStatusDesc;
		                	}
		                }},
		                { display: '付款日期',render: function (rowdata, rowindex) {
		                	if(rowdata.payDate!=null){
		                		var payDate = new Date(rowdata.payDate);
		                		return payDate.format("yyyy-MM-dd");
		                	}
		                }},
		                { display: '操作',render: function (rowdata, rowindex) {
		                	return '<a href="javascript:;" onclick="view('+rowdata.id+')">详情</a>';
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      },  							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  $(function(){
	  $("#export").bind('click',function(){
		  var mchtConfirmDateBegin = $("#mcht_confirm_date_begin").val();
		  var mchtConfirmDateEnd = $("#mcht_confirm_date_end").val();
		  var mchtCode = $("#mchtCode").val();
		  var confirmStatus = $("#confirmStatus").val();
		  var platformInvoiceStatus = $("#platformInvoiceStatus").val();
		  var payStatus = $("#payStatus").val();
		  location.href="${pageContext.request.contextPath}/mchtSettleOrder/exportPoolMchtSettleOrder.shtml?mcht_confirm_date_begin="+mchtConfirmDateBegin+"&mcht_confirm_date_end="+mchtConfirmDateEnd+"&mchtCode="+mchtCode+"&confirmStatus="+confirmStatus+"&platformInvoiceStatus="+platformInvoiceStatus+"&payStatus="+payStatus;
	  });
	  
  });
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}">
			</div>
			</div>
		 	
		 	<div class="search-td">
			<div class="search-td-label" style="float:left;">确认状态：</div>
			<div class="l-panel-search-item" >
				<select id="confirmStatus" name="confirmStatus" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="confirmStatus" items="${confirmStatusList}">
						<option value="${confirmStatus.statusValue}" <c:if test="${confirmStatus.statusValue eq defaultConfirmStatus}">selected="selected"</c:if>>${confirmStatus.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
		 	
			<div class="search-td">
			<div class="search-td-label" style="float:left;">商家确认日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="mcht_confirm_date_begin" name="mcht_confirm_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#mcht_confirm_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="mcht_confirm_date_end" name="mcht_confirm_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#mcht_confirm_date_end").ligerDateEditor({
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
			<div class="search-td-label" style="float:left;">平台收票：</div>
			<div class="l-panel-search-item" >
				<select id="platformCollectStatus" name="platformCollectStatus" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="platformCollectStatus" items="${platformCollectStatusList}">
						<option value="${platformCollectStatus.statusValue}">${platformCollectStatus.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">付款状态：</div>
			<div class="l-panel-search-item" >
				<select id="payStatus" name="payStatus" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="payStatus" items="${payStatusList}">
						<option value="${payStatus.statusValue}">${payStatus.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">结算金额：</div>
			<div class="l-panel-search-item" >
				<select id="settleAmount" name="settleAmount" style="width: 150px;">
					<option value="">请选择</option>
					<option value="0">只看为零</option>
					<option value="1">只看不为零</option>
				</select>
		 	 </div>
			 </div>
			
			<div  style="display: inline-flex;">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量确认" id="batchConfirm">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量排款" id="batchPk">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索" id="searchbtn">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="导出" id="export">
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