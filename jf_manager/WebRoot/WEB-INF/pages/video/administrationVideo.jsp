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
	 
	   
	   
	 function videoWeicht(id) {
			$.ligerDialog.open({
				height: 300,
				width: 700,
				title: "权重修改",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/video/videoWeicht.shtml?videoid=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	 
	
	 
	 //视频上下架状态
	 function videoStatus(id,status) {
			var title;
			if (status=="0"){
				title="是否下架？";
			}
			if (status=="1"){
				title="是否下上架？";
			}
			$.ligerDialog.confirm(title, function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/video/videoStatus.shtml?id=" +id+"&status="+status,
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
	 
	 
	    //清空视频排序值
		function delVideoSeqNo(id) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/video/delVideoSeqNo.shtml",
					 data : {id : id},
					 dataType : 'json',
					 success : function(data){
						 if(data.code != null && data.code == 200){	 
							 commUtil.alertError(json.message);
						 }else{
							 $("#searchbtn").click();
						 }
						
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
		}
	 
	    
		 //修改排序值
		function updateArtNo(id) {
			$("#seqNo" + id).parent().find("span").remove();
			var seqNo = $("#seqNo" + id).val();
			var flag = seqNo.match(/^[1-9]\d*$/);
			if(seqNo != '' && flag != null) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/video/updatevideoSeqNo.shtml",
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
			}else{
				$("#seqNo" + id).val("");
				$("#seqNo" + id).parent().append("<br>"+"<span style='color:red;'>请输入正整数</span>");
			}
		}

		//是否推荐到精选
		function videoisRecommend(ids) {
		    var title="推荐到精选";
		    $.ligerDialog.confirm("是否"+title+"？", function (yes){
		    if (yes) {
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/video/videoisRecommend.shtml',
				data: {ids : ids},
				dataType: 'json',
				success: function(data) {
					if(data.code != null && data.code == 200) {
						$("#searchbtn").click();
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

	//视频管理批量推荐
	function videoIsRecommends(ids) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/video/videoisRecommend.shtml',
			data: {ids : ids},
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success: function(data) {
				if(data.code != null && data.code == 200) {
					commUtil.alertSuccess("推荐成功");
					setTimeout(function(){
						$("#searchbtn").click();
					},1000);
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}

	//是否取消推荐到精选
	function videoisNotRecommend(ids) {
		var title="取消推荐到精选";
		$.ligerDialog.confirm("是否"+title+"？", function (yes){
			if (yes) {
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/video/videoIsNotRecommend.shtml',
					data: {ids : ids},
					dataType: 'json',
					success: function(data) {
						if(data.code != null && data.code == 200) {
							$("#searchbtn").click();
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

	//视频管理批量取消推荐
	function videoIsNotRecommend(ids) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/video/videoIsNotRecommend.shtml',
			data: {ids : ids},
			dataType: 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success: function(data) {
				if(data.code != null && data.code == 200) {
					commUtil.alertSuccess("取消推荐成功");
					setTimeout(function(){
						$("#searchbtn").click();
					},1000);
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}

	    //视频管理批量下架
		function videoStsus(id) {
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/video/videoStsus.shtml',
				data: {id : id},
				dataType: 'json',
				cache : false,
				async : false,
				timeout : 30000,
				success: function(data) {
					if(data.code != null && data.code == 200) {
						setTimeout(function(){
							$("#searchbtn").click();
						},1000);
					}else {
						commUtil.alertError(data.msg);
					}
				},
				error: function(e) {
				  commUtil.alertError("系统异常请稍后再试");
				}
			});
		  }

		 //查看评论
		 function videoComment(id){
			$.ligerDialog.open({
				height: $(window).height(),
				width: $(window).width(),
				title: "视频评论管理",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/video/videoComment.shtml?videoId=" + id,
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
	      url:"/video/administrationVideoList.shtml",
	      btnItems:[{text: '批量下架',icon: 'add',click: function() {
				var data = listModel.gridManager.getSelectedRows();
	         	if (data.length == 0) {
	         		commUtil.alertError('请选择行！');
	         	}else {
	            		var str = "";                         
	             	$(data).each(function() {    
	           	  		if(str=='') {
	           		  		str = this.id;
	           	  		}else {
	           		  		str += ","+ this.id;
	           	  		}
	             	});
	             	videoStsus(str); 
	         	}
	      }},
			  {text: '批量推荐',icon: 'add',click: function() {
					  var data = listModel.gridManager.getSelectedRows();
					  if (data.length == 0) {
						  commUtil.alertError('请选择推荐的视频');
					  }else {
						  var str = "";
						  $(data).each(function() {
							  if(str=='') {
								  str = this.id;
							  }else {
								  str += ","+ this.id;
							  }
						  });
						  videoIsRecommends(str);
					  }
				  }},
			  {text: '批量取消推荐',icon: 'add',click: function() {
					  var data = listModel.gridManager.getSelectedRows();
					  if (data.length == 0) {
						  commUtil.alertError('请选择要取消推荐的视频');
					  }else {
						  var str = "";
						  $(data).each(function() {
							  if(str=='') {
								  str = this.id;
							  }else {
								  str += ","+ this.id;
							  }
						  });
						  videoIsNotRecommend(str);
					  }
				  }}
		],
	      listGrid:{ columns: [
							{display:'排序', name:'seqNo', align:'center', isSort:false, width:130, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "'>");
								if (seqNo!=null && seqNo!='') {
									html.push("<br>");
									html.push("<a href=\"javascript:delVideoSeqNo(" + rowdata.id + ");\">清空</a>");
								}
								if (rowdata.isrecommend=='1') {
									html.push("<br>");
									html.push("<a href=\"javascript:videoisNotRecommend(" + rowdata.id + ");\">取消推荐</a>");
								}else {
									html.push("<br>");
									html.push("<a href=\"javascript:videoisRecommend(" + rowdata.id + ");\">推荐到精选</a>");
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
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.videoCover+"' width='80' height='80' onclick='videoCover("+rowdata.id+")'>";
			                      }
			                    return h;

						    }},
			                {display:'视频图片',name:'', align:'center', isSort:false, width:310,render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.videoThumbnails) {
									var videoThumbnails = rowdata.videoThumbnails.split(",");
									for(var i=0;i<videoThumbnails.length;i++) {
										html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+videoThumbnails[i]+"' width='50' height='89' onclick='viewerPic("+rowdata.id+", \""+videoThumbnails[i]+"\")'>");
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
							{display:'上下架状态',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
					             if (rowdata.status=='0') {
									html.push("下架");
								 }else if (rowdata.status=='1') {
									 html.push("上架");
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
				                       h += "<div cclass='no-check' style='display:block; margin:  20px;'><img src='"+pic+"' width='60' height='60' onclick='viewerPic("+productId+")'>";
				                      }else{
				                       h += "<div class='no-check' style='display:block; margin:  20px;'><img src='${pageContext.request.contextPath}/file_servelt/"+pic+"' width='60' height='60' onclick='viewerPic("+productId+")'>";
				                     }
			               			html.push(h);
			               			html.push("<div><p><a href='javascript:;' onclick='viewProduct(" +productId+ ");'>"+productName+"</a></p><p>ID:"+productCode+"</p></div>");
			  
			               		}
			               	}
			               		return html.join("");
								   
						    }},
						    {display:'点赞数',name:'likeCount', align:'center', isSort:false, width:100},
						    {display:'收藏数',name:'collectCount', align:'center', isSort:false, width:100},
						    {display:'评论数',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
						    	var html=[];
						    	html.push(rowdata.commentCount+"<br>");
						    	html.push("<a href='javascript:;' onclick='videoComment(" + rowdata.id + ");'>查看评论</a>");
						    	return html.join("");
						    }},
						    {display:'播放数',name:'playCount', align:'center', isSort:false, width:100},
						    {display:'操作',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	var html=[];
							    if (rowdata.status=='1') {
							    	html.push("<a href='javascript:;' onclick='videoStatus(" + rowdata.id + ",0);'>下架</a></br>");
							    	if (${sessionScope.USER_SESSION.staffID} == 1) {
							    	  html.push("<a href='javascript:;' onclick='videoWeicht(" + rowdata.id + ");'>权重修改</a>");	
									}
								}else if (rowdata.status=='0') {
									html.push("<a href='javascript:;' onclick='videoStatus(" + rowdata.id + ",1);'>上架</a></br>");
									if (${sessionScope.USER_SESSION.staffID} == 1) {
								    html.push("<a href='javascript:;' onclick='videoWeicht(" + rowdata.id + ");'>权重修改</a>");	
								   }
								}
							    return html.join("");  
						    }},
							
			         ], 			    
	                 showCheckbox :true,  //不设置默认为 true
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
		<div class="search-pannel">
			<div class="search-tr"  >
			  <div class="search-td" style="width:250px;">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
			   <div class="search-td" style="width:250px;">
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
		 	   <div class="search-td" style="width:250px;">
					<div class="search-td-label" >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" >
					</div>
		      </div>
		      <div class="search-td" style="width:250px;">
					<div class="search-td-label" >视频标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="title" name="title" >
					</div>
			  </div>
			  <div class="search-td" style="width:250px;">
					<div class="search-td-label" >是否推荐：</div>
					<div class="search-td-condition" >
						<select id="isRecommend" name="isRecommend" class="querysel">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
			  </div>
			</div>
			<div class="search-tr"  >
			   <div class="search-td" style="width:250px;">
			 	<div class="search-td-label" >上下架状态：</div>
			 	<div class="search-td-condition" >
				<select id="status" name="status" class="querysel">
					<option value="">请选择...</option>
					<option value="0">下架</option>
					<option value="1" selected="selected">上架</option>	
				</select>
				</div>
		 	  </div>
		 	  <div class="search-td" style="width:250px;">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="companyName" name="companyName" >
					</div>
			  </div>
			  <div class="search-td" style="width:250px;">
					<div class="search-td-label" >视频ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="videoId" name="videoId" >
					</div>
			  </div>
			  <div class="search-td" style="width:250px;">
					<div class="search-td-label" >视频来源：</div>
					<div class="search-td-condition" >
						<select id="videoSource" name="videoSource" class="querysel">
							<option value="">请选择</option>
							<option value="0">品牌方</option>
							<option value="1">自己拍摄</option>
						</select>
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
