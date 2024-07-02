package model.VO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TransaccionVO {
	
	int id;
	int comprador_id;
	int vendedor_id;
	int producto_id;
	String metodo_pago;
	Double total;
	Timestamp fecha;
	String estado;

}
