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
 	 var viewerPictures;
	 $(function() {
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
	 });
	
	//放大图片
	function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath=imgPath.replace('_S', '');
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
	
	//添加
	var oldproductBrandId = true;
	function addProductBrand(productBrandId, mallCategoryModuleId) {
		if (oldproductBrandId != productBrandId) {
			oldproductBrandId = productBrandId;
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/mallCategoryItem/addProductBrand.shtml",
			 data : {productBrandId : productBrandId, mallCategoryModuleId : mallCategoryModuleId},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.code != 200)
					 commUtil.alertError(data.msg);
				 else{
					 $("#searchbtn").click();
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
		
		}
	}
	
 	var listConfig={
	      url:"/mallCategoryItem/getProductBrandList.shtml?mallCategoryModuleId=${mallCategoryModuleId }",
	      btnItems:[],
	      listGrid:{ columns: [
	                        {display:'品牌ID', name:'id', align:'center', isSort:false, width:180},
	                        {display:'品牌名称', name:'name', align:'center', isSort:false, width:180},
	                        {display:'品牌图片', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								var logo = rowdata.logo==null?'':rowdata.logo;
								if(logo != '') {
									html.push("<img style='margin: 5px;' src='${pageContext.request.contextPath}/file_servelt"+logo+"' onclick='viewerPic(this.src)' width='60' height='60'>");
								}
								return html.join("");
	                        }},
	                        {display:'商品数', name:'productCount', align:'center', isSort:false, width:180},
	                        {display:'操作', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:addProductBrand(" + rowdata.id + ", ${mallCategoryModuleId });\">【添加】</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >品牌ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productId" name="productId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productName" name="productName" >
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
