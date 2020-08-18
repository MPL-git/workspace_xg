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
		
	function zsAudit(id) {
		var role107 = ${role107};
		if(role107){
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width() - 200,
				title: "招商审核",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/toMchtInfoZsAudit.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}else{
			$.ajax({
				url : "${pageContext.request.contextPath}/mchtContract/checkZsPlatformContact.shtml",
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
							title: "招商审核",
							name: "INSERT_WINDOW",
							url: "${pageContext.request.contextPath}/mcht/toMchtInfoZsAudit.shtml?mchtId=" + id,
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
	  
      url:"/mcht/zsSettledInfoConfirmData.shtml",
      listGrid:{ columns: [ 
   		                { display: '商家提审日期', name: 'zs_commit_audit_date', width:120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.zs_commit_audit_date!=null&&rowdata.zs_commit_audit_date!=""){
   	   		                   var zs_commit_audit_date=new Date(rowdata.zs_commit_audit_date);
   	   		          	       return zs_commit_audit_date.format("yyyy-MM-dd hh:mm:ss");	
   		                	}

		                }},
                           
                        { display: '招商对接人', name: 'zs_contact_name',width:100 }, 
		                // { display: '商家序号', name: 'mcht_code',width:100 },
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
		                //{ display: '商家简称',  name: 'short_name', width: 150 }, 
		                { display: '公司名称',  name: 'company_name', width: 150 , render: function(rowdata, rowindex) {
							var html = [];
							html.push("<span style='color:red;'>["+rowdata.is_company_inf_perfect_desc+"]</span>");
							html.push(rowdata.company_name);
						    return html.join("");
		              	}}, 
		                { display: '店铺名', name: 'shop_name' ,width: 200 , render: function(rowdata, rowindex) {
		        			var html = [];
							html.push("<span style='color:red;'>["+rowdata.shop_name_audit_status_desc+"]</span>");
							html.push(rowdata.shop_name);
						    return html.join("");
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
		              	{ display: "招商总审状态", width: 100,render: function(rowdata, rowindex) {
							if(rowdata.zs_total_audit_status == "4"){
								return "未提交";
							}else if(rowdata.zs_total_audit_status == "0"){//已提交
								if(!rowdata.is_zs_total_audit_re_commit || rowdata.is_zs_total_audit_re_commit == "0"){//首次提审
									return "待审(首次提审)";
								}else if(rowdata.is_zs_total_audit_re_commit == "1"){//重新提审
									return "待审(重新提审)";
								}
							}else if(rowdata.zs_total_audit_status == 2){
								return "通过";
							}else if(rowdata.zs_total_audit_status == 3){
								return "驳回";
							}else if(rowdata.zs_total_audit_status == 5){
								return "不签约";
							}else if(rowdata.zs_total_audit_status == 6){
								return "黑名单";
							}
		              	}},
		              	{ display: "未通过的品牌", width: 80,render: function(rowdata, rowindex) {
							return rowdata.brand_names;
		              	}},
		              	{ display: "我的备注", name: "zs_total_audit_remarks", width: 150, align: "center"},
		              	{ display: "操作", name: "op", width: 150, align: "center",render: function(rowdata, rowindex) {
							var html = [];
							var isZs = $("#isZs").val();
							var role107 = ${role107};
							if(role107){
								html.push("<a href=\"javascript:zsAudit(" + rowdata.id + ");\">招商审核</a>&nbsp;&nbsp;"); 
							}else{
								if(rowdata.zs_total_audit_status!=4 && isZs && rowdata.zs_total_audit_status!=2){//不等于未提交且是招商
									html.push("<a href=\"javascript:zsAudit(" + rowdata.id + ");\">招商审核</a>&nbsp;&nbsp;"); 
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
    					data : {
    						id:id
    					},
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
	<input type="hidden" value="${myContactId}" id="isZs">
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				
			<div class="search-td">
			<div style="float:left;">商家提审日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="zs_commit_audit_date_begin" name="zs_commit_audit_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#zs_commit_audit_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="zs_commit_audit_date_end" name="zs_commit_audit_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#zs_commit_audit_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
				
				<div class="search-td">
				<div class="search-td-label">商家序号：</div>
				<div class="search-td-condition">
				<input type="text" id ="mcht_code" name="mcht_code" >
				</div>
				</div>
				
<!-- 				<div class="search-td"> -->
<!-- 			 	<div class="search-td-label">模式：</div> -->
<!-- 			 	<div class="search-td-condition" > -->
<!-- 				<select  id="mcht_type" name="mcht_type" class="querysel"> -->
<!-- 					<option value="">请选择</option> -->
<!-- 					<c:forEach var="list" items="${mcht_type}"> -->
<!-- 						<option value="${list.STATUS_VALUE}">${list.STATUS_DESC} -->
<!-- 						</option> -->
<!-- 					</c:forEach> -->
<!-- 				</select> -->
<!-- 				</div> -->
<!-- 				</div> -->
		
				<div class="search-td">
				<div class="search-td-label"  >名称：</div>
				<div class="search-td-condition" >
				<input type="text" id ="mcht_name" name="mcht_name" >
				</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
				  <div class="search-td-label">对接人：</div>
				    <div class="search-td-condition" >
					<select id="platContactId" name="platContactId" >
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
				  <div class="search-td-label">招商总审状态：</div>
				    <div class="search-td-condition" >
					<select id="zs_total_audit_status" name="zs_total_audit_status" >
						<option value="" >请选择</option>
						<option value="4" >未提交</option>
						<option value="0" <c:if test="${zsTotalAuditStatus == '0'}">selected="selected"</c:if>>待审</option>
						<option value="2" >通过</option>
						<option value="3" >驳回</option>
						<option value="5" >不签约</option>
						<option value="6" >黑名单</option>
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
					<div class="search-td-label" style="float:left;">是否自营：</div>
					<div class="search-td-condition" >
						<select id="isManageSelf" name="isManageSelf" style="width: 150px;">
							<option value="">请选择</option>
							<option value="0">非自营</option>
							<option value="1">自营</option>
						</select>
					</div>
				</div>
				
				<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
		</div>
		
		
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
