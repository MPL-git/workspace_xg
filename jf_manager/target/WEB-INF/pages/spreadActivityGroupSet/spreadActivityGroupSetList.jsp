<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript">
 $(function(){
	 
	 $(".dateEditor").ligerDateEditor( {
		showTime : false,
		labelAlign : 'left'
	 });
	 
 }); 

 //排序值
 function updateSeqNo(id) {
	$("#seqNo" + id).parent().find("span").remove();
	var seqNo = $("#seqNo" + id).val();
	var flag = seqNo.match(/^[1-9]\d*$/);
	if(seqNo != '' && flag != null) {
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/spreadActivityGroupSet/updateSeqNo.shtml",
			 data : {id : id, seqNo : seqNo},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.code != 200) {
					 commUtil.alertError(data.msg);
				 }else{
					 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
					 $("#seqNo" + id).attr("seqNo", seqNo);
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	}else{
		$("#seqNo" + id).val($("#seqNo" + id).attr("seqNo"));
		$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
	} 
 }
 
 // 编辑
 function editSpreadActivityGroupSet(id, title) {
	 $.ligerDialog.open({
		 	height: $(window).height()-50,
			width: $(window).width()-50,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/spreadActivityGroupSet/editSpreadActivityGroupSetManager.shtml?deviceType=1&id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	});
 }
 
 var listConfig={
	 btnItems:[{text:'添加', icon:'add', click: function() {
		 editSpreadActivityGroupSet('', '添加');
        }}
	 ],
     url:"/spreadActivityGroupSet/spreadActivityGroupSetList.shtml",
     listGrid:{ columns: [
			{display:'排序',name:'seqNo', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
				var html = [];
				var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
				html.push("<input type='text' style='width:80px; margin-top: 5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >");
               	return html.join("");
            }},
			{ display: '活动组集合', name:'name', align:'center', isSort:false, width:180},
			{ display: '所属推广渠道', name:'channelName', align:'center', isSort:false, width:180},
            { display: '备注', name:'remarks', align:'center', isSort:false, width:320},
            { display: '状态', name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
            	if(rowdata.status == '1' ) {
					return "启用";
				}else{
					return "停用";
				}
            }},
            { display: '创建时间', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
            	if(rowdata.createDate == null || rowdata.createDate == '' ) {
					return "";
				}else{
					var createDate = new Date(rowdata.createDate);
					return createDate.format("yyyy-MM-dd hh:mm:ss");
				}
            }},
            { display: '操作', name:'', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
				return "<a href='javascript:;' onclick='editSpreadActivityGroupSet("+rowdata.id+", \"编辑\");'>【编辑】</a>";
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label" style="float:left;">
						推广渠道：
					</div>
					<div class="search-td-combobox-condition">
						<select id="channel" name="channel" style="width: 135px;">
							<option value="">请选择...</option>
							<c:forEach var="list" items="${channelList}">
								<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">活动组集合：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="name" name="name">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">状态：</div>
					<div class="search-td-combobox-condition">
						<select id="status" name="status" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="0">停用</option>
							<option value="1">启用</option>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn">查询</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>