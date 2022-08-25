package com.serviciocivil.gov.core.servicios;

import com.serviciocivil.gov.core.entidades.Persona;

import java.util.List;

public interface IPersonaService {

    void guardar(Persona persona, Integer tipoDocumentoId);
    List<Persona> buscarTodos();
    Persona buscarPorId(Integer personaId);
    Persona modificar(Persona persona, Integer personaId);
    void eliminar(Integer personaId);
}
