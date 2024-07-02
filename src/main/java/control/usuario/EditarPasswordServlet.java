package control.usuario;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.StrongPasswordEncryptor;

import model.DAO.ProductoDAO;
import model.DAO.UsuarioDAO;
import model.VO.ProductoVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/editarPassword")
public class EditarPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("views/editarPassword.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String pagina = "views/editarPassword.jsp";
		
		String clave = request.getParameter("clave");
		String nuevaClave = request.getParameter("nuevaClave");
		String repNuevaClave = request.getParameter("repNuevaClave");
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
		if (!comprobarUsuario(usuario)) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			
		} else {
			
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			//comparar clave actual con la bd
			if (passwordEncryptor.checkPassword(clave, usuario.getClave())) {
				
//				request.setAttribute("validar", "Sesión iniciada");
//				System.out.println("contraseña actual correcta");
				
				if (nuevaClave.equals(repNuevaClave)) {
					
					String encryptedPassword = passwordEncryptor.encryptPassword(nuevaClave);
					usuario.setClave(encryptedPassword);
					
					if (UsuarioDAO.updateUsuario(usuario)) {
						//contraseña cambiada
						request.getSession().setAttribute("usuario", usuario);
						response.sendRedirect(request.getContextPath()+"/perfil");
					} else {
						//error consulta
						System.out.println("error consulta");
						request.getRequestDispatcher(pagina).forward(request, response);
					}
				} else {
					request.getRequestDispatcher(pagina).forward(request, response);
				}
				
			} else {
				System.out.println("contraseña actual incorrecta");
				
				request.setAttribute("validar", "Email y/o contraseña incorrectos");
				request.getRequestDispatcher(pagina).forward(request, response);
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
