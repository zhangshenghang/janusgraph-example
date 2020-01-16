package com.jast.graph.example.api;

import java.util.List;
import java.util.Map;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.ResultSet;

import com.jast.graph.example.entity.ResultAll;

/**
 * 传播分析查询
 * @author jast
 * @date 2020年1月16日 下午4:44:35
 */
public class PropagationQuery extends GraphQuery{
	
	
	
	/**
	 * 指定顶点id查询相关数据 
	 * @return ResultAll
	 * @param client 连接
	 * @param id 顶点id
	 * @param times 查询层级
	 * @return 路径以及顶点信息
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static ResultAll query(Client client,Object id ,int times ) throws NoSuchMethodException, SecurityException {
		ResultSet submit = GraphQuery.queryPath(client, id, times);
		ResultAll pathConvert = pathConvert(submit);
		List<Map<Object, Object>> vertexData = JanusGraphGremlinVertex.getVertexData(client, pathConvert.getIds());
		pathConvert.setVertexData(vertexData);
		return pathConvert;
	}

}
