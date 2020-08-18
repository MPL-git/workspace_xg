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

function xiaonengcustomer(id, status) {
	var title;
	if (status=='1'){
		title="启用";
	}else{
		title="停用";
	}
	$.ligerDialog.confirm("是否"+title+"？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/xiaonengcustomerservice/chgStatus.shtml?id=" + id + "&status=" + status,
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




function editxiaonengcustomer(id) {
	$.ligerDialog.open({
	height: $(window).height() - 400,
	width: $(window).width() - 1100,
	title: "修改",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/editxiaonengcustomer.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}



var listConfig={
     url:"/mcht/xiaonengcustomerservice/data.shtml",
    
     btnItems: [{ text: '添加', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/mcht/addxiaonengcustomer.shtml", seqId:"", width: 700, height: 550 }], 
     listGrid:{  columns: [{ display: '客服序号', name:'id', width: 100,},
     			{ display: '企业ID', width: 100, name: 'busId' },
     			{ display: '商家名称', width: 200, name: 'mchtName' },
                { display: '客服代码', width: 200, name:'code' },        
                { display: '状态', width: 90, render: function (rowdata, rowindex){
            	   if(rowdata.status=="0"){
               		return "停用";       		
               	}else if(rowdata.status=="1"){
               	    return "启用";	                        		
               	}
               }},
               { display: "操作", name: "OPER", align: "center", width: 140, render: function(rowdata, rowindex) {
				var html = [];
				if (rowdata.status=='0'){
				    html.push("<a href=\"javascript:xiaonengcustomer(" + rowdata.id + ",'1');\">启用</a>"); 
				    html.push("<a href=\"javascript:editxiaonengcustomer(" + rowdata.id + ");\">修改</a>");
				}else{
					html.push("<a href=\"javascript:xiaonengcustomer(" + rowdata.id + ",'0');\">停用</a>");
					html.push("<a href=\"javascript:editxiaonengcustomer(" + rowdata.id + ");\">修改</a>");
				}
			    return html.join("&nbsp;&nbsp;");
			}}
               ],   
                showCheckbox : false,  //不设置默认为 true
                showRownumber:true//不设置默认为 true
     } , 							
    container:{
       toolBarName:"toptoolbar",
       searchBtnName:"searchbtn",
       fromName:"dataForm",
       listGridName:"maingrid"
     }     
};
</script>
<body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>  
	  <form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label">客服序号:</div>
					<div class="search-td-condition" >
						<input type="text" id = "id" name="id" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">企业ID:</div>
					<div class="search-td-condition" >
						<input type="text" id = "busId" name="busId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家名称:</div>
					<div class="search-td-condition" >
						<input type="text" id = "mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					 <div class="search-td-label" >状态:</div>
					 <div class="search-td-condition" >
						<select id=status name="status" class="aptitudeType">
							<option value="">请选择...</option> 
							<option value="0">停用</option> 
							<option value="1">启用</option> 
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
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
  </body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
