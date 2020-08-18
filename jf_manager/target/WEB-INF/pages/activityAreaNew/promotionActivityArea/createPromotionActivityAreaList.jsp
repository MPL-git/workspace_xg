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
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
	 });
	 
	 //修改会场启动状态
	 function updateActivityAreaStatus(activityAreaId) {
		 if(activityAreaId == '-1') {
			 commUtil.alertError("对不起，活动时间未设置，不可启用！");
		 }else {
			 $.ligerDialog.confirm(
				'您确认要启用会场？启用之后，商家就可以看到会场、报名活动。但启用之后部分内容不可修改。', 
				function (yes) { 
					if(yes) {
						$.ajax({
							type: 'post',
							url: '${pageContext.request.contextPath}/activityAreaNew/updateActivityAreaStatus.shtml',
							data: {activityAreaId : activityAreaId, status : '1'},
							dataType: 'json',
							success: function(data) {
								if(data.statusCode != null && data.statusCode == 200) {
									$("#searchbtn").click();
								}else {
									commUtil.alertError(data.message);
								}
							},
							error: function(e) {
								commUtil.alertError("系统异常请稍后再试");
							}
						});
					}
			 	}
			 );
		 }
		 $(".l-dialog-content").css("margin-right", "10px");
	 }
	 
	 //修改会场信息
	 function updateActivityArea(activityAreaId){
		$.ligerDialog.open({
			height: 800,
			width: 1200,
			title: "修改会场信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/addOrUpdateOrShowActivityAreaManager.shtml?statusPage=1&activityAreaType=3&activityAreaId="+activityAreaId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //会场活动优惠设置
	 function activityAreaPreferentialManager(activityAreaId){
		$.ligerDialog.open({
			height: 800,
			width: 1200,
			title: "会场活动优惠设置",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/promotionActivityAreaPreferentialManager.shtml?activityAreaId="+activityAreaId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //游离码管理
	 function showCoupon(activityAreaId) {
		 $.ligerDialog.open({
			height: 500,
			width: 1000,
			title: "游离码管理",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/coupon/couponManager.shtml?activityAreaId="+activityAreaId+"&statusPage=1",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
 	 var listConfig={
	      url:"/activityAreaNew/getActivityAreaList.shtml?statusPage=${statusPage}",
	      btnItems:[{ text: '创建会场', icon: 'add', dtype:'win',  click: itemclick, url:'/activityAreaNew/addOrUpdateOrShowActivityAreaManager.shtml?statusPage=1&activityAreaType=3', seqId:"", width : 1200, height:800}],
	      listGrid:{ columns: [
							{display:'会场ID',name:'id', align:'center', isSort:false, width:80},
							{display:'会场名称',name:'name', align:'center', isSort:false, width:180},
	                        {display:'利益点',name:'benefitPoint', align:'center', isSort:false, width:160},
	                        {display:'促销方式',name:'preferentialTypeDesc', align:'center', isSort:false, width:100},
	                        {display:'报名时间',name:'enrollTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	var html = [];
								if(rowdata.enrollBeginTime != null && rowdata.enrollBeginTime != "") {
									html.push("起：");
									var enrollBeginTime = new Date(rowdata.enrollBeginTime);
									if(new Date(rowdata.enrollBeginTime) <= new Date) {
										html.push("<span style='color: red;'>"+enrollBeginTime.format("yyyy-MM-dd hh:mm:ss")+"</span></br>");
									}else {
										html.push(enrollBeginTime.format("yyyy-MM-dd hh:mm:ss"));
									}
								}
								if(rowdata.enrollEndTime != null && rowdata.enrollEndTime != "") {
									html.push("止：");
									var enrollEndTime = new Date(rowdata.enrollEndTime);
									if(new Date(rowdata.enrollEndTime) <= new Date) {
										html.push("<span style='color: red;'>"+enrollEndTime.format("yyyy-MM-dd hh:mm:ss")+"</span>");
									}else {
										html.push(enrollEndTime.format("yyyy-MM-dd hh:mm:ss"));
									}
								}
							    return html.join("");
	                        }},
	                        {display:'活动时间',name:'activityTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	var html = [];
								if(rowdata.activityBeginTime != null && rowdata.activityBeginTime != "") {
									html.push("起：");
		                        	var activityBeginTime = new Date(rowdata.activityBeginTime);
									if(new Date(rowdata.activityBeginTime) <= new Date) {
										html.push("<span style='color: red;'>"+activityBeginTime.format("yyyy-MM-dd hh:mm:ss")+"</span></br>");
									}else {
										html.push(activityBeginTime.format("yyyy-MM-dd hh:mm:ss"));
									}
								}
								if(rowdata.activityEndTime != null && rowdata.activityEndTime != "") {
									html.push("止：");
									var activityEndTime = new Date(rowdata.activityEndTime);
									if(new Date(rowdata.activityEndTime) <= new Date) {
										html.push("<span style='color: red;'>"+activityEndTime.format("yyyy-MM-dd hh:mm:ss")+"</span>");
									}else {
										html.push(activityEndTime.format("yyyy-MM-dd hh:mm:ss"));
									}
								}
							    return html.join("");
	                        }},
	                        {display:'修改操作',name:'', align:'center', isSort:false, width:250, render:function(rowdata, rowindex) {
	                        	var html = [];
								html.push("<a href=\"javascript:updateActivityArea("+rowdata.id+");\">【基本信息】</a>");
								html.push("<a href=\"javascript:activityAreaPreferentialManager("+rowdata.id+");\">【优惠与时间】</a>");
								if(rowdata.status == '1') {
									html.push("<a href=\"javascript:showCoupon("+rowdata.id+");\">【游离码】</a>");
								}
							    return html.join("");
	                        }},
	                        {display:'启用状态',name:'status', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.status == '1') {
									html.push(rowdata.statusDesc);
	                        	}else if(rowdata.activityBeginTime != null && rowdata.activityEndTime != null){
									html.push("<a href=\"javascript:updateActivityAreaStatus("+rowdata.id+");\">"+rowdata.statusDesc+"</a>");
	                        	}else {
	                        		html.push("<a href=\"javascript:updateActivityAreaStatus('-1');\">"+rowdata.statusDesc+"</a>");
	                        	}
							    return html.join("");
	                        }},
	                        {display:'推送商家',name:'pushMchtTypeDese', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	if(rowdata.pushMchtTypeDese != '') {
									return rowdata.pushMchtTypeDese;
	                        	}else if(rowdata.mchtIdGroup != '') {
									return "指定商家";
	                        	}
	                        }},
	                        {display:'报名数限额',name:'limitMchtNum', align:'center', isSort:false, width:80},
	                        {display:'通过商品数',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	return rowdata.adoptActivityProductNum+"/"+rowdata.signActivityProductNum;
	                        }},
	                        {display:'创建人',name:'createStaffName', align:'center', isSort:false, width:80}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >会场ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityAreaId" name="activityAreaId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会场名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >促销方式：</div>
					<div class="search-td-combobox-condition" >
						<select id="preferentialType" name="preferentialType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="preferentialType" items="${preferentialTypeList }">
								<c:if test="${preferentialType.statusValue != '0' }">
									<option value="${preferentialType.statusValue }">
										${preferentialType.statusDesc }
									</option>
								</c:if>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会场创建人：</div>
					<div class="search-td-combobox-condition" >
						<select id="createBy" name="createBy" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="createStaff" items="${createStaffList }">
								<option value="${createStaff.create_by }">
									${createStaff.create_staff_name }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >活动开始日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="activityBeginTime" name="activityBeginTime" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动结束日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="activityEndTime" name="activityEndTime" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >启用状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="status" items="${statusList }">
								<option value="${status.statusValue }">
									${status.statusDesc }
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
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
