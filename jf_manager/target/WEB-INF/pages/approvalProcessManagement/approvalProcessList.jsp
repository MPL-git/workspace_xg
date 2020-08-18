<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<script type="text/javascript">


	
	//添加
	function addcommoditylist() {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title : "添加",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/resourceLocationManagement/administrationCommodity.shtml",
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	//禁用
	function prohibitProcedure(id) {
	    var title="禁用";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/approvalProcessManagement/prohibitProcedure.shtml',
			data: {id : id},
			dataType: 'json',
			success: function(data) {
				if(data.returnCode == 0000) {
					 window.location.reload();;
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
	
	
	//删除
	function delProcedure(id) {
	    var title="删除";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/approvalProcessManagement/delProcedure.shtml',
			data: {id : id},
			dataType: 'json',
			success: function(data) {
				if( data.returnCode == 0000) {
					window.location.reload();
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
	
	
	//启用
	function enableProcedure(id) {
	    var title="启用";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/approvalProcessManagement/enableProcedure.shtml',
			data: {id : id},
			dataType: 'json',
			success: function(data) {
				if( data.returnCode == 0000) {
					 window.location.reload();;
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
	

	
	//添加流程节点
	function addApproval(procedureId){
		 $.ligerDialog.open({
				height: 800,
				width: 1000,
				title: "添加",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/approvalProcessManagement/addProcedureNode.shtml?procedureId="+procedureId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	}
	
	

	  //添加流程
	 function addProcedure() {
		 $.ligerDialog.open({
				height: 600,
				width: 600,
				title: "添加",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/approvalProcessManagement/addProcedure.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	  
	  //修改流程
	 function addProcedures(id) {
		 $.ligerDialog.open({
				height: 600,
				width: 600,
				title: "修改",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/approvalProcessManagement/addProcedure.shtml?id="+id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	  
	  

	
 var listConfig={
      url:"/approvalProcessManagement/approvalProcessListData.shtml",
      btnItems:[
				{ text: '添加', icon:'add', id:'add', dtype:'win', click:addProcedure }   
	  ],
	 
		listGrid:{ columns: [
	                    { display: '流程名称',  name: 'name', width: 180},
	                    { display: '创建时间',  name: 'createDate', width: 180,render: function(rowdata, rowindex) {
	                    	 var date;
		            	 	    if (rowdata.createDate!=null){
		            	 	    	date=new Date(rowdata.createDate);
			            	        return date.format("yyyy-MM-dd hh:mm");
		            	 	    }
						 }},
	                    { display: '流程节点',  width: 180,render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.status==1) {
								html.push("<a href=\"javascript:addApproval(" + rowdata.id + ");\">查看</a>");
							}else{
								html.push("<a href=\"javascript:addApproval(" + rowdata.id + ");\">添加</a>");
							}	
						    return html.join("");
					 	}},
	                    { display: '创建人',  name: 'createName', width: 180},
	                    { display: '状态',  name: 'status', width: 180,render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.status==1) {
								html.push("启用");
							}else{
								html.push("未启用");
							}	
						    return html.join("");
					 	}},
	                    { display: '操作',   width: 180,render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.status==1) {
								html.push("<a href=\"javascript:prohibitProcedure(" + rowdata.id + ");\">禁用</a>");
							}else{
								html.push("<a href=\"javascript:enableProcedure(" + rowdata.id + ");\">启用</a>  <a href=\"javascript:delProcedure(" + rowdata.id + ");\">删除</a>  <a href=\"javascript:addProcedures(" + rowdata.id + ");\">修改</a> ");
							}	
						    return html.join("");
					 	}},
			           
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
 
 function chaneGoods(){
	 var temp = $("#whichProduct").val();
	 if(temp==1){
		 $("#artnos").hide();
		 $("#goodIds").show();
	 }
	 if(temp==2){
		 $("#artnos").show();
		 $("#goodIds").hide();
	 }

 }

    </script>  
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	
	</form>
	   <div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
