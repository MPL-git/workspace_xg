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
 <style>
.seqClass{width:50px;height:23px;text-align:center; border:1px solid red;box-sizing: border-box;border-width: 1px; border-style: solid; border-color: rgba(121, 121, 121, 1);border-radius: 0px; }
</style>
 <script type="text/javascript">
 var modifyFlag=1;
 function seeActivityPreviewSortDel(areaId){
	 $.ligerDialog.confirm('确定删除?', function (yes)
         {
       	  if(yes==true){
	       		$.ajax({ //ajax提交
	    			type:'POST',
	    			url:'${pageContext.request.contextPath}' +"/activityArea/previewActivityDel.shtml?id="+areaId,
	    			data:"",
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
     });
 }
 
//选择会场或特卖
 function choiceActivityList(){
 	$.ligerDialog.open({
 		height: $(window).height() - 100,
 		width: $(window).width() - 450,
 		title: "添加活动",
 		name: "INSERT_WINDOW",
 		url: "${pageContext.request.contextPath}/activityArea/previewActivityList.shtml",
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
						url : "${pageContext.request.contextPath}/activityArea/seqEdit/Submit.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {seqData:seqData,type:1},
						timeout : 30000,
						success : function(data) {
							if ("0000" == data.returnCode) {
								location.reload();
								frameElement.dialog.close();
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
	  
      url:"/activityArea/previewSortListData.shtml",
   
      btnItems: [{ text: '添加活动', icon: 'add', id:'add', click: function(yes) {choiceActivityList();}},
                 { line:true },
                 { text: '编辑排序', icon: 'modify', id:'modify', click:itemclickX}
                ], 
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                        {display:'类型',width: 80, name:'sourceDesc'},
                        {display:'特卖ID/会场ID',width: 100,render:function(rowdata,rowindex){
	                       	if (rowdata.source==2){
	                       		return rowdata.activityId;
	                       	}else{
	                       		return rowdata.id;
	                       	}
                        }},
                        {display:'商家序号', width: 100,render:function(rowdata,rowindex){
		                	if(rowdata.source==2){
		                		return rowdata.mchtCode;							
							}
						}},
						{display:'商家名称',width: 200, render:function(rowdata,rowindex){
							if(rowdata.source == 2){
								var html = [];
								if(rowdata.mchtGradeDesc) {
				                    html.push("<span style='color:red;margin-right:5px;'>"+rowdata.mchtGradeDesc+"</span>"); 
 	                        	}
			                    html.push(rowdata.shortName); 
			                    return html.join("");				
							}
						}},
						{display:'品牌',width: 100,render:function(rowdata,rowindex){
		                	if(rowdata.source==2){
		                		return rowdata.brandName;							
							}
						}},
                        {display:'特卖/会场名称',width: 250,name:'name'},
                        {display:'利益点',width: 200,name:'benefitPoint'},
						{display:'预热开始时间',width: 150,render:function(rowdata,rowindex){
							if(rowdata.preheatTime!=null){
								var preheatTime=new Date(rowdata.preheatTime);
								return preheatTime.format("yyyy-MM-dd hh:mm:ss");								
							}
						}},
		                {display: '活动开始时间',width: 150, name: 'activityBeginTime', render: function(rowdata, rowindex) {
			              		if(rowdata.activityBeginTime!=null){
				                	var activityBeginTime=new Date(rowdata.activityBeginTime);
				            	   return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");
			              		}   
			            }},
			            {display: '状态',width: 140, name: 'activityDays', render: function(rowdata, rowindex) {
			            	if(rowdata.activityDays<=0){
			            		return "预热中";
			            	}else{
			            		return rowdata.activityDays+"天后开始预热";
			            	}
			            }},
		                { display: "操作", name: "OPER",align: "center",width: 100, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:seeActivityPreviewSortDel(" + rowdata.id + ");\">剔除</a>&nbsp;&nbsp;"); 
						    return html.join("");
					 	}},
					 	{ display: '排序',width: 100, render: function (rowdata, rowindex){
		    	  			var seqNo;
		    	  			if (rowdata.preheatSeqNo==null){
		    	  				seqNo="";
		    	  			}else{
		    	  				seqNo=rowdata.preheatSeqNo;
		    	  			}
		    	  			return "<input id='seqNo"+rowdata.id+ "' name='seqNo' class='seqClass' disabled='' value='"+seqNo+ "'>";
		      			}},
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
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td" >
					<div class="search-td-label">
						<select id="source" name="source">
							<option value="2">特卖ID</option>
							<option value="1">会场ID</option>
						</select>
					</div>
					<div class="search-td-label" >
						<input type="text" id = "activityOrAreaId" name="activityOrAreaId" >
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
				
				<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			</div>
			<div class="search-tr">	
				<div class="search-td">
				<div class="search-td-label"  >预览：</div>
					<div class="search-td-combobox-condition" >
						<select id="dateTime" name="dateTime">
							<option value="1">今天</option>
							<option value="2">明天</option>
							<option value="3">后天</option>
							<option value="4">大后天</option>
						</select>
					</div>
				</div>
			<div class="search-td">
			<div class="search-td-label" style="color: red">对接人：</div>
			<div class="search-td-condition" >
				<select id="platformContactId" name="platformContactId" >
					<c:if test="${isContact==1}">
						<c:if test="${sessionScope.USER_SESSION.isManagement=='1'}">
							<option value="">全部商家</option>
						</c:if>
						<option value="${myContactId}">我对接的商家</option>
						<c:forEach items="${platformAssistantContacts}" var="platformAssistantContactList">
							<option value="${platformAssistantContactList.id}">${platformAssistantContactList.contactName}的商家</option>
						</c:forEach>
					</c:if>
					
					<c:if test="${isContact==0}">
					<option value="">全部商家</option>
					<c:forEach items="${platformMchtContacts}" var="platformMchtContactList">
						<option value="${platformMchtContactList.id}">${platformMchtContactList.contactName}的商家</option>
					</c:forEach>			
					</c:if>
				</select>
			</div>
			</div>
			 	
			 	<div class="search-td"">
					<div class="search-td-combobox-condition" >
						<input type="button" style="width: 100px;height: 30px;" value="预览活动预告"/>
					</div>
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
