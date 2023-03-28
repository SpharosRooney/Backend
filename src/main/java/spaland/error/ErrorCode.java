package spaland.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_INVALID(HttpStatus.NOT_FOUND,"MEMBER_001", "이메일 혹은 비밀번호가 틀립니다."),
    MEMBER_AUTHORIZATION(HttpStatus.UNAUTHORIZED,"MEMBER_002", "로그인이 필요합니다."),
    MEMBER_AUTHENTICATION(HttpStatus.FORBIDDEN,"MEMBER_003", "인증되지 않는 사용자입니다.");

    private final String code;
    private final String message;
    private final HttpStatus status;


    ErrorCode(HttpStatus status, String code, String message) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
