package pe.edu.vallegrande.karla_mv.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.karla_mv.car.model.Vehiculo;
import pe.edu.vallegrande.karla_mv.car.service.VehiculoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    @GetMapping
    public Flux<Vehiculo> getAllVehiculos() {
        return vehiculoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Vehiculo>> getVehiculoById(@PathVariable Long id) {
        return vehiculoService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.save(vehiculo);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Vehiculo>> updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculoDetails) {
        return vehiculoService.update(id, vehiculoDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteVehiculo(@PathVariable Long id) {
        return vehiculoService.deleteById(id);
    }
}
