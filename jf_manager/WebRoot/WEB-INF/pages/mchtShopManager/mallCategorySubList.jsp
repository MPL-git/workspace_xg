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
 function saveSeqNo(id,_this){
	 var seqNo = $(_this).val();
	 if(seqNo){
		 var reg = /^[1-9]\d*$/;
		 if(reg.test(seqNo)){
			 $.ajax({
					url : "${pageContext.request.contextPath}/mchtShopManager/mallCategorySub/saveSeqNo.shtml?id=" + id+"&seqNo="+seqNo,
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
		 }else{
			 commUtil.alertError("只能输入正整数");
		 }
	 }
 }
 
	 function toEdit(id){
		 $.ligerDialog.open({
				width: $(window).width()*0.8,
				height: $(window).height()*0.9,
				title: "编辑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtShopManager/mallCategorySub/toEdit.shtml?id=" + id,
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
	
	 function preview(decorateInfoId,mallCategorySubId) {
			$.ligerDialog.open({
				height: 800,
				width: 800,
				title: "预览",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtShopManager/mallCategory/preview.shtml?decorateInfoId="+decorateInfoId+"&mallCategorySubId="+mallCategorySubId,
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
						url : "${pageContext.request.contextPath}/mchtShopManager/mallCategorySub/changeStatus.shtml?id=" + id,
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
	  
      url:"/mchtShopManager/mallCategorySub/data.shtml",
	  btnItems:[{ text: '添加', icon: 'add', dtype:'win',  click: itemclick, url:'/mchtShopManager/mallCategorySub/toEdit.shtml', seqId:"", width : 1200, height:650}],
      listGrid:{ columns: [ 
		                { display: 'ID', name: 'id',width:100 }, 
		                { display: '类型', width: 150 , render: function(rowdata, rowindex) {
		                	return rowdata.productTypeName;
		              	}}, 
		                { display: '名称', name:'name',width: 120},
		                { display: '品牌', width:150, render: function(rowdata, rowindex) {
							return rowdata.brandName;
		              	}},
		                { display: '二级类目', width:150, render: function(rowdata, rowindex) {
							return rowdata.secondProductTypeName;
		              	}},
		                { display: '上架状态', name: 'statusDesc', width:80},
		                { display: '主题信息操作', width:120, render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:toEdit(" + rowdata.id + ");\">编辑</a>");																								                		
						    return html.join("");
		              	}},
 		                { display: '操作', width:180, render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:toDesign(" + rowdata.decorateInfoId + ");\">装修</a>&nbsp;&nbsp;");																								                		
							if(rowdata.status=="0"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">上架</a>&nbsp;&nbsp;");
							}else if(rowdata.status=="1"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">下架</a>&nbsp;&nbsp;");
							}
							html.push("<a href=\"javascript:preview(" + rowdata.decorateInfoId + ","+rowdata.id+");\">预览</a>");																								                		
						    return html.join("");
		              	}},
		              	{ display: '排序操作', width:200, render: function(rowdata, rowindex) {
							return '<input name="seqNo" mallCategorySubId="'+rowdata.id+'" onblur="saveSeqNo('+rowdata.id+',this);" value="'+rowdata.seqNo+'">';
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
						<c:forEach var="list" items="${productTypes}">
							<option value="${list.id}">${list.name}</option>
						</c:forEach>
					</select>
				</div>
				</div>
			
				<div class="search-td">
				<div class="search-td-label">名称：</div>
				<div class="search-td-condition" >
					<input type="text" id="name" name="name" >
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
