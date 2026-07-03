package pe.edu.vallegrande.karla_mv.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.karla_mv.car.model.Vehiculo;
import pe.edu.vallegrande.karla_mv.car.repository.VehiculoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public Flux<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    public Mono<Vehiculo> findById(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Mono<Vehiculo> save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Mono<Void> deleteById(Long id) {
        return vehiculoRepository.deleteById(id);
    }

    public Mono<Vehiculo> update(Long id, Vehiculo vehiculoDetails) {
        return vehiculoRepository.findById(id)
                .flatMap(vehiculo -> {
                    vehiculo.setPlaca(vehiculoDetails.getPlaca());
                    vehiculo.setMarca(vehiculoDetails.getMarca());
                    vehiculo.setModelo(vehiculoDetails.getModelo());
                    vehiculo.setAnio(vehiculoDetails.getAnio());
                    vehiculo.setColor(vehiculoDetails.getColor());
                    vehiculo.setPrecioPorDia(vehiculoDetails.getPrecioPorDia());
                    vehiculo.setEstado(vehiculoDetails.getEstado());
                    return vehiculoRepository.save(vehiculo);
                });
    }
}
