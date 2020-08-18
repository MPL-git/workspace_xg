<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title></title>
   <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript">	

	$(function() {
		$("#update_begin").ligerDateEditor({
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	$(function() {
		$("#update_end").ligerDateEditor({			
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	
	function edit(id) {
		$.ligerDialog.open({
			height: 450,
			width: 750,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/memberLabel/ruleAdd.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	
	function delrule(id) {
		$.ligerDialog.confirm("是否确定删除标签规则？", function (yes) 
		{ 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/memberLabel/updatememberLabelRule.shtml?id="+id,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							listModel.gridManager.reload();
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
						
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
	}
	
	
	function seemeMemberId(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "查看会员",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/memberLabel/seemeMemberIdList.shtml?labelRuleId=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	
	
	function memberlabelStatus(id,status) {
		$.ligerDialog.confirm("是否确定提交标签规则？", function (yes) 
		{ 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/memberLabel/updateMemberlabelStatus.shtml?id="+id+"&status="+status,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							listModel.gridManager.reload();
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
						
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
	}
	
	
	 var listConfig={
		     url:"/memberLabel/ruleListdata.shtml",
		     btnItems:[{ text: '添加标签规则', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/memberLabel/ruleAdd.shtml", seqId:"", width: 750, height: 900}],
		     listGrid:{ columns: [
								{ display: '规则条件', name:'',width: 330,render:function(rowdata, rowindex){
									var html=[];
									if (rowdata.signDateBegin!=null && rowdata.signDateEnd!=null) {			
										var signDateBegin=new Date(rowdata.signDateBegin);
										var signDateEnd=new Date(rowdata.signDateEnd);
										var signDateBegin1=signDateBegin.format("yyyy-MM-dd");
										var signDateEnd1=signDateEnd.format("yyyy-MM-dd");
										html.push( "注册时间="+signDateBegin1+"~"+signDateEnd1);
										html.push("<br>");
									
									}	
									if (rowdata.lastLoginDateBegin!=null && rowdata.lastLoginDateEnd!=null) {
										 var lastLoginDateBegin=new Date(rowdata.lastLoginDateBegin);
										 var lastLoginDateEnd=new Date(rowdata.lastLoginDateEnd);
										 var lastLoginDateBegin1=lastLoginDateBegin.format("yyyy-MM-dd");
										 var lastLoginDateEnd1=lastLoginDateEnd.format("yyyy-MM-dd");
										 html.push( "最后登录时间="+lastLoginDateBegin1+"~"+lastLoginDateEnd1);
										 html.push("<br>");
									}
									if (rowdata.lastExpenseDateBegin!=null && rowdata.lastExpenseDateEnd!=null) {
										 var lastExpenseDateBegin=new Date(rowdata.lastExpenseDateBegin);
										 var lastExpenseDateEnd=new Date(rowdata.lastExpenseDateEnd);
										 var lastExpenseDateBegin1=lastExpenseDateBegin.format("yyyy-MM-dd");
										 var lastExpenseDateEnd1=lastExpenseDateEnd.format("yyyy-MM-dd");
										 html.push( "最后消费时间="+lastExpenseDateBegin1+"~"+lastExpenseDateEnd1);
										 html.push("<br>");
									}
									if (rowdata.loginDateBegin!=null && rowdata.loginDateEnd!=null) {
										 var loginDateBegin=new Date(rowdata.loginDateBegin);
										 var loginDateEnd=new Date(rowdata.loginDateEnd);
										 var loginDateBegin1=loginDateBegin.format("yyyy-MM-dd");
										 var loginDateEnd1=loginDateEnd.format("yyyy-MM-dd");
										 html.push( "在"+loginDateBegin1+"~"+loginDateEnd1+"之间有登入");
										 html.push("<br>");
									}
									if (rowdata.payDateBegin!=null && rowdata.payDateEnd!=null) {
										 var payDateBegin=new Date(rowdata.payDateBegin);
										 var payDateEnd=new Date(rowdata.payDateEnd);
										 var payDateBegin1=payDateBegin.format("yyyy-MM-dd");
										 var payDateEnd1=payDateEnd.format("yyyy-MM-dd");
										 html.push( "在"+payDateBegin1+"~"+payDateEnd1+"之间有付款");
										 html.push("<br>");
									}
									if (rowdata.payCountBegin!=null && rowdata.payCountEnd!=null) {
										 var payCountBegin=rowdata.payCountBegin;
										 var payCountEnd=rowdata.payCountEnd;						
										 html.push( "付款次数="+payCountBegin+"~"+payCountEnd);
										 html.push("<br>");
									}
									if (rowdata.payAmountBegin!=null && rowdata.payAmountEnd!=null) {
										 var payAmountBegin=rowdata.payAmountBegin;
										 var payAmountEnd=rowdata.payAmountEnd;
										 html.push( "付款金额="+payAmountBegin+"~"+payAmountEnd);
										 html.push("<br>");
									}
									if (rowdata.payLogCountBegin!=null && rowdata.payLogCountEnd!=null) {
										 var payLogCountBegin=rowdata.payLogCountBegin;
										 var payLogCountEnd=rowdata.payLogCountEnd;
										 html.push( "历史付款次数="+payLogCountBegin+"~"+payLogCountEnd);
										 html.push("<br>");
									}
									if (rowdata.payLogAmountBegin!=null && rowdata.payLogAmountEnd!=null) {
										 var payLogAmountBegin=rowdata.payLogAmountBegin;
										 var payLogAmountEnd=rowdata.payLogAmountEnd;
										 html.push( "历史付款金额="+payLogAmountBegin+"~"+payLogAmountEnd);
										 html.push("<br>");
									}
									if (rowdata.returnGoodsRateMin!=null && rowdata.returnGoodsRateMax!=null) {
										var returnGoodsRateMin=rowdata.returnGoodsRateMin;
										var returnGoodsRateMax=rowdata.returnGoodsRateMax;
										html.push( "退货率="+returnGoodsRateMin+"%~"+returnGoodsRateMax+"%");
										html.push("<br>");
									}
									if (rowdata.refundRateMin!=null && rowdata.refundRateMax!=null) {
										var refundRateMin=rowdata.refundRateMin;
										var refundRateMax=rowdata.refundRateMax;
										html.push( "退款率="+refundRateMin+"%~"+refundRateMax+"%");
										html.push("<br>");
									}
									if (rowdata.sportTypeCountBegin!=null && rowdata.sportTypeCountEnd!=null) {
										 var sportTypeCountBegin=rowdata.sportTypeCountBegin;
										 var sportTypeCountEnd=rowdata.sportTypeCountEnd;
										 html.push( "运动户外类目付款次数="+sportTypeCountBegin+"~"+sportTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.shoeTypeCountBegin!=null && rowdata.shoeTypeCountEnd!=null) {
										 var shoeTypeCountBegin=rowdata.shoeTypeCountBegin;
										 var shoeTypeCountEnd=rowdata.shoeTypeCountEnd;
										 html.push( "鞋靴箱包类目付款次数="+shoeTypeCountBegin+"~"+shoeTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.costumeTypeCountBegin!=null && rowdata.costumeTypeCountEnd!=null) {
										 var costumeTypeCountBegin=rowdata.costumeTypeCountBegin;
										 var costumeTypeCountEnd=rowdata.costumeTypeCountEnd;
										 html.push( "服装配饰类目付款次数="+costumeTypeCountBegin+"~"+costumeTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.jewelTypeCountBegin!=null && rowdata.jewelTypeCountEnd!=null) {
										 var jewelTypeCountBegin=rowdata.jewelTypeCountBegin;
										 var jewelTypeCountEnd=rowdata.jewelTypeCountEnd;
										 html.push( "钟表珠宝类目付款次数="+jewelTypeCountBegin+"~"+jewelTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.liveTypeCountBegin!=null && rowdata.liveTypeCountEnd!=null) {
										 var liveTypeCountBegin=rowdata.liveTypeCountBegin;
										 var liveTypeCountEnd=rowdata.liveTypeCountEnd;
										 html.push( "生活家居类目付款次数="+liveTypeCountBegin+"~"+liveTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.digitalTypeCountBegin!=null && rowdata.digitalTypeCountEnd!=null) {
										 var digitalTypeCountBegin=rowdata.digitalTypeCountBegin;
										 var digitalTypeCountEnd=rowdata.digitalTypeCountEnd;
										 html.push( "数码家电类目付款次数="+digitalTypeCountBegin+"~"+digitalTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.makeupTypeCountBegin!=null && rowdata.makeupTypeCountEnd!=null) {
										 var makeupTypeCountBegin=rowdata.makeupTypeCountBegin;
										 var makeupTypeCountEnd=rowdata.makeupTypeCountEnd;
										 html.push( "美妆个护类目付款次数="+makeupTypeCountBegin+"~"+makeupTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.childTypeCountBegin!=null && rowdata.childTypeCountEnd!=null) {
										 var childTypeCountBegin=rowdata.childTypeCountBegin;
										 var childTypeCountEnd=rowdata.childTypeCountEnd;
										 html.push( "母婴童装类目付款次数="+childTypeCountBegin+"~"+childTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.foodTypeCountBegin!=null && rowdata.foodTypeCountEnd!=null) {
										 var foodTypeCountBegin=rowdata.foodTypeCountBegin;
										 var foodTypeCountEnd=rowdata.foodTypeCountEnd;
										 html.push( "食品超市类目付款次数="+foodTypeCountBegin+"~"+foodTypeCountEnd);
										 html.push("<br>");
									}
									if (rowdata.svipYear!=null) {
										 var svipYear=rowdata.svipYear;
										 html.push( "SVIP付费年限为="+svipYear);
										 html.push("<br>");
									}
									
									 return html.join("");
								}},
								{ display: '标签类型', name:'memberlabelTypeName',width:130},
								{ display: '标签选项',name:'memberlabelName',width: 230},
								{ display: '创建人',name:'staffName',width:100},
					            { display: '更新时间', name: '',width: 200, render: function(rowdata, rowindex) {
		    	  					if (rowdata.updateDate!=null && rowdata.updateDate!="") {
		    	  						var updateDate=new Date(rowdata.updateDate);
		    			            	return updateDate.format("yyyy-MM-dd hh:mm:ss");
								   }
		      					}},
								{ display: '状态',name:'',width:100,render:function(rowdata, rowindex){
									if (rowdata.memberlabelStatus=='0') {
										return "待提交";
									}else if (rowdata.memberlabelStatus=='1') {
										return "待执行";
									}else {
										return "已执行";
									}
								}},
		      					{display:'操作',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
									 var html = [];
									 if (rowdata.memberlabelStatus=='0') {
									 html.push("<a href=\"javascript:edit("+ rowdata.id +");\">编辑</a>&nbsp&nbsp");
									 html.push("<a href=\"javascript:memberlabelStatus(" + rowdata.id + ",'1');\">提交执行</a>&nbsp&nbsp");
									 html.push("<a href=\"javascript:delrule("+ rowdata.id +");\">删除</a>&nbsp&nbsp");		
									}else if (rowdata.memberlabelStatus=='1') {
										html.push("<a href=\"javascript:delrule("+ rowdata.id +");\">删除</a>&nbsp&nbsp");
									}else {
										 html.push("<a href=\"javascript:edit("+ rowdata.id +");\">编辑</a>&nbsp&nbsp");
										 html.push("<a href=\"javascript:seemeMemberId("+ rowdata.id +");\">查看会员</a>&nbsp&nbsp");
										 html.push("<br>");
										 html.push("<a href=\"javascript:delrule("+ rowdata.id +");\">删除</a>&nbsp&nbsp");	
										 html.push("<a href=\"javascript:memberlabelStatus(" + rowdata.id + ",'1');\">再次执行</a>&nbsp&nbsp");
									}
									 return html.join("");
								}},
				                ],
		                 showCheckbox : false,  //不设置默认为 true
		                 showRownumber: true //不设置默认为 true
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
		<div class="search-tr" > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">标签类型：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="memberlabelTypeName" name="memberlabelTypeName">
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" >状态：</div>
			<div class="search-td-condition" >
			   <select id="status" name="status">
				<option value="">请选择</option>
				<option value="0">待提交</option>
				<option value="1">待执行</option>
				<option value="2">已执行</option>
			  </select>
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" style="float:left;">更新日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="update_begin" name="update_begin" value=""/>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至</div>
			</div>
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="update_end" name="update_end" value=""/>
			</div>
			</div>
			 <div class="search-td-search-btn">
				<div id="searchbtn">搜索</div>
			</div>
			</div>
			</div>
		 <div id="maingrid" style="margin: 0; padding: 0"></div>
   </form>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
  </body>
</html>
