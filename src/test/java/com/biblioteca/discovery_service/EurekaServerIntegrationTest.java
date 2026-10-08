package com.biblioteca.discovery_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EurekaServerIntegrationTest {

    @Value("${local.server.port}")
    private int puerto;

    private final HttpClient http = HttpClient.newHttpClient();

    private HttpResponse<String> get(String ruta, String accept) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create("http://localhost:" + puerto + ruta))
                .timeout(Duration.ofSeconds(10));
        if (accept != null) {
            builder.header("Accept", accept);
        }
        return http.send(builder.GET().build(), HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void elPanelDeEurekaRespondeConElEstadoDelRegistro() throws Exception {
        HttpResponse<String> respuesta = get("/", "text/html");

        assertEquals(200, respuesta.statusCode());
        assertTrue(respuesta.body().toLowerCase().contains("eureka"));
    }

    @Test
    void laApiDeAplicacionesRespondeEnJson() throws Exception {
        HttpResponse<String> respuesta = get("/eureka/apps", "application/json");

        assertEquals(200, respuesta.statusCode());
        assertTrue(respuesta.body().contains("applications"));
    }
}
