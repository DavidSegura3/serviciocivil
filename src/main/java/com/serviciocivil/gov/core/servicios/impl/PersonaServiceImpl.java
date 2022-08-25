package com.serviciocivil.gov.core.servicios.impl;

import com.serviciocivil.gov.core.entidades.Persona;
import com.serviciocivil.gov.core.entidades.TipoDocumento;
import com.serviciocivil.gov.core.repositorios.PersonaRepository;
import com.serviciocivil.gov.core.repositorios.TipoDocumentoRepository;
import com.serviciocivil.gov.core.servicios.IPersonaService;
import com.serviciocivil.gov.excepciones.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    @Transactional
    public void guardar(Persona persona, Integer tipoDocumentoId) {

        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(tipoDocumentoId)
                .orElseThrow(() -> new NotFoundException(String.format("El tipo de documento id %s no existe en la bd", tipoDocumentoId)));

        persona.setTipoDocumento(tipoDocumento);
        personaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarTodos() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscarPorId(Integer personaId) {

        return personaRepository.findById(personaId)
                .orElseThrow(() -> new NotFoundException(String.format("La persona con id %s no se encuentra en la bd", personaId)));
    }

    @Override
    @Transactional
    public Persona modificar(Persona persona, Integer personaId) {

        Persona personaEncontrada = personaRepository.findById(personaId)
                .orElseThrow(() -> new NotFoundException(String.format("La persona con id %s no se encuentra en la bd", personaId)));

        personaEncontrada.setApellido(persona.getApellido());
        personaEncontrada.setNombre(persona.getNombre());
        personaEncontrada.setFechaNacimiento(persona.getFechaNacimiento());
        return personaRepository.save(personaEncontrada);
    }

    @Override
    @Transactional
    public void eliminar(Integer personaId) {

        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new NotFoundException(String.format("La persona con id %s no se encuentra en la bd", personaId)));

        personaRepository.delete(persona);
    }
}
