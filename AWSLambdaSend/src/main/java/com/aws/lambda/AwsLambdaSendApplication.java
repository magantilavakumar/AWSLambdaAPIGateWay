package com.aws.lambda;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AwsLambdaSendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsLambdaSendApplication.class, args);
	}
	
	
	@Bean
	public Function<String,String> getValue(){
		
		return (m)->"You Out is here";
	}

}
