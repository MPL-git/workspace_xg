<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("brandAptitude") != null ? request.getParameter("brandAptitude") : "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
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
		
		var editor1;
        KindEditor.ready(function(K) {
    		 editor1 = K.create('textarea[name="brandAptitude"]', {
    			width:'85%',
    			height : '650px',
    			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
    			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
    			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
    			allowFileManager : true,
    			afterCreate : function() {
    			}
    			
    		});
    		prettyPrint();
    	});
		
		function checkMoney(money){
			var re = /^(0|[1-9]\d*)(\.\d{1,2})?$/;
			return re.test(money);
		}
		
        function submit_fun(){
         	$("#labelName").text(""); 
			if(${type}==2){
				$("#labelSeq_no").text("");
			}
			if($.trim($("#name").val())==''){
				commUtil.alertError("请填写品类名称");
				return;
			}
			var t_level = $("#t_level").val();
			if(t_level == 1){
				var productTypeIds = $("#productTypeIds").val();
				if(!productTypeIds){
					commUtil.alertError("请选择关联APP一级类目");
					return;
				}
			}else if(t_level == 2){
				var deposit = $("#deposit").val().trim();
				if(!deposit){
					commUtil.alertError("保证金不能为空");
					return;
				}
				if(!checkMoney(deposit)){
					commUtil.alertError("请输入正确格式的保证金金额");
					return false;
				}
				if(parseFloat(deposit)<0){
					commUtil.alertError("对不起，保证金只能填写零和正数。");
					return false;
				}
				var fee_rate = $("#fee_rate").val().trim();
				if(!fee_rate){
					commUtil.alertError("技术服务费不能为空");
					return;
				}
				if(!checkMoney(fee_rate)){
					commUtil.alertError("请输入正确格式的技术服务费");
					return false;
				}
				if(parseFloat(fee_rate)>=1 || parseFloat(fee_rate)<0){
					commUtil.alertError("技术服务费必须大于等于0小于1");
					return false;
				}
				if(parseFloat(deposit)<0){
					commUtil.alertError("对不起，技术服务费只能填写零和正数。");
					return false;
				}
				var product_type_ids = "";
				$("#thirdProductType").find("option:selected").each(function(index){
					if(index!=$("#thirdProductType").find("option:selected").length-1){
						product_type_ids+=$(this).val()+",";
					}else{
						product_type_ids+=$(this).val();
					}
				});
				if(!product_type_ids){
					commUtil.alertError("请选择关联APP三级类目");
					return false;
				}
				$("#product_type_ids").val(product_type_ids);
				editor1.sync();
				var brandAptitude = document.getElementById("brandAptitude").value;
				if(!brandAptitude){
					commUtil.alertError("品牌资质其他资质要求不能为空");
					return;
				}
			}
			if (${type}==2){
				if (($("#seq_no").val())=='')
		 		{
					$("#labelSeq_no").text("请填写排序值 ");
					return;
				}
			}
			$("#form1").submit();
		}
        
        $(function(){
        	
            var submitting;
            $("option[name='secondProductType']").bind('click',function(){
            	if(submitting){
            		return false;
            	}
            	var parentIds="";
            	$("#secondProductType").find("option:selected").each(function(index){
            		if(index!=$("#secondProductType").find("option:selected").length-1){
	            		parentIds+=$(this).val()+",";
            		}else{
	            		parentIds+=$(this).val();
            		}
            	});
            	submitting = true;
            	$.ajax({
    				url : "${pageContext.request.contextPath}/prod/zsProductType/getProductTypeSub.shtml",
    				type : 'POST',
    				dataType : 'json',
    				cache : false,
    				async : false,
    				data : {parentIds:parentIds},
    				timeout : 30000,
    				success : function(data) {
    					if ("0000" == data.returnCode) {
    						var productTypes = data.productTypes;
    						$("#thirdProductType").empty();
    						var array = [];
    						var product_type_ids = $("#product_type_ids").val();
    						for(var i=0;i<productTypes.length;i++){
    							var selectedDesc = "";
    							if(product_type_ids.indexOf(productTypes[i].id)>=0){
    								selectedDesc = "（已选）";
    							}
    							if(selectedDesc){
	    							array.push('<option value="'+productTypes[i].id+'" style="color:red;" name="thirdProductType" selected="selected">'+productTypes[i].name+''+selectedDesc+'</option>');
    							}else{
	    							array.push('<option value="'+productTypes[i].id+'" name="thirdProductType">'+productTypes[i].name+'</option>');
    							}
    						}
    						$("#thirdProductType").html(array.join());
    						$("#thirdProductType").show();
    						submitting = false;
    					}else{
    						$.ligerDialog.error(data.returnMsg);
    						submitting = false;
    					}
    				},
    				error : function() {
    					$.ligerDialog.error('操作超时，请稍后再试！');
    					submitting = false;
    				}
    			});
            });
        });
</script>
<html>
	<body style="padding:50px">
	<form name="form1" action="${pageContext.request.contextPath}/prod/zsProductType/success.shtml" method="post" id="form1" enctype="multipart/form-data">
		<input name="type" type="hidden"  value="${type}" />
		<input name="id" type="hidden"  value="${id}" />
		<input name="parent_id" type="hidden"  value="${parent_id}" />
		<input id="t_level" name="t_level" type="hidden" value="${t_level}"/>
		<input id="product_type_ids" name="product_type_ids" type="hidden" value="${product_type_ids}"/>
		
		<table  class="gridtable"  >
		   <tr >
               <td  class="title" width="20%">上级</td>
               <td align="left" class="l-table-edit-td" >${parent_name}</td>
           </tr>
           
           <tr >
               <td  class="title" width="20%">品类名称<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
                  <input id="name" name="name" style="float:left;width: 200px;"  type="text" size="25" value="${name}"  />
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
			   </td>
			</tr>
			<c:if test="${t_level eq 2}">
			<tr >
               <td  class="title" width="20%">保证金<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
                  <input id="deposit" name="deposit" style="float:left;width: 200px;"  type="text" size="25" value="${deposit}"/>
               </td>
           </tr>
           
			<tr >
               <td  class="title" width="20%">技术服务费<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
                  <input id="fee_rate" name="fee_rate" style="float:left;width: 200px;"  type="text" size="25" value="${fee_rate}"/>
               </td>
           </tr>
           
			<tr >
               <td  class="title" width="20%">关联APP类目<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
               		<div >
	               		<select multiple="multiple" disabled="disabled" style="height: 300px;">
	               			<c:forEach items="${productTypes}" var="productType">
	               				<option value="${productType.id}" <c:if test="${productType.id eq productTypeIds}">selected="selected"</c:if>>${productType.name}</option>
	               			</c:forEach>
	               		</select>
	               		<select multiple="multiple" id="secondProductType" style="width: 200px;height: 300px;">
	               			<c:forEach items="${secondProductTypes}" var="secondProductType">
	               				<option value="${secondProductType.id}" name="secondProductType" <c:forEach items="${selectSecondIds}" var="selectSecondId"><c:if test="${selectSecondId eq secondProductType.id}">selected="selected"</c:if></c:forEach>>${secondProductType.name}</option>
	               			</c:forEach>
	               		</select>
	               		<select multiple="multiple" style="width: 400px;height: 300px;" <c:if test="${id == 0}">style="display: none;"</c:if> id="thirdProductType">
	               			<c:forEach items="${thirdProductTypes}" var="thirdProductType">
	               				<option value="${thirdProductType.id}" name="thirdProductType" selected="selected">${thirdProductType.name}</option>
	               			</c:forEach>
	               		</select>
               		</div>
               </td>
            </tr>
            
			<tr >
               <td  class="title" width="20%">品牌资质其他资质要求<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
               		<textarea name="brandAptitude" id="brandAptitude" style="width:150px;height:300px;visibility:hidden;"><%=htmlspecialchars(htmlData)%>${brandAptitude}</textarea>
               </td>
            </tr>
		   </c:if>
		   <c:if test="${t_level eq 1}">
         	<tr>
               <td  class="title"width="20%">关联APP一级类目<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td">
			     <select style="float:left;width: 200px;" id="productTypeIds" name="productTypeIds">
			     	 <option value="">请选择</option>
				     <c:forEach var="productType" items="${productTypes}">
					   	<option value="${productType.id}" <c:if test="${productType.id eq product_type_ids}">selected="selected"</c:if>>${productType.name}</option>
					 </c:forEach>
				  </select>
			   </td>
			</tr>
         	<tr>
               <td class="title" width="20%">备注</td>
               <td align="left" class="l-table-edit-td" >
               		<textarea rows="6" cols="50" id="remarks" name="remarks">${remarks}</textarea>
               </td>
           </tr>
          </c:if>
         <!-- 
           <c:if test="${type==2}">
           <tr>
               <td  class="title" width="20%">排序值<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td" >
				 <input id="seq_no" name="seq_no" style="float:left;width: 200px;" type="text" size="25" value="${seq_no}"  />
                <label id="labelSeq_no" style="float:left;margin:0 0 0 10px;color: #FF0000"></label></td>
           </tr>
           </c:if>
		 -->	
           <tr> 
           		<td class="title" width="20%">操作</td>	           
	           	<td colspan="2"> 
					<input name="btnSubmit" type="button" id="Button1" class="l-button l-button-submit" value="提交" onclick="submit_fun();" /> 
		            <input type="button" value="取消" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
	      		</td>
       	   </tr>
		</table> 
		
	</form>
	</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>