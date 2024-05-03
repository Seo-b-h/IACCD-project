/*
 * 클래스 기능 : 도보 정점 정보 편집을 위한 SidewalkVertexService 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.service;

import Design.Idea.dto.VertexDto;
import Design.Idea.model.Objects;
import Design.Idea.model.SidewalkVertex;
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
public class SidewalkVertexServiceImpl implements SidewalkVertexService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SidewalkVertexRepository sidewalkVertexRepository;

    private final RoadVertexRepository roadVertexRepository;

    private final ObjectsRepository objectsRepository;

    @Override
    @Transactional
    public SidewalkVertex save(double latitude, double longitude) {
        logger.info("Save sidewalk vertex, latitude: {}, longitude: {}", latitude, longitude);
        SidewalkVertex sidewalkVertex = SidewalkVertex.createSidewalkVertex(latitude,longitude);
        sidewalkVertexRepository.save(sidewalkVertex);
        return sidewalkVertex;
    }

    @Override
    public SidewalkVertex findById(Long id) {
        logger.info("Find sidewalk vertex by id, id: {}", id);
        return sidewalkVertexRepository.findById(id);
    }

    @Override
    public List<VertexDto> findAll() {
        logger.info("Find all sidewalk vertex");
        return sidewalkVertexRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Delete sidewalk vertex by id, id: {}", id);
        Objects objects = sidewalkVertexRepository.findById(id).getObject();
        if(objects != null) {
            roadVertexRepository.deleteById(objects.getRoadVertex().getId());
            objectsRepository.deleteById(objects.getId());
        }
        sidewalkVertexRepository.deleteById(id);
    }
}
