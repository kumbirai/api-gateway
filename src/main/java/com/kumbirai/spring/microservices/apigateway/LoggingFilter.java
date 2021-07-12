/*
 Copyright (c) 2021

 All rights reserved.
 */
package com.kumbirai.spring.microservices.apigateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> LoggingFilter<br>
 * <br>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 * <br>
 */
@Component
public class LoggingFilter implements GlobalFilter
{
    private static final Logger LOGGER = LogManager.getLogger(LoggingFilter.class.getName());

    /**
     * Constructor:
     */
    public LoggingFilter()
    {
        super();
        LOGGER.trace("LoggingFilter.CONSTRUCTOR");
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.cloud.gateway.filter.GlobalFilter#filter(org.springframework.web.server.ServerWebExchange, org.springframework.cloud.gateway.filter.GatewayFilterChain)
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        LOGGER.info("Path of the request received -> {}", exchange.getRequest()
                .getPath());
        return chain.filter(exchange);
    }
}
