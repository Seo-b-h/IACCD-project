/*
 * 클래스 기능 : 도보 간선 정보 편집을 위한 repository interface
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.repository;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.SidewalkEdge;

import java.util.List;

public interface SidewalkEdgeRepository {

    public SidewalkEdge save(SidewalkEdge sidewalkEdge);

    public SidewalkEdge findById(Long id);

    public List<SidewalkEdge> findByVertices(Long sidewalkVertex1, Long sidewalkVertex2);

    public List<EdgeDto> findAll();

    public void deleteById(Long id);

    public void deleteByVertices(Long sidewalkVertex1, Long sidewalkVertex2);
}
