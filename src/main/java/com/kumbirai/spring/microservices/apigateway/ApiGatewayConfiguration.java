/*
 Copyright (c) 2021

 All rights reserved.
 */
package com.kumbirai.spring.microservices.apigateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> ApiGatewayConfiguration<br>
 * <br>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 * <br>
 *					
 */
@Configuration
public class ApiGatewayConfiguration
{
	private static final Logger LOGGER = LogManager.getLogger(ApiGatewayConfiguration.class.getName());
	private static final String URI_HTTP_HTTPBIN_ORG_80 = "http://httpbin.org:80";
	private static final String URI_LB_CURRENCY_EXCHANGE = "lb://currency-exchange";
	private static final String URI_LB_CURRENCY_CONVERSION = "lb://currency-conversion";

	/**
	 * Constructor:
	 */
	public ApiGatewayConfiguration()
	{
		super();
		LOGGER.trace("ApiGatewayConfiguration.CONSTRUCTOR");
	}

	/**
	 * Purpose:<br>
	 * <br>
	 * gatewayRouter<br>
	 * <br>
	 * @param builder
	 * @return<br>
	 * <br>
	 */
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
	{
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri(URI_HTTP_HTTPBIN_ORG_80))
				.route(p -> p.path("/currency-exchange/**")
						.uri(URI_LB_CURRENCY_EXCHANGE))
				.route(p -> p.path("/currency-conversion/**")
						.uri(URI_LB_CURRENCY_CONVERSION))
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri(URI_LB_CURRENCY_CONVERSION))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
						.uri(URI_LB_CURRENCY_CONVERSION))
				.build();
	}
}
