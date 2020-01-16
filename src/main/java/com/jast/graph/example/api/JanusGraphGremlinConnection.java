package com.jast.graph.example.api;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;


public class JanusGraphGremlinConnection {

	private static volatile GraphTraversalSource g = null;

	private static final Class<JanusGraphGremlinConnection> CLASS_LOCK = JanusGraphGremlinConnection.class;

	private String config = "config/client/remote-graph.properties";
	
	private JanusGraphGremlinConnection() {
	}
	
	private JanusGraphGremlinConnection(String config) {
		this.config=config;
	}

	public static JanusGraphGremlinConnection getInstance(String config) {
		return new JanusGraphGremlinConnection(config);
	}
	
	public static JanusGraphGremlinConnection getInstance() {
		return new JanusGraphGremlinConnection();
	}
	
	public GraphTraversalSource getGraphTraversalSource() {
		synchronized (CLASS_LOCK) {
			if (g == null) {
				try {
				Configuration conf = new PropertiesConfiguration(config);
				g = AnonymousTraversalSource.traversal().withRemote(conf);
				} catch (ConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return g;
			}
		}
		return g;
	}

	public static void closeGraphTraversalSource() throws Exception {
		try {
			if (g != null) {
				// this closes the remote, no need to close the empty graph
				g.close();
			}
		} finally {
			g = null;
		}
	}
}
