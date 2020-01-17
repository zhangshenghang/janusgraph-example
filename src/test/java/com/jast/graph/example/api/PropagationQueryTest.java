package com.jast.graph.example.api;

import java.util.Date;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import com.jast.graph.example.entity.ResultAll;

/**
 * 传播路径查询例子
 * @author jast
 * @date 2020年1月16日 下午4:56:09
 */
public class PropagationQueryTest {
	static GraphTraversalSource g = JanusGraphGremlinConnection.getInstance().getGraphTraversalSource();
	static Client client = RemoteScriptConnection.getClient();	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		ResultAll query = PropagationQuery.query(client, 82251856, 5);
		System.out.println("所有顶点信息:"+query.getVertexData());
		System.out.println("所有路径："+query.getLp());
		System.out.println("所有涉及顶点"+query.getIds());
		System.exit(0);
		
	}
	/**
	 *	添加测试数据 
	 * @return void
	 */
	@SuppressWarnings("unused")
	public static void addTestData() {
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
