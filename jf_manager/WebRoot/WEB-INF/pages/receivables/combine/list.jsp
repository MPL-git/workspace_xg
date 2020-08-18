<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style type="text/css">
.color-s0,.color-fs1{color: #0000FF;}
.color-s1,.color-fs2{color: #008000;}
.color-s4{color: #333333;}
.color-fs0{color: #000000;}
</style>
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

$(function(){
	$("#batch").live('click',function(){
		if($(this).attr("checked")){
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",false);
			});
		}
	});
	$("input[name='checkbox']").live('click',function(){
		var totalLength = $("input[name='checkbox']").length;
		if($(this).attr("checked")){
			var selectedLength = $("input[name='checkbox']:checked").length;
			if(selectedLength == totalLength){
				$("#batch").attr("checked",true);
			}else{
				$("#batch").attr("checked",false);
			}
		}else{
			$("#batch").attr("checked",false);
		}
	});
	
	$("#batchSelected").bind('click',function(){
		var selectedLength = $("input[name='checkbox']:checked").length;
		if(selectedLength == 0){
			commUtil.alertError("请先勾选要处理的母订单");
			return false;
		}
		var id=[];
		$("input[name='checkbox']:checked").each(function(){
			id.push($(this).val());
		});
		$.ligerDialog.open({
	 		height: $(window).height()*0.6,
			width: $(window).width()*0.6,
			title: "收款确认操作",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/receivables/combine/toConfirm.shtml?id=" + id.join(","),
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	});
	
	$("#batchAll").bind('click',function(){
		var paymentId = $("#paymentId").val();
		var financialStatus = $("#financialStatus").val();
		var combineOrderCode = $("#combineOrderCode").val();
		var paymentNo = $("#paymentNo").val();
		var financialNo = $("#financialNo").val();
		var create_date_begin = $("#create_date_begin").val();
		var create_date_end = $("#create_date_end").val();
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		var register_date_begin = $("#register_date_begin").val();
		var register_date_end = $("#register_date_end").val();
		$.ligerDialog.open({
	 		height: $(window).height()*0.6,
			width: $(window).width()*0.6,
			title: "收款确认操作",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/receivables/combine/toConfirm.shtml?paymentId="+paymentId+"&financialStatus="+financialStatus+"&combineOrderCode="+combineOrderCode+"&paymentNo="+paymentNo+"&financialNo="+financialNo+"&create_date_begin="+create_date_begin+"&create_date_end="+create_date_end+"&pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&register_date_begin="+register_date_begin+"&register_date_end="+register_date_end,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	});
	
	$("#export").bind('click',function(){
		var paymentId = $("#paymentId").val();
		var financialStatus = $("#financialStatus").val();
		var combineOrderCode = $("#combineOrderCode").val();
		var paymentNo = $("#paymentNo").val();
		var financialNo = $("#financialNo").val();
		var create_date_begin = $("#create_date_begin").val();
		var create_date_end = $("#create_date_end").val();
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		var register_date_begin = $("#register_date_begin").val();
		var register_date_end = $("#register_date_end").val();
		location.href="${pageContext.request.contextPath}/receivables/combine/export.shtml?paymentId="+paymentId+"&financialStatus="+financialStatus+"&combineOrderCode="+combineOrderCode+"&paymentNo="+paymentNo+"&financialNo="+financialNo+"&create_date_begin="+create_date_begin+"&create_date_end="+create_date_end+"&pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&register_date_begin="+register_date_begin+"&register_date_end="+register_date_end;
	});
	
	$("#count").bind('click',function(){
		var paymentId = $("#paymentId").val();
		var financialStatus = $("#financialStatus").val();
		var combineOrderCode = $("#combineOrderCode").val();
		var paymentNo = $("#paymentNo").val();
		var financialNo = $("#financialNo").val();
		var create_date_begin = $("#create_date_begin").val();
		var create_date_end = $("#create_date_end").val();
		var pay_date_begin = $("#pay_date_begin").val();
		var pay_date_end = $("#pay_date_end").val();
		var register_date_begin = $("#register_date_begin").val();
		var register_date_end = $("#register_date_end").val();
		$.ligerDialog.open({
	 		height: $(window).height()*0.20,
			width: $(window).width()*0.15,
			title: "合计",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/receivables/combine/count.shtml?paymentId="+paymentId+"&financialStatus="+financialStatus+"&combineOrderCode="+combineOrderCode+"&paymentNo="+paymentNo+"&financialNo="+financialNo+"&create_date_begin="+create_date_begin+"&create_date_end="+create_date_end+"&pay_date_begin="+pay_date_begin+"&pay_date_end="+pay_date_end+"&register_date_begin="+register_date_begin+"&register_date_end="+register_date_end,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
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

function toRegister(id) {
	$.ligerDialog.open({
		height: 500,
		width: 600,
		title: "收款登记操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/receivables/combine/toRegister.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toConfirm(id) {
	$.ligerDialog.open({
		height: 500,
		width: 600,
		title: "收款确认操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/receivables/combine/toConfirm.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

  var listConfig={
		  
	  url:"/receivables/combine/data.shtml",
      
      listGrid:{ columns: [{ display: "母订单号",width: "10%", render: function(rowdata, rowindex) {
    						var html = [];
    						html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">"+rowdata.combineOrderCode+"</a>"); 
    						return html.join("");
    					}},
 		                { display: '品牌/货号', width: "14%", render: function (rowdata, rowindex) {
 		                	var artBrandGroup=rowdata.artBrandGroup;
 		                	if (artBrandGroup!=null){
 		                		return artBrandGroup.replace(/,/g,"<br>");
 		                	}
		                }},
		                { display: '付款渠道', width: "6%",name: 'paymentName'},
		                { display: '实付金额', width: "5%", render: function (rowdata, rowindex) {
 		                	if(rowdata.totalPayAmount){
	 		                	return formatMoney(rowdata.totalPayAmount,2);
 		                	}else{
 		                		return "0.00";
 		                	}
		                }},
		                { display: '母订单状态', width: "4%", render: function (rowdata, rowindex) {
							return "<span class='color-s"+rowdata.status+"'>"+rowdata.statusDesc+"</span>";
		                }},
		                { display: '下单时间', width: "9%", render: function (rowdata, rowindex) {
		                	if (rowdata.createDate!=null){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '付款时间', width: "9%", render: function (rowdata, rowindex) {
		                	if (rowdata.payDate!=null){
								var payDate=new Date(rowdata.payDate);
								return payDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
		                }},
		                { display: '收款状态', width: "4%", render: function (rowdata, rowindex) {
							return "<span class='color-fs"+rowdata.financialStatus+"'>"+rowdata.financialStatusDesc+"</span>";
		                }},
		                { display: '收款编号', width: "6%", render: function (rowdata, rowindex) {
		                	if(rowdata.financialNo){
		                		return rowdata.financialNo;
		                	}else{
		                		var payDate=new Date(rowdata.payDate);
		                		var dateStr = payDate.format("yyyyMMdd");
		            			if(rowdata.paymentName == "支付宝"){
		            				return "ZFB"+dateStr;
		            			}else if(rowdata.paymentName == "微信支付"){
		            				return "WX"+dateStr;
		            			}else if(rowdata.paymentName == "银联在线"){
		            				return "YL"+dateStr;
		            			}
								return ;
		                	}
		                }},
		                { display: '收款登记日期', width: "5%",render: function (rowdata, rowindex) {
		                	if (rowdata.collectionRegisterDate){
								var collectionRegisterDate=new Date(rowdata.collectionRegisterDate);
								return collectionRegisterDate.format("yyyy-MM-dd");
		                	}else{
		                		return new Date(rowdata.payDate).format("yyyy-MM-dd");
		                	}
		                }},
		                { display: '收款人', width: "6%", name: 'financialStaffName'},
		                { display: '支付交易号',width: "12%", name: 'paymentNo'},
		                { display: '操作',width: "4%",render: function (rowdata, rowindex) {
		                	if(rowdata.financialStatus == "0"){//未处理
		                		return '<a href="javascript:;" onclick="toRegister('+rowdata.id+');">登记</a>';
		                	}else if(rowdata.financialStatus == "1"){//已登记
		                		return '<a href="javascript:;" onclick="toConfirm('+rowdata.id+');">确认</a>';
		                	}else if(rowdata.financialStatus == "2"){//已确认
		                		return "";
		                	}
		                }},
		                { display: '批量<input type="checkbox" id="batch">',width: "4%",render: function (rowdata, rowindex) {
		                	if(rowdata.financialStatus != "2"){
								return '<input type="checkbox" name="checkbox" value="'+rowdata.id+'">';
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
}
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
		
			 <div class="search-td">
			 <div class="search-td-label" >母订单状态：</div>
			 <div class="search-td-condition" >
				<select id="status" name="status">
					<option value="1">已付</option>
				</select>
		 	 </div>
			 </div>
			
			 <div class="search-td">
			 <div class="search-td-label" >付款渠道：</div>
			 <div class="search-td-condition" >
				<select id="paymentId" name="paymentId">
					<option value="">全部</option>
					<option value="1" <c:if test="${paymentId eq 1}">selected="selected"</c:if>>微信APP/H5</option>
					<option value="2" <c:if test="${paymentId eq 2}">selected="selected"</c:if>>支付宝</option>
					<option value="3" <c:if test="${paymentId eq 3}">selected="selected"</c:if>>微信公众号/小程序</option>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >收款状态：</div>
			 <div class="search-td-condition" >
				<select id="financialStatus" name="financialStatus">
					<option value="">请选择</option>
					<c:forEach var="list" items="${financialStatus}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label" >母订单号：</div>
			<div class="search-td-condition" >
				<input type="text" id="combineOrderCode" name="combineOrderCode" >
			</div>
			</div>
		</div>
		
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">下单日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="create_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
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
				<input type="text" id="create_date_end" name="create_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">付款日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="pay_date_begin" name="pay_date_begin" value="${nowDate}"/>
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
				<input type="text" id="pay_date_end" name="pay_date_end" value="${nowDate}"/>
				<script type="text/javascript">
					$(function() {
						$("#pay_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
		</div>
		
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >支付交易号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "paymentNo" name="paymentNo" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >收款编号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "financialNo" name="financialNo" >
			</div>
			</div>
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">收款登记日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="register_date_begin" name="register_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#register_date_begin").ligerDateEditor( {
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
				<input type="text" id="register_date_end" name="register_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#register_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			</div>
			<div style="display: inline-flex;padding-left: 75%;">
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="搜索" id="searchbtn">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量（全部）" id="batchAll">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="批量（选中）" id="batchSelected">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="统计" id="count">
				</div>
			</div>
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>