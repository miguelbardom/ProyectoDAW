package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.CategoriaDAO;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

//import control.log.Log;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import model.DAO.ProductoDAO;
import model.VO.CategoriaVO;
//import model.DAO.ProductoDAO;
//import model.VO.ProductoVO;
import model.VO.ProductoVO;

/**
 * Servlet implementation class EntradaServlet
 */

@WebServlet("")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LogManager.getLogger(IndexServlet.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String pagina = "views/index.jsp";
		
		//recuperar los datos (productos)
		request.setAttribute("catalogo", ProductoDAO.findAllAlta());
		
		//recuperar las categorias
		List<CategoriaVO> categorias = CategoriaDAO.getAll();
		request.setAttribute("categorias", categorias);
		
		//recuperar los datos (productos)
		List<ProductoVO> ultimosProd = ProductoDAO.findTop3ByHighestId();
		request.setAttribute("ultimosProd", ultimosProd);
		
		request.getRequestDispatcher(pagina).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
