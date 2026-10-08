# Changelog

Todos los cambios relevantes de este proyecto se documentan en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/es-ES/1.1.0/)
y este proyecto sigue [Semantic Versioning](https://semver.org/lang/es/).

## [1.1.0] - 2026-10-08

### Añadido

- 2 tests de integración: el panel de Eureka responde 200 y la API `/eureka/apps` responde en JSON

## [1.0.0] - 2026-10-05

### Añadido

- Servidor Eureka en modo standalone para el registro y descubrimiento de microservicios
- Panel web en `http://localhost:8761` con el estado de las instancias registradas
- Renovación periódica del registro de cada servicio, sin dependencia de base de datos
