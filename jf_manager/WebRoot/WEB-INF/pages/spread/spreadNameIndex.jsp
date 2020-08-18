<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    
     <script type="text/javascript">
     
	    var listConfig={
	    		  
	    	      url:"/spread/spreadNameDataList.shtml",
	    	      btnItems:[
	    	      			{text: '批量修改', icon: 'modify', click: batchEditSpreadName}
	    	                ],
	    	      listGrid:{ columns: [
		    			          { display: '排序值', name: 'seqNo',render:function(rowdata, rowindex) {
			                        	var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
			                        	return "<input type='text' id='seqNo" + rowdata.id + "' name='seqNo' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >";
			                        }},   
		    			          { display: '活动名称', name: 'spreadName'},   
		    			          { display: '所属活动组', name: 'activityGroup'},   
		    			          { display: '所属推广渠道', name: 'channelName'}, 
	    	                       { display: '是否有效', name: 'isEffect' , render: function(rowdata, rowindex){
					  					if(rowdata.isEffect=='1'){
					  						return '有效';
					  					}else{
					  						return '无效';
					  					}
				  					}}
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
	    
	    //批量修改推广活动名称
	    function batchEditSpreadName() {
	    	
			  var data = listModel.gridManager.getSelectedRows();
              if (data.length == 0){
            	  $.ligerDialog.alert('请选择要修改的记录');
            	  return;
              }
                 var str = "";
                  $(data).each(function ()
                  {    
                	  if(str==''){
                		  str = this.id ;
                	  }else{
                		  str += ","+ this.id ;
                	  }
                  });
	    	
	   		$.ligerDialog.open({
	   		height: 400,
	   		width: 600,
	   		title: "修改活动组",
	   		name: "INSERT_WINDOW",
	   		url: "${pageContext.request.contextPath}/spread/batchEditSpreadName.shtml?spreadNameIds="+str,
	   		showMax: true,
	   		showToggle: false,
	   		showMin: false,
	   		isResize: true,
	   		slide: false,
	   		data: null
	   	});
	   	}    
	    
		function updateSeqNo(id){
			$("#seqNo" + id).parent().find("span").remove();
			$("#seqNo" + id).parent().find("br").remove();
			var seqNo = $("#seqNo" + id).val();
			var flag = seqNo.match(/^[1-9]\d*$/);
			if(seqNo != '' && flag != null) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/spread/updateSpreadNameSeqNo.shtml",
					 data : {id : id, seqNo : seqNo},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200){
							 commUtil.alertError(json.message);
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}else{
				$("#seqNo" + id).val("");
				$("#seqNo" + id).parent().append("<br><span style='color:red;'>请输入正整数</span>");
			}
			
		}	    
	    
	    
    </script>
  </head>
  
  <body>
    <div class="l-loading" style="display: block" id="pageloading"></div>
	  <div id="toptoolbar"></div>
  
	  <form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
			<div class="search-td">
			 <div class="search-td-label" >推广渠道：</div>
			 <div class="search-td-condition" >
				<select id="channel" name="channel">
					<option value="">请选择</option>
					<c:forEach var="list" items="${channelList}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			<div class="search-td">
			<div class="search-td-label">活动组:</div>
			<div class="search-td-condition" >
			    <select id="activityGroupId" name="activityGroupId">
				<option value="">请选择</option>
				<c:forEach var="list" items="${spreadActivityGroupList}">
					<option value="${list.id}">${list.activityGroup}
					</option>
				</c:forEach>
				</select>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label">活动名称:</div>
			<div class="search-td-condition" >
				<input type="text" id = "spreadName" name="spreadName" >
			</div>
			</div>
			
				
			<div class="search-td">
			 <div class="search-td-label" >是否有效：</div>
			 <div class="search-td-condition" >
				<select id="isEffect" name="isEffect">
					<option value="">--请选择--</option>
						<option value="1">有效</option>
						<option value="0">无效</option>
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
