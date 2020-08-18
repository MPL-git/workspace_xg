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
     
     function toView(id) {
    		$.ligerDialog.open({
    		height: $(window).height() - 40,
    		width: $(window).width() - 40,
    		title: "查看品牌更新信息",
    		name: "INSERT_WINDOW",
    		url: "${pageContext.request.contextPath}/mcht/viewMchtBrandChg.shtml?id=" + id,
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
	    		  
	    	      url:"/mcht/mchtBrandChgData.shtml",
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '品牌名称', name: 'commitDate', width:180,render:function(rowdata,rowindex){
		    			        	  		if(rowdata.commitDate){
			    			                	var commitDate=new Date(rowdata.commitDate);
			    			                	return commitDate.format("yyyy-MM-dd");
		    			        	  		}else{
		    			        	  			return "";
		    			        	  		}
		    			           }},   
	    			                { display: '资质类型', name: 'aptitudeType',width:180,render:function(rowdata,rowindex){
	    			        	  		if(rowdata.aptitudeType == 1){
	    			        	  			return "自有商标";
	    			        	  		}else if(rowdata.aptitudeType == 2){
	    			        	  			return "品牌商授权";
	    			        	  		}
	    			          		}},
	    			                { display: '授权期限', name: 'platformAuthExpDate',width:180,render:function(rowdata,rowindex){
	    			        	  		if(rowdata.platformAuthExpDate){
		    			                	var platformAuthExpDate=new Date(rowdata.platformAuthExpDate);
		    			                	return platformAuthExpDate.format("yyyy-MM-dd");
	    			        	  		}else{
	    			        	  			return "";
	    			        	  		}
	    			           		}},
	    			                { display: '法务审核状态',   name: 'auditStatus' , width: 180,render:function(rowdata,rowindex){
		    			                	if(rowdata.auditStatus =='0'){
		    			                		return "未提审";
		    			                	}else if(rowdata.auditStatus =='1'){
		    			                		return "待审";
		    			                	}else if(rowdata.auditStatus =='2'){
			    							    return "待定";
		    			                	}else if(rowdata.auditStatus =='3'){
		    			                		return "通过";
		    			                	}else if(rowdata.auditStatus =='4'){
		    			                		return "驳回";
		    			                	}
	    			                  	}
	    			                },
	    			                { display: '法务通过时间', name: 'commitDate', width:180,render:function(rowdata,rowindex){
	    			        	  		if(rowdata.commitDate!=null){
		    			                	var commitDate=new Date(rowdata.commitDate);
		    			                	return commitDate.format("yyyy-MM-dd");
	    			        	  		}else{
	    			        	  			return "";
	    			        	  		}
	    			           		}},
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
	  	<input type="hidden" name="mchtProductBrandId" value="${mchtProductBrandId}">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
	
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
