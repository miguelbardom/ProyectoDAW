package control.usuario;

import java.io.IOException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MailContactoServlet
 */
@WebServlet("/contacto")
public class MailContactoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailContactoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("views/contacto.jsp").forward(request, response);
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
		String mensaje = request.getParameter("mensaje");
		
		String remitente = "tienda-online-cliente@outlook.com";
		String destinatario = "tienda-online-cliente@outlook.com";
		
		try {
			// Propiedades de la conexion
			Properties prop = new Properties();
			// Nombre del servidor de salida
//			prop.setProperty("mail.smtp.host", "smtp.office365.com");
			prop.setProperty("mail.smtp.host", "smtp-mail.outlook.com");
			// Habilitamos TLS
			prop.setProperty("mail.smtp.starttls.enable", "true");
			// Indicamos el puerto
			prop.setProperty("mail.smtp.port", "587");
			// Indicamos el usuario
			prop.setProperty("mail.smtp.user", "tienda-online-cliente@outlook.com");
			// Indicamos que requiere autenticación
//			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

			// Creamos un objeto sesion
			Session sesion = Session.getDefaultInstance(prop);
			
			//TODO
			sesion.setDebug(true);
			
			// Creamos un objeto mensaje a traves de la sesion
			MimeMessage message = new MimeMessage(sesion);
			
			// Indicamos la cuenta desde la que se va a enviar
			message.setFrom(new InternetAddress(remitente));

			// Añadimos el recipiente al mensaje al que va a ir dirigido el mensaje
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

			//TODO
			// Creamos el asunto del mensaje
			message.setSubject("Mensaje nuevo de un cliente");

			//TODO
			// Creamos el cuerpo del mensaje
			message.setText("Nombre: "+ nombre+"\n"+
					"Apellidos: "+ ape+"\n"+
					"Email: "+email+"\n"+
					"Mensaje: \n"+mensaje);

//			mensaje.setText(
//					"Esto es una prueba <br> con <b>JavaMail</b>",
//					"ISO-8859-1",
//					"html");
			
			// Utilizamos un objeto transport para hacer el envio indicando el protocolo
			Transport t = sesion.getTransport("smtp");
			// Hacemos la conexion
			t.connect("tienda-online-cliente@outlook.com", "cliente12345");
			// Enviamos el mensaje
			t.sendMessage(message, message.getAllRecipients());

			// Cerramos la conexion
			t.close();
			
//			String idioma = (String) request.getSession().getAttribute("idioma");
			
			String msj = "Mensaje enviado";
			request.setAttribute("msj",msj);
			request.getRequestDispatcher("views/contacto.jsp").forward(request, response);

		} catch (AddressException ex) {
			Logger.getLogger(MailContactoServlet.class.getName()).log(Level.SEVERE, null, ex);
			request.getRequestDispatcher("views/contacto.jsp").forward(request, response);
		} catch (MessagingException ex) {
			Logger.getLogger(MailContactoServlet.class.getName()).log(Level.SEVERE, null, ex);
			request.getRequestDispatcher("views/contacto.jsp").forward(request, response);
		}
		
		
		
		
		
	}

}
