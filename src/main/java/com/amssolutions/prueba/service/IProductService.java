package com.amssolutions.prueba.service;

import com.amssolutions.prueba.api.dto.SimilarProducts;
import com.amssolutions.prueba.exception.ProductNotFoundException;
import com.amssolutions.prueba.exception.WebServiceException;

/**
 * Product Service.
 * 
 * @author obarcia
 *
 */
public interface IProductService {

	/**
	 * Get similar products by id.
	 * 
	 * @param productId Product id.
	 * @return List of similar products.
	 * @throws ProductNotFoundException
	 * @throws WebServiceException
	 */
	SimilarProducts getSimilarProducts(String productId) 
			throws ProductNotFoundException, WebServiceException;
	
}
