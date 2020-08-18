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

function chgsstyleadministraion(id, status) {
	var title;
	if (status=='1'){
		title="上架";
	}else{
		title="下架";
	}
	$.ligerDialog.confirm("是否"+title+"？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/styleadministraion/chgStatus.shtml?id=" + id + "&status=" + status,
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


//修改排序值
function updateSeqNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		if(seqNo != '') {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/appMng/styleAdministration/submit.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
					 }
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}
	}


function editsale(id) {
	$.ligerDialog.open({
	height: $(window).height() - 400,
	width: $(window).width() - 1100,
	title: "修改风格",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/appMng/styleAdministration/edit.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function delstyle(id) {
	$.ligerDialog.confirm("是否删除？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/styleAdministration/styledellist.shtml?id=" + id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						//location.reload();
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


var listConfig={
     url:"/appMng/styleAdministration/data.shtml",
    
     btnItems: [{ text: '添加', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/appMng/styleAdministration/edit.shtml", seqId:"", width: 700, height: 550 }], 
     listGrid:{  columns: [{ display: '排序', name:'seqNo', width: 100, render: function (rowdata, rowindex){
    	      var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
     	      return "<input type='text' id='seqNo" + rowdata.id + "' name='seqNo' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >";
     			}}, 
     			{ display: 'ID', width: 100, name: 'id' },
     			{ display: '风格名称', width: 200, name: 'name' },
               { display: '图片', width: 120, render: function (rowdata, rowindex){
                   var h = "";
                      h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='80' height='80' onclick='pic_show(this.src)'>";
                   return h;
               }}, 
               { display: '性别', width: 200, name: 'sex' , render: function (rowdata, rowindex){
            	   if(rowdata.sex=="1"){
               		return "男";       		
               	}else if(rowdata.sex=="2"){
               	   return "女";	                        		
               	}
               }},
               { display: "操作", name: "OPER", align: "center", width: 140, render: function(rowdata, rowindex) {
				var html = [];
				if (rowdata.status=='0'){
				    html.push("<a href=\"javascript:chgsstyleadministraion(" + rowdata.id + ",'1');\">上架</a>"); 
				    html.push("<a href=\"javascript:delstyle(" + rowdata.id + ");\">删除</a>");
				    html.push("<a href=\"javascript:editsale(" + rowdata.id + ");\">修改</a>");
				}else{
					html.push("<a href=\"javascript:chgsstyleadministraion(" + rowdata.id + ",'0');\">下架</a>");
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
}
</script>
<body style="padding: 0px; overflow: hidden;">
	<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div id="searchbtn" style="display:none;">搜索</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	</div>
	
	<div id="pic_Div" style="z-index: 10000;display: none;width: 100%;height: 100%;position: absolute; !important;">
	<table
		style="background-color: transparent; z-index: 2001; position: absolute; width: 100%; height: 100% !important">
		<tr>
			<td valign="middle" align="center">
			<div onclick="pic_hide()" title="关闭图片">
				<img style="width: 100%;height: 100%;" id="pic_img" alt="自动关闭" />
			</div>
			</td>
		</tr>
	</table>
	<div
		style="position: fixed; filter: alpha(Opacity = 50); -moz-opacity: 0.5; opacity: 0.5; z-index: 2000; background-color: #000000; height: 100%; width: 100%; text-align: center; vertical-align: middle;">
	</div>
	</div>
</body>
<script type="text/javascript">
	function pic_show(url){
		$("#pic_img").attr("src",url);
		$("#pic_Div").show(100);
	}
	
	function pic_hide(){
		$("#pic_Div").hide(100);
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
