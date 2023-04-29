package com.amssolutions.prueba.service.mapper.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.amssolutions.prueba.api.dto.ProductDetail;
import com.amssolutions.prueba.service.adapter.dto.ProductDetailBusiness;
import com.amssolutions.prueba.service.mapper.IProductDetailMapper;

/**
 * Product mapper implementation.
 * 
 * @author obarcia
 */
@Component
public class ProductDetailMapperImpl implements IProductDetailMapper {

	@Override
	public ProductDetail map(ProductDetailBusiness source) {
		
		ProductDetail response = new ProductDetail();
		BeanUtils.copyProperties(source, response);
		return response;
	}
	
}
