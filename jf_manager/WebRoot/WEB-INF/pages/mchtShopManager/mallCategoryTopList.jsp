<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 
	 function toEdit(id){
		$.ligerDialog.open({
			width: 600,
			height: 450,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtShopManager/mallCategoryTop/toEdit.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 function toDesign(decorateInfoId){
		 $.ligerDialog.open({
				height: $(window).height()*0.9,
				width: $(window).width()*0.8,
				title: "装修",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtShopManager/mallCategory/design.shtml?decorateInfoId=" + decorateInfoId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	
	 function preview(decorateInfoId) {
			$.ligerDialog.open({
				height: 800,
				width: 800,
				title: "预览",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtShopManager/mallCategory/preview.shtml?decorateInfoId="+decorateInfoId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
		
		function changeStatus(id,status) {
			var title;
			if (status=="0"){
				title="确定要将状态改为上架吗？";
			}else if(status=="1"){
				title="确定要将状态改为下架吗？";
			}
			$.ligerDialog.confirm(title, function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/mchtShopManager/mallCategoryTop/changeStatus.shtml?id=" + id,
						secureuri : false,
						dataType : 'json',
						cache : false,
						async : false,
						success : function(data) {
							if ("0000" == data.returnCode) {
								listModel.gridManager.reload();
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
		
 
      var listConfig={
	  
      url:"/mchtShopManager/mallCategoryTop/data.shtml",
	  btnItems:[{ text: '添加', icon: 'add', dtype:'win',  click: itemclick, url:'/mchtShopManager/mallCategoryTop/toEdit.shtml', seqId:"", width : 600, height:450}],
      listGrid:{ columns: [ 
		                { display: 'ID', name: 'id',width:100 }, 
		                { display: '类型', width: 150 , render: function(rowdata, rowindex) {
		                	if(rowdata.productTypeId==1){
		                		return "商城【首页】";
		                	}else{
							    return "商城【"+rowdata.productTypeName+"馆】";
		                	}
		              	}}, 
		                { display: '备注', name:'remarks',width: 350},
		                { display: '上架时间', width:150, render: function(rowdata, rowindex) {
							return new Date(rowdata.upDate).format("yyyy-MM-dd hh:mm:ss");
		              	}},
		                { display: '下架时间', width:150, render: function(rowdata, rowindex) {
							return new Date(rowdata.downDate).format("yyyy-MM-dd hh:mm:ss");
		              	}},
		                { display: '信息操作', width:80, render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:toEdit(" + rowdata.id + ");\">编辑</a>");																								                		
						    return html.join("");
		              	}},
		                { display: '上架状态', name: 'statusDesc', width:80},
 		                { display: '装修操作', width:180, render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:toDesign(" + rowdata.decorateInfoId + ");\">装修</a>&nbsp;&nbsp;");																								                		
							if(rowdata.status=="0"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">上架</a>&nbsp;&nbsp;");
							}else if(rowdata.status=="1"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">下架</a>&nbsp;&nbsp;");
							}
							html.push("<a href=\"javascript:preview(" + rowdata.decorateInfoId + ");\">预览</a>");																								                		
						    return html.join("");
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
       
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
				<div class="search-td-label">类型：</div>
				<div class="search-td-condition">
					<select id="productTypeId" name="productTypeId" class="querysel">
						<option value="">不限</option>
						<option value="1">商城【首页】</option>
						<c:forEach var="list" items="${productTypes}">
							<option value="${list.id}">商城【${list.name}馆】</option>
						</c:forEach>
					</select>
				</div>
				</div>
			
				<div class="search-td">
				<div class="search-td-label">备注：</div>
				<div class="search-td-condition" >
					<input type="text" id="remarks" name="remarks" >
				</div>
				</div>
	
			 	<div class="search-td">
				 	<div class="search-td-label">上架状态：</div>
				 	<div class="search-td-condition"  >
				    	<select id="status" name="status" class="querysel">
							<option value="">不限</option>
							<option value="0">下架</option>
							<option value="1">上架</option>
						</select>
					</div>
			 	</div>
			 	
			 	<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
			</div>
			
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
