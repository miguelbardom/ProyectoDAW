package control.producto;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IndexServlet;
import control.usuario.LoginServlet;
import model.DAO.MensajeDAO;
import model.DAO.ProductoDAO;
import model.DAO.TransaccionDAO;
import model.VO.MensajeVO;
import model.VO.ProductoVO;
import model.VO.TransaccionVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class ComprarServlet
 */
@WebServlet("/comprar")
public class ComprarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LogManager.getLogger(IndexServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		Log.log();
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		HashMap<Integer, String> alert = new HashMap<Integer, String>();
		
		if (request.getSession().getAttribute("usuario") == null) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			if (request.getParameter("id") != null) {
				
				if (ProductoDAO.findByID(id) != null) {
					
					ProductoVO producto = ProductoDAO.findByID(id);
					System.out.println(producto);
					
					//el comprador no puede comprar sus productos
					if (producto.getUsuario_id() == usuario.getId()) {
						
						
						System.out.println("Error: Intento de compra de un producto por el usuario propietario");
						String mensaje = "Error: No puedes comprar un producto tuyo";
						alert.put(id, mensaje);
						request.setAttribute("alert", alert);
						
						redireccion(request, response);
					} else {
						
						//COMPROBAR QUE UN USUARIO SOLO PUEDE COMPRAR 1 VEZ UN PRODUCTO (en estado PE)
						if (!TransaccionDAO.existeProductoYComprador(id, usuario.getId())) {
							
							request.setAttribute("producto", producto);
							
							//en lugar de comprar directamente, iniciaremos una transaccion en estado pedido
							//la tiene que aprobar el vendedor
							TransaccionVO transaccion = new TransaccionVO();
							transaccion.setComprador_id(usuario.getId());
							transaccion.setVendedor_id(producto.getUsuario_id());
							transaccion.setProducto_id(producto.getId());
							transaccion.setMetodo_pago(null);
							transaccion.setTotal(producto.getPrecio());
							transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
							transaccion.setEstado("Pendiente de Envío");
							TransaccionDAO.crearTransaccion(transaccion);
							
							//mensaje automatico
							MensajeVO mensaje = new MensajeVO();
							mensaje.setEmisor_id(usuario.getId());
							mensaje.setReceptor_id(producto.getUsuario_id());
							mensaje.setTexto("Mensaje automático:\n"
									+ "Solicitud de compra del producto: "+producto.getNombre()+".\n"
									+ "Vaya al menú de gestión de productos en su perfil para aceptar o cancelar la compra.");
							mensaje.setFecha_envio(new Timestamp(System.currentTimeMillis()));
							MensajeDAO.crearMensaje(mensaje);
							
							request.getRequestDispatcher("views/solicitarCompra.jsp").forward(request, response);
							
						} else {
							
							System.out.println("Error: Intento de compra de un producto más de una vez");
							String mensaje = "Error: Ya has solicitado la compra de este producto";
							alert.put(id, mensaje);
							request.setAttribute("alert", alert);
							
							redireccion(request, response);
						}
						
					}
					
				} else {
					System.out.println("id incorrecto. no existe el producto");
					redireccion(request, response);
				}
			} else {
				System.out.println("id nulo");
				redireccion(request, response);
			}
			
//			String vistaOrigen = request.getParameter("vista");
//			String categoria_id = request.getParameter("categoria_id");
//			
//			if (vistaOrigen.equals("")) 
//			{
//				request.getRequestDispatcher("/").forward(request, response);
//				
//			} else if (vistaOrigen.equals("producto")) 
//			{
//				request.getRequestDispatcher("/producto").forward(request, response);
//				
//			} 
//			else if (vistaOrigen.equals("categoria")) {
//				request.getRequestDispatcher("/categoria?id="+categoria_id).forward(request, response);
//			}
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
	}
	
	private void redireccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vistaOrigen = request.getParameter("vista");
		String categoria_id = request.getParameter("categoria_id");
		
		if (vistaOrigen == null) {
			
			request.getRequestDispatcher("/").forward(request, response);
			
		} else if (vistaOrigen.equals("producto")) {
			
			request.getRequestDispatcher("/producto").forward(request, response);
			
		} else if (vistaOrigen.equals("categoria")) {
			
			request.getRequestDispatcher("/categoria?id="+categoria_id).forward(request, response);
		}
	}

}
