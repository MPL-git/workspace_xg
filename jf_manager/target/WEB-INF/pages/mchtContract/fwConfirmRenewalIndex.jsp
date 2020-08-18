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
 		$("#closeReload .l-dialog-close").live("click", function () {
            $("#searchbtn").click();
        });
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
	
	//商家基础资料
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
	
	//查看
	function check(id,type) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "法务确认续签",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/checkOrExamine.shtml?id=" + id+"&&type=2",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			id: "closeReload"
		});
	}
	
	// 查看商家合同详情
	function viewMchtContract(mchtContractId,isNew) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 250,
			title: "商家合同详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/viewMchtContract.shtml?id=" + mchtContractId+"&&isNew="+isNew,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	var listConfig={
		url:"/mchtContract/fwConfirmRenewalList.shtml",
      	listGrid:{ columns: [ 
						{ display: '招商提交时间', name: 'zsLogCreateDate', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.zsLogCreateDate != null && rowdata.zsLogCreateDate != ""){
   	   		                   var zsLogCreateDate = new Date(rowdata.zsLogCreateDate);
   	   		          	       return zsLogCreateDate.format("yyyy-MM-dd hh:mm");	
   		                	}
		                }},
		                { display: '法务对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.fwContact;
		                }},
						{ display: '商家序号', name: 'mchtCode',width: 100 }, 
						{ display: '类目', name: 'productName',width: 100 }, 
						{ display: '公司名称', name: 'companyName', width: 120 ,render: function(rowdata, rowindex) {
			                var html = [];
							html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a><br/>");
							return html.join("");
	 		            }},
						{ display: '店铺名称', width: 200, name:'shopName'},
						{ display: '商家品牌信息', name: 'mchtProductBrandName', width: 180 ,render: function(rowdata, rowindex) {
		                	var mchtProductBrandName = rowdata.mchtProductBrandName;
		                	var html = [];
		                	if(mchtProductBrandName != null && mchtProductBrandName.length > 0){
		                		var mchtProductBrandNames = mchtProductBrandName.split(",");
								for (var i = 0; i < mchtProductBrandNames.length; i++) {
									var brandName = mchtProductBrandNames[i].substring(0, mchtProductBrandNames[i].lastIndexOf("）")+1);
									var productBrandId = mchtProductBrandNames[i].substring(mchtProductBrandNames[i].lastIndexOf("）")+1);
									if(i != 0) {
										html.push("<br/>");
									}
									if(productBrandId!=0){
										html.push(brandName);
									}else{//无品牌
										html.push("无品牌");
									}
								}
		                	}
							return html.join("");
		                }},
						{ display: "商家联系人信息", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:mchtContact(" + rowdata.mchtId + ");\">查看</a>"); 
						    return html.join("");
		              	}},
		              	{ display: '确认状态', width: 200, name:'zsRenewProStatusDesc'},
		                { display: '合同详情', name: '', width: 120 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push("<a href=\"javascript:viewMchtContract(" + rowdata.id + ",1);\">查看本合同</a><br>");
		                	if(rowdata.renewProStatus == "7"){
		                		html.push("<a href=\"javascript:viewMchtContract(" + rowdata.parentContractId + ",0);\">查看新签合同</a>");
		                	}      	
		                	return html.join("");
		                }},
		                { display: '操作', name: '', width: 120 ,render: function(rowdata, rowindex) {
   		                	return "<a href=\"javascript:check(" + rowdata.id + ");\">查看</a>";
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
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">商家序号</div>
					<div class="search-td-condition"> 
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">名称</div>
					<div class="search-td-condition">
						<input type="text" id="companyOrShop" name="companyOrShop">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
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
		 		<div class="search-td" style="width:230px;">
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
				<div class="search-td" style="width:230px;">
						<div class="search-td-label">法务确认状态：</div>
						<div class="search-td-condition">
							<select id="renewProStatusFW" name="renewProStatusFW">
								<option value="">请选择</option>
								<option value="3">确认续签(招商确认)</option>
								<option value="7">已生成合同</option>
							</select>
						</div>
				</div>	
				<div class="search-td-search-btn" style="margin-top:-3px;margin-right:200px;">
						<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
