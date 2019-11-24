package edu.val.cfticionic.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import edu.val.cfticionic.dao.ListaComentarios;
import edu.val.cfticionic.dao.ListaUsuariosRegistradosCFTIC;
import edu.val.cfticionic.dto.Comentario;
import edu.val.cfticionic.dto.UsuarioCFTIC;

/**
 * Application Lifecycle Listener implementation class EsuchaEnciendeLevantaSevidor
 *
 */
@WebListener
public class EsuchaEnciendeLevantaSevidor implements ServletContextListener {

	
	private final static Logger log = Logger.getLogger("mylog");
    /**
     * Default constructor. 
     */
    public EsuchaEnciendeLevantaSevidor() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	List<UsuarioCFTIC> lista_usuarios = ListaUsuariosRegistradosCFTIC.obtenerListaUsuariosRegisgtrados();
    	ListaUsuariosRegistradosCFTIC.guardarListaUsuarios(lista_usuarios);
    	log.debug("guardada lista de usuarios");
    	
    	int id = ListaComentarios.nextId();
    	ListaComentarios.guardarID(id);
    	
    	List<Comentario> lista_comentarios = ListaComentarios.obtenerListaComentarios();
    	ListaComentarios.guardarListaComentarios(lista_comentarios);
    	log.debug("guardada lista de comentarios");
    	
    	log.debug("Servidor destruido");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	List<UsuarioCFTIC> lista_usuarios = null;
    	List<Comentario> lista_comentario = null;
    	int id_semilla = -1;
         // TODO Auto-generated method stub
    
    	lista_usuarios = ListaUsuariosRegistradosCFTIC.cargarListaUsuariosRegisgtrados();
    	lista_comentario = ListaComentarios.cargarListaComentarios();
    	id_semilla = ListaComentarios.obtenerIdDisponible();
    	ListaComentarios.setSemilla(id_semilla);
    	
    	log.debug("lista_usuarios = " +lista_usuarios);
    	log.debug("lista_comentarios = " + lista_comentario);
    	log.debug("id_semilla = " +id_semilla);
    	log.debug("Servidor iniciado");
    	
    }
	
}
