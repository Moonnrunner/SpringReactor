package Improve.reactor.router;

import Improve.reactor.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        return RouterFunctions
                .route(GET("/hello")
                .and(accept(MediaType.TEXT_PLAIN)), greetingHandler::hello)
                .andRoute(GET("/"),
                        serverRequest -> {
                            return ServerResponse
                                    .ok()
                                    .render("index", Map.of("user", ""));
                        }
                );

    }
}