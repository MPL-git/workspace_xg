<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerTreeUtil.js"></script>	
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
	
	//添加
	function addLaxincommoditylist() {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title : "添加",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/resourceLocationManagement/addLaxincommodity.shtml",
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
			$("#seqNo" + id).parent().append("<br>"+"<span style='color:red;'>请输入正整数</span>");
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
	 
	 
	var productTypeCombo;
    $(function(){
    	productTypeCombo = $("#productTypeName").ligerComboBox({
              selectBoxWidth:200,
              selectBoxHeight:200, 
              valueField: 'id',
              textField: 'name',
              valueFieldID: 'productType',
              treeLeafOnly: false,
              valueField: 'id',
              resize: false,
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
	 
	 
	
 var listConfig={
      url:"/resourceLocationManagement/laXinRunGoodQualitydata.shtml",

      btnItems:[{text: '添加',icon: 'add',click: function() {
    	         addLaxincommoditylist();
      }},
	  ],
      listGrid:{ columns: [
						{display:'排序', name:'seqNo', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
								if (seqNo!=null && seqNo!='') {
									html.push("<br>");
									html.push("<a href=\"javascript:delseqNo(" + rowdata.id + ");\">清空</a>");
								}
	                        	return html.join("");
	                    }},
			            {display: '主图', name: 'pic', width: 80,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
			                       h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.linkId+")'>";
			                      }else{
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.linkId+")'>";
			                      }
			                    return h;

						}},
			            { display: '商品ID', name: 'productCode', width: 100}, 
			            { display: '品牌/货号', width: 180, render: function (rowdata, rowindex) {
			            	var html=[];
 		                	var artbrandGroup=rowdata.artbrandGroup;
 		                	var proutArtno=rowdata.proutArtno;
 		                	if (artbrandGroup!=null || proutArtno!=null){
 		                		/* return artbrandGroup.replace(/,/g,"<br>"); */
 		                		html.push(rowdata.artbrandGroup+"<br>");
 			                	html.push(rowdata.proutArtno+"<br>");               		
 		                	}
 			                	return html.join("");
		                }},
		                { display: '名称', width: 200, render: function(rowdata, rowindex) { 
							var html = [];
							html.push("<a href=\"javascript:viewProduct(" + rowdata.linkId + ");\">"+rowdata.productName+"</a>"); 
							return html.join("");
      					}},
      					{ display: '分类', name: '', width: 100,render:function(rowdata, rowindex){
		                	var html=[];
		                	html.push(rowdata.producttype1Name+"<br>");
		                	html.push(rowdata.producttype2Name+"<br>");
		                	html.push(rowdata.producttype3Name);
		                	return html.join("");
		                }},
		                { display: '性别/人群', width: 100, name: 'suitSex', render: function(rowdata, rowindex) {
		                	var sexStr=String(rowdata.suitSex);
		                	var sexShow="";
							var html = [];
		 					if (sexStr.charAt(0)=="1"){
								sexShow+="男、";
							}
		                    if (sexStr.charAt(1)=="1"){
		                    	sexShow+="女、";
							}
		                    html.push(sexShow.replace(/[、]$/,""));
		                    if (rowdata.suitSex!=null && rowdata.suitSex!='') {
		                    	html.push("<br>");
							}
		                    
		                    var groupStr=String(rowdata.suitGroup);
		                	var groupShow="";
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
		                  { display: '吊牌价/商城价/活动价', name: '', width: 130, render: function(rowdata, rowindex) {
	                             var html = [];
								
								html.push(rowdata.tagPriceMin);
								
								if(rowdata.tagPriceMin!=rowdata.tagPriceMax){
									html.push("-");
									html.push(rowdata.tagPriceMax);
								};
								if (rowdata.tagPriceMin!=null && rowdata.tagPriceMin!='') {						
								    html.push("<br>");
								}
	                              html.push(rowdata.mallPriceMin);
								if(rowdata.mallPriceMin!=rowdata.mallPriceMax){
									html.push("-");
									html.push(rowdata.mallPriceMax);
								};
								if (rowdata.mallPriceMin!=null && rowdata.mallPriceMin!='') {						
								    html.push("<br>");
								}
	                             html.push(rowdata.salePriceMin);
								if(rowdata.salePriceMin!=rowdata.salePriceMax){
									html.push("-");
									html.push(rowdata.salePriceMax);
								};
							    return html.join("");
			                	
			                	
						   }},
						   
						   
				     { display: '推荐文案', name: 'supPortQuantity', width: 200,render:function(rowdata, rowindex){
				         if (rowdata.recommendRemarks!=null && rowdata.recommendRemarks !='') {
				        	 return rowdata.recommendRemarks;
						 }else{
							 return "";
						 }
				     }},
				     { display: '状态', name: 'productStatusDesc', width: 100},  
		             { display: '更新时间', width: 100, render: function(rowdata, rowindex) {
            	 	 	var updateDate;
            	 	    if (rowdata.productUpdateDate!=null){
            	 	    	updateDate=new Date(rowdata.productUpdateDate);
	            	        
            	 	    }else{
            	 	    	updateDate=new Date(rowdata.productCreateDate);
            	 	    }
            	 	   return updateDate.format("yyyy-MM-dd");
			         }},
			         { display: "操作", name: "OPER", width: 88, align: "center", render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:deleteviewProduct(" + rowdata.id + ");\">删除</a>"); 
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
    
    </script>  
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
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
				<div class="search-td">
					<div class="search-td-label">醒购价：</div>
					<div class="search-td-condition">
						<input name="salePriceBegin" style="width:43%"> <span
							style="width:10%">--</span> <input name="salePriceEnd"
							style="width:43%">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">性别：</div>
					<div class="search-td-condition">
						<select id="suitSex" name="suitSex" class="querysel">
							<option value="">请选择</option>
							<option value="10">男</option>
							<option value="01">女</option>
							<option value="11">男、女</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">人群：</div>
					<div class="search-td-condition">
						<select id="suitGroup" name="suitGroup" class="querysel">
							<option value="">请选择</option>
							<option value="100">青年</option>
							<option value="010">儿童</option>
							<option value="001">中老年</option>
						</select>
					</div>
				</div>
			
			</div>
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">分类：</div>
					<div class="search-td-combobox-condition">
						<input id="productTypeName" type="text">
					</div>
				</div>	
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode" value="">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家：</div>
					<div class="search-td-condition">
						<input name="mchtName">
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
					<div class="search-td-label">货号：</div>
					<div class="search-td-condition">
						<input name="artNo">
					</div>
				</div>

				<div class="search-td">
					<div class="search-td-label">商品名称：</div>
					<div class="search-td-condition">
						<input name="name">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商品ID：</div>
					<div class="search-td-condition">
						<input name="code">
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">更新时间：</div>
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
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="create_end" name="create_end" value=""  />
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
