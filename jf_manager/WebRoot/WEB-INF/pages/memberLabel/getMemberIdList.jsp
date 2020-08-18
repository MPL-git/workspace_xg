<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	 $(function() {
		 $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		 });
	 });
	 
	 //查看会员
	 function viewDetail(id) {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
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
	      url:"/memberLabel/getMemberIdList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:100},
							{display:'会员昵称',name:'memberNick', align:'memberNick', isSort:false, width:160},
							{display:'会员状态',name:'memberStatusDesc', align:'center', isSort:false, width:130},
							{display:'注册时间',name:'', align:'center', isSort:false, width:200, render:function(rowdata,rowindex){ 
								var html = [];
								if(rowdata.memberCreateDate != null && rowdata.memberCreateDate != "" ){
									var memberCreateDate = new Date(rowdata.memberCreateDate);
									html.push(memberCreateDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							 {display:'操作',name:'', align:'center', isSort:false, width:180, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:viewDetail(" + rowdata.memberId + ");\">【查看】</a>");
								return html.join("");
							}} 
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } , 							
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<%-- <input type="hidden" id="id" name="id" value="${getMemeberId}" > --%>
			<input type="hidden" id="labelRuleId" name="labelRuleId" value="${labelRuleId}" >
			<div class="search-tr" > 
				<div class="search-td">
					<div class="search-td-label"  >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memeberId" name="memeberId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会员昵称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="nick" name="nick" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">注册时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
