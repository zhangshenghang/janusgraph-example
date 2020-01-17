package com.jast.graph.example.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tinkerpop.gremlin.process.traversal.Bindings;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.junit.Test;

/**
 *  边操作例子
 * @author jast
 * @date 2020年1月16日 下午4:55:42
 */
public class JanusGraphGremlinEdgeExample {

	GraphTraversalSource g = JanusGraphGremlinConnection.getInstance().getGraphTraversalSource();
	public static final String LABEL = "label";
	public static final String OUT_V = "outV";
	public static final String IN_V = "inV";
	public static final String REASON = "reason";



	@Test
	public void queryVertexValue() {
		// 根据ID查询顶点
		//		GraphTraversal<Vertex,Vertex> has = g.V().has("name","zhangsan");
		//		has.toList().forEach(x->System.out.println(x.id()));
		g.V(8304).drop().iterate();
		GraphTraversal<Vertex,Vertex> has = g.V().has("name","zhangsan");
		has.toList().forEach(x->System.out.println(x.id()));

	}

	@Test
	public void drop() {
		// 删除所有边或者具体边
		g.E().drop().iterate();
		g.V().drop().iterate();	
	}

	/**
	 * 创建边 
	 * @return void
	 */
	@Test
	public void addEdge() {


		Vertex v1 = g.addV().property("name", "Iverson").property("age", 36).next();
		Vertex v2 = g.addV().property("name", "Cluo").property("age", 35).next();
		Edge e1 = g.addE("friend").property("name", "relationship").from(v1).to(v2).next();
		System.out.println(e1.id());

		// 推荐采用这种方式
		Vertex v3 = g.addV().property("name", "Iverson").property("age", 37).next();
		Vertex v4 = g.addV().property("name", "Cluo").property("age", 34).next();

		// Use bindings to allow the Gremlin Server to cache traversals that
		// will be reused with different parameters. This minimizes the
		// number of scripts that need to be compiled and cached on the server.
		// https://tinkerpop.apache.org/docs/3.2.6/reference/#parameterized-scripts
		final Bindings b = Bindings.instance();
		Edge e2 = g.V(b.of(OUT_V, v3)).as("a").V(b.of(IN_V, v4)).addE(b.of(LABEL, "friends"))
				.property(REASON, b.of(REASON, "ball")).from("a").next();
		System.out.println(e2.id());
	}

	/**
	 *  查询指定边的两个顶点信息（1）  
	 * @return void
	 */
	@Test
	public void queryPath() {
		Set<Path> set = g.V(123120).bothE().path().toSet();
		set.forEach(
				x->{
					//这里x是path类型
					x.forEach(
							y->
							{
								if(y instanceof Vertex) {
									System.out.println("点:"+y);
								}else if(y instanceof Edge){
									System.out.println("边:" + y);
								}
							}
							);
					System.out.println("-----------------------------");
				});
	}
	/**
	 * 查询指定边的两个顶点信息（2）
	 * @return void
	 */
	@Test
	public void queryEdgeVertex() {
		GraphTraversal<Vertex,Edge> bothE = g.V(159856).inE();
		bothE.toList().forEach(edge->System.out.println("原id："+edge.inVertex()+" 目标id:"+edge.outVertex()+" 边："+edge.label()+" "+edge));
	}
	/**
	 * 查询边
	 * @return void
	 * @param g
	 */
	@Test
	public void queryEdge() {
		// 选择一个Vertex->选择出去的线outE->进入指定的Vertex inV
		// 选择一个Vertex->选择进来的线inE->选择出自哪个Vertex outV
		Vertex v1 = (Vertex) (g.V().has("name", "Iverson").as("a").outE().as("b").inV().has("age", 34).as("c")
				.select("a").next());
		System.out.println(v1.id());
		Edge e = (Edge) (g.V().has("name", "Iverson").as("a").outE().as("b").inV().has("age", 34).as("c").select("b")
				.next());
		System.out.println(e.id());
		Vertex v2 = (Vertex) (g.V().has("name", "Iverson").as("a").outE().as("b").inV().has("age", 34).as("c")
				.select("c").next());
		System.out.println(v2.id());
		// 选择ID为40980672的顶点所有进来的边的id，可能是一条也可能是多条
		System.out.println(g.V(40980672).inE().id().toList());
	}

	// 操作属性
	// 操作属性也是基于上述查询结果进行操作，返回值等与查询一样，唯一不一样的就是对查询结果做了修改
	public static void operateVertexEdgeProperty(GraphTraversalSource g) {

		// 根据顶点ID对单个顶点增加/修改属性，有则修改，无则增加
		Vertex v1 = g.V(57520).property("name", "kobe").next();
		System.out.println(v1.id());

		// 根据顶点ID对多个顶点增加/修改属性，有则修改，无则增加
		List<Vertex> list1 = g.V(57520, 53424).property("sex", "女").toList();
		list1.forEach((v) -> {
			System.out.println(v.id());
		});

		// 根据顶点ID(多个或者单个顶点)查看所有属性值
		List<Object> list2 = g.V(57520, 53424).values().toList();
		list2.forEach((v) -> {
			System.out.println(v);
		});

		// 根据顶点ID(多个或者单个顶点)查看具体key(单个或多个key)的属性值
		List<Object> list3 = g.V(57520, 53424).values("name").toList();
		list3.forEach((v) -> {
			System.out.println(v);
		});

		// 根据顶点属性(多个或者单个顶点)查看所有属性值
		List<Object> list4 = g.V().has("name", "kobe").values().toList();
		list4.forEach((v) -> {
			System.out.println(v);
		});

		// 根据顶点属性(多个或者单个顶点)查看具体key(单个或多个key)的属性值
		List<Object> list5 = g.V().has("name", "Iverson").values("name", "age").toList();
		list5.forEach((v) -> {
			System.out.println(v);
		});

		// 删除顶点属性
		g.V(53424).properties("name").drop().iterate();

		// 根据边ID增加/修改属性，有则修改，无则增加
		Edge e1 = g.E("hee-1580-2e51-18ds").property("name", "classmates").next();
		System.out.println(e1.id());

		// 查看边属性
		List<Map<Object, Object>> list6 = g.E("hee-1580-2e51-18ds").valueMap().toList();
		list6.forEach((map) -> {
			map.forEach((key, value) -> {
				System.out.println(key + "---" + value);
			});
		});

		// 删除所有边或者具体边
		g.E().drop().iterate();
	}
}
