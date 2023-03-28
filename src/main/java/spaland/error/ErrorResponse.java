package spaland.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Builder
public class ErrorResponse {
    private String message;
    private String code;
    private HttpStatus status;


    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponse.builder()
                        .status(e.getStatus())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build()
                );
    }
}
