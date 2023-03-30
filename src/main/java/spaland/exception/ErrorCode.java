package spaland.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    /*
    400 BAD_REQUEST : 잘못된 요청
    401 UNAUTHORIZED : 인증되지 않은 사용자
    403 FORBIDDEN : 접근할 권한이 없음, 반대로 생각하면 권한이 필요하다라는 의미로, 이 정보 조차 제공하지 않기 위해 404를 많이 씀.
    404 NOT_FOUND : Resource 를 찾을 수 없음
    409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재
    */

    /* email    */
    DIFFERENT_CONFIRM_KEY(NOT_FOUND,"인증번호 미일치"),
    NULL_MAIL_ADMIN(NOT_FOUND, "인증번호 관리자가 등록되지 않았습니다."),
    DUPLICATE_EMAIL(CONFLICT, "이미 가입된 메일입니다"),

    /* users    */
    INVALID_MEMBER_INFO(NOT_FOUND,"유저 정보가 일치하지 않습니다."),
    INVALID_MEMBER(NOT_FOUND,"등록되지 않은 유저입니다."),


    /* shipping */

    /* products */

    /* history  */

    /* auth     */

    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),


    /* cart     */


    /* 400 BAD_REQUEST : 잘못된 요청 */
    CANNOT_FOLLOW_MYSELF(BAD_REQUEST, "자기 자신은 팔로우 할 수 없습니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),
    NOT_FOLLOW(NOT_FOUND, "팔로우 중이지 않습니다"),
    TEST(NOT_FOUND, "지정된 히스토리 번호가 존재하지 않습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),

    ;

    private final HttpStatus httpStatus;
    private final String detail;

}
