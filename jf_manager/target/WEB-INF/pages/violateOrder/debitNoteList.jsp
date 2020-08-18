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
				window.location.href="${pageContext.request.contextPath}/violateOrder/exportMchtDepositDtl.shtml?create_date_begin="+create_date_begin+"&create_date_end="+create_date_end+"&mchtCode="+mchtCode+"&companyName="+companyName+"&txnType="+txnType;
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

var listConfig={
     url:"/violateOrder/debitNoteData.shtml",
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
							return rowdata.remarks;
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="create_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
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
				<input type="text" id="create_date_end" name="create_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_end").ligerDateEditor( {
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
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition">
				<input type="text" id="mchtCode" name="mchtCode" value="${mchtCode}">
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >公司名称：</div>
			<div class="search-td-condition">
				<input type="text" id="companyName" name="companyName" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">类型：</div>
			<div class="l-panel-search-item">
				<select id="txnType" name="txnType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="txnType" items="${txnTypeList}">
						<option value="${txnType.statusValue}" <c:if test="${txnType.statusValue eq defaultTxnType}">selected="selected"</c:if>>${txnType.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 <div class="search-td">
			<div class="search-td-label" style="color: red">对接人：</div>
			<div class="search-td-condition" >
				<select id="platformContactId" name="platformContactId" >
					<c:if test="${isContact==1}">
						<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
							<option value="">全部商家</option>
						</c:if>
						<option value="${myContactId}">我对接的商家</option>
						<c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
							<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
						</c:forEach>
					</c:if>
					
					<c:if test="${isContact==0}">
					<option value="">全部商家</option>
					<c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
						<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
					</c:forEach>			
					</c:if>
				</select>
			</div>
			</div>			 
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" style="height: 28px;line-height: 28px;">
					搜索
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 62px;height: 30px;cursor: pointer;" value="导出" id="export">
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