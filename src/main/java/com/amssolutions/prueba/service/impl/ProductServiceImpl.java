package com.amssolutions.prueba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amssolutions.prueba.api.dto.SimilarProducts;
import com.amssolutions.prueba.exception.ProductNotFoundException;
import com.amssolutions.prueba.exception.WebServiceException;
import com.amssolutions.prueba.service.IProductService;
import com.amssolutions.prueba.service.adapter.IProductWebClientAdapter;
import com.amssolutions.prueba.service.adapter.exception.ProductBusinessNotFoundException;
import com.amssolutions.prueba.service.adapter.exception.WebServiceBusinessException;
import com.amssolutions.prueba.service.mapper.IProductDetailMapper;

import lombok.AllArgsConstructor;

/**
 * Product service implementation.
 * 
 * @author obarcia
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final IProductWebClientAdapter productWebClientAdapter;
	
	private final IProductDetailMapper productDetailMapper;
	
	@Override
	public SimilarProducts getSimilarProducts(String productId) 
			throws ProductNotFoundException, WebServiceException {
		
		try {
			List<String> ids = productWebClientAdapter.getSimilarProducts(productId);
			
			SimilarProducts list = new SimilarProducts();
			if (!CollectionUtils.isEmpty(ids)) {
				list.addAll(ids.stream()
						.map(id -> productDetailMapper.map(productWebClientAdapter.getProductDetails(id)))
						.toList());
			}
			
			return list;
		} catch (WebServiceBusinessException wsEx) {
			throw new WebServiceException();
		} catch (ProductBusinessNotFoundException notFoundEx) {
			throw new ProductNotFoundException();
		}
		
	}
	
}
