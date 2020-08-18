<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/clipboard.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">
 function formatMoney(s, n)
 {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1];
    t = "";
    for(i = 0; i < l.length; i ++ )
    {
       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
    }
    return t.split("").reverse().join("") + "." + r;
 }

var viewerPictures;
$(function() {
	  $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
	  });
	  
	  viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
  		$("#viewer-pictures").hide();
  	  }});
	  
	  //复制
	  var clipboard = new Clipboard('.copyLink');
	  clipboard.on('success', function (e) {
		  $.ligerDialog.success('复制成功');
	  });
});
	
	 function viewerPic(_this){
		 var img = $(_this).attr("src");
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+img+'" src="'+img+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();			
	 }
	 
	//拉新记录
	function laNewRecordShopowner(id,nick) {
		$.ligerDialog.open({
			height: $(window).height()-40,
			width:  $(window).width()-40,
			title: "拉新记录（"+id+"/"+nick+"）",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/salesmanManage/laNewRecordShopowner.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
	//编辑
	function edit(id) {
		$.ligerDialog.open({
			height: 800,
			width: 1500,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/salesmanManage/edit.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
	//禁用
	function prohibit(id,status) {
		$.ligerDialog.confirm('是否禁用？', function(yes) {
			if(yes) {
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/salesmanManage/prohibit.shtml",
					 data : {id : id,status:status},
					 dataType : 'json',
					 success : function(data){
						 if(data.statusCode != 200)
							 commUtil.alertError(data.message);
						 else{
							 $("#searchbtn").click();
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			}
		});
	}

 	var listConfig={
	      url:"/novaStrategy/salesmanManageList.shtml",
	      btnItems:[{ text: '添加', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/salesmanManage/edit.shtml", seqId:"", width: 1500, height: 800}],
	      listGrid:{ columns: [                    
	                        {display:'业务员会员ID', name:'memberId', align:'center', isSort:false},
	                        {display:'业务员会员昵称', name:'nick', align:'center', isSort:false, render: function(rowdata, rowindex) {
								if(rowdata.nick==null){
									return "醒购会员";
								}else{
									return rowdata.nick;
								}
							}},
	                        {display:'名称备注', name:'name', align:'center', isSort:false},
	                        {display:'其他备注', name:'otherRemarks', align:'center', isSort:false},
	                        {display:'店长数', name:'shopownerCount', align:'center', isSort:false},
	                        {display:'专属二维码', name: 'inviteCodePic', width: 80,render: function(rowdata, rowindex) {
			                    var h = "";
			                    h += "<img src='${pageContext.request.contextPath}/salesmanManage/getQrCode.shtml?id="+rowdata.id+"' width='55' height='55' style='margin-top:4px;margin-bottom:4px;' onclick='viewerPic(this)'>";
			                    return h;
							}},
							{display:'邀请链接', name:'inviteUrl', align:'center', isSort:false, render: function(rowdata, rowindex) {
								return "<a href=\"javascript:void(0);\" class=\"copyLink\" data-clipboard-action=\"copy\" data-clipboard-text=\""+rowdata.inviteUrl+"\">复制</a>"
							}},
							{display:'状态', name:'status', align:'center', isSort:false,render: function(rowdata, rowindex) {
								if(rowdata.status == '0'){
									return '禁用';
								}else{
									return '启用';
								}
							}},
							{display:'创建人', name:'createName', align:'center', isSort:false},
	                        {display:'创建时间', name:'createDate', align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'操作', name:'', align:'center', isSort:false, render: function(rowdata, rowindex) {
								var html = [];
								html.push('<a href="javascript:laNewRecordShopowner('+rowdata.memberId+',\''+rowdata.nick+'\')">拉新记录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
								html.push("<a href=\"javascript:edit(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
								if(rowdata.status == '0'){
									html.push("<a href=\"javascript:prohibit(" + rowdata.id + ","+rowdata.status+");\">启用</a>");
								}else{
									html.push("<a href=\"javascript:prohibit(" + rowdata.id + ","+rowdata.status+");\">禁用</a>");
								}

								return html.join("");
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
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
	<input type="hidden" id="pageSize" value="${pageSize}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"> 
				<div class="search-td"  style="width:230px;">
					<div class="search-td-label">业务员会员ID</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">业务员会员昵称</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="nick" name="nick">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">状态</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1">启用</option>
							<option value="0">禁用</option>
						</select>
					</div>
				</div>
					<div class="search-td" style="width: 30%;margin-bottom:-6px;">
						<div class="search-td-label" style="float: left;width: 20%;margin-top:2px;">创建日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn" style="display: inline-flex;margin-right:200px;">
					<div id="searchbtn" class="l-button">搜索</div>
				</div>
			</div>
		</div>
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
		</ul>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
