package com.http.breeze.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import android.util.Log;

public class RawHttpRequestExecutor extends HttpRequestExecutorTemplate{
	private static final String TAG = RawHttpRequestExecutor.class.getName();

	protected RawHttpRequestExecutor(HttpRequest httpRequest) {
		super(httpRequest);
	}
	
	public static RawHttpRequestExecutor newInstance(HttpRequest httpRequest){
		return new RawHttpRequestExecutor(httpRequest);
	}

	@Override
	public Object executeAsGetRequest() throws MalformedURLException, UnsupportedEncodingException,IOException {
		String encodedUrl = this.httpRequest.getEncodedUrl();
		URL requestUrl = new URL(encodedUrl);
		Log.d(TAG, "Making a GET request to : " + encodedUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
		httpURLConnection.setRequestMethod(HttpMethod.GET.getDescription());
		
		Set<String> requestHeadersKeys = this.httpRequest.getHeaders().keySet();
		
		for(String headerKey : requestHeadersKeys){
			httpURLConnection.addRequestProperty(headerKey, this.httpRequest.getHeaders().get(headerKey));
		}
		
		BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		if(responseReader != null){
			StringBuilder httpResponse = new StringBuilder();
			String readLine = null;
			while((readLine = responseReader.readLine()) != null){
				httpResponse.append(readLine);
			}
			Log.d(TAG, "Response from GET request to : " + encodedUrl + " is : " + httpResponse.toString());
			return httpResponse.toString();
		}
		return null;
	}

	@Override
	public Object executeAsPostRequest() throws MalformedURLException, UnsupportedEncodingException,IOException{
		URL requestUrl = new URL(this.httpRequest.getBaseUrl());
		Log.d(TAG, "Making a POST request to : " + this.httpRequest.getBaseUrl());
		HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
		httpURLConnection.setRequestMethod(HttpMethod.POST.getDescription());
		
		
		Set<String> requestHeaders = this.httpRequest.getHeaders().keySet();
		
		for(String headerKey : requestHeaders){
			httpURLConnection.addRequestProperty(headerKey, this.httpRequest.getHeaders().get(headerKey));
		}
		
		String requestParameters = this.httpRequest.getEncodedParameters();
		Log.d(TAG, "Request parameters : " + requestParameters);
		
		httpURLConnection.setDoOutput(true);
		DataOutputStream writer = new DataOutputStream(httpURLConnection.getOutputStream());
		writer.writeBytes(requestParameters);
		writer.flush();
		writer.close();
		
		BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		if(responseReader != null){
			StringBuilder httpResponse = new StringBuilder();
			String readLine = null;
			while((readLine = responseReader.readLine()) != null){
				httpResponse.append(readLine);
			}
			Log.d(TAG, "Response from POST request to : " + this.httpRequest.getEncodedUrl() + " is : " + httpResponse.toString());
			return httpResponse.toString();
		}
		return null;
	}

}
