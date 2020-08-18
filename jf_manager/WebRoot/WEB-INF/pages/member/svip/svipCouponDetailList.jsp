<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
    var usingDetail ="";
    $(function () {
        var id =${couponId};
        usingDetail = $(window.parent.document).find("#usingDetail"+id).text();
    });

    var listConfig={
	  
      url:"/coupon/svipCouponDetailList.shtml",
      listGrid:{ columns: [
		                { display: '优惠劵名称', name: 'couponName', width: 150},
		                { display: '优惠劵额度', width: 100, render: function(rowdata, rowindex) {
		                	return usingDetail;
		                }},
		                { display: '会员昵称', name: 'nick', width: 150},
		                { display: '会员手机号', name: 'mobile', width: 150},
			            { display: '领劵时间', width: 150, render: function(rowdata, rowindex) {
			            	if (rowdata.recDate!=null){
				            	var recDate=new Date(rowdata.recDate);
				            	return recDate.format("yyyy-MM-dd hh:mm:ss");
			            	}
			         	}},
			            { display: '使用时间', width: 150, render: function(rowdata, rowindex) {
			            	if (rowdata.useDate!=null){
			            		var useDate=new Date(rowdata.useDate);
				            	return useDate.format("yyyy-MM-dd hh:mm:ss");
			            	}	
			         	}},
		                { display: '优惠劵状态', name: 'statusDesc', width: 100},
		                { display: '订单号', name: 'combineOrderCode', width: 150}
		                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
       
 }
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<input id="couponId" name="couponId" type="hidden" value="${couponId}">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >手机号：</div>
					<div class="search-td-condition" >
						<input type="text" id = "mobile" name="mobile" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >用户昵称：</div>
					<div class="search-td-condition" >
						<input type="text" id = "nick" name="nick" >
					</div>
				</div>
			 	<div class="search-td">
					<div class="search-td-label" >状态:</div>
					<div class="search-td-condition" >
						<select id="status" name="status">
							<option value="">请选择</option>
							<c:forEach var="statusItem" items="${memberCouponStatus}">
								<option value="${statusItem.statusValue}">${statusItem.statusDesc}
								</option>
							</c:forEach>
						</select>
				 	</div>
			 	</div>
			 	<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
