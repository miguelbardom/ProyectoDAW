package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.MensajeVO;
import model.VO.UsuarioVO;

public class MensajeDAO {
	
	public static boolean crearMensaje(MensajeVO mensaje) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false); // Habilitar el modo de transacción explícita

			String sql = "INSERT INTO mensajes (emisor_id, receptor_id, texto, fecha_envio) VALUES (?, ?, ?, ?)";

			ps = con.prepareStatement(sql);
			ps.setInt(1, mensaje.getEmisor_id());
			ps.setInt(2, mensaje.getReceptor_id());
			ps.setString(3, mensaje.getTexto());
			ps.setTimestamp(4, mensaje.getFecha_envio());

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
	
	public static List<MensajeVO> obtenerChatsDeUsuario(int usuarioId) {
        List<MensajeVO> listaMensajes = new ArrayList<>();
        String sql = "SELECT * FROM mensajes m1 " +
                "WHERE fecha_envio = (SELECT MAX(fecha_envio) " +
                "FROM mensajes m2 " +
                "WHERE (m2.emisor_id = m1.emisor_id AND m2.receptor_id = m1.receptor_id) " +
                "   OR (m2.emisor_id = m1.receptor_id AND m2.receptor_id = m1.emisor_id)) " +
                "AND (m1.emisor_id = ? OR m1.receptor_id = ?) " +
                "ORDER BY fecha_envio DESC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, usuarioId);
            ps.setInt(2, usuarioId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MensajeVO mensaje = new MensajeVO();
                    mensaje.setId(rs.getInt("id"));
                    mensaje.setEmisor_id(rs.getInt("emisor_id"));
                    mensaje.setReceptor_id(rs.getInt("receptor_id"));
                    mensaje.setTexto(rs.getString("texto"));
                    mensaje.setFecha_envio(rs.getTimestamp("fecha_envio"));
                    listaMensajes.add(mensaje);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return listaMensajes;
    }
	
    public static List<MensajeVO> findByEmisorReceptor(UsuarioVO emisor, UsuarioVO receptor) {
        List<MensajeVO> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();

        String sql = "SELECT * FROM mensajes "
                   + "WHERE (emisor_id = ? AND receptor_id = ?) "
                   + "   OR (emisor_id = ? AND receptor_id = ?) "
                   + "ORDER BY fecha_envio";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int idEmisor = emisor.getId();
            int idReceptor = receptor.getId();
            ps.setInt(1, idEmisor);
            ps.setInt(2, idReceptor);
            ps.setInt(3, idReceptor);
            ps.setInt(4, idEmisor);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MensajeVO mensaje = new MensajeVO();
                    mensaje.setId(rs.getInt("id"));
                    mensaje.setEmisor_id(rs.getInt("emisor_id"));
                    mensaje.setReceptor_id(rs.getInt("receptor_id"));
                    mensaje.setTexto(rs.getString("texto"));
                    mensaje.setFecha_envio(rs.getTimestamp("fecha_envio"));

                    // Agregar el objeto a la lista de resultados
                    lista.add(mensaje);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return lista;
    }

	public static MensajeVO findById(int id) {

		MensajeVO mensaje = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("SELECT * FROM mensajes WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				mensaje = new MensajeVO();

				mensaje.setId(rs.getInt("id"));
				mensaje.setEmisor_id(rs.getInt("emisor_id"));
				mensaje.setReceptor_id(rs.getInt("receptor_id"));
				mensaje.setTexto(rs.getString("texto"));
				mensaje.setFecha_envio(rs.getTimestamp("fecha_envio"));

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
		return mensaje;

	}
	
	public static List<MensajeVO> getAll() {
		
		ArrayList<MensajeVO> lista = new ArrayList<MensajeVO>();
		MensajeVO mensaje = null;
		
		Connection conexion = Conexion.getConexion();
        
        String sql = "SELECT * FROM mensajes";
        try (Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
            	mensaje = new MensajeVO();

            	mensaje.setId(rs.getInt("id"));
				mensaje.setEmisor_id(rs.getInt("emisor_id"));
				mensaje.setReceptor_id(rs.getInt("receptor_id"));
				mensaje.setTexto(rs.getString("texto"));
				mensaje.setFecha_envio(rs.getTimestamp("fecha_envio"));

                // Agregar el objeto a la lista de resultados
                lista.add(mensaje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return lista;
    }
	
	public static boolean update(MensajeVO mensaje) {
		
		boolean success = false;
		
	    Connection conexion = Conexion.getConexion();
	    String sql = "UPDATE mensajes SET emisor_id=?, receptor_id=?, texto=?, fecha_envio=? WHERE id=?";
	    
	    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	    	conexion.setAutoCommit(false);
	    	
	    	statement.setInt(1, mensaje.getEmisor_id());
	    	statement.setInt(2, mensaje.getReceptor_id());
	    	statement.setString(3, mensaje.getTexto());
	    	statement.setTimestamp(4, mensaje.getFecha_envio());
	    	
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
