package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.TransaccionVO;

public class TransaccionDAO {

	public static boolean crearTransaccion(TransaccionVO transaccion) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false); // Habilitar el modo de transacción explícita

			String sql = "INSERT INTO transacciones (comprador_id, vendedor_id, producto_id, metodo_pago,"
					+ " total, fecha, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

			ps = con.prepareStatement(sql);
			ps.setInt(1, transaccion.getComprador_id());
			ps.setInt(2, transaccion.getVendedor_id());
			ps.setInt(3, transaccion.getProducto_id());
			ps.setString(4, transaccion.getMetodo_pago());
			ps.setDouble(5, transaccion.getTotal());
			ps.setTimestamp(6, transaccion.getFecha());
			ps.setString(7, transaccion.getEstado());

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

	public static TransaccionVO findById(int id) {

		TransaccionVO transaccion = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("SELECT * FROM transacciones WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				transaccion = new TransaccionVO();

				transaccion.setId(rs.getInt("id"));
				transaccion.setComprador_id(rs.getInt("comprador_id"));
				transaccion.setVendedor_id(rs.getInt("vendedor_id"));
				transaccion.setProducto_id(rs.getInt("producto_id"));
				transaccion.setMetodo_pago(rs.getString("metodo_pago"));
				transaccion.setTotal(rs.getDouble("total"));
				transaccion.setFecha(rs.getTimestamp("fecha"));
				transaccion.setEstado(rs.getString("estado"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		Conexion.desconectar();
		return transaccion;

	}
	
	public static List<TransaccionVO> findAllByUsuarioId(int usuario_id) {
		
		ArrayList<TransaccionVO> lista = new ArrayList<TransaccionVO>();
		TransaccionVO transaccion = null;
		
		Connection conexion = Conexion.getConexion();
        
		String sql = "SELECT * FROM transacciones WHERE comprador_id = ? OR vendedor_id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, usuario_id);
            ps.setInt(2, usuario_id);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transaccion = new TransaccionVO();

                    transaccion.setId(rs.getInt("id"));
                    transaccion.setComprador_id(rs.getInt("comprador_id"));
                    transaccion.setVendedor_id(rs.getInt("vendedor_id"));
                    transaccion.setProducto_id(rs.getInt("producto_id"));
                    transaccion.setMetodo_pago(rs.getString("metodo_pago"));
                    transaccion.setTotal(rs.getDouble("total"));
                    transaccion.setFecha(rs.getTimestamp("fecha"));
                    transaccion.setEstado(rs.getString("estado"));

                    // Agregar el objeto a la lista de resultados
                    lista.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return lista;
    }
	
	public static List<TransaccionVO> getAll() {
		
		ArrayList<TransaccionVO> lista = new ArrayList<TransaccionVO>();
		TransaccionVO transaccion = null;
		
		Connection conexion = Conexion.getConexion();
        
        String sql = "SELECT * FROM transacciones";
        try (Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
            	transaccion = new TransaccionVO();

            	transaccion.setId(rs.getInt("id"));
				transaccion.setComprador_id(rs.getInt("comprador_id"));
				transaccion.setVendedor_id(rs.getInt("vendedor_id"));
				transaccion.setProducto_id(rs.getInt("producto_id"));
				transaccion.setMetodo_pago(rs.getString("metodo_pago"));
				transaccion.setTotal(rs.getDouble("total"));
				transaccion.setFecha(rs.getTimestamp("fecha"));
				transaccion.setEstado(rs.getString("estado"));

                // Agregar el objeto a la lista de resultados
                lista.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return lista;
    }
	
	public static boolean existeProductoYComprador(int producto_id, int comprador_id) {
		
        boolean existe = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            String sql = "SELECT 1 FROM transacciones WHERE comprador_id = ? AND producto_id = ? AND estado = 'PE'";
            ps = con.prepareStatement(sql);
            ps.setInt(1, comprador_id);
            ps.setInt(2, producto_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return existe;
    }

	public static int obtenerUltimoId() {
		int ultimoId = 0;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
//			String sql = "SELECT MAX(id) AS max_id FROM transacciones";
			String sql = "SELECT id FROM transacciones ORDER BY id DESC LIMIT 1";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				ultimoId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		Conexion.desconectar();
		return ultimoId;
	}
	
	public static boolean updateTransaccion(TransaccionVO transaccion) {
		
		boolean success = false;
		
	    Connection conexion = Conexion.getConexion();
	    String sql = "UPDATE transacciones SET metodo_pago=?, total=?, fecha=?, estado=? WHERE id=?";
	    
	    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	    	conexion.setAutoCommit(false);
	    	
	        statement.setString(1, transaccion.getMetodo_pago());
	        statement.setDouble(2, transaccion.getTotal());
	        statement.setTimestamp(3, transaccion.getFecha());
	        statement.setString(4, transaccion.getEstado());
	        statement.setInt(5, transaccion.getId());
	        
	        int rowsUpdated = statement.executeUpdate();
	        System.out.println("Filas actualizadas: " + rowsUpdated); // Depuración
//	        return rowsUpdated > 0;
	        
	        if (rowsUpdated > 0) {
				success = true;
				conexion.commit(); // Confirmar la transacción
			} else {
				conexion.rollback(); // Revertir la transacción si no se realizó la inserción
			}
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    }
	    Conexion.desconectar();
	    return success;
	}

	
	public static boolean deleteTransaccion(int id) {
		
		boolean success = false;
		
		Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM transacciones WHERE id=?";
        
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
	
	public static TransaccionVO buscarUltimaTransaccion() {
        TransaccionVO ultTransaccion = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            String sql = "SELECT * FROM transacciones ORDER BY id DESC LIMIT 1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                ultTransaccion = new TransaccionVO();
                ultTransaccion.setId(rs.getInt("id"));
				ultTransaccion.setComprador_id(rs.getInt("comprador_id"));
				ultTransaccion.setVendedor_id(rs.getInt("vendedor_id"));
				ultTransaccion.setProducto_id(rs.getInt("producto_id"));
				ultTransaccion.setMetodo_pago(rs.getString("metodo_pago"));
				ultTransaccion.setTotal(rs.getDouble("total"));
				ultTransaccion.setFecha(rs.getTimestamp("fecha"));
				ultTransaccion.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
			e.printStackTrace();
			
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
        Conexion.desconectar();
        return ultTransaccion;
    }

}
