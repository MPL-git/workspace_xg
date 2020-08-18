<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	 $("#export").bind('click',function(){
			var date_begin = $("#date_begin").val();
			var date_end = $("#date_end").val();
			var status = $("#status").val();
			location.href="${pageContext.request.contextPath}/mchtSettled/commissioner/export.shtml?date_begin="+date_begin+"&date_end="+date_end+"&status="+status;
		});
});
function editMchtSettled(id) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "修改入驻信息",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/editRemark.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
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

function submitMchtSettled(id,platformContactId) {
	$.ligerDialog.confirm('确定提交?', function (yes)
    {
		if (yes){
	   		$.ajax({ //ajax提交
				type:'POST',
				url: "${pageContext.request.contextPath}/mchtSettled/submit.shtml?id=" +id+"&platformContactId="+platformContactId,
				data: null,
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

//批量不合作
function uncooperative(ids){
    $.ligerDialog.confirm('确认该公司不合作吗？<br>确认不合作后，以后要重新合作，<br>只需重新提交。', function (yes)
    {
    	if(yes){
   		   $.ajax({ //ajax提交
   				type:'POST',
   				url:'${pageContext.request.contextPath}' +"/mchtSettled/uncooperative.shtml?Ids="+ids+"&status=2",
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


function unMchtSettled(ids){
    $.ligerDialog.confirm('确认加入该黑名单吗?', function (yes)
    {
    	if(yes){
   		   $.ajax({ //ajax提交
   				type:'POST',
   				url:'${pageContext.request.contextPath}' +"/mchtSettled/unMchtSettled.shtml?Ids="+ids+"&status=3",
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

function unCancelMchtSettled(ids){
    $.ligerDialog.confirm('确认取消该黑名单吗?', function (yes)
    {
    	if(yes){
   		   $.ajax({ //ajax提交
   				type:'POST',
   				url:'${pageContext.request.contextPath}' +"/mchtSettled/unCancelMchtSettled.shtml?Ids="+ids+"&status=0",
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

function submitSettled(id,platformContactId,isView) {
	var title = "";
	if(isView == 0){
		title = "提交入驻";
	}else{
		title = "查看";
	}
	$.ligerDialog.open({
		height: 550,
		width: 650,
		title: title,
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/confirm.shtml?settledApplyId=" + id+"&platformContactId="+platformContactId+"&isView="+isView,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toNotCooperate(id,status) {
	$.ligerDialog.open({
		height: $(window).height()*0.6,
		width: $(window).width()*0.5,
		title: "确认不合作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/toNotCooperate.shtml?id=" + id+"&status="+status,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

  var listConfig={
	  
      url:"/mchtSettled/commissioner/data.shtml",
    
/*       btnItems:[{ text: '添加入驻信息', icon: 'add', dtype:'win',  click: itemclick, url: "/mchtSettled/edit.shtml", seqId:"", width: 1200, height: 800}], */
      
      listGrid:{ columns: [
			            { display: '入驻申请时间', width: 120, render: function(rowdata, rowindex) {
			            	var createDate=new Date(rowdata.createDate);
			            	return createDate.format("yyyy-MM-dd");
			         	}},
			            { display: '保证金确认时间', width: 120, render: function(rowdata, rowindex) {
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
		                { display: '公司', width: 150, name: 'companyName'},
			            { display: '注册资本', width: 120, render: function(rowdata, rowindex) {
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
		                { display: '联系电话', name: 'phone'},
		                { display: '保证金方式', render: function(rowdata, rowindex) {
			            	if (rowdata.contractDeposit && rowdata.depositType){
			            		return "保证金："+rowdata.contractDeposit+"元<br>"+rowdata.depositTypeDesc;
			            	}else if(rowdata.depositType){
			            		return rowdata.depositTypeDesc;
			            	}else{
			            		return "";
			            	}
			         	}},
			         	{ display: '商品情况', name: 'productSituation'},
		                { display: '商家其他渠道链接', width: 150,name: 'otherChannelLink'},
		                { display: '物流配送', name: 'logistics'},
		                { display: '团队情况', name: 'teamSituation'},
		                { display: '公司概况', name: 'companySituation'},
		                { display: '我的备注', name: 'remarks'},
		                { display: '操作', name: "OPER", align: "center", render: function(rowdata, rowindex) {
							return "<a href=\"javascript:submitSettled(" + rowdata.id + ","+ rowdata.platformContactId + ",1);\">【查看】</a><a href=\"javascript:submitSettled(" + rowdata.id + ","+ rowdata.platformContactId + ",0);\">【提交入驻】</a><a href=\"javascript:toNotCooperate(" + rowdata.id + ",2);\">【不合作】</a>";
						}}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
/*         toolBarName:"toptoolbar", */
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
}

$(function(){

});
</script>

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
			<div class="search-td-label" style="float:left;">保证金确认时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="deposit_confirm_date_begin" name="deposit_confirm_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#deposit_confirm_date_begin").ligerDateEditor( {
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
				<input type="text" id="deposit_confirm_date_end" name="deposit_confirm_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#deposit_confirm_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
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
			<div class="search-td-label" style="float:left;">保证金方式：</div>
			<div class="search-td-condition" >
				<select id="depositType" name="depositType" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach var="list" items="${depositTypes}">
						<option value="${list.statusValue}">${list.statusDesc}
						</option>
					</c:forEach>
				</select>
		 	 </div>
			 </div>
			 

		</div>
			<div class="search-tr"  >
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

				<div class="search-td">
					<div class="search-td-label" style="float:left;">是否自营：</div>
					<div class="search-td-condition" >
						<select id="isManageSelf" name="isManageSelf" style="width: 150px;">
							<option value="">请选择</option>
							<option value="0">非自营</option>
							<option value="1">自营</option>
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