/*
 * 클래스 기능 : 건물 정보 편집을 위한 repository interface
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.repository;

import Design.Idea.model.Objects;

public interface ObjectsRepository {

    public Objects save(Objects objects);

    public Objects findById(Long id);

    public void deleteById(Long id);
}
