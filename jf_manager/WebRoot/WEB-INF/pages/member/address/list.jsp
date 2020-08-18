<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript">
$(function(){
	  var data = [{id : "province", pid : null, data : "${pageContext.request.contextPath}/webcommon/prodata.shtml"},
	              {id : "city", pid : "province", data : "${pageContext.request.contextPath}/webcommon/areadata.shtml"},
	    	      {id : "county", pid : "city", data : "${pageContext.request.contextPath}/webcommon/areadata.shtml"}];
	      LinkageComboBox.init(data);
	      $("#province").ligerComboBox({selectBoxWidth: 200, selectBoxHeight: 200});
		  $("#city").ligerComboBox({selectBoxWidth: 200, selectBoxHeight: 200});
		  $("#county").ligerComboBox({selectBoxWidth:200, selectBoxHeight: 200});
});

function viewDetail(id) {
	$.ligerDialog.open({
	height: $(window).height() - 100,
	width: $(window).width() - 200,
	title: "会员资料",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/member/info/detail.shtml?id=" + id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}
	  var listConfig={
// 		      url:"/member/address/dataList.shtml",
		      url:"",
		    
		      listGrid:{ columns: [  
			                    { display: '地址ID', name: 'id'},
			                    { display: '会员ID',  name: 'memberId'},
			                    { display: '收货人',  name: 'recipient'},
			                    { display: '收货电话',  name: 'phone', render: function(rowdata, rowindex) {
		    	  					if(rowdata.phone != '') {
		    	  						return rowdata.phone.substring(0, 6)+"**"+rowdata.phone.substring(rowdata.phone.length-1);
		    	  					}else {
		    	  						return rowdata.phone;
		    	  					}
		      					}},
			                    { display: '收货地址',  name: 'fullAddress', render: function(rowdata, rowindex) {
		    	  					if (rowdata.fullAddress != '' && rowdata.fullAddress.indexOf(rowdata.address) != -1){
		    	  						return "***"+rowdata.fullAddress.substring(rowdata.fullAddress.indexOf(rowdata.address)); 
		    	  					}else{
		    	  						return rowdata.fullAddress;
		    	  					}
		      					}},
			                    { display: '邮编', name: 'zipCode'},
				                { display: '是否默认',  name: 'isPrimary', render: function(rowdata, rowindex) {
		    	  					if (rowdata.isPrimary=="Y"){
		    	  						return "是";
		    	  					}else{
		    	  						return "否";
		    	  					}
		      					}},
				                { display: "操作", name: "OPER", align: "center", render: function(rowdata, rowindex) {
									var html = [];
									html.push("<a href=\"javascript:viewDetail(" + rowdata.memberId + ");\">查看会员</a>");
								    return html.join("");
							 	}}
				               ],   
		                 showCheckbox : false,  //不设置默认为 true
		                 showRownumber:true, dataAction: 'server' 
		      } ,     						
		     container:{
		        toolBarName:"toptoolbar",
		        searchBtnName:"searchbtn",
		        fromName:"dataForm",
		        listGridName:"maingrid"
		      } 
		       
		  };
	  
	  function excel(){
		  
			$("#dataForm").attr("action",contextPath +"/memberAccount/integralDtl/downloadList.shtml");
			$("#dataForm").submit();
		}
	  
	  function subFunction() {
		  	var memberId = $("[name='memberId']").val();
		  	var recipient = $("[name='recipient']").val();
		  	var phone = $("[name='phone']").val();
		  	var province = $("[name='province']").val();
		  	var city = $("[name='city']").val();
		  	var county = $("[name='county']").val();
			if(memberId != '' || recipient != '' || phone != '' || province != ''
					|| city != '' || county != '' ) {
				listModel.ligerGrid.url = '${pageContext.request.contextPath}/member/address/dataList.shtml';
				listModel.initdataPage();
			}else {
				commUtil.alertError("对不起，请设置搜索条件。");
			}
	  }
	  
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr"> 	
			<div class="search-td">
			<div class="search-td-label">会员ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "memberId" name="memberId" >
			</div>
			</div>
			 	
			<div class="search-td">
			<div class="search-td-label">收货人：</div>
			<div class="search-td-condition" >
				<input type="text" id = "recipient" name="recipient" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">收货电话：</div>
			<div class="search-td-condition" >
				<input type="text" id = "phone" name="phone" >
			</div>
			</div>
					
		</div>
		
		<div class="search-tr">
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">省：</div>
			<div class="l-panel-search-item">
				<input id="province" name="province" type="text" style="float:left;width: 100%;"/>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">市：</div>
			<div class="l-panel-search-item" >
				<input id="city" name="city" type="text" style="float:left;width: 100%;"/>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">区：</div>
			<div class="l-panel-search-item" >
				<input id="county" name="county" type="text" style="float:left;width: 100%;"/>
			</div>
			</div>
			
			<div class="search-td-search-btn">
				<div class="l-button" onclick="subFunction();" >搜索</div>
			</div>
			
		</div>
	</div>
		
	<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>


