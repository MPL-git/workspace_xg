<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css" />

 <script type="text/javascript">

	 function getkfStaffid(id) {
			$.ligerDialog.confirm("是否领取？", function (yes) 
			{ 
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/businessCirclesAppealOrder/kfbusinessCirclesAppealOrder.shtml?id=" + id,
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
		
	 
	 function businessCirclesAppealOrderPic(id) {
			$.ligerDialog.open({
				height: $(window).height() - 40,
				width: 1200,
				title: "工商投诉原件",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/businessCirclesAppealOrder/businessCirclesAppealOrderPic.shtml?id=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		} 
	 
	 
 	 var listConfig={ 
	      url:"/businessCirclesAppealOrder/unclaimedbusinessCirclesAppealOrderdata.shtml",
	      listGrid:{ columns: [
							{display:'工商投诉编号',name:'id', align:'center', isSort:false, width:100},
							{display:'创建人',name:'createbyName', align:'center', isSort:false, width:100},
							{display:'消费者投诉时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
				             var html = [];
							 if(rowdata.consumerAppealDate != null && rowdata.consumerAppealDate != '' ) {
								var consumerAppealDate = new Date(rowdata.consumerAppealDate);
									html.push(consumerAppealDate.format("yyyy-MM-dd"));
							    }
								return html.join("");
				            }},
			            
				           {display:'登记编号',name:'registrationNumber', align:'center', isSort:false, width:200},
				           {display:'投诉人',name:'appealName', align:'center', isSort:false, width:100},
				           {display:'投诉人联系电话',name:'appealMobile', align:'center', isSort:false, width:200},
						   {display:'投诉单类型',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
							if (rowdata.appealOrderType=='1') {
								return "投诉单";
							}
							if (rowdata.appealOrderType=='2' ) {
								return "举报单";
							}
							   
						   }},
						   {display:'被投诉类型',name:'typesOfComplaints', align:'center', isSort:false, width:100},
						   {display:'消费者投诉具体内容',name:'consumerAppealContent', align:'center', isSort:false, width:300},
						   {display:'工商投诉原件',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
							   return "<a href=\"javascript:businessCirclesAppealOrderPic(" + rowdata.id + ");\">查看</a>";
						   }},
						   {display:'投诉单状态',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
							 if (rowdata.appealStatus=='0') {
								 return "待跟进";
						     }
				  				   
						  }},				
						  { display: '客服处理人', width:100 , render: function(rowdata, rowindex) {
	 		                	return "<a href=\"javascript:getkfStaffid(" + rowdata.id + ");\">领取</a>";
			              }}

			         ], 			    
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 						
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
	<form id="dataForm" runat="server" >
		<div id="searchbtn" style="display:none;">搜索</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
