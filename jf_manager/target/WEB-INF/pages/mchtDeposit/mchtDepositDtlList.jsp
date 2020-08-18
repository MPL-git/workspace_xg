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
	
	$(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelWidth : 150,
		width:150,
		labelAlign : 'left'
	});
	
	$("a[name='view']").live('click',function(){
		var txnType = $(this).attr("txnType");
		if(txnType == "E"){//违规扣款
			var bizId = $(this).attr("bizId");
			$.ligerDialog.open({
				height: $(window).height() - 100,
				width: $(window).width() - 400,
				title: "商家违规详情页",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + bizId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
	});
		 $("#export").bind('click',function(){
				var create_date_begin = $("#create_date_begin").val();
				var create_date_end = $("#create_date_end").val();
				var mchtCode = $("#mchtCode").val();
				var companyName = $("#companyName").val();
				var txnType = $("#txnType").val();
				window.location.href="${pageContext.request.contextPath}/mchtDeposit/exportMchtDepositDtl.shtml?create_date_begin="+create_date_begin+"&create_date_end="+create_date_end+"&mchtCode="+mchtCode+"&companyName="+companyName+"&txnType="+txnType;
			});
}); 
 
function checkComfirm(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "保证金缴纳确认",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtDeposit/checkComfirm.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//商家违规详情页
function viewViolateOrder(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "商家违规详情页",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/violateOrder/view.shtml?id=" + id+"&role="+"2", //role为2查看的违规单无法进行相关操作  为1时有待审核操作没挂起操作
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

var listConfig={
     url:"/mchtDeposit/mchtDepositDtlData.shtml",
     listGrid:{ columns: [
						{ display: '时间', render: function (rowdata, rowindex) {
							var createDate=new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");	                		
		                }},
			            { display: '商家序号', name:'mchtCode'},
			            { display: '公司名称',name:'companyName'},
		                { display: '类型', name:'txnTypeDesc'},
			            { display: '子类', name:'typeSubDesc'},
		                { display: '摘要', width: 150, render: function (rowdata, rowindex) {
							var html = [];
							html.push(rowdata.remarks);
							if (rowdata.txnType == "E" && rowdata.bizId){
								html.push("<br><a href=\"javascript:viewViolateOrder(" + rowdata.bizId + ");\">"+rowdata.violateOrderCode+"</a>&nbsp;");
							}
							return html.join("");
		                }},
		                { display: '应缴额变化值', render: function (rowdata, rowindex) {
		                	if(rowdata.txnType == "A"){
			                	return rowdata.txnAmt;
		                	}else{
		                		return "0";
		                	}
		                }},
		                { display: '保证金余额变化值', render: function (rowdata, rowindex) {
		                	if(rowdata.txnType == "A"){
		                		return "0";
		                	}else{
			                	return rowdata.txnAmt;
		                	}
		                }},
		                { display: '变化后保证金余额', render: function (rowdata, rowindex) {
		                	return rowdata.payAmt;
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
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="create_date_begin" name="create_date_begin" class="dateEditor" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="create_date_end" name="create_date_end" class="dateEditor" />
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >商家序号：</div>
				<div class="search-td-combobox-condition">
					<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}" <c:if test="${not empty mchtCode }">readonly="readonly"</c:if> >
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >公司名称：</div>
				<div class="search-td-combobox-condition">
					<input type="text" id="companyName" name="companyName" >
				</div>
			</div>
		</div>	
		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" >类型：</div>
				<div class="search-td-combobox-condition" >
					<select id="txnType" name="txnType" style="width: 135px;">
						<option value="">请选择</option>
						<c:forEach var="txnType" items="${txnTypeList}">
							<option value="${txnType.statusValue}">${txnType.statusDesc}
							</option>
						</c:forEach>
					</select>
			 	 </div>
			</div>
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 62px;height: 25px;cursor: pointer;" value="导出" id="export">
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