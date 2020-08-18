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
		$("#updatedateBegin").ligerDateEditor( {
			showTime : false,
			labelWidth : 150,
			width:150,
			labelAlign : 'left'
		});
		
		$("#updatedateEnd").ligerDateEditor( {			
			showTime : false,
			labelWidth : 150,
			width:150,
			labelAlign : 'left'
		})
		
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
	});
	
 	//延期
	function delay(mchtId) {
		$.ligerDialog.open({
		 	height: 300,
			width: 400,
			title: "选择延期时长",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtDelay/toDelay.shtml?mchtId="+mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 	
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
		
	function editMchtFinanceInfo(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width() - 200,
			title: "商家财务信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtFinanceInfoEdit.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
		
	
	//查看店铺
	function showMchtShop(mchtId, mchtType) {
		$.ligerDialog.open({
		 	height: $(window).height() - 50,
			width: $(window).width() - 100,
			title: "商品列表",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/showMchtShopManager.shtml?mchtId="+mchtId+"&mchtType="+mchtType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	//领取
	function addMchtPlatformContact(mchtId, contactType) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/mcht/addMchtPlatformContacts.shtml',
			data: {mchtId : mchtId, contactType : contactType},
			dataType: 'json',
			async: false,
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}
	
	var listConfig={
		url:"/mcht/mchtDelay/data.shtml",
      	listGrid:{ columns: [ 
						{ display: '开店日期', name: 'cooperate_begin_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.cooperate_begin_date != null && rowdata.cooperate_begin_date != ""){
   	   		                   var cooperate_begin_date = new Date(rowdata.cooperate_begin_date);
   	   		          	       return cooperate_begin_date.format("yyyy-MM-dd");	
   		                	}
		                }},
		                { display: '招商对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.zs_contact_name) {
		                		html.push(rowdata.zs_contact_name);
		                	}else {
		                		if(${staffID} == 1) {
				                 	html.push("<a href=\"javascript:addMchtPlatformContact(" + rowdata.id + ", 1);\">【领取】</a>");
			                	}
		                	}
		                	return html.join("");
		                }},
						{ display: '商家序号', name: 'mcht_code',width: 100 }, 
		                { display: '公司名称', width: 200, name:'company_name'},
		                { display: '店铺名称', width: 200, name:'shop_name'},
		                { display: '公司/经营信息', name: 'view', width: 120 ,render: function(rowdata, rowindex) {
		                	var html = [];
						    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">查看</a><br/>");
						    return html.join("");
 		                }},
		                { display: '财务信息', name: 'view', width: 120 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.id + ");\">查看</a><br/>");
						    return html.join("");
 		                }},
		                { display: '营业执照有效期', name: 'yearly_inspection_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.yearly_inspection_date){
   	   		                   var yearlyInspectionDate = new Date(rowdata.yearly_inspection_date);
   	   		                   return yearlyInspectionDate.format("yyyy-MM-dd");
    		                }else{
    		                   return "";
    		                }
 		                }},
		              	{ display: '延期状态', name: 'delay_status_desc', width:100 ,render: function(rowdata, rowindex) {
		                	if(rowdata.delay_status == 0){
		                		return "未延期";
		                	}else{
		                		return "延期"+rowdata.delay_days+"天";
		                	}
		                }},
		                { display: '法务对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.fw_contact_name) {
		                		html.push(rowdata.fw_contact_name);
		                	}else {
		                		if(${contactType} == 7) {
				                 	html.push("<a href=\"javascript:addMchtPlatformContact(" + rowdata.id + ", 7);\">【领取】</a>");
			                	}
		                	}
		                	return html.join("");
		                }},
		                { display: '操作', name: 'op',width:120, render: function(rowdata, rowindex) {
							if(rowdata.delay_status == 0 && rowdata.fw_staff_id == ${sessionScope.USER_SESSION.staffID}){
								return '<a href="javascript:;" onclick="delay('+rowdata.id+');">延期</a>';
							}else{
								return "";
							}
		              	}}
		         ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
      container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition"> 
						<input type="text" id="mcht_code" name="mcht_code">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">名称：</div>
					<div class="search-td-condition">
						<input type="text" id="mcht_name" name="mcht_name">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">延期状态：</div>
					<div class="search-td-condition">
						<select id="delay_status" name="delay_status" class="querysel">
							<option value="">请选择</option>
							<option value="0">未延期</option>
							<option value="1">已延期</option>
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
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
