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
			showTime : true,
			format: "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
		});
		
		$("#export").bind('click', function(){
			$("#dataForm").attr("action", "${pageContext.request.contextPath}/mchtInfoChangeLog/lawAuditLogListExport.shtml");
			$("#dataForm").submit();
		});
		
	 });
	 
 	 var listConfig={
	      url:"/mchtInfoChangeLog/lawAuditLogList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
						{display:'序号',name:'id', align:'center', isSort:false, width:60}, 
						{display:'操作时间',name:'createDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        	var html = [];
							if(rowdata.createDate != null && rowdata.createDate != "") {
								var createDate = new Date(rowdata.createDate);
								if(new Date(rowdata.createDate) <= new Date) {
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
							}
						    return html.join("");
                        }},
                        {display:'操作人',name:'creatorName', align:'center', isSort:false, width:100},      
                        {display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:100},      
                        {display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180},      
                        {display:'公司名称',name:'companyName', align:'center', isSort:false, width:180},      
                        {display:'主营类目',name:'productTypeName', align:'center', isSort:false, width:120},      
                        {display:'合作状态',name:'mchtStatusDeac', align:'center', isSort:false, width:120},      
                        {display:'状态类型',name:'logType', align:'center', isSort:false, width:120},      
                        {display:'名称',name:'logName', align:'center', isSort:false, width:180},  
                        {display:'变更前',name:'beforeChange', align:'center', isSort:false, width:100}, 
                        {display:'变更后',name:'afterChange', align:'center', isSort:false, width:100}, 
                        {display:'备注 / 驳回原因',name:'remarks', align:'center', isSort:false, width:200}
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
	<form id="dataForm" method="post" action="" >
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >公司名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="companyName" name="companyName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >操作人：</div>
					<div class="search-td-combobox-condition" >
						<select id="createBy" name="createBy" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="createByList" items="${createByList }">
								<option value="${createByList.create_by }">
									${createByList.creator_name }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;" >
					<div class="l-panel-search-item search_right">操作时间：</div>
					<div class="l-panel-search-item">
						<input type="text" class="dateEditor" id="createDateBegin" name="createDateBegin" />
					</div>
					<div class="l-panel-search-item">至 </div>
					<div class="l-panel-search-item">
						<input type="text" class="dateEditor" id="createDateEnd" name="createDateEnd" />
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >主营类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >合作状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="mchtStatus" name="mchtStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="mchtStatus" items="${mchtStatusList }">
								<option value="${mchtStatus.statusValue }">
									${mchtStatus.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >状态类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="logType" name="logType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="logType" items="${logTypeList }">
								<option value="${logType }">
									${logType }
								</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
