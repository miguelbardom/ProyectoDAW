package control.producto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import control.IndexServlet;
//import control.log.Log;
import control.usuario.LoginServlet;
import model.DAO.ProductoDAO;
import model.VO.ProductoVO;

/**
 * Servlet implementation class ComprarServlet
 */
@WebServlet("/precio")
public class FiltrarPrecioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LogManager.getLogger(IndexServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrarPrecioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		Log.log();
		
		// Obtener parámetros de precio mínimo y máximo del request
        double precioMin = Double.parseDouble(request.getParameter("precio_min"));
        double precioMax = Double.parseDouble(request.getParameter("precio_max"));

        // Obtener productos filtrados por el rango de precios
        List<ProductoVO> productos = ProductoDAO.getProductosPorPrecio(precioMin, precioMax);

        // Guardar productos en el request como atributo para mostrar en la vista
        request.setAttribute("productosFiltro", productos);
        request.setAttribute("precioMin", precioMin);
        request.setAttribute("precioMax", precioMax);

        // Redirigir a la página de visualización de productos
//        request.getRequestDispatcher("views/index.jsp").forward(request, response);
		
			
		request.getRequestDispatcher("/").forward(request, response);
			
			
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		
		
		
	}

}
