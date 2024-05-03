/*
 * 클래스 기능 : 정점에 대한 정보를 저장하는 dto 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VertexDto {
    private Long id;
    private double latitude;
    private double longitude;
    private ObjectDto objectDto;
}
