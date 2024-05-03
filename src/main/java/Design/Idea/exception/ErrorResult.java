/*
 * 클래스 기능 : 예외가 발생했을 때 JSON 형태로 내용을 확인
 * 최근 수정 일자 : 2024.02.09(금)
 */
package Design.Idea.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String field;
    private String message;
}
