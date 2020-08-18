<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/clipboard.min.js"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
<script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		
		//复制
		var clipboard = new Clipboard(".copySprUrl");  
		clipboard.on('success', function(e) {  
            console.log(e);
	    });  
		clipboard.on('error', function(e) {  
            console.log(e);
	    });
		
	 });
	 
	 //复制
	 function copySprUrl(sprPlanId) {
		 $("#copySprUrl_"+sprPlanId).attr("style", "color: red;");
         setTimeout(function(){ $("#copySprUrl_"+sprPlanId).attr("style", ""); }, 3000);
	 }
	 
	 //添加渠道
	 function addDouyinSprChnl() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "添加渠道",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/douyinSprChnl/editDouyinSprChnlManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //添加链接
	 function addDouyinSprPlan() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "添加链接",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/douyinSprPlan/addDouyinSprPlanManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //删除链接
	 function delDouyinSprPlan(douyinSprPlanId) {
		 $.ligerDialog.confirm("是否删除？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/douyinSprPlan/delDouyinSprPlan.shtml",
					 data : {douyinSprPlanId : douyinSprPlanId},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.code != 200) {
							 commUtil.alertError(data.msg);
						 }else {
							 $("#searchbtn").click();
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				 });
			 }
		 });
	 }
	 
 	 var listConfig={ 
	      url:"/douyinSprPlan/getDouyinSprPlanList.shtml",
	      btnItems:[
  		      { text: '添加渠道', icon:'add', id:'add', dtype:'win', click:addDouyinSprChnl },
  		      { text: '添加链接', icon:'add', id:'add', dtype:'win', click:addDouyinSprPlan }
  	      ],
	      listGrid:{ columns: [
							{display:'ID', name:'id', align:'center', isSort:false, width:100},
							{display:'渠道', name:'sprChnlName', align:'center', isSort:false, width:180},
							{display:'投放广告位置', name:'sprPlanSite', align:'center', isSort:false, width:180},
							{display:'页面类型', name:'sprPlanTypeDesc', align:'center', isSort:false, width:120},
							{display:'抖音转化id(提交订单)', name:'convertId', align:'center', isSort:false, width:120},
							{display:'抖音转化id(支付成功)', name:'convertId2', align:'center', isSort:false, width:120},
							{display:'抖音跟踪id', name:'trackingId', align:'center', isSort:false, width:120},
							{display:'页面ID', name:'', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
			                	if(rowdata.linkValue == null || rowdata.linkValue == '' ) {
									return "--";
								}else{
									return rowdata.linkValue;
								}
			                }},
							{display:'创建时间', name:'', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
			                	if(rowdata.createDate == null || rowdata.createDate == '' ) {
									return "";
								}else{
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								}
			                }},
			                {display:'链接', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
			                	var html = [];
			                	var sprUrl = rowdata.sprUrl==null?"":rowdata.sprUrl;
								html.push("<a href='javascript:;' onclick='copySprUrl("+ rowdata.id +");' class='copySprUrl' id='copySprUrl_"+ rowdata.id +"' data-clipboard-text=${mUrl }"+ sprUrl +" >【复制】</a>");
							    return html.join("");
							}},
			                {display:'操作', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='delDouyinSprPlan("+rowdata.id+");'>【删除】</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
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
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="douyinSprPlanId" name="douyinSprPlanId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >渠道：</div>
					<div class="search-td-combobox-condition" >
						<select id="sprChnlId" name="sprChnlId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="douyinSprChnl" items="${douyinSprChnlList }">
								<option value="${douyinSprChnl.id }">${douyinSprChnl.sprChnlName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >投放广告位置：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sprPlanSite" name="sprPlanSite" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >页面类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="sprPlanType" name="sprPlanType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sprPlanType" items="${sprPlanTypeList }">
								<option value="${sprPlanType.statusValue }">${sprPlanType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >页面ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="linkValue" name="linkValue" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			 	<div class="search-td-search-btn" >
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
