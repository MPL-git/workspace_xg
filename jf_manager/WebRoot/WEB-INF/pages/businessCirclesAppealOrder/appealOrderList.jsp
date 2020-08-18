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
    
	function viewAppealOrder(id) {
		$.ligerDialog.open({
			height : $(window).height() - 50,
			width : $(window).width() - 100,
			title : "查看投诉单",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/appealOrder/view.shtml?id="
					+ id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	
	function viewDetail(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
 	
 var listConfig={
      url:"/businessCirclesAppealOrder/appealOrderdata.shtml?appealOrderList=${appealOrderList}",
      btnItems:[],
      listGrid:{ columns: [ 
						{display:'投诉单编号',name:'', align:'center', width:200,render:function(rowdata, rowindex){
							var html = [];
    						html.push("<a href=\"javascript:viewAppealOrder(" + rowdata.id + ");\">"+rowdata.orderCode+"</a>"); 
    						return html.join("");
						}},
						{display:'相关子订单编号',name:'', align:'center', width:200,render:function(rowdata, rowindex){
							var html = [];
    						html.push("<a href=\"javascript:viewDetail(" + rowdata.subOrderId + ");\">"+rowdata.subOrderCode+"</a>"); 
    						return html.join("");
						}},

						{display:'客服状态',name:'serviceStatusDesc', align:'center', isSort:false, width:100},
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
					<div class="search-td-label" >投诉单编号：</div>
					<div class="search-td-condition" >
						<input type="text" id="orderCode" name="orderCode" >
					</div>
			  </div>
			  <div class="search-td">
			 	<div class="search-td-label" >投诉单状态：</div>
			 	<div class="search-td-condition" >
				<select id="serviceStatus" name="serviceStatus" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="statuslist" items="${statuslist}">
					   <c:if test="${statuslist.service_status eq '0'}"><option value="${statuslist.service_status}">待介入</option></c:if>
					   <c:if test="${statuslist.service_status eq '1'}"><option value="${statuslist.service_status}">处理中</option></c:if>
					   <c:if test="${statuslist.service_status eq '2'}"><option value="${statuslist.service_status}">已结束</option></c:if>
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
