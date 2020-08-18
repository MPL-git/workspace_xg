<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<html>
 <script type="text/javascript">
function toEditSmallware(id){
	$.ligerDialog.open({
		height: 200,
		width: 400,
		title: "商品类型",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/service/prod/product_type/toEditSmallware.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
} 

function toEditReturn7days(id){
	$.ligerDialog.open({
		height: 250,
		width: 400,
		title: "7天无理由退换设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/service/prod/product_type/toEditReturn7days.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
} 
 
function toEdit(id,isView){
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.6,
		title: "招商资质",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/service/prod/product_type/editBrandAptitude.shtml?id=" + id+"&isView="+isView,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
} 
 
var commUtil={
		     toDoAjaxForm: function(formName){
				       var dform=$("#"+formName);
					   $.ajax({ //ajax提交
								type:'POST',
								url: dform.attr("action"),
								data:dform.serializeArray(),
								dataType:"json",
								cache: false,
								success: function(json){
								  if(json==null || json.statusCode!=200){
								    commUtil.alertError(json.message);
								  }else{
								    commUtil.alertSuccess("操作成功");
								  }
								},
								error: function(e){
								 commUtil.alertError("系统异常请稍后再试");
								}
					    });
		     },
		     toDoAjax: function(url,jsonParms){
					   $.ajax({ //ajax提交
								type:'POST',
								url:'${pageContext.request.contextPath}' +url,
								data:jsonParms,
								dataType:"json",
								cache: false,
								success: function(json){
								alert(json);
								  if(json==null || json.statusCode!=200){
								    commUtil.alertError(json.message);
								  }else{
					              $.ligerDialog.success("操作成功",function() {
					              		window.parent.delNode(json.id);
					                            javascript:location.reload();
											 });
								  }
								},
								error: function(e){
								 commUtil.alertError("系统异常请稍后再试");
								}
					    });
		     },
		     openUrlWin:function(winTile,url,params)  {  
			            $.ligerDialog.open({
			                height:600,
			                width: 800,
			                title : winTile,
			                url: "${pageContext.request.contextPath}"+url, 
			                showMax: false,
			                showToggle: true,
			                showMin: false,
			                isResize: true,
			                slide: false,
			                data:params ,
			                //自定义参数
			                myDataName: $("#txtValue").val()
			            });
             },
             alert:function(content){
               $.ligerDialog.alert(content);
             },
             alertSuccess:function(content){
              $.ligerDialog.success(content,function() {
                            javascript:location.reload();
						 });
             },
             
             alertError:function(content){
              $.ligerDialog.warn(content,function() {
                            javascript:location.reload();
						 });
             }
    
    
    }
    function f_open(winTile,url,params) 
       {  
            $.ligerDialog.open({
                height:600,
                width: 800,
                title : winTile,
                url: "${pageContext.request.contextPath}"+url, 
                showMax: false,
                showToggle: true,
                showMin: false,
                isResize: true,
                slide: false,
                data:params ,
                //自定义参数
                myDataName: $("#txtValue").val()
            });
        } ;
        
        
       var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        
       /**
        *  parms: [{ name: 'Country', value: Country}]
        */
        
	 function formToJson(form){
			 	var paramsArray = $("#"+form).serializeArray();
			 	return paramsArray;
		   }
	 
	 
        var gridManager = null;
        $(function (){
        	if('${roleId }' == '1') {
	            //工具条
	           $("#toptoolbar").ligerToolBar({
	    			items: [
	    			  {text: '增加', icon: 'add', click: function() {
							 commUtil.openUrlWin("增加" ,"/service/prod/product_type/add.shtml?id="+${id});
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
							 commUtil.openUrlWin("修改" ,"/service/prod/product_type/edit.shtml?id="+str, params);
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
	                                commUtil.toDoAjax("/service/prod/product_type/del_success.shtml?id="+str,params);
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
                { display: '上级ID', name: 'parent_id' },
                { display: '品类名称', name: 'name'},
                { display: '备注', name: 'remarks'},
                { display: '状态', name: 'status_desc'},
                { display: '企业默认保证金', name: 'deposit'},
                { display: '企业默认佣金比例', name: 'fee_rate', align: "center", render: function(rowdata, rowindex) {
					var html = [];
					var feeRate=rowdata.fee_rate;
					if (feeRate==null || feeRate==0){
	                    html.push("-");
					}else
					{
						html.push(feeRate);
					}
				    return html.join("");
                  }
                },
                { display: '个体商户保证金', name: 'individual_deposit'},
                { display: '个体商户佣金比例', name: 'individual_fee_rate', align: "center", render: function(rowdata, rowindex) {
					var html = [];
					var individualFeeRate=rowdata.individual_fee_rate;
					if (!individualFeeRate || individualFeeRate==0){
	                    html.push("-");
					}else
					{
						html.push(individualFeeRate);
					}
				    return html.join("");
                  }
                },
                { display: '企业开通活动DSR', name: 'enterprise_activities_DSR', width: 100},
                { display: '企业开通营业额 ', name: 'enterprise_turnover', width: 100},
                { display: '企业开通活动销量', name: 'enterprise_activity_sales', width: 100},
                { display: '适合性别', name: 'suit_sex', width: 150, align: "center", render: function(rowdata, rowindex) {
                	var sexStr=String(rowdata.suit_sex);
					var html = [];
 					if (sexStr.charAt(0)=="1"){
 						html.push("男");
					}
                    if (sexStr.charAt(1)=="1"){
                    	html.push("女");
					}
				    return html.join("、");
                  }
                },
                { display: '适合人群', name: 'suit_group', width: 150, align: "center", render: function(rowdata, rowindex) {
                	var groupStr=String(rowdata.suit_group);
					var html = [];
 					if (groupStr.charAt(0)=="1"){
 						html.push("青年");
					}
                    if (groupStr.charAt(1)=="1"){
                    	html.push("儿童");
					}
                    if (groupStr.charAt(2)=="1"){
                    	html.push("中老年 ");
					}	
				    return html.join("、");
                  }
                },
                { display: '排序', name: 'seq_no'},
                { display: '上级品类名称', name: 'parent_name' },
                { display: '招商资质', name: 'aptitude', align: "center", render: function(rowdata, rowindex) {
					var html = [];
					var brand_aptitude=rowdata.brand_aptitude;
					var platformContacts = ${platformContacts};
					var staffID = ${sessionScope.USER_SESSION.staffID};
					if(!brand_aptitude){
						if(platformContacts>0 || staffID == 1){
							html.push('<a href="javascript:;" onclick="toEdit('+rowdata.id+',0);">【添加】</a>');
						}
					}else{
						if(platformContacts>0 || staffID == 1){
							html.push('<a href="javascript:;" onclick="toEdit('+rowdata.id+',1);">【查看】</a><br><a href="javascript:;" onclick="toEdit('+rowdata.id+',0);">【修改】</a>');
						}
					}
				    return html.join("");
                  }
                },
                { display: '7天无理由', name: '', align:'center',width:80, render: function(rowdata, rowindex) {
                	if(rowdata.is_return_7days){
                		if(rowdata.is_return_7days == 0){
                			return '不支持<a href="javascript:;" onclick="toEditReturn7days('+rowdata.id+');">【修改】</a>';
                		}else if(rowdata.is_return_7days == 1){
                			return '支持<a href="javascript:;" onclick="toEditReturn7days('+rowdata.id+');">【修改】</a>';
                		}else if(rowdata.is_return_7days == 2){
                			return '可选择<a href="javascript:;" onclick="toEditReturn7days('+rowdata.id+');">【修改】</a>';
                		}
                	}else{
                		return '<a href="javascript:;" onclick="toEditReturn7days('+rowdata.id+');">设置</a>';
                	}
                }},
                { display: '是否支持小商品', name: 'is_smallware', align:'center',width:80, render: function(rowdata, rowindex) {
                	if(!rowdata.is_smallware){
                		return '<a href="javascript:;" onclick="toEditSmallware('+rowdata.id+');">设置</a>';
                	}else if(rowdata.is_smallware == 0){
                		return '<a href="javascript:;" onclick="toEditSmallware('+rowdata.id+');">有品牌</a>';
                	}else if(rowdata.is_smallware == 1){
                		return '<a href="javascript:;" onclick="toEditSmallware('+rowdata.id+');">小商品</a>';
                	}else if(rowdata.is_smallware == 2){
                		return '<a href="javascript:;" onclick="toEditSmallware('+rowdata.id+');">无品牌</a>';
                	}
                }}
                ], 
                method :'post',
                dataAction: 'server',
                data: "",
                url:"${pageContext.request.contextPath}/service/prod/product_type/datalist.shtml?id="+${id},
                sortName:'id',
                width: '100%', height: '100%',
                pageSize:100,
                pageSizeOptions:[10, 15, 20, 50, 100],
                rownumbers:true,
                checkbox : false,
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6,
                //换行
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

</html>
