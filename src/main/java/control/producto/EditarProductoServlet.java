package control.producto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.DAO.CategoriaDAO;
import model.DAO.ProductoDAO;
import model.VO.ProductoVO;
import model.VO.UsuarioVO;
import model.VO.CategoriaVO;

/**
 * Servlet implementation class EditarProductoServlet
 */
@WebServlet("/editar")
public class EditarProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarProductoServlet() {
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
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			ProductoVO producto = ProductoDAO.findByID(id);
			System.out.println(producto);
			
			request.setAttribute("producto", producto);
			
			List<CategoriaVO> categorias = new ArrayList<>();
			categorias = CategoriaDAO.getAll();
			request.setAttribute("categorias", categorias);
			
			request.getRequestDispatcher("views/editarProducto.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
		if (ServletFileUpload.isMultipartContent(request)) {
		    try {
		        // Crear un manejador de carga de archivos
		        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		        // Parsear la solicitud y obtener una lista de elementos de archivo
		        List<FileItem> items = upload.parseRequest(request);

		        // Declarar variables para los campos del formulario
		        int id = 0;
		        String nombre = null;
		        String descripcion = null;
		        int categoriaId = 0;
		        double precio = 0.0;
		        double impuesto = 4.0;
		        String img = null;
		        boolean baja = false;
		        
		        // Procesar cada elemento de archivo
		        for (FileItem item : items) {
		            if (item.isFormField()) {
		                // Es un campo de formulario regular
		                String fieldName = item.getFieldName();
		                String fieldValue = item.getString("UTF-8");
		                
		                System.out.println(fieldValue);

		                // Asignar valor a la variable correspondiente
		                switch (fieldName) {
		                	case "id":
		                		id = Integer.parseInt(fieldValue);
		                		break;
		                	case "nombre":
		                        nombre = fieldValue;
		                        break;
		                    case "descripcion":
		                    	descripcion = fieldValue;
		                    	break;
		                    case "categoria_id":
		                        categoriaId = Integer.parseInt(fieldValue);
		                        break;
		                    case "precio":
		                        precio = Double.parseDouble(fieldValue);
		                        break;
		                    case "baja":
		                        baja = Boolean.parseBoolean(fieldValue);
		                        break;
		                    default:
		                        // Otro campo de formulario (no se maneja en este ejemplo)
		                        break;
		                }
		            } else {
		            	if (item!=null) {
		            		// Es un campo de archivo (img en este caso)
			                String fileName = new File(item.getName()).getName();
			                String filePath = "C:/Users/Miguel/eclipse-workspace-web/Prueba"
			                		+ "/src/main/webapp/img/" + fileName;
			                String applicationPath = request.getServletContext().getRealPath("");
			                String path = applicationPath + "/img/" + fileName;
	
			                // Guardar el archivo en el servidor
//			                item.write(new File(path));
			                item.write(new File(filePath));
	
			                // Asignar la ruta del archivo a la variable img
			                img = "/img/"+fileName;
		            	}
		            }
		        }
		        
		        ProductoVO producto = new ProductoVO(id, usuario.getId(), nombre, descripcion, categoriaId, precio, impuesto,
		        		img, baja);
		        ProductoDAO.updateProducto(producto);
		        System.out.println(producto);

		        // Redireccionar a la página de productos después de guardar exitosamente
//		        request.getRequestDispatcher("views/admin/productos.jsp").forward(request, response);
		        response.sendRedirect(request.getContextPath()+"/gestionProductos");

		    } catch (Exception e) {
		        e.printStackTrace();
		        // Manejar la excepción si ocurre algún error durante el procesamiento
		        System.out.println(e);
		        request.getRequestDispatcher("views/editarProducto.jsp").forward(request, response);
		    }
		} else {
		    // La solicitud no es multipart (no es un formulario de carga de archivos)
			System.out.println("no es multipart");
		    request.getRequestDispatcher("views/editarProducto.jsp").forward(request, response);
		}
		
	}

}
