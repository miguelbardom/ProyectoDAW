package control.producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.CategoriaDAO;
import model.DAO.ProductoDAO;
import model.VO.CategoriaVO;
import model.VO.ProductoVO;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int id = Integer.parseInt(request.getParameter("id"));
		List<ProductoVO> productosCateg = new ArrayList<>();
		
		if (obtenerProductos(id).isEmpty()) {
			
		} else {
			productosCateg = obtenerProductos(id);
			
			request.setAttribute("catalogoCateg", productosCateg);
			
			CategoriaVO categoria = CategoriaDAO.findByID(id);
			request.setAttribute("categoria", categoria);
			
			request.getRequestDispatcher("views/categoria.jsp?id="+id).forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}
	
	private List<ProductoVO> obtenerProductos(int id) {
		
        List<CategoriaVO> results = new ArrayList<>();
        List<ProductoVO> productos = new ArrayList<>();
        List<ProductoVO> productosCateg = new ArrayList<>();
        
        results = CategoriaDAO.getAll();
        int idCat = -1;
        
        for (CategoriaVO categoria : results) {
        	
        	if (categoria.getId() != id) {
        		//categoria incorrecta
        	} else {
        		idCat = categoria.getId();
        		//obtener productos de categoria_id
        		productos = ProductoDAO.findAllAlta();
        	}
        }
        
        for (ProductoVO producto : productos) {
        	
        	if (producto.getCategoria_id() == idCat) {
        		productosCateg.add(producto);
        	}
        }
        
        return productosCateg;
        
	}

}
