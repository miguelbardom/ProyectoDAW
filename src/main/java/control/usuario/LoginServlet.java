package control.usuario;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.StrongPasswordEncryptor;

import control.IndexServlet;
//import control.log.Log;
import model.DAO.ProductoDAO;
import model.DAO.UsuarioDAO;
import model.VO.ProductoVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("views/login.jsp").forward(request, response);
		
		/*
		if (request.getParameter("Home_Login") != null) {
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else if (request.getParameter("Home_Registro") != null) {
			
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			
		} else if (request.getParameter("Home_Perfil") != null) {
			
			request.getRequestDispatcher("perfil.jsp").forward(request, response);
			
		} else if (request.getParameter("Home_Logout") != null) {
			
			request.getSession().removeAttribute("usuario");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else if (request.getParameter("Home_Logo") != null) {
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
//		Log.log();
		
		String pagina = "views/login.jsp";
		
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		
		if (email.isEmpty()) 
		{
			request.setAttribute("emailVacio", "Email vacío");
//			request.getRequestDispatcher(pagina).forward(request, response);
		} if (clave.isEmpty()) 
		{
			request.setAttribute("claveVacio", "Contraseña vacía");
//			request.getRequestDispatcher(pagina).forward(request, response);
		} if (email.isEmpty() && clave.isEmpty()) {
			request.getRequestDispatcher(pagina).forward(request, response);
		} else 
		{
			//comprobar si existe el email
			if (UsuarioDAO.obtenerUsuarioPorEmail(email) != null) {
				
				UsuarioVO usuario = UsuarioDAO.obtenerUsuarioPorEmail(email);
				System.out.println(usuario);
				
				//comprobar rol del usuario
				if (usuario.getRol_id() != 1) {
					System.out.println("Rol incorrecto");
//					logger.info("Rol incorrecto");
					
					request.setAttribute("validar", "Rol de usuario incorrecto");
					request.getRequestDispatcher(pagina).forward(request, response);
				} else {
					System.out.println("Rol de usuario, es correcto");
//					logger.info("Rol de usuario, es correcto");
					
					try {
						StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
						
						//comparar clave con la bd
						if (passwordEncryptor.checkPassword(clave, usuario.getClave())) {
//							request.setAttribute("validar", "Sesión iniciada");
							System.out.println("Clave correcta");
//							logger.info("clave correcta");
							
							request.getSession().setAttribute("usuario", usuario);
							
							response.sendRedirect(request.getContextPath()+"/");
							
						} else {
							System.out.println("Clave incorrecta");
//							logger.info("clave incorrecta");
							
							request.setAttribute("validar", "Email y/o contraseña incorrectos");
							request.getRequestDispatcher(pagina).forward(request, response);
						}
						
					} catch(EncryptionOperationNotPossibleException e) {
						// Manejo de la excepción (por ejemplo, registro de error)
						System.out.println("Error al verificar la contraseña: " + e.getMessage());
//						logger.error("Error al verificar la contraseña: " + e.getMessage());
						// Otra lógica de manejo de errores si es necesario
						request.setAttribute("validar", "Email y/o contraseña incorrectos");
						request.getRequestDispatcher(pagina).forward(request, response);
					}
					
				}
				
			} else {
				request.setAttribute("validar", "Email y/o contraseña incorrectos");
				request.getRequestDispatcher(pagina).forward(request, response);
			}
			
		}
//		request.getRequestDispatcher(pagina).forward(request, response);
			
		
	}

}
