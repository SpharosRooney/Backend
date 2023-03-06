package spaland.products.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString // 객체 값을 로그로 찍으면 보여주는 역할
public class CategoryDTO {

    private Integer id;
    private String name;
    private String type;
}
