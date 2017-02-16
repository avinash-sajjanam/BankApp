package com.utils;

public class JsonMessage {
	
	private String data;
	private String data2;
	private String data3;
	
	public JsonMessage(String data){
		super();
		this.data = data;
	}
	
	public JsonMessage(String data, String data2){
		super();
		this.data = data;
		this.data2 = data2;
	}
	
	public JsonMessage(String data, String data2, String data3){
		super();
		this.data = data;
		this.data2 = data2;
		this.data3 = data3;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}

}
