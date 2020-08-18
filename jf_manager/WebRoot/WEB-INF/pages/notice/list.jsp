<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<html>
<head>

<script type="text/javascript">
$(function(){
	$(".querysel2").get(0).selectedIndex=1;//默认选中在用
	
	});
	
 var listConfig={
      url:"/notice/datalist.shtml",
     
      btnItems:[{ text: '增加', icon: 'add',     dtype:'win',  click: itemclick, url:"/notice/add.shtml", seqId:"" },
                { text: '修改', icon: 'modify',  dtype:'win',  checkType:'single', click: itemclick, url:"/notice/edit.shtml?NOTICE_ID=",  seqId:"NOTICE_ID" },
                { text: '删除', icon: 'delete',  dtype:'ajax', checkType:'mult',   click: itemclick, url:"/notice/del.shtml?NOTICE_ID=",seqId:"NOTICE_ID" } 
               ],
      listGrid:{ columns: [   { display: '公告ID', name: 'NOTICE_ID' },
		                { display: '公告标题', name: 'NOTICE_TITLE' ,width:"10%" },
		                { display: '公告级别', name: 'NOTICE_LEVEL_NAME'  },
		                { display: '公告开始时间',  name: 'BGN_DATE'},
		                { display: '公告结束时间', name: 'END_DATE' },
		                { display: '公告发布人', name: 'CREATE_STAFF_NAME' },
		                { display: '公告发布时间', name: 'CREATE_DATE' },
		                { display: '公告状态', name: 'STATUS_NAME' },
		                { display: '公告修改人', name: 'MODIFY_STAFF_NAME' }
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
       
  }
   
    </script>
	</head>
	<body style="padding: 0px; overflow: hidden;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
			<div id="topmenu"></div>
			<div id="toptoolbar"></div>
			<div class="l-panel-search" >  
		     <div class="l-panel-search-item" >
					公告标题：
				</div> 
				<div class="l-panel-search-item"> 
				<input type="text" id="NOTICE_TITLE" name="NOTICE_TITLE" />
				
					<input type="hidden" id="totalCount" name="totalCount" value=""/>
				</div>
				   <div class="l-panel-search-item">
					公告级别：
				</div> 
				<div class="l-panel-search-item"> 
				<select style="width: 200px;" id="NOTICE_LEVEL" name="NOTICE_LEVEL"
						class="querysel">
						<option value="">
							请选择
						</option>
						<c:forEach var="list" items="${NoticeLevel}">
							<option <c:if test="${NOTICE_LEVEL==list.STATUS_VALUE}">selected</c:if>
								value="${list.STATUS_VALUE}">
								${list.STATUS_DESC}
							</option>
						</c:forEach>
					</select>
				</div> 
				 <div class="l-panel-search-item">
					公告状态：
				</div> 
				
				<div class="l-panel-search-item"> 
				<select style="width: 200px;" id="STATUS" name="STATUS"
						class="querysel2">
						<option value="">
							请选择
						</option>
						<c:forEach var="list" items="${STATUSType}">
							<option <c:if test="${STATUS==list.STATUS_VALUE}">selected</c:if>
								value="${list.STATUS_VALUE}">
								${list.STATUS_DESC}
							</option>
						</c:forEach>
					</select>
				</div> 
				<div class="l-panel-search-item">
					<div id="searchbtn" style="width: 80px;">
						搜索
					</div>
				</div> 
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		 
	</body> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
	
</html>
