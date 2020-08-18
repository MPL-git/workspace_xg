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
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
	 });
	 
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
		 
		 
	    //视频评论批量通过
		function auditStatus(id) {
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/video/auditStatus.shtml',
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
		
		

		//视频评论审核状态
		 function changeCommentAuditstatus(id,auditstatus) {
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
							url : "${pageContext.request.contextPath}/video/changeCommentAuditstatus.shtml?id=" +id+"&auditstatus="+auditstatus,
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
		
		
		//视频显示和隐藏状态
		 function changeCommentStatus(id,status) {
				var title;
				if (status=="0"){
					title="是否隐藏？";
				}
				if (status=="1"){
					title="是否显示？";
				}
				$.ligerDialog.confirm(title, function (yes) {
					if(yes){
						$.ajax({
							url : "${pageContext.request.contextPath}/video/changeCommentStatus.shtml?id=" +id+"&status="+status,
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
		
		
		
		
		//视频评论删除
		 function delCommentStatus(id,delstatus) {
				var title;
				if (delstatus=="1"){
					title="是否删除？";
				}
				$.ligerDialog.confirm(title, function (yes) {
					if(yes){
						$.ajax({
							url : "${pageContext.request.contextPath}/video/delCommentStatus.shtml?id=" +id+"&delstatus="+delstatus,
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
	      url:"/video/commentVideoList.shtml",
	      btnItems:[{text: '批量通过',icon: 'add',click: function() {
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
	             	auditStatus(str); 
	         	}
	      }}
		],
	      listGrid:{ columns: [
							{display:'评论时间', name:'', align:'center', isSort:false, width:150, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
	                        }},
			                {display:'用户ID',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
			                	var html=[];
	                        	html.push("<a href=\"javascript:viewDetail(" + rowdata.memberId+ ");\">"+rowdata.memberId+"</a>"); 
	                        	return html.join("");
			                }},
			               { display: '评论内容', name: 'content', width: 200},
						   {display:'视频相关信息',name:'', align:'center', isSort:false, width:230,render:function(rowdata, rowindex){
						      var html=[];
						      html.push("<a href='javascript:;' onclick='videoUrl(" + rowdata.videoId + ");'>视频播放</a></br>");
						      html.	push("<span style='color: red'>标题：</span>"+rowdata.videoTitle+"<br>");
						      html.	push("<span style='color: red'>描述：</span>"+rowdata.videoDescription);
						      return html.join("");
						   }},
							{display:'涉及商家',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='editMchtBaseInfo(" + rowdata.mchtId + ");'>"+rowdata.mchtName+"</a></br>");
								html.push(rowdata.mchtCode);
								return html.join("");
							}},
							{display:'审核状态',name:'', align:'center', isSort:false, width:120,render:function(rowdata, rowindex){
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
						    {display:'显示状态',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
						    	 var html = [];
					             if (rowdata.status=='1') {
									html.push("显示");
								 }else if (rowdata.status=='0') {
									 html.push("隐藏");
								}									
							    return html.join("");
						    }},
						    {display:'操作',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	var html = [];
								if (rowdata.auditStatus=='1') {
									html.push("<a href=\"javascript:changeCommentAuditstatus(" + rowdata.id + ",2);\">通过</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
								    html.push("<a href=\"javascript:changeCommentAuditstatus(" + rowdata.id + ",3);\">驳回&nbsp;&nbsp;</a>"); 
								}else if (rowdata.auditStatus=='2' || rowdata.auditStatus=='3') {
									    if (rowdata.status=='1') {
									    	html.push("<a href=\"javascript:changeCommentStatus(" + rowdata.id + ",0);\">隐藏</a><br>");
										    html.push("<a href=\"javascript:delCommentStatus(" + rowdata.id + ",1);\">删除</a>"); 
										}else if (rowdata.status=='0') {
											html.push("<a href=\"javascript:changeCommentStatus(" + rowdata.id + ",1);\">显示</a><br>");
										    html.push("<a href=\"javascript:delCommentStatus(" + rowdata.id + ",1);\">删除</a>"); 
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
			   <div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >视频标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="title" name="title" >
					</div>
			    </div>
				<div class="search-td">
					<div class="search-td-label" >用户ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
		        </div>
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
