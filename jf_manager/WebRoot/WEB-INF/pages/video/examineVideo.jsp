<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css" />

 <script type="text/javascript">
 	var viewerPictures;
 	var viewerFlag = true;
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});

	 });
 
	 
	 //获取视频缩略图
	 function viewerPic(videoId, src) {
		var url = "${pageContext.request.contextPath}/video/videothumbnailsList.shtml";
		viewerFlag = true;
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {videoId : videoId},
			timeout : 30000,
			success : function(data) {
				if(data && data.length > 0){
					var ind = 0;
					for (var i=0;i<data.length;i++) {
						var videoThumbnails=data[i].videoThumbnails.split(",");
						for (var i = 0; i < videoThumbnails.length; i++) {
						    if(videoThumbnails[i] == src) {

							   ind = i;
						   }
							
						  $("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+videoThumbnails[i]+'" src="${pageContext.request.contextPath}/file_servelt'+videoThumbnails[i]+'" alt=""></li>');
						}
					}
					viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
						url: 'data-original',
						hide: function(){
							$("#viewer-pictures").hide();
						},
						viewed: function() {
							if(viewerFlag) {
								viewerPictures.view(ind);
								viewerFlag = false;
							}
						}
					});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	 }
	 
	 
	 //获取视频封面图
	 function videoCover(videoID){
			$("#viewer-pictures").empty();
			viewerPictures.destroy();
			$.ajax({
				url : "${pageContext.request.contextPath}/video/videoCoverList.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {videoID:videoID},
				timeout : 30000,
				success : function(data) {
					if(data&&data.length>0){
						for (var i=0;i<data.length;i++)
						{	if(data[i].videoCover.indexOf("http") >= 0){
							$("#viewer-pictures").append('<li><img data-original="'+data[i].videoCover+'" src="'+data[i].videoCover+'" alt=""></li>');
						    }else{
							$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].videoCover+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].videoCover+'" alt=""></li>');
						    }
						}
						viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
							$("#viewer-pictures").hide();
						}});
						$("#viewer-pictures").show();
						viewerPictures.show();
					}
					
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
			
		}
	 
	 
	 function viewProduct(id) {
			$.ligerDialog.open({
				height: $(window).height() - 40,
				width: 1200,
				title: "商品信息",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	 
	 var dialog;
	 function videoUrl(id) {
		 dialog=$.ligerDialog.open({
				height: 400,
				width: 500,
				title: "视频播放",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/video/videoUrl.shtml?videoid=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null,
				onDialogClose:"pauseVideo()"
			});
	 }
	 
	 
	 function pauseVideo(){//关闭视频播放
		   dialog.close();
	 }
	 
	 
	 function rejectExamineVieoStatus(id,auditStatus) {
			$.ligerDialog.open({
				height: 300,
				width: 600,
				title: "驳回原因",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/video/rejectExamineVieoStatus.shtml?videoid=" + id + "&auditStatus="+auditStatus,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	 
	 
	 //视频审核状态
	 function changeExamineVieoStatus(id,auditstatus) {
         $.ligerDialog.confirm('是否通过？', function(yes) {
             if(yes) {
                 $.ajax({
                     url : "${pageContext.request.contextPath}/video/changeExamineVieoStatus.shtml?id=" +id+ "&auditstatus="+auditstatus,
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
	 
	 
	 function viewProduct(id) {
			$.ligerDialog.open({
				height: $(window).height() - 40,
				width: 1200,
				title: "商品信息",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}

    //下载方法
    function downLoadVideoAuthorizedAccessories(videoId) {
        $.ligerDialog.confirm('是否下载该附件？', function(yes) {
            if(yes) {
                var fileName = $("#videoAuthorizedAccessories"+videoId).attr("fileName");
                var filePath = $("#videoAuthorizedAccessories"+videoId).attr("filePath");
                location.href="${pageContext.request.contextPath}/video/downLoadVideoAuthorizedAccessories.shtml?fileName="+fileName+"&filePath="+filePath;
            }
        });
    }

 	 var listConfig={ 
	      url:"/video/examineVideoList.shtml",
	      listGrid:{ columns: [
							{display:'提交时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
			                }},
			                {display:'商家名称/类目/商家序号',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								html.push("<a href='javascript:;' onclick='editMchtBaseInfo(" + rowdata.mchtId + ");'>"+rowdata.companyName+"</a></br>");
								html.push(rowdata.mchtProductTypeName+"<br>");
								html.push(rowdata.mchtCode);
								return html.join("");
			                }},
			                { display: '视频封面', name: '', width: 200,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.videoCover!=null&&(rowdata.videoCover.indexOf("http") >= 0)){
			                       h += "<img src='"+rowdata.videoCover+"' width='120' height='120' onclick='videoCover("+rowdata.id+")'>";
			                      }else{
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.videoCover+"' width='120' height='120' onclick='videoCover("+rowdata.id+")'>";
			                      }
			                    return h;

						    }},
			                {display:'视频图片',name:'', align:'center', isSort:false, width:410,render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.videoThumbnails) {
									var videoThumbnails = rowdata.videoThumbnails.split(",");
									for(var i=0;i<videoThumbnails.length;i++) {
										html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+videoThumbnails[i]+"' width='120' height='213' onclick='viewerPic("+rowdata.id+", \""+videoThumbnails[i]+"\")'>");
									}
								}
																																					
								return html.join("");
							}},
						   {display:'视频相关信息',name:'', align:'center', isSort:false, width:230,render:function(rowdata, rowindex){
								   var html=[];
								   html.push("<a href='javascript:;' onclick='videoUrl(" + rowdata.id + ");'>视频播放</a></br>");
								   html.push("<span style='color: red'>视频ID：</span>"+rowdata.id+"<br>");
								   html.push("<span style='color: red'>标题：</span>"+rowdata.title+"<br>");
								   html.push("<span style='color: red'>描述：</span>"+rowdata.description+"<br>");
                                   html.push("<span style='color: red'>视频来源：</span>" + rowdata.videoStatusDesc + "<br>");
                                   if(rowdata.videoAuthorizedAccessoriesList!=null && rowdata.videoAuthorizedAccessoriesList.length>0){
                                       html.push("<span style='color: red'>授权附件：</span>");
                                       for (i = 0;i<rowdata.videoAuthorizedAccessoriesList.length;i++){
                                           var videoAuthorizedAccessories = rowdata.videoAuthorizedAccessoriesList[i];
                                           if(i == 0){
                                               html.push(videoAuthorizedAccessories.fileName + "&nbsp;<a href='javascript:;' id='videoAuthorizedAccessories"+videoAuthorizedAccessories.id+"' fileName="+videoAuthorizedAccessories.fileName+" filePath="+videoAuthorizedAccessories.filePath+" onclick='downLoadVideoAuthorizedAccessories("+ videoAuthorizedAccessories.id + ");'>[下载附件]</a></br>");
                                           }else{
                                               html.push("<br>"+videoAuthorizedAccessories.fileName + "&nbsp;<a href='javascript:;' id='videoAuthorizedAccessories"+videoAuthorizedAccessories.id+"'  fileName="+videoAuthorizedAccessories.fileName+" filePath="+videoAuthorizedAccessories.filePath+" onclick='downLoadVideoAuthorizedAccessories("+ videoAuthorizedAccessories.id + ");'>[下载附件]</a></br>");
                                           }
                                       }
								   }
								   return html.join("");
						   }},
						   {display:'相关商品',name:'', align:'center', isSort:false, width:330,render:function(rowdata, rowindex){
							    var html=[];
			               		if(rowdata.videoProductCustoms!=null){
			               		for(var i=0;i<rowdata.videoProductCustoms.length;i++){
			               			var pic = rowdata.videoProductCustoms[i].pic;
			               			var productCode = rowdata.videoProductCustoms[i].productCode;
			               			var productName = rowdata.videoProductCustoms[i].productName;
			               			var productId = rowdata.videoProductCustoms[i].productId;
			               			var h = "";
			               		   if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
				                       h += "<div class='no-check' style='display:block; margin:  20px;'><img src='"+pic+"' width='60' height='60' onclick='viewerPic("+productId+")'>";
				                      }else{
				                       h += "<div class='no-check' style='display:block; margin:  20px;'><img src='${pageContext.request.contextPath}/file_servelt/"+pic+"' width='60' height='60' onclick='viewerPic("+productId+")'>";
				                     }
			               			html.push(h);
			               			html.push("<div><p><a href='javascript:;' onclick='viewProduct(" +productId+ ");'>"+productName+"</a></p><p>ID:"+productCode+"</p></div>");
									/* html.push("<div>"); */
			  
			               		}
			               	}
			               		return html.join("");
							   
						   }},
							{display:'审核状态',name:'', hide:${type==2}, align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								if (rowdata.auditStatus=='2') {
									html.push("<a href=\"javascript:changeExamineVieoStatus(" + rowdata.id + ",3);\">通过</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
								    html.push("<a href=\"javascript:rejectExamineVieoStatus(" + rowdata.id + ",4);\">驳回&nbsp;&nbsp;</a>");
								}else if (rowdata.auditStatus=='3') {
									html.push("通过");
								}else if (rowdata.auditStatus=='4') {
									html.push("驳回");
								}
							    return html.join("");
							}},
							{display:'审核状态',name:'', hide:${type==1}, align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								if (rowdata.auditStatus=='3') {
								    if($("#staffID").text()== '1' || ${fwStaffId=='true'}){
                                        html.push("<a href=\"javascript:changeExamineVieoStatus(" + rowdata.id + ",5);\">通过</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
                                        html.push("<a href=\"javascript:rejectExamineVieoStatus(" + rowdata.id + ",6);\">驳回&nbsp;&nbsp;</a>");
									}else {
                                        html.push("待审");
									}
								}else if (rowdata.auditStatus=='5') {
									html.push("通过");
								}else if (rowdata.auditStatus=='6') {
									html.push("驳回");
								}
							    return html.join("");
							}}
							
			         ], 			    
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 						
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
	<form id="dataForm" runat="server" >
		<div id="staffID" style="display: none">${staffID}</div>
		<input type="hidden" id="type" name="type" value="${type}">
		<div class="search-pannel">
			<div class="search-tr"  >
			 <div class="search-td">
				<div class="search-td-label" style="float:left;"  >提交时间：</div>
				<div class="l-panel-search-item" >
					 <input type="text" class="dateEditor" id="create_begin" name="create_begin" >
				 </div>
				 <div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			  </div>
			  <div class="search-td">
				   <div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="create_end" name="create_end" >
				   </div>
			  </div>
			  <div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
			   <div class="search-td">
			 	<div class="search-td-label" >主营类目：</div>
			 	<div class="search-td-condition" >
				<select id="productTypeId" name="productTypeId" class="querysel">
					<option value=''>请选择...</option>
					<c:forEach var="productType" items="${productTypes}">
					   <option value="${productType.id}">${productType.name}</option>
				   </c:forEach>	
				</select>
				</div>
		 	   </div>	 	   	 	  	 	   					 
			</div>
			
			<div class="search-tr"  >
			    <div class="search-td" style="width: 250px">
					<div class="search-td-label" >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode">
					</div>
				</div>
			    <div class="search-td" style="width: 250px">
					<div class="search-td-label" >视频ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="vedioId" name="vedioId" >
					</div>
				</div>
				<div class="search-td" style="width: 250px">
					<div class="search-td-label" >视频标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="title" name="title" >
					</div>
				</div>
				<c:if test="${type eq 1}">
					<div class="search-td" style="width: 250px">
						<div class="search-td-label" >审核状态：</div>
						<div class="search-td-condition" >
							<select id="auditStatus" name="auditStatus" class="querysel">
								<option value="">请选择...</option>
								<option value="2" selected="selected">待审</option>
								<option value="3">待复审</option>
								<option value="4">初审驳回</option>
							</select>
						</div>
					</div>
				</c:if>
				<c:if test="${type eq 2}">
					<div class="search-td" style="width: 250px">
						<div class="search-td-label" >审核状态：</div>
						<div class="search-td-condition" >
							<select id="auditStatus" name="auditStatus" class="querysel">
								<option value="">请选择...</option>
								<option value="3" selected="selected">待复审</option>
								<option value="5">通过</option>
								<option value="6">复审驳回</option>
							</select>
						</div>
					</div>
				</c:if>
		 	  <div class="search-td" style="width: 250px">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="companyName" name="companyName" >
					</div>
			  </div>
			  			
			<div class="search-td-search-btn" >
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
