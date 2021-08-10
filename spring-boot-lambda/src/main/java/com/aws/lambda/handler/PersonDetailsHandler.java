package com.aws.lambda.handler;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.lambda.aws.ApiGatewayResponse;
import com.aws.lambda.aws.JsonApiGatewayCaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersonDetailsHandler implements RequestHandler<Map<String, String>, String> {

	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public String handleRequest(Map<String, String> inputDetails, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("CONTEXT: " + gson.toJson(context));
		final String ENDPOINT = inputDetails.get("INPUT_FOR_ENDPOINT");
		final String REQUESTBODY = inputDetails.get("REQUEST_BODY");
		final String RESOURCEPATH = inputDetails.get("RESOURCE_PATH");
		
		ApiGatewayResponse response = null;
		
		JsonApiGatewayCaller caller = null;
		try {
			caller = new JsonApiGatewayCaller(inputDetails.get("KEY"), inputDetails.get("SECRET_KEY"), null,
					inputDetails.get("REGION"), new URI(ENDPOINT));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		response = caller.execute(HttpMethodName.POST, RESOURCEPATH,
				new ByteArrayInputStream(REQUESTBODY.getBytes()));
		return response.getBody();
	}
}	
