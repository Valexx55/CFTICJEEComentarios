package edu.val.cfticionic.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.val.cfticionic.dao.ListaComentarios;
import edu.val.cfticionic.dao.ListaUsuariosRegistradosCFTIC;
import edu.val.cfticionic.dto.Comentario;
import edu.val.cfticionic.dto.FotoBean;


@Controller
public class FotoController {
	
	private final static Logger log = Logger.getLogger("mylog");
	
	public final static String[] RUTAS_FOTOS = {"http://10.1.2.10:8081/cfticionic/fotos/adios.jpg"
			, "http://10.1.2.10:8081/cfticionic/fotos/dinero.jpg" 
			, "http://10.1.2.10:8081/cfticionic/fotos/frozen.jpg" 
			, "http://10.1.2.10:8081/cfticionic/fotos/gigolo.jpg"
			, "http://10.1.2.10:8081/cfticionic/fotos/joker.jpg" 
			, "http://10.1.2.10:8081/cfticionic/fotos/ladronas.jpg"
			, "http://10.1.2.10:8081/cfticionic/fotos/lluvia.jpg" 
			, "http://10.1.2.10:8081/cfticionic/fotos/malefica.jpg"
			, "http://10.1.2.10:8081/cfticionic/fotos/rico.jpg" 
			, "http://10.1.2.10:8081/cfticionic/fotos/terminator.jpg"};
	
	public final static String[] TITULOS_FOTOS = {"Adiós"
			, "Un perro llamado dinero" 
			, "Frozen 2" 
			, "Gigolo"
			, "Joker" 
			, "Ladronas"
			, "Un dia de lluvia en NY" 
			, "Maléfica"
			, "Si yo fuera rico" 
			, "Terminator"};
	
	
	//TODO: RESTRINIR EL ACCESO A LA FOTO, DEFINIR EL API DE COMENTARIOS
	@RequestMapping(path = "/foto", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FotoBean> obtenerFoto( @RequestParam (required = true, value = "idfoto", defaultValue = "") int idfoto, @RequestParam(required = true, value = "key", defaultValue = "") String clave) 
	{
		ResponseEntity<FotoBean> respuesta = null;
		HttpStatus httpStatus = null;
		FotoBean fotoBean = null;
		
				log.debug("Entramos en obtenerFoto " +idfoto);	
				if (ListaUsuariosRegistradosCFTIC.tokenValido(clave))
				{
					if (idfoto >=0 && idfoto<=RUTAS_FOTOS.length)
					{
						String nombre_foto = RUTAS_FOTOS[idfoto];
						String titulo = TITULOS_FOTOS[idfoto];
						fotoBean = new FotoBean(idfoto, nombre_foto, titulo);
						httpStatus = HttpStatus.OK;
					}
					else {
						log.debug("Foto fuera de rango " +idfoto);
						httpStatus = HttpStatus.BAD_REQUEST;
					}
				} else {
					log.debug("Token INvalido");
					httpStatus = HttpStatus.FORBIDDEN;
				}
					
				respuesta = new ResponseEntity<FotoBean>(fotoBean, httpStatus);
				
		return respuesta;
	}
	
	@RequestMapping(path = "/fotos", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<FotoBean>> obtenerFotos(@RequestParam(required = true, value = "key", defaultValue = "") String clave) 
	{
		ResponseEntity<List<FotoBean>> respuesta = null;
		List<FotoBean> lista_fotos = null;
		FotoBean fotoBean;
		
				log.debug("Entramos en obtenerFotos con clave = " + clave);	
				if (ListaUsuariosRegistradosCFTIC.tokenValido(clave))
				{
					log.debug("Token valido");	
					lista_fotos = new ArrayList<FotoBean>();
					for (int i = 0; i<RUTAS_FOTOS.length; i++)
					{
						fotoBean = new FotoBean(i, RUTAS_FOTOS[i], TITULOS_FOTOS[i]);
						lista_fotos.add(fotoBean);
					}
					respuesta = new ResponseEntity<List<FotoBean>>(lista_fotos, HttpStatus.OK);
				}
				else {
					log.debug("Token INvalido");
					respuesta = new ResponseEntity<List<FotoBean>>(lista_fotos, HttpStatus.FORBIDDEN);
				}
				
				
		return respuesta;
	}
	
	
	@RequestMapping(path = "/comentarios/foto", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Comentario>> obtenerFotos(@RequestParam(required = true, value = "key", defaultValue = "") String clave,
			@RequestParam(required = true, value = "idfoto", defaultValue = "") int idfoto) 
	{
		ResponseEntity<List<Comentario>> respuesta = null;
		List<Comentario> lista_comentarios_foto = null;
		FotoBean fotoBean;
		
				log.debug("Entramos en obtenerFotos con clave = " + clave);	
				if (ListaUsuariosRegistradosCFTIC.tokenValido(clave))
				{
					log.debug("Token valido");	
					
					lista_comentarios_foto = ListaComentarios.obtenerListaComentarios(idfoto);
					log.debug("lista Comentarios obtenidos = " + lista_comentarios_foto);
					if (lista_comentarios_foto.size()==0)
					{
						log.debug("Foto con id " + idfoto + " sin comentarios");
						lista_comentarios_foto = null;
						respuesta = new ResponseEntity<List<Comentario>>(lista_comentarios_foto, HttpStatus.NO_CONTENT);
					} else {
						log.debug("Foto con id " + idfoto + "con " + lista_comentarios_foto.size() + " comentarios");
						respuesta = new ResponseEntity<List<Comentario>>(lista_comentarios_foto, HttpStatus.OK);
					}
					
				}
				else {
					log.debug("Token INvalido");
					respuesta = new ResponseEntity<List<Comentario>>(lista_comentarios_foto, HttpStatus.FORBIDDEN);
				}
				
				
		return respuesta;
	}

}
