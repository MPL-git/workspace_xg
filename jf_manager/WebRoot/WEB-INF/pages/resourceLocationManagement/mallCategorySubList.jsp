<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
    //修改排序值
	function updateProducttypeArtNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/resourceLocationManagement/updateProducttypeArtNo.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
					 }
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
						 $("#searchbtn").click();
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + id).val("");
			$("#seqNo" + id).parent().append("<br>"+"<span style='color:red;'>请输入正整数</span>");
		}
	}
 
	 function toEdit(id){
		 $.ligerDialog.open({
				width: $(window).width()*0.8,
				height: $(window).height()*0.9,
				title: "编辑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/resourceLocationManagement/toEdit.shtml?id=" + id+"&pagetype=${pagetype}",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	
		function changeStatus(id,status) {
			var title;
			if (status=="0"){
				title="状态是否改为上架？";
			}else if(status=="1"){
				title="状态是否改为下架？";
			}
			$.ligerDialog.confirm(title, function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/resourceLocationManagement/changeStatus.shtml?id=" + id,
						secureuri : false,
						dataType : 'json',
						cache : false,
						async : false,
						success : function(data) {
							if ("0000" == data.returnCode) {
								listModel.gridManager.reload();
							}else{
								$.ligerDialog.error(data.returnMsg);
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				}
			});
		}
	
		//删除
		function deleteviewCategory(id) {
		    var title="删除";
		    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
		    if (yes) {
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/resourceLocationManagement/deleteviewCategory.shtml',
				data: {id : id},
				dataType: 'json',
				success: function(data) {
					if(data.code != null && data.code == 200) {
						$("#searchbtn").click();
					}else {
						commUtil.alertError(data.msg);
					}
				},
				error: function(e) {
					commUtil.alertError("系统异常请稍后再试");
				}
			});
		   }
		  });
		 }
		
		//banner
		function addBannerList(sourceProductTypeId) {
			$.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 50,
				title : "",
				name : "INSERT_WINDOW",
				url : "${pageContext.request.contextPath}/resourceLocationManagement/bannerList.shtml?pagetype=${pagetype}&sourceProductTypeId="+sourceProductTypeId,
				showMax : true,
				showToggle : false,
				showMin : false,
				isResize : true,
				slide : false,
				data : null
			});
		}
		
      var listConfig={
	
      url:"/resourceLocationManagement/mallCategorySub/data.shtml?pagetype=${pagetype}",
	  btnItems:[{ text: '添加', icon: 'add', dtype:'win',  click: itemclick, url:'/resourceLocationManagement/toEdit.shtml?pagetype=${pagetype}', seqId:"", width : 600, height:500}],
      listGrid:{ columns: [ 
		                {display:'排序', name:'seqNo', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateProducttypeArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
	                        	return html.join("");
	                    }},
		                { display: '分类名称', name:'sourceProductTypeName',width: 150}, 
		              	{ display: '绑定一级类目', name:'producttype1Name',width:200,render:function(rowdata, rowindex){
		              		if (rowdata.producttype1Name!=null && rowdata.producttype1Name!='') {
		              			return rowdata.producttype1Name;
							}else if(rowdata.productType1Id!=null){
								return "请选择";
							}else{
								return "全部分类";
							}
		              	}},
		              	
		              	{ display: '绑定二级类目', name:'producttype2Name',width:200,hide:${pagetype!='1003' && pagetype!='1008' && pagetype!='1033'},render:function(rowdata, rowindex){
		              		if (rowdata.producttype2Name!=null && rowdata.producttype2Name!='') {
		              			return rowdata.producttype2Name;
							}else{
								return "-";
							}
		              	}},
		                { display: '状态', name: '', width:100,render:function(rowdata, rowindex){
		                	if (rowdata.status=='0') {
								return "下架";
							}else{
								return "上架";
							}
		                	
		                }},
		                { display: '创建时间', width: 150, render: function(rowdata, rowindex) {
	            	 	 	var createDate;
	            	 	    if (rowdata.createDate!=null){
	            	 	    	createDate=new Date(rowdata.createDate);
		            	        return createDate.format("yyyy-MM-dd hh:mm:ss");
	            	 	    }
				         }},
 		                { display: '操作', width:180, render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:toEdit(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;");																									                		
							if(rowdata.status=="0"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">上架</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
							    html.push("<a href=\"javascript:deleteviewCategory(" + rowdata.id + ");\">删除&nbsp;&nbsp;</a>"); 																							                		
							}else if(rowdata.status=="1"){
							    html.push("<a href=\"javascript:changeStatus(" + rowdata.id + ","+rowdata.status+");\">下架</a>&nbsp;&nbsp;");
							}
							if(${pagetype==1003}){
							html.push("|&nbsp;&nbsp;<a href=\"javascript:addBannerList(" + rowdata.id + ");\">banner</a>&nbsp;");	
							}
							
						    return html.join("");
		              	}},
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
       
  }
      
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	<input type="hidden" id="pagetype" name="pagetype" value="${pagetype}">
		<div class="search-pannel">
			<div class="search-tr"  >
			 	<div class="search-td">
				 	<div class="search-td-label">状态：</div>
				 	<div class="search-td-condition"  >
				    	<select id="status" name="status" class="querysel">
							<option value="">请选择</option>
							<option value="0">下架</option>
							<option value="1">上架</option>
						</select>
					</div>
			 	</div>
			 	<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="create_begin" name="create_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#create_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="create_end" name="create_end" value=""  />
						<script type="text/javascript">
							$(function() {
								$("#create_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>
			 	<div  class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" style="width: 100px; ">搜索</div> 
				</div>
			</div>
			
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
