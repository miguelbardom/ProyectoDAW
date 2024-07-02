package control.producto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.TransaccionDAO;
import model.VO.TransaccionVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class CancelarCompraServlet
 */
@WebServlet("/cancelarCompra")
public class CancelarCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarCompraServlet() {
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
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
		if (request.getSession().getAttribute("usuario") == null) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			if (request.getParameter("id") != null) {
				
				TransaccionVO transaccion = TransaccionDAO.findById(id);
				
				if (transaccion.getComprador_id() == usuario.getId()) {
					
					if (transaccion.getEstado().equals("Pendiente de Envío")) {
						
						transaccion.setEstado("Cancelado");
						TransaccionDAO.updateTransaccion(transaccion);
					}
				}
				
			}
			response.sendRedirect(request.getContextPath()+"/gestionTransacciones");
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
