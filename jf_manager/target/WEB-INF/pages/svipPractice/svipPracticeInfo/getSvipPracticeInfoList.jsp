<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 150
		});
		
	 });
 
	 function formatMoney(s, n) {
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
	 
	 //添加
	 function addSvipPracticeInfo() {
		 $.ligerDialog.open({
			height: 500,
			width: 600,
			title: "添加",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/svipPracticeInfo/editSvipPracticeInfoManager.shtml",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //编辑
	 function editSvipPracticeInfo(id) {
		 $.ligerDialog.open({
			height: 500,
			width: 600,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/svipPracticeInfo/editSvipPracticeInfoManager.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //上下架
	 function statusSvipPracticeInfo(id, status) {
	 	var title = "是否上架";
		if(status == '0' ) {
			title = "是否下架";
		}
		$.ligerDialog.confirm(title, function(yes) {
			if(yes) {
				$.ajax({
					type : 'POST',
				 	url : "${pageContext.request.contextPath}/svipPracticeInfo/statusSvipPracticeInfo.shtml",
				 	data : {id : id, status : status},
				 	dataType : 'json',
				 	success : function(data){
				 		if(data == null || data.code != 200) {
					 		commUtil.alertError(data.msg);
				 		}else {
					 		$("#searchbtn").click();
				 		}
				 	},
				 	error : function(e) {
				 		commUtil.alertError("系统异常请稍后再试！");
				 	}
				});
			}
		});
		$(".l-dialog-content").css("margin-right", "5px");
		$(".l-dialog-btn-inner:even").html("取消");
		$(".l-dialog-btn-inner:odd").html("确认");
	 }
	 
 	 var listConfig={ 
	      url:"/svipPracticeInfo/getSvipPracticeInfoList.shtml",
	      btnItems:[
		      { text: '添加', icon:'add', id:'add', dtype:'win', click:addSvipPracticeInfo }
	      ],
	      listGrid:{ columns: [
							{display:'ID',name:'id', align:'center', isSort:false, width:100},
							{display:'主题名称',name:'title', align:'center', isSort:false, width:180},
							{display:'活动时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.startTime != null && rowdata.startTime != '' ) {
									var startTime = new Date(rowdata.startTime);
									html.push("起"+startTime.format("yyyy-MM-dd hh:mm:ss"));
								}
								if(rowdata.endTime != null && rowdata.endTime != '' ) {
									var endTime = new Date(rowdata.endTime);
									html.push("</br>止"+endTime.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
				  			{display:'体验时间类型',name:'', align:'center', isSort:false, width:120, render:function(rowdata, rowindex) {
								if(rowdata.practiceTimeType == '1' ) {
									return "绝对时间";
								}else if(rowdata.practiceTimeType == '2' ) {
									return "相对时间";
								}
							}},
				  			{display:'体验时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if(rowdata.practiceTimeType == '1' ) {
									var html = [];
									if(rowdata.practiceStartTime != null && rowdata.practiceStartTime != '' ) {
										var practiceStartTime = new Date(rowdata.practiceStartTime);
										html.push("起"+practiceStartTime.format("yyyy-MM-dd hh:mm:ss"));
									}
									if(rowdata.practiceEndTime != null && rowdata.practiceEndTime != '' ) {
										var practiceEndTime = new Date(rowdata.practiceEndTime);
										html.push("</br>止"+practiceEndTime.format("yyyy-MM-dd hh:mm:ss"));
									}
									return html.join("");
								}else if(rowdata.practiceTimeType == '2' && rowdata.practiceHours != null && rowdata.practiceHours != '' ) {
									return rowdata.practiceHours+"小时";
								}
							}},
			                {display:'创建时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
			                }},
					  		{display:'状态',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if(rowdata.status == '0' ) {
									return "下架";
								}else if(rowdata.status == '1' ) {
									return "上架";
								}
			                }},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:void(0);' onclick='editSvipPracticeInfo("+rowdata.id+");'>【编辑】</a>");
								var status = rowdata.status=='0'?'1':'0';
								var statusDesc = rowdata.status=='0'?'【上架】':'【下架】';
								html.push("<a href='javascript:void(0);' onclick='statusSvipPracticeInfo("+rowdata.id+","+status+");'>"+statusDesc+"</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >体验时间类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="practiceTimeType" name="practiceTimeType" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">绝对时间</option>
							<option value="2">相对时间</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">下架</option>
							<option value="1">上架</option>
						</select>
				 	 </div>
				</div>
			 	<div class="search-td-search-btn" >
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
