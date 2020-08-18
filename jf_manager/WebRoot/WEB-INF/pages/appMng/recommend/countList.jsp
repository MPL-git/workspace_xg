<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<html>
<style>
.seqClass{width:50px;height:23px;text-align:center; border:1px solid red;box-sizing: border-box;border-width: 1px; border-style: solid; border-color: rgba(121, 121, 121, 1);border-radius: 0px; }
</style>
<script type="text/javascript">
function view(catalog,eachDay) {
	var title="";
	if(catalog==1){
		title="首页推荐";
	}else if(catalog==2){
		title="运动推荐";
	}else if(catalog==3){
		title="鞋包推荐";
	}else if(catalog==4){
		title="服饰推荐";
	}else if(catalog==5){
		title="生活推荐";
	}
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: title,
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/appMng/recommend/list.shtml?catalog="+catalog+"&autoUpDate="+eachDay+"&autoDownDate="+eachDay ,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

	var listConfig={
      url:"/appMng/recommend/countData.shtml?catalog=${catalog}",
      listGrid:{  columns: [
	            { display: '日期', name:'eachDay'},
	            { display: '推荐中',render: function(rowdata, rowindex) {
	            	if (rowdata.recommendCount>0){
		            	return rowdata.recommendCount;
	            	}else{
	            		return "<span style='color:red;'>0</span>";
	            	}
	         	}},
	            { display: '今天上架', render: function(rowdata, rowindex) {
	            	if (rowdata.upCount>0){
	            		return rowdata.upCount;
	            	}else{
	            		return "<span style='color:red;'>0</span>";
	            	}
	         	}},
	            { display: '今天下架', render: function(rowdata, rowindex) {
	            	if (rowdata.downCount>0){
	            		return rowdata.downCount;
	            	}else{
	            		return "<span style='color:red;'>0</span>";
	            	}
	         	}},
                { display: '推荐专题', align: "center", render: function(rowdata, rowindex) {
                	var catalog = $("#catalog").val();
					return '<a href="javascript:;" onclick="view('+catalog+','+"'"+rowdata.eachDay+"'"+')">查看</a>';
				}}
                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true,//不设置默认为 true
                 showPager:false
      } , 							
     container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }     
  }
 </script>  
	<body style="padding: 0px; overflow: hidden;">
	<c:if test="${!showFlag }">
		<h2 style="color: red;margin: 10px 0px 0px 15px;">您暂无权限~~</h2>
	</c:if>
	<c:if test="${showFlag }">
		<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
			<div class="l-loading" style="display: block" id="pageloading"></div>
			<form id="dataForm" runat="server">
				<input type="hidden" id="catalog" name="catalog" value="${catalog}" />
				<div class="search-pannel">
					<div class="search-tr">
						<div class="search-td" style="margin-top:5px;">
							<div class="search-td-label" style="float:left;margin-top:5px;">日期：</div>
							<div class="l-panel-search-item">
								<input type="text" id="dateBegin" name="dateBegin" value="${dateBegin}" />
								<script type="text/javascript">
									$(function() {
										$("#dateBegin").ligerDateEditor({
											showTime : false,
											labelWidth : 150,
											width : 120,
											labelAlign : 'left'
										});
									});
								</script>
							</div>
							<div class="l-panel-search-item" style="margin-top:5px;">&nbsp;&nbsp;至：</div>
						</div>
						<div class="search-td" style="margin-top:5px;">
							<div class="l-panel-search-item">
								<input type="text" id="dateEnd" name="dateEnd" value="${dateEnd}" />
								<script type="text/javascript">
									$(function() {
										$("#dateEnd").ligerDateEditor({
											showTime : false,
											labelWidth : 150,
											width : 120,
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
				<div id="maingrid" style="margin: 0; padding: 0"></div>
			</form>
		</div>
	</c:if>
</body>
<script type="text/javascript">
function pic_show(url){
			$("#pic_img").attr("src",url);
			$("#pic_Div").show(100);
}
			
function pic_hide(){
			$("#pic_Div").hide(100);
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
