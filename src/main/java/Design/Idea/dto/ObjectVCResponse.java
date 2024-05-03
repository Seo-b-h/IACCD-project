/*
 * 클래스 기능 : 오브젝트에 대한 반환 정보를 저장하는 dto 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectVCResponse {
    private Long id;
    private Long roadVertexId;
    private Long sidewalkVertexId;
    private double latitude;
    private double longitude;
}
