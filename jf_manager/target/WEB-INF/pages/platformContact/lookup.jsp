<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript">
//查看商家联系人
 function mchtContact(id){
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width(),
		title: "商家联系人",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtContact.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }
 
 //平台对接人
 function mchtPlatformContact(id){
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width(),
		title: "平台分配对接人",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtplatformcontact.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }
 
 
 
function editMchtBaseInfo(id) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "商家基础资料",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
	
function editMchtFinanceInfo(id) {
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width() - 200,
		title: "商家财务信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtFinanceInfoEdit.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
	
 var listConfig={
      url:"/service/platformContact/lookupdata.shtml?platContactId=${ID}",
   
      listGrid:{ columns: [  { display: '商家序号', name: 'mcht_code',width:100 }, 
         					{ display: '商家类型', width:100, render: function(rowdata, rowindex) {
    		                	var html = [];
    		                	html.push(rowdata.is_manage_self_desc);
    		                	html.push(rowdata.mcht_type_desc);
    		                	return html.join("");
        					}},
		                { display: '商家简称',  name: 'short_name', width: 200 }, 
		                { display: '店铺名', name: 'shop_name' ,width: 200 },
		                { display: '合作状态', name: 'status_desc', width:100},
		                { display: '公司信息', name: 'is_company_inf_perfect', width:100, render: function(rowdata, rowindex) {
		                	var html = [];
							 if(rowdata.is_company_inf_perfect ==0){//(0:未完善 )
								 html.push("<img src=\"${pageContext.request.contextPath}/liger/lib/ligerUI/skins/icons/delete.gif\" /><br>");
							 }
							 html.push(rowdata.is_company_inf_perfect_desc);
							 return html.join("");
		              	}}, 
		              	{ display: '税务资料', name: 'tax_invoice_audit_status', width:100, render: function(rowdata, rowindex) {
		                	var html = [];
							 if(rowdata.tax_invoice_audit_status ==0){//(0:未完善 )
								 html.push("<img src=\"${pageContext.request.contextPath}/liger/lib/ligerUI/skins/icons/delete.gif\" /><br>");
							 }
							 html.push(rowdata.tax_invoice_audit_status_desc);
							 return html.join("");
		              	}},
		              	{ display: "基础资料", name: "OPER1", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    return html.join("");
		              	}},
		              	{ display: "财务信息", name: "OPER2", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    return html.join("");
		              	}},
		              	{ display: "商家联系人", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:mchtContact(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    return html.join("");
		              	}},
		              	{ display: "平台对接人", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:mchtPlatformContact(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
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
<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
