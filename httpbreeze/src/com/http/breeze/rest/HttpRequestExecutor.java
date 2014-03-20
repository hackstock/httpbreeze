package com.http.breeze.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface HttpRequestExecutor {
 
	public Object executeAsGetRequest() throws MalformedURLException, UnsupportedEncodingException,IOException;
	public Object executeAsPostRequest() throws MalformedURLException, UnsupportedEncodingException,IOException;
 
}
