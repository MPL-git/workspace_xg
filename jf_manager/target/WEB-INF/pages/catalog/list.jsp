<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<html>
 <script type="text/javascript">
 
	 function editorder(id) {
		$.ligerDialog.open({
				height: 400,
				width: 800,
				title: "编辑栏目",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/catalog/editcatalog.shtml?id=" + id ,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
		}
	 
	 function canclecatalog(id) {
		 if(catalogIsHased(id)){
			 $.ligerDialog.alert("存在子栏目，无法删除");
		 }else{
			 $.ligerDialog.confirm('确定删除?', function (yes){
				 if(yes){
					cancleCatalog(id);
				 }
    		 }); 
			 
		 }
	 }
	 
	 var maingrid ;
        $(function (){
            //工具条
           $("#toptoolbar").ligerToolBar({
    			items: [
    			  {text: '增加栏目', icon: 'add', click: function() {
						 commUtil.openUrlWin("增加栏目" ,"/catalog/editcatalog.shtml?id="+${id}+ "&todo=add");
						}},
					{ line:true },
    			]
    		});

            //表格
           maingrid =  $("#maingrid").ligerGrid({
                columns: [  
                { display: '排序', name: 'seq_no' },
                { display: '栏目目录', name: 'catalog' },
                { display: '后端栏目名称', name: 'back_name' },
                { display: '前端栏目名称', name: 'front_name' },
                { display: '上级栏目名称', name: 'parent_name' },
                { display: '状态', name: 'status_desc'},
                { display: "操作", name: "todo", width: 150, align: "center", render: function(rowdata, rowindex) {
					var html = [];
				    html.push("<a href=\"javascript:editorder(" + rowdata.id + ");\">编辑</a>&nbsp;&nbsp;"); 
				    html.push("<a href=\"javascript:canclecatalog(" + rowdata.id + ");\">删除</a>&nbsp;&nbsp;"); 
				    
				    return html.join("");}
			 	}
                ], 
                method :'post',
                dataAction: 'server',
                data: "",
                url:"${pageContext.request.contextPath}/catalog/datalist.shtml?id="+${id},
                sortName:'id',
                width: '100%', height: '100%',
                pageSize:10,
                pageSizeOptions:[10, 15, 20, 50, 100] ,
                rownumbers:true,
                checkbox : false,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
            $("#pageloading").hide();
        });
        
        
     	//判断是否有子目录
		function catalogIsHased(id) {
			var isHased = false;
			$.ajax({
				url : "${pageContext.request.contextPath}/catalog/checkchildCatalog.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					name : id
				},
				timeout : 30000,
				success : function(data) {
					if ("1" == data.hased) {
						isHased = true;
					}
				},
				error : function() {
					alert('操作超时，请稍后再试！');
				}
			});
			return isHased;
		}
     	
		//删除目录
		function cancleCatalog(id) {
			$.ajax({
				url : "${pageContext.request.contextPath}/catalog/cancle.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {
					id : id
				},
				timeout : 30000,
				success : function(data) {
					if ("200" == data.statusCode) {
						$.ligerDialog.success("操作成功",function() {
		              		window.parent.delNode(id);
		                    javascript:location.reload();
						});
					}else{
						$.ligerDialog.warn(data.message,function() {
                            javascript:location.reload();
						 });
					}
				},
				error : function() {
					alert('操作超时，请稍后再试！');
				}
			});
			return isHased;
		}
		
		$(function(){
			//搜索
	        $("#searchbtn").ligerButton({ click: function ()
	        {
	        	 var gridparms = [];
	        	 var title=document.getElementById("catalogname");
	        	 var titlevalue = title.value;
	 	         gridparms.push({ name: "catalogname", value: titlevalue });
	 	        maingrid.loadServerData(gridparms);
	         }
	      });
		});
		
		
    </script>
	<body style="padding: 0px; overflow: hidden;">
	<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
			<div id="topmenu"></div>
			<div id="toptoolbar"></div>
			<div class="search-tr"  > 
				<div class="search-td">
				<div class="search-td-label"  >标栏目名称：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "catalogname" name="catalogname" >
				</div>
				</div>
				
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		<div style="display: none;">
		</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
