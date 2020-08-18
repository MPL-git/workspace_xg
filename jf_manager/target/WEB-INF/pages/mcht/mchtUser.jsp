<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
 
function toSendPassowrd(id){
	
	$.ligerDialog.confirm('给用户重新发送短信？', function (yes) {
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/mcht/sendPassword2MchtUser.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id:id
				},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$.ligerDialog.success(data.returnMsg);
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

function toNotCooperate(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.6,
		width: $(window).width()*0.5,
		title: "确认不合作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/toNotCooperate.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 //添加
 
 var listConfig={
	  
      url:"/mcht/mchtUserDataList.shtml",
      listGrid:{ columns: [      
                        { display: '创建日期',width:150, render: function (rowdata, rowindex) {
					      	if (rowdata.createDate!=null){
								var createDate=new Date(rowdata.createDate);
								return createDate.format("yyyy-MM-dd hh:mm:ss");          		
					    	}
					    }},
                        { display: '招商对接人', name: 'zsContactName',width:80},
		                // { display: '商家序号', name: 'mchtCode',width:100},
			            { display: '商家序号',width: 100, render: function(rowdata, rowindex) {
							  var html = [];
							  html.push(rowdata.mchtCode);
							  html.push("<br>");
							  if(rowdata.mchtType == "2"){
								  html.push("POP");
							  }else if (rowdata.mchtType == "1" && rowdata.isManageSelf == "1"){
								  html.push("自营SPOP");
							  }else {
								  html.push("SPOP");
							  }
							  return html.join("");
						 }},
		                { display: '入驻类型',width: 80, render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.settledType == "1"){
			            		html.push("企业公司");
			            	}else if(rowdata.settledType == "2"){
			            		html.push("个体商户");
			            	}
			            	return html.join("");
			         	}},
		                { display: '申请公司名称', name: 'mchtsettledapplycompanyname',width:180},
		                { display: '入驻公司名称', name: 'mchtinfocompanyname',width:180},
		                { display: '用户名',  name: 'userCode', width: 180 }, 
		                { display: '初始密码', name: 'statusDesc' ,width: 180 ,render: function(rowdata, rowindex) {
							return "123456";
					 	}},
		                { display: '密码修改', name: 'statusDesc' ,width: 180 ,render: function(rowdata, rowindex) {
							if(rowdata.password=='e10adc3949ba59abbe56e057f20f883e'){
								return "未修改";
							}else{
								return "已修改";
							}
					 	}},
		                { display: '资质总审核状态', name: 'totalAuditStatusDesc' ,width: 180},
		                { display: "操作", name: "OPER", width: 150, align: "center", render: function(rowdata, rowindex) {
		                	var html = [];
		            	    html.push("[<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">查看商家</a>]"); 
							if(rowdata.password=='e10adc3949ba59abbe56e057f20f883e'){
								 html.push("[<a href=\"javascript:toSendPassowrd(" + rowdata.id + ");\">重发短信</a>]"); 
							}
		            	    html.push("<br>[<a href=\"javascript:toNotCooperate(" + rowdata.mchtId + ");\">不合作</a>]"); 
						    return html.join("");
					 }
	             }
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
	<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label">序号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "search_id" name="search_id" >
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label">名称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "search_name" name="search_name" >
			</div>
			</div>
			 	
			 <div class="search-td">
			 <div class="search-td-label">密码:</div>
			 <div class="search-td-condition" >
				<select  id="search_password" name="search_password" class="querysel">
					<option value="">请选择</option> 
					<option value="1">已修改</option> 
					<option value="0">未修改</option> 
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
				  <div class="search-td-label">对接人：</div>
				    <div class="search-td-condition" >
					<select id="platformContactId" name="platformContactId" >
					<c:if test="${isAssistant}">
						<c:if test="${isManagement==1}">
							<option value="" selected="selected">全部商家</option>
							<option value="${myContactId}">我对接的商家</option>
							<c:forEach items="${platformContacts}" var="platformContact">
								<option value="${platformContact.id}">${platformContact.contactName}的商家</option>
							</c:forEach>
						</c:if>
						<c:if test="${isManagement!=1}">
							<option value="${myContactId}" selected="selected">我对接的商家</option>
							<c:forEach items="${platformContacts}" var="platformContact">
								<option value="${platformContact.id}">${platformContact.contactName}的商家</option>
							</c:forEach>
						</c:if>
					</c:if>
					<c:if test="${!isAssistant}">
						<option value="" selected="selected">全部商家</option>
						<c:forEach items="${yyPlatformContactCustomList}" var="yyPlatformContactCustom">
							<option value="${yyPlatformContactCustom.id}">${yyPlatformContactCustom.contactName}的商家</option>
						</c:forEach>
					</c:if>
					</select>
				</div>
			</div>
		</div>
		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" style="float:left;">入驻类型：</div>
				<div class="search-td-condition" >
					<select id="settledType" name="settledType" style="width: 150px;">
						<option value="">请选择</option>
						<option value="1">企业公司</option>
						<option value="2">个体商户</option>
					</select>
			 	 </div>
			 </div>

			<div class="search-td">
				<div class="search-td-label" style="float:left;">是否自营：</div>
				<div class="search-td-condition" >
					<select id="isManageSelf" name="isManageSelf" style="width: 150px;">
						<option value="">请选择</option>
						<option value="0">非自营</option>
						<option value="1">自营</option>
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
