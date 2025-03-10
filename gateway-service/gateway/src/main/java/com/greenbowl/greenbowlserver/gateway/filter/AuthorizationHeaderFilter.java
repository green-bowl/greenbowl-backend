package com.greenbowl.greenbowlserver.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${spring.jwt.secret}")
    private String secret;

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String jwt = extractTokenForHeader(request);
            String userId = getUserIdFromJwt(jwt);
            if (userId == null || userId.isEmpty()) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("userId", userId)
                    .build();
            ServerWebExchange mutateExchange = exchange.mutate().request(mutatedRequest).build();

            return chain.filter(mutateExchange);
        });
    }

    private String getUserIdFromJwt(String jwt) {
        try {
            byte[] secretKeyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
            SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
            JwtParser jwtParser = Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey)
                    .build();

            Claims claims = jwtParser.parseClaimsJws(jwt).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    private String extractTokenForHeader(ServerHttpRequest request) {
        String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        return authorizationHeader.replace("Bearer ", "");
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);

        byte[] bytes = "".getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

        return response.writeWith(Flux.just(buffer));
    }
}
