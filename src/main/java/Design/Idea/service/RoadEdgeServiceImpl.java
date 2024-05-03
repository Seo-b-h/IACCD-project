/*
 * 클래스 기능 : 도로 간선 정보 편집을 위한 RoadEdgeService 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.service;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.RoadEdge;
import Design.Idea.repository.RoadEdgeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoadEdgeServiceImpl implements RoadEdgeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadEdgeRepository roadEdgeRepository;


    @Override
    @Transactional
    public RoadEdge save(Long roadVertex1, Long roadVertex2, double length) {
        logger.info("Save road edge, roadVertex1: {}, roadVertex2: {}, length: {}", roadVertex1, roadVertex2, length);
        RoadEdge roadEdge = RoadEdge.createRoadEdge(roadVertex1, roadVertex2, length);
        roadEdgeRepository.save(roadEdge);
        return roadEdge;
    }

    @Override
    @Transactional
    public void changeOneWayType(Long id) {
        logger.info("Change road edge's onewayType, id: {}", id);
        RoadEdge edge1 = roadEdgeRepository.findById(id);
        Optional<RoadEdge> edge2 = roadEdgeRepository.findByVertices(edge1.getRoadVertex2(), edge1.getRoadVertex1()).stream().findFirst();
        if (edge2.isPresent()) {
            deleteById(edge2.get().getId());
        } else {
            roadEdgeRepository.save(RoadEdge.createRoadEdge(edge1.getRoadVertex2(), edge1.getRoadVertex1(), edge1.getLength()));
        }
    }

    @Override
    public RoadEdge findById(Long id) {
        logger.info("Find road edge by id, id: {}", id);
        return roadEdgeRepository.findById(id);
    }

    @Override
    public List<EdgeDto> findAll() {
        logger.info("Find all road edge");
        return roadEdgeRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Delete road edge by id, id: {}", id);
        roadEdgeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByVertices(Long roadVertex1, Long roadVertex2) {
        logger.info("Delete road edge by vertices, vertex1: {}, vertex2: {}", roadVertex1, roadVertex2);
        roadEdgeRepository.deleteByVertices(roadVertex1, roadVertex2);
    }
}
