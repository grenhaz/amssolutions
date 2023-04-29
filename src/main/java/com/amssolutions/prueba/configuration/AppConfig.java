package com.amssolutions.prueba.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Application configuration.
 * 
 * @author obarcia
 */
@EnableCaching
@EnableRetry
@Configuration
public class AppConfig {
}
