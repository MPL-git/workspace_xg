<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

<html>
<style>
	.seqClass{width:50px;height:23px;text-align:center; border:1px solid red;box-sizing: border-box;border-width: 1px; border-style: solid; border-color: rgba(121, 121, 121, 1);border-radius: 0px; }
</style>
<script type="text/javascript">
	var linkValueList;
	$(function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
					linkValueList = data.linkValueList;
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
		$(".dateTime").ligerDateEditor({
			showTime : true,
			labelWidth : 150,
			width : 150,
			format : "yyyy-MM-dd hh:mm",
			labelAlign : 'left'
		});
		
		$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		});
	});
	
	function chgAdInfoStatus(id, status) {
		var title;
		if (status=='1'){
			title="上架";
		}else{
			title="下架";
		}
		$.ligerDialog.confirm("是否"+title+"？", function (yes) {
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/appMng/adMng/chgStatus.shtml",
					data : {id : id, status : status},
					dataType : 'json',
					success : function(data) {
						if ("0000" == data.returnCode) {
							listModel.gridManager.reload();
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
					},
					error : function(){
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
	}
	
	function delAdInfo(id) {
		$.ligerDialog.confirm("是否删除？", function (yes) {
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/appMng/adMng/del.shtml",
					data : {id : id},
					dataType : 'json',
					success : function(data) {
						if('0000' == data.returnCode) {
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
	
	function editAdInfo(id) {
		$.ligerDialog.open({
			height: 600,
			width: 900,
			title: "修改广告",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/aouAdinfoManager.shtml?id="+id+"&flag=1&statusFlag=${flag }",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function showHomePage() {
		var catalog = $("#catalog").val();
		var adInfoStatus = $("#adInfoStatus").val();
		var autoUpDate = $("#autoUpDate").val();
		$.ligerDialog.open({
			height: 800,
			width: 800,
			title: "APP首页效果图",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/previewAd.shtml?adInfoStatus="+adInfoStatus+"&autoUpDate="+autoUpDate,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function pic_show(url) {
		$("#pic_img").attr("src",url);
		$("#pic_Div").show(100);
	}
	
	function pic_hide() {
		$("#pic_Div").hide(100);
	}
	
	var listConfig = {
		      url:"/appMng/prefectureImageList.shtml",
		      btnItems:[{ text:'添加', icon:'add', id:'add', dtype:'win', click:itemclick, url:"/appMng/aouAdinfoManager.shtml?flag=1&statusFlag=${flag }", seqId:"", width: 900, height: 600 }], 
		      listGrid:{ columns: [
		                        {display:'图片',name:'pic', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
		                        	return "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' onclick='pic_show(this.src)'>";
		                        }},
		                        {display:'备注名称',name:'remarks', align:'center', isSort:false, width:180 },
		                        {display:'位置',name:'position', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		                        	if('${flag }' == '5') {
		                        		if(rowdata.catalog == '6')
			                        		return "新用户专享";
			                        	if(rowdata.catalog == '7')
			                        		return "限时抢购";
			                        	if(rowdata.catalog == '8')
			                        		return "新用户秒杀入口背景图";
			                        	if(rowdata.catalog == '9')
			                        		return "新用户秒杀频道";
			                        	if(rowdata.catalog == '10')
			                        		return "积分抵扣频道";
		                        	}else{
			                        	if(rowdata.position == '1')
			                        		return "头部海报";
			                        	if(rowdata.position == '2')
			                        		return "广告位1";
			                        	if(rowdata.position == '3')
			                        		return "广告位2";
			                        	if(rowdata.position == '4')
			                        		return "首页新用户专区";
			                        	if(rowdata.position == '5')
			                        		return "首页秒杀专区";
			                        	if(rowdata.position == '6')
			                        		return "首页爆款专区";
			                        	if(rowdata.position == '7')
			                        		return "首页品牌专区";
			                        	if(rowdata.position == '9')
			                        		return "推荐区域";
			                        	if(rowdata.position == '10')
			                        		return "积分抵扣";
			                        	if(rowdata.position == '11')
			                        		return "断码清仓";
		                        	}
		                        }},
		                        {display:'类型',name:'linkType', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
		                        		return rowdata.linkTypeDesc;
		                        }},
		                        {display:'ID',name:'linkId', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
		                        	if(rowdata.linkType=='28'){
		                        		if (rowdata.mallName) {
		        	            			return rowdata.mallName;
		        						}
		                        	}else if (rowdata.linkType=='11') {
		        	            		if (rowdata.productTypeName) {
		        	            			return rowdata.productTypeName;
		        						}
		        					}else if (rowdata.linkType=='10') {
		        						if (rowdata.mchtCode) {
		        							return rowdata.mchtCode;
		        						}
		        					}else if (rowdata.linkType=='9') {
		        						  if (rowdata.brandTeamTypeName) {
		        							  return rowdata.brandTeamTypeName;
		        						}
		        					}else if (rowdata.linkType=='6') {
		        						   for (var i= 0; i < linkValueList.length; i++) {
		        							    if (linkValueList[i].linkValue==rowdata.linkId) {
		        							    	return linkValueList[i].linkValueName;
		        								}
		        						  	}
		        					}else if(rowdata.linkType=='3' || rowdata.linkType=='4' || rowdata.linkType=='5'){//3.商品4.外部链接5.无连接
		        						return rowdata.linkUrl;
		        					}else{
		        	            	  return rowdata.linkId;
		        					}
		                        }},
		                        {display:'上架时间',name:'autoUpDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		                        	if (rowdata.autoUpDate != null){
		        		            	var autoUpDate = new Date(rowdata.autoUpDate);
		        		            	return autoUpDate.format("yyyy-MM-dd hh:mm");	
		        	            	}
		                        	return "";
		                        }},
		                        { display: '活动开始时间', name:'activityBeginTime', align:'center', isSort:false, width: 200, render: function(rowdata, rowindex) {
		        	            	if (rowdata.activityBeginTime != null){
		        		            	var activityBeginTime = new Date(rowdata.activityBeginTime);
		        		            	var autoDownDate = new Date(rowdata.autoDownDate);
		        		            	if (activityBeginTime >= autoDownDate){
		        		            		return "<span style='color:#FF0000'>"+activityBeginTime.format("yyyy-MM-dd hh:mm:ss")+"</span>";
		        		            	}else{
		        		            		return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");
		        		            	}
		        	            	}
		        	         	}},
		        	         	{ display: '活动结束时间', name:'activityEndTime', align:'center', isSort:false, width: 200, render: function(rowdata, rowindex) {
		        	            	if (rowdata.activityEndTime != null){
		        	            		var autoDownDate = new Date(rowdata.autoDownDate);
		        		            	var activityEndTime = new Date(rowdata.activityEndTime);
		        		            	if (autoDownDate > activityEndTime){
		        		            		return "<span style='color:#FF0000'>"+activityEndTime.format("yyyy-MM-dd hh:mm:ss")+"</span>";
		        		            	}else{
		        		            		return activityEndTime.format("yyyy-MM-dd hh:mm:ss");
		        		            	}
		        	            	}
		        	         	}},
		        	         	{display:'上架状态', name:'status', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
		                        	if(rowdata.status == '0')
		                        		return "下架";
		                        	if(rowdata.status == '1')
		                        		return "上架";
		                        }} ,
		                        { display:'操作', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
		        					var html = [];
		        					if (rowdata.status=='0'){
		        					    html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'1');\">上架</a>"); 
		        					    html.push("<a href=\"javascript:editAdInfo(" + rowdata.id + ");\">修改</a>");
		        					    html.push("<a href=\"javascript:delAdInfo(" + rowdata.id + ");\">删除</a>");
		        					}else{
		        						html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'0');\">下架</a>");
		        					}
		        				    return html.join("&nbsp;&nbsp;");
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
<body style="padding: 0px; overflow: hidden;">
	<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
		<div id="toptoolbar"></div>
		<form id="dataForm" runat="server" >
			<input type="hidden" name="flag" value="${flag }" >
			<div class="search-pannel">
				<div class="search-tr"  >
				
					<c:if test="${flag == '3' }">
						<div class="search-td">
			 				<div class="search-td-label" >位置：</div>
			 				<div class="search-td-combobox-condition" >
								<select id="position" name="position" style="width: 150px;" >
									<option value="" selected >请选择...</option>
									<option value="4" >首页新用户专区</option>
									<option value="5" >首页秒杀专区</option>
									<option value="6" >首页爆款专区</option>
									<option value="10" >积分抵扣</option>
									<option value="11" >断码清仓</option>
									<option value="2" >首页专区广告位1</option>
									<option value="3" >首页专区广告位2</option>
								</select>
		 	 				</div>
			 			</div>
					</c:if>
					<c:if test="${flag == '5' }">
						<div class="search-td">
			 				<div class="search-td-label" >频道：</div>
			 				<div class="search-td-combobox-condition" >
								<select id="catalog" name="catalog" style="width: 150px;" >
									<option value="" selected >请选择...</option>
									<option value="7" >限时抢购频道广告</option>
									<option value="6" >新用户频道广告</option>
									<option value="8" >新用户秒杀入口背景图</option>
									<option value="9" >新用户秒杀频道</option>
									<option value="10" >积分抵扣频道</option>
								</select>
		 	 				</div>
			 			</div>
					</c:if>
		 			
					<div class="search-td">
		 				<div class="search-td-label" >状态：</div>
		 				<div class="search-td-combobox-condition" >
							<select id="adInfoStatus" name="adInfoStatus" style="width: 150px;" >
								<option value="" selected >请选择...</option>
								<c:forEach var="adInfoStatus" items="${adInfoStatusList}">
									<option value="${adInfoStatus.statusValue}">${adInfoStatus.statusDesc}</option>
								</c:forEach>
							</select>
	 	 				</div>
		 			</div>
					
					<div class="search-td" style="position: relative;" >
						<div class="search-td-label"  >时间：</div>
						<div class="search-td-combobox-condition" style="position: absolute; top: -2px;" >
							<input type="text" id=autoUpDate name="autoUpDate" value="${autoUpDate }" class="dateTime" />
						</div>
					</div>
					
					<div class="search-td" style="padding-left: 150px;width: 100px;">
						<div style="position: relative;">
							<div id="searchbtn" style="position: absolute; top: -17px;" >搜索</div>
						</div>
					</div>
					<c:if test="${flag == '3' }">
						<div class="search-td" style="padding-left: 0px;">
							<div style="height: 23px;width: 130px;line-height: 23px;cursor: pointer;text-align: center;border: solid 1px #A3C0E8;color: #333333;background-color: #0099FF;" onClick="showHomePage()" >按此状态此时间预览</div>
						</div>
					</c:if>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		
		<div style="display: none;"></div>
	</div>
	<div id="pic_Div" style="z-index: 10000;display: none;width: 100%;height: 100%;position: absolute; !important;">
		<table 
			style="background-color: transparent; z-index: 2001; position: absolute; width: 100%; height: 100% !important">
			<tr onclick="pic_hide()" >
				<td valign="middle" align="center">
					<div  title="关闭图片">
						<img id="pic_img" alt="自动关闭" />
					</div>
				</td>
			</tr>
		</table>
		<div style="position: fixed; filter: alpha(Opacity = 50); -moz-opacity: 0.5; opacity: 0.5; z-index: 2000; background-color: #000000; height: 100%; width: 100%; text-align: center; vertical-align: middle;"></div>
	</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
