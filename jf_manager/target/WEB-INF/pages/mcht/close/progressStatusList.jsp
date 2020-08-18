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
     function delApply(id){
 	  	$.ligerDialog.confirm('确认取消关店？', function (yes) {
 			if(yes){
 				$.ajax({
 					url : "${pageContext.request.contextPath}/mcht/close/delApply.shtml",
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
 							$.ligerDialog.success("取消关店成功!");
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
     
     
     var viewerPictures;
    $(function(){
     	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    });

     function viewApply(id) {
    		$.ligerDialog.open({
    			height: $(window).height() - 100,
    			width: $(window).width() - 200,
	    		title: "关店详情",
	    		name: "INSERT_WINDOW",
	    		url: "${pageContext.request.contextPath}/mcht/close/viewApply.shtml?id=" + id,
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
	    		  
	    	      url:"/mcht/close/progressStatusData.shtml",
	    	      
	    	      listGrid:{ columns: [
		    			          { display: '申请时间', name: 'createDate', width:180,render:function(rowdata,rowindex){
		    			        	  	if(rowdata.createDate){
		    			                	var createDate=new Date(rowdata.createDate);
		    			                	return createDate.format("yyyy-MM-dd");
		    			        	  	}else{
		    			        	  		return "";
		    			        	  	}
		    			           }}, 
		    			                
		    			          { display: '申请人', name: 'applyName',width:100 },   
					                { display: '公司信息',  name: 'companyInfo', width: 150 , render: function(rowdata, rowindex){
					  					var html = [];
					  					html.push(rowdata.mchtCode+"<br>");
					  					html.push(rowdata.companyName+"<br>");
					  					html.push(rowdata.shopName);
					  					return html.join("");
				  					}}, 
	    			                { display: '经营品牌', name: 'mchtProductBrands',width:180 },
	    			                { display: '招商部确认', name: 'zsConfirmStatus', width: 180 ,render:function(rowdata,rowindex){
	    			                	if(rowdata.zsConfirmStatus == 0){
	    			                		return "待确认";
	    			                	}else if(rowdata.zsConfirmStatus == 1){
	    			                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
	    			                	}else if(rowdata.zsConfirmStatus == 2){
	    			                		return "不同意关店";
	    			                	}
	    			                }},
	    			                { display: '商品部确认',   name: 'commodityConfirm' , width: 180,render:function(rowdata,rowindex){
	    							    if(rowdata.mchtInfoStatus == 1 && rowdata.activityStatus == 1 && rowdata.commodityStatus == 1 && rowdata.marketingStatus == 1){
	    							    	return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
	    							    }else{
	    							    	return "待确认";
	    							    }
	    			                }
	    			                },
    		 		                { display: '商家资料确认', name: "mchtArchive",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.mchtContractStatus == 1 && rowdata.mchtArchiveStatus == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
    		 		                		if(rowdata.fwCloseHangUpStatus == 1) {
    		 		                		return '待确认</br><span style="color: red">已挂起</span><a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
											}
											else {
												return "待确认";
											}
    		 		                	}
    				              	}},
    		 		                { display: '客服部确认', name: "clientConfirm",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.orderConfirmStatus == 1 && rowdata.serviceOrderConfirmStatus == 1 && rowdata.appealOrderConfirmStatus == 1 && rowdata.interventionOrderConfirmStatus == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
											if(rowdata.kfCloseHangUpStatus == 1) {
												return '待确认</br><span style="color: red">已挂起</span><a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
											}
											else {
												return "待确认";
											}
    		 		                	}
    				              	}},
    		 		                { display: '财务部确认', name: "cwConfirm",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.paymentOfGoodsConfirm == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    		 		                { display: '法务部确认', name: "fwConfirm",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.endCooperationAgreement == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    		 		                { display: '分管确认关店', name: "directorConfirm",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.directorConfirmStatus == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    		 		                { display: '操作关店', name: "productConfirm",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.productConfirmStatus == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    		 		               /* { display: '结算审核', name: "settleConfirm",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.settlementAuditStatus == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},*/
    		 		                { display: '操作结算', name: "opSettleConfirm",width:100 , render: function(rowdata, rowindex) {
    		 		                	if(rowdata.depositReturnStatus == 1 && rowdata.needPayStatus == 1){
    		 		                		return '已确认<a href="javascript:;" onclick="viewApply('+rowdata.id+')">【查看】</a>';
    		 		                	}else{
    		 		                		return "待确认";
    		 		                	}
    				              	}},
    				              	{ display: "操作", name: "op",width:100 , render: function(rowdata, rowindex) {
    				              		var delApply = $("#delApply").val(); 
    				              		// delApply判断权限，  directorConfirmStatus为分管确认关店状态 当分管已确认关店后则不能取消关店
    		 		                	if(delApply && rowdata.directorConfirmStatus != 1){
    		 		                		return "<a href=\"javascript:delApply(" + rowdata.id + ");\">取消关店</a>";
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
	    	  };
    </script>
  </head>
  
  <body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
  
	  <form id="dataForm" runat="server">
	  	<input type="hidden" name="delApply" id="delApply" value="${delApply}">
		<div class="search-pannel">
				<div class="search-tr"  >
					<div class="search-td">
						<div class="search-td-label" style="float:left;width: 20%;">申请时间：</div>
						<div class="l-panel-search-item">
							<input type="text" id="create_date_begin" name="create_date_begin" />
							<script type="text/javascript">
								$(function() {
									$("#create_date_begin").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
						<div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
					</div>
					<div class="search-td">
						<div class="l-panel-search-item">
							<input type="text" id="create_date_end" name="create_date_end" />
							<script type="text/javascript">
								$(function() {
									$("#create_date_end").ligerDateEditor({
										showTime : false,
										labelWidth : 150,
										width : 150,
										labelAlign : 'left'
									});
								});
							</script>
						</div>
					</div>
			 	
				 	<div class="search-td">
					<div class="search-td-label">申请人:</div>
					<div class="search-td-condition" >
						<input type="text" id="appleName" name="appleName" >
					</div>
					</div>
				
				 	<div class="search-td">
					<div class="search-td-label">名称:</div>
					<div class="search-td-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
					</div>
				</div>
				
				<div class="search-tr"  >
					<div class="search-td">
					<div class="search-td-label">商家序号:</div>
					<div class="search-td-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
					</div>
					
					<div class="search-td">
						<div class="search-td-label">主营类目:</div>
						<div class="search-td-condition" >
							<select  id="productTypeId" name="productTypeId" class="querysel">
								<option value="">请选择</option>
								<c:forEach var="productType" items="${productTypes}">
									<option value="${productType.id}">${productType.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="search-td">
						<div class="search-td-label">进度状态:</div>
						<div class="search-td-condition" >
							<select  id="progressStatus" name="progressStatus" class="querysel">
								<option value="">请选择</option>
								<c:forEach var="list" items="${progressStatusList}">
									<option value="${list.statusValue}">${list.statusDesc}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="search-td">
						<div class="search-td-label">当前是否挂起:</div>
						<div class="search-td-condition" >
							<select  id="closeHangUpStatus" name="closeHangUpStatus" class="querysel">
								<option value="">请选择</option>
								<option value="0">未挂起</option>
								<option value="1">已挂起</option>
							</select>
						</div>
					</div>
				 	
					<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
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
