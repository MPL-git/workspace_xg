<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    
    <link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
     <script type="text/javascript">
    $(function(){
    	$("#export").bind('click',function(){
    		var search_status = $("#search_status").val();
    		var search_accType = $("#search_accType").val();
    		var search_companyName = $("#search_companyName").val();
    		var length = $("input[name='is_my_audit']:checked").length;
    		var is_my_audit="0";
    		if(length>0){
	    		is_my_audit = "1";
    		}
    		location.href="${pageContext.request.contextPath}/mcht/exportMchtBankAccount.shtml?search_status="+search_status+"&search_accType="+search_accType+"&search_companyName="+search_companyName+"&is_my_audit="+is_my_audit;
    	});
    });
     
    $(function(){
    	$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		});
    	  	
    });
    
     function getAuditCW(id){
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
     
     function toBaseInfoAudit(mchtId){
         $.ligerDialog.confirm('首次审核，请在公司信息详情页中审核。', function (yes)
                 {
                     if(yes){
                    	 editMchtBaseInfo(mchtId); 
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


     function auditMchtBankAccount(mchtBankAccountId) {
    	 
    		$.ligerDialog.open({
    			height: 600,
    			width: 800,
	    		title: "商家银行帐号审核",
	    		name: "INSERT_WINDOW",
	    		url: "${pageContext.request.contextPath}/mcht/mchtBankAccountAudit.shtml?id=" + mchtBankAccountId,
	    		showMax: true,
	    		showToggle: false,
	    		showMin: false,
	    		isResize: true,
	    		slide: false,
	    		data: null,
	    		id:"audit_dialog"
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
 	
 	
 	
 //批量审核
function editmchtFinanceInfo(mchtId) {
	var title="确认审核通过";
  $.ligerDialog.confirm("是否"+title+"？", function (yes){
  if (yes) {
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/editmchtFinanceInfo.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"mchtId" : mchtId},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
			    $.ligerDialog.success("审核通过成功!");
			    $("#searchbtn").click();
			} else {
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
		 
     
     function viewerMchtPic(mchtProductBrandId,picType){
    		
    		var url;
    		if(picType==1){
    			url="${pageContext.request.contextPath}/mcht/getMchtBrandPicChg.shtml";
    		}
    		if(picType==2){
    			url="${pageContext.request.contextPath}/mcht/getPlatfromAuthPicChg.shtml";
    		}
    		
    		$("#viewer-pictures").empty();
    		viewerPictures.destroy();
    		$.ajax({
    			url : url,
    			type : 'POST',
    			dataType : 'json',
    			cache : false,
    			async : false,
    			data : {mchtProductBrandId:mchtProductBrandId},
    			timeout : 30000,
    			success : function(data) {
    				console.log(data);
    				if(data&&data.length>0){
    					for (var i=0;i<data.length;i++)
    					{
    						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
    					}
    					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    						$("#viewer-pictures").hide();
    					}});
    					$("#viewer-pictures").show();
    					viewerPictures.show();
    				}
    				
    				
    			},
    			error : function() {
    				$.ligerDialog.error('操作超时，请稍后再试！');
    			}
    		});
    		
    	}
     
	    var listConfig={
	    		  
	    	    url:"/mcht/mchtBankCheckDataList.shtml",
	    	      
	    	    btnItems:[{text: '批量领取',icon: 'modify',click: function() {
                var data = listModel.gridManager.getSelectedRows();
               	if (data.length == 0) {
               		commUtil.alertError('请选择行！');
               	}else {
                    var str = "";                         
                   	$(data).each(function() {    
                 	  	if(str=='') {
                 		  str = this.mchtId ;
                 	     }else {
                 		  		str += ","+ this.mchtId ;
                 	  	}
                   	});
                   	getAuditCW(str); 
               	}
               }},
			   {line:true},
          	   {text: '批量审核',icon: 'modify',click: function() {
  			  	var data = listModel.gridManager.getSelectedRows();
               	if (data.length == 0) {
               		commUtil.alertError('请选择行！');
               	}else {
                    var str = "";                         
                   	$(data).each(function() {    
                 	  	if(str=='') {
                 		  str = this.mchtId ;
                 	     }else {
                 		  		str += ","+ this.mchtId ;
                 	  	}
                   	});
                   	editmchtFinanceInfo(str); 
               	}
  			   }}
			   ],
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '提审日期', name: 'commitDate', width:180,render:function(rowdata,rowindex){
		    			        	  		if(rowdata.commitDate!=null){
			    			                	var commitDate=new Date(rowdata.commitDate);
			    			                	return commitDate.format("yyyy-MM-dd");
		    			        	  		}else{
		    			        	  			return "";
		    			        	  		}
		    			                }},   
		    			           // { display: '商家序号', name: 'mchtCode',width:80},
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
		    			           { display: '入驻类型', width: 80,render: function(rowdata, rowindex) {
		    			            	var html = [];
		    			            	if(rowdata.settledType == "1"){
		    			            		html.push("企业公司");
		    			            	}else if(rowdata.settledType == "2"){
		    			            		html.push("个体商户");
		    			            	}
		    			            	return html.join("");
		    			         	}},
	    	                       { display: '公司名称', name: 'companyName',width:180 , render: function(rowdata, rowindex){
					  					var html = [];
					  					 html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>"); 
					  					return html.join("");
				  					}},  
	    			                { display: '最新合同编号', name: 'contractCode',width:180},
	    			                { display: '帐号类型', name: 'accTypeDesc',width:180},
	    			                { display: '账户名', name: 'accName',width:180},
	    			                { display: '开户银行',  name: 'bankName', width: 100}, 
	    			                { display: '开户支行', name: 'depositBank', width: 180 },
	    			                { display: '帐号',  name: 'accNumber', width: 100}, 
	    			                { display: '状态',   name: 'status' , width: 180,render:function(rowdata,rowindex){
	    			                	var html = [];
    			                		var role107 = ${role107};
	    			                	if(rowdata.cwStaffId==${sessionScope.USER_SESSION.staffID} || role107){
		    			                	html.push("<a href=\"javascript:editMchtFinanceInfo(" + rowdata.mchtId + ");\">"+rowdata.statusDesc+"</a>");
	    			                	}else{
	    			                		html.push(rowdata.statusDesc);
	    			                	}
	    							    return html.join("");
	    			                }
	    			                },
    		 		                { display: "财务对接人", name: "cwContactName",width:100 , render: function(rowdata, rowindex) {
		 		                		if(!rowdata.cwContactName && rowdata.status=="0"){
    		 		                		return "<a href=\"javascript:getAuditCW(" + rowdata.mchtId + ");\">领取</a>";
    		 		                	}else{
    		 		                		return rowdata.cwContactName;
    		 		                	}
    				              	}}
//	    			                ,
//    				              	{ display: "财务对接人", width:100 , render: function(rowdata, rowindex) {
//   		 		                	return "<a href=\"javascript:getAuditCW(" + rowdata.mchtId + ");\">领取</a>";
//    				              	}}
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
  
  <body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
  
	  <form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" style="float:left;">提交日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="commit_date_begin" name="commit_date_begin"/>
						<script type="text/javascript">
							$(function() {
								$("#commit_date_begin").ligerDateEditor( {
									showTime : false,
									labelWidth : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
				</div>
			
				<div class="search-td">
					<div class="l-panel-search-item">
						<input type="text" id="commit_date_end" name="commit_date_end"/>
						<script type="text/javascript">
							$(function() {
								$("#commit_date_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									labelAlign : 'left'
								});
							});
						</script>	
					</div>
				</div>
					 
				 <div class="search-td">
				 <div class="search-td-label" >审核状态:</div>
				 <div class="search-td-condition" >
					<select id="search_status" name="search_status"
						class="querysel">
						<!-- <option value="">--请选择--</option>  -->
						<c:forEach var="list" items="${statusDescList}">
							<option value="${list.statusValue}">${list.statusDesc}
							</option>
						</c:forEach>
					</select>
			 	 </div>
			 	 </div>
				 	
				 <div class="search-td">
				 <div class="search-td-label">帐号类型:</div>
				 <div class="search-td-condition" >
					 <select  id="search_accType" name="search_accType"
						class="querysel">
						<option value="">--请选择--</option>
						<c:forEach var="list" items="${accTypeDescList}">
							<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:forEach>
					</select>
				 </div>
				 </div>
				
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
				<div class="search-td-label">公司名称:</div>
				<div class="search-td-condition" >
					<input type="text" id = "search_companyName" name="search_companyName" >
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
			<!-- 
				<div class="search-td">
			 	<div class="search-td-label" >只看本人财务对接</div>
			 	<div class="search-td-condition" style="text-align: left;">
					<input type="checkbox" value="1" name="is_my_audit" style="width: 0;">
				</div>
				</div>
			 -->		
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div>
			<!-- 
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="导出" id="export">
					</div>
			-->
				</div>
			</div>
				
			
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
