package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.ProductoVO;

public class ProductoDAO {

	public static List<ProductoVO> findAllAlta() {
		
		ArrayList<ProductoVO> lista = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		Connection con = Conexion.getConexion();
		
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM productos WHERE baja = false");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setUsuario_id(rs.getInt("usuario_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setUrl_imagen(rs.getString("url_imagen"));
				producto.setBaja(rs.getBoolean("baja"));
				
				lista.add(producto);
			}
			
			return lista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return lista;
		
	}
	
	public static List<ProductoVO> findAll() {
		
		ArrayList<ProductoVO> lista = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		Connection con = Conexion.getConexion();
		
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM productos");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setUsuario_id(rs.getInt("usuario_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setUrl_imagen(rs.getString("url_imagen"));
				producto.setBaja(rs.getBoolean("baja"));
				
				lista.add(producto);
			}
			
			return lista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return lista;
		
	}
	
	public static List<ProductoVO> findAllByUsuarioId(int usuario_id) {
		
		ArrayList<ProductoVO> lista = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM productos WHERE usuario_id = ?");
			
			ps.setInt(1, usuario_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setUsuario_id(rs.getInt("usuario_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setUrl_imagen(rs.getString("url_imagen"));
				producto.setBaja(rs.getBoolean("baja"));
				
				lista.add(producto);
			}
		} catch (SQLException e) {
		    e.printStackTrace();
		    try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
	    }
		
		return lista;		
		
	}
	
	public static ProductoVO findByID(int id) {
		
		ProductoVO producto = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM productos WHERE id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setUsuario_id(rs.getInt("usuario_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setUrl_imagen(rs.getString("url_imagen"));
				producto.setBaja(rs.getBoolean("baja"));
				
			}
		} catch (SQLException e) {
		    e.printStackTrace();
		    try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
	    }
		
		return producto;		
		
	}
	
	public static boolean crearProducto(ProductoVO producto) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false); // Habilitar el modo de transacción explícita

			String sql = "INSERT INTO productos (usuario_id, nombre, descripcion, categoria_id, precio, impuesto,"
					+ " url_imagen, baja) VALUES (?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setInt(1, producto.getUsuario_id());
	        ps.setString(2, producto.getNombre());
	        ps.setString(3, producto.getDescripcion());
	        ps.setInt(4, producto.getCategoria_id());
	        ps.setDouble(5, producto.getPrecio());
	        ps.setDouble(6, producto.getImpuesto());
	        ps.setString(7, producto.getUrl_imagen());
	        ps.setBoolean(8, producto.isBaja());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				success = true;
				con.commit(); // Confirmar la transacción
			} else {
				con.rollback(); // Revertir la transacción si no se realizó la inserción
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (con != null) {
                try {
                    con.rollback(); // Revertir la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
		} 
		Conexion.desconectar();
		return success;
	}
    
    public static boolean updateProducto(ProductoVO producto) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false); // Habilitar el modo de transacción explícita

            String sql = "UPDATE productos SET usuario_id=?, nombre=?, descripcion=?, categoria_id=?, " +
                         "precio=?, impuesto=?, url_imagen=?, baja=? WHERE id=?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, producto.getUsuario_id());
	        ps.setString(2, producto.getNombre());
	        ps.setString(3, producto.getDescripcion());
	        ps.setInt(4, producto.getCategoria_id());
	        ps.setDouble(5, producto.getPrecio());
	        ps.setDouble(6, producto.getImpuesto());
	        ps.setString(7, producto.getUrl_imagen());
	        ps.setBoolean(8, producto.isBaja());
	        ps.setInt(9, producto.getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
                con.commit(); // Confirmar la transacción
            } else {
                con.rollback(); // Revertir la transacción si no se realizó la actualización
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback(); // Revertir la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        Conexion.desconectar(); // Cerrar la conexión
        return success;
    }
    
    
    
    public static boolean deleteProducto(int id) {
		
		boolean success = false;
		
		Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM productos WHERE id=?";
        
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
				success = true;
				conexion.commit(); // Confirmar la transacción
			} else {
				conexion.rollback(); // Revertir la transacción si no se realizó la inserción
			}
            
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        Conexion.desconectar();
        return success;
    }
    
	public static List<ProductoVO> getProductosPorPrecio(double precioMin, double precioMax) {
		List<ProductoVO> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM productos WHERE precio BETWEEN ? AND ?");
            ps.setDouble(1, precioMin);
            ps.setDouble(2, precioMax);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	ProductoVO producto = new ProductoVO();
            	
                // Asignar atributos del producto desde el ResultSet
            	producto.setId(rs.getInt("id"));
            	producto.setUsuario_id(rs.getInt("usuario_id"));
            	producto.setNombre(rs.getString("nombre"));
            	producto.setDescripcion(rs.getString("descripcion"));
            	producto.setCategoria_id(rs.getInt("categoria_id"));
            	producto.setPrecio(rs.getDouble("precio"));
            	producto.setImpuesto(rs.getDouble("impuesto"));
            	producto.setUrl_imagen(rs.getString("url_imagen"));
            	producto.setBaja(rs.getBoolean("baja"));
                
                // Agregar producto a la lista de productos
                lista.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.desconectar();
        }

        return lista;
	}
	
	public static List<ProductoVO> findTop3ByHighestId() {
	    List<ProductoVO> lista = new ArrayList<>();
	    Connection con = Conexion.getConexion();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT * FROM productos WHERE baja = false ORDER BY id DESC LIMIT 3";
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            ProductoVO producto = new ProductoVO();

	            producto.setId(rs.getInt("id"));
	            producto.setUsuario_id(rs.getInt("usuario_id"));
	            producto.setNombre(rs.getString("nombre"));
	            producto.setDescripcion(rs.getString("descripcion"));
	            producto.setCategoria_id(rs.getInt("categoria_id"));
	            producto.setPrecio(rs.getDouble("precio"));
	            producto.setImpuesto(rs.getDouble("impuesto"));
	            producto.setUrl_imagen(rs.getString("url_imagen"));
	            producto.setBaja(rs.getBoolean("baja"));

	            lista.add(producto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.desconectar();
	    }

	    return lista;
	}


}
