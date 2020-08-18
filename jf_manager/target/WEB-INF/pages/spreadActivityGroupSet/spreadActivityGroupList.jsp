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

 // 调用父窗口方法
 function parentSpreadActivityGroupSetDtl(html){
	 parent.childSpreadActivityGroupSetDtl(html);
	 frameElement.dialog.close();
 }
 
 // 
 function addSpreadActivityGroupSetDtl(obj, id, activityGroup, channelName, isEffect){
	 var trId = $(obj).parent().parent().parent().attr('id');
	 var str = trId.split("|");
	 $("#[id='"+str[0]+"|1|"+str[2]+"']").remove();
	 $(obj).parent().parent().parent().remove();
	 var activityGroupIds = $("#activityGroupIds").val();
	 if(activityGroupIds == '' ) {
		 $("#activityGroupIds").val(id);
	 }else {
		 $("#activityGroupIds").val(activityGroupIds+","+id);
	 }
	 var html = [];
	 html.push({id:id, activityGroup:activityGroup, channelName:channelName, isEffect:isEffect});
	 parent.childSpreadActivityGroupSetDtl(html);
 }
 
 var listConfig={
	 btnItems:[{text:'批量添加', icon:'add', click: function() {
		 var data = listModel.gridManager.getSelectedRows();
        	if (data.length == 0) {
        		commUtil.alertError('请选择行！');
        	}else {
           		var html = [];                         
            	$(data).each(function() {    
            		html.push({id:this.id, activityGroup:this.activityGroup, channelName:this.channelName, isEffect:this.isEffect});
            	});
            	parentSpreadActivityGroupSetDtl(html); 
        	}
        }}
	 ],
     url:"/spreadActivityGroupSet/spreadActivityGroupList.shtml",
     listGrid:{ columns: [
			{ display: '活动组', name:'activityGroup', align:'center', isSort:false, width:180},
			{ display: '所属推广渠道', name:'channelName', align:'center', isSort:false, width:180},
            { display: '是否有效', name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
            	if(rowdata.isEffect == '1' ) {
					return "有效";
				}else{
					return "无效";
				}
            }},
            { display: '操作', name:'', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
				return "<a href='javascript:;' onclick='addSpreadActivityGroupSetDtl(this,"+rowdata.id+",\""+rowdata.activityGroup+"\",\""+rowdata.activityGroup+"\","+rowdata.isEffect+");'>添加</a>";
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" id="channel" name="channel" value="${channel }" >
		<input type="hidden" id="activityGroupIds" name="activityGroupIds" value="${activityGroupIds }" >
		<input type="hidden" id="deviceType" name="activityGroupIds" value="${deviceType }" >
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">活动组：</div>
					<div class="search-td-combobox-condition">
						<input type="text" id="activityGroup" name="activityGroup">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">是否有效：</div>
					<div class="search-td-combobox-condition">
						<select id="isEffect" name="isEffect" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="0">无效</option>
							<option value="1">有效</option>
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