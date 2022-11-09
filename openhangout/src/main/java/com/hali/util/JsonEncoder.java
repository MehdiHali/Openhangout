package com.hali.util;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

import org.codehaus.jackson.map.ObjectMapper;

import com.hali.openhangout.message.Message;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;


public class JsonEncoder implements Encoder.Text<Message>{

	  @Override
	  public void init(EndpointConfig config) {
	    //for custom initialization logic (details omitted)
	  }
	  @Override
	  public String encode(Message msg) throws EncodeException {
	    //using the JSON processing API (JSR 353)
	    return Json.createObjectBuilder()
	    .add("author", msg.getAuthor())
	    .add("content", msg.getContent())
	    .add("timestamp", msg.getTimestamp())
	    .build()
	    .toString();
	  }
	  @Override
	  public void destroy() {
	    //close resources (details omitted)
	  }
}