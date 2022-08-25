package com.serviciocivil.gov.core.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "personas")
public class Persona implements Serializable {

    private static final long serialVersionUID = 7022294733277006594L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank
    @NotNull
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotBlank
    @NotNull
    @Column(name = "numero_documento", nullable = false, unique = true)
    private String numeroDocumento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private ZonedDateTime fechaNacimiento;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tipo_documento_id")
    private TipoDocumento tipoDocumento;
}
