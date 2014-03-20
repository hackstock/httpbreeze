package com.http.breeze.rest;

public enum HttpMethod {
	GET("GET"), POST("POST");

	private String description;

	private HttpMethod(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
