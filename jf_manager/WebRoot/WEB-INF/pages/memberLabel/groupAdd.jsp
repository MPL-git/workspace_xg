<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
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
<style>
	.hide {
		display:none !important;
	}
	.button-hide {
		display:block;
	}
	.l-button-test {
		position: absolute;
		top: 0;
		left: 210px;
	}
	select {
		margin: 4px;
	}
</style>
<script type="text/javascript">

 /* $(function (){
   var html = []; 
    var memberLabelTypeList=${memberLabelTypeList};
    
    html.push("<div class='memberLabel' style='display: inline-block;margin: 5px 0px;' >");
	html.push("<select id='memberLabelType' name='memberLabelType' style='width: 200px;'>");
	html.push('<option value="">请选择</option>');
	for(var i=0;i<memberLabelTypeList.length;i++){ 
    html.push('<option value="'+memberLabelTypeList[i].id+'">'+memberLabelTypeList[i].labelTypeName+'</option>');
    } 
	html.push("</select>");
	html.push("</div>");
	html.push("<span class='typeList'></span>");
	html.push("<br>");
	html.push("<span style='margin-left: 5px;' class='addmemberLabel' ><input type='button' name='priceRuleAdd' style='color: #FFFFFF;background-color:#00BFFF;border: none;width: 70px;height: 20px;border-radius: 3px;cursor:pointer;' value='+增加标签' onclick='addRelevantmemberLabel();' ></span>");
   $("#labelGroupList").html(html.join(""));
    
   
   $("#memberLabelType").bind('change',function(){
		var memberLabelTypeid = $(this).val();
		$.ajax({
			url : "${pageContext.request.contextPath}/memberLabel/getMemberLabelType.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"memberLabelTypeid":memberLabelTypeid},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var memberLabels = data.memberLabels;
					var optionArray = [];
					for(var i=0;i<memberLabels.length;i++){
						optionArray.push('<input name="memberLabelid" type="checkbox" value="'+memberLabels[i].id+'" style="width: 20px;"/>'+memberLabels[i].labelName+'');
					}
					 $(".typeList").html(optionArray.join(""));
				}else {
					 $(".typeList").html("");
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}); 
    
   
   $.metadata.setType("attr", "validate");
   $("#form1").validate({
 		errorPlacement : function(lable, element) {
 			var elementType=$(element).attr("type");

         	if($(element).hasClass("l-text-field")){
         		$(element).parent().ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
         	else if('radio'==elementType){
         		var radioName=$(element).attr("expiryType");
         		$("input[type=radio][name="+radioName+"]:last").ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
         	else{
         		$(element).ligerTip({
 					content : lable.html(),width: 100
 				});
         	}
 		},
 		success : function(lable) {
 			lable.ligerHideTip();
 			lable.remove();
 		},
 		submitHandler : function(form) {
 			var labelGroupName = $("[name='labelGroupName']").val();
			 if($.trim(labelGroupName)== '') {
	    	   $.ligerDialog.alert("分组不能为空！"); 
	     	  return;
	     	} 
     	    formSubmit();

 		}
 	}); 
    
 }); */  



  function addRelevantmemberLabel() {
	var html = [];
	var memberLabelTypeList=${memberLabelTypeList};
	html.push("<br/>");
	html.push("<div class='memberLabel' style='display: inline-block;margin: 5px 0px;' >");
	html.push("<select id='' name='' style='width: 200px;'>");
	html.push('<option value="">请选择</option>');
	for(var i=0;i<memberLabelTypeList.length;i++){ 
    html.push('<option value="'+memberLabelTypeList[i].id+'">'+memberLabelTypeList[i].labelTypeName+'</option>');
    } 
	html.push("</select>");
	html.push("</div>");
	if($(".deladdmemberLabel").length == 0) {
		/* $(".addmemberLabel").after("<span style='margin-left: 10px;' class='deladdmemberLabel' ><input type='button' style='color: #FFFFFF;background-color:#00BFFF;border: none;width: 70px;height: 20px;border-radius: 3px;cursor:pointer;' value='-删除' onclick='deladdmemberLabel();' ></span>"); */
 		html.push("<span style='margin-left: 10px;' class='deladdmemberLabel' ><input type='button' style='color: #FFFFFF;background-color:#00BFFF;border: none;width: 70px;height: 20px;border-radius: 3px;cursor:pointer;' value='-删除' onclick='deladdmemberLabel();' ></span>");
  	} 
	 $(".memberLabel:last").after(html.join(""));
}


/*  function deladdmemberLabel() {
	$(".memberLabel:last").prev().remove();
	$(".memberLabel:last ").remove();
	if($(".memberLabel").length == 1) {
		$(".deladdmemberLabel").remove();
    }
}  
 */

 function memberLabel(){
	
	 /* $("input[name='memberLabelDiv0']").live('click',function(){ */
			var $closestDiv =$("#memberLabelID").find("div[name='memberLabelDiv0']").closest("div");
			var index = $closestDiv.parent().find("div").length;
			var $div = $closestDiv.parent().find("div").eq(index-1).clone();
			$div.find("option:selected").attr("selected",false);
			$div.find("span").hide();
			if(index == 1){
				var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
				$div.append(html);
				$("#memberLabelID").append($div);
			}else{
				$("#memberLabelID").append($div);
			}
		/* }); */
		
 }
 
 
 function memberLabel1(){
		
	 /* $("input[name='memberLabelDiv0']").live('click',function(){ */
			var $closestDiv =$("#memberLabelID1").find("div[name='memberLabelDiv1']").closest("div");
			var index = $closestDiv.parent().find("div").length;
			var $div = $closestDiv.parent().find("div").eq(index-1).clone();
			$div.find("option:selected").attr("selected",false);
			$div.find("span").hide();
			if(index == 1){
				var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
				$div.append(html);
				$("#memberLabelID1").append($div);
			}else{
				$("#memberLabelID1").append($div);
			}
		/* }); */
		
 }
 


 $(function(){
	/* $("input[name='memberLabeladd']").live('click',function(){
		var $closestDiv = $(this).closest("div");
		var index = $closestDiv.parent().find("div").length;
		var $div = $closestDiv.parent().find("div").eq(index-1).clone();
		$div.find("option:selected").attr("selected",false);
		$div.find("span").hide();
	    $(this).eq(0).addClass('hide');  
		if(index == 1){
			var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
			$div.append(html);
			$("#memberLabelID").append($div);
		}else{
			$("#memberLabelID").append($div);
		}
	}); */
	
	
	/* $("input[name='memberLabeladd1']").live('click',function(){
		var $closestDiv = $(this).closest("div");
		var index = $closestDiv.parent().find("div").length;
		var $div = $closestDiv.parent().find("div").eq(index-1).clone();
		$div.find("option:selected").attr("selected",false);
		$div.find("span").hide();
		$(this).eq(0).addClass('hide'); 
		if(index == 1){
			var html = '&nbsp;&nbsp;<input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">';
			$div.append(html);
			$("#memberLabelID1").append($div);
		}else{
			$("#memberLabelID1").append($div);
		}
	}); */
	

	
	 $.metadata.setType("attr", "validate");
	   $("#form1").validate({
	 		errorPlacement : function(lable, element) {
	 			var elementType=$(element).attr("type");

	         	if($(element).hasClass("l-text-field")){
	         		$(element).parent().ligerTip({
	 					content : lable.html(),width: 100
	 				});
	         	}
	         	else if('radio'==elementType){
	         		var radioName=$(element).attr("expiryType");
	         		$("input[type=radio][name="+radioName+"]:last").ligerTip({
	 					content : lable.html(),width: 100
	 				});
	         	}
	         	else{
	         		$(element).ligerTip({
	 					content : lable.html(),width: 100
	 				});
	         	}
	 		},
	 		success : function(lable) {
	 			lable.ligerHideTip();
	 			lable.remove();
	 		},
	 		submitHandler : function(form) {
	 	
				var labelTypeId=$("select[name='labelTypeId']").val();
				var labelTypeId1=$("select[name='labelTypeId1']").val();
				if ($.trim(labelTypeId)== '' && $.trim(labelTypeId1)== '') {
					$.ligerDialog.alert("必要条件和排除条件必填其中一个！");
					return;
				} 
				
				var memberLabelid=$("input[type='checkbox']:checked").val();
				if ($.trim(labelTypeId)!= ''  && $.trim(memberLabelid)== '') {
					$.ligerDialog.alert("必要条件复选项必须选择！");
					return;
				}
				
				var memberLabelid=$("input[type='checkbox']:checked").val();
				if ($.trim(labelTypeId1)!= ''  && $.trim(memberLabelid)== '') {
					$.ligerDialog.alert("排除条件复选选项必须选择！");
					return;
				}
				
				
	     	    formSubmit();

	 		}
	 	}); 
	
}); 


 function del(_this){
	$(_this).closest("div").remove(); 
	
} 

 
 function memberLabelType(_this){
	 $(_this).find("option:selected").each(function(index){
		var memberLabelTypeid = $(this).eq(index).val(); 
		/*  var memberLabelTypeid = $(this).find("select[name='productTypeId']:selected").val(); */
		if(memberLabelTypeid){
			$.ajax({
				url : "${pageContext.request.contextPath}/memberLabel/getMemberLabelType.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"memberLabelTypeid":memberLabelTypeid},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var optionArray = [];
						var memberLabels = data.memberLabels;
						for(var i=0;i<memberLabels.length;i++){
							optionArray.push('<input name="memberLabelid" type="checkbox" value="'+memberLabels[i].id+'" style="width: 20px;"/>'+memberLabels[i].labelName+'');

						}
						
						 $(_this).eq(index).parent().find("span").html(optionArray.join("")).show();
						
						/* $(".typelist").html(optionArray.join("")); */
						
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	 }); 
	
} 
 
 
 
 function memberLabelType1(_this){
	 $(_this).find("option:selected").each(function(index){
		var memberLabelTypeid = $(this).eq(index).val(); 
		if(memberLabelTypeid){
			$.ajax({
				url : "${pageContext.request.contextPath}/memberLabel/getMemberLabelType.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"memberLabelTypeid":memberLabelTypeid},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var memberLabels = data.memberLabels;
						var optionArray = [];
						for(var i=0;i<memberLabels.length;i++){
							optionArray.push('<input name="memberLabelid1" type="checkbox" value="'+memberLabels[i].id+'" style="width: 20px;"/>'+memberLabels[i].labelName+'');
							
						}
						
						$(_this).eq(index).parent().find("span").html(optionArray.join("")).show();
						
						/* $(".typelist1").html(optionArray.join("")); */
						
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	 }); 
	
} 
 


function formSubmit(){
	var memberlabelgroupList=[];
	$("#memberLabelID").find("div[name='memberLabelDiv0']").each(function(){
		var $_this = $(this);
		var labelTypeId = $_this.find("option:selected").val();
		$_this.find("input[type='checkbox']:checked").each(function () { 
			var memberLabelid=$(this).val();
			var object = new Object();
			object.labelTypeId = labelTypeId;
    		object.memberLabelid = memberLabelid;
    		memberlabelgroupList.push(object);
	    });
		
	});
		var jsonMemberlabelgroup = JSON.stringify(memberlabelgroupList);
		$("#jsonMemberlabelgroup").val(jsonMemberlabelgroup);
		
		
		
		var memberlabelgroupList1=[];
		$("#memberLabelID1").find("div[name='memberLabelDiv1']").each(function(){
			var $_this = $(this);
			var labelTypeId1 = $_this.find("option:selected").val();
			$_this.find("input[type='checkbox']:checked").each(function () { 
				var memberLabelid1=$(this).val();
				var object = new Object();
				object.labelTypeId1 = labelTypeId1;
	    		object.memberLabelid1 = memberLabelid1;
	    		memberlabelgroupList1.push(object);
		    });
			
		});
			var jsonMemberlabelgroup1 = JSON.stringify(memberlabelgroupList1);
			$("#jsonMemberlabelgroup1").val(jsonMemberlabelgroup1);
			
		
	$.ajax({
		url : "${pageContext.request.contextPath}/memberLabel/addmemberLabelGroup.shtml",
		type : 'POST',
		dataType : 'json',
		data : $("#form1").serialize(),
		success : function(data) {
			if ("0000" == data.returnCode) {
				$.ligerDialog.success(data.returnMsg);
				parent.location.reload();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1" class="form1" action="" >
		<input type="hidden" id="id" name="id" value="${memberLabelGroup.id}"/>
		<input type="hidden" id="labelId" name="labelId" value=""/>
		<input type="hidden" id="type0" name="type0" value="0"/>
		<input type="hidden" id="type1" name="type1" value="1"/>
		<input type="hidden" id="jsonMemberlabelgroup" name="jsonMemberlabelgroup">
		<input type="hidden" id="jsonMemberlabelgroup1" name="jsonMemberlabelgroup1">
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">分组名称<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="labelGroupName"  name="labelGroupName" type="text" style="width:200px;" value="${memberLabelGroup.labelGroupName}"  validate="{required:true,maxlength:50}" placeholder="限制为1-50个字"/>
				</td>
			</tr>
             
	        <tr>
				<td class="title">必要条件</td>
				<td align="left" class="l-table-edit-td" id="memberLabelID">
					<!-- <div id="labelGroupList" style="border-style: none;" ></div> -->
				 <span style="color: red;">温馨提示：</span>必要条件，即该分组必须满足的条件；必要条件与排除条件是交集关系。可添加多个标签类型，标签类型之间是交集关系；一个标签类型可选择多个值，值之间是并集关系。<br>
				 <c:if test="${not empty LabelTypeIdList}">
				 <input type="button" class="l-button button-hide" name="memberLabeladd" value="+选择标签类型" style="width: 100px;" onclick="memberLabel()">
				 </c:if>
				<c:forEach items="${LabelTypeIdList}" var="LabelTypeID" varStatus="idx">
			     <div name="memberLabelDiv0" style="position: relative;">
				    <select name="labelTypeId" onchange="memberLabelType(this);" style="width: 200px;">
					<option value="">请选择</option>
	               	<c:forEach items="${memberLabelTypeList}" var="memberLabelTypeList">
		               	<option value="${memberLabelTypeList.id}" <c:if test="${LabelTypeID eq memberLabelTypeList.id}">selected</c:if>>${memberLabelTypeList.labelTypeName}</option>
	               	</c:forEach>
                   	</select>
			      <span class="typelist" style="margin-left: 60px;">
			        <c:forEach items="${memberLabels}" var="memberLabels">
			         <c:if test="${LabelTypeID eq memberLabels.labelTypeId}">
			         <input name="memberLabelid" type="checkbox" value="${memberLabels.id}" style="width: 20px;" checked="checked">${memberLabels.labelName}
			         </c:if>
			        </c:forEach>
			      </span>
			      <c:if test="${idx.index == 1}">
			      <input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
			      </c:if>
			      <c:if test="${idx.index gt 0}"> 
			     <!--  <input type="button" class="l-button button-hide" name="memberLabeladd" value="+选择标签类型" style="width: 100px;"> -->
			     <input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
			     </c:if> 
			     </div>
			    </c:forEach>
			     
			     <c:if test="${empty LabelTypeIdList}">
			      <input type="button" class="l-button button-hide" name="memberLabeladd" value="+选择标签类型" style="width: 100px;" onclick="memberLabel()">
			      <div name="memberLabelDiv0" style="position: relative;">
				    <select name="labelTypeId" onchange="memberLabelType(this);" style="width: 200px;">
					<option value="">请选择</option>
	               	<c:forEach items="${memberLabelTypeList}" var="memberLabelTypeList">
		               	<option value="${memberLabelTypeList.id}">${memberLabelTypeList.labelTypeName}</option>
	               	</c:forEach>
                   	</select>
			      <span class="typelist" style="margin-left: 60px;"></span>
<!-- 			       <input type="button" class="l-button button-hide" name="memberLabeladd" value="+选择标签类型" style="width: 100px;" onclick="memberLabe()">
 -->			  </div>
<!--  			     <input type="button" class="l-button button-hide" name="memberLabeladd" value="+选择标签类型" style="width: 100px;" onclick="memberLabe()">
 --> 			  </c:if>      
			   </td>
			</tr>
			
			 <tr>
				<td class="title">排除条件</td>
				<td align="left" class="l-table-edit-td" id="memberLabelID1">
				 <span style="color: red;">温馨提示：</span>排除条件，即该分组要过滤掉满足这些条件的用户；必要条件与排除条件是交集关系。可添加多个标签类型，标签类型之间是交集关系；一个标签类型可选择多个值，值之间是并集关系。<br>
			    <c:if test="${not empty LabelTypeIdList1}">
				 <input type="button" class="l-button button-hide" name="memberLabeladd1" value="+选择标签类型" style="width: 100px;" onclick="memberLabel1()">
				</c:if>
				<c:forEach items="${LabelTypeIdList1}" var="LabelType1ID" varStatus="idx">
			     <div name="memberLabelDiv1" style="position: relative;">
				    <select name="labelTypeId1" onchange="memberLabelType1(this);" style="width: 200px;">
					<option value="">请选择</option>
	               	<c:forEach items="${memberLabelTypeList}" var="memberLabelTypeList">        
		               	<option value="${memberLabelTypeList.id}" <c:if test="${LabelType1ID eq memberLabelTypeList.id}">selected</c:if>>${memberLabelTypeList.labelTypeName}</option>
	               	</c:forEach>
                   	</select>
			       <span class="typelist1" style="margin-left: 60px;">
			       <c:forEach items="${memberLabels1}" var="memberLabels1">
			         <c:if test="${LabelType1ID eq memberLabels1.labelTypeId}">
			         <input name="memberLabelid" type="checkbox" value="${memberLabels1.id}" style="width: 20px;" checked="checked">${memberLabels1.labelName}
			         </c:if>
			        </c:forEach>
			       </span>
			       <c:if test="${idx.index == 1}">
			       <!-- <input type="button" class="l-button button-hide" name="memberLabeladd1" value="+选择标签类型" style="width: 100px;"> -->
			       <input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
			       </c:if>
			       <c:if test="${idx.index gt 0}">
			      <!--  <input type="button" class="l-button button-hide" name="memberLabeladd1" value="+选择标签类型" style="width: 100px;"> -->
			        <input type="button" class="l-button l-button-test" name="delBtn" value="删除" onclick="del(this);">
			       </c:if>
			     </div>
			     </c:forEach>
			     
			     
			     <c:if test="${empty LabelTypeIdList1}">
			      <input type="button" class="l-button button-hide" name="memberLabeladd1" value="+选择标签类型" style="width: 100px;" onclick="memberLabel1()">
			      <div name="memberLabelDiv1" style="position: relative;">
				    <select name="labelTypeId1" onchange="memberLabelType1(this);" style="width: 200px;">
					<option value="">请选择</option>
	               	<c:forEach items="${memberLabelTypeList}" var="memberLabelTypeList">
		               	<option value="${memberLabelTypeList.id}">${memberLabelTypeList.labelTypeName}</option>
	               	</c:forEach>
                   	</select>
			      <span class="typelist1" style="margin-left: 60px;"></span>
			      <!-- <input type="button" class="l-button button-hide" name="memberLabeladd1" value="+选择标签类型" style="width: 100px;">	 -->		
			     </div>
			     </c:if> 
			   </td>
			</tr>
			       	
	         <tr>
				<td class="title">内部备注</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="10" cols="50" id='remarks' name="remarks" validate="{maxlength:100}" placeholder="限制为0-100个字">${memberLabelGroup.remarks}</textarea>
				</td>
			</tr> 
			      
			 <tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>