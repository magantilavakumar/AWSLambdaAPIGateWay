package com.aws.lambda.config;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.http.HttpMethodName;
import com.aws.lambda.aws.ApiGatewayResponse;
import com.aws.lambda.aws.JsonApiGatewayCaller;

@Configuration
public class PersonDetailsConfig {
	

	
	
	static final String AWS_IAM_ACCESS_KEY = "AKIAURSKXB3SWAKALGPF";
    static final String AWS_IAM_SECRET_ACCESS_KEY = "4R9mpzY/0f2z6eFk5won99kJlVVVzAGAUNHMuOj8";
    static final String AWS_REGION = "u-east-1"; //for example "eu-west-1"
    static final String AWS_API_GATEWAY_ENPOINT = "https://nbic0gtqx4.execute-api.us-east-1.amazonaws.com/Dev"; //for example https://234n34k5678k.execute-api.eu-west-1.amazonaws.com/TEST
	
    static final String exampleJsonRequest = "\"\"";
    
	@Bean
	public Function<String,String> getPersonDetails() {	
		ApiGatewayResponse response = null;

		 JsonApiGatewayCaller caller = null;
		try {
			caller = new JsonApiGatewayCaller(
			         AWS_IAM_ACCESS_KEY,
			         AWS_IAM_SECRET_ACCESS_KEY,
			         null,
			         AWS_REGION,
			         new URI(AWS_API_GATEWAY_ENPOINT)
			 );
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 response = caller.execute(HttpMethodName.POST, "/awstestingresource", new ByteArrayInputStream(exampleJsonRequest.getBytes()));

		 String output = response.getBody();
		
		return (n)->output;
	}
}
