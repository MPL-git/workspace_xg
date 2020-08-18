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
	 
	 //批量解冻
	 function updateBlackList(str) {
		 $.ajax({
			url : "${pageContext.request.contextPath}/blackList/updateBlackList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {blackListId : str},
			success : function(data) {
				if(data.code == '200') {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	 }
	 
 	 var listConfig={
	      url:"/blackList/getBlackListList.shtml",
	      btnItems:[
					{text: '批量解冻', icon: 'modify', click: function() {
						  var data = listModel.gridManager.getSelectedRows();
				          if (data.length == 0) {
				        	  $.ligerDialog.alert('请选择行');
				          }else {
				             var str = "";
				              $(data).each(function () {    
				            	  if(str==''){
				            		  str = this.id ;
				            	  }else{
				            		  str += ","+ this.id ;
				            	  }
				              });                                                      
				              updateBlackList(str);
				          }; 
				          return;
					  }}
	                ],
	      listGrid:{ columns: [
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:80},
							{display:'会员名称',name:'memberNick', align:'center', isSort:false, width:180},
							{display:'电话号码',name:'memberMobile', align:'center', isSort:false, width:180},
							{display:'屏蔽类型',name:'blackTypeDesc', align:'center', isSort:false, width:180},
							{display:'拉黑原因',name:'blackReason', align:'center', isSort:false, width:180},
							{display:'冻结时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if (rowdata.blackToDate != null && rowdata.blackToDate != '') {
									var blackToDate = new Date(rowdata.blackToDate);
									return blackToDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'操作人员',name:'createStaffName', align:'center', isSort:false, width:180}
			         ], 
	                 showCheckbox :true,  //不设置默认为 true
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
					<div class="search-td-label"  >会员名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >电话号码：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberMobile" name="memberMobile" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">冻结时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginBlackToDate" name="beginBlackToDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endBlackToDate" name="endBlackToDate" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >屏蔽类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="blackType" name="blackType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="blackType" items="${blackTypeList }">
								<option value="${blackType.statusValue }">
									${blackType.statusDesc }
								</option>
							</c:forEach>
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
