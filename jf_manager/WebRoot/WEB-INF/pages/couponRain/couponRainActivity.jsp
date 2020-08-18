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
	$(function() {

		$(".dateEditor").ligerDateEditor({
			showTime : false,
			width: 158,
			format: "yyyy-MM-dd"
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
	
 
 function edItcouponRain(id) {
		$.ligerDialog.open({
			height: $(window).height()-200,
			width: $(window).width()-600,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/couponRain/addcouponRain.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
 
 
 function changeCouponRainStatus(id,status) {
		var title;
		if (status=="0"){
			title="状态是否改为上架？";
		}else if(status=="1"){
			title="状态是否改为下架？";
		}
		$.ligerDialog.confirm(title, function (yes) {
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/couponRain/changeCouponRainStatus.shtml?id=" + id,
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
 
 
 
 	 var listConfig={ 
	      url:"/couponRain/couponRainActivitydata.shtml",
	      btnItems:[{ text: '新增', icon: 'add', dtype:'win',  click: itemclick, url: "/couponRain/addcouponRain.shtml", seqId:"", width: 700, height: 530},],
	      listGrid:{ columns: [
					
						    {display:'红包雨名称',name:'title', align:'center', isSort:false, width:200},
						    { display: '日期', width: 150, render: function(rowdata, rowindex) {
				            	var starttime=rowdata.startTime;
				            	var startdate=new Date(starttime);
				            	return startdate.format("yyyy-MM-dd");	
				         	}},
                            { display: '开始时间', width: 150, render: function(rowdata, rowindex) {
                            	var starttime=rowdata.startTime;
				            	var startdate1=new Date(starttime);
				            	return startdate1.format("hh:mm");	
				         	}},
                            { display: '结束时间', width: 150, render: function(rowdata, rowindex) {
                            	var endTime=rowdata.endTime;
				            	var enddate=new Date(endTime);
				            	return enddate.format("hh:mm");	
				         	}},
				         	/* { display: '弹窗图片', width: 120, render: function (rowdata, rowindex){
			                    var h = "";
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
			                    return h;
			                }},  */
			                {display:'持续时间',name:'gameSeconds', align:'center', isSort:false, width:150},
			                {display:'炸弹比例',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){						    
						        return Number(rowdata.bombPercent*100).toFixed(2)+"%";
								
						    }},
						    {display:'参与人数',name:'memberCount', align:'center', isSort:false, width:150},
						    
				         	{ display: '创建时间', width: 150, render: function(rowdata, rowindex) {
				         		if (rowdata.createDate!=null){
					            	var createDate=new Date(rowdata.createDate);
					            	return createDate.format("yyyy-MM-dd hh:mm");	
				            	}
				         	}},
                            { display: '状态', width: 150, render: function(rowdata, rowindex) {
                            	if(rowdata.status == 0){
            	            		return "下架";
            	            	}else{
            	            		return "上架";
            	            	}
				         	}},
						    {display:'操作',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	var html=[];
							    html.push("<a href='javascript:;' onclick='edItcouponRain(" + rowdata.id + ");'>编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;");	
							    if (rowdata.status=='0') {
							    	html.push("<a href=\"javascript:changeCouponRainStatus(" + rowdata.id + ","+rowdata.status+");\">上架</a>&nbsp;&nbsp;");
								}else if (rowdata.status=='1') {
									html.push("<a href=\"javascript:changeCouponRainStatus(" + rowdata.id + ","+rowdata.status+");\">下架</a>&nbsp;&nbsp;");
								}
							    return html.join("");  
						    }},
							
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
		<div class="search-pannel">
			<div class="search-tr"  >
		      <div class="search-td">
					<div class="search-td-label" >红包雨名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="title" name="title" >
					</div>
			 </div>
			 <div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="starDate" name="starDate" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="enDate" name="endateDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			<div class="search-td">
			 	<div class="search-td-label" >状态：</div>
			 	<div class="search-td-condition" >
				<select id="status" name="status" class="querysel">
					<option value="">请选择...</option>
					<option value="0">下架</option>
					<option value="1">上架</option>	
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
