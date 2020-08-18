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
    	
    	$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		});
    	
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

	//修改上线线状态
	function upAndDownLine(id,status) {
		var title;
		if (status=="1"){
			title="状态是否改为下线？";
		}else {
			title="状态是否改为上线？";
		}
		$.ligerDialog.confirm(title, function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/resourceLocationManagement/upAndDownLine.shtml?id=" + id+"&bannerId=${bannerId}",
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
      url:"/resourceLocationManagement/commodityManagementDate.shtml?bannerId=${bannerId}",
      listGrid:{ columns: [
						{display:'商品', name:'seqNo', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
							var html=[];
							 var h = "";
		                      if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
		                       h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.linkId+")'>";
		                      }else{
		                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.linkId+")'>";
		                      }
		                    html.push(h);
							html.push("<div class='no-check'>");
						/* 	html.push("<div class='width-60'>"+"<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"'>"+"</div>"); */
							html.push("<div  class='width-226'><p class='ellipsis'>"+rowdata.productName+"</p><p>商品ID:"+rowdata.productCode+"</p><p>"+rowdata.artbrandGroupEasy+"&nbsp|&nbsp;"+rowdata.producttype1Name+"&nbsp;&nbsp;</p></div>");   		
							html.push("<div>");
							return html.join("");
	                    }},
			          
			            { display: '吊牌价/活动价/折扣', width: 180, render: function (rowdata, rowindex) {
			                var html = [];
							//吊牌价
							html.push(rowdata.tagPriceMin);
							if(rowdata.tagPriceMin!=rowdata.tagPriceMax){
								html.push("-");
								html.push(rowdata.tagPriceMax);
							};
							if (rowdata.tagPriceMin!=null && rowdata.tagPriceMin!='') {						
							    html.push("<br>");
							}
							
							//活动价
							 html.push(rowdata.salePriceMin);
								if(rowdata.salePriceMin!=rowdata.salePriceMax){
									html.push("-");
									html.push(rowdata.salePriceMax);
								};
								if (rowdata.salePriceMin!=null && rowdata.salePriceMin!='') {						
								    html.push("<br>");
								}
							//折扣
							html.push(rowdata.discountMin);
							if(rowdata.discountMin!=rowdata.discountMax){
								html.push("-");
								html.push(rowdata.discountMax);
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
     			        
	                { display: '库存', name: 'stockSum', width: 80, },
	                
	                { display: '操作', width:180, render: function(rowdata, rowindex) {
						var html = [];
																														                		
						if(rowdata.bannerStatus=="1"){
						    html.push("<a href=\"javascript:upAndDownLine(" + rowdata.id + ","+rowdata.bannerStatus+");\">下线</a>&nbsp;&nbsp;");																						                		
						}else {
						    html.push("<a href=\"javascript:upAndDownLine(" + rowdata.id + ","+rowdata.bannerStatus+");\">上线</a>&nbsp;&nbsp;");	
						}
					    return html.join("");
	              	}},
	                
	                { display: '状态', width:180, render: function(rowdata, rowindex) {																								                		
						if(rowdata.bannerStatus=="0"){
							return "";																			                		
						}else if(rowdata.bannerStatus=="1"){
						    return "已上线";
						}
					  
	              	}},
	              	
	              	
	                { display: '商品DSR', name: '', width: 150,render:function(rowdata, rowindex){
				     	var html=[];
				    	html.push("商品描述:<span>"+rowdata.avgProductScore+"</span><br>")
				    	html.push("卖家服务:<span>"+rowdata.avgMchtScore+"</span><br>")
				    	html.push("物流服务:<span>"+rowdata.avgWuliuScore+"</span>")
				    	return html.join(""); 
				     }},
	   
				     { display: '好评率', name: 'goodCommentRate', width: 100},  
				     
	                { display: '点赞数', name: 'supportQuantity', width: 100,render:function(rowdata, rowindex){
				    	 if (rowdata.supportQuantity!=null && rowdata.supportQuantity!='') {
				    		 return rowdata.supportQuantity;
						}else{
							return "0";
						}
				     }},
				     
				     {display: '权重', name: 'weight', width: 80},
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
 
 function chaneGoods(){
   	 var temp = $("#whichProduct").val();
   	 if(temp==1){
   		 $("#artnos").hide();
   		 $("#goodIds").show();
   	 }
   	 if(temp==2){
   		 $("#artnos").show();
   		 $("#goodIds").hide();
   	 }

    }
 
    </script>  
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	<input type="hidden" id="bannerId" name="bannerId" value="${bannerId}">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input id="productBrandName"  name="productBrandName"/
							<c:if test="${not empty productBrandName }">readonly</c:if>
							value="${productBrandName }">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">商品名称：</div>
					<div class="search-td-condition">
						<input id="name" name="name">
					</div>
				</div>
		
				<div class="search-td">
					<div class="search-td-label">
						<select id="whichProduct" name="whichProduct" class="querysel" onchange="chaneGoods()">
							<option value="1">商品ID</option>
							<option value="2">商品货号</option>
						</select>
					</div>
					<div class="search-td-condition">
						<textarea class="goodIds" id="goodIds"  name="goodIds" rows="1" cols="4" style="resize:none;" placeholder="多个商品ID查询请换行" ></textarea> 
						<textarea class="goodIds" id="artnos"  name="artnos" rows="1" cols="4" style="resize:none;display:none;" placeholder="多个商品货号查询请换行" ></textarea> 
						<!-- <input id="goodIds" name="goodIds" placeholder="多个商品ID查询请换行" > -->
					</div>
					
				</div>
				
				<div class="search-td">
						<div class="search-td-label" style="float: left;">商品状态：</div>
						<div class="l-panel-search-item">
						<select id="bannerStatus" name="bannerStatus" class="status" style="width:135px;">
							<option value="">请选择</option>
							<option value="1">已上线</option>
							<option value="0">未上线</option>
						</select>
					</div>
				</div>
			</div>

			<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label" style="float: left;">一级类目：</div>
				<div class="l-panel-search-item">
					<select  class="ad-select" name="productTypeId" id="productTypeId" >
						<option value="">--请选择--</option>
						<c:forEach var="productType" items="${productTypes}">
							<option value="${productType.id}">${productType.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="search-td" type="hidden">
					<div class="search-td-label"></div>
				 	<div class="search-td-condition">
					</div> 
				</div>



				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
	</form>
	   <div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
