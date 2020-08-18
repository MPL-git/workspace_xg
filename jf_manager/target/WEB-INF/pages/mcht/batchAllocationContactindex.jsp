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
 $(function(){
	 
	 changePlatfromContactType();
	 
// 	 $("#export").bind('click',function(){
// 			var mcht_type = $("#mcht_type").val();
// 			var status = $("#status").val();
// 			var is_company_inf_perfect = $("#is_company_inf_perfect").val();
// 			var productType = $("#productType").val();
// 			var productBrandName = $("#productBrandName").val();
// 			var mcht_code = $("#mcht_code").val();
// 			var mcht_name = $("#mcht_name").val();
// 			location.href="${pageContext.request.contextPath}/mcht/export.shtml?mcht_type="+mcht_type+"&status="+status+"&is_company_inf_perfect="+is_company_inf_perfect+"&productType="+productType+"&productBrandName="+productBrandName+"&mcht_code="+mcht_code+"&mcht_name="+mcht_name;
// 		});

 });
 
 
 function changePlatfromContactType(){
	 var platfromContactType=$("#platfromContactType").val();
	 $("#platContactId").empty();
	 if(platfromContactType==""){
	 	$("#platContactId").append('<option value="">请选择...</option>');
		return;
	 }
	 $.ajax({ //ajax提交
			type:'POST',
			url:"${pageContext.request.contextPath}/platformContact/getPlatformContact.shtml",
			data:{contactType:platfromContactType},
			dataType:'json',
			cache: false,
			success: function(returnJson){
				if(returnJson.returnCode=='0000'){
					if(returnJson.platformContacts!=null&&returnJson.platformContacts.length>0){
						var platformContacts=returnJson.platformContacts;
						$("#platContactId").append('<option value="">请选择...</option>');
	              		for(var i=0;i<platformContacts.length;i++){
    				        $("#platContactId").append('<option value="'+platformContacts[i].id+'">'+platformContacts[i].contactName+'</option>');
	              		}
					}else {
						$("#platContactId").append('<option value="">请选择...</option>');
					}
				}

			},
			error: function(e){
			 commUtil.alertError("系统异常请稍后再试");
			}
	});
 }
 
 
 
 
 
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
	 
	 //平台对接人
	 function mchtPlatformContact(id){
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
 
	//批量更改对接人 	
	function allotPlatformContact(ids) {
		$.ligerDialog.open({
	 		height: 300,
			width: 450, 
			title: "批量更改对接人",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/allotPlatformContact.shtml?ids=" + ids,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}			
		
      var listConfig={
	  
      url:"/mcht/batchAllocationContactDataList.shtml",
      btnItems:[{text: '批量更改对接人', icon: 'customers', click: function(yes) {
    	  		var data = listModel.gridManager.getSelectedRows();
    	  		if(data.length == 0) 
    	  			$.ligerDialog.alert('对不起，请选择商家！');
    	  		else {
    	  			var str = "";
    	  			$(data).each(function() {
    	  				if(str == '')
    	  					str = this.id;
    	  				else
    	  					str += "," + this.id;
    	  			});
    	  			allotPlatformContact(str);
    	  		}
      		}
      	}
      ],
      listGrid:{ columns: [ 
   		                { display: '创建日期', name: 'status_date', width:120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.create_date!=null&&rowdata.create_date!=""){
   	   		                   var create_date=new Date(rowdata.create_date);
   	   		          	       return create_date.format("yyyy-MM-dd");	
   		                	}

		                }},
                           
                        { display: '对接人', name: 'zs_contact_name',width:100, render: function(rowdata, rowindex) {
                        	var platfromContactType = $("#platfromContactType").val();
                        	if(platfromContactType == '1') {
                        		return rowdata.zs_contact_name;
                        	}else if(platfromContactType == '2') {
                        		return rowdata.yy_contact_name;
                        	}else if(platfromContactType == '3') {
                        		return rowdata.dd_contact_name;
                        	}else if(platfromContactType == '4') {
                        		return rowdata.sh_contact_name;
                        	}else if(platfromContactType == '5') {
                        		return rowdata.cw_contact_name;
                        	}else if(platfromContactType == '6') {
                        		return rowdata.kf_contact_name;
                        	}else if(platfromContactType == '7') {
                        		return rowdata.fw_contact_name;
                        	}else if(platfromContactType == '8') {
                        		return rowdata.sj_contact_name;
                        	}
                        }}, 
                          
		                { display: '商家序号', name: 'mcht_code',width:100 }, 
		                { display: '公司名称',  name: 'company_name', width: 150 }, 
		                { display: '店铺名', name: 'shop_name' ,width: 200 },
		                { display: '主营类目', name: 'product_type_name' ,width: 180 },
 		                { display: '开通品牌', name: 'mcht_product_brand', width:180 ,render: function(rowdata, rowindex) {
 		                	if(rowdata.product_brand_null){
								return rowdata.mcht_product_brand+rowdata.product_brand_null;
 		                	}else{
								return rowdata.mcht_product_brand;
 		                	}
 		                }},
		              	{ display: "公司/经营信息", name: "OPER1", width: 100, align: "center", render: function(rowdata, rowindex) {
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
		              	}},
		              	{ display: "平台对接人", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:mchtPlatformContact(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    return html.join("");
		              	}},  
		              	{ display: "资质总状态", name: "total_audit_status_desc", width: 100},  
		              	{ display: "线上合同", name: "contract_audit_status_desc", width: 100},  
		              	{ display: "合同归档", name: "contract_archive_status_desc", width: 100},  
		              	{ display: '合作状态', name: 'status_desc', width: 100 }
		                ],   
                 showCheckbox : true,  //不设置默认为 true
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">序号：</div>
				<div class="search-td-condition">
				<input type="text" id ="mchtcodelist" name="mchtcodelist" >
				</div>
				</div>
			
				<div class="search-td">
			 	<div class="search-td-label">模式：</div>
			 	<div class="search-td-condition" >
				<select  id="mcht_type" name="mcht_type" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${mcht_type}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
				</div>
				</div>
		
				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" id ="mcht_name" name="mcht_name" >
				</div>
				</div>
		
				<div class="search-td">
			 	<div class="search-td-label" >资质总状态：</div>
			 	<div class="search-td-condition" >
				<select id="total_audit_status" name="total_audit_status" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${total_audit_status_list}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
				</div>
		 	 	</div>
				
		 	 	
			</div>
		
			<div class="search-tr"  >
			
			<div class="search-td">
			 	<div class="search-td-label" >线上合同：</div>
			 	<div class="search-td-condition" >
				<select id="contract_audit_status" name="contract_audit_status" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${contract_audit_status_list}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
				</div>
		 	</div>
			<div class="search-td">
			 	<div class="search-td-label" >合同归档：</div>
			 	<div class="search-td-condition" >
				<select id="contract_archive_status" name="contract_archive_status" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${contract_archive_status_list}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
				</div>
		 	</div>
			<div class="search-td">
			 	<div class="search-td-label" >合作状态：</div>
			 	<div class="search-td-condition" >
				<select id="status" name="status" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${status}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
				</div>
		 	</div>
		 	
		 	<div class="search-td" style="position: relative;">
			 	<div class="search-td-label" >对接人：</div>
			 	<div style="display: inline-block;width: 80%;position: absolute;" >
					<select style="width:48%;" id="platfromContactType" name="platfromContactType" class="querysel" onchange="changePlatfromContactType();">
						<c:forEach var="list" items="${platform_contact_type_list}">
							<%-- <option value="${list.statusValue}" <c:if test="${list.statusDesc == 1 }">selected</c:if> >${list.statusDesc}</option> --%>
							<option value="${list.statusValue}" >${list.statusDesc}</option>
						</c:forEach>
					</select>
					<select style="width:48%;" id="platContactId" name="platContactId" class="querysel">
						<option value="">请选择</option>
					</select>
				</div>
		 	</div>
			</div>
			
			<div class="search-tr"  >
				<div class="search-td">
				 	<div class="search-td-label" >主营类目：</div>
				 	<div class="search-td-condition" >
				 		<c:if test="${isCwOrgStatus == 1 }">
				 			<select id="productTypeId" name="productTypeId" class="querysel" disabled="disabled">
								<c:forEach var="productType" items="${productTypes }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
				 		</c:if>
				 		<c:if test="${isCwOrgStatus == 0 }">
				 			<select id="productTypeId" name="productTypeId" class="querysel">
								<option value="">请选择</option>
								<c:forEach var="productType" items="${productTypes }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
				 		</c:if>
					</div>
			 	</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌名称：</div>
					<div class="search-td-condition" >
						<input type="text" id="productBrandName" name="productBrandName" >
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
