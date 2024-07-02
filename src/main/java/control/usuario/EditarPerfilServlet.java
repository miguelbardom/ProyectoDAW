package control.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.UsuarioDAO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class EditarPerfilServlet
 */
@WebServlet("/editarPerfil")
public class EditarPerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfilServlet() {
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
		
		if (!comprobarUsuario(usuario)) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			request.getRequestDispatcher("views/editarPerfil.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		String nombre = request.getParameter("nombre");
		String ape = request.getParameter("apellidos");
		String email = request.getParameter("email");
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
		if (!comprobarUsuario(usuario)) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			
		} else {
			
			usuario.setNombre(nombre);
			usuario.setApellidos(ape);
			
			if (UsuarioDAO.updateUsuario(usuario)) {
				
				request.getSession().setAttribute("usuario", usuario);
				response.sendRedirect(request.getContextPath()+"/perfil");
			} else {
				//error consulta
				System.out.println("error consulta");
				request.getRequestDispatcher("views/editarPerfil.jsp").forward(request, response);
			}
			
		}
		
	}
	
	private boolean comprobarUsuario(UsuarioVO usuario) {
		
		if (usuario == null) {
			//redireccionar al carrito de nuevo, despues de logear
			return false;
		} else {
			return true;
		}
	}

}
