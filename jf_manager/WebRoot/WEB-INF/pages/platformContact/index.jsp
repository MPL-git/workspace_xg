<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
 function viewRegisterUrl(id){
	 $.ligerDialog.open({
			height: 250,
			width: 500,
			title: "查看注册专属链接",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/service/platformContact/viewRegisterUrl.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
 function setRegisterUrl(id){
	 $.ajax({
			url : "${pageContext.request.contextPath}/service/platformContact/setRegisterUrl.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {id:id},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("操作成功");
					setTimeout(function(){
						location.reload();
					},1000);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
 }
 
 function viewZsSettledUrl(id){
	 $.ligerDialog.open({
			height: 250,
			width: 500,
			title: "查看招商专属链接",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/service/platformContact/viewZsSettledUrl.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
 function setZsSettledUrl(id){
	 $.ajax({
			url : "${pageContext.request.contextPath}/service/platformContact/setZsSettledUrl.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {id:id},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("操作成功");
					setTimeout(function(){
						location.reload();
					},1000);
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
 }
 //修改
 function editorder(id) {
		$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width(),
		title: "修改平台对接人",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/service/platformContact/editoraddcontact.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
 //查看
 function lookuporder(id, name){
	 $.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width() ,
			title: name+"负责的商家",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/service/platformContact/lookup.shtml?platformID=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

 }
 
 //修改或添加当前协助人
 function ediAssistant(id) {
		$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width(),
		title: "修改或添加当前协助人",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/service/platformContact/assistant.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
 //添加
 var myurl = "/service/platformContact/editoraddcontact.shtml?platformID=";
 
 var listConfig={
	  
      url:"/service/platformContact/data.shtml",
   
      btnItems:[{ text: '添加平台对接人', icon: 'add', dtype:'win',  click: itemclick, url:myurl, seqId:"", width : 700, heigh:400}],
      
      listGrid:{ columns: [  { display: '类型', name: 'contactTypeDesc',width:180 },
                        { display: '系统用户', name: 'staffName',width:150},
		                { display: '对外姓名', name: 'contactName',width:150},
		                { display: '手机号',  name: 'mobile', width: 150 }, 
		                { display: '当前协助人', name: 'assistantcontactname',width:100},
		                { display: '对接人状态', name: 'statusDesc' ,width: 150 },
		                /* { display: '座机号', name: 'tel', width:130}, */
		                { display: 'QQ号', name: 'qq', width:150},
		                { display: '专属链接', render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.registerUrl){
							    html.push("<a href=\"javascript:viewRegisterUrl(" + rowdata.id + ");\">查看注册专属链接</a>&nbsp;&nbsp;"); 
		                	}else{
							    html.push("<a href=\"javascript:setRegisterUrl(" + rowdata.id + ");\">注册专属链接</a>&nbsp;&nbsp;"); 
		                	}
							if(rowdata.zsSettledUrl){
							    html.push("<a href=\"javascript:viewZsSettledUrl(" + rowdata.id  + ");\">查看招商专属链接</a>&nbsp;&nbsp;"); 
		                	}else{
							    html.push("<a href=\"javascript:setZsSettledUrl(" + rowdata.id  + ");\">招商专属链接</a>&nbsp;&nbsp;"); 
		                	}
						    return html.join("");
		                }},
		                /* { display: '邮箱', name: 'email', width: 150 }, */
		                { display: '最后更新日期', render: function (rowdata, rowindex) {
		                	if (rowdata.updateDate!=null){
								var updateDate=new Date(rowdata.updateDate);
								return updateDate.format("yyyy-MM-dd hh:mm:ss");          		
		                	}
		                }},
		                { display: "操作", name: "OPER", width: 180, align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:editorder(" + rowdata.id + ");\">修改</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:lookuporder(" + rowdata.id  + ",'" + rowdata.contactName + "');\">商家</a>&nbsp;&nbsp;"); 
						    if(rowdata.assistantid==null){
							  html.push("<a href=\"javascript:ediAssistant(" + rowdata.id +");\">添加协助人</a>&nbsp;&nbsp;"); 	
						    }else{
							  html.push("<a href=\"javascript:ediAssistant(" + rowdata.id + ");\">修改协助人</a>&nbsp;&nbsp;"); 	
				    	
						    }
						    return html.join("");
					 }
	             }
		                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr"  > 
		
			 <div class="search-td">
			 <div class="search-td-label" >类型:</div>
			 <div class="search-td-condition" >
				 <select  id="contactType" name="contactType"
					class="querysel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${platformType}">
						<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}</option>
					</c:forEach>
				</select>
			 </div>
		 	 </div>
			 	
			 <div class="search-td">
			 <div class="search-td-label">状态:</div>
			 <div class="search-td-condition" >
				<select  id="status" name="status"
					class="querysel">
					<option value="">请选择</option> 
					<c:forEach var="list" items="${platformStatus}">
						<option value="${list.STATUS_VALUE}">${list.STATUS_DESC}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			<div class="search-td">
			<div class="search-td-label">关键词：</div>
			<div class="search-td-condition" >
				<input type="text" id = "SEARCHCHAR" name="SEARCHCHAR" >
			</div>
			</div>
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
