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
	

	var delProductIds=new Array()
	//删除方法
	function delProduct(productid) {
		$.ligerDialog.confirm('是否删除？', function(yes) {
			if(yes) {
				delProductIds.push(productid);
				$("#OPER"+productid).html("<a href='javascript:restoreProduct(" + productid + ");'>已删除</a>");
			}
		});
	}
	//还原方法
	function restoreProduct(productid) {
		$.ligerDialog.confirm('是否还原？', function(yes) {
			if(yes) {
				var index=delProductIds.indexOf(productid);
				delProductIds.splice(index,1);
				$("#OPER"+productid).html("<a href='javascript:delProduct(" + productid + ");'>删除</a>");
			}
		});
	}
	
	$(function(){
		$("#save").bind('click',function(){
			$.ajax({
				url : "${pageContext.request.contextPath}/svipOrder/saveAutomaticGrab.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"delProductIds" : delProductIds.join(",")},
				contentType: "application/x-www-form-urlencoded",
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$.ligerDialog.alert('提交成功！', function (yes) { 
							parent.location.reload();
						});		
					}else{
						$.ligerDialog.error(data.returnMsg);
					}			
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
	});
	
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
     url:"/svipOrder/getAutomaticGrabList.shtml",
      listGrid:{ columns: [
			            { display: '主图', name: 'pic', width: 80,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
			                       h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.productId+")'>";
			                      }else{
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.productId+")'>";
			                      }
			                    return h;
						}},
			            { display: '商品ID', name: 'code', width: 100}, 
			            { display: '分类', name: 'productTypeName', width: 100 }, 
			            { display: '品牌', name: 'productBrandName', width: 100}, 
			            { display: '名称', name: 'name', width: 200}, 
			            { display: '货号', name: 'artNo', width: 150}, 
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
						 { display: '状态', name: 'status', width: 100, align: "center", render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status == 0){
									html.push("下架");
								}else if(rowdata.status == 1){
									html.push("上架");
								}
								return html.join("");
							}},
						 { display: "操作", name: "OPER", width: 100, align: "center", render: function(rowdata, rowindex) {
								var html = [];
								if(delProductIds.indexOf(rowdata.productId)==-1){
									html.push("<div id='OPER"+ rowdata.productId +"'><a href=\"javascript:delProduct(" + rowdata.productId + ");\">删除</a></div>");
								}else{
									html.push("<div id='OPER"+ rowdata.productId +"'><a href=\"javascript:restoreProduct(" + rowdata.productId + ");\">已删除</a></div>");
								}
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
		<div id="topmenu"><input type="button" style="margin-left: 40px;margin-top: 10px;margin-bottom: 10px;width: 120px;height: 30px;cursor: pointer;" value="提交保存" id="save"></div>
	</form>
	
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
