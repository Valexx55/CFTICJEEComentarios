package edu.val.cfticionic.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroDni
 */
@WebFilter("/calculaletra/*")
public class FiltroDni implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroDni() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		System.out.println("pasa por el filtro a la ida");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		System.out.println("pasa por el filtro a la vuelta");
		HttpServletResponse hr = (HttpServletResponse)response;
		hr.setHeader("Access-Control-Allow-Origin", "*");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
