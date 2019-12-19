package edu.val.cfticionic.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.val.cfticionic.dao.ListaComentarios;
import edu.val.cfticionic.dao.ListaUsuariosRegistradosCFTIC;
import edu.val.cfticionic.dto.Comentario;
import edu.val.cfticionic.dto.Dni;
import edu.val.cfticionic.dto.FotoBean;

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
	
	
	
	@RequestMapping(path = "/jsonpdni", method=RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public ResponseEntity<String> dniJSONP(@RequestParam(required = true, value = "callback") String callback) 
	{
		ResponseEntity<String> respuesta = null;
		//ResponseEntity<JSONPObject> respuesta = null;
		String str_json = null;
		String dni_json = null;
		Dni dni = null;
		Gson gson = null;
		
				log.debug("Entramos en dniJSONP con callback = " + callback);
				dni = new Dni(53130984, 'H');
				gson = new Gson();
				dni_json = gson.toJson(dni);
				//str_json = callback +"(" + dni.getNumero() + ");";
				str_json = callback +"( '{\"numero\":\""+dni.getNumero()+"\", \"letra\":\""+dni.getLetra()+"\" }');";
				log.debug("str_json = " + str_json);
				
				respuesta = new ResponseEntity<String>(str_json, HttpStatus.OK);
				//respuesta = new ResponseEntity<JSONPObject>(jp, HttpStatus.OK);
				
		return respuesta;
	}
	
	
	@RequestMapping(path = "/jsondni", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Dni> dniJSON() 
	{
		ResponseEntity<Dni> respuesta = null;
		String str_json = null;
		String dni_json = null;
		Dni dni = null;
		Gson gson = null;
		
				log.debug("jsondni");
				dni = new Dni(53130984, 'H');
				
				respuesta = new ResponseEntity<Dni>(dni, HttpStatus.OK);
				
		return respuesta;
	}
 

}
