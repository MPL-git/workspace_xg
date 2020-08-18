<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
</style>
<script type="text/javascript">

	$(function (){
	    $("#form1").ligerForm();
	    var html = [];
	    if('${productBrandTrademarkInfoList }' == '') {
	    	html.push("<div class='tmkCodeType' style='display: inline-block;margin: 5px 0px;' >");
	    	html.push("<span style='margin: 0px 10px;'>商标注册证号</span>");
	    	html.push("<span><input type='text' class='brandTmkCode' name='brandTmkCode' value='' ></span>");
	    	html.push("<span style='margin: 0px 10px 0px 60px'>适用类别</span>");
	    	html.push("<span><input type='text' class='brandTmkType' name='brandTmkType' value='' ></span>");
	    	html.push("</div>");
	    	html.push("<span style='margin-left: 20px;' class='addTmkCodeType' ><input type='button' style='color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;cursor:pointer;' value='+' onclick='addProductBrandTrademarkInfo();' ></span>");
	    }else {
	    	var productBrandTrademarkInfoList ;
	    	<c:if test="${not empty productBrandTrademarkInfoList }">
	    		productBrandTrademarkInfoList = ${productBrandTrademarkInfoList };
			</c:if>
	    	for(var i=0;i<productBrandTrademarkInfoList.length;i++) {
	    		html.push("<div class='tmkCodeType' style='display: inline-block;margin: 5px 0px;' >");
	    		html.push("<span style='margin: 0px 10px;'>商标注册证号</span>");
		    	html.push("<span><input type='text' class='brandTmkCode' name='brandTmkCode' value='"+productBrandTrademarkInfoList[i].tmkCode+"' ></span>");
		    	html.push("<span style='margin: 0px 10px 0px 60px'>适用类别</span>");
		    	html.push("<span><input type='text' class='brandTmkType' name='brandTmkType' value='"+productBrandTrademarkInfoList[i].tmkType+"' ></span>");
		    	html.push("</div>");
		    	if(productBrandTrademarkInfoList.length-1 != i) {
		    		html.push("<br/>");
		    	}else {
		    		html.push("<span style='margin-left: 20px;' class='addTmkCodeType' ><input type='button' style='color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;cursor:pointer;' value='+' onclick='addProductBrandTrademarkInfo();' ></span>");
					if(i != 0) {
			    		html.push("<span style='margin-left: 10px;' class='delTmkCodeType' ><input type='button' style='color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;cursor:pointer;' value='-' onclick='delProductBrandTrademarkInfo();' ></span>");
		    		}
		    	}
	    	}
	    }
	    $("#tmkTypeGroup").html(html.join(""));
	});	

	function addProductBrandTrademarkInfo() {
		var tmkFlag = false;
		$(".tmkCodeType").each(function(index, element){
			if($(this).find("[name='brandTmkCode']").val() == '' || !/^[A-Za-z0-9]+$/.test($(this).find("[name='brandTmkCode']").val()) ) {
				tmkFlag = true;
				return false;
			}
			if($(this).find("[name='brandTmkType']").val() == '' || !/^[0-9]+$/.test($(this).find("[name='brandTmkType']").val()) ) {
				tmkFlag = true;
				return false;
			}
		});
		if(tmkFlag) {
			$.ligerDialog.question("商标类别只能填写数字和字母且适用类别只能数字！");
			return;
		} 
		var html = [];
		html.push("<br/>")
		html.push("<div class='tmkCodeType' style='display: inline-block;margin: 5px 0px;' >");
    	html.push("<span style='margin: 0px 10px;'>商标注册证号</span>");
    	html.push("<span><input type='text' class='brandTmkCode' name='brandTmkCode' value='' ></span>");
    	html.push("<span style='margin: 0px 10px 0px 60px'>适用类别</span>");
    	html.push("<span><input type='text' class='brandTmkType' name='brandTmkType' value='' ></span>");
    	html.push("</div>");
    	$(".tmkCodeType:last").after(html.join(""));
    	if($(".delTmkCodeType").length == 0 ) {
    		$(".addTmkCodeType").after("<span style='margin-left: 10px;' class='delTmkCodeType' ><input type='button' style='color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;cursor:pointer;' value='-' onclick='delProductBrandTrademarkInfo();' ></span>");
    	}
	}
	function delProductBrandTrademarkInfo() {
		$(".tmkCodeType:last").prev().remove();
		$(".tmkCodeType:last ").remove();
		if($(".tmkCodeType").length == 1) {
			$(".delTmkCodeType").remove();
		}
	}
	
	$(function() {
		$("#listbox1").ligerListBox({
	        isShowCheckBox: false,
	        isMultiSelect: true,
	        height: 100,
	        width:200,
	        url:"${pageContext.request.contextPath}/productType/getProductType4ListBox.shtml?productTypeIds="+"${ProductBrand.productTypeGroup}"
	    });
		
		$("#listbox2").ligerListBox({
	        isShowCheckBox: false,
	        isMultiSelect: true,
	        height: 150,
	        width:200,
	        url:"${pageContext.request.contextPath}/productType/getProductType4ListBox.shtml?parentId=1"
	    });
	});
		
	//移动到左边
	function moveToLeft() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box2.getSelectedItems();
		if (!selecteds || !selecteds.length)
			return;
		box2.removeItems(selecteds);
		box1.addItems(selecteds);
	}
	//移动到右边
	function moveToRight() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box1.getSelectedItems();
		if (!selecteds || !selecteds.length)
			return;
		box1.removeItems(selecteds);
		box2.addItems(selecteds);
	}
	//全部移动到左边
	function moveAllToLeft() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box2.data;
		if (!selecteds || !selecteds.length)
			return;
		box1.addItems(selecteds);
		box2.removeItems(selecteds);
	}
	//全部移动到右边
	function moveAllToRight() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box1.data;
		if (!selecteds || !selecteds.length)
			return;
		box2.addItems(selecteds);
		box1.removeItems(selecteds);
	}
	
	$(function() {
	    $.metadata.setType("attr", "validate");
	    
	    $.validator.addMethod("catalogUnique", function(value, element) {
	    	var result=false;
			$.ajax({
				url : "${pageContext.request.contextPath}/productBrand/checkBrandCatalog.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id : $("#id").val(),
					catalog:$("#catalogvalue").val()
				},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode=='0000'){
						result=true;
					}else{
						result=false;
					}
				},
				error : function() {
					alert('操作超时，请稍后再试！');
				}
			});
			return result;
	    }, "品牌目录已经存在");
	    
	    $.validator.addMethod("nameUnique", function(value, element) {
	    	var result=false;
			$.ajax({
				url : "${pageContext.request.contextPath}/productBrand/checkBrandName.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id : $("#id").val(),
					name:$("#brandname").val()
				},
				timeout : 30000,
				success : function(data) {
					if(data.returnCode=='0000'){
						result=true;
					}else{
						result=false;
					}
				},
				error : function() {
					alert('操作超时，请稍后再试！');
				}
			});
			return result;
	    }, "品牌已存在无法添加");
	    
	    $.validator.addMethod("nameZhUnique", function(value, element) {
	    	var result = false;
	    	if($("#nameZh").val() != '') {
				$.ajax({
					url: "${pageContext.request.contextPath}/productBrand/checkProductBrandName.shtml",
					type: 'POST',
					dataType: 'json',
					async: false,
					data: {id : $("#id").val(), nameZh : $("#nameZh").val()},
					success: function(data) {
						if(data.code == '200' && data.count == '0'){
							result = true;
						}else{
							result = false;
						}
					},
					error : function() {
						alert('操作超时，请稍后再试！');
					}
				});
	    	}else {
	    		result = true;
	    	}
	    	return result;
	    }, "品牌添加失败，该品牌中文名称已存在");
	    
	    $.validator.addMethod("nameEnUnique", function(value, element) {
	    	var result = false;
	    	if($("#nameEn").val() != '') {
		    	$.ajax({
					url: "${pageContext.request.contextPath}/productBrand/checkProductBrandName.shtml",
					type: 'POST',
					dataType: 'json',
					async: false,
					data: {id : $("#id").val(), nameEn : $("#nameEn").val()},
					success: function(data) {
						if(data.code == '200' && data.count == '0'){
							result = true;
						}else{
							result = false;
						}
					},
					error : function() {
						alert('操作超时，请稍后再试！');
					}
				});
	    	}else {
	    		result = true;
	    	}
	    	return result;
	    }, "品牌添加失败，该品牌英文名称已存在");
	    
        var v = $("#form1").validate({
            errorPlacement: function(lable, element) {
                $(element).ligerTip({
					content: lable.html(),width: 220
				});
            },
            success: function(lable) {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function(form) {   
            	var isValidateOk=true;
        		var nameZh = document.getElementById("nameZh");//中文
        		var nameEn = document.getElementById("nameEn");//英文
        		var catalogvalue = document.getElementById("catalogvalue");//品牌目录
        		var log = document.getElementById("logo");
        		//判断中文英文
        		if(($.trim(nameZh.value)=="") && ($.trim(nameEn.value)=="")) {
        			$("#nameZh").ligerTip({ content: "中文、英文，两者至少填一个。"});
        			nameZh.focus();
        			$("html,body").animate({scrollTop:$("#nameZh").offset().top},100);
        			isValidateOk = false;
        		}
        		//判断商标logo
        		if($.trim(log.value)=="") {
        			$.ligerDialog.question('请上传logo图片！');
        			isValidateOk = false;
        			return;
        		}
        		//判断使用品类
        		var productTypeGroup = liger.get("listbox1").data;
            	if(productTypeGroup != null && productTypeGroup.length>0) {
            		var productTypeGroupIds="";
                	for (var i=0;i<productTypeGroup.length;i++) {
                		productTypeGroupIds=productTypeGroupIds+","+productTypeGroup[i].id;
                	}
                	$("#productTypeGroup").val(productTypeGroupIds.substring(1));
            	}else {
            		$.ligerDialog.question('请选择使用品类！');
            		isValidateOk = false;
            		return;
            	}
            	//判断商标类别
				var html = [];
            	var tmkCodeArray = [];
            	var tmkCodeFlag = false; 
            	var tmkCodeNullFlag = false;
            	var tmkTypeFlag = false;
            	var tmkTypeNullFlag = false;
            	for (var i = 0; i < 10; i++) {
					
				}
        		$(".tmkCodeType").each(function(index, element){
        			if($(this).find("[name='brandTmkCode']").val() == '' && $(this).find("[name='brandTmkType']").val() == '' ) {
        				tmkCodeNullFlag = true;
        				tmkTypeNullFlag = true;
        				return false;
        			}
        			if(!/^[A-Za-z0-9]+$/.test($(this).find("[name='brandTmkCode']").val()) && !/^[0-9]+$/.test($(this).find("[name='brandTmkType']").val()) ) {
        				tmkCodeFlag = true;
        				tmkTypeFlag = true;
        				return false;
        			}
        			if($(this).find("[name='brandTmkCode']").val() == '' ) {
        				tmkCodeNullFlag = true;
        				return false;
        			}
        			if($(this).find("[name='brandTmkType']").val() == '' ) {
        				tmkTypeNullFlag = true;
        				return false;
        			}
        			if(!/^[A-Za-z0-9]+$/.test($(this).find("[name='brandTmkCode']").val()) ) {
        				tmkCodeFlag = true;
        				return false;
        			} 
        			if(!/^[0-9]+$/.test($(this).find("[name='brandTmkType']").val()) ) {
        				tmkTypeFlag = true;
        				return false;
        			}
        			html.push($(this).find("[name='brandTmkCode']").val()+"-"+$(this).find("[name='brandTmkType']").val());
        			tmkCodeArray.push($(this).find("[name='brandTmkCode']").val());
        		});
        		if(tmkCodeNullFlag && tmkTypeNullFlag) {
        			$.ligerDialog.question("商标注册证号、适用类别都不能为空！");
        			isValidateOk = false;
        			return;
        		}else if(tmkTypeFlag) {
        			$.ligerDialog.question("适用类别都只能填写数字！");
        			isValidateOk = false;
        			return;
        		}else if(tmkCodeNullFlag) {
        			$.ligerDialog.question("商标注册证号不能为空！");
        			isValidateOk = false;
        			return;
        		}else if(tmkCodeFlag) {
        			$.ligerDialog.question("商标注册证号只能填写数字和字母！");
        			isValidateOk = false;
        			return;
        		} else if(tmkTypeNullFlag) {
        			$.ligerDialog.question("适用类别不能为空！");
        			isValidateOk = false;
        			return;
        		}else if(tmkTypeFlag) {
        			$.ligerDialog.question("适用类别只能填写数字！");
        			isValidateOk = false;
        			return;
        		}else {
        			/* var obj = {};
        			for(var i=0;i<tmkCodeArray.length;i++) {
        				if(obj[tmkCodeArray[i]]) {
        					$.ligerDialog.question("商标注册号重复！");
        					isValidateOk = false;
        					return;
        				}
        				obj[tmkCodeArray[i]] = 1;
        			} */
        			var ajaxFlag = false;
	        		$.ajax({
						url: "${pageContext.request.contextPath}/productBrand/validateTmkCodeType.shtml",
						type: 'POST',
						dataType: 'json',
						async: false,
						data: {id : $("#id").val(), tmkCodeStr : tmkCodeArray.join(",")},
						success: function(data) {
							if(data.code != '200'){
								$.ligerDialog.question(data.msg);
								ajaxFlag = true;
							}/* else{
								if(data.count > 0) {
									$.ligerDialog.question("商标注册号已存在！");
									ajaxFlag = true;
								}	
							} */
						},
						error : function() {
							$.ligerDialog.question("操作超时，请稍后再试！");
						}
					});
	        		if(ajaxFlag) {
	        			isValidateOk = false;
	        			return;
	        		}else {
	        			$("#tmk").val(html.join(","));
	        		}
        		}
        		//判断商标证图片
            	var pictures = [];
        		var lis = $(".upload_image_list").find("li");
        		lis.each(function(index, item) {
        			var path = $("img", item).attr("path");
        			var def = ($(".def", item).length == 1 ? "1" : "0");
        			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
        			pictures.push(pic);
        		});
        		if(pictures.length > 0) {
        			$("#brandTmkPics").val(JSON.stringify(pictures));
        		}else {
        			$("#brandTmkPics").val("");
        		}
            	//判断状态
            	var statuDe = liger.get("depart").getValue();
            	if($.trim(statuDe)=="") {
            		$.ligerDialog.question('请选择状态！');
            		isValidateOk = false;
            		return;
            	}else {
            		$("#status").val(statuDe);
            	}
            	//判断品牌目录
            	if($.trim(catalogvalue.value)=="") {
            		$("#catalogvalue").ligerTip({ content: "请输入品牌目录！"});
        			catalogvalue.focus();
        			isValidateOk = false;
        		}
            	if(isValidateOk) {
            		form.submit();
            	}else{
		    		$("html,body").animate({scrollTop:$("body").offset().top},0);
            	}
            }
        });
	});    
	
	function brandNameIsHased() {
		var barndName = $("#brandname").val();
		var catalog = $("#catalogvalue").val();
		if ($.trim(barndName) == "") {
			return false;
		}
		if ($.trim(catalog) == "") {
			return false;
		}
		var returnData;
		$.ajax({
			url : "${pageContext.request.contextPath}/productBrand/checkName.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {
				name : barndName,
				catalog:catalog
			},
			timeout : 30000,
			success : function(data) {
				returnData=data;
			},
			error : function() {
				alert('操作超时，请稍后再试！');
			}
		});
		return returnData;
	}
	
	$(function() { 
		var manager;
		 var dataset = ${resulList};
		 var values = "${ProductBrand.status}";
		 if(!values){
			 values = 1; 
		 }
		 manager = $("#depart").ligerComboBox({
 		   data: dataset,
           textField: 'text',
           selectBoxWidth: 100,
           value: '0',
           resize:true
        });
		manager.selectValue(values);
	}); 
	
	function closewindow() {
		window.opener= null;
		window.open("","_self");
		window.close();
		if(window){
		   window.location.href="about:blank";
		}
	}
	
	function ajaxFileUpload() {
		$.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "brandTmkPic",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					uploadImageList.addImage(contextPath + "/file_servelt", result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
	}
	
	function ajaxFileUploadLogo() {
		$.ajaxFileUpload({
			url: contextPath + '/service/common/ajax_upload.shtml',
			secureuri: false,
			fileElementId: "logoPicFile",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
					$("#logo").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				alert("服务异常");
			}
		});
	}
	
  </script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/service/prod/product_brand/editbrandsave.shtml"  >
		<input id="id" name="id" type="hidden" value="${ProductBrand.id}" />
		<input id="tmk" name="tmk" type="hidden" value="" />
		<table class="gridtable">
			<tr>
				<td class="title">品牌名称 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input id="brandname" name="name" type="text" value="${ProductBrand.name}" style="float:left;width: 200px;" validate="{required:true,nameUnique:true}" />
				</td>
			</tr>
			<tr>
				<td class="title">中文</td>
				<td align="left" class="l-table-edit-td"><input id="nameZh" name="nameZh" type="text" value="${ProductBrand.nameZh}" style="float:left;width: 200px;" validate="{nameZhUnique:true}" />
				</td>
			</tr>
			<tr>
				<td class="title">英文</td>
				<td align="left" class="l-table-edit-td"><input id="nameEn" name="nameEn" type="text" value="${ProductBrand.nameEn}" style="float:left;width: 200px;" validate="{nameEnUnique:true}" />
				</td>
			</tr>
			<tr>
				<td class="title">LOGO图片 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<div><img id="logoPic" style="width: 100px;height: 50px" alt="" src="${pageContext.request.contextPath}/file_servelt${ProductBrand.logo}"></div>
	    			<div style="float: left;margin: 10px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="logo" name="logo" type="hidden" value="${ProductBrand.logo}">
				</td>
			</tr>
			<tr>
				<td class="title">适用品类<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<input type="hidden" id="productTypeGroup" name="productTypeGroup" value="${ProductBrand.productTypeGroup}">
		           	<div style="margin:4px;float:left;">
	           			<div style="margin-bottom: 13px;">选中：</div>
				    	<div id="listbox1" class="l-listbox"></div>  
				    </div>
				    <div style="margin:4px;float:left;" class="middle">
				        <input type="button" style="margin-top: 20px;width: 25px;height: 25px" onclick="moveToLeft()" value="<">
				        <input type="button" style="margin-top: 5px;width: 25px;height: 25px" onclick="moveToRight()" value=">">
				        <input type="button" style="margin-top: 5px;width: 25px;height: 25px" onclick="moveAllToLeft()" value="<<">
				    	<input type="button" style="margin-top: 5px;width: 25px;height: 25px" onclick="moveAllToRight()" value=">>">
				    </div>
				    <div style="margin:4px;float:left;" class="test">
				    	<div id="listbox2" class="l-listbox" ></div> 
				    </div>
				</td>
			</tr>
			<tr>
				<td class="title">商标序号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<div id="tmkTypeGroup" style="border-style: none;" >
						
					</div>
				</td>
			</tr>
			<tr>
				<td class="title">商标证图片</td>
				<td colspan="3">
	    			<t:imageList name="pictures" list="${brandTmkPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
	    			<div style="float: left;margin: 10px;">
	    				<input style="position:absolute; opacity:0;" type="file" id="brandTmkPic" name="file" onchange="ajaxFileUpload();" />
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="brandTmkPics" name="brandTmkPics" type="hidden" value="${brandTmkPics}">
	    		</td>
			</tr>
			<tr>
               <td class="title"  style="text-align: center;" >状态<span class="required">*</span></td>  
	               <td class="statu_value" colspan="5" height="40px">
						<select name="depart"  id = "depart"></select>
						<input type="hidden" id="status" name="status" value="${ProductBrand.status}">
				   </td> 
            </tr>
            <tr>
               <td class="title"  style="text-align: center;" >品牌目录<span class="required">*</span></td>  
               <td class="catalog_value" height="40px">
               		<div class="l-brandname" style="float:left; "><input id="catalogvalue"  validate="{required:true,catalogUnique:true}" type="text" name = "catalog" value="${ProductBrand.catalog}"/></div>
               </td>  
            </tr>
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="提交"  /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
