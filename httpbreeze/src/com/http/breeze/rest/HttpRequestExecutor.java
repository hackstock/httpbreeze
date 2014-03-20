package com.http.breeze.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface HttpRequestExecutor {
 
	abstract String executeAsGetRequest() throws MalformedURLException, UnsupportedEncodingException,IOException;
	abstract String executeAsPostRequest() throws MalformedURLException, UnsupportedEncodingException,IOException;
 
}
