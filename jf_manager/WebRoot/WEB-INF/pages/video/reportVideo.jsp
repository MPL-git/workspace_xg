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
 
	 
	 
	 //获取视频举报凭证
	 function reportVideoPics(id, src) {
		var url = "${pageContext.request.contextPath}/video/reportVideoPicsList.shtml";
		viewerFlag = true;
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {id : id},
			timeout : 30000,
			success : function(data) {
				if(data && data.length > 0){
					var ind = 0;
					for (var i=0;i<data.length;i++) {
						var pics=data[i].pics.split(",");
					   for (var i = 0; i < pics.length; i++) {
						 if(pics[i] == src) {
							ind = i;
					     }
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+pics[i]+'" src="${pageContext.request.contextPath}/file_servelt'+pics[i]+'" alt=""></li>');
							
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

	 
	 function viewDetail(id) {
			$.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 100,
				title: "会员资料",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/member/info/detail.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
	 
	 
	//视频举报批量审核状态	 
	 function videoTipOffAuditstatus(id) {
			$.ligerDialog.open({
				height: 200,
				width: 300,
				title: "批量审核",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/video/videoTipOffAuditstatus.shtml?videoid=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	 
	
	//视频举报审核状态
	 function reportVieoauditStatus(id,auditstatus) {
			var title;
			if (auditstatus=="2"){
				title="是否通过？";
			}
			if (auditstatus=="3"){
				title="是否驳回？";
			}
			$.ligerDialog.confirm(title, function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/video/reportVieoAuditStatus.shtml?id=" +id+"&auditstatus="+auditstatus,
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
	      url:"/video/reportVideoList.shtml",
	      btnItems:[{text: '批量审核',icon: 'add',click: function() {
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
	             	videoTipOffAuditstatus(str); 
	         	}
	      }}
		],
	      listGrid:{ columns: [
							{display:'举报时间', name:'', align:'center', isSort:false, width:150, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
	                        }},
	                        {display:'用户ID',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
	                        	var html=[];
	                        	html.push("<a href=\"javascript:viewDetail(" + rowdata.memberId+ ");\">"+rowdata.memberId+"</a>"); 
	                        	return html.join("");
	                        }},
							{display:'举报事项',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								if (rowdata.matter=='1') {
									html.push("低俗色情");
								}else if (rowdata.matter=='2') {
									html.push("非法政治");
								}else if (rowdata.matter=='3') {
									html.push("枪支毒品");
								}else if (rowdata.matter=='4') {
									html.push("影视版权");
								}else if (rowdata.matter=='5') {
									html.push("其他");
								}
								return html.join("");
							}},
						    {display:'举报说明',name:'matterRemark', align:'center', isSort:false, width:100},
			                {display:'举报凭证',name:'', align:'center', isSort:false, width:310,render:function(rowdata, rowindex) {
			                	var html = [];
			                	if(rowdata.pics) {
									var pics = rowdata.pics.split(",");
									for(var i=0;i<pics.length;i++) {
										html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+pics[i]+"' width='80' height='80' onclick='reportVideoPics("+rowdata.id+", \""+pics[i]+"\")'>");
									}
								}																													
			                	return html.join("");
							}},
						   {display:'视频相关信息',name:'', align:'center', isSort:false, width:230,render:function(rowdata, rowindex){
								   var html=[];
								   html.push("<a href='javascript:;' onclick='videoUrl(" + rowdata.videoId + ");'>视频播放</a></br>");
                                   html.push("<span style='color: red'>视频ID：</span>"+rowdata.videoId+"<br>");
								   html.push("<span style='color: red'>标题：</span>"+rowdata.videoTitle+"<br>");
								   html.push("<span style='color: red'>描述：</span>"+rowdata.videoDescription+"<br>");
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
						   {display:'涉及商家',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								html.push("<a href='javascript:;' onclick='editMchtBaseInfo(" + rowdata.mchtId + ");'>"+rowdata.mchtName+"</a></br>");
								html.push(rowdata.mchtCode);
								return html.join("");
			                }},
			                {display:'审核状态',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								if (rowdata.auditStatus=='1') {
									html.push("待审");
								}else if (rowdata.auditStatus=='2') {
									html.push("通过");
								}else if (rowdata.auditStatus=='3') {
									html.push("驳回");
								}									
							    return html.join("");
							}},
							{display:'上下架状态',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
					             if (rowdata.videoStatus=='0') {
									html.push("下架");
								 }else if (rowdata.videoStatus=='1') {
									 html.push("上架");
								}									
							    return html.join("");
							}},
						    {display:'操作',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	var html = [];
								if (rowdata.auditStatus=='1') {
									html.push("<a href=\"javascript:reportVieoauditStatus(" + rowdata.id + ",'2');\">通过</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
								    html.push("<a href=\"javascript:reportVieoauditStatus(" + rowdata.id + ",'3');\">驳回&nbsp;&nbsp;</a>"); 
								}else if (rowdata.auditStatus=='2') {
									html.push("通过");
								}else if (rowdata.auditStatus=='3') {
									html.push("驳回");
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
					<c:forEach var="sysStaffProductTypeList" items="${sysStaffProductTypeList}">
					   <option value="${sysStaffProductTypeList.productTypeId}">${sysStaffProductTypeList.staffName}</option>
				   </c:forEach>	
				</select>
				</div>
		 	   </div>
		 	   <div class="search-td">
					<div class="search-td-label" >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productId" name="productId" >
					</div>
		      </div>
		      <div class="search-td">
					<div class="search-td-label" >视频标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="title" name="title" >
					</div>
			  </div>	 	   	 	  	 	   					 
			</div>
			<div class="search-tr"  >
			   <div class="search-td">
			 	<div class="search-td-label" >审核状态：</div>
			 	<div class="search-td-condition" >
				<select id="auditStatus" name="auditStatus" class="querysel">
					<option value="">请选择...</option>
					<option value="1" selected="selected">待审</option>
					<option value="2">通过</option>	
					<option value="3">驳回</option>				
				</select>
				</div>
		 	  </div>
		 	  <div class="search-td">
			 	<div class="search-td-label" >上下架状态：</div>
			 	<div class="search-td-condition" >
				<select id="videoStatus" name="videoStatus" class="querysel">
					<option value="">请选择...</option>
					<option value="0">下架</option>
					<option value="1">上架</option>	
				</select>
				</div>
		 	  </div>
		 	  <div class="search-td">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
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
