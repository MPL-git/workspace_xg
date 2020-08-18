<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<html>

<head>

 <script type="text/javascript">
 var listConfig={
      url:"/roleMng/datalist.shtml", 
      btnItems:[{ text: '增加', icon: 'add',     dtype:'win',  click: itemclick, url:"/roleMng/toadd.shtml", seqId:"" },
                { text: '修改', icon: 'modify',  dtype:'win',  checkType:'single', click: itemclick, url:"/roleMng/toadd.shtml?ROLE_ID=",  seqId:"ROLE_ID" },
                { text: '删除', icon: 'delete',  dtype:'ajax', checkType:'mult',   click: itemclick, url:"/roleMng/delData.shtml?ROLE_ID=",seqId:"ROLE_ID" },//支持单多选
                { text: '菜单权限',icon: 'config', dtype:'win', click: itemclick, url:"/roleMng/toMenuMng.shtml?CHECK_ROLE_ID=", seqId:"ROLE_ID" }
               ],
      listGrid:{ columns: [   { display: '角色标识', name: 'dataMap.ROLE_ID',width: 100, minWidth: 60 },
		                { display: '角色类型', name: 'dataMap.ROLE_TYPE_NAME', minWidth: 120 },
		                { display: '角色名称', name: 'dataMap.ROLE_NAME', align: 'left', width: 200, minWidth: 60 },
		                { display: '已分配人员', name: 'staffNames', align: 'left', width: 200, minWidth: 60 },
		                { display: '创建人',  name: 'dataMap.CREATE_STAFF_ID', minWidth: 140 },
		                { display: '创建时间', name: 'dataMap.CREATE_DATE' },
		                { display: '修改人员', name: 'dataMap.MODIFY_STAFF_ID' },
		                { display: '修改时间', name: 'dataMap.STATUS_DATE' },
		                { display: '上级角色', name: 'dataMap.PARENT_ROLE_NAME', align: 'left', width: 200, minWidth: 60 },
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
    </head>
	<body style="padding: 0px; overflow: hidden;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
			<div id="topmenu"></div> 
			<div class="l-panel-search"> 
				<div class="l-panel-search-item">
					角色名称：
				</div>
				<div class="l-panel-search-item">
					<input type="text" id="ROLE_NAME" name="ROLE_NAME"/>
					<input type="hidden" id="totalCount" name="totalCount" value=""/>
				</div>
				<div class="l-panel-search-item">
					角色类型：
				</div>
				<div class="l-panel-search-item">
				  <select id="ROLE_TYPE" name="ROLE_TYPE" class="querysel">
					<option value="">请选择...</option>
					<c:forEach var="getroletypeNameList" items="${getroletypeNameList}">
					   <option value="${getroletypeNameList.ROLE_TYPE}">${getroletypeNameList.ROLE_TYPE_NAME}</option>
				   </c:forEach>					
				 </select>
				</div>
				<div class="l-panel-search-item">
					<div id="searchbtn" style="width: 80px;">
						搜索
					</div>
				</div>
			</div>
			<div id="toptoolbar"></div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
	</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
