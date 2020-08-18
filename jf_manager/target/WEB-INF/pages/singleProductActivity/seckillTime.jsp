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
 
 var listConfig={
      url:"/singleProductActivity/getSeckillTimeList.shtml",
      btnItems:[{ text: '添加秒杀时间', icon: 'add', dtype: 'win', click: itemclick, url: '/singleProductActivity/editSeckillTime.shtml', seqId:'', width: 550, height: 400}],
      listGrid:{ columns: [
                        {display:'开始时间点', name:'startTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        	return rowdata.startHours + ":" + rowdata.startMin;
                        }},
                        {display:'时长', name:'startMinTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        	return rowdata.continueHours + "小时" + rowdata.continueMin + "分钟";
                        }},
                        {display:'秒杀类型', name:'seckillType', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        	if(rowdata.seckillType == '1'){
                        		return "限时秒杀";
                        	} else{
                        		return "会场秒杀";
                        	}
                        }},
                        {display:'状态', name:'status', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
                        	if(rowdata.status == '0') 
                        		return "关闭";
                        	else
                        		return "启用";
                        }},
                        {display:'操作', name:'oper', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
							var html = [];
							if(rowdata.status == '0') {
						   		html.push("<a href=\"javascript:updateSeckillTime(" + rowdata.id + ", 'start', " + rowdata.seckillType + ");\">启用</a>&nbsp;&nbsp;"); 
							    html.push("<a href=\"javascript:updateSeckillTime(" + rowdata.id + ", '', " + rowdata.seckillType + ");\">修改</a>&nbsp;&nbsp;"); 
							    html.push("<a href=\"javascript:updateSeckillTime(" + rowdata.id + ", 'del', " + rowdata.seckillType + ");\">删除</a>&nbsp;&nbsp;"); 
							}else{
							    html.push("<a href=\"javascript:updateSeckillTime(" + rowdata.id + ", 'close', " + rowdata.seckillType + ");\">关闭</a>&nbsp;&nbsp;");
							}
						    return html.join("");
						  }
		            	 }
		              ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true, //不设置默认为 true
                 usePager: true //不分页
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  
  function updateSeckillTime(id, flag, seckillType) {
	  if('${roleId }' != '1' && '${roleId90 }' != '90' ) { //类型=限时抢购，操作那一列需要判断权限，只有超级管理员才可操作
		  $.ligerDialog.question("请联系超级管理员进行修改！");
	  }else {
		  if(flag != '') {
	 		  $.ligerDialog.confirm('请您确认要执行此操作？', function (yes) { 
					if(yes) {
						$.ajax({
	    					url : "${pageContext.request.contextPath}/singleProductActivity/updateSeckillTime.shtml",
	    					type : 'POST',
	    					dataType : 'json',
	    					cache : false,
	    					async : false,
	    					data : {
	    						id:id,
	    						flag:flag
	    					},
	    					timeout : 30000,
	    					success : function(data) {
	    						if (data.statusCode == "200") {
	    							$("#searchbtn").click();
	    						}else{
	    							$.ligerDialog.error(data.message);
	    						}
	    					},
	    					error : function() {
	    						$.ligerDialog.error('操作超时，请稍后再试！');
	    					}
	    				});
					}
			  });
		  }else{
			  editSeckillTime(id);
		  }
	  }
  }
 
  function editSeckillTime(id) {
	  $.ligerDialog.open({
		    height: 400,
			width: 550,
			title: "修改秒杀时间",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/singleProductActivity/editSeckillTime.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	  });
  }
  
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
		<div id="searchbtn" style="display: none; ">搜索</div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>

