/*
 * 클래스 기능 : 도보 정점 정보 편집을 위한 service interface
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.service;

import Design.Idea.dto.VertexDto;
import Design.Idea.model.SidewalkVertex;

import java.util.List;

public interface SidewalkVertexService {

    public SidewalkVertex save(double latitude, double longitude);

    public SidewalkVertex findById(Long id);

    public List<VertexDto> findAll();

    public void deleteById(Long id);
}
