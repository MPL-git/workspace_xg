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
	 
	 //新增推送
	 function addSignSendMsgCnf() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "新增推送",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/memberSignIn/addOrUpdateSignSendMsgCnfManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //编辑
	 function updateSignSendMsgCnf(signSendMsgCnfId) { 
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "修改推送",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/memberSignIn/addOrUpdateSignSendMsgCnfManager.shtml?signSendMsgCnfId="+signSendMsgCnfId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		}); 
	 }
	
	 //删除
	 function delSignSendMsgCnf(signSendMsgCnfId) {
		 $.ligerDialog.confirm("是否确认删除此推送？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type: 'post',
					 url: '${pageContext.request.contextPath }/memberSignIn/delSignSendMsgCnf.shtml',
					 data: {signSendMsgCnfId : signSendMsgCnfId},
					 dataType: 'json',
					 success: function(data) {
						 if(data == null || data.code != 200){
						     commUtil.alertError(data.msg);
						 }else {
							 listModel.gridManager.reload();
						 }
					 },
					 error: function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				 });
			 }
		 });
	 }
	 
	 
 	 var listConfig={
	      url:"/memberSignIn/getSignSendMsgCnfList.shtml",
	      btnItems:[
		      { text: '新增', icon:'add', id:'add', dtype:'win', click:addSignSendMsgCnf }
	      ],
	      listGrid:{ columns: [
							{display:'推送形式',name:'sendWayDesc', align:'center', isSort:false, width:180},
							{display:'推送条件',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if(rowdata.noSignDay){
									return "满"+rowdata.noSignDay+"天未签到";
	 		                	}
								return "";
			                }},
							{display:'推送内容',name:'content', align:'center', isSort:false, width:260},
							{display:'备注',name:'remarks', align:'center', isSort:false, width:260},
			                {display:'操作人员',name:'createStaffName', align:'center', isSort:false, width:160},
							{display:'操作',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='updateSignSendMsgCnf("+rowdata.id+");'>【编辑】</a>");
								html.push("<a href='javascript:;' onclick='delSignSendMsgCnf("+rowdata.id+");'>【删除】</a>");
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
					<div class="search-td-label" >推送方式：</div>
					<div class="search-td-combobox-condition" >
						<select id="sendWay" name="sendWay" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sendWay" items="${sendWayList }">
								<option value="${sendWay.statusValue }">
									${sendWay.statusDesc }
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
