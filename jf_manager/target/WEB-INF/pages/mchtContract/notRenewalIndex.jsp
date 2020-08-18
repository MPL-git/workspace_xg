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

	//查看
	function check(id,type) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "运营确认续签",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/checkOrExamine.shtml?id=" + id+"&&type=3",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	var listConfig={
		url:"/mchtContract/notRenewalList.shtml",
      	listGrid:{ columns: [ 
						{ display: '提交时间', name: 'logCreateDate', width: 120 ,render: function(rowdata, rowindex) {
							if(rowdata.renewProStatus == "1"){
								if(rowdata.logCreateDate1 != null && rowdata.logCreateDate1 != ""){
		   	   		                   var logCreateDate1 = new Date(rowdata.logCreateDate1);
		   	   		          	       return logCreateDate1.format("yyyy-MM-dd");	
		   		                }
							}
							if(rowdata.renewProStatus == "4"){
								if(rowdata.logCreateDate4 != null && rowdata.logCreateDate4 != ""){
		   	   		                   var logCreateDate4 = new Date(rowdata.logCreateDate4);
		   	   		          	       return logCreateDate4.format("yyyy-MM-dd");	
		   		                }
							}
		                }},
		                { display: '招商对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.zsContact;
		                }},
						{ display: '商家序号', name: 'mchtCode',width: 100 }, 
						{ display: '公司名称', name: 'companyName', width: 120},
	 		           	{ display: '主营类目', name: 'productName',width: 100 }, 
						{ display: '店铺名称', width: 200, name:'shopName'},
						{ display: "商家联系人信息", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:mchtContact(" + rowdata.mchtId + ");\">查看</a>"); 
						    return html.join("");
		              	}},
		              	{ display: '不签约确认方', width: 200, name:'zsRenewProStatusDesc',align: "center", render: function(rowdata, rowindex) {
							if(rowdata.renewProStatus == "4"){
								return "平台";
							}else{
								return "商家";
							}
		              	}},
		              	{ display: '不续签原因', width: 200, name:'',align: "center", render: function(rowdata, rowindex) {
							if(rowdata.renewProStatus == "4"){
								return rowdata.zsNotRenewRemarks;
							}else{
								return rowdata.mchtNotRenewRemarks;
							}
		              	}},
		              	{ display: '确认状态', width: 200, name:'zsRenewProStatusDesc'},
		              	{ display: '运营对接人', width: 200, name:'yyContact'},
		                { display: '操作', name: '', width: 120 ,render: function(rowdata, rowindex) {
   		                	return "<a href=\"javascript:check(" + rowdata.id + ");\">运营操作</a>";
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
		 		<div class="search-td" style="width:250px;">
						<div class="search-td-label">对接人：</div>
						<div class="search-td-condition">
							<select id="platContactStaffId" name="platContactStaffId">
								<c:if test="${isManagement == 1}">
									<option value="" selected="selected">全部商家</option>
								</c:if>
								<option value="${staffID}">我自己的商家</option>
								<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
									<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
								</c:forEach>
							</select>
						</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td" style="width:250px;">
						<div class="search-td-label">确认状态：</div>
						<div class="search-td-condition">
							<select id="renewProStatusFW" name="renewProStatusFW">
								<option value="">请选择</option>
								<option value="4">不予续签</option>
								<option value="1">不再续签</option>
							</select>
						</div>
				</div>	
				<div class="search-td" style="width:250px;">
						<div class="search-td-label">不续签确认方：</div>
						<div class="search-td-condition">
							<select id="type" name="type">
								<option value="">请选择</option>
								<option value="4">平台</option>
								<option value="1">商家</option>
							</select>
						</div>
				</div>					
				<div class="search-td-search-btn" style="margin-top:-3px;margin-right:600px;">
						<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
