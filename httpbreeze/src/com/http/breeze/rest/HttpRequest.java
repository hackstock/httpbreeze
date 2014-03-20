package com.http.breeze.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpRequest {
	private final String baseUrl;
	private final HttpMethod method;
	private final String characterEncoding;
	private final Map<String,String> parameters;
	private final Map<String,String> headers;
	
	private HttpRequest(HttpRequestBuilder builder){
		this.baseUrl = builder.baseUrl;
		this.method = builder.method;
		this.parameters = builder.parameters;
		this.headers = builder.headers;
		this.characterEncoding = builder.characterEncoding;
	}
	
	public String getBaseUrl(){
		return this.baseUrl.trim();
	}
	
	public HttpMethod getMethod(){
		return this.method;
	}
	
	public String getCharacterEncoding(){
		return this.characterEncoding;
	}
	
	public Map<String,String> getParameters(){
		return this.parameters;
	}
	
	public Map<String,String> getHeaders(){
		return this.headers;
	}
	
	public String getEncodedParameters() throws UnsupportedEncodingException{
		String encodedParameters = "";
		if(!this.parameters.isEmpty()){
			Set<String> parameterKeys = this.parameters.keySet();
			boolean isFirstParameter = true;
			for(String key : parameterKeys){
				if(isFirstParameter){
					encodedParameters+= key + "=" + URLEncoder.encode(this.parameters.get(key), this.getCharacterEncoding());
					isFirstParameter = false;
				}else{
					encodedParameters+= "&" + key + "=" + URLEncoder.encode(this.parameters.get(key), this.getCharacterEncoding());
				}
			}
		}
		
		return encodedParameters.trim();
	}
	
	public String getUnEncodedParameters(){
		String parameters = "";
		if(!this.parameters.isEmpty()){
			Set<String> parameterKeys = this.parameters.keySet();
			boolean isFirstParameter = true;
			for(String key : parameterKeys){
				if(isFirstParameter){
					parameters+= key + "=" + this.parameters.get(key);
					isFirstParameter = false;
				}else{
					parameters+= "&" + key + "=" + this.parameters.get(key);
				}
			}
		}
		
		return parameters.trim();
	}
	
	public String getEncodedUrl() throws UnsupportedEncodingException{
		String encodedUrl = this.getBaseUrl();
		if(!this.parameters.isEmpty()){
			encodedUrl+="?";
			Set<String> parameterKeys = this.parameters.keySet();
			boolean isFirstParameter = true;
			for(String key : parameterKeys){
				if(isFirstParameter){
					encodedUrl+= key + "=" + URLEncoder.encode(this.parameters.get(key), this.getCharacterEncoding());
					isFirstParameter = false;
				}else{
					encodedUrl+= "&" + key + "=" + URLEncoder.encode(this.parameters.get(key), this.getCharacterEncoding());
				}
			}
		}
		return encodedUrl.trim();
	}
	
	public String getUnEncodedUrl(){
		String url = this.getBaseUrl();
		if(!this.parameters.isEmpty()){
			url+="?";
			Set<String> parameterKeys = this.parameters.keySet();
			boolean isFirstParameter = true;
			for(String key : parameterKeys){
				if(isFirstParameter){
					url+= key + "=" + this.parameters.get(key);
					isFirstParameter = false;
				}else{
					url+= "&" + key + "=" +this.parameters.get(key);
				}
			}
		}
		return url.trim();
	}
	
	public static class HttpRequestBuilder{
		private String baseUrl;
		private HttpMethod method;
		private Map<String,String> parameters;
		private final Map<String,String> headers;
		private String characterEncoding;
		
		public HttpRequestBuilder(){
			this.parameters = new HashMap<String, String>();
			this.headers = new HashMap<String,String>();
			this.characterEncoding = "UTF-8";
		}
		
		public HttpRequestBuilder setBaseUrl(String baseUrl){
			this.baseUrl = baseUrl;
			return this;
		}
		
		public HttpRequestBuilder setMethod(HttpMethod method){
			this.method = method;
			return this;
		}
		
		public HttpRequestBuilder addParameter(String headerKey,String headerValue){
			this.parameters.put(headerKey, headerValue);
			return this;
		}
		
		public HttpRequestBuilder addHeader(String headerKey,String headerValue){
			this.headers.put(headerKey, headerValue);
			return this;
		}
		
		public HttpRequestBuilder setCharacterEncoding(String encoding){
			this.characterEncoding = encoding;
			return this;
		}
		
		public HttpRequest build(){
			return new HttpRequest(this);
		}
	}

}
