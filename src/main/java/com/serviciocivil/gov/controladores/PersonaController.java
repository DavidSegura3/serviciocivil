package com.serviciocivil.gov.controladores;

import com.serviciocivil.gov.core.entidades.Persona;
import com.serviciocivil.gov.core.servicios.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    /**
     * Endpoint para crear una persona
     * @param persona Objeto con la información necesaria para crear a la persona en bd
     * @param result Variable para validar si la data cumple con las validaciones hechas desde jpa
     * @param tipoDocumentoId Variable del tipo documento a consultar
     * @return Retorna un mensaje y un código 200-OK  si la persona se crea correctamente,
     * si no retorna un mensaje con las validaciones y un código 400-BadRequest
     * @author David Segura - 24-08-2022
     */
    @PostMapping("/persona/tipo-documento-id/{tipoDocumentoId}")
    public ResponseEntity<?> guardarPersona(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Integer tipoDocumentoId){

        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }
        personaService.guardar(persona, tipoDocumentoId);
        return new ResponseEntity<>("Persona almacenada correctamente", HttpStatus.CREATED);
    }

    /**
     * Endpoint para consultar todas las personas creadas en bd.
     * @return Retorna una lista de personas
     * @author David Segura - 24-08-2022
     */
    @GetMapping("/personas")
    public ResponseEntity<?> consultarTodasPersona(){
        return new ResponseEntity<>(personaService.buscarTodos(), HttpStatus.OK);
    }

    /**
     * Endpoint para consultar una persona por id
     * @param personaId Variable de la persona a consultar
     * @return Retorna el objeto persona y un código 200-OK,
     * si la persona no existe retorna un mensaje y un código 404-NotFound
     * @author David Segura - 24-08-2022
     */
    @GetMapping("/persona/persona-id/{personaId}")
    public ResponseEntity<?> consultarPersona(@PathVariable Integer personaId){
        return new ResponseEntity<>(personaService.buscarPorId(personaId), HttpStatus.OK);
    }

    /**
     * Endpoint para eliminar una persona por id
     * @param personaId Variable de la persona a consultar
     * @return Retorna un código 204-NoContent,
     * si la persona no existe retorna un mensaje y un código 404-NotFound
     * @author David Segura - 24-08-2022
     */
    @DeleteMapping("/persona/{personaId}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Integer personaId){
        personaService.eliminar(personaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para actualizar una persona
     * @param persona Objeto con la información a cambiar
     * @param result Variable para validar si la data cumple con las validaciones hechas desde jpa
     * @param personaId Variable de la persona a modificar
     * @return Retorna el objeto modificado y un código 200-OK si la persona se modifica correctamente,
     * si no retorna un mensaje con las validaciones y un código 400-BadRequest o 404 en caso de que no exista la persona
     * a modificar
     * @author David Segura - 24-08-2022
     */
    @PutMapping("/persona-mod/{personaId}")
    public ResponseEntity<?> actualizarPersona(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Integer personaId){
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista Errores", listaErrores);
            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaService.modificar(persona, personaId), HttpStatus.OK);
    }
}
