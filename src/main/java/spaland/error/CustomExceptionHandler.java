package spaland.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(ApiException e){
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
//
//    @ExceptionHandler({ApiException.class})
//    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, ApiException e) {
//        return ResponseEntity
//                .status(e.getErrorCode().getStatus())
//                .body(ApiExceptionEntity.builder()
//                        .errorCode(e.getErrorCode().getCode())
//                        .message(e.getErrorCode().getMessage())
//                        .build());
//    }
//
//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
//        e.printStackTrace();
//        return ResponseEntity
//                .status(ErrorCode.MEMBER_AUTHENTICATION.getStatus())
//                .body(ApiExceptionEntity.builder()
//                        .errorCode(ErrorCode.MEMBER_AUTHENTICATION.getCode())
//                        .message(e.getMessage())
//                        .build());
//    }
//
//    @ExceptionHandler({AccessDeniedException.class})
//    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
//        e.printStackTrace();
//        return ResponseEntity
//                .status(ErrorCode.MEMBER_AUTHORIZATION.getStatus())
//                .body(ApiExceptionEntity.builder()
//                        .errorCode(ErrorCode.MEMBER_AUTHORIZATION.getCode())
//                        .message(e.getMessage())
//                        .build());
//    }
//
//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
//        e.printStackTrace();
//        return ResponseEntity
//                .status(ErrorCode.MEMBER_INVALID.getStatus())
//                .body(ApiExceptionEntity.builder()
//                        .errorCode(ErrorCode.MEMBER_INVALID.getCode())
//                        .message(e.getMessage())
//                        .build());
//    }
}
