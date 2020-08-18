<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
.upload_image_list li a{display:none !important}
</style>
<script type="text/javascript">
var viewerPictures;
var pics="${mchtSettledApplyPics}".replace('[','').replace(']','').replace(/, /g,',').split(',');
var mchtSettledApply_viewer;
$(function ()  {
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {});
	mchtSettledApply_viewer = new Viewer(document.getElementById('mchtSettledApply_viewer'), {});
	
	getProvinceList();
	getCityList(${mchtSettledApplyCustom.province});
	getCountyList(${mchtSettledApplyCustom.city});  
	
	$("#province").val(${mchtSettledApplyCustom.province});
	$("#city").val(${mchtSettledApplyCustom.city});
	$("#county").val(${mchtSettledApplyCustom.county});
	
	$.metadata.setType("attr", "validate");

	var v = $("#form1").validate({
	            	
		errorPlacement: function (lable, element)
		{   
        	var elementType=$(element).attr("type");

        	if($(element).hasClass("l-text-field")){
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}
        	else if('radio'==elementType){
        		var radioName=$(element).attr("name");
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
		
		success: function (lable,element)
		{
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler: function (form)
		{   
			var isValidateOk=true;
    		//判断商标证图片
        	var pictures = [];
    		var lis = $(".upload_image_list").find("li");
    		lis.each(function(index, item) {
    			var path = $("img", item).attr("path");
    			var def = ($(".def", item).length == 1 ? "1" : "0");
    			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    			pictures.push(pic);
    		});
    		if(pictures.length >0 && pictures.length<=5){
    			$("#mchtSettledApplyPics").val(JSON.stringify(pictures));
    		}else if(pictures.length >5){
    			$.ligerDialog.question('公司证件最多上传5张！');
    			isValidateOk=false;
    		}else{
    			$.ligerDialog.question('请上传公司证件！');
    			isValidateOk=false;
    		}
    		
        	if(isValidateOk){
        		form.submit();
        	}else{
				$("html,body").animate({scrollTop:$("body").offset().top},0);
        	}
		},
		
        rules:{
            companyName:{
                required:true,
                maxlength:100
            },
            regCapital:{
                number:true,
                maxlength:12
            },
             corporationName:{
                required:true,
                maxlength:100
            },
            province:{
                required:true
            },
            city:{
                required:true
            },
            county:{
                required:true
            },
            address:{
                required:true,
                maxlength:100
            },
            contactName:{
                required:true,
                maxlength:10
            },
            qq:{
                required:true,
                digits:true,
                maxlength:15
            },
            phone:{
                required:true,
                maxlength:15
            },
            email:{
                required:true,
                email:true,
                maxlength:60
            },
            tmallShop:{
                maxlength:100
            },
            taobaoShop:{
                maxlength:100
            },
            jdShop:{
                maxlength:100
            },
            vipsShop:{
                maxlength:100
            },
            otherShop:{
                maxlength:100
            },
            productBrandMain:{
                required:true,
                maxlength:200
            },
            validCode:{
                required:true
            }
        },
        messages:{
            companyName:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            regCapital:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
             corporationName:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            province:{
                required:"必选"
            },
            city:{
                required:"必选"
            },
            county:{
                required:"必选"
            },
            address:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            contactName:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            qq:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            phone:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            email:{
                required:"必填",
                email:"E-Mail格式不正确",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            tmallShop:{
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            taobaoShop:{
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            jdShop:{
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            vipsShop:{
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            otherShop:{
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            productBrandMain:{
                required:"必填",
                maxlength: $.validator.format("最多输入 {0}个字")
            },
            validCode:{
                required:"必填",
            }
        }
	});
});

function ajaxFileUpload() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "mchtSettledApplyPic",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				uploadImageList.addImage(contextPath + "/file_servelt", result.FILE_PATH,"pictures");
				mchtSettledApply_viewer.destroy();
				mchtSettledApply_viewer = new Viewer(document.getElementById('mchtSettledApply_viewer'), {});
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});	
}

function getProvinceList()
{   
	var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentAreaId:0},
  		timeout : 30000,
  		success : function(json) 
  		{  var sel = $("#province");
				sel.empty();
				sel.append("<option value=\"" + "\">  请选择 </option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			$.ligerDialog.warn('操作超时，请稍后再试！');
		}
  	}); 
}

function getCityList(parentAreaId)
{
	if(typeof(parentAreaId)!="undefined"){
		var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		cache : false,
	  		async : false,
	  		data : { parentAreaId : parentAreaId},
	  		timeout : 30000,
	  		success : function(json) 
	  		{  var sel = $("#city");
					sel.empty();
					sel.append("<option value=\"" + "\">  请选择 </option>");
					
					$.each(json, function(index, item) {
						sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
					});
	  	      
	  		},
			error : function() 
			{
				$.ligerDialog.warn('操作超时，请稍后再试！');
			}
	  	});
	}
	
 
}

function getCountyList(parentAreaId)
{
	if(typeof(parentAreaId)!="undefined"){
	var path = '${pageContext.request.contextPath}/area/getAreaList.shtml';
	jQuery.ajax( {
  		url : path,
  		type : 'POST',
  		dataType : 'json',
  		cache : false,
  		async : false,
  		data : { parentAreaId : parentAreaId},
  		timeout : 30000,
  		success : function(json) 
  		{  var sel = $("#county");
				sel.empty();
				sel.append("<option value=\"" + "\">  请选择  </option>");
				
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.areaId + "\">" + item.areaName + "</option>");
				});
  	      
  		},
		error : function() 
		{
			$.ligerDialog.warn('操作超时，请稍后再试！');
		}
  	}); 
	}
}
function  onchangeProvince()
{
	   $("#city").empty();
	   $("#county").empty();
	   getCityList($("#province").val()); 
}
function  onchangeCity()
{ 
	   $("#county").empty();  
	   getCountyList($("#city").val()); 
}
</script>
<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mchtSettled/editSave.shtml">
	<input id="id" name="id" type="hidden" value="${mchtSettledApplyCustom.id }"/>
	<table class="gridtable">
		<tr>
			<td colspan="1" class="title">获取入驻信息来源 <span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<c:if test="${mchtSettledApplyCustom.clientType==1}">PC端</c:if>
				<c:if test="${mchtSettledApplyCustom.clientType==2}">招商H5</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">入驻类型<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<c:if test="${mchtSettledApplyCustom.settledType==1}">企业公司</c:if>
				<c:if test="${mchtSettledApplyCustom.settledType==2}">个体商户</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">申请主类目 <span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
			<c:if test="${not empty productTypeName}">
				${productTypeName}
			</c:if>
			<c:if test="${empty productTypeName}">
				${mchtSettledApplyCustom.productTypeMain}
			</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">经营类目明细 <span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<c:forEach items="${zsProductTypeDetails}" var="zsProductTypeDetail">
					${zsProductTypeDetail}<br>
				</c:forEach>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">公司名称 /个体名称<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input id="companyName" name="companyName" validate="{required:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.companyName}"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">公司证件 <span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td" id="mchtSettledApply_viewer">
	    		<t:imageList name="pictures" list="${mchtSettledApplyPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
	    		<div style="float: left;height: 105px;margin: 10px;">
	    		<input style="position:absolute; opacity:0;" type="file" id="mchtSettledApplyPic" name="file" onchange="ajaxFileUpload();" />
					<div class="l-icon l-icon-up" style="display:inline-block;"></div>
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
					<span style="color:#CC0000;">(图片最多5张)</span>
	    		</div>
	    		<input id="mchtSettledApplyPics" name="mchtSettledApplyPics" type="hidden" value="${mchtSettledApplyPics}">
	    	</td>
		</tr>
		
		<tr>
				<td colspan="1" class="title">注册资本<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="regCapital" name="regCapital" validate="{required:true,number:true}" type="text" style="width:100px;" value="${mchtSettledApplyCustom.regCapital }">&nbsp;万&nbsp;
					<select style="width: 100px;" id="regFeeType" name="regFeeType">
 					<c:forEach var="typeItem" items="${regFeeTypes}">
						<option value="${typeItem.statusValue}" <c:if test="${typeItem.statusValue==mchtSettledApplyCustom.regFeeType}">selected="selected"</c:if>>${typeItem.statusDesc}
						</option>
					</c:forEach>
					</select>
				</td>
		</tr>

		<tr>
			<td colspan="1" class="title">法人姓名 <span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input id="corporationName" name="corporationName" validate="{required:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.corporationName }"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">通讯地址 <span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<select style="width: 100px;" id="province" onchange="onchangeProvince();" name="province" validate="{selectRequired:true}"></select>
				<select style="width: 100px;" id="city" onchange="onchangeCity();" name="city" validate="{selectRequired:true}"></select>
				<select style="width: 100px;" id="county" name="county" validate="{selectRequired:true}"></select>
				<input id="address" name="address" style="display:block;margin-top:10px;width: 400px;" validate="{required:true}" type="text" value="${mchtSettledApplyCustom.address }"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">联系人(职务) <span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<c:choose>
                    <c:when test="${not empty mchtSettledApplyCustom.contactJob}">
                      <input id="contactName" name="contactName" validate="{required:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.contactName}(${mchtSettledApplyCustom.contactJob})"/>
                    </c:when>   
                  <c:otherwise>
                    <input id="contactName" name="contactName" validate="{required:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.contactName}"/>
                  </c:otherwise>
              </c:choose>			
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">QQ<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input id="qq" name="qq" validate="{required:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.qq }"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">联系人手机<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input id="phone" name="phone" validate="{required:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.phone }"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">联系人邮箱<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input id="email" name="email" validate="{required:true,email:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.email }"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">联系人微信号<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input id="wechat" name="wechat" validate="{required:true}" type="text" style="width:400px;" value="${mchtSettledApplyCustom.wechat }"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">商品名称<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				${mchtSettledApplyCustom.productName}
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">商品主图<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td" id="viewer-pictures">
				<t:imageList name="productPictures" list="${productPicList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">其它店地址</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">天猫</span><input id="tmallShop" name="tmallShop" type="text" style="width:500px;" value="${mchtSettledApplyCustom.tmallShop }"/></div>
				<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">淘宝</span><input id="taobaoShop" name="taobaoShop" type="text" style="width:500px;" value="${mchtSettledApplyCustom.taobaoShop }"/></div>
				<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">京东</span><input id="jdShop" name="jdShop" type="text" style="width:500px;" value="${mchtSettledApplyCustom.jdShop }"/></div>
				<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">唯品会</span><input id="vipsShop" name="vipsShop" type="text" style="width:500px;" value="${mchtSettledApplyCustom.vipsShop }"/></div>
				<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">其他</span><input id="otherShop" name="otherShop" type="text" style="width:500px;" value="${mchtSettledApplyCustom.otherShop }"/></div>
			</td>
		</tr>
		<!--  
		<tr>
			<td colspan="1" class="title">旧类目</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<textarea id="productTypeMain" name="productTypeMain" validate="{required:true}" rows="6" cols="60" class="text">${mchtSettledApplyCustom.productTypeMain}</textarea>
			</td>
		</tr>
		-->	
			<tr>
				<td colspan="1" class="title">商家常用快递公司<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea id="usedExpress" name="usedExpress" validate="{required:true}" rows="6" cols="60" class="text">${mchtSettledApplyCustom.usedExpress}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">运营品牌<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea id="productBrandMain" name="productBrandMain" validate="{required:true}" rows="6" cols="60" class="text">${mchtSettledApplyCustom.productBrandMain}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">招商人员备注</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="6" cols="60" name="remarks">${mchtSettledApplyCustom.remarks}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">商品情况</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="6" cols="60" name="productSituation">${mchtSettledApplyCustom.productSituation}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">商家其他渠道链接</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="6" cols="60" name="otherChannelLink">${mchtSettledApplyCustom.otherChannelLink}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">物流配送</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="6" cols="60" name="logistics">${mchtSettledApplyCustom.logistics}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">商家团队概况</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="6" cols="60" name="teamSituation">${mchtSettledApplyCustom.teamSituation}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">公司概况</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="6" cols="60" name="companySituation">${mchtSettledApplyCustom.companySituation}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${isZs}">
							<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						</c:if>
						<input type="button" value="关闭" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
		</form>	
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
