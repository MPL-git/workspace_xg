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
 		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});

 		 /* periodDateFun();  */
 		
	});
 	
 	 /* //自定时间
 	function periodDateFun() {
 		if($("#periodDate").val() == '4') {
			$("#period").show();
		}else {
			$("#period").hide();
		}
 	}  */

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
 
	function requestCloseShop(mchtId) {
		$.ligerDialog.open({
			height: 480,
			width: 640,
			title: "申请关店",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtCloseApply/request.shtml?mchtId=" + mchtId,
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
	
	//查看备注
	function editRemarks(mchtId, status, remarksType) {
		var title = "";
		if(remarksType == 'yy') {
			title = "运营备注";
		}
		if(remarksType == 'fw') {
			title = "法务备注";
		}
		if(remarksType == 'bz') {
			title = "保证金优化";
		}
		if(remarksType == 'ml') {
			title = "毛利率优化";
		}
		if(remarksType == 'sp') {
			title = "商品优化";
		}
		if(remarksType == 'ff') {
			title = "服务优化";
		}
		if(remarksType == 'wl') {
			title = "物流配送";
		}
		if(remarksType == 'tg') {
			title = "是否站外推广";
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
		}else if(gradeType == '3') {
			title = "站内资源等级设置";
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
	
	//商城入口
	function updateShopStatus(mchtId, shopStatus) {
		var title = "确认开通商城吗？";
		if(shopStatus == "0") {
			title = "确认关闭商城吗？";
		}
		$.ligerDialog.confirm(title, function(yes) {
			 if(yes) {
				 $.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/mcht/updateShopStatus.shtml',
					data: {mchtId : mchtId, shopStatus : shopStatus},
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
		 });
	}
	
	//活动入口
	function updateActivityAuthStatus(mchtId, activityAuthStatus) {
		var title = "确认开通活动吗？";
		if(activityAuthStatus == "0") {
			title = "确认关闭活动吗？";
		}
		$.ligerDialog.confirm(title, function(yes) {
			 if(yes) {
				 $.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/mcht/updateActivityAuthStatus.shtml',
					data: {mchtId : mchtId, activityAuthStatus : activityAuthStatus},
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
		 });
	}
	
	 /* //自定义时间检查
	function checkSearchCondition() {
		if($("#periodDate").val() == '4') {
			if($("#periodDateBegin").val() == '' ){
		    	 commUtil.alertError("请选择销售周期开始日期！"); 
		    	 return false;
		     }
		     if($("#periodDateEnd").val() == ''){
		    	 commUtil.alertError("请选择销售周期结束日期！"); 
		    	 return false;
		     }
		}
		return true;
	}  */
	
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
	
	//导出
	function excel(){  
		$("#dataForm").attr("action","${pageContext.request.contextPath}/mcht/mchtOptimizeExport.shtml");
		$("#dataForm").submit();
	}
	
	var listConfig={
		url:"/mcht/mchtOptimizeList.shtml",
//    	btnItems:[{ text: '商家录入', icon: 'add', dtype:'win',  click: itemclick, url:'/mcht/toAddMcht.shtml', seqId:"", width : 1100, height:500}],
      	listGrid:{ columns: [ 
						{ display: '商家序号', name: 'mcht_code',width: 100 }, 
						{ display: '开通日期', name: 'cooperate_begin_date', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.cooperate_begin_date != null && rowdata.cooperate_begin_date != ""){
   	   		                   var cooperate_begin_date = new Date(rowdata.cooperate_begin_date);
   	   		          	       return cooperate_begin_date.format("yyyy-MM-dd");	
   		                	}
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
		                	var html = [];
		                	if(mchtProductBrandName != null && mchtProductBrandName.length > 0){
		                		var mchtProductBrandNames = mchtProductBrandName.split(",");
								for (var i = 0; i < mchtProductBrandNames.length; i++) {
									var brandName = mchtProductBrandNames[i].substring(0, mchtProductBrandNames[i].indexOf("）")+1);
									var productBrandId = mchtProductBrandNames[i].substring(mchtProductBrandNames[i].indexOf("）")+1);
									if(i != 0) {
										html.push("<br/>");
									}
									html.push("<a href=\"javascript:showMchtBand("+ rowdata.mcht_type +", '"+ rowdata.mcht_code +"', "+ productBrandId +");\">"+ brandName +"</a>");
								}
		                	}
							return html.join("");
		                }},
		                { display: '应缴保证金', name: 'total_amt', width: 100 },
		                { display: '已缴保证金', name: 'pay_amt', width: 100 },
		                /* { display: '使用的快递', name: 'express_names', width: 100 }, */
		                { display: '合作状态', name: 'status_desc', width: 100 , render: function(rowdata, rowindex) {
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
		              	{ display: '入驻资质是否完善', name: '', width: 100, align: 'center', render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.archive_status != '1') {
							    html.push("公司资质<span style='color:red;'>[" + rowdata.archive_status_desc + "]</span><br/>");
							}else {
								html.push("公司资质[" + rowdata.archive_status_desc + "]<br/>");
							}
							if(rowdata.contract_archive_status != '1') {
								html.push("最新合同<span style='color:red;'>[" + rowdata.contract_archive_status_desc + "]</span>");
							}else {
								html.push("最新合同[" + rowdata.contract_archive_status_desc + "]");
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
		                { display: '发布商品数量', name: 'mcht_product_count', width: 100 },
		                /*{ display: '近期特买数', name: 'activity_count', width: 100 },
		                { display: '近期销售额', name: 'pay_amount_sum', width: 100 },
		                { display: '近期订单量', name: 'sub_order_count', width: 100 },
		                { display: '近期退货率', name: 'customer_service_order_rate', width: 100 },
		                { display: '近期投诉率', name: 'appeal_order_rate', width: 100 },
		                { display: '近期介入率', name: 'intervention_order_rate', width: 100 }, */
		                
		                { display: '近期特买数', name: '', width: 100,render:function(rowdata, rowindex){
		                	if (rowdata.activity_count_7_days) {
		                		 return rowdata.activity_count_7_days;
							 }else if (rowdata.activity_count_30_days) {
								 return rowdata.activity_count_30_days;
							 }else if (rowdata.activity_count_90_days) {
								 return rowdata.activity_count_90_days;
							}
		                }},
		                { display: '近期销售额', name: '', width: 100,render:function(rowdata, rowindex){
		                	if (rowdata.pay_amount_sum_7_days) {
		                		return rowdata.pay_amount_sum_7_days;
							}else if (rowdata.pay_amount_sum_30_days) {
								return rowdata.pay_amount_sum_30_days;
							 }else if (rowdata.pay_amount_sum_90_days) {
								return rowdata.pay_amount_sum_90_days;
							}
		                }},
		                { display: '近期订单量', name: '', width: 100,render:function(rowdata, rowindex){
		                	if (rowdata.sub_order_count_7_days) {
		                		return rowdata.sub_order_count_7_days;
							}else if (rowdata.sub_order_count_30_days) {
								return rowdata.sub_order_count_30_days;
							 }else if (rowdata.sub_order_count_90_days) {
								return rowdata.sub_order_count_90_days;
							}
		                }},
		                { display: '近期退货率', name: '', width: 100,render:function(rowdata, rowindex){
		                	if (rowdata.return_rate_7_days) {
		                		return rowdata.return_rate_7_days;
							}else if (rowdata.return_rate_30_days) {
								return rowdata.return_rate_30_days;
							 }else if (rowdata.return_rate_90_days) {
								return rowdata.return_rate_90_days;
							}
		                }},
		                { display: '近期投诉率', name: '', width: 100,render:function(rowdata, rowindex){
		                	if (rowdata.appeal_rate_7_days) {
		                		return rowdata.appeal_rate_7_days;
							}else if (rowdata.appeal_rate_30_days) {
								return rowdata.appeal_rate_30_days;
							 }else if (rowdata.appeal_rate_90_days) {
								return rowdata.appeal_rate_90_days;
							}
		                }},
		                { display: '近期介入率', name: '', width: 100,render:function(rowdata, rowindex){
		                	if (rowdata.intervention_rate_7_days) {
		                		return rowdata.intervention_rate_7_days;
							}else if (rowdata.intervention_rate_30_days) {
								return rowdata.intervention_rate_30_days;
							 }else if (rowdata.intervention_rate_90_days) {
								return rowdata.intervention_rate_90_days;
							}
		                }},
		                { display: '好评率', name: 'product_applause_rate', width: 100 },
		                { display: '评价平均分', name: 'product_score_avg', width: 100 },
		              	{ display: '商家等级', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push(rowdata.grade_desc);
		                	if(rowdata.grade_desc) {
			                 	html.push("<br/><a href=\"javascript:updateGrade(" + rowdata.id + ", 1);\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:updateGrade(" + rowdata.id + ", 2);\">【设置】</a>");
		                 	} 
		                	return html.join("");roleId
		                }},
		              	{ display: '商城入口', name: '', width:100, render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push(rowdata.shop_status_desc);
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                	if(rowdata.shop_status == '0') {
				                 	html.push("<br/><a href=\"javascript:updateShopStatus(" + rowdata.id + ", 1);\">【开通】</a>");
			                 	}else {
				                 	html.push("<br/><a href=\"javascript:updateShopStatus(" + rowdata.id + ", 0);\">【关闭】</a>");
			                 	} 
		                	}
		                	return html.join("");
		                }},
		              	{ display: '活动入口', name: '', width:100, render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push(rowdata.activity_auth_status_desc);
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                	if(rowdata.activity_auth_status == '0') {
				                 	html.push("<br/><a href=\"javascript:updateActivityAuthStatus(" + rowdata.id + ", 1);\">【开通】</a>");
			                 	}else {
				                 	html.push("<br/><a href=\"javascript:updateActivityAuthStatus(" + rowdata.id + ", 0);\">【关闭】</a>");
			                 	} 
		                	}
		                	return html.join("");
		                }},
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
		                { display: '保证金优化', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'bz');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'bz');\">【查看】</a>");
		                 	}
		                	var mchtOptimizeDepositRemarks = rowdata.mcht_optimize_deposit_remarks;
		                	if(mchtOptimizeDepositRemarks != null && mchtOptimizeDepositRemarks.length > 40) {
		                		html.push(mchtOptimizeDepositRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(mchtOptimizeDepositRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '毛利率优化', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'ml');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'ml');\">【查看】</a>");
		                 	}
		                	var mchtOptimizeGrossProfitRateRemarks = rowdata.mcht_optimize_gross_profit_rate_remarks;
		                	if(mchtOptimizeGrossProfitRateRemarks != null && mchtOptimizeGrossProfitRateRemarks.length > 40) {
		                		html.push(mchtOptimizeGrossProfitRateRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(mchtOptimizeGrossProfitRateRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '商品优化', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'sp');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'sp');\">【查看】</a>");
		                 	}
		                	var mchtOptimizeProductRemarks = rowdata.mcht_optimize_product_remarks;
		                	if(mchtOptimizeProductRemarks != null && mchtOptimizeProductRemarks.length > 40) {
		                		html.push(mchtOptimizeProductRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(mchtOptimizeProductRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '服务优化', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'ff');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'ff');\">【查看】</a>");
		                 	}
		                	var mchtOptimizeServiceRemarks = rowdata.mcht_optimize_service_remarks;
		                	if(mchtOptimizeServiceRemarks != null && mchtOptimizeServiceRemarks.length > 40) {
		                		html.push(mchtOptimizeServiceRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(mchtOptimizeServiceRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '物流配送', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'wl');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'wl');\">【查看】</a>");
		                 	}
		                	var mchtOptimizeWuliuRemarks = rowdata.mcht_optimize_wuliu_remarks;
		                	if(mchtOptimizeWuliuRemarks != null && mchtOptimizeWuliuRemarks.length > 40) {
		                		html.push(mchtOptimizeWuliuRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(mchtOptimizeWuliuRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '站内资源等级', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push(rowdata.resource_grade_desc);
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                	if(rowdata.resource_grade_desc) {
				                 	html.push("<br/><a href=\"javascript:updateMchtOptimizeGrade(" + rowdata.id + ", 3);\">【修改】</a>");
			                 	}else {
				                 	html.push("<br/><a href=\"javascript:updateMchtOptimizeGrade(" + rowdata.id + ", 3);\">【设置】</a>");
			                 	} 
		                	}
		                	return html.join("");
		                }},
		                { display: '是否站外推广', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if('77' == ${sessionScope.sysStaffRole }) {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 1, 'tg');\">【修改】</a>");
		                 	}else {
			                 	html.push("<br/><a href=\"javascript:editRemarks(" + rowdata.id + ", 2, 'tg');\">【查看】</a>");
		                 	}
		                	var mchtOptimizeSpreadRemarks = rowdata.mcht_optimize_spread_remarks;
		                	if(mchtOptimizeSpreadRemarks != null && mchtOptimizeSpreadRemarks.length > 40) {
		                		html.push(mchtOptimizeSpreadRemarks.substring(0, 40)+"...")
		                	}else {
		                		html.push(mchtOptimizeSpreadRemarks)
		                	}
		                	return html.join("");
		                }},
		                { display: '运营专员备注', name: '', align: 'left', width:150 ,render: function(rowdata, rowindex) {
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
		                { display: '操作', name: 'fw_contact_name',width: 100, render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.zs_staff_id == ${sessionScope.USER_SESSION.staffID}){						
						       html.push("<a href=\"javascript:requestCloseShop(" + rowdata.id + ");\">申请关店</a><br/>");
							}
			                if(rowdata.zs_staff_id == ${sessionScope.USER_SESSION.staffID}||rowdata.yy_staff_id==${sessionScope.USER_SESSION.staffID}){
			                	if(rowdata.in_blacklist == 0){
							    	html.push("<a href=\"javascript:editMchtBlacklist(" + rowdata.id + ");\">加入黑名单</a><br/>");																								                		
			                	}else{		
							    	html.push("<a href=\"javascript:cancelmchtBlacklist(" + rowdata.id + ");\">取消黑名单</a><br/>");															                		
			                	}	                	
			                }
			                if(!rowdata.mcht_contract_id && rowdata.status == "1"){
			                	html.push("<br><a href=\"javascript:addMchtContract(" + rowdata.id + ");\">生成合同</a>");
			                }
						    return html.join("");
		              	}}
		         ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true, //不设置默认为 true
                /*  beforeSearch: checkSearchCondition */
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
		<input type="hidden" name="settledType" value="${settledType}">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">序号：</div>
					<div class="search-td-condition">
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">模式：</div>
					<div class="search-td-condition">
						<select id="mchtType" name="mchtType" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="mchtType" items="${mchtTypeList }">
								<option value="${mchtType.statusValue }">${mchtType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">名称：</div>
					<div class="search-td-condition">
						<input type="text" id="mchtName" name="mchtName">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">站内资源等级：</div>
					<div class="search-td-condition">
						<select id="resourceGrade" name="resourceGrade" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="resourceGrade" items="${resourceGradeList }">
								<option value="${resourceGrade.statusValue }">${resourceGrade.statusDesc }</option>
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
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input type="text" id="productBrandName" name="productBrandName">
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
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">主营类目：</div>
					<div class="search-td-condition">
						<select id="productTypeId" name="productTypeId" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${sysStaffProductTypeCustomList}">
								<option value="${list.productTypeId }">${list.staffName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
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
					<div class="search-td-label">法务风险：</div>
					<div class="search-td-condition">
						<select id="auditRisk" name="auditRisk" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="auditRisk" items="${auditRiskList }">
								<option value="${auditRisk.statusValue }">${auditRisk.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">合作状态：</div>
					<div class="search-td-condition">
						<select id="mchtStatus" name="mchtStatus" class="querysel">
							<option value="1" selected="selected">正常</option>
							<option value="2">暂停</option>
							<option value="3">关闭</option>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">配合度：</div>
					<div class="search-td-condition">
						<select id="degreeAdaptability" name="degreeAdaptability" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="degreeAdaptability" items="${degreeAdaptabilityList }">
								<option value="${degreeAdaptability.statusValue }">${degreeAdaptability.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<!-- <div class="search-td">
					<div class="search-td-label">查看销售周期：</div>
					<div class="search-td-condition">
						<select id="periodDate" name="periodDate" class="querysel" onchange="periodDateFun();">
							<option value="1">7天</option>
							<option value="2">30天</option>
							<option value="3">90天</option>
							<option value="4">自定时间</option>
						</select>
					</div>
				</div>
				
				<div class="search-td" id="period" style="width: 40%;">
					<div class="l-panel-search-item" >
						<input type="text" id="periodDateBegin" name="periodDateBegin" class="dateEditor" style="width: 150px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="periodDateEnd" name="periodDateEnd" class="dateEditor" style="width: 150px;" />
					</div>
				</div> -->
				<div class="search-td">
					<div class="search-td-label">查看销售周期：</div>
					<div class="search-td-condition">
						<select id="periodDate" name="periodDate" class="querysel">
							<option value="1">7天</option>
							<option value="2">30天</option>
							<option value="3">90天</option>
						</select>
					</div>
				</div>
			</div>
			
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" style="float:left;">开通时间：</div>
					<div class="l-panel-search-item">
						<input type="text" id="cooperateBeginDateBegin" name="cooperateBeginDateBegin" class="dateEditor" style="width: 150px;"/>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="cooperateBeginDateEnd" name="cooperateBeginDateEnd" class="dateEditor" style="width: 150px;"/>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label" style="float:left;">关闭时间：</div>
					<div class="l-panel-search-item">
						<input type="text" id="closeDateBegin" name="closeDateBegin" class="dateEditor" style="width: 150px;"/>
					</div>
					<div class="l-panel-search-item">&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="closeDateEnd" name="closeDateEnd" class="dateEditor" style="width: 150px;"/>
					</div>
				</div>
				
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn" >搜索</div>
					<c:if test="${not empty role87}">
						<div style="padding-left: 10px;">
							<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();">
						</div>
					</c:if>
				</div>
			</div>
			
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
