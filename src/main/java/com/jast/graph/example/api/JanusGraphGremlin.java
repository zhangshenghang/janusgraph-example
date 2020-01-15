package com.jast.graph.example.api;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;


public class JanusGraphGremlin {

	private static volatile GraphTraversalSource g = null;

	private static final Class<JanusGraphGremlin> CLASS_LOCK = JanusGraphGremlin.class;

	private String config = "config/client/remote-graph.properties";
	
	private JanusGraphGremlin() {
	}
	
	private JanusGraphGremlin(String config) {
		this.config=config;
	}

	public static JanusGraphGremlin getInstance(String config) {
		return new JanusGraphGremlin(config);
	}
	
	public static JanusGraphGremlin getInstance() {
		return new JanusGraphGremlin();
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
