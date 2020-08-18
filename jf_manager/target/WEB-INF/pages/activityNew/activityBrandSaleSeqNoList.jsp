<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 
 <script type="text/javascript">
 	 var viewerPictures;
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
		
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
	 
	 //图片查看
	 function viewerPic(entryPic){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+entryPic+'" src="${pageContext.request.contextPath}/file_servelt'+entryPic+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
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
	 
	 //查看商品
	 function showActivityProduct(statusPage, activityId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看商品",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityProductNew/activityProductManager.shtml?statusPage="+statusPage+"&activityId="+activityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //排序值
	 function updateSeqNo(activityId) {
		$("#ok" + activityId).hide();
		var seqNoStr = $("#seqNo" + activityId).attr("seqNoStr");
		var seqNo = $("#seqNo" + activityId).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/activityNew/updateSeqNo.shtml",
				 data : {activityId : activityId, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.returnCode != 200)
						 commUtil.alertError(data.returnMsg);
					 else{
						 $("#ok" + activityId).show();
						 $("#seqNo" + activityId).attr("seqNoStr", seqNo);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			commUtil.alertError("请输入正整数");
			$("#seqNo" + activityId).val(seqNoStr);
		}
	 }
	 
	 //推荐到首页
	 function addAdInfo(activityId) {
		var seqNo = $("#ad" + activityId).val();
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/activityNew/addAdInfo.shtml",
			 data : {activityId : activityId},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.returnCode != 200)
					 commUtil.alertError(data.returnMsg);
				 else{
					 $("#ad" + activityId).html("已推荐首页");
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
	 
 	 var listConfig={
	      url:"/activityNew/activityBrandSaleAuditList.shtml?statusAudit=3",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'特卖ID',name:'id', align:'center', isSort:false, width:80},
							{display:'分类排序值',name:'seqNo', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								var html = [];
								var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
								html.push("<input type='text' style='width: 80px;' seqNoStr='" + seqNo + "' id='seqNo" + rowdata.id + "' name='seqNo' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >");
								html.push("<span id='ok" + rowdata.id + "' style='color: #009999; display: none;'>OK</span><br/>");
	                        	return html.join("");
	                        }},
	                        {display:'入口图',name:'entryPic', align:'center', isSort:false, width:220, render:function(rowdata, rowindex) {
	                        	return "<img style='width:200px; height:100px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.entryPic+"' onclick='viewerPic(\""+rowdata.entryPic+"\")' >";
	                        }},
							{display:'活动名称',name:'name', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.name != '') {
									html.push("<span>"+rowdata.name+"</span><br/>");
								}
								html.push("<span>销售额："+rowdata.payAmountSum+"元</span><br/>");
								if('${isCwOrgStatus}' == '0') {
									if(rowdata.adInfoId == null) {
										html.push("<span id='ad" + rowdata.id + "' ><a href=\"javascript:addAdInfo(" + rowdata.id + ");\">推荐到首页</a></span>");
									}else {
										html.push("<span id='ad" + rowdata.id + "' >已推荐首页</span>");
									}
								}else {
									if(rowdata.adInfoId != null) {
										html.push("<span id='ad" + rowdata.id + "' >已推荐首页</span>");
									}
								}
	                        	return html.join("");
	                        }},
							{display:'品牌团',name:'', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.activityBrandGroupName != null ) {
									html.push(rowdata.activityBrandGroupName);
								}else {
									html.push("未加入");
								}
	                        	return html.join("");
	                        }},
	                        {display:'利益点',name:'benefitPoint', align:'center', isSort:false, width:160},
	                        {display:'促销方式',name:'preferentialTypeDesc', align:'center', isSort:false, width:100},
	                        {display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	var html = [];
 	                        	if(rowdata.mchtGradeDesc) {
				                    html.push("<span style='color:red;margin-right:5px;'>"+rowdata.mchtGradeDesc+"</span>"); 
 	                        	}
			                    html.push(rowdata.shopName); 
			                    return html.join("");
	                        }},
	                        {display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:100},
	                        {display:'通过商品',name:'sumCooPass', align:'center', isSort:false, width:80},
	                        {display:'库存',name:'sumCooProductItem', align:'center', isSort:false, width:80},
	                        {display:'操作',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:cooAuditActivity(" + rowdata.id + ", '1', '2');\">【查看活动】</a>");
								html.push("<a href=\"javascript:showActivityProduct('', " + rowdata.id + ");\">【查看商品】</a>");
							    return html.join("");
							}},
	                        {display:'活动开始时间',name:'activityBeginTime', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityBeginTime == null || rowdata.activityBeginTime == '' ) {
									return "";
								}else{
									var activityBeginTime = new Date(rowdata.activityBeginTime);
									return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},
	                        {display:'活动结束时间',name:'activityEndTime', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityEndTime == null || rowdata.activityEndTime == '' ) {
									return "";
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
					<div class="search-td-label" style="color: red;" >查看日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="seeDateTime" name="seeDateTime" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >一级类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" onchange="productTypelist();" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" onchange="productTypelist();" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
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
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productBrandName" name="productBrandName"  />
					</div>
				</div>
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
								<option value="${activityGroup.id }" <c:if test="${activityGroup.id == activityGroupId }">selected</c:if> >
									${activityGroup.id }.${activityGroup.groupName }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
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
				  	<div class="search-td-label" style="color: red">对接人：</div>
				    <div class="search-td-combobox-condition" >
						<select id="platformContactId" name="platformContactId" style="width: 135px;" >
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
				<div class="search-td">
				  	<div class="search-td-label" >品牌团：</div>
				    <div class="search-td-combobox-condition" >
						<select id="activityBrandGroupId" name="activityBrandGroupId" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="A">全部品牌团</option>
							<option value="B">未加入</option>
							<c:forEach items="${activityBrandGroupList}" var="activityBrandGroup">
								<option value="${activityBrandGroup.id}">${activityBrandGroup.name}</option>
							</c:forEach>			
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
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
