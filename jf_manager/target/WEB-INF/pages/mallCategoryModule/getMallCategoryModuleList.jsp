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
		
		$("#child-div .l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		});
		
	 });
	 
	//排序
	function updateArtNo(mallCategoryModuleId) {
		$("#seqNo" + mallCategoryModuleId).parent().find("span").remove();
		var seqNo = $("#seqNo" + mallCategoryModuleId).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			if(Number(seqNo) < 100000 ) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/mallCategoryModule/updateSeqNo.shtml",
					 data : {mallCategoryModuleId : mallCategoryModuleId, seqNo : seqNo},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.code != 200)
							 commUtil.alertError(data.msg);
						 else{
							 $("#seqNo" + mallCategoryModuleId).parent().append("<span style='color:#009999;'>更改成功</span>");
							 $("#seqNo" + mallCategoryModuleId).attr("seqNo", seqNo);
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}else {
				$("#seqNo" + mallCategoryModuleId).val($("#seqNo" + mallCategoryModuleId).attr("seqNo"));
				$("#seqNo" + mallCategoryModuleId).parent().append("<span style='color:red;'>输入数字过大</span>");
			}
		}else{
			$("#seqNo" + mallCategoryModuleId).val($("#seqNo" + mallCategoryModuleId).attr("seqNo"));
			$("#seqNo" + mallCategoryModuleId).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	
	//上架或下架
	function updateStatus(mallCategoryModuleId, status) {
		var titleStr = '是否上架？';
		if(status == '0') {
			titleStr = '是否下架？';
		}
		$.ligerDialog.confirm(titleStr, function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/mallCategoryModule/updateStatus.shtml",
					 data : {mallCategoryModuleId : mallCategoryModuleId, status : status},
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
	function delMallCategoryModule(mallCategoryModuleId) {
		$.ligerDialog.confirm('是否删除？', function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/mallCategoryModule/delMallCategoryModule.shtml",
					 data : {mallCategoryModuleId : mallCategoryModuleId},
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
	function addMallCategoryModule() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "添加模块",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mallCategoryModule/addOrUpdateMallCategoryModuleManager.shtml?mallCategoryId=${mallCategoryId }",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	}
	
	//编辑
	function editMallCategoryModule(mallCategoryModuleId) {
		$.ligerDialog.open({
			height: 600,
			width: 700,
			title: "编辑模块",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mallCategoryModule/addOrUpdateMallCategoryModuleManager.shtml?mallCategoryModuleId="+mallCategoryModuleId+"&mallCategoryId=${mallCategoryId }",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//装修
	function decorateMallCategoryModule(mallCategoryModuleId, moduleType) {
		$.ligerDialog.open({
		 	height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "装修",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mallCategoryItem/mallCategoryItemManager.shtml?mallCategoryModuleId="+mallCategoryModuleId+"&moduleType="+moduleType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			id: 'child-div'
		});
	}
	
 	var listConfig={
	      url:"/mallCategoryModule/getMallCategoryModuleList.shtml?mallCategoryId=${mallCategoryId }",
	      btnItems:[
				{ text: '添加模块', icon:'add', id:'add', dtype:'win', click:addMallCategoryModule }          
	      ],
	      listGrid:{ columns: [
							{display:'排序值', name:'seqNo', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
	                        	return html.join("");
	                        }},
	                        {display:'ID', name:'id', align:'center', isSort:false, width:100},
	                        {display:'模块名称', name:'categoryModuleName', align:'center', isSort:false, width:180},
	                        {display:'绑定类目ID', name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html = [];
								var productType2Ids = rowdata.productType2Ids==null?'':rowdata.productType2Ids;
								if(productType2Ids != '') {
									var str = productType2Ids.split(",");
									for(var i=0;i<str.length;i++) {
										if(i != 0) {
											html.push("<br/>");
										}
										html.push(str[i]);
									}
								}
								return html.join("");
	                        }},
	                        {display:'绑定类目', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								var productType2Names = rowdata.productType2Names==null?'':rowdata.productType2Names;
								if(productType2Names != '') {
									var str = productType2Names.split(",");
									for(var i=0;i<str.length;i++) {
										if(i != 0) {
											html.push("<br/>");
										}
										html.push(str[i]);
									}
								}
								return html.join("");
	                        }},
	                        {display:'类型', name:'moduleTypeDesc', align:'center', isSort:false, width:180},
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
								html.push("<a href=\"javascript:editMallCategoryModule(" + rowdata.id + ");\">【编辑】</a>");
								html.push("<a href=\"javascript:decorateMallCategoryModule(" + rowdata.id + ", '" +rowdata.moduleType+ "');\">【装修】</a>");
								if(rowdata.status == '0' ) {
									html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '1');\">【上架】</a>");	
									html.push("<a href=\"javascript:delMallCategoryModule(" + rowdata.id + ");\">【删除】</a>");
								}else {
									html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '0');\">【下架】</a>");	
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
					<div class="search-td-label"  >ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mallCategoryModuleId" name="mallCategoryModuleId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >模块名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="categoryModuleName" name="categoryModuleName" >
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
					<div class="search-td-label"  >类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="moduleType" name="moduleType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="moduleType" items="${moduleTypeList }" >
								<option value="${moduleType.statusValue }">${moduleType.statusDesc }</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
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
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
