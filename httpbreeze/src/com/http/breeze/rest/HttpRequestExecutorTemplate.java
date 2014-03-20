package com.http.breeze.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public abstract class HttpRequestExecutorTemplate implements HttpRequestExecutor {
	protected HttpRequest httpRequest;

	protected HttpRequestExecutorTemplate(HttpRequest httpRequest) {
		super();
		this.httpRequest = httpRequest;
	}
	
	public final Object makeRequest() throws MalformedURLException, UnsupportedEncodingException,IOException{
		Object response = null;
		switch(this.httpRequest.getMethod()){
		case GET:
			response = executeAsGetRequest();
			break;
		case POST:
			response = executeAsPostRequest();
			break;
		}
		
		return response;
	}

}
