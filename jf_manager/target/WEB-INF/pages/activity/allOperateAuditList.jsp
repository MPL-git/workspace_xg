<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

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
 
 var type=${type};

 /* 查看活动 */
 function seeActivity(activityId) {
		$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "品牌特卖活动专题审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allSeeActivityInfo.shtml?activityId=" + activityId+"&type="+type,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
	
 /* 查看活动商品 */
 function seeActivityProduct(activityId) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "品牌特卖专题活动商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allActivityProductList.shtml?activityId=" + activityId+"&type=0",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
 
 var listConfig={
	  
      url:"/activity/allOperateAuditListData.shtml?type="+type,
   
      btnItems:[],
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                        {display:'特卖ID',name:'id'},
                        {display:'活动名称',name:'name'},
                        {display:'利益点',name:'benefitPoint'},
		                {display:'商家名称', name:'shortName'},
		                {display:'商家序号',name:'mchtCode'},
		                {display:'商品数',name:'productNum'},
		                {display:'库存',name:'activityStock'},
						{display:'期望开始时间',name:'expectedStartTime',width: 150,render:function(rowdata,rowindex){
							if(rowdata.expectedStartTime==null||rowdata.expectedStartTime==""||rowdata.expectedStartTime==undefined){
								return "";
							}else{
								var expectTime=new Date(rowdata.expectedStartTime);
								return expectTime.format("yyyy-MM-dd");								
							}
						}},
						{display:'运营专员',name:'contactName'},
						{display:'运营专员审核',name:'operateAuditStatusDesc',width:100,render:function(rowdata, rowindex){
                        	if(rowdata.operateAuditStatus==1){
                        		return "<samp style='color: #FF6600;'>"+rowdata.operateAuditStatusDesc+"</samp>";
                        	}else if(rowdata.operateAuditStatus==2){
                        		return "<samp style='color: #0000FF;'>"+rowdata.operateAuditStatusDesc+"</samp>";
                        	}else if(rowdata.operateAuditStatus==3){
                        		return "<samp style='color: red;'>"+rowdata.operateAuditStatusDesc+"</samp>";
                        	}else{
                        		return "未审核";
                        	}
                        }},
                        {display:'设计审核',name:'designAuditStatusDesc',width: 100,render:function(rowdata, rowindex){
                        	if(rowdata.designAuditStatus==1){
                        		return "<samp style='color: #FF6600;'>"+rowdata.designAuditStatusDesc+"</samp>";
                        	}else if(rowdata.designAuditStatus==2){
                        		return "<samp style='color: #0000FF;'>"+rowdata.designAuditStatusDesc+"</samp>";
                        	}else if(rowdata.designAuditStatus==3){
                        		return "<samp style='color: red;'>"+rowdata.designAuditStatusDesc+"</samp>";
                        	}else{
                        		return "未审核";
                        	}
                        }},
                        {display:'法务审核',name:'lawAuditStatusDesc',width: 100,render:function(rowdata, rowindex){
                        	if(rowdata.lawAuditStatus==1){
                        		return "<samp style='color: #FF6600;'>"+rowdata.lawAuditStatusDesc+"</samp>";
                        	}else if(rowdata.lawAuditStatus==2){
                        		return "<samp style='color: #0000FF;'>"+rowdata.lawAuditStatusDesc+"</samp>";
                        	}else if(rowdata.lawAuditStatus==3){
                        		return "<samp style='color: red;'>"+rowdata.lawAuditStatusDesc+"</samp>";
                        	}else{
                        		return "未审核";
                        	}
                        }},
                        {display:'运营总监审核',name:'cooAuditStatusDesc',width: 100,render:function(rowdata, rowindex){
                        	if(rowdata.cooAuditStatus==1){
                        		return "<samp style='color: #FF6600;'>"+rowdata.cooAuditStatusDesc+"</samp>";
                        	}else if(rowdata.cooAuditStatus==2){
                        		return "<samp style='color: #0000FF;'>"+rowdata.cooAuditStatusDesc+"</samp>";
                        	}else if(rowdata.cooAuditStatus==3){
                        		return "<samp style='color: red;'>"+rowdata.cooAuditStatusDesc+"</samp>";
                        	}else{
                        		return "未审核";
                        	}
                        }},
						{display:'技术服务费', name:'feeRate'},
		                {display: '首次提报时间', name: 'enrollBeginTime', render: function(rowdata, rowindex) {
		              		if(rowdata.submitTime!=null){
			                	var submitTime=new Date(rowdata.submitTime);
			            	   return submitTime.format("yyyy-MM-dd hh:mm:ss");
		              		}   
			            }},
			            {display: '最后审核时间', name: 'updateDate', render: function(rowdata, rowindex) {
		            		if(rowdata.updateDate!=null){
			            		var updateDate=new Date(rowdata.updateDate);
			            	   return updateDate.format("yyyy-MM-dd hh:mm:ss");
		            		}else{return "";}
			            }},
		                { display: "操作", name: "OPER",align: "center",width: 150, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:seeActivity(" + rowdata.id + ");\">查看活动</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:seeActivityProduct(" + rowdata.id + ");\">查看商品</a>&nbsp;&nbsp;");
						    return html.join("");
					 }
	             }
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label"  >特卖ID：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "activityId" name="activityId" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >活动名称：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "name" name="name" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >商家：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "shortName" name="shortName" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >商家序号：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "mchtCode" name="mchtCode" >
			</div>
		</div>
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label"  >运营专员审核：</div>
			<div class="search-td-combobox-condition" >
				<select id="operateAuditStatus" name="operateAuditStatus">
					<option value="">不限</option>
					<c:forEach items="${operateAuditStatus}" var="list">
						<c:if test="${list.statusValue!='0'}">
						<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >设计专员审核：</div>
			<div class="search-td-combobox-condition" >
				<select id="designAuditStatus" name="designAuditStatus">
					<option value="">不限</option>
					<c:forEach items="${designAuditStatus}" var="list">
						<c:if test="${list.statusValue!='0'}">
						<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >法务部审核：</div>
			<div class="search-td-combobox-condition" >
				<select id="lawAuditStatus" name="lawAuditStatus">
					<option value="">不限</option>
					<c:forEach items="${lawAuditStatus}" var="list">
						<c:if test="${list.statusValue!='0'}">
						<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			</div>
		</div>
			<div class="search-tr">	
				<div class="search-td">
				<div class="search-td-label"  >运营总监审核：</div>
				<div class="search-td-combobox-condition" >
					<select id="cooAuditStatus" name="cooAuditStatus">
						<option value="">不限</option>
						<c:forEach items="${cooAuditStatus}" var="list">
							<c:if test="${list.statusValue!='0'}">
							<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label"  >运营专员：</div>
				<div class="search-td-combobox-condition" >
					<select id="platformContactId" name="platformContactId">
						<option value="">请选择</option>
						<c:forEach items="${platformContacts}" var="list">
							<option value="${list.id}">${list.contactName}</option>
						</c:forEach>
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
