<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript">
 var viewerPictures;
 var linkValueList;
 	$(function(){
 		$.ajax({
			url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
					linkValueList = data.linkValueList;
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});	
 		
 	//加载时获取广告(banner)的类型
	var sourceProductTypeId = "${sourceProductTypeId}";
 	
	 $.ajax({
			url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType="+14,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : "",
			timeout : 30000,
			success : function(data) {
				var linkTypeLists = data.linkTypeList
				for(var i=0;i<linkTypeLists.length;i++){
					var id=linkTypeLists[i].linkType
					var name=linkTypeLists[i].linkTypeName
					$("#linkType").append('<option value="'+id+'" >'+name+'</option>')
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	 
	 viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
 		$("#viewer-pictures").hide();
 		}});
 	
 		$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		}); 
 	
 	
 	})
 
 	//点击显示banner大图
	 function viewerPics(bannerId){	
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : "${pageContext.request.contextPath}/resourceLocationManagement/getBannerPic.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {bannerId:bannerId},
			timeout : 30000,
			success : function(data) {
		
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
 	
 	//点击查看轮播大图
	function viewerRotaryPics(productId){	
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
				if(data){
					if(data[0].pic.indexOf("http") >= 0){
						$("#viewer-pictures").append('<li><img data-original="'+data[0].pic+'" src="'+data[i].pic+'" alt=""></li>');
					    }else{
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[0].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[0].pic+'" alt=""></li>');
					    }
					
					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
						$("#viewer-pictures").hide();
					}});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}
/* 				
				
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
				 */
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	}
 	
 	//查看大图
/*  	 function viewerPic(bannerId , bannerPic){	
 		$("#viewer-pictures").append('<li><img data-original="'+bannerPic+'" src="'+bannerPic+'" alt=""></li>');
 		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
 	 } */
 	
 	
 

		//修改上线线状态
		function changeStatus(id,status) {
			var title;
			if (status=="0"){
				title="状态是否改为上线？";
			}else if(status=="1"){
				title="状态是否改为下线？";
			}
			$.ligerDialog.confirm(title, function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/resourceLocationManagement/changeBannerStatus.shtml?id=" + id,
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
	
		//删除
		function deleteviewCategory(id) {
		    var title="删除";
		    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
		    if (yes) {
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/resourceLocationManagement/deleteBannerCategory.shtml',
				data: {id : id},
				dataType: 'json',
				success: function(data) {
					if(data.code != null && data.code == 200) {
						$("#searchbtn").click();
					}else {
						commUtil.alertError(data.msg);
					}
				},
				error: function(e) {
					commUtil.alertError("系统异常请稍后再试");
				}
			});
		   }
		  });
		 };
		 
		//修改
 	 	function bannerEdit(id,sourceProductTypeId){
 	 		 $.ligerDialog.open({
 				width: 900,
 				height:800,
 				title: "修改banner",
 				name: "INSERT_WINDOW",
 				url: "${pageContext.request.contextPath}/resourceLocationManagement/bannerEdit.shtml?id=" + id+"&sourceProductTypeId="+sourceProductTypeId,
 				showMax: true,
 				showToggle: false,
 				showMin: false,
 				isResize: true,
 				slide: false,
 				data: null
 			});
		}
		
		//商品管理
 	 	function commodityManagement(bannerId){
 	 		$.ligerDialog.open({
				height: $(window).height() - 60,
				width: $(window).width() - 60,
				title : "选择轮播商品",
				name : "INSERT_WINDOW",
				url : "${pageContext.request.contextPath}/resourceLocationManagement/commodityManagement.shtml?bannerId="+bannerId,
				showMax : true,
				showToggle : false,
				showMin : false,
				isResize : true,
				slide : false,
				data : null
			});
		}
		
		
		
      var listConfig={
	
      url:"/resourceLocationManagement/bannerListDate.shtml?pagetype=${pagetype}",
	  btnItems:[ { text: '添加banner', icon: 'add', dtype:'win',  click: itemclick, url: "/resourceLocationManagement/bannerAdd.shtml?pagetype=${pagetype}&sourceProductTypeId=${sourceProductTypeId}", seqId:"", width: 900, height: 800},
	           	 {text: '修改', icon: 'add', click: function(yes) {
	      			  var data = listModel.gridManager.getCheckedRows();
                     if (data.length == 0){
                   	  $.ligerDialog.alert('请选择行');
                     }else{
                   	  var idStr = "";
                         $(data).each(function ()
                         {    
                        	 idStr = this.id ;
                         });
                         
                       var sourceProductTypeId=${sourceProductTypeId}
                      	bannerEdit(idStr,sourceProductTypeId);
                     }
                     return;
	      	  		  }}, 
	             
	             ],
      listGrid:{ columns: [ 
		                {display:'banner图', name:'pic', align:'center', isSort:false, width:130, render:function(rowdata, rowindex) {
		                	var html=[];
							 var h = "";
		                      if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
		                       h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPics("+rowdata.id+")'>";
		                      }else{
		                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60'  onclick='viewerPics("+rowdata.id+")'   >";
		                      }
		                    html.push(h);
		                    return html.join("");
	                    }},
		                { display: 'banner类型', name:'linkTypeDesc',width: 150}, 
		              	{ display: 'banner链接', name:'linkValue',width:200, render: function(rowdata, rowindex) {
		              		if (rowdata.linkType=='14') {
			            		if (rowdata.productTypeNames) {
			            			return rowdata.productTypeNames;
								}
							}else if (rowdata.linkType=='11') {
			            		if (rowdata.productTypeName!=null && rowdata.productTypeName!='') {
			            			return rowdata.productTypeName;
								}
							}else if (rowdata.linkType=='10') {
								if (rowdata.mchtCode!=null && rowdata.mchtCode!='') {
									return rowdata.mchtCode;
								}
							}else if (rowdata.linkType=='9') {
								  if (rowdata.brandteamtypeName!=null && rowdata.sbrandteamtypeName!='') {
									  return rowdata.brandteamtypeName;
								}
							}else if (rowdata.linkType=='6') {
							   for (var i= 0; i < linkValueList.length; i++) {
								    if (linkValueList[i].linkValue==rowdata.linkValue) {
								    	return linkValueList[i].linkValueName;
									}
							  	}
							}else if (rowdata.linkType=='5') {
								return "";
							}else if(rowdata.linkType=='3'){
								return rowdata.productCode;
							}else{
			            	  return rowdata.linkValue;
							}
			         	}},
		              	
		                { display: '上线时间', width: 150, render: function(rowdata, rowindex) {
		              		var upDate;
	            	 	    if (rowdata.upDate!=null){
	            	 	    	upDate=new Date(rowdata.upDate);
		            	        return upDate.format("yyyy-MM-dd hh:mm");
	            	 	    }
		              	}},
		              
		               	{ display: '轮播商品', align:'left', name:'',width:520,render: function(rowdata, rowindex) {
		               		var html=[];
		               		if(rowdata.bannerList!=null){
		               		for(var i=0;i<rowdata.bannerList.length;i++){
		               			var pic = rowdata.bannerList[i].pic;
		               			var tagPriceMin = rowdata.bannerList[i].tagPriceMin;
		               			var tagPriceMax =  rowdata.bannerList[i].tagPriceMax;
		               			var salePriceMin =  rowdata.bannerList[i].salePriceMin;
		               			var salePriceMax =  rowdata.bannerList[i].salePriceMax;
		               			var productId = rowdata.bannerList[i].productId;
		               			var h = "";
								 if(pic!=null&&(pic.indexOf("http") >= 0)){
				                       h += "<div  style='display:  inline-block; margin :10px '><img src='"+pic+"' width='60' height='60' onclick='viewerRotaryPics("+productId+")'>";
				                      }else{
				                       h += "<div  style='display:  inline-block; margin :10px '><img src='${pageContext.request.contextPath}/file_servelt/"+pic+"' width='60' height='60' onclick='viewerRotaryPics("+productId+")' >";
				                      }  
		               			html.push(h);
		               			
		               			/* html.push('<img src="${pageContext.request.contextPath}/file_servelt/'+pic+'" style="width:60px; height:60px;"><div>'+tagPriceMin+'<span>'+salePriceMin+'</span></div>'); */
		               			html.push('<div>¥'+tagPriceMin+'<span  style="text-decoration: line-through;">¥'+salePriceMin+'</span></div>')
		               		html.push('</div>')
		               		}
		               	}
		               		return html.join("");
		               	}},
 		                { display: '操作', width:180, render: function(rowdata, rowindex) {
							var html = [];
																															                		
							if(rowdata.status=="0"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">上线</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
							    html.push("<a href=\"javascript:commodityManagement(" + rowdata.id + ");\">商品管理</a>&nbsp;&nbsp;|&nbsp;&nbsp;");	
							    html.push("<a href=\"javascript:deleteviewCategory(" + rowdata.id + ");\">删除&nbsp;&nbsp;</a>"); 																							                		
							}else if(rowdata.status=="1"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">下线</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
								 html.push("<a href=\"javascript:commodityManagement(" + rowdata.id + ");\">商品管理</a>&nbsp;&nbsp;");	
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
       
  }
      
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	<input type="hidden" id="sourceProductTypeId" name="sourceProductTypeId" value="${sourceProductTypeId}">
		<input type="hidden" id="pagetype" name="pagetype" value="${pagetype}">
		<div class="search-pannel">
			<div class="search-tr"  >
			 	<div class="search-td">
				 	<div class="search-td-label">banner类型：</div>
				 	<div class="search-td-condition"  >
				    	<select id="linkType" name="linkType" class="querysel">
				    	<option value="">请选择</option>
						</select>
					</div>
			 	</div>
			 	<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">上线时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="sign_begin" name="sign_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#sign_begin").ligerDateEditor({
									format : "yyyy-MM-dd hh:mm",
									showTime : true,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="sign_end" name="sign_end" value=""  />
						<script type="text/javascript">
							$(function() {
								$("#sign_end").ligerDateEditor({
									format : "yyyy-MM-dd hh:mm",
									showTime : true,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>
			 	<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
			</div>
			
		</div>
		
		
	</form>
	<div id="maingrid" style="margin: 0; padding: 0"></div>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
