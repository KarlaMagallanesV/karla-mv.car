package pe.edu.vallegrande.karla_mv.car.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String placa;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    private Integer anio;

    @Column(length = 30)
    private String color;

    @Column(name = "precio_por_dia", precision = 10, scale = 2)
    private BigDecimal precioPorDia;

    @Column(length = 20)
    private String estado;
}
