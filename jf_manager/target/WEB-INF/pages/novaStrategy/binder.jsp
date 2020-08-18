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
	 
	 //添加
	 function add(memberId){
		 $.ajax({
				url : "${pageContext.request.contextPath}/salesmanManage/add.shtml?memberId="+memberId,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				timeout : 30000,
				success : function(data) {
				if ("200" == data.statusCode) {
					$.ligerDialog.success('成功绑定会员');
					setTimeout(function(){
						$(window.parent.document).find("#memberIdTag").val(data.memberInfo.id);
						$(window.parent.document).find("#memberId").html(data.memberInfo.id+'&nbsp;&nbsp;&nbsp;<a href="javascript:bind();">绑定会员</a>');
						$(window.parent.document).find("#nick").html(data.memberInfo.nick);
						frameElement.dialog.close();		
					},1000);
				}else{
					$.ligerDialog.error(data.message);
				}},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
	 }
	 
 	var listConfig={
	      url:"/salesmanManage/binderList.shtml",
	      listGrid:{ columns: [                    
	                        {display:'会员ID', name:'id',width:110, align:'center', isSort:false},
	                        {display:'会员昵称', name:'nick', width:170,align:'center', isSort:false},
	                        {display:'手机号', name:'mobile',width:164,align:'center', isSort:false},
	                        {display:'角色', name:'tag', align:'center',width:90, isSort:false, render: function(rowdata, rowindex) {
	                        	if(rowdata.salesmanTag != null){
	                        		return '业务员';
	                        	}else if(rowdata.shopownerTag != null){
	                        		return '店长';
	                        	}else{
	                        		return '会员';
	                        	}
							}},
	                        {display:'会员状态', name:'status',width:120, align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	if(rowdata.status == 'A'){
	                        		return '已激活';
	                        	}
	                        	if(rowdata.status == 'N'){
	                        		return '待激活';
	                        	}
	                        	if(rowdata.status == 'P'){
	                        		return '黑名单';
	                        	}
	                        	if(rowdata.status == 'C'){
	                        		return '注销';
	                        	}
							}},
	                        {display:'注册时间', name:'createDate', width:170,align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
							 {display:'最后登录时间', name:'lastDate',width:170, align:'center', isSort:false, render: function(rowdata, rowindex) {
		                        	var html = [];
		                        	if (rowdata.lastDate != null && rowdata.lastDate != '') {
										var lastDate = new Date(rowdata.lastDate);
										html.push(lastDate.format("yyyy-MM-dd hh:mm:ss"));
									}
									return html.join("");
									
								}},
	                        {display:'操作', name:'', align:'center', width:80,isSort:false, render: function(rowdata, rowindex) {
	                        	if(rowdata.salesmanTag != null || rowdata.shopownerTag != null || (rowdata.invitationMemberId != '' && rowdata.invitationMemberId != null)){
	                        		return '';
	                        	}else{
	                        		return '<a href="javascript:add('+rowdata.id+');">添加</a>';
	                        	}
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        searchBtnName:"searchbtn1",
	        fromName:"dataForm1",
	        listGridName:"maingrid"
	      }   
	   };

 	function judge(){
 		if($('#memberId').val() == '' && $('#nick').val() == '' && $('#mobile').val() == ''){
 			$.ligerDialog.warn('搜索条件必填一项！');
 			return false;
 		}
 	}
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<input type="hidden" id="pageSize" value="${pageSize}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<form id="dataForm1" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td"  style="width:230px;">
					<div class="search-td-label">会员ID</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">会员昵称</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="nick" name="nick">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">手机号</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mobile" name="mobile">
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;margin-right:80px;margin-top:-3px;">
					<div id="searchbtn1" class="l-button" onclick="judge();">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
