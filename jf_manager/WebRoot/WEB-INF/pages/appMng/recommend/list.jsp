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
var modifyFlag=1;

function chgAdInfoStatus(id, status) {
	var title;
	if (status=='1'){
		title="上架";
	}else{
		title="下架";
	}
	$.ligerDialog.confirm("是否"+title+"？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/appMng/adMng/chgStatus.shtml?id=" + id + "&status=" + status,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						//location.reload();
						/* listModel.gridManager.reload(); */
					if(data.status == '0') {
					     var statusHtml=[];
					     statusHtml.push("<a id='"+id+"-a' href=\"javascript:chgAdInfoStatus(" + id + ",'1');\">上架</a>");
					     statusHtml.push("<a id='"+ id+"-a' href=\"javascript:delAdInfo(" + id + ");\">删除</a>");
					     statusHtml.push("<a id='"+ id +"-a' href=\"javascript:editAdInfo(" + id + ");\">修改</a>");
					     $("#"+id+"-a").parent("div").html(statusHtml.join("&nbsp;&nbsp;"));
					     
					 }else {
						 var strHtml = [];
						 strHtml.push("<a id='"+ id +"-a' href=\"javascript:chgAdInfoStatus(" + id + ",'0');\">下架</a>");
						 $("#"+id+"-a").parent("div").html(strHtml.join(""));
					
					 }
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

function delAdInfo(id) {
	$.ligerDialog.confirm("是否删除？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/appMng/adMng/del.shtml?id=" + id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						//location.reload();
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
		height: 400,
		width: 400,
		title: "修改推荐",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/appMng/recommend/edit.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

	function itemclickX() {
		var obj=document.getElementsByName("seqNo");
		if (modifyFlag==1){
			document.querySelector('div[toolbarid="modify"]').firstChild.innerText="完成编辑";
			for(var i=0;i<obj.length;i++){
				obj[i].disabled=false;
			};
			modifyFlag=2;
		}else if (modifyFlag==2){
			$.ligerDialog.confirm('是否保存', function (yes){
				if (yes){
					var str3=[];
					var seqData;
					for(var i=0;i<obj.length;i++){
						var str1=obj[i].id.replace(/seqNo/g,"");
						var str2=str1+","+obj[i].value;
						str3.push(str2);
					};
					seqData=str3.join("|");
					if (seqData!=null && seqData!=""){
						$.ajax({
							url : "${pageContext.request.contextPath}/appMng/seqEdit/Submit.shtml",
							type : 'POST',
							dataType : 'json',
							cache : false,
							async : false,
							data : {seqData:seqData,type:1},
							timeout : 30000,
							success : function(data) {
								if ("0000" == data.returnCode) {
									/* location.reload(); */
									/* frameElement.dialog.close(); */
								}else{
									$.ligerDialog.error(data.returnMsg);
								}
								
							},
							error : function() {
								$.ligerDialog.error('操作超时，请稍后再试！');
							}
						});
					}
				}else{
					location.reload();
				}
				document.querySelector('div[toolbarid="modify"]').firstChild.innerText="编辑排序";
				for(var i=0;i<obj.length;i++){
					obj[i].disabled=true;
				};
				modifyFlag=1;
			});
			
		}
	}
	
	var listConfig={
      url:"/appMng/recommend/data.shtml?catalog=${catalog }",
     
      btnItems: [{ text: '添加推荐', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/appMng/recommend/add.shtml?catalog=${catalog}", seqId:"", width: 700, height: 550 },
                 { line:true },
                 { text: '编辑排序', icon: 'modify', id:'modify', click:itemclickX},
                 { line:true },
                 { text: '预览', icon: 'pager', id:'pager', dtype:'win', click:itemclick, url:"/xxxxx/delete.shtml?catalog=", seqId:"${catalog}" }
                ], 
      listGrid:{  columns: [{ display: '排序', width: 80, render: function (rowdata, rowindex){
    	  			var seqNo;
    	  			if (rowdata.seqNo==null){
    	  				seqNo="";
    	  			}else{
    	  				seqNo=rowdata.seqNo;
    	  			}
    	  			return "<input id='seqNo"+rowdata.id+ "' name='seqNo' class='seqClass' disabled='' value='"+seqNo+ "'>";
      			}}, 
      			{ display: '特卖名称', width: 150, name: 'activityName' },
                { display: '图片', width: 120, render: function (rowdata, rowindex){
                    var h = "";
                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.activityEntryPic+"' width='100' height='50' onclick='pic_show(this.src)'>";
                    return h;
                }}, 
	            { display: '类型', width: 80, name: 'linkTypeDesc' },
	            { display: '品类', width: 80, name: 'productName' },
	            { display: 'ID', width: 80, name: 'linkId' },
	            { display: '上架时间', width: 150, render: function(rowdata, rowindex) {
	            	if (rowdata.autoUpDate!=null){
		            	var autoUpDate=new Date(rowdata.autoUpDate);
		            	return autoUpDate.format("yyyy-MM-dd hh:mm:ss");
	            	}
	         	}},
	            { display: '会场开始时间', width: 140, render: function(rowdata, rowindex) {
	            	if (rowdata.activityBeginTime!=null){
		            	var activityBeginTime=new Date(rowdata.activityBeginTime);
		            	var autoDownDate=new Date(rowdata.autoDownDate);
		            	if (activityBeginTime>=autoDownDate){
		            		return "<span style='color:#FF0000'>"+activityBeginTime.format("yyyy-MM-dd hh:mm:ss")+"</span>";
		            	}else{
		            		return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");
		            	}
	            	}
	         	}},
	            { display: '下架时间', width: 140, render: function(rowdata, rowindex) {
	            	if (rowdata.autoDownDate!=null){
	            		var activityBeginTime=new Date(rowdata.activityBeginTime);
		            	var autoDownDate=new Date(rowdata.autoDownDate);
		            	var activityEndTime=new Date(rowdata.activityEndTime);
		            	if (activityBeginTime>=autoDownDate||autoDownDate>activityEndTime){
		            		return "<span style='color:#FF0000'>"+autoDownDate.format("yyyy-MM-dd hh:mm:ss")+"</span>";
		            	}else{
		            		return autoDownDate.format("yyyy-MM-dd hh:mm:ss");
		            	}
	            	}
	         	}},
	            { display: '会场结束时间', width: 140, render: function(rowdata, rowindex) {
	            	if (rowdata.activityEndTime!=null){
	            		var autoDownDate=new Date(rowdata.autoDownDate);
		            	var activityEndTime=new Date(rowdata.activityEndTime);
		            	if (autoDownDate>activityEndTime){
		            		return "<span style='color:#FF0000'>"+activityEndTime.format("yyyy-MM-dd hh:mm:ss")+"</span>";
		            	}else{
		            		return activityEndTime.format("yyyy-MM-dd hh:mm:ss");
		            	}
	            	}
	         	}},
	            { display: '会场活动剩余时间', width: 140, render: function(rowdata, rowindex) {
	            	if (rowdata.activityEndTime!=null){
	            		var curTime = new Date();
		            	var activityEndTime=new Date(rowdata.activityEndTime);
		            	if (curTime>=activityEndTime){
		            		return "活动已结束";
		            	}else{
		            		//时间差的毫秒数
		            		var dateDiff=activityEndTime.getTime()-curTime.getTime();
		            		//计算出相差天数
		            		var days=Math.floor(dateDiff/(24*3600*1000));
		            		//计算出小时数
		            		var leave1=dateDiff%(24*3600*1000)    //计算天数后剩余的毫秒数
		            		var hours=Math.floor(leave1/(3600*1000))
		            		//计算相差分钟数
		            		var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
		            		var minutes=Math.floor(leave2/(60*1000))
		            		//计算相差秒数
		            		//var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
		            		//var seconds=Math.round(leave3/1000)
		            		if (days==0 && hours==0){
		            			return minutes+"分钟";
		            		}else if (days==0){
		            			return hours+"小时"+minutes+"分钟";
		            		}else{
		            			return days+"天"+hours+"小时"+minutes+"分钟";
		            		}
		            	}
	            	}
	         	}},
                { display: "操作", name: "OPER", align: "center", width: 140, render: function(rowdata, rowindex) {
					var html = [];
					if (rowdata.status=='0'){
					    html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'1');\">上架</a>"); 
					    html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:delAdInfo(" + rowdata.id + ");\">删除</a>");
					    html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:editAdInfo(" + rowdata.id + ");\">修改</a>");
					}else{
						html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'0');\">下架</a>");
					}
				    return html.join("&nbsp;&nbsp;");
				}}
                ],   
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber:true//不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }     
  }   
 </script>  
	<body style="padding: 0px; overflow: hidden;">
	<c:if test="${!showFlag }">
		<h2 style="color: red;margin: 10px 0px 0px 15px;">您暂无权限~~</h2>
	</c:if>
	<c:if test="${showFlag }">
		<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
			<div class="l-loading" style="display: block" id="pageloading"></div>
			<div id="toptoolbar"></div>
			<form id="dataForm" runat="server">
				<div class="search-pannel">
					<div class="search-tr">
						<div class="search-td" style="margin-top:5px;">
							<div class="search-td-label" style="float:left;margin-top:5px;">日期：</div>
							<div class="l-panel-search-item">
								<input type="text" id="autoUpDate" name="autoUpDate" value="${autoUpDate}" />
								<script type="text/javascript">
									$(function() {
										$("#autoUpDate").ligerDateEditor({
											showTime : false,
											labelWidth : 150,
											width : 120,
											labelAlign : 'left'
										});
									});
								</script>
							</div>
							<div class="l-panel-search-item" style="margin-top:5px;">&nbsp;&nbsp;至：</div>
						</div>
						<div class="search-td" style="margin-top:5px;">
							<div class="l-panel-search-item">
								<input type="text" id="autoDownDate" name="autoDownDate" value="${autoDownDate}" />
								<script type="text/javascript">
									$(function() {
										$("#autoDownDate").ligerDateEditor({
											showTime : false,
											labelWidth : 150,
											width : 120,
											labelAlign : 'left'
										});
									});
								</script>
							</div>
						</div>
						<div class="search-td">
							<div class="search-td-label">特卖名称：</div>
							<div class="search-td-condition">
								<input type="text" id="activityName" name="activityName">
							</div>
						</div>
						<div class="search-td">
							<div class="search-td-label">品类：</div>
							<div class="search-td-condition">
								<select id="productTypeId" name="productTypeId">
									<option value="">请选择</option>
									<c:forEach items="${productTypeList}" var="productType">
										<option value="${productType.id}">${productType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="search-td-search-btn">
							<div id="searchbtn">搜索</div>
						</div>
					</div>
				</div>
				<div id="maingrid" style="margin: 0; padding: 0"></div>
			</form>
			<div style="display: none;"></div>
		</div>
		<div id="pic_Div" style="z-index: 10000;display: none;width: 100%;height: 100%;position: absolute; !important;">
			<table style="background-color: transparent; z-index: 2001; position: absolute; width: 100%; height: 100% !important">
				<tr>
					<td valign="middle" align="center">
						<div onclick="pic_hide()" title="关闭图片">
							<img style="width: 100%;height: 100%;" id="pic_img" alt="自动关闭" />
						</div>
					</td>
				</tr>
			</table>
			<div
				style="position: fixed; filter: alpha(Opacity = 50); -moz-opacity: 0.5; opacity: 0.5; z-index: 2000; background-color: #000000; height: 100%; width: 100%; text-align: center; vertical-align: middle;">
			</div>
		</div>
	</c:if>
</body>
<script type="text/javascript">
			function pic_show(url){
				$("#pic_img").attr("src",url);
				$("#pic_Div").show(100);
			}
			
			function pic_hide(){
				$("#pic_Div").hide(100);
			}
		</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
