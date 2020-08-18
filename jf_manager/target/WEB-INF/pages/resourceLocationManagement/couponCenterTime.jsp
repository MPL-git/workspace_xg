<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<script type="text/javascript">
	
$(function(){
	
	$(".l-dialog-close").live("click", function(){
		$("#searchbtn").click();
	});
	
});

		//查看
		function watchProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "查看店铺信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/watchProduct.shtml?sourceId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	//添加时间
	function add() {
		$.ligerDialog.open({
			height: $(window).height() - 600,
			width: $(window).width() - 1100,
			title: "添加时间点",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/editCouponCenterTime.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	//修改时间
	function update(id) {
		$.ligerDialog.open({
			height: $(window).height() - 600,
			width: $(window).width() - 1100,
			title: "修改时间点",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/editCouponCenterTime.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	//修改状态
	function updateStatus(id,status) {
		var warningStr = '是否关闭?';
		if(status == 1){
            warningStr = '是否开启?';
		}
		$.ligerDialog.confirm(warningStr, function(yes) {
			if(yes) {

				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/resourceLocationManagement/updateStatusCouponCenterTime.shtml",
					data : {id : id,status : status},
					dataType : 'json',
					success : function(data){
						if(data.returnCode == "0000" )
                            location.reload();
						else{
							commUtil.alertError(data.message);
						}
					},
					error : function(e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}
		});
	}

	//删除时间点
	function del(id) {
		$.ligerDialog.confirm('是否删除?', function(yes) {
			if(yes) {

				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/resourceLocationManagement/delCouponCenterTime.shtml",
					data : {id : id},
					dataType : 'json',
					success : function(data){
						if(data.returnCode == "0000" )
                            location.reload();
						else{
							commUtil.alertError(data.message);
						}
					},
					error : function(e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}
		});
	}

	 
 var listConfig={
      url:"/resourceLocationManagement/couponCenterTimeData.shtml?pagetype=${pagetype}",
      btnItems:[{ text: '添加时间', icon:'add', id:'add', dtype:'win', click:add }],
      listGrid:{ columns: [
			            { display: '时间', name: '', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.startHours != null && rowdata.startHours != "" && rowdata.startMin != null && rowdata.startMin != ""){
   	   		          	       return 	rowdata.startHours+':'+rowdata.startMin;
   		                	}
		                }},
			            { display: '时长', width: 200, render: function(rowdata, rowindex){
		                	var html = [];
		                	if(rowdata.continueHours != null && rowdata.continueHours != "") {
                                html.push(rowdata.continueHours + "小时");
                            }
                            if(rowdata.continueMin != null && rowdata.continueMin != "") {
                                html.push(rowdata.continueMin + "分钟");
                            }
		                    return html.join("");
		                 }},
		                { display: '状态', name: '', width: 180 ,render: function(rowdata, rowindex) {
		                    var html = [];
							if(rowdata.status != null && rowdata.status != "") {
                                if(rowdata.status == '1') {
                                    html.push("启用");
                                }
                                if(rowdata.status == '0') {
                                    html.push("关闭");
                                }
							}
							return html.join("");
		                }},
			             { display: '操作', width: 150, render: function(rowdata, rowindex) {
			                 var html = [];
							 if(rowdata.status == '1') {
                                 html.push("<a href=\"javascript:updateStatus(" + rowdata.id +",0);\">关闭</a>");
							 }
							 if(rowdata.status == '0') {
                                 html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ",1);\">启用</a> | <a href=\"javascript:update(" + rowdata.id + ");\">修改</a> | <a href=\"javascript:del(" + rowdata.id + ");\">删除</a>");
							 }
							 return html.join("");
				         }}
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
		<div id="topmenu"></div>
		<div class="search-pannel">


			</div>
	</form>
	   <div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
