package com.jast.graph.example.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedPath;

import com.jast.graph.example.entity.PathEntity;

public class GraphQuery {



	/**
	 * 查询指定顶点，times层所有入边的数据，与 pathConvert 共用
	 * @return ResultSet 查询结果集合
	 * @param client
	 * @param id
	 * @param times
	 * https://blog.csdn.net/javeme/article/details/82760106
	 */
	public static ResultSet queryPath (Client client,Object id ,int times ) {
		ResultSet submit = client.submit("g.V("+id+").repeat(inE().otherV()).times("+times+").emit().path().dedup();");
		return submit;
	}
	/**
	 * 指定顶点查询n层内所有路径
	 * @return 返回整合后路径
	 * @param rs 传入所有路径集合
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static List<PathEntity> pathConvert(ResultSet rs) throws NoSuchMethodException, SecurityException {
		List<PathEntity> list = new ArrayList<PathEntity>();
		rs.forEach(
				x->{
					StringBuilder sb = new StringBuilder();
					//转换为 DetachedPath 类型
					DetachedPath dePath = (DetachedPath) x.get(x.getObject().getClass());
					//获取path路径
					Path path = dePath.get();
					path.forEach(z->{
						if(z instanceof Vertex) {
							//							System.out.println(z);
						}else if(z instanceof Edge) {
							Edge edge = (Edge) z;
							int indexOf = sb.indexOf(edge.inVertex().id().toString());
							if(indexOf==-1) 
							{
								indexOf = 0 ;
								sb.insert(indexOf, edge.inVertex().id().toString() + "->"  );
							}
							sb.insert(indexOf, edge.outVertex().id().toString() + "->" );
							
						}
					});
					String result = sb.substring(0,sb.length()-2);
					PathEntity p = new PathEntity();
					p.setPath(result);
					p.setInfo(path.toString());
					list.add(p);
				}
				);
		return list;
	}
}
