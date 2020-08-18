<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 	$(function() {
	});
 	
	 function editMchtBaseInfo(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家基础资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//立即暂停
	function soonStop(id) {
		$.ligerDialog.confirm('确认后,商品牌的运营状态变更为：暂停', function (yes) { 
			if(yes) {
				$.ajax({
					type: 'GET',
					url: '${pageContext.request.contextPath}/mcht/mchtDelay/soonStop.shtml?id='+id,
					dataType: 'json',
					async: false,
					success: function(data) {
						if(data.returnCode != null && data.returnCode == '0000') {
							$("#searchbtn").click();
						}else {
							commUtil.alertError(data.returnMsg);
						}
					},
					error: function(e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}
		});
		
	}
	var listConfig={
		url:"/mcht/mchtDelay/newMchtDelayList.shtml",
      	listGrid:{ columns: [ 
						{ display: '开店日期', name: 'cooperate_begin_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.cooperate_begin_date != null && rowdata.cooperate_begin_date != ""){
   	   		                   var cooperate_begin_date = new Date(rowdata.cooperate_begin_date);
   	   		          	       return cooperate_begin_date.format("yyyy-MM-dd");	
   		                	}
		                }},
		                { display: '招商对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.zs_contact_name;
		                }},
						{ display: '商家序号', name: 'mcht_code',width: 100 }, 
		                { display: '公司名称', width: 200, name:'company_name'},
		                { display: '店铺名称', width: 200, name:'shop_name'},
		                { display: '公司/经营信息', name: 'view', width: 120 ,render: function(rowdata, rowindex) {
		                	var html = [];
						    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">查看</a><br/>");
						    return html.join("");
 		                }},
		                { display: '运营执照有效期', name: 'yearly_inspection_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.yearly_inspection_date){
   	   		                   var yearlyInspectionDate = new Date(rowdata.yearly_inspection_date);
   	   		                   return yearlyInspectionDate.format("yyyy-MM-dd");
    		                }else{
    		                   return "";
    		                }
 		                }},
 		               { display: '法人身份证有效期', name: 'corporation_idcard_date', width: 120 ,render: function(rowdata, rowindex) {
  		                	if(rowdata.corporation_idcard_date){
  	   		                   var corporationIdcardDate = new Date(rowdata.corporation_idcard_date);
  	   		                   return corporationIdcardDate.format("yyyy-MM-dd");
   		                }else{
   		                   return "";
   		                }
		                }},
		              	{ display: '合作状态', name: 'status', width:100 ,render: function(rowdata, rowindex) {
		              		if(rowdata.status == 0){
		                		return "未启用";
		                	}
		                	if(rowdata.status == 1){
		                		return "正常";
		                	}
		                	if(rowdata.status == 2){
		                		return "暂停";
		                	}
		                	if(rowdata.status == 3){
		                		return "关闭";
		                	}
		                }},
		                { display: '法务对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.fw_contact_name) {
		                		html.push(rowdata.fw_contact_name);
		                	}
		                	return html.join("");
		                }},
		                { display: '操作', name: '',width:120, render: function(rowdata, rowindex) {
							if((rowdata.yy_staff_id == ${sessionScope.USER_SESSION.roleId} || ${sessionScope.USER_SESSION.staffID eq 1}) &&  rowdata.status == 1){
								return '<a href="javascript:;" onclick="soonStop('+rowdata.id+');">立即暂停</a>';
							}else{
								return "";
							}
		              	}}
		         ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
      container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td" style="width:250px;">
					<div class="search-td-label">商家序号</div>
					<div class="search-td-condition"> 
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td" style="width:250px;">
					<div class="search-td-label">名称</div>
					<div class="search-td-condition">
						<input type="text" id="companyOrShop" name="companyOrShop">
					</div>
				</div>
				<div class="search-td" style="width:250px;">
					<div class="search-td-label">类目:</div>
					<div class="search-td-condition" >
					<select id="productTypeId" name="productTypeId">
						<option value="">请选择</option>
						<c:forEach items="${sysStaffProductTypeCustomList}" var="sysStaffProductTypeCustom">
							<option value="${sysStaffProductTypeCustom.productTypeId}">${sysStaffProductTypeCustom.staffName}</option>
						</c:forEach>
					</select>
		 			</div>
		 		</div>
		 		<div class="search-td" style="width:250px;">
						<div class="search-td-label">对接人：</div>
						<div class="search-td-condition">
							<select id="platContactStaffId" name="platContactStaffId">
								<c:if test="${isManagement == 1}">
									<option value="" selected="selected">全部商家</option>
								</c:if>
								<option value="${staffID}">我自己的商家</option>
								<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
									<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
								</c:forEach>
							</select>
						</div>
				</div>			
				<div class="search-td-search-btn" style="margin-top:-3px;">
						<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
