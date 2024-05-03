/*
 * 클래스 기능 : 도로 정점 정보 편집을 위한 RoadVertexService 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.service;

import Design.Idea.dto.VertexDto;
import Design.Idea.model.Objects;
import Design.Idea.model.RoadVertex;
import Design.Idea.repository.ObjectsRepository;
import Design.Idea.repository.RoadVertexRepository;
import Design.Idea.repository.SidewalkVertexRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoadVertexServiceImpl implements RoadVertexService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadVertexRepository roadVertexRepository;

    private final SidewalkVertexRepository sidewalkVertexRepository;

    private final ObjectsRepository objectsRepository;

    @Override
    @Transactional
    public RoadVertex save(double latitude, double longitude) {
        logger.info("Save road vertex, latitude: {}, longitude: {}", latitude, longitude);
        RoadVertex roadVertex = RoadVertex.createRoadVertex(latitude, longitude);
        roadVertexRepository.save(roadVertex);
        return roadVertex;
    }

    @Override
    public RoadVertex findById(Long id) {
        logger.info("Find road vertex by id, id: {}", id);
        return roadVertexRepository.findById(id);
    }

    @Override
    public List<VertexDto> findAll() {
        logger.info("Find all road vertex");
        return roadVertexRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Delete road vertex by id, id: {}", id);
        Objects objects = roadVertexRepository.findById(id).getObject();
        if (objects != null) {
            sidewalkVertexRepository.deleteById(objects.getSidewalkVertex().getId());
            objectsRepository.deleteById(objects.getId());
        }
        roadVertexRepository.deleteById(id);
    }
}
