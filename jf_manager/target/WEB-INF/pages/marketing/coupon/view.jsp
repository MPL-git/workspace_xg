<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>
<html>
<script type="text/javascript">
$(document).ready(function() {
	/* var recBeginDate=new Date("${couponCustom.recBeginDate}");
	var recEndDate=new Date("${couponCustom.recEndDate}");
	document.getElementById('recBeginDate').value=recBeginDate.format("yyyy-MM-dd hh:mm");
	document.getElementById('recEndDate').value=recEndDate.format("yyyy-MM-dd hh:mm"); */
	
	/* if (${couponCustom.expiryType}==1)
	{
		var expiryBeginDate=new Date("${couponCustom.expiryBeginDate}");
		var expiryEndDate=new Date("${couponCustom.expiryEndDate}");
		document.getElementById('expiryBeginDate').value=expiryBeginDate.format("yyyy-MM-dd hh:mm");
		document.getElementById('expiryEndDate').value=expiryEndDate.format("yyyy-MM-dd hh:mm");
	} */
    /* if (${couponAddtaskConfigs.couponId!=null}) {
	 var beginDate=new Date("${couponAddtaskConfigs.beginDate}");
	var endDate=new Date("${couponAddtaskConfigs.endDate}");
	if (beginDate!=null && beginDate!='' && endDate!=null && endDate!='') {
	document.getElementById('beginDate').value=beginDate.format("yyyy-MM-dd hh:mm");
	document.getElementById('endDate').value=endDate.format("yyyy-MM-dd hh:mm");
		
	 } 
		
  } */
	//加载时判断是否是红包雨
     var activityType = $("#activityType").val()
	if("1"==activityType){
		$("#redEnveloped").show();
	}else{
		$("#redEnveloped").hide();
	}
    
    var typeIds=$("#typeIds").val().replace('[','').replace(']','').replace(/\s+/g,"");
    var checkVar = document.getElementsByName("productTypeId");
 	if(typeIds!=''){
 		var strs= new Array(); 
 		strs=typeIds.split(","); 
 		for (i=0;i<strs.length;i++) {		
 			for(j=0;j<checkVar.length;j++){
 				if (strs[i]==checkVar[j].value){
 					checkVar[j].checked =true; 
 				}
 			}		
 		} 
 	}
});
</script>
								
<body>
    <input type="hidden" id="typeIds" name="typeIds" value="${couponCustom.typeIds}"/>
	<table class="gridtable">
		<tr>
			<td colspan="1" class="title">优惠券名称</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input type="text" style="width:200px;" value="${couponCustom.name }" disabled="true"/>
				定义前缀<input type="text" style="width:100px;" value="${couponCustom.definitionPrefix}" disabled="true"/>
			</td>
		</tr>
	
		<%-- <tr>
			<td colspan="1" class="title">面额</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input type="text" style="width:60px;" value="${couponCustom.money }" disabled="true"/>元
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">使用条件</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				满<input type="text" style="width:60px;" value="${couponCustom.minimum }" disabled="true"/>元使用
			</td>
		</tr> --%>
		    <tr>
				<td colspan="1" class="title">优惠券类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="radio"  name="preferentialType" value="1" disabled="disabled" <c:if test="${couponCustom.preferentialType eq '1'}">checked="checked"</c:if>/>满减&nbsp;
					<input type="radio"  name="preferentialType" value="2" disabled="disabled" <c:if test="${couponCustom.preferentialType eq '2'}">checked="checked"</c:if>/>折扣&nbsp;
				</td>
			</tr>
			
			<tr>
			 <td colspan="1" class="title">折扣<span class="required">*</span></td>
			<c:if test="${couponCustom.preferentialType eq '1'}">
				<td id="mmu" colspan="5" align="left" class="l-table-edit-td">
					面额&nbsp;<input id="money" validate="{digits:true,number:true,maxlength:8}" name="money" type="text" style="width:60px;" maxlength="6" disabled="disabled" value="${couponCustom.money}"/>元
					&nbsp;&nbsp;
					满&nbsp;<input id="minimum" validate="{digits:true,number:true,maxlength:8}" name="minimum" type="text" style="width:60px;" disabled="disabled" value="${couponCustom.minimum}"/>元
				</td>
			</c:if>
			<c:if test="${couponCustom.preferentialType eq '2'}">
				<td id="dcm" colspan="5" align="left" class="l-table-edit-td">
					<input id="discount" validate="{maxlength:5}" name="discount" type="text" style="width:60px;" disabled="disabled" value="${Discount}"/>折
					&nbsp;&nbsp;
					<select style="width: 120px;" id="conditionType" name="conditionType" disabled="disabled">	
						<c:forEach var="conditionTypeS" items="${conditionTypeS}">
							<option value="${conditionTypeS.statusValue}" <c:if test="${conditionTypeS.statusValue==couponCustom.conditionType}">selected="selected"</c:if>>${conditionTypeS.statusDesc}</option>
						</c:forEach>
					 </select>	
					&nbsp;&nbsp;
					 <c:if test="${couponCustom.minimum !=null}">
					 <label>满<input id="" validate="{number:true,maxlength:8}" name="minimum" type="text" style="width:60px;" value="${couponCustom.minimum}" disabled="disabled"/>元</label>
					 </c:if>
					&nbsp;&nbsp;
					<c:if test="${couponCustom.minicount !=null}">
					 <label>满<input id="minicount" validate="{number:true,maxlength:8}" name="minicount" type="text" style="width:60px;" value="${couponCustom.minicount}" disabled="disabled"/>件</label>
					 </c:if>
					&nbsp;&nbsp;
					最多优惠<input id="maxDiscountMoney" validate="{digits:true,number:true,maxlength:8}" name="maxDiscountMoney" type="text" style="width:60px;" value="${couponCustom.maxDiscountMoney}" disabled="disabled"/>元

				</td>
			 </c:if>
			</tr>
		
		<%-- <tr>
			<td colspan="1" class="title">品类范围<span class="required">*</span></td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<c:if test="${not empty typeIdList and typeIdList.size()>1}">
				<select style="width: 120px;" id="typeIds" name="typeIds" disabled="disabled" multiple="multiple">
					<option value="">全部品类</option>
					<c:forEach var="productType" items="${productTypes}">
							<option value="${productType.id}" <c:forEach var="typeId" items="${typeIdList}"><c:if test="${typeId eq productType.id}">selected="selected"</c:if></c:forEach>>${productType.name}</option>
					</c:forEach>
				</select>
				</c:if>
				<c:if test="${empty typeIdList or typeIdList.size()==1}">
				<select style="width: 120px;" id="typeIds" name="typeIds" disabled="disabled">
					<option value="">全部品类</option>
					<c:forEach var="productType" items="${productTypes}">
							<option value="${productType.id}" <c:forEach var="typeId" items="${typeIdList}"><c:if test="${typeId eq productType.id}">selected="selected"</c:if></c:forEach>>${productType.name}</option>
					</c:forEach>
				</select>
				</c:if>
				<c:if test="${empty couponCustom.typeIds}">
				是否叠加使用品类券<input type="radio" name="canSuperpose" value="1" disabled="disabled" <c:if test="${couponCustom.canSuperpose == 1}">checked="checked"</c:if>>是<input type="radio" name="canSuperpose" value="0" disabled="disabled" <c:if test="${couponCustom.canSuperpose == 0}">checked="checked"</c:if>>否
				</c:if>
			</td>
		</tr> --%>
		
		     <tr>
				<td colspan="1" class="title">品类范围<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="radio"  name="couponType" value="1" onchange="couponTypeS(1);" <c:if test="${couponCustom.couponType=='1'}">checked="checked"</c:if> disabled="true"/>平台卷&nbsp;
					<input type="radio"  name="couponType" value="2" onchange="couponTypeS(1);" <c:if test="${couponCustom.couponType=='2'}">checked="checked"</c:if>disabled="true"/>品类卷&nbsp;
				</td>
			</tr>
			
		 <c:if test="${couponCustom.couponType eq '2'}">
			<tr>
				<td colspan="1" class="title">指定品类<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
				   <c:forEach var="productTypes" items="${productTypes}">
					  <input name="productTypeId" type="checkbox" value="${productTypes.id}" style="width: 20px;" disabled="true"/>${productTypes.name}
					</c:forEach>
				</td>
			</tr>
		  </c:if>
		
		<tr>
			<td colspan="1" class="title">领取时间</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input id="recBeginDate" type="text"  value="<fmt:formatDate value='${couponCustom.recBeginDate}' pattern='yyyy-MM-dd HH:mm'/>" disabled="true"/>&nbsp;&nbsp;至&nbsp;&nbsp;
				<input id="recEndDate" type="text" value="<fmt:formatDate value='${couponCustom.recEndDate}' pattern='yyyy-MM-dd HH:mm'/>" disabled="true"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">有效期类型</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<c:forEach var="typeItem" items="${expiryTypes }">
					<span class="radioClass"><input class="radioItem" type="radio" value="${typeItem.statusValue }" <c:if test="${typeItem.statusValue==couponCustom.expiryType}">checked="checked"</c:if> disabled="true"/>${typeItem.statusDesc}</span>
				</c:forEach>
			</td>
		</tr>
	
		<tr>
			<td colspan="1" class="title">有效期</td>
			<td colspan="5" align="left" class="l-table-edit-td">
			<c:if test="${couponCustom.expiryType==1}">
				<input id="expiryBeginDate" type="text" disabled="true" value="<fmt:formatDate value='${couponCustom.expiryBeginDate}' pattern='yyyy-MM-dd'/>"/>
				&nbsp;&nbsp;至&nbsp;&nbsp;
				<input id="expiryEndDate" type="text" disabled="true" value="<fmt:formatDate value='${couponCustom.expiryEndDate}' pattern='yyyy-MM-dd'/>"/>
			</c:if>
			<c:if test="${couponCustom.expiryType==2}">
				<input type="text" value="${couponCustom.expiryDays}" disabled="true"/>
			</c:if>
				<font color="#CC0000">&nbsp;&nbsp;（不超过90天。）</font>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">发行量</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input type="text" style="width:60px;" value="${couponCustom.grantQuantity }" disabled="true"/>张</td>
		</tr>
		
		    <tr>
				<td colspan="1" class="title">追加日期</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input name="days" type="checkbox" value="1" style="width: 20px;" onchange="sedays(1)" <c:if test="${couponAddtaskConfigs.couponId==couponCustom.id}">checked="checked"</c:if>disabled="true"/>是否每日23:45追加&nbsp;&nbsp;<input id="addCount" name="addCount" type="text" style="width:30px;" maxlength="6" value="${couponAddtaskConfigs.addCount}" disabled="true"/>张
					&nbsp;&nbsp;
					<input type="text" id="beginDate" name="beginDate" value="<fmt:formatDate value='${couponAddtaskConfigs.beginDate}' pattern='yyyy-MM-dd HH:mm'/>" disabled="true"/>
					&nbsp;至&nbsp;
					<input type="text" id="endDate" name="endDate" value="<fmt:formatDate value='${couponAddtaskConfigs.endDate}' pattern='yyyy-MM-dd HH:mm'/>" disabled="true"/>
				</td>
	      </tr>
		
		<tr>
			<td colspan="1" class="title">使用/领取</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				${couponCustom.useQuantity } / ${couponCustom.recQuantity }
		</tr>
		
		<tr>
			<td colspan="1" class="title">领取对象</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<select style="width: 160px;" disabled="disabled">
					<option>${couponCustom.minMemberGroupName}及以上</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">领取方式</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<c:forEach var="typeItem" items="${recTypes }">
					<span class="radioClass"><input class="radioItem" type="radio" value="${typeItem.statusValue }" <c:if test="${couponCustom.recType==typeItem.statusValue}">checked="checked"</c:if> disabled="true"/>${typeItem.statusDesc}</span>
				</c:forEach>
				<c:if test="${couponCustom.recType==2}"><input type="text" style="width:60px;" value="${couponCustom.needIntegral}" disabled="true"/>金币换1张优惠券</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="1" class="title">限领</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<span class="radioClass"><input class="radioItem" type="radio" <c:if test="${couponCustom.recLimitType==1}">checked="checked"</c:if> disabled="true"/>每人每天限领1张</span>
				<span class="radioClass"><input class="radioItem" type="radio" value="2" <c:if test="${couponCustom.recLimitType==2}">checked="checked"</c:if> disabled="true"/>
					每人限领 <input type="text" style="width:60px;" value="${couponCustom.recEach}" disabled="true"/>张
				</span>
				<span class="radioClass"><input class="radioItem" type="radio" value="3" <c:if test="${couponCustom.recLimitType==3}">checked="checked"</c:if> disabled="true"/>不限</span>
				<c:if test="${CouponIdgroup!=''}">
				  &nbsp;&nbsp;&nbsp;关联优惠券ID<input type="text" style="width:200px;" value="${CouponIdgroup}" disabled="true"/>
				</c:if>
			</td>
		</tr>
		
		   <tr>
				<td colspan="1" class="title">跳转页</td>
				<td colspan="5" align="left" class="l-table-edit-td">
				类型&nbsp;&nbsp;
				<select id="linkType" name="linkType" style="width: 150px;" onchange="LinkType()" disabled="true">
               		<option value="">请选择</option> 
               		 <c:forEach var="linkTypeList" items="${linkTypeList}">
               		       <option value="${linkTypeList.linkType}" <c:if test="${linkTypeList.linkType==couponCustom.linkType}">selected="selected"</c:if>>${linkTypeList.linkTypeName}</option>
               		 </c:forEach>    			     
               	</select>
               	&nbsp;&nbsp;
               	<c:if test="${couponCustom.linkType=='1' or couponCustom.linkType=='2' or couponCustom.linkType=='3' or couponCustom.linkType=='4' or couponCustom.linkType=='7'}">
               	<c:if test="${couponCustom.linkType=='1'}">
               	会场ID&nbsp;&nbsp;</c:if>
               	<c:if test="${couponCustom.linkType=='2'}">
               	活动ID&nbsp;&nbsp;</c:if>
               	<c:if test="${couponCustom.linkType=='3'}">
               	商品ID&nbsp;&nbsp;</c:if>
               	<c:if test="${couponCustom.linkType=='4'}">
               	外部链接&nbsp;&nbsp;</c:if>
               	<c:if test="${couponCustom.linkType=='7'}">
               	自建页面ID&nbsp;&nbsp;</c:if>
               	<input type="text" id="linkValue" name="linkValue" value="${couponCustom.linkValue}" disabled="true"/>
               	</c:if>
               	
               	<c:if test="${empty couponCustom.linkType}">
               	链接&nbsp;&nbsp;<input type="text" id="linkValue" name="linkValue" value="" disabled="true"/>
               	</c:if>
               	
               	<c:if test="${couponCustom.linkType=='6'}">
               	栏目&nbsp;&nbsp;
               	<select id="linkValue0" name="linkValue0" style="width: 150px;" disabled="true">
               	       <option value="">请选择</option>
               		 <c:forEach var="linkValueList" items="${linkValueList}">
					  <option value="${linkValueList.linkValue}" <c:if test="${linkValueList.linkValue==couponCustom.linkValue}">selected="selected"</c:if>>${linkValueList.linkValueName}</option>
					</c:forEach>			     
               	</select> 
               	</c:if>
               	
                <c:if test="${couponCustom.linkType=='11'}">
                                                 品牌特卖一级分类&nbsp;&nbsp;
               	<select id="linkValue1" name="linkValue1" style="width: 150px;" disabled="true">
               	       <option value="">请选择</option>
               		 <c:forEach var="productTypes" items="${productTypes}">
					  <option value="${productTypes.id}"  <c:if test="${productTypes.id==couponCustom.linkValue}">selected="selected"</c:if>>${productTypes.name}</option>
					</c:forEach>			     
               	</select> 
               	</c:if>
               	
               	<c:if test="${couponCustom.linkType=='9'}">
               	品牌团一级类目&nbsp;&nbsp;
               	<select id="linkValue2" name="linkValue2" style="width: 150px;" disabled="true">
               	      <option value="">请选择</option>
               		 <c:forEach var="brandteamTypes" items="${brandteamTypes}">
					  <option value="${brandteamTypes.id}" <c:if test="${brandteamTypes.id==couponCustom.linkValue}">selected="selected"</c:if>>${brandteamTypes.name}</option>
					</c:forEach>			     
               	</select> 
               	</c:if>
               	  
               	 <c:if test="${couponCustom.linkType=='28'}"> 
               	 商城一级分类&nbsp;&nbsp; 	
               	 <select id="linkValue3" name="linkValue3" style="width: 150px;" disabled="true">
               	     <option value="">请选择</option>
               		<c:forEach var="mallCategories" items="${mallCategories}">
					  <option value="${mallCategories.id}" <c:if test="${mallCategories.id==couponCustom.linkValue}">selected="selected"</c:if>>${mallCategories.categoryName}</option>
					</c:forEach>			     
               	</select>
               	</c:if> 
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">活动类型</td>
				<td colspan="5" align="left" class="l-table-edit-td">
			<%--		<span class="radioClass"><input class="radioItem" type="radio" value="1" name="isSupportCouponRain" disabled="true" <c:if test="${couponCustom.isSupportCouponRain eq 1}">checked="checked"</c:if>>是否参与红包雨</span>
					获取条件 <input id="limitCouponRainScore" name="limitCouponRainScore" type="text" style="width:60px;" maxlength="3" value="${couponCustom.limitCouponRainScore}" disabled="true"/>张 &nbsp;（用户参与红包雨游戏领取数量达到多少及以上才可获得）
					--%>
				<select  id="activityType" name="activityType" disabled="true">
					<option value="0">请选择</option>
					<option value="1" <c:if test="${couponCustom.activityType==1}">selected</c:if>>红包雨</option>
					<option value="2" <c:if test="${couponCustom.activityType==2}">selected</c:if>>领券秒杀</option>
				</select>
				<span id="redEnveloped" style="display: none;">获取条件 <input id="limitCouponRainScore" name="limitCouponRainScore" type="text" style="width:60px;" maxlength="3" disabled="true" value="${couponCustom.limitCouponRainScore}" />张 &nbsp;（用户参与红包雨游戏领取数量达到多少及以上才可获得）</span>
				</td>
			</tr>
		
		<tr>
			<td colspan="1" class="title">创建人</td>
			<td colspan="5" align="left" class="l-table-edit-td">
				<input type="text" style="width:60px;" value="${couponCustom.staffName }" disabled="true"/>
			</td>
		</tr>
	</table>
</body>
</html>
