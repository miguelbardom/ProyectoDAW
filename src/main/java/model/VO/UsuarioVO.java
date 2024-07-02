package model.VO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioVO {
	
	int id;
	int rol_id;
	String email;
	String clave;
	String nombre;
	String apellidos;
	Date fecha_nacimiento;
//	String direccion;
//	String provincia;
//	String localidad;
//	String telefono;
//	String dni;
	boolean baja;
}
