package com.amssolutions.prueba.service.adapter.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.amssolutions.prueba.service.adapter.IProductWebClientAdapter;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceRequestBusinessException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceResponseBusinessException;
import com.amssolutions.prueba.service.webclient.ProductApiWebClient;

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
			throws ProductBusinessNotFoundException, WebServiceRequestBusinessException, WebServiceResponseBusinessException {
		return productApiWebClient.getSimilarProducts(productId);
	}
	
	@Override
	@Cacheable("productDetailsById")
	public ProductDetailBusiness getProductDetails(String productId) 
			throws ProductBusinessNotFoundException, WebServiceRequestBusinessException, WebServiceResponseBusinessException {
		return productApiWebClient.getProductDetail(productId);
	}
	
}
