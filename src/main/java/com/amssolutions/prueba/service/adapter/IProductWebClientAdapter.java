package com.amssolutions.prueba.service.adapter;

import java.util.List;

import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceRequestBusinessException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceResponseBusinessException;

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
	 * @throws WebServiceRequestBusinessException
	 * @throws WebServiceResponseBusinessException
	 */
	List<String> getSimilarProducts(String productId) 
			throws ProductBusinessNotFoundException, WebServiceRequestBusinessException, WebServiceResponseBusinessException;
	
	/**
	 * Get product details by id.
	 * 
	 * @param productId Product id.
	 * @return Product details.
	 * @throws ProductBusinessNotFoundException
	 * @throws WebServiceResponseBusinessException
	 */
	ProductDetailBusiness getProductDetails(String productId) 
			throws ProductBusinessNotFoundException, WebServiceRequestBusinessException, WebServiceResponseBusinessException;
	
}
