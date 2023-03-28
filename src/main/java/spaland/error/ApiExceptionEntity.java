package spaland.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class ApiExceptionEntity {
    private String errorCode;
    private String message;
    private HttpStatus status;

    @Builder
    public ApiExceptionEntity(HttpStatus status, String errorCode, String message){
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
    }
}
