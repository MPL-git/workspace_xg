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
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left',
			labelwidth: 120,
			width:120
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
	var listConfig={
		url:"/mchtContract/contractExpireList.shtml",
      	listGrid:{ columns: [
						{ display: '合同编号', width: 200, name:'contractCode'},
						{ display: '合同开始时间', name: 'contractBeginDate', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.contractBeginDate != null && rowdata.contractBeginDate != ""){
   	   		                   var contractBeginDate = new Date(rowdata.contractBeginDate);
   	   		          	       return contractBeginDate.format("yyyy-MM-dd");	
   		                	}
		                }},
		                { display: '合同到期日期', name: 'contractEndDate', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.contractEndDate != null && rowdata.contractEndDate != ""){
   	   		                   var contractEndDate = new Date(rowdata.contractEndDate);
   	   		          	       return contractEndDate.format("yyyy-MM-dd");	
   		                	}
		                }},
		                { display: '招商对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.zsContactName;
		                }},
						{ display: '商家序号', name: 'mchtCode',width: 100 }, 
						{ display: '公司名称', name: 'companyName', width: 120 ,render: function(rowdata, rowindex) {
			                var html = [];
							html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a><br/>");
							return html.join("");
	 		            }},
						{ display: '店铺名称', width: 200, name:'shopName'},
						{ display: '品牌', name: 'mchtProductBrandName', width: 180 ,render: function(rowdata, rowindex) {
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
						{ display: "商家联系人", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:mchtContact(" + rowdata.mchtId + ");\">查看</a>");
						    return html.join("");
		              	}},
			         	{ display: '商家合作状态', width: 200, name:'statusDesc'},
			         	{ display: '合同审核状态', width: 200, name:'auditStatusDesc'},
			         	{ display: '合同归档状态', width: 200, name:'archiveStatusDesc'},
			         	{ display: '续签进度状态', width: 200, name:'renewProStatusDesc'},
			         	{ display: '新合同编号', width: 200, name:'nextContactCode'},
			         	{ display: '新合同审核状态', width: 200, name:'newAuditStatusDesc'},
			         	/*{ display: '距离到期时间', width: 200, name:'days', align: "center", render: function(rowdata, rowindex) {
							if(rowdata.days<0){
								return '已过期';
							}else{
								return "<span style='color:blue;'>"+rowdata.days+"天</span>";
							}
		              	}},*/

			         	{ display: '法务对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.fwContactName;
		                }},
		                { display: '最后短信发送时间', name: 'sendDate', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.sendDate != null && rowdata.sendDate != ""){
   	   		                   var sendDate = new Date(rowdata.sendDate);
   	   		          	       return sendDate.format("yyyy-MM-dd hh:mm:ss");	
   		                	}
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
				<div class="search-td" style="width:220px;">
					<div class="search-td-label">商家合作状态：</div>
					<div class="search-td-condition">
						<select id="mchtInfoStatus" name="mchtInfoStatus">
							<option value="">请选择</option>
							<option value="1" selected="selected">正常</option>
							<option value="2">暂停</option>
							<option value="3">关闭</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width:220px;">
					<div class="search-td-label">合同归档状态：</div>
					<div class="search-td-condition">
						<select id="archiveStatus" name="archiveStatus">
							<option value="" >请选择 </option>
							<option value="0">未处理 </option>
							<option value="1">通过已归档</option>
							<option value="2">不通过驳回</option>
							<option value="3">不签约</option>
						</select>
					</div>
				</div>
				<%--<div class="search-td" style="width: 25%;margin-bottom:-5px;">
						<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">距离合同到期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="agreementBeginDate" name="agreementBeginDate" class="dateEditor" placeholder="请选择" style="width: 120px;" />天
						</div>
						<div class="l-panel-search-item" style="margin-left: 5px;margin-right: 5px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="agreementEndDate" name="agreementEndDate" class="dateEditor" placeholder="请选择" style="width: 120px;" />天
						</div>
				</div>
				<div class="search-td-search-btn" style="margin-top:-3px;">
					<div id="searchbtn" >搜索</div>
				</div>--%>
			</div>

			<div class="search-tr">

				<div class="search-td" style="float: left;width: 15%;">
					<div class="search-td-label">续签进度状态：</div>
					<div class="search-td-condition">
						<select id="renewProStatus" name="renewProStatus">
							<option value="">请选择</option>
							<option value="0">待申请</option>
							<option value="1">不再续签</option>
							<option value="2">待招商确认</option>
							<option value="3">待法务确认</option>
							<option value="4">招商不予续签</option>
							<option value="5">店铺暂停</option>
							<option value="6">开放续签入口</option>
							<option value="7">生成续签合同</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="float: left;width: 15%;">
					<div class="search-td-label">合同审核状态：</div>
					<div class="search-td-condition">
						<select id="auditStatus" name="auditStatus">
							<option value="">请选择</option>
							<option value="1">未上传</option>
							<option value="2">待审核</option>
							<option value="3" selected="selected">审核通过</option>
							<option value="4">驳回</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="float: left;width: 20%;">
					<div class="search-td-label">新合同审核状态：</div>
					<div class="search-td-condition">
						<select id="newAuditStatus" name="newAuditStatus">
							<option value="" >请选择</option>
							<option value="1">未上传</option>
							<option value="2">待审核</option>
							<option value="3">审核通过</option>
							<option value="4">驳回</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 32%;">
					<div class="search-td-label" style="float: left;width: 18%;">合同到期日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="endDate1" name="endDate1" class="dateEditor" value="${lastYearStart}" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endDate2" name="endDate2" class="dateEditor" value="${lastYearEnd}" style="width: 135px;" />
					</div>
				</div>


				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn" >搜索</div>

				</div>
			</div>



			</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
