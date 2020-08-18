var LigerTreeUtil = {
	/**
	 * 
	 * @param tree
	 * @param target
	 * @param data
	 */
	addNodes: function(tree, node, data) {
		if(tree && node && data) {
			if(data instanceof Array) {
				tree.append(node.target, data);
			} else {
				tree.append(node.target, [data]);
			}
			//如果收缩，则展示
			$(node.target).children().children(".l-expandable-close").click();
		}
	},
  updateNodes:function(tree, node, text_name)
    { 
        	tree.update(node.target, { text: text_name});
    },
	removeNodes: function(tree, id) {
		if(tree && id) {
			var data = tree.getDataByID(id);
			if(data) tree.remove(data);
		}
	},
	
	dataFormat: function(list, idField, textField) {
		var nodes = [];
		for(var i = 0; i < list.length; i++) {
			var node = {};
			node["id"] = list[i][idField];
			node["text"] = list[i][textField];
			nodes.push(node);
		}
		return nodes;
	},
	dataFormatNew: function(list, idField, textField,codeField) {
		var nodes = [];
		for(var i = 0; i < list.length; i++) {
			var node = {};
			node["id"] = list[i][idField];
			node["text"] = list[i][textField];
			node["code"] = list[i][codeField];
			nodes.push(node);
		}
		return nodes;
	}
};