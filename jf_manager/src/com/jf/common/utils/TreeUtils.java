package com.jf.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeUtils {
	public static List<Map<String, Object>> buildTree(
			List<Map<String, Object>> list, String pid) {

		List<Map<String, Object>> _list = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> _map : list) {
			try {
				String _pid = _map.get("pid").toString();
				if (_pid.equals(pid)) {
					List<Map<String, Object>> childs = buildTree(list, _map
							.get("id").toString());
					if (childs != null && childs.size() > 0)
						_map.put("children", childs);
					_list.add(_map);
				}
			} catch (Exception e) {
				continue;
			}
		}

		return _list;
	}

}
