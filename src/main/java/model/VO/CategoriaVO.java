package model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoriaVO {
	
	int id;
	String nombre;
	String descripcion;
	boolean baja;

}
