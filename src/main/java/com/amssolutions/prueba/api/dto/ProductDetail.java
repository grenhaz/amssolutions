package com.amssolutions.prueba.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Product details Dto.
 * 
 * @author obarcia
 */
@Data
public class ProductDetail implements Serializable {

	private static final long serialVersionUID = 1380849687683517082L;

	/**
	 * Id.
	 */
	@JsonProperty("id")
	private String id;

	/**
	 * Name.
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * Price.
	 */
	@JsonProperty("price")
	private BigDecimal price;

	/**
	 * Availibility.
	 */
	@JsonProperty("availability")
	private Boolean availability;
	
}
