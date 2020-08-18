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
	
}); 

function toSignInSettingList(signInManageId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "签到设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/signInSetting/list.shtml?signInManageId=" + signInManageId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toCumulativeSignInSettingList(signInManageId,year,month) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: year+"-"+month+"累签设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/cumulativeSignInSetting/list.shtml?signInManageId=" + signInManageId,
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
	  
     url:"/marketing/memberSignInSetting/data.shtml?memberId=${memberId}",
     listGrid:{ columns: [
			            { display: '会员ID', name:'memberId'},
			            { display: '签到时间',render: function (rowdata, rowindex) {
		                	if(rowdata.signInTime){
		                		var signInTime = new Date(rowdata.signInTime);
				            	return signInTime.format("yyyy-MM-dd hh:mm:ss");
		                	}
						}},
			            { display: '是否补签',render: function (rowdata, rowindex) {
		                	if(rowdata.signInType == 1){
		                		return "否";
		                	}else{
		                		return "是";
		                	}
						}},
			            { display: '奖励类型',name:'rewardTypeDesc'},
			            { display: '奖励名称',name:'rewardName'},
		                { display: '本月累计签到',name:'signInCount'}
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td" style="display: inline-flex;">
			<div class="search-td-label" style="float:left;">签到时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="sign_in_time_begin" name="sign_in_time_begin" />
				<script type="text/javascript">
					$(function() {
						$("#sign_in_time_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="sign_in_time_end" name="sign_in_time_end" />
				<script type="text/javascript">
					$(function() {
						$("#sign_in_time_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div class="search-td">
			 <div class="search-td-label" >奖励类型：</div>
			 <div class="search-td-condition" >
				<select id="rewardType" name="rewardType">
					<option value="">请选择</option>
					<c:forEach var="list" items="${statusList}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			 <div class="search-td-label" >是否补签：</div>
			 <div class="search-td-condition" >
				<select id="signInType" name="signInType">
					<option value="">请选择</option>
					<option value="1">否</option>
					<option value="2">是</option>
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