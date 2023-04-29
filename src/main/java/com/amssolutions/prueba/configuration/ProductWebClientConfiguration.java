package com.amssolutions.prueba.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.amssolutions.prueba.webclient.ProductApiWebClient;

/**
 * External web service configuration.
 * 
 * @author obarcia
 */
@Configuration
public class ProductWebClientConfiguration {
	
	@Value("${product.api.endpoint:}")
	private String productEndpoint;

	@Bean
	public ProductApiWebClient productApiWebClient() {
		return new ProductApiWebClient(WebClient.builder()
		        .baseUrl(productEndpoint)
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		        .build());
	}
	
}
