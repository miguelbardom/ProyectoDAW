package control.usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

//import control.log.Log;
import model.DAO.UsuarioDAO;
import model.VO.UsuarioVO;
import service.UsuarioService;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LogManager.getLogger(RegistroServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("views/registro.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
//		Log.log();
		
		String pagina = "views/registro.jsp";
		
		String nombre = request.getParameter("nombre");
		String ape = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		String claveRep = request.getParameter("claveRep");
		String fecha_nacimientoStr = request.getParameter("fecha_nacimiento");
		java.sql.Date fecha_nacimientoSql = null;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Convertir el String a java.util.Date
            Date fecha_nacimiento = dateFormat.parse(fecha_nacimientoStr);

            // Convertir java.util.Date a java.sql.Date
            fecha_nacimientoSql = new java.sql.Date(fecha_nacimiento.getTime());

            // Uso de la fecha de SQL Date
            System.out.println("Fecha de nacimiento (java.sql.Date): " + fecha_nacimientoSql);

        } catch (ParseException e) {
            System.out.println("Error al parsear la fecha: " + e.getMessage());
        }
		
		// Crear mapa para almacenar errores
	    Map<String, String> errores = new HashMap<>();
	    Map<String, String> recuerda = new HashMap<>();

	    // Validar los datos del formulario
	    boolean esValido = UsuarioService.validaRegistro(request, errores);
	    
	    //rellenar recuerda
	    recuerda.put("nombre", nombre);
	    recuerda.put("apellidos", ape);
	    recuerda.put("email", email);
	    recuerda.put("fecha", fecha_nacimientoStr);
		
		if (esValido) 
		{
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(clave);
//			System.out.println("encriptado: " + encryptedPassword);
//			logger.info("password encriptada");
			
			//comprobar el email en la bd
			UsuarioVO usuarioComprobar = UsuarioDAO.obtenerUsuarioPorEmail(email);
			
			if (usuarioComprobar != null) {
				
				request.setAttribute("validarMal", "El correo electrónico ya está registrado. Por favor, use otro.");
				request.setAttribute("recuerda", recuerda);
//			    logger.error("Error: correo electronico ya registrado");
				request.getRequestDispatcher(pagina).forward(request, response);
				
			} else {
				
				if (UsuarioDAO.crearUsuario(email, encryptedPassword, nombre, ape, fecha_nacimientoSql)) {
					request.setAttribute("validar", "Usuario " + nombre + " registrado con éxito!<br/>Ya puedes iniciar sesión");
					
//					pagina = "/index.jsp";
//					response.sendRedirect(request.getContextPath()+pagina);
//					logger.info("usuario registrado");
					request.getRequestDispatcher(pagina).forward(request, response);
				} else {
					//errores al hacer el insert en la base de datos: MANEJAR!
					request.setAttribute("validarMal", "algo incorrecto");
					request.setAttribute("recuerda", recuerda);
//					pagina = "registro.jsp";
//					logger.error("errores en el registro");
					request.getRequestDispatcher(pagina).forward(request, response);
				}
			}	
			
		} else {
			
			request.setAttribute("errores", errores);
			request.setAttribute("recuerda", recuerda);
			
			System.out.println(recuerda);
			
			request.getRequestDispatcher(pagina).forward(request, response);
			
		}
		
		/*
		if (nombre.isEmpty()) 
		{
			request.setAttribute("nombreVacio", "Nombre vacío");
			request.getRequestDispatcher(pagina).forward(request, response);
		} if (ape.isEmpty()) 
		{
			request.setAttribute("apeVacio", "Apellidos vacío");
			request.getRequestDispatcher(pagina).forward(request, response);
		} if (email.isEmpty()) 
		{
			request.setAttribute("emailVacio", "Email vacío");
			request.getRequestDispatcher(pagina).forward(request, response);
		} if (clave.isEmpty()) 
		{
			request.setAttribute("claveVacio", "Contraseña vacía");
			request.getRequestDispatcher(pagina).forward(request, response);
		} if (!clave.equals(repClave)) 
		{
			request.setAttribute("repClave", "La contraseña no coincide");
			request.getRequestDispatcher(pagina).forward(request, response);
		}
		else {
			
			if (usuarioDao.crearUsuario(email, clave, nombre, ape)) {
				request.setAttribute("validar", "Registro exitoso");
				pagina = "index.jsp";
			} else {
				request.setAttribute("validar", "algo incorrecto");
				pagina = "registro.jsp";
			}
			
			request.getRequestDispatcher("perfil.jsp").forward(request, response);
		}
		*/
		/*
		// Simulación de errores asociados a campos
        Map<String, String> errores = new HashMap<>();
        errores.put("nombre", "El nombre es requerido");
        errores.put("email", "El formato del correo electrónico es inválido");
        
        // Almacenar los errores como atributos en el request
        for (Map.Entry<String, String> entry : errores.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
		*/
		
	}

}
