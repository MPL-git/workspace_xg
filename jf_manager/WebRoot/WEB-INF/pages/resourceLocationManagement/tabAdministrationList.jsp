<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<script type="text/javascript">

var linkTypeList;
$(function ()  {
	var showType = "16,17,18,20,22,24,26,28,30";
	 $.ajax({
			url : "${pageContext.request.contextPath}/linkType/moduleMap/getLinkTypeList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {showType:showType},
			timeout : 30000,
			success : function(data) {
				linkTypeList = data.linkTypeList;
				console.log(linkTypeList)
		    },
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
  });

//编辑
function changeEdit(id) {
	$.ligerDialog.open({
		height: $(window).height() - 50,
		width: $(window).width() - 50,
		title : "编辑",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/resourceLocationManagement/changeEditTab.shtml?id="+id,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}


//关闭
function changeClose(id) {
    var title="关闭";
    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
    if (yes) {
	$.ajax({
		type: 'post',
		url: '${pageContext.request.contextPath}/resourceLocationManagement/changeClose.shtml',
		data: {id : id},
		dataType: 'json',
		success: function(data) {
			if(data.code != null && data.code == 200) {
				window.location.reload();
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
 
 
//启用
function changeEnable(id) {
    var title="启用";
    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
    if (yes) {
	$.ajax({
		type: 'post',
		url: '${pageContext.request.contextPath}/resourceLocationManagement/changeEnable.shtml',
		data: {id : id},
		dataType: 'json',
		success: function(data) {
			if(data.code != null && data.code == 200) {
				window.location.reload();
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
 
 
 

 //修改排序值
function updateArtNo(id) {
	$("#seqNo" + id).parent().find("span").remove();
	var seqNo = $("#seqNo" + id).val();
	/* var flag = seqNo.match(/^[1-9]\d*$/); */
	/* if(seqNo != '' && flag != null) { */
	 if(seqNo == 2 || seqNo == 3) {
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/resourceLocationManagement/updateTabSeqNo.shtml",
			 data : {id : id, seqNo : seqNo},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.code != 200){
					 commUtil.alertError(json.message);
				 }
				 else{
					 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
					 window.location.reload();
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	}else{
		$("#seqNo" + id).val("");
		$("#seqNo" + id).parent().append("<span style='color:red;'>请输入2或3</span>");
	}
}
 

var listConfig={
  url:"/resourceLocationManagement/tabAdministrationListData.shtml",
  listGrid:{ columns: [
					{display:'排序', name:'seqNo', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
							var html = [];
							var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
							if(seqNo==1||seqNo==4){
								html.push("<input type='text' disabled='disabled' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
							}else{
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
							}
						
							
							html.push("<br>");
                        	return html.join("");
                    }},
                    
		            {display: 'Tab名称', name: 'name', width: 80},
		            
					{display:'图标', name:'seqNo', align:'center', isSort:false, width:200, render:function(rowdata, rowindex) {
	                	var h = "";
	                	if(rowdata.pic!= null && rowdata.pic!= ""){
	                		 h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50'>";
	                	}
	                   return h;
	                }},
	                
	                { display: '类型',  name: 'linkType', width: 180, render: function(rowdata, rowindex) {
						var html = [];
						var index = rowdata.linkType;
						var linkTypeName=""
						for(var i =0 ; i < linkTypeList.length ;i++){
							if(linkTypeList[i].linkType == index){
								linkTypeName=linkTypeList[i].linkTypeName;
							}
						}
						 return linkTypeName; 
	                }},

	                { display: '状态',name: 'status', width: 100, render: function(rowdata, rowindex) {
						var html = [];
						if (rowdata.status=='0') {
							html.push("未启用");
						}else if(rowdata.status=='1'){
							html.push("启用");
						}	
					    return html.join("");
				 	}}, 
	                { display: '操作', width:180, render: function(rowdata, rowindex) {
						var html = [];
						if(rowdata.seqNo == 1 || rowdata.seqNo == 4){
							html.push("<a href=\"javascript:changeEdit(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;");					
						}else{
	                		
							if(rowdata.status=="0" ){
							    html.push("<a href=\"javascript:changeEnable(" + rowdata.id + ");\">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;");
							    html.push("<a href=\"javascript:changeEdit(" + rowdata.id + ");\">编辑&nbsp;&nbsp;</a>"); 																							                		
							}else if(rowdata.status=="1"){
							     html.push("<a href=\"javascript:changeClose(" + rowdata.id + ");\">关闭</a>&nbsp;&nbsp;&nbsp;&nbsp;");
								html.push("<a href=\"javascript:changeEdit(" + rowdata.id + ");\">编辑&nbsp;&nbsp;</a>"); 
							}
							
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
   
};







    </script>  
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>

	   <div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
