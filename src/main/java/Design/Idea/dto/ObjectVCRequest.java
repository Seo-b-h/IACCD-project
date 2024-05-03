/*
 * 클래스 기능 : 사용자의 오브젝트에 대한 요청 정보를 저장하는 dto 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.dto;

import Design.Idea.model.ObjType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ObjectVCRequest {
    private Long id;
    @NotNull(message = "이름은 필수 입니다.")
    private String name;
    private String description;
    @NotNull(message = "종류는 필수 입니다.")
    private ObjType objType;
    private Long existingAddressId;
    private String newAddress;
    private Long roadVertexId;
    private Long sidewalkVertexId;
    private double latitude;
    private double longitude;
}
