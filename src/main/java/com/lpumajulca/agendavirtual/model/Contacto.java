package com.lpumajulca.agendavirtual.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontacto")
    private Integer id;

    private String nombre;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fechanac")
    private LocalDate fechaNacimiento;

    private String celular;

    private String email;

    @Column(name = "fechareg")
    private LocalDateTime fechaRegistro;

}
