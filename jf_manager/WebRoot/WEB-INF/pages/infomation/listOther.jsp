<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 
 <script type="text/javascript">
 function seeInfoInfo(id) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "信息详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/infomation/editinfo.shtml?inforId=" + id+"&seeInfoInfo=1",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
	function gridRefresh() {//查看本人发布的
	    if (listModel.gridManager) {
	        var gridparms = [];
	        gridparms.push({ name: "staff", value: "self" });
	        listModel.gridManager.loadServerData(gridparms);
	    }
	}

	var listConfig={
			  
		      url:"/infomation/listdata.shtml?id="+${id},
		      
		      listGrid:{ columns: [  { display: 'ID', name: 'id'}, 
				                { display: ' 标题', name: 'title',render:function(rowdata, rowindex){
				                	var html=[];
				                	html.push("<a href=\"javascript:seeInfoInfo(" + rowdata.id + ");\">"+rowdata.title+"</a>");
				                	return html.join("");
				                }},
				                { display: '发布人<a href=\"javascript:gridRefresh();\">我发布的信息</a>',  name: 'creatName'}, 
				                { display: '发布时间', name: 'creatDataDsc'},
				                { display: '推送渠道', name: 'infoTypeDsc'},
				                { display: "操作", name: "OPER",align: "center", render: function(rowdata, rowindex) {
									var html = [];
								    html.push("<a href=\"javascript:seeInfoInfo(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
								    return html.join("");
							 }
			             }
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


<script type="text/javascript">
    var productTypeCombo;
    $(function(){
    	productTypeCombo= $("#catalogName").ligerComboBox({
              selectBoxWidth: 200,
              selectBoxHeight: 200,  
              valueField: 'id',
              textField: 'frontName',
              valueFieldID:'catalogId',
              treeLeafOnly:false,
              valueField : 'id',
              tree: { url: '${pageContext.request.contextPath}/infomation/getCatalogTree.shtml', checkbox: false, ajaxType: 'get', idFieldName: 'id',textFieldName:'frontName',parentIDFieldName:'parentId',isExpand:2}
          });
    });
    
    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  >
		    <div class="search-td">
			<div class="search-td-label">ID:</div>
			<div  class="search-td-combobox-condition">
				<input id="ids" name="ids" type="text" >
			</div>
			</div>
			 	
			 <div class="search-td">
			 <div class="search-td-label" >信息类别:</div>
			 <div class="search-td-condition" >
				<select id="status" name="status" class="status">
					<option value="1">正常信息</option>
					<option value="2">未使用信息</option>
				</select>
		 	 </div>
			 </div>
			 	 	
			<div class="search-td">
			<div class="search-td-label"  >标题：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "title" name="title" >
			</div>
			</div>
			
			<div class="search-td">
			 <div class="search-td-label" >推送渠道:</div>
			 <div class="search-td-condition" >
				<select id="infoTypeDsc" name="infoTypeDsc">
				    <option value=''>请选择...</option>
					<option value="1">游客</option>
					<option value="2">会员</option>
					<option value="3">未合作商家</option>
					<option value="4">入驻商家</option>
					<option value="5">平台用户</option>
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
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
