package spaland.Response;

import lombok.Data;

@Data
public class Message {
    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.OK;
        this.data = null;
        this.message = null;
    }
}
