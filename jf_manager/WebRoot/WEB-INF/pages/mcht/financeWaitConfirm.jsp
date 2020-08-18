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
 
      var listConfig={
	  
      url:"/mcht/shopToOprativeDataList.shtml",
      listGrid:{ columns: [ 
   		                { display: '创建日期', name: 'create_date', width:120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.create_date!=null&&rowdata.create_date!=""){
   	   		                   var create_date=new Date(rowdata.create_date);
   	   		          	       return create_date.format("yyyy-MM-dd");	
   		                	}

		                }},
                           
                        { display: '招商对接人', name: 'zs_contact_name',width:100 }, 
		                { display: '商家序号', name: 'mcht_code',width:100 },
		                { display: '入驻类型',width: 80, render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.settled_type == "1"){
			            		html.push("企业公司");
			            	}else if(rowdata.settled_type == "2"){
			            		html.push("个体商户");
			            	}
			            	return html.join("");
			         	}},
		               // { display: '商家简称',  name: 'short_name', width: 150 }, 
		                { display: '公司名称',  name: 'company_name', width: 150 , render: function(rowdata, rowindex) {
							var html = [];
							html.push("<span style='color:red;'>["+rowdata.is_company_inf_perfect_desc+"]</span>");
							html.push(rowdata.company_name);
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
// 		              	{ display: "对接人", name: "OPER4", width: 100, align: "center", render: function(rowdata, rowindex) {
// 							var html = [];
// 						    html.push("<a href=\"javascript:mchtPlatformContact(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
// 						    return html.join("");
// 		              	}},
   		                { display: '线上合同日期', name: 'contract_audit_date', width:120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.contract_audit_date!=null&&rowdata.contract_audit_date!=""){
   	   		                   var contract_audit_date=new Date(rowdata.contract_audit_date);
   	   		          	       return contract_audit_date.format("yyyy-MM-dd");	
   		                	}

		                }},
		                { display: '保证金总额', name: 'contract_audit_date', width:120 ,render: function(rowdata, rowindex) {
   		                	return '<span style="color:red;">'+rowdata.total_amt+'</span>';
		                }},
   		                { display: '应缴金总额', name: 'contract_audit_date', width:120 ,render: function(rowdata, rowindex) {
   	   		          	    return rowdata.unpay_deposit_amt;	
		                }},
   		                { display: '已缴金总额', name: 'contract_audit_date', width:120 ,render: function(rowdata, rowindex) {
   	   		          	    return rowdata.mcht_deposit_pay_amt;	
		                }},
		                { display: '保证金状态',  name: 'id', width: 150, render: function(rowdata, rowindex) {
		                	var html = [];
							if(rowdata.total_amt == rowdata.contract_deposit){
								if(rowdata.unpay_deposit_amt>0){
							   		html.push("不足,");
							   		if(rowdata.deposit_type==1){
							   			html.push("可抵缴");
							   		}else{
							   			html.push("不可抵缴");
							   		}
							   	}else{
							   		html.push("正常");
							   	}
							}else{
								html.push("异常");
							}
						    return html.join("");
		              	} }, 
 		                { display: "财务对接人", name: "cw_contact_name",width:100 , render: function(rowdata, rowindex) {
 		                	if((rowdata.cw_contact_name==null||rowdata.cw_contact_name=="")&&${isCanGet}){
 		                		return "<a href=\"javascript:getAudit(" + rowdata.id + ");\">领取</a>";
 		                	}else{
 		                		return rowdata.cw_contact_name;
 		                	}
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
    					url : "${pageContext.request.contextPath}/mcht/getAuditCw.shtml",
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" name="financeConfirmStatus" value="${financeConfirmStatus}">
		<div class="search-pannel">
			<div class="search-tr"  >
			
				<div class="search-td">
				<div class="search-td-label">序号：</div>
				<div class="search-td-condition">
				<input type="text" id ="mcht_code" name="mcht_code" >
				</div>
				</div>
			
				<div class="search-td">
			 	<div class="search-td-label">模式：</div>
			 	<div class="search-td-condition" >
				<select  id="mcht_type" name="mcht_type" class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${mcht_type}">
						<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}
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
				<div class="search-td-label">保证金状态：</div>
				<div class="search-td-condition" >
					<select id="deposit_status" name="deposit_status" class="querysel">
					<option value="">请选择</option>
					<option value="1">不足，不可抵缴</option>
					<option value="2">不足，可抵缴</option>
					<option value="3">正常</option>
					<option value="4">异常</option>
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
