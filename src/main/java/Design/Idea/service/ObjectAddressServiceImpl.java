/*
 * 클래스 기능 : 건물 주소 정보 편집을 위한 ObjectAddressService 구현체
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.service;

import Design.Idea.model.ObjectAddress;
import Design.Idea.repository.ObjectAddressRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ObjectAddressServiceImpl implements ObjectAddressService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectAddressRepository objectAddressRepository;

    @Override
    @Transactional
    public ObjectAddress save(String address) {
        logger.info("Save address, address: {}", address);
        ObjectAddress objectAddress = ObjectAddress.createObjectAddress(address);
        objectAddressRepository.save(objectAddress);
        return objectAddress;
    }

    @Override
    public ObjectAddress findById(Long id) {
        logger.info("Find address by id, id: {}", id);
        return objectAddressRepository.findById(id);
    }

    @Override
    public List<ObjectAddress> findAll() {
        logger.info("Find all address");
        return objectAddressRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Delete address by id, id: {}", id);
        objectAddressRepository.deleteById(id);
    }
}
