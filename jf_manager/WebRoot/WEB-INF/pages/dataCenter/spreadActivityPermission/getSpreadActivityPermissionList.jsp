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
			showTime : true,
			format : "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 150
		});
		
	 });
 
	 function formatMoney(s, n) {
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
	 
	 //iOS活动组权限设置  or 安卓渠道集合权限设置
	 function addOrUpdatePermissionManager(staffId, type, staffCode, staffName) {
		 var title = "iOS活动组权限设置（"+staffCode+"-"+staffName+"）";
		 if(type == 2 ) {
			 title = "安卓渠道集合权限设置（"+staffCode+"-"+staffName+"）";
		 }else if(type == 3 ) {
			 title = "安卓活动组权限设置（"+staffCode+"-"+staffName+"）";
		 }
		 $.ligerDialog.open({
			 	height: $(window).height() - 200,
				width: $(window).width() - 200,
				title: title,
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/spreadActivityPermission/addOrUpdatePermissionManager.shtml?staffId="+staffId+"&type="+type,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
 	 var listConfig={ 
	      url:"/spreadActivityPermission/getSpreadActivityPermissionList.shtml",
	      listGrid:{ columns: [
							{display:'人员标识', name:'STAFF_ID', align:'center', isSort:false, width:120},
							{display:'人员工号', name:'STAFF_CODE', align:'center', isSort:false, width:120},
							{display:'人员名称', name:'STAFF_NAME', align:'center', isSort:false, width:180},
			                {display:'组织部门', name:'ORG_ID_NAME', align:'center', isSort:false, width:180},
			                {display:'联系号码', name:'MOBILE_PHONE', align:'center', isSort:false, width:180},
			                {display:'管理层', name:'', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
					        	var html = [];
					        	if(rowdata.IS_MANAGEMENT == '1') {
						        	html.push("是");
					        	}
					        	return html.join("");
				         	}},
			                {display:'创建时间', name:'CREATE_DATE', align:'center', isSort:false, width:180},
							{display:'操作', name:'', align:'center', isSort:false, width:420, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='addOrUpdatePermissionManager("+rowdata.STAFF_ID+", 1, \""+rowdata.STAFF_CODE+"\", \""+rowdata.STAFF_NAME+"\");'>【iOS活动组权限设置】</a>");
								html.push("<a href='javascript:;' onclick='addOrUpdatePermissionManager("+rowdata.STAFF_ID+", 2, \""+rowdata.STAFF_CODE+"\", \""+rowdata.STAFF_NAME+"\");'>【安卓渠道集合权限设置】</a>");
								html.push("<a href='javascript:;' onclick='addOrUpdatePermissionManager("+rowdata.STAFF_ID+", 3, \""+rowdata.STAFF_CODE+"\", \""+rowdata.STAFF_NAME+"\");'>【安卓活动组权限设置】</a>");
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server" >
		<div id="maingrid" style="margin: 0; padding: 0; "></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" >

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
