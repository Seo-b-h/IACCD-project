/*
 * 클래스 기능 : 도보 간선 정보 편집을 위한 service interface
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.service;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.SidewalkEdge;

import java.util.List;

public interface SidewalkEdgeService {

    public SidewalkEdge save(Long sidewalkVertex1, Long sidewalkVertex2, double length);

    public SidewalkEdge findById(Long id);

    public List<EdgeDto> findAll();

    public void deleteById(Long id);

    public void deleteByVertices(Long sidewalkVertex1, Long sidewalkVertex2);
}
