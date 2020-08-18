<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
	});

	function delXiaoneng(xiaonengId, mchtId) {
		$.ligerDialog.confirm('确定关闭吗？', function (yes){
			if(yes == true){
				$.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath}/xiaonengCustomerService/delXiaoneng.shtml',
					data : {xiaonengId : xiaonengId, mchtId : mchtId},
					dataType : "json",
					success : function(data){
					   if(data == null || data.code != 200){
					     commUtil.alertError(data.msg);
					  }else{
		                 $("#searchbtn").click();
					  }
					},
					error : function(e){
					 commUtil.alertError("系统异常请稍后再试！");
					}
				});
		    }
		}); 
	}

	var listConfig = {
		url : "/xiaonengCustomerService/xiaonengCustomerServiceList.shtml",
		btnItems:[],
		listGrid : {
			columns : [{display:'合作模式', name:'mchtTypeDesc', align:'center', isSort:false, width:100},
			        {display:'商家序号', name:'mchtCode', align:'center', isSort:false, width:120},
			        {display:'公司名称', name:'companyName', align:'center', isSort:false, width:200},
			        {display:'店铺名称', name:'shopName', align:'center', isSort:false, width:200},
					{display:'客服序号', name:'xiaonengId', align:'center', isSort:false, width:100},
					{display:'企业ID', name:'xiaonengBusId', align:'center', isSort:false, width:100},
					{display:'客服代码', name:'xiaonengCode', align:'center', isSort:false, width:200},
					{display:'操作', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						var html = []; 
						html.push("<a href=\"javascript:delXiaoneng(" + rowdata.xiaonengId + "," + rowdata.id + ");\">【关闭】</a>");
						return html.join("");
					}}
				],
			showCheckbox : false, //不设置默认为 true
			showRownumber : true
		//不设置默认为 true
		},
		container : {
			toolBarName : "toptoolbar",
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
		}
	};
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<input type="hidden" name="flagStatus" value="3" />
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>

	<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
