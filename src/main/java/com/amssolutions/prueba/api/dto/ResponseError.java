package com.amssolutions.prueba.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Error dto.
 * 
 * @author obarcia
 */
@Data
public class ResponseError implements Serializable {
	
	private static final long serialVersionUID = 9154110301754090330L;

	private String message;
}
