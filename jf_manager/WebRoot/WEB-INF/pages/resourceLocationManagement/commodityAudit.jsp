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
	
	//添加
	function addcommoditylist() {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title : "添加",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/resourceLocationManagement/administrationCommodity.shtml",
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	
	//删除
	function deleteviewProduct(id) {
	    var title="删除";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/deleteviewProduct.shtml',
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
	 }

	 //修改排序值
	function updateArtNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/resourceLocationManagement/updateSourcenicheseNo.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
					 }
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
						 $("#searchbtn").click();
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + id).val("");
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	 
	 //清空排序值
	function delseqNo(id) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/resourceLocationManagement/delseqNo.shtml",
				 data : {id : id},
				 dataType : 'json',
				 success : function(data){
					 if(data.code != null && data.code == 200){
						 
						 commUtil.alertError(json.message);
					 }else{
						 $("#searchbtn").click();
					 }
					
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
	}
	 
	 
	 
	//批量通过

	function batchPass(ids){
		   var title="通过";
		    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
		    if (yes) {
			$.ajax({
				type: 'post',
				url: "${pageContext.request.contextPath}/resourceLocationManagement/toBatchPass.shtml?ids=" + ids,
				data: {ids : ids},
				dataType: 'json',
				success: function(data) {
					if(data.returnCode != null && data.returnCode == 0000) {
						$("#searchbtn").click();
					}else {
						commUtil.alertError(data.returnMsg);
					}
				},
				error: function(e) {
					commUtil.alertError("系统异常请稍后再试");
				}
			});
		   }
		  });
		
		
		
/* 		$.ligerDialog.open({
			height:200,
			width:400,
			title: "批量修改频道",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/toBatchPass.shtml?ids=" + ids,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		}); */
	}	
	
	//批量驳回

	function batchRejection(ids){
		   var title="驳回";
		    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
		    if (yes) {
			$.ajax({
				type: 'post',
				url: "${pageContext.request.contextPath}/resourceLocationManagement/toBatchRejection.shtml?ids=" + ids,
				data: {ids : ids},
				dataType: 'json',
				success: function(data) {
					if(data.returnCode != null && data.returnCode == 0000) {
						$("#searchbtn").click();
					}else {
						commUtil.alertError(data.returnMsg);
					}
				},
				error: function(e) {
					commUtil.alertError("系统异常请稍后再试");
				}
			});
		   }
		  });
		
		
	/* 	
		$.ligerDialog.open({
			height:200,
			width:400,
			title: "批量修改频道",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/toBatchRejection.shtml?ids=" + ids,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		}); */
	}
	
	  //修改
	 function updateupdate(id) {
		 $.ligerDialog.open({
				height: 400,
				width: 500,
				title: "修改",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/resourceLocationManagement/toEditDate.shtml?id="+id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	  
	  
/* 	 function rejectMethod(id){
			$.ajax({
				type: 'post',
				url: "${pageContext.request.contextPath}/resourceLocationManagement/rejectMethod.shtml?id="+id,
				data: {id : id},
				dataType: 'json'
			});
		 }  */
 
	
 var listConfig={
      url:"/resourceLocationManagement/commodityAuditData.shtml?pagetype=${pagetype}",

      btnItems:[{text: '批量通过', icon: 'add', click: function(yes) {
			  var data = listModel.gridManager.getSelectedRows();
              if (data.length == 0){
            	  $.ligerDialog.alert('请选择行');
              }else{
                 var str = "";
                 var params={};
                 
                  $(data).each(function ()
                  {    
                	  if(str==''){
                		  str = this.id ;
                	  }else{
                		  str += ","+ this.id ;
                	  }
                  });
                  
                  batchPass(str);
           /*    $.ligerDialog.confirm('确定通过?', function (yes)
                  {
                	  if(yes==true){
                      batchPass(str);
                	  }
                  });  */ 
              }
              return;
		  }},
		  {text: '批量驳回', icon: 'add', click: function(yes) {
			  var data = listModel.gridManager.getSelectedRows();
              if (data.length == 0){
            	  $.ligerDialog.alert('请选择行');
              }else{
                 var str = "";
                 var params={};
                 
                  $(data).each(function ()
                  {    
                	  if(str==''){
                		  str = this.id ;
                	  }else{
                		  str += ","+ this.id ;
                	  }
                  });
                  
                  batchRejection(str);
        /*       $.ligerDialog.confirm('确定驳回?', function (yes)
                  {
                	  if(yes==true){
                      batchRejection(str);
                	  }
                  });  */ 
              }
              return;
		  }},
	  ],
	 
		listGrid:{ columns: [
						{display:'商品', name:'seqNo', align:'center', isSort:false, width:330, render:function(rowdata, rowindex) {
							var html=[];
							 var h = "";
							 if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
			                       h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.linkId+")'>";
			                      }else{
			                       h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.linkId+")'>";
			                      }
							html.push(h);
							html.push("<div class='width-226'><p class='ellipsis'>"+rowdata.productName+"</p><p>商品ID:"+rowdata.productCode+"</p><p>"+rowdata.artbrandGroupEasy+"&nbsp|&nbsp;"+rowdata.producttype1Name+"&nbsp;&nbsp;</p></div>")
							html.push("<div>")
							
							return html.join("");
	                    }},
	                  
	                    { display: '店铺名称',  name: 'pShopName', width: 180},
			            { display: '推荐文案',  name: 'recommendRemarks', width: 180,hide:${pagetype!=1009}}, 
			            
			            { display: '二级分类',  name: 'producttype2Name', width: 180,hide:${pagetype!=1030}}, 
	                   
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
     				{ display: '近三个月最低活动价', name: 'productlogminsalePrice', width: 200},
	                { display: '近三个月销量', width: 100, name: 'totalQuantitySum'},             
	                { display: '最新库存数', name: 'stockSum', width: 80, },
	   
				     { display: '商品DSR', name: '', width: 150,render:function(rowdata, rowindex){
				     	var html=[];
				    	html.push("商品描述:<span>"+rowdata.avgProductScore+"</span><br>")
				    	html.push("卖家服务:<span>"+rowdata.avgMchtScore+"</span><br>")
				    	html.push("物流服务:<span>"+rowdata.avgWuliuScore+"</span>")
				    	return html.join(""); 
				     }},
				     { display: '好评率', name: 'goodCommentRate', width: 100},  
		             { display: '操作', width: 150, render: function(rowdata, rowindex) {
		            	  var html = []; 
							html.push("<a href=\"javascript:batchPass(" + rowdata.id + ");\">通过</a> | <a href=\"javascript:batchRejection(" + rowdata.id + ");\">驳回</a>"); 
							return html.join(""); 
			         }},
			         { display: '审核状态', name: "auditStatus", width: 100, align: "center", render: function(rowdata, rowindex) {
			        		var name = "无"	
			        	 
			        	 	var auditDate;
	            	 	    if (rowdata.createDate!=null){
	            	 	    	auditDate=new Date(rowdata.auditDate); 
	            	 	    }
							
							if(rowdata.auditStatus=='0'){
								return "待审核"
							}
							if(rowdata.auditStatus=='1'){
								if(rowdata.auditName!=null&&rowdata.auditName!=""){
									return "<span >审核通过</span><br><span>"+rowdata.auditName+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}else{
									return "<span >审核通过</span><br><span>"+name+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}

							}
							if(rowdata.auditStatus=='2'){
								if(rowdata.auditName!=null&&rowdata.auditName!=""){
									return "<span >驳回</span><br><span>"+rowdata.auditName+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}else{
									return "<span >驳回</span><br><span>"+name+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}
							}
							
						/*var html = []; 
						html.push("<a href=\"javascript:deleteviewProduct(" + rowdata.id + ");\">删除</a>"); 
							return html.join(""); */
						 }},
					 { display: '上线日期', name: "upDate", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = []; 
						 	var upDate;
	            	 	    if (rowdata.upDate!=null){
	            	 	    	upDate=new Date(rowdata.upDate);
		            	       /*  return upDate.format("yyyy-MM-dd"); */
	            	 	    }
	            	 	 /*   	html.push(upDate) */ //updateWetaoChannel
							html.push("<span>"+upDate.format("yyyy-MM-dd")+"</span><br><a href=\"javascript:updateupdate(" + rowdata.id + ");\">修改</a>"); 
/* 							
	            	 	 	var date =new Date();
	            	 	 
							if(date>rowdata.upDate&&rowdata.auditStatus=="0"){
								rejectMethod(rowdata.id); 
							}  */
							
							return html.join("");
							
						
						 }},
					 { display: '报名日期', name: "createDate", width: 100, align: "center", render: function(rowdata, rowindex) {
						 var createDate;
	            	 	    if (rowdata.createDate!=null){
	            	 	    	createDate=new Date(rowdata.createDate);
		            	        return createDate.format("yyyy-MM-dd");
	            	 	    }
						 }},
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
	<input type="hidden" id="pagetype" name="pagetype" value="${pagetype}">
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
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input name="productBrandName"/
							<c:if test="${not empty productBrandName }">readonly</c:if>
							value="${productBrandName }">
					</div>
				</div>
				<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">上线日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="sign_begin" name="sign_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#sign_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >~</div>
					<div class="l-panel-search-item">
						<input type="text" id="sign_end" name="sign_end" value=""  />
						<script type="text/javascript">
							$(function() {
								$("#sign_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>
			</div>
			
			
			<div class="search-tr">
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
					 		<div class="search-td-label" >品类：</div>
					 		<div class="search-td-condition" >
						 		<select id="productTypeId" name="productTypeId">
									<option value="">请选择</option>
									<c:forEach var="productType" items="${productTypes}">
										<option value="${productType.id}">${productType.name}</option>
									</c:forEach>
								</select>
		 	 				</div>
			 	</div>	
			 	
			 	<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">报名日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="create_begin" name="create_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#create_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >~</div>
					<div class="l-panel-search-item">
						<input type="text" id="create_end" name="create_end"  value=""  />
						<script type="text/javascript">
							$(function() {
								$("#create_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>		
				
			</div>

			<div class="search-tr">
				<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">审核状态：</div>
						<div class="l-panel-search-item">
						<select id="auditStatus" name="auditStatus" class="querysel" style="width:135px;">
							<option value="">请选择</option>
							<option value="0" selected>待审核</option>
							<option value="1">通过</option>
							<option value="2">驳回</option>
						</select>
					</div>
				</div>
		
			<c:if test="${pagetype==1030}">
				<div class="search-td">
					<div class="search-td-label">二级分类:</div>
					<div class="search-td-condition">
						<input name="twoType">
					</div>
				</div>
			</c:if>
			
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
