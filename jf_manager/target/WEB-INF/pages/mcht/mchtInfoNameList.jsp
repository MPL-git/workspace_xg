<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : false,
			labelWidth : 150,
			width : 150,
			labelAlign : 'left'
		});
	 });
	 
  //导出	 
  function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/mcht/getMchtinfonameListexport.shtml");
	$("#dataForm").submit(); 
  } 
  
 	 var listConfig={
	      url:"/mcht/getMchtinfoNameList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [	                      
	                        {display:'商家序号',name:'mchtCode', align:'center', width:100},
	                        {display:'公司名称',name:'companyName', align:'center', width:260},
	                        {display:'合作状态',name:'status', align:'center',width:100,render:function(rowdata, rowindex){
	                        if (rowdata.status=='1') {
								return "正常";
							}
	                        if (rowdata.status=='2') {
								return "暂停";
							}
	                        if (rowdata.status=='3') {
								return "关闭";
							}
	                        }},
	                        {display:'店铺名称',name:'shopName', align:'center', width:260},
	                        {display:'主营类目',name:'productTypeName', align:'center', width:100},	                	                
	                        {display:'开通日期', align:'center',width:200,render:function(rowdata, rowindex){
	                        	                
							  if (rowdata.cooperateBeginDate!=null && rowdata.cooperateBeginDate!='') {
								  var cooperateBeginDate = new Date(rowdata.cooperateBeginDate);
								  return cooperateBeginDate.format("yyyy-MM-dd");
							   }else{
								  var createDate = new Date(rowdata.createDate);
								  return createDate.format("yyyy-MM-dd");							   								   										       
							   
							  }
							  							  							  							  							  							   							  						 											   							   							   											  								  								  																					  							   							  												   							                        						 						 							  
						}},
						{display:'关闭时间', align:'center',width:200,render:function(rowdata, rowindex){        
							  if (rowdata.status=='3') {
								  var closeDate = new Date(rowdata.statusDate);
								  return closeDate.format("yyyy-MM-dd");
							   }else{						
								  return "";							   								   										         
							  }
							  							  							  							  							  							   							  						 											   							   							   											  								  								  																					  							   							  												   							                        						 						 							  
						}},
						{display:'招商对接人',name:'zsContactName', align:'center', width:100},
						{display:'平台商家运营',align:'center', width:160, render:function(rowdata, rowindex) {
	                        	var html = [];
			                	if(rowdata.yyContactName != '' && rowdata.yyContactName != null) {
			                		html.push("<span style='color: #00998F;'>[运营]</span>");
			                		html.push( rowdata.yyContactName +"</br>");
			                	}			               
			                	return html.join("");
	                        }},
	                    {display:'活动开通状态',name:'activityAuthStatus', align:'center',width:100,render:function(rowdata, rowindex){
		                        if (rowdata.activityAuthStatus=='0') {
									return "关闭";
								}
		                        if (rowdata.activityAuthStatus=='1') {
									return "开通";
								}
		                 
		                     }},  
		                {display:'商城开通状态',name:'shopStatus', align:'center',width:100,render:function(rowdata, rowindex){
		                    if (rowdata.shopStatus=='0') {
								return "关闭";
							}
	                        if (rowdata.shopStatus=='1') {
								return "开通";
							}
			                  }},
	                    
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
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>			
				<div class="search-td">
					<div class="search-td-label"  >名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >合作状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
						    <option value="">请选择...</option>
							<option value="1">正常</option>
							<option value="2">暂停</option>
							<option value="3">关闭</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if> 
						 <c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" style="float:left;" >开通日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="startCooperateBeginDate" name="startCooperateBeginDate" >
				 	</div>
				 	<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至</div>
				</div>
				<div class="search-td">
					<div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="endCooperateBeginDate" name="endCooperateBeginDate" >
				 	</div>
				</div>	
				
			<div class="search-td">
					<div class="search-td-label"  >活动开通状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="activityAuthStatus" name="activityAuthStatus" style="width: 135px;" >
						    <option value="">请选择...</option>
							<option value="0">关闭</option>
							<option value="1">开通</option>				
						</select>
				 </div>
			</div>
						
			<div class="search-td">
					<div class="search-td-label"  >商城开通状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="shopStatus" name="shopStatus" style="width: 135px;" >
						    <option value="">请选择...</option>
							<option value="0">关闭</option>
							<option value="1">开通</option>	
						</select>
				 </div>
			</div>
											
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" class="l-button">搜索</div>
				<c:if test="${sessionScope.USER_SESSION.staffID=='1'}">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" onclick="return excel();" id="export">
					</div>	
				</c:if>
			</div>				
			</div>
		</div>
		
	

	</form>
	
	<div id="maingrid" style="margin: 0; padding: 0"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
