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
	
	<style type="text/css">
		.table-title{
	 		font-size: 17px;
	 		font-weight: 600; 
	 	}
	 	.table-text{
	 		margin: 10px 0px 20px 0px;
	 	}
	</style>
	
	<script type="text/javascript">
		
		$(function(){
			
			var auditLog = $("#auditLog").ligerGrid({
				url:'${pageContext.request.contextPath}/singleProductActivity/getSingleProductActivityAuditLog.shtml?singleProductActivityId=${singleProductActivityCustom.id }',
				columns: [
					{display:'操作状态',name:'operationStatus', width:'30%', align:'center', height:$(this).height(), isSort:false, render:function(rowdata, rowindex) {
						var html = rowdata.createName;
						if(rowdata.createDate != null && rowdata.createDate != "" && rowdata.createDate != undefined) {
							var createDate = new Date(rowdata.createDate);
							html += "<br/>" + createDate.format("yyyy-MM-dd hh:mm:ss");
						}
						var status = "待审";
						if(rowdata.status == '1')
							status = "初审通过";
						if(rowdata.status == '2')
							status = "初审驳回";
						if(rowdata.status == '3')
							status = "排期通过";
						if(rowdata.status == '4')
							status = "排期驳回";
						if(rowdata.status == '5')
							status = "复审驳回";
						if(rowdata.status == '6')
							status = "修改排序";
						html += "<br/>" + status;
						return html;
					}},
					{display:'原因说明',name:'remarks', width:'70%', align:'center', isSort:false}      
				],
				pageSize: 5,
				pageSizeOptions: [5,10,20,50,100],
	            width: '100%',
	            onAfterShowData: function() { 
					$(".l-grid-row-cell-inner").css("height", "auto");
					var i = 0;
					$("tr",".l-grid2","#"+listConfig.container.listGridName+"").each(function() {
						$($("tr",".l-grid1","#"+listConfig.container.listGridName+"")[i]).css("height",$(this).height());
						i++;
					});
				}
	        });
			
			var productActivity = $("#productActivity").ligerGrid({
				url:'${pageContext.request.contextPath}/singleProductActivity/getSingleProductActivity.shtml?productId=${singleProductActivityCustom.productId }',
				columns: [
					{display:'排期时间开始',name:'beginTime', width:'20%', align:'center', isSort:false, render:function(rowdata, rowindex) {
						var beginTime = new Date(rowdata.beginTime);
						return beginTime.format("yyyy-MM-dd hh:mm:ss");
					}},
					{display:'排期时间结束',name:'endTime', width:'20%', align:'center', isSort:false, render:function(rowdata, rowindex) {
						var beginTime = new Date(rowdata.endTime);
						return beginTime.format("yyyy-MM-dd hh:mm:ss");
					}},
					{display:'报名类型',name:'typeDesc', width:'20%', align:'center', isSort:false},
					{display:'价格',name:'activityPrice', width:'20%', align:'center', isSort:false},      
					{display:'销量',name:'quantitySum', width:'20%', align:'center', isSort:false}    
				],
				pageSize:5,
				pageSizeOptions:[5,10,20,50,100],
	            width: '100%',
	            onAfterShowData: function() { 
					$(".l-grid-row-cell-inner").css("height", "auto");
					var i = 0;
					$("tr",".l-grid2","#"+listConfig.container.listGridName+"").each(function() {
						$($("tr",".l-grid1","#"+listConfig.container.listGridName+"")[i]).css("height",$(this).height());
						i++;
					});
				}
	        });
			
		});
	
	</script>

</head>
	<body style="padding: 10px;">
		<div>
			<div class="table-title" >商家优势说明：</div>
			<div class="table-text" >${singleProductActivityCustom.remarks }</div>
			<div class="table-title" >审核日志：</div>
			<div class="table-text" id="auditLog" ></div>
			<div class="table-title" >本商品历史数量：</div>
			<div class="table-text" id="productActivity" ></div>
		</div>
	</body>
</html>
