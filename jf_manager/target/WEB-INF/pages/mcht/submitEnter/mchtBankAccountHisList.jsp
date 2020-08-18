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
     
     function toView(id) {
 		$.ligerDialog.open({
 			height: $(window).height(),
 			width: $(window).width() - 200,
 			title: "查看银行账号",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/mcht/viewMchtBankAccountHis.shtml?id=" + id,
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
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
	    		  
	    	      url:"/mcht/mchtBankAccountHisData.shtml",
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '提交日期', name: 'commitDate', width:180,render:function(rowdata,rowindex){
		    			        	  		if(rowdata.commitDate!=null){
			    			                	var commitDate=new Date(rowdata.commitDate);
			    			                	return commitDate.format("yyyy-MM-dd");
		    			        	  		}else{
		    			        	  			return "";
		    			        	  		}
		    			                }},   
	    			                { display: '帐号类型', name: 'accTypeDesc',width:180,render:function(rowdata,rowindex){
	    			        	  		if(rowdata.accType == '1'){
		    			                	return "对私账户";
	    			        	  		}else{
	    			        	  			return "对公账户";
	    			        	  		}
	    			                }},
	    			                { display: '开户行',  name: 'depositBank', width: 100}, 
	    			                { display: '帐号',  name: 'accNumber', width: 100}, 
	    			                { display: '状态',   name: 'status' , width: 180,render:function(rowdata,rowindex){
	    			                	if(rowdata.status =='1'){
	    			                		return "待审";
	    			                	}else if(rowdata.status =='2'){
	    			                		return "通过";
	    			                	}else{
		    							    return "驳回";
	    			                	}
	    			                }
	    			                },
    		 		                { display: "操作", name: "op",width:100 , render: function(rowdata, rowindex) {
    		 		                	return "<a href=\"javascript:toView(" + rowdata.id + ");\">查看</a>";
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
	    	  };
    </script>
  </head>
  
  <body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	 <form id="dataForm" runat="server">
	  	<input type="hidden" name="mchtId" value="${mchtId}">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
