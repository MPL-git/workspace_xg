<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	 $(function() {
		 $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		 });
	 });
	 
	//导入
	function inBlack() {
 		$.ligerDialog.open({
 			height: $(window).height() - 400,
 			width: $(window).width() - 1200,
 			title: "导入",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/taskSms/inBlackManager.shtml",
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	}
	
	//导出
	function outBlack() {
        var data = listModel.gridManager.getSelectedRows();
        if (data.length > 0 ) {
            var str = "";
            $(data).each(function(){
                if(str == '' ) {
                    str = this.id ;
                }else {
                    str += ","+this.id ;
                }
            });
            $("#ids").val(str);
        }
        $("#dataForm").attr("action","${pageContext.request.contextPath}/taskSms/outBlack.shtml?ids="+str);
        $("#dataForm").submit();

 	}
	
	//添加
	function addBlack() {
 		$.ligerDialog.open({
 			height: $(window).height() - 400,
 			width: $(window).width() - 1200,
 			title: "添加",
 			name: "INSERT_WINDOW",
 			url: "${pageContext.request.contextPath}/taskSms/addBlackManager.shtml",
 			showMax: true,
 			showToggle: false,
 			showMin: false,
 			isResize: true,
 			slide: false,
 			data: null
 		});
 	 }
	 
	 //移出
	 function delBlack(id) {
		 if(id == 0 ) {
				var data = listModel.gridManager.getSelectedRows();
			    if (data.length == 0 ) {
			  		commUtil.alert("请选择行");
			  		return;
			    }else {
			       	var str = "";
			        $(data).each(function(){    
						if(str == '' ) {
						 str = this.id ;
						}else {
						 str += ","+this.id ;
						}
			        });
			        id = str;
			    }
		 }
		 $.ligerDialog.confirm("是否移出？", function(status){  
            if(status==true){
           	 $.ajax({
       			 type: 'post',
       			 url: '${pageContext.request.contextPath }/taskSms/delBlack.shtml',
       			 data: {id : id},
       			 dataType: 'json',
       			 success: function(data) {
       				 if(data == null || data.statusCode != 200){
       				     commUtil.alertError(data.message);
       				 }else {
       					 $("#searchbtn").click();
       				 }
       			 },
       			 error: function(e) {
       				 commUtil.alertError("系统异常请稍后再试");
       			 }
       		 });
            }
        });
	 }
	 
 	 var listConfig={
	      url:"/taskSms/getBlackList.shtml",
	      btnItems:[
						{ text: '导入', icon:'add', id:'add', dtype:'win', click:inBlack },
						{ line:true }, 
						{ text: '导出', icon:'add', id:'add', dtype:'win', click:outBlack },
						{ line:true },
						{ text: '添加', icon:'add', id:'add', dtype:'win', click:addBlack }
				],
	      listGrid:{ columns: [
							{display:'用户ID',name:'memberId', align:'center', isSort:false, width:120},
							{display:'手机号',name:'memberMobile', align:'center', isSort:false, width:200},
							{display:'操作',name:'', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:delBlack(" + rowdata.id + ");\">【移出】</a>");
								return html.join("");
							}}
			         ], 
	                 showCheckbox : true,  //不设置默认为 true
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" id="ids" name="ids" >
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >用户ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >手机号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberMobile" name="memberMobile" >
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;" >
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" class="l-button" style="width: 60px;height: 25px;cursor: pointer;" value="批量移除" onclick="delBlack(0);">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
