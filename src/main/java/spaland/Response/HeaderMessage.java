package spaland.Response;

import lombok.Data;
import org.springframework.util.MultiValueMap;

@Data
public class HeaderMessage {
    private StatusEnum status;
    private String message;
    private Object data;
    private MultiValueMap<String, String> headers;

    public HeaderMessage() {
        this.status = StatusEnum.OK;
        this.data = null;
        this.message = null;
        this.headers = null;
    }

}
