<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
 
 /* 查看活动 */
 function seeSingleProductActivityAreaInfo(activityAreaId) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 800,
		title: "编辑单品活动",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityArea/createSingleProductActivity.shtml?id=" + activityAreaId+"&type=${type}",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 /* 查看活动商品 */
 function seeSingleActivityProductInfo(activityAreaId) {
		$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width(),
		title: "查看单品活动商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/singleActivityProductGoodList.shtml?activityAreaId=" + activityAreaId+"&type=${type}",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 /* 审核商品 */
 function seeSingleActivityProductAudit(activityAreaId) {
		$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width() - 10,
		title: "商家对接运营专员审核商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allSingleActivityProductAuditList.shtml?activityAreaId=" + activityAreaId+"&type=1&op=0",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
 /*商品排序 */
 function productSort(activityAreaId,type) {
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()*0.5,
		title: "商品排序",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityArea/activityAreaDesign.shtml?activityAreaId=" + activityAreaId+"&type="+type,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
 
 var listConfig={
      url:"/activityArea/activityAreaManagerData.shtml?type=${type}",
      btnItems:[{ text: '创建单品活动', icon: 'add', dtype:'win',  click: itemclick, url:'/activityArea/createSingleProductActivity.shtml', seqId:"", width : 800, height:800}],
      listGrid:{ columns: [
                        {display:'单品活动ID',name:'id'},
                        {display:'活动名称',name:'name'},
                        {display:'活动类型',name:'typeDesc'},
//                         {display:'利益点',name:'benefitPoint'},
                        {display:'报名时间',name:'enrollBeginTime',width: 200,render:function(rowdata, rowindex){
                        	if(rowdata.enrollBeginTime==null||rowdata.enrollBeginTime==""||rowdata.enrollBeginTime==undefined){
								return "";
							}else{
								var enrollBeginTime=new Date(rowdata.enrollBeginTime);
								var enrollEndTime=new Date(rowdata.enrollEndTime);
								return "起："+enrollBeginTime.format("yyyy-MM-dd hh:mm:ss")+"止："+enrollEndTime.format("yyyy-MM-dd hh:mm:ss");
							}
                        }},
                        {display:'活动时间',name:'activityBeginTime',width: 200,render:function(rowdata, rowindex){
                        	if(rowdata.activityBeginTime==null||rowdata.activityBeginTime==""||rowdata.activityBeginTime==undefined){
								return "";
							}else{
								var activityBeginTime=new Date(rowdata.activityBeginTime);
								var activityEndTime=new Date(rowdata.activityEndTime);
								return "起："+activityBeginTime.format("yyyy-MM-dd hh:mm:ss")+"止："+activityEndTime.format("yyyy-MM-dd hh:mm:ss");								
							}
                        }},
                        {display:'限额',name:'limitMchtNum'},
                        {display:'通过商家数/报名数',name:'adoptOrSign',width: 150,render:function(rowdata, rowindex){
                        	return rowdata.adoptMchNum+"/"+rowdata.signUpNum;
                        }},
                        {display:'通过商品数/报名商品数',name:'',width: 150,render:function(rowdata,rowindex){
                        	return rowdata.adoptActivityProductNum+"/"+rowdata.signActivityProductNum;
                        }},
                        {display:'销量',name:'thisActivityAreaNum',render:function(rowdata,rowindex){
                        	if(rowdata.thisActivityAreaNum==null){
                        		return "0";
                        	}else{
                        		return rowdata.thisActivityAreaNum;
                        	}
                        	
                        }},
                        {display:'销售额',name:'thisActivityAreaSalePrice',render:function(rowdata,rowindex){
                        	if(rowdata.thisActivityAreaSalePrice==null){
                        		return "0";
                        	}else{
                        		return rowdata.thisActivityAreaSalePrice;
                        	}
                        }},
/*                         {display:'销量',name:'activityAreaSalesNum'},
                        {display:'销售额',name:'activityAreaSalesPrice',render:function(rowdata,rowindex){
                        	if(rowdata.activityAreaSalesPrice==null){
                        		return "";
                        	}else{
                        		return rowdata.activityAreaSalesPrice;
                        	}
                        }}, */
//                         {display:'面对对象',name:'minMemberGroupName'},
//                         {display:'专场特惠',name:'preferentialTypeDesc'},
                        {display:'是否启用',name:'statusDesc'},
                        { display: "操作", name: "OPER",align: "center",width: 180, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:seeSingleProductActivityAreaInfo(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:seeSingleActivityProductInfo(" + rowdata.id + ");\">查看商品</a>&nbsp;&nbsp;");
						    /* html.push("<a href=\"javascript:seeSingleActivityProductAudit(" + rowdata.id + ");\">审核商品</a>&nbsp;&nbsp;"); */
						    if(rowdata.status == "1" && rowdata.adoptActivityProductNum>0){
							    html.push("<a href=\"javascript:productSort(" + rowdata.id + ","+rowdata.type+");\">商品排序</a>&nbsp;&nbsp;");
						    }
						    return html.join("");
						 }
			            }
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
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"> 
				<div class="search-td">
				<div class="search-td-label" >活动名称：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "name" name="name" >
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label" >单品活动ID：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "activityAreaId" name="activityAreaId" >
				</div>
				</div>

				<div class="l-panel-search-item">活动时间：</div>
				<div class="l-panel-search-item">
					<input type="text" id="activityBeginTime" name="activityBeginTime" />
					<script type="text/javascript">
						$(function() {
							$("#activityBeginTime").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script> 
				</div>
					
				<div class="l-panel-search-item">至 </div>
				<div class="l-panel-search-item">
					<input type="text" id="activityEndTime" name="activityEndTime" />
					<script type="text/javascript">
						$(function() {
							$("#activityEndTime").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>	
				</div>
				
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
				
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
