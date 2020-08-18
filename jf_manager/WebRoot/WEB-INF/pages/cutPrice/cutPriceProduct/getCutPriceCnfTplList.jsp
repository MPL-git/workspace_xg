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
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
	 });
	 
	//新增
	function addCutPriceCnfTplManager() {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 100,
			title: "新增",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/cutPriceProduct/addOrUpdateOrSeeCutPriceCnfTplManager.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//编辑或查看
	function updateOrSeeCutPriceCnfTpl(cutPriceCnfTplId, flag) {
		var title = "";
		if(flag == '1') {
			title = "编辑";
		}else if(flag == '2') {
			title = "查看";
		}
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 100,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/cutPriceProduct/addOrUpdateOrSeeCutPriceCnfTplManager.shtml?cutPriceCnfTplId="+cutPriceCnfTplId+"&flag="+flag,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
 	var listConfig={
	      url:"/cutPriceProduct/getCutPriceCnfTplList.shtml",
	      btnItems:[
	  		      { text: '新增', icon:'add', id:'add', dtype:'win', click:addCutPriceCnfTplManager }
	  	      ],
	      listGrid:{ columns: [
							{display:'方案名称',name:'name', align:'center', isSort:false, width:150},
							{display:'商品价格区间（吊牌价）',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return rowdata.beginPrice+"-"+rowdata.endPrice;
	                        }},
							{display:'预计完成砍价最低人数',name:'predictMinTime', align:'center', isSort:false, width:150},
							{display:'预计完成砍价最高人数',name:'predictMaxTime', align:'center', isSort:false, width:150},
							{display:'操作时间',name:'', align:'center', isSort:false, width:150, render:function(rowdata, rowindex) {
								if(rowdata.createDate == null || rowdata.createDate == '' ) {
									return '';
								}else{
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm");
								}
	                        }},
	                        {display:'操作人员',name:'createByName', align:'center', isSort:false, width:150},
	                        {display:'操作',name:'', align:'center', isSort:false, width:150, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:updateOrSeeCutPriceCnfTpl(" + rowdata.id + ", '1');\">【编辑】</a>");
								html.push("<a href=\"javascript:updateOrSeeCutPriceCnfTpl(" + rowdata.id + ", '2');\">【查看】</a>");
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
	<form id="dataForm" runat="server" style="display: none;" >
		<div class="search-pannel" >
			<div class="search-tr"  > 
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
