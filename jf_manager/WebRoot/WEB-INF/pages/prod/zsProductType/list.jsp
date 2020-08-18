<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<html>
 <script type="text/javascript">
        var gridManager = null;
        $(function (){
        	if('${roleId }' == '1') {
	            //工具条
	           $("#toptoolbar").ligerToolBar({
	    			items: [
	    			  {text: '增加', icon: 'add', click: function() {
							 commUtil.openUrlWin("增加" ,"/prod/zsProductType/add.shtml?id="+${id},{},1200,850);
							}},
						{ line:true },
						
						{text: '修改', icon: 'modify', click: function() {
	    				  var rowsdata = gridManager.getCheckedRows();
	                        var str = ""
	                        var params={};
	                        $(rowsdata).each(function ()
	                        {
	                            str = this.id ;
	                        });
	                        if (!rowsdata.length) alert('请选择行');
	                        else
							 commUtil.openUrlWin("修改" ,"/prod/zsProductType/edit.shtml?id="+str, params,1200,850);
							}},
	    			  { line:true },
	    			  
	                 {text: '删除', icon: 'delete', click: function(yes) {
	    			  var data = gridManager.getCheckedRows();
	                        if (data.length == 0)
	                            alert('请选择行');
	                        else
	                        {
	                           // var checkedIds = [];
	                           var str = ""
	                           var params={};
	                           
	                            $(data).each(function ()
	                            {    
	                                str = this.id ;
	                            });
	                            $.ligerDialog.confirm('确定删除?', function ()
	                            {
	                                commUtil.toDoAjax("/prod/zsProductType/del_success.shtml?id="+str,params);
	                            }); 
	                        }
	                        return;
	    			  }}
	    			]
	    		});
        	}

            //表格
            $("#maingrid").ligerGrid({
                columns: [  
                { display: '品类ID', name: 'id' },
                { display: '级别', name: 't_level' },
                { display: '上级ID', name: 'parent_id',hide:${t_level==1}},
                { display: '名称', name: 'name'},
                { display: '关联APP一级类目', name: 'product_type_name',hide:${tLevel==1 || tLevel==2}},
                { display: '状态', name: 'status_desc'},
                { display: '关联APP三级类目', name: 'third_product_type_name',hide:${tLevel==0}},
                { display: '备注', name: 'remarks'}
                ], 
                method :'post',
                dataAction: 'server',
                data: "",
                url:"${pageContext.request.contextPath}/prod/zsProductType/datalist.shtml?id="+${id},
                sortName:'id',
                width: '100%', height: '100%',
                pageSize:10,
                pageSizeOptions:[10, 15, 20, 50, 100] ,
                rownumbers:true,
                checkbox : false,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6,
                onAfterShowData: function() { 
					$(".l-grid-row-cell-inner").css("height", "auto");
					var i = 0;
					$("tr",".l-grid2","#maingrid").each(function() {
					$($("tr",".l-grid1","#maingrid")[i]).css("height",$(this).height());
					i++;
					});
				}
            });
            gridManager = $("#maingrid").ligerGetGridManager();
            $("#pageloading").hide();
        });
    </script>
	<body style="padding: 0px; overflow: hidden;">
	<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<form id="dataForm" runat="server" >
			<div id="topmenu"></div>
			<div id="toptoolbar"></div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		<div style="display: none;">
		</div>
		</div>
				<div id="pic_Div" style="z-index: 10000;display: none;width: 100%;height: 100%;position: absolute; !important;">
		
		<table
			style="background-color: transparent; z-index: 2001; position: absolute; width: 100%; height: 100% !important">
			<tr>
				<td valign="middle" align="center">
				<div onclick="pic_hide()" title="关闭图片">
						<img style="width: 100%;height: 100%;" id="pic_img" alt="自动关闭" />
					</div>
				</td>
			</tr>
		</table>
		<div
			style="position: fixed; filter: alpha(Opacity = 50); -moz-opacity: 0.5; opacity: 0.5; z-index: 2000; background-color: #000000; height: 100%; width: 100%; text-align: center; vertical-align: middle;">
		</div>
		</div>
	</body>
<script type="text/javascript">
			function pic_show(url){
				$("#pic_img").attr("src",url);
				$("#pic_Div").show(100);
			}
			
			function pic_hide(){
				$("#pic_Div").hide(100);
			}
		</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
