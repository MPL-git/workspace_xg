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
 	
	//查看商家联系人
	function mchtContact(id){
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
	  
	//审核
	function mchtInfoChgApplyAudit(id) {
    	$.ligerDialog.open({
    	height: $(window).height() - 40,
    	width: 1200,
    	title: "公司信息更新审核",
    	name: "INSERT_WINDOW",
    	url: "${pageContext.request.contextPath}/mcht/mchtInfoChgApplyAudit.shtml?id=" + id,
    	showMax: true,
    	showToggle: false,
    	showMin: false,
    	isResize: true,
    	slide: false,
    	data: null
   	    });
    }
	
	var listConfig={
		url:"/mcht/mchtDelay/mchtCompanyInfoUpdateAuditList.shtml",
      	listGrid:{ columns: [ 
						{ display: '提审日期', name: 'createDate', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.createDate != null && rowdata.createDate != ""){
   	   		                   var createDate = new Date(rowdata.createDate);
   	   		          	       return createDate.format("yyyy-MM-dd");	
   		                	}
		                }},
		                { display: '招商对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.zsContactName;
		                	
		                }},
						{ display: '商家序号', name: 'mchtCode',width: 100 }, 
						{ display: '入驻类型',width: 80, render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.settledType == "1"){
			            		html.push("企业公司");
			            	}else if(rowdata.settledType == "2"){
			            		html.push("个体商户");
			            	}
			            	return html.join("");
			         	}},
		                { display: '公司名称', width: 200, name:'companyName'},
		                { display: '店铺名称', width: 200, name:'shopName'},
		                { display: "商家联系人", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:mchtContact(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;"); 
						    return html.join("");
		              	}},
		              	{ display: '审核状态',   name: 'statusDesc' , width: 180, render: function(rowdata, rowindex) {
			                	var html = [];
			                	if((rowdata.status=='1')&&rowdata.fwStaffId==${sessionScope.USER_SESSION.staffID}){
							    html.push("<a href=\"javascript:mchtInfoChgApplyAudit(" + rowdata.id + ");\">"+rowdata.statusDesc+"</a>"); 
			                	}else{
			                		html.push(rowdata.statusDesc); 
			                	}
							    return html.join("");
			            }},
		                { display: '法务对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.fwContactName;
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
				<div class="search-td-label">审核状态</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1" selected="selected">待审</option>
							<option value="3">通过</option>						
							<option value="4">驳回</option>
						</select>
					</div>
				</div>
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
						<div class="search-td-label">对接人：</div>
						<div class="search-td-condition">
							<select id="platContactStaffId" name="platContactStaffId">
								<c:if test="${isManagement == 1}">
									<option value="" selected="selected">全部商家</option>
								</c:if>
								<option value="${staffID}" selected="selected" >我自己的商家</option>
								<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
									<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
								</c:forEach>
							</select>
						</div>
				</div>

				</div>
				<div class="search-tr">
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
