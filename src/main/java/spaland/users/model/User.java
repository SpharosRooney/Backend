package spaland.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spaland.utility.BaseTimeEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
// 상속을 받으면 여기 없는 것들도 필드로 받아올 수 있다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userId; //uuid
    @Column(nullable = false)
    private String email;
    private String password;
    private String phone;
    @Column(nullable = false, length = 25)
    private String name;
    private String address;
    private String nickname;
    private Boolean agree;
    private Integer grade;

}
