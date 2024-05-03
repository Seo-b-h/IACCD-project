/*
 * 클래스 기능 : 도보 간선 정보 편집을 위한 SidewalkEdgeRepository 구현체
 * 최근 수정 일자 : 2024.05.02(목)
 */
package Design.Idea.repository;

import Design.Idea.dto.EdgeDto;
import Design.Idea.model.SidewalkEdge;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SidewalkEdgeRepositoryImpl implements SidewalkEdgeRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager em;

    @Override
    public SidewalkEdge save(SidewalkEdge sidewalkEdge) {
        logger.info("Save sidewalk edge, id: {}", sidewalkEdge.getId());
        em.persist(sidewalkEdge);
        return sidewalkEdge;
    }

    @Override
    public SidewalkEdge findById(Long id) {
        logger.info("Find sidewalk edge by id, id: {}", id);
        return em.find(SidewalkEdge.class, id);
    }

    @Override
    public List<SidewalkEdge> findByVertices(Long sidewalkVertex1, Long sidewalkVertex2) {
        logger.info("Find sidewalk edge by vertices, sidewalkVertex1: {}, sidewalkVertex2: {}", sidewalkVertex1, sidewalkVertex2);
        return em.createQuery("select e from SidewalkEdge e" +
                " where e.sidewalkVertex1 = :sidewalkVertex1" +
                " and e.sidewalkVertex2 = : sidewalkVertex2", SidewalkEdge.class)
                .setParameter("sidewalkVertex1", sidewalkVertex1)
                .setParameter("sidewalkVertex2", sidewalkVertex2)
                .getResultList();
    }

    @Override
    public List<EdgeDto> findAll() {
        logger.info("Find all sidewalk edge");
        return em.createQuery("select new Design.Idea.dto.EdgeDto(e.id, e.sidewalkVertex1, e.sidewalkVertex2, e.length) from SidewalkEdge e", EdgeDto.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete sidewalk edge by id, id: {}", id);
        SidewalkEdge sidewalkEdge = findById(id);
        if(sidewalkEdge != null) em.remove(sidewalkEdge);
    }

    @Override
    public void deleteByVertices(Long sidewalkVertex1, Long sidewalkVertex2) {
        logger.info("Delete sidewalk edge by vertices, vertex1: {}, vertex2: {}", sidewalkVertex1, sidewalkVertex2);
        em.createQuery("delete from SidewalkEdge e" +
                " where e.sidewalkVertex1 = :sidewalkVertex1" +
                " and e.sidewalkVertex2 = :sidewalkVertex2")
                .setParameter("sidewalkVertex1", sidewalkVertex1)
                .setParameter("sidewalkVertex2", sidewalkVertex2)
                .executeUpdate();
    }
}
