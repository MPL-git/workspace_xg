<%@ page language="java" pageEncoding="UTF-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function batchEffect(ids){
	$.ajax({
		url : "${pageContext.request.contextPath}/appMng/version/batchEffect.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {"ids":ids},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("批量生效成功");
				setTimeout(function(){
					location.reload();
					frameElement.dialog.close();
				},1000);
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

var listConfig={
     url:"/appMng/version/datalist.shtml",
    
     btnItems:[{ text: '增加版本', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/appMng/version/edit.shtml?chnlType=${chnlType}", seqId:"",width:700,height:550 },
               { text: '修改版本', icon: 'modify', id:'modify', dtype:'win', click: itemclick, url:"/appMng/version/edit.shtml?chnlType=${chnlType}&id=",seqId:"id" },
               { text: '删除版本', icon: 'delete', id:'delete', dtype:'ajax', click:itemclick, url:"/appMng/version/del.shtml?id=", seqId:"id" },
               <c:if test="${chnlType==2}">
               { text: '批量添加', icon: 'add', id:'batchAdd', dtype:'win', click: itemclick, url:"/appMng/version/batchAdd.shtml?chnlType=${chnlType}", seqId:"",width:900,height:550 },
               {text: '批量生效', icon: 'modify', click: function() {
         		  var data = listModel.gridManager.getSelectedRows();
                        if (data.length == 0)
                      	  $.ligerDialog.alert('请选择行');
                        else
                        {
                           var str = "";
                            $(data).each(function ()
                            {    
                          	  if(str==''){
                          		  str = this.id ;
                          	  }else{
                          		  str += ","+ this.id ;
                          	  }
                            });                                                      
                            	batchEffect(str);
                            }; 
                        
                        return;
         		  }}
               </c:if>
              ],
     listGrid:{  columns: [   
				 { display: 'id', name: 'id'},
				 { display: '版本', name: 'appVersion' },
	            { display: '版本号', name: 'appVersionNo'},
	            { display: 'APP类型',name: 'appTypeDesc' },
	            { display: '推广渠道', name: 'sprChnlDesc', hide:${chnlType=="3"} },
	            { display: '文件', hide:${chnlType=="3"}, render: function (rowdata, rowindex){
	            	if (rowdata.fileName!=""){
		                var h = "";
		                h += "<a href='"+rowdata.packageUrl+"' >"+rowdata.fileName+"</a>";
		                return h;
	            	}
	            }},
	            { display: '发布时间', render: function(rowdata, rowindex) {
	            	if (rowdata.createDate!=null){
		            	var createDate=new Date(rowdata.createDate);
		            	return createDate.format("yyyy-MM-dd hh:mm:ss");	
	            	}
	         	}},
		        { display: '发布内容', name: 'launchContent' },
		        { display: '提醒版本', name: 'targetVersion', hide:${chnlType!="3"}},
	            { display: '发布人', name: 'staffName' },
		        { display: '是否生效', name: 'isEffectDesc' },
		        { display: '是否必须', name: 'isMustDesc', hide:${chnlType=="3"} },
	            { display: '更新时间', render: function(rowdata, rowindex) {
	            	if (rowdata.updateDate!=null){
		            	var updateDate=new Date(rowdata.updateDate);
		            	return updateDate.format("yyyy-MM-dd hh:mm:ss");	
	            	}
	         	}}
               ],   
                showCheckbox : true,  //不设置默认为 true
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
	<body style="padding: 0px; overflow: hidden;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<div id="toptoolbar"></div>
		<form id="dataForm" runat="server" >
		<input id="chnlType" name="chnlType" type="hidden" value="${chnlType }">
			<div class="search-pannel">
			
				<div class="search-tr">
				
				<div class="search-td">
				<div class="search-td-label">是否生效：</div> 
				<div class="search-td-condition"> 
					<select style="width: 150px;" id="isEffect" name="isEffect">
						<option value="">请选择</option>
						<c:forEach var="list" items="${isEffectList}">
							<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:forEach>
					</select>
				</div>
				</div>
				
				<c:if test="${chnlType!=3}">
				<div class="search-td">
				<div class="l-panel-search-item">是否必须：</div> 
				<div class="search-td-condition"> 
					<select style="width: 150px;" id="isMust" name="isMust">
						<option value="">请选择</option>
						<c:forEach var="list" items="${isMustList}">
							<option value="${list.statusValue}">${list.statusDesc}</option>
						</c:forEach>
					</select>
				</div>
				</div>
				</c:if>
				
				<c:if test="${chnlType==2}">
				<div class="search-td">
				<div class="search-td-label" >推广渠道：</div> 
				<div class="search-td-condition"> 
					<select style="width: 150px;" id="sprChnl" name="sprChnl">
						<option value="">请选择</option>
						<c:forEach var="list" items="${sprChnls}">
							<c:if test="${list.statusValue!=1001 and list.statusValue!=1002 and list.statusValue!=9999}">	
							<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				</div>
				</c:if>
				
				<c:if test="${chnlType!=3}">
				</div>
  				<div class="search-tr">
				</c:if>
						
			 	<div class="l-panel-search-item">发布日期：</div>
                 <div class="l-panel-search-item">
					<input type="text" id="dateBegin" name="dateBegin"   />
					<script type="text/javascript">
						$(function() {
							$("#dateBegin").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script> 
				</div>
				<div class="l-panel-search-item"> 至 </div>
				
				<div class="l-panel-search-item">
					<input type="text" id="dateEnd" name="dateEnd"/>
					<script type="text/javascript">
					$(function() {
						$("#dateEnd").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
					</script>	
				</div>
				
				<div class="search-td">
		        <div class="search-td-label">版本：</div> 
				<div class="search-td-condition">  
					<input type="text" id="appVersion" name="appVersion"  />
				</div>
				</div>
				
				<div class="search-td">
				<div class="search-td-label">版本号：</div> 
				<div class="search-td-condition"> 
					<input type="text" id="appVersionNo" name="appVersionNo"   style="width: 150px"/> 
				</div> 
				</div>
					
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>	
			</div>
			
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
		 
	</body> 
	
