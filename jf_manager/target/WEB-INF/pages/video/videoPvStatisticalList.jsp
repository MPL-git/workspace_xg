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
	 var statisticsType = '1';
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

		 $(".l-dialog-close").live("click", function(){
			 $("#searchbtn").click();
		 });

		 $("#statisticsType").change(function(){
			 if($(this).val() == '1'){
				 $("#nowPayDate").show();
				 $("#payDate").hide();
			 }else{
				 $("#nowPayDate").hide();
				 $("#payDate").show();
			 }
			 statisticsType = $(this).val();
		 });

		 $("#searchbtn1").bind("click",function(){
			 if(statisticsType == '2'){
				 var date = new Date().format("yyyy-MM-dd");
				 if($("#payDateBegin").val() == "" || $("#payDateEnd").val() == "" ){
					 $.ligerDialog.error('开始日期或结束日期不可为空');
					 return;
				 }else if(new Date($("#payDateBegin").val()).getTime() > new Date($("#payDateEnd").val()).getTime() ){
					 $.ligerDialog.error('开始日期不可大于结束日期');
					 return;
				 }else if(new Date($("#payDateBegin").val()).getTime() >= new Date(date).getTime() || new Date($("#payDateEnd").val()).getTime() >= new Date(date).getTime() ){
					 $.ligerDialog.error('开始日期或结束日期必须小于今天');
					 return;
				 }
			 }
			 $("#searchbtn").click();
		 });

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
		  url:"/video/videoPvStatisticalList.shtml",
		  btnItems:[],
		  listGrid:{ columns: [
						{display:'排序', name:'seqNo', align:'center', isSort:false, width:130, render:function(rowdata, rowindex) {
							var html = [];
							var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
							html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "'>");
							if (seqNo != null && seqNo != '') {
								html.push("<br>");
								html.push("<a href=\"javascript:delVideoSeqNo(" + rowdata.id + ");\">清空</a>");
							}
							if (rowdata.isrecommend == '1') {
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
						{display:'访客数(会员)',name:'totalVisitorCount', align:'center', isSort:false, width:120},
						{display:'访客数(非会员)',name:'totalVisitorCountTourist', align:'center', isSort:false, width:120},
						{display:'浏览量(会员)',name:'totalPvCount', align:'center', isSort:false, width:120},
						{display:'浏览量(非会员)',name:'totalPvCountTourist', align:'center', isSort:false, width:120},
						{display:'加购件数',name:'shoppingCartCount', align:'center', isSort:false, width:120},
						{display:'加购转化',name:'addProductRate', align:'center', isSort:false, width:120},
						{display:'提交订单件数',name:'subProductCount', align:'center', isSort:false, width:120},
						{display:'下单转化',name:'submitOrderRate', align:'center', isSort:false, width:120},
						{display:'付款件数',name:'payProductCount', align:'center', isSort:false, width:120},
						{display:'付款转化',name:'paymentRate', align:'center', isSort:false, width:120}
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
	<%--<div id="toptoolbar"></div>--%>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
			  	<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
			   	<div class="search-td">
			 		<div class="search-td-label" >一级类目：</div>
					<div class="search-td-condition" >
						<select id="productTypeId" name="productTypeId" class="querysel" style="width: 135px;">
							<option value=''>请选择...</option>
							<c:forEach var="productType" items="${productTypes}">
							   <option value="${productType.id}">${productType.name}</option>
						   	</c:forEach>
						</select>
					</div>
		 	   	</div>
				<div class="search-td">
					<div class="search-td-label" >视频标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="videoTitle" name="videoTitle" >
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >统计类型：</div>
					<div class="search-td-condition" >
						<select id="statisticsType" name="statisticsType" style="width: 135px;">
							<option value="1">实时数据</option>
							<option value="2">历史记录</option>
						</select>
					</div>
				</div>
				<div class="search-td" id="nowPayDate" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="nowPayDateBegin" name="nowPayDateBegin" value="${nowPayDate}" readonly="readonly" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="nowPayDateEnd" name="nowPayDateEnd" value="${nowPayDate}" readonly="readonly" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" id="payDate" style="width: 40%;display: none;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="payDateBegin" name="payDateBegin" class="dateEditor" value="${payDate}" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="payDateEnd" name="payDateEnd" class="dateEditor" value="${payDate}" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn">
					<div class="l-button" id="searchbtn1">搜索</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn" style="display:none;">搜索</div>
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
