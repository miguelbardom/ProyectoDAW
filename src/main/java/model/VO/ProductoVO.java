package model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductoVO {
	
	int id;
	int usuario_id;
	String nombre;
	String descripcion;
	int categoria_id;
	Double precio;
	Double impuesto;
	String url_imagen;
	boolean baja;

}
