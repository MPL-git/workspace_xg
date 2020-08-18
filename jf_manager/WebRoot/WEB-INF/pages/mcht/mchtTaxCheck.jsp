<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<html>
<head>
<style type="text/css">
.table-title-link{color: #1B17EE;font-size: 14px;text-decoration: none;}
</style>
 <script type="text/javascript">
 function mchtTaxInvoiceInfoEdit(id) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-500,
		title: "税务开票信息修改与审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtTaxInvoiceInfoEdit.shtml?id=" +id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
      var listConfig={
	  
      url:"/mcht/mchtTaxcheckData.shtml",
      listGrid:{ columns: [
   		                { display: '纳税类型', name: 'taxTypeDesc',width:150 },
		                { display: '公司名称',  name: 'companyName'}, 
		                { display: '纳税人识别号', name: 'taxNumber'},
		                { display: '地址', name: 'address'},
		                { display: '电话', name: 'tel' ,width: 150 },
		                { display: '开户行', name: 'depositBank' },
		                { display: '账号', name: 'accountNumber' ,width: 200 },
		                { display: '状态', name: 'auditStatusDesc' ,width: 100 },
		                { display: '操作', name: 'OPER', width:150, render: function(rowdata, rowindex) {
		                	var html = [];
		                	if (rowdata.auditStatus==1 || rowdata.auditStatus==2)
		                	{
		                		html.push('<a href="javascript:mchtTaxInvoiceInfoEdit('+rowdata.id+')" class="table-title-link">【审核】</a>');
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
       
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
			 	<div class="search-td-label" >审核状态：</div>
			 	<div class="search-td-condition" >
				<select  id="auditStatus" name="auditStatus" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${auditStatus}">
						<c:if test="${list.STATUS_VALUE!=0}">
						<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}</option>
						</c:if>
					</c:forEach>
				</select>
				</div>
				</div>
				
				<div class="search-td">
			 	<div class="search-td-label" >纳税类型：</div>
			 	<div class="search-td-condition" >
				<select  id="taxType" name="taxType" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${taxType}">
						<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}</option>
					</c:forEach>
				</select>
				</div>
				</div>
			
				<div class="search-td">
				<div class="search-td-label">公司名称：</div>
				<div class="search-td-condition" >
				<input type="text" id ="companyName" name="companyName" >
				</div>
				</div>
				
				<div  class="search-td-search-btn" >
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
			</div>
						
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
