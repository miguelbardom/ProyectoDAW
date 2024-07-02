package control.producto;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.ProductoDAO;
import model.DAO.TransaccionDAO;
import model.DAO.UsuarioDAO;
import model.VO.ProductoVO;
import model.VO.TransaccionVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class GestionTransaccionesUsuarioServlet
 */
@WebServlet("/gestionTransacciones")
public class GestionTransaccionesUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionTransaccionesUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
		if (request.getSession().getAttribute("usuario") == null) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			ArrayList<TransaccionVO> transacciones = (ArrayList<TransaccionVO>) TransaccionDAO.findAllByUsuarioId(usuario.getId());
			request.setAttribute("transacciones", transacciones);
			
			ArrayList<ProductoVO> productos = (ArrayList<ProductoVO>) ProductoDAO.findAll();
			request.setAttribute("productos", productos);
			
			ArrayList<UsuarioVO> usuarios = (ArrayList<UsuarioVO>) UsuarioDAO.getAll();
			request.setAttribute("usuarios", usuarios);
			
			request.getRequestDispatcher("views/transacciones.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
