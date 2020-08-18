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
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
  $(function(){//导出 	  	 	  
	$("#export").bind('click',function(){
		var sex = $("#sex").val();
		var age = $("#age").val();
		var occupation = $("#occupation").val();
		var stylename = $("#stylename").val();
		location.href="${pageContext.request.contextPath}/appMng/InformationTatistics/export.shtml?sex="+sex+"&age="+age+"&occupation="+occupation+"&stylename="+stylename;
	});
});
 
 
	var listConfig={
	      url:"/appMng/InformationTatistics/data.shtml",  
	      listGrid:{ columns: [
	                        {display:'序号',name:'id', align:'center', width:80},
	                        {display:'手机序列号',name:'reqimei', align:'center', width:150},
	                        {display:'用户ID',name:'memberId', align:'center', width:150},	                     	                                                       	                                   
	                        {display:'性别',name:'sex', align:'center', width:150,render: function (rowdata, rowindex){
	                        	if(rowdata.sex=="1"){
	                        		return "男";       		
	                        	}else if(rowdata.sex=="2"){
	                        	   return "女";	                        		
	                        	}
	                        }},
	                        {display:'年龄',name:'age', align:'center',  width:150},
	                        {display:'职业',name:'occupation', align:'center',  width:150},
	                        {display:'风格',name:'stylename', align:'center',  width:150},	                        		             
	                        
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
			<div class="search-tr"  > 		
				<div class="search-td">
					<div class="search-td-label"  >性别：</div>
					<div class="search-td-combobox-condition" >
						<select id="sex" name="sex" style="width: 135px;" >
							<option value="">请选择...</option>
						    <option value="1">男</option>									
						    <option value="2">女</option>
						</select>
				 	 </div>
				</div>
							
				<div class="search-td">
					<div class="search-td-label"  >年龄：</div>
					<div class="search-td-combobox-condition" >
						<select id=age name="age" style="width: 135px;" >
							<option value="">请选择...</option>
						    <option value="between">15-20</option>									
						    <option value="before">20-35</option>
						    <option value="greater">35以上</option>
						</select>
				 	 </div>
				</div>
								
				<div class="search-td">
					<div class="search-td-label"  >职业：</div>
					<div class="search-td-combobox-condition" >
						<select id="occupation" name="occupation" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">学生</option>
							<option value="2">职场新人</option>
							<option value="3">职场精英</option>
							<option value="4">其他</option>
						</select>
				 	 </div>
				</div>
							
				<div class="search-td">
					<div class="search-td-label"  >风格：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="stylename" name="stylename" >
					</div>
				</div>
				
			<div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" >搜索</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 25px;cursor: pointer;" value="导出" id="export">
				</div>	
			</div>
								
			</div>
		</div>
			
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
