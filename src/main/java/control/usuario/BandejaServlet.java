package control.usuario;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class BandejaServlet
 */
@WebServlet("/bandeja")
public class BandejaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BandejaServlet() {
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
			
			ArrayList<MensajeVO> chats = (ArrayList<MensajeVO>) MensajeDAO.obtenerChatsDeUsuario(usuario.getId());
			
			request.setAttribute("chats", chats);
			
			ArrayList<UsuarioVO> usuarios = (ArrayList<UsuarioVO>) UsuarioDAO.getAll();
			
			request.setAttribute("usuarios", usuarios);
			
			request.getRequestDispatcher("views/bandeja.jsp").forward(request, response);
			
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
