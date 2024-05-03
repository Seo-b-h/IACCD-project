/*
 * 클래스 기능 : 도로 간선 정보 편집을 위한 service interface
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.service;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.RoadEdge;

import java.util.List;

public interface RoadEdgeService {

    public RoadEdge save(Long roadVertex1, Long roadVertex2, double length);

    public void changeOneWayType(Long id);

    public RoadEdge findById(Long id);

    public List<EdgeDto> findAll();

    public void deleteById(Long id);

    public void deleteByVertices(Long roadVertex1, Long roadVertex2);
}
