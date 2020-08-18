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
	 
	 //会场活动优惠设置
	 function activityAreaPreferentialManager(activityAreaId, statusPage){
		$.ligerDialog.open({
			height: 800,
			width: 1200,
			title: "会场活动优惠设置",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/promotionActivityAreaPreferentialManager.shtml?activityAreaId="+activityAreaId+"&statusPage="+statusPage,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //查看会场信息
	 function showActivityArea(activityAreaId){
		$.ligerDialog.open({
			height: 800,
			width: 1200,
			title: "查看会场信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAreaNew/addOrUpdateOrShowActivityAreaManager.shtml?statusPage=2&activityAreaType=3&activityAreaId="+activityAreaId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	//推广查看商品
	function showActivityProduct(statusPage, activityAreaId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title: "推广查看商品",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityProductNew/activityProductManager.shtml?statusPage="+statusPage+"&activityAreaId="+activityAreaId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	
	//查看游离码
	function showCouponExchangeCodeList(statusPage, couponId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title: "查看游离码",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/couponExchangeCode/couponExchangeCodeManager.shtml?statusPage="+statusPage+"&couponId="+couponId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	
	//生成码
	function updateCouponExchangeCode(statusPage, couponId, notNum) {
		$.ligerDialog.open({
			height: $(window).height() - 300,
			width: $(window).width() - 700,
			title: "生成码",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/couponExchangeCode/couponExchangeCodeManager.shtml?statusPage="+statusPage+"&couponId="+couponId+"&notNum="+notNum,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
 	 var listConfig={
	      url:"/coupon/getCouponList.shtml?statusPage=3",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'会场ID',name:'activityAreaId', align:'center', isSort:false, width:80},
							{display:'会场名称',name:'activityAreaName', align:'center', isSort:false, width:180},
	                        {display:'利益点',name:'activityAreaBenefitPoint', align:'center', isSort:false, width:160},
	                        {display:'优惠券',name:'id', align:'center', isSort:false, width:80},
	                        {display:'优惠内容',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return rowdata.money+"元券（满"+rowdata.minimum+"用）";
	                        }},
	                        {display:'活动时间',name:'activityTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	var html = [];
								if(rowdata.activityAreaABeginTime != null && rowdata.activityAreaABeginTime != "") {
									html.push("起：");
		                        	var activityAreaABeginTime = new Date(rowdata.activityAreaABeginTime);
									if(new Date(rowdata.activityAreaABeginTime) <= new Date) {
										html.push("<span style='color: red;'>"+activityAreaABeginTime.format("yyyy-MM-dd hh:mm:ss")+"</span></br>");
									}else {
										html.push(activityAreaABeginTime.format("yyyy-MM-dd hh:mm:ss"));
									}
								}
								if(rowdata.activityAreaEndTime != null && rowdata.activityAreaEndTime != "") {
									html.push("止：");
									var activityAreaEndTime = new Date(rowdata.activityAreaEndTime);
									if(new Date(rowdata.activityAreaEndTime) <= new Date) {
										html.push("<span style='color: red;'>"+activityAreaEndTime.format("yyyy-MM-dd hh:mm:ss")+"</span>");
									}else {
										html.push(activityAreaEndTime.format("yyyy-MM-dd hh:mm:ss"));
									}
								}
							    return html.join("");
	                        }},
	                        {display:'查看',name:'', align:'center', isSort:false, width:250, render:function(rowdata, rowindex) {
	                        	var html = [];
								html.push("<a href=\"javascript:showActivityArea("+rowdata.activityAreaId+");\">【基本信息】</a>");
								html.push("<a href=\"javascript:activityAreaPreferentialManager("+rowdata.activityAreaId+", 1);\">【优惠与时间】</a>");
								html.push("<a href=\"javascript:showActivityProduct(5, "+rowdata.activityAreaId+");\">【商品管理】</a>");
							    return html.join("");
	                        }},
	                        {display:'发行量',name:'grantQuantity', align:'center', isSort:false, width:80},
	                        {display:'已生成',name:'sumCouponExchange', align:'center', isSort:false, width:80},
	                        {display:'未生成',name:'', align:'center', width:80, isSort:false, render:function(rowdata, rowindex) {
	                        	return Number(rowdata.grantQuantity) - Number(rowdata.sumCouponExchange);
	                        }},
	                        {display:'操作',name:'', align:'center', isSort:false, width:250, render:function(rowdata, rowindex) {
	                        	var html = [];
								html.push("<a href=\"javascript:showCouponExchangeCodeList(1, "+rowdata.id+");\">【查看】</a>");
								if((Number(rowdata.grantQuantity) - Number(rowdata.sumCouponExchange)) > 0) {
									if(rowdata.activityAreaEndTime != null && rowdata.activityAreaEndTime != "") {
										if(new Date(rowdata.activityAreaEndTime) > new Date) {
											html.push("<a href=\"javascript:updateCouponExchangeCode(3, "+rowdata.id+", "+(Number(rowdata.grantQuantity) - Number(rowdata.sumCouponExchange))+");\">【生成码】</a>");
										}
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
					<div class="search-td-label" >会场ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityAreaId" name="activityAreaId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会场名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityAreaname" name="activityAreaname" >
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
