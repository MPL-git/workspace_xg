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
    //修改排序值
	function updateSeqNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/product/updateSpecificationManagementList.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
					 }
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>OK</span>");
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + id).val("");
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	
	//修改标准名称
	function updateAlias(id) {
		$("#alias" + id).parent().find("span").remove();
		var alias = $("#alias" + id).val();
		if(alias != '') {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/prod/product/updateAlias.shtml",
				 data : {id : id, alias : alias},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200){
						 
						 commUtil.alertError(json.message);
						 
					 }else{
						 /* $("#alias" + id).parent().append("<span style='color:#009999;'>OK</span>");  */
						 /* $("#searchbtn").click(); */
						 $("#maingrid").ligerGetGridManager().reload();
						
					 } 
					
				  },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 } 
			 });
		}	
	}
	
	
	function updateSeqNoType(id){
		  $.ligerDialog.open({
				height: 260,
				width: 400,
				title: "批量修改排序值",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/prod/product/updateSpecificationManagementList.shtml?seqnoId=" + id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	  }
	    
 	var listConfig={
	      url:"/product/SpecificationManagementListdata.shtml",
	      btnItems:[{text: '批量修改排序值', icon: 'modify', click: function() {
     			  var data = listModel.gridManager.getSelectedRows();
                         if (data.length == 0)
                       	  $.ligerDialog.alert('请选择行');
                         else
                         {
                            var str = "";
                             $(data).each(function ()
                             {    
                           	  if(str==''){
                           		  str = this.id ;
                           	  }else{
                           		  str += ","+ this.id ;
                           	  }
                             });
//                             $.ligerDialog.confirm('确定修改?', function (yes)                                                        
                               updateSeqNoType(str);
                             	
                             }; 
                         
                         return;
     			  }},
			],
	       	          
	      listGrid:{ columns: [
	                        {display:'ID',name:'id', align:'center', width:80},
	                        {display:'规格名称',name:'productpropname', align:'center', width:230},
	                        {display:'规格值',name:'propValue', align:'center', width:230},
	                        {display:'标准名称',name:'alias', align:'center', width:100,render:function(rowdata, rowindex) {
	                        	var alias=rowdata.alias==null?'':rowdata.alias;
		                        return "<input type='text' id='alias" + rowdata.id + "' name='alias' onChange='updateAlias(" + rowdata.id + ")' value='" + alias + "' >";
	                        }},
	                        
	                        {display:'排序值',name:'seqNo', align:'center', width:80, render:function(rowdata, rowindex) {
	                        	var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
	                        	return "<input type='text' id='seqNo" + rowdata.id + "' name='seqNo' onChange='updateSeqNo(" + rowdata.id + ")' value='" + seqNo + "' >";
	                        }},	                                 
	                                   
	                        {display:'创建者商家序号',name:'createbymchtcode', align:'center', width:230},
	                        {display:'创建者主营类目',name:'producttypename', align:'center',  width:230},
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
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >规格名称：</div>
					<div class="search-td-combobox-condition" >
						<select id="productPropId" name="productPropId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="productProps" items="${productPropsList}">
								<option value="${productProps.id }">
									${productProps.name}
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >规格值：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="propValue" name="propValue" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >同义词：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id=alias name="alias" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >创建者商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="createbymchtcode" name="createbymchtcode" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >创建者主营类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;" >
							<option value="">请选择...</option>
						    <c:forEach var="productType" items="${productTypeList }">
								<option value="${productType.id }">
									${productType.name }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label"  >是否同义：</div>
					<div class="search-td-combobox-condition" >
						<select id="alIaS" name="alIaS" style="width: 135px;" >
							<option value="">请选择...</option>
						    <option value="1">未填</option>									
						    <option value="2">已填</option>
						</select>
				 	 </div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label"  >是否排序：</div>
					<div class="search-td-combobox-condition" >
						<select id="SeqNo" name="SeqNo" style="width: 135px;" >
							<option value="">请选择...</option>							
								<option value="1">未填</option>									
								<option value="2">已填</option>							
						</select>
				 	 </div>
				</div>
								
				<div class="search-td" style="position:relative;">
			 		<div class="search-td-label" style="text-align: right: ;" >
			 			<span style="margin-right: 10px;">
				 			<input type="checkbox" value="1" name="seqNo" >
			 			</span>
			 		</div>
			 		<div class="search-td-condition" style="position:absolute; top:-2px;">按排序值排序</div>
				</div>
				
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>							
			</div>
		</div>		
	</form>
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
