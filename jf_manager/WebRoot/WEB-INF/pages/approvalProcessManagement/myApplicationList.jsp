<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

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
<style>
.seqClass{width:50px;height:23px;text-align:center; border:1px solid red;box-sizing: border-box;border-width: 1px; border-style: solid; border-color: rgba(121, 121, 121, 1);border-radius: 0px; }
</style>
<script type="text/javascript">

	
	//删除我的申请
	function del(approvalApplicationId) {
	    var title="删除";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/approvalProcessManagement/delMyApplication.shtml?approvalApplicationId='+approvalApplicationId,
			data: {approvalApplicationId: approvalApplicationId},
			dataType: 'json',
			success: function(data) {
				if(data.returnCode != null && data.returnCode == 0000) {
					  window.location.reload(); 
					/* 	$("#searchbtn").click(); */
				}else {
					commUtil.alertError(data.returnMsg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	   }
	  });
	 }
	
	
	//提审
   	function arraignment(approvalApplicationId){
   	 var title="提审";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/approvalProcessManagement/arraignment.shtml?approvalApplicationId='+approvalApplicationId,
			data: {approvalApplicationId: approvalApplicationId},
			dataType: 'json',
			success: function(data) {
				if(data.returnCode != null && data.returnCode == 0000) {
					/*  commUtil.alertSuccess(data.returnMsg); */
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.returnMsg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	   }
	  });
	 }
	
	
	//恢复
   	function recovery(approvalApplicationId){
   	 var title="恢复";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/approvalProcessManagement/recovery.shtml?approvalApplicationId='+approvalApplicationId,
			data: {approvalApplicationId: approvalApplicationId},
			dataType: 'json',
			success: function(data) {
				if(data.returnCode != null && data.returnCode == 0000) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.returnMsg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	   }
	  });
	 }
	
	  //查看我的申请
	 function watchApproval(applicationId) {
		 $.ligerDialog.open({
				height: 800,
				width: 1200,
				title: "查看",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/approvalProcessManagement/watchApprovalApplication.shtml?applicationId="+applicationId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	

	  //添加我的申请
	 function addApproval() {
		 $.ligerDialog.open({
				height: 800,
				width: 1200,
				title: "创建",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/approvalProcessManagement/createMyApplication.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	  
	  //修改我的申请
	 function addApprovals(approvalApplicationId) {
		 $.ligerDialog.open({
			    height: 800,
				width: 1200,
				title: "修改",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/approvalProcessManagement/createMyApplication.shtml?approvalApplicationId="+approvalApplicationId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }


	//创建说明
	function creatInstructions() {
		$.ligerDialog.open({
            height: 700,
            width: 900,
			title: "创建说明",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/approvalProcessManagement/creatInstructions.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

 var listConfig={
      url:"/approvalProcessManagement/myApplicationDate.shtml",
      btnItems:[
				{ text: '创建', icon:'add', id:'add', dtype:'win', click:addApproval },
		  		{ text: '创建说明', icon:'add', id:'add', dtype:'win', click:creatInstructions }
	  ],
	 
		listGrid:{ columns: [
	               
	                    { display: '创建时间',  name: 'createDate', width: 180,render: function(rowdata, rowindex) {
	                    	 var date;
		            	 	    if (rowdata.createDate!=null){
		            	 	    	date=new Date(rowdata.createDate);
			            	        return date.format("yyyy-MM-dd hh:mm");
		            	 	    }
						 }},
					     { display: '流程名称',  name: 'procedureName', width: 180},
					     { display: '标题名称',  name: 'name', width: 180},
	                    { display: '申请单状态',  name: 'status', width: 180,render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.status==0) {
								html.push("未提审");
							}
							if (rowdata.status==1) {
								html.push("审批中");
							}
							if (rowdata.status==2) {
								html.push("完成");
							}
							if (rowdata.status==3) {
								html.push("驳回");
							}
							if (rowdata.status==4) {
								html.push("取消");
							}
						    return html.join("");
					 	}},
	                    { display: '操作',   width: 180,render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.status==0) {
								html.push("<a href=\"javascript:addApprovals(" + rowdata.id + ");\">修改</a>  &nbsp;  <a href=\"javascript:arraignment(" + rowdata.id + ");\">提审</a> &nbsp; <a href=\"javascript:del(" + rowdata.id + ");\">删除</a>  ");
							}
							if (rowdata.status==1 ||rowdata.status==2 ) {
								html.push("<a href=\"javascript:watchApproval(" + rowdata.id + ");\">查看</a>");
							}
							if (rowdata.status==3) {
								html.push("<a href=\"javascript:addApprovals(" + rowdata.id + ");\">修改</a> &nbsp; <a href=\"javascript:del(" + rowdata.id + ");\">删除</a>");
							}	
							if (rowdata.status==4) {
								html.push("<a href=\"javascript:recovery(" + rowdata.id + ");\">恢复</a>");
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
		<form id="dataForm" runat="server" >
			<input type="hidden" id="mngPos" name="mngPos" value="${mngPos}"/> 
			<input type="hidden" id="showType" name="showType" value="${showType}"/> 
			<div class="search-pannel">
				<div class="search-tr"  >				

				 		<div class="search-td">
						 	 <div class="search-td-label" >标题名称：</div>
							 <div class="search-td-condition" >
							<input type="text" id="name" name="name" value=""/>
						 	 </div>
				 		</div>
				 				
				 
				 
						<div class="search-td">
					 		<div class="search-td-label" >流程名称：</div>
					 		<div class="search-td-condition" >
						 		<select id="procedureId" name="procedureId">
									<option value="">请选择</option>
									<c:forEach var="procedure" items="${procedureList}">
										<option value="${procedure.id}">${procedure.name}</option>
									</c:forEach>
								</select>
		 	 				</div>
			 			</div>
				 	   
				
				 	<div class="search-td">
				 		<div class="search-td-label" >申请单状态：</div>
				 		<div class="search-td-condition" >
					 		<select id="status" name="status">
								<option value="">请选择</option>
								<option value="0">未提审</option>
								<option value="1">审批中</option>
								<option value="2">完成</option>
								<option value="3">驳回</option>
								<option value="4">取消</option>
								
							</select>
	 	 				</div>
			 		</div>

		
					<div class="search-td-search-btn">
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