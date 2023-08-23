package com.lpumajulca.agendavirtual.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Este campo es obligatorio.")
    private String nombre;

    @PastOrPresent(message = "El valor no debe estar en futuro")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fechanac")
    private LocalDate fechaNacimiento;

    @Size(max = 15, message = "El valor debe tener 15 catacteres como máximo.")
    private String celular;

    @Email(message = "El email tiene un formato inválido.")
    private String email;

    @Column(name = "fechareg")
    private LocalDateTime fechaRegistro;

}
