<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>

    <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>

<script type="text/javascript">

		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		function closeDialog(){
    		dialog.close();//关闭dialog 
		}
		
        function submit_fun(){
         	$("#labelName").text(""); 
			$("#labelFee_rate").text("");
			$("#activities_DSR_tips").text("");
			$("#turnover_tips").text("");
			$("#activity_sales_tips").text("");
			if (${type}==2){
				$("#labelSeq_no").text("");
			}
			
			if ($.trim($("#name").val())=='')
			{
				$("#labelName").text("请填写品类名称");
				return;
			}
			if (($("#fee_rate").val())=='' && ${t_level}==1)
			{
				$("#labelFee_rate").text("请填写默认佣金比例");
				return;
			}
			if (($("#deposit").val())=='' && ${t_level}==1)
			{
				$("#labelDeposit").text("请填写默认保证金");
				return;
			}
			if (($("#fee_rate").val())!='')
		 	{
				if (isNaN($("#fee_rate").val())|| $("#fee_rate").val()>=1)
				{
					$("#labelFee_rate").text("请填写正确的格式，例如:12.5%填写为0.125");
					return;
				}
				var feeRate=document.getElementById("fee_rate").value;
			}
			
			if (($("#individual_fee_rate").val())=='' && ${t_level}==1)
			{
				$("#labelIndividual_Fee_rate").text("请填写个体商户佣金比例");
				return;
			}
			if (($("#individual_deposit").val())=='' && ${t_level}==1)
			{
				$("#labelIndividual_deposit").text("请填写个体商户保证金");
				return;
			}
			if (($("#individual_fee_rate").val())!='')
		 	{
				if (isNaN($("#individual_fee_rate").val())|| $("#individual_fee_rate").val()>=1)
				{
					$("#labelIndividual_Fee_rate").text("请填写正确的格式，例如:12.5%填写为0.125");
					return;
				}
				var individualFeeRate=document.getElementById("individual_fee_rate").value;
			}
			
			if (${t_level}==1) {
				if ($('#enterprise_activities_DSR').val() == null || $('#enterprise_activities_DSR').val() == '') {
					$('#activities_DSR_tips').text("请填写企业开通活动SDR");
					return;
				}
				if (parseInt($('#enterprise_activities_DSR').val()) == null ) {
					$('#activities_DSR_tips').text("请填写大于等于0并且小于5的数值");
					return;
				}
				if ($('#enterprise_activities_DSR').val() > 5.00 || $('#enterprise_activities_DSR').val() < 0 ) {
					$('#activities_DSR_tips').text("请填写大于等于0并且小于5的数值");
					return;
				}
				if ($('#enterprise_turnover').val() == null || $('#enterprise_turnover').val() == '') {
					$('#turnover_tips').text("请填写企业开通营业额");
					return;
				}
				if (!/^\d*$/.test($('#enterprise_turnover').val())) {
					$('#turnover_tips').text("请填写0或者大于0的整数");
					return;
				}
				if ($('#enterprise_activity_sales').val() == null || $('#enterprise_activity_sales').val() == '') {
					$('#activity_sales_tips').text("请填写企业开通活动销量");
					return;
				}
				if (!/^\d*$/.test($('#enterprise_activity_sales').val())) {
					$('#activity_sales_tips').text("请填写0或者大于0的整数");
					return;
				}
			}
			if (${type}==2){
				if(($("#seq_no").val()) == '') {
					$("#labelSeq_no").text("请填写排序值 ");
					return;
				}
				var principal_staff_id = $("#principal_staff_id").val();
	            if(principal_staff_id != '' ) {
	            	if(!/^[1-9]\d*$/.test(principal_staff_id)) {
						$("#labelPrincipal_staff_id").text("请填正确的负责人ID");
						return;
	            	}else {
	            		var returnStatus = false;
						$.ajax({
							type: 'post',
							url: '${pageContext.request.contextPath}/service/prod/product_type/getPrincipalStaffId.shtml',
							data: {staffId : principal_staff_id},
							dataType: 'json',
							async: false,
							success: function(data) {
								if(data.code != null && data.code == 200) {
									if(data.status == 1) {
										$("#labelPrincipal_staff_id").text("负责人ID不存在");
										returnStatus = true;
									}
								}else {
									commUtil.alertError(data.msg);
									returnStatus = true;
								}
							},
							error: function(e) {
								commUtil.alertError("系统异常请稍后再试");
								returnStatus = true;
							}
						});
						if(returnStatus) {
							return;
						}
	            	}
				}
			}
	 		
            var suitSex = "";
	 		var suitGroup = "";
            $("[name='Sex']").each(function () {
            	if (this.checked){
            		suitSex += "1";
            	}else{
            		suitSex += "0";
            	}
            });
            $("[name='Group']").each(function () {
            	if (this.checked){
            		suitGroup += "1";
            	}else{
            		suitGroup += "0";
            	}
            });
            document.getElementById("suit_sex").value=suitSex;
            document.getElementById("suit_group").value=suitGroup;
			$("#form1").submit();
		}
        
        $(function(){
        	var Sex = document.getElementsByName("Sex");
        	var Group = document.getElementsByName("Group");
        	if (${type}==1){
        		for (var m = 0; m < Sex.length; m++) {
        			Sex[m].checked=true;
        		}
            	for (var n = 0; n < Group.length; n++) {
            		Group[n].checked=true;
            	}
        	}else{
            	var SexStr=document.getElementById("suit_sex").value;
            	var GroupStr=document.getElementById("suit_group").value;
            	for (var m = 0; m < Sex.length; m++) {
            		if(SexStr[m]==1){
            			Sex[m].checked=true;
            		}
            	}
            	for (var n = 0; n < Group.length; n++) {
            		if(GroupStr[n]==1){
            			Group[n].checked=true;
            		}
            	}
        	}

        });
</script>
<html>
	<body style="padding:50px">
	<form name="form1" action="${pageContext.request.contextPath}/service/prod/product_type/success.shtml" method="post" id="form1" enctype="multipart/form-data">
		<input name="type" type="hidden"  value="${type}" />
		<input name="id" type="hidden"  value="${id}" />
		<input name="parent_id" type="hidden"  value="${parent_id}" />
		<input id="suit_sex" name="suit_sex" type="hidden" value="${suit_sex}"/>
		<input id="suit_group" name="suit_group" type="hidden" value="${suit_group}"/>
		<input id="t_level" name="t_level" type="hidden" value="${t_level}"/>
		<table  class="gridtable"  >
		   <tr >
               <td  class="title" width="20%">上级</td>
               <td align="left" class="l-table-edit-td" >${parent_name}</td>
           </tr>
           
           <tr >
               <td  class="title" width="20%">品类名称<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td" >
               		<c:if test="${type eq '1' }">
               			<textarea rows="6" cols="50" id="name" name="name"></textarea>
               			<label id="labelName" style="margin:0 0 0 10px;color: #FF0000"></label>
               		</c:if>
               		<c:if test="${type eq '2' }">
				   		<input id="name" name="name" style="float:left;width: 200px;" type="text" size="25" value="${name}"  />
               			<label id="labelName" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
               		</c:if>
               </td>
           </tr>
            
           <tr>
               <td   class="title" width="20%">备注</td>
               <td align="left" class="l-table-edit-td">
                  <input id="remarks"name="remarks" style="float:left;width: 200px;"  type="text" size="25" value="${remarks}"  />
               <label id="labelRemarks" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
               </td>
           </tr>
           
           <tr>
               <td  class="title"width="20%">状态<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
			     <select  style="float:left;width: 200px;" id="status" name="status">
				     <c:forEach var="list" items="${Showtype}">
					   <option
					     <c:if test="${status==list.STATUS_VALUE}">selected</c:if>
							   value="${list.STATUS_VALUE}">
					           ${list.STATUS_DESC}
						</option>
					  </c:forEach>
				  </select>
				  <label id="labelStatus" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
			   </td>
			</tr>
         
            <tr>
               <td class="title" width="20%">企业默认保证金
               		<c:if test="${t_level == '1'}"><span class="required">*</span></c:if>
               	</td>
               <td align="left" class="l-table-edit-td">
                  <input id="deposit" name="deposit" style="float:left;width: 200px;"  type="text" size="25" value="${deposit}"  />
               <label id="labelDeposit" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
               </td>
           </tr>
           
            <tr>
               <td class="title" width="20%">企业默认佣金比例
               		<c:if test="${t_level == '1'}"><span class="required">*</span></c:if>
               </td>
               <td align="left" class="l-table-edit-td">
                  <input id="fee_rate" name="fee_rate" style="float:left;width: 200px;"  type="text" size="25" value="${fee_rate}"  />
               <label id="labelFee_rate" style="float:left;margin:0 0 0 10px;color: #FF0000">一级品类，此项为必填(如：12.5%请填写为0.125)</label>
               </td>
           </tr>
           
            <tr>
               <td class="title" width="20%">个体商户保证金
               		<c:if test="${t_level == '1'}"><span class="required">*</span></c:if>
               	</td>
               <td align="left" class="l-table-edit-td">
                  <input id="individual_deposit" name="individual_deposit" style="float:left;width: 200px;"  type="text" size="25" value="${individual_deposit}"  />
               <label id="labelIndividual_deposit" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
               </td>
           </tr>
           
            <tr>
               <td class="title" width="20%">个体商户佣金比例
               		<c:if test="${t_level == '1'}"><span class="required">*</span></c:if>
               </td>
               <td align="left" class="l-table-edit-td">
                  <input id="individual_fee_rate" name="individual_fee_rate" style="float:left;width: 200px;"  type="text" size="25" value="${individual_fee_rate}"  />
               <label id="labelIndividual_Fee_rate" style="float:left;margin:0 0 0 10px;color: #FF0000">一级品类，此项为必填(如：12.5%请填写为0.125)</label>
               </td>
           </tr>
           
           
           <c:if test="${t_level == '1'}">
           <tr>
               <td class="title" width="20%">企业开通活动DSR
               		<span class="required">*</span>
               </td>
               <td align="left" class="l-table-edit-td">
                  <input id="enterprise_activities_DSR" name="enterprise_activities_DSR" style="float:left;width: 200px;" type="text" size="4" value="${enterprise_activities_DSR }"  />
               <label id="activities_DSR_tips" style="float:left;margin:0 0 0 10px;color: #FF0000">最大可填写5.00</label>
               </td>
           </tr>
           </c:if>
           
           <c:if test="${t_level == '1'}">
           <tr>
               <td class="title" width="20%">企业开通营业额
               		<span class="required">*</span>
               </td>
               <td align="left" class="l-table-edit-td">
                  <input id="enterprise_turnover" name="enterprise_turnover" style="float:left;width: 200px;"  type="text" maxlength="9" value="${enterprise_turnover }"  />
               <label id="turnover_tips" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
               </td>
           </tr>
           </c:if>
           
           <c:if test="${t_level == '1'}">
           <tr>
               <td class="title" width="20%">企业开通活动销量
               		<span class="required">*</span>
               </td>
               <td align="left" class="l-table-edit-td">
                  <input id="enterprise_activity_sales" name="enterprise_activity_sales" style="float:left;width: 200px;"  type="text" maxlength="9" value="${enterprise_activity_sales }"  />
               <label id="activity_sales_tips" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
               </td>
           </tr>
           </c:if>
           
           <tr>
               <td   class="title" width="20%">适合性别</td>
               <td>
                  <input name="Sex" type="checkbox" style="width: 30px;"/>男
				  <input name="Sex" type="checkbox" style="width: 30px;"/>女 
               </td>
           </tr>
           
           <tr>
               <td  class="title"width="20%">适合人群</td>
               <td align="left" class="l-table-edit-td">
                  <input name="Group" type="checkbox" style="width: 30px;"/>青年
				  <input name="Group" type="checkbox" style="width: 30px;"/>儿童
				  <input name="Group" type="checkbox" style="width: 30px;"/>中老年
               </td> 
           </tr>
           
        <%--    <tr>
               <td   class="title" width="20%">是否小商品</td>
               <td>
                  <input name="isSmallware" type="radio" value="1" <c:if test="${is_smallware eq 1}">checked="checked"</c:if>/>是
				  <input name="isSmallware" type="radio" value="0" <c:if test="${is_smallware eq 0}">checked="checked"</c:if>/>否 
               </td>
           </tr>
            --%>
            <tr>
               <td class="title">商品类型<span class="required">*</span></td>
               <td>
               		<select id="isSmallware" name="isSmallware" style="width: 135px;" >
							<option value="0" <c:if test="${is_smallware eq 0}">selected="selected"</c:if>>有品牌</option>
							<option value="2" <c:if test="${is_smallware eq 2}">selected="selected"</c:if>>无品牌</option>
							<option value="1" <c:if test="${is_smallware eq 1}">selected="selected"</c:if>>小商品</option>
					</select>
               </td>
			</tr>
           <c:if test="${type==2}">
	           <tr >
	               <td  class="title" width="20%">排序值<span class="required">*</span></td>
	               <td align="left" class="l-table-edit-td" >
					 	<input id="seq_no" name="seq_no" style="float:left;width: 200px;" type="text" size="25" value="${seq_no}"  />
	                	<label id="labelSeq_no" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
	                </td>
	           </tr>
	           <tr >
	               <td  class="title" width="20%">类目负责人ID</td>
	               <td align="left" class="l-table-edit-td" >
						<input id="principal_staff_id" name="principal_staff_id" style="float:left;width: 200px;" type="text" size="25" value="${principal_staff_id}"  />
						<label id="labelPrincipal_staff_id" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
	               </td>
	           </tr>
           </c:if>
           <tr>            
           <td colspan="2"> 
            <input type="button" value="取消" style="float:right;" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
		 
		<input name="btnSubmit" type="button" id="Button1" style="float:right;" class="l-button l-button-submit" value="保存" onclick="submit_fun();" /> 
       </td></tr>
		</table> 
		
	</form>
	</body>
</html>
