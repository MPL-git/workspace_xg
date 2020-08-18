<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 var listConfig={
	  
      url:"/product/afterTemplet/data.shtml",
      
      listGrid:{ columns: [  { display: '供应商简称', name: 'mchtName', width: 200}, 
		                { display: '模板名称', name: 'name',width:180, width: 300},
		                { display: '默认品牌', name: 'brandName', align: "center", width: 150, render: function(rowdata, rowindex) {
		                	var html = [];
		                	var brandName=rowdata.brandName;
		                	if (brandName==null){
		                		html.push("不限");
		                	}else{
		                		html.push(brandName);
		                	}
		                	return html.join("");
		                }},
		                { display: '收货人', name: 'recipient', width: 150 },
		                { display: '电话', name: 'phone', width: 150},
		                { display: '收货地址', name: 'address', align: "left", render: function(rowdata, rowindex) {
		                	var html = [];
		                	var areaName=rowdata.areaName;
		                	var address=rowdata.address;
		                	html.push(areaName+address);
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
	<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr"  > 
			 
			<div class="search-td">
			<div class="search-td-label">商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label">商家名称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mchtName" name="mchtName" >
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" style="color: red">对接人：</div>
			<div class="search-td-condition" >
				<select id="platformContactId" name="platformContactId" >
					<c:if test="${isContact==1}">
						<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
							<option value="">全部商家</option>
						</c:if>
						<option value="${myContactId}">我对接的商家</option>
						<c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
							<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
						</c:forEach>
					</c:if>
					
					<c:if test="${isContact==0}">
					<option value="">全部商家</option>
					<c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
						<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
					</c:forEach>			
					</c:if>
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
