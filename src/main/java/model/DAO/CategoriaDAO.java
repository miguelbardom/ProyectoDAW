package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.CategoriaVO;

public class CategoriaDAO {
	
	private static Connection conexion;
	
	public static List<CategoriaVO> getAll() {
		
		conexion = Conexion.getConexion();
        List<CategoriaVO> results = new ArrayList<>();
        
        String sql = "SELECT * FROM categorias";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
            	CategoriaVO categoria = new CategoriaVO();
            	categoria.setId(resultSet.getInt("id"));
            	categoria.setNombre(resultSet.getString("nombre"));
            	categoria.setDescripcion(resultSet.getString("descripcion"));
            	categoria.setBaja(resultSet.getBoolean("baja"));

                // Agregar el objeto a la lista de resultados
                results.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return results;
    }
	
	public static CategoriaVO findByID(int id) {
		
		CategoriaVO categoria = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM categorias WHERE id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				categoria = new CategoriaVO();
				
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				categoria.setDescripcion(rs.getString("descripcion"));
				categoria.setBaja(rs.getBoolean("baja"));
				
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
		Conexion.desconectar();
		return categoria;
		
	}
	
}

