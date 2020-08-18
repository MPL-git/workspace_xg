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
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
		
		$("#type").bind('change',function(){
			var type = $(this).val();
			$.ajax({
				url : "${pageContext.request.contextPath}/impeach/getimpeachMember.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"type":type},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var sceneList = data.sceneList;
						var optionArray = [];
						optionArray.push('<option value="">请选择</option>');
						for(var i=0;i<sceneList.length;i++){
							var statusValue = sceneList[i].statusValue;
							var statusDesc = sceneList[i].statusDesc;
							optionArray.push('<option value="'+statusValue+'">'+statusDesc+'</option>');
						}
						$("#scene").html(optionArray.join(""));
					}else if ("4004"==data.returnCode) {
						var html = [];
						html.push('<option value="">请选择</option>');
						$("#scene").html(html.join(""));
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
		
		
		
		var dateArray = [];
		var defaultProductTypeIds = [];
		<c:if test="${not empty sysStaffProductTypeList }" >
			var sysStaffProductTypeList = ${sysStaffProductTypeList };
			for(var i=0;i<sysStaffProductTypeList.length;i++) {
				dateArray.push(dataBox(sysStaffProductTypeList[i].staffName, sysStaffProductTypeList[i].productTypeId));
				if(defaultProductTypeIds.length != 0) {
					defaultProductTypeIds.push(",");
				}
				defaultProductTypeIds.push(sysStaffProductTypeList[i].productTypeId);
			}
		</c:if>
		var productType2IdsComboBox = $("#productTypeId").ligerComboBox({ 
			isShowCheckBox: true, 
			isMultiSelect: true, 
			emptyText: false,
	        data: dateArray, 
	        valueFieldID: 'productTypeIds',
	        selectBoxWidth: 165.5,
	        width: 165.5
	    }); 
		<c:if test="${isCwOrgStatus == 1 }" >
			productType2IdsComboBox.selectValue(defaultProductTypeIds.join());
			productType2IdsComboBox.updateStyle();
		</c:if>
		
	 });
	 
	 
	 function dataBox(text, id){ 
		 var obj = new Object();
		 obj.text = text; 
		 obj.id = id; 
		 return obj;
	}
	 
	 
	 function editMchtBaseInfo(id) {
			$.ligerDialog.open({
				height: $(window).height() - 40,
				width: $(window).width() - 40,
				title: "商家基础资料",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		} 
	 
	 
	 
	 //保存客服领取人
	 function imPeachkf(id, input) {
	 	var title="领取";
	   $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	   if (yes) {
	 	$.ajax({
	 		url : "${pageContext.request.contextPath}/imPeach/imPeachkf.shtml",
	 		type : 'POST',
	 		dataType : 'json',
	 		cache : false,
	 		async : false,
	 		data : {"id" : id},
	 		timeout : 30000,
	 		success : function(data) {
	 			if ("0000" == data.returnCode) {
	 				$("#maingrid").ligerGetGridManager().reload();
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
	 
	 
	//保存法务领取人
	 function imPeachfawu(id, input) {
	 	var title="领取";
	   $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	   if (yes) {
	 	$.ajax({
	 		url : "${pageContext.request.contextPath}/imPeach/imPeachfawu.shtml",
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
					$div.text(data.fawuStaffName);
	 				$("#maingrid").ligerGetGridManager().reload();
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
	
	 function imPeachExamine(id) {
			$.ligerDialog.open({
		 		height: $(window).height(),
				width: $(window).width()-50,
				title: "查看详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/imPeach/impeachExamine.shtml?id=" + id+"&imPeachType=${imPeachType}",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
	 
	 
	 function imPeachSee(id) {
			$.ligerDialog.open({
		 		height: $(window).height(),
				width: $(window).width()-50,
				title: "查看详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/imPeach/impeachExamine.shtml?id=" + id+"&imPeachType=${imPeachType}",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
	 
		
 	 var listConfig={ 
	      url:"/imPeach/impeachMemberdata.shtml?imPeachType=${imPeachType}",
	    		  
	      btnItems:[<c:if test="${imPeachType==1}">{text: '创建举报单', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/impeach/addimpeachMember.shtml", seqId:"", width: 950, height: 600},</c:if>
	                <c:if test="${imPeachType==6 && roleId==120}">{text: '批量更换处理人', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/impeach/modifyHandlerimpeachMember.shtml", seqId:"", width: 450, height: 260}</c:if>],
	      
	      listGrid:{ columns: [
							{display:'举报编号',name:'code', align:'center', isSort:false, width:130,render:function(rowdata, rowindex){
								var html = [];
								<c:if test="${imPeachType==1 || imPeachType==3 || imPeachType==4 || imPeachType==5}">
								html.push("<a href=\"javascript:imPeachSee(" + rowdata.id + ");\">"+rowdata.code+"</a>"); 
								</c:if>
								<c:if test="${imPeachType==2 || imPeachType==6}">
								html.push(rowdata.code); 
								</c:if>
								return html.join("");
								
							}},
							{display:'举报时间',name:'', align:'center', isSort:false, width:100,hide:${imPeachType==3 || imPeachType==4 || imPeachType==5},render:function(rowdata, rowindex){
								 var html = [];
								 if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
										html.push(createDate.format("yyyy-MM-dd hh:mm"));
								    }
									return html.join("");
							}},
							{display:'领取时间',name:'', align:'center', isSort:false, width:100,hide:${imPeachType==2 || imPeachType==1 || imPeachType==4 || imPeachType==5 || imPeachType==6},render:function(rowdata, rowindex){
								 var html = [];
								 if(rowdata.receiverDate != null && rowdata.receiverDate != '' ) {
									var receiverDate = new Date(rowdata.receiverDate);
										html.push(receiverDate.format("yyyy-MM-dd hh:mm"));
								    }
									return html.join("");
							}},
							{display:'初审日期',name:'', align:'center', isSort:false, width:100,hide:${imPeachType==2 || imPeachType==1 || imPeachType==3 || imPeachType==5 || imPeachType==6},render:function(rowdata, rowindex){
								 var html = [];
								 if(rowdata.commissionerAuditDate != null && rowdata.commissionerAuditDate != '' ) {
									var commissionerAuditDate = new Date(rowdata.commissionerAuditDate);
										html.push(commissionerAuditDate.format("yyyy-MM-dd hh:mm"));
								    }else {
									    var createDate = new Date(rowdata.createDate);
										html.push(createDate.format("yyyy-MM-dd hh:mm"));
								   }
									return html.join("");
							}},
							{display:'复审提交日期',name:'', align:'center', isSort:false, width:100,hide:${imPeachType==2 || imPeachType==1 || imPeachType==3 || imPeachType==4 || imPeachType==6},render:function(rowdata, rowindex){
								 var html = [];
								 if(rowdata.fwAuditDate != null && rowdata.fwAuditDate != '' ) {
									var fwAuditDate = new Date(rowdata.fwAuditDate);
										html.push(fwAuditDate.format("yyyy-MM-dd hh:mm"));
								    }
									return html.join("");
							}},
							{display:'创建者',name:'', align:'center', isSort:false, width:180,hide:${imPeachType==2}, render:function(rowdata, rowindex) {
				                 if (rowdata.source=='0') {
				                	  return "商家";
								 }else if (rowdata.source=='1') {
									 return "平台";
								}
				            }},				       
				           {display:'创建者信息',name:'', align:'center', isSort:false, width:200,hide:${imPeachType==2 || imPeachType==5},render:function(rowdata, rowindex){
				        		var html = [];
				        	   if (rowdata.mchtId!=null && rowdata.mchtId != '') {
 	                               html.push(rowdata.mchtCode+"<br>");
 	                               html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>");
 	                               
							   }else if (rowdata.staffName!=null && rowdata.staffName != '') {
								   html.push(rowdata.staffName);
							   }
				        	   return html.join("");
				           }},
				           {display:'举报商家',name:'', align:'center', isSort:false, width:200,hide:${imPeachType==1 || imPeachType==3 || imPeachType==4 || imPeachType==6},render:function(rowdata, rowindex){
				        		var html = [];
				        	   if (rowdata.mchtId!=null && rowdata.mchtId != '') {
	                               html.push(rowdata.mchtCode+"<br>");
	                               html.push("<a href=\"javascript:editMchtBaseInfo(" + rowdata.mchtId + ");\">"+rowdata.companyName+"</a>");
	                               
							   }
				        	   return html.join("");
				           }},
				           {display:'举报类型',name:'typeDesc', align:'center', isSort:false, width:100},
				           {display:'举报场景',name:'', align:'center', isSort:false, width:200,render:function(rowdata, rowindex){
				        	   if (rowdata.type=='1') {
				        		   return rowdata.scenedesc1;
							  }else if (rowdata.type=='2') {
								  return rowdata.scenedesc2;
							  }else if (rowdata.type=='3') {
								  return rowdata.scenedesc3;
							}
				           }},
						   {display:'举报状态',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						          if (rowdata.status=='0') {
						        	  return "待领取";
								  }
						          if (rowdata.status=='1') {
						        	  return "待初审";
								  }
						          if (rowdata.status=='2') {
						        	  return "待复审";
								  }
						          if (rowdata.status=='3') {
						        	  return "驳回";
								  }
						          if (rowdata.status=='4') {
						        	  return "待结案";
								  }
						          if (rowdata.status=='5') {
						        	  return "复审驳回";
								  }
						          if (rowdata.status=='6') {
						        	  return "结案驳回";
								  }
						          if (rowdata.status=='7') {
						        	  return "结案通过";
								  }
						          if (rowdata.status=='8') {
						        	  return "超时关闭";
								  }
							   
						   }},
						   {display:'结案说明/驳回理由',name:'', align:'center', isSort:false,hide:${imPeachType==2 || imPeachType==3 || imPeachType==6}, width:200,render:function(rowdata, rowindex){
							   if (rowdata.casecloseDesc) {
								   return rowdata.casecloseDesc;
							   }else if (rowdata.rejectreasonDesc) {
								   return rowdata.rejectreasonDesc;
							}
						   }},
						   {display:'限制行为',name:'', align:'center', isSort:false,hide:${imPeachType==2 || imPeachType==3}, width:150,render:function(rowdata, rowindex){
							   var limitMemberAction=String(rowdata.limitMemberAction);
							   var limitFunctionStatus="";
							   if (limitMemberAction!=null) {
								   if (limitMemberAction.charAt(0)=="1"){
				 						limitFunctionStatus+="限制评价"+"<br>";
									}else if (limitMemberAction.charAt(0)=="2") {
										limitFunctionStatus+="限制下单"+"<br>";
									}else if (limitMemberAction.charAt(0)=="3") {
										limitFunctionStatus+="限制登入"+"<br>";
									}
				                    if (limitMemberAction.charAt(2)=="2"){
				                    	limitFunctionStatus+="限制下单"+"<br>";
									}else if (limitMemberAction.charAt(2)=="3") {
										limitFunctionStatus+="限制登入"+"<br>";
									}else if (limitMemberAction.charAt(2)=="1") {
										limitFunctionStatus+="限制评价"+"<br>";
									}
				                    if (limitMemberAction.charAt(4)=="3"){
				                    	limitFunctionStatus+="限制登入"+"<br>";
									}else if (limitMemberAction.charAt(4)=="2") {
										limitFunctionStatus+="限制下单"+"<br>";
									}else if (limitMemberAction.charAt(4)=="1") {
										limitFunctionStatus+="限制评价"+"<br>";
									}
				                    return limitFunctionStatus;
							}
						   }},
						   {display:'初审时间',name:'', align:'center', isSort:false,hide:${imPeachType==2 || imPeachType==4 || imPeachType==3 || imPeachType==5}, width:150,render:function(rowdata, rowindex){
								var html = [];
								 if(rowdata.commissionerAuditDate != null && rowdata.commissionerAuditDate != '' ) {
									var commissionerAuditDate = new Date(rowdata.commissionerAuditDate);
										html.push(commissionerAuditDate.format("yyyy-MM-dd hh:mm"));
								  }else {
									    var createDate = new Date(rowdata.createDate);
										html.push(createDate.format("yyyy-MM-dd hh:mm"));
								   }
									return html.join("");
							}},
							{display:'初审人',name:'kfStaffName', align:'center', isSort:false, width:100,hide:${imPeachType==2},render:function(rowdata, rowindex){
								if (rowdata.kfStaffName != null && rowdata.kfStaffName != '') {
									return rowdata.kfStaffName;
								}
							}},
							{display:'初审驳回理由',name:'rejectreasonDesc', align:'center', isSort:false, width:180,hide:${imPeachType==2 || imPeachType==4 || imPeachType==1 || imPeachType==5 || imPeachType==6}},
							{display:'复审时间',name:'', align:'center', isSort:false, width:150,hide:${imPeachType==2 || imPeachType==3 || imPeachType==4 || imPeachType==5},render:function(rowdata, rowindex){
								var html = [];
								 if(rowdata.fwAuditDate != null && rowdata.fwAuditDate != '' ) {
									var fwAuditDate = new Date(rowdata.fwAuditDate);
										html.push(fwAuditDate.format("yyyy-MM-dd hh:mm"));
								    }
									return html.join("");
							}},
						  {display:'复审人',name:'fawuStaffName', align:'center', isSort:false, width:180,hide:${imPeachType==2 || imPeachType==3 || imPeachType==4}},
						  {display:'复审人',name:'', align:'center', isSort:false, width:180,hide:${imPeachType==2 || imPeachType==3 || imPeachType==1 || imPeachType==5 || imPeachType==6},render:function(rowdata, rowindex){
							  if (rowdata.fawuStaffName) {
								  return rowdata.fawuStaffName;
							   }else {
							   if (${faWu==true}) {
							     return "<input type='button' style='cursor: pointer;width: 60px;height: 35px;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius:6px;border:1px solid #ddd;background-color:#269abc;border-color: #269abc;' onclick='imPeachfawu("
								+ rowdata.id
								+ ",this)' value='领取'></input>";
								
							   }
							}
						  }},
						  {display:'复审驳回理由/内部备注',name:'fwInnerRemarks', align:'center', isSort:false, width:180,hide:${imPeachType==2 || imPeachType==4 || imPeachType==5 || imPeachType==6},render:function(rowdata, rowindex){
							  if (rowdata.status=='5') {
								  return rowdata.fwRejectReason;
							 }else if (rowdata.status=='4') {
								 return rowdata.fwInnerRemarks;
							 }
						  }},
						  {display:'结案时间',name:'', align:'center', isSort:false, width:100,hide:${imPeachType==2 || imPeachType==3 || imPeachType==4 || imPeachType==5},render:function(rowdata, rowindex){
								 var html = [];
								 if(rowdata.endAuditDate != null && rowdata.endAuditDate != '' ) {
									var endAuditDate = new Date(rowdata.endAuditDate);
										html.push(endAuditDate.format("yyyy-MM-dd hh:mm"));
								 }
								 return html.join("");
							}},
						   {display:'结案驳回理由/内部备注',name:'endInnerRemarks', align:'center', isSort:false, width:180,hide:${imPeachType==2 || imPeachType==4 || imPeachType==5 || imPeachType==6},render:function(rowdata, rowindex){
							   if (rowdata.status=='6') {
									  return rowdata.endRejectReason;
								 }else if (rowdata.status=='7') {
									 return rowdata.endInnerRemarks;
								}
						   }},
						   {display:'操作',name:'', align:'center', isSort:false, width:100,hide:${imPeachType==2 || imPeachType==3},render:function(rowdata, rowindex){
							      if (${imPeachType=='4'} && rowdata.fwAuditBy==${sessionScope.USER_SESSION.staffID}) {
							         return "<a href=\"javascript:imPeachSee(" + rowdata.id + ");\">查看</a>";	
								  }
							      if (${imPeachType=='1' || imPeachType=='5' || imPeachType=='6'}) {
							    	  return "<a href=\"javascript:imPeachSee(" + rowdata.id + ");\">查看</a>";
								  }
						   }},
						   {display:'操作',name:'', align:'center', isSort:false, width:100,hide:${imPeachType==1 || imPeachType==2 || imPeachType==4 || imPeachType==5 || imPeachType==6},render:function(rowdata, rowindex){
							     if (rowdata.commissionerAuditBy==${sessionScope.USER_SESSION.staffID}) {
							    	 return "<a href=\"javascript:imPeachExamine(" + rowdata.id + ");\">审核</a>";
								 }else {
							         return "<a href=\"javascript:imPeachSee(" + rowdata.id + ");\">查看</a>";
									
								}
						   }},
						   { display: '领取人', width: 150,hide:${imPeachType==1 || imPeachType==3 || imPeachType==4 || imPeachType==5 || imPeachType==6},render: function (rowdata, rowindex) {
			 	                 if (${kfStaffInfos==true}) {
									
				                 return "<input type='button' style='cursor: pointer;width: 60px;height: 35px;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius:6px;border:1px solid #ddd;background-color:#269abc;border-color: #269abc;' onclick='imPeachkf("
										+ rowdata.id
										+ ",this)' value='领取'></input>";
								}
				                	  
				         }},
							
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
 
 	//导出
 	function subFunction(statusExcel) {
      	$("#dataForm").attr("action","${pageContext.request.contextPath}/imPeach/imPeachExport.shtml");
		$("#dataForm").submit();

   }

	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
			 <c:if test="${imPeachType ne '3'}">
			 <div class="search-td">
			    <c:if test="${imPeachType eq '1'}">
				<div class="search-td-label" style="float:left;"  >提审时间：</div>			   
				</c:if>
				<c:if test="${imPeachType eq '2' || imPeachType eq '6'}">
				<div class="search-td-label" style="float:left;"  >举报时间：</div>
				</c:if>
				<c:if test="${imPeachType eq '4'}">
				<div class="search-td-label" style="float:left;"  >初审日期：</div>
				</c:if>
				<c:if test="${imPeachType eq '5'}">
				<div class="search-td-label" style="float:left;"  >复审日期：</div>
				</c:if>
				<div class="l-panel-search-item" >
					 <input type="text" class="dateEditor" id="starcreateDate" name="starcreateDate" >
				 </div>
				 <div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			  </div>
			  <div class="search-td">
				   <div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="endcreateDate" name="endcreateDate" >
				   </div>
			  </div>
			  </c:if>
			   <div class="search-td">
					<div class="search-td-label" >举报编号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="code" name="code" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
			<c:if test="${imPeachType eq '3'}">
				<div class="search-td">
			 	<div class="search-td-label" >处理人：</div>
			 	<div class="search-td-condition" >
				<select id="commissionerAuditBy" name="commissionerAuditBy" class="querysel">
					<option value="">请选择...</option>
					<option value="${staffId}" selected="selected">我自己的</option>
					<c:forEach var="getReceiverbyList" items="${getReceiverbyList}">
					    <c:if test="${getReceiverbyList.commissioner_audit_by ne staffId}">
						<option value="${getReceiverbyList.commissioner_audit_by}" >${getReceiverbyList.staff_name}</option>
						</c:if>
					</c:forEach>			
				</select>
				</div>
		 	    </div>
		 	    
		 	    <div class="search-td">
					<div class="search-td-label" >相关单号：</div>
					<div class="search-td-condition" >
						<input type="text" id="subOrderCode" name="subOrderCode" >
					</div>
			  </div>
		 	 </c:if> 	 	   	 	  	 	   					 
			</div>	
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
			 	<div class="search-td-label" >举报类型：</div>
			 	<div class="search-td-condition" >
				<select id="type" name="type" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="impeachListMemberTypeList" items="${impeachListMemberTypeList}">
						<option value="${impeachListMemberTypeList.statusValue}" >${impeachListMemberTypeList.statusDesc}</option>
					</c:forEach>			
				</select>
				</div>
		 	    </div>
		 	    <div class="search-td">
			 	<div class="search-td-label" >举报场景：</div>
			 	<div class="search-td-condition" >
				<select id="scene" name="scene" class="querysel">
					<option value="">请选择...</option>			
				</select>
				</div>
		 	    </div>
		 	   <c:if test="${imPeachType ne '3'}">
		 	  <div class="search-td">
					<div class="search-td-label" >子订单号：</div>
					<div class="search-td-condition" >
						<input type="text" id="subOrderCode" name="subOrderCode" >
					</div>
			  </div>
			  </c:if>				
			</div>
			<c:if test="${imPeachType eq '1' || imPeachType eq '4' || imPeachType eq '5' || imPeachType eq '6' || imPeachType eq '3'}">
			<div class="search-tr"  >
			    <div class="search-td">
			 	<div class="search-td-label" >举报状态：</div>
			 	<div class="search-td-condition" >
				<select id="status" name="status" class="querysel">
					<option value="">请选择...</option>
				   <c:if test="${imPeachType eq '1' || imPeachType eq '6' || imPeachType eq '3'}">
					<option value="0">待领取</option>
					<option value="1">待初审</option>
					<option value="2">待复审</option>
					<option value="3">驳回</option>
					<option value="4">待结案</option>
					<option value="5">复审驳回</option>
					<option value="6">结案驳回</option>
					<option value="7">结案通过</option>
				    <option value="8">超时关闭</option>
				    </c:if>
				    <c:if test="${imPeachType eq '4' }">
				    <option value="2" selected="selected">待复审</option>
				    <option value="5">复审驳回</option>
				    <option value="4">复审通过</option>
				    </c:if>
				    
				    <c:if test="${imPeachType eq '5' }">
				    <option value="4" selected="selected">待结案</option>
				    <option value="6">结案驳回</option>
				    <option value="7">结案通过</option>
				    </c:if>				
				</select>
				</div>
		 	    </div>
		 	    <c:if test="${imPeachType ne '5' && imPeachType ne '3'}">
		 	    <div class="search-td">
			 	<div class="search-td-label" >初审人：</div>
			 	<div class="search-td-condition" >
				<select id="commissionerAuditBy" name="commissionerAuditBy" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="getCommissionerauditbyList" items="${getCommissionerauditbyList}">
						<option value="${getCommissionerauditbyList.commissioner_audit_by}" >${getCommissionerauditbyList.staff_name}</option>
					</c:forEach>			
				</select>
				</div>
		 	    </div>
		 	    <div class="search-td">
			 	<div class="search-td-label" >复审人：</div>
			 	<div class="search-td-condition" >
				<select id="fwAuditBy" name="fwAuditBy" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="getFwauditbyList" items="${getFwauditbyList}">
						<option value="${getFwauditbyList.fw_audit_by}" >${getFwauditbyList.staff_name}</option>
					</c:forEach>			
				</select>
				</div>
		 	    </div>
		 	    </c:if>		
		 	    <c:if test="${imPeachType eq '4' || imPeachType eq '6'}">
		 	    <div class="search-td">
			 	<div class="search-td-label" >创建方：</div>
			 	<div class="search-td-condition" >
				<select id="source" name="source" class="querysel">
					<option value="">请选择...</option>
					<option value="0">商家</option>
					<option value="1">平台</option>			
				</select>
				</div>
		 	    </div>
			    </c:if>	 
			 <c:if test="${imPeachType eq '1' ||  imPeachType eq '5'}">
			  <div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" >搜索</div>
			 </div>  
			 </c:if>	
			</div>
			</c:if>
			<c:if test="${imPeachType eq '2' || imPeachType eq '3' || imPeachType eq '4' || imPeachType eq '6'}">
			<div class="search-tr"  >
			  <div class="search-td">
				<div class="search-td-label" >商家类目：</div>
				<div style="display: inline-block;" >
					<input type="text" id="productTypeId" name="productTypeId" />		
				</div>
			</div>	
			 <div class="search-td">
					<div class="search-td-label" >会员ID：</div>
					<div class="search-td-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
			  </div>		
			  <div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" >搜索</div>
			 </div>
			</div>
			 </c:if>		
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
