package edu.val.cfticionic.controller;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.val.cfticionic.dao.ListaUsuariosRegistradosCFTIC;
import edu.val.cfticionic.dto.UsuarioCFTIC;
import edu.val.cfticionic.util.CodeControlUtilCFTIC;


@Controller
public class UsuarioCFTICController {
	
	private final static Logger log = Logger.getLogger("mylog");
	
	
	@RequestMapping(path = "/usuariocftic", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<UsuarioCFTIC> altaUsuarioCFTIC(@Valid @RequestBody UsuarioCFTIC usuarioCFTIC, 	BindingResult result) 
	{
		ResponseEntity<UsuarioCFTIC> respuesta = null;
		HttpStatus httpStatus = null;
		boolean hay_errores = false;
		
				log.debug("Entramos en alta usuario uRX " +usuarioCFTIC.toString());
				hay_errores = result.hasErrors();
				
				if ((hay_errores)||(!usuarioCFTIC.getNombre().equals(usuarioCFTIC.getPwd())))
				{
					log.debug("Errores léxicos en la petición");
					httpStatus = HttpStatus.BAD_REQUEST;
					usuarioCFTIC = null;
					
				} else 
					{
						log.debug("Validación ok");
						httpStatus = HttpStatus.OK;
						
						UsuarioCFTIC uax = ListaUsuariosRegistradosCFTIC.existeUsuario(usuarioCFTIC);
						
						if (uax==null) {
							
							log.debug("Usuario no existe");
							try {
								String token = CodeControlUtilCFTIC.obtenerCodigoAutenticacion(usuarioCFTIC.getNombre());
								usuarioCFTIC.setToken(token);
								ListaUsuariosRegistradosCFTIC.insertarUsuario(usuarioCFTIC);
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								log.error("Error al generar la clave", e);
								usuarioCFTIC = null;
								httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
							}
							
						} else {
							log.debug("Usuario ya existía");
							usuarioCFTIC = uax;
						}				
						
					} 
					
				respuesta = new ResponseEntity<UsuarioCFTIC>(usuarioCFTIC, httpStatus);
				
		return respuesta;
	}

}
