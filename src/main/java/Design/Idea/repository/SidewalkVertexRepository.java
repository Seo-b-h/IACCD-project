/*
 * 클래스 기능 : 도보 정점 정보 편집을 위한 repository interface
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.repository;

import Design.Idea.dto.VertexDto;
import Design.Idea.model.SidewalkVertex;

import java.util.List;

public interface SidewalkVertexRepository {

    public SidewalkVertex save(SidewalkVertex sidewalkVertex);

    public SidewalkVertex findById(Long id);

    public List<VertexDto> findAll();

    public void deleteById(Long id);
}
