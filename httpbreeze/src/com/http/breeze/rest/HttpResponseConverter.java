package com.http.breeze.rest;

public interface HttpResponseConverter <T>{
  public T convertResponse(String httpResponse);
}
