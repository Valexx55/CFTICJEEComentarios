package edu.val.cfticionic.controller;


import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.val.cfticionic.dto.Dni;

@Controller
public class MiController {
	
	
	
	private final static Logger log = Logger.getLogger("mylog");
	

	
	
	@RequestMapping(path = "/login", method=RequestMethod.GET)
	public String paginicio() {
 
		String pagina = null;
		
			log.info("Ha entrado en Saludo");
			pagina = "inicio";
		
		return pagina;
	}
	
	
	//http://10.1.2.10:8081/cfticionic/calculaletra/5656
	
	@RequestMapping(path = "/calculaletra/{ndni}", method=RequestMethod.GET, 
			produces = "application/json")
	public ResponseEntity<Dni> calculoDni(@PathVariable Integer ndni) {
 
		ResponseEntity<Dni> respuesta = null;
		Dni dni = null;
			
			log.info("Pidiendo letra dni " + ndni);
		
			dni = new Dni(ndni);
			dni.setLetra(dni.calculaLetra());
			respuesta = new ResponseEntity<Dni>(dni, HttpStatus.OK);
		
		return respuesta;
	}
	
 

}
