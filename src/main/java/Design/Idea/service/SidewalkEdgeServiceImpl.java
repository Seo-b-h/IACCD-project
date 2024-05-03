/*
 * 클래스 기능 : 도보 간선 정보 편집을 위한 SidewalkEdgeService 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.service;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.SidewalkEdge;
import Design.Idea.repository.SidewalkEdgeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SidewalkEdgeServiceImpl implements SidewalkEdgeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SidewalkEdgeRepository sidewalkEdgeRepository;

    @Override
    @Transactional
    public SidewalkEdge save(Long sidewalkVertex1, Long sidewalkVertex2, double length) {
        logger.info("Save sidewalk edge, sidewalkVertex1: {}, sidewalkVertex2: {}, length: {}", sidewalkVertex1, sidewalkVertex2, length);
        SidewalkEdge sidewalkEdge = SidewalkEdge.createSidewalkEdge(sidewalkVertex1, sidewalkVertex2, length);
        sidewalkEdgeRepository.save(sidewalkEdge);
        return sidewalkEdge;
    }

    @Override
    public SidewalkEdge findById(Long id) {
        logger.info("Find sidewalk edge by id, id: {}", id);
        return sidewalkEdgeRepository.findById(id);
    }

    @Override
    public List<EdgeDto> findAll() {
        logger.info("Find all sidewalk edge");
        return sidewalkEdgeRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Delete sidewalk edge by id, id: {}", id);
        sidewalkEdgeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByVertices(Long sidewalkVertex1, Long sidewalkVertex2) {
        logger.info("Delte sidewalk edge by vertices, vertex1: {}, vertex2: {}", sidewalkVertex1, sidewalkVertex2);
        sidewalkEdgeRepository.deleteByVertices(sidewalkVertex1, sidewalkVertex2);
    }
}
