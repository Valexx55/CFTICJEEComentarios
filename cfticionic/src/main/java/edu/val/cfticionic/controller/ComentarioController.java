package edu.val.cfticionic.controller;

import java.util.Date;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.val.cfticionic.dao.ListaComentarios;
import edu.val.cfticionic.dao.ListaUsuariosRegistradosCFTIC;
import edu.val.cfticionic.dto.Comentario;
import edu.val.cfticionic.dto.controller.ComentarioPostRequestDTO;
import edu.val.cfticionic.dto.controller.ComentarioPostResponseDTO;

@Controller
public class ComentarioController {
	
	private final static Logger log = Logger.getLogger("mylog");
	
	@RequestMapping(path = "/comentario", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<ComentarioPostResponseDTO> altaComentario(@Valid @RequestBody ComentarioPostRequestDTO comentario_nuevo, 	BindingResult result) 
	{
		ResponseEntity<ComentarioPostResponseDTO> respuesta = null;
		HttpStatus httpStatus = null;
		boolean hay_errores = false;
		ComentarioPostResponseDTO comentario_respuesta = null;
		
				log.debug("Entramos en alta comentario Rx " +comentario_nuevo.toString());
				hay_errores = result.hasErrors();
				
				if ((hay_errores))
				{
					log.debug("Errores léxicos en la petición");
					httpStatus = HttpStatus.BAD_REQUEST;
					
				} else 
					{
						log.debug("Validación ok");
					if (ListaUsuariosRegistradosCFTIC.credencialesCorrectas(comentario_nuevo.getNombre(), comentario_nuevo.getToken()))
							{
								log.debug("Credenciales correctas");
								int id_nuevoc = ListaComentarios.nextId();
								Comentario comentario = new Comentario(id_nuevoc, comentario_nuevo.getNombre(), comentario_nuevo.getIdfoto(), new Date().getTime(), comentario_nuevo.getTexto());
								ListaComentarios.insertarNuevoComentario(comentario);
								comentario_respuesta = new ComentarioPostResponseDTO(comentario.getAutor(), comentario.getTexto(), comentario.getId(), comentario.getMomento());
								httpStatus = HttpStatus.CREATED;
							} else {
								log.debug("Credenciales INCcorrectas");
								httpStatus = HttpStatus.FORBIDDEN;
							}
							
					} 
					
				respuesta = new ResponseEntity<ComentarioPostResponseDTO>(comentario_respuesta, httpStatus);
				
		return respuesta;
	}
	
	
	@RequestMapping(path = "/comentario", method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<ComentarioPostResponseDTO> editarComentario(@Valid @RequestBody ComentarioPostRequestDTO comentario_modificado, 	BindingResult result) 
	{
		ResponseEntity<ComentarioPostResponseDTO> respuesta = null;
		HttpStatus httpStatus = null;
		boolean hay_errores = false;
		ComentarioPostResponseDTO comentario_respuesta = null;
		int posicion_comentario = -1;
		
				log.debug("Entramos en PUT comentario Rx " +comentario_modificado.toString());
				hay_errores = result.hasErrors();
				
				if ((hay_errores))
				{
					log.debug("Errores léxicos en la petición");
					httpStatus = HttpStatus.BAD_REQUEST;
					
				} else 
					{
						log.debug("Validación ok");
						if (ListaUsuariosRegistradosCFTIC.credencialesCorrectas(comentario_modificado.getNombre(), comentario_modificado.getToken()))
							{
								log.debug("Credenciales correctas");
								posicion_comentario = ListaComentarios.esPropietario(comentario_modificado.getIdcomentario(), comentario_modificado.getNombre());
								if (posicion_comentario!=-1)//
								{
									log.debug("El usuario es el autor de dicho comentario pos " + posicion_comentario);
									ListaComentarios.borrarComentario(posicion_comentario);
									log.debug("Borramos el comentario viejo");
									Comentario comentario = new Comentario(comentario_modificado.getIdcomentario(), comentario_modificado.getNombre(), comentario_modificado.getIdfoto(), new Date().getTime(), comentario_modificado.getTexto());
									ListaComentarios.insertarNuevoComentario(comentario);
									log.debug("Insertamos el nuevo");
									comentario_respuesta = new ComentarioPostResponseDTO(comentario.getAutor(), comentario.getTexto(), comentario.getId(), comentario.getMomento());
									httpStatus = HttpStatus.CREATED;
								} else {
									httpStatus = HttpStatus.NO_CONTENT;
								}
								
							} else {
								log.debug("Credenciales INCcorrectas");
								httpStatus = HttpStatus.FORBIDDEN;
							}
							
					} 
					
				respuesta = new ResponseEntity<ComentarioPostResponseDTO>(comentario_respuesta, httpStatus);
				
		return respuesta;
	}

	
	
	@RequestMapping(path = "/comentario", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> bajaComentario(@RequestParam(required = true, value = "key", defaultValue = "") String clave,
			@RequestParam(required = true, value = "idcomentario", defaultValue = "") int idcomentario,
			@RequestParam(required = true, value = "nombre", defaultValue = "") String nombre) {
		ResponseEntity<Void> respuesta = null;
		HttpStatus httpStatus = null;
		int posicion_comentario = -1;

		log.debug("Entramos en baja comentario Rx ");
		if (ListaUsuariosRegistradosCFTIC.credencialesCorrectas(nombre, clave)) {
			log.debug("Credenciales correctas");

			posicion_comentario = ListaComentarios.esPropietario(idcomentario, nombre);
			if (posicion_comentario != -1) {
				log.debug("Usuario autorizado es el autor del comentario ");
				ListaComentarios.borrarComentario(posicion_comentario);
				httpStatus = HttpStatus.OK;
			} else {
				log.debug("Usuario no autorizado no es el autor del comentario ");
				httpStatus = HttpStatus.FORBIDDEN;
			}
		} else {
			log.debug("Credenciales INCcorrectas");
			httpStatus = HttpStatus.FORBIDDEN;
		}

		respuesta = new ResponseEntity<Void>(httpStatus);

		return respuesta;
	}

}
