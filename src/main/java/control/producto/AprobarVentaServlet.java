package control.producto;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.MensajeDAO;
import model.DAO.ProductoDAO;
import model.DAO.TransaccionDAO;
import model.VO.MensajeVO;
import model.VO.ProductoVO;
import model.VO.TransaccionVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class ConfirmarServlet
 */
@WebServlet("/aprobarVenta")
public class AprobarVentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AprobarVentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int id = Integer.parseInt(request.getParameter("id"));
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
		if (request.getSession().getAttribute("usuario") == null) {
					
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			if (request.getParameter("id") != null) {
				
				TransaccionVO transaccion = TransaccionDAO.findById(id);

				if (transaccion.getVendedor_id() == usuario.getId()) {

					if (transaccion.getEstado().equals("Pendiente de Envío")) {
						
						List<ProductoVO> productos = ProductoDAO.findAll();
						ProductoVO producto = null;
						for (ProductoVO pro : productos) {
							if (transaccion.getProducto_id() == pro.getId()) {
								producto = pro;
							}
						}
						
						// cancelar el resto de transacciones que querían comprar el mismo producto
						List<TransaccionVO> transacciones = TransaccionDAO.getAll();
						for (TransaccionVO tra : transacciones) {

							if (tra.getProducto_id() == transaccion.getProducto_id()) {
								
								if (tra.getId() != transaccion.getId()) {
									
									tra.setEstado("Cancelado");
									TransaccionDAO.updateTransaccion(tra);
									
									if (tra.getComprador_id() != transaccion.getComprador_id()) {
										
										if (producto != null) {
											//mensaje automatico
											MensajeVO mensaje = new MensajeVO();
											mensaje.setEmisor_id(usuario.getId());
											mensaje.setReceptor_id(tra.getComprador_id());
											mensaje.setTexto("Mensaje automático:\n"
													+ "Tu solicitud de compra del producto: "+producto.getNombre()+" ha sido cancelada.\n"
													+ "Lo sentimos.");
											mensaje.setFecha_envio(new Timestamp(System.currentTimeMillis()));
											MensajeDAO.crearMensaje(mensaje);
										}
									}
								}
							}

						}

						transaccion.setEstado("Completado");
						TransaccionDAO.updateTransaccion(transaccion);
						
						if (producto != null) {
							
							//mensaje automatico
							MensajeVO mensaje = new MensajeVO();
							mensaje.setEmisor_id(usuario.getId());
							mensaje.setReceptor_id(transaccion.getComprador_id());
							mensaje.setTexto("Mensaje automático:\n"
									+ "Tu solicitud de compra del producto: "+producto.getNombre()+" ha sido aprobada.\n"
									+ "El producto ha sido enviado.");
							mensaje.setFecha_envio(new Timestamp(System.currentTimeMillis()));
							MensajeDAO.crearMensaje(mensaje);
						}
						
						producto.setBaja(true);
						ProductoDAO.updateProducto(producto);
						
					}
				}
				
			}
			response.sendRedirect(request.getContextPath()+"/gestionTransacciones");
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
