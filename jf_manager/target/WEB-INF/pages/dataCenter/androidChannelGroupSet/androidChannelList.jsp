<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript">
 $(function(){
	 
	 $(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left'
	 });
	 
 }); 

 
 function updateAndroidChannel(statusDescS){
	 $.ligerDialog.open({
		 height: 500,
		 width: 600,
		 title: "编辑",
		 name: "INSERT_WINDOW",
		 url: "${pageContext.request.contextPath}/androidChannelGroupSet/updateAndroidChannelManager.shtml?statusDescS="+statusDescS,
		 showMax: true,
		 showToggle: false,
		 showMin: false,
		 isResize: true,
		 slide: false,
		 data: null
	 });
 }
 
 var listConfig={
	 btnItems:[{text:'批量编辑', icon:'add', click: function() {
		 var data = listModel.gridManager.getSelectedRows();
        	if (data.length == 0) {
        		commUtil.alertError('请选择行！');
        	}else {
           		var statusDescS = "";
            	$(data).each(function() {
            		if(statusDescS != '' ) {
						statusDescS += ','
					}
					statusDescS += this.statusDesc;
            	});
				updateAndroidChannel(statusDescS);
        	}
        }}
	 ],
     url:"/androidChannelGroupSet/androidChannelList.shtml",
     listGrid:{ columns: [
			{ display: '渠道名称', name:'statusDesc', align:'center', isSort:false, width:180},
            { display: '所属推广渠道', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
            	if(rowdata.remark == '推广渠道' ) {
					return "";
				}else{
					return rowdata.remark;
				}
            }},
            { display: '操作', name:'', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
				return "<a href='javascript:;' onclick='updateAndroidChannel(\""+rowdata.statusDesc+"\");'>编辑</a>";
			}}
            ],
           showCheckbox : true,  //不设置默认为 true
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
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">渠道名称：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="remark" name="remark">
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn">查询</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>