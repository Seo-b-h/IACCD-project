/*
 * 클래스 기능 : 건물에 대한 정보를 JSON 형식으로 반환하거나 수정할 수 있는 API 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.api;

import Design.Idea.dto.ObjectVCRequest;
import Design.Idea.dto.ObjectVCResponse;
import Design.Idea.model.Objects;
import Design.Idea.service.ObjectAddressService;
import Design.Idea.service.ObjectsService;
import Design.Idea.service.RoadVertexService;
import Design.Idea.service.SidewalkVertexService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ObjectsApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectAddressService objectAddressService;

    private final ObjectsService objectsService;

    private final RoadVertexService roadVertexService;

    private final SidewalkVertexService sidewalkVertexService;

    @GetMapping("/object/{id}")
    public ObjectVCResponse getObjectInfo(@PathVariable(value = "id") Long objectId) {
        Objects objects = objectsService.findById(objectId);
        return new ObjectVCResponse(
                objects.getId(),
                objects.getRoadVertex().getId(),
                objects.getSidewalkVertex().getId(),
                objects.getRoadVertex().getLatitude(),
                objects.getRoadVertex().getLongitude());
    }

    @PostMapping("/object")
    public Long addNewObject(@RequestBody @Valid ObjectVCRequest form) {
        logger.info("{}", form);
        Long addressId, RId = form.getRoadVertexId(), SWId = form.getSidewalkVertexId();
        if (form.getExistingAddressId() != null) addressId = form.getExistingAddressId();
        else addressId = objectAddressService.save(form.getNewAddress()).getId();

        if (form.getSidewalkVertexId() == null) SWId = sidewalkVertexService.save(form.getLatitude(), form.getLongitude()).getId();
        if (form.getRoadVertexId() == null) RId = roadVertexService.save(form.getLatitude(), form.getLongitude()).getId();

        Objects objects;
        if (form.getId() == null) objects = objectsService.save(form.getName(), form.getDescription(), form.getObjType(), addressId, RId, SWId);
        else objects = objectsService.changeInfo(form.getId(), form.getName(), form.getDescription(), form.getObjType(), addressId, RId, SWId);

        return objects.getId();
    }
}
