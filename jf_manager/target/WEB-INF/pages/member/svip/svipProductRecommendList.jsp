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
	
	//删除方法
	function del(id) {
		$.ligerDialog.confirm('是否删除？', function(yes) {
			if(yes) {

				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/svipOrder/delSvipProductRecommend.shtml",
					 data : {id : id},
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
	
	//排序
	function updateArtNo(id,seqNo) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		if(seqNo <0){
			commUtil.alertError("请输入正整数");
			return;
		}
		
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null || seqNo == '') {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/svipOrder/updateSvipProductRecommendSeqNo.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.code != 200)
						 commUtil.alertError(data.msg);
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>更改成功</span>");
						 $("#seqNo" + id).attr("seqNo", seqNo);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + id).val($("#seqNo" + hotSearchWordId).attr("seqNo"));
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	
	//自动抓取添加
 	function addSvipProductRecommendFirst() {
 		$.ligerDialog.open({
 			height: $(window).height() - 100,
 			width: $(window).width() - 200,
 			title: "商品确认",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/svipOrder/automaticGrab.shtml",
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	 }
	
	//手动新增添加
 	function addSvipProductRecommendSecond() {
 		$.ligerDialog.open({
 			height: $(window).height() - 100,
 			width: $(window).width() - 1150,
 			title: "新增商品",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/svipOrder/manuallyAdd.shtml",
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	 }

	//批量追加关键字
	function batchAppendKeyword(ids) {
		$("#ids").val(ids);
		$("#keyword").val("");
		$.ligerDialog.open({
			target: $("#appendKeywordDiv"),
			title: '添加/编辑追加关键字',
			width: 380,
			height: 150,
			isResize: true,
			modal: true
		});
	}

	//批量保存关键字
	function batchSaveKeyword() {
		let ids = $("#ids").val();
		let keyword = $("#keyword").val();
		if(keyword.length > 50){
			commUtil.alertError("关键字不能超过50字!");
			return;
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/svipOrder/batchSaveKeyword.shtml',
			data: {keyword : keyword, ids : ids},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
					$.ligerDialog.hide();
				}else {
					commUtil.alertError(data.msg);
					$.ligerDialog.hide();
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
				$.ligerDialog.hide();
			}
		});
	}

	//编辑关键字
	function editKeyword(id, keyword) {
		$("#ids").val(id);
		$("#keyword").val(keyword);
		$.ligerDialog.open({
			target: $("#appendKeywordDiv"),
			title: '添加/编辑追加关键字',
			width: 380,
			height: 150,
			isResize: true,
			modal: true
		});
	}

	var listConfig={
     url:"/svipOrder/getSvipProductRecommendList.shtml",
     btnItems:[  { text: '重新抓取', icon:'add', id:'add', dtype:'win', click:addSvipProductRecommendFirst },
                 {text: '新增商品', icon:'add', id:'add', dtype:'win', click:addSvipProductRecommendSecond },
				 {text: '批量追加关键字', icon: 'add', click: function () {
						 var data = listModel.gridManager.getSelectedRows();
						 if (data.length == 0) {
							 commUtil.alertError('请选择行！');
						 } else {
							 var str = "";
							 $(data).each(function () {
								 if (str == '') {
									 str = this.id;
								 } else {
									 str += "," + this.id;
								 }
							 });
							 batchAppendKeyword(str);
						 }
					 }}
               ],
      listGrid:{ columns: [
						{ display: '排序', name: 'seqNo', align:'center', width: 100, render: function(rowdata, rowindex) {
							var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id +","+seqNo+")' value='" + seqNo + "' >");
							return html.join("");
						}}, 
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
						 { display: '来源', name: 'source', width: 100, align: "center", render: function(rowdata, rowindex) {
							 var html = [];
								if(rowdata.source == '0'){
									html.push("自动抓取");
								}else if(rowdata.source == '1'){
									html.push("手动新增");
								}
								return html.join("");
							}},
						 { display: '追加关键字', name: 'keyword', width: 100, align: "center", render: function(rowdata, rowindex) {
								if(rowdata.keyword == null || rowdata.keyword == ''){
									return '<a href="javascript:editKeyword('+rowdata.id+',\''+" "+'\')">新增</a>';
								}else{
									return '<a href="javascript:editKeyword('+rowdata.id+',\''+rowdata.keyword+'\')">'+rowdata.keyword+'</a>';
								}
							}},
						 { display: '商家', name: 'mchtName', width: 200},
						 { display: "操作", name: "OPER", width: 100, align: "center", render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:viewProduct(" + rowdata.productId + ");\">查看</a> | ");
								html.push("<a href=\"javascript:del(" + rowdata.id + ");\">删除</a>");
								return html.join("");
							}}
		          ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true199
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
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
					<div class="search-td-label">商品ID：</div>
					<div class="search-td-condition">
						<input name="code">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">分类：</div>
					<div class="search-td-condition">
						<select id="productType1" name="productType1">
		               		<option value="">请选择</option>
		               		<c:forEach items="${productTypes}" var="productType">
			               		<option value="${productType.id}" <c:if test="${productType.id == mallCategorySub.productType1}">selected="selected"</c:if>>${productType.name}</option>
		               		</c:forEach>
		               	</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">来源：</div>
					<div class="search-td-condition">
						<select id="source" name="source">
		               		<option value="">请选择</option>
		               		<option value="0">自动抓取</option>
		               		<option value="1">手动新增</option>
		               	</select>
					</div>
				</div>

			</div>

			<div class="search-tr">
				
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode"
							<c:if test="${not empty mchtCode }">readonly</c:if>
							value="${mchtCode }">
					</div>
				</div>
				
				<div class="search-td">
			 	<div class="search-td-condition" style="text-align: left;width: 5%;margin-left:100px;">
					<input type="checkbox" value="1" name="order_by_seq_no">
				</div>
				<div class="search-td-label" >按排序值排序展示</div>
				</div>
				
				<div class="search-td-search-btn" style="display: inline-flex; ">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="搜索" id="searchbtn">
					</div>
					<!-- <div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();" >
					</div> -->
				</div>
			</div>
		</div>
	</form>
	<div id="appendKeywordDiv" style="text-align:center;display: none">
		<input type="hidden" id="ids" name="ids" value="">
		<input type="text" style="width: 90%;height: 25px" id="keyword" name="keyword" value="" onafterpaste="this.value=this.value.replace(/，/g,',')" onkeyup="this.value=this.value.replace(/，/g,',')" />
		<br>
		<br>
		<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="提交" onclick="batchSaveKeyword()">
	</div>
	
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
