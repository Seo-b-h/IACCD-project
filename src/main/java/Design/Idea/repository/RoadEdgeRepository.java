/*
 * 클래스 기능 : 도로 간선 정보 편집을 위한 repository interface
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.repository;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.RoadEdge;

import java.util.List;

public interface RoadEdgeRepository {

    public RoadEdge save(RoadEdge roadEdge);

    public RoadEdge findById(Long id);

    public List<RoadEdge> findByVertices(Long roadVertex1, Long roadVertex2);

    public List<EdgeDto> findAll();

    public void deleteById(Long id);

    public void deleteByVertices(Long roadVertex1, Long roadVertex2);
}
