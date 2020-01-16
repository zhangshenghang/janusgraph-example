package com.jast.graph.example.entity;

	
public class PathEntity {
	private String inVID;
	private String EID;
	private String outVID;
	private String path;
	private String info;
	public String getInVID() {
		return inVID;
	}
	public void setInVID(String inVID) {
		this.inVID = inVID;
	}
	public String getEID() {
		return EID;
	}
	public void setEID(String eID) {
		EID = eID;
	}
	public String getOutVID() {
		return outVID;
	}
	public void setOutVID(String outVID) {
		this.outVID = outVID;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "path = " + path;
	}
	

}
