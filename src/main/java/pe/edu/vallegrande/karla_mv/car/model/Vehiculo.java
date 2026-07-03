package pe.edu.vallegrande.karla_mv.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    private Long id;

    @Column("placa")
    private String placa;

    @Column("marca")
    private String marca;

    @Column("modelo")
    private String modelo;

    @Column("anio")
    private Integer anio;

    @Column("color")
    private String color;

    @Column("precio_por_dia")
    private BigDecimal precioPorDia;

    @Column("estado")
    private String estado;
}
