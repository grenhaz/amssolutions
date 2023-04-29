package com.amssolutions.prueba.webclient;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceBusinessException;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * WebClient component for products external api.
 * 
 * @author obarcia
 */
@AllArgsConstructor
public class ProductApiWebClient {
	
	private static final String ENDPOINT_PRODUCT_SIMILAR = "/product/{productId}/similarids";
	
	private static final String ENDPOINT_PRODUCT_DETAILS = "/product/{productId}";
	
	private final WebClient productWebClient;
	
	/**
	 * Get product id's by the related id from web service.
	 * 
	 * @param productId Product id.
	 * @return List of id's.
	 * @throws ProductBusinessNotFoundException
	 * @throws WebServiceBusinessException
	 */
	public List<String> getSimilarProducts(String productId) 
			throws ProductBusinessNotFoundException, WebServiceBusinessException {
		try {
			return Arrays.asList(productWebClient.get()
					.uri(ENDPOINT_PRODUCT_SIMILAR, productId)
				    .retrieve()
				    .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.error(ProductBusinessNotFoundException::new))
				    .onStatus(HttpStatusCode::isError, clientResponse -> Mono.error(WebServiceBusinessException::new))
				    .bodyToMono(String[].class)
				    .block());
		} catch (WebClientRequestException ex) {
			throw new WebServiceBusinessException(ex);
		}
	}
	
	/**
	 * Get product details by id from web service.
	 * 
	 * @param productId Product id.
	 * @return Product details.
	 * @throws ProductBusinessNotFoundException
	 * @throws WebServiceBusinessException
	 */
	public ProductDetailBusiness getProductDetail(String productId) 
			throws ProductBusinessNotFoundException, WebServiceBusinessException {
		try {
			return productWebClient.get()
					.uri(ENDPOINT_PRODUCT_DETAILS, productId)
					.retrieve()
				    .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.error(ProductBusinessNotFoundException::new))
				    .onStatus(HttpStatusCode::isError, clientResponse -> Mono.error(WebServiceBusinessException::new))
					.bodyToMono(ProductDetailBusiness.class)
					.block();
		} catch (WebClientRequestException ex) {
			throw new WebServiceBusinessException(ex);
		}
	}
	
}
