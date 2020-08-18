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
	
 	function toChangeApply(id){
 		$.ligerDialog.open({
			height: 400,
			width: 400,
			title: "开放变更申请类型",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/cooperationChangeApply/toChangeApply.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 	}
 	
	function editMchtProductBrand(mchtProductBrandId) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1000,
			title: "入驻品牌",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/editMchtProductBrand.shtml?view=0&mchtProductBrandId=" + mchtProductBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
	function toApplyClose(mchtId) {
		$.ligerDialog.open({
			height: 480,
			width: 640,
			title: "申请关店",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/close/toApplyClose.shtml?mchtId=" + mchtId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
 	//查看商家联系人
	function mchtContact(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width(),
			title: "商家联系人",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtContact.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //平台对接人
	 function mchtPlatformContact(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width(),
			title: "平台分配对接人",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtplatformcontact.shtml?mchtId=" + id,
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
		
	function editMchtBlacklist(id) {
		$.ligerDialog.open({
			height: 260,
			width: 350,
			title: "加入黑名单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/editMchtBlacklist.shtml?ids="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
		
	function cancelmchtBlacklist(id) {
		$.ligerDialog.open({
			height: 260,
			width: 350,
			title: "查看",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/cancelmchtBlacklist.shtml?ids="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
		
	function addMchtContract(mchtId) {
		if(${hasAuth}) {
			$.ligerDialog.open({
				height: 360,
				width: 350,
				title: "生成合同",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtContract/addMchtContract.shtml?mchtId="+mchtId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}else{
			alert("没有权限");
		}
	}
	
	//添加/修改商家标识 
	function updateGrade(mchtId, status) {
		$.ligerDialog.open({
		 	height: 300,
			width: 500,
			title: "商家等级设置",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/updateGradeManager.shtml?mchtId="+mchtId+"&status="+status,
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
	
	function allowMchtApplyClose(mchtId){
		 $.ligerDialog.confirm('是否确认开放商家关店申请入？', function (yes) {
 			if(yes){
 				$.ajax({
 					url : "${pageContext.request.contextPath}/mcht/close/allowMchtApplyClose.shtml",
 					type : 'POST',
 					dataType : 'json',
 					cache : false,
 					async : false,
 					data : {
 						mchtId:mchtId
 					},
 					timeout : 30000,
 					success : function(data) {
 						if ("0000" == data.returnCode) {
 							$.ligerDialog.success("操作成功!");
 							$("#searchbtn").click();
 						}else{
 							$.ligerDialog.error(data.returnMsg);
 						}
 						
 					},
 					error : function() {
 						$.ligerDialog.error('操作超时，请稍后再试！');
 					}
 				});
 			}
 		});
	  }
	
	//查看备注
	function editRemarks(mchtId, status, remarksType) {
		var title = "";
		if(remarksType == 'yy') {
			title = "运营备注";
		}
		if(remarksType == 'fw') {
			title = "法务备注";
		}
		$.ligerDialog.open({
			height: 400,
			width: 400,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/editRemarksManager.shtml?mchtId="+mchtId+"&status="+status+"&remarksType="+remarksType,
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
	
	//配合度、法务风险等级 
	function updateMchtOptimizeGrade(mchtId, gradeType) {
		var title = "";
		if(gradeType == '1') {
			title = "法务等级设置";
		}else if(gradeType == '2') {
			title = "配合度等级设置";
		}
		$.ligerDialog.open({
		 	height: 300,
			width: 500,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/updateMchtOptimizeGradeManager.shtml?mchtId="+mchtId+"&gradeType="+gradeType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//品牌
	function showMchtBand(mchtType, mchtCode, productBrandId ) {
		$.ligerDialog.open({
		 	height: $(window).height() - 50,
			width: $(window).width() - 50,
			title: "商家品牌",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/productIndex.shtml?mchtType="+mchtType+"&mchtCode="+mchtCode+"&productBrandId="+productBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	var listConfig={
		url:"/mcht/list.shtml",
//    	btnItems:[{ text: '商家录入', icon: 'add', dtype:'win',  click: itemclick, url:'/mcht/toAddMcht.shtml', seqId:"", width : 1100, height:500}],
      	listGrid:{ columns: [ 
						{ display: '商家序号', name: 'mcht_code',width: 100 }, 
						{ display: '开通日期', name: 'cooperate_begin_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.cooperate_begin_date != null && rowdata.cooperate_begin_date != ""){
   	   		                   var cooperate_begin_date = new Date(rowdata.cooperate_begin_date);
   	   		          	       return cooperate_begin_date.format("yyyy-MM-dd");	
   		                	}
		                }},
		                { display: '模式类型', name: '',width: 100,render:function(rowdata, rowindex){
		                	var html=[];
		                	if (rowdata.mcht_type_desc=='SPOP') {
								html.push("SPOP");
							}else {
								html.push("POP");
							}
		                	return html.join("");
		                }},
		                { display: '类目/商家名称', width: 200, render: function(rowdata, rowindex){
		                	var html = [];
					    	html.push(rowdata.product_type_name);
						    html.push("<br/>"+rowdata.company_name);
						    if(rowdata.shop_status == '1') {
								html.push("<br/><a href=\"https://m.xgbuy.cc/webApp/xgbuy/views/index.html?redirect_url=seller/index.html?mchtId="+ rowdata.id +"\" target=\"_blank\">"+ rowdata.shop_name +"</a>");
		                	}else {
		                		html.push("<br/><a href=\"javascript:showMchtShop("+ rowdata.id +", "+ rowdata.mcht_type +");\">"+ rowdata.shop_name +"</a>");
		                	}
		                    return html.join("");
		                }},
		                { display: '品牌', name: '', width: 180 ,render: function(rowdata, rowindex) {
		                	var mchtProductBrandName = rowdata.mcht_product_brand_name;
		                	var spopmchtProductBrandName = rowdata.spop_mcht_product_brand_name;
		                	var html = [];
		                	if (rowdata.mcht_type_desc=='SPOP') {
		                		if(spopmchtProductBrandName != null && spopmchtProductBrandName.length > 0){
			                		var spopmchtProductBrandNames = spopmchtProductBrandName.split(",");
									for (var i = 0; i < spopmchtProductBrandNames.length; i++) {
										var brandName = spopmchtProductBrandNames[i].substring(0, spopmchtProductBrandNames[i].lastIndexOf(" ")+1);
										var productBrandId = spopmchtProductBrandNames[i].substring(spopmchtProductBrandNames[i].lastIndexOf(" ")+1);
										if(i != 0) {
											html.push("<br/>");
										}
										if(productBrandId!=0){
											html.push("<a href=\"javascript:showMchtBand("+ rowdata.mcht_type +", '"+ rowdata.mcht_code +"', "+ productBrandId +");\">"+ brandName +"</a>");
										}else{//无品牌
											html.push("无品牌");
										}
									}
			                	}
							}else {
								if(mchtProductBrandName != null && mchtProductBrandName.length > 0){
			                		var mchtProductBrandNames = mchtProductBrandName.split(",");
									for (var i = 0; i < mchtProductBrandNames.length; i++) {
										var brandName = mchtProductBrandNames[i].substring(0, mchtProductBrandNames[i].lastIndexOf("）")+1);
										var productBrandId = mchtProductBrandNames[i].substring(mchtProductBrandNames[i].lastIndexOf("）")+1);
										if(i != 0) {
											html.push("<br/>");
										}
										if(productBrandId!=0){
											html.push("<a href=\"javascript:showMchtBand("+ rowdata.mcht_type +", '"+ rowdata.mcht_code +"', "+ productBrandId +");\">"+ brandName +"</a>");
										}else{//无品牌
											html.push("无品牌");
										}
									}
			                	}								
							}		                	
							return html.join("");
		                }},
		                { display: '合作状态', name: 'status_desc', width: 100, render: function(rowdata, rowindex) {
   		                	if(rowdata.status=="3"){
    	   		                var html = [];
    	   		                html.push(rowdata.status_desc);
    	   		                html.push('<br/>');
    	   		                if(rowdata.status_date){
    	   		                	var statusDate = new Date(rowdata.status_date);
	    	   		                html.push(statusDate.format("yyyy-MM-dd"));
    	   		                }
    	   		                return html.join("");
     		                }else{
     		                	return rowdata.status_desc;
     		                }
  		                }},
		                { display: '合同到期日期', name: 'status_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.agreement_end_date != null && rowdata.agreement_end_date != ""){
   	   		                   var agreementEndDate = new Date(rowdata.agreement_end_date);
   	   		                   var nowDate = new Date();
   	   		                   var nowDateStr = nowDate.format("yyyy-MM-dd");
   	   		                   var agreementEndDateStr = agreementEndDate.format("yyyy-MM-dd");
   	   		                   if(nowDateStr > agreementEndDateStr){
   	   		                	   return '<span style="color:red;">'+agreementEndDateStr+'</span>';
   	   		                   }else{
   	   		                	   return agreementEndDateStr;
   	   		                   }
    		                }
 		                }},
 		               { display: '查看详情', name: '', width: 100, align: 'center', render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">店铺主信息</a><br/>"); 
						    html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.id + ");\">财务</a><br/>"); 
						    html.push("<a href=\"javascript:mchtContact(" + rowdata.id + ");\">商家对接人</a><br/>"); 
						    html.push("<a href=\"javascript:mchtPlatformContact(" + rowdata.id + ");\">平台对接人</a>"); 
						    return html.join("");
		              	}}, 
		              	{ display: '商家等级', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push(rowdata.grade_desc);
		                	if(rowdata.grade_desc) {
			                 	html.push("<br/><a href=\"javascript:updateGrade(" + rowdata.id + ", 1);\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:updateGrade(" + rowdata.id + ", 2);\">【设置】</a>");
		                 	} 
		                	return html.join("");
		                }},
		              	{ display: '商城入口', name: 'shop_status_desc', width:100 },
		              	{ display: '活动入口', name: 'activity_auth_status_desc', width:100 },
		              	{ display: '配合度', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push(rowdata.degree_adaptability_desc);
		                	if(rowdata.yy_staff_id == ${sessionScope.USER_SESSION.staffID }) {
			                	if(rowdata.degree_adaptability_desc) {
				                 	html.push("<br/><a href=\"javascript:updateMchtOptimizeGrade(" + rowdata.id + ", 2);\">【修改】</a>");
			                 	}else {
				                 	html.push("<br/><a href=\"javascript:updateMchtOptimizeGrade(" + rowdata.id + ", 2);\">【设置】</a>");
			                 	} 
		                	}
		                	return html.join("");
		                }},
		                { display: '招商', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.zs_contact_name) {
		                		html.push(rowdata.zs_contact_name);
		                	}else {
		                		if(${sessionScope.contactType } == 1) {
				                 	html.push("<a href=\"javascript:addMchtPlatformContact(" + rowdata.id + ", 1);\">【领取】</a>");
			                	}
		                	}
		                	return html.join("");
		                }},
		                { display: '法务', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.fw_contact_name) {
		                		html.push(rowdata.fw_contact_name);
		                	}else {
		                		if(${sessionScope.contactType } == 7) {
				                 	html.push("<a href=\"javascript:addMchtPlatformContact(" + rowdata.id + ", 7);\">【领取】</a>");
			                	}
		                	}
		                	return html.join("");
		                }},
		                { display: '订单', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.dd_contact_name) {
		                		html.push(rowdata.dd_contact_name);
		                	}else {
		                		if(${sessionScope.contactType } == 3) {
				                 	html.push("<a href=\"javascript:addMchtPlatformContact(" + rowdata.id + ", 3);\">【领取】</a>");
			                	}
		                	}
		                	return html.join("");
		                }},
		                { display: '商管运营', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.yy_contact_name) {
		                		html.push(rowdata.yy_contact_name);
		                	}else {
		                		if(${sessionScope.contactType } == 2) {
				                 	html.push("<a href=\"javascript:addMchtPlatformContact(" + rowdata.id + ", 2);\">【领取】</a>");
			                	}
		                	}
		                	return html.join("");
		                }},
		                { display: '运营备注', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.yy_staff_id == ${sessionScope.USER_SESSION.staffID }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'yy');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'yy');\">【查看】</a>");
		                 	}
		                	var operateRemarks = rowdata.mcht_optimize_operate_remarks;
		                	if(operateRemarks != null && operateRemarks.length > 40) {
		                		html.push(operateRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(operateRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '法务备注', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.fw_staff_id == ${sessionScope.USER_SESSION.staffID }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'fw');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'fw');\">【查看】</a>");
		                 	}
		                	var companyInfAuditInnerRemarks = rowdata.company_inf_audit_inner_remarks;
		                	if(companyInfAuditInnerRemarks != null && companyInfAuditInnerRemarks.length > 40) {
		                		html.push(companyInfAuditInnerRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(companyInfAuditInnerRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '法务风险等级', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push(rowdata.audit_risk_desc);
		                	if(rowdata.fw_staff_id == ${sessionScope.USER_SESSION.staffID }) {
			                	if(rowdata.audit_risk_desc) {
				                 	html.push("<br/><a href=\"javascript:updateMchtOptimizeGrade(" + rowdata.id + ", 1);\">【修改】</a>");
			                 	}else {
				                 	html.push("<br/><a href=\"javascript:updateMchtOptimizeGrade(" + rowdata.id + ", 1);\">【设置】</a>");
			                 	} 
		                	}
		                	return html.join("");
		                }},
		                { display: '操作', name: 'fw_contact_name',width:120, render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.status==1 && rowdata.zs_staff_id==${sessionScope.USER_SESSION.staffID}){
								html.push("<a href=\"javascript:toChangeApply(" + rowdata.id + ");\">开放变更申请</a>&nbsp;&nbsp;<br>");
							}
							if(!rowdata.mcht_close_application_id){
								if(rowdata.allow_mcht_apply_close == 1){
									html.push("已开放关店入口<br>");
								}else{
									if ($("#role86").val()==86){
										if (rowdata.status==1 || rowdata.status==2){
											html.push("<a href=\"javascript:toApplyClose(" + rowdata.id + ");\">申请关店</a>&nbsp;&nbsp;<br>");
										}
										html.push("<a href=\"javascript:allowMchtApplyClose(" + rowdata.id + ");\">开放关店入口</a>&nbsp;&nbsp;<br>");
									}
								}
							}else{
								html.push("已申请关店<br>");
							}
			                if(rowdata.zs_staff_id==${sessionScope.USER_SESSION.staffID}||rowdata.yy_staff_id==${sessionScope.USER_SESSION.staffID}){
			                	if(rowdata.in_blacklist==0){
							    	html.push("<a href=\"javascript:editMchtBlacklist(" + rowdata.id + ");\">加入黑名单</a>&nbsp;&nbsp;<br>");																								                		
			                	}else{		
							    	html.push("<a href=\"javascript:cancelmchtBlacklist(" + rowdata.id + ");\">取消黑名单</a>&nbsp;&nbsp;<br>");															                		
			                	}	                	
			                }
			                if(!rowdata.mcht_contract_id && rowdata.status == "1"){
			                	html.push("<br><a href=\"javascript:addMchtContract(" + rowdata.id + ");\">生成合同</a>&nbsp;&nbsp;");
			                }
						    return html.join("");
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
		<input type="hidden" id="role86" value="${role86}">
		<input type="hidden" id="settledType" name="settledType" value="${settledType}">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">序号：</div>
					<div class="search-td-condition">
						<input type="text" id="mcht_code" name="mcht_code">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">模式：</div>
					<div class="search-td-condition">
						<select id="mcht_type" name="mcht_type" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${mcht_type}">
								<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">名称：</div>
					<div class="search-td-condition">
						<input type="text" id="mcht_name" name="mcht_name">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">合作状态：</div>
					<div class="search-td-condition">
						<select id="status" name="status" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${status}">
								<c:if test="${list.STATUS_VALUE!='0'}">
									<option <c:if test="${list.STATUS_VALUE=='1'}">selected = "selected"</c:if> value="${list.STATUS_VALUE}">${list.STATUS_DESC}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">到期年份：</div>
					<div class="search-td-condition">
						<select id="endYear" name="endYear" class="querysel">
							<option value="">请选择</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">到期月份：</div>
					<div class="search-td-condition">
						<select id="endMonth" name="endMonth" class="querysel">
							<option value="">请选择</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家等级：</div>
					<div class="search-td-condition">
						<select id="grade" name="grade" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="grade" items="${gradeList }">
								<option value="${grade.statusValue }">${grade.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input type="text" id="productBrandName" name="productBrandName">
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">主营类目：</div>
					<div class="search-td-condition">
						<select id="productTypeId" name="productTypeId" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${sysStaffProductTypeCustomList}">
								<option value="${list.productTypeId}">${list.staffName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">法务风险：</div>
					<div class="search-td-condition">
						<select id="audit_risk" name="audit_risk" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${audit_risk}">
								<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">配合度：</div>
					<div class="search-td-condition">
						<select id="degree_adaptability" name="degree_adaptability" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${degree_adaptability}">
								<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">法务备注：</div>
					<div class="search-td-condition">
						<select id="company_inf_audit_inner_status" name="company_inf_audit_inner_status" class="querysel">
							<option value="">请选择</option>
							<option value="1">为空</option>
							<option value="2">不为空</option>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID }" selected="selected" >我自己</option>
							<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="float:left;">开通时间：</div>
					<div class="l-panel-search-item">
						<input type="text" id="cooperateBeginDateBegin" name="cooperateBeginDateBegin" class="dateEditor"/>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="cooperateBeginDateEnd" name="cooperateBeginDateEnd" class="dateEditor"/>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" style="float:left;">关闭时间：</div>
					<div class="l-panel-search-item">
						<input type="text" id="closeDateBegin" name="closeDateBegin" class="dateEditor"/>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="closeDateEnd" name="closeDateEnd" class="dateEditor"/>
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
