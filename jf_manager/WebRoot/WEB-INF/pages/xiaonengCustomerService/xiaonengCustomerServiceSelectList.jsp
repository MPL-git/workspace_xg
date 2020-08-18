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

	//调用父级窗口方法
	function selectXiaonengCustomerService(id, busId, mchtName, code) {
		window.parent.childFun(id, busId, mchtName, code);
		frameElement.dialog.close();
	}

	var listConfig = {
		url : "",
		btnItems:[],
		listGrid : {
			columns : [{display:'选择', name:'mchtTypeDesc', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
						var html = []; 
						html.push("<a href=\"javascript:selectXiaonengCustomerService('"+rowdata.id+"','"+rowdata.busId+"','"+rowdata.mchtName+"','"+rowdata.code+"');\">【选择】</a>");
						return html.join("");
					}},
			        {display:'商户名称', name:'mchtName', align:'center', isSort:false, width:200},
					{display:'客服序号', name:'id', align:'center', isSort:false, width:100},
					{display:'企业ID', name:'busId', align:'center', isSort:false, width:100},
					{display:'客服代码', name:'code', align:'center', isSort:false, width:200}
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
	
	function subFunction() {
	  	var mchtName = $("#mchtName").val();
	  	var xiaonengId = $("#xiaonengId").val();
	  	var xiaonengBusId = $("#xiaonengBusId").val();
		if(mchtName != '' || xiaonengId != '' || xiaonengBusId != '') {
			listModel.ligerGrid.url = '${pageContext.request.contextPath}/xiaonengCustomerService/xiaonengCustomerServiceData.shtml';
			listModel.initdataPage();
		}else {
			commUtil.alertError("对不起，请设置搜索条件！");
		}
  	}
	
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<input type="hidden" name="flagStatus" value="2" />
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >商户名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >客服序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="xiaonengId" name="xiaonengId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >企业ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="xiaonengBusId" name="xiaonengBusId" >
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div class="l-button" onclick="subFunction();" >搜索</div>
				</div>
			</div>
		</div>
	</form>

	<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
