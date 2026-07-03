package pe.edu.vallegrande.karla_mv.car.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.karla_mv.car.model.Vehiculo;

@Repository
public interface VehiculoRepository extends ReactiveCrudRepository<Vehiculo, Long> {
}
