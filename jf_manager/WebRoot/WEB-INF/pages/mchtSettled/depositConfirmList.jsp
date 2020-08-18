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
	 
	 $(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left'
	 });
	 
	 $("#export").bind('click',function(){
		var date_begin = $("#date_begin").val();
		var date_end = $("#date_end").val();
		var status = $("#status").val();
		location.href="${pageContext.request.contextPath}/mchtSettled/director/export.shtml?date_begin="+date_begin+"&date_end="+date_end+"&status="+status;
	 });
	 
 }); 
 //批量或单个分配
function allot(ids) {
	$.ligerDialog.open({
 		height: 500,
		width: 300, 
		title: "招商对接人分配",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/allot/list.shtml?ids=" + ids,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
 
function confirmDeposit(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.6,
		width: $(window).width()*0.4,
		title: "确认保证金额度",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/confirmDeposit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
	  
     url:"/mchtSettled/depositConfirm/data.shtml",
     listGrid:{ columns: [
			            { display: '入驻申请时间', width: 100, render: function(rowdata, rowindex) {
			            	var createDate=new Date(rowdata.createDate);
			            	return createDate.format("yyyy-MM-dd");
			         	}},
			            { display: '信息确认时间', width: 120, render: function(rowdata, rowindex) {
			            	var infoConfirmDate=new Date(rowdata.infoConfirmDate);
			            	return infoConfirmDate.format("yyyy-MM-dd hh:mm");
			         	}},
			            { display: '来源', width: 80,render: function(rowdata, rowindex) {
			            	if(rowdata.clientType == "1"){
			            		return "PC端";
			            	}else if(rowdata.clientType == "2"){
			            		return "招商H5";
			            	}else{
			            		return "PC端";
			            	}
			         	}},
			         	{ display: '入驻类型', width: 80,render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.settledType == "1"){
			            		html.push("企业公司");
			            	}else if(rowdata.settledType == "2"){
			            		html.push("个体商户");
			            	}
			            	return html.join("");
			         	}},
		                { display: '公司', width: 180, name: 'companyName' },
			            { display: '注册资本', width: 100, render: function(rowdata, rowindex) {
			            	if (rowdata.regCapital==null){
			            		return "/";
			            	}else{
			            		return rowdata.regCapital+"万"+rowdata.regFeeTypeDesc;
			            	}
			         	}},
		                { display: '主营类目', width: 100, name: 'productTypeMain'},
		                { display: '品牌', width: 100, name: 'productBrandMain'},
		                { display: '联系人(职务)',width: 100,render:function(rowdata, rowindex){
		                   var html=[];
		                   if (rowdata.contactName!=null) {
							   html.push(rowdata.contactName);
							  if (rowdata.contactJob!=null) {
							   html.push("(");
							   html.push(rowdata.contactJob);
							   html.push(")");					   								
							}
						}
		                   return html.join("");
		                }}, 
		                { display: '联系电话', width: 100, name: 'phone'},
 		                { display: '信息确认人', width: 100, name: 'infoConfirmByName'},
 		               	{ display: '主管确认状态', width: 100, render: function(rowdata, rowindex) {
 		               		if(rowdata.depositConfirmByName){
				            	return rowdata.depositConfirmByName+'<br>'+rowdata.depositConfirmStatusDesc;
 		               		}else{
				            	return rowdata.depositConfirmStatusDesc;
 		               		}
			         	}},
 		                { display: '保证金缴费方式', width: 180, name: 'depositTypeDesc'}, 
 		               	{ display: '保证金类型', width: 100, render: function(rowdata, rowindex) {
		               		var html = [];
		               		if(rowdata.selectContractDeposit == '1') {
		               			html.push("店铺保证金");
		               		}
		               		if(rowdata.selectBrandDeposit == '1') {
		               			html.push("品牌保证金");
		               		}
		               		return html.join("<br/>");
			         	}},
 		               	{ display: '店铺保证金金额', width: 180, name: 'contractDeposit'},
 		                { display: '技术服务费率预定', width: 180, render: function(rowdata, rowindex) {
		               		var html = [];
		               		if(rowdata.feeRate != null && rowdata.feeRate != '') {
		               			html.push(rowdata.feeRate*100+"%");
		               		}
		               		return html.join("");
			         	}},
		                { display: '商品情况', width: 180, name: 'productSituation'},
		                { display: '商家其他渠道链接', width: 180, name: 'otherChannelLink'},
		                { display: '物流配送', width: 180, name: 'logistics'},
		                { display: '团队情况', width: 180, name: 'teamSituation'},
		                { display: '公司概况', width: 180, name: 'companySituation'},
		                { display: '操作', width: 100, name: "OPER", align: "center", render: function(rowdata, rowindex) {
		                	var role107 = ${role107};
		                	if(role107){
		                		return "<a href=\"javascript:confirmDeposit(" + rowdata.id + ");\">预设保证金</a>";
		                	}else{
		                		if(rowdata.status == "4" && (rowdata.depositConfirmStatus == "0" || rowdata.depositConfirmStatus == "2") && rowdata.roleId=='78'){//确认合作
							    	return "<a href=\"javascript:confirmDeposit(" + rowdata.id + ");\">预设保证金</a>";
	 						    }else{ //黑名单
	 						    	return "";
	 						    }
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
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">入驻申请日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="date_begin" name="date_begin" class="dateEditor" value="" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="date_end" name="date_end" class="dateEditor" value="" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">信息确认时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="info_confirm_date_begin" name="info_confirm_date_begin" class="dateEditor" value="" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="info_confirm_date_end" name="info_confirm_date_end" class="dateEditor" value="" style="width: 135px;" />
					</div>
				</div>
			</div>
			<div class="search-tr">	
				<div class="search-td">
					<div class="search-td-label">公司名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="companyName" name="companyName">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >来源：</div>
					<div class="search-td-combobox-condition">
						<select id="sourceType" name="sourceType" style="width: 135px;">
							<option value="">请选择</option>
							<c:forEach var="list" items="${sourceTypes}">
								<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:forEach>
							<option value="-1">PC端</option>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >主管确认状态：</div>
					<div class="search-td-combobox-condition">
						<select id="depositConfirmStatus" name="depositConfirmStatus" style="width: 135px;">
							<option value="">请选择</option>
							<c:forEach var="list" items="${depositConfirmStatusList}">
								<option value="${list.statusValue}" <c:if test="${depositConfirmStatus eq list.statusValue}">selected="selected"</c:if>>${list.statusDesc}
								</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >信息确认人：</div>
					<div class="search-td-combobox-condition">
						<select id="infoConfirmBy" name="infoConfirmBy" style="width: 135px;">
							<option value="">请选择</option>
							<c:forEach var="list" items="${infoConfirmByList}">
								<option value="${list.info_confirm_by}">${list.STAFF_NAME}
								</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
			</div>
			<div class="search-tr">
			 	 <div class="search-td">
					<div class="search-td-label" >入驻类型：</div>
					<div class="search-td-combobox-condition">
						<select id="settledType" name="settledType" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1">企业公司</option>
							<option value="2">个体商户</option>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>