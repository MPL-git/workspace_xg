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
	
 function editInfo(id) {
		$.ligerDialog.open({
		height: $(window).height() - 400,
		width: $(window).width() - 800,
		title: "站内信详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/platformMsg/platformMsg-deitInfo.shtml?infoId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
	//批量删除
	function deletInfo(platformMsgId){
		   $.ajax({ //ajax提交
					type:'POST',
					url:'${pageContext.request.contextPath}' +"/platformMsg/platformMsg-del.shtml?platformMsgId="+platformMsgId,
					data:"",
					dataType:"json",
					cache: false,
					success: function(json){
						$.ligerDialog.alert(json);
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
	  
      url:"/platformMsg/platformMsg-list-data.shtml",
   
      btnItems:[{text: '批量删除', icon: 'delete', click: function(yes) {
      			  var data = listModel.gridManager.getSelectedRows();
                          if (data.length == 0)
                        	  $.ligerDialog.alert('请选择行');
                          else
                          {
                             var str = "";
                             var params={};
                             
                              $(data).each(function ()
                              {    
                            	  if(str==''){
                            		  str = this.id ;
                            	  }else{
                            		  str += ","+ this.id ;
                            	  }
                              });
                              $.ligerDialog.confirm('确定删除?', function (yes)
                              {
                            	  if(yes==true){
                            	  	deletInfo(str);
                            	  }
                              }); 
                          }
                          return;
      			  }}
      			],
      
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
		                { display: ' 商家', name: 'mchtName',render:function(rowdata,rowindex){
		                	return rowdata.mchtName+"<br>" + rowdata.mchtCode;
		                }},
		                { display: '站内信类型',  name: 'msgTypeDesc'},
		                { display: '标题', name: 'title',render:function(rowdata,rowindex){
		                	if(rowdata.title==null||rowdata.title==""||rowdata.title==undefined){
		                		return "-";
		                	}else{
		                		return rowdata.title;
		                	}
		                }},
		                { display: '时间', name: 'createTime', render: function(rowdata, rowindex) {
			              	   var createDate=new Date(rowdata.createDate);
			            	   return createDate.format("yyyy-MM-dd hh:mm:ss");
			            }},
		                { display: '状态',name: 'status',render:function(rowdata,rowindex){
		                	if(rowdata.status==0){
		                		return "未读";
		                	}else if(rowdata.status==1){
		                		return "<font color='red'>已读</font>";
		                	}else{return "-";}
		                }},
		                { display: "操作", name: "OPER",align: "center", render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:editInfo(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    html.push("<a href=\"javascript:deletInfo(" + rowdata.id + ");\">删除</a>&nbsp;&nbsp;"); 
						    return html.join("");
					 }
	             }
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
			<div class="search-td-label"  >商家：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "memberName" name="memberName" >
			</div>
			</div>
			
			 <div class="search-td">
			 <div class="search-td-label" >类别:</div>
			 <div class="search-td-condition" >
 				<select id="msgType" name="msgType" style="width:100px;">
 					<option value="">不限</option>
 					<c:forEach var="list" items="${statusList}">
					<option value="${list.statusValue}">${list.statusDesc}</option>
 					</c:forEach>
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
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
