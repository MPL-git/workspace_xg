<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

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
<style>
.seqClass{width:50px;height:23px;text-align:center; border:1px solid red;box-sizing: border-box;border-width: 1px; border-style: solid; border-color: rgba(121, 121, 121, 1);border-radius: 0px; }
</style>
<script type="text/javascript">
	var modifyFlag = 1;
	var josnStr = [];
	var viewerPictures;
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
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});

        $.ajax({
            url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType=14",
            type : 'POST',
            dataType : 'json',
            cache : false,
            async : false,
            data : "",
            timeout : 30000,
            success : function(data) {
                var linkTypeLists = data.linkTypeList
                for(var i=0;i<linkTypeLists.length;i++){
                    var id=linkTypeLists[i].linkType
                    var name=linkTypeLists[i].linkTypeName
                    $("#linkType").append('<option value="'+id+'">'+name+'</option>')
                }
            },
            error : function() {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
	});
	
	function viewerPic(imgPath) {
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
	}
	
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
					url : "${pageContext.request.contextPath}/appMng/adMng/chgStatus.shtml?id=" + id + "&status=" + status,
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

	function delAdInfo(id) {
		$.ligerDialog.confirm("是否删除？", function (yes) { 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/appMng/adMng/del.shtml?id=" + id,
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

	function editAdInfo(id) {
		$.ligerDialog.open({
			height: $(window).height()-200,
			width: $(window).width()-600,
			title: "修改广告",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/adMng/edit.shtml?mngPos=${mngPos}&showType=${showType}&id=" + id,
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
									location.reload();
									// frameElement.dialog.close();
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
	
	var listConfig = {
      url:"/appMng/adMng/data.shtml?mngPos=${mngPos}",
      btnItems: [{ text: '添加广告', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/appMng/adMng/add.shtml?mngPos=${mngPos}&showType=${showType}", seqId:"", width: 700, height: 550 },
                 { line:true }, 
                 { text: '编辑排序', icon: 'modify', id:'modify', click:itemclickX}
                ], 
      listGrid:{ columns: [{ display: '排序', width: 80, render: function (rowdata, rowindex){
    	  			var seqNo;
    	  			if (rowdata.seqNo==null){
    	  				seqNo="";
    	  			}else{
    	  				seqNo=rowdata.seqNo;
    	  			}
    	  			return "<input id='seqNo"+rowdata.id+ "' name='seqNo' class='seqClass' disabled='' value='"+seqNo+ "'>";
      			}}, 
                { display: '图片', width: 120, hide:${mngPos==10},render: function (rowdata, rowindex){
                	var h = "";
	                    h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
	                return h;
                }}, 
                 { display: 'ios广告图片', width: 120, hide:${mngPos==1 || mngPos==2 || mngPos==9 || mngPos==11 || mngPos==1002 || mngPos==1004|| mngPos==1007|| mngPos==1012|| mngPos==1022|| mngPos==1032|| mngPos==1042|| mngPos==1052|| mngPos==1062|| mngPos==1082 || mngPos==1095 || mngPos==1102  },render: function (rowdata, rowindex){
                	var h = "";
	                    h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.iosPic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
	                return h;
                }}, 
                { display: '安卓广告图片', width: 120, hide:${mngPos==1 || mngPos==2 || mngPos==9 ||mngPos==1002 || mngPos==11 || mngPos==1004|| mngPos==1007|| mngPos==1012|| mngPos==1022|| mngPos==1032|| mngPos==1042|| mngPos==1052|| mngPos==1062|| mngPos==1082 || mngPos==1095 || mngPos==1102 },render: function (rowdata, rowindex){
                	var h = "";
	                    h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.androidPic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
	                return h;
                }},
                { display: '所属类目', width: 100, name: 'sourceProductTypeName', hide:${mngPos==1 || mngPos==9 || mngPos==10 || mngPos==11 || mngPos==1012 || mngPos==1022  || mngPos==1032  || mngPos==1042  || mngPos==1052  || mngPos==1062 || mngPos==1082  || mngPos==1095 }},
                { display: '所属分类', width: 80, name: 'catalogDesc', hide:${mngPos==1 || mngPos==9 || mngPos==11 || mngPos==1002 || mngPos==1004 || mngPos==1007 || mngPos==10  || mngPos==1012 || mngPos==1022  || mngPos==1032  || mngPos==1042  || mngPos==1052  || mngPos==1062 || mngPos==1082  || mngPos==1095  || mngPos==1102 }},
                { display: '所属广告位', width: 80, name: 'positionDesc', hide:${mngPos==1 || mngPos==9 || mngPos==11 || mngPos==1002 || mngPos==1004 || mngPos==1007 || mngPos==10  || mngPos==1012 || mngPos==1022  || mngPos==1032  || mngPos==1042  || mngPos==1052  || mngPos==1062  || mngPos==1082 || mngPos==1095  || mngPos==1102 }},
                { display: '广告类型', width: 80, name: 'linkTypeDesc' },
	            { display: '广告链接', width: 150, render: function(rowdata, rowindex) {
	            	if( rowdata.linkType=='14'|| rowdata.linkType=='16'|| rowdata.linkType=='18' 
	            			|| rowdata.linkType=='20' || rowdata.linkType=='22' 
	            			|| rowdata.linkType=='24' || rowdata.linkType=='26' 
	            			|| rowdata.linkType=='30' || rowdata.linkType=='32'){
	            		return rowdata.productTypeNames;
	            	}else if(rowdata.linkType=='28'){
	            		return rowdata.mallName;
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
					}else if(rowdata.linkType=='3' || rowdata.linkType=='4' || rowdata.linkType=='8' || rowdata.linkType=='10'
						|| rowdata.linkType=='15' || rowdata.linkType=='17' || rowdata.linkType=='19' || rowdata.linkType=='21'
							|| rowdata.linkType=='23' || rowdata.linkType=='25' || rowdata.linkType=='27'){
						return rowdata.linkUrl;
					}else{
	            	  return rowdata.linkId;
					}
	         	}},
	            { display: '客户端', width: 140, name: 'showChannelDesc', hide:${mngPos!=1}},
	            { display: '上架时间', width: 140, render: function(rowdata, rowindex) {
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
		            		var leave1=dateDiff%(24*3600*1000);    //计算天数后剩余的毫秒数
		            		var hours=Math.floor(leave1/(3600*1000));
		            		//计算相差分钟数
		            		var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
		            		var minutes=Math.floor(leave2/(60*1000));
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
			    { display: '状态', width: 100, name: 'statusDesc', hide:${mngPos!=1}},
                { display: "操作", name: "OPER", align: "center", width: 140, render: function(rowdata, rowindex) {
					var html = [];
					if (rowdata.status=='0'){
					    html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'1');\">上架</a>"); 
					    html.push("<a href=\"javascript:delAdInfo(" + rowdata.id + ");\">删除</a>");
					    html.push("<a href=\"javascript:editAdInfo(" + rowdata.id + ");\">修改</a>");
					}else{
						html.push("<a href=\"javascript:chgAdInfoStatus(" + rowdata.id + ",'0');\">下架</a>");
					}
				    return html.join("&nbsp;&nbsp;");
				}}
                ],   
                 showCheckbox : false,  //不设置默认为 true
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
 </head>  
	<body style="padding: 0px; overflow: hidden;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<div id="toptoolbar"></div>
		<form id="dataForm" runat="server" >
			<input type="hidden" id="mngPos" name="mngPos" value="${mngPos}"/> 
				<input type="hidden" id="showType" name="showType" value="${showType}"/> 
			<div class="search-pannel">
				<div class="search-tr"  >
					<c:if test="${mngPos!=1}">
						<div class="search-td" style="width: 40%;">
							<div class="search-td-label" style="float: left;width: 20%;">日期：</div>
							<div class="l-panel-search-item" >
								<input type="text" id="autoUpDate" name="autoUpDate" value="${autoUpDate }" class="dateEditor" style="width: 135px;" />
							</div>
							<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
							<div class="l-panel-search-item">
								<input type="text" id="autoDownDate" name="autoDownDate" value="${autoDownDate }" class="dateEditor" style="width: 135px;" />
							</div>
						</div>
					</c:if>

					<c:if test="${mngPos==1}">
						<div class="search-td" style="position: relative;" >
							<div class="search-td-label"  >日期：</div>
							<div class="search-td-combobox-condition" style="position: absolute; top: -2px;" >
								<input type="text" id=onLineDate name="onLineDate" value="${onLineDate }" class="dateEditor" />
							</div>
						</div>
						<div class="search-td">
							<div class="search-td-label" >客户端：</div>
							<div class="search-td-condition" >
								<select id="showChannel" name="showChannel">
									<option value="">请选择</option>
									<option value="1">APP</option>
									<option value="2">小程序</option>
									<option value="3">H5&其他</option>
								</select>
							</div>
						</div>
						<div class="search-td">
							<div class="search-td-label" >上架状态：</div>
							<div class="search-td-condition" >
								<select id="status" name="status">
									<option value="">请选择</option>
									<option value="1">上架</option>
									<option value="0">下架</option>
								</select>
							</div>
						</div>
					</c:if>
					
			 		<c:if test="${mngPos==2}">
						<div class="search-td">
					 		<div class="search-td-label" >类目：</div>
					 		<div class="search-td-condition" >
						 		<select id="catalog" name="catalog">
									<option value="">请选择</option>
									<c:forEach var="catalogItem" items="${catalogs}">
										<c:if test="${catalogItem.statusValue==2 || catalogItem.statusValue==3 || catalogItem.statusValue==4|| catalogItem.statusValue==5 || catalogItem.statusValue==11
										|| catalogItem.statusValue==12 || catalogItem.statusValue==13|| catalogItem.statusValue==14 || catalogItem.statusValue==15}">
										<option value="${catalogItem.statusValue}">${catalogItem.statusDesc}</option>
										</c:if>
									</c:forEach>
								</select>
		 	 				</div>
			 			</div>
				 		<div class="search-td">
						 	 <div class="search-td-label" >所属广告位：</div>
							 <div class="search-td-condition" >
								<select id="position" name="position">
									<option value="">请选择</option>
									<c:forEach var="positionItem" items="${positions}">
										<c:if test="${positionItem.statusValue==1 || positionItem.statusValue==2 || positionItem.statusValue==3}">
										<option value="${positionItem.statusValue}">${positionItem.statusDesc}</option>
										</c:if>
									</c:forEach>
								</select>
						 	 </div>
				 		</div>
				 	</c:if>
				 	
				 	   
				 <c:if test="${ mngPos==1007 || mngPos==1004}">
				 	<div class="search-td">
				 		<div class="search-td-label" >上架状态：</div>
				 		<div class="search-td-condition" >
					 		<select id="status" name="status">
								<option value="">请选择</option>
								<option value="0">下架</option>
								<option value="1">上架</option>
							</select>
	 	 				</div>
			 		</div>
				 </c:if>
				 
				 	<c:if test="${mngPos==1002 || mngPos==1004 || mngPos==1007 || mngPos==1032  || mngPos==1102 }">
						<div class="search-td">
					 		<div class="search-td-label" >所属类目：</div>
					 		<div class="search-td-condition" >
						 		<select id="sourceProductTypeId" name="sourceProductTypeId">
									<option value="">请选择</option>
									<c:forEach var="sourceProductTypes" items="${sourceProductTypes}">
										<option value="${sourceProductTypes.id}">${sourceProductTypes.sourceProductTypeName}</option>
									</c:forEach>
								</select>
		 	 				</div>
			 			</div>
				 	</c:if>

					<c:if test="${mngPos==1095}">
						<div class="search-td">
					 		<div class="search-td-label" >广告类型：</div>
					 		<div class="search-td-condition" >
						 		<select id="linkType" name="linkType">
									<option value="">请选择</option>
								</select>
		 	 				</div>
			 			</div>
				 	</c:if>
					<div class="search-td-search-btn">
						<div id="searchbtn" >搜索</div>
					</div>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

		</ul>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
