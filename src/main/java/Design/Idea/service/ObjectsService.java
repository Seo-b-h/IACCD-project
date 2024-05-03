/*
 * 클래스 기능 : 건물 정보 편집을 위한 service interface
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.service;

import Design.Idea.model.ObjType;
import Design.Idea.model.Objects;

public interface ObjectsService {

    public Objects save(String name, String description, ObjType objectType, Long addressId, Long RId, Long SWId);

    public Objects changeInfo(Long id, String name, String description, ObjType objectType, Long addressId, Long RId, Long SWId);

    public Objects findById(Long id);

    public void deleteById(Long id);
}
