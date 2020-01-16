package com.jast.graph.example.api;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedPath;

public class JanusGraphGremlinVertex {


	/**
	 * 批量查询顶点属性值 
	 * @return List<Map<Object,Object>>
	 * @param client
	 * @param ids
	 * @return 返回顶点属性值列表
	 */
	public static List<Map<Object,Object>> getVertexData(Client client , Set<String> ids) {
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		for(String id:ids) {
			Map<Object, Object> vertexData = getVertexData(client,id);
			list.add(vertexData);
		}
		return list;
	}

	/**
	 * 根据顶点id返回所有顶点id的属性值 
	 * @return Map<Object,Object>
	 * @param client
	 * @param id
	 * @return 返回顶点属性值
	 */
	public static Map<Object,Object> getVertexData(Client client , String id) {
		Map<Object,Object> map = new HashMap<Object, Object>();
		ResultSet submit = client.submit("g.V("+id+").valueMap().next()");
		submit.forEach(result ->{
			@SuppressWarnings("unchecked")
			SimpleEntry<Object,Object> entry = (SimpleEntry<Object,Object>) result.get(result.getObject().getClass());
			map.put(entry.getKey(), entry.getValue());		
		});
		map.put("vid", id);
		return map;
	}

	/**
	 *  批量查询顶点属性值 
	 * @TODO  批量查询顶点属性值 
	 * @return Map<String,String>
	 * @param g
	 * @param ids
	 * @return 返回顶点属性值列表
	 */
	public static Map<String,String> getVertexData(GraphTraversalSource g, Set<String> ids) {
		return null;
	}

	/**
	 * 根据顶点id返回所有顶点id的属性值 
	 * @TODO  根据顶点id返回所有顶点id的属性值 
	 * @return Map<String,String>
	 * @param g
	 * @param id
	 * @return 返回顶点属性值
	 */
	public static Map<String,String> getVertexData(GraphTraversalSource g, String id) {
		return null;
	}
}
