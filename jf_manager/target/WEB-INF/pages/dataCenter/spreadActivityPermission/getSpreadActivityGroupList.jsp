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
	 
	 //添加权限 or 移除权限
	 function updatePermission(spreadId, type) {
		 $.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/spreadActivityPermission/updateIOSPermission.shtml',
			data: {spreadId : spreadId, staffId : ${staffId}, type : type},
		 	dataType: 'json',
		 	success: function(data) {
		 		if(data == null || data.statusCode != 200 ) {
		 			commUtil.alertError(data.message);
		 		}else {
		 			$("#searchbtn").click();
		 		}
		 	},
		 	error: function(e) {
		 		commUtil.alertError("系统异常请稍后再试");
		 	}
		 });
	 }
	 
 	 var listConfig={ 
	      url:"/spreadActivityPermission/getSpreadActivityGroupList.shtml",
	      listGrid:{ columns: [
							{display:'活动组ID', name:'id', align:'center', isSort:false, width:120},
							{display:'活动组名称', name:'activity_group', align:'center', isSort:false, width:200},
							{display:'所属推广渠道集合', name:'group_set_name', align:'center', isSort:false, width:200},
			                {display:'是否有效', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
			                	if(rowdata.is_effect == '1' ) {
									return "是";
								}else{
									return "否";
								}
			                }},
			                {display:'权限状态', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
			                	if(rowdata.permission_id == null ) {
									return "未添加";
								}else{
									return "已添加";
								}
			                }},
							{display:'操作', name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.permission_id == null ) {
									html.push("<a href='javascript:;' onclick='updatePermission("+rowdata.id+", 1);'>【添加权限】</a>");
								}else{
									html.push("<a href='javascript:;' onclick='updatePermission("+rowdata.id+", 0)'>【移除权限】</a>");
								}
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
		<input type="hidden" id="staffId" name="staffId" value="${staffId }" />
		<input type="hidden" id="deviceType" name="deviceType" value="${deviceType }" />
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >推广渠道集合：</div>
					<div class="search-td-combobox-condition" >
						<select id="groupSetId" name="groupSetId" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="list" items="${spreadActivityGroupSetCustomList}">
								<option value="${list.id}">${list.channelName}_${list.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >活动组ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id" >
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >活动组名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityGroup" name="activityGroup" >
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >是否有效：</div>
					<select id="isEffect" name="isEffect" style="width: 135px;">
						<option value="">请选择...</option>
						<option value="1">有效</option>
						<option value="0">失效</option>
					</select>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >权限状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="permissionStatus" name="permissionStatus" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="0">未添加</option>
							<option value="1">已添加</option>
						</select>
					</div>
				</div>
			 	<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" >

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
