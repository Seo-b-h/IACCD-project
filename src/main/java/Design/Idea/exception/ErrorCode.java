/*
 * 클래스 기능 : 예외 발생 enum 클래스
 * 최근 수정 일자 : 2024.03.18(금)
 */
package Design.Idea.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "-400", "올바른 값을 입력하지 않은 경우"),
    ROOM_EXCEEDED(403, "-403", "방 최대 인원인 5명보다 많은 사람을 초대하는 경우");

    private final int status;
    private final String code;
    private final String description;
}
