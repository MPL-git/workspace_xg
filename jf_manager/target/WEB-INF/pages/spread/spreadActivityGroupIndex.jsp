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
    	      url:"/spread/spreadActivityGroupDataList.shtml",
    	      btnItems:[
    	                { text: '添加', icon: 'add', dtype:'win',  click: itemclick, url:"/spread/editSpreadActivityGroup.shtml?deviceType=1", seqId:"", width : 600, height:400},
    	      			{text: '批量修改', icon: 'modify', click: batchEditSpreadActivityGroup},
    	      			{text: '批量修改优惠率', icon: 'modify', click: updateDiscountRateBatch},
    	                ],
    	      listGrid:{ columns: [
	    			          { display:'排序',name:'seqNo', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
									var html = [];
									var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
									html.push("<input type='text' style='width:80px; margin-top: 5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >");
		                        	return html.join("");
		                      }},
		                      { display: '活动组ID', name: 'groupId'}, 
		                      { display: '活动组优惠率', name: 'discountRate'}, 
	    			          { display: '活动组', name: 'activityGroup'}, 
	    			          // { display: '所属推广渠道', name: 'channelName'},
					  		  { display: '所属推广渠道', name: 'channelName' , render: function(rowdata, rowindex){
							  if(rowdata.channelName){
								  return rowdata.channelName;
							  }else{
								  return '落地页';
							  }
						 	  }},
							 { display: '是否有效', name: 'isEffect' , render: function(rowdata, rowindex){
				  					if(rowdata.isEffect=='1'){
				  						return '是';
				  					}else{
				  						return '否';
				  					}
			  				  }}, 
				              { display: "操作", name: "OPER", width: 150, align: "center", render: function(rowdata, rowindex) {
									var html = [];
								    html.push("<a href=\"javascript:editSpreadActivityGroup(" + rowdata.id + ");\">【修改】</a>"); 
								    if(rowdata.isEffect == '0') {
								    	html.push("<a href=\"javascript:delSpreadActivityGroup(" + rowdata.id + ");\">【删除】</a>");
								    }
								    return html.join("");
							 		}
					          }
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
	    
	    //比量修改修改
	    function batchEditSpreadActivityGroup() {
			 var data = listModel.gridManager.getSelectedRows();
             if (data.length == 0){
            	  $.ligerDialog.alert('请选择要修改的记录');
            	  return;
             }
             var str = "";
               	$(data).each(function () {    
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
		   		url: "${pageContext.request.contextPath}/spread/batchEditSpreadActivityGroup.shtml?deviceType=1&spreadActivityGroupIds="+str,
		   		showMax: true,
		   		showToggle: false,
		   		showMin: false,
		   		isResize: true,
		   		slide: false,
		   		data: null
		   	});
	   	}    
	    
	    //修改
	    function editSpreadActivityGroup(id) {
		   	$.ligerDialog.open({
		   		height: 400,
		   		width: 600,
		   		title: "修改活动组",
		   		name: "INSERT_WINDOW",
		   		url: "${pageContext.request.contextPath}/spread/editSpreadActivityGroup.shtml?deviceType=1&id=" + id,
		   		showMax: true,
		   		showToggle: false,
		   		showMin: false,
		   		isResize: true,
		   		slide: false,
		   		data: null
		   	});
	   	}

		//批量修改优惠率
		function updateDiscountRateBatch() {
			$.ligerDialog.open({
				height: 600,
				width: 900,
				title: "批量修改优惠率",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/spread/updateDiscountRateBatchManager.shtml?deviceType=1",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}

	    //排序值
	    function updateSeqNo(id) {
			$("#seqNo" + id).parent().find("span").remove();
			var seqNo = $("#seqNo" + id).val();
			var flag = seqNo.match(/^[1-9]\d*$/);
			if(seqNo != '' && flag != null) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/spread/updateSeqNo.shtml",
					 data : {id : id, seqNo : seqNo},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.code != 200) {
							 commUtil.alertError(data.msg);
						 }else{
							 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
							 $("#seqNo" + id).attr("seqNo", seqNo);
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}else{
				$("#seqNo" + id).val($("#seqNo" + id).attr("seqNo"));
				$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
			} 
	   }
	    
	   //删除
	   function delSpreadActivityGroup(id) {
		 $.ligerDialog.confirm("是否删除？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/spread/delSpreadActivityGroup.shtml",
					 data : {id : id},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.code != 200) {
							 commUtil.alertError(data.msg);
						 }else {
							 $("#searchbtn").click();
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				 });
			 }
		 });
		 $(".l-dialog-content").css("margin-right", "5px");
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
					<option value="6">落地页</option>
					<c:forEach var="list" items="${channelList}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			<div class="search-td-label">活动组ID:</div>
			<div class="search-td-condition" >
				<input type="text" id = "groupId" name="groupId" >
			</div>
			</div>
				
			<div class="search-td">
			<div class="search-td-label">活动组名称:</div>
			<div class="search-td-condition" >
				<input type="text" id = "activityGroup" name="activityGroup" >
			</div>
			</div>
				
			<div class="search-td">
			 <div class="search-td-label" >状态：</div>
			 <div class="search-td-condition" >
				<select id="isEffect" name="isEffect">
					<option value="">请选择</option>
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
