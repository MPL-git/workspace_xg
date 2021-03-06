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
	// 查看商家信息
	function viewMchtInfo(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家基础资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
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
	

	
	//驳回方法
	function discountReject(id) {
		$.ligerDialog.confirm('是否驳回？', function(yes) {
			if(yes) {

				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/product/discountReject.shtml",
					 data : {id : id,monitoring : 3},
					 dataType : 'json',
					 success : function(data){
						 if(data.returnCode == "0000" )
							 $("#searchbtn").click();
							 
						 else{
							 commUtil.alertError(data.message); 
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}
		});
	}
	//批量驳回方法
	function batchDiscountReject(ids) {
		$.ligerDialog.confirm('是否驳回所有选中项？', function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/product/batchDiscountReject.shtml",
					 data : {ids : ids,monitoring : 3},
					 dataType : 'json',
					 success : function(data){
						 if(data.returnCode == "0000" )
							 $("#searchbtn").click();
							 
						 else{
							 commUtil.alertError(data.message); 
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}
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
				console.log(data);
				if(data&&data.length>0){
					for (var i=0;i<data.length;i++)
					{	if(data[i].pic.indexOf("http") >= 0){
						$("#viewer-pictures").append('<li><img data-original="'+data[i].pic+'" src="'+data[i].pic+'" alt=""></li>');
					    }else{
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
					    }
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
      url:"/product/dataMonitoringList.shtml",
     
      btnItems:[{text: '批量驳回', icon: 'modify', click: function() {
        var data = listModel.gridManager.getSelectedRows();
              if (data.length == 0)
               $.ligerDialog.alert('请选择行');
              else
              {
                  var str = "";                     
                  $(data).each(function ()
                  {    
                   if(str==''){
                    str = this.id ;
                   }else{
                    str += ","+ this.id ;
                   }
                  });
                  batchDiscountReject(str);
              }
              return;
     }},
     { line:true },
      ],
      listGrid:{ columns: [
						{ display: '系统ID', name: 'id', align:'center', width: 100}, 
			            { display: '主图', name: 'pic', width: 80,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
			                       h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
			                      }else{
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
			                      }
			                    return h;

						}},
			            { display: '商品ID', name: 'code', width: 100}, 
			            { display: '品牌', name: 'productBrandName', width: 100}, 
			            { display: '名称', name: 'name', width: 200},
			            { display: '一级类目', name: 'productType1Name', width: 100}, 
			            { display: '对接人', name: 'yyContactName', width: 150}, 
		                { display: '分类', name: 'productTypeName', width: 100 }, 
		                { display: '上市年份', name: 'yearOfListing', width: 100 }, 
		                { display: '吊牌价', name: 'tagPrice', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.tagPriceMin);
							
							if(rowdata.tagPriceMin!=rowdata.tagPriceMax){
								html.push("-");
								html.push(rowdata.tagPriceMax);
							};
						    return html.join("");
					 }},
		                { display: '商城价',name: 'saleName', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.mallPriceMin);
							
							if(rowdata.mallPriceMin!=rowdata.mallPriceMax){
								html.push("-");
								html.push(rowdata.mallPriceMax);
							};
						    return html.join("");
					 }},
		                { display: '活动价',name: 'saleName', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							
							html.push(rowdata.salePriceMin);
							
							if(rowdata.salePriceMin!=rowdata.salePriceMax){
								html.push("-");
								html.push(rowdata.salePriceMax);
							};
						    return html.join("");
					 }},
					 { display: 'SVIP折扣',name: '', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.svipDiscount!=null) {
								html.push(rowdata.svipDiscount);
							}else{
								html.push("-");
								
							}	
						    return html.join("");
					 }},
		                { display: '折扣',name: 'mchtName', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							var minTagPrice = rowdata.minTagPrice;
							var minSalePrice = rowdata.minSalePrice;
							var discount = parseFloat(minSalePrice/minTagPrice*10).toFixed(2);
							html.push(discount);
							
						    return html.join("");
					 }},
 		                
		             { display: '类型', name: 'saleTypeDesc', width: 100},

		             { display: '更新时间', width: 150, render: function(rowdata, rowindex) {
            	 	 	var updateDate;
            	 	    if (rowdata.updateDate!=null){
            	 			updateDate=new Date(rowdata.updateDate);
            	 	    }else{
            	 		  	updateDate=new Date(rowdata.createDate);
            	 	    }
	            	    return updateDate.format("yyyy-MM-dd hh:mm:ss");
			         }},
			         { display: '公司名称', name: "OPER1", width: 200, render: function(rowdata, rowindex) { 
							var html = [];
							html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>"); 
							return html.join("");
					}},
					
		             { display: "操作", name: "OPER", width: 100, align: "center", render: function(rowdata, rowindex) {
						var html = [];
						html.push("<a href=\"javascript:discountReject(" + rowdata.id + ");\">【驳回】</a>");
						return html.join("");
					 }} 
		          ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
       
  };
 
	//导出
	function excel(){  
		$("#dataForm").attr("action","${pageContext.request.contextPath}/product/export.shtml");
		$("#dataForm").submit();
	}
 
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
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" value="${mchtType}" name="mchtType"> 
		<input type="hidden" value="${auditStatus}" name="auditStatus">
		<input type="hidden" value=3 name="monitoring">
		<div id="topmenu"></div>
		<div class="search-pannel">
			
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商品名称：</div>
					<div class="search-td-condition">
						<input name="name">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode"
							<c:if test="${not empty mchtCode }">readonly</c:if>
							value="${mchtCode }">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">商品ID：</div>
					<div class="search-td-condition">
						<input name="code">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">商品类型：</div>
					<div class="search-td-condition">
						<select id="suitGroup" name="saleType" class="querysel">
							<option value="">请选择</option>
							<option value="1">品牌款</option>
							<option value="2">单品款</option>
						</select>
					</div>
				</div>
				
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">对接人：</div>
					<div class="search-td-condition">
						<select id="yyPlatformContactId" name="yyPlatformContactId">
							<option value="" selected="selected" >请选择</option>
							<c:forEach items="${yyPlatformContacts}" var="yyPlatformContact">
								<option value="${yyPlatformContact.staffId }">${yyPlatformContact.contactName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="search-td-search-btn">
						<div id="searchbtn">搜索</div>
				</div>
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
