package com.http.breeze.rest;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonResponseConverter implements HttpResponseConverter<JSONObject>{
	
	private JsonResponseConverter(){
		
	}

	@Override
	public JSONObject convertResponse(String httpResponse) {
		try {
			return new JSONObject(httpResponse);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static JsonResponseConverter newInstance(){
		return new JsonResponseConverter();
	}

}
