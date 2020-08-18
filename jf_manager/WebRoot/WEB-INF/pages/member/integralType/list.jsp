<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>

<html>
<head>

<script type="text/javascript">
function stopIntegralType(id, name) {
	var title="是否停用规则: "+name;
	$.ligerDialog.confirm(title, function (yes) { 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/member/integralType/stop.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {id:id},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$("#maingrid").ligerGetGridManager().reload();
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

function startIntegralType(id, name) {
	var title="是否启用规则: "+name;
	$.ligerDialog.confirm(title, function (yes) { 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/member/integralType/start.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {id:id},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$("#maingrid").ligerGetGridManager().reload();
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

function editIntegralType(id) {
	$.ligerDialog.open({
		height: 650,
		width: 800,
		title: "修改积分规则",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/member/integralType/edit.shtml?id=" +id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
      url:"/member/integralType/datalist.shtml",
     
	  /* btnItems:[{ text: '增加积分规则', icon: 'add',  dtype:'win',  checkType:'single', click: itemclick, url:"/member/integralType/add.shtml",  seqId:"",height:650,width:800}], */
      listGrid:{ columns: [   { display: '积分类型ID', name: 'id' },
		                { display: '积分类型名称', name: 'name'},
		                { display: '进出帐类型', name: 'tallyModeName'},
		                { display: '状态',  name: 'statusDesc'},
		                { display: '积分配置方式', name: 'intTypeDesc' },
		                { display: '定额积分', render: function(rowdata, rowindex) {
		                	if (rowdata.integral==null || rowdata.integral==0){
		                		return "-";
		                	}else{
		                		return rowdata.integral;
		                	}
		                }},
		                { display: '积分/元', render: function(rowdata, rowindex) {
		                	if (rowdata.iCharge==null || rowdata.iCharge==0){
		                		return "-";
		                	}else{
		                		return rowdata.iCharge;
		                	}
		                }},
		                { display: '成长值配置方式', name: 'growTypeDesc' },
		                { display: '定额成长值 ', render: function(rowdata, rowindex) {
		                	if (rowdata.gValue==null || rowdata.gValue==0){
		                		return "-";
		                	}else{
		                		return rowdata.gValue;
		                	}
		                }},
		                { display: '成长值/元', render: function(rowdata, rowindex) {
		                	if (rowdata.gCharge==null || rowdata.gCharge==0){
		                		return "-";
		                	}else{
		                		return rowdata.gCharge;
		                	}
		                }},
		                { display: '修改人', name: 'staffName' },
		                { display: '修改时间', name: 'updateDate', render: function(rowdata, rowindex) {
    	  					var date=new Date(rowdata.updateDate);
    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
      					}},
		                { display: '备注', name: 'remarks' },
		                { display: "操作", name: "OPER", align: "center", render: function(rowdata, rowindex) {
							var html = [];
							/* html.push("<a href=\"javascript:editIntegralType(" + rowdata.id + ");\">修改</a>&nbsp;&nbsp;"); */
							if (rowdata.status==1){
								html.push("<a href=\"javascript:stopIntegralType(" + rowdata.id + ",\'"+rowdata.name+"\');\">停用</a>");
							}else{
								html.push("<a href=\"javascript:startIntegralType(" + rowdata.id + ",\'"+rowdata.name+"\');\">启用</a>");
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
       
  }
  
</script>
 <style type="text/css">
    	.search_right {
    		width: 80px;
    		text-align: right;
    	} 
    </style>
</head>
<body style="padding: 0px; overflow: hidden;">
	 <div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
<!-- 			<div id="topmenu"></div>
			<div id="toptoolbar"></div> -->
			<div class="l-panel-search" style="display:none;">
			 
				<div class="l-panel-search-item" >
					<div id="searchbtn" style="width: 135px;" >
						搜索
					</div>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		<div style="display: none;">
		</div>
		</div>
				 
	</body> 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js">
</script>

</html>
    