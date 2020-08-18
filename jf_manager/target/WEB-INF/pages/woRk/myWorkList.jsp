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
 	var viewerPictures;
 	var viewerFlag = true;
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
		$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		});
		
	 });	 
	 
	 //标题
	 function viewWork(id) {
		 $.ligerDialog.open({
				height: 600,
				width: 950,
				title: "工单详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/work/viewWork.shtml?id="+id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		  });
	  }
	 
	 
	    //查看介入单详情
		function interventionOrder(interventionOrderId, statusPage) {
			$.ligerDialog.open({
				height : $(window).height() - 100,
				width : $(window).width() - 200,
				title : "查看详情",
				name : "INSERT_WINDOW",
				url : "${pageContext.request.contextPath}/interventionOrder/showInterventionOrderManager.shtml?interventionOrderId="
						+ interventionOrderId + "&statusPage=" + statusPage,
				showMax : true,
				showToggle : false,
				showMin : false,
				isResize : true,
				slide : false,
				data : null
			});
		}
	    
		//查看子订单详情
		function subOrder(id) {
			$.ligerDialog.open({
		 		height: $(window).height(),
				width: $(window).width()-50,
				title: "子订单详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		} 
	    
	   //查看投诉单详情	
	  function appealOrder(id) {
			$.ligerDialog.open({
				height : $(window).height() - 50,
				width : $(window).width() - 100,
				title : "投诉详情页",
				name : "INSERT_WINDOW",
				url : "${pageContext.request.contextPath}/appealOrder/view.shtml?id="
						+ id,
				showMax : true,
				showToggle : false,
				showMin : false,
				isResize : true,
				slide : false,
				data : null
			});
		}
	  
	  //查看售后单详情
	  function customerServiceOrder(id) {
			$.ligerDialog.open({
		 		height: $(window).height(),
				width: $(window).width()-50,
		 		title: "售后详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
	  
	  //查看产权投诉详情
	  function rightAppeal(id) {
			$.ligerDialog.open({
		 		height: $(window).height(),
				width: $(window).width()-50,
		 		title: "产权投诉详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/propertyRightAppeal/appealManageDetail.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
		
 	 var listConfig={ 
	      url:"/work/data.shtml",
	      btnItems:[{ text: '新增工单', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/work/addwork.shtml", seqId:"", width: 950, height: 600 }],
	      listGrid:{ columns: [
							{display:'工单编号',name:'id', align:'center', isSort:false, width:100},
							{display:'创建时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
				             var html = [];
							 if(rowdata.createDate != null && rowdata.createDate != '' ) {
								var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
							    }
								return html.join("");
				            }},
			            
						   {display:'紧急程度',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    if (rowdata.urgentDegree=='1') {
								return "普通";
							}
							if (rowdata.urgentDegree=='2') {
								return "重要";
							}
						    if (rowdata.urgentDegree=='3') {
								return "紧急";
							}
							if (rowdata.urgentDegree=='4') {
								return "重要紧急";
							}					
						   
						   }},
						   {display:'类型',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
							if (rowdata.workType=='1') {
							    return "售后咨询";
							 }
							if (rowdata.workType=='2') {
								return "售前咨询";
							}
							if (rowdata.workType=='3') {
								return "入驻咨询";
							}
							if (rowdata.workType=='4') {
								return "合同咨询";
							}
							if (rowdata.workType=='5') {
								return "合工商投诉";
							}	
							if (rowdata.workType=='6') {
								return "其他";
							}
							if (rowdata.workType=='7') {
								return "产权投诉";
							}
							   
						   }},
						   {display:'标题',name:'titleContent', align:'center', isSort:false, width:300,render:function(rowdata, rowindex){
							   var html=[];
							   html.push("<a href='javascript:;' onclick='viewWork(" + rowdata.id + ");'>"+rowdata.titleContent+"</a></br>");
							   return html.join("");
							   
						   }},
						   {display:'当前指派',name:'staffName', align:'center', isSort:false, width:100},
						   {display:'问题状态',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
							if (rowdata.status=='0' && rowdata.statusBehavior=='1') {
								return "未回复(首次提审)";
							}
							if (rowdata.status=='0' && rowdata.statusBehavior=='2') {
								return "未回复(重新编辑)";
							}
							if (rowdata.status=='0' && rowdata.statusBehavior=='3') {
								return "未回复(添加备注)";
							} 
							if (rowdata.status=='1') {
								return "已回复";
							}
							if (rowdata.status=='2') {
								return "已关闭";
							}
							   
						   }},
						   {display:'关闭原因',name:'', align:'center', isSort:false, width:300,render:function(rowdata, rowindex){
							 if (rowdata.closeReason!=null || rowdata.closeReason!='') {
								 if (rowdata.closeReason=='1') {
									 return "已完成"+"<br>"+rowdata.remarks;

								}
								 if (rowdata.closeReason=='2') {
									 return "已终止"+"<br>"+rowdata.remarks;
								
								}
								 if (rowdata.closeReason=='3') {
									 return "已作废"+"<br>"+rowdata.remarks;

								}
							 }
						   
						   }},
						   {display:'相关单号',name:'', align:'center', isSort:false, width:200, render: function(rowdata, rowindex) {
							 var html=[];
							 if (rowdata.relevantType=='1') {
								html.push("介入单"+"</br>");
								html.push("<a href='javascript:;' onclick='interventionOrder(" + rowdata.relevantId + ", 1);'>"+rowdata.relevantCode+"</a></br>");
							    }
							 if (rowdata.relevantType=='2') {
									html.push("投诉单"+"</br>");
									html.push("<a href='javascript:;' onclick='appealOrder(" + rowdata.relevantId + ");'>"+rowdata.relevantCode+"</a></br>");
								 }
							 if (rowdata.relevantType=='3') {
									html.push("");
								 }
							 if (rowdata.relevantType=='4') {
									html.push("子订单"+"</br>");
									html.push("<a href='javascript:;' onclick='subOrder(" + rowdata.relevantId + ");'>"+rowdata.relevantCode+"</a></br>");
								 }
							 if (rowdata.relevantType=='5') {
									html.push("售后单"+"</br>");
									html.push("<a href='javascript:;' onclick='customerServiceOrder(" + rowdata.relevantId + ");'>"+rowdata.relevantCode+"</a></br>");
								 }
							 if (rowdata.relevantType=='6') {
									html.push("");								
							     }
							 if (rowdata.relevantType=='7') {
								 	html.push("产权投诉单"+"</br>");
								 	html.push("<a href='javascript:;' onclick='rightAppeal(" + rowdata.relevantCode + ");'>"+rowdata.relevantCode+"</a></br>");
									html.push("");								
							     }
							   return html.join("");
						   }}
							
			         ], 			    
	                 showCheckbox :false,  //不设置默认为 true
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
				<div class="search-td-label" style="float:left;"  >创建日期：</div>
				<div class="l-panel-search-item" >
					 <input type="text" class="dateEditor" id="startCreateDate" name="startCreateDate" >
				 </div>
				 <div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			  </div>
			  <div class="search-td">
				   <div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="endCreateDate" name="endCreateDate" >
				   </div>
			  </div>
			   <div class="search-td">
			 	<div class="search-td-label" >工单类型：</div>
			 	<div class="search-td-condition" >
				<select id="workType" name="workType" class="querysel">
					<option value=''>请选择...</option>
				    <option value='1'>售后咨询</option>
				    <option value='2'>售前咨询</option>
					<option value='3'>入驻咨询</option>
					<option value='4'>合同咨询</option>
					<option value='5'>工商投诉</option>
					<option value='7'>产权投诉</option>
					<option value='6'>其他</option>
				</select>
				</div>
		 	   </div>
		 	  <div class="search-td">
			 	<div class="search-td-label" >问题状态：</div>
			 	<div class="search-td-condition" >
				<select id="status" name="status" class="querysel">
					<option value="">请选择...</option>
					<option value="0">未回复</option>
					<option value="1">已回复</option>
					<option value="2">已关闭</option>						
				</select>
				</div>
		 	  </div>	 	   	 	  	 	   					 
			</div>
			
			<div class="search-tr"  >
			   <div class="search-td">
					<div class="search-td-label" >标题：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="titleContent" name="titleContent" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >工单编号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id" >
					</div>
				</div>
				<div class="search-td">
			 	<div class="search-td-label" >当前指派：</div>
			 	<div class="search-td-condition" >
				<select id="staffId" name="staffId" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="sysStaffInfos" items="${sysStaffInfos}">
					   <option value="${sysStaffInfos.staffId}">${sysStaffInfos.staffName}</option>
				   </c:forEach>					
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
