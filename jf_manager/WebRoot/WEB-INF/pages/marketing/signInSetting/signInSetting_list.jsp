<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
$(function(){
	$("#maingrid").ligerGrid({
		  usePager:false
	});
}); 

function toEditSignInSetting(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "签到奖励设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/signInSetting/toEdit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function deleteViolatePunishStandard(id) {
	if(confirm("确定删除吗？")){
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/deleteViolatePunishStandard.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess(data.returnMsg);
				}else{
					commUtil.alertError("删除失败，请稍后重试");
				}
				setTimeout(function(){
					location.reload();
					frameElement.dialog.close();
				},1000);
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}

 var listConfig={
     url:"/marketing/signInSetting/data.shtml?signInManageId=${signInManageId}",
     listGrid:{ columns: [
			            { display: '时间', render: function (rowdata, rowindex) {
		                	if(rowdata.signInDate){
		                		var signInDate = new Date(rowdata.signInDate);
				            	return signInDate.format("yyyy-MM-dd");
		                	}
						}},
			            { display: '奖励类型',name:'rewardTypeDesc'},
			            { display: '奖励名称',name:'rewardName'},
			            { display: '状态', render: function (rowdata, rowindex) {
			            	var signInDate = new Date(rowdata.signInDate);
			            	var nowStr = new Date().format("yyyy-MM-dd");
			            	var nowDate = new Date(nowStr);
			            	if(signInDate<=nowDate){
			            		return "已启用";
			            	}else{
			            		return "未启用";
			            	}
						}},
			            { display: '最后操作人员',name:'staffName'},
		                { display: '最后修改时间',render: function (rowdata, rowindex) {
		                	if(rowdata.updateDate){
		                		var updateDate = new Date(rowdata.updateDate);
				            	return updateDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
						}},
		                { display: '操作', render: function (rowdata, rowindex) {
		                	var signInDate = new Date(rowdata.signInDate);
			            	var nowStr = new Date().format("yyyy-MM-dd");
			            	var nowDate = new Date(nowStr);
			            	if(signInDate<=nowDate){
								return '<a href="javascript:;" onclick="toEditSignInSetting('+rowdata.id+')">查看</a>';		                		
			            	}else{
								return '<a href="javascript:;" onclick="toEditSignInSetting('+rowdata.id+')">编辑</a>';		                		
			            	}
						}}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >奖励类型：</div>
			<div class="search-td-condition">
				<select name="rewardType">
					<option value="">请选择</option>
					<c:forEach items="${rewardTypeList}" var="rewardType">
						<option value="${rewardType.statusValue}">${rewardType.statusDesc}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>