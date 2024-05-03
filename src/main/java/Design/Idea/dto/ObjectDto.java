/*
 * 클래스 기능 : 오브젝트에 대한 정보를 저장하는 dto 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.dto;

import Design.Idea.model.ObjType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectDto {
    private Long id;
    private String name;
    private String description;
    private ObjType objectType;
    private String address;
}
