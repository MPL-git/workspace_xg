<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.color-1{color: #9D999D;}
.color-2{color: #FC0000;}
.color-3{color: #EFD104;}
.color-4{color: #00FC28;}
.color-5{color: #0351F7;}
.color-6{color: #DF00FB;}
.l-box-select .l-box-select-table td {
	font-size: 12px;
	line-height: 18px;
}
</style>
<script type="text/javascript">

function editMchtBaseInfo(id) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "商家基础资料",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

  var listConfig={
	  url:"/operationlog/mchtdetailsdata.shtml",
      listGrid:{ columns: [

		                { display: '用户工号', width: 150, name: 'createBy'},
		                { display: '用户名称', width: 150, name: 'staffName'},
		                { display: '公司名称', width: 200, name: 'companyName'},
		                { display: '商家序号', width: 150, name: 'mchtCode',render:function(rowdata, rowindex){
		                	if (rowdata.mchtCode!=null && rowdata.mchtCode!='') {
		                		return rowdata.mchtCode;
							 }
		                }},
		                { display: '店铺名称', width: 200, name: 'shopName',render:function(rowdata, rowindex){
		                	 var html=[];
		                	if (rowdata.shopName!=null && rowdata.shopName!='') {
		                		html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.shopName+"</a>"); 
							 }
		                	return html.join("");
		                }},
		                { display: '浏览时间', width: 150, name: '',render:function(rowdata, rowindex){
		                  if (rowdata.createDate!=null){
							  var createDate=new Date(rowdata.createDate);
							  return createDate.format("yyyy-MM-dd hh:mm:ss");
		                   }
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
};
  
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" method="post" action="" >
		<div class="search-pannel">	
		<div class="search-tr"  > 		
			<div class="search-td">
			<div class="search-td-label" >用户工号：</div>
			<div class="search-td-condition" >
				<input type="text" id="createBy" name="createBy" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label" >用户姓名：</div>
			<div class="search-td-condition" >
				<input type="text" id="staffName" name="staffName" >
			</div>
			</div>	
			
			<div class="search-td">
			<div class="search-td-label" >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtCode" name="mchtCode" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >商家名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="mchtName" name="mchtName" >
			</div>
			</div>
			
		</div>
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">浏览时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="createDatestar" name="createDatestar" value=""/>
				<script type="text/javascript">
					$(function() {
						$("#createDatestar").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;至</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="createDateEnd" name="createDateEnd" value=""/>
				<script type="text/javascript">
					$(function() {
						$("#createDateEnd").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>		
		</div>		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>