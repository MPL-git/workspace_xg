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
	 
	//编辑
	function edit(id,type) {
		$.ligerDialog.open({
			height: 450,
			width: 750,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/novaStrategy/edit.shtml?Id=" + id+"&type="+type ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
		
	//删除
	function delNovaStrategy(novaStrategyId) {
		$.ligerDialog.confirm('是否删除？', function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/novaStrategy/delnovaStrategy.shtml",
					 data : {novaStrategyId : novaStrategyId},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200)
							 commUtil.alertError(data.message);
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
	 
	//排序
	function updateArtNo(novaStrategyId,seqNo) {
		$("#seqNo" + novaStrategyId).parent().find("span").remove();
		var seqNo = $("#seqNo" + novaStrategyId).val();
		if(seqNo <0){
			commUtil.alertError("请输入正整数");
			return;
		}
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/novaStartegy/updateSeqNo.shtml",
				 data : {novaStrategyId : novaStrategyId, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.code != 200)
						 commUtil.alertError(data.msg);
					 else{
						 $("#seqNo" + novaStrategyId).parent().append("<span style='color:#009999;'>更改成功</span>");
						 $("#seqNo" + novaStrategyId).attr("seqNo", seqNo);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + novaStrategyId).val($("#seqNo" + hotSearchWordId).attr("seqNo"));
			$("#seqNo" + novaStrategyId).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	
 	var listConfig={	
	      url:"/novaStrategy/getNovaStrategyList.shtml?type="+${type},
	      btnItems:[{ text: '新增', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/novaStrategy/edit.shtml?type="+${type}, seqId:"", width: 750, height: 450}],
	      listGrid:{ columns: [
							{display:'排序', name:'seqNo', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id +","+seqNo+")' value='" + seqNo + "' >");
	                        	return html.join("");
	                        }},
	                        {display:'攻略标题', name:'title', align:'center', isSort:false, width:180},
	                        {display:'创建人', name:'createName', align:'center', isSort:false, width:180},
	                        {display:'创建时间', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'操作', name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
									html.push("<a href=\"javascript:edit(" + rowdata.id +");\">【编辑】</a>");	
									html.push("<a style=\"color:red\" href=\"javascript:delNovaStrategy(" + rowdata.id + ");\">【删除】</a>");
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
					<div class="search-td-label"  >攻略标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="title" name="title">
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;margin-right: 580px">
					<div id="searchbtn" class="l-button">搜索</div>
				</div>
			</div>	
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
