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
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
<style type="text/css">
 	.radioClass{
		margin-right: 20px;
	}
 
	.table td {
    border-width: 0px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
}
 </style>
<script type="text/javascript">

/*  	$(document).ready(function () {
 		var a = "${sourceNicheProductList}";
		console.log("${sourceNicheProductList}")
	});  */
	//图片查看
	function viewerPic(){
		
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+entryPic+'" src="${pageContext.request.contextPath}/file_servelt'+entryPic+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
	}

</script>

</head>
	<body style="margin: 10px;">

		<table class="gridtable"  >
			<tr>
				<td>
            	<span>近三十天销售额 : ${sourceNicheCustom.pay_amount_sum_30_days }</span><br>
            	</td>
           	</tr>
           	<tr>
           		<td>
            	<span>近三十天订单量 : ${sourceNicheCustom.sub_order_count_30_days }</span><br>
            	</td>
           	</tr>
           	<tr>
           		<td>
            	<span>近三十天退货率 : ${sourceNicheCustom.return_rate_30_days }</span><br>
            	</td>
           	</tr>
		</table> 
		   	<br>	
				
				<c:if test="${sourceNicheProductList!='-1' }">
				<div style="margin-left: 8px;font-size: 14px;color: black">店铺推荐商品</div>
				<br>
			    <div class="x_content container-fluid" id="content" >
			        <div class="row">
			            <div class="col-md-12 datatable-container">
			                <table border="1" bordercolor=#ddd style="width:90%;margin-left: 5%;" id="trendsTable">
			                    <thead>
			                    <tr role="row">
			                        <th width="78" style="text-align:center">商品</th>
			                        <th width="88" style="text-align:center">商城价</th>
			                        <th width="68" style="text-align:center">活动价</th>
			                        <th width="68" style="text-align:center">库存</th>
			                        <th width="68" style="text-align:center">状态</th>
			                      
			                    </tr>
                    </thead>
                    <tbody   id="tableBody"><!-- 店铺管理中  已添加的商品  -->
                    
      					<c:forEach var="row" items="${sourceNicheProductList}">
							<tr>
								<td>
									<div class="is-check">
										<div class="width-60" style="float:left;margin-right: 10px;"><img src="${pageContext.request.contextPath}/file_servelt${row.pic}"  style="width: 120px;hight:50px;"></div>
										<div class="width-226">
										<p class="h34" style="padding-top:20px">${row.pName}</p>
										
										<div style="margin-left:0;margin-top: 40px;">	
										<span>货号：${row.pArtNo }</span>
										</div> 
										<span style="color: #999;margin-left:0">ID：${row.pCode}</span>
										
										
										<a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id=${row.productId}" target="_blank">预览</a>
										
										</div>
									</div>
								</td>
								
								<td style="text-align:center">
								${row.mallPriceMin}
								<c:if test="${row.mallPriceMin!=row.mallPriceMax }">
								 - ${row.mallPriceMax}
								</c:if>
								</td>
								
								<td style="text-align:center">${row.salePriceMin}
								<c:if test="${row.salePriceMin!=row.salePriceMax }">
								 - ${row.salePriceMax}
								</c:if>
								</td>
								
								<td style="text-align:center">${row.pStock}</td>
								
								<td style="text-align:center">
									<c:if test="${row.pStatus =='0'}">
									 	下架<br>
									</c:if>
									<c:if test="${row.pStatus =='1'}">
									 	上架<br>
									</c:if>
								</td>					
							</tr>
						</c:forEach>          	 
		            </tbody>
		                </table>           
		            </div>
		        </div>
		    </div>
		    </c:if>
		   	<br>
		
		<c:if test="${sourceNicheCustom.type == 10}">  
        <div class="search-container container-fluid vm-famate">
         
            <input type="hidden" id="shopStoryId" name="shopStoryId" value="${shopStory.id}">
            <input type="hidden" id="shopStory" name="shopStory" value="${shopStory}">
             <input type="hidden" id="shopStoryDetailList" value="${shopStoryDetailList}">
                <div class="table-responsive">
                    <table class="gridtable">
                        <tbody>

                        <style>
                            .single_pic_picker.x1 {
                                width: 600px;
                                height: 100px;
                                margin: 0;
                            }
                            .single_pic_picker.x1 div,
                            .single_pic_picker.x1 img,
                            .single_pic_picker.x1 input {
                                width: 100% !important;
                                height: 100% !important;
                            }
                           .single_pic_picker.x1 div {
                                line-height: 100px;
                            }
                        </style>
                        <tr>
                            <td class="editfield-label-td">故事简介</td>
                            <td colspan="2" class="text-left padding-10">
                             <textarea rows="10" cols="30" id="storyBrief" name="storyBrief" style="resize:none;width:600px;" onkeyup="$('#product_name_length').text(($('#storyBrief').val().length));" >${shopStory.storyIntroduction}</textarea>
                            </td>
                        </tr>
									                            
                        <tr>
                        	<td class="editfield-label-td" >故事详情</td>
                            <td colspan="2" class="text-left padding-10" >
                            <div style="overflow-y:scroll;height:300px;" id="storyDetails">
                            <c:if test="${shopStoryDetailList != null}">
                           		<c:forEach var="shopStoryDetail" items="${shopStoryDetailList}">
                           			<!--文字  -->
                           			 <c:if test="${shopStoryDetail.type==0 }">
                           			 <div name="wordAndPic" >
								     	<textarea name="word" rows="5" cols="30" style="resize:none;width:600px;" placeholder=" 请输入文字" >${shopStoryDetail.content }</textarea>
								     </div>
                           			 </c:if>
                            
                            		<!--图片  -->
                            	<c:if test="${shopStoryDetail.type==1 }">
                            			<div name="wordAndPic" class="single_pic_picker x1" >
									         <a onclick="javascript:window.open('${shopStoryDetail.picUrl}')"> <img id="${shopStoryDetail.id }" name="picImg" src="${pageContext.request.contextPath}/file_servelt${shopStoryDetail.pic}"></a>
									          <input type="hidden"  name="picPath" value="${shopStoryDetail.pic }"> 
									          <input type="hidden"  name="picUrl" value="${shopStoryDetail.picUrl }">
								         </div>
								       
                            		</c:if>
                            
                            	</c:forEach>
                            
                            </c:if>
                            
                            </div>
                            </td>
                        </tr>
						

                        </tbody>
                    </table>
                </div>
           	</div>
           	</c:if>
           	
           	<br>
           	<br>
       
        	<c:if test="${sourceNicheCustom.auditRemarks != null && sourceNicheCustom.auditRemarks != '' }">
           	<tr>
            	<span>驳回理由 : ${sourceNicheCustom.auditRemarks }</span>
            	<br>
            	<span>驳回类型 : 
            	<c:if test="${sourceNicheCustom.canApply == 0}">
            	七天内不可报名
            	</c:if>
            	<c:if test="${sourceNicheCustom.canApply == 1}">
            	可立即报名
            	</c:if>
            	<c:if test="${sourceNicheCustom.canApply == 2}">
            	不可再次报名
            	</c:if>
            	
            	</span>
           	</tr>
           	</c:if>
           	
<%--             <c:if test="${sourceNicheCustom.auditRemarks == null || sourceNicheCustom.auditRemarks == '' }">
           	<tr>
            	<span>驳回理由 : 上次未驳回</span>
           	</tr>
           	</c:if> --%>

		

	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	</body>
</html>