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
	 
	 //审核结果
	 function updateAuditStatus(sportId, homeScore, awaysScore, homeTeamName, awayTeamName) {
		 var str = "";
		 if(homeScore > awaysScore) {
			 str = "当前结果是<span style='color:red;'>"+homeScore+":"+awaysScore+homeTeamName+"胜</span>请确认！";
		 }else if(homeScore == awaysScore) {
			 str = "当前结果是<span style='color:red;'>"+homeScore+":"+awaysScore+"比赛平局</span>请确认！";
		 }else if(homeScore < awaysScore) {
			 str = "当前结果是<span style='color:red;'>"+homeScore+":"+awaysScore+awayTeamName+"胜</span>请确认！";
		 }
		 $.ligerDialog.confirm(str, function(yes) {
			 if(yes) {
				 $.ajax({
					 type: 'post',
					 url: '${pageContext.request.contextPath }/sport/editAuditStatus.shtml',
					 data: {sportId : sportId, auditStatus : "3"},
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
					 url: '${pageContext.request.contextPath }/sport/editAuditStatus.shtml',
					 data: {sportId : sportId, auditStatus : "2"},
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
	 }
	 
	 //确认赛事结果
	 function openAuditStatus(sportId) {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "确认赛事结果",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sport/openAuditStatus.shtml?sportId="+sportId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		}); 
	 }
	 
	 //新增
	 function addSport() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "新增赛事",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sport/addOrUpdateSportManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //编辑
	 function updateSport(sportId) {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "修改队伍",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sport/addOrUpdateSportManager.shtml?sportId="+sportId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //操作记录
	 function sportLog(sportId) {
		 $.ligerDialog.open({
				height: 700,
				width: 800,
				title: "操作记录",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/sport/sportLogManager.shtml?sportId="+sportId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 
 	 var listConfig={
	      url:"/sport/getSportList.shtml",
	      btnItems:[
		      { text: '新增', icon:'add', id:'add', dtype:'win', click:addSport }
	      ],
	      listGrid:{ columns: [
							{display:'比赛开始时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if (rowdata.beginTime != null && rowdata.beginTime != '') {
									var beginTime = new Date(rowdata.beginTime);
									return beginTime.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'主场队伍',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.homeTeamName != '') {
									html.push(rowdata.homeTeamName);
								}
								if(rowdata.homeRate != '') {
									html.push("（"+rowdata.homeRate+"）");
								}
								return html.join("");
			                }},
							{display:'平局比例',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var drawRate = "0.00";
								if(rowdata.drawRate){
									drawRate = formatMoney(rowdata.drawRate, 2);
	 		                	}
								return drawRate;
			                }},
			                {display:'客场队伍',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.awayTeamName != '') {
									html.push(rowdata.awayTeamName);
								}
								if(rowdata.awaysRate != '') {
									html.push("（"+rowdata.awaysRate+"）");
								}
								return html.join("");
			                }},
							{display:'比赛名称',name:'sportName', align:'center', isSort:false, width:180},
							{display:'比赛结果',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.result == '0') {
									html.push("等待开赛");
								}else if(rowdata.result == '1') {
									html.push(rowdata.homeScore+":"+rowdata.awaysScore+"（"+rowdata.homeTeamName+"胜）");
								}else if(rowdata.result == '2') {
									html.push(rowdata.homeScore+":"+rowdata.awaysScore+"（"+rowdata.awayTeamName+"胜）");
								}else if(rowdata.result == '3') {
									html.push(rowdata.homeScore+":"+rowdata.awaysScore+"（平局）");
								}
								return html.join("");
			                }},
			                {display:'状态',name:'resultDesc', align:'center', isSort:false, width:100},
			                {display:'操作人员',name:'createStaffName', align:'center', isSort:false, width:100},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='updateSport("+rowdata.id+");'>【编辑】</a>");
								html.push("<a href='javascript:;' onclick='sportLog("+rowdata.id+")'>【操作记录】</a>");
							    return html.join("");
							}},
							{display:'审核',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								var nowTime = new Date().getTime();
								var beginTime = 0;
								if (rowdata.beginTime != null && rowdata.beginTime != '') {
									beginTime = new Date(rowdata.beginTime).getTime();
								}
								if(nowTime > beginTime) {
									if(rowdata.auditStatus == '2') {
										html.push("<span style='color: gray;' >【已审核通过】</span>");
									}else if(rowdata.auditStatus == '0' || rowdata.auditStatus == '3') {
										html.push("<a href='javascript:;' onclick='openAuditStatus("+rowdata.id+");'>【确认赛事结果】</a>");
									}else if(rowdata.auditStatus == '1') {
										html.push("<a href='javascript:;' onclick='updateAuditStatus("+rowdata.id+", "+rowdata.homeScore+", "
												+rowdata.awaysScore+", \""+rowdata.homeTeamName+"\", \""+rowdata.awayTeamName+"\");'>【审核结果】</a>");
									}
								}else {
									html.push("<span style='color: gray;' >【确认赛事结果】</span>");
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
					<div class="search-td-label"  >比赛名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sportName" name="sportName" >
					</div>
				</div>
			 	<div class="search-td">
					<div class="search-td-label" >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="sportResult" name="sportResult" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="result" items="${resultList }">
								<option value="${result.statusValue }">
									${result.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">比赛开始时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="startBeginTime" name="startBeginTime" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endBeginTime" name="endBeginTime" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >队伍名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sportTeamName" name="sportTeamName" >
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
