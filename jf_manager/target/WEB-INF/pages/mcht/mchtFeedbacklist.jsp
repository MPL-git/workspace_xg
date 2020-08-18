<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript"> 
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});
function viewPic(id){
	
	var url="${pageContext.request.contextPath}/mcht/mchtfeedbackgetPics.shtml";
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id},
		timeout : 30000,
		success : function(data) {
			if(data&&data.length>0){
				for (var i=0;i<data.length;i++)
				{
					$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
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

//保存领取人
function saveMchtFeedback(id, input) {
	var title="领取";
  $.ligerDialog.confirm("是否要"+title+"？", function (yes){
  if (yes) {
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/savemchtfeedbackdata.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"id" : id},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				var $div = $(input).parent();
				$div.empty();
				$div.text(data.staffName);
				$("#maingrid").ligerGetGridManager().reload();
				/* $("#searchbtn").click(); */
			} else {
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


function updateprocesby(id){
	  $.ligerDialog.open({
			height: 260,
			width: 400,
			title: "批量领取",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/batchmchtfeedback.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
}

function MchtFeedbackProcesBy(id) {
	$.ligerDialog.open({
	height: $(window).height() - 400,
	width: $(window).width() - 1100,
	title: "处理",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/mcht/mchtfeedbackedit.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

var listConfig={
  
    url:"/mcht/mchtfeedbackd/data.shtml",
    btnItems:[{text: '批量领取', icon: 'modify', click: function() {
		  var data = listModel.gridManager.getSelectedRows();
               if (data.length == 0)
             	  $.ligerDialog.alert('请选择行');
               else
               {
                  var str = "";
                   $(data).each(function ()
                   {    
                 	  if(str==''){
                 		  str = this.id ;
                 	  }else{
                 		  str += ","+ this.id ;
                 	  }
                   });                                                      
                     updateprocesby(str);
                   	
                   }; 
               
               return;
		  }},
	],
	
    listGrid:{ columns: [{ display: '商家序号', name: 'mchtinfocode', width: 100}, 
	                { display: '店铺名称', name:'mchtshopname',width: 100},      
	                { display: '反馈类型', width: 150, render: function (rowdata, rowindex) {
		              if(rowdata.type=="1"){
		                return "功能异常";
		                 }else if (rowdata.type=="2") {
					    return "其它问题";
					}                 
		               	}},
	                { display: '反馈内容', name: 'content', width: 300,render: function (rowdata, rowindex){
	                	 if(rowdata.content!=null){
	 		                return rowdata.content;
	 		                 }else{
	 					    return "";
	 					} 
	                }},
	                { display: "反馈图片", name: "OPER", align: "center", width: 100, render: function(rowdata, rowindex) {
						var html = [];
						if (rowdata.picQuantity!=0){
							html.push("<a href=\"javascript:viewPic(" + rowdata.id + ");\">查看</a>"); 
						}
					    return html.join("");
					}},
	               	{ display: '反馈时间', width: 150, render: function (rowdata, rowindex) {
	                   	var createDate=new Date(rowdata.createDate);
	                   	return createDate.format("yyyy-MM-dd hh:mm");
	               	}},
	            	{ display: '操作系统', name: 'system', width: 100},
	               	{ display: '浏览器/浏览器版本/版本号', name: '', width: 200,render:function(rowdata, rowindex){
	               		if (rowdata.browser!=null && rowdata.browser!="") {
							return rowdata.browser;
						}else{
							return "";
						}
	               		    
	               	}},            
	            	{ display: '领取人', width: 150, render: function (rowdata, rowindex) {
 	                  if(rowdata.staffName){
 	                	return rowdata.staffName;
 	                  }else{
	                	  return "<input type='button' style='cursor: pointer;width: 60px;height: 35px;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius:6px;border:1px solid #ddd;background-color:#269abc;border-color: #269abc;' onclick='saveMchtFeedback("
							+ rowdata.id
							+ ",this)' value='领取'></input>";
	                	  
	                  }	
	               	}},
	               	{ display: '领取时间', width: 150, render: function (rowdata, rowindex) {
	               		if(rowdata.processDate!=null && rowdata.processDate!=""){
	                   	var processDate=new Date(rowdata.processDate);
	                   	return processDate.format("yyyy-MM-dd hh:mm");               			
	               		}else{
	               		 return "";	
	               		}
	               	}},
	               	{ display: '处理状态',width: 150, render: function (rowdata, rowindex) {
	                 if(rowdata.dealStatus=="0"){
	                	  return "未领取";
	                 }else if (rowdata.dealStatus=="1") {
						  return "待处理";
					}else if (rowdata.dealStatus=="2") {
						  return "已处理";
					}else if (rowdata.dealStatus=="3") {
						  return "不需要处理";
					}             
	               	}},
	            	{ display: '处理意见', name: 'dealOpinion', width: 100},
	            	{ display: '相关订单号', name: 'relatedOrder', width: 100},
	            	{ display: '处理时间', width: 100,render: function (rowdata, rowindex){
	            		if(rowdata.dealDate !=null && rowdata.dealDate!=""){
	            		var dealDate=new Date(rowdata.dealDate);
	                   	return dealDate.format("yyyy-MM-dd hh:mm");		            			            			
	            		}else{
	            		 return "";	
	            		}
	            	}},
	            	{ display: '操作', name: 'OPER', width: 100,render: function (rowdata, rowindex){	
	            		if(rowdata.procesBy!=null && rowdata.procesBy!=""){
	            			if ((rowdata.procesBy==${sessionScope.USER_SESSION.staffID} || rowdata.assistantContact=='1') && rowdata.dealStatus=='1') {
	            			return "<a href=\"javascript:MchtFeedbackProcesBy(" + rowdata.id + ");\">处理</a>";								
							}
	            		}else if(rowdata.procesby==null && rowdata.procesby==""){
	            			return "";
	            		}            				            		             		 		           					

	            	}},
                ],
               showCheckbox : true,  //不设置默认为 true
               showRownumber:true //不设置默认为 true
    } , 							
   container:{
      toolBarName:"toptoolbar",
      searchBtnName:"searchbtn",
      fromName:"dataForm",
      listGridName:"maingrid"
    }        
};

//选择批量导出
function execel(){
	 var data = listModel.gridManager.getSelectedRows();	 
	 if (data.length == 0) {
		 excel();
	} else {
		 var str = "";
		  $(data).each(function (){
			  if(str==''){
	       		  str = this.id;
	       	  }else{
	       		  str += ","+ this.id;
	       	  } 
		  });
		    $("#dataForm").attr("action","${pageContext.request.contextPath}/mcht/mchtfeedbackCheckout.shtml?id="+str);
		    $("#dataForm").submit();
		
	}
     return;
}

 function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/mcht/mcht/mchtfeedbackCheckouts.shtml");
	$("#dataForm").submit();
} 

</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<c:if test="${roleType!=1}">
	<div id="toptoolbar"></div>
	</c:if>
	<form id="dataForm" runat="server" method="post">
		<div class="search-pannel">		
		<div class="search-tr"  > 		
			 <div class="search-td">
			 <div class="search-td-label" >反馈类型：</div>
			 <div class="search-td-condition" >
				<select id="type" name="type">
					<option value="">请选择</option>
					<option value="1">功能异常 </option>
					<option value="2">其它问题</option>
				</select>
		 	 </div>
			 </div>		 
			 <div class="search-td">
			 <div class="search-td-label" >浏览器/版本/版本号：</div>
			 <div class="search-td-condition" >
				 <input type="text" id = "browser" name="browser" >
			 </div>
			 </div>			 
			 <div class="search-td">
			 <div class="search-td-label" >处理状态:</div>
			 <div class="search-td-condition" >
				<select id="dealstatus" name="dealstatus">
					<option value="">请选择</option>
				    <option value="0">未领取</option>
				    <option value="1">待处理</option>
				    <option value="2">已处理</option>
				    <option value="3">不需要处理</option>
				</select>
		 	 </div>
			 </div>
			 <div class="search-td">
					<div class="search-td-label" style="float:left;">处理人：</div>
					<div class="l-panel-search-item">
						<select id="procesBy" name="procesBy" style="width: 150px;">
							<option value="">请选择</option>
							<!-- <option value="unclaimed">未领取</option> -->
							<c:forEach var="mchtfeedback" items="${MchtfeedbackList}">
								<option value="${mchtfeedback.proces_by}">${mchtfeedback.proces_by_name}</option>
							</c:forEach>
						</select>
					</div>
				</div>					 			
		</div>
		
		<div class="search-tr"  > 
		  <div class="search-td">
			<div class="search-td-label" style="float:left;">领取时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="process_date_begin" name="process_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#process_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;至：</div>
			</div>
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="process_date_end" name="process_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#process_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >商家序号：</div>
			 	<div class="search-td-condition" >
					<input type="text" id="mchtinfocode" name="mchtinfocode" >
			 	</div>
			</div>				
		</div>	
		<div class="search-tr"  > 		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">反馈时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="date_begin" name="date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;至：</div>
			</div>		
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" />
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn"  class="l-button">搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" onclick="return execel();">
					</div>	
			</div>
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>