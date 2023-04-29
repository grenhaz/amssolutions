package com.amssolutions.prueba.service.adapter.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * Product business DTO.
 * 
 * @author obarcia
 */
@Data
public class ProductDetailBusiness implements Serializable {

	private static final long serialVersionUID = -3075531469171621905L;

	/**
	 * Id.
	 */
	private String id;

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Price.
	 */
	private BigDecimal price;

	/**
	 * Availability.
	 */
	private Boolean availability;
	
}
