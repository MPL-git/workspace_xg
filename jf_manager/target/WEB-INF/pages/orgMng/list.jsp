<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<html>
 <script type="text/javascript">
 
 function updateStaffManagement(staffId, isManagement) {
	 $.ajax({
		 type: 'post',
		 url: '${pageContext.request.contextPath }/orgMng/updateStaffManagement.shtml',
		 data: {staffId : staffId, isManagement : isManagement},
		 dataType: 'json',
		 success: function(data) {
			 if(data == null || data.returnCode != 200){
			     commUtil.alertError(data.returnMsg);
			 }else {
				  commUtil.alertSuccess("操作成功！");
				  $("#maingrid").ligerGetGridManager().reload();
			 }
		 },
		 error: function(e) {
			 commUtil.alertError("系统异常请稍后再试");
		 }
	 });
 }
 
 function sysStaffproductType(id){
		$.ligerDialog.open({
			height: 230,
			width: 500,
			title: "负责类目设置",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/orgMng/addsysStaffProductType.shtml?staffid="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
function updatestaffProductTypeId(staffproducttypeid) {
	    var title="取消";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/orgMng/upDatestaffProductTypeId.shtml',
			data: {staffproducttypeid : staffproducttypeid},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	   }
	  });
	}
 
 function staffmchtcontactpermission(id){
		$.ligerDialog.open({
			height: 230,
			width: 500,
			title: "可查看商家对接人类型",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/orgMng/addstaffmchtcontactpermission.shtml?staffid="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
 }
 
 
 function menuPermissions(id){
		$.ligerDialog.open({
			height: 630,
			width: 500,
			title: "菜单权限",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/orgMng/menuPermissions.shtml?staffid="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
}
 
 
 //详情
 function showStaffNames(staffNames) {
	 $.ligerDialog.alert(staffNames, '下级人员', 'none');
 }
 
 //设置管理层及下属人员
 function updateStaffManager(staffId) {
	 $.ligerDialog.open({
		height: 500,
		width: 400,
		title: "设置管理层",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/orgMng/updateStaffManager.shtml?staffId="+staffId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }
 
 var listConfig={
      url:"/orgMng/list.shtml?ORG_ID=${ORG_ID }",
     
      btnItems:[{ text: '增加人员', icon: 'add', id:'add',    dtype:'win', click: itemclick, url:"/orgMng/toadd_staff.shtml?ORG_ID=${ORG_ID }",      seqId:"" },
                { text: '修改人员', icon: 'modify', id:'modify', dtype:'win', click: itemclick, url:"/orgMng/toadd_staff.shtml?STAFF_ID=",      seqId:"STAFF_ID" },
                { text: '删除人员', icon: 'delete', id:'delete', dtype:'ajax', click:itemclick, url:"/orgMng/delData_staff.shtml?STAFF_ID=",    seqId:"STAFF_ID" },
                { line:true },
                { text: '密码重置',icon: 'role', id:'delete',   dtype:'ajax', click: itemclick, url:"/orgMng/reset_staff_pwd.shtml?STAFF_ID=", seqId:"STAFF_ID" },
                { text: '人员角色权限',icon:'config', id:'modify',   dtype:'win', click: itemclick, url:"/orgMng/toRoleMng_index.shtml?CHECK_STAFF_ID=", seqId:"STAFF_ID" },
                { line:true },
                { text: '【温馨提示】：新增人员时，初始密码为<font color="red">123456</font>' }
               ], 
      listGrid:{  columns: [   { display: '人员标识', name: 'STAFF_ID' },
                { display: '人员工号', name: 'STAFF_CODE'},
                { display: '人员名称', name: 'STAFF_NAME' },
                { display: '组织部门',  name: 'ORG_ID_NAME' },
                { display: '联系号码', name: 'MOBILE_PHONE', width: 120 },
                { display: '查看商家联系人',width:150,render: function(rowdata, rowindex){
		            var html=[];
		            if (rowdata.mcht_contact_type!=null && rowdata.mcht_contact_type!='') {
						var mchtcontacttype=rowdata.mcht_contact_type.split(",");
						for (var i=0; i < mchtcontacttype.length; i++) {
						    if (mchtcontacttype[i]=='1') {
								html.push("电商总负责人");
								html.push("<br>");
							}
							if (mchtcontacttype[i]=='2') {
								html.push("运营对接人");
								html.push("<br>");
							}
							if (mchtcontacttype[i]=='3') {
								html.push("订单对接人");
								html.push("<br>");
							}
							if (mchtcontacttype[i]=='4') {
								html.push("售后对接人");
								html.push("<br>");
							}
							if (mchtcontacttype[i]=='5') {
								html.push(" 财务对接人");
								html.push("<br>");
							}
							if (mchtcontacttype[i]=='6') {
								html.push("客服对接人");
								html.push("<br>");
						  }		
							 
						}
					}
		              return html.join("");	        			          		             		          
		        }},
                { display: '负责类目',width:150,render: function(rowdata, rowindex){
		            var html=[];
		            if (rowdata.staffproducttype_Id!=null && rowdata.staffproducttype_Id!='') {
		            var staffproducttypeid=rowdata.staffproducttype_Id.split(","); 
		            var producttypenames=rowdata.product_typename.split(",");
		            for(var i=0; i < staffproducttypeid.length; i++){
		               html.push(producttypenames[i]);
		               html.push("<a href=\"javascript:updatestaffProductTypeId(" +staffproducttypeid[i]+ ");\">【取消】</a>");
		               html.push("<br>"); 	
		             }					
				  }		      		               					                							  
		             return html.join("");		        			          		             		          
		        }},
                { display: '管理层', name: '', render: function(rowdata, rowindex) {
		        	var html = [];
		        	html.push("<span id='isManagement-" + rowdata.STAFF_ID + "'>");
						if(rowdata.IS_MANAGEMENT == '1') {
			        	html.push("是"+"</br>");
		        	}
		        	if (rowdata.CAN_VIEW_QUALIFICATION =='1'){
						html.push("可看资质");
					}else {
						html.push("不可看资质");
					}
		        	html.push("</span>");
		        	return html.join("");
	         	}},
                { display: '下级人员', name: '', width: 120 , render: function(rowdata, rowindex) {
		        	var html = [];
		        	if(rowdata.staff_names != null && rowdata.staff_names != '') {
			        	var staff_names = rowdata.staff_names.split(",");
			        	if(staff_names.length > 3) {
			        		html.push(staff_names[0]+","+staff_names[1]+","+staff_names[2]+"...");
			        		html.push("</br><a href=\"javascript:showStaffNames('"+rowdata.staff_names+"');\">详情</a>")
			        	}else {
			        		html.push(rowdata.staff_names);
			        	}
		        	}
		        	return html.join("");
	         	}},
		        { display: '创建时间', name: 'CREATE_DATE', width: 120 }, 
		        { display: '修改人', name: 'MODIFY_STAFF_ID' },
		        { display: '修改时间', name: 'STATUS_DATE', width: 120 },
		        { display: '操作', name: '', width:180, hide:${ROLE_ID != '1'}, render: function(rowdata, rowindex) {
		        	var html = [];
		        	html.push("<span id='staffId-" + rowdata.STAFF_ID + "'>");
		        	html.push("<a href=\"javascript:updateStaffManager("+rowdata.STAFF_ID+");\">【设为管理层】</a></br>");
		        	html.push("<a href=\"javascript:sysStaffproductType("+rowdata.STAFF_ID+");\">【负责类目设置】</a></br>");
		        	html.push("<a href=\"javascript:staffmchtcontactpermission("+rowdata.STAFF_ID+");\">【可查看商家对接人类型】</a></br>");
		        	html.push("<a href=\"javascript:menuPermissions("+rowdata.STAFF_ID+");\">【查看菜单权限】</a>");
		        	html.push("</span>");
		        	return html.join("");
	         	}}
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
     
	<body style="padding: 0px; overflow: hidden;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
			<div id="topmenu"></div>
			<div class="l-panel-search">
				<div class="l-panel-search-item">
					人员名称（或工号）：
				</div>
				<div class="l-panel-search-item">
				    <input type="text" name="STAFF_NAME" value="${ STAFF_NAME}" />
					<input type="hidden" id="ORG_ID" name="ORG_ID" value="${ ORG_ID}"/>
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
