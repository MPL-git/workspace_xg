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

 function viewAfterServiceDetail(id, customerServiceTypeDesc) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
	 		title: "售后详情（"+customerServiceTypeDesc+"）",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
 
    //查看
	function showInterventionOrder(interventionOrderId, statusPage) {
		$.ligerDialog.open({
			height : $(window).height() - 100,
			width : $(window).width() - 200,
			title : "查看详情",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/interventionOrder/showInterventionOrderManager.shtml?interventionOrderId="
					+ interventionOrderId + "&statusPage=" + statusPage,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
 
 var listConfig={
      url:"/businessCirclesAppealOrder/interventionOrderdata.shtml?interventionOrderList=${interventionOrderList}",
      btnItems:[],
      listGrid:{ columns: [ 
						{display:'介入单编号',name:'', align:'center', width:200,render:function(rowdata, rowindex){
							var html = [];
    						html.push("<a href=\"javascript:showInterventionOrder(" + rowdata.id + ", 1);\">"+rowdata.interventionCode+"</a>"); 
    						return html.join("");
						}},
						{display:'售后单编号',name:'', align:'center', width:200,render:function(rowdata, rowindex){
							var html = [];
    						html.push("<a href=\"javascript:viewAfterServiceDetail(" + rowdata.serviceOrderId + ",'" +rowdata.customerServiceTypeDesc+ "');\">"+rowdata.customerServiceOrderCode+"</a>"); 
    						return html.join("");
						}},

						{display:'处理状态',name:'statusDesc', align:'center', isSort:false, width:100},
						{display:'处理人', name:'platformProcessorName', align:'center', isSort:false, width:100},
						{display:'申请时间',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
							 var html=[];
							 if (rowdata.createDate!=null && rowdata.createDate!='') {
								 var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
							    }
								return html.join("");
						}},
						{display:'更新时间',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
							 var html=[];
							 if (rowdata.updateDate!=null && rowdata.updateDate!='') {
								 var updateDate = new Date(rowdata.updateDate);
									html.push(updateDate.format("yyyy-MM-dd hh:mm:ss"));
							    }
								return html.join("");
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
	<form id="dataForm" runat="server" >
		<div class="search-pannel">	
			<div class="search-tr"  >
		 	  <div class="search-td">
					<div class="search-td-label" >介入单号：</div>
					<div class="search-td-condition" >
						<input type="text" id="interventionCode" name="interventionCode" >
					</div>
			  </div>
			  <div class="search-td">
			 	<div class="search-td-label" >介入单状态：</div>
			 	<div class="search-td-condition" >
				<select id="status" name="status" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="statuslist" items="${statuslist}">
					   <c:if test="${statuslist.status eq '0'}"><option value="${statuslist.status}">待领取</option></c:if>
					   <c:if test="${statuslist.status eq '1'}"><option value="${statuslist.status}">待处理</option></c:if>
					   <c:if test="${statuslist.status eq '2'}"><option value="${statuslist.status}">待复审(客服驳回客户)</option></c:if>
					   <c:if test="${statuslist.status eq '3'}"><option value="${statuslist.status}">复审驳回</option></c:if>
					   <c:if test="${statuslist.status eq '4'}"><option value="${statuslist.status}">待商家申诉(客服同意客户介入申请)</option></c:if> 
					   <c:if test="${statuslist.status eq '5'}"><option value="${statuslist.status}">商家申诉中（平台待评判）</option></c:if>
					   <c:if test="${statuslist.status eq '6'}"><option value="${statuslist.status}">待商家上传凭证（买家胜诉）</option></c:if>
					   <c:if test="${statuslist.status eq '7'}"><option value="${statuslist.status}">待结案</option></c:if>
					   <c:if test="${statuslist.status eq '8'}"><option value="${statuslist.status}">已结案</option></c:if>
				   </c:forEach>					
				</select>
				</div>
		 	  </div>	  			
			 <div class="search-td-search-btn" style="display: inline-flex;">
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
