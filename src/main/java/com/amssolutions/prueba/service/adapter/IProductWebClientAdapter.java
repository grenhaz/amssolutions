package com.amssolutions.prueba.service.adapter;

import java.util.List;

import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceBusinessException;

/**
 * Product adapter.
 * 
 * @author obarcia
 */
public interface IProductWebClientAdapter {

	/**
	 * Get similar products id's by the related product id.
	 * 
	 * @param productId Product id.
	 * @return List of id's.
	 * @throws ProductBusinessNotFoundException
	 * @throws WebServiceBusinessException
	 */
	List<String> getSimilarProducts(String productId) 
			throws ProductBusinessNotFoundException, WebServiceBusinessException;
	
	/**
	 * Get product details by id.
	 * 
	 * @param productId Product id.
	 * @return Product details.
	 * @throws ProductBusinessNotFoundException
	 * @throws WebServiceBusinessException
	 */
	ProductDetailBusiness getProductDetails(String productId) 
			throws ProductBusinessNotFoundException, WebServiceBusinessException;
	
}
