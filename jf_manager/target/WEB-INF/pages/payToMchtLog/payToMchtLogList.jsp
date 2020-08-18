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
 function view(mchtCode) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "余额变化明细表",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtDeposit/mchtDepositDtlList.shtml?mchtCode=" + mchtCode,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 function toConfirm(id){
	 $.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "确认付款状态",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/payToMchtLog/toConfirm.shtml?ids=" +id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
 function checkAllFun(obj) {
	 var payIds = document.getElementsByName('payId');//获取到复选框的名称 
	 for(var i=0;i<payIds.length;i++){  
	 	payIds[i].checked = obj.checked;  
     }  
 }
 
 var listConfig={
     url:"/payToMchtLog/data.shtml",
     btnItems:[{ text: '手工添加', icon: 'add', dtype:'win',  click: itemclick, url: "/payToMchtLog/toAdd.shtml", seqId:"", width: 1200, height: 800}],
     listGrid:{ columns: [
			            { display: '付款ID', name:'id'},
			            { display: '类型',name:'typeDesc'},
		                { display: '结算单ID', name:'mchtSettleOrderId'},
			            { display: '店铺名', name:'shopName'},
		                { display: '公司名称',name:'companyName'},
		                { display: '付款日期',render: function (rowdata, rowindex) {
		                	var payDate = new Date(rowdata.payDate);
		                	return payDate.format("yyyy-MM-dd hh:mm:ss");
		                }},
		                /* { display: '付款编号', name:'payCode'}, */
		                { display: '结算单日期', width:200,render: function (rowdata, rowindex) {
		                	var beginDate = new Date(rowdata.beginDate);
		                	var endDate = new Date(rowdata.endDate);
		                	return beginDate.format("yyyy-MM-dd")+"&nbsp;到&nbsp;"+endDate.format("yyyy-MM-dd");
		                }},
		                { display: '结算金额（元）',render: function (rowdata, rowindex) {
		                	if(rowdata.settleAmount){
								return formatMoney(rowdata.settleAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '转保证金抵缴（元）',render: function (rowdata, rowindex) {
		                	if(rowdata.depositAmount){
								return formatMoney(rowdata.depositAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '以前实付金额',render: function (rowdata, rowindex) {
		                	if(rowdata.beforePayAmount){
								return formatMoney(rowdata.beforePayAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '还需支付',render: function (rowdata, rowindex) {
		                	if(rowdata.needPayAmount){
								return formatMoney(rowdata.needPayAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '本次实付金额(元)',render: function (rowdata, rowindex) {
		                	if(rowdata.payAmount){
								return formatMoney(rowdata.payAmount,2);	                		
		                	}else{
		                		return "0.00";
		                	}
		                }},
		                { display: '收保证金记录',render: function (rowdata, rowindex) {
		                	return '<a href="javascript:;" onclick="view('+"'"+rowdata.mchtCode+"'"+')">查看</a>';
		                }},
		                { display: '付款人',name:'payStaffName'},
		                { display: '是否确认<input type="checkbox" id="checkAll" onchange="checkAllFun(this);" >',render: function (rowdata, rowindex) {
		                	if(rowdata.confirmStatus == "0"){
		                		return '<a href="javascript:;" onclick="toConfirm('+rowdata.id+');">'+rowdata.confirmStatusDesc+'</a><input type="checkbox" name="payId" value="'+rowdata.id+'">';
		                	}else{
			                	return rowdata.confirmStatusDesc;
		                	}
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      },  							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  $(function(){
	  $("#batchConfirm").live('click',function(){
		  if($("input[name='payId']:checked").length == 0){
			  commUtil.alertError("请先选中待确认的付款记录");
			  return false;
		  }
		  var ids="";
		  var totalSettleAmount=0;
		  var totalDepositAmount=0;
		  var totalPayAmount=0;
		  $("input[name='payId']:checked").each(function(){
			  ids += $(this).val()+",";
			  var settleAmount = $(this).closest("tr").find("td").eq(7).find("div").text();
			  var depositAmount = $(this).closest("tr").find("td").eq(8).find("div").text();
			  var payAmount = $(this).closest("tr").find("td").eq(11).find("div").text();
			  totalSettleAmount = Number(totalSettleAmount) + Number(settleAmount);
			  totalDepositAmount = Number(totalDepositAmount) + Number(depositAmount);
			  totalPayAmount = Number(totalPayAmount) + Number(payAmount);
		  });
		  ids = ids.substring(0, ids.length-1);
		  $.ligerDialog.open({
				height: $(window).height() - 100,
				width: $(window).width() - 400,
				title: "确认付款状态",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/payToMchtLog/toConfirm.shtml?ids="+ids+"&totalSettleAmount="+totalSettleAmount+"&totalDepositAmount="+totalDepositAmount+"&totalPayAmount="+totalPayAmount ,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	  });
	  
	  $("#export").bind('click',function(){
			var pay_date_begin = $("#pay_date_begin").val();
			var pay_date_end = $("#pay_date_end").val();
			var mchtCode = $("#mchtCode").val();
			var payCode = $("#payCode").val();
			var type = $("#type").val();
			var companyName = $("#companyName").val();
			var settleAmount = $("#settleAmount").val();
			var confirmStatus = $("#confirmStatus").val();
			var mchtName = $("#mchtName").val();
			var productTypeId = $("#productTypeId").val();
			var productBrandName = $("#productBrandName").val();
			window.location.href="${pageContext.request.contextPath}/payToMchtLog/export.shtml?pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&payCode="+payCode+"&type="+type+"&companyName="+companyName+"&mchtCode="+mchtCode+"&settleAmount="+settleAmount+"&confirmStatus="+confirmStatus+"&mchtName="+mchtName+"&productTypeId="+productTypeId+"&productBrandName="+productBrandName;
		});
	   	  
  });
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="mchtId" value="${mchtId}">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">付款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${pay_date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_begin").ligerDateEditor( {
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
				<input type="text" id="pay_date_end" name="pay_date_end" value="${pay_date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_end").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}">
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >付款编号：</div>
			<div class="search-td-condition" >
				<input type="text" id="payCode" name="payCode" >
			</div>
			</div>
		</div>
		<div class="search-tr"  >	
			<div class="search-td">
			<div class="search-td-label" style="float:left;">类型：</div>
			<div class="l-panel-search-item" >
				<select id="type" name="type" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="type" items="${typeList}">
						<option value="${type.statusValue}" <c:if test="${searchType eq type.statusValue}">selected="selected"</c:if>>${type.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label" >公司名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="companyName" name="companyName" >
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
			 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">是否确认：</div>
			<div class="l-panel-search-item" >
				<select id="confirmStatus" name="confirmStatus" style="width: 150px;">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">待确认</option>
				</select>
		 	 </div>
			 </div>
			 <!-- <div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量确认" id="batchConfirm">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="导出全部" id="export">
				</div>
			</div> -->
		</div>
		<div class="search-tr"  >
		 <div class="search-td">
			<div class="search-td-label"  >商家名称：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id="mchtName" name="mchtName" >
			</div>
		</div>
		<div class="search-td">
				<div class="search-td-label" >主营类目：</div>
				<div class="search-td-condition" >
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled">
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;">
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
			</div>
			<div class="search-td">
			<div class="search-td-label">品牌：</div>
			<div  class="search-td-condition">
				<input name="productBrandName" id="productBrandName">
			</div>
		    </div>
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量确认" id="batchConfirm">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="导出全部" id="export">
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