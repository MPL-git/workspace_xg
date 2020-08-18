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
 		$("#export").bind('click', function(){
			var mchtCode = $("#mchtCode").val();
			var companyName = $("#companyName").val();
			var isMchtSend = $("#isMchtSend").val();
			var contractArchiveStatus = $("#contractArchiveStatus").val();
			location.href="${pageContext.request.contextPath}/mcht/close/exportTerminationAgreement.shtml?mchtCode="+mchtCode
					+"&companyName="+companyName+"&isMchtSend="+isMchtSend+"&contractArchiveStatus="+contractArchiveStatus
		});	
	 });

     
     
	// 查看商家信息
	function viewMchtInfo(id) {
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
	};
	
	//修改法务备注
	function reviseRemarks(mchtCloseId){
 		$.ajax({
 			url : "${pageContext.request.contextPath}/mcht/close/checkFwPlatformContact.shtml",
 			type : 'POST',
 			dataType : 'json',
 			cache : false,
 			async : false,
 			data : {
 				mchtCloseId:mchtCloseId
 			},
 			timeout : 30000,
 			success : function(data) {
 			if ("0000" == data.returnCode) {
			$.ligerDialog.open({
				height: 400,
				width: 600,
				title: "修改备注",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/reviseRemarks.shtml?mchtCloseId=" + mchtCloseId,
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
	};
	
	
	//归档未处理
    function processingFiling(mchtCloseId) {
     		$.ajax({
     			url : "${pageContext.request.contextPath}/mcht/close/checkFwPlatformContact.shtml",
     			type : 'POST',
     			dataType : 'json',
     			cache : false,
     			async : false,
     			data : {
     				mchtCloseId:mchtCloseId
     			},
     			timeout : 30000,
     			success : function(data) {
     				if ("0000" == data.returnCode) {
     					$.ligerDialog.open({
     						height: $(window).height() - 40,
     						width: $(window).width() - 40,
     						title: "处理归档",
     						name: "INSERT_WINDOW",
     						url: "${pageContext.request.contextPath}/mcht/processingFiling.shtml?mchtCloseId=" + mchtCloseId,
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
 
   
   
	    var listConfig={
	    		  
	    	      url:"/mcht/terminationAgreementListData.shtml?progressStatus=5",
	    	      
	    	      listGrid:{ columns: [
	    	                           
 		    			          { display: '开通日期', name: 'openingDate', width:100,render:function(rowdata,rowindex){
		    			        	  	if(rowdata.openingDate){
		    			                	var openingDate=new Date(rowdata.openingDate);
		    			                	return openingDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }},
		    			           
		    			            { display: '招商对接人', name: 'platformMerchantsContact',width:180 },
		    			            
		    			            { display: '商家序号',   width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					html.push(rowdata.mchtCode+"<br>");
					  					return html.join("");
				  					}},
		    			           
					                { display: '公司名称',  name: 'companyInfo', width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					html.push(rowdata.companyName+"<br>");
					  					return html.join("");
				  					}}, 
				  					
				  				   { display: '店铺名称',  name: 'companyInfo', width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					html.push(rowdata.shopName);
					  					return html.join("");
				  					}},
				  				  { display: "公司/经营信息", name: "OPER1", width: 100, align: "center", render: function(rowdata, rowindex) {
				  					var html = [];
				  					html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
				  					return html.join("");
				  			  }},
	    			            
							  { display: "商家寄件状态", name: "isMchtSendStr", width: 100, align: "center", render: function(rowdata, rowindex) {
								  var expressNo = rowdata.expressNo
								  var html=[];
								  if(expressNo == null || expressNo == ''){
									  html.push("未寄出");
								  }else{
									  html.push("已寄出");
								  }
								  return html.join("");
							  }},
	    			               
    			                { display: '协议归档状态', name: 'contractArchiveStatus', width:350, render:function(rowdata,rowindex){
    			                	var archiveStatus = rowdata.contractArchiveStatus
    			                	var html = [];
	    			                    if(archiveStatus == 1){
	    			                		html.push("已归档");
	    			                	}else if(archiveStatus == 2){ 
	    			                		html.push("不通过驳回");
	    			                	}else{
	    			                		html.push("<a href=\"javascript:processingFiling(" + rowdata.id + ");\">未处理</a>")
	    			                	}
    			                	return html.join('');

		    			           }},
		    			           { display: '法务备注', name: 'fwRemarks',width:180 ,render: function(rowdata, rowindex){
					  					var html = [];
					  					if(rowdata.fwRemarks!=null && rowdata.fwRemarks!= ''){
					  						html.push(rowdata.fwRemarks);
					  						html.push("<br><a href=\"javascript:reviseRemarks(" + rowdata.id + ");\">修改</a>")
					  					}
					  					return html.join("");
				  					}},
				  					
	    			                { display: '法务对接人', name: 'platformFawuContact',width:180 }
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
	  <div id="toptoolbar"></div>
  
	  <form id="dataForm" runat="server">
	  	<input type="hidden" name="delApply" id="delApply" value="${delApply}">
	  <%-- 	<input type="hidden" name="progressStatus" id="progressStatus" value="${progressStatus}"> --%>
		<div class="search-pannel">
				<div class="search-tr"  >

					<div class="search-td">
						<div class="search-td-label">商家序号:</div>
						<div class="search-td-condition" >
							<input type="text" id="mchtCode" name="mchtCode" >
						</div>
					</div>
				
				 	<div class="search-td">
						<div class="search-td-label">公司名称:</div>
						<div class="search-td-condition" >
							<input type="text" id="companyName" name="companyName" >
						</div>
					</div>
				
					
					<div class="search-td">
						<div class="search-td-label">商家寄件状态:</div>
						<div class="search-td-condition" >
							<select  id="isMchtSend" name="isMchtSend" class="querysel">
								<!-- <option value="0" selected="selected">待确认</option> -->	
								<option value="">请选择</option>
								<option value="0">未寄出</option>
								<option value="1">已寄出</option>
							</select>
						</div>
					</div>
					
					
					
					<div class="search-td">
						<div class="search-td-label">协议归档状态:</div>
						<div class="search-td-condition" >
							<select  id="contractArchiveStatus" name="contractArchiveStatus" class="querysel">
								<!-- <option value="0" selected="selected">待确认</option> -->	
								<option value="">请选择</option>
								<option value="0">未处理</option>
								<option value="1">通过已归档</option>
								<option value="2">不通过驳回</option>
							</select>
						</div>
					</div>
					
				</div>
				
				<div class="search-tr"  >
	 				<div class="search-td">
						<div class="search-td-label"></div>
						<div class="search-td-condition" >
							<input  type="hidden"  >
						</div>
					</div>

					<div class="search-td-search-btn" style="display: inline-flex;">
						<div id="searchbtn"  class="l-button">搜索</div>
							<div style="padding-left: 10px;">
								<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export">
							</div>	
					</div>
					
				<!-- 	<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
						<div style="padding-left: 10px;">
							<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export">
						</div>	
					</div> -->
				</div>
				
			
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
