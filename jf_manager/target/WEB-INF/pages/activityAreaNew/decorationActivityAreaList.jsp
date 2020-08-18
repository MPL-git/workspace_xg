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
	 
	 //查看会场信息
	 function showActivityArea(activityAreaId){
		$.ligerDialog.open({
			height: 800,
			width: 1200,
			title: "查看会场信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/addOrUpdateOrShowActivityAreaManager.shtml?statusPage=2&activityAreaId="+activityAreaId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //会场活动优惠设置
	 function activityAreaPreferentialManager(activityAreaId, statusPage){
		$.ligerDialog.open({
			height: 800,
			width: 1200,
			title: "会场活动优惠设置",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/activityAreaPreferentialManager.shtml?activityAreaId="+activityAreaId+"&statusPage="+statusPage,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //装修会场
	 function updateTempletType(activityAreaId, type, templetType ,isPreSell) {
		 $.ligerDialog.open({
			height: $(window).height()*0.95,
			width: $(window).width()*0.95,
			title: "选择装修模板",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/updateTempletTypeManager.shtml?activityAreaId="+activityAreaId+"&type="+type+"&templetType="+templetType+"&isPreSell="+isPreSell,
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
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'会场ID',name:'id', align:'center', isSort:false, width:80},
							{display:'会场类型',name:'typeDesc', align:'center', isSort:false, width:120},
							{display:'会场名称',name:'name', align:'center', isSort:false, width:180},
							{display:'是否预付',name:'isPreSell', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								  if(rowdata.isPreSell == "1") {
									  return "是";
								  }else{
									  return "否";
								  }
							}},
	                        {display:'利益点',name:'benefitPoint', align:'center', isSort:false, width:160},
	                        {display:'促销方式',name:'preferentialTypeDesc', align:'center', isSort:false, width:100},
	                        {display:'装修类型',name:'templetTypeDesc', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.templetType == '1') {
	                        		if(rowdata.type == '1') {
	                        			html.push("默认品牌模板");
	                        		}else {
	                        			html.push("默认单品模板");
	                        		}
	                        	}else {
	                        		html.push(rowdata.templetTypeDesc);
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
	                        {display:'查看/操作',name:'', align:'center', isSort:false, width:260, render:function(rowdata, rowindex) {
								if(rowdata.isPreSell == "1") {
                                    var isPreSell = "1";
								}else{
                                    var isPreSell = "0";
								}
	                        	var html = [];
								html.push("<a href=\"javascript:showActivityArea("+rowdata.id+");\">【基本信息】</a>");
								html.push("<a href=\"javascript:activityAreaPreferentialManager("+rowdata.id+", 1);\">【优惠与时间】</a>");
								html.push("<a href=\"javascript:updateTempletType("+rowdata.id+", "+rowdata.type+", "+rowdata.templetType+", "+isPreSell+");\">【装修会场】</a>");
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
	                        {display:'待审报名数',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
							    return rowdata.trialCooNum+"/"+rowdata.passCooNum;
	                        }},
	                        {display:'待审商品数',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	return rowdata.trialCooAuditNum+"/"+rowdata.passCooAuditNum;
	                        }}
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
<!-- 	<div id="toptoolbar"></div> -->
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
					<div class="search-td-label"  >类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="type" name="type" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="type" items="${typeList }">
								<c:if test="${type.statusValue == '1' or type.statusValue == '2' }">
									<option value="${type.statusValue }">
										${type.statusDesc }
									</option>
								</c:if>
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
					<div class="search-td-label"  >是否预付会场：</div>
					<div class="search-td-combobox-condition" >
						<select id="isPreSell" name="isPreSell" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">是</option>
							<option value="0">否</option>
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
