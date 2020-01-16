package com.jast.graph.example.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.junit.Test;


public class JanusGraphGremlinVertexExample {

	GraphTraversalSource g = JanusGraphGremlinConnection.getInstance().getGraphTraversalSource();

	/**
	 * 删除顶点 
	 * @return void
	 */
	@Test
	public void dropVertex() {
		g.V(8304).drop().iterate();
	}


	/**
	 * 创建顶点 
	 * @return void
	 */
	@Test
	public  void addVertex() {
		// 创建无标签的顶点，默认标签vertex
		Vertex v1 = g.addV().next();
		System.out.println(v1.id());

		// 创建有标签的顶点，标签一旦创建，不可更改
		Vertex v2 = g.addV("student").next();
		System.out.println(v2.id());

		// 创建无标签单属性顶点
		Vertex v3 = g.addV().property("name", "kobe").next();
		System.out.println(v3.id());

		// 创建无标签多属性顶点
		Vertex v4 = g.addV().property("name", "Iverson").property("age", 37).next();
		System.out.println(v4.id());

		// 创建有标签单属性顶点
		Vertex v5 = g.addV("teacher").property("name", "zhangsan").next();
		System.out.println(v5.id());

		// 创建有标签多属性顶点
		Vertex v6 = g.addV("docter").property("name", "lisi").property("age", 14).next();
		System.out.println(v6.id());


		System.out.println(g.V().label().toList());
		System.out.println(g.V().valueMap().toList());
	}

	/**
	 * vertex 查询相关操作
	 * @return void
	 */
	@Test
	public void queryVertex() {
		// 基本查询
		// 查询所有顶点
		List<Vertex> list1 = g.V().toList();
		list1.forEach((v) -> {
			System.out.println(v.id());
		});

		// 根据ID查询顶点
		List<Vertex> list2 = g.V("8224").toList();
		list2.forEach((v) -> {
			System.out.println(v.id());
		});
		
		// 只查看属性值
		GraphTraversal<Vertex, Object> values = g.V("4336").values();
		System.out.println(values.toList());


		// 根据标签查询顶点
		List<Vertex> list3 = g.V().hasLabel("teacher").toList();
		list3.forEach((v) -> {
			System.out.println(v.id());
		});

		// 根据单属性查看顶点
		List<Vertex> list4 = g.V().has("name", "zhangsan").toList();
		list4.forEach((v) -> {
			System.out.println(v.id());
		});

		// 根据多属性查看顶点
		List<Vertex> list5 = g.V().has("name", "Iverson").has("age", 37).toList();
		list5.forEach((v) -> {
			System.out.println(v.id());
		});

		// 根据属性key的不包含查询，属性中key不包含name的所有顶点
		List<Vertex> list6 = g.V().hasNot("name").toList();
		list6.forEach((v) -> {
			System.out.println(v.id());
		});

		// 根据属性key的不包含查询，属性中key不包含name的所有顶点
		List<Vertex> list7 = g.V().has("name").toList();
		list7.forEach((v) -> {
			System.out.println(v.id());
		});

		// 符合查询，包括一些具体的Label/Key等的查询，其实就是根据上述基本查询或者基于fluent
		// API查询出的多个或者一个Vertex，然后在基于该Vertex查询其具体的信息
		// 根据单个ID查看顶点的所有Key-Value
		if(g.V(40968384).hasNext()) {
			Map<Object, Object> map1 = g.V(40968384).valueMap().next();
			map1.forEach((key, value) -> {
				System.out.println(key + "---" + value);
			});
		}

		// 根据多个ID查看顶点的所有Key-Value
		List<Map<Object, Object>> list8 = g.V(40968384, 8224).valueMap().toList();
		list8.forEach((map) -> {
			map.forEach((key, value) -> {
				System.out.println(key + "---" + value);
			});
		});

		// 根据属性(Key-Value)查看顶点的标签
		List<String> list9 = g.V().has("name", "zhangsan").label().toList();
		list9.forEach((v) -> {
			System.out.println(v);
		});

		// 根据属性(Key-Value)查看顶点的Key-Value
		List<Map<Object, Object>> list10 = g.V().has("name", "lisi").valueMap().toList();
		list10.forEach((map) -> {
			map.forEach((key, value) -> {
				System.out.println(key + "---" + value);
			});
		});

		// 查看所有顶点的标签，toSet为去重，如果不去重请使用toList
		Set<String> list11 = g.V().label().toSet();
		list11.forEach((v) -> {
			System.out.println(v);
		});

		// 根据标签和属性一起查
		List<Vertex> list12 = g.V().hasLabel("teacher").has("name", "zhangsan").toList();
		list12.forEach((v) -> {
			System.out.println(v.id());
		});

		// 根据ID查询顶点
		Vertex v = g.V(4336).next();
		System.out.println(v.label());
	}

}
