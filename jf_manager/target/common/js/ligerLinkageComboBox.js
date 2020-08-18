var iiiii = 0;
var LinkageComboBox = {
	//
	init : function(list) {
		//alert(333);
		var d = LinkageComboBox.buildLinkageComboBox(list, null);
	},
	
	dataFilter : function(data, pid) {
		if(!data) return [];
		var result = [];
		for(var i = 0; i < data.length; i++) {
			if(data[i].pid == pid) result.push(data[i]);
		}
		return result;
	},
	
	isExists : function(id, list) {
		for(var i = 0; i < list.length; i++) {
			if(list[i].id == id) return true;
		}
		return false;
	},
	
	bindData : function(node, pid) {
		var data = node.data;
		if(!data) return;
		liger.get(node.id).unbind("selected");
		if(typeof data == "string") {//如果是字符串就当成URL地址
			//alert(pid);
			$.ajax({
				type: "POST",
				url: data,
				data: "id=" + pid,
				dataType: 'json',
				//async: false,
				success: function(json) {
					//alert(JSON.stringify(json));
					liger.get(node.id).setData(LinkageComboBox.dataFilter(json, pid));
					
					var childs = node.childs;
					if(childs && childs.length > 0) {
						for(var j = 0; j < childs.length; j++) {
							liger.get(childs[j].id).setData(null);
						}
						
						liger.get(node.id).bind("selected", function(val) {
							for(var j = 0; j < childs.length; j++) {
								LinkageComboBox.bindData(childs[j], val);
							}
						});
					}
					
					var value = node.value;
					if(value && value != "" && LinkageComboBox.isExists(value, json)) {
						//alert(LinkageComboBox.isExists(value, json));
						liger.get(node.id).selectValue(value);
						//liger.get(node.id).trigger('selected', value); 
					}
				},
				error: function(msg) {
					alert("接口调用失败");
				}
			});
		} else if(typeof data == "object" && data.constructor == Array) {
			liger.get(node.id).setData(LinkageComboBox.dataFilter(data, pid));
			var value = node.value;
			if(value && value != "") {
				liger.get(node.id).selectValue(value);
				//liger.get(node.id).trigger('selected', value); 
			}
		}
	},
	
	buildLinkageComboBox : function(list, pid) {
		if(!list || !(list instanceof Array)) return false;
		var nodes = [];
		for(var i = 0; i < list.length; i++) {
			if(list[i].pid == pid) {
				var node = list[i];
				var childs = LinkageComboBox.buildLinkageComboBox(list, node.id);
				if(childs && childs.length > 0) node.childs = childs;
				node.value = $("#" + node.id).val();
				nodes.push(node);
				$("#" + node.id).ligerComboBox({ data: null, isMultiSelect: false, isShowCheckBox: false });
				/*
				if(childs && childs.length > 0) {
					//如果有下级则触发下级数据变更
					liger.get(node.id).bind("selected", function(val) {
						for(var j = 0; j < childs.length; j++) {
							LinkageComboBox.bindData(childs[j], val);
						}
					});
				}
				*/
				
				if(!pid) LinkageComboBox.bindData(node, 0);  //如果是根节点则立即绑定数据
			}
		}
		return nodes;
	}
};