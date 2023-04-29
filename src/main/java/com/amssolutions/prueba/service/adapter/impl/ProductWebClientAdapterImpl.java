package com.amssolutions.prueba.service.adapter.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.amssolutions.prueba.service.adapter.IProductWebClientAdapter;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceBusinessException;
import com.amssolutions.prueba.webclient.ProductApiWebClient;

import lombok.AllArgsConstructor;

/**
 * Product adapter implementation.
 * 
 * @author obarcia
 */
@Component
@AllArgsConstructor
public class ProductWebClientAdapterImpl implements IProductWebClientAdapter {
	
	private final ProductApiWebClient productApiWebClient;

	@Override
	@Cacheable("similaraProductsById")
	public List<String> getSimilarProducts(String productId) 
			throws ProductBusinessNotFoundException, WebServiceBusinessException {
		
		return productApiWebClient.getSimilarProducts(productId);
	}
	
	@Override
	@Cacheable("productDetailsById")
	public ProductDetailBusiness getProductDetails(String productId) 
			throws ProductBusinessNotFoundException, WebServiceBusinessException {
		return productApiWebClient.getProductDetail(productId);
	}
	
}
