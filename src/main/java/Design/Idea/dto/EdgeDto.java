/*
 * 클래스 기능 : 간선에 대한 정보를 저장하는 dto 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EdgeDto {
    private Long id;
    private Long vertex1;
    private Long vertex2;
    private double length;
}
