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
 
var type=${type};
 /* 查看活动 */
 function seeActivity(activityId) {
		$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "品牌特卖活动专题审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allSeeActivityInfo.shtml?activityId=" + activityId+"&type="+type,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 /* 权限控制*/
 function seeActivityAuth(activityId) {
		$.ligerDialog.open({
		height: 800,
		width: 600,
		title: "品牌特卖商家权限管理",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/activityAuthInfo.shtml?activityId=" + activityId+"&type="+type,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
	
 /* 查看活动商品 */
 function seeActivityProduct(activityId) {
		$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "品牌特卖专题活动商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allActivityProductList.shtml?activityId=" + activityId+"&type="+type,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
 
 var listConfig={
	  
      url:"/activity/allOperateAuditListData.shtml?type=${type}",
   
      btnItems:[],
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                        {display:'特卖ID',name:'id'},
                        {display:'活动名称',name:'name'},
                        {display:'利益点',name:'benefitPoint'},
		                {display:'商家名称', name:'shortName'},
		                {display:'商家序号',name:'mchtCode'},
		                {display:'对接运营',name:'contactName'},
		                {display:'商品数',name:'productNum'},
		                {display:'库存',name:'activityStock'},
						{display:'期望活动开始时间',name:'expectedStartTime',width: 150,render:function(rowdata,rowindex){
							if(rowdata.expectedStartTime==null||rowdata.expectedStartTime==""||rowdata.expectedStartTime==undefined){
								return "";
							}else{
								var expectTime=new Date(rowdata.expectedStartTime);
								return expectTime.format("yyyy-MM-dd");								
							}
						}},
						{display: '最后审核时间', name: 'updateDate', render: function(rowdata, rowindex) {
			              	   if(rowdata.updateDate!=null){
								   var updateDate=new Date(rowdata.updateDate);
				            	   return updateDate.format("yyyy-MM-dd hh:mm:ss");
			              	   }else{return "";}
			            }},
						{display:'审核状态',name:'cooAuditStatusDesc',render:function(rowdata, rowindex){
							if(rowdata.cooAuditStatus==null){
                    			return "未审核";
                    		}else if(rowdata.cooAuditStatus==1){
                    			return "<samp style='color: #FF6600;'>"+rowdata.cooAuditStatusDesc+"</samp>";
                    		}
                    		else if(rowdata.cooAuditStatus==2){
                    			return "<samp style='color: #0000FF;'>"+rowdata.cooAuditStatusDesc+"</samp>";
                    		}
                    		else if(rowdata.cooAuditStatus==3){
                    			return "<samp style='color: red;'>"+rowdata.cooAuditStatusDesc+"</samp>";
                    		}
						}},
		                { display: "操作", name: "OPER",align: "center",width: 200, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:seeActivity(" + rowdata.id + ");\">查看活动</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:seeActivityProduct(" + rowdata.id + ");\">查看商品</a>&nbsp;&nbsp;");
						    html.push("<a href=\"javascript:seeActivityAuth(" + rowdata.id + ");\">权限管理</a>&nbsp;&nbsp;");
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
        fromName:"operateAuditForm",
        listGridName:"maingridOperate"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="operateAuditForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label"  >特卖ID：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "activityId" name="activityId" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >活动名称：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "name" name="name" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >商家：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "shortName" name="shortName" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >商家序号：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "mchtCode" name="mchtCode" >
			</div>
		</div>
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label"  >审核状态：</div>
			<div class="search-td-combobox-condition" >
				<select id="cooAuditStatus" name="cooAuditStatus">
					<option value="">不限</option>
					<c:forEach items="${cooAuditStatus}" var="list">
						<c:if test="${list.statusValue!=0}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			</div>

			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>
		</div>
		
		<div id="maingridOperate" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
