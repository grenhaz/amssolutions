package com.amssolutions.prueba.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amssolutions.prueba.api.dto.SimilarProducts;
import com.amssolutions.prueba.exception.ProductNotFoundException;
import com.amssolutions.prueba.exception.WebServiceException;
import com.amssolutions.prueba.service.IProductService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

/**
 * Api product controller.
 * 
 * @author obarcia
 */
@RestController
@Validated
@AllArgsConstructor
public class ProductController {
	
	public static final String ENDPOINT_PRODUCT_SIMILAR = "/product/{productId}/similar";
	
	private final IProductService productService;

	/**
	 * Endpoint to get a list of similar products by the related product id.
	 * 
	 * @param productId Product id.
	 * @return List of product details.
	 * @throws ProductNotFoundException
	 * @throws WebServiceException
	 */
	@GetMapping(ENDPOINT_PRODUCT_SIMILAR)
	public ResponseEntity<SimilarProducts> getProductSimilar(
			@PathVariable("productId") 
			@NotNull @NotBlank
			String productId) 
			throws ProductNotFoundException, WebServiceException {
        
		return new ResponseEntity<>(productService.getSimilarProducts(productId), HttpStatus.OK);
    }
	
}
