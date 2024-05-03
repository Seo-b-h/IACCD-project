/*
 * 클래스 기능 : 사용자의 간선에 대한 요청 정보를 저장하는 dto 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EdgeVCRequest {
    @NotNull(message = "정점 1은 필수 입니다.")
    private Long vertex1;
    @NotNull(message = "정점 2는 필수 입니다.")
    private Long vertex2;
    private double length;
}
