package model.VO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MensajeVO {
	
	int id;
	int emisor_id;
	int receptor_id;
	String texto;
	Timestamp fecha_envio;

}
