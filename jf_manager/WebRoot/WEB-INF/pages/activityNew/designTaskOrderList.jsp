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
		 $("#status").bind('click',function(){
				if($(this).attr("checked")){
					$(this).val(1);
				}else{
					$(this).val(0);
				}
			}); 
	 });
	 
	 //领取人
	 function getDesigner(id) {
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/activityNew/getDesigner.shtml',
			 data: {id : id},
			 dataType: 'json',
			 success: function(data) {
				 if(data == null || data.statusCode != 200){
				     commUtil.alertError(data.message);
				 }else {
					 $("#searchbtn").click();
				 }
			 },
			 error: function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
	 	 
	 //查看任务
	 function showTask(id,statusPage) {
		 var title="";
		if (statusPage=='1') {
			title="查看任务";
		}else if (statusPage=='2') {
			title="上传图片";
		}else if (statusPage=='3') {
			title="重新上传";
		}
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityNew/showTask.shtml?statusPage="+statusPage+"&id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //退款
	 function reFund(id) {
			$.ligerDialog.confirm("是否确认退款？", function (yes) { 
				if(yes) {
					$.ajax({
						url : "${pageContext.request.contextPath}/activityNew/reFund.shtml?id=" + id,
						dataType : 'json',
						type : 'POST',
						cache : false,
						async : false,
						success : function(data) {
							if ("0000" == data.returnCode) {
								commUtil.alertSuccess("操作成功，等待退款中");
								setTimeout(function(){
									$("#searchbtn").click();
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
			});
		}

	 
 	 var listConfig={
	      url:"/activityNew/designTaskOrderdata.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'订单编号',name:'orderCode', align:'center', isSort:false, width:180},
	                        {display:'类型',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	var html=[];
	                        	if (rowdata.taskType=='1') {
	                        		html.push("品牌特卖");
								}else if (rowdata.taskType=='2') {
									html.push("店铺装修");
								}
	                        	return html.join("");
	                        }},
							{display:'任务名称',name:'taskName', align:'center', isSort:false, width:180},
	                        {display:'需求详情',name:'', align:'center', isSort:false, width:180,render: function(rowdata, rowindex) {
								var html=[];
								if (rowdata.requirement!=null && rowdata.requirement!='') {
									if (rowdata.requirement.length > 100) {
									    html.push(rowdata.requirement.substring(0,30)+"******"+rowdata.requirement.substring(rowdata.requirement.length-1)+"<br>");
									    html.push("<span  style='margin-left: 5px;'><a href=\"javascript:showTask(" + rowdata.id + ",'1');\">【查看任务】</a></span>");					
									}else {
										html.push(rowdata.requirement+"<br>");
									    html.push("<span  style='margin-left: 5px;'><a href=\"javascript:showTask(" + rowdata.id + ",'1');\">【查看任务】</a></span>");	
									}
								}
								return html.join("");
							}},
							{display:'付款金额(元)',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								var html=[];
								if (rowdata.payAmount!=null && rowdata.payAmount!='') {
	                        		html.push(rowdata.payAmount);
								}
								return html.join("");
	                        }},
	                        {display:'付款状态',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
							    var html=[];
							    if(rowdata.designTaskRefundOrderStatus != null) {
									if(rowdata.designTaskRefundOrderStatus == '2') {
										html.push("退款成功");
									}else if(rowdata.designTaskRefundOrderStatus == '3') {
										html.push("退款失败");
									}else {
										html.push("退款中");
									}
								}else {
									html.push(rowdata.payStatusDesc1);			
								}
						         return html.join("");
						    }},
                            {display:'设计进度',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
                            	var html=[];
	                        	if (rowdata.designStatus=='0') {
	                        		html.push("待设计");
								}else if (rowdata.designStatus=='1') {
									html.push("已设计");
								}
	                        	return html.join("");
						    }},
                            {display:'取消状态',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
                            	var html=[];
	                        	if (rowdata.status=='0') {
	                        		html.push("未取消");
								}else if (rowdata.status=='1') {
									html.push("已取消");
								}
	                        	return html.join("");
						    }},
	                        {display:'设计领取人',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
							 if (rowdata.payStatus=='1') {
								if (rowdata.designerStaffName) {
								    html.push(rowdata.designerStaffName);		
								}
								<c:if test="${type=='1'}">
								if(${roleId=='26'} && rowdata.designerStaffName==null && rowdata.designTaskRefundOrderStatus<2) {
									html.push("<span  style='margin-left: 5px;'><a href=\"javascript:getDesigner(" + rowdata.id + ");\">领取</a></span>");
								}
								</c:if>	
								}else {
									 html.push("");
							  }
							    return html.join("");
							}},
							<c:if test="${type=='1'}">
							{display:'操作',name:'', align:'center', isSort:false, width:150, render: function(rowdata, rowindex) {
								var html=[];		
								if (rowdata.uploadCount!='2') {
								 if (rowdata.designer!=null && rowdata.designer!='') {
									if (rowdata.payStatus=='1' && rowdata.designStatus=='0' && rowdata.designer==${sessionScope.USER_SESSION.staffID}) {
										html.push("<span  style='margin-left: 5px;'><a href=\"javascript:showTask(" + rowdata.id + ",'2');\">上传图片</a></span>");
									}else if (rowdata.payStatus=='1' && rowdata.designStatus=='1' && rowdata.designer==${sessionScope.USER_SESSION.staffID}) {
										html.push("<span  style='margin-left: 5px;'><a href=\"javascript:showTask(" + rowdata.id + ",'3');\">重新上传</a></span>");
									}
								 }
									
								}else if (rowdata.uploadCount=='2') {
									html.push("");
								}
								return html.join("");
							}},
							</c:if>
							<c:if test="${type=='2'}">
							{display:'操作',name:'', align:'center', isSort:false, width:150, render: function(rowdata, rowindex) {
								var html=[];
								if (rowdata.payStatus=='1' && rowdata.refundCreateBy==null ) {
									 if (rowdata.designStatus=='0') {
								       html.push("<span  style='margin-left: 5px;'><a href=\"javascript:reFund(" + rowdata.id + ");\">退款</a></span>");				
									 }else {
										 html.push("");
									}
								}else if (rowdata.payStatus=='0' && rowdata.refundCreateBy==null) {
									 html.push("");
								}else {
									html.push(rowdata.refundCreateBy+"<br>");
									if(rowdata.refundCreateDate != null && rowdata.refundCreateDate != '' ) {
										var refundCreateDate = new Date(rowdata.refundCreateDate);
											html.push(refundCreateDate.format("yyyy-MM-dd hh:mm"));
									}
								}
								return html.join("");
							}},
							</c:if>
							{display:'任务评价',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html=[];
							 if (rowdata.commentType!=null && rowdata.commentType!='') {
								if (rowdata.commentType=='1') {
									html.push("<span style='color:red;'>好评</span>");
								}else if (rowdata.commentType=='2') {
									html.push("<span style='color:red;'>中评</span>");
								}else {
									html.push("<span style='color:red;'>差评</span>");
								}
								if (rowdata.comment!=null && rowdata.comment!='') {
									html.push("<br>");
								    html.push(rowdata.comment);
								}
									
							 }
								return html.join("");
							}},
	                       
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  >
			   <div class="search-td">
					<div class="search-td-label" >订单编号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="orderCode" name="orderCode" >
					</div>
			  </div>
			  <div class="search-td">
					<div class="search-td-label"  >类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="taskType" name="taskType" style="width: 135px;" >
							<option value="">请选择...</option>
						    <option value="1">品牌特卖</option>
						    <option value="2">店铺装修</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >设计领取人：</div>
					<div class="search-td-combobox-condition" >
						<select id="designer" name="designer" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">未领取</option>
						   <c:forEach var="getDesignerList" items="${getDesignerList}"> 
						     <option value="${getDesignerList.designer}" >${getDesignerList.staff_name}</option>
					       </c:forEach>
						</select>
				 	 </div>
				</div> 
				<div class="search-td">
					<div class="search-td-label"  >设计进度：</div>
					<div class="search-td-combobox-condition" >
						<select id="designStatus" name="designStatus" style="width: 135px;" >
							<option value="">请选择...</option>
						    <option value="0">待设计</option>
						    <option value="1">已设计</option>
						</select>
				 	 </div>
				</div> 
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >任务名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="taskName" name="taskName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >付款状态：</div>
					<div class="search-td-combobox-condition" >
					    <select id="payStatus" name="payStatus" style="width: 135px; " >
							<option value="">请选择...</option>
							<option value="0">未付款</option>
							<option value="1" selected="selected">已付款</option>
							<option value="2">退款中</option>
							<option value="3">退款成功</option>
							<option value="4">退款失败</option>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >任务评价：</div>
					<div class="search-td-combobox-condition" >
						<select id="commentType" name="commentType" style="width: 135px; " >
							<option value="">请选择...</option>
							<option value="1">好评</option>
						    <option value="2">中评</option>
						    <option value="3">差评</option>
						</select>
				 	</div>
				</div>
			  <div class="search-td">
				<input type="checkbox" id = "status" name="status" >
				<div class="search-td-condition" >
					显示已取消任务
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
