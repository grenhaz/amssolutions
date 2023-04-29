package com.amssolutions.prueba.service.mapper;

import com.amssolutions.prueba.api.dto.ProductDetail;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;

/**
 * Product mapper.
 * 
 * @author obarcia
 */
public interface IProductDetailMapper {

	/**
	 * Map product details from business.
	 * 
	 * @param source Business product.
	 * @return Product Dto.
	 */
	ProductDetail map(ProductDetailBusiness source);
	
}
