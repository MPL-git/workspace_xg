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
		 $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		 });
		
	 });
 
	//查看商家联系人
	/* function mchtContact(id){
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
	 } */
	 
	 //平台对接人
	 /* function mchtPlatformContact(id){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width(),
			title: "平台分配对接人",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtplatformcontact.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	} */
	 
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
		
	function editMchtFinanceInfo(id) {
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
 
	//不签约
	function toNotContract(id){
		$.ligerDialog.open({
			height: $(window).height()*0.5,
			width: $(window).width()*0.5,
			title: "不签约",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/toNotContract.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}	
	
	function audit(id){
		var role107 = ${role107};
		if(role107){
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width() - 200,
				title: "法务审核",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/toMchtInfoAudit.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}else{
			$.ajax({
				url : "${pageContext.request.contextPath}/mchtContract/checkFwPlatformContact.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					mchtId:id
				},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$.ligerDialog.open({
							height: $(window).height(),
							width: $(window).width() - 200,
							title: "法务审核",
							name: "INSERT_WINDOW",
							url: "${pageContext.request.contextPath}/mcht/toMchtInfoAudit.shtml?mchtId=" + id,
							showMax: true,
							showToggle: false,
							showMin: false,
							isResize: true,
							slide: false,
							data: null
						});
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	}
	
    var listConfig={
      url:"/mcht/totalAuditDataList.shtml",
      listGrid:{ columns: [ 
		            { display: '提审日期', name: 'status_date', width:160 ,render: function(rowdata, rowindex) {
	                	if(rowdata.commit_audit_date!=null&&rowdata.commit_audit_date!=""){
   		                   var commit_audit_date=new Date(rowdata.commit_audit_date);
   		          	       return commit_audit_date.format("yyyy-MM-dd");	
	                	}
	                }},
                    { display: '招商对接人', name: 'zs_contact_name',width:80 }, 
	                // { display: '商家序号', name: 'mcht_code',width:80 },
				    { display: '商家序号',width: 100, render: function(rowdata, rowindex) {
					  var html = [];
					  html.push(rowdata.mcht_code);
					  html.push("<br>");
					  if(rowdata.mcht_type == "2"){
						  html.push("POP");
					  }else if (rowdata.mcht_type == "1" && rowdata.is_manage_self == "1"){
						  html.push("自营SPOP");
					  }else {
						  html.push("SPOP");
					  }
					  return html.join("");
				    }},
	                { display: '入驻类型', width: 80,render: function(rowdata, rowindex) {
		            	var html = [];
		            	if(rowdata.settled_type == "1"){
		            		html.push("企业公司");
		            	}else if(rowdata.settled_type == "2"){
		            		html.push("个体商户");
		            	}
		            	return html.join("");
		         	}},
	                { display: '公司名称',  name: 'company_name', width: 150 , render: function(rowdata, rowindex) {
						var html = [];
						html.push("<span style='color:red;'>["+rowdata.is_company_inf_perfect_desc+"]</span>");
						html.push(rowdata.company_name);
					    return html.join("");
	              	}}, 
	                { display: '店铺名称', name: 'shop_name' ,width: 200 , render: function(rowdata, rowindex) {
	        			var html = [];
						html.push("<span style='color:red;'>["+rowdata.shop_name_audit_status_desc+"]</span>");
						html.push(rowdata.shop_name);
					    return html.join("");
	              	}},
	                { display: '主营类目', name: 'product_type_name' ,width: 100 },
	                { display: '品牌', name: 'fw_brand_names' ,width: 180 },
	                { display: '保证金', name: 'contract_deposit' ,width: 100},
	              	{ display: "查看详情", name: "OPER1", width: 160, align: "center", render: function(rowdata, rowindex) {
						var html = [];
					    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">【公司信息】</a>"); 
					    html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.id + ");\">【财务信息】</a>"); 
					    return html.join("");
	              	}},
	              	/* { display: "公司/经营信息", name: "OPER1", width: 100, align: "center", render: function(rowdata, rowindex) {
						var html = [];
					    html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
					    return html.join("");
	              	}},
	              	{ display: "财务信息", name: "OPER2", width: 100, align: "center", render: function(rowdata, rowindex) {
						var html = [];
					    html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
					    return html.join("");
	              	}},
	              	{ display: "联系人信息", name: "OPER3", width: 100, align: "center", render: function(rowdata, rowindex) {
						var html = [];
					    html.push("<a href=\"javascript:mchtContact(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
					    return html.join("");
	              	}}, */
	              	{ display: "资质总审状态", name: "total_audit_status_desc", width: 80, align: "center", render: function(rowdata, rowindex) {
	                	if(!rowdata.total_audit_status || rowdata.total_audit_status == 4){
	                		return "未提交";
	                	}else if(rowdata.total_audit_status == 0){
	                		if(!rowdata.is_total_audit_re_commit || rowdata.is_total_audit_re_commit == 0){
		                		return "待审（首次提审）";
	                		}else{
		                		return "待审（重新提审）";
	                		}
	                	}else if(rowdata.total_audit_status == 2){
	                		return "通过";
	                	}else if(rowdata.total_audit_status == 3){
	                		return "驳回";
	                	}else if(rowdata.total_audit_status == 5){
	                		return "不签约";
	                	}else if(rowdata.total_audit_status == 6){
	                		return "黑名单";
	                	}
	              	}},
	                { display: "法务对接人", name: "fw_contact_name",width:80 , render: function(rowdata, rowindex) {
	                	if(!rowdata.fw_contact_name && ${isCanGet}){
	                		return  "<a href=\"javascript:getAudit(" + rowdata.id + ");\">领取</a>&nbsp;&nbsp;";
	                	}else{
	                		return rowdata.fw_contact_name;
	                	}
	              	}},
	              	{ display: "操作", width:100 , render: function(rowdata, rowindex) {
	              		var html = [];
	              		var role107 = ${role107};
              			if(role107){
			                html.push('<a href="javascript:;" onclick="audit('+rowdata.id+');">法务审核</a>');
              			}else{
		              		if(rowdata.fw_contact_name && rowdata.total_audit_status != 2 && ${isCanGet}){
				                html.push('<a href="javascript:;" onclick="audit('+rowdata.id+');">法务审核</a>');
		              		}
              			}
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
      
    function getAudit(id){
  	  	$.ligerDialog.confirm('确认领取？', function (yes) {
  			if(yes){
  				$.ajax({
  					url : "${pageContext.request.contextPath}/mcht/getAudit.shtml",
  					type : 'POST',
  					dataType : 'json',
  					cache : false,
  					async : false,
  					data : {id:id},
  					timeout : 30000,
  					success : function(data) {
  						if ("0000" == data.returnCode) {
  							$.ligerDialog.success("领取成功!");
  							$("#searchbtn").click();
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
      

      
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" id="loginStaffID" value="${loginStaffID}">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input type="text" id ="mcht_code" name="mcht_code" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >名称：</div>
					<div class="search-td-condition" >
						<input type="text" id ="mcht_name" name="mcht_name" >
					</div>
				</div>
		 	 	<div class="search-td">
				 	<div class="search-td-label" >公司信息状态：</div>
				 	<div class="search-td-condition" >
						<select  id="is_company_inf_perfect" name="is_company_inf_perfect" class="querysel">
							<option value="">请选择</option>
							<option value="0">未填写</option>
							<option value="2">已填</option>
							<option value="3">待审核</option>
							<option value="1">通过</option>
							<option value="4">驳回</option>
							
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
			</div>
			<div class="search-tr"  >
				<div class="search-td">
				 	<div class="search-td-label" >资质总审核状态：</div>
				 	<div class="search-td-condition" >
						<select  id="total_audit_status" name="total_audit_status" class="querysel">
							<option value="">请选择</option>
							<option value="4" <c:if test="${totalAuditStatus == '4'}">selected="selected"</c:if>>未提交</option>
							<option value="0" <c:if test="${totalAuditStatus == '0'}">selected="selected"</c:if>>待审</option>
							<option value="2" <c:if test="${totalAuditStatus == '2'}">selected="selected"</c:if>>通过</option>
							<option value="3" <c:if test="${totalAuditStatus == '3'}">selected="selected"</c:if>>驳回</option>
							<option value="5" <c:if test="${totalAuditStatus == '5'}">selected="selected"</c:if>>不签约</option>
							<option value="6" <c:if test="${totalAuditStatus == '6'}">selected="selected"</c:if>>黑名单</option>
						</select>
					</div>
				</div>
				<div class="search-td">
				 	<div class="search-td-label" >类目：</div>
				 	<div class="search-td-condition" >
				 		<c:if test="${isCwOrgStatus == 1 }">
				 			<select  id="productTypeId" name="productTypeId" class="querysel" disabled="disabled">
								<c:forEach items="${productTypes}" var="productType">
									<option value="${productType.id}">${productType.name}</option>
								</c:forEach>
							</select>
				 		</c:if>
				 		<c:if test="${isCwOrgStatus == 0 }">
				 			<select  id="productTypeId" name="productTypeId" class="querysel">
								<option value="">请选择</option>
								<c:forEach items="${productTypes}" var="productType">
									<option value="${productType.id}">${productType.name}</option>
								</c:forEach>
							</select>
				 		</c:if>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">提审日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="commit_audit_date_begin" name="commit_audit_date_begin" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="commit_audit_date_end" name="commit_audit_date_end" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
				 	<div class="search-td-label" >招商对接人：</div>
				 	<div class="search-td-condition" >
				 		<c:if test="${isAssistant == true }">
				 			<select  id="zs_platform_contact_id" name="zs_platform_contact_id" class="querysel" >
								<option value="">全部商家</option>
								<c:forEach items="${zsPlatformContactCustomList }" var="zsPlatformContactCustom">
									<option value="${zsPlatformContactCustom.id }">${zsPlatformContactCustom.staffName }的商家</option>
									<c:if test="${not empty zsPlatformContactCustom.assistantStaffName }">
										<option value="${zsPlatformContactCustom.assistantid }">${zsPlatformContactCustom.assistantStaffName }的商家</option>
									</c:if>
								</c:forEach>
							</select>
				 		</c:if>
				 		<c:if test="${isAssistant == false }">
				 			<select  id="zs_platform_contact_id" name="zs_platform_contact_id" class="querysel" >
								<option value="">全部商家</option>
								<c:forEach items="${zsPlatformContactCustomList }" var="zsPlatformContactCustom">
									<option value="${zsPlatformContactCustom.id }">${zsPlatformContactCustom.staffName }的商家</option>
								</c:forEach>
							</select>
				 		</c:if>
					</div>
				</div>
				<div class="search-td">
				 	<div class="search-td-label" >法务对接人：</div>
				 	<div class="search-td-condition" >
			 			<select  id="fw_platform_contact_id" name="fw_platform_contact_id" class="querysel" >
							<option value="">全部商家</option>
							<c:forEach items="${fwPlatformContactCustomList }" var="fwPlatformContactCustom">
								<option value="${fwPlatformContactCustom.id }">${fwPlatformContactCustom.staffName }的商家</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
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
			 	<div class="search-td-label" >只看本人领取</div>
			 	<div class="search-td-condition" style="text-align: left;width: 5%;">
					<input type="checkbox" value="1" name="is_my_audit">
				</div>
				</div>
				
				<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
