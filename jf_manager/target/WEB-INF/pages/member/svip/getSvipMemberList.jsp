<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<script type="text/javascript">
$(function(){

	$(".dateEditor").ligerDateEditor({
		showTime : true,
		format : "yyyy-MM-dd",
		labelAlign : 'left',
		width : 135
	});
	
	$("#exportExcel").bind('click',function(){
		$("#dataForm").attr("action", "${pageContext.request.contextPath}/svipOrder/getSvipMemberList/exportExcel.shtml");
		$("#dataForm").submit();
	});
	
	/* $("#searchbtn").bind('click',function(){
			var payOrderAmountMin = $.trim($("#payOrderAmountMin").val());
			var payOrderAmountMax = $.trim($("#payOrderAmountMax").val());
			var reg=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
			if(payOrderAmountMin){
				if(!reg.test(payOrderAmountMin)){
					commUtil.alertError("消费金额格式不正确");
					return;
				}
			}
			if(payOrderAmountMax){
				if(!reg.test(payOrderAmountMax)){
					commUtil.alertError("消费金额格式不正确");
					return;
				}
			}	
			
	});  */
	
});



function searchbtnFunction() {
	var Verification=true;
	var payOrderAmountMin = $.trim($("#payOrderAmountMin").val());
	var payOrderAmountMax = $.trim($("#payOrderAmountMax").val());
	var reg=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
	if(payOrderAmountMin){
		if(!reg.test(payOrderAmountMin)){
			commUtil.alertError("消费金额格式不正确");
			return Verification=false;
		}
	}
	if(payOrderAmountMax){
		if(!reg.test(payOrderAmountMax)){
			commUtil.alertError("消费金额格式不正确");
			return Verification=false;
		}
	}
	
	   if (Verification==true) {
        listModel.ligerGrid.url = '${pageContext.request.contextPath}/svipOrder/getSvipMemberList.shtml';
        listModel.initdataPage();		 
		
	   }
			
} 

var listConfig = {
		url:"/svipOrder/getSvipMemberList.shtml",
    	btnItems:[],
    	listGrid:{ columns: [
				{ display: '会员ID', name: 'id', width: 100, render: function(rowdata, rowindex) {
			      	var html = [];
			      	html.push(rowdata.id);
			      	if(rowdata.status == 'P') {
			      		html.push("</br><span style='color: red;'>黑名单</span>");
			      	}
			      	return html.join("");
			    } },
			    { display: '昵称', width: 150, name: 'nick' },
			    { display: '会员状态', name: 'statusDesc', width: 100 },
 	            { display: 'SVIP状态', name: 'type', width: 100, render: function(rowdata, rowindex) {
 	                if (rowdata.svipExpireDate != null && new Date(rowdata.svipExpireDate).getTime() > new Date().getTime()) {
						return "已激活";
	               	}else {
	               		return "已过期";
	               	}
 	            } },
                { display: '注册时间', width:150, render: function (rowdata, rowindex) {
                	if (rowdata.createDate != null){
						var createDate = new Date(rowdata.createDate);
						return createDate.format("yyyy-MM-dd hh:mm:ss");
               		}
                }},
                { display: '首次购买SVIP时间', width: 150, render: function (rowdata, rowindex) {
                	if (rowdata.buySvipPayDate != null){
						var buySvipPayDate = new Date(rowdata.buySvipPayDate);
						return buySvipPayDate.format("yyyy-MM-dd hh:mm:ss");
               		}
                }},
                { display: 'SVIP到期时间', width: 150, render: function (rowdata, rowindex) {
                	if (rowdata.svipExpireDate != null){
						var svipExpireDate = new Date(rowdata.svipExpireDate);
						return svipExpireDate.format("yyyy-MM-dd hh:mm:ss");
               		}
                }},
                { display: '最后登录时间', width: 150, render: function (rowdata, rowindex) {
                	if (rowdata.msLastLoginTime != null){
						var msLastLoginTime = new Date(rowdata.msLastLoginTime);
						return msLastLoginTime.format("yyyy-MM-dd hh:mm:ss");
               		}
                }},
                { display: '首次消费时间', width: 150, render: function (rowdata, rowindex) {
                	if (rowdata.msFirstBuyTime != null){
						var msFirstBuyTime = new Date(rowdata.msFirstBuyTime);
						return msFirstBuyTime.format("yyyy-MM-dd hh:mm:ss");
                	}
                }},
                { display: '最后消费时间', width: 150, render: function (rowdata, rowindex) {
                	if (rowdata.msLastBuyTime != null){
						var msLastBuyTime = new Date(rowdata.msLastBuyTime);
						return msLastBuyTime.format("yyyy-MM-dd hh:mm:ss");
                	}
                }},
                { display: '消费订单数', name: 'totalBuyCount', width: 100 },
                { display: '消费金额', name: 'totalBuyAmount', width: 100 },
                { display: '积分', name: 'integral', width: 100 }
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
};
 
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">会员ID：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="id" name="id">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">SVIP状态：</div>
					<div class="search-td-combobox-condition">
						<select id="svipStatus" name="svipStatus" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1">已激活</option>
							<option value="2">已过期</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">购买时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="payDateBegin" name="payDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="payDateEnd" name="payDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">过期时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="svipExpireDateBegin" name="svipExpireDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="svipExpireDateEnd" name="svipExpireDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
 						<div class="search-td-label" style="float: left;width: 20%;">最后登录时间：</div>
					<div class="l-panel-search-item" > 
 							<input type="text" id="loginDateBegin" name="loginDateBegin" class="dateEditor" style="width: 135px;" />
 					</div> 
 						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div> 
 						<div class="l-panel-search-item">
 							<input type="text" id="loginDateEnd" name="loginDateEnd" class="dateEditor" style="width: 135px;" /> 
 						</div>
    			</div>
			</div>
			<div class="search-tr">
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">最后消费时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="lastPayDateBegin" name="lastPayDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="lastPayDateEnd" name="lastPayDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			    <div class="search-td">
						<div class="search-td-label">消费金额：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="payOrderAmountMin" name="payOrderAmountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="payOrderAmountMax" name="payOrderAmountMax"></div>
						</div>
				 </div>
    			<div class="search-td">
						<div class="search-td-label">消费订单数：</div>
						<div class="search-td-condition" style="display: inline-flex;">
							<div style="float: left;"><input type="text" id="payOrderCountMin" name="payOrderCountMin"></div>
							<div style="width: 25px;">--</div>
							<div style="float: left;"><input type="text" id="payOrderCountMax" name="payOrderCountMax"></div>
						</div>
				 </div>
			</div>
			<div class="search-tr">
			     <div class="search-td" style="width: 40%;">
 						<div class="search-td-label" style="float: left;width: 20%;">首次消费时间：</div>
					<div class="l-panel-search-item" >
 							<input type="text" id="firstPayDateBegin" name="firstPayDateBegin" class="dateEditor" style="width: 135px;" />
 					</div> 
 						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div> 
 					<div class="l-panel-search-item">
 						<input type="text" id="firstPayDateEnd" name="firstPayDateEnd" class="dateEditor" style="width: 135px;" /> 
 					</div>
    			</div>
    			<div class="search-td" style="width: 40%;">
 						<div class="search-td-label" style="float: left;width: 20%;">注册时间：</div>
					<div class="l-panel-search-item" >
 							<input type="text" id="date_begin" name="date_begin" class="dateEditor" style="width: 135px;" />
 					</div> 
 						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div> 
 					<div class="l-panel-search-item">
 						<input type="text" id="date_end" name="date_end" class="dateEditor" style="width: 135px;" /> 
 					</div>
    			</div>
			    <!-- <div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div> -->
				<div class="search-td-search-btn" style="display: inline-flex;">
				    <div class="search-td-search-btn" >
					     <!--  <div id="searchbtn" >搜索</div> -->
					      <div class="l-button" onclick="searchbtnFunction();" >搜索</div>	
				    </div>		
					<div style="padding-left: 30px;">
						<input type="button" class="l-button" value="导出Excel" id="exportExcel">
					</div>
					</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js">
</script>