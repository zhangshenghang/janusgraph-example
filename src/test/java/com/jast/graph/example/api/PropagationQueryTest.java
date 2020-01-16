package com.jast.graph.example.api;

import org.apache.tinkerpop.gremlin.driver.Client;

import com.jast.graph.example.entity.ResultAll;

/**
 * 传播路径查询例子
 * @author jast
 * @date 2020年1月16日 下午4:56:09
 */
public class PropagationQueryTest {

	static Client client = RemoteScriptConnection.getClient();	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		ResultAll query = PropagationQuery.query(client, 82251856, 5);
		System.out.println("所有顶点信息:"+query.getVertexData());
		System.out.println("所有路径："+query.getLp());
		System.out.println("所有涉及顶点"+query.getIds());
		System.exit(0);
		
	}
}
