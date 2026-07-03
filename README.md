# MS-VEHICULOS - Microservicio de Vehículos (Reactivo)

Microservicio reactivo para la gestión de vehículos desarrollado con Spring Boot WebFlux y R2DBC.

## Estructura del Proyecto
```
karla-mv.car/
├── src/
│   └── main/
│       ├── java/pe/edu/vallegrande/karla_mv/car/
│       │   ├── Application.java          # Clase principal de Spring Boot (inicial)
│       │   ├── model/
│       │   │   └── Vehiculo.java         # Entidad/Modelo de Vehículo
│       │   ├── repository/
│       │   │   └── VehiculoRepository.java # Repositorio Reactivo
│       │   ├── service/
│       │   │   └── VehiculoService.java  # Logica de Negocio
│       │   └── controller/
│       │       └── VehiculoController.java # Controlador REST
│       └── resources/
│           └── application.yml           # Configuración (YAML)
├── manifest/                              # Archivos de Kubernetes
│   ├── ms-vehiculos-namespace.yml
│   ├── ms-vehiculos-secret.yml
│   ├── ms-vehiculos-deployment.yml
│   └── ms-vehiculos-service.yml
└── pom.xml                                # Archivo de dependencias Maven
```

## Paso a Paso de Creación del Código

### 1. Clase Modelo: `Vehiculo.java`
- Representa la entidad `vehiculos` en la base de datos
- Usa anotaciones de **Spring Data R2DBC** (`@Table`, `@Id`, `@Column`)
- Lombok genera getters/setters/constructores (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`)
- Incluye todos los campos de la tabla: id, placa, marca, modelo, anio, color, precioPorDia, estado

### 2. Repositorio: `VehiculoRepository.java`
- Extiende `ReactiveCrudRepository<Vehiculo, Long>`
- Proporciona métodos CRUD reactivos listos para usar (sin necesidad de escribir SQL básico)
- Anotado con `@Repository` para que Spring lo detecte

### 3. Servicio: `VehiculoService.java`
- Contiene la lógica de negocio
- Usa `Mono` para valores únicos y `Flux` para colecciones
- Métodos: `findAll()`, `findById()`, `save()`, `deleteById()`, `update()`
- Anotado con `@Service` y `@RequiredArgsConstructor` para inyección de dependencias

### 4. Controlador: `VehiculoController.java`
- Expone los endpoints REST en `/api/vehiculos`
- Anotado con `@RestController`, `@RequestMapping`, `@RequiredArgsConstructor`
- Mapea cada método HTTP (GET, POST, PUT, DELETE) a los métodos del servicio
- Usa `@CrossOrigin` para permitir solicitudes desde cualquier origen

### 5. Configuración: `application.yml`
- Configura el puerto del servidor (8081)
- Configura la conexión reactiva a PostgreSQL (R2DBC)
- Incluye configuración del pool de conexiones

### 6. Dependencias: `pom.xml`
- Reemplazadas dependencias tradicionales por las reactivas:
  - `spring-boot-starter-webmvc` → `spring-boot-starter-webflux`
  - `spring-boot-starter-data-jpa` → `spring-boot-starter-data-r2dbc`
  - `postgresql` → `r2dbc-postgresql`

## Tecnologías y Dependencias
- **Spring Boot 4.1.0**
- **Spring WebFlux**: Para API reactiva (Mono/Flux)
- **Spring Data R2DBC**: Para acceso reactivo a bases de datos
- **R2DBC PostgreSQL**: Driver reactivo para PostgreSQL
- **Lombok**: Para reducir código boilerplate
- **Java 26**

## Endpoints REST API

| Método | Endpoint           | Descripción                                  |
|--------|--------------------|----------------------------------------------|
| GET    | `/api/vehiculos`   | Obtener todos los vehículos                  |
| GET    | `/api/vehiculos/{id}` | Obtener vehículo por ID                    |
| POST   | `/api/vehiculos`   | Crear nuevo vehículo (status 201)            |
| PUT    | `/api/vehiculos/{id}` | Actualizar vehículo existente               |
| DELETE | `/api/vehiculos/{id}` | Eliminar vehículo (status 204)              |

## Estructura del Vehículo
```json
{
  "id": 1,
  "placa": "ABC-123",
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2023,
  "color": "Rojo",
  "precioPorDia": 50.00,
  "estado": "disponible"
}
```

## Paso a Paso de Despliegue con Kubernetes

### 1. Crear el Namespace
```bash
kubectl create namespace ms-vehiculos-namespace
```

### 2. Aplicar todos los manifestos (en orden)
```bash
cd manifest
kubectl apply -f ms-vehiculos-namespace.yml
kubectl apply -f ms-vehiculos-secret.yml
kubectl apply -f ms-vehiculos-deployment.yml
kubectl apply -f ms-vehiculos-service.yml
```

### 3. Verificar el despliegue
```bash
kubectl get pods -n ms-vehiculos-namespace
kubectl get svc -n ms-vehiculos-namespace
```

## Compilación y Ejecución Local

### Compilar el proyecto
```bash
mvn clean compile
```

### Ejecutar la aplicación
```bash
mvn spring-boot:run
```

La aplicación se ejecutará en el puerto **8081** por defecto.

## Configuración de Base de Datos
La conexión a PostgreSQL se configura mediante variables de entorno (se usan en Kubernetes Secret) o valores por defecto en `application.yml`:
- `DB_HOST`: Host de la base de datos
- `DB_PORT`: Puerto (default: 5432)
- `DB_NAME`: Nombre de la base de datos (default: vehiculos)
- `DB_USER`: Usuario
- `DB_PASSWORD`: Contraseña
- `DB_SSL_MODE`: Modo SSL (default: require)
- `SERVER_PORT`: Puerto del servidor (default: 8081)
