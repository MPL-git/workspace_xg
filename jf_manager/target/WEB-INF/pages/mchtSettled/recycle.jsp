<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
//批量删除
function recoverInfo(ids){
    $.ligerDialog.confirm('确认还原这些信息吗？<br>还原后，可以重新分配。', function (yes)
    {
       if(yes){
	   $.ajax({ //ajax提交
				type:'POST',
				url:'${pageContext.request.contextPath}' +"/mchtSettled/recover.shtml?Ids="+ids+"&status=0",
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
 
function viewMchtSettled(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "查看入驻信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/view.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
	
 var listConfig={
	  
      url:"/mchtSettled/recycle/data.shtml",
   
      btnItems:[{text: '批量还原', icon: 'back', click: function() {
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
                          recoverInfo(str); 
                      }
                      return;
        		}}
      			],
      
     listGrid:{ columns: [
						{ display: '招商专员', render: function(rowdata, rowindex) {
							var html = [];
			            	if (rowdata.platformContactName==null || rowdata.platformContactName==''){
			            		html.push("-"); 
			            	}else{
			            		html.push(rowdata.platformContactName);
			            	}
			            	return html.join("");
						}},
			            { display: '入驻申请时间', width: 120, render: function(rowdata, rowindex) {
			            	var createDate=new Date(rowdata.createDate);
			            	return createDate.format("yyyy-MM-dd");
			         	}},
			            { display: '来源', render: function(rowdata, rowindex) {
			            	if(rowdata.clientType == "1"){
			            		return "PC端";
			            	}else if(rowdata.clientType == "2"){
			            		return "招商H5";
			            	}else{
			            		return "PC端";
			            	}
			         	}},
			         	{ display: '入驻类型', width: 80,render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.settledType == "1"){
			            		html.push("企业公司");
			            	}else if(rowdata.settledType == "2"){
			            		html.push("个体商户");
			            	}
			            	return html.join("");
			         	}},
		                { display: '公司名称', name: 'companyName'},
			            { display: '注册资本', render: function(rowdata, rowindex) {
			            	if (rowdata.regCapital==null){
			            		return "/";
			            	}else{
			            		return rowdata.regCapital+"万"+rowdata.regFeeTypeDesc;
			            	}
			         	}},
		                { display: '主营类目', name: 'productTypeMain'},
		                { display: '品牌', name: 'productBrandMain'},
		                { display: '联系人(职务)',render:function(rowdata, rowindex){
		                   var html=[];
		                   if (rowdata.contactName!=null) {
							   html.push(rowdata.contactName);
							  if (rowdata.contactJob!=null) {
							   html.push("(");
							   html.push(rowdata.contactJob);
							   html.push(")");					   								
							}
						}
		                   return html.join("");
		                }},
		                { display: '联系电话',  name: 'phone'},
/* 		                { display: 'QQ',  name: 'qq'}, */
		                { display: '提交状态', name: 'statusDesc', render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.mchtCode!=null){
								html.push(rowdata.mchtCode);
							}else{
								html.push(rowdata.statusDesc);
							}
						    return html.join("");
						}},
		                { display: '操作', name: "OPER", align: "center", render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:viewMchtSettled(" + rowdata.id + ");\">【查看】</a>&nbsp;&nbsp;");
							html.push("<a href=\"javascript:recoverInfo(" + rowdata.id + ");\">【还原】</a>");
						    return html.join("");
						}}
		                ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
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
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">申请时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="date_begin" name="date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" />
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			 
			 <div class="search-td">
			<div class="search-td-label" style="float:left;">来源：</div>
			<div class="search-td-condition" >
				<select id="sourceType" name="sourceType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="list" items="${sourceTypes}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 
			 <div class="search-td">
			<div class="search-td-label" style="float:left;">分配人员：</div>
			<div class="search-td-condition" >
				<select id="platformContactId" name="platformContactId" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="list" items="${platformContacts}">
						<option value="${list.id}">${list.contactName}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 	
		</div>
		
		
		<div class="search-tr"  >
			<div class="search-td">
			<div class="search-td-label" >公司名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="companyName" name="companyName" >
			</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label" style="float:left;">入驻类型：</div>
				<div class="search-td-condition" >
					<select id="settledType" name="settledType" style="width: 150px;">
						<option value="">请选择</option>
						<option value="1">企业公司</option>
						<option value="2">个体商户</option>
					</select>
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