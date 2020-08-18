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
 
 var type="${type}";
 /* 查看活动 */
 function seeDesignOrLawVenueActivity(activityId) {
		$.ligerDialog.open({
		height: $(window).height() ,
		width: $(window).width() - 100,
		title: "官方会场商家报名审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityArea/venueActivityInfo.shtml?activityId=" + activityId+"&type="+type,
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
		title: "官方会场活动商品审核",
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
 
 function receive(activityId){
	 var type="${type}";
	 $.ajax({ //ajax提交
			type:'POST',
			url:'${pageContext.request.contextPath}' +"/activity/receive.shtml",
			data:{activityId:activityId,type:type},
			dataType:"json",
			cache: false,
			success: function(json){
			   if(json==null || json.statusCode!=200){
			     commUtil.alertError(json.message);
			  }else{
              		$.ligerDialog.success("操作成功",function() {
                         javascript:location.reload();
					});
			  }
			},
			error: function(e){
			 commUtil.alertError("系统异常请稍后再试");
			}
 });
 }
 
 var listConfig={
	  
      url:"/activityArea/seeActivityMchtListData.shtml?type=${type}",
   
      btnItems:[],
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                         {display:'特卖ID',name:'id'},
                         {display:'会场名称',name:'activityAreaName'},
                         {display:'活动类型',name:'activityAreaTypeDesc'},
                         {display:'品牌特卖名称',name:'name'},
	   		             {display:'商家名称', name:'shortName'},
	   		             {display:'商家序号',name:'mchtCode'},
	   		             {display:'商品数',name:'productNum'},
	   		             {display:'库存',name:'activityStock'},
						/* {display:'期望活动开始时间',name:'expectActivityTime',width: 150,render:function(rowdata,rowindex){
							if(rowdata.expectActivityTime==null||rowdata.expectActivityTime==""||rowdata.expectActivityTime==undefined){
								return "";
							}else{
								var expectTime=new Date(rowdata.expectActivityTime);
								return expectTime.format("yyyy-MM-dd");								
							}
						}}, */
			            {display: '最后审核时间', name: 'updateDate', render: function(rowdata, rowindex) {
			              	   if(rowdata.updateDate!=null){
				            		var updateDate=new Date(rowdata.updateDate);
				            	   return updateDate.format("yyyy-MM-dd hh:mm:ss");
			              	   }else{return "";}
			            }},
						{display:'审核人',name:'designOrLawName',render:function(rowdata,rowindex){
							if(type==3){
								if(rowdata.designName==null|| rowdata.designName==""||rowdata.designName==undefined){
									return "<input type='button' style='background-color:rgba(0, 218, 236, 1);width: 50px;height: 30px;color: #FFFFFF;' value='领取' onclick='receive("+rowdata.id+");'>";
								}else{
									return rowdata.designName;
								}
							}else{
								if(rowdata.lawName==null|| rowdata.lawName==""||rowdata.lawName==undefined){
									return "<input type='button' style='background-color:rgba(0, 218, 236, 1);width: 50px;height: 30px;color: #FFFFFF;' value='领取' onclick='receive("+rowdata.id+");'>";
								}else{
									return rowdata.lawName;
								}
							}
						}},
						{display:'审核状态',name:'designAuditStatusDesc',render:function(rowdata, rowindex){
							if(type==3){
								if(rowdata.designAuditStatus==null||rowdata.designAuditStatus==0){
                        			return "未审核";
                        		}else if(rowdata.designAuditStatus==1){
                        			return "<samp style='color: #FF6600;'>"+rowdata.designAuditStatusDesc+"</samp>";
                        		}
                        		else if(rowdata.designAuditStatus==2){
                        			return "<samp style='color: #0000FF;'>"+rowdata.designAuditStatusDesc+"</samp>";
                        		}
                        		else if(rowdata.designAuditStatus==3){
                        			return "<samp style='color: red;'>"+rowdata.designAuditStatusDesc+"</samp>";
                        		}
							}else{
								if(rowdata.lawAuditStatus==null||rowdata.lawAuditStatus==0){
                        			return "未审核";
                        		}else if(rowdata.lawAuditStatus==1){
                        			return "<samp style='color: #FF6600;'>"+rowdata.lawAuditStatusDesc+"</samp>";
                        		}
                        		else if(rowdata.lawAuditStatus==2){
                        			return "<samp style='color: #0000FF;'>"+rowdata.lawAuditStatusDesc+"</samp>";
                        		}
                        		else if(rowdata.lawAuditStatus==3){
                        			return "<samp style='color: red;'>"+rowdata.lawAuditStatusDesc+"</samp>";
                        		}
							}
						}},
		                { display: "操作", name: "OPER",align: "center",width: 200, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:seeDesignOrLawVenueActivity(" + rowdata.id + ");\">查看活动</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:seeActivityProduct(" + rowdata.id + ");\">查看商品</a>&nbsp;&nbsp;");
						    return html.join("");
					 }
	             }
		                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
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
			<div class="search-td">
			<div class="search-td-label"  >审核人：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "auditName" name="auditName" >
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label"  >审核状态：</div>
			<div class="search-td-combobox-condition" >
				<select id="designOrlawAuditStatus" name="designOrlawAuditStatus">
					<option value="">不限</option>
					<c:if test="${type eq 3 }">
						<c:forEach items="${designAuditStatus}" var="list">
							<c:if test="${list.statusValue!=0}">
							<option value="${list.statusValue}">${list.statusDesc}
							</option>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${type eq 4 }">
						<c:forEach items="${lawAuditStatus}" var="list">
							<c:if test="${list.statusValue!=0}">
							<option value="${list.statusValue}">${list.statusDesc}
							</option>
							</c:if>
						</c:forEach>
					</c:if>
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
