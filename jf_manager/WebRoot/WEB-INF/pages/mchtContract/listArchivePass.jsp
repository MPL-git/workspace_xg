<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<title>合同已归档</title>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
$(function() {
	$("#contractarchivedateBegin").ligerDateEditor( {
		showTime : false,
		labelWidth : 150,
		width:150,
		labelAlign : 'left'
	});
});

$(function() {
	$("#contractarchivedateEnd").ligerDateEditor( {			
		showTime : false,
		labelWidth : 150,
		width:150,
		labelAlign : 'left'
	});
}); 

      var listConfig={
      url:"/mchtContract/listMchtContractData.shtml",
      listGrid:{
		  columns: [
				{ display: '合同开始日期', name: 'beginDate', width:120 ,render: function(rowdata, rowindex) {
					if(rowdata.beginDate != null && rowdata.beginDate!=""){
					   return new Date(rowdata.beginDate).format("yyyy-MM-dd");
					}
				}},
			  	{ display: '合同到期日期', name: 'endDate', width:120 ,render: function(rowdata, rowindex) {
				  if(rowdata.endDate != null && rowdata.endDate!=""){
					  return new Date(rowdata.endDate).format("yyyy-MM-dd");
				  }
			  	}},
				{ display: '招商对接人', name: 'platformMerchantsContact.contactName',width:100 },
				{ display: '商家序号', name: 'mchtInfo.mchtCode',width:100 },
				{ display: '公司名称',  name: 'mchtInfo.companyName', width: 150 },
				{ display: '店铺名',  name: 'mchtInfo.shopName', width: 150 },
				{ display: '商家合作状态',  name: 'mchtInfo.status', width: 150 , render: function(rowdata, rowindex) {
			  		if(rowdata.mchtInfo.status == '0'){
			  			return "入驻中";
			  		}else if(rowdata.mchtInfo.status == '1'){
			  			return "正常";
			  		}else if(rowdata.mchtInfo.status == '2'){
			  			return "业务暂停";
			  		}else if(rowdata.mchtInfo.status == '3'){
			  			return "关闭";
			  		}else if(rowdata.mchtInfo.status == '4'){
			  			return "未提交";
			  		}
			  	}},
			  	{ display: "查看信息", name: "OPER5", width: 100, align: "center", render: function(rowdata, rowindex) {
			  		var html = [];
					html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">公司信息</a><br>");
					html.push("<a href=\"javascript:viewFinanceInfo(" + rowdata.mchtId + ");\">财务信息</a><br>");
					html.push("<a href=\"javascript:viewMchtContact(" + rowdata.mchtId + ");\">联系人信息</a><br>");
					return html.join("");
			  	}},
			  	{ display: "合同详情", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
				  var html = [];
				  html.push("<a href=\"javascript:viewMchtContract(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;");
				  return html.join("");
			  	}},
				{ display: "商家寄件状态", name: "isMchtSendStr", width: 100, align: "center"},
				{ display: '合同归档日期', name: "archiveDate", width:160 ,render: function(rowdata, rowindex) {
					  if(rowdata.archiveDate != null && rowdata.archiveDate!=""){
						  return new Date(rowdata.archiveDate).format("yyyy-MM-dd hh:mm:ss");
					  }
				  	}},
				{ display: "合同归档状态", name: "archiveStatusStr", width: 100, align: "center"},
			  	{ display: "合同编号", name: "contractCode", width: 100, align: "center"},
			  	{ display: "续签状态", name: "OPER5", width: 100, align: "center", render: function(rowdata, rowindex) {
				  	var html = [];
					if(rowdata.renewStatusStr=="未处理"){
						var endDateStr = new Date(rowdata.endDate).format("yyyy-MM-dd");
						var s1 = new Date(endDateStr.replace(/-/g, "/"));
						var s2 = new Date();
						var diff = s1.getTime() - s2.getTime();
						var days = parseInt(diff / (1000 * 60 * 60 * 24));
						if(rowdata.mchtInfo.status!=3 && days<=90){
							html.push("<a href=\"javascript:renew(" + rowdata.id + ");\">未处理</a>&nbsp;&nbsp;");
						}
					}else{
						html.push(rowdata.renewStatusStr);
					}

				  	return html.join("");
			  	}},
			  	{ display: '法务对接人', name: 'platformFawuContact.contactName',width:100 },
			  	{ display: '平台是否寄回', name: "isPlatformSend", width: 100, align: "center", render: function(rowdata, rowindex) {
					if(rowdata.isPlatformSend=="0"){
						 return "否";
					}else{
						return "是";
					}
			 	}},
			  	
			  	{ display: "平台寄回单号", name: "OPER6", width: 200, align: "center", render: function(rowdata, rowindex) {
				  	var html = [];
					if(rowdata.platformExpressNo==null && rowdata.expressname==null){
						html.push("<a href=\"javascript:updatemchtexpres(" + rowdata.id + ");\">【未填写】</a>&nbsp;&nbsp;");
					}else{
						html.push(rowdata.expressname + "：" + rowdata.platformExpressNo);
						html.push("<a href=\"javascript:updatemchtexpres(" + rowdata.id +");\">【修改】</a>&nbsp;&nbsp;");						
					}

				  	return html.join("");
			  	}},
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
       
  };
    
$(function(){
        $.ajax({
    		url : "${pageContext.request.contextPath}/mchtContract/updateMchtContractData.shtml",
    		type : 'POST',
    		dataType : 'json',
    		cache : false,
    		async : false,
    		success : function(result) {
    			if (result.success) {
    				parent.location.reload();
    				frameElement.dialog.close();
    			}else{
    				$.ligerDialog.error(result.message);
    			}

    		},
    	});
});

</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="orderByBeginDate" value="2" alt="按合同开始时间倒序"/>
		<input type="hidden" name="notEqualMchtInfoStatus" value="0" alt="不等于入驻中"/>
		<div class="search-pannel">
		  <div class="search-tr"  >
			<div class="search-td" >
			<div class="search-td-label" style="float:left;">合同归档日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="contractarchivedateBegin" name="contractarchivedateBegin" />
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="contractarchivedateEnd" name="contractarchivedateEnd" />	
			</div>			
			</div>
			      
				<div class="search-td">
				<div class="search-td-label">序号：</div>
				<div class="search-td-condition">
				<input type="text" name="mcht_code" >
				</div>
				</div>

				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" name="mcht_name" >
				</div>
				</div>
															
			 </div>
				
		    
			<div class="search-tr" >
				<div class="search-td">
					<div class="search-td-label" >到期年份：</div>
					<div class="search-td-condition" style="text-align: left;">
						<select name="endYear" class="querysel">
							<option value="">请选择</option>
							<option value="2017" <c:if test="${endYear=='2017'}">selected="selected"</c:if>>2017</option>
							<option value="2018" <c:if test="${endYear=='2018'}">selected="selected"</c:if>>2018</option>
							<option value="2019" <c:if test="${endYear=='2019'}">selected="selected"</c:if>>2019</option>
							<option value="2020" <c:if test="${endYear=='2020'}">selected="selected"</c:if>>2020</option>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label" >到期月份：</div>
					<div class="search-td-condition" style="text-align: left;">
						<select name="endMonth" class="querysel">
							<option value="">请选择</option>
							<option value="01" <c:if test="${endMonth=='01'}">selected="selected"</c:if>>01</option>
							<option value="02" <c:if test="${endMonth=='02'}">selected="selected"</c:if>>02</option>
							<option value="03" <c:if test="${endMonth=='03'}">selected="selected"</c:if>>03</option>
							<option value="04" <c:if test="${endMonth=='04'}">selected="selected"</c:if>>04</option>
							<option value="05" <c:if test="${endMonth=='05'}">selected="selected"</c:if>>05</option>
							<option value="06" <c:if test="${endMonth=='06'}">selected="selected"</c:if>>06</option>
							<option value="07" <c:if test="${endMonth=='07'}">selected="selected"</c:if>>07</option>
							<option value="08" <c:if test="${endMonth=='08'}">selected="selected"</c:if>>08</option>
							<option value="09" <c:if test="${endMonth=='09'}">selected="selected"</c:if>>09</option>
							<option value="10" <c:if test="${endMonth=='10'}">selected="selected"</c:if>>10</option>
							<option value="11" <c:if test="${endMonth=='11'}">selected="selected"</c:if>>11</option>
							<option value="12" <c:if test="${endMonth=='12'}">selected="selected"</c:if>>12</option>
						</select>
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label" >
						<input type="checkbox" class="liger-checkbox" name="is_my_fawu" value="1" />
					</div>
					<div class="search-td-condition" style="text-align: left;">
						只看本人法务对接
					</div>
				</div>  
				
				
				<div class="search-td">
					<div class="search-td-label"  >合同编号：</div>
					<div class="search-td-condition" >
						<input type="text" name="contractCode" >
					</div>
				</div>					
			</div>
			
			
			<div class="search-tr" >
				<div class="search-td">
					<div class="search-td-label">平台是否寄回：</div>
					<div class="search-td-condition" >
						<select name="isPlatformSend" class="querysel">
							<option value="">请选择</option>
							<option value="0">未寄出</option>
							<option value="1">已寄出</option>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label"  >平台寄回单号：</div>
					<div class="search-td-condition" >
						<input type="text" name="platformExpressNo" >
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
						<c:forEach items="${zsPlatformContactCustomList}" var="zsPlatformContactCustom">
							<option value="${zsPlatformContactCustom.id}">${zsPlatformContactCustom.contactName}的商家</option>
						</c:forEach>
					</c:if>
					</select>
				</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label"  >商家合作状态：</div>
					<div class="search-td-condition" >
						<select name="mchtInfoStatus" class="querysel">
							<option value="">请选择</option>
							<option value="0" <c:if test="${mchtInfoStatus eq '0'}">selected="selected"</c:if>>入驻中</option>
							<option value="1" <c:if test="${mchtInfoStatus eq '1'}">selected="selected"</c:if>>正常</option>
							<option value="2" <c:if test="${mchtInfoStatus eq '2'}">selected="selected"</c:if>>业务暂停</option>
							<option value="3" <c:if test="${mchtInfoStatus eq '3'}">selected="selected"</c:if>>关闭</option>
							<option value="4" <c:if test="${mchtInfoStatus eq '4'}">selected="selected"</c:if>>未提交</option>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label"  >合同归档状态：</div>
					<div class="search-td-condition" >
						<select name="archiveStatus" class="querysel">
							<option value="">请选择</option>
							<option value="0" <c:if test="${archiveStatus eq '0'}">selected="selected"</c:if>>未处理</option>
							<option value="1" <c:if test="${archiveStatus eq '1'}">selected="selected"</c:if>>已归档</option>
							<option value="2" <c:if test="${archiveStatus eq '2'}">selected="selected"</c:if>>不通过驳回</option>
							<option value="4" <c:if test="${archiveStatus eq '4'}">selected="selected"</c:if>>不签约</option>
						</select>
					</div>
				</div>
				
			 	<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div>
				</div>
								
			 </div>

		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

<script type="text/javascript">

	/* $(function(){

	}); */


	// 查看商家信息
	function viewMchtInfo(id) {
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

	// 查看财务信息
	function viewFinanceInfo(id) {
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

	// 查看商家联系人
	function viewMchtContact(id){
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

	// 查看商家合同详情
	function viewMchtContract(mchtContractId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 250,
			title: "商家合同详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/viewMchtContract.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	// 合同续签处理
	function renew(mchtContractId) {
		if(${hasAuth}){
			$.ligerDialog.open({
				height: 450,
				width: 550,
				title: "处理续签",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtContract/renew.shtml?id=" + mchtContractId,
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
	
	
	function updatemchtexpres(id) {
		$.ligerDialog.open({
			height: 450,
			width: 550,
			title: "修改寄回单号",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/mchtContractExprelist.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
