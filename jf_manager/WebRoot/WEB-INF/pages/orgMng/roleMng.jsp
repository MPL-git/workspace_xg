<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<%-- Liger  --%>
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<html>
 <script type="text/javascript">
 var listConfig={
      url:"/orgMng/toRoleMng.shtml",
     
     btnItems: [{ text: '授权保存', icon: 'save',   id:'add',    dtype:'ajax', click: itemclick, url:"/orgMng/saveRoles.shtml?CHECK_STAFF_ID=${CHECK_STAFF_ID}&OPER_FLAG=add&ROLE_IDS=", seqId:"ROLE_ID" },
                { text: '权限注销', icon: 'delete',id:'delete', dtype:'ajax', click:itemclick, url:"/orgMng/saveRoles.shtml?CHECK_STAFF_ID=${CHECK_STAFF_ID}&OPER_FLAG=update&ROLE_IDS=", seqId:"ROLE_ID" },
                { line:true },
                { text: '【当前选中人员】：${STAFF_NAME}(${STAFF_CODE})' }
               ]
               , 
      listGrid:{  columns: [ { display: '角色标识', name: 'ROLE_ID' },
                { display: '角色名称', name: 'ROLE_NAME' },
                { display: '已授权', name: 'IF_CONFIG' },
                { display: '角色类型', name: 'ROLE_TYPE_NAME' },
                { display: '创建人',  name: 'CREATE_STAFF_ID' },
                { display: '创建时间', name: 'CREATE_DATE' }
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
	<body style="padding: 0px; overflow: hidden;"  onload="javascript:$('#searchbtn').click();">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
			<div id="topmenu"></div>
			<div class="l-panel-search">
				<div class="l-panel-search-item"> 角色名称： </div>
				<div class="l-panel-search-item">
					<input type="text" id="ROLE_NAME" name="ROLE_NAME"/>
					<input type="hidden" name="CHECK_STAFF_ID" value="${CHECK_STAFF_ID}"/>
				</div>				
				<div class="l-panel-search-item"> 角色类型： </div>
				<div class="l-panel-search-item">
					<select name="ROLE_TYPE" >
					<option value="" > </option>
					<c:forEach items="${ROLE_TYPE_DATA}" var="itemt">
					 <option value="${itemt.STATUS_VALUE }" <c:if test="${itemt.STATUS_VALUE== 0}"> selected</c:if> >
					  ${itemt.STATUS_DESC } 
					 </option>
					</c:forEach>
					</select>
				</div>
				<div class="l-panel-search-item">是否已授权：</div>
				<div class="l-panel-search-item">
					<select name="IF_CONFIG" >
					<option value="" selected> </option>
					<option value="0">
					  未授权
					 </option>
					 <option value="1"  >
					  已授权
					 </option>
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
		<div style="display: none;">
		</div>
	</body>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
