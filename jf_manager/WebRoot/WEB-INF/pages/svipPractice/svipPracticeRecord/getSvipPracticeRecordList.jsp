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
	 
	 //延长体验时间
	 function addPracticeTime(id) {
		 $.ligerDialog.open({
			height: 500,
			width: 600,
			title: "延长体验时间",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/svipPracticeRecord/addPracticeTimeManager.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }

	 //会员资料
	 function viewDetail(id) {
		 $.ligerDialog.open({
			 height: $(window).height() - 50,
			 width: $(window).width() - 100,
			 title: "会员资料",
			 name: "INSERT_WINDOW",
			 url: "${pageContext.request.contextPath}/member/info/detail.shtml?id=" + id,
			 showMax: true,
			 showToggle: false,
			 showMin: false,
			 isResize: true,
			 slide: false,
			 data: null
		 });
	 }
	 
 	 var listConfig={
	      url:"/svipPracticeRecord/getSvipPracticeRecordList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:void(0);' onclick='viewDetail("+rowdata.memberId+");'>"+rowdata.memberId+"</a>");
								return html.join("");
							}},
							{display:'昵称',name:'nick', align:'center', isSort:false, width:180},
							{display:'会员状态',name:'statusDesc', align:'center', isSort:false, width:180},
							{display:'领取时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.recTime != null && rowdata.recTime != '' ) {
									var recTime = new Date(rowdata.recTime);
									html.push(recTime.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
				  			{display:'体验开始时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.practiceStartTime != null && rowdata.practiceStartTime != '' ) {
									var practiceStartTime = new Date(rowdata.practiceStartTime);
									html.push(practiceStartTime.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
				  			{display:'体验结束时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.practiceEndTime != null && rowdata.practiceEndTime != '' ) {
									var practiceEndTime = new Date(rowdata.practiceEndTime);
									html.push(practiceEndTime.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								var practiceStartTime = new Date(rowdata.practiceStartTime);
								if(new Date(practiceStartTime.setDate(practiceStartTime.getDate()+30)) >= new Date() ) {
									html.push("<a href='javascript:void(0);' onclick='addPracticeTime("+rowdata.id+");'>【延长体验时间】</a>");
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
	<%--<div id="toptoolbar"></div>--%>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">领取时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="recTimeStart" name="recTimeStart" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="recTimeEnd" name="recTimeEnd" class="dateEditor" style="width: 135px;" />
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
