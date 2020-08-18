<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
<script type="text/javascript">
	 
	 $(function() {
		 
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		
	 });
	 
 	 var listConfig={ 
	      url:"/douyinSprPlan/getDouyinSprPlanStatisticsList.shtml",
	      listGrid:{ columns: [
							{display:'ID', name:'id', align:'center', isSort:false, width:80},
							{display:'渠道', name:'spr_chnl_name', align:'center', isSort:false, width:120},
							{display:'投放广告位置', name:'spr_plan_site', align:'center', isSort:false, width:120},
							{display:'页面类型', name:'spr_plan_type_desc', align:'center', isSort:false, width:120},
							{display:'页面ID', name:'', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
			                	if(rowdata.link_value == null || rowdata.link_value == '' ) {
									return "--";
								}else{
									return rowdata.link_value;
								}
			                }},
							{display:'点击打开次数', name:'douyin_spr_log_count', align:'center', isSort:false, width:100},
							{display:'IP数', name:'ip_count', align:'center', isSort:false, width:100},
							{display:'用户数', name:'member_count', align:'center', isSort:false, width:100},
							{display:'新客母订单数', name:'new_guest_combine_order_count', align:'center', isSort:false, width:100},
							{display:'新客消费人数', name:'new_guest_consume_count', align:'center', isSort:false, width:100},
							{display:'新客买家实付金额', name:'new_guest_pay_amount', align:'center', isSort:false, width:100},
							{display:'老客母订单数', name:'old_guest_combine_order_count', align:'center', isSort:false, width:100},
							{display:'老客消费人数', name:'old_guest_consume_count', align:'center', isSort:false, width:100},
							{display:'老客买家实付金额', name:'old_guest_pay_amount', align:'center', isSort:false, width:100},
							{display:'iOS母订单数', name:'ios_count', align:'center', isSort:false, width:100},
							{display:'iOS母订单金额', name:'ios_amount', align:'center', isSort:false, width:100},
							{display:'安卓母订单', name:'android_count', align:'center', isSort:false, width:100},
							{display:'安卓母订单金额', name:'android_amount', align:'center', isSort:false, width:100},
							{display:'激活用户数', name:'download_member_count', align:'center', isSort:false, width:100},
							{display:'总销售额', name:'total_pay_amount', align:'center', isSort:false, width:100},
							{display:'创建时间', name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
			                	if(rowdata.create_date == null || rowdata.create_date == '' ) {
									return "";
								}else{
									var createDate = new Date(rowdata.create_date);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								}
			                }}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
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
	<form id="dataForm" runat="server" >
		<div class="search-pannel" >
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sprPlanId" name="sprPlanId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >渠道：</div>
					<div class="search-td-combobox-condition" >
						<select id="sprChnlId" name="sprChnlId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="douyinSprChnl" items="${douyinSprChnlList }">
								<option value="${douyinSprChnl.id }">${douyinSprChnl.sprChnlName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >投放广告位置：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sprPlanSite" name="sprPlanSite" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >页面类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="sprPlanType" name="sprPlanType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sprPlanType" items="${sprPlanTypeList }">
								<option value="${sprPlanType.statusValue }">${sprPlanType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" style="width: 135px;"  />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">统计日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCountDate" name="beginCountDate" class="dateEditor" value="${beginDate }" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCountDate" name="endCountDate" class="dateEditor" value="${endDate }" style="width: 135px;" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >页面ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="linkValue" name="linkValue" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >访问类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="accessType" name="accessType" style="width: 135px;" >
							<option value="1">首次访问</option>
							<option value="2">最新访问</option>
						</select>
					</div>
				</div>
			 	<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
