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

	  
	
 var listConfig={
      url:"/approvalProcessManagement/copyApprovedToMeListDate.shtml",
	 
		listGrid:{ columns: [
	               
	                    { display: '提审时间',  name: 'commitDate', width: 180,render: function(rowdata, rowindex) {
	                    	 var date;
		            	 	    if (rowdata.commitDate!=null){
		            	 	    	date=new Date(rowdata.commitDate);
			            	        return date.format("yyyy-MM-dd hh:mm");
		            	 	    }
						 }},
					     { display: '流程名称',  name: 'procedureName', width: 180},
					     { display: '标题名称',  name: 'name', width: 180},
					     { display: '创建人',  name: 'createName', width: 180},
					     { display: '所属部门',  name: 'department', width: 180},
	                    { display: '操作',   width: 180,render: function(rowdata, rowindex) {
	                    	var html=[];
							html.push("<a href=\"javascript:watchApproval(" + rowdata.id + ");\">查看</a>");
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
							<input type="text" id="applicationName" name="applicationName" value=""/>
						 	 </div>
				 		</div>
				 				
				 
				 
						<div class="search-td">
					 		<div class="search-td-label" >创建人：</div>
					 		<div class="search-td-condition" >
						 	 <div class="search-td-condition" >
								<input type="text" id="createBy" name="createBy" value=""/>
						 	 </div>
		 	 				</div>
			 			</div>
				 	   
				
				 	<div class="search-td">
				 		<div class="search-td-label" >流程名称：</div>
				 		<div class="search-td-condition" >
					 		<select id="procedureType" name="procedureType">
					 			<option value="">请选择...</option>
								<c:forEach items="${procedures }" var="procedure">
								<option value="${procedure.id}">${procedure.name }</option>
								</c:forEach>
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
