package spaland.api.email.vo;

import lombok.Data;

@Data
public class RequestCheckCode {
    private String userEmail;
    private String confirmKey;
}
