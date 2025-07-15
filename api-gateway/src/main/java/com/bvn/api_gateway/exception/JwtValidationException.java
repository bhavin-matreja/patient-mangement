package com.bvn.api_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*
 When api gateway tries to call validation endpoint on the off server and if the off server
 returns a unauthorized response - instead of returning 500 response from api gateway to client
 we intercept this and ensure that the response (401) gets sent back from api gateway to the rest client
 */
@RestControllerAdvice // to handle exception
public class JwtValidationException {
    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    // mono tells spring current execution is finished
    public Mono<Void> handleUnauthorizedException(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
