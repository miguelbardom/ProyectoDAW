package control.usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.MensajeDAO;
import model.DAO.UsuarioDAO;
import model.VO.MensajeVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class MensajeServlet
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
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
			if (request.getParameter("idUser") == null) {
				
				request.getRequestDispatcher("views/bandeja.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		int id = Integer.parseInt(request.getParameter("id"));
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		HashMap<Integer, String> alert = new HashMap<Integer, String>();
		
		if (request.getSession().getAttribute("usuario") == null) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			if (request.getParameter("idUser") != null) {
				
				if (UsuarioDAO.findByID(idUser) != null) {
					
					if (usuario.getId() == idUser) {
						
						String mensaje = "Error: No puedes enviarte un mensaje a tí mismo";
						alert.put(id, mensaje);
						request.setAttribute("alert", alert);
						request.getRequestDispatcher("views/producto.jsp?id="+id).forward(request, response);
					} else {
						
						UsuarioVO vendedor = UsuarioDAO.findByID(idUser);
						request.setAttribute("vendedor", vendedor);
						
						ArrayList<MensajeVO> lista = (ArrayList<MensajeVO>) MensajeDAO.findByEmisorReceptor(usuario, vendedor);
						request.setAttribute("lista", lista);
						
						request.getRequestDispatcher("views/chat.jsp").forward(request, response);
					}
					
				}
				
			}
			
		}
		
	}

}
