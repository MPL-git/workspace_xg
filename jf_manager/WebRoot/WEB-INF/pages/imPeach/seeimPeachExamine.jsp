<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
      rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
        type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
      rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
      rel="stylesheet" type="text/css"/>

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    td input, td select {
        border: 1px solid #AECAF0;
    }

    .radioClass {
        margin-right: 20px;
    }

    .l-table-edit {

    }

    .l-table-edit-td {
        padding: 4px;
    }

    .l-button-submit, .l-button-test {
        width: 80px;
        float: left;
        margin-left: 10px;
        padding-bottom: 2px;
    }

    .l-verify-tip {
        left: 230px;
        top: 120px;
    }

    .table-title-link {
        color: #1B17EE;
        font-size: 15px;
        text-decoration: none;
    }

    .table-title {
        font-size: 17px;
        font-weight: 600;
    }
</style>
<style type="text/css">
    .middle input {
        display: block;
        width: 30px;
        margin: 2px;
    }

    table.l-checkboxlist-table td {
        border-style: none;
    }

    table.l-listbox-table td {
        border-style: none;
    }

    .td-pictures li {
        display: inline-block;
    }

    td img {
        width: 60px;
        height: 40px;
    }
    /*显示大图*/
	.show-img {
		position: fixed;
		top: 0;
		left: 0;
		z-index: 11;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, .8);
	}
	.show-img img {
		width: auto;
		margin: 50vh auto 0;
		-webkit-transform: translateY(-50%);
		transform: translateY(-50%);
		display:block;
	}
	.blue {
		color: #2476ff;
	}
</style>
<script type="text/javascript">
    $(function(){
    	
    	 $("a[name='downLoadimpeachMemberProof']").bind('click',function(){//下载多个文件
       		 var attachmentPath = $(this).attr("attachmentpath");
       		 var attachmentName = $(this).attr("attachmentname");
       		 $.ajax({
       				type: 'post',
       				url: '${pageContext.request.contextPath}/imPeach/checkFileExists.shtml',
       				data: {"attachmentPath":attachmentPath},
       				dataType: 'json',
       				success: function(data){
       					if(data == null || data.code != 200){
       				    	commUtil.alertError(data.msg);
       				  	}else{
       				  		location.href="${pageContext.request.contextPath}/imPeach/downLoadAttachment.shtml?fileName="+attachmentName+"&filePath="+attachmentPath;
       				  	}
       				},
       				error: function(e){
       				 	commUtil.alertError("系统异常请稍后再试！");
       				}
       			});
       	   }); 
    	 
    	 
    	 
     if (status!='5' && status!='6') {
    	var limitMemberAction=$("#limitMemberAction").val(); 
     	if(limitMemberAction!='' && limitMemberAction!=null){
     		var strs= new Array(); //定义一数组 
     		strs=limitMemberAction.split(","); //字符分割 
     		for (i=0;i<strs.length;i++) 
     		{
     			 var j=strs[i]-1;
     			 $("input[name='limitMemberAction']")[j].checked =true;
     			if (${imPeachType=='6'}) {
       			   $("input[name='endlimitMemberActionS']")[j].checked =true;
     			}
     		} 
     	}
     }
    	  
 });
    
   
    
    function subOrderCustomscuont(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看子订单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
    
    function customerServiceOrderCount(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看售后单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 function interventionOrderCount(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看介入单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 
 function appealOrderCount(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看投诉单",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 
 function memberId(id,state){
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看收货地址",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/imPeach/subOrderListdata.shtml?memberId="+id+"&state=" + state,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});

	}
 
 
 function mchtInfoid(id) {
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
 
 
 function subOrdersId(id) {
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
    

</script>

<html>
<body>
<form method="post" id="form1" name="form1">
 <input type="hidden" id="impeachMemberId" value="${impeachMemberCustom.id}">
 <input type="hidden" id="limitMemberAction" value="${impeachMemberCustom.limitMemberAction}">
 <input type="hidden" id="imPeachType" value="${imPeachType}">
 <div class="title-top">
		<div class="table-title">
			<span>创建者信息</span>
		</div>
		 <br>
	  <c:if test="${impeachMemberCustom.source eq '0'}">
		<table class="gridtable marT10">
			<tr>
				<td class="title" style="width: 80px;">商家序号</td>
				<td class="title" style="width: 80px;">商家名称</td>
				<td class="title" style="width: 80px;">退货率</td>
				<td class="title" style="width: 80px;">换货率</td>
				<td class="title" style="width: 80px;">退款率</td>
				<td class="title" style="width: 80px;">商品好评率</td>
			</tr>		
			<tr>
				<td align="center">${mchtInfoCustom.mchtCode}</td>
				<td align="center"><a href="javascript:mchtInfoid(${mchtInfoCustom.id});">${mchtInfoCustom.companyName}</a></td>
				<c:if test="${not empty mchtStatisticalInfos.returnRate90Days}">
				<td align="center">${mchtStatisticalInfos.returnRate90Days}%</td>
				</c:if>
				<c:if test="${empty mchtStatisticalInfos.returnRate90Days}">
				<td align="center">0.0%</td>
				</c:if>
				<c:if test="${not empty mchtInfoCustom.customerServiceOrderRateC}">
				<td align="center">${mchtInfoCustom.customerServiceOrderRateC}%</td>
				</c:if>
				<c:if test="${empty mchtInfoCustom.customerServiceOrderRateC}">
				<td align="center">0.0%</td>
				</c:if>
				<c:if test="${not empty mchtInfoCustom.customerServiceOrderRateA}">
				<td align="center">${mchtInfoCustom.customerServiceOrderRateA}%</td>
				</c:if>
				<c:if test="${empty mchtInfoCustom.customerServiceOrderRateA}">
				<td align="center">0.0%</td>
				</c:if>
				<c:if test="${not empty mchtStatisticalInfos.productApplauseRate}">
				<td align="center">${mchtStatisticalInfos.productApplauseRate}%</td>
				</c:if>
				<c:if test="${empty mchtStatisticalInfos.productApplauseRate}">
				<td align="center">0.0%</td>
				</c:if>
			</tr>	
		</table>
		</c:if>
	  <c:if test="${impeachMemberCustom.source eq '1'}">
		<table class="gridtable marT10">
			<tr>
				<td class="title" style="width: 80px;">工号</td>
				<td class="title" style="width: 80px;">姓名</td>
				<td class="title" style="width: 80px;">部门</td>
				<td class="title" style="width: 80px;">联系电话</td>
			</tr>
			<tr>
				<td align="center">${StaffID}</td>
				<td align="center">${Name}</td>
				<td align="center">${OrgName}</td>
				<td align="center">${MobilePhone}</td>
			</tr>		
                 
		</table>
	</c:if>
	</div>

    <br>
    <div><span class="table-title">举报信息</span></div>
    <br>
    <table class="gridtable marT10 status-table">
        <tr>
            <td class="title title-width" style="width:20%;">项目</td>
            <td colspan="7" align="center" class="l-table-edit-td">
                                                               内容
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报类型</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                ${impeachMemberCustom.typeDesc}
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报场景</td>
            <td colspan="7" align="left" class="l-table-edit-td"> 
                              <c:if test="${impeachMemberCustom.type=='1'}">   
                              ${impeachMemberCustom.scenedesc1}
                              </c:if>
                              <c:if test="${impeachMemberCustom.type=='2'}">   
                              ${impeachMemberCustom.scenedesc2}
                              </c:if> 
                              <c:if test="${impeachMemberCustom.type=='3'}">   
                              ${impeachMemberCustom.scenedesc3}
                              </c:if>          
   
            </td>
        </tr>
        <tr>
            <td class="title title-width">相关订单</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <c:forEach var="subOrders" items="${subOrders}">
		                  <a href="javascript:subOrdersId(${subOrders.id});">${subOrders.subOrderCode}</a>&nbsp<span style="color: red;">(会员ID:${subOrders.memberId})</span><br>
	            </c:forEach> 
            </td>
        </tr>
       <tr>
            <td class="title title-width">联系人信息</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                 <c:if test="${impeachMemberCustom.source eq '1'}">${Name}</c:if>
                 <c:if test="${impeachMemberCustom.source eq '0'}">${impeachMemberCustom.name}</c:if>
            </td>
        </tr>
        <tr>
            <td class="title title-width">联系人电话</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <c:if test="${impeachMemberCustom.source eq '1'}">${MobilePhone}</c:if>
                 <c:if test="${impeachMemberCustom.source eq '0'}">${impeachMemberCustom.mobile}</c:if>
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报说明</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                ${impeachMemberCustom.description}
            </td>
        </tr>
        <tr>
            <td class="title title-width">举报凭证</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <c:if test="${not empty impeachMemberProofs}">
				        <c:forEach var="impeachMemberProofs" items="${impeachMemberProofs}">
						${impeachMemberProofs.fileName}&nbsp;&nbsp;<a href="javascript:;" id="${impeachMemberProofs.id}" name="downLoadimpeachMemberProof" attachmentname="${impeachMemberProofs.fileName}" attachmentpath="${impeachMemberProofs.filePath}">【下载】<c:if test="${impeachMemberProofs.isAdd eq '1'}"><label style="color: red;">[新增]</label></c:if></a>
						</c:forEach>
				</c:if>
            </td>
        </tr>
    </table>
    
    <br>
    <div class="title-top">
		<div class="table-title">
			<span>相关会员信息</span>
		</div>
		<br>
		<table class="gridtable marT10">
			<tr>
				<td class="title" style="width: 80px;">会员ID</td>
				<td class="title" style="width: 80px;">订单</td>
				<td class="title" style="width: 80px;">售后单</td>
				<td class="title" style="width: 80px;">介入单</td>
				<td class="title" style="width: 80px;">投诉单</td>
				<td class="title" style="width: 80px;">收货地址</td>
			</tr>
			<c:forEach var="memberInfoCustoms" items="${memberInfoCustoms}">
				 <tr>
					<td align="center">${memberInfoCustoms.id}</td>
					<c:if test="${memberInfoCustoms.subOrderCustomscuont == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.subOrderCustomscuont > '0'}"><td align="center"><a href="javascript:subOrderCustomscuont(${memberInfoCustoms.id},'1');">${memberInfoCustoms.subOrderCustomscuont}&nbsp(查看)</a></td></c:if>
					<c:if test="${memberInfoCustoms.customerServiceOrderCount == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.customerServiceOrderCount > '0'}"><td align="center"><a href="javascript:customerServiceOrderCount(${memberInfoCustoms.id},'2');">${memberInfoCustoms.customerServiceOrderCount}&nbsp(查看)</a></td></c:if>
					<c:if test="${memberInfoCustoms.interventionOrderCount == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.interventionOrderCount > '0'}"><td align="center"><a href="javascript:interventionOrderCount(${memberInfoCustoms.id},'3');">${memberInfoCustoms.interventionOrderCount}&nbsp(查看)</a></td></c:if>
					<c:if test="${memberInfoCustoms.appealOrderCount == '0'}"><td align="center">0</td></c:if>
					<c:if test="${memberInfoCustoms.appealOrderCount > '0'}"><td align="center"><a href="javascript:appealOrderCount(${memberInfoCustoms.id},'4');">${memberInfoCustoms.appealOrderCount}&nbsp(查看)</a></td></c:if>
					<td align="center"><a href="javascript:memberId(${memberInfoCustoms.id},'5');">查看</a></td>
				</tr>
			</c:forEach>	   
		</table>
	</div>
	    <c:if test="${impeachMemberCustom.status eq '2' || impeachMemberCustom.status eq '4' || impeachMemberCustom.status eq '7'}">
        <br>
        <div><span class="table-title">专员审核</span>
        </div>
        <br>
        <table class="gridtable">
           <c:if test="${impeachMemberCustom.source eq '0'}">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>判断结果</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="status1" value="3" disabled="true">驳回（至商家）
                    &nbsp; &nbsp;
                    <input type="radio" name="status1" value="2" <c:if test="${impeachMemberCustom.status eq '2' || impeachMemberCustom.status eq '4' || impeachMemberCustom.status eq '7'}">checked</c:if> disabled="true">专员通过
                </td>
            </tr>
            </c:if>
            <c:if test="${impeachMemberCustom.status ne '3'}">           
            <tr id="needLimit">
                <td colspan="1" class="title"><span class="required">*</span>是否限制用户行为</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="needLimit" value="1" onchange="need(1)" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4' or impeachMemberCustom.status eq '7') and impeachMemberCustom.needLimit eq '1'}">checked</c:if> disabled="true">是
                    &nbsp; &nbsp;
                    <input type="radio" name="needLimit" value="0" onchange="need(0)" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4' or impeachMemberCustom.status eq '7') and impeachMemberCustom.needLimit eq '0'}">checked</c:if> disabled="true">否
                </td>
            </tr>
            <tr id="limitMemberAction1">
                <td colspan="1" class="title"><span class="required">*</span>限制用户行为选择</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="checkbox" name="limitMemberAction" value="1" disabled="true">限制评价
                    &nbsp; &nbsp;
                    <input type="checkbox" name="limitMemberAction" value="2" disabled="true">限制下单
                    &nbsp; &nbsp;
                    <input type="checkbox" name="limitMemberAction" value="3" disabled="true">限制登入
                </td>
            </tr>
            </c:if>
           <c:if test="${impeachMemberCustom.source eq '1' and (impeachMemberCustom.status eq '1' or impeachMemberCustom.status eq '5' or impeachMemberCustom.status eq '6')}">
             <tr>
                <td colspan="1" class="title"><span class="required">*</span>补充举证材料</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                 
                </td>
            </tr>
            </c:if>
            <c:if test="${impeachMemberCustom.status ne '3'}">       
            <tr id="caseCloseDesc1">
                <td colspan="1" class="title"><span class="required">*</span>结案说明</td>
                <td colspan="7" align="left" class="l-table-edit-td">              
               		<select id="caseCloseDesc" name="caseCloseDesc" style="width: 610px;" disabled="true">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}"> 
               			   <%--  <c:if test="${caseCloselist.statusValue ne '4'}">  --%>       			     
							<option value="${caseCloselist.statusValue}" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4' or impeachMemberCustom.status eq '7') and caseCloselist.statusValue==impeachMemberCustom.caseCloseDesc}">selected</c:if>>${caseCloselist.statusDesc}</option>
							<%-- </c:if> --%>
						</c:forEach>
               		</select>
                </td>
            </tr>
            <%-- <tr id="caseCloseDesc2" style="display:none;">
               <td colspan="1" class="title"><span class="required">*</span>结案说明</td>
                <td colspan="7" align="left" class="l-table-edit-td">
               		<select id="caseCloseDescS" name="caseCloseDescS" style="width: 300px;" disabled="true">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}">
               			     <c:if test="${caseCloselist.statusValue=='4'}">
							<option value="${caseCloselist.statusValue}" <c:if test="${(impeachMemberCustom.status eq '2' or impeachMemberCustom.status eq '4' or impeachMemberCustom.status eq '7') and caseCloselist.statusValue==impeachMemberCustom.caseCloseDesc}">selected</c:if>>${caseCloselist.statusDesc}</option>
							</c:if>
						</c:forEach>
               		</select>
               </td>
	        </tr> --%>
	         </c:if>
            <c:if test="${impeachMemberCustom.status eq '5' || impeachMemberCustom.status eq '6' || impeachMemberCustom.status eq '3'}">
            <tr id="rejectReason1">
                <td colspan="1" class="title"><span class="required">*</span>驳回原因</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                   <select id="rejectReason" name="rejectReason" style="width: 500px;" disabled="true">
               			<option value="">请选择</option>
               			<c:forEach var="rejectReasonList" items="${rejectReasonList}">         			     
							<option value="${rejectReasonList.statusValue}" <c:if test="${rejectReasonList.statusValue==impeachMemberCustom.rejectReason}">selected</c:if>>${rejectReasonList.statusDesc}</option>
						</c:forEach>
               		</select>
                </td>
            </tr>
            </c:if>
            <tr>
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="commissionerInnerRemarks" name="commissionerInnerRemarks" cols="50" class="text" disabled="true">${impeachMemberCustom.commissionerInnerRemarks}</textarea>
                </td>
            </tr>
            <c:if test="${(impeachMemberCustom.status eq '5' || impeachMemberCustom.status eq '6') and imPeachType ne '6'}">       
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>操作</td>
                <td align="left" class="l-table-edit-td">
                    <button type="button" class="l-button l-button-submit" onclick="submitAudit()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
            </c:if>
        </table>
       </c:if>
      
       <c:if test="${impeachMemberCustom.status eq '4' || impeachMemberCustom.status eq '7'}">
        <br>
        <div><span class="table-title">法务复审</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>判断结果</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="status" value="5" onchange="fwStatus(5)" disabled="true">复审驳回（至专员）
                    &nbsp; &nbsp;
                    <input type="radio" name="status" value="4" onchange="fwStatus(4)" disabled="true" <c:if test="${impeachMemberCustom.status eq '4' or impeachMemberCustom.status eq '7'}">checked</c:if>>复审通过
                </td>
            </tr>
      
            <tr id="fwInnerRemarks1">
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="fwInnerRemarks" name="fwInnerRemarks" cols="50" disabled="true" class="text">${impeachMemberCustom.fwInnerRemarks}</textarea>
                </td>
            </tr>
              <c:if test="${impeachMemberCustom.status eq '6' || impeachMemberCustom.status eq '2'}">
              <tr id="fwRejectReason1">
                <td colspan="1" class="title"><span class="required">*</span>驳回原因</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="fwRejectReason" name="fwRejectReason" disabled="true" cols="50" class="text"></textarea>
                </td>
            </tr>
            </c:if>
        </table>
      </c:if>
  
        <c:if test="${impeachMemberCustom.status eq '7'}">
        <br>
        <div><span class="table-title">平台结案</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>判断结果</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="status2" value="7" onchange="endStatus(7)" disabled="true" <c:if test="${impeachMemberCustom.status eq '7'}">checked</c:if>>结案通过
                    &nbsp; &nbsp;
                    <input type="radio" name="status2" value="6" onchange="endStatus(6)" disabled="true">结案驳回
                </td>
            </tr>
            <tr id="limitMemberActionS">
                <td colspan="1" class="title"><span class="required">*</span>限制用户行为选择</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="checkbox" name="endlimitMemberActionS" value="1" disabled="true">限制评价
                    &nbsp; &nbsp;
                    <input type="checkbox" name="endlimitMemberActionS" value="2" disabled="true">限制下单
                    &nbsp; &nbsp;
                    <input type="checkbox" name="endlimitMemberActionS" value="3" disabled="true">限制登入
                </td>
            </tr>
            <tr id="endcaseCloseDesc1">
                <td colspan="1" class="title"><span class="required">*</span>结案说明</td>
                <td colspan="7" align="left" class="l-table-edit-td">              
               		<select id="endcaseCloseDesc" name="endcaseCloseDesc" style="width: 610px;" disabled="true">
               			<option value="">请选择</option>
               			<c:forEach var="caseCloselist" items="${caseCloselist}">             			        			     
							<option value="${caseCloselist.statusValue}" <c:if test="${caseCloselist.statusValue==impeachMemberCustom.caseCloseDesc}">selected</c:if>>${caseCloselist.statusDesc}</option>
						</c:forEach>
               		</select>
                </td>
            </tr>
            <tr id="endInnerRemarks1">
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="endInnerRemarks" name="endInnerRemarks" cols="50" class="text" disabled="true">${impeachMemberCustom.endInnerRemarks}</textarea>
                </td>
            </tr>
             <c:if test="${impeachMemberCustom.status eq '6'}">
              <tr id="endRejectReason1">
                <td colspan="1" class="title"><span class="required">*</span>结案驳回</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows="7" id="endRejectReason" name="endRejectReason" cols="50" class="text" disabled="true"></textarea>
                </td>
            </tr>
            </c:if>
        </table>
       </c:if>
        <br>
        <div class="title-top">
		<div class="table-title">
			<span>平台处理记录</span>
		</div>
		<br>
		<table class="gridtable marT10">
           	<tr>
               <td width="30%" class="title">时间</td> 
               <td width="30%" class="title">状态</td>
               <td width="40%" class="title">内容</td>
			</tr>
	        <c:forEach var="impeachHandleLogs" items="${impeachHandleLogs}">
		        <tr>
		        	<td align="center">
	               		<fmt:formatDate value="${impeachHandleLogs.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	               	</td>
	               	<c:if test="${impeachHandleLogs.status eq '1'}">
	               	   <td align="center">创建举报</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '2'}">
	               	   <td align="center">待初审</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '3'}">
	               	   <td align="center">驳回</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '4'}">
	               	   <td align="center">专员通过</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '5'}">
	               	   <td align="center">复审通过</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '6'}">
	               	   <td align="center">复审驳回</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '7'}">
	               	   <td align="center">结案通过</td>
	               	</c:if>
	               	<c:if test="${impeachHandleLogs.status eq '8'}">
	               	   <td align="center">结案驳回</td>
	               	</c:if>
	               	<td align="center">${impeachHandleLogs.content}</td>
		        </tr>
	        </c:forEach>
        </table>
	</div>
</form>
</body>
</html>
