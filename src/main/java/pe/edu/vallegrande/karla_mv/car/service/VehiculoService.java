package pe.edu.vallegrande.karla_mv.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.karla_mv.car.model.Vehiculo;
import pe.edu.vallegrande.karla_mv.car.repository.VehiculoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> findById(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public void deleteById(Long id) {
        vehiculoRepository.deleteById(id);
    }

    public Vehiculo update(Long id, Vehiculo vehiculoDetails) {
        return vehiculoRepository.findById(id)
                .map(vehiculo -> {
                    vehiculo.setPlaca(vehiculoDetails.getPlaca());
                    vehiculo.setMarca(vehiculoDetails.getMarca());
                    vehiculo.setModelo(vehiculoDetails.getModelo());
                    vehiculo.setAnio(vehiculoDetails.getAnio());
                    vehiculo.setColor(vehiculoDetails.getColor());
                    vehiculo.setPrecioPorDia(vehiculoDetails.getPrecioPorDia());
                    vehiculo.setEstado(vehiculoDetails.getEstado());
                    return vehiculoRepository.save(vehiculo);
                })
                .orElse(null);
    }
}
