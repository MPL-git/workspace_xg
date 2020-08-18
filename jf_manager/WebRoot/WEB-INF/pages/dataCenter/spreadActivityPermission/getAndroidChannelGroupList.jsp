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
			url: '${pageContext.request.contextPath}/spreadActivityPermission/updateAndroidPermission.shtml',
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
	      url:"/spreadActivityPermission/getAndroidChannelGroupList.shtml",
	      listGrid:{ columns: [
							{display:'渠道集合ID', name:'id', align:'center', isSort:false, width:120},
							{display:'渠道集合名称', name:'group_name', align:'center', isSort:false, width:300},
							{display:'所属渠道组', name:'group_set_name', align:'center', isSort:false, width:200},
			                {display:'是否有效', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
			                	if(rowdata.status == '1' ) {
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
		<div class="search-pannel">
			<div class="search-tr"  >

				<div class="search-td">
					<div class="search-td-label"  >所属渠道组：</div>
					<div class="search-td-combobox-condition" >
						<select id="androidChannelGroupSetId" name="androidChannelGroupSetId" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="list" items="${androidChannelGroupSetList}">
								<option value="${list.id}">${list.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label"  >渠道集合ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >渠道集合名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="groupName" name="groupName" >
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >是否有效：</div>
					<select id="isEffect" name="status" style="width: 135px;">
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
