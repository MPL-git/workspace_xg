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
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
		//上架或下架
		$(".updateStatus").live("click", function(){
			 var commentDrawSettingId = $(this).attr("commentDrawSettingId");
			 var status = $(this).attr("commentDrawSettingStatus");
			 var titleStr = "是否上架？";
			 if(status == '0') {
				 titleStr = "是否下架？";
			 }
			 $.ligerDialog.confirm(titleStr, function(yes) {
				 if(yes) {
					 $.ajax({
						 type : 'POST',
						 url : "${pageContext.request.contextPath}/commentDrawSetting/updateCommentDrawSetting.shtml",
						 data : {commentDrawSettingId : commentDrawSettingId, status : status},
						 dataType : 'json',
						 success : function(data){
							 if(data == null || data.code != 200) {
								 commUtil.alertError(data.msg);
							 }else {
								 if(status == '1') {
									 $("#"+commentDrawSettingId+"-status").html("上架");
									 $("#"+commentDrawSettingId+"-status").attr("style", "");
									 $("[commentDrawSettingId='"+commentDrawSettingId+"']").html("【下架】");
									 $("[commentDrawSettingId='"+commentDrawSettingId+"']").attr("commentDrawSettingStatus", "0");
								 }else {
									 $("#"+commentDrawSettingId+"-status").html("下架");
									 $("#"+commentDrawSettingId+"-status").attr("style", "color:red;");
									 $("[commentDrawSettingId='"+commentDrawSettingId+"']").html("【上架】");
									 $("[commentDrawSettingId='"+commentDrawSettingId+"']").attr("commentDrawSettingStatus", "1");
								 }
							 }
						 },
						 error : function(e) {
							 commUtil.alertError("系统异常请稍后再试！");
						 }
					 });
				 }
			 });
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
	 
	 //添加刮奖活动
	 function addCommentDrawSetting() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "新增",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/commentDrawSetting/addOrUpdateCommentDrawSettingManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //编辑
	 function updateCommentDrawSetting(commentDrawSettingId) {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "编辑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/commentDrawSetting/addOrUpdateCommentDrawSettingManager.shtml?commentDrawSettingId="+commentDrawSettingId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //删除
	 function delCommentDrawSetting(commentDrawSettingId) {
		 $.ligerDialog.confirm("是否删除？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/commentDrawSetting/delCommentDrawSetting.shtml",
					 data : {commentDrawSettingId : commentDrawSettingId},
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
	      url:"/commentDrawSetting/getCommentDrawSettingList.shtml",
	      btnItems:[
		      { text: '添加刮奖活动', icon:'add', id:'add', dtype:'win', click:addCommentDrawSetting }
	      ],
	      listGrid:{ columns: [
							{display:'活动时间',name:'', align:'center', isSort:false, width:360, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.beginDate != null && rowdata.beginDate != '' ) {
									var beginDate = new Date(rowdata.beginDate);
									html.push(beginDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								html.push(" ~ ");
								if(rowdata.endDate != null && rowdata.endDate != '' ) {
									var endDate = new Date(rowdata.endDate);
									html.push(endDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'积分范围',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								html.push(rowdata.integralMin);
								html.push(" - ");
								html.push(rowdata.integralMax);
								return html.join("");
							}},
							{display:'状态',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status == '1') {
									html.push("<span id='"+rowdata.id+"-status'>上架</span>");
									
								}else {
									html.push("<span id='"+rowdata.id+"-status' style='color:red;'>下架</span>");
								}
								return html.join("");
							}},
							{display:'创建时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
			                }},
							{display:'操作',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='updateCommentDrawSetting("+rowdata.id+");'>【编辑】</a>");
								if(rowdata.status == '1') {
									html.push("<a href='javascript:;' class='updateStatus' commentDrawSettingId='"+rowdata.id+"' commentDrawSettingStatus='0' >【下架】</a>");
								}else {
									html.push("<a href='javascript:;' class='updateStatus' commentDrawSettingId='"+rowdata.id+"' commentDrawSettingStatus='1' >【上架】</a>");
								}
								html.push("<a href='javascript:;' onclick='delCommentDrawSetting("+rowdata.id+")'>【删除】</a>");
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
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
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
