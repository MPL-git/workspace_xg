<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
<html>
<head>
<script type="text/javascript">
	var viewerPictures;
    $(function(){
    	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    });
	function viewBrandTmp(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家品牌查看",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/productBrandTmp/view.shtml?productBrandTmpId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	function editBrandTmp(id) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "商家品牌审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/productBrandTmp/auditing.shtml?productBrandTmpId=" + id,
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
	
	
 var listConfig={
      url:"/productBrandTmp/datalist.shtml",
     
      btnItems:[],
      listGrid:{ columns: [
		                
			            { display: '申请ID', name: 'productBrandTmp.id',width:60},
			            { display: '品牌名称', name: 'productBrandTmp.name',width:180 }, 
			            { display: '中文',  name: 'productBrandTmp.nameZh', width: 180 }, 
		                { display: '英文',  name: 'productBrandTmp.nameEn' ,width: 180 }, 
		                { display: '图片',  name: 'productBrandTmp.logo', width:60,render: function(rowdata, rowindex) {
		                    var h = "";
		                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productBrandTmp.logo+"' width='60' height='60' onclick='viewerPic(this.src)'>";
		                    return h;
						    }},
		                { display: '适用品类', name: 'productBrandTmp.productTypeGroup', width:180 ,render: function(rowdata, rowindex) {
							var html = [];
					    	var productTypes=rowdata.productTypes;
					    	for (var i=0;i<productTypes.length;i++)
					    	{
					    		html.push(productTypes[i].name+"<br>");
					    	}
						    
						    return html.join("");
					 }},
		                { display: '商标类别', name: 'productBrandTmp.tmkTypeGroup', width: 100 },
		                { display: '状态', name: 'statusName' , width: 50},
		                { display: "操作", name: "OPER", width: 150, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    	
						     if (rowdata.productBrandTmp.status=='1'||rowdata.productBrandTmp.status=='4'){
						    	  html.push("<a href=\"javascript:editBrandTmp(" + rowdata.productBrandTmp.id + ");\">审核</a>&nbsp;&nbsp;"); 
						     }else{
						    	 html.push("<a href=\"javascript:viewBrandTmp(" + rowdata.productBrandTmp.id + ");\">查看</a>&nbsp;&nbsp;"); 
						     }
						    return html.join("");
					 }
	             }
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
 
 
 function addMcht() {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家录入",
			name: "INSERT_WINDOW",
			url: contextPath + "/mcht/toAddMcht.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			buttons: [
				{text: "保存",
				 cls: "l-dialog-btn-highlight",
				 onclick: function(item, dialog) {
					 var result = $("#INSERT_WINDOW")[0].contentWindow.submit_product();
					 //var result = $("#INSERT_WINDOW")[0].contentWindow.onSubmit();
					 if(result.RESULT_CODE != 0) {
						 //$.ligerDialog.warn(result.RESULT_MESSAGE);
					 } else {
						 dialog.close();
						$.ligerDialog.success(result.RESULT_MESSAGE);
						 searchData();
					 }
				 }
				},
				{text: "取消",
				 onclick: function (item, dialog) {dialog.close();}
				}
			]
		});
	}
  
    </script>
<script type="text/javascript"> 
function pic_show(url){
	$("#pic_img").attr("src",url);
	$("#pic_Div").show(100);
}

function pic_hide(){
	$("#pic_Div").hide(100);
}
    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	
	<form id="dataForm" runat="server">
		<div id="topmenu"></div>
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label">状态：</div>
			<div class="search-td-condition">
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
			<div class="search-td-label">品类：</div>
			<div  class="search-td-condition">
				<select  id="productTypeGroup" name="productTypeGroup"
					class="querysel">
					<option value="">请选择</option>
					<!-- 						<option value="A">未审核</option> -->
					<c:forEach var="list" items="${productTypes}">
						<option value="${list.id}">${list.name}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label">商标类别：</div>
			<div  class="search-td-condition">
				<select  id="tmkTypeGroup" name="tmkTypeGroup"
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
			<div class="search-td-label">关键词：</div>
			<div  class="search-td-condition">
				<input name="searchKeyWord">
			</div>
			</div>
			</div>
			<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label">申请ID：</div>
			<div class="search-td-condition">
				<input name="id">
			</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			</div>
			</div>
	</form>
	 	<div id="toptoolbar"></div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
<div id="pic_Div" style="z-index: 10000;display: none;width: 100%;height: 100%;position: absolute; !important;">
		
		<table
			style="background-color: transparent; z-index: 2001; position: absolute; width: 100%; height: 100% !important">
			<tr>
				<td valign="middle" align="center">
				<div onclick="pic_hide()" title="关闭图片">
						<img style="width: 100%;height: 100%;" id="pic_img" alt="自动关闭" />
					</div>
				</td>
			</tr>
		</table>
		<div
			style="position: fixed; filter: alpha(Opacity = 50); -moz-opacity: 0.5; opacity: 0.5; z-index: 2000; background-color: #000000; height: 100%; width: 100%; text-align: center; vertical-align: middle;">
		</div>

</div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
