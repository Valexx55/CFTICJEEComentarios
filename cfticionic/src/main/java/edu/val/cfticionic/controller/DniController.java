package edu.val.cfticionic.controller;

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

import edu.val.cfticionic.dto.Dni;

@Controller
public class DniController {

	private final static Logger log = Logger.getLogger("mylog");

	private boolean letraCorrecta(Dni dni) {
		
		return dni.calculaLetra()==dni.getLetra();
	}
	

	@RequestMapping(path = "/dni", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Void> altaDni(@Valid @RequestBody Dni dni, BindingResult result) {
		ResponseEntity<Void> respuesta = null;
		HttpStatus httpStatus = null;
		boolean hay_errores = false;

		log.debug("Entramos en alta usuario");
		hay_errores = result.hasErrors();

		if (hay_errores) {
			log.debug("Errores léxicos en la petición");
			httpStatus = HttpStatus.BAD_REQUEST;//400

		} else if (!letraCorrecta(dni)) {
			log.debug("Errores léxicos en la petición");
			httpStatus = HttpStatus.NOT_ACCEPTABLE;//406
		} else {
			log.debug("Sin Errores en la entrada. Se procede a dar de alta " + dni);
			httpStatus = HttpStatus.CREATED;//201
		}

		respuesta = new ResponseEntity<Void>(httpStatus);

		return respuesta;
	}

}
