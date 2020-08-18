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
		
		$("#child-div .l-dialog-close").live("click", function(){
			$("#searchbtn").click();
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
	 
	//排序
	function updateArtNo(mallCategoryItemId) {
		$("#seqNo" + mallCategoryItemId).parent().find("span").remove();
		var seqNo = $("#seqNo" + mallCategoryItemId).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			if(Number(seqNo) < 100000 ) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/mallCategoryItem/updateSeqNo.shtml",
					 data : {mallCategoryItemId : mallCategoryItemId, seqNo : seqNo},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.code != 200)
							 commUtil.alertError(data.msg);
						 else{
							 $("#seqNo" + mallCategoryItemId).parent().append("<span style='color:#009999;'>更改成功</span>");
							 $("#seqNo" + mallCategoryItemId).attr("seqNo", seqNo);
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}else {
				$("#seqNo" + mallCategoryItemId).val($("#seqNo" + mallCategoryItemId).attr("seqNo"));
				$("#seqNo" + mallCategoryItemId).parent().append("<span style='color:red;'>输入数字过大</span>");
			}
		}else{
			$("#seqNo" + mallCategoryItemId).val($("#seqNo" + mallCategoryItemId).attr("seqNo"));
			$("#seqNo" + mallCategoryItemId).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	
	//上架或下架
	function updateStatus(mallCategoryItemId, status, itemPic) {
		if(status == '1' && itemPic == '' ) {
			commUtil.alertError("品牌图片不能为空，请先设置！");
			return;
		}
		var titleStr = '是否上架？';
		if(status == '0') {
			titleStr = '是否下架？';
		}
		$.ligerDialog.confirm(titleStr, function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/mallCategoryItem/updateStatus.shtml",
					 data : {mallCategoryItemId : mallCategoryItemId, status : status},
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
		});
	}
	
	//删除
	function delMallCategoryItem(mallCategoryItemId) {
		$.ligerDialog.confirm('是否删除？', function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/mallCategoryItem/delMallCategoryItem.shtml",
					 data : {mallCategoryItemId : mallCategoryItemId},
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
		});
	}
	
	//添加
	function addMallCategoryItem() {
		 $.ligerDialog.open({
			 	height: $(window).height() - 40,
				width: $(window).width() - 300,
				title: "添加品牌",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mallCategoryItem/productBrandManager.shtml?mallCategoryModuleId=${mallCategoryModuleId }",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null,
				id: 'child-div'
		});
	}
	
	//编辑
	function editMallCategoryItem(mallCategoryItemId) {
		$.ligerDialog.open({
			height: 600,
			width: 700,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mallCategoryItem/updateMallCategoryItemBrandManager.shtml?mallCategoryItemId="+mallCategoryItemId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
 	var listConfig={
	      url:"/mallCategoryItem/getMallCategoryItemList.shtml?mallCategoryModuleId=${mallCategoryModuleId }",
	      btnItems:[
				{ text: '添加品牌', icon:'add', id:'add', dtype:'win', click:addMallCategoryItem }          
	      ],
	      listGrid:{ columns: [
							{display:'排序值', name:'seqNo', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
	                        	return html.join("");
	                        }},
	                        {display:'品牌ID', name:'itemLinkValue', align:'center', isSort:false, width:100},
	                        {display:'品牌名称', name:'itemName', align:'center', isSort:false, width:180},
	                        {display:'品牌图片', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html = [];
								var itemPic = rowdata.itemPic==null?'':rowdata.itemPic;
								if(itemPic != '') {
									html.push("<img style='margin: 5px;' src='${pageContext.request.contextPath}/file_servelt"+itemPic+"' onclick='viewerPic(this.src)' width='60' height='60'>");
								}
								return html.join("");
	                        }},
	                        {display:'商品数', name:'productBrandCount', align:'center', isSort:false, width:100},
	                        {display:'状态', name:'statusDesc', align:'center', isSort:false, width:180},
	                        {display:'创建时间', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'操作', name:'', align:'center', isSort:false, width:260, render: function(rowdata, rowindex) {
								var html = [];
								var itemPic = rowdata.itemPic==null?'':rowdata.itemPic;
								html.push("<a href=\"javascript:editMallCategoryItem(" + rowdata.id + ");\">【编辑】</a>");
								if(rowdata.status == '0' ) {
									html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '1', '" + itemPic + "');\">【上架】</a>");	
									html.push("<a href=\"javascript:delMallCategoryItem(" + rowdata.id + ");\">【删除】</a>");
								}else {
									html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '0', '" + itemPic + "');\">【下架】</a>");	
								}
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >品牌ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="itemLinkValue" name="itemLinkValue" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="itemName" name="itemName" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="status" items="${statusList }" >
								<option value="${status.statusValue }">${status.statusDesc }</option>
							</c:forEach>
						</select>
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
