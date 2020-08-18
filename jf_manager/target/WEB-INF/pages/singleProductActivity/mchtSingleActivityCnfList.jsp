<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
			
		});
	 });

	//添加或修改
	function saveOrUpdateMchtSingleActivityCnf(id) {
		$.ligerDialog.open({
			height: 600,
			width: 700,
			title: "商家报名数追加",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/singleProductActivity/mchtSingleActivityCnfManager.shtml?flag=2&id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//删除
	function deleteMchtSingleActivityCnf(id) {
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/singleProductActivity/deleteMchtSingleActivityCnf.shtml",
			 data : {id : id},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.statusCode != 200)
					 commUtil.alertError(json.message);
				 else{
					 $.ligerDialog.success("操作成功",function() {
						 listModel.gridManager.loadData();
					 });
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	}
	 
 var listConfig={
	      url:"/singleProductActivity/getMchtSingleActivityCnfList.shtml",
	      btnItems:[
	    			{text: '添加', icon: 'add', click: function() {
	    				saveOrUpdateMchtSingleActivityCnf('');
	    			    return;
	    			  }
	    			}        
	    	      ],
	      listGrid:{ columns: [
	                        {display:'创建日期',name:'createDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.createDate == null || rowdata.createDate == undefined) {
									return "";
								}else{
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd");
								}
	                        }},
	                        {display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:180},
	                        {display:'公司名称',name:'companyName', align:'center', isSort:false, width:180},
	                        {display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180},
	                        {display:'单品活动名称',name:'activityTypeDesc', align:'center', isSort:false, width:180},
	                        {display:'每天可报数',name:'limitQuality', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.limitMchtQuality == null) {
	                        		if(rowdata.activityType == '1') {
	                        			if(rowdata.limitQuality > 5) {
		                        			var limitQuality = rowdata.limitQuality - 5;
		                        			return "5+"+limitQuality;
		                        		}else if(rowdata.limitQuality < 5) {
		                        			var limitQuality = 5 - rowdata.limitQuality;
		                        			return "5-"+limitQuality;
		                        		}else {
			                        		return rowdata.limitQuality;
		                        		}
		                        	}else if(rowdata.activityType == '2') {
		                        		if(rowdata.limitQuality > 5) {
		                        			var limitQuality = rowdata.limitQuality - 5;
		                        			return "5+"+limitQuality;
		                        		}else if(rowdata.limitQuality < 5) {
		                        			var limitQuality = 5 - rowdata.limitQuality;
		                        			return "5-"+limitQuality;
		                        		}else {
			                        		return rowdata.limitQuality;
		                        		}
		                        	}else if(rowdata.activityType == '3') {
		                        		if(rowdata.limitQuality > 3) {
		                        			var limitQuality = rowdata.limitQuality - 3;
		                        			return "3+"+limitQuality;
		                        		}else if(rowdata.limitQuality < 3) {
		                        			var limitQuality = 3 - rowdata.limitQuality;
		                        			return "3-"+limitQuality;
		                        		}else {
			                        		return rowdata.limitQuality;
		                        		}
		                        	}else if(rowdata.activityType == '4') {
		                        		if(rowdata.limitQuality > 0) {
		                        			return "0+"+rowdata.limitQuality;
		                        		}
		                        		return rowdata.limitQuality;
		                        	}else if(rowdata.activityType == '5') {
		                        		if(rowdata.limitQuality > 3) {
		                        			var limitQuality = rowdata.limitQuality - 3;
		                        			return "3+"+limitQuality;
		                        		}else if(rowdata.limitQuality < 3) {
		                        			var limitQuality = 3 - rowdata.limitQuality;
		                        			return "3-"+limitQuality;
		                        		}else {
			                        		return rowdata.limitQuality;
		                        		}
		                        	}else if(rowdata.activityType == '6') {
		                        		if(rowdata.limitQuality > 3) {
		                        			var limitQuality = rowdata.limitQuality - 3;
		                        			return "3+"+limitQuality;
		                        		}else if(rowdata.limitQuality < 3) {
		                        			var limitQuality = 3 - rowdata.limitQuality;
		                        			return "3-"+limitQuality;
		                        		}else {
			                        		return rowdata.limitQuality;
		                        		}
		                        	}else if(rowdata.activityType == '7') {
		                        		if(rowdata.limitQuality > 0) {
		                        			return "0+"+rowdata.limitQuality;
		                        		}
		                        		return rowdata.limitQuality;
		                        	}else if(rowdata.activityType == '8') {
		                        		if(rowdata.limitQuality > 0) {
		                        			return "0+"+rowdata.limitQuality;
		                        		}
		                        		return rowdata.limitQuality;
		                        	}else if(rowdata.activityType == '10') {
		                        		if(rowdata.limitQuality > 0) {
		                        			return "0+"+rowdata.limitQuality;
		                        		}
		                        		return rowdata.limitQuality;
		                        	}else {
		                        		return "";
		                        	}
	                        	}else {
	                        		if(rowdata.limitQuality > rowdata.limitMchtQuality) {
	                        			var limitQuality = rowdata.limitQuality - rowdata.limitMchtQuality;
	                        			return rowdata.limitMchtQuality+"+"+limitQuality;
	                        		}else if(rowdata.limitQuality < rowdata.limitMchtQuality) {
	                        			var limitQuality = rowdata.limitMchtQuality - rowdata.limitQuality;
	                        			return rowdata.limitMchtQuality+"-"+limitQuality;
	                        		}else {
	                        			return rowdata.limitMchtQuality;
	                        		}
	                        	}
	                        }},
	                        {display:'特殊报名状态',name:'isSpecial', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(!rowdata.isSpecial || rowdata.isSpecial == '0') {
									return "关闭";
								}else{
									return "已开通";
								}
	                        }},
	                        {display:'操作',name:'auditOrRetrial', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:saveOrUpdateMchtSingleActivityCnf(" + rowdata.id + ");\">【修改】</a>");
								html.push("<a href=\"javascript:deleteMchtSingleActivityCnf(" + rowdata.id + ");\">【删除】</a>");	
							    return html.join("");
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
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >单品活动名称：</div>
					<div class="search-td-combobox-condition" >
						<select id="activityType" name="activityType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="activityType" items="${activityTypeList }">
								<option value="${activityType.statusValue }">
									${activityType.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >创建日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="startCreateDate" name="startCreateDate" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="endCreateDate" name="endCreateDate" >
					</div>
				</div>
			<!-- 	
				<div class="search-td">
					<div class="search-td-label"  >特殊报名状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="isSpecial" name="isSpecial" style="width: 135px;" >
							<option value="" select>请选择</option>
							<option value="0">关闭</option>
							<option value="1">已开通</option>
						</select>
				 	 </div>
				</div> -->
					</div>
		</div>
				
		<div class="search-pannel">
				<div  class="search-tr">
				<div class="search-td" >
					<div class="search-td-label"  >特殊报名状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="isSpecial" name="isSpecial" style="width: 135px;" >
							<option value="" select>请选择</option>
							<option value="0">关闭</option>
							<option value="1">已开通</option>
						</select>
					</div>
				</div>
				
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
				</div>

				
				
		</div>
		
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
