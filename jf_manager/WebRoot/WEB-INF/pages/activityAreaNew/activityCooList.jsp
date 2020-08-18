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
		
		//品牌
		$("#productBrandName").ligerComboBox({
	     	 width: 135,
	         slide: false,
	         selectBoxWidth: 450,
	         selectBoxHeight: 300,
	         valueField: 'id',
	         textField: 'name',
	         valueFieldID:'productBrandId',
	         grid: getGridOptions(false),
	         condition:{ fields: [{ name:'name', label:'品牌名', width:90, type:'text' } ]}
	     });	
		
	 });
	 
	 function getGridOptions(checkbox){
	     var options = {
	         columns: [
				{display:'ID',name:'id', align:'center', isSort:false, width:100},
				{display:'品牌',name:'name', align:'center', isSort:false, width:100}
	         ], 
	         switchPageSizeApplyComboBox: false,
		     url: '${pageContext.request.contextPath}/activityNew/getProductBrandList.shtml',
	         pageSize: 10, 
	         checkbox: checkbox
	     };
	     return options;
	 }
	 
	 //领取
	 function getActivity(activityId, status) {
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/activityNew/getActivity.shtml',
			 data: {activityId : activityId, status : status},
			 dataType: 'json',
			 success: function(data) {
				 if(data == null || data.statusCode != 200){
				     commUtil.alertError(data.message);
				 }else {
					 $("#searchbtn").click();
				 }
			 },
			 error: function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
	 
	 //查看商品
	 function showActivityProduct(statusPage, activityId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看商品",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityProductNew/activityProductManager.shtml?statusPage="+statusPage+"&activityId="+activityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }

	 //查看活动、排期审核活动
	 function cooAuditActivity(activityId, statusPage, statusAudit) {
		var titleStr 
		if(statusPage == '1') {
			titleStr = "查看图片";
		}else if(statusPage == '2') {
			titleStr = "审核图片";
		} 
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: titleStr,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/showOrAuditActivity.shtml?activityId="+activityId+"&statusPage="+statusPage+"&statusAudit="+statusAudit,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
 	 var listConfig={
	      url:"/activityAreaNew/activityList.shtml?statusAudit=1&activityAreaId=${activityAreaId }",
 		  btnItems:[],
	      listGrid:{ columns: [
							{display:'特卖ID',name:'id', align:'center', isSort:false, width:80},
							{display:'活动名称',name:'name', align:'center', isSort:false, width:180},
	                        {display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180},
	                        {display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:100},
	                        {display:'待审商品',name:'sumCooAudit', align:'center', isSort:false, width:80},
	                        {display:'通过商品',name:'sumCooPass', align:'center', isSort:false, width:80},
	                        {display:'库存',name:'sumCooProductItem', align:'center', isSort:false, width:80},
	                        {display:'期望时间',name:'expectedStartTime', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	if(rowdata.expectedStartTime == null || rowdata.expectedStartTime == "" ) {
									return "";
								}else{
									var expectedStartTime = new Date(rowdata.expectedStartTime);
									return expectedStartTime.format("yyyy-MM-dd");
								}
	                        }},
	                        {display:'审核',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.operateAuditStatus == '2' && rowdata.cooAuditStatus != '3' && rowdata.cooAuditBy != null ) {
									html.push("<a href=\"javascript:cooAuditActivity(" + rowdata.id + ", '2', '2');\">【审核图片】</a>");
								}else {
									html.push("<a href=\"javascript:cooAuditActivity(" + rowdata.id + ", '1', '2');\">【查看图片】</a>");
								}
								html.push("<a href=\"javascript:showActivityProduct(2, " + rowdata.id + ");\">【查看商品】</a>");
							    return html.join("");
							    
							}},
	                        {display:'排期审核人',name:'cooAuditName', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.cooAuditBy != null ) {
									html.push(rowdata.cooAuditName);
								}else {
									html.push("<a href=\"javascript:getActivity(" + rowdata.id + ", '2');\">领取</a></br>");
								}
							    return html.join("");
							}},
							{display:'排期审核状态',name:'cooAuditStatusDesc', align:'center', isSort:false, width:100},
							{display:'活动状态',name:'activityStatusDesc', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status != '6') {
									html.push(rowdata.activityStatusDesc);
								}else if(rowdata.status == '6') {
									if(new Date(rowdata.preheatTime) > new Date()) {
										html.push("待开始");
									}else if(new Date(rowdata.preheatTime) <= new Date() && new Date(rowdata.activityBeginTime) > new Date()) {
										html.push("预热中");
									}else if(new Date(rowdata.activityBeginTime) <= new Date() && new Date(rowdata.activityEndTime) >= new Date()) {
										html.push("活动中");
									}else if(new Date(rowdata.activityEndTime) < new Date()) {
										html.push("已结束");
									}
								}
							    return html.join("");
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
					<div class="search-td-label"  >特卖ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityId" name="activityId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >特卖名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >排期审核人：</div>
					<div class="search-td-combobox-condition" >
						<select id="cooAuditBy" name="cooAuditBy" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sysStaffInfo" items="${sysStaffInfoList }">
								<option value="${sysStaffInfo.staffId }">
									${sysStaffInfo.staffName }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red;" >排期审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="cooAuditStatus" name="cooAuditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="cooAuditStatus" items="${cooAuditStatusList }">
								<option value="${cooAuditStatus.statusValue }">
									${cooAuditStatus.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="productType" items="${productTypeList }">
								<option value="${productType.id }">
									${productType.name }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productBrandName" name="productBrandName"  />
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
