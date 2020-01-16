package com.jast.graph.example.entity;

import java.util.List;

public class Result {

	private List<PathEntity> lp ;

	public List<PathEntity> getLp() {
		return lp;
	}

	public void setLp(List<PathEntity> lp) {
		this.lp = lp;
	}

	@Override
	public String toString() {
		return "Result [lp=" + lp + "]";
	}
	
	
}
