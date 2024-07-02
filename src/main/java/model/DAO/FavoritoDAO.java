package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.FavoritoVO;

public class FavoritoDAO {
	
	public static boolean crearFavorito(FavoritoVO favorito) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false); // Habilitar el modo de transacción explícita

			String sql = "INSERT INTO favoritos (producto_id, usuario_id) VALUES (?, ?)";

			ps = con.prepareStatement(sql);
			ps.setInt(1, favorito.getProducto_id());
			ps.setInt(2, favorito.getUsuario_id());

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

	public static FavoritoVO findById(int id) {

		FavoritoVO favorito = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("SELECT * FROM favoritos WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				favorito = new FavoritoVO();

				favorito.setId(rs.getInt("id"));
				favorito.setProducto_id(rs.getInt("producto_id"));
				favorito.setUsuario_id(rs.getInt("usuario_id"));
				favorito.setBaja(rs.getBoolean("baja"));

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
		return favorito;

	}
	
	public static List<FavoritoVO> getAll() {
		
		ArrayList<FavoritoVO> lista = new ArrayList<FavoritoVO>();
		FavoritoVO favorito = null;
		
		Connection conexion = Conexion.getConexion();
        
        String sql = "SELECT * FROM favoritos";
        try (Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
            	favorito = new FavoritoVO();

				favorito.setId(rs.getInt("id"));
				favorito.setProducto_id(rs.getInt("producto_id"));
				favorito.setUsuario_id(rs.getInt("usuario_id"));
				favorito.setBaja(rs.getBoolean("baja"));

                // Agregar el objeto a la lista de resultados
                lista.add(favorito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return lista;
    }
	
	public static boolean update(FavoritoVO favorito) {
		
		boolean success = false;
		
	    Connection conexion = Conexion.getConexion();
	    String sql = "UPDATE favoritos SET producto_id=?, usuario_id=?, baja=? WHERE id=?";
	    
	    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	    	conexion.setAutoCommit(false);
	    	
	    	statement.setInt(1, favorito.getProducto_id());
	    	statement.setInt(2, favorito.getUsuario_id());
	    	statement.setBoolean(3, favorito.isBaja());
	    	statement.setInt(4, favorito.getId());
	        
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

}
