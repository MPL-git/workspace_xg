<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript">
	
</script>

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
var contextPath = "${pageContext.request.contextPath}";
	$(function() {
		$("#form1").ligerForm();
	});
	function save() {
		var productTypeGroup = liger.get("listbox1").data;
		var productTypeGroupIds="";
    	for (var i=0;i<productTypeGroup.length;i++)
    	{
    		productTypeGroupIds=productTypeGroupIds+","+productTypeGroup[i].id;
    	}
    	if(productTypeGroup.length>0){
    		$("#productTypeGroup").val(productTypeGroupIds.substring(1));
    	}else{
    		$("#productTypeGroup").val("");
    	}
    	
//     	获取图片
    	var pictures = [];
		var lis = $(".upload_image_list").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
			pictures.push(pic);
		});
		$("#brandTmkPics").val(JSON.stringify(pictures));
	}
</script>

<script type="text/javascript">


$(function ()  { 
	    $.metadata.setType("attr", "validate");
	    //添加品牌商品类别验证
	    $.validator.addMethod("productTypeGroupRequired", function(value, element) {   
	    	var productTypeGroup = liger.get("listbox1").data;
	    	return (productTypeGroup.length>0);
	    }, "请选择商品类别");
	    
	    //中英文名称验证
	    $.validator.addMethod("nameEnZhRequired", function(value, element) {   
	    	if($.trim($("#nameEn").val()) == ""
				&& $.trim($("#nameZh").val()) == ""){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    }, "中文、英文，两者至少填一个。");
	    
	    
	    //驳回原因必填
	    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
	    	if($("input[type=radio][name='status'][value=3]").attr("checked")&&$.trim($("#auditRemarks").val())==""){
     			return false;
     		}else{
     			return true;
     		}
	    }, "请注明驳回原因");
	    
	    var val = $("#form1").validate({
							errorPlacement : function(lable, element) {
								if ($(element).attr('name') == 'productTypeGroup') {
									$("#listbox2").ligerTip({
										content : lable.html()
									});
								} else {

									$(element).ligerTip({
										content : lable.html()
									});
								}
							},
							success : function(lable) {
								lable.ligerHideTip();
								lable.remove();
							},
							submitHandler : function(form) {
								save();
								var isValidateOk = true;
								var hasSelectTmkType = false;
								$("input[type=checkbox][name='tmkTypeGroup']")
										.each(function() {
											if ($(this).attr("checked")) {
												hasSelectTmkType = true;
											}
										});
								if (!hasSelectTmkType) {
									$(
											$(
													$("#tmkTypeGroup")
															.children("div")
															.get(0)).children(
													"table").get(0)).ligerTip({
										content : "必须选择商标类别"
									});
									isValidateOk = false;
								}

								//如果品牌已存在则提示
								if (brandNameIsHased()) {
									$("#name").ligerTip({
										content : "品牌已存在"
									});
									isValidateOk = false;
								}

								if (isValidateOk) {
									form.submit();
								} else {
									$("html,body").animate({
										scrollTop : $("body").offset().top
									}, 0);
								}

							}
						});

		//如果品牌已存在则提示
		if (brandNameIsHased()) {
			$("#name").ligerTip({
				content : "品牌已存在"
			});
		}

	});

	$(function() {
		$("#listbox1")
				.ligerListBox(
						{
							isShowCheckBox : false,
							isMultiSelect : true,
							height : 100,
							width : 200,
							url : "${pageContext.request.contextPath}/productType/getProductType4ListBox.shtml?productTypeIds="
									+ "${productBrandTmp.productTypeGroup}"
						});
		$("#listbox2")
				.ligerListBox(
						{
							isShowCheckBox : false,
							isMultiSelect : true,
							height : 150,
							width : 200,
							url : "${pageContext.request.contextPath}/productType/getProductType4ListBox.shtml?parentId=1"
						});

		var dataGrid = [ {
			id : 1,
			name : '第1类'
		}, {
			id : 2,
			name : '第2类'
		}, {
			id : 3,
			name : '第3类'
		}, {
			id : 4,
			name : '第4类'
		}, {
			id : 5,
			name : '第5类'
		}, {
			id : 6,
			name : '第6类'
		}, {
			id : 7,
			name : '第7类'
		}, {
			id : 8,
			name : '第8类'
		}, {
			id : 9,
			name : '第9类'
		}, {
			id : 10,
			name : '第10类'
		}, {
			id : 11,
			name : '第11类'
		}, {
			id : 12,
			name : '第12类'
		}, {
			id : 13,
			name : '第13类'
		}, {
			id : 14,
			name : '第14类'
		}, {
			id : 15,
			name : '第15类'
		}, {
			id : 16,
			name : '第16类'
		}, {
			id : 17,
			name : '第17类'
		}, {
			id : 18,
			name : '第18类'
		}, {
			id : 19,
			name : '第19类'
		}, {
			id : 20,
			name : '第20类'
		}, {
			id : 21,
			name : '第21类'
		}, {
			id : 22,
			name : '第22类'
		}, {
			id : 23,
			name : '第23类'
		}, {
			id : 24,
			name : '第24类'
		}, {
			id : 25,
			name : '第25类'
		}, {
			id : 26,
			name : '第26类'
		}, {
			id : 27,
			name : '第27类'
		}, {
			id : 28,
			name : '第28类'
		}, {
			id : 29,
			name : '第29类'
		}, {
			id : 30,
			name : '第30类'
		}, {
			id : 31,
			name : '第31类'
		}, {
			id : 32,
			name : '第32类'
		}, {
			id : 33,
			name : '第33类'
		}, {
			id : 34,
			name : '第34类'
		}, {
			id : 35,
			name : '第35类'
		}, {
			id : 36,
			name : '第36类'
		}, {
			id : 37,
			name : '第37类'
		}, {
			id : 38,
			name : '第38类'
		}, {
			id : 39,
			name : '第39类'
		}, {
			id : 40,
			name : '第类'
		}, {
			id : 41,
			name : '第41类'
		}, {
			id : 42,
			name : '第42类'
		}, {
			id : 43,
			name : '第43类'
		}, {
			id : 44,
			name : '第44类'
		}, {
			id : 45,
			name : '第45类'
		} ];

		$("#tmkTypeGroup").ligerCheckBoxList({
			data : dataGrid,
			textField : 'name',
			valueField : 'id',
			rowSize : 6

		});

		//设置品类选中
		var tmkTypeGroupIdsStr = "${productBrandTmp.tmkTypeGroup}";
		if (tmkTypeGroupIdsStr != "") {
			var tmkTypeGroupIds = tmkTypeGroupIdsStr.split(",");
			for (var i = 0; i < tmkTypeGroupIds.length; i++) {
				$(
						"input[type=checkbox][name='tmkTypeGroup'][value="
								+ tmkTypeGroupIds[i] + "]").attr("checked",
						'true');
			}

		};
		
		
		$("input[type=checkbox][name='tmkTypeGroup']").bind("change",function(){
			var hasSelectTmkType = false;
			$("input[type=checkbox][name='tmkTypeGroup']")
					.each(function() {
						if ($(this).attr("checked")) {
							hasSelectTmkType = true;
						}
					});
			if (!hasSelectTmkType) {
				$(
						$(
								$("#tmkTypeGroup")
										.children("div")
										.get(0)).children(
								"table").get(0)).ligerTip({
					content : "必须选择商标类别"
				});
				
			}else{
				$(
						$(
								$("#tmkTypeGroup")
										.children("div")
										.get(0)).children(
								"table").get(0)).ligerHideTip();
			}
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
		checkProductTypeGroup();
	}
	//移动到右边
	function moveToRight() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box1.getSelectedItems();
		if (!selecteds || !selecteds.length)
			return;
		box1.removeItems(selecteds);
		box2.addItems(selecteds);
		checkProductTypeGroup();
	}
	//全部移动到左边
	function moveAllToLeft() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box2.data;
		if (!selecteds || !selecteds.length)
			return;
		box1.addItems(selecteds);
		box2.removeItems(selecteds);
		checkProductTypeGroup();
	}
	//全部移动到右边
	function moveAllToRight() {
		var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
		var selecteds = box1.data;
		if (!selecteds || !selecteds.length)
			return;
		box2.addItems(selecteds);
		box1.removeItems(selecteds);
		checkProductTypeGroup();
	}

	function getdatas() {
		var s = liger.get('listbox1').data[0];
		alert(s.id);
		return false;
	}

	function ajaxFileUpload() {
		$.ajaxFileUpload({
			url : contextPath + '/service/common/ajax_upload.shtml',
			secureuri : false,
			fileElementId : "brandTmkPic",
			dataType : 'json',
			success : function(result, status) {
				if (result.RESULT_CODE == 0) {
					uploadImageList.addImage(contextPath + "/file_servelt",
							result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error : function(result, status, e) {
				alert("服务异常");
			}
		});

	}
	function ajaxFileUploadLogo() {
		$.ajaxFileUpload({
			url : contextPath + '/service/common/ajax_upload.shtml',
			secureuri : false,
			fileElementId : "logoPicFile",
			dataType : 'json',
			success : function(result, status) {
				if (result.RESULT_CODE == 0) {
					$("#logoPic").attr("src",
							contextPath + "/file_servelt" + result.FILE_PATH);
					$("#logo").val(result.FILE_PATH);
				} else {
					alert(result.RESULT_MESSAGE);
				}
			},
			error : function(result, status, e) {
				alert("服务异常");
			}
		});

	}

	function brandNameIsHased() {
		var barndName = $("#name").val();
		if ($.trim(barndName) == "") {
			return false;
		}
		var isHased = false;
		$
				.ajax({
					url : "${pageContext.request.contextPath}/productBrand/checkName.shtml",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {
						name : barndName
					},
					timeout : 30000,
					success : function(data) {
						if ("1" == data.hased) {
							isHased = true;
						}
					},
					error : function() {
						alert('操作超时，请稍后再试！');
					}
				});
		return isHased;
	}
	
	function nameEnZhChange(){
		if($.trim($("#nameEn").val()) != ""||$.trim($("#nameZh").val()) != ""){
			$("#nameEn").ligerHideTip();
			$("#nameZh").ligerHideTip();
		}
		
	}
	
	function checkProductTypeGroup(){
		if(liger.get("listbox1").data.length>0){
			$("#listbox2").ligerHideTip();
		}else{
			$("#listbox2").ligerTip({
				content : '请选择商品类别'
			});
		}
	}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/productBrandTmp/auditingSave.shtml">
		<input id="id" name="id" type="hidden" value="${productBrandTmp.id}" />
		<table class="gridtable">
			<tr>

				<td class="title">品牌名称 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td"><input id="name" validate="{required:true}"
					name="name" type="text" value="${productBrandTmp.name}"
					style="float:left;width: 200px;" /></td>
			</tr>
			<tr>
				<td class="title">中文</td>
				<td align="left" class="l-table-edit-td"><input id="nameZh" onkeyup="nameEnZhChange();"  validate="{nameEnZhRequired:true}"
					name="nameZh" type="text" value="${productBrandTmp.nameZh}"
					style="float:left;width: 200px;" /></td>
			</tr>
			<tr>
				<td class="title">英文</td>
				<td align="left" class="l-table-edit-td"><input id="nameEn" onkeyup="nameEnZhChange();"
					name="nameEn" type="text" value="${productBrandTmp.nameEn}" validate="{nameEnZhRequired:true}"
					style="float:left;width: 200px;" /></td>
			</tr>
			<tr>
				<td class="title">LOGO图片<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<div><img id="logoPic" style="width: 100px;height: 100px;" alt="" src="${pageContext.request.contextPath}/file_servelt${productBrandTmp.logo}"></div>
    			<div style="float: left;height: 105px;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="logo" name="logo" type="hidden" value="${productBrandTmp.logo}" validate="{required:true}">
				</td>
			</tr>
			<tr>
				<td class="title">适用品类<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<input  type="hidden" id="productTypeGroup" name="productTypeGroup" value="${productBrandTmp.productTypeGroup}" validate="{productTypeGroupRequired:true}">
		           <div style="margin:4px;float:left;">
		           			 <div style="margin-bottom: 13px;">选中：</div>
					        <div id="listbox1" class="l-listbox">
					        </div>  
				</div>
					    <div style="margin:4px;float:left;" class="middle">
					         <input type="button" style="margin-top: 20px;width: 25px;height: 25px"  onclick="moveToLeft()" value="<">
					         <input type="button" style="margin-top: 5px;width: 25px;height: 25px"  onclick="moveToRight()" value=">">
					         <input type="button" style="margin-top: 5px;width: 25px;height: 25px"  onclick="moveAllToLeft()" value="<<">
					        <input type="button"  style="margin-top: 5px;width: 25px;height: 25px"  onclick="moveAllToRight()" value=">>">
					    </div>
					   <div style="margin:4px;float:left;" id="listbox2">
					       <div id="listbox2" class="l-listbox" ></div> 
					   </div>
					   
				</td>
			</tr>
			<tr>
				<td class="title">商标类别<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<div id="tmkTypeGroup"></div>
				<div id="hitLable"></div>
				</td>
			</tr>
		  	<tr>
    		<td class="title">商标证图片</td>
    		<td colspan="3">
    			<t:imageList name="pictures" list="${brandTmkPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
    			<div style="float: left;height: 105px;margin: 10px;">
    			<input style="position:absolute; opacity:0;" type="file" id="brandTmkPic" name="file" onchange="ajaxFileUpload();" />
    			<a href="javascript:void(0);" style="width:30%;">上传图片</a>
    			</div>
    			<input id="brandTmkPics" name="brandTmkPics" type="hidden" value="">
    		</td>
    	</tr>
			<tr>
				<td class="title">商标类型</td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" value="1" name="tmkType" <c:if test="${productBrandTmp.tmkType==1}">checked="checked"</c:if> >R标已发商标证</span>
					<span><input  type="radio" value="2" name="tmkType" <c:if test="${productBrandTmp.tmkType==2}">checked="checked"</c:if>>TM标受理中</span>
					
				</td>
			</tr>
			<tr>
				<td class="title">审核状态<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" value="2" name="status" <c:if test="${productBrandTmp.status==2}">checked="checked"</c:if> >通过</span>
					<span class="radioClass"><input  type="radio" value="4" name="status" <c:if test="${productBrandTmp.status==4}">checked="checked"</c:if>>待定</span>
					<span><input  type="radio" value="3" name="status" <c:if test="${productBrandTmp.status==3}">checked="checked"</c:if>>驳回</span>
					
				</td>
			</tr>
			<tr>
				<td class="title">驳回原因<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td"><textarea rows=5
						id="auditRemarks" name="auditRemarks" cols="50" class="text" validate="{auditRemarksRequired:true}"  >${productBrandTmp.auditRemarks}</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">品牌目录 <span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<input validate="{required:true}"
					id="catalog" name="catalog" type="text"
					value="${productBrandTmp.catalog}"
					style="float:left;width:300px;" >
				</td>
				
			</tr>
			<tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="submit" id="Button1"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
