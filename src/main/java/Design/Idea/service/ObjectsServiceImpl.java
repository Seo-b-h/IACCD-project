/*
 * 클래스 기능 : 건물 정보 편집을 위한 ObjectsService 구현체
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.service;

import Design.Idea.model.*;
import Design.Idea.repository.ObjectAddressRepository;
import Design.Idea.repository.ObjectsRepository;
import Design.Idea.repository.RoadVertexRepository;
import Design.Idea.repository.SidewalkVertexRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ObjectsServiceImpl implements ObjectsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectAddressRepository objectAddressRepository;

    private final ObjectsRepository objectsRepository;

    private final RoadVertexRepository roadVertexRepository;

    private final SidewalkVertexRepository sidewalkVertexRepository;

    @Override
    @Transactional
    public Objects save(String name, String description, ObjType objectType, Long addressId, Long RId, Long SWId) {
        logger.info("Save object, name: {}", name);
        SidewalkVertex sidewalkVertex = sidewalkVertexRepository.findById(SWId);
        RoadVertex roadVertex = roadVertexRepository.findById(RId);
        Objects objects = Objects.createObjects(name, description, objectType, objectAddressRepository.findById(addressId));

        roadVertex.changeObjects(objects);
        sidewalkVertex.changeObjects(objects);
        return objects;
    }

    @Override
    @Transactional
    public Objects changeInfo(Long id, String name, String description,ObjType objectType, Long addressId, Long RId, Long SWId) {
        logger.info("change object, name: {}", name);
        SidewalkVertex sidewalkVertex = sidewalkVertexRepository.findById(SWId);
        RoadVertex roadVertex = roadVertexRepository.findById(RId);
        Objects objects = objectsRepository.findById(id);

        objects.changeName(name);
        objects.changeDescription(description);
        objects.changeObjectType(objectType);
        objects.changeObjectAddress(objectAddressRepository.findById(addressId));
        roadVertex.changeObjects(objects);
        sidewalkVertex.changeObjects(objects);
        return objects;
    }

    @Override
    public Objects findById(Long id) {
        logger.info("Find object by id, id: {}", id);
        return objectsRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Delete object by id, id: {}", id);
        objectsRepository.deleteById(id);
    }
}
