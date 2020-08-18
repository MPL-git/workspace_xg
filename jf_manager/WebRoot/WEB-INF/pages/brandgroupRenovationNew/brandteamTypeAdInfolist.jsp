<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<style>
.seqClass{width:50px;height:23px;text-align:center; border:1px solid red;box-sizing: border-box;border-width: 1px; border-style: solid; border-color: rgba(121, 121, 121, 1);border-radius: 0px; }
</style>
<script type="text/javascript">
	var viewerPictures;
	var linkValueList="";
	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
		
		$(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					linkValueList = data.linkValueList;
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
			
			
			$(".dateEditor").ligerDateEditor({
				showTime : true,
				format : "yyyy-MM-dd",
				labelAlign : 'left',
				width : 135
			});
			viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
	    		$("#viewer-pictures").hide();
	    	}});
		
		});
	});
	
	function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
	}
	
	/* function saveSeqNo(id,_this){
		var seqNo = $(_this).val();
		if(!seqNo){
			return;
		}
		if(seqNo && !testNumber(seqNo)){
			return;		
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/brandteamTypeAdInfo/savebrandteamTypeAdInfoSeqNo.shtml?id=" + id+"&seqNo="+seqNo,
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
	} */
	
	//修改排序值
	function updateArtNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/brandteamTypeAdInfo/savebrandteamTypeAdInfoSeqNo.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
					 }
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
						 $("#searchbtn").click();
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		
	}
	
	function chgAdInfoStatus(id, status) {
		var title;
		if (status=='1'){
			title="上架";
		}else{
			title="下架";
		}
		$.ligerDialog.confirm("是否"+title+"？", function (yes) { 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/brandteamTypeAdInfo/changeAdInfoStatus.shtml?id=" + id + "&status=" + status,
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

	function delAdInfo(id) {
		$.ligerDialog.confirm("是否删除？", function (yes) { 
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/brandteamTypeAdInfo/deleteAdInfo.shtml?id=" + id,
					dataType : 'json',
					type : 'POST',
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

	function editAdInfo(id) {
		$.ligerDialog.open({
			height: $(window).height()-200,
			width: $(window).width()-600,
			title: "修改广告",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/brandteamType/toEditbrandteamTypeAdInfo.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	
	var listConfig={
      url:"/brandteamTypeAdInfo/data.shtml",
      listGrid:{columns: [{ display: '排序', width: 80, render: function (rowdata, rowindex){
    	  			/* var seqNo;
    	  			if (!rowdata.seqNo){
    	  				seqNo="";
    	  			}else{
    	  				seqNo=rowdata.seqNo;
    	  			}
    	  			return "<input id='seqNo"+rowdata.id+ "' name='seqNo' class='seqClass' value='"+seqNo+ "' onblur='saveSeqNo("+rowdata.id+",this)'>"; */
    	             var html=[];
			         var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
			         html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
			         return html.join("");
      			}}, 
                { display: '图片', width: 120, render: function (rowdata, rowindex){
                    var h = "";
                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
                    return h;
                }}, 
                { display: '广告类型', width: 80, name: 'linkTypeDesc' },
	            { display: '链接ID/URL', width: 150, render: function(rowdata, rowindex) {
	            	if (rowdata.linkType=='28') {
	            		if (rowdata.mallName) {
	            			return rowdata.mallName;
						}
					}else if(rowdata.linkType=='13'){
						if(rowdata.productTypeNames){
							return rowdata.productTypeNames;
						}
					}else if (rowdata.linkType=='11') {
	            		if (rowdata.productTypeName!=null && rowdata.productTypeName!='') {
	            			return rowdata.productTypeName;
						}
					}else if (rowdata.linkType=='10') {
						if (rowdata.mchtCode!=null && rowdata.mchtCode!='') {
							return rowdata.mchtCode;
						}
					}else if (rowdata.linkType=='9') {
						  if (rowdata.brandteamtypeName!=null && rowdata.sbrandteamtypeName!='') {
							  return rowdata.brandteamtypeName;
						}
					}else if (rowdata.linkType=='6') {
						   for (var i= 0; i < linkValueList.length; i++) {
							    if (linkValueList[i].linkValue==rowdata.linkValue) {
							    	return linkValueList[i].linkValueName;
								}
						  	}
					}else if(rowdata.linkType=='3') {
	            	  return rowdata.productCode;
					}else{
	            	  return rowdata.linkValue;
					}
	         	}},
	            { display: '上架时间', width: 140, render: function(rowdata, rowindex) {
	            	if (rowdata.upDate!=null){
		            	var upDate=new Date(rowdata.upDate);
		            	return upDate.format("yyyy-MM-dd hh:mm:ss");	
	            	}
	         	}},
	            { display: '下架时间', width: 140, render: function(rowdata, rowindex) {
	            	if (rowdata.downDate){
		            	var downDate=new Date(rowdata.downDate);
		            	return downDate.format("yyyy-MM-dd hh:mm:ss");
	            	}
	         	}},
                { display: "操作", name: "OPER", align: "center", width: 140, render: function(rowdata, rowindex) {
					var html = [];
					if (rowdata.status=='0'){
					    html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'1');\">上架</a>"); 
					    html.push("<a href=\"javascript:delAdInfo(" + rowdata.id + ");\">删除</a>");
					    html.push("<a href=\"javascript:editAdInfo(" + rowdata.id + ");\">修改</a>");
					}else{
						html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'0');\">下架</a>");
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
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<input type="hidden" name="brandteamTypeid" value="${brandteamTypeid}">
		<div class="search-pannel">
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
