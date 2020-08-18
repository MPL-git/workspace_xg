function addProduct() {
	//alert($(window).height() + ":" + $(window).width());
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "新增商品",
		name: "INSERT_WINDOW",
		url: contextPath + "/service/product/add.shtml",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null,
		buttons: [
			{text: "保存",
			 cls: "l-dialog-btn-highlight",
			 onclick: function(item, dialog) {
				 var result = $("#INSERT_WINDOW")[0].contentWindow.submit_product();
				 //var result = $("#INSERT_WINDOW")[0].contentWindow.onSubmit();
				 if(result.RESULT_CODE != 0) {
					 //$.ligerDialog.warn(result.RESULT_MESSAGE);
				 } else {
					 dialog.close();
					$.ligerDialog.success(result.RESULT_MESSAGE);
					 searchData();
				 }
			 }
			},
			{text: "取消",
			 onclick: function (item, dialog) {dialog.close();}
			}
		]
	});
}

function editProduct(productId) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "编辑商品",
		name: "EDIT_WINDOW",
		url: contextPath + "/service/product/edit.shtml?PRODUCT_ID=" + productId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null,
		buttons: [
			{text: "保存",
			 cls: "l-dialog-btn-highlight",
			 onclick: function(item, dialog) {
				 var result = $("#EDIT_WINDOW")[0].contentWindow.submit_product_edit();
				 if(result.RESULT_CODE != 0) {
					 $.ligerDialog.warn(result.RESULT_MESSAGE);
				 } else {
					 dialog.close();
					 $.ligerDialog.success(result.RESULT_MESSAGE);
					 searchData();
				 }
			 }
			},
			{text: "取消",
			 onclick: function (item, dialog) {dialog.close();}
			}
		]
	});
}

function checkeditProduct(productId) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "审核商品",
		name: "EDIT_WINDOW",
		url: contextPath + "/service/product/checkedit.shtml?PRODUCT_ID=" + productId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null,
		buttons: [
			{text: "保存",
			 cls: "l-dialog-btn-highlight",
			 onclick: function(item, dialog) {
				 var result = $("#EDIT_WINDOW")[0].contentWindow.checksubmit_product();
				 
				 if(result.RESULT_CODE != 0) {
					 $.ligerDialog.warn(result.RESULT_MESSAGE);
				 } else {
					 dialog.close();
					 $.ligerDialog.success(result.RESULT_MESSAGE);
					 searchData();
				 }
			 }
			},
			{text: "取消",
			 onclick: function (item, dialog) {dialog.close();}
			}
		]
	});
}
function delProduct() {
	
}

function deleteProduct(productId) {
	$.ligerDialog.confirm("确定要删除该商品吗？", function(YoN) {
		if(YoN) {
			var data = {PRODUCT_ID: productId};
			$.ajax({
				type: "POST",
				url: contextPath + "/service/product/delete_product.shtml",
				data: data,
				dataType: 'json',
				success: function(result) {
					//alert(JSON.stringify(result));
					if(result.RESULT_CODE == 0) {
						searchData();
					} else {
						$.ligerDialog.alert(result.RESULT_MESSAGE);
					}
				}
			});
		}
	});
}

function upDownProduct(productId, type) {
	 
	if(type=="UP")
		{ 
	var msg="确定要上架该商品吗？";}
	if(type=="DOWN"){var msg="确定要下架该商品吗？";}
		$.ligerDialog.confirm(msg, function(YoN) {
	var data = {PRODUCT_ID: productId, ACTION_TYPE: type};
	//alert(JSON.stringify(data));
	$.ajax({
		type: "POST",
		url: contextPath + "/service/product/up_down_product.shtml",
		data: data,
		dataType: 'json',
		success: function(result) {
			//alert(JSON.stringify(result));
			if(result.RESULT_CODE == 0) {
				searchData();
			} else {
				$.ligerDialog.alert(result.RESULT_MESSAGE);
			}
		}
	});
	});
		}

function tipsClear(obj) {
	$(obj).next().hide();
}

function tipsPrompt(obj, type, text) {
	var tips = $(obj).next();
	if(tips.length > 0) {
		tips.removeClass();
	} else {
		tips = $("<span></span>");
		$(obj).parent().append(tips);
	}
	tips.addClass(type);
	tips.text(text);
	tips.show();
}

function verifyInput(obj, reg, text) {
	var val = $(obj).val();
	var ret = reg.test(val);
	if(ret) {
		tipsPrompt(obj, "correct", "");
	} else {
		tipsPrompt(obj, "error", text);
	}
	return ret;
}
 
function checkEditInput() {
	var check = true;
	$("span.info").hide();
	$("span.error").hide();
	var cls3=$("#cls3").val();
	if (cls3 == '' || cls3 == null){
	  tipsPrompt("#cls3", "error", "请选择商品类型");
	  check = false && check;
	} 
	var selPingpai=$("#selPingpai").val();
	if (selPingpai == '' || selPingpai == null){
	  tipsPrompt("#selPingpai", "error", "请选择商品品牌");
	  check = false && check;
	}
	check = verifyInput("#PRODUCT_NAME", /^.{2,}$/, "无效的标题,至少2个字符") && check;
	check = verifyInput("#LEAST_AMOUNT", /^(([1-9]\d{0,9})|0)(\.\d{1,3})?$/, "无效的重量") && check;
	if($("#IF_OPEN").val()==0)
	{
	check = verifyInput("#BGN_PRICE", /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/, "无效的金额") && check;
	check = verifyInput("#END_PRICE", /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/, "无效的金额") && check; 
	}
	check = verifyInput("#PRODUCT_NAME", /^.{2,}$/, "无效的标题,至少2个字符") && check;
	 
	var lis = $(".upload_image_list").find("li");
	tipsClear("#PRODUCT_PICTURE");
	if(lis.length == 0 || lis.length > 5) {
		tipsPrompt("#PRODUCT_PICTURE", "error", "至少上传1张商品图片,最多上传5张商品图片");
		check = false && check;
	}
    
	if($("#IF_OPEN").val()==0)
	{
		var price = $.trim($("#BGN_PRICE").val());
	var tuangouPrice = $.trim($("#END_PRICE").val());
	if(price != "" && tuangouPrice != "") {
		price = parseFloat(price);
		tuangouPrice = parseFloat(tuangouPrice);
		if(tuangouPrice >= price) {
			tipsPrompt($("#END_PRICE"), "error", "优惠价不能大于等于原价");
			check= false;
		} 
	}
}

	return check;
}

function submit_product() {  
	var ret = {RESULT_CODE: 1, RESULT_MESSAGE: "请完整填写商品信息"};
	var check = checkEditInput(); 
	if(check) {
		var data = {
				PRODUCT_ID: $("#PRODUCT_ID").val(),
				PRODUCT_NAME: $("#PRODUCT_NAME").val(),    
				LEAST_AMOUNT: $("#LEAST_AMOUNT").val(), 
				BGN_PRICE: $("#BGN_PRICE").val(),
				END_PRICE: $("#END_PRICE").val() ,				
				PRODUCT_TYPE_ID: $("#cls3").val()
		};
		data["BRAND_ID"] = $("#selPingpai").val(); 
		data["PRODUCT_SPECS"] = getSpecs();
		data["PRODUCT_PRICES"] = $("#product_prices").val();
		data["PRODUCT_CATALOG"] = $("#PRODUCT_CATALOG").val();
		
		var pictures = [];
		var lis = $(".upload_image_list").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {PICTURE_PATH: path, IS_PRIMARY: def, PICTURE_ORDER: index + 1};
			pictures.push(pic);
		});
		
		//alert(JSON.stringify(pictures));
		data["PRODUCT_PICTURES"] = pictures;
		data["PRODUCT_DESC"] = $("#PRODUCT_DESC").val();
		
		$.ajax({
			type: "POST",
			async: false,
			url: contextPath + "/service/product/save_product.shtml",
			data: {JSON: JSON.stringify(data), ACTION_TYPE: $("#ACTION_TYPE").val()},
			dataType: 'json',
			success: function(result) {
				//alert(JSON.stringify(result));
				if(result.RESULT_CODE == 0) {
					ret = result;
				} else {
					//alert(result.RESULT_MESSAGE);
				}
			}
		});
		
		//alert(JSON.stringify(data));
	}
	return ret;
}
function submit_product_edit() { 
	var ret = {RESULT_CODE: 1, RESULT_MESSAGE: "请完整填写商品信息"};
	var check = checkEditInput(); 
	if(check) {
		var data = {
				PRODUCT_ID: $("#PRODUCT_ID").val(),
				PRODUCT_NAME: $("#PRODUCT_NAME").val(),    
				LEAST_AMOUNT: $("#LEAST_AMOUNT").val(), 
				BGN_PRICE: $("#BGN_PRICE").val(),
				END_PRICE: $("#END_PRICE").val() ,
				PRODUCT_TYPE_ID: $("#cls3").val() 
		};
		data["BRAND_ID"] = $("#selPingpai").val(); 
		 if(getSpecsedit()=='[]') 
			{ data["PRODUCT_SPECS"] = getSpecs();}
		else
		{
			 data["PRODUCT_SPECS"] = getSpecsedit();
		}
		data["PRODUCT_PRICES"] = $("#product_prices").val();
		data["PRODUCT_CATALOG"] = $("#PRODUCT_CATALOG").val();
		
		var pictures = [];
		var lis = $(".upload_image_list").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {PICTURE_PATH: path, IS_PRIMARY: def, PICTURE_ORDER: index + 1};
			pictures.push(pic);
		});
		
		//alert(JSON.stringify(pictures));
		data["PRODUCT_PICTURES"] = pictures;
		data["PRODUCT_DESC"] = $("#PRODUCT_DESC").val(); 
		
		$.ajax({
			type: "POST",
			async: false,
			url: contextPath + "/service/product/save_product.shtml",
			data: {JSON: JSON.stringify(data), ACTION_TYPE: $("#ACTION_TYPE").val()},
			dataType: 'json',
			success: function(result) {
				//alert(JSON.stringify(result));
				if(result.RESULT_CODE == 0) {
					ret = result;
				} else {
					//alert(result.RESULT_MESSAGE);
				}
			}
		});
		
		//alert(JSON.stringify(data));
	}
	return ret;
} 
 	function getSpecs(){
	    var obj=[]; 
	    $("tr[isadd='add']", "#tblist").each(function(){
			 obj.push({SPEC_ID:$(this).find("span").attr('data'),SPEC_VALUE:$(this).find("input[type='text']").val()});
			});
		return JSON.stringify(obj);	
	}
 	function getSpecsedit(){
	    var obj=[]; 
	    $("tr[isdata='add']", "#tblist").each(function(){
			 obj.push({SPEC_ID:$(this).find("span").attr('data'),SPEC_VALUE:$(this).find("input[type='text']").val()});
			});
		return JSON.stringify(obj);	
	}		
function calculateDiscount() {
	tipsClear($("#END_PRICE"));
	var price = $.trim($("#BGN_PRICE").val());
	var tuangouPrice = $.trim($("#END_PRICE").val());
	if(price != "" && tuangouPrice != "") {
		price = parseFloat(price);
		tuangouPrice = parseFloat(tuangouPrice);
		if(tuangouPrice >= price) {
			tipsPrompt($("#END_PRICE"), "error", "优惠价不能大于等于原价");
			return false;
		} 
	}
	return true;
}

function checksubmit_product() {
	  
		var data = {
				PRODUCT_ID: $("#PRODUCT_ID").val(),
				CHECK_REMARK: $("#CHECK_REMARK").val(),    
				STATUS: $("#STATUS").val(),
				PRODUCT_TYPE_ID:$("#cls3").val(),
				BRAND_ID:$("#selPingpai").val()
		}; 
		$.ajax({
			type: "POST",
			async: false,
			url: contextPath + "/service/product/save_product.shtml",
			data: {JSON: JSON.stringify(data), ACTION_TYPE: $("#ACTION_TYPE").val()},
			dataType: 'json',
			success: function(result) {
				//alert(JSON.stringify(result));
				if(result.RESULT_CODE == 0) {
					ret = result;
				} else {
					$.ligerDialog.alert(result.RESULT_MESSAGE);
				}
			}
		});
	 
	return ret;
}
function ajaxFileUpload() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "PRODUCT_PICTURE",
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

