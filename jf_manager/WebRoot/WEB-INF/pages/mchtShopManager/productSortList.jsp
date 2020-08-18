<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<script type="text/javascript">
	var viewerPictures;
    $(function(){
    	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    });
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
	function viewProductMchtInfo(mchtId,productId,mchtName) {
		$.ligerDialog.open({
			height: 400,
			width: 500,
			title: mchtName,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProductMchtInfo.shtml?mchtId=" + mchtId+"&productId="+productId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function lawAuditProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width()*0.75,
			title: "法务审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/lawAuditProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: false,
			slide: false,
			data: null
		});
	}
	
	function viewerPic(productId){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : "${pageContext.request.contextPath}/product/getPoductPic.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productId:productId},
			timeout : 30000,
			success : function(data) {
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
      url:"/mchtShopManager/productSort/data.shtml",
      listGrid:{ columns: [
						{ display: '系统ID', name: 'id', width: 100}, 
			            { display: '主图', name: 'pic', width: 80,render: function(rowdata, rowindex) {
		                    var h = "";
		                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
		                    return h;
						    }},
			            { display: '商品ID', name: 'code', width: 100}, 
			            { display: '品牌', name: 'product_brand_name', width: 100}, 
		                { display: '名称', width: 200, render: function(rowdata, rowindex) { 
							var html = [];
							html.push("<a href=\"javascript:viewProduct(" + rowdata.id + ");\">"+rowdata.name+"</a>"); 
							return html.join("");
      					}},
		                { display: '状态', width: 80, render: function(rowdata, rowindex) { 
							return rowdata.status_desc;
      					}},
		                { display: '总值', width: 80, render: function(rowdata, rowindex) { 
							if(rowdata.weights){
								return rowdata.weights;
							}else{
								return 0;
							}
      					}}, 
		                { display: '季节', width: 80, name:'season_weight'}, 
		                { display: '销量', width: 80, name:'sale_weight'}, 
		                { display: '销售额', width: 80, name:'sale_amount_weight'}, 
		                /* { display: '点击', width: 80, name:'pv_weight'},  */
		                { display: '评分', width: 80, name:'product_weight'}, 
		                { display: '商家', width: 80, name:'mcht_grade_weight'}, 
		                { display: '品牌', width: 80, name:'brand_grade_weight'}, 
		                { display: '人工', width: 80, name:'manual_weight'}, 
		                { display: '分类', name: 'product_type_name', width: 100 }, 
		                { display: '性别', width: 100, name: 'suit_sex', render: function(rowdata, rowindex) {
		                	var sexStr=String(rowdata.suit_sex);
		                	var sexShow="";
							var html = [];
		 					if (sexStr.charAt(0)=="1"){
								sexShow+="男、";
							}
		                    if (sexStr.charAt(1)=="1"){
		                    	sexShow+="女、";
							}
		                    html.push(sexShow.replace(/[、]$/,""));
						    return html.join("");
		                  }},
		                { display: '人群', name: 'suit_group', width: 100, render: function(rowdata, rowindex) {
		                	var groupStr=String(rowdata.suit_group);
		                	var groupShow="";
							var html = [];
		 					if (groupStr.charAt(0)=="1"){
								groupShow+="青年、";
							}
		                    if (groupStr.charAt(1)=="1"){
		                    	groupShow+="儿童、";
							}
		                    if (groupStr.charAt(2)=="1"){
		                    	groupShow+="中老年 、";
							}
		                    html.push(groupShow.replace(/[、]$/,""));
						    return html.join("");
		                  }},
		                { display: '货号', name: 'art_no', width: 100},
		                { display: '吊牌价', name: 'tagPrice', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.tag_price_min);
							
							if(rowdata.tag_price_min!=rowdata.tag_price_max){
								html.push("-");
								html.push(rowdata.tag_price_max);
							};
						    return html.join("");
					 }},
		                { display: '商城价',name: 'saleName', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.mall_price_min);
							
							if(rowdata.mall_price_min!=rowdata.mall_price_max){
								html.push("-");
								html.push(rowdata.mall_price_max);
							};
						    return html.join("");
					 }},
		             { display: '活动价',name: 'saleName', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.sale_price_min);
							
							if(rowdata.sale_price_min!=rowdata.sale_price_max){
								html.push("-");
								html.push(rowdata.sale_price_max);
							};
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
       
  };
 
 
    var productTypeCombo;
    $(function(){
    	productTypeCombo = $("#productTypeName").ligerComboBox({
              selectBoxWidth: 200,
              selectBoxHeight: 200,  
              valueField: 'id',
              textField: 'name',
              valueFieldID: 'productType',
              treeLeafOnly: false,
              valueField: 'id',
              resize: true,
              tree: {
            	  onClick: function(note){
            		  if(!productTypeCombo.treeManager.hasChildren(note.data)) {
	            		  productTypeCombo.treeManager.loadData(note.target, '${pageContext.request.contextPath}/service/prod/product_type/getProductTypeTree.shtml?parentId='+note.data.id);
					  }
            	  },
            	  url: '${pageContext.request.contextPath}/service/prod/product_type/getProductTypeTree.shtml', 
            	  checkbox: false, 
            	  ajaxType: 'get', 
            	  idFieldName: 'id',
            	  textFieldName: 'name',
            	  parentIDFieldName: 'parentId',
            	  isExpand: false
              }
          });
    	
    });
    
    
    
    </script>  
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
				<div class="search-td-label">性别：</div>
				<div  class="search-td-condition">
					<select  id="suitSex" name="suitSex"
						class="querysel">
						<option value="">请选择</option>
						<option value="10">男</option>
						<option value="01">女</option>
						<option value="11">男、女</option>
					</select>
				</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">人群：</div>
					<div  class="search-td-condition">
						<select  id="suitGroup" name="suitGroup"
							class="querysel">
							<option value="">请选择</option>
							<option value="100">青年</option>
							<option value="010">儿童 </option>
							<option value="001">中老年</option>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">上架状态：</div>
					<div class="search-td-condition">
					<select id="status" name="status">
							<option value="">请选择</option>
							<option value="1">上架</option>
							<option value="0">下架</option>
						</select>
					</div>
				</div>
				
<!-- 				<div class="search-td"> -->
<!-- 					<div class="search-td-label">隐藏状态：</div> -->
<!-- 					<div class="search-td-condition"> -->
<!-- 					<select id="isShow" name="isShow"> -->
<!-- 							<option value="">请选择</option> -->
<!-- 							<option value="0">隐藏</option> -->
<!-- 							<option value="1">显示</option> -->
<!-- 						</select> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="search-td">
					<div class="search-td-label">商城价：</div>
					<div  class="search-td-condition">
						<input name="mallPriceBegin" style="width:43%">
						<span style="width:10%">--</span>
						<input name="mallPriceEnd" style="width:43%">
					</div>
				</div>
			</div>

			<div class="search-tr">

				<div class="search-td">
					<div class="search-td-label">商品名称：</div>
					<div  class="search-td-condition">
						<input name="name">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商品ID：</div>
					<div  class="search-td-condition">
						<input name="code">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">系统ID：</div>
					<div  class="search-td-condition">
						<input name="id">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div  class="search-td-condition">
						<input name="mchtCode">
					</div>
				</div>
			</div>
			
			<div class="search-tr">
			
				<div class="search-td">
					<div class="search-td-label">商家名称：</div>
					<div  class="search-td-condition">
						<input name="mchtName">
					</div>
				</div>
			
				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div  class="search-td-condition">
						<input name="productBrandName">
					</div>
				</div>
			
				<div class="search-td">
					<div class="search-td-label">货号：</div>
					<div  class="search-td-condition">
						<input name="artNo">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">分类：</div>
					<div class="search-td-combobox-condition">
						<input id="productTypeName" type="text">
					</div>
				</div>
			</div>
			
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">总值：</div>
					<div  class="search-td-condition">
						<input name="weights">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">季节分值：</div>
					<div  class="search-td-condition">
						<input name="seasonWeight">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">销量分值：</div>
					<div  class="search-td-condition">
						<input name="saleWeight">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">点击分值：</div>
					<div  class="search-td-condition">
						<input name="pvWeight">
					</div>
				</div>
			</div>
			
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家分值：</div>
					<div  class="search-td-condition">
						<input name="mchtGradeWeight">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">品牌分值：</div>
					<div  class="search-td-condition">
						<input name="brandGradeWeight">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">人工分值：</div>
					<div  class="search-td-condition">
						<input name="manualWeight">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">销售分值：</div>
					<div  class="search-td-condition">
						<input name="saleAmountWeight">
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">评分分值：</div>
					<div  class="search-td-condition">
						<input name="commentWeight">
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
			
		</div>
	</form>
	 	<div id="toptoolbar"></div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
