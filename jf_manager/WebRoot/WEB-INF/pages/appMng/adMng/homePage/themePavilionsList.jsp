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
	function chgAdInfoStatus(id, status) {
		var title;
		if (status=='1'){
			title="上架";
		}else{
			title="下架";
		}
		$.ligerDialog.confirm("是否"+title+"？", function (yes) {
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/appMng/adMng/chgStatus.shtml",
					data : {id : id, status : status},
					dataType : 'json',
					success : function(data) {
						if ("0000" == data.returnCode) {
							location.reload();
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function(){
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
	}
	
	function editAdInfo(id) {
		$.ligerDialog.open({
			height: 600,
			width: 900,
			title: "设置主题馆",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/adMng/editThemePavilions.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function preview(decorateInfoId) {
		$.ligerDialog.open({
			height: 800,
			width: 800,
			title: "预览",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/adMng/previewThemePavilions.shtml?decorateInfoId="+decorateInfoId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function design(decorateInfoId) {
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.8,
			title: "装修",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/adMng/design.shtml?decorateInfoId=" + decorateInfoId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	var listConfig = {
		      url:"/appMng/adMng/themePavilionsList.shtml",
		      btnItems:[{ text:'添加', icon:'add', id:'add', dtype:'win', click:itemclick, url:"/appMng/adMng/editThemePavilions.shtml?positionFlag=${positionFlag}", seqId:"", width: 600, height: 400 }], 
		      listGrid:{ columns: [
		                        {display:'ID',name:'id',width:80},
		                        {display:'主题名称',name:'remarks', align:'center', isSort:false, width:180 },
		                        {display:'上架时间',name:'autoUpDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		                        	if (rowdata.autoUpDate){
		        		            	var autoUpDate = new Date(rowdata.autoUpDate);
		        		            	if(autoUpDate<new Date()){
			        		            	return '<span style="color:red;">'+autoUpDate.format("yyyy-MM-dd hh:mm:ss")+'</span>';	
		        		            	}else{
			        		            	return autoUpDate.format("yyyy-MM-dd hh:mm:ss");	
		        		            	}
		        	            	}
		                        	return "";
		                        }},
		                        {display:'下架时间',name:'autoDownDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		                        	if (rowdata.autoDownDate != null){
		                        		var autoDownDate = new Date(rowdata.autoDownDate);
		        		            	if(autoDownDate<new Date()){
			        		            	return '<span style="color:red;">'+autoDownDate.format("yyyy-MM-dd hh:mm:ss")+'</span>';	
		        		            	}else{
			        		            	return autoDownDate.format("yyyy-MM-dd hh:mm:ss");	
		        		            	}
		        	            	}
		                        	return "";
		                        }},
		        	         	{display:'上架状态', name:'status', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
		                        	if(rowdata.status == '0')
		                        		return "下架";
		                        	if(rowdata.status == '1')
		                        		return "上架";
		                        }} ,
		                        { display:'主题信息操作', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		        					var html = [];
		        					if (rowdata.status=='0'){
		        					    html.push("<a href=\"javascript:editAdInfo(" + rowdata.id + ");\">编辑</a>");
		        					    html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'1');\">上架</a>"); 
		        					    html.push("<a href=\"javascript:preview(" + rowdata.linkId + ");\">预览</a>");
		        					}else{
		        						html.push("<a href=\"javascript:editAdInfo(" + rowdata.id + ");\">编辑</a>");
		        						html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'0');\">下架</a>");
		        					    html.push("<a href=\"javascript:preview(" + rowdata.linkId + ");\">预览</a>");
		        					}
		        				    return html.join("&nbsp;&nbsp;");
		        				}},
		        				{ display:'主题信息操作', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		        				    return "<a href=\"javascript:design(" + rowdata.linkId + ");\">装修</a>";
		        				}}
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
	
</script>  
<body style="padding: 0px; overflow: hidden;">
	<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
		<div id="toptoolbar"></div>
		<form id="dataForm" runat="server" >
			<div class="search-pannel">
				<div class="search-tr"  >
					<div class="search-td" style="position: relative;" >
						<div>
							<c:if test="${not empty id}">
								APP首页显示：ID为${id}
							</c:if>
							<c:if test="${empty id}">
								APP首页显示：空
							</c:if>
						</div>
					</div>
					<div class="search-td">
				 		<div class="search-td-label" >上架状态：</div>
				 		<div class="search-td-condition" >
					 		<select id="status" name="status">
								<option value="">请选择</option>
								<option value="0">下架</option>
								<option value="1">上架</option>
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
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
