# It-Patagonia-Backend

Backend correspondiente al **Challenge para IT Patagonia**.

Este proyecto implementa una **arquitectura hexagonal** utilizando **Java** con **Spring Boot**.


## Tecnologías

- **Java 21**
- **Spring Boot 3.4.2**
- **Maven 3.8.1**
- **Base de datos: MySQL 9.2.0**


## Instrucciones de instalación y ejecución

1. **Compilar el proyecto**:

   ```
   mvn clean install
   mvn spring-boot:run

## Endpoints disponibles

### 1. Adherir una empresa
- **Método**: `POST`
- **URL**: `http://localhost:8080/empresa/adhesion`
- **Parámetros**:
    - `cuit` (String) - **Obligatorio**
    - `razonSocial` (String) - **Obligatorio**


### 2. Obtener empresas adheridas en el último mes
- **Método**: `GET`
- **URL**: `http://localhost:8080/empresa/adheridas-ultimo-mes`


### 3. Obtener empresas con transferencias en el último mes
- **Método**: `GET`
- **URL**: `http://localhost:8080/empresa/transferencias-ultimo-mes`

---
