<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">
$(function(){
	$("#violateType").bind('change',function(){
		var violateType = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/getViolateActions.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"violateType":violateType},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var violateActions = data.violateActions;
					var optionArray = [];
					optionArray.push('<option value="">请选择</option>');
					for(var i=0;i<violateActions.length;i++){
						var statusValue = violateActions[i].statusValue;
						var statusDesc = violateActions[i].statusDesc;
						optionArray.push('<option value="'+statusValue+'">'+statusDesc+'</option>');
					}
					$("#violateAction").html(optionArray.join(""));
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
}); 
 
function edit(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.5,
		width: $(window).width()*0.5,
		title: "编辑",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/editCustomPage.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

</script>
</head>
<body style="padding: 0px;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateAreaId}">
		<div class="search-pannel">
			<div class="search-tr" id="decorateModuleDiv">
			<c:forEach items="${decorateAreaCustoms}" var="decorateAreaCustom">
				<c:forEach items="${decorateAreaCustom.decorateModuleCustoms}" var="decorateModuleCustom">
					<c:if test="${decorateModuleCustom.moduleType == 1 || decorateModuleCustom.moduleType == 9 || decorateModuleCustom.moduleType == 13}">
						<div style="position: relative;width: 750px;">
							<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 750px;">
						</div>
						<br>
					</c:if>
					<c:if test="${decorateModuleCustom.moduleType == 2}">
						<table class="gridtable" style="border: solid;">
							<c:forEach var="moduleItemCustom" items="${decorateModuleCustom.moduleItemCustoms}" varStatus="status" begin="0" end="${decorateModuleCustom.showNum-1}">
							<c:if test="${(status.index+1)%2==1}">
								<tr>				
							</c:if>
							<td class="l-grid-row-cell ">
								<div class="l-grid-row-cell-inner">
									<span style="display:block;text-align:left;margin-top:8px;">
										<img style="margin:10px;" src="${pageContext.request.contextPath}/file_servelt${moduleItemCustom.pic}" width="100" height="100" onclick="viewerPic(this.src)"><br>
										${moduleItemCustom.productName}<br>
										价格：<c:if test="${moduleItemCustom.salePriceMin != moduleItemCustom.salePriceMax}">
												${moduleItemCustom.salePriceMin}-${moduleItemCustom.salePriceMax}
											</c:if>
											<c:if test="${moduleItemCustom.salePriceMin == moduleItemCustom.salePriceMax}">
												${moduleItemCustom.salePriceMin}
											</c:if>
										元<br>
										折扣：<c:if test="${moduleItemCustom.discountMin != moduleItemCustom.discountMax}">
												${moduleItemCustom.discountMin}-${moduleItemCustom.discountMax}
											</c:if>
											<c:if test="${moduleItemCustom.discountMin == moduleItemCustom.discountMax}">
												${moduleItemCustom.discountMax}
											</c:if>
										折<br>
									</span>
								</div>
							</td>
							<c:if test="${(status.index+1)%2==0}">
								</tr>
							</c:if>
							</c:forEach>
						</table>	
						<br>
					</c:if>
					<c:if test="${decorateModuleCustom.moduleType == 3}">
						<div style="position: relative;width: 750px;">
							<c:forEach items="${decorateModuleCustom.activityCustoms}" var="activityCustom" begin="0" end="${decorateModuleCustom.showNum-1}">
								<img src="${pageContext.request.contextPath}/file_servelt${activityCustom.entryPic}" style="width: 750px;">
								<br>
								<span>${activityCustom.name}</span>
								<br>
								<span>${activityCustom.benefitPoint}</span>
								<br>
							</c:forEach>
						</div>
						<br>
					</c:if>
					<c:if test="${decorateModuleCustom.moduleType == 4}">
						<table class="gridtable" style="border: solid;">
							<c:forEach var="productCustom" items="${decorateModuleCustom.productCustoms}" varStatus="status">
							<c:if test="${(status.index+1)%2==1}">
								<tr>				
							</c:if>
							<td class="l-grid-row-cell">
								<div class="l-grid-row-cell-inner">
									<span style="display:block;text-align:left;margin-top:8px;">
										<img style="margin:10px;" src="${pageContext.request.contextPath}/file_servelt${productCustom.pic}" width="100" height="100" onclick="viewerPic(this.src)"><br>
										${productCustom.name}<br>
										价格：<c:if test="${productCustom.salePriceMin != productCustom.salePriceMax}">
												${productCustom.salePriceMin}-${productCustom.salePriceMax}
											</c:if>
											<c:if test="${productCustom.salePriceMin == productCustom.salePriceMax}">
												${productCustom.salePriceMin}
											</c:if>
										元<br>
										折扣：<c:if test="${productCustom.discountMin != productCustom.discountMax}">
												${productCustom.discountMin}-${productCustom.discountMax}
											</c:if>
											<c:if test="${productCustom.discountMin == productCustom.discountMax}">
												${productCustom.discountMax}
											</c:if>
										折<br>
									</span>
								</div>
							</td>
							<c:if test="${(status.index+1)%2==0}">
								</tr>
							</c:if>
							</c:forEach>
						</table>	
						<br>
					</c:if>
					<c:if test="${decorateModuleCustom.moduleType == 5}">
						<div style="position: relative;width: 750px;">
							<c:forEach items="${decorateModuleCustom.activityCustoms}" var="activityCustom">
								<img src="${pageContext.request.contextPath}/file_servelt${activityCustom.entryPic}" style="width: 750px;">
								<br>
								<span>${activityCustom.name}</span>
								<br>
								<span>${activityCustom.benefitPoint}</span>
								<br>
							</c:forEach>
						</div>
						<br>
					</c:if>
				</c:forEach>
			</c:forEach>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>