/*
 * 클래스 기능 : 도로 간선 정보 편집을 위한 RoadEdgeRepository 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.repository;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.RoadEdge;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoadEdgeRepositoryImpl implements RoadEdgeRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager em;

    @Override
    public RoadEdge save(RoadEdge roadEdge) {
        logger.info("Save road edge, id: {}", roadEdge.getId());
        em.persist(roadEdge);
        return roadEdge;
    }

    @Override
    public RoadEdge findById(Long id) {
        logger.info("Find road edge by id, id: {}", id);
        return em.find(RoadEdge.class, id);
    }

    @Override
    public List<RoadEdge> findByVertices(Long roadVertex1, Long roadVertex2) {
        logger.info("Find road edge by vertices, roadVertex1: {}, roadVertex2: {}", roadVertex1, roadVertex2);
        return em.createQuery("select e from RoadEdge e" +
                " where e.roadVertex1 = :roadVertex1" +
                " and e.roadVertex2 = :roadVertex2", RoadEdge.class)
                .setParameter("roadVertex1", roadVertex1)
                .setParameter("roadVertex2", roadVertex2)
                .getResultList();
    }

    @Override
    public List<EdgeDto> findAll() {
        logger.info("Find all road edge");
        return em.createQuery("select new Design.Idea.dto.EdgeDto(e.id, e.roadVertex1, e.roadVertex2, e.length) from RoadEdge e", EdgeDto.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete road edge by id, id: {}", id);
        RoadEdge roadEdge = findById(id);
        if(roadEdge != null) em.remove(roadEdge);
    }

    @Override
    public void deleteByVertices(Long roadVertex1, Long roadVertex2) {
        logger.info("Delete road edge by vertices, vertex1: {}, vertex2: {}", roadVertex1, roadVertex2);
        em.createQuery("delete from RoadEdge e" +
                        " where e.roadVertex1 = :roadVertex1" +
                        " and e.roadVertex2 = :roadVertex2")
                .setParameter("roadVertex1", roadVertex1)
                .setParameter("roadVertex2", roadVertex2)
                .executeUpdate();
    }
}
