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
	
	var url="${pageContext.request.contextPath}/appMng/feedback/getPics.shtml";
	
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
function saveMemberFeedback(id, input) {
	var title="领取";
  $.ligerDialog.confirm("是否要"+title+"？", function (yes){
  if (yes) {
	$.ajax({
		url : "${pageContext.request.contextPath}/appMng/saveMemberFeedback.shtml",
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
				/* commUtil.alertSuccess("领取成功"); */
				$("#searchbtn").click();
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
			url: "${pageContext.request.contextPath}/appMng/feedback/procesbydata.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
}

//批量变更领取人
function editUpdateBy(id){
	  $.ligerDialog.open({
		height: 300,
		width: 500,
		title: "批量变更",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/appMng/version/editUpdateByManager.shtml?ids="+id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function MemberFeedbackProcesBy(id) {
	$.ligerDialog.open({
	height: $(window).height() - 400,
	width: $(window).width() - 1100,
	title: "处理",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/appMng/feedback/edit.shtml?id=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

//创建工单
function addWork() {
	 $.ligerDialog.open({
			height: 600,
			width: 950,
			title: "创建工单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/addfeedbackwork.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	  });
 }

var listConfig={
    url:"/appMng/feedback/data.shtml",
    btnItems:[{text: '批量领取', icon: 'modify', click: function() {
		  var data = listModel.gridManager.getSelectedRows();
               if (data.length == 0){
             	  $.ligerDialog.alert('请选择行');
               }else{
                  var str = "";
                  $(data).each(function (){    
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
		  {line:true },
		  {text: '批量变更领取人', icon: 'modify', click: function() {
			   var data = listModel.gridManager.getSelectedRows();
               if (data.length == 0) {
             	  $.ligerDialog.alert('请选择行');
               }else{
                  var str = "";
                  $(data).each(function (){    
                 	  if(str==''){
                 		  str = this.id ;
                 	  }else{
                 		  str += ","+ this.id ;
                 	  }
                  });                                                      
                  editUpdateBy(str);
               }; 
               return;
		  }}
	],
    listGrid:{ columns: [{ display: '会员ID', name: 'memberId', width: 100}, 
	                { display: '会员昵称', width: 100, render: function(rowdata,rowindex){
	                	var Nick=rowdata.nick;
	                	/* if (rowdata.procesby==null) {
	                	 if(Nick!=null && Nick.length>8){
		                   return Nick.substring(0, 6)+"**"+Nick.substring(Nick.length-1, Nick.length);;          		
	                	}else{
	                	   return Nick;
	                	}							
					    }else if (rowdata.procesby!=null) {
						   return rowdata.nick;
						} */
						if (rowdata.procesby!=null && rowdata.procesby==${sessionScope.USER_SESSION.staffID}) {
		                	  return Nick;
		                  }
						if (rowdata.procesby!=null && rowdata.procesby!=${sessionScope.USER_SESSION.staffID}) {
		                	 if(Nick!=null && Nick.length>8){
		                		return Nick.substring(0, 6)+"**"+Nick.substring(Nick.length-1, Nick.length);	                 
		  	                 }		                	
						  }
	                }}, 
	                { display: '手机号码', width: 100,render: function(rowdata,rowindex){
	                	var Mobile=rowdata.mobile;
	                	/* if (rowdata.procesby==null ) {
	                	 if(Mobile!=null){
		                   return Mobile.substring(0, 7)+"****";          		
	                	}else {
	                	   return "";
						}   							
					   }else if (rowdata.procesby!=null && rowdata.procesby==${sessionScope.USER_SESSION.staffID}) {
						 return rowdata.mobile;
					   } */	
					   if (rowdata.procesby!=null && rowdata.procesby==${sessionScope.USER_SESSION.staffID} || rowdata.assistantContact=='1' ) {
		                	  return Mobile;
		                  }
		               if (rowdata.procesby!=null && rowdata.procesby!=${sessionScope.USER_SESSION.staffID} || rowdata.assistantContact=='1') {
		                    if(Mobile!=null){
		  		              return Mobile.substring(0, 6)+"****"+Mobile.substring(Mobile.length-1, Mobile.length); 	                 
		  	                }		                	
						  }
	                }},	              
	                { display: '反馈类型', name: 'typeDesc', width: 100},
	                { display: '反馈内容', name: 'content', width: 300},
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
	               	{ display: '系统', name: 'phoneSystemName', width: 100},
	            	{ display: '手机系统版本', name: 'systemversion', width: 100},
	               	{ display: '手机型号', name: 'phoneModel', width: 100},
	               	{ display: 'APP版本', width: 150 , render: function (rowdata, rowindex) {
	               		var html = [];
	               		if (rowdata.phoneSystemName!=null){
	               			html.push(rowdata.phoneSystemName);
	               		}
	               		if (rowdata.appVersion!=null){
	               			html.push("V"+rowdata.appVersion);
	               		}
	               		return html.join("");
	            	}},
	            	{ display: '领取人', width: 150, render: function (rowdata, rowindex) {
 	                  if(rowdata.staffName){
 	                	return rowdata.staffName;
 	                  }else{
	                	  return "<input type='button' style='cursor: pointer;width: 60px;height: 35px;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius:6px;border:1px solid #ddd;background-color:#269abc;border-color: #269abc;' onclick='saveMemberFeedback("
							+ rowdata.id
							+ ",this)' value='领取'></input>";
	                	  
	                  }	
	               	}},
	               	{ display: '领取时间', width: 150, render: function (rowdata, rowindex) {
	               		if(rowdata.processdate!=null && rowdata.processdate!=""){
	                   	var processDate=new Date(rowdata.processdate);
	                   	return processDate.format("yyyy-MM-dd hh:mm");               			
	               		}else{
	               		 return "";	
	               		}
	               	}},
	               	{ display: '处理状态', name:'dealstatus', width: 150, render: function (rowdata, rowindex) {
	                   if(rowdata.dealstatus=="0"){
	                	  return "待处理";
	                   }else if (rowdata.dealstatus=="1") {
						  return "已处理";
					}else if (rowdata.dealstatus=="2") {
						  return "不需要处理";
					} 
	                   
	               	}},
	            	{ display: '处理意见', name: 'dealopinion', width: 100},
	            	{ display: '相关订单号', name: 'relatedorder', width: 100},
	            	{ display: '商家序号/商家公司名称', width: 200,render:function(rowdata, rowindex){
	            		if (rowdata.mchtCode!=null && rowdata.mchtcompanyName!=null) {
	            		   return rowdata.mchtCode+rowdata.mchtcompanyName;							
						}else{
						   return "";
						}
	            	}},
	            	{ display: '处理时间', width: 100,render: function (rowdata, rowindex){
	            		if(rowdata.dealdate !=null && rowdata.dealdate!=""){
	            		var dealDate=new Date(rowdata.dealdate);
	                   	return dealDate.format("yyyy-MM-dd hh:mm");		            			            			
	            		}else{
	            		 return "";	
	            		}
	            	}},
	            	{ display: '操作', name: 'OPER', width: 100,render: function (rowdata, rowindex){	
	            		var html = [];
	            		if(rowdata.procesby!=null && rowdata.procesby!=""){
	            			if ((rowdata.procesby==${sessionScope.USER_SESSION.staffID} || rowdata.assistantContact=='1') && rowdata.dealstatus=='0') {
	            				html.push("<a href=\"javascript:MemberFeedbackProcesBy(" + rowdata.id + ");\">处理</a>");
	            				html.push( "<a href=\"javascript:addWork();\">【创建工单】</a>");
							}
	            		}else if(rowdata.procesby==null && rowdata.procesby==""){
	            			html.push( "");
	            		} 
	            		 return html.join("");

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
		    $("#dataForm").attr("action","${pageContext.request.contextPath}/appMng/feedback/downloadListCheckout.shtml?id="+str);
		    $("#dataForm").submit();
		
	}
     return;
}

function excel(){  
	$("#dataForm").attr("action","${pageContext.request.contextPath}/appMng/feedback/downloadList.shtml");
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
			 <div class="search-td-label" >反馈类型:</div>
			 <div class="search-td-condition" >
				<select id="type" name="type">
					<option value="">请选择</option>
					<c:forEach var="list" items="${types}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >系统:</div>
			 <div class="search-td-condition" >
				<select id="phoneSystem" name="phoneSystem">
					<option value="">请选择</option>
					<c:forEach var="list" items="${phoneSystems}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >版本号：</div>
			 <div class="search-td-condition" >
				 <input type="text" id = "appVersion" name="appVersion" >
			 </div>
			 </div>
			 
			 <div class="search-td">
			 <div class="search-td-label" >处理状态:</div>
			 <div class="search-td-condition" >
				<select id="dealstatus" name="dealstatus">
					<option value="">请选择</option>
				    <option value="0">待处理</option>
				    <option value="1">已处理</option>
				    <option value="2">不需要处理</option>
				    <option value="3">未处理</option>
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
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
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
					<div class="search-td-label" style="float:left;">处理人：</div>
					<div class="l-panel-search-item">
						<select id="procesBy" name="procesBy" style="width: 150px;">
							<option value="">请选择</option>
							<!-- <option value="unclaimed">未领取</option> -->
							<c:forEach var="memberfeedback" items="${MemberfeedbackList}">
								<option value="${memberfeedback.proces_by}">${memberfeedback.proces_by_name}</option>
							</c:forEach>
						</select>
					</div>
				</div>	
			<div class="search-td">
				<div class="search-td-label" >会员ID：</div>
			 	<div class="search-td-condition" >
					<input type="text" id="memberId" name="memberId" >
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
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
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
			<div class="search-td">
				<div class="search-td-label" >商家序号：</div>
			 	<div class="search-td-condition" >
					<input type="text" id="mchtCode" name="mchtCode" >
			 	</div>
			</div>	 
				<div class="search-td">
				<div class="search-td-label" >商家公司名称：</div>
			 	<div class="search-td-condition" >
					<input type="text" id="mchtcompanyName" name="mchtcompanyName" >
			 	</div>
			</div>
			<!-- <div class="search-td">
				<input name="btnSubmit" type="button" id="Button1" class="l-button l-button-submit" value="导出excel" onclick="return excel();" />
			</div>	
			<div class="search-td">
				<div id="searchbtn" >搜索</div>
				
			</div> -->
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