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
var modifyFlag=1;
var josnStr=[];

function chgFavoritesStatus(id, status) {
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
				url : "${pageContext.request.contextPath}/appMng/favorites/chgStatus.shtml?id=" + id + "&status=" + status,
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

function delFavorites(id) {
	$.ligerDialog.confirm("是否删除？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/appMng/favorites/del.shtml?id=" + id,
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

function editFavorites(id) {
	$.ligerDialog.open({
		height: 550,
		width: 700,
		title: "修改喜好",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/appMng/favorites/edit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function itemclickX() {
	var obj=document.getElementsByName("seqNo");
	if (modifyFlag==1){
		document.querySelector('div[toolbarid="modify"]').firstChild.innerText="完成编辑";
		for(var i=0;i<obj.length;i++){
			obj[i].disabled=false;
		};
		modifyFlag=2;
	}else if (modifyFlag==2){
		$.ligerDialog.confirm('是否保存', function (yes){
			if (yes){
				var str3=[];
				var seqData;
				for(var i=0;i<obj.length;i++){
					var str1=obj[i].id.replace(/seqNo/g,"");
					var str2=str1+","+obj[i].value;
					str3.push(str2);
				};
				seqData=str3.join("|");
				if (seqData!=null && seqData!=""){
					$.ajax({
						url : "${pageContext.request.contextPath}/appMng/seqEdit/Submit.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {seqData:seqData,type:2},
						timeout : 30000,
						success : function(data) {
							if ("0000" == data.returnCode) {
								location.reload();
								frameElement.dialog.close();
							}else{
								$.ligerDialog.error(data.returnMsg);
							}
							
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
				}
			}else{
				location.reload();
			}
			document.querySelector('div[toolbarid="modify"]').firstChild.innerText="编辑排序";
			for(var i=0;i<obj.length;i++){
				obj[i].disabled=true;
			};
			modifyFlag=1;
		});
		
	}
}
	
var listConfig={
     url:"/appMng/favorites/data.shtml",
    
     btnItems: [{ text: '添加喜爱', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/appMng/favorites/edit.shtml", seqId:"", width: 700, height: 550 },
                { line:true }, 
                { text: '编辑排序', icon: 'modify', id:'modify', click:itemclickX}
               ], 
     listGrid:{  columns: [{ display: '排序', width: 100, render: function (rowdata, rowindex){
   	  			var seqNo;
   	  			if (rowdata.seqNo==null){
   	  				seqNo="";
   	  			}else{
   	  				seqNo=rowdata.seqNo;
   	  			}
   	  			return "<input id='seqNo"+rowdata.id+ "' name='seqNo' class='seqClass' disabled='' value='"+seqNo+ "'>";
     			}}, 
     			{ display: '品类名称', width: 100, name: 'name' },
     			{ display: '关联品类', width: 200, name: 'secondProductTypeNameGroup' },
               { display: '图片', width: 120, render: function (rowdata, rowindex){
                   var h = "";
                      h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='80' height='80' onclick='pic_show(this.src)'>";
                   return h;
               }}, 
            { display: '添加时间', width: 150, render: function(rowdata, rowindex) {
            	if (rowdata.createDate!=null){
	            	var createDate=new Date(rowdata.createDate);
	            	return createDate.format("yyyy-MM-dd hh:mm:ss");
            	}
         	}},
               { display: "操作", name: "OPER", align: "center", width: 140, render: function(rowdata, rowindex) {
				var html = [];
				if (rowdata.status=='0'){
				    html.push("<a href=\"javascript:chgFavoritesStatus(" + rowdata.id + ",'1');\">上架</a>"); 
				    html.push("<a href=\"javascript:delFavorites(" + rowdata.id + ");\">删除</a>");
				    html.push("<a href=\"javascript:editFavorites(" + rowdata.id + ");\">修改</a>");
				}else{
					html.push("<a href=\"javascript:chgFavoritesStatus(" + rowdata.id + ",'0');\">下架</a>");
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
