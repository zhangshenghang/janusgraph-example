package com.jast.graph.example.api;

import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.detached.DetachedPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jast.graph.example.entity.PathEntity;

/**
 * 
 * @author Anlw
 *
 * @date May 31, 2019
 */
public class RemoteScriptConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteScriptConnection.class);

	private static volatile Client client = null;

	private static volatile Cluster cluster = null;

	private static final Class<RemoteScriptConnection> CLASS_LOCK = RemoteScriptConnection.class;

	private static final String INVOKE_CLASS = "gremlin.remote.driver.clusterFile";

	private RemoteScriptConnection() {
	}

	// using the remote driver for schema
	public static Client getClient() throws Exception {
		LOGGER.info("open client...");
		synchronized (CLASS_LOCK) {
			if (client == null) {
				Configuration conf = new PropertiesConfiguration("config/client/remote-graph.properties");
				cluster = Cluster.open(conf.getString(INVOKE_CLASS));
				client = cluster.connect();
				return client;
			}
		}
		return client;
	}

	public static void closeClient() throws Exception {
		LOGGER.info("closing client");

		try {
			if (cluster != null) {
				// the cluster closes all of its clients
				cluster.close();
			}
		} finally {
			client = null;
			cluster = null;
		}
	}

	public static void main(String[] args) throws Exception {
		getClient();
		//TODO https://blog.csdn.net/javeme/article/details/82627396
		//repeat(bothE().otherV()).times(5).emit().simplePath().path().by(valueMap(true))
		long currentTimeMillis = System.currentTimeMillis();
		//		ResultSet submit = client.submit("g.V(82251856).repeat(bothE().otherV()).times(5).emit().simplePath().path().by(valueMap(true))");
//		ResultSet submit = client.submit("g.V(82251856).repeat(inE().otherV()).times(5).emit().path().dedup().dedup();");
		ResultSet submit = GraphQuery.queryPath(client, 82251856, 5);
		List<PathEntity> queryPath = GraphQuery.pathConvert(submit);
//		System.out.println(queryPath);
		queryPath.forEach(x->System.out.println(x.getPath()));
	
		//		ResultSet submit = client.submit("g.V(82251856).repeat(inE().otherV()).times(4).path().dedup()");
		
		//		ResultSet submit = client.submit("g.V(82251856).in().in().in().inE().path().dedup()");
		//		submit.forEach(x->System.out.println(x));
		//		 submit = client.submit("g.V(82251856).in().in().inE().path().dedup()");
		//		submit.forEach(x->System.out.println(x));
		//		 submit = client.submit("g.V(82251856).in().inE().path().dedup()");
		//		submit.forEach(x->System.out.println(x));
		//		 submit = client.submit("g.V(82251856).inE().path().dedup()");
		//		submit.forEach(x->System.out.println(x));
		System.out.println(System.currentTimeMillis()-currentTimeMillis);
		System.exit(0);
	}
}
