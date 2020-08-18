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
    $(function() {
        $(".dateEditor").ligerDateEditor({
            showTime: true,
            format: "yyyy-MM-dd",
            labelAlign: 'left',
            width: 135
        });
    })

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
			url: "${pageContext.request.contextPath}/customAdPage/keyPage/edit.shtml?id="+id+"&pageType=${pageType}",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function design(decorateInfoId,status) {
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.8,
			title: "装修",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/customAdPage/keyPage/design.shtml?decorateInfoId=" + decorateInfoId+"&status="+status+"&showType=${showType}",
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
	
	var listConfig = {
		      url:"/customAdPage/keyPage/getDataList.shtml?pageType=${pageType}",
		      btnItems:[{ text:'添加', icon:'add', id:'add', dtype:'win', click:itemclick, url:"/customAdPage/keyPage/edit.shtml?pageType=${pageType}", seqId:"", width: 600, height: 500 }], 
		      listGrid:{ columns: [
		                        {display:'ID',name:'id',width:80},
		                        {display:'主题名称',name:'pageName', align:'center', isSort:false, width:180 },
		                        { display: '所属类目', width: 100, name: 'sourceProductTypeName',hide:${pageType != 12 && pageType != 13 && pageType != 16 && pageType != 25}},
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
		        				{ display:'主题信息操作', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		        				    var html = [];
                                    html.push("<a href=\"javascript:design(" + rowdata.decorateInfoId + ",'1');\">装修</a>");
		        				    if(${pageType == 24}){
                                        html.push("<a href=\"javascript:copy(" + rowdata.decorateInfoId + ");\">复制</a>");
									}
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
		<input type="hidden" id="showType" name="showType" value="${showType}">
			<div class="search-pannel">
				<div class="search-tr"  >
				<c:if test="${pageType == 12 || pageType == 13 || pageType == 16 || pageType == 25}">
					<div class="search-td">
					 		<div class="search-td-label" >所属类目：</div>
					 		<div class="search-td-condition" >
						 		<select id="sourceProductTypeId" name="sourceProductTypeId">
									<option value="">请选择</option>
									<c:forEach var="sourceProductTypes" items="${sourceProductTypes}">
										<option value="${sourceProductTypes.id}">${sourceProductTypes.sourceProductTypeName}</option>
									</c:forEach>
								</select>
		 	 				</div>
			 		</div>
			 	</c:if>

				<c:if test="${pageType == 24}">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
						<div class="l-panel-search-item" >
							<input type="text" id="autoUpDateBegin" name="autoUpDateBegin" value="${autoUpDate }" class="dateEditor" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="autoUpDateEnd" name="autoUpDateEnd" value="${autoDownDate }" class="dateEditor" style="width: 135px;" />
						</div>
					</div>
			 	</c:if>
			 	 <div class="search-td">
					<div class="search-td-label">上架状态：</div>
					<div class="search-td-condition">
						<select id="isEffect" name="isEffect">
							<option value="">请选择</option>
							<option value="1">上架</option>
							<option value="0">下架</option>
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
