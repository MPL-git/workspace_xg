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
	 
	 //排序值
	 function updateSeqNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/helpTag/updateSeqNo.shtml",
				 data : {helpTagId : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.code != 200) {
						 commUtil.alertError(data.msg);
					 }else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
						 $("#seqNo" + id).attr("seqNo", seqNo);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + id).val($("#seqNo" + id).attr("seqNo"));
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		} 
	 }
	 
	 
	 //新增
	 function addHelpTag() {
		 $.ligerDialog.open({
				height: 400,
				width: 500,
				title: "新增",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/helpTag/addOrUpdateHelpTagManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //编辑
	 function updateHelpTag(helpTagId) {
		 $.ligerDialog.open({
				height: 400,
				width: 500,
				title: "编辑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/helpTag/addOrUpdateHelpTagManager.shtml?helpTagId="+helpTagId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //删除
	 function delHelpTag(helpTagId) {
		 $.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/helpTag/helpItemCount.shtml",
			 data : {helpTagId : helpTagId},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.code != 200) {
					 commUtil.alertError(data.msg);
				 }else if(Number(data.helpItemCount) > 0) {
					 commUtil.alertError("该标签下有关联帮助问题，请解除关联后重试！");
					 $(".l-dialog-content").css("margin-right", "5px");
				 }else {
					 $.ligerDialog.confirm("删除后，该帮助标签将从后台删除，确定删除？", function(yes) {
						 if(yes) {
							 $.ajax({
								 type : 'POST',
								 url : "${pageContext.request.contextPath}/helpTag/delHelpTag.shtml",
								 data : {helpTagId : helpTagId},
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
					 $(".l-dialog-content").css("margin-right", "5px");
					 $(".l-dialog-btn-inner:even").html("取消");
					 $(".l-dialog-btn-inner:odd").html("确认");
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试！");
			 }
		 });
	 }
	 
 	 var listConfig={
	      url:"/helpTag/getHelpTagList.shtml",
	      btnItems:[
		      { text: '新增', icon:'add', id:'add', dtype:'win', click:addHelpTag }
	      ],
	      listGrid:{ columns: [
							{display:'排序',name:'seqNo', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:80px; margin-top: 5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >");
	                        	return html.join("");
	                        }},
							{display:'标签名称',name:'name', align:'center', isSort:false, width:180},
							{display:'备注',name:'remarks', align:'center', isSort:false, width:180},
			                {display:'创建时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	if(rowdata.createDate == null || rowdata.createDate == '' ) {
									return "";
								}else{
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								}
			                }},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='updateHelpTag("+rowdata.id+");'>【编辑】</a>");
								html.push("<a href='javascript:;' style='color: red;' onclick='delHelpTag("+rowdata.id+")'>【删除】</a>");
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
					<div class="search-td-label"  >标签名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="helpTagName" name="helpTagName" >
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
