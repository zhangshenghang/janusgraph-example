package com.jast.graph.example;

import java.util.Date;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import com.jast.graph.example.api.JanusGraphGremlin;

public class Demo {
	static GraphTraversalSource g = JanusGraphGremlin.getInstance().getGraphTraversalSource();
	public static final String LABEL = "label";
	public static final String OUT_V = "outV";
	public static final String IN_V = "inV";
	public static final String REASON = "reason";


	public static void main(String[] args) {
		g.V().drop().iterate();
		g.E().drop().iterate();
		boolean hasNext = g.V().has("name","语文星光").hasNext();
		if(!hasNext) {
			addGraph();
		}
		Object id = g.V().has("name","语文星光").next().id();
		GraphTraversal<Vertex,Vertex> both = g.V(id).both();
		both.toList().forEach(x->System.out.println(g.V(x.id()).valueMap().next()));

		GraphTraversal<Vertex, Edge> bothE = g.V(id).bothE().as("e").select("e");
//		bothE.toList().forEach(x->System.err.println(g.E(x.id()).valueMap().next()));
//		bothE.toList().forEach(x->System.err.println(g.E(x.id())));
		bothE.toList().forEach(x->System.err.println(g.E(x.id())));
		//TODO 边的详细信息查看

		System.exit(1);
	}

	@SuppressWarnings("unused")
	public static void addGraph() {
		Vertex article1 = g.addV("article").property("name", "语文星光").property("uid", 6177764748l).property("city", "加里敦").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article2 = g.addV("article").property("name", "ling001972").property("uid", 6430029562l).property("city", "深圳").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article3 = g.addV("article").property("name", "山中野猫m").property("uid", 6051786224l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article4 = g.addV("article").property("name", "县红i能品面_eQ").property("uid", 7367894775l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article5 = g.addV("article").property("name", "日长k叫门伟_").property("uid", 7367894771l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article6 = g.addV("article").property("name", "用户6384977836").property("uid", 7361237622l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article7 = g.addV("article").property("name", "唱悲殇乄吕LQQ").property("uid", 6384977836l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article8 = g.addV("article").property("name", "我还是觉得我的名字不够洋气").property("uid", 3964098185l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article9 = g.addV("article").property("name", "不冫令").property("uid", 7005136440l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Vertex article10 = g.addV("article").property("name", "无敌风火轮").property("uid", 2664293327l).property("city", "未知").property("url", "https://weibo.com/6177764748/Io73xCtrF").next();
		Edge e1 = g.addE("forward").property("date", new Date()).from(article2).to(article1).next();
		Edge e2 = g.addE("forward").property("date",  new Date()).from(article3).to(article1).next();
		Edge e3 = g.addE("forward").property("date",  new Date()).from(article4).to(article2).next();
		Edge e4 = g.addE("forward").property("date",  new Date()).from(article5).to(article2).next();
		Edge e5 = g.addE("forward").property("date",  new Date()).from(article6).to(article2).next();
		Edge e6 = g.addE("forward").property("date",  new Date()).from(article7).to(article6).next();
		Edge e7 = g.addE("forward").property("date",  new Date()).from(article8).to(article5).next();
		Edge e8 = g.addE("forward").property("date",  new Date()).from(article9).to(article5).next();
		Edge e9 = g.addE("forward").property("date",  new Date()).from(article10).to(article9).next();
	}
}
