var styleList=[];
var productaddAPI={ 
		contextPath : "",
        priceFlag : false,
        parentId : "",
        parentIds : "",
        productTypeId  : "",
        productId : "",
		init:function(){
			if($("#IF_OPEN").val()==1)
			{$("#style-if-p").addClass("hidden");} ;
			$("#btnAddStyle").click(productaddAPI.addColumn);
			$("#btnAddRow").click(productaddAPI.addRow);
			$("#btnSaveStyle").click(productaddAPI.saveStyle);
			$("#win-close").click(function(){
				 
				$("#win-panel").addClass("hidden");	
				$("#bg-style").removeClass("hidden");
				$("#style-if-p").removeClass("hidden");
				$("#IF_OPEN").val(0);
				});
			$("#btn-open-style").click(function(){
				$("#style-if-p").addClass("hidden");
				//判断是否开启规格
				$("#IF_OPEN").val(1);
				$("#BGN_PRICE").val(0);
				$("#END_PRICE").val(0);
				$("#win-panel").removeClass("hidden");
				$("#bg-style").addClass("hidden");
			});
			$("#btn-edit").click(function(){
				$("#win-panel").removeClass("hidden");
				$("#style-sucess").addClass("hidden");	
			});
			
			$("#btn-cancel").click(function(){
				$("#bg-style").removeClass("hidden");	
				$("#style-sucess").addClass("hidden");
				$("#style-if-p").removeClass("hidden");
				$("#IF_OPEN").val(0);
				//清空规格
				productaddAPI.priceFlag = false;
				$("#product_prices").val('');
				//库存
				$("#PRODUCT_STOCK").attr("disabled",false);
			});
			$("#btnSubmit").click(productaddAPI.dataSubmit);//保存数据
			$(".sel").change(function(){
				productaddAPI.changeSelect($(this).attr("rel"),$(this).val());	
			});
			
			$(".delimg").click(function(){ 
				$(this).parents("li").remove();	
			});
			
			$(".def").click(function(){  
				$(".def").removeClass("hover");
				$(this).addClass("hover");	
			});
			productaddAPI.initClsSelect();
			$("#cls3").change(productaddAPI.chkType);
			$("#selPingpai").change(productaddAPI.chkBrand);
			$("#product_name").blur(productaddAPI.chkProdName);
			$("#least_amount").blur(productaddAPI.chkAmount);
			$("#bgn_price").blur(productaddAPI.chkPrice);
			$("#end_price").blur(productaddAPI.chkPrice);
			$("#ch_amount").click(productaddAPI.amountEvent);
			$("#ch_price").click(productaddAPI.priceEvent);
			$("#if_amount").click(productaddAPI.ifAmountEvent);
		},
		selectHTML:'',
		selectObjHMTL:'',
		styleValue:{selname:[],selvalue:[],list:[]},//selvalue存属性名称值,list存行值
		addColumn:function(){
		    var count=$("select[name='sel']", "#header2").length;
			if (count >=3){
				$.ligerDialog.alert("您最多可以添加三种规格！");
			   return false;
			}
			$(".clsgd").each(function(index, element) {
                $(this).before('<td><span class="close1">close</span></td>');
				$(this).prev().find(".close1").click(productaddAPI.delColumn);
            });
			$(".selgd").each(function(index, element) {
                $(this).before('<td>'+productaddAPI.selectHTML+'</td>');
            });
			$(".gd").each(function(index, element) {
                $(this).before('<td><input type="text" class="txt txt-mini" name="" /></td>');
            });
			},
		delColumn:function(){
		 	var count=$("select[name='sel']", "#header2").length;
			if (count == 1){
				$.ligerDialog.alert("您至少要保留一种规格！");
			   return false;
			}
			var tds=$("#header1 > td");
			var tds2=$("#header2 > td");
			var o = $(this).parents("td");
			index = o.prevAll().length-1;
			tds.eq(index).remove();
			tds2.eq(index).remove();
			//tds.get(index).remove();
			//tds2.get(index).remove();
			$("#tblist2 > .val").each(function(i, element) {
                //$(this).find("td").get(index).remove();
				$(this).find("td").eq(index).remove();
            });
			
		},
		addRow:function(){			
			var obj=$("#tblist2").find("tr");	
			var d = obj.last().clone();	 
			d.find("input").val("");/*
			d.find(".up").click(productaddAPI.up);
			d.find(".down").click(productaddAPI.down);*/
			d.find(".colse").click(productaddAPI.delRow); 
			obj.last().after(d);
		},
		delRow:function(){
			if($(".val").length==1)return;
			$(this).parents(".val").remove();	
		},
		up:function(){
			var o =$(this).parents(".val");
			if(o.prevAll(".val").length >= 1)	
			{
				o.insertBefore(o.prev());
			}
		},
		down:function(){
			var o =$(this).parents(".val");
			if(o.nextAll(".val").length >= 1)	
			{								
				o.insertAfter(o.next());
			}	
		},
		saveStyle:function(){
			var obj={};
			obj.SPEC_LIST=[];
			obj.SPEC_VALUE=[];
			var addFlag=true;
			
			var selects = $("select", $("#header2"));
			selects.each(function(index, select) {
				var sel = $(select);
				obj.SPEC_LIST.push({SPEC_ID : sel.val(), SPEC_NAME : sel.find("option:selected").text()});
			});
			
			//alert($("#tblist2")[0].outerHTML);
			
			$(".val", "#tblist2").each(function(rowIndex, rowElement) {
				var specValues = {};
				$("input[type='text']", rowElement).each(function(colIndex, colElement) {
					var attrValue = $(colElement).val();
					if (attrValue ==  null || attrValue == ''){
						$.ligerDialog.alert("请填写完整商品规格");
					   addFlag = false;
					   return false;
					}
					if(colIndex < obj.SPEC_LIST.length) {
						var attrName = "SPEC" + obj.SPEC_LIST[colIndex].SPEC_ID;
						specValues[attrName] = attrValue;
					}
					
					if(colIndex - obj.SPEC_LIST.length == 0) {
						specValues["PRODUCT_PRICE"] = attrValue;  
					}
					if(colIndex - obj.SPEC_LIST.length == 1) {
						specValues["ORIGINAL_PRICE"] = attrValue; 
					}
					if(colIndex - obj.SPEC_LIST.length == 2) {
						specValues["PRODUCT_STOCK"] = attrValue;
					}
					if( parseFloat(specValues["PRODUCT_PRICE"])>parseFloat(specValues["ORIGINAL_PRICE"]))
						{
						$.ligerDialog.alert("销售价不能超过原价");
					   addFlag = false;
					   return false;
						}
					/*
					if(colIndex - obj.SPEC_LIST.length == 2) {
						specValues["PRICE_CODE"] = attrValue;
					}*/
				});
				
				obj.SPEC_VALUE.push(specValues);
			});
			
			if (!addFlag) {
			  return false;
			}
			$("#product_prices").val(JSON.stringify(obj))
			var list1=[];
			var list3=[];
			$("select[name=sel]").each(function(index, element) {
                list1.push($(this).val());
				list3.push($(this).find("option:selected").text());
            });
			productaddAPI.styleValue.selname=list3;
			productaddAPI.styleValue.selvalue=list1;
			var list2=[];
			var dblist=[];
			//productaddAPI.styleValue;
			$("#tblist2 > .val").each(function(index, element) {
            	$(this).find("input").each(function(i, o) {
                    list2.push($(this).val());
                });   
				dblist.push(list2); 
				//productaddAPI.styleValue.list.push(list2);
				list2=[];
          	});
			productaddAPI.styleValue.list=dblist;
			productaddAPI.initDoneHtml();
			$("#win-panel").addClass("hidden");
			$("#style-sucess").removeClass("hidden");
			//开启规格 隐藏价格
			productaddAPI.priceFlag = true;
			//库存
			$("#PRODUCT_STOCK").val('');
			$("#PRODUCT_STOCK").attr("disabled",true);
		},
		initStyleHTML:function(){
			//初始化属性表
			var html1=[];//表头删除
			var html2=[];//表头名称
			var html3=[];//行值
			$.each(productaddAPI.styleValue.selvalue,function(i,o){
					html1.push('<td><span class="close1">close</span></td>');
					html2.push('<td>',productaddAPI.getSelected(styleList,o),'</td>');
			});
			html1.push('<td class="clsgd"></td><td></td><td></td><td></td>');
			html2.push('<td class="selgd">销售价格</td><td>原价</td><td>库存</td><td>操作</td>');
			var hao_index= 2 + productaddAPI.styleValue.selvalue.length; //货号的索引位置
			
			$.each(productaddAPI.styleValue.list,function(i,o){
				html3.push('<tr class="val">');
				$.each(o,function(index,obj){
					html3.push('<td ',index==productaddAPI.styleValue.selvalue.length?"class=\"gd\"":"",'><input type="text" class="txt ',index!=hao_index?"txt-mini":"",'" value="',obj,'" /></td>')
					
					});
				html3.push('<td>  <span class="colse"></span></td>');
				/*html3.push('<td> <span class="up">up</span> <span class="down"></span> <span class="colse"></span></td>');
			*/	
				html3.push('</tr>');
				});
			
			$("#header1").html(html1.join(''));
			$("#header2").html(html2.join(''));
			$("#trGD").after(html3.join(''));
			
		},
		initDoneHtml:function(){
			var html1=[];
			var html2=[];
			html1.push('<tr>');			
			$.each(productaddAPI.styleValue.selname,function(i,o){
				html1.push('<td>',o,'</td>');
			});
			html1.push('<td>销售价格</td><td>原价</td><td>库存</td>');
			html1.push('</tr>');
			
			$.each(productaddAPI.styleValue.list,function(i,o){
				html2.push('<tr class="val">');
				$.each(o,function(index,obj){
					html2.push('<td>',obj,'</td>');					
					});
				
				html2.push('</tr>');
				});
			$("#su-head").html(html1.join(''));
			$("#su-body").html(html2.join(''));
		},
		getSelect:function(list,val){
			var html=[];
			$.each(list,function(i,o){
				html.push('<option value="',o.id,'" ',val==o.id?"selected":"",'>',o.name,'</option>');
			});
			return html.join('');
		},
		getSelected:function(list,val){
			var html=[];
			html.push('<select name="sel">');
			$.each(list,function(i,o){
				html.push('<option value="',o.id,'" ',val==o.id?"selected":"",'>',o.name,'</option>');
			});
			html.push('</select>');
			return html.join('');
		},
		
		changeSelect:function(n,id){  
			//N:0,1,2
			if($.trim(id)=="")return;
			if(n==1){
				$("#cls2,#cls3").html("");
				productaddAPI.clearSpec();
			}
			else if(n==2){
				$("#cls3").html("");	
				productaddAPI.clearSpec();  
			} else if(n==3){
				$("tr[isdata='add']", "#tblist").remove();
				productaddAPI.clearSpec();
			     $("#selPingpai").html(""); 
                   if($("#IF_OPEN").val()=="1")
					{ 
                	 $("#style-if-p").removeClass("hidden"); 
					$("#IF_OPEN").val(0);}
			     productaddAPI.brandHTML(id);
			}
			/*var msg={list:[{id:'1',name:'分类名称1'},{id:'2',name:'分类名称2'}]}
			productaddAPI.bindSelectHTML(n,msg);
			return;*/
			var data = {PARENT_ID: id};
			$.ajax({
				type: "POST",
				url: productaddAPI.contextPath + "/service/product/type.shtml",
				data: data,
				dataType: 'json',
				success: function(msg){
					//{list:[{id:'1',name:'分类名称'},{}]}
					productaddAPI.bindSelectHTML(n,msg);
				}
			});
			
		},
		bindSelectHTML:function(n,msg){ 
			var html=[];
			html.push('<option value="">请选择</option>');
			$.each(msg.LIST_DATA,function(i,o){
				html.push('<option value="',o.id,'">',o.text,'</option>');
			});
			++n;
			$("#cls"+n).html(html.join(''));
			//编辑
			if (n == 2){
				$("#cls"+n).val(productaddAPI.parentId);
			} else {
			    $("#cls"+n).val(productaddAPI.productTypeId);
			}
		},
		initClsSelect:function(){
		    var data = {PARENT_ID: 1};
			$.ajax({
				type: "POST",
				url: productaddAPI.contextPath + "/service/product/type.shtml",
				data: data,
				dataType: 'json',
				success: function(msg){  
					//{list:[{id:'1',name:'分类名称'},{}]}
					//productaddAPI.bindSelectHTML(0,msg);
					
					var html=[];
					html.push('<option value="">一级分类</option>');
					$.each(msg.LIST_DATA,function(i,o){
						html.push('<option value="',o.id,'">',o.text,'</option>');
					});
					$("#cls1").html(html.join(''));
					//编辑 
					$("#cls1").val(productaddAPI.parentIds);
				}
			});
			
		},
		
	  	
		//清空规格和产品规格标识位
		clearSpec:function(){
			//清空规格 
			$("tr[isadd='add']", "#tblist").remove();
			productaddAPI.priceFlag=false; 
			$("#product_prices").val('');
			$("tr[class='val']", "#tblist2").remove();
			$("#trsc").hide();
			$("#bg-style").addClass("hidden");
			$("#win-panel").addClass("hidden");
			$("#style-sucess").addClass("hidden");
		},
		
		brandHTML:function(id){ 
		        var data = {PRODUCT_TYPE_ID: id};
				$.ajax({
					type: "POST",
					url: productaddAPI.contextPath + "/service/product/spec.shtml",
					data: data,
					dataType: 'json',
					success: function(msg){ 
					   var html=[];
						html.push('<option value="">请选择</option>');
						$.each(msg.BRAND_LIST,function(i,o){
							html.push('<option value="',o.id,'">',o.name,'</option>');
						});
						$("#selPingpai").html(html.join(''));
						//规格 
						$("tr[isadd='add']", "#tblist").remove();
						$.each(msg.SPEC_LIST,function(i,o) {
							 var html=[];
							 html.push("<tr isadd=\"add\">");
							 html.push("<td class=\"title\"><span class=\"star\" data=", o.id , " vl=", o.name ,">", o.name, "</span></td>");
							 html.push("<td colspan=\"3\">");
							 html.push("<input type=\"text\" class=\"text\" />");
							 html.push("</td>");
							 html.push("</tr>");
							 $("#tblist tr:eq(1)").after(html.join(""));
						   });
						 //开启规格
						 $("tr[class='val']", "#tblist2").remove();
						  if (msg.PRICE_LIST.length > 0){
						    //显示开启规格
						    $("#trsc").show();
						    $("#bg-style").removeClass("hidden");
						    $("#win-panel").addClass("hidden");
							$("#style-sucess").addClass("hidden");
						    
						    styleList= msg.PRICE_LIST;
							productaddAPI.styleValue={selname:['','',''],selvalue:['', '',''],list:[['','','','','','']]};
							var h=productaddAPI.getSelect(styleList);
							productaddAPI.selectHTML='<select name="sel">'+h+'</select>';
							productaddAPI.selectObjHMTL=h;
							if(productaddAPI.styleValue.selvalue.length > 0 ){
								productaddAPI.initDoneHtml();
								productaddAPI.initStyleHTML();	
						    }
							$(".colse").click(productaddAPI.delRow);
							$(".close1").click(productaddAPI.delColumn);
							$(".up").click(productaddAPI.up);
							$(".down").click(productaddAPI.down);
						  } else {
						    //不显示开启规格
						    $("#trsc").hide();
						    $("#bg-style").addClass("hidden");
							$("#style-sucess").addClass("hidden");
						  }
			      	 }
				});
		},
	
		//编辑
		initedit:function(){
		    //初始化产品类型树 
			productaddAPI.changeSelect(1,productaddAPI.parentIds);
			productaddAPI.changeSelect(2,productaddAPI.parentId);
		  	productaddAPI.brandValueHTML(productaddAPI.productTypeId, productaddAPI.productId);
		
		},
		
	    brandValueHTML:function(id, pid){
	        var data = {PRODUCT_TYPE_ID: id,PRODUCT_ID: pid};
			$.ajax({
				type: "POST",
				url: productaddAPI.contextPath + "/service/product/specinfo.shtml",
				data: data,
				dataType: 'json',
				success: function(msg){
					 //开启规格
					 $("tr[class='val']", "#tblist2").remove();
					  if (msg.PRICE_LIST.length > 0){
					    //显示开启规格
					    $("#trsc").show();
					    styleList= msg.PRICE_LIST;
						//编辑
						var json = msg.PRODUCT_PRICES;
						$("#product_prices").val(JSON.stringify(json));
						if (json.SPEC_LIST.length > 0){
							productaddAPI.styleValue.selname=['','',''];
							productaddAPI.styleValue.selvalue = [];
							productaddAPI.styleValue.list = [];
							
							//默认选择下拉框值
							$.each(json.SPEC_LIST,function(i,o) {
							 	productaddAPI.styleValue.selvalue.push(o.SPEC_ID);
						    });
						    //行值
						   	$.each(json.SPEC_VALUE,function(i,o) {
						       var html=[];
						       $.each(json.SPEC_LIST,function(index, data) {
							 		html.push(o['SPEC' + data.SPEC_ID]);
						       });
							     html.push(o.PRODUCT_PRICE);
							     html.push(o.ORIGINAL_PRICE);
							     html.push(o.PRODUCT_STOCK);
							     productaddAPI.styleValue.list.push(html);
						     });
						 	
						     //展示规格编辑界面
						     $("#win-panel").removeClass("hidden");
							 $("#bg-style").addClass("hidden");
							
					   	} else {
					   	     $("#product_prices").val('');
					  		 productaddAPI.styleValue={selname:['','',''],selvalue:['', '',''],list:[['','','','','','']]};
					  		 //关闭规格后，展示开启规格
					  		  $("#win-panel").addClass("hidden");
							  $("#bg-style").removeClass("hidden");
							
					   	}
						var h=productaddAPI.getSelect(styleList);
						productaddAPI.selectHTML='<select name="sel">'+h+'</select>';
						productaddAPI.selectObjHMTL=h;
						if(productaddAPI.styleValue.selvalue.length > 0 ){
							productaddAPI.initDoneHtml();
							productaddAPI.initStyleHTML();	
					    }
						$(".colse").click(productaddAPI.delRow);
						$(".close1").click(productaddAPI.delColumn);
						$(".up").click(productaddAPI.up);
						$(".down").click(productaddAPI.down);
						productaddAPI.priceFlag = true;
					  } else {
					    //不显示开启规格
					    $("#trsc").hide();
					    $("#bg-style").addClass("hidden");
						$("#style-sucess").addClass("hidden");
					  }
		      	 }
			});
		}
	};
	
		
		