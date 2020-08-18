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
    	$("#agoods").hide();
    	
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
	    if(${pagetype==1001 && staffID ne 1}){
            commUtil.alertError("仅有Admin有权限操作!");
		}else{
            $.ligerDialog.open({
                height: $(window).height() - 50,
                width: $(window).width() - 50,
                title : "添加",
                name : "INSERT_WINDOW",
                url : "${pageContext.request.contextPath}/resourceLocationManagement/administrationCommodity.shtml?sourceType=${sourceType}",
                showMax : true,
                showToggle : false,
                showMin : false,
                isResize : true,
                slide : false,
                data : null
            });
		}

	}

	//权重明细
	function weightDetail(linkId) {
		$.ligerDialog.open({
			height: $(window).height() - 300,
			width: $(window).width() - 500,
			title : "明细",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/resourceLocationManagement/weightDetail.shtml?linkId="+linkId,
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
	
 var listConfig={
      url:"/resourceLocationManagement/sourcenichedata.shtml?pagetype=${pagetype}",

      btnItems:[{text: '添加',icon: 'add',click: function() {
    	         addcommoditylist();
      }},
	  ],
      listGrid:{ columns: [
						{display:'排序', name:'seqNo', hide:${pagetype==1001}, align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
								if (seqNo!=null && seqNo!='') {
									html.push("<br>");
									html.push("<a href=\"javascript:delseqNo(" + rowdata.id + ");\">清空</a>");
								}
	                        	return html.join("");
	                    }},
	                    
			            {display: '权重', name: 'weight', width: 80, render: function(rowdata, rowindex) {
                            var html = [];
			            	if(rowdata.weight==null|| rowdata.weight==0){
                                html.push("0");
			            	}else{
                                html.push(rowdata.weight);
			            	}
			            	html.push("<br><a href=\"javascript:weightDetail(" + rowdata.linkId + ");\">明细</a>");
			            	return html.join("");
			            }},
						
			       	 { display: '上线日期', name: "upDate", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = []; 
						 	var upDate;
	            	 	    if (rowdata.upDate!=null && rowdata.upDate != ""){
	            	 	    	upDate=new Date(rowdata.upDate);
								html.push("<span>"+upDate.format("yyyy-MM-dd")+"</span>");
		            	       /*  return upDate.format("yyyy-MM-dd"); */
	            	 	    }
	            	 	 /*   	html.push(upDate) */ //updateWetaoChannel
							return html.join("");
						 }},
			            
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
		                
		                    { display: '二级分类',  name: 'producttype2Name', width: 180,hide:${pagetype!=1031}}, 
		                    
		                    { display: '推荐文案', name: 'recommendRemarks', width: 180 , hide:${pagetype!=1001}}, 
      					
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
	                
	                { display: '最新库存数', name: 'stockSum', width: 80, render:function(rowdata, rowindex){
	                	return rowdata.stockSum;
	                }},
	   
				     { display: '商品DSR', name: '', width: 150,render:function(rowdata, rowindex){
				     	var html=[];
				     	var productScore = rowdata.avgProductScore == null || rowdata.avgProductScore == 0 ? 5: rowdata.avgProductScore;
				     	var mchtScore = rowdata.avgMchtScore == null || rowdata.avgMchtScore == 0 ? 5: rowdata.avgMchtScore;
				     	var wuliuScore = rowdata.avgWuliuScore == null || rowdata.avgWuliuScore == 0 ? 5: rowdata.avgWuliuScore;
				    	html.push("商品描述:<span>"+(productScore*1).toFixed(2)+"</span><br>")
				    	html.push("卖家服务:<span>"+(mchtScore*1).toFixed(2)+"</span><br>")
				    	html.push("物流服务:<span>"+(wuliuScore*1).toFixed(2)+"</span>")

				    	return html.join(""); 
				     }},
				     
				     { display: '好评率', name: 'goodCommentRate', width: 100,render:function(rowdata, rowindex){
 							
						 return rowdata.goodCommentRate;
				     }},  

				     { display: '点赞数', name: 'supportQuantity',  hide:${pagetype!=1001}, width: 100,render:function(rowdata, rowindex){
				    	 if (rowdata.supportQuantity!=null && rowdata.supportQuantity!='') {
				    		 return rowdata.supportQuantity;
						}else{
							return "0";
						}
				     }},
				     
				     { display: '上线日期', name: "upDate", width: 100, align: "center", render: function(rowdata, rowindex) {
							var html = []; 
						 	var upDate;
	            	 	    if (rowdata.upDate!=null){
	            	 	    	upDate=new Date(rowdata.upDate);
								html.push("<span>"+upDate.format("yyyy-MM-dd")+"</span>");
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
       
  };
 
/*  function changeGoods(){
	 alert('11')
	 var a =  $("#whichProduct").val();
	 console.log(a)
	 if(a==1){
		$("#agoods").hide;
		$("#pgoods").show;
	 }
	 if(a==2){
		$("#agoods").show;
		$("#pgoods").hide;
	 }
 }
  */

 

/*  
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
    	
    }); */
    
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
	
		<input type="hidden" id="sourceType" name="sourceType" value="${sourceType}">
		<input type="hidden" id="pagetype" name="pagetype" value="${pagetype}">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				
				<div class="search-td">
					<div class="search-td-label">醒购价：</div>
					<div class="search-td-condition">
						<input name="salePriceBegin" style="width:43%"> <span
							style="width:10%">--</span> <input name="salePriceEnd"
							style="width:43%">
					</div>
				</div>
		
				<div class="search-td">
					<div class="search-td-label">商品名称：</div>
					<div class="search-td-condition">
						<input name="name">
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
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input name="productBrandName"
							<c:if test="${not empty productBrandName }">readonly</c:if>
							value="${productBrandName }">
					</div>
				</div>
			
			</div>
			
			
			
			<div class="search-tr">
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
			 	
			 	
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode" value="">
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
			<c:if test="${pagetype==1031}">
				<div class="search-td">
					<div class="search-td-label">二级分类:</div>
					<div class="search-td-condition">
						<input name="twoType">
					</div>
				</div>
			</c:if>
			
			
				<div class="search-td">
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
