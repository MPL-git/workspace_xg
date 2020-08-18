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
     url:"/mchtDeposit/depositOrderData.shtml",
     listGrid:{ columns: [
						{ display: '时间', render: function (rowdata, rowindex) {
							var createDate=new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");	                		
		                }},
			            { display: '商家序号', name:'mchtCode'},
			            { display: '公司名称',name:'companyName'},
		                { display: '类型', render: function (rowdata, rowindex) {
		                	if(rowdata.paymentType == "1"){
			            		return "支付宝";
			            	}else if(rowdata.paymentType == "2"){
			            		return "微信";
			            	}else if(rowdata.paymentType == "3"){
			            		return "网银";
			            	}
		                }},
			            { display: '银行类型', render: function (rowdata, rowindex) {
			            	if(rowdata.paymentType == "1"){
			            		return "支付宝";
			            	}else if(rowdata.paymentType == "2"){
			            		return "微信";
			            	}else if(rowdata.paymentType == "3"){
			            		return rowdata.platformPaymentName;
			            	}		                		
		                }},
		                { display: '摘要', width: 150, render: function (rowdata, rowindex) {
							if(rowdata.paymentType == "1" || rowdata.paymentType == "2"){
								return "交易号："+rowdata.paymentNo;
							}else{
								if(rowdata.platformCapitalAccount){
									var html = "账户名："+rowdata.accountName+"<br>开户银行："+rowdata.paymentName+"<br>银行账号："+rowdata.accountNo+"<br>打款时间："+new Date(rowdata.payDate).format("yyyy-MM-dd hh:mm:ss")+"<br>交易号："+rowdata.paymentNo;
									return html;
								}
							}
		                }},
		                { display: '金额', render: function (rowdata, rowindex) {
		                	return rowdata.amount;
		                }},
		                { display: '状态',  name: 'statusDesc'}, 
		                { display: '操作',render: function (rowdata, rowindex) {
		                	if(rowdata.status == "0"){
			                	return "<a href='javascript:;' onclick='checkComfirm("+rowdata.id+");'>【审核】</a>";
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
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
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
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >公司名称：</div>
			<div class="search-td-condition">
				<input type="text" id="companyName" name="companyName" >
			</div>
			</div>
		</div>	
		<div class="search-tr"  >	
			<div class="search-td">
			<div class="search-td-label" style="float:left;">类型：</div>
			<div class="l-panel-search-item" >
				<select id="paymentType" name="paymentType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="paymentType" items="${paymentTypeList}">
						<option value="${paymentType.statusValue}">${paymentType.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">银行类型：</div>
			<div class="l-panel-search-item" >
				<select id="paymentName" name="paymentName" style="width: 150px;">
					<option value="">请选择</option>
					<option value="1">支付宝</option>
					<option value="2">微信</option>
					<c:forEach var="paymentName" items="${paymentNames}">
						<option value="${paymentName}">${paymentName}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">状态：</div>
			<div class="l-panel-search-item" >
				<select id="status" name="status" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="status" items="${statusList}">
						<option value="${status.statusValue}">${status.statusDesc}</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>