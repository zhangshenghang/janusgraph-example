package com.jast.graph.example.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultAll extends Result{

	private List<Map<Object, Object>> vertexData;
	//每次查询出来的所有顶点集合
	private Set<String> ids ;

	public Set<String> getIds() {
		return ids;
	}

	public void setIds(Set<String> ids) {
		this.ids = ids;
	}

	public List<Map<Object, Object>> getVertexData() {
		return vertexData;
	}

	public void setVertexData(List<Map<Object, Object>> vertexData) {
		this.vertexData = vertexData;
	}

	@Override
	public String toString() {
		return "ResultAll [vertexData=" + vertexData + ", ids=" + ids + "]";
	}
	
	
	
	
}
