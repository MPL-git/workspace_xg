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
	var modifyFlag = 1;
	var josnStr = [];
	var viewerPictures;
	var linkValueList;
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
	
	function saveSeqNo(id,_this){
		var seqNo = $(_this).val();
		if(!seqNo){
			return;
		}
		if(seqNo && !testNumber(seqNo)){
			return;		
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/wetaoPage/saveAdInfoSeqNo.shtml?id=" + id+"&seqNo="+seqNo,
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
					url : "${pageContext.request.contextPath}/wetaoPage/changeAdInfoStatus.shtml?id=" + id + "&status=" + status,
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
					url : "${pageContext.request.contextPath}/wetaoPage/deleteAdInfo.shtml?id=" + id,
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
			url: "${pageContext.request.contextPath}/wetaoPage/toEditWetaoPageAdInfo.shtml?id="+id,
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
							data : {seqData:seqData,type:1},
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
      url:"/wetaoPage/wetaoPageAdInfo/data.shtml",
      listGrid:{columns: [{ display: '排序', width: 80, render: function (rowdata, rowindex){
    	  			var seqNo;
    	  			if (!rowdata.seqNo){
    	  				seqNo="";
    	  			}else{
    	  				seqNo=rowdata.seqNo;
    	  			}
    	  			return "<input id='seqNo"+rowdata.id+ "' name='seqNo' class='seqClass' value='"+seqNo+ "' onblur='saveSeqNo("+rowdata.id+",this)'>";
      			}}, 
                { display: '图片', width: 120, render: function (rowdata, rowindex){
                    var h = "";
                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
                    return h;
                }}, 
                { display: '广告类型', width: 80, name: 'linkTypeDesc' },
	            { display: '链接ID/URL', width: 150, render: function(rowdata, rowindex) {
	            	if (rowdata.linkType=='11') {
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
					}else if(rowdata.linkType=='3'){
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
		<input type="hidden" name="wetaoPageId" value="${wetaoPageId}">
		<div class="search-pannel">
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
