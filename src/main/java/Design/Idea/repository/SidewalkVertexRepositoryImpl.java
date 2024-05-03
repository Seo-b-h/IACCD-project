/*
 * 클래스 기능 : 도보 정점 정보 편집을 위한 SidewlakVertexRepository 구현체
 * 최근 수정 일자 : 2024.05.02(목)
 */
package Design.Idea.repository;

import Design.Idea.dto.VertexDto;
import Design.Idea.model.SidewalkVertex;
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
public class SidewalkVertexRepositoryImpl implements SidewalkVertexRepository{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager em;

    @Override
    public SidewalkVertex save(SidewalkVertex sidewalkVertex) {
        logger.info("Save sidewalk vertex, id: {}", sidewalkVertex.getId());
        em.persist(sidewalkVertex);
        return sidewalkVertex;
    }

    @Override
    public SidewalkVertex findById(Long id) {
        logger.info("Find sidewalk vertex by id, id: {}", id);
        return em.find(SidewalkVertex.class, id);
    }

    @Override
    public List<VertexDto> findAll() {
        logger.info("Find all sidewalk vertex");
        return em.createQuery("select new Design.Idea.dto.VertexDto(v.id,v.latitude,v.longitude, new Design.Idea.dto.ObjectDto(o.id, o.name,o.description,o.objectType, a.address)) from SidewalkVertex v" +
                        " left join v.object o" +
                        " left join o.objectAddress a" +
                        " order by v.id", VertexDto.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete sidewalk vertex by id, id: {}", id);
        StoredProcedureQuery query = em.createStoredProcedureQuery("SWVTX_DEL_PROCEDURE");
        query.registerStoredProcedureParameter("ID", Long.class, ParameterMode.IN);
        query.setParameter("ID", id);
        query.execute();
    }
}
