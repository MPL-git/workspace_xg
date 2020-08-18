<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	 function viewerPic(img, status, activityId){
		var entryPic = '';
		if(status == '1') {
			entryPic = $("#groupImg_"+activityId).attr("entryPicStr");
		}else if(status == '2') {
			entryPic = img;
		}
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+entryPic+'?tempid='+Math.random()+'" src="${pageContext.request.contextPath}/file_servelt'+entryPic+'?tempid='+Math.random()+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
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
	 
	 //查看活动
	 function showActivity(activityId, statusPage) {
		 $.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看活动",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityNew/showOrAuditActivity.shtml?activityId="+activityId+"&statusPage="+statusPage,
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

	 //设计审核活动
	 function designAuditActivity(activityId, statusPage, statusAudit) {
		var titleStr = "查看活动";
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
	 
	 //加标   还原
	 function addIsSign(activityId, isSign) {
		if(isSign == '0') {
			isSign = '1';
		}else {
			isSign = '0';
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/updateActivityGroupId.shtml',
			data: {activityId : activityId, isSign : isSign},
			dataType: 'json',
			success: function(data) {
				if(data.code != '200') {
					commUtil.alertError(data.msg);
				}else {
					var strGroupName = '';
					var strHref = '';
					if(data.isSignStr == '0') {   
						strGroupName = '【加标】';
						strHref = 'javascript:addIsSign('+activityId+', 0);';
					}else {
						strGroupName = '【还原】';
						strHref = 'javascript:addIsSign('+activityId+', 1);';
					}
					$("#group_"+activityId).html(strGroupName);
					$("#group_"+activityId).attr("href", strHref);
					$("#groupImg_"+activityId).attr("src", "${pageContext.request.contextPath}/file_servelt"+data.entryPicStr+"?tempid="+Math.random());
					$("#groupImg_"+activityId).attr("entryPicStr", data.entryPicStr);
				}
			},
			error: function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	 }
	 
	 //加标   还原（后期扩展）
	 function addIsSignCustom(activityId, isSign) {
		if(isSign == '0') {
			isSign = '1';
		}else {
			isSign = '0';
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/updateActivityGroupIdCustom.shtml',
			data: {activityId : activityId, isSign : isSign},
			dataType: 'json',
			success: function(data) {
				if(data.code != '200') {
					commUtil.alertError(data.msg);
				}else {
					var strGroupName = '';
					var strHref = '';
					if(data.isSignStr == '0') {   
						strGroupName = '【加标】';
						strHref = 'javascript:addIsSignCustom('+activityId+', 0);';
					}else {
						strGroupName = '【还原】';
						strHref = 'javascript:addIsSignCustom('+activityId+', 1);';
					}
					$("#group_"+activityId).html(strGroupName);
					$("#group_"+activityId).attr("href", strHref);
					$("#groupImg_"+activityId).attr("src", "${pageContext.request.contextPath}/file_servelt"+data.entryPicStr+"?tempid="+Math.random());
					$("#groupImg_"+activityId).attr("entryPicStr", data.entryPicStr);
				}
			},
			error: function(e) {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	 }
	 
	 //设计审核状态
	 function updateDesignAuditStatus(activityId, designAuditStatus) {
		 console.log(activityId);
		 if(designAuditStatus == '2') { //通过
			 $.ajax({
				 type: 'post',
				 url: '${pageContext.request.contextPath }/activityNew/auditDesignPass.shtml',
				 data: {activityId : activityId, designAuditStatus : designAuditStatus},
				 dataType: 'json',
				 success: function(data) {
					 if(data == null || data.statusCode != 200) {
					     commUtil.alertError(data.message);
					 }else {
						 $("#searchbtn").click();
					 }
				 },
				 error: function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		 }else if(designAuditStatus == '3') { //驳回
			var titleStr = "设计驳回";
			$.ligerDialog.open({
				height: $(window).height() - 100,
				width: $(window).width() - 400,
				title: titleStr,
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/activityNew/auditDesignReject.shtml?activityId="+activityId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		 }
	 }
	 
 	 var listConfig={
	      url:"/activityNew/activityAuditList.shtml?statusAudit=1",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'特卖ID',name:'id', align:'center', isSort:false, width:80},
							{display:'品牌团入口图/入口图 / 海报图',name:'pic', align:'center', isSort:false, width:520, render:function(rowdata, rowindex) {
								var html = [];
								html.push("<img brandTeamPicStr='"+rowdata.brandteamPic+"' style='width:250px; height:78px; margin: 10 5 5 5;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.brandteamPic+"?tempid="+Math.random()+"' onclick='viewerPic(\""+rowdata.brandteamPic+"\", \"2\", "+ rowdata.id +")' >");
								html.push("<br/>");
								html.push("<img id='groupImg_"+rowdata.id+"' entryPicStr='"+rowdata.entryPic+"' style='width:250px; height:125px; margin: 10 5 5 5;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.entryPic+"?tempid="+Math.random()+"' onclick='viewerPic(\"\", \"1\", "+ rowdata.id +")' >");
								html.push("<br/>");
								html.push("<img style='width:250px; height:125px; margin: 5 5 10 5;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.posterPic+"?tempid="+Math.random()+"' onclick='viewerPic(\""+rowdata.posterPic+"\", \"2\", "+ rowdata.id +")' >");
	                        	return html.join("");
	                        }},
							{display:'活动名称 / 促销方式 / 利益点',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.source == '1') {
									html.push("【会场ID："+rowdata.activityAreaId+"】<br/>");
								}else {
									html.push("【特卖】<br/>");
								}
								html.push("<span style='color :blue;'>"+rowdata.name+"</span><br/>【"+rowdata.preferentialTypeDesc+"】<br/>");
								if(rowdata.remarks != '') {
									html.push("<span style='color :red;'>"+rowdata.remarks+"</span><br/>");
								}
								if(rowdata.brandLimitType == '2') {
									html.push("【联合品牌】<br/>");
								}else {
									html.push("【品牌专场】<br/>");
								}
								html.push("<span style='color :#9900FF;'>"+rowdata.productBrandName+"</span>");
								return html.join("");
	                        }},
	                        {display:'查看详情',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								html.push(rowdata.mchtCode+"<br/>");
								html.push(rowdata.shopName+"<br/>");
								html.push("<a href=\"javascript:designAuditActivity(" + rowdata.id + ", '3', '3');\">【查看活动】</a><br/>");
								html.push("<a href=\"javascript:showActivityProduct('', " + rowdata.id + ");\">【查看商品】</a>");
							    return html.join("");
							}},
							{display:'设计审核人',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.designAuditBy != null ) {
									html.push(rowdata.designAuditName);
								}else {
									html.push("<a href=\"javascript:getActivity(" + rowdata.id + ", '3');\">领取</a></br>");
								}
							    return html.join("");
							}},
							{display:'设计审核状态',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.designAuditBy == null ) {
									html.push(rowdata.designAuditStatusDesc);
								}else {
									if(rowdata.designAuditStatus != '2' && rowdata.designAuditStatus != '3') {
										html.push(rowdata.designAuditStatusDesc+"</br>");
										html.push("<a href=\"javascript:updateDesignAuditStatus(" + rowdata.id + ", '2');\">【通过】</a></br>");
										html.push("or</br>");
										html.push("<a href=\"javascript:updateDesignAuditStatus(" + rowdata.id + ", '3');\">【驳回】</a>");
									}else {
										html.push(rowdata.designAuditStatusDesc);
									}
								}
							    return html.join("");
							}},
							{display:'分组 / 加标',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.activityAreaActivityGroupId == null || rowdata.activityAreaActivityGroupId == '' ) {
									html.push("未分组");
								}else {
									html.push(rowdata.groupName+"<br/>");
									if(rowdata.source == '2') {
										if(rowdata.activityAreaIsSign == '0') {
											html.push("<a id='group_"+rowdata.id+"' href=\"javascript:addIsSign(" + rowdata.id + ", '0');\">【加标】</a>");
										}else {
											html.push("<a id='group_"+rowdata.id+"' href=\"javascript:addIsSign(" + rowdata.id + ", '1');\">【还原】</a>");
										}
									}else if(rowdata.source == '1') { 
										if(rowdata.isSign == '0') {
											html.push("<a id='group_"+rowdata.id+"' href=\"javascript:addIsSignCustom(" + rowdata.id + ", '0');\">【加标】</a>");
										}else {
											html.push("<a id='group_"+rowdata.id+"' href=\"javascript:addIsSignCustom(" + rowdata.id + ", '1');\">【还原】</a>");
										}
									}
								}
								return html.join("");
							}},
							{display:'活动状态 / 时间',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.status != '6') {
									html.push(rowdata.activityStatusDesc+"<br/>");
								}else if(rowdata.status == '6') {
									if(new Date(rowdata.preheatTime) > new Date()) {
										html.push("待开始<br/>");
									}else if(new Date(rowdata.preheatTime) <= new Date() && new Date(rowdata.activityBeginTime) > new Date()) {
										html.push("预热中<br/>");
									}else if(new Date(rowdata.activityBeginTime) <= new Date() && new Date(rowdata.activityEndTime) >= new Date()) {
										html.push("活动中<br/>");
									}else if(new Date(rowdata.activityEndTime) < new Date()) {
										html.push("已结束<br/>");
									}
								}
								if(rowdata.activityBeginTime != null) {
									var activityBeginTime = new Date(rowdata.activityBeginTime);
									html.push(activityBeginTime.format("yyyy-MM-dd hh:mm:ss")+"<br/>");
								}
								if(rowdata.activityBeginTime != null || rowdata.activityEndTime != null) {
									html.push("~<br/>");
								}
								if(rowdata.activityEndTime != null) {
									var activityEndTime = new Date(rowdata.activityEndTime);
									html.push(activityEndTime.format("yyyy-MM-dd hh:mm:ss"));
								}
							    return html.join("");
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
					<div class="search-td-label"  >会场ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityAreaId" name="activityAreaId" >
					</div>
				</div>
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
					<div class="search-td-label"  >活动状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">全部</option>
							<c:forEach var="status" items="${statusList }">
								<c:if test="${status.value != '1' && status.value != '4' && status.value != '14' }">
									<option value="${status.value }">
										${status.name }
									</option>
								</c:if>
							</c:forEach>
						</select>
				 	</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >设计审核人：</div>
					<div class="search-td-combobox-condition" >
						<select id="designAuditBy" name="designAuditBy" style="width: 135px;" >
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
					<div class="search-td-label" style="color: red;" >设计审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="designAuditStatus" name="designAuditStatus" style="width: 135px;" >
							<option value="" selected="selected">请选择...</option>
							<c:forEach var="designAuditStatus" items="${designAuditStatusList }" >
								<option value="${designAuditStatus.statusValue }"
<%--										<c:if test="${designAuditStatus.statusValue == '1' }">selected</c:if> --%>
								>
									${designAuditStatus.statusDesc }
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
					<div class="search-td-label"  >一级类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1  }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" onchange="productTypelist();" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">
										${productType.name }
									</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0  }">
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
					<div class="search-td-label"  >是否已加标：</div>
					<div class="search-td-combobox-condition" >
						<select id="isSign" name="isSign" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">已加标</option>
							<option value="0">未加标</option>
						</select>
				 	 </div>
				</div>
			</div>
		<div class="search-tr"  >
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
