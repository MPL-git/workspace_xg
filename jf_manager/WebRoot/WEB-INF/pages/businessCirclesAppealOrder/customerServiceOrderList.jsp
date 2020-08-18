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
 
 
 function viewAfterServiceDetail(id, serviceTypeDesc) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
	 		title: "售后详情（"+serviceTypeDesc+"）",
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
 
 var listConfig={
      url:"/businessCirclesAppealOrder/customerServiceOrderdata.shtml?customerServiceOrderlist=${customerServiceOrderlist}",
      btnItems:[],
      listGrid:{ columns: [ 
						{display:'售后单编号',name:'', align:'center', width:200,render:function(rowdata, rowindex){
							var html = [];
    						html.push("<a href=\"javascript:viewAfterServiceDetail(" + rowdata.id + ",'" +rowdata.serviceTypeDesc+ "');\">"+rowdata.orderCode+"</a>"); 
    						return html.join("");
						}},
						{display:'品牌/商品名称',name:'', align:'center', isSort:false, width:300,render:function(rowdata, rowindex){
							var brandProductName=rowdata.brandProductName;
							if (brandProductName!=null) {
								return brandProductName.replace(/,/g,"<br>");
							}
						}},
						{ display: '实付金额', width: 100, name: 'payAmount'},
						{display:'售后单状态',name:'proStatusDesc', align:'center', isSort:false, width:100},
						{display:'申请时间',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
							 var html=[];
							 if (rowdata.createDate!=null && rowdata.createDate!='') {
								 var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
							    }
								return html.join("");
						}},
						{display:'相关子订单号',name:'', align:'center', isSort:false, width:300,render:function(rowdata, rowindex){
							var html = [];
    						html.push("<a href=\"javascript:viewDetail(" + rowdata.subOrderId + ");\">"+rowdata.subOrderCode+"</a>"); 
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
					<div class="search-td-label" >售后单号：</div>
					<div class="search-td-condition" >
						<input type="text" id="orderCode" name="orderCode" >
					</div>
			  </div>
			  <div class="search-td">
					<div class="search-td-label" >商品名称：</div>
					<div class="search-td-condition" >
						<input type="text" id="productName" name="productName" >
					</div>
			  </div>
			  <div class="search-td">
			 	<div class="search-td-label" >售后单状态：</div>
			 	<div class="search-td-condition" >
				<select id="proStatus" name="proStatus" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="proStatuslist" items="${proStatuslist}">
					   <c:if test="${proStatuslist.prostatus eq 'A1'}"><option value="${proStatuslist.prostatus}">客户已申请（退款）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'A2'}"><option value="${proStatuslist.prostatus}">商家同意申请（退款）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'A3'}"><option value="${proStatuslist.prostatus}">商家同意申请（退款）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'A4'}"><option value="${proStatuslist.prostatus}">平台已退款（退款）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'B1'}"><option value="${proStatuslist.prostatus}">客户已申请（退货）</option></c:if> 
					   <c:if test="${proStatuslist.prostatus eq 'B2'}"><option value="${proStatuslist.prostatus}">商家同意申请（退货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'B3'}"><option value="${proStatuslist.prostatus}">商家不同意申请（退货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'B4'}"><option value="${proStatuslist.prostatus}">客户已寄件（退货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'B5'}"><option value="${proStatuslist.prostatus}">商家已同意退款（退货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'B6'}"><option value="${proStatuslist.prostatus}">商家不同意退款（退货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'B7'}"><option value="${proStatuslist.prostatus}">平台已退款（退货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'C1'}"><option value="${proStatuslist.prostatus}">客户已申请（换货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'C2'}"><option value="${proStatuslist.prostatus}">商家同意申请（退款）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'C3'}"><option value="${proStatuslist.prostatus}">商家不同意申请（换货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'C4'}"><option value="${proStatuslist.prostatus}">客户已寄件（换货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'C5'}"><option value="${proStatuslist.prostatus}">商家同意换货（换货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'C6'}"><option value="${proStatuslist.prostatus}">商家同意换货（换货）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'C7'}"><option value="${proStatuslist.prostatus}">商家已寄件（换货））</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'D1'}"><option value="${proStatuslist.prostatus}">商家创建直赔（直赔）</option></c:if>
					   <c:if test="${proStatuslist.prostatus eq 'D2'}"><option value="${proStatuslist.prostatus}">平台已退款（直赔）</option></c:if>
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
