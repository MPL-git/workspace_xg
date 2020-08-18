<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
$(function(){
	$("#toSearch").bind('click',function(){
		var type = $("#type").val();
		if(type == 1){
			$("a[name='common']").closest("tr").show();
			$("a[name='highCommission']").closest("tr").hide();
		}else if(type == 2){
			$("a[name='highCommission']").closest("tr").show();
			$("a[name='common']").closest("tr").hide();
		}else{//请选择类型时
			var favorites_title = $("#favorites_title").val();
			if(favorites_title){
				$("a[name='"+favorites_title+"']").closest("tr").siblings().hide();
				$("a[name='"+favorites_title+"']").closest("tr").show();
			}else{
				$("a[name='common']").closest("tr").show();
				$("a[name='highCommission']").closest("tr").show();
			}
		}
	}); 
});
 
function toBatchCatch(id){
	$.ligerDialog.open({
		height: 350,
		width: $(window).width()*0.8,
		title: "选择分类",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/taobaoke/toBatchCatch.shtml?favoritesId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
     url:"/taobaoke/getFavoritesData.shtml",
     listGrid:{ columns: [
		                { display: '选品库类型', name: 'type', render: function (rowdata, rowindex) {
		                	if(rowdata.type == 1){
		                		return '<a href="javascript:;" name="common">普通类型</a>';
		                	}else if(rowdata.type == 2){
		                		return '<a href="javascript:;" name="highCommission">高佣金类型</a>';
		                	}
		                }},
		                { display: '选品库id', name: 'favorites_id'}, 
		                { display: '选品组名称', name: 'favorites_title', render: function (rowdata, rowindex) {
		                	return '<a href="javascript:;" name="'+rowdata.favorites_title+'">'+rowdata.favorites_title+'</a>';
		                }},
		                { display: '操作', render: function (rowdata, rowindex) {
		                	var html = [];
		                	html.push('<a href="javascript:;" onclick="toBatchCatch('+rowdata.favorites_id+');">一键抓取</a>');
	                		return html.join("");
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: false //不设置默认为 true
      } ,  							
     container:{
        searchBtnName:"search2",
        fromName:"dataForm2",
        listGridName:"maingrid2"
      }        
  };
</script>
<style type="text/css">
	.l-box-select .l-box-select-table td {
		font-size: 12px;
		line-height: 18px;
	}
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm2" runat="server" method="post">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label">类型</div>
				<select id="type" name="type">
					<option value="">请选择</option>
					<option value="1">普通类型</option>
					<option value="2">高佣金类型</option>
				</select>
			</div>
			<div class="search-td">
				<div class="search-td-label">名称</div>
				<div class="search-td-condition">
					<input type="text" id="favorites_title" name="favorites_title" >
				</div>
			</div>
			<div class="search-td-search-btn">
				<input type="button" style="width: 80px;height: 25px;cursor: pointer;" value="搜索" id="toSearch">
			</div>
		</div>
		</div>
		<div id="maingrid2" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>