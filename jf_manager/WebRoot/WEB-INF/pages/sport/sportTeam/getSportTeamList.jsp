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
	 
	 //审核
	 function updateAuditStatus(sportTeamId, auditStatus, sportName) {
		 var str = "";
		 var content = "";
		 if(auditStatus == '2') {
			 str = "你当前审核内容为淘汰<span style='color: red;'>"+sportName+"</span>！";
			 $.ligerDialog.confirm(str, function(yes) {
				 if(yes) {
					 $.ajax({
						 type: 'post',
						 url: '${pageContext.request.contextPath }/sportTeam/updateAuditStatus.shtml',
						 data: {sportTeamId : sportTeamId, auditStatus : "0", content : "驳回审核淘汰"},
						 dataType: 'json',
						 success: function(data) {
							 if(data == null || data.code != 200){
							     commUtil.alertError(data.msg);
							 }else {
								 $("#searchbtn").click();
							 }
						 },
						 error: function(e) {
							 commUtil.alertError("系统异常请稍后再试！");
						 }
					 });
				 }else {
					 $.ajax({
						 type: 'post',
						 url: '${pageContext.request.contextPath }/sportTeam/updateAuditStatus.shtml',
						 data: {sportTeamId : sportTeamId, auditStatus : auditStatus, content : "通过审核淘汰"},
						 dataType: 'json',
						 success: function(data) {
							 if(data == null || data.code != 200){
							     commUtil.alertError(data.msg);
							 }else {
								 $("#searchbtn").click();
							 }
						 },
						 error: function(e) {
							 commUtil.alertError("系统异常请稍后再试！");
						 }
					 });
				 }
			 });
			 $(".l-dialog-btn-inner:even").html("通过");
			 $(".l-dialog-btn-inner:odd").html("驳回");
		 }else {
			 if(auditStatus == '1') {
			 	str = "确定淘汰<span style='color: red;'>"+sportName+"</span>吗？";
			 	content = "审核淘汰";
			 }else if(auditStatus == '4') {
			 	str = "确定<span style='color: red;'>"+sportName+"</span>夺冠吗？"; 
			 	content = "夺冠";
			 }
			 $.ligerDialog.confirm(str, function(yes) {
				 if(yes) {
					 $.ajax({
						 type: 'post',
						 url: '${pageContext.request.contextPath }/sportTeam/updateAuditStatus.shtml',
						 data: {sportTeamId : sportTeamId, auditStatus : auditStatus, content : content},
						 dataType: 'json',
						 success: function(data) {
							 if(data == null || data.code != 200){
							     commUtil.alertError(data.msg);
							 }else {
								 $("#searchbtn").click();
							 }
						 },
						 error: function(e) {
							 commUtil.alertError("系统异常请稍后再试！");
						 }
					 });
				 }
			 });
			 $(".l-dialog-btn-inner:even").html("取消");
			 $(".l-dialog-btn-inner:odd").html("确认");
		 }
	 }
	 
	 //最后投注时间
	 function updateSportType() {
		 $.ligerDialog.open({
				height: 400,
				width: 500,
				title: "夺冠最后投注时间",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sportTeam/sportTypeManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //新增
	 function addSportTeam() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "新增队伍",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sportTeam/addOrUpdateSportTeamManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //编辑
	 function updateSportTeam(sportTeamId) {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "修改队伍",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sportTeam/addOrUpdateSportTeamManager.shtml?sportTeamId="+sportTeamId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //操作记录
	 function sportTeamLog(sportTeamId) {
		 $.ligerDialog.open({
				height: 700,
				width: 800,
				title: "操作记录",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sportTeam/sportTeamLogManager.shtml?sportTeamId="+sportTeamId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 
 	 var listConfig={
	      url:"/sportTeam/getSportTeamList.shtml",
	      btnItems:[
		      { text: '新增', icon:'add', id:'add', dtype:'win', click:addSportTeam },
	          { line:true }, 
	          { text: '最后投注时间', icon:'modify', id:'modify', click:updateSportType }
	      ],
	      listGrid:{ columns: [
							{display:'参赛队伍',name:'name', align:'center', isSort:false, width:180},
							{display:'参赛图标',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
			                    return "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' >";
			                }},
							{display:'夺冠比例',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var winRate = "0.00";
								if(rowdata.winRate){
									winRate = formatMoney(rowdata.winRate, 2);
	 		                	}
								return winRate;
			                }},
							{display:'队伍状态',name:'resultDesc', align:'center', isSort:false, width:180},
	                        {display:'操作人员',name:'createStaffName', align:'center', isSort:false, width:180},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='updateSportTeam("+rowdata.id+");'>【编辑】</a>");
								html.push("<a href='javascript:;' onclick='sportTeamLog("+rowdata.id+")'>【操作记录】</a>");
							    return html.join("");
							}},
							{display:'审核',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.result == '0') {
									if(rowdata.auditStatus == '1') {
										html.push("<a href='javascript:;' onclick='updateAuditStatus("+rowdata.id+", 2, \""+rowdata.name+"\");'>【审核淘汰】</a>");
									}else {
										if(rowdata.sumSport == 0) {
											if(rowdata.sumCount == '1') {
												html.push("<a href='javascript:;' onclick='updateAuditStatus("+rowdata.id+", 4, \""+rowdata.name+"\");'>【夺冠】</a>");
											}else {
												html.push("<a href='javascript:;' onclick='updateAuditStatus("+rowdata.id+", 1, \""+rowdata.name+"\");'>【淘汰】</a>");
											}
										}else {
											if(rowdata.sumCount == '1') {
												html.push("<a href='javascript:void(0);' style='color: gray;' >【夺冠】</a>");
											}else {
												html.push("<a href='javascript:void(0);' style='color: gray;' >【淘汰】</a>");
											}
										}
									}
								}else if(rowdata.result == '1'){
									html.push("<span style='color: gray;' >【已淘汰】</span>");
								}else if(rowdata.result == '2'){
									html.push("<span style='color: red;' >【已夺冠】</span>");
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >参赛队伍：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sportTeamName" name="sportTeamName" >
					</div>
				</div>
			 	<div class="search-td">
					<div class="search-td-label" >队伍状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="sportTeamResult" name="sportTeamResult" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="result" items="${resultList }">
								<option value="${result.statusValue }">
									${result.statusDesc }
								</option>
							</c:forEach>
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
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
