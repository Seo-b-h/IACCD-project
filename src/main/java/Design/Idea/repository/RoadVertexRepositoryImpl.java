/*
 * 클래스 기능 : 도로 정점 정보 편집을 위한 RoadVertexRepository 구현체
 * 최근 수정 일자 : 2024.05.02(목)
 */
package Design.Idea.repository;

import Design.Idea.dto.VertexDto;
import Design.Idea.model.RoadVertex;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoadVertexRepositoryImpl implements RoadVertexRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager em;

    @Override
    public RoadVertex save(RoadVertex roadVertex) {
        logger.info("Save road vertex, id: {}", roadVertex.getId());
        em.persist(roadVertex);
        return roadVertex;
    }

    @Override
    public RoadVertex findById(Long id) {
        logger.info("Find road vertex by id, id: {}", id);
        return em.find(RoadVertex.class, id);
    }

    @Override
    public List<VertexDto> findAll() {
        logger.info("Find all road vertex");
        return em.createQuery("select new Design.Idea.dto.VertexDto(v.id,v.latitude,v.longitude, new Design.Idea.dto.ObjectDto(o.id, o.name,o.description,o.objectType, a.address)) from RoadVertex v" +
                        " left join v.object o" +
                        " left join o.objectAddress a" +
                        " order by v.id", VertexDto.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete road vertex by id, id: {}", id);
        StoredProcedureQuery query = em.createStoredProcedureQuery("RVTX_DEL_PROCEDURE");
        query.registerStoredProcedureParameter("ID", Long.class, ParameterMode.IN);
        query.setParameter("ID", id);
        query.execute();
    }
}
