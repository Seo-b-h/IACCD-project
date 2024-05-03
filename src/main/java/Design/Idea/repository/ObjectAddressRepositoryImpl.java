/*
 * 클래스 기능 : 건물 주소 정보 편집을 위한 ObjectAddressRepository 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.repository;

import Design.Idea.model.ObjectAddress;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ObjectAddressRepositoryImpl implements ObjectAddressRepository{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager em;

    @Override
    public ObjectAddress save(ObjectAddress address) {
        logger.info("Save object address, id: {}", address.getId());
        em.persist(address);
        return address;
    }

    @Override
    public ObjectAddress findById(Long id) {
        logger.info("Find object address by id, id: {}", id);
        return em.find(ObjectAddress.class, id);
    }

    @Override
    public List<ObjectAddress> findAll() {
        logger.info("Find all object address");
        return em.createQuery("select a from ObjectAddress a" +
                " order by a.id", ObjectAddress.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete object address by id, id: {}", id);
        ObjectAddress objectAddress = findById(id);
        em.remove(objectAddress);
    }
}
