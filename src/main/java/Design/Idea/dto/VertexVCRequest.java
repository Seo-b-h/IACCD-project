/*
 * 클래스 기능 : 사용자의 정점에 대한 요청 정보를 저장하는 dto 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VertexVCRequest {
    @NotNull(message = "위도는 필수 입니다.")
    private double latitude;
    @NotNull(message = "경도는 필수 입니다.")
    private double longitude;
}
