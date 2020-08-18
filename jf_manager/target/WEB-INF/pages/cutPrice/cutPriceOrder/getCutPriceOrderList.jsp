<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 150
		});
		
	 });
 
	 function formatMoney(s, n) {
	    n = n > 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(),
	    r = s.split(".")[1];
	    t = "";
	    for(i = 0; i < l.length; i ++ )
	    {
	       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	    }
	    return t.split("").reverse().join("") + "." + r;
	 }
	 
	//商品信息
	function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1200,
			title: "商品信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
	 //拉黑
	 function addBlackList(memberId) { 
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "拉黑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/cutPriceOrder/addblackListManager.shtml?memberId="+memberId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	
	 //砍价详情
	 function showCutPriceOrderDtl(cutPriceOrderId) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 300,
				title: "砍价详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/cutPriceOrder/cutPriceOrderDtlManager.shtml?cutPriceOrderId="+cutPriceOrderId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //审核
	 function updateAuditStatus(ids, auditStatus) {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "审核",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/cutPriceOrder/updateAuditStatusManager.shtml?ids="+ids+"&auditStatus="+auditStatus,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
 	 var listConfig={
	      url:"/cutPriceOrder/getCutPriceOrderList.shtml",
	      btnItems:[
					{text: '批量审核下单', icon: 'modify', click: function() {
						  var data = listModel.gridManager.getSelectedRows();
				          if (data.length == 0) {
				        	  $.ligerDialog.alert('请选择行');
				          }else {
				             var str = "";
				              $(data).each(function () {    
				            	  if(str==''){
				            		  str = this.id ;
				            	  }else{
				            		  str += ","+ this.id ;
				            	  }
				              });                                                      
				              updateAuditStatus(str, '0');
				          }; 
				          return;
					  }}
	                ],
	      listGrid:{ columns: [
							{display:'砍价编号',name:'orderCode', align:'center', isSort:false, width:180},
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:80},
							{display:'会员名称',name:'memberNick', align:'center', isSort:false, width:120},
							{display:'主图',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
								return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"'></div>";
							}},
							{display:'品牌 / 货号/ 商品ID',name:'brandName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return (rowdata.brandName==null?'':rowdata.brandName)
	                        		   + "<br/>" + 
	                        		   (rowdata.artNo==null?'':rowdata.artNo)
	                        		   + "<br/>" + 
	                        		   (rowdata.productCode==null?'':rowdata.productCode);
	                        }},
	                        {display:'店铺名 / 商品名',name:'shopName', align:'center', isSort:false, width:260, render:function(rowdata, rowindex) {
	                        	return (rowdata.shopName==null?'':rowdata.shopName) 
	                        			+ "<br/><a href=\"javascript:viewProduct(" + rowdata.productId + ");\">"
	                        			+ (rowdata.productName==null?'':rowdata.productName) + "</a>";
	                        }},
	                        {display:'此次砍价次数',name:'cutPriceOrderDtlCount', align:'center', isSort:false, width:100},
	                        {display:'商品原价',name:'tagPrice', align:'center', isSort:false, width:100},
	                        {display:'发起时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'状态',name:'statusDesc', align:'center', isSort:false, width:100},
							{display:'审核状态',name:'auditStatusDesc', align:'center', isSort:false, width:100},
							{display:'参与砍价次数',name:'cutPriceOrderCount', align:'center', isSort:false, width:100},
							{display:'累计成功砍价次数',name:'cutPriceOrderSuccCount', align:'center', isSort:false, width:110},
							{display:'操作',name:'', align:'center', isSort:false, width:220, render: function(rowdata, rowindex) {
								var html = [];
								var auditRemarks = rowdata.auditRemarks==null?'':rowdata.auditRemarks;
								if(rowdata.status == '2') {
									if(rowdata.auditStatus == '0') {
										html.push("<a href='javascript:;' onclick='updateAuditStatus("+rowdata.id+", "+rowdata.auditStatus+");'>【审核下单】</a>");
									}else if(rowdata.auditStatus == '2') {
										html.push("<a href='javascript:;' onclick='updateAuditStatus("+rowdata.id+", "+rowdata.auditStatus+");'>【重新审核】</a>");
									}
								}
								html.push("<a href='javascript:;' onclick='addBlackList("+rowdata.memberId+");'>【拉黑】</a>");
								html.push("<a href='javascript:;' onclick='showCutPriceOrderDtl("+rowdata.id+");'>【砍价记录】</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :true,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
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
					<div class="search-td-label"  >砍价编号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="orderCode" name="orderCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会员名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">砍价时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >砍价状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="status" items="${statusList }">
								<option value="${status.statusValue }">
									${status.statusDesc }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="auditStatus" name="auditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="auditStatus" items="${auditStatusList }">
								<option value="${auditStatus.statusValue }">
									${auditStatus.statusDesc }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商品名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productName" name="productName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div class="search-td-search-btn" >
						<div id="searchbtn" >搜索</div>
					</div>
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
