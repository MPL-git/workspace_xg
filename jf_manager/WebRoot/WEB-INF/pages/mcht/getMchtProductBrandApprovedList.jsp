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
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"type="text/javascript"></script>	
<script type="text/javascript">
    var viewerPictures;
    $(function(){
     	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
     	
     	$("#exportExcel").bind('click',function(){
     		var param = $("#dataForm").serialize();
     		location.href="${pageContext.request.contextPath}/mcht/exportMchtProductBrandApproved.shtml?"+param;
     	});
    });
	
    function editMchtProductBrand(mchtProductBrandId, view) {
    	$.ligerDialog.open({
	    	height: $(window).height() - 40,
	    	width: 1000,
	    	title: "品牌信息",
	    	name: "INSERT_WINDOW",
	    	url: "${pageContext.request.contextPath}/mcht/editMchtProductBrand.shtml?view="+ view +"&mchtProductBrandId=" + mchtProductBrandId,
	    	showMax: true,
	    	showToggle: false,
	    	showMin: false,
	    	isResize: true,
	    	slide: false,
	    	data: null
    	});
    }
    
  	//修改或查看特批
    function editOrShowMchtProductBrandApproved(mchtProductBrandId, statusPage) {
    	$.ligerDialog.open({
    		height: 400,
    		width: 500,
    		title: "招商标记",
    		name: "INSERT_WINDOW",
    		url: "${pageContext.request.contextPath}/mcht/editOrShowMchtProductBrandApproved.shtml?mchtProductBrandId=" + mchtProductBrandId +"&statusPage=" + statusPage,
    		showMax: true,
    		showToggle: false,
    		showMin: false,
    		isResize: true,
    		slide: false,
    		data: null
    	});
    }

  	//撤销特批
    function updateMchtProductBrandApproved(mchtProductBrandId) {
    	$.ligerDialog.confirm("撤销后将不能更改，请确认是否更改", function (yes) { 
    		if(yes){
    			$.ajax({
    				url : "${pageContext.request.contextPath}/mcht/updateMchtProductBrandApproved.shtml",
    				type : 'post',
    				dataType : 'json',
    				data : {mchtProductBrandId : mchtProductBrandId, isSpeciallyApproved : '0'},
    				success : function(data) {
    					if(data.returnCode == '0000') 
    						$("#maingrid").ligerGetGridManager().reload();
    					else 
    						$.ligerDialog.error(data.returnMsg);
    				},
    				error : function() {
    					$.ligerDialog.error('操作超时，请稍后再试！');
    				}
    			});
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
   	      url:"/mcht/getMchtProductBrandApprovedList.shtml",
   	      listGrid:{ columns: [
    			          { display:'创建日期', name:'createDate', width:120, render:function(rowdata,rowindex){
   			                	var createDate=new Date(rowdata.createDate);
   			                	return createDate.format("yyyy-MM-dd hh:mm:ss");
    			          }}, 
    			          { display:'商家序号', name:'mchtCode', width:100}, 
			              { display:'公司名称',  name:'companyName', width: 150 , render:function(rowdata, rowindex){
		  				  		var html = [];
		  					 	html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>"); 
		  						return html.join("");
		  				  }}, 
			              { display:'店铺名称', name:'shopName', width: 200},
/*    			              { display:'品牌LOGO', name:'logo',width:180, render:function(rowdata, rowindex) {
   			                	if(rowdata.logo != null && rowdata.logo != '') {
   			                		return "<div style='padding:3px;'><img style='width:60px;height:40px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.logo+"'></div>";
   			                	}
   			                	return "";
   			              }},
 */   			              
 						  { display:'主营品类', name:'productTypeName', width:80},	
 						  { display:'品牌名称', name:'productBrandName', width:100,render:function(rowdata,rowindex){
   			                	return '<a href="javascript:;" onclick="editMchtProductBrand('+rowdata.id+',0);">'+rowdata.productBrandName+'</a>';
   			              }},
   			             /*  { display:'品牌库ID', name:'productBrandId', width:80}, */
   			             
   			              { display: '授权有效期', name: 'platformAuthExpDate', width: 120 ,render:function(rowdata,rowindex){
   			                	var platformAuthExpDate=new Date(rowdata.platformAuthExpDate);
   			                	return platformAuthExpDate.format("yyyy-MM-dd");
   			              }},
   			              { display:'资质审核状态', name:'auditStatusDesc', width:100}, 
   			              { display: '运营状态', name:'statusDesc', width:100},
   			              { display: '招商标记', name:'isSpeciallyApproved', width:150, render:function(rowdata,rowindex){
   			            		var html = [];
   								    html.push(rowdata.isSpeciallyApprovedDesc);
   								var zsMarked = ${zsMarked};
	   							if(rowdata.isSpeciallyApproved) {
	   								if(zsMarked == 1 && rowdata.isSpeciallyApproved == 1){
	   									html.push("<a href=\"javascript:editOrShowMchtProductBrandApproved(" + rowdata.id + ", 1);\">【修改】</a>&nbsp;&nbsp;");
	   								}	
   			            		}
	   							
	   						    return html.join("");
   			              }},
   			              { display: '标记有效期', name:'speciallyApprovedDate', width:150, render:function(rowdata,rowindex){
   			            	if(rowdata.speciallyApprovedDate){
	   			            	var speciallyApprovedDate=new Date(rowdata.speciallyApprovedDate);
				                return speciallyApprovedDate.format("yyyy-MM-dd hh:mm:ss");
   			            	}else{
   			            		return "";
   			            	}  
   			              }},
   			              { display: '备注', name:'speciallyApprovedRemarks', width:180},
	 		              { display:'法务对接人', name:'fwContactName', width:100}
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
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label">名称:</div>
					<div class="search-td-condition" >
						<input type="text" id = "name" name="name" >
					</div>
				</div>
				
				<div class="search-td">
		           <div class="search-td-label">商家序号：</div>
				   <div class="search-td-condition" >
					<input type="text" id = "mchtCode" name="mchtCode" >
				   </div>
				</div>
				
				<div class="search-td">
		           <div class="search-td-label">品牌：</div>
				   <div class="search-td-condition" >
					<input type="text" id = "productBrandName" name="productBrandName" >
				   </div>
				</div>
				
				<div class="search-td">
					 <div class="search-td-label" >品类</div>
					 <div class="search-td-condition" >
						<select id="productTypeId" name="productTypeId" class="aptitudeType">
							<option value="">请选择...</option> 
							<c:forEach var="productType" items="${productTypes}">
								<option value="${productType.id}">${productType.name}</option>
							</c:forEach>
						</select>
				 	 </div>
			 	 </div>
			</div> 	 
			<div class="search-tr"  >
				<div class="search-td">
					 <div class="search-td-label" >品牌运营状态:</div>
					 <div class="search-td-condition" >
						<select id="status" name="status" class="status">
							<option value="">请选择...</option> 
							<c:forEach var="brandStatus" items="${brandStatusList}">
								<option value="${brandStatus.statusValue}">${brandStatus.statusDesc}</option>
							</c:forEach>
						</select>
				 	 </div>
			 	 </div>
			 	 
				<div class="search-td">
					 <div class="search-td-label" >合作状态:</div>
					 <div class="search-td-condition" >
						<select id="mchtInfoStatus" name="mchtInfoStatus" class="status">
							<option value="">请选择...</option> 
							<option value="0">入驻中</option>
							<option value="1" selected="selected">正常</option>
							<option value="2">暂停</option>
							<option value="3">关闭</option>
						</select>
				 	 </div>
			 	 </div>
			 	
				<%-- <div class="search-td">
					 <div class="search-td-label" >资质类型:</div>
					 <div class="search-td-condition" >
						<select id="aptitudeType" name="aptitudeType" class="aptitudeType">
							<option value="">请选择...</option> 
							<c:forEach var="brandAptitude" items="${brandAptitudeList}">
								<option value="${brandAptitude.statusValue}">${brandAptitude.statusDesc}</option>
							</c:forEach>
						</select>
				 	 </div>
			 	 </div> --%>
				
				<div class="search-td">
					<div class="search-td-label" style="float:left;">标记有效期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="speciallyApprovedDateBegin" name="speciallyApprovedDateBegin"/>
						<script type="text/javascript">
							$(function() {
								$("#speciallyApprovedDateBegin").ligerDateEditor( {
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
						<input type="text" id="speciallyApprovedDateEnd" name="speciallyApprovedDateEnd"/>
						<script type="text/javascript">
							$(function() {
								$("#speciallyApprovedDateEnd").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									labelAlign : 'left'
								});
							});
						</script>	
					</div>
					</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >对接人：</div>
					<div class="search-td-combobox-condition" >
						<select id="fwStaffId" name="fwStaffId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="mchtProductBrandCustomlistList" items="${mchtProductBrandCustomlistList }">
							    <c:if test="${not empty mchtProductBrandCustomlistList.fw_staff_id}">
								<option value="${mchtProductBrandCustomlistList.fw_staff_id}">
									${mchtProductBrandCustomlistList.fw_contact_name}
								</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="exportExcel">
					</div>
				 </div>								
			</div> 
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
