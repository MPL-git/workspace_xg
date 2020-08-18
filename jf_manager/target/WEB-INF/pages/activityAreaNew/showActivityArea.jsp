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
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	margin-left: 20px;
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
	var entryPic;
	var pic;
	$(function(){
		entryPic = new Viewer(document.getElementById('entryPic'), {});
		pic = new Viewer(document.getElementById('pic'), {});
		
		if('${activityArea}' != '' && '${activityArea.productTypeGroup}' != '') {
			var productTypeGroups = '${activityArea.productTypeGroup}'.split(",");
			for(var i=0;i<productTypeGroups.length;i++){    
				$("#productTypeGroup"+productTypeGroups[i]).attr("checked", "checked");       
			}
		}
		
	});

    //判断字符是否为空
    function isEmpty(obj){
        return (typeof obj === 'undefined' || obj === null || obj === "");
    }

    //设置预售规则
    function setAreaActivityPreSellRule(type){
        var activityPriceLimit=$("#activityPriceLimit").val();
        var depositLimit=$("#depositLimit").val();
        var minRate=$("#minRate").val();
        var maxRate=$("#maxRate").val();
        var offsetMultiple=$("#offsetMultiple").val();
        var html = [];
        //type为1时可修改,0只读
        if(!isEmpty(type)){
            if(html.length==0){
                html.push('?type='+type);
            }else{
                html.push('&type='+type);
            }
        }if(!isEmpty(activityPriceLimit)){
            if(html.length==0){
                html.push('?activityPriceLimit='+activityPriceLimit);
            }else{
                html.push('&activityPriceLimit='+activityPriceLimit);
            }
        }
        if(!isEmpty(depositLimit)){
            if(html.length==0){
                html.push('?depositLimit='+depositLimit);
            }else{
                html.push('&depositLimit='+depositLimit);
            }
        }
        if(!isEmpty(minRate)){
            if(html.length==0){
                html.push('?minRate='+minRate);
            }else{
                html.push('&minRate='+minRate);
            }
        }
        if(!isEmpty(maxRate)){
            if(html.length==0){
                html.push('?maxRate='+maxRate);
            }else{
                html.push('&maxRate='+maxRate);
            }
        }
        if(!isEmpty(offsetMultiple)){
            if(html.length==0){
                html.push('?offsetMultiple='+offsetMultiple);
            }else{
                html.push('&offsetMultiple='+offsetMultiple);
            }
        }
        $.ligerDialog.open({
            height: $(window).height()*0.4,
            width: $(window).width()*0.5,
            title: "预售规则",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/activityAreaNew/setAreaActivityPreSellRule.shtml"+html.join(""),
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }
	
</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form1" class="form1" method="post" id="form1" action="" >
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">会场名称</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" id="name" name="name" type="text" value="${activityArea.name }" readonly="readonly" />
				</td>
           	</tr>
           	<c:if test="${activityAreaType == '3' }">
           		<tr>
	            	<td class="title" width="20%">类型</td>
					<td align="left" class="l-table-edit-td">
						<span class="radioClass"><input type="radio" disabled="disabled" name="type" value="3" checked="checked" />推广会场</span>
					</td>
	           	</tr>
           		<tr>
	            	<td class="title" width="20%">类目限制</td>
					<td align="left" class="l-table-edit-td">
						<c:forEach items="${productTypeList }" var="productType">
							<span class="radioClass"><input type="checkbox" disabled="disabled" id="productTypeGroup${productType.id }" class="productTypeGroup" value="${productType.id }" />${productType.name }</span>
						</c:forEach>
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${activityAreaType != '3' }">
	           	<tr>
	            	<td class="title" width="20%">类型</td>
					<td align="left" class="l-table-edit-td">
						<span class="radioClass"><input type="radio" disabled="disabled" name="type" value="2" <c:if test="${activityArea.type == '2' }">checked</c:if> />单品会场</span>
						<span class="radioClass"><input type="radio" disabled="disabled" name="type" value="1" <c:if test="${activityArea.type == '1' }">checked</c:if> />品牌会场</span>
					</td>
	           	</tr>
	        </c:if>
			<tr>
				<td class="title" width="20%">是否预付会场<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" name="isPreSell" disabled="disabled" value="0" <c:if test="${empty activityArea || activityArea.isPreSell == '0' }">checked</c:if> validate="{required:true}" />否</span>
					<span class="radioClass"><input type="radio" name="isPreSell" disabled="disabled" value="1" <c:if test="${activityArea.isPreSell == '1' }">checked</c:if> validate="{required:true}" />是</span>
					<a id="setPreSellRule" onclick="setAreaActivityPreSellRule(0);" style="color:blue;">【设置预售规则】</a>
					<input type="hidden" id="activityPriceLimit" name="activityPriceLimit" value="${activityAreaPreSellRule.activityPriceLimit }"/>
					<input type="hidden" id="depositLimit" name="depositLimit" value="${activityAreaPreSellRule.depositLimit }"/>
					<input type="hidden" id="minRate" name="minRate" value="${activityAreaPreSellRule.minRate }"/>
					<input type="hidden" id="maxRate" name="maxRate" value="${activityAreaPreSellRule.maxRate }"/>
					<input type="hidden" id="offsetMultiple" name="offsetMultiple" value="${activityAreaPreSellRule.offsetMultiple }"/>
				</td>
			</tr>
			<tr>
				<td class="title" width="20%">分享得积分<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" disabled="disabled" name="shareIntegralType" value="0" <c:if test="${empty activityArea || activityArea.shareIntegralType == '0' }">checked</c:if> validate="{required:true}" />无积分</span>
					<span class="radioClass"><input type="radio" disabled="disabled" name="shareIntegralType" value="1" <c:if test="${activityArea.shareIntegralType == '1' }">checked</c:if> validate="{required:true}" />首次得积分</span>
					<span class="radioClass"><input type="radio" disabled="disabled" name="shareIntegralType" value="2" <c:if test="${activityArea.shareIntegralType == '2' }">checked</c:if> validate="{required:true}" />每日首次分享得积分</span>
					<input type="text" readonly="readonly" style="width: 50px;" id="minShareIntegral" name="minShareIntegral" <c:if test="${activityArea.minShareIntegral gt 0}">value="${activityArea.minShareIntegral}"</c:if>>~
					<input type="text" readonly="readonly" style="width: 50px;" id="maxShareIntegral" name="maxShareIntegral" <c:if test="${activityArea.maxShareIntegral gt 0}">value="${activityArea.maxShareIntegral}"</c:if>>
				</td>
			</tr>
	        <tr>
	           	<td class="title" width="20%">会场限购<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" name="purchaseLimits" value="0" <c:if test="${empty activityArea || activityArea.purchaseLimits == '0' }">checked</c:if> validate="{required:true}" />不限购</span>
					<span class="radioClass"><input type="radio" name="purchaseLimits" value="1" <c:if test="${activityArea.purchaseLimits == '1' }">checked</c:if> validate="{required:true}" />限购<input type="text" style="width: 50px;" id="purchaseLimitsQuantity" name="purchaseLimitsQuantity" <c:if test="${activityArea.purchaseLimitsQuantity gt 0}">value="${activityArea.purchaseLimitsQuantity}"</c:if>>件</span>
				</td>
		    </tr>
	        <tr>
				<td class="title">活动描述和报名要求</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="5" cols="100" id="description" name="description" readonly="readonly" >${activityArea.description }</textarea>
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">利益点</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" type="text" id="benefitPoint" name="benefitPoint" readonly="readonly" value="${activityArea.benefitPoint }" />
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">报名时间</td>
				<td align="left" class="l-table-edit-td table-edit-activity-time">
					<div><input type="text" class="dateEditor" id="enrollBeginTimeStr" name="enrollBeginTimeStr" readonly="readonly" value="<fmt:formatDate value="${activityArea.enrollBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
					<span>到</span>
					<div><input type="text" class="dateEditor" id="enrollEndTimeStr" name="enrollEndTimeStr" readonly="readonly" value="<fmt:formatDate value="${activityArea.enrollEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" ></div>
				</td>
           	</tr>
           	<tr height="100px;">
            	<td class="title" width="20%">报名图</td>
				<td align="left" class="l-table-edit-td">
					<div style="width: 80px;height: 80px;border: 1px solid #6B6B6B;">
						<ul class="docs-pictures clearfix td-pictures" id="pic">
							<li><img src="${pageContext.request.contextPath}/file_servelt${activityArea.pic}" id="picImg" style="width: 80px;height: 80px;display: block;"></li>
						</ul>	
					</div>
				</td>
           	</tr>
           	<tr height="100px;">
            	<td class="title" width="20%">APP入口图</td>
				<td align="left" class="l-table-edit-td">
					<div style="width: 200px;height: 100px;border: 1px solid #6B6B6B;">
						<ul class="docs-pictures clearfix td-pictures" id="entryPic">
							<li><img src="${pageContext.request.contextPath}/file_servelt${activityArea.entryPic}" id="entryPicImg" style="width: 200px;height: 100px;display: block;"></li>
						</ul>
					</div>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">限商家报名额</td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" id="limitMchtNum" name="limitMchtNum" value="${activityArea.limitMchtNum }" readonly="readonly" />
				</td>
           	</tr>
           	<tr>
			<td class="title">推送商家</td>
				<td align="left" class="l-table-edit-td">
					<c:forEach items="${pushMchtTypeList }" var="pushMchtType" >
						<span style="margin-right: 20px;">
							<input type="radio" id="pushMchtType" disabled="disabled" name="pushMchtType" value="${pushMchtType.statusValue }" <c:if test="${activityArea.pushMchtType == pushMchtType.statusValue }">checked</c:if> />${pushMchtType.statusDesc }
						</span>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="title">追加指定商家序号</td>
				<td align="left" class="l-table-edit-td">
					<textarea id="mchtIdGroups" rows="5" readonly="readonly" class="textarea" cols="100">${mchtIdGroupCode }</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">会员限制</td>
				<td align="left" class="l-table-edit-td">
					<select style="width: 135px;" id="minMemberGroup" name="minMemberGroup" disabled="disabled" >
						<c:forEach items="${memberGroupList }" var="group">
							<option value="${group.id}" <c:if test="${activityArea.minMemberGroup eq group.id }">selected</c:if> >${group.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<c:if test="${activityAreaType != '3' }">
				<tr>
					<td class="title">分组</td>
					<td align="left" class="l-table-edit-td">
						<select id="activityGroupId" name="activityGroupId" style="width: 135px;" disabled="disabled" >
							<option value="">请选择...</option>
							<c:forEach var="activityGroup" items="${activityGroupList }">
								<option value="${activityGroup.id }" <c:if test="${activityArea.activityGroupId eq activityGroup.id }">selected</c:if> >
									${activityGroup.id }.${activityGroup.groupName }
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>