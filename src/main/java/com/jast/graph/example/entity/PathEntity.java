package com.jast.graph.example.entity;

	
public class PathEntity {
	private String inVID; //入顶点
	private String EID; //边id
	private String outVID; //出顶点
	private String path; // 路径
	private String info; // path.toString
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
		return "path = " + path + "\n";
	}
	

}
