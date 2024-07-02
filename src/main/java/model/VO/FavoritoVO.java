package model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FavoritoVO {
	
	int id;
	int producto_id;
	int usuario_id;
	boolean baja;

}
