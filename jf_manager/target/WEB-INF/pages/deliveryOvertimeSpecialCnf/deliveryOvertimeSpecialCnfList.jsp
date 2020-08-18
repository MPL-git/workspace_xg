<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : false,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		
	 });
	 
	 function addDeliveryOvertimeSpecialCnf() {
		 $.ligerDialog.open({
				height: 600,
				width: 700,
				title: "添加",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/deliveryOvertimeSpecialCnf/editDeliveryOvertimeSpecialCnfManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 function updateDeliveryOvertimeSpecialCnf(deliveryOvertimeSpecialCnfId) {
		 $.ligerDialog.open({
				height: 600,
				width: 700,
				title: "修改",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/deliveryOvertimeSpecialCnf/editDeliveryOvertimeSpecialCnfManager.shtml?deliveryOvertimeSpecialCnfId="+deliveryOvertimeSpecialCnfId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 function delDeliveryOvertimeSpecialCnf(deliveryOvertimeSpecialCnfId) {
		 $.ligerDialog.confirm("确定删除吗？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/deliveryOvertimeSpecialCnf/delDeliveryOvertimeSpecialCnf.shtml",
					 data : {deliveryOvertimeSpecialCnfId : deliveryOvertimeSpecialCnfId},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.code != 200) {
							 commUtil.alertError(data.msg);
						 }else {
							 $("#searchbtn").click();
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				 });
			 }
		 });
	 }
	 
 	 var listConfig={ 
	      url:"/deliveryOvertimeSpecialCnf/deliveryOvertimeSpecialCnfList.shtml",
	      btnItems:[
		      { text: '添加', icon:'add', id:'add', dtype:'win', click:addDeliveryOvertimeSpecialCnf }
	      ],
	      listGrid:{ columns: [
						{display:'开始时间', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						  	if(rowdata.beginPayDate == null || rowdata.beginPayDate == '' ) {
							  	return "";
						  	}else{
							  	var beginPayDate = new Date(rowdata.beginPayDate);
							  	return beginPayDate.format("yyyy-MM-dd hh:mm:ss");
						  	}
						}},
						{display:'结束时间', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						  	if(rowdata.endPayDate == null || rowdata.endPayDate == '' ) {
							  	return "";
						  	}else{
							  	var endPayDate = new Date(rowdata.endPayDate);
							  	return endPayDate.format("yyyy-MM-dd hh:mm:ss");
						  	}
						}},
						{display:'内部备注', name:'name', align:'center', isSort:false, width:180},
						{display:'类型',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						  	if(rowdata.timeType == '0' ) {
							  	return "相对值";
						  	}else{
							  	return "绝对值";
						  	}
						}},
						{display:'发货承诺时间', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						  	var html = [];
						  	if(rowdata.timeType == '0' ) {
							  	html.push(rowdata.overtime+"小时");
						  	}else if(rowdata.timeType == '1' && rowdata.deliveryDate != null && rowdata.deliveryDate != '' ){
							  	var deliveryDate = new Date(rowdata.deliveryDate);
							  	html.push(deliveryDate.format("yyyy-MM-dd hh:mm:ss"));
						  	}
						  	return html.join("");
						}},
						{display:'涉及地区', name:'remarks', align:'center', isSort:false, width:280},
				  		{ display: '操作', name:'', align:'center', isSort:false, width:150, render:function(rowdata, rowindex) {
							var html = [];
							html.push("<a href='javascript:;' onclick='updateDeliveryOvertimeSpecialCnf("+rowdata.id+");'>【修改】</a>");
							html.push("<a href='javascript:;' onclick='delDeliveryOvertimeSpecialCnf("+rowdata.id+")'>【删除】</a>");
							return html.join("");
					  	}}
			         ],
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >支付日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="payDate" name="payDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			 	<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
