package control.usuario;

import java.io.IOException;
import java.sql.Timestamp;

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
@WebServlet("/mensaje")
public class MensajeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MensajeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		String mensaje = request.getParameter("mensaje");
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
		if (request.getSession().getAttribute("usuario") == null) {
				
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			if (UsuarioDAO.findByID(idUser) != null) {
				
				if (!mensaje.isEmpty()) {
					
					MensajeVO mensajeVO = new MensajeVO();
					mensajeVO.setEmisor_id(usuario.getId());
					mensajeVO.setReceptor_id(idUser);
					mensajeVO.setTexto(mensaje);
					mensajeVO.setFecha_envio(new Timestamp(System.currentTimeMillis()));
					MensajeDAO.crearMensaje(mensajeVO);
					
//					request.getRequestDispatcher("views/chat.jsp").forward(request, response);
					request.getRequestDispatcher("/chat").forward(request, response);
				} else {
					request.getRequestDispatcher("/chat").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("/chat").forward(request, response);
			}
			
		}
		
	}

}
