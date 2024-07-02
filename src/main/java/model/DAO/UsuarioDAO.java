package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import conexion.Conexion;
import model.VO.UsuarioVO;

public class UsuarioDAO {
	
	private static Connection conexion;
	
	public static boolean crearUsuario(String email, String clave, String nombre, String apellidos, Date fecha_nacimiento) {
		
		conexion = Conexion.getConexion();
		boolean creado = false;
		
		try {
			
			if (conexion != null) {
				
				String query = "INSERT INTO usuarios (email, clave, nombre, apellidos, fecha_nacimiento) "
						+ "VALUES (?, ?, ?, ?, ?)";

				
				PreparedStatement ps = conexion.prepareStatement(query);
	            ps.setString(1, email);
	            ps.setString(2, clave);
	            ps.setString(3, nombre);
	            ps.setString(4, apellidos);
	            ps.setDate(5, fecha_nacimiento);
	            
//	            int filasInsertadas = ps.executeUpdate();

	            if (ps.executeUpdate() > 0) {
	                System.out.println("Usuario creado correctamente");
	                conexion.commit();
	                creado = true;
	            } else {
	                System.out.println("Error al insertar usuario");
	            }
				
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
		return creado;
		
	}
	
    public static UsuarioVO findByID(int id) {
        Connection con = Conexion.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioVO usuario = null;

        try {
            ps = con.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
            	usuario = new UsuarioVO();

            	usuario.setId(rs.getInt("id"));
                usuario.setRol_id(rs.getInt("rol_id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setBaja(rs.getBoolean("baja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        Conexion.desconectar();
        return usuario;
    }
	
	public static boolean deleteUsuario(int id) {
		
		boolean success = false;
		
		conexion = Conexion.getConexion();
        String sql = "DELETE FROM usuarios WHERE id=?";
        
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
	
	public static List<UsuarioVO> getAll() {
		
		conexion = Conexion.getConexion();
        List<UsuarioVO> results = new ArrayList<>();
        
        String sql = "SELECT * FROM usuarios";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                UsuarioVO usuario = new UsuarioVO();
                usuario.setId(resultSet.getInt("id"));
                usuario.setRol_id(resultSet.getInt("rol_id"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setClave(resultSet.getString("clave"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellidos(resultSet.getString("apellidos"));
                usuario.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                usuario.setBaja(resultSet.getBoolean("baja"));

                // Agregar el objeto a la lista de resultados
                results.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return results;
    }
	
	public static boolean updateUsuario(UsuarioVO usuario) {
		
		boolean success = false;
		
		conexion = Conexion.getConexion();
		
        String sql = "UPDATE usuarios SET rol_id=?, clave=?, nombre=?, apellidos=?, fecha_nacimiento=?,"
        		+ " baja=? WHERE email=?";
        
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        	
            UsuarioVO usuarioSt = usuario;
            statement.setInt(1, usuarioSt.getRol_id());
            statement.setString(2, usuarioSt.getClave());
            statement.setString(3, usuarioSt.getNombre());
            statement.setString(4, usuarioSt.getApellidos());
            statement.setDate(5,  usuarioSt.getFecha_nacimiento());
            statement.setBoolean(6, usuarioSt.isBaja());
            statement.setString(7, usuarioSt.getEmail());

            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
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

	public static boolean validarUsuarioCliente(String email, String clave) {
		
		boolean existe = false;
		conexion = Conexion.getConexion();
		
		try {
			
			if (conexion != null) {
				
				String query = "SELECT * FROM usuarios WHERE email = ? AND clave = ? AND rol_id = 1";
				
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setString(1, email);
	            ps.setString(2, clave);
	            
	            ResultSet rs = ps.executeQuery();
	            
	            if(rs.next()) {
	            	existe = true;
	            }
				
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
		return existe;
		
	}
	
	public static boolean validarUsuarioAdmin(String email, String clave) {
		
		boolean existe = false;
		conexion = Conexion.getConexion();
		
		try {
			
			if (conexion != null) {
				
				String query = "SELECT * FROM usuarios WHERE email = ? AND clave = ? AND rol_id = 2 ";
				
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setString(1, email);
	            ps.setString(2, clave);
	            
	            ResultSet rs = ps.executeQuery();
	            
	            if(rs.next()) {
	            	existe = true;
	            }
				
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
		return existe;
		
	}
	
	public static UsuarioVO obtenerUsuarioPorEmail(String email) {
        UsuarioVO usuario = null;
        conexion = Conexion.getConexion();
        
        try {
            if (conexion != null) {
                String query = "SELECT * FROM usuarios WHERE email = ?";
                
                PreparedStatement ps = conexion.prepareStatement(query);
                ps.setString(1, email);
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    // Extraer los datos del ResultSet y crear un objeto UsuarioVO
                    int id = rs.getInt("id");
                    int rol_id = rs.getInt("rol_id");
                    String clave = rs.getString("clave");
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    Date fecha_nacimiento = rs.getDate("fecha_nacimiento");
                    boolean baja = rs.getBoolean("baja");
                    
                    // Crear el objeto UsuarioVO con los datos recuperados
                    usuario = new UsuarioVO();
                    usuario.setId(id);
                    usuario.setRol_id(rol_id);
                    usuario.setEmail(email);
                    usuario.setClave(clave);
                    usuario.setNombre(nombre);
                    usuario.setApellidos(apellidos);
                    usuario.setFecha_nacimiento(fecha_nacimiento);
                    usuario.setBaja(baja);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Conexion.desconectar();
        return usuario;
    }
	
	public static String obtenerNombrePorEmail(String email) {
        String nombre = null;
        conexion = Conexion.getConexion();
        
        try {
            if (conexion != null) {
                String query = "SELECT nombre FROM usuarios WHERE email = ?";
                
                PreparedStatement ps = conexion.prepareStatement(query);
                ps.setString(1, email);
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Conexion.desconectar();
        return nombre;
    }

}
