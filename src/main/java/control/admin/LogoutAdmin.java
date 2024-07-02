package control.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutAdmin
 */
@WebServlet("/logoutA")
public class LogoutAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            response.getWriter().println("Sesión eliminada correctamente");
        } else {
            response.getWriter().println("No hay sesión activa para eliminar");
        }
		
//		if (request.getSession().getAttribute("usuarioA") != null) 
//		{
//			request.getSession().removeAttribute("usuarioA");
//		}
//		request.getRequestDispatcher("index.jsp").forward(request, response);
        
		response.sendRedirect(request.getContextPath()+"/acceso");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
