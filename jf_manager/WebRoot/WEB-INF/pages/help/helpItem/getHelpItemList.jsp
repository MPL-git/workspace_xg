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
				 url : "${pageContext.request.contextPath}/helpItem/updateSeqNo.shtml",
				 data : {helpItemId : id, seqNo : seqNo},
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
	 function addHelpItem() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "新增",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/helpItem/addOrUpdateHelpItemManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //编辑
	 function updateHelpItem(helpItemId) {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "编辑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/helpItem/addOrUpdateHelpItemManager.shtml?helpItemId="+helpItemId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //删除
	 function delHelpItem(helpItemId) {
		 $.ligerDialog.confirm("删除后，该帮助将从后台删除且用户端也无法查看，确定删除？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/helpItem/delHelpItem.shtml",
					 data : {helpItemId : helpItemId},
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
	 
 	 var listConfig={ 
	      url:"/helpItem/helpItemList.shtml",
	      btnItems:[
		      { text: '新增', icon:'add', id:'add', dtype:'win', click:addHelpItem }
	      ],
	      listGrid:{ columns: [
							{display:'排序',name:'seqNo', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:80px; margin-top: 5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >");
	                        	return html.join("");
	                        }},
							{display:'帮助标题',name:'title', align:'center', isSort:false, width:180},
							{display:'所属标签',name:'helpTagName', align:'center', isSort:false, width:180},
							{display:'创建人',name:'createStaffName', align:'center', isSort:false, width:160},
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
								html.push("<a href='javascript:;' onclick='updateHelpItem("+rowdata.id+");'>【编辑】</a>");
								html.push("<a href='javascript:;' style='color: red;' onclick='delHelpItem("+rowdata.id+")'>【删除】</a>");
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
					<div class="search-td-label"  >帮助标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="helpItemTitle" name="helpItemTitle" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >所属标签：</div>
					<div class="search-td-combobox-condition" >
						<select id="helpTagId" name="helpTagId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="helpTag" items="${helpTagList }">
								<option value="${helpTag.id }">
									${helpTag.name }
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
