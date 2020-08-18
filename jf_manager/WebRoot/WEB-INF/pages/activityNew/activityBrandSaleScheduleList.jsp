<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/specialSale_Search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
		//品牌
		$("#productBrandName").ligerComboBox({
	     	 width: 135,
	         slide: false,
	         selectBoxWidth: 450,
	         selectBoxHeight: 300,
	         valueField: 'id',
	         textField: 'name',
	         valueFieldID:'productBrandId',
	         grid: getGridOptions(false),
	         condition:{ fields: [{ name:'name', label:'品牌名', width:90, type:'text' } ]}
	    });
		
		productTypelist();


		 $("#batchSchedule").bind('click',function(){
			 var data = listModel.gridManager.getSelectedRows();
			 console.log(data)
			 if (data.length == 0){
				 $.ligerDialog.alert('请选择行！');
			 }else{
				 var str = "";
				 $(data).each(function (){
					 if(str==''){
						 str = this.id ;
						 console.log(str)
					 }else{
						 str += ","+ this.id;
					 }
					 console.log(str)
				 });
				 $.ligerDialog.open({
					 height: $(window).height()*0.55,
					 width: $(window).width()*0.3,
					 title: "批量排期",
					 name: "INSERT_WINDOW",
					 url: "${pageContext.request.contextPath}/activityNew/batchSchedule.shtml?ids="+str,
					 showMax: true,
					 showToggle: false,
					 showMin: false,
					 isResize: true,
					 slide: false,
					 data: null
				 });
			 }
		 });
		
	 });
	 
	 function getGridOptions(checkbox){
	     var options = {
	         columns: [
				{display:'ID',name:'id', align:'center', isSort:false, width:100},
				{display:'品牌',name:'name', align:'center', isSort:false, width:100}
	         ], 
	         switchPageSizeApplyComboBox: false,
		     url: '${pageContext.request.contextPath}/activityNew/getProductBrandList.shtml',
	         pageSize: 10, 
	         checkbox: checkbox
	     };
	     return options;
	 }
	 
	 //获取二级类目
	 function productTypelist() {
		 var productTypeId = $("#productTypeId").val();
		 if(productTypeId == '') {
		 	var option = [];
			option.push('<option value="">请选择...</option>');
			$("#productTypeSecondId").html(option.join(''));
			$("#productTypeSecondId").attr("disabled", "disabled");
		 }else {
			$.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/activityNew/productTypelist.shtml',
				dataType : 'json',
				data: {productTypeId:productTypeId},
				success: function(data) {
					if(data.code == 200) {
						var option = [];
						option.push('<option value="">请选择...</option>');
						for(var i=0;i<data.productTypeList.length;i++) {
							option.push('<option value="'+data.productTypeList[i].id+'">'+data.productTypeList[i].name+'</option>');
						}
						$("#productTypeSecondId").html(option.join(''));
						$("#productTypeSecondId").attr("disabled", "");
					}else {
						commUtil.alertError(data.msg);
					}
				},
				error: function(e) {
					commUtil.alertError('操作超时，请稍后再试！');
				}
			});
		}	
	 }
	 
	 //领取
	 function getActivity(activityId, status) {
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/activityNew/getActivity.shtml',
			 data: {activityId : activityId, status : status},
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
	 
	 //查看商品
	 function showActivityProduct(statusPage, activityId,mchtType) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看商品",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityProductNew/activityProductManager.shtml?statusPage="+statusPage+"&activityId="+activityId+"&mchtType="+mchtType,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }

	 //查看活动、排期审核活动
	 function cooAuditActivity(activityId, statusPage, statusAudit) {
		var titleStr 
		if(statusPage == '1') {
			titleStr = "查看活动";
		}else if(statusPage == '2') {
			titleStr = "活动排期审核";
		} 
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: titleStr,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityNew/showOrAuditActivity.shtml?activityId="+activityId+"&statusPage="+statusPage+"&statusAudit="+statusAudit,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 
	 function schedulingStatus(cooAuditStatus,id){
	      if (cooAuditStatus=='2') {
	    	  $("#"+id+"-label").parent("div").html("<label id='"+ id +"-label'>审核通过</label>");
		  }else {
			  $("#"+id+"-label").parent("div").html("<label id='"+ id +"-label'>已驳回</label>"); 
		}
      }
	 
 	 var listConfig={
	      url:"/activityNew/activityBrandSaleAuditList.shtml?statusAudit=1",
 		  btnItems:[],
	      listGrid:{ columns: [
							{display:'特卖ID',name:'id', align:'center', isSort:false, width:80},
							// {display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:100},
				            { display: '商家序号',width: 100, render: function(rowdata, rowindex) {
								  var html = [];
								  html.push(rowdata.mchtCode);
								  html.push("<br>");
								  if(rowdata.mchtType == "2"){
									  html.push("POP");
								  }else if (rowdata.mchtType == "1" && rowdata.isManageSelf == "1"){
									  html.push("自营SPOP");
								  }else {
									  html.push("SPOP");
								  }
								  return html.join("");
						    }},
							{display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	var html = [];
 	                        	if(rowdata.mchtGradeDesc) {
				                    html.push("<span style='color:red;margin-right:5px;'>"+rowdata.mchtGradeDesc+"</span>"); 
 	                        	}
			                    html.push(rowdata.shopName); 
			                    return html.join("");
	                        }},
	                        {display:'品牌',name:'productBrandName', align:'center', isSort:false, width:120},
							{display:'活动名称',name:'name', align:'center', isSort:false, width:180},
	                        {display:'利益点',name:'benefitPoint', align:'center', isSort:false, width:160},
	                        {display:'促销方式',name:'preferentialTypeDesc', align:'center', isSort:false, width:100},
	                        {display:'待审商品',name:'sumCooAudit', align:'center', isSort:false, width:80},
	                        {display:'通过商品',name:'sumCooPass', align:'center', isSort:false, width:80},
	                        {display:'库存',name:'sumCooProductItem', align:'center', isSort:false, width:80},
	                        {display:'期望时间',name:'expectedStartTime', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	                        	if(rowdata.expectedStartTime == null || rowdata.expectedStartTime == "" ) {
									return "";
								}else{
									var expectedStartTime = new Date(rowdata.expectedStartTime);
									return expectedStartTime.format("yyyy-MM-dd");
								}
	                        }},
	                        {display:'审核',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.operateAuditStatus == '2' && rowdata.cooAuditStatus != '3' && rowdata.cooAuditBy != null ) {
									html.push("<a href=\"javascript:cooAuditActivity(" + rowdata.id + ", '2', '2');\">【审核活动】</a>");
								}else {
									html.push("<a href=\"javascript:cooAuditActivity(" + rowdata.id + ", '1', '2');\">【查看活动】</a>");
								}
								html.push("<a href=\"javascript:showActivityProduct(2, " + rowdata.id + ","+rowdata.mchtType+");\">【查看商品】</a>");
							    return html.join("");
							}},
	                        {display:'排期审核人',name:'cooAuditName', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.cooAuditBy != null ) {
									html.push(rowdata.cooAuditName);
								}else {
									html.push("<a href=\"javascript:getActivity(" + rowdata.id + ", '2');\">领取</a></br>");
								}
							    return html.join("");
							}},
							{display:'排期审核状态',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
								var html = [];
							    if (rowdata.cooAuditStatusDesc=='未审核') {
									html.push("<label id='"+ rowdata.id+"-label'>未审核</label>");
								}else if (rowdata.cooAuditStatusDesc=='待审核') {
									html.push("<label id='"+ rowdata.id+"-label'>待审核</label>");
								}else if (rowdata.cooAuditStatusDesc=='审核通过') {
									html.push("<label id='"+ rowdata.id+"-label'>审核通过</label>");
								}else if (rowdata.cooAuditStatusDesc=='已驳回') {
									html.push("<label id='"+ rowdata.id+"-label'>已驳回</label>");
								}
							    return html.join("");
							}},
							{display:'活动状态',name:'activityStatusDesc', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status != '6') {
									html.push(rowdata.activityStatusDesc);
								}else if(rowdata.status == '6') {
									if(new Date(rowdata.preheatTime) > new Date()) {
										html.push("待开始");
									}else if(new Date(rowdata.preheatTime) <= new Date() && new Date(rowdata.activityBeginTime) > new Date()) {
										html.push("预热中");
									}else if(new Date(rowdata.activityBeginTime) <= new Date() && new Date(rowdata.activityEndTime) >= new Date()) {
										html.push("活动中");
									}else if(new Date(rowdata.activityEndTime) < new Date()) {
										html.push("已结束");
									}
								}
							    return html.join("");
							}},
							{display:'活动开始时间',name:'activityBeginTime', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityBeginTime == null || rowdata.activityBeginTime == '' ) {
									return '';
								}else{
									var activityBeginTime = new Date(rowdata.activityBeginTime);
									return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},
							{display:'活动结束时间',name:'activityEndTime', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityEndTime == null || rowdata.activityEndTime == '' ) {
									return '';
								}else{
									var activityEndTime = new Date(rowdata.activityEndTime);
									return activityEndTime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},
	                        {display:'特卖分组',name:'activityGroupId', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityGroupId == null || rowdata.activityGroupId == '' ) {
									return '';
								}else{
									return rowdata.activityGroupId +"."+ rowdata.groupName;
								}
	                        }}
			         ], 
	                 showCheckbox :true,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };

	 //点击搜索当不是待审核时，隐藏全部通过按钮
	 function showBatch() {
		 debugger;
		 var status = $("#status").val();
		 var cooAuditStatus = $("#cooAuditStatus").val();
		 var activityEndTime = $("#activityEndTime").val();
		 if (status=="13"&&cooAuditStatus=="2"&& activityEndTime!=""){
			 $("#batchSchedule").show();
		 }else {
			 $("#batchSchedule").hide();
		 }
	 }
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >特卖ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityId" name="activityId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >特卖名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >促销方式：</div>
					<div class="search-td-combobox-condition" >
						<select id="preferentialType" name="preferentialType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="preferentialType" items="${preferentialTypeList }">
								<c:if test="${preferentialType.statusValue != '0' }">
									<option value="${preferentialType.statusValue }">
										${preferentialType.statusDesc }
									</option>
								</c:if>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>

				<div class="search-td" style="color: red">
					<div class="search-td-label"  >合作类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="mchtType" name="mchtType" style="width: 135px;" >
								<option value="">请选择...</option>
								<option value="1">SPOP</option>
								<option value="2">POP</option>
						</select>
					</div>
				</div>

			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">全部</option>
							<c:forEach var="status" items="${statusList }">
								<option value="${status.value }" <c:if test="${status.value == '3' }">selected</c:if> >
									${status.name }
								</option>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >排期审核人：</div>
					<div class="search-td-combobox-condition" >
						<select id="cooAuditBy" name="cooAuditBy" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sysStaffInfo" items="${sysStaffInfoList }">
								<option value="${sysStaffInfo.staffId }">
									${sysStaffInfo.staffName }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red;" >排期审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="cooAuditStatus" name="cooAuditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="cooAuditStatus" items="${cooAuditStatusList }">
								<option value="${cooAuditStatus.statusValue }">
									${cooAuditStatus.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>

				<div class="search-td">
					<div class="search-td-label" style="float:left; color: red">是否自营：</div>
					<div class="search-td-condition" >
						<select id="isManageSelf" name="isManageSelf" style="width: 150px;">
							<option value="">请选择</option>
							<option value="0">非自营</option>
							<option value="1">自营</option>
						</select>
					</div>
				</div>

			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >一级类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" onchange="productTypelist();" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">
										${productType.name }
									</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" onchange="productTypelist();" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">
										${productType.name }
									</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >二级类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeSecondId" name="productTypeSecondId" style="width: 135px;" >
							<option value="">请选择...</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productBrandName" name="productBrandName"  />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >期望日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="expectedStartTime" name="expectedStartTime" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >活动开始日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="activityBeginTime" name="activityBeginTime" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动结束日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="activityEndTime" name="activityEndTime" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >分组：</div>
					<div class="search-td-combobox-condition" >
						<select id="activityGroupId" name="activityGroupId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="activityGroup" items="${activityGroupList }">
								<option value="${activityGroup.id }">
									${activityGroup.id }.${activityGroup.groupName }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >设计审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="designAuditStatus" name="designAuditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="designAuditStatus" items="${designAuditStatusList }">
								<c:if test="${designAuditStatus.statusValue != '0' }">
									<option value="${designAuditStatus.statusValue }" <c:if test="${designAuditStatus.statusValue == '2' }">selected</c:if> >
										${designAuditStatus.statusDesc }
									</option>
								</c:if>
							</c:forEach>
						</select>
				 	 </div>
				</div>
			<div class="search-td">
			  <div class="search-td-label" style="color: red">对接人：</div>
			    <div class="search-td-condition" >
				<select id="platformContactId" name="platformContactId" >
					<c:if test="${isContact==1}">
						<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
							<option value="">全部商家</option>
						</c:if>
						<option value="${myContactId}">我对接的商家</option>
						<c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
							<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
						</c:forEach>
					</c:if>
					
					<c:if test="${isContact==0}">
					<option value="">全部商家</option>
					<c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
						<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
					</c:forEach>			
					</c:if>
				</select>
			</div>
			</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" onclick="showBatch()" >搜索</div>
				</div>
				<div class="search-td-export-btn">
					<input type="button" style="width: 60px;height: 25px;cursor: pointer;display: none" value="批量排期" id="batchSchedule">
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
