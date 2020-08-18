<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>
 
 <script type="text/javascript">
 
  var viewerPictures;
    $(function(){
    	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    });
    
 function editorder(id) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "品牌修改",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/service/prod/product_brand/editbrand.shtml?productBrandID=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
	function viewerPic(imgPath){
		
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
		
	}
 
	 //首字母
	 function updateLetterIndex(id) {
		$("#letterIndex" + id).parent().find("span").remove();
		$("#letterIndex" + id).parent().find("br").remove();
		var letterIndex = $("#letterIndex" + id).val();
		var flag = letterIndex.match(/^[A-Z]$/);
		if(letterIndex != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/service/prod/product_brand/updateLetterIndex.shtml",
				 data : {productBrandId : id, letterIndex : letterIndex},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.code != 200) {
						 commUtil.alertError(data.msg);
					 }else{
						 $("#letterIndex" + id).parent().append("<span style='color:#009999;'>OK</span>");
						 $("#letterIndex" + id).attr("letterIndex", letterIndex);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#letterIndex" + id).val($("#letterIndex" + id).attr("letterIndex"));
			$("#letterIndex" + id).parent().append("</br><span style='color:red;'>请输入单个大写字母</span>");
		} 
	 }	

 
 //添加和修改品牌
 var myurl = "/service/prod/product_brand/editbrand.shtml?productBrandID=";
 
 var listConfig={
      url:"/service/prod/product_brand/data.shtml",
      btnItems:[{ text: '品牌添加', icon: 'add', dtype:'win',  click: itemclick, url:myurl, seqId:"", width : 1100, height:900}],
      listGrid:{ columns: [  { display:'品牌ID', name:'id', width:60 }, 
		                { display: '品牌名称', name:'name', width:180},
		                { display: '中文', name:'nameZh', width:180 }, 
		                { display: '英文', name:'nameEn', width:180 },
		                { display: '品牌logo', width:120, render: function (rowdata, rowindex) {
		                	var html = [];
		                	html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.logo+"' width='60' height='60' onclick='viewerPic(this.src)'>");
		                	return html.join("");
		                }},
		                { display: '适用品类', name:'', width:180, render:function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.productTypes != null) {
						    	var productTypes = rowdata.productTypes.split(",");
						    	for (var i=0;i<productTypes.length;i++) {
						    		if(i != 0) {
						    			html.push("<br>");
						    		}
						    		html.push(productTypes[i]);
						    	}
		                	}
						    return html.join("");
					 	}}, 
		                { display: '商标序号', name: '', width:180, render:function(rowdata, rowindex) {
							var html = [];
					    	if(rowdata.productBrandTrademarks != null) {
								var productBrandTrademarks = rowdata.productBrandTrademarks.split(",");
						    	for (var i=0;i<productBrandTrademarks.length;i++) {
						    		if(i != 0) {
						    			html.push("<br>");
						    		}
						    		html.push("商标注册号："+productBrandTrademarks[i]);
						    	}
					    	}
						    return html.join("");
					 	}}, 
		                { display:'状态', name:'statusDesc', width: 80},
		                { display:'操作', name:'', width:150, align:'center', render:function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:editorder(" + rowdata.id + ");\">修改</a>&nbsp;&nbsp;"); 
						    return html.join("");
					 	}},
					 	{display:'首字母',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
							var html = [];
							var letterIndex = rowdata.letterIndex==null?'':rowdata.letterIndex;
							html.push("<input type='text' style='width:80px; margin-top: 5px;' id='letterIndex" + rowdata.id + "' name='seqNo' letterIndex='"+letterIndex+"' onChange='updateLetterIndex(" + rowdata.id + ")' value='" + letterIndex + "' >");
                        	return html.join("");
                        }}
		         ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
     }, 							
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
		<div class="search-pannel">
			<div class="search-tr"  > 
				 <div class="search-td">
					 <div class="search-td-label" >状态:</div>
					 <div class="search-td-condition" >
						<select id="status" name="status"
							class="querysel">
							<option value="">请选择</option>
							<c:forEach var="list" items="${brandTmpStatus}">
								<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}
								</option>
							</c:forEach>
						</select>
				 	 </div>
				 </div>
				 <div class="search-td">
					 <div class="search-td-label" >品类:</div>
					 <div class="search-td-condition" >
						 <c:if test="${isCwOrgStatus == 1 }">
						 	<select id="productTypeGroup" name="productTypeGroup" class="querysel" disabled="disabled">
								<c:forEach var="list" items="${productTypes}">
									<option value="${list.id}">${list.name}</option>
								</c:forEach>
							 </select>
						 </c:if>
						 <c:if test="${isCwOrgStatus == 0 }">
						 	<select id="productTypeGroup" name="productTypeGroup" class="querysel">
								<option value="">请选择</option>
								<c:forEach var="list" items="${productTypes}">
									<option value="${list.id}">${list.name}</option>
								</c:forEach>
							 </select>
						 </c:if>
					 </div>
				 </div>
				 <div class="search-td">
					 <div class="search-td-label">商标序号:</div>
					 <div class="search-td-condition"  >
						<select id="tmkTypeGroup" name="tmkTypeGroup"
							class="querysel">
							<option value="">请选择</option>
							<option value="14">第14类</option>
							<option value="18">第18类</option>
							<option value="24">第24类</option>
							<option value="25">第25类</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >关键词：</div>
					<div class="search-td-condition" >
						<input type="text" id = "SEARCHCHAR" name="SEARCHCHAR" >
					</div>
				</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商标注册号：</div>
					<div class="search-td-condition" >
						<input type="text" id = "tmkCode" name="tmkCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >首字母：</div>
					<div class="search-td-condition" >
						<select id="letterIndex" name="letterIndex" class="querysel">
							<option value="">请选择</option>
							<option value="AA">空</option>
							<c:forEach var="AZ" items="${listAZ}">
								<option value="${AZ }">${AZ }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
