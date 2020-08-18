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
	function changeIsEffect(id, isEffect) {
		var title;
		if (isEffect=='1'){
			title="上架";
		}else{
			title="下架";
		}
		$.ligerDialog.confirm("是否"+title+"？", function (yes) {
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/customAdPage/keyPage/changeIsEffect.shtml",
					data : {id : id, isEffect : isEffect},
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
	
	function edit(id) {
		$.ligerDialog.open({
			height: 600,
			width: 900,
			title: "设置主题馆",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/customAdPage/keyPage/edit.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function design(decorateInfoId,status,pageType) {
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.8,
			title: "装修",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/customAdPage/keyPage/design.shtml?decorateInfoId=" + decorateInfoId+"&status="+status +"&pageType="+ pageType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//复制
	function copy(decorateInfoId) {
		$.ligerDialog.confirm('是否确认复制？',function(yes){
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/customAdPage/keyPage/copy.shtml?decorateInfoId="+decorateInfoId,
					type : 'GET',
					dataType : 'json',
					cache : false,
					async : false,
					timeout : 30000,
					success : function(result) {
						if (result.returnCode =='200') {
							window.location.reload();
						}else if(result.returnCode =='4004'){
							$.ligerDialog.error(result.message);
						}
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
	}

    //删除装修
    function delInfo(customAdPageId) {
        $.ligerDialog.confirm('是否删除【'+$("#tagDiv"+customAdPageId).attr("pageName")+'】？',function(yes){
            if(yes){
                $.ajax({
                    url : "${pageContext.request.contextPath}/customAdPage/keyPage/delInfo.shtml?customAdPageId="+customAdPageId,
                    type : 'GET',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    timeout : 30000,
                    success : function(result) {
                        if (result.returnCode =='200') {
                            window.location.reload();
                        }else if(result.returnCode =='4004'){
                            $.ligerDialog.error(result.message);
                        }
                    },
                    error : function() {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
            }
        });
    }

	var id = -1;
	<c:if test="${not empty id}">
	id = ${id};
	</c:if>
	var listConfig = {
		      url:"/customAdPage/keyPage/getDataList.shtml?pageType=${pageType}",
		      btnItems:[{ text:'添加', icon:'add', id:'add', dtype:'win', click:itemclick, url:"/customAdPage/keyPage/edit.shtml?pageType=${pageType}", seqId:"", width: 600, height: 400 }], 
		      listGrid:{ columns: [
		                        {display:'ID',name:'id',width:80},
		                        {display:'主题名称',name:'pageName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		                        	if(rowdata.id == id){
		                        		return '<span style="color:red;">'+rowdata.pageName+'</span>';
		                        	}else{
		                        		return rowdata.pageName;
		                        	}
		                        	return "";
		                        }},
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
		        	         	{display:'上架状态', name:'isEffect', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
		                        	if(rowdata.isEffect == '0')
		                        		return "下架";
		                        	if(rowdata.isEffect == '1')
		                        		return "上架";
		                        }} ,
		                        { display:'主题信息操作', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		        					var html = [];
		        					if (rowdata.isEffect=='0'){
		        					    html.push("<a href=\"javascript:edit(" + rowdata.id + ");\">编辑</a>");
		        					    html.push("<a href=\"javascript:changeIsEffect(" + rowdata.id + ",'1');\">上架</a>"); 
		        					    html.push('<a href="${previewUrl}'+rowdata.decorateInfoId+'" target="_blank">预览</a>');
		        					}else{
		        						html.push("<a href=\"javascript:edit(" + rowdata.id + ");\">编辑</a>");
		        						html.push("<a href=\"javascript:changeIsEffect(" + rowdata.id + ",'0');\">下架</a>");
		        					    html.push('<a href="${previewUrl}'+rowdata.decorateInfoId+'" target="_blank">预览</a>');
		        					}
		        				    return html.join("&nbsp;&nbsp;");
		        				}},
		        				{ display:'主题信息操作', align:'center', isSort:false, width:180, hide:${pageType =='3'|| pageType =='20'}, render:function(rowdata, rowindex) {
		        					var html = [];
		        					html.push("<a href=\"javascript:design(" + rowdata.decorateInfoId + ",'0'," + rowdata.pageType + ");\">装修</a>");
		        					html.push("<a href=\"javascript:copy(" + rowdata.decorateInfoId + ");\">复制</a>");
		        					if(rowdata.isEffect == '0'){
                                        html.push("<span id='tagDiv" + rowdata.id + "' pageName='"+rowdata.pageName+"'><a href=\"javascript:delInfo(" + rowdata.id + ");\">删除</a></span>");
									}else {
                                        html.push("<span id='tagDiv" + rowdata.id + "' pageName='"+rowdata.pageName+"' style='display: none'><a href=\"javascript:delInfo(" + rowdata.id + ");\">删除</a></span>");
									}
		        					return html.join("&nbsp;&nbsp;");
		        				}},
		        				{ display:'主题信息操作', align:'center', isSort:false, width:180, hide:${pageType =='2'|| pageType =='10' || pageType =='11'}, render:function(rowdata, rowindex) {
		        					var html = [];
		        					html.push("<a href=\"javascript:design(" + rowdata.decorateInfoId + ",'0'," + rowdata.pageType + ");\">装修</a>");
		        					html.push("<a href=\"javascript:copy(" + rowdata.decorateInfoId + ");\">复制</a>");
		        					return html.join("&nbsp;&nbsp;");
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
							<c:if test="${pageType =='2'}">首页栏目装修</c:if>
							<c:if test="${pageType =='3'}">商品主题馆</c:if>
							<c:if test="${pageType =='10'}">首页栏目装修</c:if>
							<c:if test="${pageType =='11'}">首页栏目装修</c:if>
							<c:if test="${pageType =='20'}">首页H5装修</c:if>
							<c:if test="${not empty id}">
								APP首页显示：ID为${id}
							</c:if>
							<c:if test="${empty id}">
								APP首页显示：空
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
