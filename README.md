# Discovery Service

Servidor Eureka de la plataforma de gestión de biblioteca. Es el registro donde se dan de alta todos los microservicios del sistema (gateway, catalog, transactions y customer), de forma que puedan encontrarse entre ellos por nombre sin necesidad de conocer IPs ni puertos de antemano.

En este proyecto Eureka está en modo standalone (un solo nodo, sin registrarse a sí mismo), que es suficiente para el entorno de desarrollo y para el despliegue con docker-compose.

## Qué hace

- Registro de microservicios: cada servicio se da de alta al arrancar y renueva su registro periódicamente
- Panel web en `http://localhost:8761` con el estado de las instancias registradas
- Sin dependencia de base de datos: el registro vive en memoria

## Stack

- Java 17
- Spring Boot 4.1
- Spring Cloud Netflix Eureka (Eureka Server)

## Cómo ejecutarlo

```bash
./mvnw spring-boot:run
```

El servidor queda disponible en `http://localhost:8761`. Los demás servicios apuntan a él con la variable `EUREKA_URL` (default `http://localhost:8761/eureka/`).

## Parte de un sistema más grande

La plataforma completa se compone de:

- [gateway-service](https://github.com/jjrmch/gateway-service) — API Gateway (punto de entrada, `localhost:8080`)
- [catalog-service](https://github.com/jjrmch/catalog-service) — catálogo de libros y stock
- [transactions-service](https://github.com/jjrmch/transactions-service) — ventas, alquileres, reservas y multas
- [customer-service](https://github.com/jjrmch/customer-service) — clientes
- [biblioteca-frontend](https://github.com/jjrmch/biblioteca-frontend) — panel web en React
- [biblioteca-deploy](https://github.com/jjrmch/biblioteca-deploy) — docker-compose con el stack completo

## Por mejorar

- El modo standalone no vale para producción; habría que montar un cluster de nodos Eureka con replicación.
- No hay autenticación en el panel de Eureka.

## Licencia

MIT
