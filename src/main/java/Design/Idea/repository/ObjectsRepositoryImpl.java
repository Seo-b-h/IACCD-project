/*
 * 클래스 기능 : 건물 정보 편집을 위한 ObjectsRepository 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.repository;

import Design.Idea.model.Objects;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ObjectsRepositoryImpl implements ObjectsRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager em;

    @Override
    public Objects save(Objects objects) {
        logger.info("Save objects, id: {}", objects.getId());
        em.persist(objects);
        return objects;
    }

    @Override
    public Objects findById(Long id) {
        logger.info("Find objects by id, id: {}", id);
        return em.find(Objects.class, id);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete objects by id, id: {}", id);
        em.createQuery("delete from Objects o" +
                " where o.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
